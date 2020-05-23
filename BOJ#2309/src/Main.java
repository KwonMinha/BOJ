/**
 * @author Minha Gwon
 * @date 2020. 5. 20.
 * 일곱 난쟁이 
 * https://www.acmicpc.net/problem/2309
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static boolean flag = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[9];
		boolean[] v = new boolean[9];
		int[] output = new int[7];

		for(int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		dfs(arr, v, output, 0, 9, 7);
	}

	public static void dfs(int[] arr, boolean[] v, int[] output, int depth, int n, int r) {
		if(r == 0) {
			int sum = 0;
			ArrayList<Integer> list = new ArrayList();
	        for(int i=0; i<n; i++) {
	            if(v[i] == true) {
	                sum += arr[i];
	                list.add(arr[i]);
	            }
	        }
	        
	        if(sum == 100 && !flag) {
	        	Collections.sort(list);
	        	for(int i = 0; i < list.size(); i++)
	        		System.out.println(list.get(i));
	        	
	        	flag = true;
	        }
			return;
		} else {
			for(int i = depth; i < n; i++) {
				v[i] = true;
				dfs(arr, v, output, i+1, n, r-1);
				v[i] = false;
			}
		}
		
	}

}
