/**
 * @author Minha Gwon
 * 2020. 10. 18.
 * URL : https://www.acmicpc.net/problem/19238
 * #19238 - 스타트 택시 
 */

import java.util.*;
import java.io.*;

public class Main {
	public static int N, M, fuel;
	public static int[][] map;
	public static int[][] check;
	public static int tx, ty;
	public static Customer[] customer;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int ans = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		tx = Integer.parseInt(st.nextToken())-1;
		ty = Integer.parseInt(st.nextToken())-1;

		customer = new Customer[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken())-1;
			int sy = Integer.parseInt(st.nextToken())-1;
			int ex = Integer.parseInt(st.nextToken())-1;
			int ey = Integer.parseInt(st.nextToken())-1;
			customer[i] = new Customer(sx, sy, ex, ey, 0);
		}
		Arrays.sort(customer);

		check = new int[N][N];

		loop:
			for(int i = 0; i < M; i++) {
				//System.out.println();
				//System.out.println("first tx ty : " + tx + " " + ty);
				bfs(tx, ty); //택시 위치를 기준으로 가장 최단 거리 손님을 구하기 위해 거리를 1씩 늘려가며 bfs로 check맵을 구성한다. 

				int min = getMinDes(); //최단 거리 얻음
				//System.out.println("min : " + min);
				if(min == Integer.MAX_VALUE) { //최단 거리의 손님을 못찾았다면 종료 
					//System.out.println("!maxval");
					ans = -1;
					break loop;
				}

				for(int j = 0; j < M; j++) {
					if(customer[j].des == min) { //이미 customer 배열을 sort해서 정렬이 되어있기 때문에 가장 먼저 min값을 가진 손님이 최단 거리에 있는 손님
						//연료 확인 - 연료보다 최단거리가 크다면 갈수 X
						if(min > fuel) {
							ans = -1;
							break loop;
						} 

						//택시 위치 - 손님 출발지 위치로 이동
						tx = customer[j].startX;
						ty = customer[j].startY;
						fuel -= min;

						//손님 위치에서 목적지로 이동 
						bfs(tx, ty); //손님의 출발지에서 목적지까지 가장 최단 루트를 구함

						if(check[customer[j].endX][customer[j].endY] == -1) { //도착지가 벽에 가로 막혀서 갈수 없는 경우 
							ans = -1;
							break loop;
						}

						int useFuel = check[customer[j].endX][customer[j].endY];
						if(useFuel > fuel) {
							ans = -1;
							break loop;
						} else {
							fuel -= useFuel;
							fuel += (useFuel*2);
							tx = customer[j].endX;
							ty = customer[j].endY; //택시 위치 승객의 목적지로 이동 
							customer[j].des = -2; //목적지까지 도착했다는 의미로 des = -2
							ans = fuel;
						}
						break; //가장 작은 택시를 찾고 나면 break; 안해주면 계속 찾으러다님 
					}
				}
			}

		System.out.println(ans);
	}

	public static void initCheck() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				check[i][j] = -1;
			}
		}
	}

	public static void bfs(int x, int y) {
		initCheck(); //-1로 초기화 
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		check[x][y] = 0;

		while(!queue.isEmpty()) {
			int qx = queue.peek().x;
			int qy = queue.poll().y;

			for(int i = 0; i < 4; i++) {
				int nx = qx + dx[i];
				int ny = qy + dy[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= N) //범위를 벗어난다면 
					continue;

				if(map[nx][ny] == -1) { //벽이라면 
					continue;
				}

				if(check[nx][ny] != -1) { //이미 갔던 곳이라면 
					continue;
				}

				check[nx][ny] = check[qx][qy] + 1; //거리 1추가 

				queue.add(new Point(nx, ny));
			}	
		}
	}

	public static int getMinDes() { //최단 값을 구하면서 승객의 des 변수에 최단 값 저장 
		int min = Integer.MAX_VALUE;

		for(int i = 0; i < M; i++)  {
			Customer cus = customer[i];
			if(cus.des != -2 && check[cus.startX][cus.startY] != -1) { //이미 목적지에 도착한 승객들(des = -2), 택시가 가지 못한 곳은 제외 
				int des = check[cus.startX][cus.startY];
				cus.des = des;
				min = Math.min(min, des);
			}
		}

		return min;
	}


	public static void printMap(int[][] arr) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Customer implements Comparable<Customer> {
	int startX;
	int startY;
	int endX;
	int endY;
	int des;

	Customer(int startX, int startY, int endX, int endY, int des) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.des = des;
	}

	public int compareTo(Customer c) {
		if(this.startX > c.startX) {
			return 1;
		} else if(this.startX == c.startX) {
			if(this.startY > c.startY) {
				return 1;
			}
		}
		return -1;

	}
}
