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
				if(jNum == 9) {
					SX = i;
					SY = j;
				}
				map[i][j] = jNum;
			}
		}

		while(true) {
			initCheck(); //재탐색을 위한 초기화 함수 
			bfs(SX, SY); //bfs로 최단 거리로 만날 수 있는 물고기를 탐색 

			if(minX != max && minY != max) { //물고기를 찾았다면 
				ans += minD; //그때의 이동 거리가 이동 시간이니 정답 변수에 추가 
				sizeCnt++;
				if(size == sizeCnt) { //아기 상어가 자신의 크기와 같은 수의 물고기를 먹었다면, 크기 1 증가 
					size++;
					sizeCnt = 0;
				}
				
				//아기 상어 위치 이동 
				map[minX][minY] = 0;
				SX = minX;
				SY = minY;
			} else {
				break;
			}
		}
		//더이상 먹을 물고기가 없다면 정답 출력 
		System.out.println(ans);
	}

	public static void initCheck() {
		minX = max;
		minY = max;
		minD = max;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				check[i][j] = -1;
			}
		}
	}

	public static void bfs(int x, int y) {
		Queue<Shark> que = new LinkedList<Shark>();
		que.add(new Shark(x, y));
		map[x][y] = 0;
		check[x][y] = 0;

		while(!que.isEmpty()) {
			Shark cur = que.poll();
			x = cur.sx;
			y = cur.sy;
			
			//4방향 탐색 
			for(int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				if(nx < 0 || nx >= N || ny < 0 || ny >= N) //map 범위를 벗어나면 이동 X 
					continue;

				if(check[nx][ny] != -1 || map[nx][ny] > size) //이미 갔던 곳이고, 현재 상어의 크기보다 크면 이동 X
					continue;
				
				//위의 조건을 모두 벗어난 이동할 수 있는 곳이라면 
				check[nx][ny] = check[x][y] + 1; //이동 거리 1 증가 

				//그런데 이곳이 현재의 아기 상어보다 작은 물고기가 있는 곳이라면 
				if(map[nx][ny] != 0 && map[nx][ny] < size) { 
					if(check[nx][ny] < minD) {
						minX = nx;
						minY = ny;
						minD = check[nx][ny];
					} else if(check[nx][ny] == minD) { //거리가 가까운 물고기가 많다면 
						if(nx < minX) { //가장 위쪽에 있는 물고기 먹음
							minX = nx;
							minY = ny;
						} else if(nx == minX) { //가장 위쪽에 있는 물고기가 많다면 
							if(ny < minY) { //가장 위쪽 중, 가장 왼쪽에 있는 물고기 먹음 
								minX = nx;
								minY = ny;
							}
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