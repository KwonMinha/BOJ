/**
 * @author Minha Gwon
 * @date 2021. 1. 5.
 * 나무 탈출 
 * https://www.acmicpc.net/problem/15900
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static LinkedList<Integer>[] adjList;
	static boolean[] visited;
	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		
		//Scanner sc = new Scanner(System.in);
		//int N = sc.nextInt();
		adjList = new LinkedList[N+1];
		visited = new boolean[N+1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
//			int a = sc.nextInt();
//			int b = sc.nextInt();

			adjList[a].add(b);
			adjList[b].add(a);
		}

		dfs(1, 0, 0); // 각 노드의 부모 노드 구하기 
		
		System.out.println((answer % 2) == 0 ? "No" : "Yes");
		
		br.close();
	}

	public static void dfs(int cur, int p, int cnt) {
		for(int next : adjList[cur]) {
			if(next != p) {
				dfs(next, cur, cnt+1);
			}
		}
		
		if(cur != 1 && adjList[cur].size() == 1) {
			answer += cnt;
		}
	}

}
