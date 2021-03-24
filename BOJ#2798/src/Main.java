/**
 * @author Minha Gwon
 * @date 2021. 3. 24.
 * 블랙잭
 * https://www.acmicpc.net/problem/2798
 */

import java.util.Scanner;

public class Main {
	static int[] arr;
	static int M, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		
		for(int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		
		combination(0, 3, 0);
		
		System.out.println(result);
	}
	
	public static void combination(int start, int r, int sum) {
		if(r == 0) {
			if(sum > result && sum <= M) 
				result = sum;
			
			return;
		} else {
			for(int i = start; i < arr.length; i++) {
				combination(i+1, r-1, sum + arr[i]);
			}
		}
		
	}
	
	

}
