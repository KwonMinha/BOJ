/**
 * @author Minha Gwon
 * @date 2021. 2. 16.
 * 합분해 
 * https://www.acmicpc.net/problem/2225
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		long[][] dp = new long[K+1][N+1]; // dp[K][N] - K개를 더해서 그 합이 N이 되는 경우의 수 저장 (1부터 시작하기 때문에 크기 +1)

		for(int i = 0; i <= N; i++) { // dp 초기값 - 1개로 만드는 경우는 무조건 1개임 
			dp[1][i] = 1; 
		} 
		
		for(int i = 1; i <= K;i++) { 
			for(int j = 0; j <= N; j++) { 
				for(int l = 0; l <= j; l++) { // 마지막에 고를 수 L ( 0 <= L <= N )
					dp[i][j] += dp[i-1][j-l]; // dp[K][N] = sum (dp[K-1][N-L])
					dp[i][j] %= 1000000000;
				}
			}
		}

		System.out.println(dp[K][N]);
	}

}