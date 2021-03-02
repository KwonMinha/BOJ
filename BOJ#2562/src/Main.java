/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 최댓값 
 * https://www.acmicpc.net/problem/2562
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			int n = sc.nextInt();
			list.add(n);
		}
		
		int max = Collections.max(list);
		System.out.println(max);
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) == max) {
				System.out.println(i+1);
			}
		}
	}

}
