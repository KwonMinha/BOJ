/**
 * @author Minha Gwon
 * @date 2021. 3. 20.
 * 숫자고르기 
 * https://www.acmicpc.net/problem/2668 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int[] arr;
	static boolean[] visited;
	static boolean[] finished;
	static int count;
	static ArrayList<Integer> cycleList;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		arr = new int[n+1];
		visited = new boolean[n+1];
		finished = new boolean[n+1];
		count = 0;
		cycleList = new ArrayList<>();

		for(int i = 1; i < n+1; i++) 
			arr[i] = sc.nextInt();

		// dfs를 돌며 사이클을 찾아야함 
		for(int i = 1; i < n+1; i++)
			dfs(i);

		System.out.println(count); // 뽑힌 정수들의 개수 
		
		Collections.sort(cycleList); // 뽑힌 정수 오름차순 정렬 
		for(int i : cycleList) {
			System.out.println(i);
		}

	}

	public static void dfs(int now) {
		if(visited[now])
			return;

		visited[now] = true;

		int next = arr[now];

		if(!visited[next]) {
			dfs(next);
		} else {
			if(!finished[next]) {
				cycleList.add(next);
				count++;
				
				// 사이클 탐색 
				for(int i = next; i != now; i = arr[i]) {
					count++;
					cycleList.add(arr[i]);
				}
			}
		}

		finished[now] = true; 
	}

}