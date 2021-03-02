/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 곱셈 
 * https://www.acmicpc.net/problem/2588
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
	
		System.out.println(A * (B % 10));
		System.out.println(A * (B % 100 / 10));
		System.out.println(A * (B / 100));
		System.out.println(A * B);
		
//		int A = sc.nextInt();
//		String B = sc.next();
//		
//		System.out.println(A * (B.charAt(2) - '0'));
//		System.out.println(A * (B.charAt(1) - '0'));
//		System.out.println(A * (B.charAt(0) - '0'));
//		System.out.println(A * Integer.parseInt(B));
	}

}