/**
 * @author Minha Gwon
 * @date 2021. 3. 26.
 * 조합 
 * https://www.acmicpc.net/problem/2407
 * 
 * 조합 공식 - nCm => n! / (n-m)!m!
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		BigInteger num1 = BigInteger.ONE;
		BigInteger num2 = BigInteger.ONE;
		
		for(int i = 0; i < m; i++) {
			num1 = num1.multiply(new BigInteger(String.valueOf(n-i))); // n! / (n-m)!
			num2 = num2.multiply(new BigInteger(String.valueOf(i+1))); // m!
		}

		System.out.println(num1.divide(num2)); // n! / (n-m)! / m!
	}

}