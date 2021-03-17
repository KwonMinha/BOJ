/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 최단경로 
 * https://www.acmicpc.net/problem/1753
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		int E = sc.nextInt();

		int start = sc.nextInt();

		int[][] arr = new int[V+1][V+1];
		for(int i = 0; i < E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();

			// 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음 -> 최소값으로 갱신 
			if(arr[u][v] == 0) {
				arr[u][v] = w;
			} else if(arr[u][v] > w) {
				arr[u][v] = w;
			}
		}

		boolean[] check = new boolean[V+1];

		int[] distance = new int[V+1];
		int INF = 10 * (V-1) + 1; // INF는 항상 (간선 가중치의 최댓값) * (정점 개수 - 1) 보다 큰 값을 사용해야 한다.


		for(int i = 1; i < V+1; i++) {
			if(distance[i] ==  INF)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
	}

}
