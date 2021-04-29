/**
 * @author Minha Gwon
 * @date 2021. 4. 30.
 * 좌표 압축 
 * https://www.acmicpc.net/problem/18870
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
			set.add(n);
		}
		
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);

		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < list.size(); i++) {
			map.put(list.get(i), i);
		}

		for(int i = 0; i < N; i++) 
			sb.append(map.get(arr[i]) + " ");
		
		System.out.println(sb);
	}

}
