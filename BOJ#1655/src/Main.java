/**
 * @author Minha Gwon
 * @date 2021. 6. 29.
 * 가운데를 말해요
 * https://www.acmicpc.net/problem/1655
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = sc.nextInt();

		// 왼쪽 우선순위 큐 - 오른쪽 보다 작은 숫자 가짐 -> 가장 큰 숫자가 중간값 
		// 왼쪽에서 가장 큰 숫자 peek 해야하기 때문에 reverse
		PriorityQueue<Integer> leftPq = new PriorityQueue<>(Collections.reverseOrder()); 

		// 오른쪽 우선순위 큐 - 왼쪽보다 큰 숫자 가짐 
		PriorityQueue<Integer> rightPq = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			int n = sc.nextInt();

			if(leftPq.isEmpty()) {
				leftPq.add(n);
			} else {
				if(leftPq.peek() >= n) {
					leftPq.add(n);
				} else if(rightPq.isEmpty()) {
					rightPq.add(n);
				} else if(rightPq.peek() >= n) {
					leftPq.add(n);
				} else {
					rightPq.add(n);
				}

				if(leftPq.size() - rightPq.size() == -1) {
					leftPq.add(rightPq.poll());
				}

				if(leftPq.size() - rightPq.size() == 2) {
					rightPq.add(leftPq.poll());
				}
			}

			// 중간값은 왼쪽 큐의 가장 앞에 있는 값 
			sb.append(leftPq.peek()).append("\n");
		}

		System.out.println(sb);
	}

}
