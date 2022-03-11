import java.util.Scanner;

import java.io.IOException;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class N과M3 {
	
	static Scanner sc = new Scanner(System.in);
	
	static int[] arr;
	static boolean[] visited;
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		int N = sc.nextInt();
		
		int M = sc.nextInt();
		
		arr = new int[M];
		visited = new boolean[N];
		
		backT(N, M, 0); // 1번째 자리에서 부터 시작
		
		bw.close();

	}
	
	static void backT(int N, int M, int depth) throws IOException{
		
		// 원하는 자리수까지 도달했다면 
		if(M == depth) {
			
			for(int val: arr) {
				bw.write(val + " ");
			}
			
			bw.write("\n");
			// 시간단축을 위해서 BufferedWriter를 사용함
			
			return;
		}
		
		
		for(int i = 0; i < N; i++) {
			
			visited[i] = true; // i번째 값 방문
			arr[depth] = i + 1; // depth = 0 인 것을 먼저 생각하자.
								          // 0번째 값에 i + 1을 넣는 것이다.
			
			backT(N, M, depth + 1); // 다음 자릿수에 있는 값을 출력할 수 있도록 재귀함수를 설정
			
			// 다 하고 나면 
			// 기존에 i번째 값을 재방문할 수 있도록 visited를 false로 바꾼다.
			
			visited[i] = false;
			
		}
		bw.flush(); // 마지막에 flush를 해야 시간초과가 발생하지 않음.
		return;
		
	}

}
