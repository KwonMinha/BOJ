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

	static int[][] colMap, rowMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		
		int[] col = new int[N];
		int[] row = new int[N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				col[j] = map[i][j];
				row[j] = map[j][i];
			}

			System.out.print("\ncol : ");
			for(int j : col) {
				System.out.print(j + " ");
			}
			if(solve(col)) ans++;
			
			System.out.print("\nrow : ");
			for(int j : row) {
				System.out.print(j + " ");
			}
			if(solve(row)) ans++;
			
			System.out.println();
		}

		System.out.println(ans);
	}
	
	public static boolean solve(int[] arr) {
		System.out.println();
		boolean[] visited = new boolean[N];
		
		for(int i = 1; i < N; i++) {
			if(arr[i] == arr[i-1]) { // 높이가 같으면 pass 
				System.out.println("same height");
				continue;
			} else if(arr[i] - arr[i-1] == 1) { // 오름차순 높이차 1 
				System.out.println("height == 1");
				int j = i-1;
				int cnt = 0;
				
				while(j >= 0 && arr[j] == arr[i]-1 && cnt < L && !visited[j]) {
					cnt++;
					j--;
				}
				System.out.println("cnt : " + cnt + ", j : " + j);
				
				if(cnt < L) {
					System.out.println("fail L");
					return false;
				} else {
					System.out.println("succ L");
					while(cnt != 0) {
						visited[j+1] = true;
						j++;
						cnt--;
					}
				}
			} else if(arr[i] - arr[i-1] == -1) { // 내림차순 높이차 1 
				System.out.println("height == -1");
				int cnt = 1;
				int j = i+1;
				
				while(j < N && arr[j] == arr[i] && cnt < L && !visited[j]) {
					cnt++;
					j++;
				}
				System.out.println("cnt : " + cnt + ", j : " + j);
				
				if(cnt < L) {
					System.out.println("fail L");
					return false;
				} else {
					System.out.println("succ L");
					while(cnt != 0) {
						visited[j-1] = true;
						j--;
						cnt--;
					}
				}
			} else { // 1보다 큰 높이차라면 실패 
				System.out.println("fail");
				return false;
			}
		}
		
		return true;
	}

}
