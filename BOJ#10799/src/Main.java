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
		
		boolean isVPS = true;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				isVPS = false;
				stack.push('(');
			} else { //')' 닫는 괄호 만났을 때 
				if(isVPS == false) { //레이저로 잘릴 때 () 
					stack.pop();
					result += stack.size();
					isVPS = true;
				
				} else { //쇠막대기 끝나는 괄호 )
					stack.pop(); //하나의 쇠막대기가 끝났으닌까, 끝나면서 한 조각이 남으닌까 +1 
					result++; 
				}
			}
		}
		
		System.out.println(result);
	}

}
