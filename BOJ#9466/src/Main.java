/**
 * @author Minha Gwon
 * @date 2021. 3. 20.
 * 텀 프로젝트
 * https://www.acmicpc.net/problem/9466
 */

import java.util.Scanner;

public class Main {
	static int[] arr;
	static boolean[] visited;
	static boolean[] finished;
	static int count;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int t = 0; t < T; t++) {
			int n = sc.nextInt();
			arr = new int[n+1];
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			count = 0;

			for(int i = 1; i < n+1; i++) 
				arr[i] = sc.nextInt();

			for(int i = 1; i < n+1; i++)
				dfs(i);

			System.out.println(n - count);
		}
	}

	public static void dfs(int now) {
		if(visited[now])
			return;

		visited[now] = true;

		int next = arr[now];

		if(!visited[next]) {
			dfs(next);
		} else {
			if(!finished[next]) {
				count++;

				// 한 노드의 dfs 탐색이 끝나려면 무조건 싸이클을 거쳐야 한다.     
				// 따라서 현재 노드가 아닌 다음 노드를 기준으로 하면 싸이클이 무조건 존재
				for(int i = next; i != now; i = arr[i]) 
					count++;
			}
		}

		finished[now] = true; // 방문하고 사이클을 찾는 모든 과정이 끝났으니 더이상 사용 X 
	}

}