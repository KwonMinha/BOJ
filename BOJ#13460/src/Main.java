/**
 * @author Minha Gwon
 * @date 2021. 4. 11.
 * 구슬 탈출2
 * https://www.acmicpc.net/problem/13460
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[][] visitedCnt;
	static Point holePoint;
	static int holeX, holeY;
	static Marble blue, red;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());		
		M = Integer.parseInt(st.nextToken());		
		
		map = new char[N][M];
		visited = new boolean[N][M];
		visitedCnt = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == 'O') {
					holePoint = new Point(i, j);
					holeX = i;
					holeY = j;
				} else if(map[i][j] == 'B') {
					blue = new Marble(0, 0, i, j, 0);
				} else if(map[i][j] == 'R') {
					red = new Marble(i, j, 0, 0, 0);
				}
			}
		}
		
		if(bfs()) { // 구슬 탈출 성공 
			System.out.println(visitedCnt[holeX][holeY]);
		} else { // 실패 
			System.out.println(-1);
		}

	}
	
	public static boolean bfs() {
		Queue<Marble> queue = new LinkedList<>();
		queue.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 0));
		
		visited[red.rx][red.ry] = true;
		visitedCnt[red.rx][red.ry] = 1;
		
		while(!queue.isEmpty()) {
			Marble marble = queue.poll();
			int curRx = marble.rx;
			int curRy = marble.ry;
			int curBx = marble.bx;
			int curBy = marble.by;
			int curCnt = marble.cnt;
			
			System.out.println("----curCnt : " + curCnt);
			
			if(curBx == holeX && curBy == holeY) { // 파란 구슬이 구멍에 빠지면 실패 
				System.out.println("* 파란 구슬 구멍에 빠짐  ");
				return false;
			}
			
			if(curCnt > 10) { // 이동 횟수가 10 초과시 실패 
				System.out.println("* 이동 횟수 10 초과 ");
				return false;
			}
			
			if(curRx == holeX && curRy == holeY) { // 이동 횟수 10이하로 빨간 구슬이 먼저 탈출하면 성공 
				System.out.println("* success : 빨간 구슬 탈출 성공 ");
				return true;
			}
			
			for(int i = 0; i < 4; i++) {
				System.out.println("--------curRx : " + curRx + ", curRy : " + curRy + ", i : " + i);
				int crx = curRx;
				int cry = curRy;
				int cbx = curBx;
				int cby = curBy;
				
				while(true) { // 1칸 움직이고 끝이 아니라 빨간 구슬이 #를 만날때 까지 움직임 
					int newRx = crx + dx[i];
					int newRy = cry + dy[i];
					int newBx = cbx + dx[i];
					int newBy = cby + dy[i];
					
					// 빨간 구슬이 범위를 #을 만나면 pass 
					if(newRx < 1 || newRy < 1 || newRx >= N || newRy >= M || map[newRx][newRy] == '#') {
						System.out.println("* 빨간 구슬 범위 이탈 ");
						break;
					} 
					
					// 파란 구슬은 빨간 구슬과 같은 방향으로 움직임 -> #을 만나면 이전에 있던 자리로 돌려놓음 
					if(newBx < 1 || newBy < 1 || newBx >= N || newBy >= M || map[newBx][newBy] == '#') {
						newBx = newBx - dx[i];
						newBy = newBy - dy[i];
						System.out.println("* 파란 구슬 범위 이탈 원상태로 ");
					}
					
					// 움직인 빨간 구슬자리에 파란 구슬이 있으면 pass 
					if(newRx == newBx && newRy == newBy) {
						System.out.println("* 빨간 구슬 파란 구슬 만남 ");
						break;
					}
					
					/*
					// 이미 방문한 곳이라면 pass 
					if(visited[newRx][newRy]) {
						break;
					}
					
					// 빨간 구슬이 이동할 수 있는 곳이라면
					visited[newRx][newRy] = true;
					queue.add(new Marble(newRx, newRy, newBx, newBy, curCnt + 1)); // 같은 방향으로 1칸이든 2칸이든 #까지 움직이는 newCnt는 curCnt+1로 동일 
					*/		 
		
					if(visitedCnt[newRx][newRy] == 0) { // 처음 이동하는 위치의 경우 
						System.out.println("첫 방문 ");
						visitedCnt[newRx][newRy] = curCnt + 1;
						queue.add(new Marble(newRx, newRy, newBx, newBy, curCnt + 1)); // 같은 방향으로 1칸이든 2칸이든 #까지 움직이는 newCnt는 curCnt+1로 동일 
					} 
//					else if(visitedCnt[newRx][newRy] > curCnt + 1) { // 이미 방문한 곳이라도 같거나 더 적은 cnt로 이동할 수 있다면 이동함
//						System.out.println("더 적은 cnt로 방문 : " + visitedCnt[newRx][newRy] + " > " + (curCnt + 1));
//						visitedCnt[newRx][newRy] = curCnt + 1;
//						queue.add(new Marble(newRx, newRy, newBx, newBy, curCnt + 1)); 
//					} 
					
					crx = newRx;
					cry = newRy;
					cbx = newBx;
					cby = newBy;
				}
			}
			
			System.out.println("\n");
		}
		
		
		System.out.println("true???");
		return false;
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

class Marble {
	int rx;
	int ry;
	int bx;
	int by;
	int cnt;
	
	Marble(int rx, int ry, int bx, int by, int cnt) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
}
