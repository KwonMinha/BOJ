/**
 * @author Minha Gwon
 * @date 2021. 1. 20.
 * 인구 이동
 * https://www.acmicpc.net/problem/16234
 * BLOG - https://minhamina.tistory.com/115
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int N, L, R;
	public static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		while(true) {	
			boolean isBfs = false;

			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) { // 이미 연합을 이루었던 경우는 pass 

						if(bfs(i, j))
							isBfs = true;
					}

				}
			}

			if(!isBfs) {
				// 전체 정점을 다 돌면서, 인구 이동이 한번이라도 안 일어나 isBfs가 false라면 break;
				break;
			} else {
				// 전체 정점을 도는 것을 하루로 생각한다면, 하루에 각 정점들에서 총 n번의 인구 이동이 일어날 수 있다. 
				// 인구 이동을 할 때마다 answer를 +1 하는 것이 아니라, 하루에 인구 이동이 n번이든 뭐든 1번 이상 일어난다면 +1을 해줘야 함. 
				answer++;
			}
		}

		System.out.println(answer);
	}

	public static void print(int[][] map) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// BFS를 이용해 국경선을 공유하면서, 인구 차이도 만족하는 나라를 찾음 
	public static boolean bfs(int x, int y) {
		boolean isUnion = false; // 인구 이동이 한번도 일어나지 않는다면 false 반환 
		
		ArrayList<Point> unionList = new ArrayList<>(); // 연합에 속하는 나라의 좌표 리스트 
		unionList.add(new Point(x, y));
		int count = 1; // 연합을 이루고 있는 나라의 개수 (처음엔 (x, y) 넣으니 1부터 시작  
		int sum = map[x][y];

		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		visited[x][y] = true;

		while(!queue.isEmpty()) {
			int curX = queue.peek().x;
			int curY = queue.poll().y;

			for(int i = 0; i < 4; i++) { // 상좌하우 검사 
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) // 범위 벗어나면 pass 
					continue;

				if(visited[nx][ny]) // 이미 확인한 곳이라면 pass 
					continue;

				// 두 나라의 인구 차이가 L명 이상, R명 이하라면 연합 가능 
				int val = Math.abs(map[curX][curY] - map[nx][ny]);

				if(val >= L && val <= R && !visited[nx][ny]) {
					unionList.add(new Point(nx, ny));
					count++;
					sum += map[nx][ny];
					
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
				}
			}
		}
		
		if(unionList.size() > 1) { // (x, y) 좌표를 기준으로 연합이 이루어진 경우 
			isUnion = true; // 인구 이동이 일어난 경우 true 반환 
			int result = sum / count;
			
			for(int i = 0; i < unionList.size(); i++) { // 연합 인구수 업데이트 
				Point p = unionList.get(i);
				map[p.x][p.y] = result;
			}
		}
		
		return isUnion;
	}
}