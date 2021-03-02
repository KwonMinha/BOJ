/**
 * @author Minha Gwon
 * @date 2021. 3. 2.
 * 여왕벌
 * https://www.acmicpc.net/problem/10836
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); 

		int M = Integer.parseInt(st.nextToken()); 
		int N = Integer.parseInt(st.nextToken()); 

		int[][] size = new int[M][M];

		for(int i = 0; i < M; i++) 
			Arrays.fill(size[i], 1);

		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " "); 
			int zero = Integer.parseInt(st.nextToken()); 
			int one = Integer.parseInt(st.nextToken()); 
			int two = Integer.parseInt(st.nextToken()); 

			// 제일 왼쪽 열 애벌레 키우기 
			for(int i = M-1; i > 0; i--) { 
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					size[i][0] += 1;
				} else if(two != 0) {
					two--;
					size[i][0] += 2;
				}
			}

			// 제일 위쪽 행 애벌레 키우기 
			for(int i = 0; i < M; i++) {
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					size[0][i] += 1;
				} else if(two != 0) {
					two--;
					size[0][i] += 2;
				}
			}
		}

		// 나머지 애벌레 키우기 
		for(int i = 1; i < M; i++) {
			for(int j = 1; j < M; j++)
				size[i][j] = Math.max(size[i-1][j], Math.max(size[i-1][j-1], size[i][j-1]));
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) { 
			for (int j = 0; j < M; j++) 
				sb.append(size[i][j] + " "); 
			sb.append("\n"); 
		}

		System.out.println(sb.toString());
	}

}
