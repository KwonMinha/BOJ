/**
 * @author Minha Gwon
 * @date 2021. 9. 17.
 * 근손
 * https://www.acmicpc.net/problem/18429
 */

import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[] arr;
	static boolean[] visited;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		arr = new int[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		dfs(0, 500);
		
		System.out.println(answer);
	}
	
	static void dfs(int day, int weight) {
		if(day == N) {
			answer++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				
				if(weight - K + arr[i] >= 500) {
					dfs(day+1, weight - K + arr[i]);
				}
				
				visited[i] = false;
			}
		}	
	}

}
