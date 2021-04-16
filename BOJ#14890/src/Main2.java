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

public class Main2 {
	static int[][] map;
	static int N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		// 초기 지도 구성 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		
		// 행길, 열길로 나누어 지나갈 수 있는지 검사 
		int[] col = new int[N];
		int[] row = new int[N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				col[j] = map[i][j];
				row[j] = map[j][i];
			}
			
			// 지나갈 수 있다면 +1 
			if(solve(col)) ans++;
			
			if(solve(row)) ans++;
		}

		System.out.println(ans);
	}
	
	public static boolean solve(int[] arr) {
		boolean[] visited = new boolean[N]; // 경사로를 놓았는지 안 놓았는지 판별하기 위한 배열 
		
		for(int i = 1; i < N; i++) {
			if(arr[i] == arr[i-1]) { // 높이가 같으면 pass 
				continue;
			} else if(arr[i] - arr[i-1] == 1) { // 오름차순으로 높이차 1인 경우  
				int j = i-1; // 현재 i칸 이전의 칸들 검사 
				int cnt = 0;
				
				// 범위를 벗어나지 않고, 높이가 같으며, 경사로가 없는 곳이고, L칸이 연속되지 않을 때까지 검사 
				while(j >= 0 && arr[j] == arr[i]-1 && !visited[j] && cnt < L ) {
					cnt++;
					j--;
				}
				
				if(cnt < L) {  // L칸이 연속되지 않는 경우 실패 
					return false;
				} else {
					// L칸이 연속되는 경우, j인덱스를 거슬러 올라오며 경사로 놓음 
					while(cnt != 0) {
						visited[j+1] = true;
						j++;
						cnt--;
					}
				}
			} else if(arr[i] - arr[i-1] == -1) { // 내림차순으로 높이차 1인 경우 
				int j = i+1; // 현재 i칸 포함 이후의 칸들 검사 
				int cnt = 1;
				
				// 범위를 벗어나지 않고, 높이가 같으며, 경사로가 없는 곳이고, L칸이 연속되지 않을 때까지 검사 
				while(j < N && arr[j] == arr[i] && !visited[j] && cnt < L) {
					cnt++;
					j++;
				}
				
				if(cnt < L) { // L칸이 연속되지 않는 경우 실패 
					return false;
				} else {
					// L칸이 연속되는 경우, j인덱스를 거슬러 내려오며 경사로 놓음 
					while(cnt != 0) {
						visited[j-1] = true;
						j--;
						cnt--;
					}
				}
			} else { // 1보다 큰 높이차라면 실패 
				return false;
			}
		}
		
		return true;
	}

}
