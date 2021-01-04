import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2021. 1. 4.
 * LCA
 * https://www.acmicpc.net/problem/11437
 */

class Node {
	int data;
	Node parent;

	Node(int data) {
		this.data = data;
	}
}

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static boolean[] visited;
	public static int tempCur, tempParent, tempDepth, answer = 0;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		adjList = new LinkedList[N+1];
		visited = new boolean[N+1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			adjList[a].add(b);
			adjList[b].add(a);
		}

		int M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			Arrays.fill(visited, false);
			dfs(1, v1, -1, 0);

			Arrays.fill(visited, false);
			dfs(1, v2, -1, 0);

			System.out.println(answer);
			tempParent = 0;
			tempDepth = 0;
			answer = 0;
			System.out.println("----------------");
		}
	}

	public static void dfs(int cur, int data, int parent, int depth) {
		if(cur == data) {
			parent(data, depth, parent);
			System.out.println("d : " + depth + ", p : " + parent);
			return;
		} 

		visited[cur] = true;
		depth++;
		for(int next : adjList[cur]) {
			if(!visited[next]) {
				dfs(next, data, cur, depth);
			}
		}
	}

	public static void parent(int cur, int depth, int parent) {
		if(tempDepth == 0 && tempParent == 0) {
			tempDepth = depth;
			tempParent = parent;
			tempCur = cur;
		} else {
			System.out.println(tempDepth + " " + tempParent + " " + depth + " " + parent);
			if(tempDepth < depth) {
				if(tempCur == parent) {
					System.out.println("t1 cur : " + cur + ", tempCur : " + tempCur);
					answer = tempCur;
				} else {
					answer = tempParent;
				}
			} else if(tempDepth == depth) { 
				if(tempParent == parent) {
					answer = parent;
				} else {
					
				}
				
			} else {
				if(cur == tempParent) {
					System.out.println("t2 cur : " + cur + ", tempCur : " + tempCur);
					answer = cur;
				} else {
					answer = parent;
				}
			}
		}
		System.out.println();
	}

}
