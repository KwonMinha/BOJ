/**
 * @author Minha Gwon
 * @date 2021. 2. 27.
 * 치즈
 * https://www.acmicpc.net/problem/2636
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visited;
	static int cheeseCnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}

		int time = 0;
		int preCheeseCnt = cheeseCnt;
		while(cheeseCnt != 0) {
			time++;
			visited = new boolean[N][M];
			preCheeseCnt = cheeseCnt;
			melting();
		}

		System.out.println(time + "\n" + preCheeseCnt);
	}
	
	static void melting() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0)); 
		visited[0][0] = true;

		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
				
				if(map[nx][ny] == 0) { // 공기일 경우 
					queue.add(new Point(nx, ny));
				} else if(map[nx][ny] == 1) { // 치즈일 경우 
					map[nx][ny] = 0; // 녹임 
					cheeseCnt--;
				}
				visited[nx][ny] = true; // 공기던 치즈던 방문 체크 
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