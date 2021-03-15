/**
 * @author Minha Gwon
 * @date 2021. 3. 15.
 * 알고스팟
 * https://www.acmicpc.net/problem/1261
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] arr = new int[N][M];
		
		for(int i = 0; i < M; i++) {
			String str = sc.next();
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
	}

}
