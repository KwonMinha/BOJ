/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 아기상어2
 * https://www.acmicpc.net/problem/17086
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] count;
	static int max;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) 
				map[i][j] = sc.nextInt();
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) 
				if(map[i][j] == 0)
					max = Math.max(max, bfs(i, j)-1);
		}
		
		System.out.println(max);
	}

	public static int bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		count = new int[N][M];
		count[x][y] = 1;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;

			for(int i = 0; i < 8; i++) {
				int nx  = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M || count[nx][ny] != 0) continue;
				
				if(map[nx][ny] == 1) {
					return count[cx][cy] + 1;
				}
				
				count[nx][ny] = count[cx][cy] + 1;
				queue.add(new Point(nx, ny));
			}
		}
		
		return 0;
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
