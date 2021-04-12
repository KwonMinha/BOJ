/**
 * @author Minha Gwon
 * @date 2021. 4. 13.
 * 파티 
 * https://www.acmicpc.net/problem/1238
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	static int INF;
	static int N, X;
	static ArrayList<Element>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());		
		int M = Integer.parseInt(st.nextToken());		
		X = Integer.parseInt(st.nextToken());

		INF = 100 * (N-1);

		adjList = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	
			int end = Integer.parseInt(st.nextToken());	
			int t = Integer.parseInt(st.nextToken());	

			adjList[start].add(new Element(end, t));
		}

		int time = 0;

		for(int n = 1; n <= N; n++) {
			time = Math.max(time, dijkstra(n, X) + dijkstra(X, n));
		}

		System.out.println(time);

	}

	public static int dijkstra(int start, int end) {
		boolean[] check = new boolean[N+1]; // 정점이 집합 S에 속하는지 아닌지를 판별할 배열 
		int[] distance = new int[N+1]; // 최단 거리를 담을 배열 
		Arrays.fill(distance, INF); // 무한대로 초기화 

		distance[start] = 0;

		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.offer(new Element(start, 0));

		while(!pq.isEmpty()) {
			int current = pq.poll().index;

			if(check[current]) continue;
			check[current] = true;

			for(Element next : adjList[current]) {
				if(distance[next.index] > distance[current] + next.distance) {
					distance[next.index] = distance[current] + next.distance;
					pq.offer(new Element(next.index, distance[next.index]));
				}
			}
		}

		return distance[end];
	}

}

//class Element implements Comparable<Element> {
//	int index;
//	int distance;
//
//	Element(int index, int distance) {
//		this.index = index;
//		this.distance = distance;
//	}
//
//	@Override
//	public int compareTo(Element o) {
//		//return this.distance <= o.distance ? -1 : 1;
//		return Integer.compare(this.distance, o.distance);
//
//		/*
//		 Integer.compare(int x, int y)
//		 x == y ->  0 return 
//		 x < y  ->  -1 return 
//		 x > y  ->  1 return 
//		 */
//	}
//} 