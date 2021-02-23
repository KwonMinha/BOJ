/**
 * @author Minha Gwon
 * @date 2021. 2. 24.
 * 01타일
 * https://www.acmicpc.net/problem/1904
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] dp = new int[N+1];
		
		dp[1] = 1;
		
		if(N > 1)
			dp[2] = 2;
		
		for(int i = 3; i < N+1; i++) {
			dp[i] = dp[i-1] + dp[i-2];
			dp[i] %= 15746;
		}
		
		System.out.println(dp[N]);
	}

}
