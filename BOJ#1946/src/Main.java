/**
 * @author Minha Gwon
 * @date 2021. 1. 24.
 * 신입사원 
 * https://www.acmicpc.net/problem/1946
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Grade implements Comparable<Grade> {
	int a;
	int b;
	
	Grade(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Grade o) {
		if(this.a > o.a) {
			return 1;
		} else if(this.a == o.a) {
			if(this.b > o.b) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			int N = sc.nextInt();
			ArrayList<Grade> list = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				list.add(new Grade(a, b));
			}
			
			Collections.sort(list); // 서류 점수 기준 오름차순 정렬 
			
			for(Grade g : list) {
				System.out.println(g.a + " " + g.b);
			}
		
			int ans = 1; // 서류 1등은 무조건 통과 
			
			for(int i = 1; i < N; i++) { // 0번 인덱스 1등은 통과했으니 1번 인덱스 부터 시작 
				int a1 = list.get(i).a;
				int b1 = list.get(i).b;
				
				boolean flag = true;
				for(int j = 0; j < i; j++) {
					int a2 = list.get(j).a;
					int b2 = list.get(j).b;
					 
					if(b1 > b2) { // 현재 인덱스 이전의 서류 등수 높은 애들보다 면접은 좋아야함 
						flag = false;
						break;
					}
				}
				
				if(flag) 
					ans++;
			}
			
			System.out.println(ans);
		}	
	}
}
