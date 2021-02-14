/**
 * @author Minha Gwon
 * @date 2021. 2. 12.
 * 행렬 제곱 
 * https://www.acmicpc.net/problem/10830
 */

import java.util.Scanner;

public class Main {
	public static int[][] map;
	public static int[][] multiMap;
	public static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int B = sc.nextInt();
		
		map = new int[N][N];
		multiMap = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		
	}
	
	public static void MatrixMultiplication() {
		for(int i = 0; i < N; i++) {
		
			for(int j = 0; j < N; j++) {
				// 1행 1열 
				for(int k = 0; k < N; k++) {
					map[i][j] += map[i][k] * map[j][k];
				}
			}
		}
	}

}
