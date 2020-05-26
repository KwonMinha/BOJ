/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * 나의 인생에는 수학과 함께
 * https://www.acmicpc.net/problem/17265
 */

import java.util.Scanner;

public class Main2 {
	public static int min = Integer.MAX_VALUE;
	public static int max = Integer.MIN_VALUE;

	public static int n;
	public static char[][] mat;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		n = N;
		mat = new char[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				mat[i][j] = sc.next().charAt(0);
			}
		}

		dfs(0, 0, mat[0][0]-'0', mat[0][0]);
		System.out.println(max + " " + min);
	}

	public static void dfs(int x, int y, int pre, char oper) {
		int temp = mat[x][y] - '0';
		if(temp >= 0 && temp <= 5) { //temp가 숫자인 경우 
			if(oper == '+')
				pre += temp;
			else if(oper == '-')
				pre -= temp;
			else if(oper == '*')
				pre *= temp;
		} else { //temp가 연산자인 경우 
			oper = mat[x][y];
		}
		
		//(n, n)까지 다 확인한 경우 종료 
		if(x == n-1 && y == n-1) {
			max = Math.max(pre, max);
			min = Math.min(pre, min);
			return;
		}

		//아래쪽으로 한 칸 이동 
		if(x < n-1) {
			dfs(x+1, y, pre, oper);
		}

		//오른쪽으로 한 칸 이동 
		if(y < n-1) {
			dfs(x, y+1, pre, oper);
		}

	}

}
