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

	static Deque<Chess>[][] queueMap;

	static int turn = 0;

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

		queueMap = new ArrayDeque[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				queueMap[i][j] = new ArrayDeque<>();
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

			
			Chess chess = new Chess(i, x, y, dir);
			chessList.add(chess);

			queueMap[x][y].add(chess);
		}

		// 게임 시작 
		while(turn < 1000) {
			turn++;
			
			System.out.println("-------- turn : " + turn);

			for(int i = 0; i < chessList.size(); i++) {
				Chess curChess = chessList.get(i);

				int curIdx = curChess.idx;
				int curX = curChess.x;
				int curY = curChess.y;
				int curDir = curChess.dir;
				
				System.out.println("curIdx : " + curIdx + ", curX : " + curX + ", curY : " + curY + ", curDir : " + curDir);

				int newX = curX + dx[curDir];
				int newY = curY + dy[curDir];

				System.out.println("nx : " + newX + ", ny : " + newY);
				
				if(newX < 0 || newY < 0 || newX >= N || newY >= N) { // 체스판을 벗어나는 경우
					System.out.println("체스판 벗어남 - 파랑 ");
					moveBlue(curChess);
				} else {
					if(map[newX][newY] == 0) { // 이동하려는 칸 흰색 
						System.out.println("이동칸 흰 색 ");
						moveWhiteRed(curChess, newX, newY, 0);
					} else if(map[newX][newY] == 1) { // 이동하려는 칸 빨간색 
						System.out.println("이동칸 빨간 색 ");
						moveWhiteRed(curChess, newX, newY, 1);
					} else { // 이동하려는 칸 파란색 
						System.out.println("이동칸 파랑 색 ");
						moveBlue(curChess);
					}
				}
				
				System.out.println();
			}
			
			System.out.println("--------------------------");
		}

		System.out.println(-1);
	}

	public static void moveWhiteRed(Chess chess, int nx, int ny, int color) {
		Deque<Chess> curQueue = new ArrayDeque<>();
		//System.out.println("chess idx : " + chess.idx + ", x : " + chess.x + ", y : " + chess.y);
		//System.out.print("pollLast : ");
			
		Chess pollChess;
		do {
			pollChess = queueMap[chess.x][chess.y].pollLast();
			pollChess.x = nx;
			pollChess.y = ny;
			curQueue.addFirst(pollChess);
			
			//System.out.print("* : " + pollChess.idx + " ");
		} while(pollChess.idx != chess.idx);

		if(color == 0) { // 흰색 칸에 넣는 경우 
			while(!curQueue.isEmpty()) {
				System.out.println("peek first : " + curQueue.peekFirst().idx);
				queueMap[nx][ny].addLast(curQueue.pollFirst());
			}
		} else { // 빨간색 칸에 넣는 경우 
			while(!curQueue.isEmpty()) {
				queueMap[nx][ny].addLast(curQueue.pollLast());
			}
		}

		if(queueMap[nx][ny].size() >= 4) {
			System.out.println("4 이상 ");
			System.out.println(turn);
			System.exit(0);
		}

		System.out.println();
	}

	public static void moveBlue(Chess chess) {
		System.out.println("before dir : " + chess.dir);
		chess.dir = (chess.dir + 2) % 4; // 이동 방향을 반대로
		System.out.println("after dir : " + chess.dir);
		
		int nx = chess.x + dx[chess.dir];
		int ny = chess.y + dy[chess.dir];
		
		if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
			//System.out.println("이도저도안댐 ");
//			System.out.println("-1");
//			System.exit(0);
			
			moveBlue(chess);
		} else {
			// 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
			if(map[nx][ny] == 0) { // 이동할 칸 흰색 
				moveWhiteRed(chess, nx, ny, 0);
			} else if(map[nx][ny] == 1) { // 이동할 칸 빨간색 
				moveWhiteRed(chess, nx, ny, 1);
			}
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
