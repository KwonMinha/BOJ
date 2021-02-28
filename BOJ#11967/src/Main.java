/**
 * @author Minha Gwon
 * @date 2021. 3. 1.
 * 불켜기 
 * https://www.acmicpc.net/problem/11967
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;

	static ArrayList<Light> lightList = new ArrayList<>();

	static int[][] map;

	static boolean[][] visited;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();

		map = new int[N+1][N+1];
		map[1][1] = 1;


		for(int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();

			if(x == 1 && y == 1) {
				map[a][b] = 1;
			} else {
				lightList.add(new Light(x, y, a, b));
			}
		}

		Collections.sort(lightList);

//		for(int i = 0; i < lightList.size(); i++) {
//			Light l = lightList.get(i);
//			System.out.println(l.x + " " + l.y + " " + l.a + " " + l.b);
//		}
//		System.out.println();
		
		for(int i = 0; i < lightList.size(); i++) {
			Light l = lightList.get(i);
			
			if(!bfs(l.x, l.y)) {
				continue;
			} else {
				map[l.a][l.b] = 1;
			}
		}
		
		int ans = 0;
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				if(map[i][j] == 1) {
					ans++;
				}
				//System.out.print(map[i][j] + " ");
			}
			//System.out.println();
		}

		System.out.println(ans);



	}
	
	public static boolean bfs(int a, int b) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(1, 1));
		visited = new boolean[N+1][N+1];
		visited[1][1] = true;
		
		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;
			
			if(cx == a && cy == b) {
				return true;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(nx < 1 || ny < 1 || nx >= N+1 || ny >= N+1 || visited[nx][ny]) continue;
				
				if(map[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
				}
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

class Light implements Comparable<Light>{
	int x;
	int y;
	int a;
	int b;

	Light(int x, int y, int a, int b) {
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Light o) {
		if(x == o.x) {
			if(y == o.y) {
				if(a == o.a) {
					return b - o.b;
				} else {
					return a - o.a;
				}
			} else {
				return y - o.y;	
			}
		} else {
			return x - o.x;
		}
	}
}