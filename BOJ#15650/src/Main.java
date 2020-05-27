/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * N과 M(2)
 * https://www.acmicpc.net/problem/15650
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static boolean[] v;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		n = N;
		v = new boolean[N];
		
		combination(0, M);
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void combination(int depth, int r) {
		if(r == 0) {
			for(int i = 0; i < v.length; i++) {
				if(v[i])
					sb.append(i+1 + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		if(depth == n) {
			return;
		} else {
			v[depth] = true;
			combination(depth+1, r-1);
			
			v[depth] = false;
			combination(depth+1, r);
		}
	}

}
