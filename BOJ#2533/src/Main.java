/**
 * @author Minha Gwon
 * @date 2021. 1. 2.
 * 사회망 서비스 (SNS))
 * https://www.acmicpc.net/problem/2533 
 * BLOG - https://minhamina.tistory.com/98
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static boolean[] visited;
	public static int[][] dp;
	public static int N;
	public static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjList = new LinkedList[N+1];
		visited = new boolean[N+1];
		dp = new int[N+1][2];

		// 정점 N개의 인접 리스트 생성 
		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			adjList[a].add(b);
			adjList[b].add(a);
		}

		//dfs(1); // 1을 루트로 가정해 1부터 시작 
		dp(1, -1); // 1부터 시작이라서 parent는 -1 없는 상태 

		System.out.println(Math.min(dp[1][0], dp[1][1]));

	}

	public static void dfs(int cur) {
		dp[cur][0] = 0; //현재 정점이 얼리어답터가 아닌 경우 0 -> 인접 노드는 얼리어답터여야지 아이디어를 전파할 수 있음 
		dp[cur][1] = 1; // 얼리어답터인 경우 1 -> 인접 노드는 얼리어답터이거나 아닐 수도 있음, 즉 둘을 dfs로 파악 후 더 작은 값으로 초기화 
		visited[cur] = true;
		
		LinkedList<Integer> list = adjList[cur];
		
		for(int i = 0; i < list.size(); i++) {
			if(!visited[list.get(i)]) { // DFS 무한 루프와 이전에 간 곳 다시 가지 않기 위해, 아직 방문하지 않은 인접 정점인 경우만 DFS 
				dfs(list.get(i)); // 인접 정점을 시작으로 다시 dfs 
				dp[cur][0] += dp[list.get(i)][1];
				dp[cur][1] += Math.min(dp[list.get(i)][0], dp[list.get(i)][1]); // 인접 노드가 얼리어답터인지 아닌지 판단 후 더 작은 값으로 초기화 
			}
		}
	}

	public static void dp(int cur, int parent) {
		dp[cur][0] = 0; 
		dp[cur][1] = 1;
		
		for(int next : adjList[cur]) {
			if(next != parent) { // 다음 노드 next가 부모 노드 parent와 다른 경우만 확인(같다는 건 이미 이전에 확인했다는 뜻) -> 사이클 X, 부모는 유일하기 때문 
				dp(next, cur); // parent에 현재 노드 값을 넣어, 다음 분기에서 판단할 수 있도록 함 
				dp[cur][0] += dp[next][1];
				dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}

}
