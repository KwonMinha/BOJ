/** 
 * @author Minha Gwon
 * @date 2020. 4. 7.
 * 괄호
 * https://www.acmicpc.net/problem/9012
 */

import java.util.Scanner;
import java.util.Stack;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int i = 0; i < T; i++) {
			boolean isVPS = true;
			String s = sc.nextLine();
			//Stack<Integer> st = new Stack<Integer>();
			Stack st = new Stack<>();
			
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == '(') {
					st.push(1);
				} else {
					if(!st.isEmpty()) {
						st.pop();
					} else {
						isVPS = false;
						break;
					}
				}
			}
			
//			if(!st.isEmpty())
//				isVPS = false;
			
			if(isVPS == true && st.isEmpty())
				System.out.println("YES");
			else
				System.out.println("NO");
		}

	}

}
