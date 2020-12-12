/**
 * @author Minha Gwon
 * 2020. 10. 16.
 * URL : https://www.acmicpc.net/problem/14503
 * #14503 - 로봇 청소기 
 */

import java.util.*;
import java.io.*;

public class Main {
	public static int N, M, R, C, D;
	public static int[][] map;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		//0 1 2 3 상 좌 하 우 
		if(D == 1)
			D = 3;
		else if(D == 3)
			D = 1;
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		boolean flag = true;
		while(true) {
			if(flag == true) {
				
				map[R][C] = 2; //청소하기 
				ans++;
			}
			
			if(cnt == 4) { //4방향 모두 청소가 되어있거나 벽인 경우 
				int nx = R + dx[(D+2)%4];
				int ny = C + dy[(D+2)%4];
				
				if(map[nx][ny] == 1) {
					break;
				} else {
					R = nx;
					C = ny;
					
					cnt = 0;
				}
			}
			
			int nd = D+1; //왼쪽 방향 
			nd %= 4;
			int nx = R + dx[nd];
			int ny = C + dy[nd];
			
			if(map[nx][ny] == 0) { //청소할 공간이 있다면 방향 바꾸고, 그 위치로 이동 
				D = nd;
				R = nx;
				C = ny;
				cnt = 0;
				flag = true;
			} else {
				D = nd;
				cnt++;
				flag = false;
			}
		}
		
		System.out.println(ans);
	}
}
