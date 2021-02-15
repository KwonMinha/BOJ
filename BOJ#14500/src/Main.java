/**
 * @author Minha Gwon
 * @date 2021. 2. 14.
 * 테트로미노
 * https://www.acmicpc.net/problem/14500
 */

import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int answer = Integer.MIN_VALUE;

	public static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계 방향 순서 
	public static int[] dy = {0, 1, 0, -1};

	static int ex[][] = {{0, 0, 0, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, -1, 0, 1}}; // ㅜ로 나올 수 있는 4가지 모양 미리 만듬 
	static int ey[][] = {{0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {0, 1, 1, 1}};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][M+1];

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				visited = new boolean[N+1][M+1];
				visited[i][j] = true;
				dfs(1, i, j, map[i][j]);
				visited[i][j] = false;

				exShape(i, j);
			}
		}

		System.out.println(answer);
	}

	// DFS로 ㅜ 제외 4가지 모양 최대합 검사 
	public static void dfs(int depth, int x, int y, int sum) {
		if(depth == 4) { // 4개의 정사각형이 모두 놓아진 경우 
			answer = Math.max(answer, sum);
			return;
		}

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 1 || nx > N || ny < 1 || ny > M) 
				continue;

			if(!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(depth+1, nx, ny, sum + map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}

	// ㅜ 모양 검사 
	public static void exShape(int x, int y){
		for(int i = 0; i < 4; i++){
			boolean isOut = false;
			int sum = 0;

			for(int j = 0; j < 4; j++){
				int nx = x + ex[i][j];
				int ny = y + ey[i][j];

				if(nx < 0 || nx > N || ny < 0 || ny > M) {
					isOut = true;
					break;
				}
				else {
					sum += map[nx][ny];
				}
			}

			if(!isOut) 
				answer = Math.max(answer, sum);
		}
	}
}