/**
 * @author Minha Gwon
 * @date 2021. 6. 30.
 * 미네랄
 * https://www.acmicpc.net/problem/2933
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};

	static int R, C;

	static String[][] map;
	static boolean[][] visited;

	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new String[R][C];

		for(int i = 0; i < R; i++) {
			String str = br.readLine();

			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) + "";
			}
		}

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());

			System.out.println("-------------" + h + "------------");

			if(i % 2 == 0) { // 짝수 번째 (왼 -> 오) / 인덱스 0부터 시작하기 때문 
				System.out.println("left -> right");

				for(int j = 0; j < C; j++) {
					if(map[C-h][j].equals("x")) {
						map[C-h][j] = "."; // 미네랄 파괴 
						cluster(C-h, j); 
						break;
					}
				}
			} else { // 홀수 번째 (오 -> 왼)
				System.out.println("right -> left");

				for(int j = C-1; j >= 0; j--) {
					if(map[C-h][j].equals("x")) {
						map[C-h][j] = "."; // 미네랄 파괴 
						cluster(C-h, j);
						break;
					}
				}
			}

			print();
		}
	}

	// 인접한 4방향에 미네랄이 있는지 확인 - 클러스터 확인  
	static void cluster(int x, int y) {
		visited = new boolean[R][C];

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny].equals("."))
				continue;

			if(!visited[nx][ny]) { // 클러스터가 있는 경우 
				if(!bfs(nx, ny)) { // 클러스터가 땅에 닿는지 확인 -> 클러스터가 분리되어 떠 있는 경우 
					move(nx, ny); // 바닥으로 떨어뜨림 
				}
			}
		}
	}
	
	// 클러스터가 땅에 닫는지 확인 
	static boolean bfs(int x, int y) {
		pq = new PriorityQueue<>(new Comparator<>() { // 바닥으로 떨어뜨리는 정도를 빠르게 찾기 위해 떠있는 클러스터 우선순위 큐로 저장 
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.c == o2.c) {
					return o2.r - o1.r;
				} else {
					return o2.c - o1.c;
				}
			}
		});

		pq.add(new Point(x, y));

		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));

		visited[x][y] = true;

		while(!queue.isEmpty()) {
			int cx = queue.peek().r;
			int cy = queue.poll().c;

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny].equals("."))
					continue;

				if(nx == R-1) { // 클러스터가 바닥에 닿음 pass 
					return true;
				}

				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));

					pq.add(new Point(nx, ny));
				}
			}
		}

		return false; // 바닥에 닿지 않고 끝남 -> 떨어뜨려야 함 
	}

	// 바닥으로 떨어뜨림 
	static void move(int x, int y) {
		int c = pq.peek().c; // 가장 첫 열의 높이가 가장 큰 미네랄이 저장되어있음 
		int min = getCount(pq.peek().r, c); // 해당 미네랄이 얼마나 바닥으로 떨어질 수 있는지 확인 
		
		ArrayList<Point> list = new ArrayList<>();
		list.add(pq.poll());

		while(!pq.isEmpty()) {
			if(c != pq.peek().c) { // 다음 열 미네랄 
				c = pq.peek().c;
				
				// 각 열의 높이가 가장 큰 미네랄이 최대로 떨어질 수 있는 값 중 가장 작은 값으로 떨어뜨려야 모양 유지 가능 
				min = Math.min(getCount(pq.peek().r, c), min); 
			} 

			list.add(pq.poll());
		}
		
		for(int i = 0; i < list.size(); i++) { // min값만큼 클러스터 떨어뜨리기 
			int cx = list.get(i).r;
			int cy = list.get(i).c;
			
			int nx = cx + min;
			
			map[cx][cy] = ".";
			map[nx][cy] = "x";
		}
	}
	
	// 각 열의 가장 밑에 있는(높이가 가장 큰) 미네랄이 최대로 몇 칸 아래로 떨어질 수 있는지 확인 
	static int getCount(int x, int y) {
		int max = 0;
		
		for(int i = x+1; i < C; i++) {
			if(map[i][y].equals("x")) { // 바닥(=높이 R) 또는 다른 클러스터 위까지 떨어질 수 있음  
				break;
			}
			
			max++;
		}
		
		return max;
	}

	static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("");			
	}

}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
