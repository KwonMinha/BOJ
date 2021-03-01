/**
 * @author Minha Gwon
 * @date 2021. 3. 1.
 * 로봇 시뮬레이션 
 * https://www.acmicpc.net/problem/2174
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 순서 
	static int[] dy = {0, 1, 0, -1};
	static int A, B;
	static ArrayList<Robot> robotList = new ArrayList<>();
	static Robot[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		map = new Robot[B][A];
		
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt()-1;
			
			int y = sc.nextInt();
			y = B-y;
			
			// 방향 지정 
			String dir = sc.next();
			int d;
			
			if(dir.equals("N")) d = 0;
			else if(dir.equals("S")) d = 2;
			else if(dir.equals("W")) d = 3;
			else d = 1;
			
			Robot robot = new Robot(i, y, x, d);
			robotList.add(robot);
			map[y][x] = robot;
		}
		
		boolean flag = true;
		for(int i = 0; i < M; i++) {
			int orderRobot = sc.nextInt()-1;
			String order = sc.next();
			int orderCnt = sc.nextInt();
			
			int result = run(orderRobot, order, orderCnt);
			
			if(result == -7) {
				continue;
			} else if(result == -1) {
				System.out.println("Robot " + (orderRobot+1) + " crashes into the wall");
				flag = false;
				break;
			} else {
				System.out.println("Robot " + (orderRobot+1) + " crashes into robot " + (result+1));
				flag = false;
				break;
			}
		}
		
		if(flag) 
			System.out.println("OK");
	}
	
	public static int run(int orderRobot, String order, int orderCnt) {
		Robot curRobot = null;
		for(int i = 0; i < robotList.size(); i++) {
			if(robotList.get(i).i == orderRobot) {
				curRobot = robotList.get(i);
				break;
			}
		}
		
		for(int i = 0; i < orderCnt; i++) {
			if(order.equals("L")) { // 왼쪽으로 90도 회전 
				curRobot.d = (curRobot.d + 3) % 4;
			} else if(order.equals("R")) { // 오른쪽으로 90도 회전 
				curRobot.d = (curRobot.d + 1) % 4;
			} else { // 앞으로 한 칸 이동 
				curRobot.x = curRobot.x + dx[curRobot.d];
				curRobot.y = curRobot.y + dy[curRobot.d];
				
				// 한 칸씩 명령만큼 이동 후 이동한 곳 검증 
				if(curRobot.x < 0 || curRobot.y < 0 || curRobot.x >= B || curRobot.y >= A) {
					return -1; // 잘못된 명령 1 
				}
				
				if(map[curRobot.x][curRobot.y] != null) {
					return map[curRobot.x][curRobot.y].i; // 잘못된 명령 2
				}
			}
		}
		
		// map 새로 구성
		map = new Robot[B][A];
		for(int i = 0; i < robotList.size(); i++) {
			Robot r = robotList.get(i);
			map[r.x][r.y] = r;
		}
		
		return -7; // 명령 수행 완료의 경우 
	}

}

class Robot {
	int i;
	int x;
	int y;
	int d;

	Robot(int i, int x, int y, int d) {
		this.i = i;
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
