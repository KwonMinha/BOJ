//백준 버전

import java.util.*;
import java.io.*;
 
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 리더 생성
        int n = Integer.parseInt(bf.readLine());
        // 사이즈 입력
        int[] a = new int[n];
        // 배열 생성
        int[] ans = new int[n];
        // 정답 배열 생성
        String[] temp = bf.readLine().split(" ");
        // 임시로 받고
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
            // int로 변환
        }
        Stack<Integer> s = new Stack<>();
        // 스택 생성
        s.push(0);
        // 첫번째 인덱스 저장
        for (int i = 1; i < n; i++) {
            if (s.isEmpty()) {
            	System.out.println("push");
                s.push(i);
                // 반복문에 들어올 때 스택이 비어있으면 인덱스 저장 
            }
            while (!s.isEmpty() && a[s.peek()] < a[i]) {
                // 비어있지 않고 숫자가 인덱스 가장 위쪽 숫자보다 크면
                ans[s.pop()] = a[i];
                // 정답 배열 중 스택의 가장 위쪽 숫자와 같은 인덱스에 i번째 숫자를 넣는다 
            }
            System.out.println("push2");
            s.push(i);
            // 다음번에 비교할 숫자를 stack에 집어넣는다
        }
        
        while (!s.empty()) {
            // 반복문을 다 돌고 나왔는데 스택이 비어있지 않다면 빌 때 까지
            ans[s.pop()] = -1;
            // stack에 쌓인 index에 -1을 넣고
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(ans[i] + " ");
            // 출력한다
        }
        bw.write("\n");
        bw.flush();
    }
}