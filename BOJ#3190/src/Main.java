/**
 * @author Minha Gwon
 * @date 2021. 2. 9.
 * 뱀
 * https://www.acmicpc.net/problem/3190
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<Point> direction = new LinkedList<>(); // 뱀의 방향 변환 정보 담는 큐 
	static Deque<Point> snakeDeque = new ArrayDeque<>(); // 뱀의 크기 및 정보 담는 큐 
	static int snakeDir = 3;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순서 
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		map = new int[N][N];
		map[0][0] = -1; // 뱀이 있는 곳은 -1 
		snakeDeque.offer(new Point(0, 0)); // 처음엔 뱀 머리, 꼬리 같음 

		// 사과 위치 입력받기 
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;

			map[a][b] = 1; // 사과가 있는 곳은 1 
		}

		// 뱀의 방향 변환 정보 입력 받기 
		int L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());

			String s = st.nextToken();
			int c;
			if(s.equals("L")) // 왼쪽으로 90도 -> 0
				c = 0;
			else
				c = 1; // 오른쪽으로 90도 -> 1

			direction.add(new Point(x, c));
		}

		int time = 0; // 게임 실행 시간 

		int X = direction.peek().x; // X초가 끝난뒤에 
		int C = direction.poll().y; // C방향으로 90도 회전 

		while(true) {
			time++;

			// 머리를 늘려 위치시킬 다음 칸 
			int nx = snakeDeque.peekFirst().x + dx[snakeDir];
			int ny = snakeDeque.peekFirst().y + dy[snakeDir];

			// 벽 또는 자기자신의 몸과 부딪힌 경우 
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1) {
				break; // 게임 끝 
			}

			if(map[nx][ny] == 1) { // 사과가 있다면 
				map[nx][ny] = -1;
				snakeDeque.offerFirst(new Point(nx, ny)); // 머리 새로운 좌표로 이동 
			} else { // 사과가 없다면 
				map[nx][ny] = -1;
				snakeDeque.offerFirst(new Point(nx, ny)); // 머리 새로운 좌표로 이동 

				// 꼬리가 위치한 칸 비우기 
				map[snakeDeque.peekLast().x][snakeDeque.pollLast().y] = 0;
			}
			if(time == X) {
				if(C == 0) { // 왼쪽 90도 방향 전환 -> 상 좌 하 우 0 1 2 3 순서 
					//snakeDir = ++snakeDir % 4;
					snakeDir = (snakeDir+1) % 4;
				} else { // 오른쪽 90도 방향 전환 -> 우 하 좌 상 3 2 1 0 순서 
					//snakeDir = (--snakeDir == -1 ? 3 : snakeDir) % 4; // 상방향 0일 경우 --하면 음수 -> if문으로 바로 우방향 3으로 처리
					snakeDir = (snakeDir+3) % 4;
				}
				
				if(!direction.isEmpty()) { // 큐에 방향 변환 정보가 있다면 갱신 
					X = direction.peek().x;
					C = direction.poll().y;
				}
			}
		}

		System.out.println(time);

		br.close();
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