/**
 * @author Minha Gwon
 * @date 2021. 2. 28.
 * 수 정렬하기2
 * https://www.acmicpc.net/problem/2751
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++)
			pq.add(Integer.parseInt(br.readLine()));
		
		for(int i = 0; i < N; i++) 
			sb.append(pq.poll() + "\n");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
