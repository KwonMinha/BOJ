/**
 * @author Minha Gwon
 * @date 2020. 4. 29.
 * 쇠막대기 
 * https://www.acmicpc.net/problem/10799
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Stack<Character> stack = new Stack<Character>();

		int result = 0;
		int count = 0;
		
		boolean isVPS = true;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				isVPS = false;
				stack.push('(');
			} else { // ) 닫는 괄호 만났을 때 
				if(isVPS == false) { //레이저로 잘릴 때 () 
					stack.pop();
					count += stack.size();
					isVPS = true;
				
				} else { //쇠막대기 끝나는 괄호 )
					stack.pop();
					count++;
				}
			}
			if(stack.isEmpty()) {
				result += count;
				count = 0;
			}
		}
		
		System.out.println(result);
	}

}
