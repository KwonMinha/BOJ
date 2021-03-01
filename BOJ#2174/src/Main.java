import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2021. 3. 1.
 * 로봇 시뮬레이션 
 * https://www.acmicpc.net/problem/2174
 */

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int A, B;
	static ArrayList<Point> robotList = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			String dir = sc.next();
			
			robotList.add(new Point(x, y, dir));
			System.out.println(x + " " + y + " " + dir);
		}
		
		System.out.println();
		
		for(int i = 0; i < M; i++) {
			int orderRobot = sc.nextInt();
			String order = sc.next();
			int orderCnt = sc.nextInt();
			
			System.out.println(orderRobot + " " + order + " " + orderCnt);
		}
	}

}

class Point {
	int x;
	int y;
	String dir;

	Point(int x, int y, String dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
