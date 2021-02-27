/**
 * @author Minha Gwon
 * @date 2021. 2. 28.
 * 미친 아두이노
 * https://www.acmicpc.net/problem/8972
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int R, C;
	static char[][] map;
	static Point[][] board;
	static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1}; // 1하좌, 2하, 3하우, 4좌, 5그대로, 6우, 7상좌, 8상, 9상우 
	static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	static Point myArduino;
	static ArrayList<Point> crazyArduino = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		map = new char[R][C];
		board = new Point[R][C];

		for(int i = 0; i < R; i++) {
			String str = sc.next();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);

				if(map[i][j] == 'I') {
					myArduino = new Point(i, j);
					board[i][j] = myArduino;
				} else if(map[i][j] != '.') {
					Point p = new Point(i, j);
					crazyArduino.add(p);
					board[i][j] = p;
				}
			}
		}

		int[] dir = Arrays.stream(sc.next().split(""))
				.mapToInt(Integer::parseInt)
				.toArray();

		int kraj = 0;

		for(int i = 0; i < dir.length; i++) {
			// 1. 내 아두이노 이동 
			if(dir[i] != 5) { // 그대로 제외 
				if(!moveMyArduino(dir[i]-1)) { // 이동한 곳에 미친 아두이노가 있다면 게임 종료 
					kraj = i+1;
					break;
				}
			}

			// 2. 미친 아두이노 이동
			if(i != 0)
				findCrazyArduino();

			if(!moveCrazyArduino()) {
				kraj = i+1;
				break;
			}
		}

		if(kraj != 0) {
			System.out.println("kraj " + kraj);
		} else {
			print();
		}
	}

	public static void findCrazyArduino() {
		crazyArduino = new ArrayList<>();

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] ==  null) {
					if(i != myArduino.x && j != myArduino.y) 
						crazyArduino.add(new Point(i, j));
				} 
			} 
		}
	}

	public static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] !=  null) {
					if(i == myArduino.x && j == myArduino.y) System.out.print("I"); // 내 아두이노 
					else System.out.print('R'); // 미친 아두이노 
				} else {
					System.out.print("."); // 빈 칸 
				}
			} System.out.println();
		}
	} 

	public static boolean moveMyArduino(int dir) {
		int mnx = myArduino.x + dx[dir];
		int mny = myArduino.y + dy[dir];

		if(board[mnx][mny] != null) { // 미친 아두이노가 있는 경우 
			return false;
		} else { // 빈칸인 경우 
			board[myArduino.x][myArduino.y] = null;
			myArduino.x = mnx;
			myArduino.y = mny;
			board[mnx][mny] = myArduino;
			return true;
		}
	}

	public static boolean moveCrazyArduino() {
		Point[][] tempBoard = new Point[R][C]; // 이동후 중복을 판별하기 위한 임시 배열 
		int[][] temp = new int[R][C];

		for(int i = 0; i < crazyArduino.size(); i++) {
			Point curArduino = crazyArduino.get(i);

			int min = Integer.MAX_VALUE;
			int minX = 0;
			int minY = 0;

			for(int j = 0; j < 9; j++) {
				if(j == 4) continue; // 그대로인 경우 제외 

				int cnx = curArduino.x + dx[j];
				int cny = curArduino.y + dy[j];

				if(cnx < 0 || cnx >= R || cny < 0 || cny >= C) continue;

				// 이동했을 때 가장 작아지는 방향 찾기 
				int val = Math.abs(myArduino.x - cnx) + Math.abs(myArduino.y - cny);
				if(val < min) {
					min = val;
					minX = cnx;
					minY = cny;
				}
			}	

			if(minX == myArduino.x && minY == myArduino.y) { // 이동할 방향에 내 아두이노가 있는 경우 
				return false;
			} 
			
			if(temp[minX][minY] == 0) {
				temp[minX][minY] = 1;
				tempBoard[minX][minY] = new Point(minX, minY);
			} else {
				tempBoard[minX][minY] = null;
			}
			
			board = copy(tempBoard);
			System.out.println();
			print();
			
		}
		
		

		return true; 
	}
	
	public static Point[][] copy(Point[][] arr) { // 2차원 배열 깊은 복사 
		Point[][] copy = new Point[R][C];
		for(int i = 0; i < arr.length; i++) {
			System.arraycopy(arr[i], 0, copy[i], 0, arr[i].length);
		}
		return copy;
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