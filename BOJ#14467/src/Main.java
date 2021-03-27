/**
 * @author Minha Gwon
 * @date 2021. 3. 27.
 * 소가 길을 건너간 이유 1
 * https://www.acmicpc.net/problem/14467
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int cnt = 0;
		int[] arr = new int[101];
		Arrays.fill(arr, -1);
		
		for(int i = 0; i < N; i++) {
			int num = sc.nextInt();
			int dir = sc.nextInt();
			
			if(arr[num] == -1) {
				arr[num] = dir;
			} else {
				if(arr[num] != dir) {
					cnt++;
					arr[num] = dir;
				}
			}
		}
		
		System.out.println(cnt);
	}

}