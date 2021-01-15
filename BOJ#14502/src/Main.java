/**
 * @author Minha Gwon
 * 2020. 10. 16.
 * #14502 - 연구소 
 * URL : https://www.acmicpc.net/problem/14502
 * BLOG - https://minhamina.tistory.com/69
 */

import java.util.*;
import java.io.*;

public class Main {
	public static int[][] map;
	public static int[][] copyMap;
	public static int N, M;
	public static ArrayList<Point> virusList = new ArrayList<>();
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j] == 2) { // 매번 바이러스를 퍼뜨릴때마다 위치를 찾는 번거로움을 없애기 위해, 애초에 바이러스의 위치를 List에 추가 
					virusList.add(new Point(i, j));
				}
			}
		}

		buildWall(0);

		System.out.println(ans);
	}

	//벽 세우기 
	public static void buildWall(int depth) { //모든 벽을 세우는 조합을 구하기 위해 DFS 사용 - N*M개중 빈칸(0)인 것들 중에 3개를 뽑는 조합이라고 생각하면 됨 
		if(depth == 3) { //3개의 벽을 다 세웠다면 
			copyMap = copy(map); // 이전 맵을 기억하기 위해 맵 복사 
			spreadVirus(); // 바이러스 퍼뜨리기 
			return;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1; // 벽을 세우는 경우 
					buildWall(depth + 1);
					map[i][j] = 0; // 벽을 세우지 않는 경우 
				}
			}
		}
	}

	//map 복사 
	public static int[][] copy(int[][] arr) { // 2차원 배열 깊은 복사 
		int[][] copy = new int[N][M];
		for(int i = 0; i < arr.length; i++) {
			System.arraycopy(arr[i], 0, copy[i], 0, arr[i].length);
		}
		return copy;
	}

	//바이러스 퍼뜨리기 
	public static void spreadVirus() {
		Queue<Point> queue = new LinkedList<>();
		
		for(int i = 0; i < virusList.size(); i++) { // 바이러스들 큐에 넣어줌 
			queue.add(virusList.get(i));
		}

		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y =  queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				// map 경계를 넘지 않는 선에서, 빈칸을 만난다면 바이러스 퍼뜨림 
				if(nx >= 0 && nx < N && ny >= 0 && ny < M && copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = 2;
					queue.add(new Point(nx, ny));
				}
			}
		}

		getSafeArea(copyMap); // 안전 영역 구하기 
	}

	//안전 영역(빈칸 0) 구하기 
	public static void getSafeArea(int[][] copyWall) {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyWall[i][j] == 0) {
					cnt++;
				}
			}
		}

		ans = Math.max(ans, cnt);
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
