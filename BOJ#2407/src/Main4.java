/**
 * @author Minha Gwon
 * @date 2021. 3. 27.
 * 조합 
 * https://www.acmicpc.net/problem/2407
 * 
 * DP (Top-Down 방식으로 구현) 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main4 {
	static BigInteger[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		dp = new BigInteger[n+1][n+1];

		System.out.println(Combination(n, m));
	}

	static BigInteger Combination(int n, int k) {
		if (dp[n][k] != null) { // 이미 풀었던 sub 문제일 경우 값을 재활용
			return dp[n][k];
		}

		if (k == 0 || n == k) {
			return dp[n][k] = BigInteger.ONE;
		}

		return dp[n][k] = Combination(n - 1, k - 1).add(Combination(n - 1, k));
	}
}