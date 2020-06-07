import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, r, c, d;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; //위 - 왼쪽 - 아래 - 오른쪽 
	public static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		ans++;
		solve();
		System.out.println(ans);
	}

	public static void solve() {
		while(!isStop()) {
			System.out.println("r : " + r + ", c : " + c + ", d : " + d);
			
			if(!visited[r][c]) {
				ans++;
				visited[r][c] = true;
			}

			NextNode next = isLeft();
			if(next.r != 0 && next.c != 0) {
				r = next.r;
				c = next.c;
				d = next.d;
			} else {
				d = next.d;
			}
		}
	}
	
	public static boolean isStop() {
		boolean flag = true;
		for(int i = 0; i < 4; i++) {
			int dx = r + dir[i][0];
			int dy = c + dir[i][1];
//			System.out.println(dx + " " + dy);
//			System.out.println(visited[dx][dy]);
//			System.out.println(map[dx][dy]);
			
			if(dx == 0 || dx == M-1 || dy == 0 || dy == N-1 || map[dx][dy] == 1 || visited[dx][dy])
				continue;
			else {
				flag = false;
				break;
			}
		}
		
		if(flag) {
			int dx = 0, dy = 0;
			if(d == 0) {
				dx = r + dir[0][0];
				dy = r + dir[1][1];
			} else if(d == 3) {
				dx = r + dir[3][0];
				dy = r + dir[3][1];
			} else if(d == 2) {
				dx = r + dir[2][0];
				dy = r + dir[2][1];
			} else if(d == 1) {
				dx = r + dir[1][0];
				dy = r + dir[1][1];
			}
			
			if(map[dx][dy] == 1) {
				return true;
			} else {
				r = dx;
				c = dy;
			}
		}
		
		return false;
	}
	
	public static NextNode isLeft() {
		System.out.println("left");
		int dx = 0;
		int dy = 0;
		int dd = d;
		
		System.out.println("s 전임 ");
		switch(dd) {
		case 0: //위쪽 
			System.out.println("위 일 떄 ");
			dx = r + dir[1][0];
			dy = c + dir[1][1];
			dd = 3;
			break;
		case 3: //왼쪽 
			System.out.println("왼  일 떄 ");
			dx = r + dir[2][0];
			dy = c + dir[2][1];
			dd = 2;
			
			System.out.println("!!!! r : " + dx);
			break;
		case 2: //아래쪽 
			System.out.println("아래  일 떄 ");
		    dx = r + dir[3][0];
			dy = c + dir[3][1];
			dd = 1;
			break;
		case 1: //오른쪽 
			System.out.println("오른  일 떄 ");
			dx = r + dir[0][0];
			dy = c + dir[0][1];
			dd = 0;
			break;
		} 
		
		NextNode next = new NextNode();
		if(!visited[dx][dy] && map[dx][dy] != 1) {
			next.r = dx;
			next.c = dy;
			next.d = dd;
			
			System.out.println(r + " " + c);
			return next;
		} else {
			next.r = 0;
			next.c = 0;
			next.d = dd;
			return next;
		}
	}

}

class NextNode {
	int r;
	int c;
	int d;
	
	public NextNode() { }
	
	NextNode(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}
