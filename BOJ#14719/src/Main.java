/**
 * @author Minha Gwon
 * @date 2020. 10. 25.
 * 빗물
 * https://www.acmicpc.net/problem/14719
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int H = sc.nextInt();
		int W = sc.nextInt();
		int answer = 0;

		int[] block = new int[W];
		for(int i = 0; i < block.length; i++) {
			block[i] = sc.nextInt();
		}

		for(int i = 1; i < block.length-1; i++) {
			//현재 i를 기준으로 왼쪽에서 최대 블록 수 구함 
			int left = 0;
			for(int j = i-1; j >= 0; j--) {
				left = Math.max(block[j], left);
			}

			//오른쪽에서 최대 블록 수 구함 
			int right = 0;
			for(int j = i+1; j < block.length; j++) {
				right = Math.max(block[j], right);
			}
			int min = Math.min(left, right); 
			//현재 블록수가 오른쪽, 왼쪽 최대 값보다 큰 경우는 넘어감 
			if(block[i] >= min) {
				continue;
			}
			
			//아니면 둘 중 더 작은 값으로 변경하고, 빗물수 구하기 
			int temp = block[i];
			block[i] = min; 
			answer += min - temp;
		}

		System.out.println(answer);
	}

}