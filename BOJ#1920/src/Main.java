/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 수 찾기 
 * https://www.acmicpc.net/problem/1920
 * 시간 초과를 방지하기 위해 이진 탐색 이용 
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) 
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr); // 이분 탐색을 위한 정렬 
	
		StringBuilder sb = new StringBuilder();
		
		int M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int m = sc.nextInt();
			sb.append(binarySearch(arr, m, 0, N-1) != -1 ? 1 + "\n" : 0 + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int binarySearch(int[] arr, int key, int low, int high) {
		int mid;
		
		while(low <= high) {
			mid = (low + high) / 2;
			
			if(key == arr[mid]) {
				return mid;
			} else if(key < arr[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		return -1; // 탐색 실패 
	}

}