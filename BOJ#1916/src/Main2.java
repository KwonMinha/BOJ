/**
 * @author Minha Gwon
 * @date 2021. 3. 6.
 * 최소비용 구하기 
 * https://www.acmicpc.net/problem/1916
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] arr = new int[N+1][N+1];
		for(int i = 1; i < N+1; i++) {
			Arrays.fill(arr[i], -1); // 가중치 0이 있을수도 있으니 -1로 초기화 
		}

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int d = sc.nextInt();
			int c = sc.nextInt();

			if(arr[a][d] == -1) // 시작 정점과 도착 정점이 같은데 비용이 다른 값이 들어올 수 있기 때문에 예외 처리 
				arr[a][d] = c;
			else if(arr[a][d] > c) 
				arr[a][d] = c;
		}

		int start = sc.nextInt();
		int end = sc.nextInt();

		boolean[] check = new boolean[N+1]; // 정점이 집합 S에 속하는지 아닌지를 판별할 배열 

		int[] distance = new int[N+1]; // 최단 거리를 담을 배열 
		Arrays.fill(distance, Integer.MAX_VALUE-1); // 무한대로 초기화 
	
		// 처음 시작을 위한 초기화 
		for(int i = 1; i < N+1; i++) {
			if(arr[start][i] != -1) 
				distance[i] = arr[start][i];
		}

		check[start] = true; // 시작 정점 방문 표시 
		distance[start] = 0;

		for(int i = 0; i < N-1; i++) { // 시작점을 넣고 시작하기 때문에 N-1만큼만 반복 
			int min = Integer.MAX_VALUE;
			int index = -1;

			for(int j = 1; j < N+1; j++) { // 집합 S에 속하지 않는 가장 최단 거리를 갖는 정점 선택함 
				if(!check[j] && distance[j] < min) {
					min = distance[j]; // 최단 거리 
					index = j; // 최단 거리를 갖는 정점의 index 
				}
			}

			check[index] = true;

			for (int j = 1; j < N+1; j++) { // S에 속하지 않는다면 더 작은 값을 갖는 거리로 distance값 갱신 
				if (!check[j] && arr[index][j] != -1 && distance[index] + arr[index][j] < distance[j]) { // 간선이 연결되지 않은 -1의 경우 역시 제외해야함 
					distance[j] = distance[index] + arr[index][j];
				}
			}
		}

		System.out.println(distance[end]); // 도착점까지의 최단 거리 출력 
	}

}