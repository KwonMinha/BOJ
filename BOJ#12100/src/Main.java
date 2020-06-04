/**
 * @author Minha Gwon
 * @date 2020. 6. 4.
 * 2048(Easy)
 * https://www.acmicpc.net/problem/12100
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static long[][] board;
	private static long max = 0;
	private static int n;
	private static long[] output;
	private static LinkedList<Long> boardList;
	private static LinkedList<Long> num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		n = N;
		board = new long[N][N];
		output = new long[5];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);

		bw.write(max + "");	
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int depth) {
		if(depth == 5) {
			moveBlock();
			return;
		}

		for(int i = 0; i < 4; i++) {
			output[depth] = i;
			dfs(depth+1);
		}
	}

	public static void moveBlock() {
		for(int i = 0; i < output.length; i++) {
			long dir = output[i];

			for(int j = 0; j < n; j++) {
				boardList = new LinkedList<Long>();
				for(int k = 0; k < n; k++) {
					if(dir == 0 || dir == 1) 
						boardList.add(board[k][j]);
					else
						boardList.add(board[j][k]);
				}

				for(int k = 0; k < boardList.size(); k++) {
					if(boardList.get(k) == 0) {
						boardList.remove(k);
						k--;
					}
				}

				//이동하며 0을 삭제한 만큼 0 채워주기 
				if(dir == 0 || dir == 2) {
					while(boardList.size() != n)
						boardList.addLast((long) 0);
				} else {
					while(boardList.size() != n)
						boardList.addFirst((long) 0);
				}

				//board에 이동 완료한 값 넣어주기 
				if(dir == 0 || dir == 1) {
					for(int k = 0; k < n; k++)
						board[k][j] = boardList.get(k);
				} else {
					for(int k = 0; k < n; k++)
						board[j][k] = boardList.get(k);
				}
			}
			
			//move후 board의 상태 출력 
			System.out.println(dir + " move");
			print();

			//상하좌우 모든 숫자 값 이동 후 블록 합치기 merge
			mergeBlock(dir);

			//merge후 board의 상태 출력 
			System.out.println(dir + " merge");
			print();

			//board의 모든 줄을 다 이동시키고 merge했다면 max 구하기 
			if(i == output.length-1) {
				getMax();
			}
		}
	}

	public static void mergeBlock(long dir) {
		//한 줄씩 merge해주기 위해 값 가져와서 LinkedList에 저장 
		for(int i = 0; i < n; i++) {
			num = new LinkedList<Long>();

			for(int j = 0; j < n; j++) {
				if(dir == 0 || dir == 1)
					num.add(board[j][i]);
				else
					num.add(board[i][j]);
			}

			//merge 
			if(dir == 0 || dir == 2) {
				for(int k = 0; k < num.size()-1; k++) {
					if(!num.get(k).equals(0) && num.get(k).equals(num.get(k+1))) {
						long val = num.get(k)*2;
						num.remove(k);
						num.remove(k);
						num.add(k, val);
					}
				}
			} else {
				for(int k = num.size()-1; k > 0; k--) {
					if(!num.get(k).equals(0) && num.get(k).equals(num.get(k-1))) {
						long val = num.get(k)*2;
						num.remove(k);
						num.remove(k);
						num.add(k-1, val);
					}
				}
			}	

			//merge 과정에서 줄어든 자리수 맞춰 주기 
			if(dir == 0 || dir == 2) {
				while(num.size() != n)
					num.addLast((long) 0);
			} else {
				while(num.size() != n)
					num.addFirst((long) 0);
			}

			//merge한 값 배열에 넣어주기 
			if(dir == 0 || dir == 1) {
				for(int k = 0; k < n; k++) {
					board[k][i] = num.get(k);
				}
			} else {
				for(int k = 0; k < n; k++) {
					board[i][k] = num.get(k);
				}
			}
		}
	}

	//board 출력 함수 
	public static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//board에 있는 값들을 전부 합해 max를 구하는 함수 
	public static void getMax() {
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++) 
				max = Math.max(board[i][j], max);
	}

}

