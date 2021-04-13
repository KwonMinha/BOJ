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
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] amount;
	static ArrayList<Tree> treeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map 크기 (1 ≤ N ≤ 10)
		M = Integer.parseInt(st.nextToken()); // 나무의 개수 
		K = Integer.parseInt(st.nextToken()); // 구할 년 수 

		map = new int[N][N]; // 0부터 시작 - 각 땅에 양분이 얼마남았는지를 저장 
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

			treeList.add(new Tree(x, y, age, 1)); //isAlive 1 : 살아있음 / 0 : 이번 봄에 죽음 / -1 : 이전에 죽어서 이미 양분이 됨 
		}

		Collections.sort(treeList);

		// 트리 리스트 출력 
//		for(int i = 0; i < M; i++) {
//			Tree t = treeList.get(i);
//			System.out.println(t.x + " " + t.y + " " + t.age);
//		}
		
		for(int i = 0; i < K; i++) { // K년까지 사계절 반복 
			System.out.println("현재 년도 : " + (i+1) + " / 현재 맵 ");
			print(map);
			
			spring();
			summer();
			fall();
			winter();
			
			System.out.println("size : " + treeList.size());
			System.out.println();
		}
		
		int answer = 0;
		
		for(int i = 0; i < treeList.size(); i++) { // K년까지 살아남은 나무 구하기 
			if(treeList.get(i).isAlive == 1) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	public static void spring() {
		for(int i = 0; i < treeList.size(); i++) {
			Tree tree = treeList.get(i);

			System.out.println("봄 - 현재 나무 " + tree.x + " " + tree.y + " " + tree.age);
			if(tree.isAlive == -1) { // 이미 죽은 나무라면 pass
				System.out.println("봄 - 죽은 나무 pass ");
				continue;
			}

			if(tree.age <= map[tree.x][tree.y]) { // 나이만큼 양분을 먹을 수 있다면 -> 나이 1 증가 
				map[tree.x][tree.y] -= tree.age;
				tree.age += 1;
				
				System.out.println("봄 - 양분 먹음 " + tree.x + " " + tree.y + " " + tree.age);
			} else { // 양분 부족 -> 죽음 
				tree.isAlive = 0;
				System.out.println("봄 - 죽음 ");
			}
		}
	}
	
	public static void summer() {
		for(int i = 0; i < treeList.size(); i++) {
			Tree tree = treeList.get(i);
			
			if(tree.isAlive == 0) {
				map[tree.x][tree.y] += tree.age / 2; // 봄에 죽은 나무 양분으로 추가 
				tree.isAlive = -1;
				System.out.println("여름 - 봄에 죽은 나무 양분 추가 - x : " + tree.x + ", y : " + tree.y + ", 양 : " + tree.age/2 + ", 갱신 값 : "+ map[tree.x][tree.y]);
			}
		}
		
		System.out.println("여름 끝나고 맵 ");
		print(map);
	}
	
	public static void fall() {
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		for(int i = 0; i < treeList.size(); i++) {
			Tree tree = treeList.get(i);
			
			System.out.println("가을 - 현재 나무 " + tree.x + " " + tree.y + " " + tree.age);
			if(tree.isAlive == 1 && tree.age % 5 == 0) { // 살아있는 나무의 나이가 5의 배수인 경우 8방향 번식 
				System.out.println("가을 - 살아있는 나무 5배수 ");
				for(int j = 0; j < 8; j++) {
					int nx = tree.x + dx[j];
					int ny = tree.y + dy[j];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) { // 범위를 벗어나면 pass 
						continue;
					}
					
					treeList.add(new Tree(nx, ny, 1, 1));
					System.out.println("가을 - 나이 1 나무 추가");
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
		
		System.out.println("\n겨울 - 양분 추가된 맵 ");
		print(map);
	}
	
	public static void print(int[][] arr) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
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
