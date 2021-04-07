/**
 * @author Minha Gwon
 * @date 2021. 4. 7.
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
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int n = Integer.parseInt(st.nextToken());	

				if(oper.equals("I")) { // 삽입 
					treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
				} else if(oper.equals("D") && !treeMap.isEmpty()){
					if(n == -1) { // 최솟값 삭제 
						int firstKey = treeMap.firstKey();
						int firstValue = treeMap.get(firstKey);
						
						if(firstValue == 1) { 
							treeMap.pollFirstEntry(); // 중복값이 없는 경우만 완전히 삭제 
						} else {
							treeMap.put(firstKey, firstValue - 1); // 중복일 경우 -1 
						}
					} else { // 최댓값 삭제 
						int lastKey = treeMap.lastKey();
						int lastValue = treeMap.get(lastKey);
						
						if(lastValue == 1) { 
							treeMap.pollLastEntry(); // 중복값이 없는 경우만 완전히 삭제 
						} else {
							treeMap.put(lastKey, lastValue - 1); // 중복일 경우 -1 
						}
					}
				}
			}

			// 정답 출력 
			if(treeMap.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
			}
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}

}