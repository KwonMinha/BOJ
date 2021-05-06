/**
 * @author Minha Gwon
 * @date 2021. 5. 5.
 * 가장 긴 감소하는 부분 수열
 * https://www.acmicpc.net/problem/11722
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
	
		dp[0] = 1;
		
		for(int i = 1; i < N; i++) {
			dp[i] = 1;
			
			for(int j = 0; j < i; j++) {
				if(arr[j] > arr[i] && dp[i] < dp[j]+1) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		int max = 0;
		for(int i : dp) {
			max = Math.max(max, i);
		}
		
		System.out.println(max);
	}

}
