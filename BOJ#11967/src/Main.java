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
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] isLight;
	static boolean[][] isMove;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();

		visited = new boolean[N][N];
		isLight = new boolean[N][N];
		isMove = new boolean[N][N];
		
		 switchs = new ArrayList[N][N];
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                switchs[i][j] = new ArrayList<>();
	            }
	        }

		
		for(int i = 0; i < M; i++) {
			int x = sc.nextInt()-1;
			int y = sc.nextInt()-1;
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;

			switchs[x][y].add(new Point(a, b));
		}
		

		
		
		bfs();



	}
	
	public static boolean bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		visited[0][0] = isLight[0][0] = true;
		
		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(nx < 1 || ny < 1 || nx >= N+1 || ny >= N+1 || visited[nx][ny]) continue;
				
				
			}
		}
		
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