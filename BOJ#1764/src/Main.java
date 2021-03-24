/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 듣보잡 
 * https://www.acmicpc.net/problem/1764
 * anyMath, retailAll 메서드로 교집합 구하는 건 시간 초과 X 
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		sc.nextLine();
		
		String[] arr1 = new String[N];
		String[] arr2 = new String[M];
		
		for(int i = 0; i < N; i++) {
			arr1[i] = sc.nextLine();
		}
		Arrays.sort(arr1);
		
		for(int i = 0; i < M; i++) {
			arr2[i] = sc.nextLine();
		}
		Arrays.sort(arr2);
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			if(Arrays.binarySearch(arr1, arr2[i]) >= 0) {
				sb.append(arr2[i] + "\n");
				cnt++;
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb);
	}

}