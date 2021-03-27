/**
 * @author Minha Gwon
 * @date 2021. 3. 27.
 * 이항 계수 2
 * https://www.acmicpc.net/problem/11051
 * 
 * DP (Top-Down 방식으로 구현) 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		dp = new int[N+1][N+1];

		System.out.println(Combination(N, K));
	}

	static int Combination(int n, int k) {
		if (dp[n][k] > 0) { // 이미 풀었던 sub 문제일 경우 값을 재활용
			return dp[n][k];
		}

		if (k == 0 || n == k) {
			return dp[n][k] = 1;
		}

		return dp[n][k] = (Combination(n - 1, k - 1) + (Combination(n - 1, k))) % 10007;
	}
}