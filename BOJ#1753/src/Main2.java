/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 최단경로 
 * https://www.acmicpc.net/problem/1753
 * 인접 리스트와 우선순위 큐를 사용해 다익스트라 알고리즘 구현 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		int E = sc.nextInt();

		int start = sc.nextInt(); // 시작 정점 

		// 인접 리스트 구현 
		ArrayList<Element>[] adjList = new ArrayList[V+1];
		for(int i = 1; i < V+1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();

			adjList[u].add(new Element(v, w));
		}

		boolean[] check = new boolean[V+1];

		int[] distance = new int[V+1];
		int INF = 10 * (V-1) + 1; // INF는 항상 (간선 가중치의 최댓값) * (정점 개수 - 1) 보다 큰 값을 사용해야 한다.
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

		for(int i = 1; i < V+1; i++) {
			if(distance[i] ==  INF)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
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

