/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 숫자 카드 2
 * https://www.acmicpc.net/problem/10816
 * System.out.println으로 출력하면 시간 초과 -> BufferedWriter나 StringBuilder 사용해야 함 
 */

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			int n = sc.nextInt();
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		
		int M = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			int m = sc.nextInt();
			sb.append(map.get(m) != null ? map.get(m) + " " : 0 + " ");
		}
		
		System.out.println(sb.toString());
		
	}

}
