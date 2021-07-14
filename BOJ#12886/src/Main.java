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
		
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[2000][2000][2000];
		
		queue.add(new Node(A, B, C));
		visited[A][B][C] = true;
		
		boolean flag = false;
		
		while(!queue.isEmpty()) {
			int a = queue.peek().a;
			int b = queue.peek().b;
			int c = queue.poll().c;
			
			if(a == b && b == c) {
				flag = true;
				break;
			}
				
			int X, Y = 0;
			
			for(int i = 0; i < 3; i++) {
				if(i == 0) {
					X = a;
					Y = b;
				} else if(i == 1) {
					X = a;
					Y = c;
				} else {
					X = b;
					Y = c;
				}
				
				if(X == Y) {
					continue;
				} 
				
				if(X < Y) {
					Y = Y - X;
					X = X + X;
				} else {
					X = X - Y;
					Y = Y + Y;
				}
				
				if(i == 0) {
					if(!visited[X][Y][c]) {
						visited[X][Y][c] = true;
						queue.add(new Node(X, Y, c));
					}
				} else if(i == 1) {
					if(!visited[X][b][Y]) {
						visited[X][b][Y] = true;
						queue.add(new Node(X, b, Y));
					}
				} else {
					if(!visited[a][X][Y]) {
						visited[a][X][Y] = true;
						queue.add(new Node(a, X, Y));
					}
				}	
			}
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
