/**
 * @author Minha Gwon
 * @date 2021. 3. 26.
 * 숨바꼭질 4
 * https://www.acmicpc.net/problem/13913
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		int min = Integer.MAX_VALUE;

		int[] parent = new int[100001];

		boolean[] visited = new boolean[100001];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(N, 0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();

			if(node.n == K) { 
				min = node.t;
				break;
			}

			if(node.n * 2 < 100001 && !visited[node.n * 2]) {
				parent[node.n*2] = node.n;
				visited[node.n*2] = true;
				queue.add(new Node(node.n * 2, node.t + 1));
			}

			if(node.n + 1 < 100001 && !visited[node.n + 1]) {
				parent[node.n+1] = node.n;
				visited[node.n+1] = true;
				queue.add(new Node(node.n + 1, node.t + 1));
			}

			if(node.n - 1 >= 0 && !visited[node.n - 1]) {
				parent[node.n-1] = node.n;
				visited[node.n-1] = true;
				queue.add(new Node(node.n - 1, node.t + 1));
			}
		}

		System.out.println(min);

		Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;
        while (index != N) {
            stack.push(parent[index]);
            index = parent[index];
        }

        while (!stack.isEmpty()) {
        	System.out.print(stack.pop() + " ");
        }
	}

}

class Node {
	int n;
	int t;

	Node(int n, int t) {
		this.n = n;
		this.t = t;
	}
}