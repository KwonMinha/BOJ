/**
 * @author Minha Gwon
 * @date 2021. 5. 11.
 * 정수 삼각형 
 * https://www.acmicpc.net/problem/1932
 * 재귀 시간초과 
 */

import java.util.Scanner;

public class Main {
	static int n;
	static int max = 0;
	static int[][] triangle;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();;
		
		triangle = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i+1; j++) {
				triangle[i][j] = sc.nextInt();
			}
		}
		
		solve(0, 0, 0);
		
		System.out.println(max);
	}
	
	public static void solve(int depth, int idx, int sum) {
		if(depth == n) {
			max = Math.max(max, sum);
		} else {
			solve(depth+1, idx, sum + triangle[depth][idx]);
			solve(depth+1, idx+1, sum + triangle[depth][idx]);
		}
	}
	
}
