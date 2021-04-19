/**
 * @author Minha Gwon
 * @date 2021. 4. 19.
 * 큐빙 
 * https://www.acmicpc.net/problem/5373
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[][] up;
	static char[][] down;
	static char[][] front;
	static char[][] back;
	static char[][] left;
	static char[][] right;

	static ArrayList<char[][]> cubeList; // 6면 리스트로 관리 
	static int[][] sideArr = {{3, 2}, {2, 3}, {0, 1}, {1, 0}}; // currentUpSide 현재 큐브의 윗면(i) 기준으로 윗면 [i][0], 아랫면[i][1] 인덱스 저장 
	static int[][] rotateCnt = {{0, 2, 3, 1}, {2, 0, 1, 3}, {1, 3, 0, 2}, {3, 1, 2, 0}}; // 회전할 면을 currentUpSide로 만들기 위해 회전해야하는 횟수 저장 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) { // 테스트 케이스 
			cubeInit();// 큐브 초기화 

			int currentUpSide = 0; // 현재 큐브의 윗면 인덱스 저장 - 초기값 윗면으로 줌 

			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for(int n = 0; n < N; n++) { // 큐브를 돌린 횟수 
				String order = st.nextToken();
				char face = order.charAt(0); // 돌린 면 (U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면) 
				char dir = order.charAt(1); // 돌린 방향 (+ : 시계 방향 / - : 반시계 방향) 
				int faceIdx = direction(face); // 돌린 면의 인덱스 값 

				// 위, 아래, 앞, 뒤 4면 회전의 경우 
				if(faceIdx != 4 && faceIdx != 5) {
					if(currentUpSide != faceIdx) { // 현재 면과 회전할 면이 다르다면 
						int cnt = rotateCnt[currentUpSide][faceIdx];
						
						while(cnt != 0) { // 회전할 면이 큐브의 윗면에 오도록 왼쪽, 오른쪽 면 회전시켜 맞춰줌 
							rotate90(4, '+');
							rotate90(5, '-');
							cnt--;
						}
								
						currentUpSide = faceIdx;
					}
					
					rotate(faceIdx, dir);
				} else { // 왼쪽, 오른쪽 면 회전의 경우 
					rotateLeftRight(faceIdx, dir);
				}
			}

			// 윗 면 색상 출력 
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					sb.append(cubeList.get(0)[i][j]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	// 각 면에 해당하는 인덱스 값 반환 
	public static int direction(char ch) {
		if(ch == 'U') return 0;
		else if(ch == 'D') return 1;
		else if(ch == 'F') return 2;
		else if(ch == 'B') return 3;
		else if(ch == 'L') return 4;
		else  return 5;
	}

	// 위, 아래, 앞, 뒷 면 회전
	public static void rotate(int faceIdx, char dir) {
		int sideUp = sideArr[faceIdx][0];
		int sideDown = sideArr[faceIdx][1];
		
		char[] leftArr = new char[3];
		char[] upArr = new char[3];
		char[] rightArr = new char[3];
		char[] downArr = new char[3];

		for(int i = 0; i < 3; i++) {
			leftArr[i] = left[i][2]; // 1-1. 왼쪽면의 3열 뽑아 저장 
			downArr[i] = cubeList.get(sideDown)[0][i]; // 2-1. 밑면 1행 뽑아 저장 
			rightArr[i] = right[i][0]; // 3-1. 오른쪽면 1열 뽑아 저장 
			upArr[i] = cubeList.get(sideUp)[2][i]; // 4-1. 윗면의 3행 뽑아 저장 
		}

		/*
		targetIdx (회전된 값을 저장할 면의 인덱스) 
		idx (몇번째 행 또는 열인지를 나타내는 값) 
		order (1 : 순서대로 / -1 : 거꾸로)
		isCol (true : 행 / false : 열) 
		value (회전된 값을 담는 배열) 
		 */

		if(dir == '+') { // 시계 방향 회전 
			insertion(sideUp, 2, true, -1, leftArr); // 1-2. 윗면 3행에 거꾸로 삽입 
			insertion(5, 0, false, 1, upArr); // 2-2. 오른쪽면(5) 1열에 순서대로 삽입 
			insertion(sideDown, 0, true, -1, rightArr); // 3-2. 밑면 1행에 거꾸로 삽입 
			insertion(4, 2, false, 1, downArr); // 4-2. 왼쪽면(4) 3열에 순서대로 삽입 
			
			rotate90(faceIdx, dir); // 현재 면 방향에 맞게 회전 
		} else { // 반시계 방향 회전 
			insertion(sideDown, 0, true, 1, leftArr); // 1-2. 밑면 1행에 순서대로 삽입 
			insertion(5, 0, false, -1, downArr); // 2-2. 오른쪽면(5) 1열에 거꾸로 삽입 
			insertion(sideUp, 2, true, 1, rightArr); // 3-2. 윗면 3행에 순서대로 삽입 
			insertion(4, 2, false, -1, upArr); // 4-2. 왼쪽면(4) 3열에 거꾸로 삽입 

			rotate90(faceIdx, dir); // 현재 면 방향에 맞게 회전 
		}
		
