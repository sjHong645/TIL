[문제](https://www.acmicpc.net/problem/9663)

## 문제 

N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

### 입력 

첫째 줄에 N이 주어진다. (1 ≤ N < 15)

### 출력 

첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

### 예제 입력 1

```
8
```

### 예제 출력 1
```
92
```

## 정답 코드 

5 * 5 체스판을 가지고 그림을 그려서 코드를 표현하면 아래와 같다. 

![image](https://user-images.githubusercontent.com/64796257/158112878-00d22f16-b0f6-4942-a963-38492f6e9905.png)

위 그림과 같이 각각의 부분에 퀸을 놓았을 때 

그 자리에 퀸을 놓아도 되는 부분인지 아닌지를 계속해서 재귀적으로 판단한다. 

놓으면 안되는 자리라면 곧바로 함수를 끝낸다. 놓아도 되는 자리라면 함수를 계속 진행시킨다. 



``` java
package backTracking;

import java.util.Scanner;

public class NQueen {
	
	static Scanner sc = new Scanner(System.in);
	
	static boolean[][] chess;
	
	static int count = 0; 

	public static void main(String[] args) {
		
		int N = sc.nextInt();
		
		chess = new boolean[N][N]; // 0부터 시작.
		
		for(int i = 0; i < N; i++) {
			
			chess[0][i] = true; // 맨 첫 줄에 queen을 놓는다.
					
			nQueen(0, i); // (0, i)에 퀸을 놓았을 때 나머지 퀸을 놓는 방법의 수를 구한다.
			
			chess[0][i] = false; // 초기화
			
		}
		
		System.out.println(count);
		

	}
	
	// (row, i)에 퀸을 놓은 상황
	static void nQueen(int row, int i) {

		// row 행에 이미 퀸이 있는지 확인
		// 있으면 return, 없으면 계속 진행
		for(int col = 0; col < chess.length; col++) {
			
			if(col == i) continue; // 자기 자신은 제외
      
			if(chess[row][col] == true) return;
			
		}
		
		
		// i 열에 이미 퀸이 있는지 확인
		// 있으면 return, 없으면 계속 진행
		for(int rw = 0; rw < chess.length; rw++) {
			
			if(rw == row) continue; // 자기 자신은 제외
			
			if(chess[rw][i] == true) return;
			
		}
		
				
		// (row, i) 에 있는 퀸과 대각선 방향에 이미 있는지 확인
		// 있으면 return, 없으면 계속 진행
		
		int tempRow = row; int tempCol = i;
		// ↗ 방향
		while(tempRow >= 0 || tempCol < chess.length) {
			
			tempRow--; tempCol++;
			
      // 범위를 넘어서면 멈춘다.
			if(tempRow < 0 || tempCol >= chess.length) break;
			
			if(chess[tempRow][tempCol] == true) return;
			
		}
		
		// ↙ 방향
		tempRow = row; tempCol = i;
		
		while(tempRow < chess.length || tempCol >= 0) {
			
			tempRow++; tempCol--;
			
      // 범위를 넘어서면 멈춘다.
			if(tempRow >= chess.length || tempCol < 0) break;
			
			if(chess[tempRow][tempCol] == true) return;
			
		}
		
		
		// ↖ 방향
		tempRow = row; tempCol = i;
		
		while(tempRow >= 0 || tempCol >= 0) {
			
			tempRow--; tempCol--;
			
      // 범위를 넘어서면 멈춘다.
			if(tempRow < 0 || tempCol < 0) break;
			
			if(chess[tempRow][tempCol] == true) return;
			
		}
		
		
		// ↘ 방향
		tempRow = row; tempCol = i;
		
		while(tempRow < chess.length || tempCol < chess.length) {
			
			tempRow++; tempCol++;
			
      // 범위를 넘어서면 멈춘다.
			if(tempRow >= chess.length || tempCol >= chess.length) break;
			
			if(chess[tempRow][tempCol] == true) return;
			
		}	
		
    // 모든 탐색을 마치고 나서 
		// row가 체스판의 맨 아래에 있게 되면 return. 그리고 경우의 수도 1 증가시킨다.
		if(row + 1 == chess.length) {
			count++;
			return;
		}	
				
		// 이제 row행 아랫줄에 퀸을 놓는다. 
		for(int idx = 0; idx < chess.length; idx++) {
			
			chess[row + 1][idx] = true; // (row + 1, idx)에 퀸을 놓는다.
			
			nQueen(row + 1, idx); // (row + 1, idx)에 퀸을 놓았을 때 나머지 퀸을 놓는 방법의 수를 구한다.
			
			chess[row + 1][idx] = false; // 초기화
    }
		
	}

}

```
