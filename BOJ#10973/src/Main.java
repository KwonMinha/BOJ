/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * 이전 순열
 * https://www.acmicpc.net/problem/10973
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
	
		
		if(prePermutaion(arr)) {
			for(int i : arr)
				System.out.print(i + " ");
		} else {
			System.out.println(-1);
		}
	}
	
	public static boolean prePermutaion(int[] arr) {
		int i = arr.length-1;
		
		while(i > 0 && arr[i] > arr[i-1]) {
			i--;
		}

		if(i == 0)
			return false;
		
		int j = arr.length-1;
		while(arr[i-1] < arr[j]) {
			j--;
		}
		swap(arr, i-1, j);
		
		j = arr.length-1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
		
		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
