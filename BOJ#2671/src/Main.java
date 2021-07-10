/**
 * @author Minha Gwon
 * @date 2021. 7. 10.
 * 잠수함식별 
 * https://www.acmicpc.net/problem/2671
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String pattern = "(100+1+|01)+";
		System.out.println(str.matches(pattern) ? "SUBMARINE" : "NOISE");
	}

}
