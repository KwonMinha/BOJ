/**
 * @author Minha Gwon
 * @date 2021. 2. 16.
 * 평범한 배낭 
 * https://www.acmicpc.net/problem/12865
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int K, N;
	static int[][] bag;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		bag = new int[N][N];

		for(int i = 0; i < N; i++) {
			bag[i][0] = sc.nextInt();
			bag[i][1] = sc.nextInt();
		}

		Arrays.sort(bag, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for(int i = 1; i < N; i++) {
			solve(0, i, 0, 0, 0);
		}

		System.out.println(answer);
	}


	public static void solve(int index, int r, int target, int tw, int tv) { 
		if (r == 0) {
			if(tw <= K) {
				answer = Math.max(answer, tv);
			} 
		} else if (target == N) {
			return; 
		} else { 
			solve(index + 1, r - 1, target + 1, tw+bag[target][0], tv+bag[target][1]); 
			solve(index, r, target + 1, tw, tv); 
		} 
	}

}