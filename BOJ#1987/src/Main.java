/**
 * @author Minha Gwon
 * @date 2021. 7. 16.
 * 알파벳 
 * https://www.acmicpc.net/problem/1987
 */

import java.util.Scanner;

public class Main {
	static String[][] map;
	static boolean[][] visited;
	static int R, C;
	static int ans = 0;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new String[R][C];
		visited = new boolean[R][C];
		
		sc.nextLine();
		
		for(int i = 0; i < R; i++) {
			String temp = sc.nextLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j) + "";
			}
		}
		visited[0][0] = true;
		dfs(0, 0, map[0][0], 1);
		
		System.out.println(ans);
	}
	
	public static void dfs(int x, int y, String str, int length) {
		if(ans < length) {
			ans = length;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny])
				continue;
			
			if(!str.contains(String.valueOf(map[nx][ny]))) {
				visited[nx][ny] = true;
				dfs(nx, ny, str+map[nx][ny], length+1);
				visited[nx][ny] = false;
			}
		}
	}

}