[문제](https://www.acmicpc.net/problem/1149)

### 문제 

#### 문제 설명
RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.  
각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

- 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
- N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
- i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

#### 입력 
첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다.  

둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.  

집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

#### 출력 

첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.

#### 예제 입력 1
```
3
26 40 83
49 60 57
13 89 99
```

#### 예제 출력 1
```
96
```

#### 예제 입력 2
```
8
71 39 44
32 83 55
51 37 63
89 29 100
83 58 11
65 13 15
47 25 29
60 66 19
```

#### 예제 출력 2
```
253
```

### 정답 코드 

단순히 각각의 집의 비용의 최솟값만 구해서 더한다고 해결되는 문제가 아님.

그리고 현재의 결과가 최소라고 해서 전체적인 결과가 최소가 아닐 수 있다. 이를 보여주는 예제가 2 예제이다.

그렇다면, 전체적인 결과가 최소가 되도록 하려면 알고리즘을 어떻게 설계해야 할까??

결국에는 모든 경로에서의 비용을 전부 구해서 마지막에 최종 비용이 가장 작은 값을 출력하도록 해야 한다. 
(해당 idea는 https://st-lab.tistory.com/128 에서 얻어왔다)


``` java
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.StringTokenizer;

public class RGB거리 {
	
	// 0 - R, 1 - G, 2 - B 를 의미한다.
  
  // 입력받는 RGB의 비용을 저장할 RGB 배열
	static int[][] RGB = new int[1001][3];
	
  // 비용을 저장할 cost 배열 
	static int[][] cost = new int[1001][3];
	
  // 최소 비용을 찾아내는 메소드
	public static void findCost(int N) {
		
    // 1번째 집의 비용은 이미 구했기 때문에
    // 2번째 집의 비용을 구하는 것 부터 시작한다.
		for(int i = 2; i <= N; i++) {

    // i번째 집을 빨간색(j = 0), 초록색(j = 1), 파란색(j = 2)으로 색칠할 때 
    // 각각의 비용을 구하도록 했다.
			for(int j = 0; j <= 2; j++) {
				
        // i번째 집을 빨간색으로 색칠할 때
        // i-1번째 집을 초록색으로 색칠했을 때의 비용과 파란색으로 색칠했을 때 비용 중 최소값을 고른다.
        // 그 최솟값과 i번째 집을 빨간색으로 색칠했을 때 비용을 더한다.
				if(j == 0) cost[i][j] = RGB[i][j] + Math.min(cost[i-1][j+1], cost[i-1][j+2]);
				
        // 나머지 동작들도 마찬가지다.
				else if(j == 1) cost[i][j] = RGB[i][j] + Math.min(cost[i-1][j-1], cost[i-1][j+1]);
				
				else cost[i][j] = RGB[i][j] + Math.min(cost[i-1][j-1], cost[i-1][j-2]);
			}	
			
		}
    
    // 이렇게 해서 반복문이 전부 끝나면 
    // N번째 집을 빨간색, 초록색, 파란색으로 색칠했을 때 최소 비용을 각각 구할 수 있다.
    
    // 그 중에서 최솟값을 골라 출력해주면 된다.
		
	}
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		StringTokenizer st; 
		
		int N = Integer.parseInt(br.readLine());
		
		int R, G, B;
		
		// 자료 입력
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			RGB[i][0] = R; RGB[i][1] = G; RGB[i][2] = B;
		}
		
    // 1번 집의 비용은 각각의 색깔에 대한 비용이다.
		cost[1][0] = RGB[1][0];
		cost[1][1] = RGB[1][1];
		cost[1][2] = RGB[1][2];
		
		// cost값 구한다.
		findCost(N);
		
    // 최종적으로 구한 비용들의 최소값을 출력한다.
		System.out.println(Math.min(cost[N][0], Math.min(cost[N][1], cost[N][2])));

	}

}
```




















