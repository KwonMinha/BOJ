/**
 * @author Minha Gwon
 * @date 2021. 3. 25.
 * 나는야 포켓몬 마스터 이다솜 
 * https://www.acmicpc.net/problem/1620
 * HashMap과 int 배열을 이용해 구현 
 */

import java.util.HashMap;
import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		sc.nextLine();

		HashMap<String, String> map = new HashMap<>();
		String[] arr = new String[N+1];

		for(int i = 0; i < N; i++) {
			String str = sc.nextLine();
			map.put(str, i+1+"");
			arr[i+1] = str;
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < M; i++) {
			String str = sc.nextLine();
			
			if(str.charAt(0) >= '1' && str.charAt(0) <= '9') {
				sb.append(arr[Integer.parseInt(str)] + "\n");
			} else {
				sb.append(map.get(str) + "\n");
			}
		}

		System.out.println(sb);
	}

}