/**
 * @author Minha Gwon
 * @date 2021. 1. 21.
 * 적록색약 
 * https://www.acmicpc.net/problem/10026
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	int x; 
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static char[][] map;
	public static boolean[][] visited ;
	public static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new char[N][N];
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			String str = sc.next();
			for(int j = 0; j < N; j++) {
				char c = str.charAt(j);
				map[i][j] = c;
			}
		}

		int[] result = new int[2];
		int cnt = 0;

		// 적록색약이 아닐 때 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}

		result[0] = cnt;
		cnt = 0;
		visited = new boolean[N][N];

		// 적록색약일 때 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				char c = map[i][j];
				if(c == 'R' || c == 'G') // R, G 같은 색으로 만들어 줌 
					map[i][j] = 'A';
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}

		result[1] = cnt;

		System.out.println(result[0] + " " + result[1]);
	}

	public static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		visited[x][y] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;
			char color = map[cx][cy];

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) 
					continue;

				if(visited[nx][ny])
					continue;

				if(map[nx][ny] == color) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
				}
			}
		}
	}
}
