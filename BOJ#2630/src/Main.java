/**
 * @author Minha Gwon
 * @date 2021. 4. 30.
 * 색종이 
 * https://www.acmicpc.net/problem/2630
 */

import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int N;
	
	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(check(N, 0, 0)) {
			System.out.println("0\n1");
		} else {
			System.out.println("start");
			solve(N/2, 0, 0);
			
			System.out.println(white + "\n" + blue);
		}
	}
	
	public static void solve(int n, int x, int y) {
		System.out.println("\n----n = " + n);
		if(n == 1) {
			System.out.println("n = 1");
			if(arr[x][y] == 1) {
				blue++;
				return;
			} else {
				white++;
				return;
			}
		}
		
		if(!check(n, x, y)) { // 1사분면 
			System.out.println("1 X");
			solve(n/2, x, y);
		} else {
			System.out.println("1 O - " + x + ", " + y);
			return;
		}
		
		if(!check(n, x, n/2*y)) { // 2사분면 
			System.out.println("2 X");
			solve(n/2, x, n/2*y);
		} else {
			System.out.println("2 O" + x + ", " + n/2*y);
			return;
		}
		
		if(!check(n, n/2*x, y)) { // 3사분면 
			System.out.println("3 X");
			solve(n/2, n/2*x, y);
		} else {
			System.out.println("3 O" + n/2*x + ", " + y);
			return;
		}
		
		if(!check(n, n/2*x, n/2*y)) { // 4사분면 
			System.out.println("4 X");
			solve(n/2, n/2*x, n/2*y);
		} else {
			System.out.println("4 O" + n/2*x + ", " + n/2*y);
			return;
		}
	}
	
	public static boolean check(int n, int x, int y) {
		int color = arr[x][y];
		
		for(int i = x; i < x+n; i++) {
			for(int j = y; j < y+n; j++) {
				if(arr[i][j] != color) {
					return false;
				}
			}
		}
		
		if(color == 1) {
			System.out.println("blue++");
			blue++;
		} else {
			System.out.println("white++");
			white++;
		}
		
		return true;
	}

}
