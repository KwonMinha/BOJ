/**
 * @author Minha Gwon
 * @date 2021. 2. 22.
 * 스티커
 * https://www.acmicpc.net/problem/9465
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			int n = sc.nextInt();
			
			int[][] sticker = new int[2][n+1];
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < n; j++) 
					sticker[i][j] = sc.nextInt();
			}
			
			int[][] dp = new int[3][n+1];
			for(int i = 1; i < n+1; i++) {
				dp[0][i] = Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[2][i-1])); // 스티커를 뜯지 않음 
				dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + sticker[1][i-1]; // 위쪽 스티커를 뜯음 
				dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + sticker[0][i-1]; // 아래쪽 스티커를 뜯음 
			}
			
			System.out.println(Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n])));
		}
	}

}
