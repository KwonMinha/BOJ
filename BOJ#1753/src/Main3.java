/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * 최단경로 
 * https://www.acmicpc.net/problem/1753
 * 인접 리스트 사용 & 우선순위 큐를 사용하지 않고 다익스트라 알고리즘 구현
 * Scanner 쓰면 시간 초과남 (역시 우선순위 큐보다는 느려서 그런가...?)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {

	public static void main(String[] args) throws IOException {    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());   
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine()); 

		// 인접 리스트 구현 
		ArrayList<Element>[] adjList = new ArrayList[V+1];
		for(int i = 1; i < V+1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());   
			int v = Integer.parseInt(st.nextToken());   
			int w = Integer.parseInt(st.nextToken());

			adjList[u].add(new Element(v, w));
		}

		boolean[] check = new boolean[V+1];

		int[] distance = new int[V+1];
		int INF = 10 * (V-1) + 1; // INF는 항상 (간선 가중치의 최댓값) * (정점 개수 - 1) 보다 큰 값을 사용해야 한다.
		Arrays.fill(distance, INF);
		distance[start] = 0;

		for(int i = 0; i < V-1; i++) {
			int min = Integer.MAX_VALUE;
			int current = -1;

			for(int j = 1; j < V+1; j++) {
				if(!check[j] && distance[j] < min) {
					min = distance[j];
					current = j;
				}
			}

			check[current] = true;

			for(Element next : adjList[current]) {
				if(!check[next.index] && distance[current] + next.distance < distance[next.index]) {
					distance[next.index] = distance[current] + next.distance;
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

/*
class Element implements Comparable<Element> {
	int index;
	int distance;

	Element(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Element o) {
		return this.distance <= o.distance ? -1 : 1;
		//return Integer.compare(this.distance, o.distance);
	}
}
 */
