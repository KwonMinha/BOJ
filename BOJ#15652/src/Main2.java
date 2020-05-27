/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * N과 M(4)
 * https://www.acmicpc.net/problem/15652
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2 {
	private static int n, r;
	private static StringBuilder sb;
	private static int[] output;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		n = N;
		r = M;
		output = new int[M];
		
		combination(0, 0);
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void combination(int start, int depth) {
		if(depth == r) {
			for(int i : output)
				sb.append(i).append(' ');
			sb.append('\n');
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			output[depth] = i+1;
			combination(i, depth+1);
		}
	}

}