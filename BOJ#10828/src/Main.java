/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 스택 
 * https://www.acmicpc.net/problem/10828
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			switch(st.nextToken()) {
			case "push":
				int n = Integer.parseInt(st.nextToken());
				stack.push(n);
				break;
			case "pop":
				System.out.println(stack.isEmpty() ? -1 : stack.pop());
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				System.out.println(stack.isEmpty() ? 1 : 0);
				break;
			case "top":
				System.out.println(stack.isEmpty() ? -1 : stack.peek());	
				break;
			}
		}
	}

}
