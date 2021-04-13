/**
 * @author Minha Gwon
 * @date 2021. 4. 13.
 * 나무 제테크 
 * https://www.acmicpc.net/problem/16235
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] amount;
	static ArrayList<Tree> treeList;
	static Stack<Tree> stack = new Stack<>();
	
	static PriorityQueue<Tree> pq;

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
		
		pq = new PriorityQueue<>();

		// 나무 정보 입력 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			treeList.add(new Tree(x, y, age, 1)); //isAlive 1 : 살아있음 / 0 : 이번 봄에 죽음
			pq.add(new Tree(x, y, age, 1));
		}
		

		
		Collections.sort(treeList);

		for(int i = 0; i < K; i++) { // K년까지 사계절 반복 
			spring();
			summer();
			fall();
			winter();
		}
		
		//System.out.println(treeList.size());
	}

	public static void spring() {
		for(int i = 0; i < treeList.size(); i++) {
			Tree tree = treeList.get(i);

			if(tree.age <= map[tree.x][tree.y]) { // 나이만큼 양분을 먹을 수 있다면 -> 나이 1 증가 
				map[tree.x][tree.y] -= tree.age;
				tree.age += 1;
			} else { // 양분 부족 -> 죽음 
				stack.add(tree);
				treeList.remove(i);
				i--;
			}
		}
	}
	
	public static void summer() {
		while(!stack.isEmpty()) {
			Tree tree = stack.pop();
			map[tree.x][tree.y] += tree.age / 2;
		}
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
				
					treeList.add(i, new Tree(nx, ny, 1, 1));
					i++;
					
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

class Tree implements Comparable<Tree>{
	int x;
	int y;
	int age;
	int isAlive;

	Tree(int x, int y, int age, int isAlive) {
		this.x = x;
		this.y = y;
		this.age = age;
		this.isAlive = isAlive;
	}

	@Override
	public int compareTo(Tree o) {
		if(o.x == x) {
			if(o.y == y) {
				return age - o.age;
			} else {
				return y - o.y;
			}
		} else {
			return x - o.x;
		}
	}
}
