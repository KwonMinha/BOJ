/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * N과 M (1)
 * https://www.acmicpc.net/problem/15649
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main3 {
	private static int N;
	private static int M;
	private static boolean[] v;
	private static int[] output;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new boolean[N];
		output = new int[M];

		dfs(
				0);
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void dfs(int r) {
		if(r == M) {
			for(int i : output)
				sb.append(i + " ");
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!v[i]) {
				v[i] = true;
				output[r] = i+1;
				dfs(r+1);
				v[i] = false;
			}
		}
	}

}
