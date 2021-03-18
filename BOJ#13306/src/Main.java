/**
 * @author Minha Gwon
 * @date 2021. 3. 18.
 * 트리
 * https://www.acmicpc.net/problem/13306
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int[] tree;
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int Q = sc.nextInt();
		
		tree = new int[N+1]; // 전체 트리, 자식 - 부모 관계로 저장 
		parent = new int[N+1]; // union find를 위한 부모 노드 배열 
		
		tree[1] = parent[1] = 1; // 루트 노드 1 
		
		// 정점 입력받기 
		for(int i = 2; i < N+1; i++) {
			int a = sc.nextInt();
			tree[i] = a;
			parent[i] = i;
		}
		
		// 질의 저장 
		Stack<int[]> query = new Stack<>(); // offline query로 역순서로 처리할 것이기에 stack 사용 
		for(int i = 0; i < N-1+Q; i++) {
			int x = sc.nextInt();
			
			if(x == 0) { // (1) 질의 
				int b = sc.nextInt();
				query.push(new int[] {b, 0});
			} else { // (2) 질의 
				int c = sc.nextInt();
				int d = sc.nextInt();
				query.push(new int[] {c, d});
			}
		}
		
		Stack<String> ans = new Stack<>();
		
		while(!query.isEmpty()) {
			int[] q = query.pop();
			
			if(q[1] == 0) { // (1) q[0]의 부모 정점과 q[0]을 연결하는 에지를 제거함 - 하지만 offline query로 처리할 것이기에 - (1) 두 정점의 간선을 생성한 뒤 (잇는다)
				// union
				parent[q[0]] = tree[q[0]];
			} else { // (2) q[0]과 q[1]을 연결하는 경로가 존재하는 지 묻는 질의 - (2) 연결성을 판별할 것 (두 정점이 같은 집합 내에 있는지 판별) 
				// find 
				if(find(q[0]) == find(q[1])) {
					ans.push("YES"); 
				} else {
					ans.push("NO");
				}
				
				//ans.push(find(q[0]) == find(q[1]) ? "YES" : "NO");
			}
		}
		
		while(!ans.isEmpty()) { // 쿼리도 역순으로 처리했으니, 답 출력도 역순으로 
			System.out.println(ans.pop());
		}
	}
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return find(parent[x]);
		
		//return p[x] == x ? x : find(p[x]);
	}

}
