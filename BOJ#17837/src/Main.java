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
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // 체스판 정보 저장 - 0은 흰색, 1은 빨간색, 2는 파란색
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		queueMap = new ArrayDeque[N][N]; // 체스판 위에 놓인 말들 저장 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				queueMap[i][j] = new ArrayDeque<>();
			}
		}

		chessList = new ArrayList<>(); // 체스 말 저장 
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;

			if(dir == 1) // 간단한 방향 이동을 위해 이동 방향 조정 
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

			for(int i = 0; i < chessList.size(); i++) { // 1번 말부터 K번 말까지 순서대로 이동
				Chess curChess = chessList.get(i);
				
				// 방향에 맞게 이동할 새로운 위치 
				int newX = curChess.x + dx[curChess.dir]; 
				int newY = curChess.y + dy[curChess.dir];

				if(newX < 0 || newY < 0 || newX >= N || newY >= N) { // 이동할 칸이 체스판을 벗어나는 경우
					moveBlue(curChess); // 파란색과 같은 경우이다.
				} else {
					if(map[newX][newY] == 0) { // 이동하려는 칸 흰색 
						moveWhiteRed(curChess, newX, newY, 0);
					} else if(map[newX][newY] == 1) { // 이동하려는 칸 빨간색 
						moveWhiteRed(curChess, newX, newY, 1);
					} else { // 이동하려는 칸 파란색 
						moveBlue(curChess);
					}
				}
			}
		}

		System.out.println(-1);
	}

	public static void moveWhiteRed(Chess chess, int nx, int ny, int color) {
		// A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
		Deque<Chess> curQueue = new ArrayDeque<>(); // A번 말과 위에 있는 모든 말들 Deque에 저장 

		// 현재 A번 말이 나올때까지 꺼냄 
		Chess pollChess;
		do {
			pollChess = queueMap[chess.x][chess.y].pollLast(); // 뒤에서 부터 빼서 
			pollChess.x = nx;
			pollChess.y = ny;
			curQueue.addFirst(pollChess); // 
		} while(pollChess.idx != chess.idx);

		if(color == 0) { // 흰색 칸에 넣는 경우 
			while(!curQueue.isEmpty()) {
				queueMap[nx][ny].addLast(curQueue.pollFirst());
			}
		} else { // 빨간색 칸에 넣는 경우 
			while(!curQueue.isEmpty()) {
				queueMap[nx][ny].addLast(curQueue.pollLast());
			}
		}

		if(queueMap[nx][ny].size() >= 4) { // 말이 4개 이상 쌓이는 순간 게임이 종료
			System.out.println(turn);
			System.exit(0);
		}
	}

	public static void moveBlue(Chess chess) {
		chess.dir = (chess.dir + 2) % 4; // 이동 방향 반대로

		int nx = chess.x + dx[chess.dir];
		int ny = chess.y + dy[chess.dir];

		if(nx < 0 || ny < 0 || nx >= N || ny >= N) { // 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
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
