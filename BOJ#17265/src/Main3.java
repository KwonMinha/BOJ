/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * 나의 인생에는 수학과 함께
 * https://www.acmicpc.net/problem/17265
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {
	public static int min = Integer.MAX_VALUE;
	public static int max = Integer.MIN_VALUE;
	public static int n;
	public static char[][] mat;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		n = N;
		mat = new char[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				mat[i][j] = st.nextToken().charAt(0);
			}
		}

		dfs(0, 0, mat[0][0]-'0', mat[0][0]);
		System.out.println(max + " " + min);
	}

	public static void dfs(int x, int y, int pre, char oper) {
		int temp = mat[x][y] - '0';
		if(temp >= 0 && temp <= 5) {
			if(oper == '+')
				pre += temp;
			else if(oper == '-')
				pre -= temp;
			else if(oper == '*')
				pre *= temp;
		} else {
			oper = mat[x][y];
		}
		
		if(x == n-1 && y == n-1) {
			max = Math.max(pre, max);
			min = Math.min(pre, min);
			return;
		}

		if(x < n-1) {
			dfs(x+1, y, pre, oper);
		}

		if(y < n-1) {
			dfs(x, y+1, pre, oper);
		}

	}

}
