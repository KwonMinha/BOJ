/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 특정한 최단 경로 
 * https://www.acmicpc.net/problem/1504
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static ArrayList<Element>[] adjList;
	static int N;
	static int INF;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int E = sc.nextInt();

		// 인접 리스트 구현 
		adjList = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();

			adjList[a].add(new Element(b, c));
			adjList[b].add(new Element(a, c));
		}

		int v1 = sc.nextInt();
		int v2 = sc.nextInt();
		
		INF = 10000 * (N-1) + 1;
		
		int result1 = 0;
		int result2 = 0;
		
		// 시작 정점 1 -> v1 -> v2 -> 도착 정점 N
		result1 += dijkstra(1, v1);
		result1 += dijkstra(v1, v2);
		result1 += dijkstra(v2, N);
		

		// 시작 정점 1 -> v2 -> v1 -> 도착 정점 N
		result2 += dijkstra(1, v2);
		result2 += dijkstra(v2, v1);
		result2 += dijkstra(v1, N);
		
		int answer = Math.min(result1, result2);
		System.out.println(answer >= INF ? -1 : answer);
	}

	public static int dijkstra(int start, int end) {
		boolean[] check = new boolean[N+1];

		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
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

class Element implements Comparable<Element> {
	int index;
	int distance;

	Element(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Element o) {
		//return this.distance <= o.distance ? -1 : 1;
		return Integer.compare(this.distance, o.distance);

		/*
		 Integer.compare(int x, int y)
		 x == y ->  0 return 
		 x < y  ->  -1 return 
		 x > y  ->  1 return 
		 */
	}
}