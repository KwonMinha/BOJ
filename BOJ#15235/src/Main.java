/**
 * @author Minha Gwon
 * @date 2021. 9. 10.
 * Olympiad Pizza
 * https://www.acmicpc.net/problem/15235
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Queue<Pizza> queue = new LinkedList<>();
		
		int[] answer = new int[N];
		
		for(int i = 0; i < N; i++) {
			int time = sc.nextInt();
			queue.add(new Pizza(i, time));
			
		}
		
		int limit = N;
		
		int time = 0;
		while(limit > 0) {
			time += 1;
			
			Pizza pizza = queue.poll();
			
			pizza.time  = pizza.time - 1;
			
			if(pizza.time == 0) {
				answer[pizza.idx] = time;
				limit -= 1;
			} else {
				queue.add(new Pizza(pizza.idx, pizza.time));
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
	}

}

class Pizza {
	int idx;
	int time;
	
	Pizza(int idx, int time) {
		this.idx = idx;
		this.time = time;
	}
}
