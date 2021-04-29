/**
 * @author Minha Gwon
 * @date 2021. 4. 28.
 * 마블 
 * https://www.acmicpc.net/problem/2843
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] tree;
	static int[] parent;
	static boolean[] cycle;

	static ArrayList<Integer> cycleList;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());


		tree = new int[N+1];
		parent = new int[N+1];
		cycle = new boolean[N+1];

		st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= N; i++) {
			int end = Integer.parseInt(st.nextToken());

			tree[i] = end;

			parent[i] = i;
		}

		int Q = Integer.parseInt(br.readLine());
		Stack<String> queryStack = new Stack<>();
		Stack<String> answerStack = new Stack<>();
		
		cycleList = new ArrayList<>();

		for(int i = 0; i < Q; i++) {
			queryStack.push(br.readLine());
		}

		while(!queryStack.isEmpty()) {
			st = new StringTokenizer(queryStack.pop());

			String query = st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			
		

			if(query.equals("1")) { // 조약돌을 정점 X에 놓았을 때, 조약돌이 무한히 움직이지 않는다면 조약돌이 멈추는 정점의 번호는 몇 번인가?
				if(cycle[x] == true) { // 사이클인 경우 
					answerStack.push("CIKLUS\n");
				} else { // 사이클이 아닌 경우 
					answerStack.push(find(x) + "\n");
				}
			} else { // 정점 X에서 나가는 간선을 지운다. (항상 나가는 간선이 있는 정점만 주어진다) = 정점 X와 정점 X에서 나가는 간선 잇는 것과 같음 
				union(x, tree[x]);
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
		int x = find(a);
		int y = find(b);
		
		if(x == y) { // 이미 이어진 경우라면? -> 사이클임
			cycle[y] = true; // 사이클임을 표시 
		} else { // 사이클이 아니라면 
			parent[y] = x; // 이어줌 
		}
	}

}