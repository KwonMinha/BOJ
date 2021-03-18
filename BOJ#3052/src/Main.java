/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 나머지 
 * https://www.acmicpc.net/problem/3052
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < 10; i++) {
			set.add(sc.nextInt() % 42);
		}
		
		System.out.println(set.size());
	}

}
