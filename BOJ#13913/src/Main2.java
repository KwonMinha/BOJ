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

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		int[] time = new int[100001];
		int[] parent = new int[100001];

		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		time[N] = 1;

		while(!queue.isEmpty()) {
			int cur = queue.poll();

			if(cur == K) 
				break;

			int[] next = {cur + 1, cur - 1, cur * 2};

			for(int i = 0; i < 3; i++) {
				if(next[i] < 0 || next[i] > 100000) continue;

				if(time[next[i]] == 0) {
					time[next[i]] = time[cur] + 1;
					parent[next[i]] = cur;
					queue.add(next[i]);
				}

			}

			/*
			for (int i = 0; i < 3; i++) {
				int next;

				if (i == 0)         next = cur + 1;
				else if (i == 1)    next = cur - 1;
				else                next = cur * 2;

				if (next < 0 || next > 100000) continue;

				if (time[next] == 0) {
					queue.add(next);
					time[next] = time[cur] + 1;
					parent[next] = cur;
				}
			}
		    */
		}

		StringBuilder sb = new StringBuilder();
		sb.append(time[K]-1 + "\n");

		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		int index = K;
		while (index != N) {
			stack.push(parent[index]);
			index = parent[index];
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}

		System.out.println(sb);
	}

}