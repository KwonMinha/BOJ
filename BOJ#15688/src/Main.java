/**
 * @author Minha Gwon
 * @date 2021. 3. 3.
 * 수 정렬하기5 
 * https://www.acmicpc.net/problem/15688
 * Java8로 해야 통과 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine()); 
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < N; i++) 
			list.add(Integer.parseInt(br.readLine())); 
		
		Collections.sort(list); // Arrays.sort()도 가능 
		
		StringBuilder sb = new StringBuilder();
		for(int i : list) 
			sb.append(i + "\n");
		
		System.out.println(sb);
	}

}
