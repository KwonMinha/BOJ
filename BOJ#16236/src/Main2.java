/**
 * @author Minha Gwon
 * @date 2020. 6. 16.
 * 아기 상어
 * https://www.acmicpc.net/problem/16236
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {
	static int N, SX, SY;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N]; //먹이 탐색 시 중복 제거를 위한 배열 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) { // 아기 상어 최초 위치
					SX = i;
					SY = j;
				}
			}
		}

		solve();
	}

	// 최적의 먹이를 먹고 다시 먹이 탐색할 때
	private static void init() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				visited[i][j] = false;
		}
	}

	private static void solve() {
		Queue<Pair> queue = new LinkedList<>();

		queue.add(new Pair(SX, SY, 2, 0, 0)); //처음 아기 상어의 x좌표, y좌표, 크기, 먹은 횟수, 이동한 거리)
		visited[SX][SY] = true;

		// 걸린 시간
		int time = 0;

		while(!queue.isEmpty()) {
			//먹을 수 있는 물고기를 찾은 경우, 그 때의 값을 저장할 변수들 
			int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
			int size = 0, eat = 0, dist = 0; 

			int qSize = queue.size();
			for(int j = 0; j < qSize; j++) {
				Pair shark = queue.poll();

				for(int i = 0; i < 4; i++) { //4방향 이동 
					int tx = shark.x + dir[i][0];
					int ty = shark.y + dir[i][1];

					if(tx < 0 || ty < 0 || tx >= N || ty >= N) continue; //map 범위를 벗어나면 이동 X 
					if(visited[tx][ty]) continue; //이미 갔던 곳이라면 이동 X 
					if(map[tx][ty] > shark.size) continue; //현재 상어의 크기보다 크면 이동 X 

					//위의 조건을 모두 벗어난 이동할 수 있는 곳이라면 
					visited[tx][ty] = true; //이동했으니 true 
					//이동한 상어의 위치와 크기, 먹은 횟수 그리고 한 칸 이동했다는 의미로 dist+1 값을 큐에 넣음 
					queue.add(new Pair(tx, ty, shark.size, shark.eat, shark.dist+1)); 

					//그런데 이곳이 현재의 아기 상어보다 작은 물고기가 있는 곳이라면 
					if(map[tx][ty] != 0 && map[tx][ty] != shark.size) {
						if(tx < minX) { //가장 가까이, 가장 위에 있는 물고기라면
							//그 때의 위치, 크기, 횟수, 거리+1 값을 변수에 저장 
							minX = tx;
							minY = ty;
							size = shark.size;
							eat = shark.eat;
							dist = shark.dist + 1;
						}else if(tx == minX) { //이미 가장 위에 있는 물고기라면
							if(ty < minY) { //가장 왼쪽에 있는 물고기인지 확인 후, 그 때의 값 변수에 저장  
								minX = tx;
								minY = ty;
								size = shark.size;
								eat = shark.eat;
								dist = shark.dist + 1;
							}
						}
					}
				}
			}
			
			//minX가 max값이 아니라 바뀌었다면 먹을 물고기가 있다는 것 
			if(minX != Integer.MAX_VALUE) {
				//그럼 물고기를 먹음 
				eat++; //먹었으니 먹은 횟수 추가 
			
				//아기 상어가 자신의 크기와 같은 수의 물고기를 먹었다면 
				if(eat == size) {
					size++; //크기 1 증가 
					eat = 0;
				}
				
				//아기 상어 위치 이동 
				map[SX][SY] = 0; //기존의 상어 위치는 이동되었으니 0으로 바꿔줌 
				map[minX][minY] = 9; 
				SX = minX; SY = minY;
				
				time += dist; //이동 거리가 시간과 같다
				init(); //재탐색을 위한 방문 초기화
				queue.clear(); //이전에 탐색하던 것들은 필요 없으므로 제거
				
				//이동된 위치에서 다시 아기 상어 탐색 
				visited[minX][minY] = true;
				queue.add(new Pair(minX, minY, size, eat, 0));
			}
		}
		System.out.println(time); //더이상 먹을 물고기가 없을 때, 지금까지 누적된 시간 출력 후 종료 
	}

	static class Pair{
		int x, y, size, eat, dist;

		Pair(int x, int y, int size, int eat, int dist){
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
			this.dist = dist;
		}
	}
}