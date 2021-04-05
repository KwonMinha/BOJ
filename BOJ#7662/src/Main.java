/**
 * @author Minha Gwon
 * @date 2021. 4. 5.
 * 이중 우선순위 큐 
 * https://www.acmicpc.net/problem/7662
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int lastNum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());

			PriorityQueue<Integer> pq = new PriorityQueue<>();

			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int n = Integer.parseInt(st.nextToken());	

				if(oper.equals("I")) {
					pq.add(n);
				} else if(oper.equals("D") && !pq.isEmpty()){
					if(n == -1) {
						pq.poll();
					} else {
						pq = removeLast(pq);
					}
				}
			}

			// 정답 출력 
			if(pq.isEmpty()) {
				sb.append("EMPTY\n");
			} else if(pq.size() == 1) {
				int num = pq.poll();
				sb.append(num + " " + num + "\n");
			} else {
				pq = removeLast(pq);
				sb.append(lastNum + " " + pq.poll() + "\n");
			}
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static PriorityQueue<Integer> removeLast(PriorityQueue<Integer> pq) {
		PriorityQueue<Integer> pqNew = new PriorityQueue<>();

		while(pq.size() > 1) {
			pqNew.add(pq.poll());
		}
		
		lastNum = pq.poll();

		pq.clear();
		
		return pqNew;
	}

}