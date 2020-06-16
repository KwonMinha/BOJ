/**
 * @author Minha Gwon
 * @date 2020. 6. 16.
 * 치킨 배달 
 * - 재귀로 조합 구현 
 * - 집은 2차원 배열로, 치킨 가게는 LinkedList로 관리 
 * https://www.acmicpc.net/problem/15686
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static LinkedList<Chicken> cList;
	public static int cCnt;
	public static ArrayList<Integer> cCheck;
	public static boolean[] visited;
	public static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		cList = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) {
					cList.add(new Chicken(i, j));
				}
			}
		}
		cCnt = cList.size();
		visited = new boolean[cCnt];
		pick(0, M);
		System.out.println(ans);
	}
	
	//재귀를 이용한 조합으로 M개의 치킨 가게 고름 
	public static void pick(int depth, int r) {
		if(r == 0) {
			cCheck = new ArrayList<>();
			for(int i = 0; i < cList.size(); i++) {
				if(visited[i] == true) 
					cCheck.add(i);
			}
			getDist();
			
			return;
		}
		
		if(depth == cCnt) {
			return;
		} else {
			visited[depth] = true;
			pick(depth+1, r-1);
			visited[depth] = false;
			pick(depth+1, r);
		}
	}
	
	public static void getDist() {
		int res = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					int min = Integer.MAX_VALUE;
					for(int k = 0; k < cCheck.size(); k++) {
						int cx = cList.get(cCheck.get(k)).cx;
						int cy = cList.get(cCheck.get(k)).cy;
						int dist = Math.abs(i - cx) + Math.abs(j - cy);
						min = Math.min(min, dist);
					}
					res += min;
				}
			}
		}
		ans = Math.min(res, ans);
	}
	
}

class Chicken {
	int cx, cy;
	
	Chicken(int cx, int cy) {
		this.cx = cx;
		this.cy = cy;
	}
}
