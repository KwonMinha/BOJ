/**
 * @author Minha Gwon
 * @date 2021. 4. 13.
 * 나무 제테크 
 * https://www.acmicpc.net/problem/16235
 * ArrayList로 나무 관리 - Collections.sort()로 정렬 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main4 {
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
		public int compareTo(Tree o) { // 나이만 오름차순 정렬하면 됨 - 첨에 x, y, age 다 고려했다가 시간 초과 났음 
			return this.age - o.age;
		}
	}
	
	static int N, M, K;
	static int[][] map;
	static int[][] amount;
	static ArrayList<Tree> treeList;
	static ArrayList<Tree> deadList = new ArrayList<>();

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

		treeList = new ArrayList<>(); // 나무 저장 

		// 나무 정보 입력 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			treeList.add(new Tree(x, y, age)); 
		}

		for(int i = 0; i < K; i++) { // K년까지 사계절 반복 
			Collections.sort(treeList); // 나이 작은 나무부터 시작하기 위해 매번 오름차순 정렬 (가을에 새로운 나무가 추가되기 때문)
			spring();
			summer();
			fall();
			winter();
		}

		System.out.println(treeList.size());
	}

	public static void spring() {
		ArrayList<Tree> tempList = new ArrayList<>();

		for(int i = 0; i < treeList.size(); i++) {
			Tree tree = treeList.get(i);

			if(tree.age <= map[tree.x][tree.y]) { // 나이만큼 양분을 먹을 수 있다면 -> 나이 1 증가 
				map[tree.x][tree.y] -= tree.age;
				tempList.add(new Tree(tree.x, tree.y, tree.age+1));
			} else { // 양분 부족 -> 죽음 
				deadList.add(new Tree(tree.x, tree.y, tree.age));
			}
		}

		// 죽을 나무 죽고, 산 나무들로 새롭게 List 구성 
//		treeList.clear();
//		treeList.addAll(tempList);

		treeList = new ArrayList<>(tempList);
	}

	public static void summer() {
		for(int i = 0; i < deadList.size(); i++) {
			Tree tree = deadList.get(i);
			map[tree.x][tree.y] += tree.age / 2; // 봄에 죽은 나무 양분으로 추가 
		}

		deadList.clear(); // remove() 삭제하면 시간 초과 - 그냥 index로 접근만 하고 확 밀어버리자 
	}

	public static void fall() {
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

		for(int i = 0; i < treeList.size(); i++) {
			Tree tree = treeList.get(i);

			if(tree.age % 5 == 0) { // 살아있는 나무의 나이가 5의 배수인 경우 8방향 번식 
				for(int j = 0; j < 8; j++) {
					int nx = tree.x + dx[j];
					int ny = tree.y + dy[j];

					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue; // 범위를 벗어나면 pass 

					treeList.add(new Tree(nx, ny, 1)); // 나이 1 새로운 나무 추가 
				}
			}
		}
	}

	public static void winter() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] += amount[i][j]; // 양분 추가 
			}
		}
	}

}