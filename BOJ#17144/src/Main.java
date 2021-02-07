/**
 * @author Minha Gwon
 * @date 2021. 2. 8.
 * 미세먼지 안녕! 
 * https://www.acmicpc.net/problem/17144
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R, C, T;
	static int[][] map;
	//static boolean[][] visited;
	static ArrayList<Point> dustList;
	static int airCleanerR;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();

		map = new int[R][C]; // 초기 맵 구성 
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == -1) {
					airCleanerR = i-1;
				}
			}
		}

		for(int i = 0; i < T; i++) {
			// 먼지가 확산되고 정화됨에따라 없어지기때문에 매 초마다 먼지리스트 구함 
			dustList = new ArrayList<>();
			findDust();

			spreadDust(); // 먼지 확산 

			runAirCleaner(airCleanerR, 0, true); // 위쪽 공기청정기 - 반시계 방향 공기청정기 순환  
			runAirCleaner(airCleanerR+1, 0, false); // 아래쪽 공기청정기 - 시계 방향 공기청정기 순환 
		}

		int answer = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != 0 && map[i][j] != -1) {
					answer += map[i][j];
				}
			}
		}

		System.out.println(answer);
	}

	public static void findDust() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != -1 && map[i][j] != 0) {
					dustList.add(new Point(i, j, map[i][j]));
				}
			}
		}
	}

	public static void spreadDust() {
		Queue<Point> queue = new LinkedList<>();
		for(Point p : dustList) {
			queue.add(p);
		}

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.peek().y;
			int cDust = queue.poll().dust; // 현재 좌표의 미세먼지 양 

			int spreadCnt = 0; // 확산된 방향의 개수

			for(int i = 0; i < 4; i++) { // 상하좌우 먼지 퍼뜨릴 새로운 좌표 확인 
				int nx = cx + dx[i]; 
				int ny = cy + dy[i];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) // 범위 벗어나거나 공기청정기라면  pass 
					continue;

				spreadCnt++;
				map[nx][ny] += cDust / 5; // 확산되는 미세먼지 양 
			}

			map[cx][cy] -= (cDust / 5) * spreadCnt; // 남은 미세먼지 양 
		}
	}

	public static void runAirCleaner(int x, int y, boolean isUp) {
		int[] arr = {3, 0, 1, 2}; // 우상좌하 이동 

		int cx = x; // 현재 좌표값 
		int cy = y;

		int preDust = -1; // 한칸씩 먼지 이동을 위한 먼지 초기값 

		// 상하좌우 방향에 따라 움직일 범위를 지정해준다. 
		for(int i = 0; i < 4; i++) {
			int start = 0;
			int end = 0;
			int dir = arr[i];

			if(dir == 1 || dir == 3) { // 좌, 우 방향 - y값만 바뀜 
				start = y;
				end = C;
			} else { // 상, 하 방향 - x값만 바뀜 
				if(!isUp) { // 아래쪽 공기청정기라면 상, 하 방향을 바꾸어서 시계 방향으로 순환하게 만듬 - 우하좌상 
					dir = (dir + 2) % 4;
				}
				start = x;
				end = R;
			}

			// 먼지 이동 
			for(int j = start; j < end; j++) {
				int nx = cx + dx[dir];
				int ny = cy + dy[dir];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C ) // 범위를 벗어나면 pass 
					continue;

				if(map[nx][ny] == -1)  // 공기청정기를 만난다면 순환 끝 
					break;

				cx = nx; // 이동할 수 있는 곳이라면 좌표값 갱신 
				cy = ny;

				if(preDust == -1) { // 시작 좌표라면 temp 값 갱신만하고 다음 좌표부터 시작  
					preDust = map[nx][ny];
					map[nx][ny] = 0; // 이동했으니 빈칸으로 만들어 줌 
					continue;
				}

				// 시작 좌표가 아니라면 이전 먼지값으로 갱신해 먼지 한 칸 이동 
				int temp = map[nx][ny];
				map[nx][ny] = preDust;
				preDust = temp;
			}
		}
	}
}

class Point {
	int x;
	int y;
	int dust;

	Point(int x, int y, int dust) {
		this.x = x;
		this.y = y;
		this.dust = dust;
	}
}
