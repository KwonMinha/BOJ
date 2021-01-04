/**
 * @author Minha Gwon
 * @date 2021. 1. 4.
 * LCA
 * https://www.acmicpc.net/problem/11437
 * BLOG - https://minhamina.tistory.com/99
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static int[] parent;
	public static int[] depth;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		adjList = new LinkedList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		parent = new int[N+1];
		depth = new int[N+1];
		
		dfs(1, 0, -1);

		int M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			// LCA 시작 
			int depthA = depth[a];
			int depthB = depth[b];
		
			while(depthA > depthB) {
				a = parent[a];
				depthA--;
			}
			
			while(depthB > depthA) {
				b = parent[b];
				depthB--;
			}
			
			while(a != b) {
				a = parent[a];
				b = parent[b];
			}
			
			System.out.println(a);
		}
	}

	// DFS를 통해 깊이와 부모 노드 배열에 저장 
	public static void dfs(int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;
		
		for(int next : adjList[cur]) {
			if(next != p) {
				dfs(next, d+1, cur);
			}
		}
	}

}
