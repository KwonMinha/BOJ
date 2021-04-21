/**
 * @author Minha Gwon
 * @date 2021. 4. 21.
 * 새로운 게임 2
 * https://www.acmicpc.net/problem/17837
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[][] map;
	static ArrayList<Chess> chessList;

	static Queue<Chess>[][] queueMap;

	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // 0은 흰색, 1은 빨간색, 2는 파란색
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		queueMap = new LinkedList[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				queueMap[i][j] = new LinkedList<>();
			}
		}

		chessList = new ArrayList<>();
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;

			if(dir == 1) 
				dir = 2;
			else if(dir == 2)
				dir = 1;

			chessList.add(new Chess(i, x, y, dir));

			queueMap[x][y].add(new Chess(i, x, y, dir));
		}

		// 게임 시작 
		while(true) {
			for(int i = 0; i < chessList.size(); i++) {
				Chess curChess = chessList.get(i);

				int curIdx = curChess.idx;
				int curX = curChess.x;
				int curY = curChess.y;
				int curDir = curChess.dir;

				int newX = curX + dx[curDir];
				int newY = curY + dy[curDir];

				if(newX < 0 || newY < 0 || newX >= N || newY >= N) {

				}
				
				if(map[newX][newY] == 0) { // 이동하려는 칸 흰색 
		
				} else if(map[newX][newY] == 1) { // 이동하려는 칸 빨간색 
					
				} else { // 이동하려는 칸 파란색 
					moveBlue(curChess);
				}

			}
		}

	}

	public static void moveWhite(Chess chess, int nx, int ny) {
		Queue<Chess> curQueue = new LinkedList<>();
		while(queueMap[chess.x][chess.y].poll().idx != chess.idx) {
			
		}
	}

	public static void moveRed(Chess chess, int nx, int ny) {

	}

	public static void moveBlue(Chess chess) {
		chess.dir = (chess.dir + 2) % 4; // 이동 방향을 반대로

		int nx = chess.x + dx[chess.dir];
		int ny = chess.y + dy[chess.dir];

		// 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
		if(map[nx][ny] == 0) { // 이동할 칸 흰색 

		} else if(map[nx][ny] == 1) { // 이동할 칸 빨간색 

		}
	}

}

class Chess {
	int idx;
	int x;
	int y;
	int dir;

	Chess(int idx, int x, int y, int dir) {
		this.idx = idx;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
