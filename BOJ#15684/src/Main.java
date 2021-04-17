/**
 * @author Minha Gwon
 * @date 2021. 4. 18.
 * 사다리 조작 
 * https://www.acmicpc.net/problem/15684
 */

import java.util.Scanner;

public class Main {
	static int N, M, H;
	static boolean[][][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		if(M == 0) { // 가로선 없는 경우 
			System.out.println("0");
			System.exit(0);
		}

		visited = new boolean[H+1][N+1][H+1][N+1];

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			visited[a][b][a][b+1] = true;
			visited[a][b+1][a][b] = true;
		}

		check(0); // 가로선을 추가 안해도 가능한 경우 

		for(int i = 1; i <= 3; i++) { // 추가할 가로선 개수  
			dfs(i, 0, i);
		}

		System.out.println(-1); // 불가능 
	}

	// 조합을 구하는 알고리즘과 비슷하게 DFS로 추가할 가로선 개수 n만큼 가로선을 놓아봄 
	public static void dfs(int n, int depth, int cnt) {
		if(depth == n) { // n개의 가로선을 다 놓았을 때 		
			check(cnt); // i -> i 대응하는지 검사 

			return; // 재귀 종료 
		}

		for(int i = 1; i < H+1; i++) {
			for(int j = 1; j < N; j++) { // N-1에서 가로선 왼->오 (x, y) -> (x, y+1) / 오->왼 (x, y+1) -> (x, y) 다 설정하기 때문에 N까지만 
				int[][] dy = {{0, 1}, {-1, 0}, {1, 2}}; // 현재 위치, 현재 -1 위치, 현재 +1 위치에 가로선을 놓을 수 있는지 검사 

				boolean flag = true;

				for(int k = 0; k < 3; k++) {
					int ny1 = j + dy[k][0];
					int ny2 = j + dy[k][1];

					if(ny1 <= 0 || ny2 <= 0 || ny1 >= N+1 || ny2 >= N+1) continue; // 범위 벗어나는 경우 pass 

					if(visited[i][ny1][i][ny2]) { // 이미 다른 가로선이 있음 -> 놓을 수 X 
						flag = false;
						break;
					}
				}

				if(flag) { // 조건 모두 통과 -> 가로선 놓아봄 
					visited[i][j][i][j+1] = true; // 현재 좌표를 true로 만들어 가로선을 놓았다고 침 
					visited[i][j+1][i][j] = true;

					dfs(n, depth+1, cnt); // 가로선을 놓았으니 depth에 1을 추가하고 다시 DFS

					visited[i][j][i][j+1] = false; // 다음 DFS를 위해 선택한 좌표를 false로 만들어 가로선 놓은걸 취소시킴 
					visited[i][j+1][i][j] = false;
				}
			}
		}
	}

	// i번 세로선이 i번에 대응하는지 검사 
	public static void check(int cnt) {
		for(int i = 1; i < N+1; i++) {
			int cx = 1; // 1행부터 시작해서 내려감 
			int cy = i;

			while(cx != H+1) { // H행을 넘을때까지 사다리 타기 
				if(cy+1 < N+1 && visited[cx][cy][cx][cy+1]) { // 현재 좌표 오른쪽에 가로선이 있는 경우  
					cy = cy+1; // 오른쪽 가로선으로 이동 
					cx++; // 아래로 내려감 
				} else if(cy - 1 > 0 && visited[cx][cy][cx][cy-1]) { // 현재 좌표 왼쪽에 가로선이 있는 경우 
					cy = cy-1; // 왼쪽 가로선으로 이동 
					cx++; // 아래로 내려감 
				} else { // 왼쪽, 오른쪽에 가로선이 둘 다 없는 경우 
					cx++; // 아래로 내려감 
				}
			}

			if(cy != i) return; // 실패 - i번 세로선의 결과가 i X
		}

		// 성공 - i번 세로선의 결과가 i번
		System.out.println(cnt);
		System.exit(0);
	}

}