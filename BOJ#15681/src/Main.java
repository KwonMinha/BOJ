/**
 * @author Minha Gwon
 * @date 2021. 1. 6.
 * 트리와 쿼리 
 * https://www.acmicpc.net/problem/15681
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static LinkedList<Integer>[] subList;
	public static int[] parent;
	public static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		adjList = new LinkedList[N+1];
		subList = new LinkedList[N+1];
		parent = new int[N+1];
		size = new int[N+1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
			subList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		makeTree(R, -1);
		countSubTree(R);

		for(int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());

			System.out.println(size[q]);
		}

		br.close();
	}

	public static void makeTree(int cur, int p) {
		for(int next : adjList[cur]) {
			if(next != p) {
				subList[cur].add(next);
				parent[next] = cur;
				makeTree(next, cur);
			}
		}
	}

	public static void countSubTree(int cur) {
		size[cur] = 1;

		for(int next : subList[cur]) {
			countSubTree(next);
			size[cur] += size[next];	
		}
	}
}
