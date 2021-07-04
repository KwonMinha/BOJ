/**
 * @author Minha Gwon
 * @date 2021. 6. 30.
 * 미네랄
 * https://www.acmicpc.net/problem/2933
 */

import java.io.*;
import java.util.*;

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

			//System.out.println("------------- 높이 : " + h + " ------------");

			if(i % 2 == 0) { // 짝수 번째 (왼 -> 오) / 인덱스 0부터 시작하기 때문 
				//System.out.println("left -> right");
				for(int j = 0; j < C; j++) {
					if(map[R-h][j].equals("x")) {
						map[R-h][j] = "."; // 미네랄 파괴 
						cluster(R-h, j); 
						break;
					}
				}
			} else { // 홀수 번째 (오 -> 왼)
				//System.out.println("right -> left");
				for(int j = C-1; j >= 0; j--) {
					if(map[R-h][j].equals("x")) {
						map[R-h][j] = "."; // 미네랄 파괴 
						cluster(R-h, j);
						break;
					}
				}
			}

			//print();
		}

		//System.out.println("------------- 정답------------");
		print();
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
		ArrayList<Point> list = new ArrayList<>();

		while(!pq.isEmpty()) {
			map[pq.peek().r][pq.peek().c] = "o"; // 다른 클러스터와 구분짓기 위함 
			list.add(pq.poll());
		}

		//		System.out.println("\n - 떨어뜨릴 클러스터 ");
		//		print();

		//System.out.println(R - list.get(0).r);
		
		int max = 0;

		loop:
			for(int i = 1; i < R - list.get(0).r; i++) {
				//System.out.println("i : " + i);
				max = i;

				for(int j = 0; j < list.size(); j++) {
					int cx = list.get(j).r;
					int cy = list.get(j).c;

					int nx = cx + i;

					//System.out.println("nx : " + nx + ", cy : " + cy);
					
					if(nx >= R || map[nx][cy].equals("x")) { // 위에서 떨어뜨릴 미네랄을 o로 바꾸지 않았다면 다른 미네랄 x와 혼동됨 
						max = i-1;
						break loop;
					}
				}
			}

		//System.out.println(" - 최대로 떨어뜨릴 수 있는 칸의 개수 : " + max + "\n");

		for(int i = 0; i < list.size(); i++) { // max값만큼 클러스터 떨어뜨리기 
			int cx = list.get(i).r;
			int cy = list.get(i).c;

			int nx = cx + max;

			map[cx][cy] = ".";
			map[nx][cy] = "x";
		}

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
