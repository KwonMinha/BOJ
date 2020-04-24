/**
 * @author Minha Gwon
 * @date 2020. 4. 25.
 * 
 * 문제
   그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
   단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
   더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
 * 
 * 입력
   첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 
   다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 
   입력으로 주어지는 간선은 양방향이다.
 *
 * 출력
   첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
 * https://www.acmicpc.net/problem/1260
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		int v = sc.nextInt();

		boolean c[] = new boolean[n + 1]; // 방문 check

		//1. 인접리스트로 구현
		/*
		LinkedList<Integer>[] a = new LinkedList[n + 1];

		for (int i = 0; i <= n; i++) {
			a[i] = new LinkedList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			a[v1].add(v2);
			a[v2].add(v1);
		}

		for (int i = 1; i <= n; i++) {
			Collections.sort(a[i]);
		}

		dfs(v, a, c);
		System.out.println();
		Arrays.fill(c, false);
		bfs(v, a, c);
		 */

		//2. 인접행렬로 구현
		int[][] a = new int[n+1][n+1];

		for(int i = 0; i < m; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();

			a[v1][v2] = 1;
			a[v2][v1] = 1;
		}

		dfs2(v, a, c);
		//스택 dfs 호출시
		//dfs2(v, a, c, true);
		System.out.println();
		Arrays.fill(c, false);
		bfs2(v, a, c);


	}

	//2-1. 인접행렬로 구현 - 재귀로 구현 
	public static void dfs2(int v, int[][] a, boolean[] c) {
		int l = a.length-1;
		c[v] = true;
		System.out.print(v + " ");

		for(int i = 1; i <= l; i++) {
			if(a[v][i] == 1 && !c[i]) {
				dfs2(i, a, c);
			}
		}
	}

	//2-2. 인접행렬로 구현 - 스택으로 구현 
	public static void dfs2(int v, int[][] a, boolean[] c, boolean flag) {
		int l = a.length-1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(v);
		c[v] = true;
		System.out.print(v + " ");

		while(!stack.isEmpty()) {
			int k = stack.peek();
			flag = false; 

			for(int i = 1; i <= l; i++) {
				if(a[k][i] == 1 && !c[i]) {
					stack.push(k);
					System.out.print(i + " ");
					c[i] = true;
					flag = true;
				}
			}

			if(!flag) {
				stack.pop();
			}
		}
	}

	//2. 인접행렬로 구현 
	public static void bfs2(int v, int[][] a, boolean[] c) {
		Queue<Integer> q = new LinkedList<>();
		int n = a.length - 1;

		q.add(v);
		c[v] = true;

		while (!q.isEmpty()) {
			v = q.poll();
			System.out.print(v + " ");

			for (int i = 1; i <= n; i++) {
				if (a[v][i] == 1 && !c[i]) {
					q.add(i);
					c[i] = true;
				}
			}
		}
	}

	//1. 인접리스트로 구현 
	public static void dfs(int v, LinkedList<Integer>[] a, boolean[] visited) {
		// 재귀로 구현
		visited[v] = true;
		System.out.print(v + " ");

		Iterator<Integer> iter = a[v].listIterator();
		while (iter.hasNext()) {
			int n = iter.next();
			if (!visited[n])
				dfs(n, a, visited);
		}
	}

	//1. 인접리스트로 구현 
	public static void bfs(int v, LinkedList<Integer>[] a, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[v] = true; 
		queue.add(v);

		while(queue.size() != 0) { 
			v = queue.poll(); 
			System.out.print(v + " ");

			Iterator<Integer> iter = a[v].listIterator();
			while(iter.hasNext()) { 
				int k = iter.next(); 
				if(!visited[k]) { 
					visited[k] = true; 
					queue.add(k); 
				} 
			}
		}
	}

}

