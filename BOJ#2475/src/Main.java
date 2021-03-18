/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 검증수 
 * https://www.acmicpc.net/problem/2475
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		String[] arr = str.split(" ");
		
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			int num = Integer.parseInt(arr[i]);
			sum += num * num;
		}
		
		System.out.println(sum % 10);
	}

}
