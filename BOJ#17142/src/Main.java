/**
 * @author Minha Gwon
 * @date 2021. 4. 21.
 * 연구소3 
 * https://www.acmicpc.net/problem/17142
 * 시간 초과 
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] copyMap;
	static int[][] visited;
	static ArrayList<Point> virusList;
	static int count;
	static int min = Integer.MAX_VALUE;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		count = N*N;

		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();

				if(map[i][j] == 1 || map[i][j] == 2) count--;
			}
		}

		if(count == 0) { // 빈 칸이 없으면 퍼뜨릴 곳도 없음 
			System.out.println(0);
		} else {
			dfs(0);
			System.out.println(min != Integer.MAX_VALUE ? min : -1);
		}
	}

	public static void dfs(int depth) {
		if(depth == M) {
			copyMap = new int[N][N];
			virusList = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					copyMap[i][j] = map[i][j];
					if(copyMap[i][j] == 3) {
						virusList.add(new Point(i, j));
					}
				}
			}

			bfs();

			return;
		} else {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 2) {
						map[i][j] = 3;
						dfs(depth+1);
						map[i][j] = 2;
					}
				}
			}
		}
	}

	public static void bfs() {
		visited = new int[N][N];

		Queue<Point> queue = new LinkedList<>();
		for(Point virus : virusList) {
			queue.add(new Point(virus.x, virus.y));
			visited[virus.x][virus.y] = 1;
		}

		int cnt = 0;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N || copyMap[nx][ny] == 1) continue;

				if(visited[nx][ny] == 0) {
					visited[nx][ny] = visited[cx][cy] + 1;
					queue.add(new Point(nx, ny));
					
					if(copyMap[nx][ny] == 0) // 비활성 바이러스를 활성화 시킨 경우는 포함하지 X 
						cnt++;

					if(cnt == count) { // 바이러스를 모든 빈 칸에 퍼뜨린 경우, 그때까지 걸린 시간이 min보다 작다면 갱신 (시작할 때 방문의미로 1을 주고 시작했으니 -1해서 계산) 
						min = Math.min(min, visited[nx][ny]-1);
					}
				}
			}
		}
	}

}