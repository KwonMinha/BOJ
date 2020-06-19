import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2020. 6. 16.
 * 낚시왕
 * https://www.acmicpc.net/problem/17143
 */

public class Main {
	public static int R, C, M;
	public static ArrayList<Shark> shark;
	public static int[][] map;
	public static int ans = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		map = new int[R][C];
		shark = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();

			shark.add(new Shark(r-1, c-1, s, d, z));
			map[r-1][c-1] = z;
		}


		Collections.sort(shark);
		//		for(Shark s : shark) {
		//			System.out.println(s.r + " " + s.c);
		//		}

		System.out.println("처음 map");
		printMap();

		for(int i = 0; i < C; i++) {
			System.out.println("다시 시작 ");
			for(int j = 0; j < shark.size(); j++) {
				System.out.println("상어 잡아먹어야함 ");
				Shark s = shark.get(j);
				if(s.c == i) {
					ans += s.z;
					map[s.r][s.c] = 0;
					shark.remove(s);
					System.out.println("낚시꾼 움직이고 상어 잡음 ");
					printMap();
				}
			}
			move();
			System.out.println("상어 이동 후 ");
			printMap();
		}
		
		System.out.println(ans);

	}

	public static void printMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void move() {
		for(int[] i : map) 
			Arrays.fill(i, 0);

		for(int x = 0; x < shark.size(); x++) {
			Shark s = shark.get(x);
			int nr, nc, nd;

			System.out.println("상어 - r : " + s.r + ", c : " + s.c + ", 크기 : " + s.z + ", 방향 : " + s.d + ", 속력 : " + s.s);

			//상어 이동 
			if(s.d == 1 || s.d == 2) { //상어의 방향이 위, 아래일 경우 
				nr = s.r + s.s;
				nc = s.c;
				nd = s.d;

				while(nr > R-1) {
					nr = nr - R;
					if(nd == 1) {
						nd = 2;
					} else {
						nd = 1;
					}
				}			
			} else { //상어의 방향이 왼쪽, 오른쪽일 경우 
				nr = s.r;
				nc = s.c;
				nd = s.d;

				int speedCnt = s.s;
				while(speedCnt > 0) {
					if(nd == 4) {
						nc--;
						if(nc < 0) {
							nc++;
							nc++;
							nd = 3;
						}
					} else {
						nc++;
						if(nc >= C) {
							nc--;
							nc--;
							nd = 4;
						}
					}
					speedCnt--;
				}
			}

			//이동한 자리가 빈칸인지 이미 다른 상어가 있는지 확인 
			if(map[nr][nc] == 0) { //빈칸 0일 경우 
				map[nr][nc] = s.z;
				s.r = nr;
				s.c = nc;
				s.d = nd;
			} else { //이미 다른 상어가 있는 경우 
				if(map[nr][nc] < s.z) { //현재 상어가 이전의 상어보다 크기가 큰 경우 > 이전 상어가 먹힘 
					for(int i = 0; i < shark.size(); i++) {
						if(shark.get(i).r == nr && shark.get(i).c == nc) {
							shark.remove(i);
						}
					}
					map[nr][nc] = s.z;
					s.r = nr;
					s.c = nc;
					s.d = nd;
				} else { //크기가 작은 경우 > 현재 상어가 먹힘 
					shark.remove(s);
				}
			}
			//printMap();
		}
		

	}

}

class Shark implements Comparable<Shark> {
	int r, c, s, d, z;

	Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}

	@Override
	public int compareTo(Shark p) {
		if(this.c > p.c) {
			return 1;
		} else if(this.c == p.c) {
			if(this.r > p.r) {
				return 1;
			}
		}
		return -1;
	}
}
