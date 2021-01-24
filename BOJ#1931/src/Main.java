/**
 * @author Minha Gwon
 * @date 2021. 1. 23.
 * 회의실 배정
 * https://www.acmicpc.net/problem/1931
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Time implements Comparable<Time> {
	int start;
	int end;

	Time(int start, int end) {
		this.start = start;
		this.end = end;
	}

    @Override
    public int compareTo(Time t) {
        if (this.end > t.end) { // 끝나는 시간 기준 오름차순 정렬 
            return 1;
        } else if (this.end == t.end) {
            if(this.start > t.start) { // 끝나는 시간 같다면 시작 시간 기준 오름차순 
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
		int N = sc.nextInt();

		ArrayList<Time> timeList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			timeList.add(new Time(a, b));
		}
		
		Collections.sort(timeList);
		
		int ans = 1;
		int endTime = timeList.get(0).end;
		for(int i = 1; i < timeList.size(); i++) {
			if(timeList.get(i).start >= endTime) {
				endTime = timeList.get(i).end;
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
