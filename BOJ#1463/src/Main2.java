import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        dp[1] = 0; // 1을 빼는 경우를 먼저 dp에 저장한다.
        
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1; // 3이나 2로 나누어지지 않으면, 주어진 숫자-1에 대한 최소 연산 횟수 + 연산을 한 횟수 1
            
            if (i % 3 == 0) // 3으로 나누어 떨어지는 경우, 연산 횟수 dp값이 더 적다면 갱신 
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); 
            
            if (i % 2 == 0)  // 2로 나누어 떨어지는 경우, 연산 횟수 dp값이 더 적다면 갱신 
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
        }
        
        bw.write(dp[N] + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

}