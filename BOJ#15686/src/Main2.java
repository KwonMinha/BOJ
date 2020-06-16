/**
 * @author Minha Gwon
 * @date 2020. 6. 16.
 * 치킨 배달
 *  - 백트래킹으로 조합 구현 
 *  - 집과, 치킨 가게 모두 x, y값을 저장하는 Point 클래스를 만들고 이를 자료형으로 갖는 ArrayList로 관리 
 * https://www.acmicpc.net/problem/15686
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main2 {
	public static int N, M;
	public static ArrayList<Point> chicken;
	public static ArrayList<Point> home;
	public static ArrayList<Point> pickChicken;
	public static boolean[] visited;
	public static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int val = sc.nextInt();
				if(val == 2) {
					chicken.add(new Point(i, j));
				} else if(val == 1) {
					home.add(new Point(i, j));
				}
			}
		}
		visited = new boolean[chicken.size()];
		pick(0, M);
		System.out.println(ans);
	}
	
	//백트래킹을 이용한 조합으로 M개의 치킨 가게 고름 
	public static void pick(int start, int r) {
		if(r == 0) {
			pickChicken = new ArrayList<>();
			for(int i = 0; i < chicken.size(); i++) {
				if(visited[i] == true)
					pickChicken.add(chicken.get(i));
			}
			getDist();
			
			return;
		} else {
			for(int i = start; i < chicken.size(); i++) {
				visited[i] = true;
				pick(i + 1, r - 1);
				visited[i] = false;
			}
		}
	}
	
	public static void getDist() {
		int res = 0;
		for(Point h : home) {
			int min = Integer.MAX_VALUE;
			for(Point c : pickChicken) {
				int dist = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
				min = Math.min(min, dist);
			}
			res += min;
		}
		ans = Math.min(res, ans);
	}
	
}

class Point {
	int x, y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
