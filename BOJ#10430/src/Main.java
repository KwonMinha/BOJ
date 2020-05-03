import java.util.Scanner;

/**
 * @author Minha Gwon
 * @date 2020. 5. 3.
 * 나머지
 * https://www.acmicpc.net/problem/10430
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		System.out.println((A+B)%C);
		System.out.println(((A%C) + (B%C))%C);
		System.out.println((A*B)%C);
		System.out.println(((A%C)*(B%C))%C);
	}

}
