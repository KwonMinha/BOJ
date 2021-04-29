/**
 * @author Minha Gwon
 * @date 2021. 4. 30.
 * 집합 
 * https://www.acmicpc.net/problem/11723
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());
		
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			String oper = st.nextToken();
			
			if(oper.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				set.add(x);
			} else if(oper.equals("remove")) {
				int x = Integer.parseInt(st.nextToken());
				
				if(set.contains(x)) 
					set.remove(x);
			} else if(oper.equals("check")) {
				int x = Integer.parseInt(st.nextToken());
				
				if(set.contains(x)) 
					sb.append("1\n");
				else 
					sb.append("0\n");
			} else if(oper.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				
				if(set.contains(x)) 
					set.remove(x);
				else 
					set.add(x);
			} else if(oper.equals("all")) {
				set.clear();
				
				for(int j = 1; j <= 20; j++) {
					set.add(j);
				}
			} else if(oper.equals("empty")) {
				set.clear();
			}
		}

		System.out.println(sb);
	}

}
