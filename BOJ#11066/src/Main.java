/**
 * @author Minha Gwon
 * @date 2021. 7. 7.
 * 파일 합치기
 * https://www.acmicpc.net/problem/11066
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());

			int[] novel = new int[K+1]; // 파일의 크기 
			int[] sum = new int[K+1]; // 파일의 총합 
			int[][] dp = new int[K+1][K+1];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 1; i <= K; i++) {
				novel[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + novel[i];
			}
			
			for(int n = 1; n <= K; n++) {
				for(int i = 1; i + n <= K; i++) {
					int j = i+n;
					dp[i][j] = Integer.MAX_VALUE;
					
					for(int k = i; k < j; k++) { // i~j 사이의 k 값 
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1]); // dp[i][k] + dp[k+1][j] + i~j까지의 부분합 
					}
				}
			}

			System.out.println(dp[1][K]);
		}
	}

}
