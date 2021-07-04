/**
 * @author Minha Gwon
 * @date 2021. 7. 4.
 * 웜홀
 * https://www.acmicpc.net/problem/1865
 */

import java.util.*;
import java.io.*;

public class Main {
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> edgeList = new ArrayList<>();
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Edge(S, E, T));
				edgeList.add(new Edge(E, S, T));
			}
			
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Edge(S, E, T * (-1)));
			}
			
			int[] dist = new int[N+1];
			Arrays.fill(dist, INF);
			dist[1] = 0;
			
			boolean isUpdate = false;
			
			loop:
			for(int i = 1; i <= N; i++) {
				isUpdate = false;
				
				for(Edge edge : edgeList) {
					if(dist[edge.end] > dist[edge.start] + edge.time) {
						dist[edge.end] = dist[edge.start] + edge.time;
						isUpdate = true;
						
						// Negative Cycle
						if(i == N) {
							break loop;
						}
					}
				}
				
				if(!isUpdate) {
					break;
				}
			}
			
			sb.append(isUpdate ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb);
	}

}

class Edge {
	int start;
	int end;
	int time;

	Edge(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}
