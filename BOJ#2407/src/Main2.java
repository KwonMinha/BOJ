/**
 * @author Minha Gwon
 * @date 2021. 3. 26.
 * 조합 
 * https://www.acmicpc.net/problem/2407
 * 
 * 조합 공식 - nCm => n! / (n-m)!m!
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		BigInteger num1 = factorial(n);
		BigInteger num2 = factorial(n-m).multiply(factorial(m));
        
		System.out.println(num1.divide(num2));
	}
	
	public static BigInteger factorial(int n) {
        if(n == 0) 
        	return BigInteger.valueOf(1);
        
        BigInteger res = new BigInteger("1");
        
        for(int i = 1; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        
        return res;
    }

}