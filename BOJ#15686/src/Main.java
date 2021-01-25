/**
 * @author Minha Gwon
 * @date 2021. 1. 25.
 * 치킨배달 
 * https://www.acmicpc.net/problem/15686
 */

import java.util.ArrayList;
import java.util.Scanner;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static int N, M;
	public static int[][] map; // 전체 도시 정보 저장 
	public static boolean[] visited; // 조합에 사용할 방문 체크 배열 
	public static ArrayList<Point> chickenList; // 전체 치킨집 저장 
	public static ArrayList<Point> homeList;// 전체 집 저장 
	public static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][N];
		chickenList = new ArrayList<>();
		homeList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) {
					chickenList.add(new Point(i, j));
				} else if(map[i][j] == 1) {
					homeList.add(new Point(i, j));
				}
			}
		}
		
		visited = new boolean[chickenList.size()];
		combination(M, 0);
		
		System.out.println(ans);
	}
	
	// 조합을 이용해 총 M개의 치킨집 선택 
	public static void combination(int r, int start) {
		if(r == 0) {
			chickenDest();
			return;
		} else {
			for(int i = start; i < chickenList.size(); i++) {
				visited[i] = true;
				combination(r-1, i+1);
				visited[i] = false;
			}
		}
	}
	
	// 치킨 거리 구하기 
	public static void chickenDest() {
		ArrayList<Point> pickChicken = new ArrayList<>(); // 조합으로 선택된 치킨집 저장 
		for(int i = 0; i < chickenList.size(); i++) {
			if(visited[i]) 
				pickChicken.add(new Point(chickenList.get(i).x, chickenList.get(i).y));
		}
		
		int sumDest = 0; // 치킨 거리 총합 
		for(int i = 0; i < homeList.size(); i++) {
			int hx = homeList.get(i).x;
			int hy = homeList.get(i).y;
			
			int minDest = Integer.MAX_VALUE; // 최소 치킨 거리 
			
			for(int j = 0; j < pickChicken.size(); j++) {
				int cx = pickChicken.get(j).x;
				int cy = pickChicken.get(j).y;
				
				int dest = Math.abs(hx-cx) + Math.abs(hy-cy); // 치킨 거리 
				minDest = Math.min(minDest, dest);
			}
			sumDest += minDest;
		}
		
		ans = Math.min(ans, sumDest);
	}
}