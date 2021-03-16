/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 소가 길을 건너간 이유 6
 * https://www.acmicpc.net/problem/14466
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Street> streetList = new ArrayList<>();
	static ArrayList<Integer>[] adjList;
	static int[][] map;
	static boolean[] visited;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int K = sc.nextInt();
		int R = sc.nextInt();
		
		map = new int[N+1][N+1];
		
		adjList = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < R; i++) {
			int r1 = sc.nextInt();
			int c1 = sc.nextInt();
			int r2 = sc.nextInt();
			int c2 = sc.nextInt();
			
			streetList.add(new Street(r1, c1, r2, c2));
		}
		
		for(int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			map[x][y] = i+1;
		}
		
		for(int i = 0; i < streetList.size(); i++) {
			Street st = streetList.get(i);
			int v1 = map[st.r1][st.c1];
			int v2 = map[st.r2][st.c2];
			
			if(v1 != 0 && v2 != 0) {
				adjList[v1].add(v2);
				adjList[v2].add(v1);
			}
		}
		
		int ans = 0;
		
		for(int i = K; i > 0; i--) {
			for(int j = i-1; j > 0; j--) {
				int v1 = i;
				int v2 = j;
				
				visited = new boolean[N+1];
				
				boolean flag = false;
				for(int k = 0; k < adjList[v1].size(); k++) {
					if(adjList[v1].get(k) == v2) {
						flag = true;
						break;
					}
				}
				
				if(!flag)
					ans += 2;
			}
		}
		
		System.out.println(ans);
	}

}

class Street {
	int r1;
	int c1;
	int r2;
	int c2;
	
	Street(int r1, int c1, int r2, int c2) {
		this.r1 = r1;
		this.c1 = c1;
		this.r2 = r2;
		this.c2 = c2;
	}
}
