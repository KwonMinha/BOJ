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

public class Main2 {
	static int[] dx = {0, -1, 0, 1}; // 0, 1, 2, 3 - 상, 좌, 하, 우 -> 반시계 방향 90도 회전을 위함 
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		boolean[][] check = new boolean[101][101];

		int N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수 

		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			int startX = Integer.parseInt(st.nextToken()); // 1. 시작점 
			int startY = Integer.parseInt(st.nextToken());

			int dir = Integer.parseInt(st.nextToken()); // 2. 시작 방향

			// 상, 좌, 하, 우 반시계 방향으로 받기 위해 방향 조정 
			if(dir == 1) dir = 0; // 상 y-- 
			else if(dir == 2) dir = 1; // 좌 x-- 
			else if(dir == 3) dir = 2; // 하 y++
			else dir = 3; // 우 x++

			int g = Integer.parseInt(st.nextToken()); // 3. 세대 

			ArrayList<Integer> direction = new ArrayList<>(); // 각 세대에서 얻어진 선분들의 방향을 저장 
			direction.add(dir); // 0 세대 방향 저장 

			// 0 세대 선분 만들기 
			int endX = startX + dx[dir];
			int endY = startY + dy[dir];

			check[startX][startY] = true; // 선분의 시작점 
			check[endX][endY] = true; // 선분의 끝점

			// g세대만큼 드래곤 커브 
			for(int i = 0; i < g; i++) {
				int size = direction.size(); // 현재 i세대 이전의 i-1 세대에서 저장된 선분만큼 반복 

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

					// 회전시키고, 끝점에 붙여 만들어진 새로운 선분 추가 
					check[endX][endY] = true; // 기존의 끝점이 추가된 선분의 시작점이 됨 
					check[newX][newY]= true; // 회전시켜 얻어진 새로운 x, y좌표가 추가된 선분의 끝점이 됨 

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

				if(check[i][j]) sameCnt++;// 1. 정사각형 위쪽 선분의 왼쪽 꼭짓점 

				if(check[i+1][j]) sameCnt++; // 2. 위쪽 선분의 오른쪽 꼭짓점

				if(check[i][j+1]) sameCnt++; // 3. 아래쪽 선분의 왼쪽 꼭짓점

				if(check[i+1][j+1]) sameCnt++; // 4. 아래쪽 선분의 오른쪽 꼭짓점

				if(sameCnt == 4) ans++; // 네 꼭짓점이 모두 드래곤 커브의 일부인 경우 
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
