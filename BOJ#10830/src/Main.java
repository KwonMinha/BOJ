/**
 * @author Minha Gwon
 * @date 2021. 2. 12.
 * 행렬 제곱 
 * https://www.acmicpc.net/problem/10830
 */

import java.util.Scanner;

public class Main {
	public static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int B = sc.nextInt();
		int[][] A = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) 
				A[i][j] = sc.nextInt();
		}
		
        int[][] result = powerMatrix(A, B);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                System.out.print(result[i][j] % 1000 + " ");
            System.out.println();
        }

	}

	// 거듭 제곱 빠르게 계산하기 
	static int[][] powerMatrix(int[][] a, long b) {
		if(b == 1)
			return a;
		else if(b % 2 == 0) {
			int[][] temp = powerMatrix(a, b/2);
			return multipleMatrix(temp, temp);
		}
		else {
			return multipleMatrix(powerMatrix(a, b-1), a);
		}
	}


	// 행렬 곱셈 
	static int[][] multipleMatrix(int[][] m1, int[][] m2) {
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++)
					temp[i][j] += m1[i][k] * m2[k][j];
				temp[i][j] %= 1000;
			}
		}
		return temp;
	}


}
