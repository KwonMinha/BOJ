/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 나는야 포켓몬 마스터 이다솜 
 * https://www.acmicpc.net/problem/1620
 * HashMap만 이용해 구현 
 */

import java.util.HashMap;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		sc.nextLine();

		HashMap<String, String> map = new HashMap<>();

		for(int i = 0; i < N; i++) {
			String str = sc.nextLine();
			map.put(str, i+1+"");
			map.put(i+1+"", str);
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < M; i++) {
			sb.append(map.get(sc.nextLine()) + "\n");
		}

		System.out.println(sb);
	}

}