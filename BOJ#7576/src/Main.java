/**
 * @author Minha Gwon
 * @date 2021. 1. 15.
 * 토마토
 * https://www.acmicpc.net/problem/7576
 * BLOG - https://minhamina.tistory.com/110
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int M, N;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int day = 0;
	public static int mapCount;
	public static int ripenedCount = 0; // 익은 토마토의 개수 
	public static int index = 0;
	public static ArrayList<Point> ripenedTomato = new ArrayList<>(); // 매번 익은 토마토의 위치를 찾는 번거로움을 없애기 위해, 애초에 익은 토마토의 위치를 List에 추가 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		mapCount = M*N; // 전체 맵 공간의 수 

		int[][] map = new int[N][M];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j] == 1) { // 익은 토마토 발견하면 
					ripenedTomato.add(new Point(i, j)); // 익은 토마토 리스트에 저장 
					ripenedCount++; // 익은 토마토수 +1 
				} else if(map[i][j] == -1) { // 전체 맵 공간에서 빈칸은 제외 
					mapCount--;
				}
			}
		}
		
		if(ripenedCount == mapCount) { // 처음부터 토마토가 다 익어있는 경우 
			System.out.println(0);
		} else {
			while(true) {
				int beforeSize = ripenedCount; // 익은게 있는지 확인을 위해 이전 ripenedCount 저장 
				
				ripen(map); // 토마토 익히기 
				
				if(ripenedCount == mapCount) { // 익힌 결과, 모든 토마토가 익은 경우 
					System.out.println(day);
					break;
				} else if(ripenedCount == beforeSize) { // 익히고 난 뒤에도 이전 사이즈와 동일하다면 모두 익지 못하는 상황 
					System.out.println(-1);
					break;
				}
			}
		}
	}

	// 토마토 익히기 
	public static void ripen(int[][] map) {
		Queue<Point> queue = new LinkedList<>();
		
/*		
		// remove하면 시간 초과 남. index 변수 만들어서 새로 들어온 익은 토마토부터 큐에 넣도록 함  
		while(!ripenedTomato.isEmpty()) { // 이미 익어있는 토마토들을 큐에 넣고 BFS 
			queue.add(ripenedTomato.get(0));
			ripenedTomato.remove(0); // 큐에 넣었다면 그 이후로는 확인할 필요 없으니 ripenedTomato 리스트에서 삭제 
		}
 */
		
		for(int i = index; i < ripenedTomato.size(); i++) {
			queue.add(ripenedTomato.get(i));
		}
		index = ripenedTomato.size();
		
		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.poll().y;
		
			for(int i = 0; i < 4; i++) { // 상 하 좌 우 이동 
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) // 범위 벗어나면 pass 
					continue;
				
				if(map[nx][ny] == 0) { // 이동한 곳에 익지 않은 토마토가 있다면 
					map[nx][ny] = 1; // 익히고 익은 ripenedTomato 리스트에 추가 
					ripenedTomato.add(new Point(nx, ny));
					ripenedCount++;
				}
			}
		}
	
		day++;
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

