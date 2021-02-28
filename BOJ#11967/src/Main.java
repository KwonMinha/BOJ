/**
 * @author Minha Gwon
 * @date 2021. 3. 1.
 * 불켜기 
 * https://www.acmicpc.net/problem/11967
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static ArrayList<Point>[][] switchs;
	static boolean[][] visited;
	static boolean[][] isLight;
	static boolean[][] isMove;

	static int cnt = 1;


	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};




	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();

		visited = new boolean[N][N];
		isLight = new boolean[N][N];
		isMove = new boolean[N][N];

		switchs = new ArrayList[N][N]; // x,y스위치로 켤 수 있는 a,b방에 대한 인접 리스트 만들기 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				switchs[i][j] = new ArrayList<>();
			}
		}

		for(int i = 0; i < M; i++) {
			int x = sc.nextInt()-1; // (0, 0) 시작을 위해 -1 
			int y = sc.nextInt()-1;
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;

			switchs[x][y].add(new Point(a, b));
		}

		bfs();

		System.out.println(cnt);
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		visited[0][0] = isLight[0][0] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;

			// 현재 위치에서 이동할 수 있는 상하좌우 4방향 방 체크하기 
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

				isMove[nx][ny] = true;
			}

			// 현재 스위치가 켤 수 있는 모든 불들을 켜 봄 
			for(int i = 0; i < switchs[cx][cy].size(); i++) {
				Point p = switchs[cx][cy].get(i);

				if(!isLight[p.x][p.y]) { // 불이 꺼져있다면 불을 켬 
					isLight[p.x][p.y] = true;
					cnt++;
				}

				// 이동할 수 있는 방이라면 큐에 추가 
				if(isMove[p.x][p.y] && !visited[p.x][p.y]) {
					visited[p.x][p.y] = true;
					queue.add(new Point(p.x, p.y));
				}
			}

			// 본격적으로 현재 위치에서 이동할 수 있는 방으로 이동 
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue; // 범위를 넘어가는 경우
				if(visited[nx][ny] || !isMove[nx][ny] || !isLight[nx][ny]) continue; // 이미 방문한 방, 이동할 수 없는 방, 불이 꺼진 방의 경우 
				
				visited[nx][ny] = true;
				queue.add(new Point(nx, ny));
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