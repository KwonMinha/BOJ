/**
 * @author Minha Gwon
 * @date 2021. 4. 13.
 * 파티 
 * https://www.acmicpc.net/problem/1238
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int INF;
	static int N, X;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());		
		int M = Integer.parseInt(st.nextToken());		
		X = Integer.parseInt(st.nextToken());

		INF = 100 * (N-1);
		arr = new int[N+1][N+1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	
			int end = Integer.parseInt(st.nextToken());	
			int t = Integer.parseInt(st.nextToken());	

			arr[start][end] = t;
		}

		int time = 0;

		for(int n = 1; n <= N; n++) {
			time = Math.max(time, dijkstra(n, X) + dijkstra(X, n));
		}
		
		System.out.println(time);

	}

	public static int dijkstra(int start, int end) {
		boolean[] check = new boolean[N+1]; // 정점이 집합 S에 속하는지 아닌지를 판별할 배열 
		int[] distance = new int[N+1]; // 최단 거리를 담을 배열 
		Arrays.fill(distance, INF); // 무한대로 초기화 

		distance[start] = 0;

		for(int i = 0; i < N-1; i++) { // 시작점을 넣고 시작하기 때문에 N-1만큼만 반복 
			int min = Integer.MAX_VALUE;
			int index = -1;

			for(int j = 1; j < N+1; j++) { // 집합 S에 속하지 않는 가장 최단 거리를 갖는 정점 선택 
				if(!check[j] && distance[j] < min) {
					min = distance[j]; // 최단 거리 
					index = j; // 최단 거리를 갖는 정점의 index 
				}
			}

			check[index] = true;

			// S에 속하지 않는다면 더 작은 값을 갖는 거리로 distance값 갱신 
			for (int j = 1; j < N+1; j++) { 
				if (!check[j] && arr[index][j] != 0 && distance[index] + arr[index][j] < distance[j]) { // 간선이 연결되지 않은 0의 경우 역시 제외해야함 
					distance[j] = distance[index] + arr[index][j];
				}
			}
		}

		return distance[end];
	}

}