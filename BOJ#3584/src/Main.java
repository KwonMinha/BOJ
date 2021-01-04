/**
 * @author Minha Gwon
 * @date 2021. 1. 4.
 * 가장 가까운 공통 조상 
 * https://www.acmicpc.net/problem/3584
 * BLOG - https://minhamina.tistory.com/101
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			LinkedList<Integer>[] adjList = new LinkedList[N+1];
			int[] parent = new int[N+1];
			int[] depth = new int[N+1];
			boolean[] vertex = new boolean[N+1];

			for (int j = 1; j <= N; j++) {
				adjList[j] = new LinkedList<Integer>();
			}

			for(int j = 0; j < N-1; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();

				vertex[b] = true;

				adjList[a].add(b);
				adjList[b].add(a);
			}

			// 루트 노드 찾기 
			int root = 0;
			for(int j = 1; j <= N; j++) {
				if(vertex[j] == false) {
					root = j;
				}
			}

			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			// DFS로 트리를 순회하며 각 노드의 깊이와 부모노드 배열에 저장 
			dfs(adjList, depth, parent, root, 0, -1);
			
			// 가장 가까운 공통 조상 구하기 
			lca(depth, parent, v1, v2);
		}
	}


	public static void dfs(LinkedList<Integer>[] adjList, int[] depth, int[] parent, int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;

		for(int next : adjList[cur]) {
			if(next != p) {
				dfs(adjList, depth, parent, next, d+1, cur);
			}
		}
	}
	
	public static void lca(int[] depth, int[] parent, int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];
	
		// 두 노드의 깊이 같게 맞추기 
		while(depthA > depthB) {
			a = parent[a];
			depthA--;
		}
		
		while(depthB > depthA) {
			b = parent[b];
			depthB--;
		}
		
		// 같은 레벨(깊이)에서 위로 올라가며 공통 부모노드 찾기 
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		System.out.println(a);
	}

}
