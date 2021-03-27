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

public class Main2 {
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		dp = new int[N+1][N+1];

		combination(N, K);
		
		System.out.println(dp[N][K]);
	}

    public static void combination(int n, int m){
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (dp[i-1][j-1] + (dp[i-1][j])) % 10007;
            }
        }
    }
    
}