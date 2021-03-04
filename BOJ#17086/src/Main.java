import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 아기상어2
 * https://www.acmicpc.net/problem/17086
 */
public class Main {
	static int N, M;
	static int[][] arr;
	static int[][] count;
	static ArrayList<Point> sharkList = new ArrayList<>();
	static boolean[][] visited;
	
	static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순서 
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) {
					sharkList.add(new Point(i, j));
				}
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < sharkList.size(); i++) {
			bfs(sharkList.get(i).x, sharkList.get(i).y);
			
			print();
		}
			


	}
	
	public static void bfs(int x, int y) {
		count = new int[N][M];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		count[x][y] = 1;
		
		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;
			
			for(int i = 0; i < 4; i++) {
				int nx  = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= M || ny >= N || count[nx][ny] != 0) continue;
				
				arr[nx][ny] += arr[x][y] + 1;
				count[nx][ny] += 1;
				
				
			}
		}
		
		print();
		
	}
	
	public static void print() {
		System.out.println();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		
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
