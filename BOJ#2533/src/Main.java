/**
 * @author Minha Gwon
 * @date 2021. 1. 2.
 * 사회망 서비스 (SNS))
 * https://www.acmicpc.net/problem/2533 
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

		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			adjList[a].add(b);
			adjList[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			System.out.println(i + " " + adjList[i]);
		}

		sns(1);

		System.out.println(answer);

	}

	public static void sns(int cur) {
		dp[cur][0] = 0; //현재 정점이 얼리어답터가 아닌 경우 0
		dp[cur][1] = 1; // 얼리어답터인 경우 1

		for(int i = 0; i < adjList[cur].size(); i++) {
			
		}

	}

//	public static void dfs(int index) { 
//		visit[index] = 1; 
//		dp[index][0] = 0; 
//		dp[index][1] = 1; 
//
//		LinkedList<Integer> item = list[index]; 
//
//		for (int to : item) { 
//			if (visit[to] == 0) { 
//				dfs(to); dp[index][0] += dp[to][1]; 
//				dp[index][1] += Math.min(dp[to][0], dp[to][1]); 
//			} 
//		} 
//	}
	
//    static void dp(int cur, int parent) {
//        dp[cur][0] = 0;
//        dp[cur][1] = 1;
//
//        for (int next : list.get(cur)) {
//          // 사이클이 존재하지 않고, 부모가 유일하므로
//          // next와 parent가 같으면 단말노드라고 생각할 수 있다.
//            if (next != parent) {
//                dp(next, cur);
//                dp[cur][0] += dp[next][1];
//                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
//            }
//        }
//    }


}
