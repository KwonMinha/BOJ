/**
 * @author Minha Gwon
 * @date 2021. 7. 5.
 * 보석 도둑
 * https://www.acmicpc.net/problem/1202 
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 보석 개수 
		int K = Integer.parseInt(st.nextToken()); // 가방 개수
		
		ArrayList<Gem> gemList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 무게 
			int V = Integer.parseInt(st.nextToken()); // 가격 
			
			gemList.add(new Gem(M, V));
		}
		
		Collections.sort(gemList);
		
		ArrayList<Integer> bagList = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			int C = Integer.parseInt(br.readLine()); // 가방 최대 무게 
			bagList.add(C);
		}
		
		Collections.sort(bagList);
		
//		PriorityQueue<Gem> pq = new PriorityQueue<>(new Comparator<Gem>() {
//			@Override
//			public int compare(Gem o1, Gem o2) {
//				return o2.price - o1.price;
//			}
//		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		Long max = (long) 0;
		
		int idx = 0;
		
		for(int bag : bagList) {
			while(idx < N && gemList.get(idx).weight <= bag) {
				pq.add(gemList.get(idx).price);
				idx++;
			}
			
			if (!pq.isEmpty()) 
				max += pq.poll();
		}
		
		System.out.println(max);
	}

}

class Gem implements Comparable<Gem> {
	int weight;
	int price;
	
	Gem(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}

	@Override
	public int compareTo(Gem o) {
		return this.weight - o.weight;
	}

}
