/**
 * @author Minha Gwon
 * @date 2021. 4. 5.
 * 바이러스 
 * https://www.acmicpc.net/problem/2606
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	
			int b = Integer.parseInt(st.nextToken());	
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		bfs();
		
		System.out.println(cnt);
	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i = 0; i < adjList[cur].size(); i++) {
				int next = adjList[cur].get(i);
				
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
					cnt++;
				}
			}
		}
	}
}
