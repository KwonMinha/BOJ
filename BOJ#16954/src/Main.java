/**
 * @author Minha Gwon
 * @date 2021. 7. 7.
 * 움직이는 미로 탈출 
 * https://www.acmicpc.net/problem/16954
 */

import java.util.*;
import java.io.*;

public class Main {
	static char[][] map;

	public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1, 0}; //상, 상좌, 좌, 좌하, 하, 하우, 우, 상우, 그대로 순서 
	public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[8][8];

		for(int i = 0; i < 8; i++) {
			String temp = br.readLine();

			for(int j = 0; j < 8; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		bfs(7, 0);
	}

	static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(7, 0));
	
		while(!queue.isEmpty()) {
			int size = queue.size();

			for(int s = 0; s < size; s++) {
				int cx = queue.peek().x;
				int cy = queue.poll().y;

				if(map[cx][cy] == '#')
					continue;

				if(cx == 0 && cy == 7) {
					System.out.println(1);
					return;
				}

				for(int i = 0; i < 9; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];

					if(nx < 0 || ny < 0 || nx >= 8 || ny >= 8 || map[nx][ny] == '#')
						continue;

					queue.add(new Point(nx, ny));
				}
			}
			
			moveWall();
		}

		System.out.println("0");
	}

	static void moveWall() {
		for(int i = 6; i >= 0; i--) {
			for(int j = 0; j < 8; j++) {
				map[i+1][j] = map[i][j];
			}
		}

		Arrays.fill(map[0], '.');
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