/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 알고스팟 
 * https://www.acmicpc.net/problem/1261
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static int ans;
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

		System.out.println(ans);
	}

	public static void bfs() {
		Deque<Point> queue = new ArrayDeque<>(); // 일반 Queue가 아니라 Deque 사용 -> 삽입시 큐의 앞 뒤로 우선순위를 부여할 것 
		queue.add(new Point(0, 0, 0));
		visited[0][0] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.peek().y;
			int cw = queue.pollFirst().wall; // 큐의 앞쪽부터 poll (앞쪽이 우선순위가 더 높은 것으로 가정) 

			if(cx == M-1 && cy == N-1) {
				ans = cw;
				break;
			}

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny]) 
					continue;

				// 최소로 벽을 부술려면 최대한 빈 방을 위주로 가고, 그 이외에 벽을 부숴야 함 
				if(map[nx][ny] == 0) { // 따라서 빈 방인 경우, 우선순위가 더 높으니 큐의 앞에 넣어줌 
					visited[nx][ny] = true;
					queue.addFirst(new Point(nx, ny, cw));
				} else if(map[nx][ny] == 1) { // 벽인 경우, 우선순위가 더 낮으니 큐의 뒤에 넣어줌 
					map[nx][ny] = 0;
					visited[nx][ny] = true;
					queue.addLast(new Point(nx, ny, cw + 1)); // 벽을 부쉈으니 current wall 변수 + 1 
				}
			}
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