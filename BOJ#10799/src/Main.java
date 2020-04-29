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
					stack.pop(); //하나의 쇠막대기가 끝났으닌까 pop()
					count++; // 그리고 1 추가 - 왜?  
				}
			}
			if(stack.isEmpty()) { //쇠막대기 제일 긴게 끝났을 때(그럼 스택엔 아무것도 없겠져?)
				result += count; //지금까지 잘린 조각 최종 result에 저장 
				count = 0; // 다시 나올 새로운 쇠막대기를 위해 0으로 리셋 
			}
		}
		
		System.out.println(result);
	}

}
