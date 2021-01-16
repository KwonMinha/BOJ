/**
 * @author Minha Gwon
 * @date 2021. 1. 17.
 * 유기농 배추 
 * https://www.acmicpc.net/problem/1012
 * 
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 
   그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 
   그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 
   그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {	
	public static int M, N;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static ArrayList<Point> cabbage = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int t = 0; t < T; t++) {
			M = sc.nextInt();
			N = sc.nextInt();
			int K = sc.nextInt();

			int[][] map = new int[M][N];
			boolean[][] visited = new boolean[M][N];

			for(int i = 0; i < K; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();

				cabbage.add(new Point(a, b));
				map[a][b] = 1;
			}

			int count = 0;
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						count++;	
						bfs(map, visited, i, j);
					}
				}
			}

			System.out.println(count);
		}		
	}

	public static void bfs(int[][] map, boolean[][] visited, int a, int b) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(a, b));
		visited[a][b] = true;

		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || ny < 0 || nx >= M || ny >= N) 
					continue;

				if(map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
				}
			}
		}

	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
