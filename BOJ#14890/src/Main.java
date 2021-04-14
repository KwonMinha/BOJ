/**
 * @author Minha Gwon
 * @date 2021. 4. 14.
 * 경사로 
 * https://www.acmicpc.net/problem/14890
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < N; i++) { 
			for(int j = 0; j < N; j++) {
				int[] col = new int[N];
				int[] row = new int[N];

				// i, j 행 검사 
				col[j] = map[i][j];
				solve(col);

				// j, i 열 검사 
				row[j] = map[j][i];
			}

		}

	}

	public static boolean solve(int[] arr) {
		boolean flag = true;

		// 앞부터 검사 
		int preHeight = arr[0];
		int sameHeightCnt = 1; // 0번째 칸은 넣고 시작하니 1부터 시작 
		for(int i = 1; i < N-1; i++) {
			if(preHeight > arr[i]) { // 이전 높이보다 작으면 경사로 놓을 수 X
				flag = false;
				break;
			} else if (preHeight == arr[i]){ // 같으면 다음으로 넘어감 
				sameHeightCnt++;
				continue;
			} else if(arr[i] - preHeight == 1) { // 이전 높이보다 현재 높이가 1 큰 경우 (높이차 1) -> 경사로 놓을 수 있는지 검증 
				if(sameHeightCnt >= L) { // L개의 높이가 같은 연속된 칸이 있는 경우 -> 경사로 놓을 수 O 
					sameHeightCnt = 0; // 경사로 세웠으니 cnt 초기화 
					preHeight = arr[i];
				} else {
					flag = false;
					break; // L개 칸 연속 X, 경사로 놓을 수 X 
				}
			} else { // 높이차가 1보다 큰 경우 경사로 놓을수 X
				flag = false;
				break;
			}
		}

		if(flag) return true; // 앞부터 검사한 길을 지나갈 수 있다면 성공 

		flag = true;
		// 앞부터 검사한게 실패라면 -> 뒤부터 다시 검사 
		preHeight = arr[N-1];
		sameHeightCnt = 1;
		for(int i = N-2; i > 0; i--) {

		}

		if(flag) return true;

		return false;
	}
}
