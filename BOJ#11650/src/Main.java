/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 좌표 정렬하기 
 * https://www.acmicpc.net/problem/11650
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] arr = new int[N][2];
		
		//ArrayList<Point> pointList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
//			pointList.add(new Point(x, y));
			
			arr[i][0] = x;
			arr[i][1] = y;
		}
		
//		Collections.sort(pointList);
//		
//		for(Point p : pointList) {
//			System.out.println(p.x + " " + p.y);
//		}
		
		// Arrays.sort 람다식으로 표현 
		Arrays.sort(arr, (e1, e2) -> {
			if(e1[0] == e2[0]) {
				return e1[1] - e2[1];
			} else {
				return e1[0] - e2[0];
			}
		});
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
	}

}

class Point implements Comparable<Point> {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if(this.x == o.x) {
			return this.y - o.y;
		} else { 
			return this.x - o.x;
		}
	}
}