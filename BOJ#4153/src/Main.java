/**
 * @author Minha Gwon
 * @date 2021. 4. 30.
 * 직각삼각형
 * https://www.acmicpc.net/problem/4153
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			double a = Integer.parseInt(st.nextToken());
			
			if(a == 0) break;
			
			double b = Integer.parseInt(st.nextToken());	
			double c = Integer.parseInt(st.nextToken());	
	
			if((Math.pow(a, 2) + Math.pow(b, 2)) == Math.pow(c, 2)) {
				sb.append("right\n");
			} else if((Math.pow(b, 2) + Math.pow(c, 2)) == Math.pow(a, 2)){
				sb.append("right\n");
			} else if((Math.pow(a, 2) + Math.pow(c, 2)) == Math.pow(b, 2)){
				sb.append("right\n");
			} else {
				sb.append("wrong\n");
			}
		}
		
		System.out.println(sb);
	}

}
