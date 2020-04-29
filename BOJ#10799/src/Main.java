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
			} else { //닫는 괄호 만났을 때 - 레이저 
				if(isVPS == false) {
					stack.pop();
					count += stack.size();
					isVPS = true;
				
				} else {
					stack.pop();
					count++;
				}
			}
			if(stack.isEmpty()) {
				//System.out.println(1111);
				//System.out.println("c : " + count + " r : " + result);
				result += count;
				count = 0;
			}
		}
		
		System.out.println(result);
	}

}
