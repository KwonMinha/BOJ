import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		String str = sc.nextLine().trim();
//		
//		if(str.isEmpty())
//			System.out.println(0);
//		else 
//			System.out.println(str.split(" ").length);
		
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		System.out.println(st.countTokens());
	}

}