/**
 * @author Minha Gwon
 * @date 2021. 7. 10.
 * 돌그룹
 * https://www.acmicpc.net/problem/12886
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		boolean flag = false;
		
		if((A+B+C) % 3 != 0) {
			System.out.println(0);
			return;
		}
		
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[1501][1501];

		queue.add(new Node(A, B, C));
		visited[A][B] = true;
		
		while(!queue.isEmpty()) {
			int a = queue.peek().a;
			int b = queue.peek().b;
			int c = queue.poll().c;
			
			if(a == b && b == c) {
				flag = true;
				break;
			}
			
			if(a != b) {
				if(a < b) {
					b = b - a;
					a = a + a;
				} else {
					a = a - b;
					b = b + b;
				}
				
				if(!visited[a][b]) {
					visited[a][b] = true;
					queue.add(new Node(a, b, c));
				}
			}
			
			if(a != c) {
				if(a < c) {
					c = c - a;
					a = a + a;
				} else {
					a = a - c;
					c = c + c;
				}
				
				if(!visited[a][c]) {
					visited[a][c] = true;
					queue.add(new Node(a, b, c));
				}
			}
			
			/*
			// a-c or b-c 둘 중 하나는 없어도 됨 -> ab, ac, bc 총 3번의 경우의 수가 아니더라도 2개로도 충분히 답 나옴 -> X, Y 더하고 빼봤자 총 합은 늘 같으니까 
			if(b != c) {
				if(b < c) {
					c = c - b;
					b = b + b;
				} else {
					b = b - c;
					c = c + c;
				}
				
				if(!visited[b][c]) {
					visited[b][c] = true;
					queue.add(new Node(a, b, c));
				}
			}
			*/
		}
		
		System.out.println(flag ? 1 : 0);
	}

}

class Node {
	int a;
	int b;
	int c;
	
	Node(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
