/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 나는야 포켓몬 마스터 이다솜 
 * https://www.acmicpc.net/problem/1620
 * Buffer를 통해 입출력을 받고, HashMap과 getValue 함수 사용 - 시간 초과 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, String> map = new HashMap<>();

		for(int i = 0; i < N; i++) {
			map.put(br.readLine(), i+1+"");
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < M; i++) {
			String str = br.readLine();

			if(str.charAt(0) >= '1' && str.charAt(0) <= '9') {
				sb.append(getKey(map, str) + "\n");
			} else {
				sb.append(map.get(str) + "\n");
			}
		}

		bw.write(sb.toString());
		
		br.close();
		bw.flush();
		bw.close();
	}

	// hashmap에서 value로 key 찾기
	public static Object getKey(HashMap<String, String> map, Object value) { 
		for(Object o: map.keySet()) { 
			if(map.get(o).equals(value)) { 
				return o; 
			} 
		} 
		return null; 
	}

}