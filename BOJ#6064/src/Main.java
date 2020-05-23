/**
 * @author Minha Gwon
 * @date 2020. 5. 24.
 * 카잉달력
 * https://www.acmicpc.net/problem/6064
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			int ans = x;
			int x1 = x;
			int y1 = x;
			int cnt = 1;
			
			if(y1 > N) {
				while(y1 > N)
					y1 -= N;
			}
			
			while(x1 != x || y1 != y) {
				if(ans > M*N) {
					ans = -1;
					break;
				}
				cnt++;
				ans += M;
				y1 += M;
				
				if(y1 > N) {
					while(y1 > N)
						y1 -= N;
				}
			}
			System.out.println(ans);	
		}
	}

}
