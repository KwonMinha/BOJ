/**
 * @author Minha Gwon
 * @date 2021. 2. 22.
 * 스티커
 * https://www.acmicpc.net/problem/9465
 */

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			int n = sc.nextInt();
			
			int[][] sticker = new int[2][n+1];
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j <= n; j++) 
					sticker[i][j] = sc.nextInt();
			}
			
			int[][] dp = new int[2][n+1];
			dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];
            for (int j = 2; j <= n; j++) { //1. 왼쪽 대각선, 왼쪽 왼쪽 대각선에 위치한 스티커 중 더 큰 스티커 떼어냄 
                dp[0][j] = Math.max(dp[1][j-2], dp[1][j-1]) + sticker[0][j]; // 위쪽
                dp[1][j] = Math.max(dp[0][j-2], dp[0][j-1]) + sticker[1][j]; // 아래쪽 
            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
	}

}
