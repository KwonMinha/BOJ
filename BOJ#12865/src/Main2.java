/**
 * @author Minha Gwon
 * @date 2021. 2. 16.
 * 평범한 배낭 
 * https://www.acmicpc.net/problem/12865
 */

import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] weight = new int[N+1];
		int[] value = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			weight[i] = sc.nextInt(); // 무게 
			value[i] = sc.nextInt(); // 가치 
		}
		
		int[] dp = new int[K + 1];
		for (int i = 1; i <= N; i++) {
			// K부터 탐색하여 담을 수 있는 무게 한계치가 넘지 않을 때까지 반복 
			for (int j = K; j - weight[i] >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
			}
		}

		System.out.println(dp[K]);
	}

}