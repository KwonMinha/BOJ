/**
 * @author Minha Gwon
 * @date 2021. 2. 27.
 * 치즈
 * https://www.acmicpc.net/problem/2638
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
	static ArrayList<Point> cheeseList;
	static int cheeseCnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		cheeseList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					cheeseList.add(new Point(i, j));
					cheeseCnt += 1;
				}
			}
		}

		int time = 0;

		while(cheeseCnt != 0) {
			time++;
			visited = new boolean[N][M];
			dfs(0, 0); // 외부 공기 찾기 
			// bfs();
			melting(); // 치즈 녹이기 
		}

		System.out.println(time);
	}

	static void melting() {
		for(int i = 0; i < cheeseList.size(); i++) {
			int x = cheeseList.get(i).x;
			int y = cheeseList.get(i).y;
			int cnt = 0;

			for(int j = 0; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				if(map[nx][ny] == 2) {
					cnt++;
				}
			}

			if(cnt >= 2) { // 외부 공기와 2변 이상 접촉한 경우 
				map[x][y] = 0;
				cheeseCnt--;
				cheeseList.remove(i);
				i--;
			}
		}
	}


	// dfs로 외부와 접촉한 공기 2로 표시
	static void dfs(int x, int y) {
		visited[x][y] = true;
		map[x][y] = 2; // 외부 공기라는 의미로 2로 바꿔줌 

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if (visited[nx][ny] || map[nx][ny] == 1) continue; // 외내부 공기 판별을 위해 치즈인 경우도 pass 

			dfs(nx, ny); // 공기인 경우만 dfs 수행 
		}
	}

	// bfs로 외부와 접촉한 공기 2로 표시
	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		visited[0][0] = true;
		map[0][0] = 2;

		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (visited[nx][ny] || map[nx][ny] == 1) continue; // 외내부 공기 판별을 위해 치즈인 경우도 pass 

				map[nx][ny] = 2; // 외부 공기라는 의미로 2로 바꿔줌 
				queue.add(new Point(nx, ny)); // 공기인 경우만 큐에 넣어줌
				visited[nx][ny] = true;
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
