/**
 * @author Minha Gwon
 * @date 2021. 7. 10.
 * Contact
 * https://www.acmicpc.net/problem/1013
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			String str = sc.next();
			String pattern = "(100+1+|01)+";
			sb.append(str.matches(pattern) ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}

}
