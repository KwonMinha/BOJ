/**
 * @author Minha Gwon
 * @date 2020. 5. 28.
 * 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb;
	private static int min = Integer.MAX_VALUE;
	private static int max = Integer.MIN_VALUE;
	private static int[] arr;
	private static LinkedList<Integer> list;
	private static int[] res;
	private static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		list = new LinkedList<Integer>();
		for(int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			while(cnt != 0) {
				list.add(i);
				cnt--;
			}
		}
		v = new boolean[list.size()];
		res = new int[list.size()];
		
		dfs(0);
		bw.write(max + " " + min);	
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int depth) {
		if(depth == list.size()) {
			int sum = arr[0];
			for(int i = 1; i < arr.length; i++) {
				int operIndex = res[i-1];
				switch(operIndex) {
				case 0:
					sum = sum + arr[i];
					break;
				case 1:
					sum = sum - arr[i];
					break;
				case 2:
					sum = sum * arr[i];
					break;
				case 3:
					int n1 = sum;
					int n2 = arr[i];
				
					if(n1 < 0) {
						n1 = Math.abs(n1);
						sum = n1 / n2;
						if(sum != 0)
							sum = sum * -1;
					} else {
						sum  = n1 / n2;
					}
					break;
				default:
					sum = 0;
					break;
				}
			}
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			
			return;
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(!v[i]) {
				v[i] = true;
				res[depth] = list.get(i);
				dfs(depth + 1);
				v[i] = false;
			}
		}
		
	}

}
