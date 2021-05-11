import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2021. 5. 11.
 * 정수 삼각형 
 * https://www.acmicpc.net/problem/1932
 */

public class Main {
	static int n, nodeCnt;
	static int[] tree;
	static int[] result;
	static int max = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		nodeCnt = (n + (int) Math.pow(n, 2)) / 2;
		System.out.println(nodeCnt);
		
		result = new int[n+1];
		tree = new int[nodeCnt+1];
		
		for(int i = 1; i < tree.length; i++) {
			tree[i] = sc.nextInt();
		}
		
		solve(1, 0);
		
		System.out.println(max);
	}
	
	public static void solve(int x, int sum) {
		System.out.println("x : " + x + ", sum : " + sum);
		if(2*x >= nodeCnt+1) { // 자식 노드가 없다면 종료 
			System.out.println("done");
			sum += tree[x];
			System.out.println("sum : " + sum + " , max : " + max);
			max = Math.max(sum, max);
		} else {
			solve(2*x, sum + tree[x]);
			solve(2*x+1, sum + tree[x]);
		}
	}
}
