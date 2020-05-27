/**
 * @author Minha Gwon
 * @date 2020. 5. 28.
 * 연산자 끼워넣기 (2)
 * https://www.acmicpc.net/problem/15658
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] arr;
	private static int min = Integer.MAX_VALUE;
	private static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		n = N;
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int plus = Integer.parseInt(st.nextToken());
		int minus = Integer.parseInt(st.nextToken());
		int multiple = Integer.parseInt(st.nextToken());
		int divide = Integer.parseInt(st.nextToken());
		
		solve(arr[0], 1, 0, plus, minus, multiple, divide);
		
		bw.write(max + "\n" + min);
		bw.close();
		br.close();
	}
	
	public static void solve(int sum, int depth, int count, int plus, int minus, int multiple, int divide) {
		if(depth == n) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			
			return;
		}
		
		if(count == n) {
			return;
		}
		
		if(plus > 0) {
			solve(sum + arr[depth], depth+1, count+1, plus-1, minus, multiple, divide);
		}
		
		if(minus > 0) {
			solve(sum - arr[depth], depth+1, count+1, plus, minus-1, multiple, divide);
		}
		
		if(multiple > 0) {
			solve(sum * arr[depth], depth+1, count+1, plus, minus, multiple-1, divide);
		}
		
		if(divide > 0) {
			if(sum < 0)
				solve(-(-sum / arr[depth]), depth+1, count+1, plus, minus, multiple, divide-1);
			else
				solve(sum / arr[depth], depth+1, count+1, plus, minus, multiple, divide-1);
		}
	}
}

