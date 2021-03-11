/**
 * @author Minha Gwon
 * @date 2021. 3. 6.
 * 최소비용 구하기 
 * https://www.acmicpc.net/problem/1916
 */


import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] arr = new int[N+1][N+1];
		for(int i = 1; i < N+1; i++) {
			Arrays.fill(arr[i], -1);
		}

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int d = sc.nextInt();
			int c = sc.nextInt();

			if(arr[a][d] == 0)
				arr[a][d] = c;
			else if(arr[a][d] > c) 
				arr[a][d] = c;
		}

		int arrival = sc.nextInt();
		int departure = sc.nextInt();

		boolean[] check = new boolean[N+1];

		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE-1);

		distance[arrival] = 0;

		for(int i = 0; i < N-1; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1;

			for(int j = 1; j < N+1; j++) { // 아직 처리하지 않았으면서, 가장 짧은 거리라면
				if(!check[j] && min > distance[j]) {
					index = j;
					min = distance[j];
				}
			}

			for (int j = 1; j < N+1; j++) { // 아직 처리하지 않았으면서 경로가 존재하고, index까지의 거리 + index부터 j까지의 거리가 distance[j]보다 작다면
				if (!check[j] && arr[index][j] != -1 && distance[index] + arr[index][j] < distance[j]) {
					distance[j] = distance[index] + arr[index][j];
				}
			}

			
//			for (int to = 1; to <= N; to++) {
//				if (maps[from][to] != -1 && cost[to] > cost[from] + maps[from][to]) {
//					cost[to] = cost[from] + maps[from][to];
//				}
//			}
			
			
			check[index] = true;
		}
		
			System.out.println(distance[departure]);
	}

}
