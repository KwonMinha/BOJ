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

		for(int i = 0; i < M; i++) 
			Arrays.fill(size[i], 1);

		for(int i = 0; i < N; i++) {
			int zero = sc.nextInt();
			int one = sc.nextInt();
			int two = sc.nextInt();
			
			// 제일 왼쪽 열 애벌레 키우기 
			for(int j = M-1; j > 0; j--) { 
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					size[j][0] += 1;
				} else if(two != 0) {
					two--;
					size[j][0] += 2;
				}
			}

			// 제일 위쪽 행 애벌레 키우기 
			for(int j = 0; j < M; j++) {
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					size[0][j] += 1;
				} else if(two != 0) {
					two--;
					size[0][j] += 2;
				}
			}

			// 나머지 애벌레 키우기 
			for(int j = 1; j < M; j++) {
				for(int k = 1; k < M; k++) {
					size[j][k] = Math.max(size[j][k], size[j-1][k]);
					size[j][k] = Math.max(size[j][k], size[j-1][k-1]);
					size[j][k] = Math.max(size[j][k], size[j][k-1]);
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
