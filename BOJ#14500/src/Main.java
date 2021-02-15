/**
 * @author Minha Gwon
 * @date 2021. 2. 14.
 * 테트로미노
 * https://www.acmicpc.net/problem/14500
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map, tetroMap;
	static ArrayList<Point> tetrominoList;
	static int answer = Integer.MIN_VALUE;

	public static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계방향 순서 
	public static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		tetroMap = new int[N][M];

		dfs(0);
		
		System.out.println(answer);
	}

	// dfs로 4개의 정사각형을 NxM 배열에 모두 놓아본다. 
	static void dfs(int depth) {
		if(depth == 4) { // 4개의 정사각형이 모두 놓아진 경우 
			bfs();

			return;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tetroMap[i][j] == 0) {
					tetroMap[i][j] = 1;
					dfs(depth+1);
					tetroMap[i][j] = 0; // 놓은 이후, 다음 dfs를 위해 다시 원상태 0으로 돌려줌 
				}
			}
		}
	}

	// bfs를 이용해 놓여진 정사각형이 테트로미노인지 확인 
	static void bfs() {
		tetrominoList = new ArrayList<>();
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		loop:
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(tetroMap[i][j] == 1) {
						queue.add(new Point(i, j)); // 정사각형이 놓여진 첫번째 위치를 찾고, 그 위치부터 bfs 시작 
						tetrominoList.add(new Point(i, j));
						visited[i][j] = true;

						break loop;
					}
				}
			}

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= M) 
					continue;

				if(!visited[nx][ny] && tetroMap[nx][ny] == 1) {
					queue.add(new Point(nx, ny));
					tetrominoList.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}

		if(tetrominoList.size() == 4) { // 4개의 정사각형이 연결되어 있는 경우 -> 테트로미노 
			tetromino();
		}
	}

	// 테트로미노가 놓인 칸에 쓰여 있는 수들의 최대합 구하기 
	static void tetromino() {
		int sum = 0;
		for(Point p : tetrominoList) {
			sum += map[p.x][p.y];
		}
		answer = Math.max(answer, sum);
	}

	static void print(int[][] arr) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
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