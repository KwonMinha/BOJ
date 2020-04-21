/**
 * @author Minha Gwon
 * @date 2020. 4. 21.
 * 에디터 
 * https://www.acmicpc.net/problem/1406
 * 
 * L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
   D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
   B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
        삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
   P $	$라는 문자를 커서 왼쪽에 추가함
 
   첫째 줄에는 초기에 편집기에 입력되어 있는 문자열이 주어진다.
   이 문자열은 길이가 N이고, 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다.
   둘째 줄에는 입력할 명령어의 개수를 나타내는 정수 M(1 ≤ M ≤ 500,000)이 주어진다.
   셋째 줄부터 M개의 줄에 걸쳐 입력할 명령어가 순서대로 주어진다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

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

		//iterator 메소드 호출 
		ListIterator<Character> iter = list.listIterator();
		//처음 커서는 문장의 맨 뒤에 있어야하기 때문에 커서를 맨뒤로 이동시켜줌 
		while(iter.hasNext()) {
			iter.next();
		}
	
		for(int i = 0; i < M; i++) {
			String command = br.readLine();
			char c = command.charAt(0);
			switch(c) {
			case 'L':
				if(iter.hasPrevious())
					iter.previous();

				break;
			case 'D':
				if(iter.hasNext())
					iter.next();

				break;
			case 'B':
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove(); //next()나 previous() 메소드에 의해 반환된 가장 마지막 요소를 리스트에서 제거함
				}
				break;
			case 'P':
				char t = command.charAt(2);
				iter.add(t);

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