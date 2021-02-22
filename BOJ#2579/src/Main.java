/**
 * @author Minha Gwon
 * @date 2021. 2. 22.
 * 계단 오르기 
 * https://www.acmicpc.net/problem/2579
 * BLOG - https://minhamina.tistory.com/150
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] stairs = new int[N+1];

		for(int i = 1; i < N+1; i++) {
			stairs[i] = sc.nextInt();
		}

		int[] dp = new int[N+1];
		dp[1] = stairs[1]; // 첫번째 계단 dp 초기값으로 초기화 
		if (N >= 2) { // N = 1의 경우가 있을 수 있기 때문에 예외처리
			dp[2] = stairs[1] + stairs[2]; // 두 칸 점프를 할 수 있는 3번째 계단부터 시작하기 때문에, 2번째 계단 역시 초기화 
		}
		
		for(int i = 3; i < N+1; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i-1]) + stairs[i];
		}

		System.out.println(dp[N]);
	}

}
