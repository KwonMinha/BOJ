/**
 * @author Minha Gwon
 * @date 2020. 5. 29.
 * 이분 그래프
 * https://www.acmicpc.net/problem/1707
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb;
	private static ArrayList<Integer>[] list;
	private static boolean[] v;
	private static ArrayList<Integer> left;
	private static ArrayList<Integer> right;
	private static int ans = 1;
	//private static int[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			list = new ArrayList[V+1];
			v = new boolean[V+1];
			//check = new int[V+1];
			
			for(int j = 0; j < V+1; j++) {
				list[j] = new ArrayList<Integer>();
			}

			for(int k = 0; k < E; k++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				list[n1].add(n2);
				//list[n2].add(n1);
			}

			for(int h = 1; h <= V; h++) {
				if(ans == 0)
					break;
				
				left = new ArrayList<Integer>();
				right = new ArrayList<Integer>();

				bfs(h);
			}

			if(ans == 1)
				System.out.println("YES");
			else
				System.out.println("NO");
			
			ans = 1;
		}
		br.close();
	}

	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		v[start] = true; 
		queue.add(start);
		if(!left.contains(start) && !right.contains(start))
			left.add(start);
		//System.out.println("s : " + Arrays.toString(left.toArray()) + " " + Arrays.toString(right.toArray()));

		while(queue.size() != 0) { 
			start = queue.poll(); 

			Iterator<Integer> iter = list[start].listIterator();
			while(iter.hasNext()) { 
				int next = iter.next(); 
				if(!v[next]) { 
					v[next] = true; 
					queue.add(next); 

					if(!left.contains(next) && !right.contains(next)) {
						if(left.contains(start)) {
							right.add(next);
							//System.out.println("r : " + Arrays.toString(left.toArray()) + " " + Arrays.toString(right.toArray()));
						}
						else {
							left.add(next);
							//System.out.println("l : " + Arrays.toString(left.toArray()) + " " + Arrays.toString(right.toArray()));
						}
					} else {
						if((left.contains(start) && left.contains(next))
								|| (right.contains(start) && right.contains(next))) {
							ans = 0;
							return;
						} else {
							if(left.size() + right.size() == list.length-1)
								return;
							break;
						}
					}
				}
				v[next] = false;
			}
		}
	}

}
