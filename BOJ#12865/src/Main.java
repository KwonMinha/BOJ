/**
 * @author Minha Gwon
 * @date 2021. 2. 15.
 * 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 */

import java.util.Scanner;

public class Main {
	static int K, N;
	static int[][] bag;
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		bag = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			bag[i][0] = sc.nextInt();
			bag[i][1] = sc.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			solve(i, 0, 0);
		}

		System.out.println(answer);
	}
	
	public static void solve(int idx, int totalWeight, int totalValue) {
		if(idx == N) {
			answer = Math.max(answer, totalValue);
			return;
		}
		
		totalWeight += bag[idx][0];
		
		if(totalWeight <= K) {
			totalValue += bag[idx][1];
			solve(idx+1, totalWeight, totalValue);
		} else {
			solve(idx+1, totalWeight-bag[idx][0], totalValue);
		}
	}


}