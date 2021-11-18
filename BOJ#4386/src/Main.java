/**
 * @author Minha Gwon
 * @date 2021. 7. 27.
 * 별자리 만들기 
 * https://www.acmicpc.net/problem/4386
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int N;
	static ArrayList<Node> starList;
	static int[] parent;
	static double answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		Node[] star = new Node[N];
		parent = new int[N];
		starList = new ArrayList<>();

		// 각 별 좌표 저장 
		for(int i = 0; i < N; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
	
			star[i] = new Node(x, y, 0);
		}
		
		// Union-Find를 위한 parent 배열 초기화 - 초기에 각 별의 부모는 자기 자신 
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}

		// 각 별간의 가중치를 구해 간선 잇기 
		for(int i = 0; i < N-1; i++) {
			Node n1 = star[i];

			for(int j = i+1; j < N; j++) {
				Node n2 = star[j];

				double weight = Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
				starList.add(new Node(i, j, weight));
			}
		}
		
		// 최소 비용 오름차순 정렬 
		Collections.sort(starList);
		
		// Union-Find를 이용해 Kruskal 탐색 
		for(int i = 0; i < starList.size(); i++) {
			Node node = starList.get(i);
			
			int start = (int) node.x;
			int end = (int) node.y;
			
			
			if(find(start) != find(end)) {
				union(start, end);
				answer += node.w;
			}
			
//			int a = find(start);
//			int b = find(end);
//			
//			if(a != b) {
//				union(start, end);
//				answer += node.w;
//			}
		}
		
		System.out.println(String.format("%.2f", answer));
	}

	static int find(int a) {
		if(a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parent[b] = a;
	}

}

class Node implements Comparable<Node> {
	double x;
	double y;
	double w;

	Node(double x, double y, double w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return o.w >= this.w ? -1 : 1;
	}

}