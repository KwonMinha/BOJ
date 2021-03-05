/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 아기상어2
 * https://www.acmicpc.net/problem/17086
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] count;
	static ArrayList<Point> sharkList = new ArrayList<>();
	static int min, ans;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					sharkList.add(new Point(i, j));
				}
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				min = M;
				
				bfs(i, j);
				
				for(Point p : sharkList) 
					min = Math.min(min, count[p.x][p.y]-1);
				
				ans = Math.max(min, ans);
			}
		}
		
		System.out.println(ans);
	}

	public static void bfs(int x, int y) {
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
				
				count[nx][ny] = count[cx][cy] + 1;
				queue.add(new Point(nx, ny));
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
