/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 나는야 포켓몬 마스터 이다솜 
 * https://www.acmicpc.net/problem/1620
 * Buffer를 통해 입출력을 받고, HashMap과 getValue 함수 사용 - 시간초과 
 */

import java.util.HashMap;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		sc.nextLine();

		HashMap<String, String> map = new HashMap<>();

		for(int i = 0; i < N; i++) {
			map.put(sc.nextLine(), i+1+"");
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < M; i++) {
			String str = sc.nextLine();

			if(str.charAt(0) >= '1' && str.charAt(0) <= '9') {
				sb.append(getKey(map, str) + "\n");
			} else {
				sb.append(map.get(str) + "\n");
			}
		}

		System.out.println(sb);
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