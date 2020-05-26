/**
 * @author Minha Gwon
 * @date 2020. 5. 26.
 * 다음 순열
 * https://www.acmicpc.net/problem/10972
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(nextPermutaion(arr)) {
			for(int i : arr)
				sb.append(i + " ");
		} else {
			sb.append(-1);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static boolean nextPermutaion(int[] arr) {
		int i = arr.length-1;
		
		while(i > 0 && arr[i] < arr[i-1]) {
			i--;
		}

		if(i == 0)
			return false;
		
		int j = arr.length-1;
		while(arr[i-1] > arr[j]) {
			j--;
		}
		swap(arr, i-1, j);
		
		j = arr.length-1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
		
		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
