/**
 * @author Minha Gwon
 * @date 2021. 3. 12.
 * 레이저 통신
 * https://www.acmicpc.net/problem/6087
 * BLOG - https://minhamina.tistory.com/162
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Point startPoint, endPoint;
	static int W, H;
	static int min = Integer.MAX_VALUE;
	static char[][] map;
	static int[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new char[H][W];

		for(int i = 0; i < H; i++) {
			String str = br.readLine();
			for(int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);

				if(map[i][j] == 'C') {
					if(startPoint == null)
						startPoint = new Point(i, j, -1, 0);
					else 
						endPoint = new Point(i, j, -1, 0);
				}
			}
		}

		bfs();

		System.out.println(min);
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(startPoint);

		visited = new int[H][W];
		visited[startPoint.x][startPoint.y] = 1;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.peek().y;
			int cd = queue.peek().dir;
			int cm = queue.poll().mirror;

			if(cx == endPoint.x && cy == endPoint.y) {
				min = Math.min(min, cm);
				continue;
			}

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				int nd = i;

				if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*')
					continue;

				int nm = cm; // 새로운 거울 개수 저장할 변수 (꺾이면 +1 됨)

				if(cd != -1 && cd != nd) { // 처음 시작이 아니고, 뱡향이 다른 경우 -> 꺾임 -> 거울 설치해야 함
					nm += 1; // 꺽임 
				}

				if(visited[nx][ny] == 0) { // 방문하지 않은 경우 
					visited[nx][ny] = nm; // 구해진 거울 값으로 초기화 
					queue.add(new Point(nx, ny, nd, nm));
				} else if(visited[nx][ny] >= nm) { // 이미 방문한 곳이지만 새롭게 구해진 거울 개수가 더 작은 경우 
					visited[nx][ny] = nm; // 새롭게 구해진 값으로 변경 
					queue.add(new Point(nx, ny, nd, nm)); // 다시 그 곳부터 bfs를 위해 큐에 넣어줌 
				}
			}
		}
	}

}

class Point {
	int x;
	int y;
	int dir; // 레이저의 현재 방향 
	int mirror; // 현재 위치까지 설치된 거울의 개수 

	Point(int x, int y, int dir, int mirror) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.mirror = mirror;
	}
}
