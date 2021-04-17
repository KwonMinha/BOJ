/**
 * @author Minha Gwon
 * @date 2021. 4. 18.
 * 사다리 조작 
 * https://www.acmicpc.net/problem/15684
 * 시간초과 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
	static class Point {
		int x1;
		int x2;
		int y1;
		int y2;

		Point(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	static ArrayList<Point>[][] map;
	static int N, M, H;
	static boolean[][][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		if(M == 0) { // 가로선 없는 경우 
			System.out.println("0");
			System.exit(0);
		}

		map = new ArrayList[H+1][N+1];
		for(int i = 1; i < H+1; i++) {
			for(int j = 1; j < N+1; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		visited = new boolean[H+1][N+1][H+1][N+1];

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			Point point1 = new Point(a, b, a, b+1);
			Point point2 = new Point(a, b+1, a, b);

			map[a][b].add(point1);
			map[a][b+1].add(point2);

			visited[a][b][a][b+1] = true;
			visited[a][b+1][a][b] = true;
		}

		for(int i = 1; i <= 3; i++) { 
			dfs(i, 0, i);
		}

		System.out.println(-1);
	}

	public static void dfs(int n, int depth, int cnt) {
		if(depth == n) {
//			// check2 코드 
//			ArrayList<Point>[][] copyMap = new ArrayList[H+1][N+1];
//			for(int i = 1; i < H+1; i++) {
//				for(int j = 1; j < N+1; j++) {
//					copyMap[i][j] = new ArrayList<>();
//				}
//			}
//
//			for(int i = 1; i < H+1; i++) {
//				for(int j = 1; j < N; j++) {
//
//					if(visited[i][j][i][j+1]) {
//						copyMap[i][j].add(new Point(i, j, i, j+1));
//						copyMap[i][j+1].add(new Point(i, j+1, i, j));
//					}
//				}
//			}
//
//			check2(cnt, copyMap);

			check(cnt);

			return;
		}

		for(int i = 1; i < H+1; i++) {
			for(int j = 1; j < N; j++) {
				int[][] dy = {{0, 1}, {-1, 0}, {1, 2}};

				boolean flag = true;
				for(int k = 0; k < 3; k++) {
					int ny1 = j + dy[k][0];
					int ny2 = j + dy[k][1];

					if(ny1 <= 0 || ny2 <= 0 || ny1 >= N+1 || ny2 >= N+1) continue;

					if(visited[i][ny1][i][ny2]) {
						flag = false;
						break;
					}
				}

				if(flag) {
					Point p1 = new Point(i, j, i, j+1);
					Point p2 = new Point(i, j+1, i, j);

					map[i][j].add(p1);
					map[i][j+1].add(p2);

					visited[i][j][i][j+1] = true;
					visited[i][j+1][i][j] = true;

					dfs(n, depth+1, cnt);

					visited[i][j][i][j+1] = false;
					visited[i][j+1][i][j] = false;

					map[i][j].remove(p1);
					map[i][j+1].remove(p2);
				}
			}
		}
	}

	public static void check(int cnt) {
		for(int i = 1; i < N+1; i++) {
			int curX = 1;
			int curY = i;

			while(curX != H+1) {

				if(map[curX][curY].isEmpty()) {
					curX++;
				} else {
					curY = map[curX][curY].get(0).y2;
					curX++;
				}
			}

			if(curY != i)
				return;
		}

		System.out.println(cnt);
		System.exit(0);
	}

	public static void check2(int cnt, ArrayList<Point>[][] copyMap) {
		for(int i = 1; i < N+1; i++) {
			int curX = 1;
			int curY = i;

			while(curX != H+1) { // 사다리 타기 
				if(copyMap[curX][curY].isEmpty()) {
					curX++;
				} else {
					curY = copyMap[curX][curY].get(0).y2;
					curX++;
				}
			}

			if(curY != i) { // 실패 - i번 세로선의 결과가 i X
				return;
			}
		}

		// 성공 - i번 세로선의 결과가 i번
		System.out.println(cnt);
		System.exit(0);
	}
}