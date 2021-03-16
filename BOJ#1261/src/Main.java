/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 알고스팟 
 * https://www.acmicpc.net/problem/1261
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[M][N];
		visited = new boolean[M][N];
		for(int i = 0; i < M; i++) {
			String str = sc.next();

			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(min);

	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 0));
		visited[0][0] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.peek().y;
			int cw = queue.poll().wall;

			if(cx == M-1 && cy == N-1) {
				min = Math.min(min, cw);
				continue;
			}

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= M || ny >= N) 
					continue;

				if(!visited[nx][ny] && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny, cw));
				} else if(!visited[nx][ny] && map[nx][ny] == 1) {
					map[nx][ny] = 0;
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny, cw + 1));
				}
			}
		}
	}

	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
	}
}

class Point {
	int x;
	int y;
	int wall;

	Point(int x, int y, int wall) {
		this.x = x;
		this.y = y;
		this.wall = wall;
	}
}