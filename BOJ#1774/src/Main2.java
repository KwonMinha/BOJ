/**
 * @author Minha Gwon
 * @date 2021. 7. 27.
 * 우주신과의 교감
 * https://www.acmicpc.net/problem/1774
 * 9% 실패 - 주어진 두 간선 union 하지 않고 0이나 음의 가중치 주면 암됨  
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {
	static int N, M;
	static int[] parent;
	static double result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		parent = new int[N];

		ArrayList<Node> pointList = new ArrayList<>();
		ArrayList<Node> nodeList = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			pointList.add(new Node(x, y, 0));
		}

		for(int i = 0; i < M; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;

			union(x, y);
		}

		for(int i = 0; i < N-1; i++) {
			Node n1 = pointList.get(i);

			for(int j = i+1; j < N; j++) {
				Node n2 = pointList.get(j);

				double weight = Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));

				nodeList.add(new Node(i, j, weight));
			}
		}

		Collections.sort(nodeList);

		for(int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.get(i);

			int start = node.x;
			int end = node.y;

			int a = find(start);
			int b = find(end);

			if(a == b) continue;

			union(start, end);

			result += node.w;
		}

		System.out.println(String.format("%.2f", result));
	}

	public static int find(int a) {
		if(a == parent[a]) {
			return a;
		}

		//parent[a] = find(parent[a]);
		//return parent[a]
		
		return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		//if(a != b) parent[a] = b;
		//else return;

		//if (a < b) parent[b] = a;
		//else parent[a] = b;

		parent[b] = a;
	}

}