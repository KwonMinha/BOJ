/**
 * @author Minha Gwon
 * @date 2021. 4. 5.
 * 절대값 힙
 * https://www.acmicpc.net/problem/11286
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {  
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.absNum > o2.absNum) {
					return 1; // 절대값에 대해서는 오름차순 정렬 
				} else if(o1.absNum == o2.absNum) {
					if(o1.originNum > o2.originNum) { // 절대값이 같다면 기본값에 대해 오름차순 정렬 
						return 1;
					}
				}
				
				return -1;
			}      
		}); 
		
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < N; i++) {
			int originNum = sc.nextInt();
			int absNum = Math.abs(originNum);

			if(originNum != 0) {
				pq.add(new Node(originNum, absNum));
			} else {
				if(pq.isEmpty()) {
					sb.append(0 + "\n");
				} else {
					sb.append(pq.poll().originNum + "\n");
				}
			}
		}

		System.out.println(sb);
	}

}

class Node {
	int originNum;
	int absNum;
	
	Node(int originNum, int absNum) {
		this.originNum = originNum;
		this.absNum = absNum;
	}
}