/**
 * @author Minha Gwon
 * @date 2021. 4. 14.
 * 시험 감독 
 * https://www.acmicpc.net/problem/13458
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int B = sc.nextInt(); // 총 감독관이 감시하는 응시자의 수 (B = 1) 
		int C = sc.nextInt(); // 부 감독관이 감시하는 응시자의 수  (C >= 1)
		
		long ans = 0;
		for(int i = 0; i < N; i++) {
			ans += 1;
			int num = arr[i] - B;
			
			if(num < 0) // 학생수가 총감독관이 감시하는 응시자 수보다 작을 경우 
				continue;
			
			if(num % C == 0) {
				ans += num / C;
			} else {
				ans += num / C + 1;
			}
		}
		
		System.out.println(ans);
	}

}