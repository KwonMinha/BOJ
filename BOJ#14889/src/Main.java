/**
 * @author Minha Gwon
 * @date 2021. 4. 14.
 * 스타트와 링크 
 * https://www.acmicpc.net/problem/14889
 */

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		combination(1, N/2); // 두 팀으로 나누기 
		
		System.out.println(min);
	}
	
	public static void combination(int start, int r) {
		if(r == 0) {
			ArrayList<Integer> listA = new ArrayList<>();
			ArrayList<Integer> listB = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				if(visited[i]) {
					listA.add(i);
				} else {
					listB.add(i);
				}
			}
			
			// 팀 능력치 계산 
			int resultA = calculate(listA);
			int resultB = calculate(listB);
			
			min = Math.min(min, Math.abs(resultA - resultB));
			
			return;
		} else {
			for(int i = start; i <= N; i++) {
				visited[i] = true;
				combination(i+1, r-1);
				visited[i] = false;
			}
		}
	}
	
	public static int calculate(ArrayList<Integer> list) {
		int result = 0;
		
		for(int i = 0; i < list.size()-1; i++) {
			for(int j = i+1; j < list.size(); j++) {
				result += arr[list.get(i)][list.get(j)] + arr[list.get(j)][list.get(i)];
			}
		}
		
		return result;
	}

}