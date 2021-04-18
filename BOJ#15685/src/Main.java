/**
 * @author Minha Gwon
 * @date 2021. 4. 18.
 * 드래곤 커브 
 * https://www.acmicpc.net/problem/15685
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
//	static int[] dx = {0, 1, 0, -1}; // 0, 1, 2, 3 - 상, 우, 하, 좌 - 시계 방향 90도 회전 
//	static int[] dy = {-1, 0, 1, 0};
	
	static int[] dx = {0, -1, 0, 1}; // 0, 1, 2, 3 - 상, 좌, 하, 우 - 반시계 방향 90도 회전 
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		boolean[][][][] check = new boolean[101][101][101][101]; // 0 ≤ x, y ≤ 100

		int N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수 

		for(int n = 0; n < N; n++) {
			
			st = new StringTokenizer(br.readLine());

			int startX = Integer.parseInt(st.nextToken()); 
			int startY = Integer.parseInt(st.nextToken());

			int dir = Integer.parseInt(st.nextToken()); // 시작 방향
			// 상, 좌, 하, 우 반시계 방향으로 받기 위해 방향 조정 
			if(dir == 1) dir = 0; // 상 y-- 
			else if(dir == 2) dir = 1; // 좌 x-- 
			else if(dir == 3) dir = 2; // 하 y++
			else dir = 3; // 우 x++

			int g = Integer.parseInt(st.nextToken()); // 세대 

			ArrayList<Integer> direction = new ArrayList<>();
			direction.add(dir);

			// 0세대
			int endX = startX + dx[dir];
			int endY = startY + dy[dir];

			System.out.println("\n0 세대");
			System.out.println("start : " + startX + " " + startY + ", end : " + endX + " " + endY + ", dir : " + direct(dir) + ", g : " + g);

			check[startX][startY][endX][endY] = true;
			check[endX][endY][startX][startY] = true; // 양방향이니까 끝점에서 오는 것도 true 

			// g세대만큼 드래곤 커브 
			for(int i = 0; i < g; i++) {

				int size = direction.size();

				System.out.println((i+1) + " 세대 / size : " + size);

				for(int j = size-1; j >= 0; j--) {
					int rotateDir = (direction.get(j) + 1) % 4; // 반시계 방향으로 회전한 방향 
					
					int newX = endX; // 끝점을 기준(고정), 시작점 좌표 -> 회전 방향으로 이동 
					int newY = endY;

					if(rotateDir == 0) { // 상 
						newY -= 1;
					} else if(rotateDir == 1) { // 좌  
						newX -= 1;
					} else if(rotateDir == 2) { // 하 
						newY += 1;
					} else { // 우 
						newX += 1;
					}

					check[endX][endY][newX][newY] = true; // 회전시키고, 끝점에 붙여 만들어진 새로운 선분 추가 
					check[newX][newY][endX][endY] = true;
					
					System.out.println("new start : " + endX + " " + endY + ", new end : " + newX + " " + newY + " " + ", dir : " + direct(rotateDir));

					// 새로 추가된 선분으로 start, end 좌표 갱신 
					startX = endX;
					startY = endY;
					endX = newX;
					endY = newY;

					// 다음 세대에서의 드래곤 커브를 위해 추가된 선분의 방향 추가 
					direction.add(rotateDir);
				}
			}
		}

		int ans = 0;

		// 정사각형 개수 구하기 
		for(int i = 0; i < 101-1; i++) {
			for(int j = 0; j < 101-1; j++) {
				int sameCnt = 0;

				if(check[i][j][i+1][j]) sameCnt++;// 정사각형 위쪽 선분 

				if(check[i][j][i][j+1]) sameCnt++; // 왼쪽 선분 
			 
				if(check[i][j+1][i+1][j+1]) sameCnt++; // 아래쪽 선분 
				
				if(check[i+1][j][i+1][j+1]) sameCnt++; // 오른쪽 선분 
			
				if(sameCnt >= 3) ans++;// 3개 이상의 선분이 있다면 정사각형 가능 
			}
		}

		System.out.println(ans);
	}

	public static String direct(int d) {
		if(d == 0) { 
			return "상";
		} else if(d == 1) {
			return "좌";
		} else if(d == 2) {
			return "하";
		} else {
			return "우";
		}
	}

}
