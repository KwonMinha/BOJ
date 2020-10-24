/**
 * @author Minha Gwon
 * @date 2020. 10. 25.
 * 탑
 * https://www.acmicpc.net/problem/2493
 * - Scanner로 입력 받으면 메모리 초과 
 * - 참고하면 좋을 블로그 포스트 - https://codevang.tistory.com/307
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		int[] answer = new int[N];
	
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < arr.length; i++) {
			int result = 0;
			
			while(!stack.isEmpty()) { //stack에 값이 있다면 
				int top = arr[stack.peek()];
				
				if(top < arr[i]) { //스택의 탑이 현재 값보다 작다면 신호 받을 일 없음 -> pop()
					stack.pop();
				} else { //탑이 현재보다 크다면 신호 받음 
					result = stack.peek() + 1; //탑은 1번부터 시작하닌까 +1
					break;
				}
			}
			
			answer[i] = result;
			stack.push(i);
		}
		
		//정답 출력 
		for(int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}

}
