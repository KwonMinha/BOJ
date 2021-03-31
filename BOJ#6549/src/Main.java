/**
 * @author Minha Gwon
 * @date 2021. 4. 1.
 * 히스토그램에서 가장 큰 직사각형 
 * https://www.acmicpc.net/problem/6549
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			
			if(n == 0) break;
			
			long[] arr = new long[n+2];
			for(int i = 1; i < n+1; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			
			long ans = 0;
			
			Stack<Integer> stack = new Stack<>();
			
			stack.push(0);
			
			for (int i = 1; i <= n+1; i++) {
			    while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
			        long height = arr[stack.peek()];
			        
			        stack.pop();
		
			        int width = i - stack.peek() - 1;

			        ans = Math.max(ans, width * height);
			    }
			    
			    stack.push(i);
			}
			
			sb.append(ans + "\n");
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
	

}