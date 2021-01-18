/**
 * @author Minha Gwon
 * @date 2021. 1. 19.
 * 결혼식 
 * https://www.acmicpc.net/problem/5567
 */

import java.util.*;

public class Main {
	public static ArrayList<Integer>[] adjList;
	public static int[] visited;
	public static int ans = 0;
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		adjList = new ArrayList[N + 1];
		visited = new int[N + 1];
	
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		bfs();
		System.out.println(ans);
	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1]++;
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			
			if(visited[v] > 3) {
				break;
			}
			
			for(int i = 0; i < adjList[v].size(); i++) {
				if(visited[adjList[v].get(i)] == 0) {
					visited[adjList[v].get(i)] = visited[v]+1;
					queue.add(adjList[v].get(i));
					ans++;
				}
			}
		}
	}
}
