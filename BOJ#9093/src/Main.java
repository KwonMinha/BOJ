/**
 * @author Minha Gwon
 * @date 2020. 4. 7.
 * 단어뒤집기 
 * https://www.acmicpc.net/problem/9093 
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine(); // nextInt()뒤에 남은 개행문자를 읽고 뒤의 문자열의 제대로 입력받기 위해 사용
		// nextLine은 개행 문자를 기준으로 줄을 구분한다.
		// 그런데 nextInt를 호출했을 때 엔터를 쳤다면 그 때의 개행 문자가 그대로 남아있고, 
		// 다음의 nextLine은 그 엔터 키를 읽고 거기가 줄의 끝이라고 판단해서 빈 문자열을 리턴한다.
		// 그래서 다음에 입력할 문자열을 제대로 입력받기위해 nextLine을 사용한다.
		
		
		// Stack으로 구현 
		for(int i = 0; i < T; i++) {
			String s = sc.nextLine();
			
			Stack<Character> st = new Stack<Character>();
			
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == ' ') {
					while(!st.isEmpty()) {
						System.out.print(st.pop());
					}
					System.out.print(" ");
				} else {
					st.push(s.charAt(j));
				}
			}
			while(!st.isEmpty()) {
				System.out.print(st.pop());
			}
			
			System.out.println();
		}
		
		/*
		// StringBuffer의 reverse()로 구현 
		for(int i = 0; i < T; i++) {
			String s = sc.nextLine();
			
			String[] arr = s.split(" ");
			
			for(int j = 0; j < arr.length; j++) {
				StringBuffer sb = new StringBuffer(arr[j]);
				System.out.print(sb.reverse() + " ");
			}
			
			System.out.println("");
		}
		*/
		
	}

}