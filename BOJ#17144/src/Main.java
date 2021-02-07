/**
 * @author Minha Gwon
 * @date 2021. 2. 8.
 * 미세먼지 안녕! 
 * https://www.acmicpc.net/problem/17144
 */

/*
3 3 3 
0 30 7
-1 10 0
-1 0 20
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

		map = new int[R][C];
		

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == -1) {
					airCleanerR = i-1;
				}
			}
		}

		//		System.out.println(airCleanerR);
		//		print();


		for(int i = 0; i < T; i++) {
			dustList = new ArrayList<>();
			findDust();
			
			System.out.println("현재 " + (i+1) + "초임 !!!");
			//visited = new boolean[R][C];
			spreadDust();
			System.out.println("먼지 퍼짐 ");
			print();

			//System.out.println("UP air : " + airCleanerR + " " + 0);
			runAirCleaner(airCleanerR, 0, true); // 위쪽 공기청정기 - 반시계 방향 공기청정기 순환  
			System.out.println("위쪽 순환 완료  ");
			print();

			//System.out.println("DOWN air : " + (airCleanerR+1) + " " + 0);
			runAirCleaner(airCleanerR+1, 0, false); // 아래쪽 공기청정기 - 시계 방향 공기청정기 순환 
			System.out.println("아래쪽 순환 완료  ");
			print();
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
			int cDust = queue.poll().dust;

			int spreadCnt = 0;

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) {
					continue;
				}

				spreadCnt++;
				map[nx][ny] += cDust / 5;
			}

			map[cx][cy] -= (cDust / 5) * spreadCnt;
		}
	}

	public static void runAirCleaner(int x, int y, boolean isUp) {
		int[] arr = {3, 0, 1, 2};

		int cx = x;
		int cy = y;

		int preDust = -1;
		boolean flag = false;

		for(int i = 0; i < 4; i++) {
			int start = 0;
			int end = 0;
			int dir = arr[i];

			if(dir == 1 || dir == 3) { // 좌, 우 방향 
				//System.out.println("좌, 우 ");
				start = y;
				end = C;
			} else { // 상, 하 방향 
				//System.out.println("상, 하  ");
				if(!isUp) { // 아래쪽 공기청정기라면 상, 하 방향을 바꾸어서 시계 방향으로 순환하게 만듬 
					dir = (dir + 2) % 4;
				}
				start = x;
				end = R;
			}

			for(int j = start; j < end; j++) {
				int nx = cx + dx[dir];
				int ny = cy + dy[dir];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C ) {
					continue;
				}

				if(map[nx][ny] == -1) { // 공기청정기를 만난다면 순환 끝 
					//System.out.println("break");
					break;
				}

				//System.out.println(nx + " " + ny);
				cx = nx;
				cy = ny;

				if(preDust == -1) { // 시작 좌표라면 temp 값 갱신만하고 다음 좌표부터 시작  
					preDust = map[nx][ny];
					map[nx][ny] = 0; // 이동했으니 빈칸으로 만들어 줌 
					continue;
				}

				int temp = map[nx][ny];
				map[nx][ny] = preDust;
				preDust = temp;






			}
		}
		System.out.println();
	}

	public static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
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
