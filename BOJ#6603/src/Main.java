/**
 * @author Minha Gwon
 * @date 2020. 5. 28.
 * 로또
 * https://www.acmicpc.net/problem/6603
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] arr;
	private static int r = 6;
	private static boolean[] v;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			if(k == 0) {
				bw.write(sb.toString());	
				bw.flush();
				bw.close();
				br.close();
				System.exit(0);
			} else {
				arr = new int[k];
				v = new boolean[k];
				for(int i = 0; i < k; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
				Arrays.sort(arr);
				solve(r, 0);
				sb.append('\n');
			}
		}
	}

	public static void solve(int r, int start) {
		if(r == 0) {
			for(int i = 0; i < v.length; i++)
				if(v[i])
					sb.append(arr[i]).append(' ');
			sb.append('\n');

			return;
		} else {
			for(int i = start; i < arr.length; i++) {
				v[i] = true;
				solve(r-1, i+1);
				v[i] = false;
			}
		}
	}
}
