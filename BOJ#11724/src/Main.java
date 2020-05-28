/**
 * @author Minha Gwon
 * @date 2020. 5. 28.
 * 연결 요소의 개수
 * https://www.acmicpc.net/problem/11724
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static LinkedList<Integer>[] list;
	private static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		n = N;
		m = M;
		
		list = new LinkedList[N+1];
		v = new boolean[N+1];
		for(int i = 0; i < N+1; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			list[n1].add(n2);
			list[n2].add(n1);
		}
		
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			if(!v[i]) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	public static void dfs(int a) {
		v[a] = true;
		
		for(int i : list[a]) {
			int next = i;
			if(!v[next])
				dfs(next);
		}
	}

}
