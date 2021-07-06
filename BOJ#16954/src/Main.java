/**
 * @author Minha Gwon
 * @date 2021. 7. 7.
 * 움직이는 미로 탈출 
 * https://www.acmicpc.net/problem/16954
 */

import java.util.*;
import java.io.*;

public class Main {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[8][8];
		
		for(int i = 0; i < 8; i++) {
			String temp = br.readLine();
			
			for(int j = 0; j < 8; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
	}
	
	static void bfs() {
		
	}

}
