/**
 * @author Minha Gwon
 * @date 2021. 2. 25.
 * 포도주 시식https://www.acmicpc.net/problem/2156
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] wine = new int[n+1];
		for(int i = 1; i < n+1; i++) {
			wine[i] = sc.nextInt();
		}
		
		int[] dp = new int[n+1];
		dp[1] = wine[1];
		if(n > 1) { // N=1의 경우를 대비해 예외처리 
			dp[2] = wine[1] + wine[2];
		}
	
		for(int i = 3; i < n+1; i++) { // 전 와인과 전전 와인을 먹을 수 있는 3번째 와인부터 시작 
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3] + wine[i-1]) + wine[i]); 
		}
		
		System.out.println(dp[n]);
	}

}
