/**
 * @author Minha Gwon
 * @date 2021. 3. 26.
 * 플로이드 
 * https://www.acmicpc.net/problem/11404 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int INF = 10000000; // 간선 가중치의 최댓값 * 정점 개수
		
		// 플로이드 와샬 알고리즘을 적용하기 위한 2차원 배열 map 초기화 
		int[][] map = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j)
					map[i][j] = 0; // 자기 자신으로 가는 비용은 0 
				else 
					map[i][j] = INF; // 아직 비용을 모르니 무한대로 초기화 
			}
		}

		// 비용 저장 
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if(map[a][b] > c) { // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있음 
				map[a][b] = c;
			}
		}

		for(int k = 1; k <= n; k++) { // 거쳐가는 노드 
			for(int i = 1; i <= n; i++) { // 출발 노드 
				for(int j = 1; j <= n; j++) { // 도착 노드 
					//i에서 k를 거쳤다가 k에서 j 까지 가는 비용과 i에서 j 까지 가는 비용을 비교해서 더 작은 값이 최소 비용 거리 
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(map[i][j] == INF) {
					sb.append("0 ");
				} else {
					sb.append(map[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}