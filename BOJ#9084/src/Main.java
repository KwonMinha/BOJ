/**
 * @author Minha Gwon
 * @date 2021. 2. 20.
 * 동전 
 * https://www.acmicpc.net/problem/9084
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			int N = sc.nextInt(); // 동전의 가지 수
			
			int[] coins = new int[N];
			for(int i = 0; i < N; i++) { // 동전의 각 금액 - 오름차순 정렬되어 있음 
				coins[i] = sc.nextInt();
			}
			
			int M = sc.nextInt(); // N가지 동전으로 만들어야 할 금액
			
			// 가장 많은 코인을 사용해서 M을 만드는 경우 -> 가장 값이 작은 동전만을 사용해서 만듬 
			// -> 이 값이 dp 배열의 열의 개수 
			int maxCnt = coins[0] % M == 0 ? coins[0] / M : coins[0] / M + 1;
			
			int[][] dp = new int[coins.length][maxCnt+1]; // 0개 쓰는 경우도 고려해야하기 때문에 maxCnt+1
			
			
			
			
		}
	}


}