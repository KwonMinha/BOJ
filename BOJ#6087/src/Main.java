/**
 * @author Minha Gwon
 * @date 2021. 3. 12.
 * 레이저 통신
 * https://www.acmicpc.net/problem/6087
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Point> laserList = new ArrayList<>();
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
					if(startPoint == null) {
						startPoint = new Point(i, j, -1, 0);
					} else {
						endPoint = new Point(i, j, -1, 0);
					}

					laserList.add(new Point(i, j, -1, 0));
				}
			}
		}

		bfs();

		System.out.println(min);
	}

	public static void print() {
		System.out.println();
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
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

				int nm = cm; // 새로운 거울 개수 구하기 (꺾이면 +1)

				if(cd != -1 || cd != nd) { // 처음 시작이 아니고, 뱡향이 다른 경우 -> 꺾임 -> 거울 설치해야 함
					nm += 1; // 꺽임 
					System.out.println("방향 바뀜 nm : " + nm);
				}

				if(visited[nx][ny] == 0) { // 방문하지 않은 경우 
					visited[nx][ny] = nm;
					queue.add(new Point(nx, ny, nd, nm));
					
					System.out.println("방문 X ");
					print();
				} else if(visited[nx][ny] >= nm) { 
					// 이미 방문한 경우, 거울 개수가 더 작은 값으로 갱신 
					visited[nx][ny] = nm; 
					queue.add(new Point(nx, ny, nd, nm));
					
					System.out.println("방문 0 ");
					print();
				}
			}
		}

	}

}

class Point {
	int x;
	int y;
	int dir;
	int mirror;

	Point(int x, int y, int dir, int mirror) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.mirror = mirror;
	}
}
