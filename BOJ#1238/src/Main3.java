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

public class Main3 {
	static int INF;
	static int N, X;
	static ArrayList<Element>[] adjList1, adjList2;
	static int[] distance1, distance2;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());		
		int M = Integer.parseInt(st.nextToken());		
		X = Integer.parseInt(st.nextToken());

		INF = 100 * (N-1);

		adjList1 = new ArrayList[N+1]; // 1 : X에서 각 마을까지의 최단 거리를 구하기 위한 인접 리스트 
		adjList2 = new ArrayList[N+1]; // 2 : 주어진 간선을 뒤집어 저장. 즉, 각 마을에서 X까지의 거리를 구하기 위한 인접 리스트 

		distance1 = new int[N+1];
		distance2 = new int[N+1];

		for(int i = 1; i < N+1; i++) {
			adjList1[i] = new ArrayList<>();
			adjList2[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	
			int end = Integer.parseInt(st.nextToken());	
			int t = Integer.parseInt(st.nextToken());	

			adjList1[start].add(new Element(end, t));
			adjList2[end].add(new Element(start, t));
		}

		dijkstra(X, adjList1, distance1); // X마을에서 N개의 학생 집까지의 최단 거리 구하기(X가 고정이기 때문에 가능) 
		dijkstra(X, adjList2, distance2); // N개의 학생 집에서 X마을까지의 최단 거리 구하기

		int time = 0;

		for(int n = 1; n <= N; n++) {
			time = Math.max(time, distance1[n] + distance2[n]);
		}

		System.out.println(time);
	}

	public static void dijkstra(int start, ArrayList<Element>[] adjList, int[] distance) {
		check = new boolean[N+1]; // 정점이 집합 S에 속하는지 아닌지를 판별할 배열 
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
		return Integer.compare(this.distance, o.distance);
	}
}