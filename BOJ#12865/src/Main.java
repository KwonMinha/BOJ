/**
 * @author Minha Gwon
 * @date 2021. 2. 16.
 * 평범한 배낭 
 * https://www.acmicpc.net/problem/12865
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] bag = new int[N+1][2];
		for(int i = 1; i < N+1; i++) {
			bag[i][0] = sc.nextInt(); // 무게 
			bag[i][1] = sc.nextInt(); // 가치 
		}

		int[][] dp = new int[N+1][K+1];
		// 0행, 0열은 공백 인덱스로 비워둠 (1부터 시작해서 i-1 참조시 오류를 방지하기 위함)
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < K+1; j++) {
				// 비교 무게 - 물품의 무게 >= 0 인 경우, 즉 i번째 무게를 더 담을 수 있는 경우 
				if(j - bag[i][0] >= 0) {
					// 이전 dp에 저장된 누적 가치와 자신의 가치 + 남은 무게의 가치 중 큰 값을 취함 
					dp[i][j] = Math.max(dp[i-1][j], bag[i][1] + dp[i-1][j-bag[i][0]]);
				} else { // i번째 무게를 더 담을 수 없는 경우, 이전 dp에 누적된 가치를 취함 
					dp[i][j] = dp[i-1][j];
				}
			}
		}

		System.out.println(dp[N][K]);
	}

}