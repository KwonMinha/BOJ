import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		boolean[] v = new boolean[N];
		LinkedList<Integer> list = new LinkedList();
		permutation2(list, v, N, sb);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void permutation2(LinkedList<Integer> list, boolean[] v, int n, StringBuilder sb) {
		if(list.size() == n) {
			for(int i : list) {
                sb.append(i + " ");
				//System.out.print(i + " ");
			}
			sb.append("\n");
            //System.out.println();
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!v[i]) {
				list.add(i+1);
				v[i] = true;
				permutation2(list, v, n, sb);
				v[i] = false;
				list.removeLast();
			}
		}
	}

}