/**
 * @author Minha Gwon
 * @date 2021. 3. 16.
 * MooTube (Silver) 
 * https://www.acmicpc.net/problem/14461
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int Q = sc.nextInt();

		// 동영상들의 연결 구조를 나타내기위해 인접 리스트 생성 
		ArrayList<int[]>[] adjList = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<int[]>(); // 유사도 r값까지 저장하기 위해 int 배열을 저장하게 함 
		}

		for(int i = 0; i < N-1; i++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int r = sc.nextInt();

			adjList[p].add(new int[] {q, r}); // 배열의 0번 인덱스는 인접 정점의 번호, 1번 인덱스는 유사도 값 
			adjList[q].add(new int[] {p, r});
		}

		for(int i = 0; i < Q; i++) {
			int k = sc.nextInt(); // 유사도 
			int v = sc.nextInt(); // 시작 정점 
			
			int cnt = 0; // 유사도 k 이상인 동영상이 몇개인지 체크할 변수 
		
			// bfs를 이용해 v와 연결된 모든 정점간의 유사도를 구함 
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(v);
			
			boolean[] visited = new boolean[N+1];
			visited[v] = true;
			
			while(!queue.isEmpty()) {
				int next = queue.poll();
				
				// for(int[] arr : adjList[next]) { } // 향상된 for문 
				for(int j = 0; j < adjList[next].size(); j++) {
					int[] arr = adjList[next].get(j);
					
					// 문제 조건 - 임의의 두 동영상의 유사도는 그 경로의 모든 연결들의 유사도 중 최소값 
					if(!visited[arr[0]] && arr[1] >= k) { // k보다 작아지면 그 뒤의 탐색들도 유사도가 k보다 작아지므로 k이상만 탐색
						cnt++;
						queue.offer(arr[0]);
						visited[arr[0]] = true;
					}
				}
			}
			
			System.out.println(cnt);
		}
	}
}
