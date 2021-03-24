import java.util.*;

public class Main3 {
	public static int N, dp[];
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
        
		dp = new int[N+1];
		Arrays.fill(dp, -1);
        
		dp[0] = dp[1] = 0;
        
		System.out.println(solve(N));
        
		sc.close();
	}

	public static int solve(int n){
		if(dp[n] != -1) 
			return dp[n];

		int min = Integer.MAX_VALUE;
        
		if(n % 3 == 0) 
			min = Math.min(min, solve(n / 3));
        
		if(n % 2 == 0) 
			min = Math.min(min, solve(n / 2));
        
		min = Math.min(min, solve(n - 1));

		return dp[n] = min + 1;
	}
}