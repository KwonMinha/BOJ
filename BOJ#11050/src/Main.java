/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 이항 계수 1 
 * https://www.acmicpc.net/problem/11050
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		System.out.println(combination(N, K));
	}

	public static int combination(int n, int r) {
		if(n == r || r == 0) 
			return 1; 
		else 
			return combination(n - 1, r - 1) + combination(n - 1, r); 
	}
}