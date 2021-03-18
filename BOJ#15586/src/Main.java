/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * MooTube (Gold)
 * https://www.acmicpc.net/problem/15586
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int[] parent;
	static int[] count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int Q = sc.nextInt();

		// union-find를 위한 make-set 초기화 
		parent = new int[N+1];
		count = new int[N+1]; // 각 정점이 몇개의 추천 동영상을 가지고 있는지 저장할 배열 
		for(int i = 1; i < N+1; i++) {
			//parent[i] = i;
			parent[i] = i;
			count[i] = 1; // 처음엔 각자 자신만을 가지고 있으니 1로 초기화 
		}

		// 정점 입력 받기 
		ArrayList<Node> nodeList = new ArrayList<>();
		for(int i = 0; i < N-1; i++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int r = sc.nextInt();

			nodeList.add(new Node(p, q, r));
		}
		Collections.sort(nodeList); // 유사도 기준 내림차순 정렬 

		// 유사도 쿼리 입력 받기 
		ArrayList<Query> queryList = new ArrayList<Query>();
		for(int i = 0; i < Q; i++) {
			int k = sc.nextInt(); // 유사도 
			int v = sc.nextInt(); // 시작 정점 

			queryList.add(new Query(i, k, v));
		}
		Collections.sort(queryList); // 유사도 기준 내림차순 정렬 

		int[] result = new int[Q]; // 쿼리 질문에 대한 index별 답변 저장 

		int idx = 0;
		for(Query query : queryList) {
			while(idx < nodeList.size() && nodeList.get(idx).r >= query.k) {
				union(nodeList.get(idx).p, nodeList.get(idx).q); // 두 정점 연결 
				idx++; // 내림차순으로 정렬되어 유사도 확인한 node는 더이상 볼 필요 X 다음 정점으로 감 
			}
			
			// 현재 쿼리에서 물어보는 정점이 몇개의 정점을 가지고 있는지 출력 (자기 자신 -1)
			// find로 현재 정점을 가진 부모 노드를 찾아감 -> 부모 노드가 가진 정점의 개수가 총 개수임 
			result[query.index] = count[find(query.v)] - 1;
		}

		// 쿼리 답변 출력 
		StringBuilder sb = new StringBuilder();
		for(int r : result) {
			sb.append(r + "\n");
		}

		System.out.println(sb.toString());
	}

	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parent[y] = x;
		count[x] += count[y]; // y가 x에 자식으로 붙여졌으니까, x의 count는 기존 x정점에 속하는 정점들의 개수 + y정점에 속하는 정점들의 개수 
	}

}

class Node implements Comparable<Node> {
	int p;
	int q;
	int r;

	Node(int p, int q, int r) {
		this.p = p;
		this.q = q;
		this.r = r;
	}

	@Override
	public int compareTo(Node o) {
		return o.r - this.r;
	}
}

class Query implements Comparable<Query> {
	int index; // 몇 번째 쿼리인지 판별을 위함 
	int k;
	int v;

	Query(int index, int k, int v) {
		this.index = index;
		this.k = k;
		this.v = v;
	}

	@Override
	public int compareTo(Query o) {
		return o.k - this.k;
	}
}
