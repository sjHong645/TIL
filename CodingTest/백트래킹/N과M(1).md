[문제](https://www.acmicpc.net/problem/15649)

[정답 출처](https://st-lab.tistory.com/114)

전혀 감이 잡히지 않아서 정답을 봤다. 반복해서 숙달하자. 

### 문제 

#### 문제 설명 

자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 출력하라.

#### 입력 

첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

#### 출력 

한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.  
중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

#### 예제 입력 1
```
3 1
```

#### 예제 출력 1
```
1
2
3
```

#### 예제 입력 2
```
4 2
```

#### 예제 출력 2
```
1 2
1 3
1 4
2 1
2 3
2 4
3 1
3 2
3 4
4 1
4 2
4 3
```

#### 예제 입력 3
```
4 4
```

#### 예제 출력 3
```
1 2 3 4
1 2 4 3
1 3 2 4
1 3 4 2
1 4 2 3
1 4 3 2
2 1 3 4
2 1 4 3
2 3 1 4
2 3 4 1
2 4 1 3
2 4 3 1
3 1 2 4
3 1 4 2
3 2 1 4
3 2 4 1
3 4 1 2
3 4 2 1
4 1 2 3
4 1 3 2
4 2 1 3
4 2 3 1
4 3 1 2
4 3 2 1
```

### 정답 코드 

출처는 맨 위에 걸어놓았음. 

간단한 예제를 통해서 함수 흐름을 직접 그려보았다. 그려보면 아래와 같다. 

ex) N = 4 / M = 2 

![image](https://user-images.githubusercontent.com/64796257/157249138-091d26f4-0bff-4ad9-9a32-7965dda328f2.png)

재귀함수에서 depth 라는 개념을 추가해서 각각의 자리수들을 모두 출력하도록 했다. 

arr[depth] = i + 1을 넣는 이유는 다음과 같다.

visited[0]의 의미는 0번째 값 즉, 1을 방문했는지 여부를 저장한다. visited[1]의 의미는 1번째 값 즉, 2를 방문했는지 여부를 저장한다. 

이와 같이 인덱스 값은 원래 숫자보다 1 작기 때문에 +1을 해서 값을 저장해야 한다.

``` java
import java.util.Scanner; 

public class N과M1 {
	
	// 1 ~ N 까지의 각각의 숫자들을 방문했는지 표시하는 배열 => 그래서 길이가 N이다.
	static boolean visited[];
	
	// 출력할 내용을 저장할 배열 => 그래서 길이가 M이다.
	static int arr[];  


	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); 
		
		int M = sc.nextInt();
		
		arr = new int[M];
		visited = new boolean[N]; 
				
		dfs(N, M, 0);
		
		sc.close();

	}
	
	public static void dfs(int N, int M, int depth) {
		 
		// 재귀 깊이가 M과 같아지면 탐색과정에서 담았던 배열을 출력
		if (depth == M) {
			for (int val : arr) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
	 
	 
		for (int i = 0; i < N; i++) {
	 
			// 만약 해당 노드(값)을 방문하지 않았다면?
			if (visited[i] == false) {
				
				visited[i] = true;		// 해당 노드를 방문상태로 변경
				arr[depth] = i + 1;		// 해당 깊이를 index로 하여 i + 1 값 저장
				dfs(N, M, depth + 1);	// 다음 자식 노드 방문을 위해 depth 1 증가시키면서 재귀호출
	            
				// 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
				visited[i] = false;
			}
		}
		return;
	}

}
```









