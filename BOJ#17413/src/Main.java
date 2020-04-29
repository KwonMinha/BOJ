/**
 * @author Minha Gwon
 * @date 2020. 4. 29.
 * 단어 뒤집기2 
 * https://www.acmicpc.net/problem/17413
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		String str = sc.nextLine();
		Deque<String> deque = new ArrayDeque<String>();
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '<') {
				while(str.charAt(i) != '>') {
					deque.add(str.charAt(i)+"");
					i++;
				}
				deque.add(">");
			} else {
				while(i+1 != str.length() && str.charAt(i+1) != ' ' && str.charAt(i+1) != '<') {
					sb.append(str.charAt(i));
					i++;
				}
				sb.append(str.charAt(i));
				if(i+1 != str.length() && str.charAt(i+1) == ' ') {
					deque.add(sb.reverse() + " ");
					i++;
				} else {
					deque.add(sb.reverse() + "");
				}
				sb.setLength(0);
			}
		}
		
		while(!deque.isEmpty()) {
			System.out.print(deque.poll());
		}
	}

}