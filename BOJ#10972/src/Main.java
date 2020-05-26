/**
 * @author Minha Gwon
 * @date 2020. 5. 26.
 * 다음 순열
 * https://www.acmicpc.net/problem/10972
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] input;
	static boolean flag = false;
	static int factorial = 1;
	static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		input = new int[N];
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
			arr[i] = i + 1;
			factorial *= (i+1);
		}
		
		boolean[] v = new boolean[N];
		int[] output = new int[N];
		
		per(arr, v, N, 0, output);
		
	}

	public static void per(int[] arr, boolean[] v, int n, int r, int[] output) {
		if(r == n) {
			count++;
			
			if(flag) {
				for(int i = 0; i < output.length; i++) {
					System.out.print(output[i] + " ");
				}
				System.exit(0);
			}
			
			if(Arrays.equals(input, output)) {
				if(count == factorial) {
					System.out.println(-1);
				} else {
					flag = true;
				}
			}
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				output[r] = arr[i];
				per(arr, v, n, r+1, output);
				v[i] = false;
			}
		}
	}
}
