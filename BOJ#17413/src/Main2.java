/**
 * @author Minha Gwon
 * @date 2020. 4. 29.
 * 단어 뒤집기2 
 * https://www.acmicpc.net/problem/17413
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String str = sc.nextLine();
		Stack<Character> stack = new Stack<>();
		str += '\n';
        boolean tag = false;
        
        for(int i = 0; i < str.length(); i++){

        	char c = str.charAt(i);
    
            if(c == '<')
            	tag = true;

            // 태그가 되버리거나, 혹은 문장이 끝나거나 혹은 공백을 만났을 때이다.
            if(tag || c == ' ' || c == '\n'){
                while(!stack.isEmpty()) 
                	sb.append(stack.pop());
                if(c != '\n') 
                	sb.append(c);
            } else {
                stack.push(c);
            }
            
            if(c == '>')
            	tag = false;
        }
        
        System.out.println(sb.toString());
	}

}