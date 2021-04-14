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

		int ans = 0;

		for(int i = 0; i < N; i++) { 
			int[] col = new int[N];
			int[] row = new int[N];
			
			for(int j = 0; j < N; j++) {
				col[j] = map[i][j];
				row[j] = map[j][i];
			}
			
			// i, j 행 검사 
			System.out.println("\n----col " + i);
			if(solve(col)) { // 길을 지나갈 수 있다면 +1
				ans++; 
				System.out.println("*********succ");
			}
			
			// j, i 열 검사 
			System.out.println("\n----row " + i);
			if(solve(row)) {// 길을 지나갈 수 있다면 +1
				ans++; 
				System.out.println("************succ");
			}
		}
		
		System.out.println();


		System.out.println(ans);

	}

	public static boolean solve(int[] arr) {
		boolean flag = true;

		// 앞부터 검사 
		System.out.println("front");
		int preHeight = arr[0];
		int sameHeightCnt = 1; // 0번째 칸은 넣고 시작하니 1부터 시작 

		for(int i = 1; i < N; i++) {
			if(preHeight > arr[i]) { // 이전 높이보다 작으면 경사로 놓을 수 X
				flag = false;
				break;
			} else if (preHeight == arr[i]){ // 같으면 다음으로 넘어감 
				sameHeightCnt++;
				continue;
			} else if(arr[i] - preHeight == 1) { // 이전 높이보다 현재 높이가 1 큰 경우 (높이차 1) -> 경사로 놓을 수 있는지 검증 
				if(sameHeightCnt >= L) { // L개의 높이가 같은 연속된 칸이 있는 경우 -> 경사로 놓을 수 O 
					sameHeightCnt = 1; // 경사로 세웠으니 cnt 초기화 
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

		System.out.println("back");
		// 앞부터 검사한게 실패라면 -> 뒤부터 다시 검사 
		flag = true;
		preHeight = arr[N-1];
		sameHeightCnt = 1;

		for(int i = N-2; i >= 0; i--) {
			System.out.println("pre : " + preHeight + ", cur i : " + arr[i]);
			if(preHeight > arr[i]) { // 이전 높이보다 작으면 경사로 놓을 수 X
				System.out.println("높이 작아!!");
				flag = false;
				break;
			} else if (preHeight == arr[i]){ // 같으면 다음으로 넘어감 
				System.out.println("높이 같아 넘어가 ");
				sameHeightCnt++;
				continue;
			} else if(arr[i] - preHeight == 1) { // 이전 높이보다 현재 높이가 1 큰 경우 (높이차 1) -> 경사로 놓을 수 있는지 검증 
				System.out.println("same : " + sameHeightCnt);
				if(sameHeightCnt >= L) { // L개의 높이가 같은 연속된 칸이 있는 경우 -> 경사로 놓을 수 O 
					System.out.println("높이차1 : 연속된거 있음 성공 ");
					sameHeightCnt = 1; // 경사로 세웠으니 cnt 초기화 
					preHeight = arr[i];
				} else {
					System.out.println("높이차1 : 연속 안됨 실패 ");
					flag = false;
					break; // L개 칸 연속 X, 경사로 놓을 수 X 
				}
			} else { // 높이차가 1보다 큰 경우 경사로 놓을수 X
				System.out.println("높이차가 1보다 큼 요 ");
				flag = false;
				break;
			}
		}

		if(flag) return true; // 뒤부터 검사한 길을 지나갈 수 있다면 성공 


		return false; // 실패 
	}

}
