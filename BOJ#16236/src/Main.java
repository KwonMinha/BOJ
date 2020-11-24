/**
 * @author Minha Gwon
 * @date 2020. 6. 12.
 * 아기 상어
 * URL : https://www.acmicpc.net/problem/16236
 * BLOG : https://minhamina.tistory.com/53
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] map;
	public static int[][] check; // 상어의 이동 거리를 체크하기 위한 배열 
	public static int N; 
	public static int SX, SY; // 상어의 x, y 좌표 
	public static int size = 2; // 상어의 초기 크기 
	public static int eat = 0; // 상어가 몇 마리의 물고기를 먹었는지 판별하기 위한 변수 
	public static int minD, minX, minY; //최소 거리, 최소 x값, 최소 y값 
	public static int ans = 0; // 정답 변수 
	public static int[][] dir = {{-1, 0}, {0, -1}, {1, 0},  {0, 1}}; // 상, 좌, 하, 우 이동을 위한 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 9) { // 상어 위치 저장 
					SX = i;
					SY = j;
				}
				map[i][j] = n;
			}
		}

		// 아기 상어 이동 시작 
		while(true) {
			initCheck(); // BFS 재탐색을 위한 초기화 함수 
			
			bfs(SX, SY); // BFS로 상어를 이동시키며, 최단 거리로 만날 수 있는 물고기를 탐색 

			if(minX != Integer.MAX_VALUE) { //물고기를 찾았다면 
				ans += minD; //그때의 이동 거리가 이동 시간이니 정답 변수에 추가 
				
				eat++; //물고기 먹었으니 +1
				if(size == eat) { //아기 상어가 자신의 크기와 같은 수의 물고기를 먹었다면, 크기 1 증가 
					size++;
					eat = 0;
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
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		minD = Integer.MAX_VALUE;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				check[i][j] = -1;
			}
		}
	}

	public static void bfs(int x, int y) {
		Queue<Shark> que = new LinkedList<Shark>();
		que.add(new Shark(x, y)); // 상어 이동 시작 
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