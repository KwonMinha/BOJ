/**
 * @author Minha Gwon
 * @date 2021. 1. 2.
 * 사회망 서비스 (SNS))
 * https://www.acmicpc.net/problem/2533 
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static int[][] adjArray;
	public static boolean[] visited;
	public static int[] vertex;
	public static int N;
	public static int answer = 0;
	static boolean isSNS = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		adjList = new LinkedList[N+1];
		visited = new boolean[N+1];
		vertex = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		for(int i = 1; i <= N; i++) {
			if(!isSNS) {
				//System.out.println(i);
				combination(1, i);
			}
		}
		
		System.out.println(answer);
		
	}
	
	public static void combination(int start, int r) {
		if(isSNS) {
			return;
		}
		
		if(r == 0) {
			SNS();
			return;
		} else {
			for(int i = start; i < vertex.length; i++) {
				vertex[i] = 1;
				combination(i+1, r-1);
				vertex[i] = 0;
			}
		}
	}
	
	public static void SNS() {
		Arrays.fill(visited, false);
		int cnt = 0;
		
		for(int i = 1; i < vertex.length; i++) {
			if(vertex[i] == 1) { 
				visited[i] = true;
				cnt++;
				
				for(int j = 0; j < adjList[i].size(); j++) {
					if(!visited[adjList[i].get(j)])
						visited[adjList[i].get(j)] = true;
				}
			}
		}
		
		boolean flag = true;
		for(int i = 1; i < visited.length; i++) {
			if(visited[i] == false) {
				flag = false;
				break;
			}
		}
		
		if(flag) {
			answer = cnt;
			isSNS = true;
		} 
		
	}
}
