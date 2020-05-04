/**
 * @author Minha Gwon
 * @date 2020. 5. 4.
 * 1로 만들기
 * https://www.acmicpc.net/problem/1463
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
   X가 3으로 나누어 떨어지면, 3으로 나눈다.
   X가 2로 나누어 떨어지면, 2로 나눈다.
   1을 뺀다.
   정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * https://minhamina.tistory.com/31
 */

import java.util.Scanner;

public class Main {
	public static int[] dp; 
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	
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
    	
    	if(n % 3 == 0)
    		dp[n] = Math.min(dp[n], solve(n/3)+1);
    	
    	if(n % 2 == 0) 
    		dp[n] = Math.min(dp[n], solve(n/2)+1);
    	
		return dp[n];
	}
 
}