//		System.out.println("---- 위, 아래, 앞, 뒤 4면 회전후 ");
//		printCubeList();
	}
	
	// 위, 아래, 앞, 뒷 면 회전 후 삽입 
	public static void insertion(int targetIdx, int idx, boolean isCol, int order, char[] value) {
		for(int i = 0; i < 3; i++) {
			if(isCol) { // 행에 삽입 
				if(order == 1) { // 순서대로 삽입 
					cubeList.get(targetIdx)[idx][i] = value[i];
				} else { // 거꾸로 삽입 
					cubeList.get(targetIdx)[idx][i] = value[2-i];
				}
			} else { // 열에 삽입 
				if(order == 1) { // 순서대로 삽입 
					cubeList.get(targetIdx)[i][idx] = value[i];
				} else { // 거꾸로 삽입 
					cubeList.get(targetIdx)[i][idx] = value[2-i];
				}
			}
		}
	}

	// 왼쪽, 오른쪽 면 회전 
	public static void rotateLeftRight(int faceIdx, char dir) {
		char[] upArr = new char[3];
		char[] downArr = new char[3];
		char[] frontArr = new char[3];
		char[] backArr = new char[3];

		int j;
		if(faceIdx == 4) { // 왼쪽면이 회전할 경우 - 오른쪽 제외 나머지 면의 1열만 조작하면 됨 [i][0]
			j = 0; 
		} else { // 오른쪽면이 회전할 경우 - 왼쪽 제외 나머지 면의 3열만 조작하면 됨 [i][2]
			j = 2; 
		}

		// 면 별 j열의 값 저장 
		for(int i = 0; i < 3; i++) {
			upArr[i] = cubeList.get(0)[i][j];
			downArr[i] = cubeList.get(1)[i][j];
			frontArr[i] = cubeList.get(2)[i][j];
			backArr[i] = cubeList.get(3)[i][j];
		}

		// 회전한 값 삽입 
		if((faceIdx == 4 && dir == '+') || (faceIdx == 5 && dir == '-')) { // 왼쪽면 시계 방향 회전, 오른쪽면 반시계 방향 회전의 경우 
			// 윗면 -> 앞면 -> 밑면 -> 뒷면 순서
			insertion2(2, j, upArr);
			insertion2(1, j, frontArr);
			insertion2(3, j, downArr);
			insertion2(0, j, backArr);
			
			rotate90(faceIdx, dir); // 현재 면 방향에 맞게 회전 
		} else { // 왼쪽면 반시계 방향 회전, 오른쪽면 시계 방향 회전의 경우 
			// 뒷면 -> 밑면 -> 앞면 -> 윗면 순서 (위 순서 거꾸로)
			insertion2(1, j, backArr);
			insertion2(2, j, downArr);
			insertion2(0, j, frontArr);
			insertion2(3, j, upArr);
			
			rotate90(faceIdx, dir); // // 현재 면 방향에 맞게 회전 
		}
		
//		System.out.println("---- 왼쪽, 오른쪽 면 회전후 ");
//		printCubeList();
	}

	// 왼쪽, 오른쪽 면 회전 후 삽입 
	public static void insertion2(int targetIdx, int j, char[] value) {
		for(int i = 0; i < 3; i++) {
			cubeList.get(targetIdx)[i][j] = value[i];
		}
	}
	
	// 90도 회전 
	public static void rotate90(int faceIdx, char dir) {
		char[][] temp = new char[3][3];

		// 기존 면의 값을 바탕으로 회전한 값을 temp 배열에 임시로 저장 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(dir == '+') { // 시계 방향일 경우 (1행 -> 3열, 2행 -> 2열, 3행 -> 1열)
					temp[j][2-i] = cubeList.get(faceIdx)[i][j];
				} else { // 반시계 방향일 경우 (1행 -> 거꾸로 1열, 2행 -> 거꾸로 2열, 3행 -> 거꾸로 3열)
					temp[2-j][i] = cubeList.get(faceIdx)[i][j];
				}
			}
		}
		
		// 회전한 값으로 변경 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cubeList.get(faceIdx)[i][j] = temp[i][j];
			}
		}
	}
	
	public static void printCubeList() {
		System.out.println("up white");
		print(up);
		
		System.out.println("down yellow");
		print(down);
		
		System.out.println("front red");
		print(front);
		
		System.out.println("back orange");
		print(back);
		
		System.out.println("left green");
		print(left);
		
		System.out.println("right blue");
		print(right);
		
		System.out.println();
	}

	public static void print(char[][] arr) {
		System.out.println("print");

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}

	// 큐브 초기화 
	public static void cubeInit() {	
		up = new char[3][3]; // white
		down = new char[3][3]; // yellow
		front = new char[3][3]; // red
		back = new char[3][3]; // orange
		left = new char[3][3]; // green
		right = new char[3][3]; // blue

		for(int i = 0; i < 3; i++) {
			Arrays.fill(up[i], 'w');
			Arrays.fill(down[i], 'y');
			Arrays.fill(front[i], 'r');
			Arrays.fill(back[i], 'o');
			Arrays.fill(left[i], 'g');
			Arrays.fill(right[i], 'b');
		}

		cubeList = new ArrayList<>();

		cubeList.add(up);
		cubeList.add(down);
		cubeList.add(front);
		cubeList.add(back);
		cubeList.add(left);
		cubeList.add(right);
	}
}
