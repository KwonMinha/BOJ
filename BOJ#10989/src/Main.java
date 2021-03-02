/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 수 정렬하기3
 * https://www.acmicpc.net/problem/10989
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine()); 
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) 
			arr[i] = Integer.parseInt(br.readLine()); 
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) 
			sb.append(arr[i] + "\n");
		
		System.out.println(sb);
	}

}
