import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int[][] board;
	private static int max = 0;
	private static int n;
	private static int[] output;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		n = N;
		board = new int[N][N];
		output = new int[5];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		output[0] = 2;
		output[1] = 3;
		output[2] = 0;
		output[3] = 1;
		output[4] = 2;

		moveBlock();

		bw.write(max + "");	
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int depth) {
		if(depth == 5) {
			moveBlock();
			return;
		}

		for(int i = 0; i < 4; i++) {
			output[depth] = i;
			dfs(depth+1);
		}
	}

	public static void moveBlock() {
		for(int i = 0; i < output.length; i++) {
			int dir = output[i];

			switch(dir) {
			case 0:
				//위로 이동 
				System.out.println("위로 이동");
				for(int j = 0; j < n; j++) {
					String s = "";

					for(int k = 0; k < n; k++) {
						s += board[k][j];
					}

					s = s.replace("0", "");

					while(s.length() != n)
						s += "0";

					for(int k = 0; k < n; k++) {
						board[k][j] = Integer.parseInt(s.charAt(k)+"");
					}
				}
				break;
			case 1:
				//아래로 이동
				System.out.println("아래로 이동");
				for(int j = 0; j < n; j++) {
					String s = "";

					for(int k = 0; k < n; k++) {
						s += board[k][j];
					}

					s = s.replace("0", "");

					while(s.length() != n)
						s  = "0" + s;

					for(int k = 0; k < n; k++) {
						board[k][j] = Integer.parseInt(s.charAt(k)+"");
					}
				}
				break;
			case 2:
				//왼쪽으로 이동 
				System.out.println("왼쪽으로 이동");
				for(int j = 0; j < n; j++) {
					String s = "";

					for(int k = 0; k < n; k++) {
						s += board[j][k];
					}

					s = s.replace("0", "");

					while(s.length() != n)
						s += "0";

					for(int k = 0; k < n; k++) {
						board[j][k] = Integer.parseInt(s.charAt(k)+"");
					}
				}
				break;
			case 3:
				//오른쪽으로 이동 
				System.out.println("오른쪽으로 이동");
				for(int j = 0; j < n; j++) {
					String s = "";

					for(int k = 0; k < n; k++) {
						s += board[j][k];
					}

					s = s.replace("0", "");

					while(s.length() != n)
						s = "0" + s;

					for(int k = 0; k < n; k++) {
						board[j][k] = Integer.parseInt(s.charAt(k)+"");
					}
				}
				break;
			default:
				break;
			}





			mergeBlock(dir);

			if(i == output.length-1) {
				getMax();
			}
		}
	}

	public static void mergeBlock(int dir) {
		switch(dir) {
		case 0:
			for(int i = 0; i < n; i++) {

				String s = "";
				for(int j = 0; j < n; j++) {
					s += board[j][i];
				}

				boolean flag = true;
				while(flag) {
					for(int k = 0; k < s.length()-1; k++) {
						if(s.charAt(k) != '0' && s.charAt(k) == s.charAt(k+1)) {
							flag = false;
							int val = Integer.parseInt(s.charAt(k) + "");
							String s1 = s.substring(0, k);
							String s2 = s.substring(k+2, s.length());
							s = s1 + val*2 + s2;
						}
					}

					if(flag = false) {
						flag = true;
					}
				}

				while(s.length() != n) {
					s += "0";
				}

				for(int k = 0; k < n; k++) {
					board[k][i] = Integer.parseInt(s.charAt(k)+"");
				}
			}
			break;
		case 1:
			//System.out.println("merge");
			for(int i = 0; i < n; i++) {
				String s = "";
				for(int j = 0; j < n; j++) {
					s += board[j][i];
				}

				//System.out.println("s : " + s);

				boolean flag = true;
				while(flag) {
					for(int k = s.length()-1; k > 0; k--) {
						if(s.charAt(k) != '0' && s.charAt(k) == s.charAt(k-1)) {
							flag = false;
							int val = Integer.parseInt(s.charAt(k) + "");
							String s1 = s.substring(k+1, s.length());
							String s2 = s.substring(0, k-1);

							//System.out.println(s1 + " " + val*2 +  " " + s2);
							s = s2 + val*2 + s1;
							//k--;
						}
					}

					if(flag = false) {
						flag = true;
					}
				}

				while(s.length() != n) {
					s = "0" + s;
				}

				for(int k = 0; k < n; k++) {
					board[k][i] = Integer.parseInt(s.charAt(k)+"");
				}
			}
			break;
		case 2:
			for(int i = 0; i < n; i++) {
				LinkedList<Integer> num = new LinkedList<Integer>();

				String s = "";
				for(int j = 0; j < n; j++) {
					s += board[i][j];
					num.add(board[i][j]);
				}

				boolean flag = true;
				while(flag) {
					for(int k = 0; k < num.size()-1; k++) {
						if(num.get(k) != 0 && num.get(k) == num.get(k+1)) {
							flag = false;
							int val = num.get(k)*2;
							num.remove(k);
							num.remove(k);
							num.addFirst(val);
						}

						if(flag = false)
							flag = true;
					}
				}
				
				while(num.size() != n)
					num.addLast(0);

				for(int k = 0; k < n; k++) {
					board[i][k] = num.get(k);
				}
			}
			break;
		case 3:
			for(int i = 0; i < n; i++) {
				String s = "";
				for(int j = 0; j < n; j++) {
					s += board[i][j];
				}

				//System.out.println("s : " + s);

				boolean flag = true;
				while(flag) {
					for(int k = s.length()-1; k > 0; k--) {
						if(s.charAt(k) != '0' && s.charAt(k) == s.charAt(k-1)) {
							flag = false;
							int val = Integer.parseInt(s.charAt(k) + "");
							String s1 = s.substring(k+1, s.length());
							String s2 = s.substring(0, k-1);

							//System.out.println(s1 + " " + val*2 +  " " + s2);
							s = s2 + val*2 + s1;
							//k--;
						}
					}

					if(flag = false) {
						flag = true;
					}
				}

				while(s.length() != n) {
					s = "0" + s;
				}

				for(int k = 0; k < n; k++) {
					board[i][k] = Integer.parseInt(s.charAt(k)+"");
				}
			}
			break;
		default:
			break;
		}

		print();

	}

	public static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void getMax() {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				cnt += board[i][j];
			}
		}

		max = Math.max(cnt, max);
	}
}

