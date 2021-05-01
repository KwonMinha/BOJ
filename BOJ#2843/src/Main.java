/**
 * @author Minha Gwon
 * @date 2021. 4. 28.
 * 마블 
 * https://www.acmicpc.net/problem/2843
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] tree;
	static int[] parent;
	static boolean[] cycle;
	static boolean[] isRemove;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		tree = new int[N+1];
		parent = new int[N+1];
		cycle = new boolean[N+1];
		isRemove = new boolean[N+1];

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());;
			parent[i] = i;
		}

		int Q = Integer.parseInt(br.readLine());
		Stack<int[]> queryStack = new Stack<>();
		Stack<String> answerStack = new Stack<>();

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if(type == 2) {
				isRemove[num] = true;
			}
			
			queryStack.push(new int[] {type, num});
		}
		
		for(int i = 1; i <= N; i++) {
			if(!isRemove[i]) {
				union(i, tree[i]);
			}
		}

		while(!queryStack.isEmpty()) {
			int[] query = queryStack.pop();
			
			if(query[0] == 1) { // 조약돌을 정점 X에 놓았을 때, 조약돌이 무한히 움직이지 않는다면 조약돌이 멈추는 정점의 번호는 몇 번인가?
				int v = find(query[1]);
				if(cycle[v] == true) { // 사이클인 경우 
					answerStack.push("CIKLUS\n");
				} else { // 사이클이 아닌 경우 
					answerStack.push(v + "\n");
				}
			} else { // 정점 X에서 나가는 간선을 지운다. (항상 나가는 간선이 있는 정점만 주어진다) = 정점 X와 정점 X에서 나가는 간선 잇는 것과 같음 
				union(query[1], tree[query[1]]);
			}
		}

		StringBuilder sb = new StringBuilder();
		
		while(!answerStack.isEmpty()) {
			sb.append(answerStack.pop());
		}

		System.out.println(sb);
	}

	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}
	
	public static void union(int a, int b) {
		if(a == 0 || b == 0) { // 나가는 간선이 없는 경우에는 0이 주어진다.
			return;
		}
		
		int x = find(a);
		int y = find(b);
		
		if(x == y) { // 이미 이어진 경우라면? -> 사이클임
			cycle[y] = true; // 사이클임을 표시 
		} else { // 사이클이 아니라면 
			parent[x] = y; // 이어줌 
		}
	}

}