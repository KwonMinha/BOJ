/**
 * @author Minha Gwon
 * @date 2020. 12. 30.
 * 상근이의 여행 
 * https://www.acmicpc.net/problem/9372
 * 18분 
 */

import java.util.Scanner;

public class Main {
	public static int answer = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[][] arr = new int[N+1][N+1];
			boolean[] visited = new boolean[N+1];
			
			for(int j = 0; j < M; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				arr[a][b] = 1;
				arr[b][a] = 1;
			}
			
			dfs(arr, visited, 1);
			System.out.println(answer-1);
			answer = 0;
		}
	}
	
	public static void dfs(int[][] arr, boolean[] visited, int v) {
		visited[v] = true;
		answer++;
		for(int i = 1; i < arr.length; i++) {
			if(arr[v][i] == 1 && !visited[i]) {
				dfs(arr, visited, i);
			}
		}
	}

}
