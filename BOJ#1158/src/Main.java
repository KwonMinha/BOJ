/**
 * @author Minha Gwon
 * @date 2020. 4. 21.
 * 조세퍼스 문제
 * https://www.acmicpc.net/problem/1158
 * 
 * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다. 이제 순서대로 K번째 사람을 제거한다.
   한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
   원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다.
   예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
   
   N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오. (1 ≤ K ≤ N ≤ 5,000)
  
   ex) input : 7 3
       output : <3, 6, 2, 7, 5, 1, 4>
       
 * 블로그 https://minhamina.tistory.com/20
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> qu = new LinkedList<Integer>();
		for(int i = 0; i < N; i++) { //큐에 N까지의 수 차례로 넣어주기
			qu.add(i+1);
		}

		while(!qu.isEmpty()) { //N명의 사람이 모두 제거될 때까지, 즉 큐가 빌 때까지 반복 
			for(int j = 0; j < K; j++) { //K번째를 제거하기 위한 루프
				if(j == K-1) {
					sb.append(qu.poll() + ", "); //K번을 제거
				} else {
					qu.add(qu.poll()); //K번이 아닐 때에는 맨 뒤로 이동
				}				
			}
		}
		
//		for(int i=0;i<m-1;i++) 
//		{ deque.addLast(deque.removeFirst()); } 
//		sb.append(deque.removeFirst() + ", "); }
//
		
		
//		int index = 0; while(!list.isEmpty()) 
//		{ index += m; if (index >= list.size()) { index %= list.size(); } 
//		sb.append(list.remove(index) + ", "); }

		
		bw.write((sb.substring(0, sb.length()-2) + ">").toString());
		bw.flush();
		bw.close();
	}
}
