/**
 * @author Minha Gwon
 * @date 2021. 7. 27.
 * 암호 만들기
 * https://www.acmicpc.net/problem/1759
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int L, C;
	static String[] arr;
	static boolean[] visited;
	static ArrayList<String> resultList;
	static int[] alpha;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		L = sc.nextInt();
		C =  sc.nextInt();

		sc.nextLine();

		String str = sc.nextLine();

		arr = str.split(" ");
		Arrays.sort(arr);

		visited = new boolean[C];
		resultList = new ArrayList<>();

		combination(0, L);

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < resultList.size(); i++) {
			sb.append(resultList.get(i) + "\n");
		}

		System.out.println(sb);
	}

	// 조합 백트래킹으로 구현 
	public static void combination(int start, int r) {
		if(r == 0) {
			alpha = new int[123];
			String result = "";
			
			for(int i = 0; i < C; i++) {
				if(visited[i]) {
					result += arr[i];
					alpha[arr[i].charAt(0)]++;
				}
			}
			
			int vowels = alpha['a'] + alpha['e'] + alpha['i'] + alpha['o'] + alpha['u']; // 모음 개수 
			int consonants = L - vowels; // 자음 개수 
			
			if(vowels >= 1 && consonants >= 2) {
				resultList.add(result);
			}

			return;
		} else {
			for(int i = start; i < C; i++) {
				visited[i] = true;
				combination(i+1, r-1);
				visited[i] = false;
			}
		}
	}

}
