/**
 * @author Minha Gwon
 * @date 2021. 1. 21.
 * 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static boolean[] visited;
	public static int[][] adjArray; 
	public static Stack<Integer> stack;
	public static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		adjArray = new int[N+1][N+1];

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			adjArray[a][b] = 1;
			adjArray[b][a] = 1;
		}

		int ans = 0;
		int ansCount = Integer.MAX_VALUE;
		
		for(int i = 1; i < N+1; i++) {
			stack = new Stack<>();
			visited = new boolean[N+1];
			int result = 0;
			
			for(int j = 1; j < N+1; j++) {
				if(i != j) {
					min = Integer.MAX_VALUE;
					dfs(i, j);
					result += (min-1); // 자기자신 -1 
				}
			}
			
			if(ansCount > result) {
				ans = i;
				ansCount = result;
			}
		}
		
		System.out.println(ans);


	}

	public static void dfs(int start, int end) {
		visited[start] = true;
		stack.push(start); // 스택에 값을 넣어줌

		if(start == end) { // 목표 노드에 왔다면
			min = Math.min(min, stack.size());
		}

		for(int i = 1; i <= adjArray.length-1; i++) {
			if(adjArray[start][i] == 1 && !visited[i]) {
				dfs(i, end);
				// dfs 후에 방문 노드를 false로 만들어주어 해당 노드를 방문하지 않은 것으로 해줌 -> 모든 경로를 구하기 위함 
				visited[i] = false; 
			}
		}

		stack.pop(); //dfs 빠져 나올땐 pop()
	}

}
