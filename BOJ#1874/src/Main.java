import java.util.Scanner;
import java.util.Stack;

/**
 * @author gwonminha
 * @date 2020. 4. 7.
 * 스택 수열 
 * https://www.acmicpc.net/problem/1874
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Stack<Integer> st = new Stack<Integer>();
		int countNum = 0;

		for(int i = 0; i < n; i++) {
			int inputNum = sc.nextInt();
			
			if(!st.isEmpty() && inputNum == st.peek()) {
				st.pop();
				System.out.println("-");
			}
			
			


		}
	}

}
