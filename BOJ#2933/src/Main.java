/**
 * @author Minha Gwon
 * @date 2021. 6. 30.
 * 미네랄
 * https://www.acmicpc.net/problem/2933
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	static int[][] check;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		String[][] map = new String[R][C];
		check = new int[R][C];

		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < C; j++) {
				map[i][j] = st.nextToken();
			}
		}

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			if(i % 2 != 0) { // 홀수 번째 (왼 -> 오)
				for(int j = 0; j < C; j++) {
					if(map[C-h][j].equals("x")) {
						
					}
				}
			} else { // 짝수 번째 (오 -> 왼)
				for(int j = C-1; j >= 0; j++) {
					if(map[C-h][j].equals("x")) {
						
					}
				}
			}
		}
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		while(!queue.isEmpty()) {
			
		}
	}

}

class Point {
	int r;
	int c;
	
	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
