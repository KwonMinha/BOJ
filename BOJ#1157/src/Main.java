/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 단어 공부 
 * https://www.acmicpc.net/problem/1157
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();
		str = str.toUpperCase();

		int[] alpha = new int[26];

		for(int i = 0; i < str.length(); i++) {
			int num = str.charAt(i) - 65;
			alpha[num] += 1;
		}

		int max = 0;
		char answer = '?';
		
		for(int i = 0; i < alpha.length; i++) {
			if(alpha[i] > max) {
				max = alpha[i];
				answer = (char) (i+65);
			} else if(alpha[i] == max) {
				answer = '?';
			}
		}
		
		System.out.println(answer);
	}

}
