/**
 * @author Minha Gwon
 * @date 2021. 2. 28.
 * 미친 아두이노
 * https://www.acmicpc.net/problem/8972
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int R, C;
	static char[][] board;
	static Point myArduino;
	static LinkedList<Point> crazyArduino = new LinkedList<>();
	
	static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1}; // 1하좌, 2하, 3하우, 4좌, 5그대로, 6우, 7상좌, 8상, 9상우 
	static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		board = new char[R][C];

		for(int i = 0; i < R; i++) {
			String str = sc.next();
			for(int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);

				if(board[i][j] == 'I') { // 내 아두이노 
					myArduino = new Point(i, j);
				} else if(board[i][j] != '.') { // 미친 아두이노 
					Point p = new Point(i, j);
					crazyArduino.add(p);
				}
			}
		}

		int[] dir = Arrays.stream(sc.next().split("")) // 움직이려는 방향 
				.mapToInt(Integer::parseInt)
				.toArray();

		int kraj = 0; // 게임이 끝나기 전 까지 이동한 횟수

		// 게임 시작 
		for(int i = 0; i < dir.length; i++) {
			// 1. 내 아두이노 이동 
			if(dir[i] != 5) { // 그대로인 경우 제외 
				if(!moveMyArduino(dir[i]-1)) { // false 반환 -> 이동한 곳에 미친 아두이노가 있음 -> 게임 종료 
					kraj = i+1;
					break;
				}
			}

			// 2. 미친 아두이노 이동
			if(!moveCrazyArduino()) { // false 반환 -> 이동한 곳에 내 아두이노가 있음 -> 게임 종료 
				kraj = i+1;
				break;
			}
			
			// 3. 보드 새로 구성하기 (이동된 값에 따라 새롭게 보드 구성)
			makeBoard();
		}

		if(kraj != 0) {
			System.out.println("kraj " + kraj); // 중간에 끝난 경우 
		} else {
			print(); // 중간에 끝나지 않고 모두 이동한 경우 보드 출력 
		}
	}

	// 1. 내 아두이노 이동 
	public static boolean moveMyArduino(int dir) {
		int nx = myArduino.x + dx[dir];
		int ny = myArduino.y + dy[dir];
		
		if(board[nx][ny] == 'R') {
			return false;
		} else {
			myArduino.x = nx;
			myArduino.y = ny;
			return true;
		}
	}

	// 2. 미친 아두이노 이동 
	public static boolean moveCrazyArduino() {
		int[][] temp = new int[R][C];

		int size = crazyArduino.size(); 
		for(int i = 0; i < size; i++) { // 미친 아두이노의 수만큼 반복 
			int x = crazyArduino.peek().x;
			int y = crazyArduino.poll().y;

			int min = Integer.MAX_VALUE; // 내 아두이노와 가장 가까워 지는 방향 구하기위한 변수 
			int minX = 0;
			int minY = 0;

			// 8방향 이동시켜 봄 
			for(int j = 0; j < 9; j++) {
				if(j == 4) continue; // 그대로인 경우 제외 

				int nx = x + dx[j];
				int ny = y + dy[j];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue; // 범위 벗어나면 제외 

				// 이동했을 때 가장 작아지는 방향 찾기 
				int val = Math.abs(myArduino.x - nx) + Math.abs(myArduino.y - ny);
				if(val < min) {
					min = val;
					minX = nx;
					minY = ny;
				}
			}

			if(minX == myArduino.x && minY == myArduino.y) { // 이동할 방향에 내 아두이노가 있는 경우 게임 종료 
				return false;
			} 
			
			temp[minX][minY] += 1; // 이동할 수 있다면 +1 
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(temp[i][j] == 1) { // 칸에 1개의 아두이노만 있어야 파괴되지 않음 
					crazyArduino.add(new Point(i, j)); // 따라서 칸에 1개 있는 아두이노만 다시 미친 아두이노 리스트에 추가 
				}
			}
		}

		return true;
	}
	
	// 3. 보드 새로 구성하기 
	public static void makeBoard() {
		board = new char[R][C];
		
		for(int i = 0; i < R; i++) // 빈칸 .으로 초기화 
			Arrays.fill(board[i], '.'); 
		
		board[myArduino.x][myArduino.y] = 'I'; // 내 아두이노 배치 
		
		for(int i = 0; i < crazyArduino.size(); i++) { // 미친 아두이노 배치 
			Point p = crazyArduino.poll();
			board[p.x][p.y] = 'R';
			crazyArduino.add(p);
		}
	}

	// 보드판 출력 
	public static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(board[i][j]);
			} System.out.println();
		}
	} 

}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}