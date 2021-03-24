/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 요세푸스 문제 0 
 * https://www.acmicpc.net/problem/11866
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < N; i++) { //큐에 N까지의 수 차례로 넣어주기
			queue.add(i+1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		while(!queue.isEmpty()) { //N명의 사람이 모두 제거될 때까지, 즉 큐가 빌 때까지 반복
			for(int j = 0; j < K; j++) { //K번째를 제거하기 위한 루프
				if(j == K-1) { //K번째 순서 도착
					sb.append(queue.poll() + ", "); //K번을 제거
				} else {
					queue.add(queue.poll()); //K번이 아닐 때에는 맨 뒤로 이동
				}				
			}
		}
		
		System.out.println(sb.substring(0, sb.length()-2) + ">".toString());
	
		
	}

}