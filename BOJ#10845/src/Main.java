/**
 * @author Minha Gwon
 * @date 2020. 4. 21.
 * 큐
 * https://www.acmicpc.net/problem/10845
 * 정수를 저장하는 큐를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 * 
 * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
	주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
	문제에 나와있지 않은 명령이 주어지는 경우는 없다.

 * push X: 정수 X를 큐에 넣는 연산이다.
	pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	size: 큐에 들어있는 정수의 개수를 출력한다.
	empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
	front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringBuilder sb = new StringBuilder();
			
			int N = Integer.parseInt(br.readLine());
			
			Queue<Integer> qu = new LinkedList<Integer>();
			int rear = 0;
			
			//switch문으로 구현 
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				
				switch(str) {
				case "push":
					int num = Integer.parseInt(st.nextToken());
					qu.add(num);
					rear = num;
					break;
				case "pop":
					sb.append(qu.isEmpty()? -1 : qu.poll());
					sb.append("\n");
					break;
				case "size":
					sb.append(qu.size());
					sb.append("\n");
					break;
				case "empty":
					sb.append(qu.isEmpty()? 1 : 0);
					sb.append("\n");
					break;
				case "front":
					sb.append(qu.isEmpty()? -1 : qu.peek());
					sb.append("\n");
					break;
				case "back":
					sb.append(qu.isEmpty()? -1 : rear);
					sb.append("\n");
					break;
				default:
					break;
				}
			}
			
			
			/*
			// if문으로 구현 
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				
				if(str.contains("push")) {
					int num = Integer.parseInt(str.substring(5));
					qu.add(num);
					rear = num;
				} else if(str.equals("pop")) {
					if(qu.isEmpty()) {
						sb.append(-1 + "\n");
					}
					else {
						sb.append(qu.poll() + "\n");
					}
				} else if(str.equals("size")) {
					sb.append(qu.size() + "\n");
				} else if(str.equals("empty")) {
					if(qu.isEmpty())
						sb.append(1 + "\n");
					else
						sb.append(0 + "\n");
				} else if(str.equals("front")) {
					if(qu.isEmpty())
						sb.append(-1 + "\n");
					else
						sb.append(qu.peek() + "\n");
				} else if(str.equals("back")) {
					if(qu.isEmpty())
						sb.append(-1 + "\n");
					else
						sb.append(rear + "\n");
				}	
			}
			*/
			
			bw.write(sb.toString());
			bw.flush();
			bw.close();
	
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

}
