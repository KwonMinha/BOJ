/**
 * @author Minha Gwon
 * @date 2021. 2. 8.
 * 미세먼지 안녕! 
 * https://www.acmicpc.net/problem/17144
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int R, C, T;
	static int[][] map;
	static LinkedList<Point> dustQueue;
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
			// 먼지가 확산되고 정화됨에따라 없어지기 때문에, 매 초마다 먼지큐를 새로 만듬 
			findDust();

			spreadDust(); // 먼지 확산 
			
			runAirCleaner(); // 공기 청정기 작동 
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
		dustQueue = new LinkedList<>();

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != -1 && map[i][j] != 0) 
					dustQueue.add(new Point(i, j, map[i][j]));
			}
		}
	}

	public static void spreadDust() {
		while(!dustQueue.isEmpty()) {
			int cx = dustQueue.peek().x;
			int cy = dustQueue.peek().y;
			int cDust = dustQueue.poll().dust; // 현재 좌표의 미세먼지 양 

//			if(cDust < 5) // 확산될 먼지가 없다면 pass 
//				continue;

			int spreadCnt = 0; // 확산된 방향의 개수

			for(int i = 0; i < 4; i++) { // 상하좌우 먼지 퍼뜨릴 새로운 좌표 확인 
				int nx = cx + dx[i]; 
				int ny = cy + dy[i];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) // 범위 벗어나거나 공기청정기라면  pass 
					continue;

				spreadCnt++;
				map[nx][ny] += (cDust/5); // 확산되는 미세먼지 양 
			}

			map[cx][cy] -= (cDust/5) * spreadCnt; // 남은 미세먼지 양 
		}
	}

	private static void runAirCleaner() {
		int top = airCleanerR;
		int down = airCleanerR + 1;
		
		// 순환 방향으로 값을 이동시키면 방향이 바뀌는 부분에서 복잡함 
		// 그래서 방향으로 흘러가는 것이 아니라, 공기청정기에 먼지가 빨려 들어온다는 느낌으로 값을 당겨주면 쉽게 이동시킬 수 있음 

		// 위쪽 공기청정기의 바람은 반시계방향 순환
		for (int i = top - 1; i > 0; i--) // 아래로 당기기
			map[i][0] = map[i-1][0];
	
		for (int i = 0; i < C - 1; i++) // 왼쪽으로 당기기
			map[0][i] = map[0][i+1];
		
		for (int i = 0; i < top; i++) // 위로 당기기
			map[i][C - 1] = map[i + 1][C - 1];
		
		for (int i = C - 1; i > 1; i--) // 오른쪽으로 당기기
			map[top][i] = map[top][i-1];
		// 공기청정기에서 부는 바람은 미세먼지가 없는 바람 (공기청정기 바로 옆은 0)
		map[top][1] = 0;

		// 아래쪽 공기청정기의 바람은 시계방향으로 순환
		for (int i = down + 1; i < R - 1; i++) // 위로 당기기
			map[i][0] = map[i + 1][0];
		
		for (int i = 0; i < C - 1; i++) // 왼쪽으로 당기기
			map[R - 1][i] = map[R - 1][i + 1]; 
		
		for (int i = R - 1; i > down; i--) // 아래로 당기기
			map[i][C - 1] = map[i - 1][C - 1];
	
		for (int i = C - 1; i > 1; i--) // 오른쪽으로 당기기
			map[down][i] = map[down][i - 1];
		
		map[down][1] = 0; // 공기청정기에서 부는 바람은 미세먼지가 없는 바람 (공기청정기 바로 옆은 0)
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
