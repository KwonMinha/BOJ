/**
 * @author Minha Gwon
 * @date 2020. 5. 23.
 * 리모콘  
 * https://www.acmicpc.net/problem/1107
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] wrong;
		if(M != 0) {
			wrong = new int[M];
			for(int i = 0; i < M; i++) {
				wrong[i] = sc.nextInt();
			}
		} else {
			wrong = new int[1];
			wrong[0] = 10;
		}

		int first = 100;
		int answer = 0;

		if(first == N) {
			System.out.println(answer);
		} else {
			int n1 = oneCount(N, first, 0);
			int n2 = minus(N, wrong);
			int n3 = plus(N, wrong);

			answer = Math.min(n1, n2);
			answer = Math.min(answer, n3);

			System.out.println(answer);
		}
	}

	public static int plus(int N, int[] wrong) {
		int ans = 0;
		String temp = "";

		int cur;

		if(wrong.length == 9 && !IntStream.of(wrong).anyMatch(x -> x == 0)) {
			ans++;
			cur = 0;
		} else {
			for(int i = 0; i < Integer.toString(N).length(); i++) {
				int n = Integer.toString(N).charAt(i) - '0';

				while(isContain(wrong, n) && n < 10)
					n++;

				if(n >= 10)
					return 11111111;

				temp += Math.abs(n);
				ans++;
			}

			
			cur = Integer.parseInt(temp);
		
		}

		return oneCount(N, cur, ans);
	}

	public static int minus(int N, int[] wrong) {
		int ans = 0;
		String temp = "";
		int cur;

		if(wrong.length == 9 && !IntStream.of(wrong).anyMatch(x -> x == 0)) {
			ans++;
			cur = 0;
		} else {
			for(int i = 0; i < Integer.toString(N).length(); i++) {
				int n = Integer.toString(N).charAt(i) - '0';

				while(isContain(wrong, n) && n >= 0)
					n--;

				if(n < 0)
					return 11111111;

				temp += Math.abs(n);
				ans++;
			}

			cur = Integer.parseInt(temp);
	
		}

		return oneCount(N, cur, ans);
	}

	public static int oneCount(int N, int cur, int ans) {
		while(N != cur) {
			if(N > cur) {
				cur++;
				ans++;
			} else {
				cur--;
				ans++;
			}
		}

		return ans;
	}

	public static boolean isContain(int[] arr, int targetValue) {
		return IntStream.of(arr).anyMatch(x -> x == targetValue);
	}

}
