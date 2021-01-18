/**
 * @author Minha Gwon
 * @date 2021. 1. 18.
 * 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Integer>[] adjList;
	public static boolean[] visited;
	public static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		result =  new int[N+1];

		// 인접 리스트 만들기 
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		// 인접 리스트에 신뢰 컴퓨터 관계 넣어주기 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
		}

		// 1번 컴퓨터부터 dfs로 인접한 신뢰 컴퓨터 찾기 
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			dfs(i);
		}
		
		// max값 찾기 
		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, result[i]);
		}

		// max값 가진 컴퓨터 출력 
		for(int i = 1; i <= N; i++) {
			if(result[i] == max) {
				bw.write(i + " ");
			}
		}

		bw.flush();
		bw.close();
	}

	public static void dfs(int v) {
		visited[v] = true;

		Iterator<Integer> iter = adjList[v].listIterator();
		while (iter.hasNext()) {
			int w = iter.next();
			if (!visited[w]) { 
				result[w]++;
				dfs(w);
			}
		}
	}

}
