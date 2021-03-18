/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 상수  
 * https://www.acmicpc.net/problem/2908
 */

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n1 = sc.nextInt();
		int n2 = sc.nextInt();

		n1 = Integer.parseInt(new StringBuilder().append(n1).reverse().toString());
		n2 = Integer.parseInt(new StringBuilder().append(n2).reverse().toString());
		
		System.out.println(n1 > n2 ? n1 : n2);
	}

}
