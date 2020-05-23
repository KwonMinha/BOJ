/**
 * @author Minha Gwon
 * @date 2020. 5. 23.
 * 리모콘  
 * https://www.acmicpc.net/problem/1107
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static boolean[] btn;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		btn = new boolean[10];
		
		for(int i = 0; i < M; i++) {
			btn[sc.nextInt()] = true;
		}
		
		int ans = Math.abs(100 - N);
		
		for(int i = 0; i < 1000000; i++) {
			int ch = i;
			int len = isBtn(ch);
			
			if(len > 0) {
				int press = Math.abs(ch - N);
				
				if(ans > press + len)
					ans = press + len;
			}
		}
		
		System.out.println(ans);
	}
	
	public static int isBtn(int ch) {
		int len = 0;
		
		if(ch == 0)
			return btn[0] ? 0 : 1;
		
		while(ch > 0) {
			if(btn[ch % 10])
				return 0;
			
			len += 1;
			ch /= 10;
		}
		
		return len;
	}
	
}
