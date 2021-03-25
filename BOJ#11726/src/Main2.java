/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 2×n 타일링
 * https://www.acmicpc.net/problem/11726
 * Main 코드를 하나의 dp 배열로 구현 
 */

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
	
		int[] dp = new int[n+1];

		dp[0] = 1;
		dp[1] = 1;

		for(int i = 2; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		System.out.println(dp[n] % 10007);
	}

}