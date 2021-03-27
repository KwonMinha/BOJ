/**
 * @author Minha Gwon
 * @date 2021. 3. 27.
 * 조합 
 * https://www.acmicpc.net/problem/2407
 * 
 * DP (Bottom-Up 방식으로 구현) 
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Main3 {
	public static BigInteger dp[][];

	public static void main(String[] args) throws Exception{

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		dp = new BigInteger[1001][1001];

		combination(n, m);

		System.out.println(dp[n][m]);
	}
	
    public static void combination(int n, int m){
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i)
                    dp[i][j] = new BigInteger("1");
                else
                    dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
            }
        }
    }

}