/**
 * @author Minha Gwon
 * @date 2021. 7. 5.
 * 파일 합치기
 * https://www.acmicpc.net/problem/11066
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			int[] arr = new int[K];
			
			for(int k = 0; k < K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[k] = Integer.parseInt(st.nextToken());
			}
			
		}
		

	}

}
