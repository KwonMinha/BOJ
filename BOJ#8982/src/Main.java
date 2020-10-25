/**
 * @author Minha Gwon
 * @date 2020. 10. 26.
 * 수족관1
 * https://www.acmicpc.net/problem/8982
 */

import java.util.*;
import java.io.*;

public class Main {
	public static final int MAX = 40001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		br.readLine(); //(0, 0) 제외 
		
		int[] depth = new int[MAX];
		int lastIdx = 0; //수족관 총 열의 수를 알아내기 위한 변수 
		
		//수족관 높이 입력 
		// 수평 선분 (세로줄 번호, 가로줄 번호).ㅡ.(세로줄 번호, 가로줄 번호) -> 한 칸 = 한 열 
		for(int i = 0; i < N/2 -1; i++) { //(0, 0) 제외후 수평 선분 (세로줄 번호, 가로줄 번호) 묶음으로 보아 /2 만큼만 반복 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			//예를들어 (3, 2) (5, 2)의 경우처럼 높이가 같아 수평 선분이 .ㅡ. 가 아니라 더 긴 .ㅡ.ㅡ. 이런 선분도 있기 때문에 x1에서 x2까지 
			for(int j = x1; j < x2; j++) { //
				depth[j] = y1; //y1즉 세로의 값이 수족관의 높이와 같음 
			}
			
			lastIdx = x2 - 1; //가장 마지막의 x2-1 값이 총 칸의 수 (총 8칸이지만 인덱스는 0부터 시작할 것이기에 -1)
		}
		
		br.readLine(); // 마지막 (A, 0) 제외 
		
		//구멍 위치 입력 
		int K = Integer.parseInt(br.readLine());
		Node[] hole = new Node[K];
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int holeIndex = Integer.parseInt(st.nextToken());
			int holeDepth = Integer.parseInt(st.nextToken());
			
			hole[i] = new Node(holeIndex, holeDepth);
		}
		
	}

}

class Node {
	int index;
	int depth;
	
	Node(int index, int depth) { 
		this.index = index;
		this.depth = depth;
	}
}
