/**
 * @author Minha Gwon
 * @date 2021. 1. 18.
 * 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 */

import java.util.*;
import java.util.Map.Entry;

public class Main {
	public static ArrayList<Integer>[] adjList;
	public static boolean[] visited;
	public static int[] result;
	public static int count = 0;
	public static int max = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		adjList = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		result =  new int[N+1];

		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjList[b].add(a);
		}

		for (int i = 1; i <= N; i++) { 
			Collections.sort(adjList[i]);
		}

		for(int i = 1; i <= N; i++) {
				dfs(i);
				result[i] = count;
				max = Math.max(max, count);
				
				count = 0;
				Arrays.fill(visited, false);
		}
		
		for(int i = 1; i <= N; i++) {
			if(result[i] == max) {
				System.out.print(i + " ");
			}
		}
	}

	public static void dfs(int v) {
		visited[v] = true;
		count++;

		Iterator<Integer> iter = adjList[v].listIterator();
		while (iter.hasNext()) {
			int w = iter.next();
			if (!visited[w]) { 
				dfs(w);
			}
		}
	}

}
