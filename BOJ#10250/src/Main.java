/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * ACM 호텔 
 * https://www.acmicpc.net/problem/10250
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			int H  = sc.nextInt();
			int W = sc.nextInt();
			int N = sc.nextInt();
			
			int x = N / H + 1;
			int y = N % H;
			
			if(N % H == 0) { // N이 H의 배수일 경우 -> 꼭대기 층에 배정
                x = N / H;
                y = H;
            }
			
			System.out.println(y * 100 + x);
		}
	}

}