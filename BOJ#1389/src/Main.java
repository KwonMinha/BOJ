/**
 * @author Minha Gwon
 * @date 2021. 1. 21.
 * 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static boolean[] visited;
	public static int[] check; // BFS 수행시 이동 거리(탐색 너비) 측정 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		adjList = new LinkedList[N+1];
		for(int i= 1; i < N+1; i++) {
			adjList[i] = new LinkedList<>();
		}

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			adjList[a].add(b);
			adjList[b].add(a);
		}

		int min = Integer.MAX_VALUE;
		int ans = 0;

		for(int i = 1; i < N+1; i++) {
			int sum = 0;
			for(int j = 1; j < N+1; j++) {
				if(j != i) {
					visited = new boolean[N+1];
					check = new int[N+1];
					sum += bfs(i, j, 0);
				}
			}

			if(sum < min) {
				ans = i;
				min = Math.min(min, sum);
			}
		}

		System.out.println(ans);
	}

	public static int bfs(int start, int find, int cnt) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;

		while(!queue.isEmpty()) {
			int cur = queue.poll();	

			Iterator<Integer> iter = adjList[cur].iterator();
			while(iter.hasNext()) {

				int next = iter.next();

				if(!visited[next]) { 
					// BFS를 수행하며 한 칸씩 너비를 늘려나갈때마다 1씩 추가됨 
					check[next] = check[cur] + 1; // 최소 거리 측정을 위함 ex) 1 -> 5의 경우 1,3,4,5가 아니라 1,4,5를 거르기 위함.

					if(next == find) { // 종료 정점 
						return check[next]; // 최소 친구 단계의 합 
					}

					visited[next] = true; 
					queue.add(next); 
				} 
			}
		}
		return 0;
	}
}
