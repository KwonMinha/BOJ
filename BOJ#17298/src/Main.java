/**
 * @author Minha Gwon
 * @date 2020. 5. 3.
 * 오큰수
 * https://www.acmicpc.net/problem/17298
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		int[] A = Stream.of(str.split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Stack<Integer> st = new Stack<Integer>();
		Stack<Integer> ans = new Stack<Integer>();
		
		st.push(A[A.length-1]);
		ans.push(-1);
		
		for(int i = A.length-2; i >= 0; i--) {
			int n = A[i];
			
			if(st.peek() > n) {
				ans.push(st.peek());
				st.push(n);
			} else {
				while(!st.isEmpty() && st.peek() <= n) {
					st.pop();
				}
				if(st.isEmpty()) {
					ans.push(-1);
					st.push(n);
				} else {
					ans.push(st.peek());
					st.push(n);
				}
			}
		}
		
		while(!ans.isEmpty()) {
			if(ans.size() == 1)
				bw.write(ans.pop() + "");
			else
				bw.write(ans.pop() + " ");		
		}
		
		bw.flush();
		bw.close();
	}

}
