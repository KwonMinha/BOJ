/**
 * @author Minha Gwon
 * @date 2020. 5. 24.
 * 일곱 난쟁이 
 * https://www.acmicpc.net/problem/2309
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[9];

		for(int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		int sum = Arrays.stream(arr).sum();
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = i; j < arr.length; j++) {
				if(sum - arr[i] - arr[j] == 100) {
					for(int k = 0; k < arr.length; k++) {
						if(k == i || k == j)
							continue;
						System.out.println(arr[k]);
					}
					System.exit(0);
				}
			}
		}
	}

}
