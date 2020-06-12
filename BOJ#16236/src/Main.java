/**
 * @author Minha Gwon
 * @date 2020. 6. 12.
 * 아기 상어
 * https://www.acmicpc.net/problem/16236
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] map;
	public static int SX, SY, N;
	public static int size = 2;
	public static int sizeCnt = 0;
	public static int minD, minX, minY;
	public static int max = Integer.MAX_VALUE;
	public static int[][] check;
	public static int ans = 0;
	public static int[][] dir = {{-1, 0}, {0, -1}, {1, 0},  {0, 1}}; // 위, 왼쪽, 아래, 오른쪽 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int jNum = Integer.parseInt(st.nextToken());
				map[i][j] = jNum;
				if(jNum == 9) {
					SX = i;
					SY = j;
					map[i][j] = 0;
				}

			}
		}

		while(true) {
			initCheck();
			bfs(SX, SY);

			if(minX != max && minY != max) {
				ans += minD;
				sizeCnt++;
				if(size == sizeCnt) {
					size++;
					sizeCnt = 0;
				}
				map[minX][minY] = 0;
				SX = minX;
				SY = minY;
			} else {
				break;
			}
		}

		System.out.println(ans);
	}

	public static void initCheck() {
		minX = max;
		minY = max;
		minD =  max;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				check[i][j] = -1;
			}
		}
	}

	public static void bfs(int x, int y) {
		Queue<Shark> que = new LinkedList<Shark>();
		que.add(new Shark(x, y));
		check[x][y] = 0;

		while(!que.isEmpty()) {
			Shark cur = que.poll();
			x = cur.sx;
			y = cur.sy;

			for(int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				if(nx < 0 || nx >= N || ny < 0 || ny >= N) 
					continue;

				if(check[nx][ny] != -1 || map[nx][ny] > size)
					continue;

				check[nx][ny] = check[x][y] + 1;

				if(map[nx][ny] != 0 && map[nx][ny] < size) {
					if(check[nx][ny] < minD) {
						minX = nx;
						minY = ny;
						minD = check[nx][ny];
					} else if(check[nx][ny] == minD) {
						if(nx == minX) {
							if(ny < minY) {
								minX = nx;
								minY = ny;
							}
						} else if(nx < minX) {
							minX = nx;
							minY = ny;
						}
					}
				}
				que.add(new Shark(nx, ny));
			}
		}	
	}

}

class Shark {
	int sx;
	int sy;

	Shark(int sx, int sy) {
		this.sx = sx;
		this.sy = sy;
	}
}