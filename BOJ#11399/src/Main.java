/**
 * @author Minha Gwon
 * @date 2021. 4. 30.
 * ATM
 * https://www.acmicpc.net/problem/11399
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int ans = arr[0];
		int sum = arr[0];
		for(int i = 1; i < N; i++) {
			sum = sum + arr[i];
			ans += sum;
		}
		
		System.out.println(ans);
	}

}
