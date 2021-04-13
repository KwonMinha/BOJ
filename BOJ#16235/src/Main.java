/**
 * @author Minha Gwon
 * @date 2021. 4. 13.
 * 나무 제테크 
 * https://www.acmicpc.net/problem/16235
 * 우선순위 큐로 나무 나이순 정렬 + 죽거나 살아있는 나무 ArrayList로 관리 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Tree implements Comparable<Tree>{
		int x;
		int y;
		int age;

		Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			//		if(o.x == x) {
			//			if(o.y == y) {
			//				return age - o.age;
			//			} else {
			//				return y - o.y;
			//			}
			//		} else {
			//			return x - o.x;
			//		}

			return this.age > o.age ? 1 : -1; // 나이만 오름차순 정렬하면 됨 - 첨에 위 코드와 같이 x, y, age 다 고려했다가 시간 초과 났음 
		}
	}
	
	static int N, M, K;
	static int[][] map;
	static int[][] amount;
	static ArrayList<Tree> deadList = new ArrayList<>(); // 죽은 나무 저장 
	static ArrayList<Tree> aliveList = new ArrayList<>(); // 살아있는 나무 저장 
	static PriorityQueue<Tree> pq = new PriorityQueue<>(); // 나무 저장 

	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map 크기 (1 ≤ N ≤ 10)
		M = Integer.parseInt(st.nextToken()); // 나무의 개수 
		K = Integer.parseInt(st.nextToken()); // 구할 년 수 

		map = new int[N][N]; // index 0부터 시작 - 각 땅에 양분이 얼마남았는지를 저장 
		amount = new int[N][N]; // 추가되는 양분의 양 저장 

		// 배열 초기화 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				amount[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}

		// 나무 정보 입력 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			pq.offer(new Tree(x, y, age));
		}

		for(int i = 0; i < K; i++) { // K년까지 사계절 반복 
			spring();
			summer();
			fall();
			winter();
		}

		System.out.println(pq.size());
	}

	public static void spring() {
		PriorityQueue<Tree> tempPq = new PriorityQueue<>();

		while(!pq.isEmpty()) {
			Tree tree = pq.poll();

			if(tree.age <= map[tree.x][tree.y]) { // 나이만큼 양분을 먹을 수 있다면 -> 나이 1 증가 
				map[tree.x][tree.y] -= tree.age;

				if((tree.age + 1) % 5 == 0) { // 나이가 5의 배수인 경우만 가을에 번식하기 위해 List에 추가 
					aliveList.add(new Tree(tree.x, tree.y, tree.age + 1));
				}

				tempPq.add(new Tree(tree.x, tree.y, tree.age + 1));
			} else { // 양분 부족 -> 죽음 
				deadList.add(new Tree(tree.x, tree.y, tree.age));
			}
		}

		pq = new PriorityQueue<>(tempPq); // 살아있는 나무만 저장한 tempPq로 pq 초기화 
	}

	public static void summer() {
		for(int i = 0; i < deadList.size(); i++) {
			Tree tree = deadList.get(i);
			map[tree.x][tree.y] += tree.age / 2;
		}
		
		deadList.clear();
	}

	public static void fall() {	
		for(int i = 0; i < aliveList.size(); i++) {
			Tree tree = aliveList.get(i);

			for(int j = 0; j < 8; j++) { // 살아있는 나무의 나이가 5의 배수인 경우 8방향 번식 
				int nx = tree.x + dx[j];
				int ny = tree.y + dy[j];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue; // 범위를 벗어나면 pass 

				pq.offer(new Tree(nx, ny, 1)); // 나이 1 새로운 나무 추가 
			}
		}

		aliveList.clear();
	}

	public static void winter() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] += amount[i][j]; // 양분 추가 
			}
		}
	}

}
