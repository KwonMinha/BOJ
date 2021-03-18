/**
 * @author Minha Gwon
 * @date 2021. 3. 18.
 * 트리
 * https://www.acmicpc.net/problem/13306
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2 {
	static int[] tree;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		tree = new int[N+1]; // 전체 트리, 자식 - 부모 관계로 저장 
		parent = new int[N+1]; // union find를 위한 부모 노드 배열 
		
		tree[1] = parent[1] = 1; // 루트 노드 1 
		
		// 정점 입력받기 
		for(int i = 2; i < N+1; i++) {
			int a = Integer.parseInt(br.readLine());
			tree[i] = a;
			parent[i] = i;
		}
		
		// 질의 저장 
		Stack<String> query = new Stack<>(); // offline query로 역순서로 처리할 것이기에 stack 사용 
		for(int i = 0; i < N-1+Q; i++)
			query.push(br.readLine());
		
		Stack<String> ans = new Stack<>();
		while(!query.isEmpty()) {
			st = new StringTokenizer(query.pop());
			
			if(Integer.parseInt(st.nextToken()) == 0) { // (1) q[0]의 부모 정점과 q[0]을 연결하는 에지를 제거함 - 하지만 offline query로 처리할 것이기에 - (1) 두 정점의 간선을 생성한 뒤 (잇는다)
				// union
				int b = Integer.parseInt(st.nextToken());
				parent[b] = tree[b];
			} else { // (2) q[0]과 q[1]을 연결하는 경로가 존재하는 지 묻는 질의 - (2) 연결성을 판별할 것 (두 정점이 같은 집합 내에 있는지 판별) 
				// find 
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				ans.push(find(c) == find(d) ? "YES\n" : "NO\n");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!ans.isEmpty())  // 쿼리도 역순으로 처리했으니, 답 출력도 역순으로 
			sb.append(ans.pop());
		
		System.out.println(sb.toString());
	}
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}

}
