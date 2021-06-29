/**
 * @author Minha Gwon
 * @date 2021. 6. 29.
 * 가운데를 말해요
 * https://www.acmicpc.net/problem/1655
 */

import java.util.*;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = sc.nextInt();
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); 
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			int n = sc.nextInt();

			if(maxHeap.size() == minHeap.size()) {
				maxHeap.add(n);
			} else {
				minHeap.add(n);
			}
			
			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if(maxHeap.peek() > minHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.add(minHeap.poll());
					minHeap.add(temp);
				}
			}
			
			sb.append(maxHeap.peek()).append("\n");
		}

		System.out.println(sb);
	}

}
