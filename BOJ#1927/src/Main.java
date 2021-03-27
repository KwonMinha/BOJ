/**
 * @author Minha Gwon
 * @date 2021. 3. 27.
 * 최소 힙 
 * https://www.acmicpc.net/problem/1927
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			int X = sc.nextInt();
			
			if(X != 0) {
				pq.add(X);
			} else {
				if(pq.isEmpty()) {
					sb.append(0 + "\n");
				} else {
					sb.append(pq.poll() + "\n");
				}
			}
		}
		
		System.out.println(sb);
	}

}