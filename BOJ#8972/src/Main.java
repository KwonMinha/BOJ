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
	static Robots[][] board;
	static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1}; // 1하좌, 2하, 3하우, 4좌, 5그대로, 6우, 7상좌, 8상, 9상우 
	static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	static Robots myArduino;
	static ArrayList<Robots> crazyArduino = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		board = new Robots[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = sc.next();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == 'I') {
					myArduino = new Robots(i, j, 3);
					board[i][j] = myArduino;
				} else if(map[i][j] != '.') {
					crazyArduino.add(new Robots(i, j, 1));
					board[i][j] = new Robots(i, j, 1);
				}
			}
		}
	
		int[] dir = Arrays.stream(sc.next().split(""))
			    .mapToInt(Integer::parseInt)
			    .toArray();
	
	
		
		int kraj = 0;
		
		loop:
		for(int i = 0; i < dir.length; i++) {

			
			// 1. 내 아두이노 이동 
			//System.out.println("move my a");
			if(dir[i] != 5) { // 그대로 제외 
				if(!moveMyArduino(dir[i]-1)) { // 이동한 곳에 미친 아두이노가 있다면 게임 종료 
					//System.out.println("1. over!");
					kraj = i+1;
					break loop;
				}
			}
			//print();
		
			// 2. 미친 아두이노 이동 
			//System.out.println("move crazy");
			if(!moveCrazyArduino()) {
				//System.out.println("2. over!");
				kraj = i+1;
				break loop;
			}
			//print();
			
		}
		
		if(kraj != 0) {
			System.out.println("kraj " + kraj);
		} else {
			print();
		}
		
	}
	
	public static void print() {
		//System.out.println();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] ==  null) {
					System.out.print(".");
				} else {
					if(board[i][j].flag == 3) {
						System.out.print("I");
					} else if(board[i][j].flag == 1) {
						System.out.print("R");
					}
				}
			}
			System.out.println();
		}
		//System.out.println();
	} //end print 
	
	public static boolean moveMyArduino(int dir) {
		
		int mnx = myArduino.x + dx[dir];
		int mny = myArduino.y + dy[dir];

		//System.out.println(myArduino.x + " " + myArduino.y + " " + mnx + " " + mny);
		
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
		Robots[][] tempBoard = new Robots[R][C]; // 이동후 중복을 판별하기 위한 임시 배열 
		
		for(int i = 0; i < crazyArduino.size(); i++) {
			Robots curArduino = crazyArduino.get(i);
			//System.out.println("cur : " + curArduino.x + " " + curArduino.y);
			if(curArduino.flag == 1) { // 아직 파괴되지 않은 미친 아두이노일 경우만 움직임 
				int min = Integer.MAX_VALUE;
				int minX = 0;
				int minY = 0;
				
				for(int j = 0; j < 9; j++) {
					if(j == 4) { // 그대로 있는 경우는 pass 
						continue;
					}
					
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
				
				if(tempBoard[minX][minY] == null) { // 빈 칸일 경우 
					board[curArduino.x][curArduino.y] = null;
					curArduino.x = minX;
					curArduino.y = minY;
					tempBoard[minX][minY] = curArduino;
	
				} else { // 이미 다른 아두이노가 있는경우 
					tempBoard[minX][minY].flag = 0; // 둘 다 파괴 
					curArduino.flag = 0;
				}
				
				for(int j = 0; j < crazyArduino.size(); j++) {
					if(crazyArduino.get(j).flag == 0) {
						board[crazyArduino.get(j).x][crazyArduino.get(j).y] = null;
					} else {
						board[crazyArduino.get(j).x][crazyArduino.get(j).y] = crazyArduino.get(j);
					}
				}
			}
		}
		return true; 
	}

}

class Robots {
	int x;
	int y;
	int flag; // 0: 파괴된 미친 아두이노 / 1: 미친 아두이노 / 3: 나의 아두이노 
	
	Robots(int x, int y, int flag) {
		this.x = x;
		this.y = y;
		this.flag = flag;
	}
}