/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 2×n 타일링
 * https://www.acmicpc.net/problem/11726
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] dp = new int[n+1][2];
		dp[1][0] = 1;
		dp[1][1] = 0;
		
		if(n >= 2) { // n이 1일 경우 예외처리 
			dp[2][0] = 1;
			dp[2][1] = 1;
		}
		
		for(int i = 3; i <= n; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-2][0]) % 10007;
			dp[i][1] = (dp[i-1][1] + dp[i-2][1]) % 10007;
		}
		
		System.out.println((dp[n][0] + dp[n][1]) % 10007);
	}

}