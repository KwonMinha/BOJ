/**
 * @author Minha Gwon
 * @date 2020. 5. 4.
 * 1로 만들기
 * https://www.acmicpc.net/problem/1463
 * BLOG - https://minhamina.tistory.com/31
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] dp; 
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		int N = Integer.parseInt(br.readLine());
    	
    	dp = new int[N + 1];
    	
    	dp[0] = dp[1] = 0;
    	
    	System.out.println(solve(N));
    }
    
    static int solve(int n) {
    	if(n == 1)
    		return 0;
    	
    	if(dp[n] > 0) 
    		return dp[n];
    	
    	dp[n] = solve(n-1) + 1;
    	
    	if(n % 3 == 0 && dp[n] > solve(n/3)+1)
    		dp[n] = dp[n/3] + 1;
    	
    	if(n % 2 == 0 && dp[n] > solve(n/2)+1) 
    		dp[n] = dp[n/2] + 1;
    	
		return dp[n];
	}
 
}