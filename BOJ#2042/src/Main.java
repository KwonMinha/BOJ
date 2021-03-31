/**
 * @author Minha Gwon
 * @date 2021. 3. 31.
 * 구간 합 구하기 
 * https://www.acmicpc.net/problem/2042
 */

import java.util.Scanner;

public class Main {
	static long[] tree;
	static long[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		arr = new long[N];
		
		for(int i = 0; i < N; i++) { // (1~N+1) 까지 받아도 됨 
			arr[i] = sc.nextInt();
		}
		
		int h = baseLog(N, 2); // 2^k로 N보다 바로 큰 값을 만들 수 있는 k
		int treeSize = (int) Math.pow(2, (h+1)); // 세그먼트 트리의 전체 크기 -  2^k * 2 = 2^(k+1)
		tree = new long[treeSize];	
		init(1, 0, N-1); // 세그먼트 트리 초기화 
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M+K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt() - 1;
			long c = sc.nextLong();
			
			if(a == 1) { // update
				long diff = c - arr[b];
				arr[b] = c;
				update(1, 0, N-1, b, diff);
			} else { // sum
				sb.append(sum(1, 0, N-1, b, (int) c-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	// 세그먼트 트리 초기화 
	static long init(int node, int start, int end) {
		if(start == end) { // 노드의 범위가 1인 리프 노드인 경우 (트리의 마지막까지 내려옴) 
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2; // 반씩 쪼개어 왼쪽, 오른쪽으로 나뉘어서 리프노드까지 내려가기 위함 
		
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end); // 노드의 왼쪽 자식(start~mid)과 오른쪽 자식(mid+1, end)으로 분리 
	}
	
	// 세그먼트 트리 업데이트(갱신) 
	static void update(int node, int start, int end, int index, long diff) { // index : 구간합을 수정할 노드 / diff : 수정할 값 
		if(index < start || index > end) { // start와 end 사이에 index가 있지 않으면 return 
			return;
		}
		
		// 범위 안에 있다면 트리를 내려가며 관련 범위에 있는 값 갱신 
		tree[node] += diff;
		
		if(start == end) 
			return;
		
		int mid = (start + end) / 2;
		update(node * 2, start, mid, index, diff);
		update(node * 2 + 1, mid + 1, end, index, diff);	
	}
	
	// 세그먼트 트리 구간합 구하기 
	static long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) // 1. 구간 합을 구하고자 하는 범위와 상관이 없는 경우
			return 0;
		
		if(left <= start && end <= right) // 2. 구하고자 하는 구간 합 구간에 포함되는 경우
			return tree[node];
		
		int mid = (start + end) / 2;
		
		//3. [start, end]가 [left, right]를 완전히 포함하는 경우 / 4. [left, right]와 [start, end]가 겹쳐져 있는 경우(1,2,3을 제외한 나머지 경우)
		// 1, 2 이외의 경우라면, 왼쪽 오른쪽 두 부분으로 나누어 구간합 재탐색
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}

	// 밑이 base인 로그 함수 
	static int baseLog(double x, double base) { 
		return (int) Math.ceil(Math.log(x) / Math.log(base));
	}
	
}