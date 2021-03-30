/**
 * @author Minha Gwon
 * @date 2021. 3. 30.
 * 벽 부수고 이동하기 
 * https://www.acmicpc.net/problem/2206
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
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			String num = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = num.charAt(j) - '0';
				//System.out.print(map[i][j]);
			}
			//System.out.println();
		}
		
		bfs();
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		int[][] visited = new int[N][M];
		queue.add(new Point(0, 0, 1, 0));
		visited[0][0] = 1;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.x == N-1 && cur.y == M-1) {
				min = Math.min(min, cur.dist);
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				
				if(visited[nx][ny] == 0) { // 한번도 방문하지 않은 곳일 경우 
					if(map[nx][ny] == 0) { // 빈칸일 경우 
						queue.add(new Point(nx, ny, cur.dist+1, cur.wall));
					} else { // 벽일 경우 
						if(cur.wall == 0) {
							queue.add(new Point(nx, ny, cur.dist+1, cur.wall+1));
						}
					}
					visited[nx][ny] = cur.dist + 1;
				} else if(visited[nx][ny] >= cur.dist + 1){ // 이미 방문한 곳이지만 더 적은 거리로 올 수 있는 경우 
					if(map[nx][ny] == 0) { // 빈칸일 경우 
						queue.add(new Point(nx, ny, cur.dist+1, cur.wall));
					} else { // 벽일 경우 
						if(cur.wall == 0) {
							queue.add(new Point(nx, ny, cur.dist+1, cur.wall+1));
						}
					}
				}
			}
		}
	}

}

class Point {
	int x;
	int y;
	int dist;
	int wall;
	
	Point(int x, int y, int dist, int wall) {
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.wall = wall;
	}
}