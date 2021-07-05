/**
 * @author Minha Gwon
 * @date 2021. 7. 6.
 * 거울 설치 
 * https://www.acmicpc.net/problem/2151
 */

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static char[][] map;
	static ArrayList<Point> mirrorList;
	static ArrayList<Point> pointList;
	
	static int[] arr;
	
	static char[][] copyMap;
	static boolean[][] visited;

	static int[] dx = {-1, 0, 1, 0}; // 0 1 2 3 상 우 하 좌 방향 
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		mirrorList = new ArrayList<>();
		pointList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == '!') {
					mirrorList.add(new Point(i, j));
					map[i][j] = '.';
				} else if(map[i][j] == '#') {
					pointList.add(new Point(i, j));
				}
			}
		}
		
		arr = new int[mirrorList.size()];
		for(int i = 1; i <= mirrorList.size(); i++) {
			combination(0, mirrorList.size(), i, 0);
		}
		
	}
	
	public static void combination(int index, int n, int r, int target) {
		if(r == 0) {
			setMirror(arr, index);
		}else if (target == n) {
			return;
		} else { 
			arr[index] = target;
			combination(index + 1, n, r - 1, target + 1); 
			combination(index, n, r, target + 1); 
		} 
	}
	
	public static void setMirror(int[] arr, int length) {
		copyMap = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			System.arraycopy(map[i]	, 0	, copyMap[i], 0, N);
		}
		
		for (int i = 0; i < length; i++) {
			int idx = arr[i];
			int x = mirrorList.get(idx).x;
			int y = mirrorList.get(idx).y;
			copyMap[x][y] = '!';
			
			System.out.print(arr[i] + " ");
			System.out.println("x : " + x + ", y : " + y);
		}
		
		System.out.println();
		
		print(copyMap);
		
		if(bfs()) {
			System.out.println(length);
			System.exit(0);
		} else {
			System.out.println("!");
		}
	} 
	
	public static boolean bfs() {
		Point start = pointList.get(0);
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		
		visited = new boolean[N][N];
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			
			
			int cx = queue.peek().x;
			int cy = queue.poll().y;
			
			System.out.println("cx : " + cx + ", cy : " + cy);
			
			if(queue.peek().d != -1) {
				
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				System.out.println("nx : " + nx + ", ny : " + ny);
				
				if(!check(nx, ny)) {
					System.out.println("범위 벗어남  ");
					continue;
				}
				
				if(copyMap[nx][ny] == '#') { // 다른 문을 만난 경우 
					System.out.println("ok");
					
					return true;
				}
				
				if(copyMap[nx][ny] == '!') { // 45도 기울어진 거울을 만난 경우 
					 // 0 1 2 3 상 우 하 좌 방향 
					int newDir;
					
					System.out.println("거울 ");
					
					if(i == 0) { // 상 -> 우로 꺾임 1
						newDir = 1;
					} else if(i == 1) { // 우 -> 아래로 꺾임 2
						newDir = 2;
					} else if(i == 2) { // 하 -> 좌로 꺾임 3
						newDir = 3;
					} else { // 좌 ->  위로 꺾임 0
						newDir = 0;
					}
					
					nx = nx + dx[newDir];
					ny = ny + dy[newDir];
					
					if(check(nx, ny)) {
						queue.add(new Point(nx, ny));
					}
					
					visited[nx][ny] = true;
				} else { // 그냥 빈칸일 경우 
					System.out.println("빈칸 ");
					queue.add(new Point(nx, ny, i));
					visited[nx][ny] = true;
				}
			}
		}
		
		System.out.println("no");
		return false;
	}
	
	public static boolean check(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N || visited[x][y] || copyMap[x][y] == '*') {
			return false;
		} else {
			return true;	
		}
	}
	
	public static void print(char[][] arr) {
		System.out.println();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

}

class Point {
	int x;
	int y;
	int d = -1;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
