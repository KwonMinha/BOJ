/**
 * @author Minha Gwon
 * @date 2020. 5. 28.
 * ABCDE
 * https://www.acmicpc.net/problem/13023
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static LinkedList<Integer>[] list;
	private static int ans = 0;
	private static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		n = N;
		m = M;
		
		list = new LinkedList[N];
		v = new boolean[N];
		for(int i = 0; i < N; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			list[n1].add(n2);
			list[n2].add(n1);
		}
		
		for(int i = 0; i < N; i++) {
			dfs(i, 1);
			
			if(ans == 1)
				break;
		}
		
		System.out.println(ans);

		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int start, int depth) {
		//System.out.println(start + " d : " + depth);
		if(depth == m || ans == 1) {
			ans = 1;
			return;
		}
		
		v[start] = true;
	
//	    for(int next : list[start]) {
//            if(!v[next]) {
//                dfs(next,depth+1);
//            }
//        }
	    
	    Iterator<Integer> iter = list[start].listIterator();
		while (iter.hasNext()) {
			int n = iter.next();
			if (!v[n]) {
				dfs(n, depth+1);
			}
		}
		
		v[start] = false;
		
	}

}
