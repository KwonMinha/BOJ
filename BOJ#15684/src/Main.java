import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Point>[][] map;
	static int N, M, H;
	static ArrayList<Point> pointList;
	static boolean[][][][] visited;
	static int min = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		if(M == 0) {
			System.out.println("0");
			System.exit(0);
		}


		map = new ArrayList[H+1][N+1];
		for(int i = 1; i < H+1; i++) {
			for(int j = 1; j < N+1; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		//System.out.println((H+1) + " " + (N+1) + "\n");

		visited = new boolean[H+1][N+1][H+1][N+1];

		//pointList = new ArrayList<>();

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			Point point1 = new Point(a, b, a, b+1);
			Point point2 = new Point(a, b+1, a, b);

			map[a][b].add(point1);
			map[a][b+1].add(point2);

			//System.out.println((a) + " " + (b+1) + "\n");

			visited[a][b][a][b+1] = true;
			visited[a][b+1][a][b] = true;

			//			pointList.add(point1);
			//			pointList.add(point2);
		}


		//		System.out.println("first map ");
		//		print();

		for(int i = 1; i <= N/2*H; i++) { // 1~ 개까지 가로선 놓아보기 
			//System.out.println(i + "개 놓음 ");
			dfs(i, 0, i);
		}

		//		Point point1 = new Point(1, 3, 1, 4);
		//		Point point2 = new Point(1, 4, 1, 3);
		//		map[1][3].add(point1);
		//		map[1][4].add(point2);
		//
		//		point1 = new Point(4, 2, 4, 3);
		//		point2 = new Point(4, 3, 4, 2);
		//		map[4][2].add(point1);
		//		map[4][3].add(point2);
		//
		//		point1 = new Point(3, 4, 3, 5);
		//		point2 = new Point(3, 5, 3, 4);
		//		map[3][4].add(point1);
		//		map[3][5].add(point2);
		//		
		//		check(3);

		System.out.println(-1);
	}

	public static void print() {
		for(int i = 1; i < H+1; i++) {
			for(int j = 1; j < N+1; j++) {
				//System.out.println("i : " + i + ", j : " + j);
				System.out.println(map[i][j].size());
				//				for(int k = 0; k < map[i][j].size(); k++) {
				//					System.out.println(map[i][j].get(k).x1 + " " + map[i][j].get(k).y1);
				//				}
			}
		}
		System.out.println();
	}

	public static void dfs(int n, int depth, int cnt) {
		if(depth == n) {
			//print();

			check(cnt);

			//System.out.println("return \n");
			return;
		}

		for(int i = 1; i < H+1; i++) {
			for(int j = 1; j < N; j++) {
				//System.out.println("i : " + i + ", j : " + j);
				int[][] dy = {{0, 1}, {-1, 0}, {1, 2}};

				boolean flag = true;
				for(int k = 0; k < 3; k++) {
					int ny1 = j + dy[k][0];
					int ny2 = j + dy[k][1];

					if(ny1 <= 0 || ny2 <= 0 || ny1 >= N+1 || ny2 >= N+1) continue;

					//System.out.println("k : " + k + ", ny1 : " + ny1 + ", ny2 : " + ny2);


					if(visited[i][ny1][i][ny2]) {
						//System.out.println("visited true X");
						flag = false;
						break;
					}
				}

				if(flag) {
					//System.out.println("OK!!!~");
					Point p1 = new Point(i, j, i, j+1);
					Point p2 = new Point(i, j+1, i, j);

					map[i][j].add(p1);
					map[i][j+1].add(p2);

					//System.out.println("* i : " + i + ", j : " + j);
					visited[i][j][i][j+1] = true;
					visited[i][j+1][i][j] = true;

					dfs(n, depth+1, cnt);

					visited[i][j][i][j+1] = false;
					visited[i][j+1][i][j] = false;

					map[i][j].remove(p1);
					map[i][j+1].remove(p2);
				}
			}
		}
	}

	public static void check(int cnt) {
		boolean flag = true;

		for(int i = 1; i < N+1; i++) {
			int curX = 1;
			int curY = i;
			
			while(curX != H+1) {
				if(map[curX][curY].isEmpty()) {
					curX++;
				} else {
					curY = map[curX][curY].get(0).y2;
					curX++;
				}
			}

			if(curY != i) {
				return;
			}
		}

		if(flag) {
			System.out.println(cnt > 3 ? -1 : cnt);
			System.exit(0);
		}
	}
}

class Point {
	int x1;
	int x2;
	int y1;
	int y2;

	Point(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}
