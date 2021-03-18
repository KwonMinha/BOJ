/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 직사각형에서 탈출  
 * https://www.acmicpc.net/problem/1085
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		int min = Math.min(w-x, x);
		min = Math.min(min, y);
		min = Math.min(min, h-y);
				
		System.out.println(min);
	}

}
