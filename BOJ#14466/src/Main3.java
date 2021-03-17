/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 소가 길을 건너간 이유 6
 * https://www.acmicpc.net/problem/14466
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main3 {
	static ArrayList<Point> cowList = new ArrayList<>();
	static int[][] cowMap;
	static boolean[][] visited;
	static int N;
	static boolean[][] check;
	static ArrayList<Point>[][] roads;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int K = sc.nextInt();
		int R = sc.nextInt();

		// 길 위치 저장
		roads = new ArrayList[N+1][N+1];
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				roads[i][j] = new ArrayList<>();
			}
		}

		for(int i = 0; i < R; i++) {
			int r1 = sc.nextInt();
			int c1 = sc.nextInt();
			int r2 = sc.nextInt();
			int c2 = sc.nextInt();

			roads[r1][c1].add(new Point(r2, c2));
			roads[r2][c2].add(new Point(r1, c1));
		}

		// 소 위치 저장 
		cowMap = new int[N+1][N+1];

		for(int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			cowList.add(new Point(x, y));
			cowMap[x][y] = i+1;
		}

		int ans = 0;
		for(int i = 0; i < cowList.size(); i++) {
			check = new boolean[N+1][N+1];

			bfs(cowList.get(i).x, cowList.get(i).y);

			for(int j = i+2; j < K+1; j++) {
				if(!check[i+1][j]) {
					ans++;
				} 
			}
		}

		System.out.println(ans);
	}

	public static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));

		visited = new boolean[N+1][N+1];
		visited[x][y] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx <= 0 || ny <= 0 || nx > N || ny > N || visited[nx][ny]) continue; // 범위를 벗어나거나, 이미 방문한 곳이라면 pass

				boolean isRoad = false;
				for(int j = 0; j < roads[cx][cy].size(); j++) { // 현재 위치가 길인데 새로운 위치도 길이라서 길을 건너야 한다면 pass 
					if(roads[cx][cy].get(j).x == nx && roads[cx][cy].get(j).y == ny) {
						isRoad = true;
						break;
					}
				}
				
				if(isRoad) continue;
				
				if(cowMap[nx][ny] >= 1) check[cowMap[x][y]][cowMap[nx][ny]] = true;

				visited[nx][ny] = true;
				queue.add(new Point(nx, ny));
			}
		}
	}

}

/*
class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
} */