/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 팰린드롬수
 * https://www.acmicpc.net/problem/1259
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			String num = sc.next();
			
			if(num.equals("0")) {
				break;
			}
			
			if(num.length() == 1) {
				sb.append("yes\n");
				continue;
			} 
			
			int p1 = 0;
			int p2 = num.length()-1;
			
			boolean flag = true;
			while(p1 != num.length()/2) {
				if(num.charAt(p1) != num.charAt(p2)) {
					flag = false;
					break;
				}
				p1++;
				p2--;
			}
			
			sb.append(flag == true? "yes\n" : "no\n");
		}
		
		System.out.println(sb.toString());
		
	}

}