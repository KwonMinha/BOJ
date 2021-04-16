/**
 * @author Minha Gwon
 * @date 2021. 4. 16.
 * 톱니바퀴 
 * https://www.acmicpc.net/problem/14891
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Wheel[] wheelList = new Wheel[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 초기 톱니바퀴 구성해서 리스트에 저장 
		for(int i = 0; i < 4; i++) {
			int[] arr = Arrays.stream(br.readLine().split(""))
					.mapToInt(Integer::parseInt)
					.toArray();

			wheelList[i] = new Wheel(arr);
		}

		// K번 회전 
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) -1;
			int dir = Integer.parseInt(st.nextToken());

			solve(num, dir);
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
		int[] rotateOrder = new int[4]; // 맞닿는 왼쪽, 오른쪽 톱니바퀴 검사 후 회전해야할 방향 저장 
		rotateOrder[num] = dir;

		// 회전시킬 첫번째 톱니바퀴 
		int[] startWheel = wheelList[num].arr;
		int startLeft = startWheel[6];
		int startRight = startWheel[2];
		int startDir = dir;

		// Up - start 톱니바퀴를 기준으로 오른쪽의 톱니바퀴 검사 (start의 3시 방향 vs next의 9시 방향)
		for(int i = num + 1; i < 4; i++) {
			int[] nextWheel = wheelList[i].arr;
			int nextLeft = nextWheel[6];
			int nextRight = nextWheel[2];
			
			if(startRight != nextLeft) { // N vs S극인 경우 
				rotateOrder[i] = startDir == 1 ? -1 : 1;
				startDir = rotateOrder[i];
				startRight = nextRight;
			} else {
				break;
			}
		}

		// Down - start 톱니바퀴를 기준으로 왼쪽의 톱니바퀴 검사 (next의 3시 방향 vs start의 9시 방향)
		startLeft = startWheel[6];
		startRight = startWheel[2];
		startDir = dir;

		for(int i = num - 1; i >= 0; i--) {
			int[] nextWheel = wheelList[i].arr;
			int nextRight = nextWheel[2];
			int nextLeft = nextWheel[6];
			
			if(startLeft != nextRight) { // N vs S극인 경우 
				rotateOrder[i] = startDir == 1 ? -1 : 1;
				startDir = rotateOrder[i];
				startLeft = nextLeft;
			} else {
				break;
			}
		}

		// rotate
		for(int i = 0; i < 4; i++) {
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
