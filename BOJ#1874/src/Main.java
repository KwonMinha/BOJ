/**
 * @author gwonminha
 * @date 2020. 4. 7.
 * 스택 수열 
 * https://www.acmicpc.net/problem/1874
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Stack<Integer> st = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		int countNum = 0;

		for(int i = 0; i < n; i++) {
			int inputNum = sc.nextInt();
			int gap = inputNum - countNum;
			if(countNum < inputNum) {
				for(int j = 0; j < gap; j++) {
					countNum++;
					st.push(countNum);
					sb.append("+\n");
				}
			}

			if(!st.isEmpty() && inputNum == st.peek()) {
				st.pop();
				sb.append("-\n");
			} else {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println(sb.toString());
	}

}
