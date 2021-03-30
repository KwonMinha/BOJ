/**
 * @author Minha Gwon
 * @date 2021. 3. 29.
 * 문자열 폭발 
 * https://www.acmicpc.net/problem/9935
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();
		String frula = sc.nextLine();

		StringBuilder result = new StringBuilder();

		Stack<Character> stack = new Stack<>();
		
		for(int i = str.length()-1; i >= 0; i--) {
			stack.push(str.charAt(i));

			if(stack.size() >= frula.length() && stack.peek() == frula.charAt(0)) {
				boolean isBomb = true;
				for(int j = 1; j < frula.length(); j++) {
					if(stack.get(stack.size()-j-1) != frula.charAt(j)) {
						isBomb = false;
						break;
					}
				}

				if(isBomb) {
					for(int j = 0; j < frula.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		} 
		
		while(!stack.isEmpty()) {
			result.append(stack.pop());
		}

		System.out.println(result);
	}

}
