import java.util.ArrayList;
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
		for(Shark s : shark) {
			System.out.println(s.r + " " + s.c);
		}
		
		for(int i = 0; i < C; i++) {
			for(Shark s : shark) {
				if(s.c == i) {
					ans += s.z;
					map[s.r][s.c] = 0;
					shark.remove(s);
					move();
				}
			}
		}

	}
	
	public static void move() {
		for(Shark s : shark) {
			if(s.d == 1 || s.d == 2) { //상어의 방향이 위, 아래일 경우 
				int nr = s.r + s.s;
				int nd = s.d;
				while(nr > C) {
					nr -= C;
					if(nd == 1) {
						nd = 2;
					} else {
						nd = 1;
					}
				}
				if(map[nr][s.c] == 0) {
					map[nr][s.c] = s.z;
					s.r = nr;
				} else {
					if(map[nr][s.c] < s.z) {
						for(int i = 0; i < shark.size(); i++) {
							if(shark.get(i).r == nr && shark.get(i).c == s.c) {
								shark.remove(i);
							}
						}
						map[nr][s.c] = s.z;
						
					}
				}
				
			} else { //상어의 방향이 왼쪽, 오른쪽일 경우 
				
			}
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
