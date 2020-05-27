/**
 * @author Minha Gwon
 * @date 2020. 5. 28.
 * N과 M(5)
 * https://www.acmicpc.net/problem/15654
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb;
	private static int[] arr, output;
	private static int n, r;
	private static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		n = N;
		r = M;

		String[] temp = br.readLine().split(" ");
		arr = Arrays.asList(temp).stream().mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		v = new boolean[n];
		output = new int[r];

		permutation(0);

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

	public static void permutation(int depth) {
		if(depth == r) {
			for(int i = 0; i < r; i++)
				sb.append(output[i]).append(' ');
			sb.append('\n');

			return;
		}

		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				output[depth] = arr[i];
				permutation(depth+1);
				v[i] = false;
			}
		}
	}
}
