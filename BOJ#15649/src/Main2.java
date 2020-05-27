/**
 * @author Minha Gwon
 * @date 2020. 5. 27.
 * N과 M (1)
 * https://www.acmicpc.net/problem/15649
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2 {
	public static boolean[] v;
	public static LinkedList<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		v = new boolean[N];
		list = new LinkedList<Integer>();
		
		permutation(N, M);
	}
	
	public static void permutation(int n, int r) {
		if(list.size() == r) {
			for(int i = 0; i < list.size(); i++)
				System.out.print(list.get(i) + " ");
			System.out.println();
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				list.add(i+1);
				permutation(n, r);
				v[i] = false;
				list.removeLast();
			}
		}
	}
}
