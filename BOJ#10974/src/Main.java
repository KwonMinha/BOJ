/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * 모든 순열 
 * https://www.acmicpc.net/problem/10974
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		boolean[] v = new boolean[N];
		int[] output = new int[N];
		
		permutation(arr, v, output, N, 0);
	}
	
	public static void permutation(int[] arr, boolean[] v, int[] output, int n, int r) {
		if(r == n) {
			for(int i : output) {
				System.out.print(i + " ");
			}
			System.out.println();
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				output[r] = arr[i];
				permutation(arr, v, output, n, r+1);
				v[i] = false;
			}
		}
	}

}
