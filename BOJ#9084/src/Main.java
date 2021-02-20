/**
 * @author Minha Gwon
 * @date 2021. 2. 20.
 * 동전 
 * https://www.acmicpc.net/problem/9084
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int t = 0; t < T; t++) {
			int N = sc.nextInt(); // 동전의 가지 수

			int[] coins = new int[N+1]; // dp가 1원인 1번 인덱스부터 시작하기 때문에 coins배열도 1부터 
			for(int i = 1; i < N+1; i++) { 
				coins[i] = sc.nextInt(); // 동전의 각 금액 - 오름차순 정렬
			}

			int M = sc.nextInt(); // N가지 동전으로 만들어야 할 금액

			int[] dp = new int[M+1]; // 계산 편의를 위해 1원부터 시작하는걸로 치고 +1

			dp[0] = 1; // dp 초기값 

			for (int i = 1; i < N+1; i++) {
				for (int j = coins[i]; j <= M; j++) {
					dp[j] += dp[j-coins[i]];
				}
			}

			System.out.println(dp[M]);
		}
	}


}