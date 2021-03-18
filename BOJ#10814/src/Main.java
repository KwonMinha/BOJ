/**
 * @author Minha Gwon
 * @date 2021. 3. 19.
 * 나이순 정렬 
 * https://www.acmicpc.net/problem/10814
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		User[] arr = new User[N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new User(i, Integer.parseInt(st.nextToken()), st.nextToken());
		}

		Arrays.sort(arr);

		for(User user : arr) {
			System.out.println(user);
			//System.out.println(user.age + " " + user.name);
		}
	}

}


class User implements Comparable<User> {
	int idx;
	int age;
	String name;

	User(int idx, int age, String name) {
		this.idx = idx;
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(User o) { // 가입 순서는 입력순이라서 나이만 정렬해주면 됨 
		return this.age - o.age;		
	}

	@Override
	public String toString() {
		return age + " " + name;
	}
}