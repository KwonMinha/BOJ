/**
 * @author Minha Gwon
 * @date 2021. 4. 21.
 * 게리맨더링 2 
 * www.acmicpc.net/problem/17779﻿
 */

import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[] output = new int[4];
	static int sum = 0;
	static int diff = Integer.MAX_VALUE;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				sum += map[i][j];
			}
		}

		//permutation(0, N, 4); // (x, y, d1, d2) 1부터 N까지의 수로 나올 수 있는 모든 경우의 수를 구해봄 

		// 기준점 (x, y)와 경계의 길이 d1, d2를 정한다. 
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				for(int d1 = 1; d1 < N; d1++) {
					for(int d2 = 1; d2 < N; d2++) {
						// 범위 : (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
						if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N) continue;

						getPopulation(x, y, d1, d2);
					}
				}
			}
		}

		System.out.println(diff);
	}

	// 인구수 구하기 
	public static void getPopulation(int x, int y, int d1, int d2) {
		/*
		(x, y), (x+1, y-1), ..., (x+d1, y-d1) // d1
		(x, y), (x+1, y+1), ..., (x+d2, y+d2) // d2
		(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2) // d2
		(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1) // d1
		 */

		// 경계선 정하기 
		boolean[][] border = new boolean[N][N];
		for(int i = 0; i <= d1; i++) {
			border[x + i][y - i] = true;
			border[x + d2 + i][y + d2 - i] = true;
		}

		for(int i = 0; i <= d2; i++) {
			border[x + i][y + i] = true;
			border[x + d1 + i][y - d1 + i] = true;
		} 

		// 각 선거구의 인구수 구하기 
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;

		// 1번 선거구: 0 ≤ r < x+d1, 0 ≤ c ≤ y
		for(int i = 0; i < x+d1; i++) {
			for(int j = 0; j <= y; j++) {
				if(border[i][j]) break;
				one += map[i][j];
			}
		}

		// 2번 선거구: 0 ≤ r ≤ x+d2, y < c ≤ N-1
		for(int i = 0; i <= x+d2; i++) {
			for(int j = N-1; j > y; j--) {
				if(border[i][j]) break;
				two += map[i][j];
			}
		}

		// 3번 선거구: x+d1 ≤ r ≤ N-1, 0 ≤ c < y-d1+d2
		for(int i = x+d1; i < N; i++) {
			for(int j = 0; j < y-d1+d2; j++) {
				if(border[i][j])  break;
				three += map[i][j];
			}
		}

		// 4번 선거구: x+d2 < r ≤ N-1, y-d1+d2 ≤ c ≤ N-1
		for(int i = x+d2+1; i < N; i++) {
			for(int j = N-1; j >= y-d1+d2; j--) {
				if(border[i][j]) break;
				four += map[i][j];
			}
		}

		// 5번 선거구 
		int five = sum - one - two - three - four;

		// 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 구하기 
		min = Math.min(min, Math.min(one, Math.min(two, Math.min(three, Math.min(four, five)))));
		max = Math.max(max, Math.max(one, Math.max(two, Math.max(three, Math.max(four, five)))));

		diff = Math.min(diff, max - min);
	}

	public static void permutation(int depth, int n, int r) {
		if(r == 0) { 
			int x = output[0];
			int y = output[1];
			int d1 = output[2];
			int d2 = output[3];
			
			if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N) return;
			if(d1 == 0 || d2 == 0) return;
			
			getPopulation(x, y, d1, d2);

			return;
		} 
		for(int i = 1; i < n; i++) {
			output[depth] = i;
			permutation(depth+1, n, r-1);
		}
	}

}
