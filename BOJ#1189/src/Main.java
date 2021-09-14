/**
 * @author Minha Gwon
 * @date 2021. 9. 14.
 * 컴백홈
 * https://www.acmicpc.net/problem/1189
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int R, C, K;
	static int answer = 0;
	static Point end;

	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for(int i = 0; i < R; i++) {
			String str = br.readLine();

			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		end = new Point(0, C-1);

		visited[R-1][0] = true;
		dfs(R-1, 0, 1);

		System.out.println(answer);

	}

	static void dfs(int x, int y, int length) {
		if(x == end.x && y == end.y) {
			if(length == K) {
				answer++;
			}
			
			return;
		}

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny])
				continue;

			if(map[nx][ny] != 'T') {
				visited[nx][ny] = true;
				dfs(nx, ny, length+1);
				visited[nx][ny] = false;
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
