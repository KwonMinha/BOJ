/**
 * @author Minha Gwon
 * @date 2021. 2. 16.
 * 히스토그램에서 가장 큰 직사각형 
 * https://www.acmicpc.net/problem/6549
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int N = sc.nextInt();
			
			if(N == 0)
				break;
			
			int[] histogram = new int[N];
			for(int i = 0; i < N; i++) {
				histogram[i] = sc.nextInt();
				System.out.print(histogram[i] + " ");
			}
			System.out.println();
			
		}
		
	}

}
