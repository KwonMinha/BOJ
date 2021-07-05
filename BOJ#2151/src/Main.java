/**
 * @author Minha Gwon
 * @date 2021. 7. 6.
 * 거울 설치 
 * https://www.acmicpc.net/problem/2151
 */

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static char[][] map;
	static boolean[][][] visit;

	static int[] dx = {0, 0, -1, 1}; // 0 1 2 3 - 좌 우 상 하 방향 
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new boolean[N][N][4];

		int sx = 0;
		int sy = 0;

		for(int i = 0; i < N; i++) {
			String str = br.readLine();

			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == '#') { // 시작점 지정 
					sx = i;
					sy = j;
				}
			}
		}

		map[sx][sy] = '.';
		bfs(sx, sy);
	}

	static void bfs(int sx, int sy) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(sx, sy, -1, 0));

		for(int i = 0; i < 4; i++) {
			// 시작점 모든 방향으로 방문완료 처리
			visit[sx][sy][i] = true;
		}

		while(!pq.isEmpty()) {
			Point point = pq.poll();

			// 목적지를 만나면 값 출력 후 종료
			if (map[point.x][point.y] == '#') {
				System.out.println(point.cnt);
				return;
			}

			// 시작점인 경우 -> 갈 수 있는 방향 찾기 
			if(point.dir == -1) {
				for (int i = 0; i < 4; i++) {
					int nx = point.x + dx[i];
					int ny = point.y + dy[i];

					if(!check(nx, ny)) continue;

					if(map[nx][ny] != '*') {
						pq.offer(new Point(nx, ny, i, point.cnt));
						visit[nx][ny][i] = true;
					}
				}
			} else { // 시작점이 아닌 경우
				if (map[point.x][point.y] == '!') { // 거울인 경우 -> 1. 거울을 세우고 진행 
					// 0 1 2 3 - 좌 우 상 하 방향 
					if (point.dir == 0 || point.dir == 1) { // 좌우로 움직이는 중이라면 
						for (int i = 2; i < 4; i++) { // 위 아래로 반사 
							int nx = point.x + dx[i];
							int ny = point.y + dy[i];

							if (!check(nx, ny)) continue;

							if (map[nx][ny] != '*' && !visit[nx][ny][i]) {
								pq.offer(new Point(nx, ny, i, point.cnt + 1));
								visit[nx][ny][i] = true;
							}
						}
					}

					if (point.dir == 2 || point.dir == 3) { // 위 아래로 움직이는 중이라면 
						for (int i = 0; i < 2; i++) { // 좌 우로 반사 
							int nx = point.x + dx[i];
							int ny = point.y + dy[i];

							if (!check(nx, ny)) continue;

							if (map[nx][ny] != '*' && !visit[nx][ny][i]) {
								pq.offer(new Point(nx, ny, i, point.cnt + 1));
								visit[nx][ny][i] = true;
							}
						}
					}			
				}

				// 2. 거울을 세우지 않고 진행 
				int nx = point.x + dx[point.dir];
				int ny = point.y + dy[point.dir];

				if (!check(nx, ny)) continue;

				if (map[nx][ny] != '*' && !visit[nx][ny][point.dir]) {
					pq.offer(new Point(nx, ny, point.dir, point.cnt));
					visit[nx][ny][point.dir] = true;
				}
			}
		}
	}

	// Point 좌표값 검사  
	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}

class Point implements Comparable<Point>{
	int x;
	int y;
	int dir;
	int cnt; // 거울 설치 횟수 저장 

	Point (int x, int y, int dir, int cnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Point o) {
		return this.cnt - o.cnt; // cnt기준 오름차순 정렬 
	}
}