/**
 * @author Minha Gwon
 * @date 2021. 4. 5.
 * 최대 힙 
 * https://www.acmicpc.net/problem/11279
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {  
			public int compare(Integer w1, Integer w2) {                         
				return w2.compareTo(w1);  
			}      
		}); 

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
