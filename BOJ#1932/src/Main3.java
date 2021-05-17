/**
 * @author Minha Gwon
 * @date 2021. 5. 11.
 * 정수 삼각형 
 * https://www.acmicpc.net/problem/1932
 */

import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i+1; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
	
		for(int i = 1; i < n; i++) {
			for(int j = 0;j <= i; j++) {
				if(j == 0) { // 0번 열일 경우, 이전행의 자신과 같은열(오른쪽 대각선)값만 더해주면 됨 
					arr[i][j] += arr[i-1][j];
				} else { // 그 외의 열은 이전행의 자신과 같은 열(오른쪽 대각선), 자신-1 열(왼쪽 대각선)중 큰 값을 더함 
					arr[i][j] += Math.max(arr[i-1][j], arr[i-1][j-1]);
				}
			}			
		}
		
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			if(arr[n-1][i] > max) {
				max = arr[n-1][i];
			}
		}
        
		System.out.println(max);
	}
	
}