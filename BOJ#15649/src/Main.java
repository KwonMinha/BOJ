/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * N과 M (1)
 * https://www.acmicpc.net/problem/15649
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		int[] output = new int[M];
		boolean[] v = new boolean[N];
		
		permutation(arr, output, v, N, M, 0);
	}
	
	public static void permutation(int[] arr, int[] output, boolean[] v, int n, int r, int depth) {
		if(depth == r) {
			for(int i : output)
				System.out.print(i + " ");
			System.out.println();
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, v, n, r, depth+1);
				v[i] = false;
				
			}
		}
	}

}
