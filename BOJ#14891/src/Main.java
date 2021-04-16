/**
 * @author Minha Gwon
 * @date 2021. 4. 16.
 * 톱니바퀴 
 * https://www.acmicpc.net/problem/14891
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Wheel[] wheelList = new Wheel[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i = 0; i < 4; i++) {
			int[] arr = Arrays.stream(br.readLine().split(""))
					.mapToInt(Integer::parseInt)
					.toArray();

			wheelList[i] = new Wheel(arr);
		}

		int K = Integer.parseInt(br.readLine());

		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) -1;
			int dir = Integer.parseInt(st.nextToken());

			solve(num, dir);

			System.out.println("done");
			for(int k = 0; k < 4; k++) {
				for(int l = 0; l < 8; l++) {
					System.out.print(wheelList[k].arr[l] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		// 점수 출력 
		int ans = 0;

		for(int i = 0; i < 4; i++) {
			if(wheelList[i].arr[0] != 0) {
				ans += (int) Math.pow(2, i);
			} 
		}

		System.out.println(ans);
	}

	public static void solve(int num, int dir) {
		int[] rotateOrder = new int[4];
		rotateOrder[num] = dir;

		int[] startWheel = wheelList[num].arr;
		int startLeft = startWheel[6];
		int startRight = startWheel[2];
		int startDir = dir;

		// Up
		System.out.println("up");
		for(int i = num + 1; i < 4; i++) {
			System.out.println("i : " + i);
		
			int[] nextWheel = wheelList[i].arr;
			int nextLeft = nextWheel[6];
			int nextRight = nextWheel[2];
			
			System.out.println("start Left : " + startLeft + ", startRight : " + startRight + ", nextL : " + nextLeft + ", nextRi : " + nextRight);
			

			if(startRight != nextLeft) {
				System.out.println("OK");
				rotateOrder[i] = startDir == 1 ? -1 : 1;
				startDir = rotateOrder[i];
				startRight = nextRight;
			} else {
				System.out.println("X");
				break;
			}
		}

		// Down
		System.out.println("down");
		startLeft = startWheel[6];
		startRight = startWheel[2];
		startDir = dir;

		for(int i = num - 1; i >= 0; i--) {
			System.out.println("i : " + i);
			
			int[] nextWheel = wheelList[i].arr;
			int nextRight = nextWheel[2];
			int nextLeft = nextWheel[6];
			
			System.out.println("start Left : " + startLeft + ", startRight : " + startRight + ", nextL : " + nextLeft + ", nextRi : " + nextRight);
			

			if(startLeft != nextRight) {
				System.out.println("OK");
				rotateOrder[i] = startDir == 1 ? -1 : 1;
				startDir = rotateOrder[i];
				startLeft = nextLeft;
			} else {
				System.out.println("X");
				break;
			}
		}

		// rotate
		for(int i = 0; i < 4; i++) {
			System.out.println("rotate : i : " + i  + ", " + rotateOrder[i]);
			if(rotateOrder[i] == 1)
				rotateRight(wheelList[i]);
			else if(rotateOrder[i] == -1)
				rotateLeft(wheelList[i]);
		}

	}

	public static void rotateRight(Wheel wheel) { // 시계 방향 회전 
		int temp = wheel.arr[7];

		for(int i = 7; i > 0; i--)
			wheel.arr[i] = wheel.arr[i-1];

		wheel.arr[0] = temp;
	}

	public static void rotateLeft(Wheel wheel) { // 반시계 방향 회전 
		int temp = wheel.arr[0];

		for(int i = 0; i < 7; i++) 
			wheel.arr[i] = wheel.arr[i+1];

		wheel.arr[7] = temp;
	}
}

class Wheel {
	int[] arr;

	Wheel(int[] arr) {
		this.arr = arr;
	}
}
