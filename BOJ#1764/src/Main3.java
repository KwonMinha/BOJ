/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 듣보잡 
 * https://www.acmicpc.net/problem/1764
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		sc.nextLine();
		
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			list1.add(sc.nextLine());
		}
		Collections.sort(list1);
		
		for(int i = 0; i < M; i++) {
			list2.add(sc.nextLine());
		}
		Collections.sort(list2);
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			if(Collections.binarySearch(list1, list2.get(i)) >= 0) {
				sb.append(list2.get(i) + "\n");
				cnt++;
			}
		}

		System.out.println(cnt);
		System.out.println(sb);
	}
	
}