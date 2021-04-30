/**
 * @author Minha Gwon
 * @date 2021. 4. 30.
 * 색종이 
 * https://www.acmicpc.net/problem/2630
 */

import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int N;
	static boolean[][] visited;
	static int blue = 0;
	static int white = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(N, 0, 0);

		System.out.println(white + "\n" + blue);
	}

	public static void solve(int n, int x, int y) {
		if(visited[x][y]) {
			return;
		}

		if(check(n, x, y)) {
			visited[x][y] = true;
			return;
		}

		solve(n/2, x, y);
		solve(n/2, x, n/2+y);
		solve(n/2, n/2+x, y);
		solve(n/2, n/2+x, n/2+y);
	}

	public static boolean check(int n, int x, int y) {
		int color = arr[x][y];

		for(int i = x; i < x+n; i++) {
			for(int j = y; j < y+n; j++) {
				if(arr[i][j] != color) {
					return false;
				}
			}
		}
		
		if(color == 1) 
			blue++; 
		else 
			white++;

		return true;
	}

}
