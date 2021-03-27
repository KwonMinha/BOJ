/**
 * @author Minha Gwon
 * @date 2021. 3. 26.
 * Z
 * https://www.acmicpc.net/problem/1074
 */

import java.util.Scanner;

public class Main {
	static int N, r, c, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();

		solve((int) Math.pow(2, N), 0, 0);
	}

	static void solve(int n, int y, int x) {
		if (y == r && x == c) {
			System.out.println(cnt);
			System.exit(0);
		}

		if (y <= r && r < (y + n) && x <= c && c < (x + n)) {
			solve(n/2, y, x);
			solve(n/2, y, x + n/2); // 오른쪽 
			solve(n/2, y + n/2, x); // 아래 
			solve(n/2, y + n/2, x + n/2); // 대각선 아래 
		} else {
			cnt += n * n;
		}
	}

}