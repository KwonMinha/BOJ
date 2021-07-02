/**
 * @author Minha Gwon
 * @date 2021. 6. 30.
 * 미네랄
 * https://www.acmicpc.net/problem/2933
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};

	static int R, C;

	static String[][] map;
	static boolean[][] visited;

	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new String[R][C];

		for(int i = 0; i < R; i++) {
			String str = br.readLine();

			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) + "";
			}
		}

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());

			System.out.println("-------------" + h + "------------");

			if(i % 2 == 0) { // 짝수 번째 (왼 -> 오) / 인덱스 0부터 시작하기 때문 
				System.out.println("left -> right");

				for(int j = 0; j < C; j++) {
					if(map[C-h][j].equals("x")) {
						map[C-h][j] = ".";
						cluster(C-h, j);
						break;
					}
				}
			} else { // 홀수 번째 (오 -> 왼)
				System.out.println("right -> left");

				for(int j = C-1; j >= 0; j--) {
					if(map[C-h][j].equals("x")) {
						map[C-h][j] = ".";
						cluster(C-h, j);
						break;
					}
				}
			}

			print();
		}
	}

	static void cluster(int x, int y) {
		visited = new boolean[R][C];

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny].equals("."))
				continue;

			if(!visited[nx][ny]) {
				if(!bfs(nx, ny)) {
					move(nx, ny);
				}
			}
		}
	}

	static void move(int x, int y) {
		System.out.println(x + " " + y);
	}

	// 땅에 닫는지 확인 
	static boolean bfs(int x, int y) {
		pq = new PriorityQueue<>(new Comparator<>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.r == o2.r) {
					return o1.c - o2.c;
				} else {
					return 0;
				}
			}
		});

		pq.add(new Point(x, y));

		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));

		visited[x][y] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().r;
			int cy = queue.poll().c;

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny].equals("."))
					continue;

				if(nx == R-1) {
					return true;
				}
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
					
					pq.add(new Point(nx, ny));
				}
			}
		}

		return false;
	}

	static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("");			
	}

}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
