/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 단어 공부 
 * https://www.acmicpc.net/problem/1157
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();

		int[] alpha = new int[52];

		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			int num = 0;

			if(Character.isUpperCase(ch)) num = ch - 65;
			else num = ch - 65 - 6;

			alpha[num] += 1;
		}

		int max = 0;
		char maxAlpha = 0;

		for(int i = 0; i < alpha.length; i++) {
			if(alpha[i] > max) {
				max = alpha[i];
				int num = i;

				if(i < 26) num += 65;
				else num += 71;

				maxAlpha = (char) num;
			}
		}

		int cnt = 0;
		for(int i = 0; i < alpha.length; i++) {
			if(alpha[i] == max) cnt++;

			if(cnt >= 2) break;
		}

		if(cnt >= 2) System.out.println("?");
		else System.out.println(maxAlpha);
	}

}
