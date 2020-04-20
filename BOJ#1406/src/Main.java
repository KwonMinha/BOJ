/**
 * @author gwonminha
 * @date 2020. 4. 20.
 * 에디터
 * https://www.acmicpc.net/problem/1406
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());

		LinkedList<Character> list = new LinkedList<Character>();

		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}

		int index = list.size();

		for(int i = 0; i < M; i++) {
			String command = br.readLine();
			char c = command.charAt(0);
			switch(c) {
			case 'L':
				if(index != 0) {
					index = index - 1;
				}

				break;
			case 'D':
				if(index != list.size()) {
					index = index + 1;
				}

				break;
			case 'B':
				if(index != 0) {
					list.remove(index-1);
					index = index -1;
				}
				break;
			case 'P':
				char t = command.charAt(2);
				list.add(index, t);
				index = index + 1;

				break;
			default:
				break;
			}
		}
		for(Character chr : list) {
			bw.write(chr);
		}
		
		bw.flush();
		bw.close(); 
	}
}