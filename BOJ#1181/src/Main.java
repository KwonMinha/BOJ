/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 단어 정렬 
 * https://www.acmicpc.net/problem/1181
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		
		sc.nextLine(); // 개행 버림 

		String[] arr = new String[N];

		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextLine();
		}

		Arrays.sort(arr, new Comparator<String>() { 
			@Override 
			public int compare(String o1, String o2) { 
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				} else {
					return o1.length() - o2.length();
				}
			} 
		}); 

		System.out.println(arr[0]);
		for (int i = 1; i < N; i++) {
			// 중복되지 않는 단어만 출력
			if (!arr[i].equals(arr[i - 1])) {
				System.out.println(arr[i]);
			}
		}
	}

}