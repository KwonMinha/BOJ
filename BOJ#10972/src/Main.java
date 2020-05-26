/**
 * @author Minha Gwon
 * @date 2020. 5. 26.
 * 다음 순열
 * https://www.acmicpc.net/problem/10972
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] input;
	static boolean flag = false;
	static int factorial = 1;
	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int N = Integer.parseInt(br.readLine());
		input = new int[N];
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			arr[i] = i + 1;
			factorial *= (i+1);
		}
		
		boolean[] v = new boolean[N];
		int[] output = new int[N];
		
		per(arr, v, N, 0, output);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}

	public static void per(int[] arr, boolean[] v, int n, int r, int[] output) {
		if(r == n) {
			count++;
			
			if(flag) {
				for(int i = 0; i < output.length; i++) {
					sb.append(output[i] + " ");
				}
				flag = false;
			}
			
			if(Arrays.equals(input, output)) {
				if(count == factorial) {
					sb.append(-1);
				} else {
					flag = true;
				}
			}
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				output[r] = arr[i];
				per(arr, v, n, r+1, output);
				v[i] = false;
			}
		}
	}
}
