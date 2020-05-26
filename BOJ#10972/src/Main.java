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

		int idx = N-1;
		while(idx > 0 && arr[idx] < arr[idx-1]) {
			idx--;
		}

		if(idx == 0) {
			sb.append(-1);
		} else {
			for(int i = N-1; i > idx-1; i--) {
				if(arr[idx-1] < arr[i]) {
					swap(arr, idx-1, i);

					int j = N-1;
					while(idx < j) {
						swap(arr, idx, j);
						idx++;
						j--;
					}
					
					for(int h : arr)
						sb.append(h + " ");
					break;
				}
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
