/**
 * @author Minha Gwon
 * @date 2021. 3. 2.
 * 여왕벌
 * https://www.acmicpc.net/problem/10836
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int M, N;
	static int[][] size, grow;
	static int[] dx = {-1, -1, 0}; //  상, 상좌, 좌 순서 
	static int[] dy = {0, -1, -1};


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();

		size = new int[M][M];
		grow = new int[M][M];

		for(int i = 0; i < M; i++) 
			Arrays.fill(size[i], 1);

		for(int i = 0; i < N; i++) {
			int zero = sc.nextInt();
			int one = sc.nextInt();
			int two = sc.nextInt();

			String num = "";
			for(int j = 0; j < zero; j++)
				num += 0;
			for(int j = 0; j < one; j++)
				num += 1;
			for(int j = 0; j < two; j++)
				num += 2;

			int cnt = 0;
			for(int j = M-1; j > 0; j--) { 
				grow[j][0] = Integer.parseInt(num.charAt(cnt)+"");
				size[j][0] += grow[j][0];
				cnt++;
			}

			for(int j = 0; j < M; j++) {
				grow[0][j] = Integer.parseInt(num.charAt(cnt)+"");
				size[0][j] += grow[0][j];
				cnt++;
			}

			for(int j = 1; j < M; j++) {
				for(int k = 1; k < M; k++) {
					int x = j;
					int y = k;
					
					int max = 0;
					
					for(int l = 0; l < 3; l++) {
						int nx = x + dx[l];
						int ny = y + dy[l];
						
						if(nx < 0 || ny < 0 || nx >= M || ny >= M) continue;
						
						max = Math.max(max, grow[nx][ny]);
					}
					
					grow[x][y] = max;
					size[x][y] += max;
				}
			}
		}
		
		print(size);
	}

	public static void print(int[][] arr) {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
