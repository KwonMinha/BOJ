import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2020. 5. 24.
 * 카잉달력
 * https://www.acmicpc.net/problem/6064
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			int ans = 1;
			int x1 = 1;
			int y1 = 1;
			
			while(x1 != x || y1 != y) {
				ans++;
				x1++;
				y1++;
				
				if(x1 >= M && y1 == N)
					break;
				
				if(x1 == M)
					x1 = 0;
				
				if(y1 == N)
					y1 = 0;
			}
			
			if(x == x1 && y == y1)
				System.out.println(ans);
			else
				System.out.println(-1);	
		}
	}

}
