/**
 * @author Minha Gwon
 * @date 2021. 1. 26.
 * N번째 큰 수
 * https://www.acmicpc.net/problem/2075 
 */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int a = sc.nextInt();
				pq.add(a);
			}
		}
	
		while(N != 1) {
			pq.poll();
			N--;
		}
		
		System.out.println(pq.poll());
	}

}
