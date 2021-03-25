/**
 * @author Minha Gwon
 * @date 2021. 3. 26.
 * 숨바꼭질 3 
 * https://www.acmicpc.net/problem/13549
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int min = Integer.MAX_VALUE;
		
		boolean[] visited = new boolean[100001];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(N, 0));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			visited[node.n] = true;
			
			if(node.n == K) // 동생의 위치에 도달한 경우 
				min = Math.min(min, node.t); // n-1, n+1, n*2로부터 온 시간 중 가장 작은 값으로 갱신 
		
			if(node.n * 2 < 100001 && !visited[node.n * 2]) 
				queue.add(new Node(node.n * 2, node.t));
			
			if(node.n - 1 >= 0 && !visited[node.n - 1]) 
				queue.add(new Node(node.n - 1, node.t + 1));
			
			if(node.n + 1 < 100001 && !visited[node.n + 1]) 
				queue.add(new Node(node.n + 1, node.t + 1));
		}

		System.out.println(min);
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