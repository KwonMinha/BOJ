/**
 * @author Minha Gwon
 * @date 2021. 3. 30.
 * 벽 부수고 이동하기 
 * https://www.acmicpc.net/problem/2206
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			String num = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = num.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1, 0));
		
		boolean[][][] visited = new boolean[N][M][2];
		visited[0][0][0] = true; //[x][y][0] : 벽을 부수지 않고 온 경우 / [x][y][1] : 벽을 부수고 온 경우
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();

			if(cur.x == N-1 && cur.y == M-1) { // 도착점 도달 
				min = cur.dist;
				break;
			}

			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

				if(map[nx][ny] == 0) { // 빈 칸인 경우 
					if(!visited[nx][ny][cur.wall]) { // 벽을 부수고 왔든 아니든, 방문하지 않은 경우 
						visited[nx][ny][cur.wall] = true; // 방문 표시 
						queue.add(new Point(nx, ny, cur.dist+1, cur.wall));
					}
				} else { // 벽인 경우 
					if(cur.wall == 0) { // 1번도 부수지 않고 온 경우만 부순다. 
						visited[nx][ny][1] = true;
						queue.add(new Point(nx, ny, cur.dist+1, 1));
					}
				}
			}
		}
	}

}

class Point {
	int x;
	int y;
	int dist;
	int wall;

	Point(int x, int y, int dist, int wall) {
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.wall = wall;
	}
}