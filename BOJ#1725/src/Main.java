/**
 * @author Minha Gwon
 * @date 2021. 4. 1.
 * 히스토그램 
 * https://www.acmicpc.net/problem/1725
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		
		int[] arr = new int[N+2];
		for(int i = 1; i < N+1; i++) {
			arr[i] = sc.nextInt();
		}
		
		int ans = 0;
		
		Stack<Integer> stack = new Stack<>();
		
		stack.push(0);

		for (int i = 1; i <= N+1; i++) {
			//System.out.println("* i : " + i);
		    while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
		    	//System.out.println("------------------------------");
		    	
		    	//System.out.println("1. stack top : " + stack.peek() + " / height : " + arr[stack.peek()]);
		        int height = arr[stack.peek()];
		        
		        //System.out.println("2. pop");
		        stack.pop();
	
		        //System.out.println("3. stack top : " + stack.peek() + " / width : " + (i-stack.peek()-1));
		        int width = i - stack.peek() - 1;
		        
		        //System.out.println("4. area : " + (height * width) + "\n------------------------------\n");

		        ans = Math.max(ans, width * height);
		    }
		    
		    stack.push(i);
		}
		
		System.out.println(ans);
	}
	

}