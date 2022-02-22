### LIS(Longest Increasing Subsequence) - 최장 증가 부분 수열 

[출처](https://sskl660.tistory.com/89)

주어진 수열에서 `오름차순으로 정렬`된 `가장 긴 부분 수열`을 찾는 문제 

ex) 10 20 10 30 20 50 이라는 수열이 주어졌다.

여기서 오름차순으로 정렬되는 가장 긴 부분 수열은 10 20 30 50이 되겠다. 각 숫자는 아래와 같이 위치한다.

⇒ `10` `20` 10 `30` 20 `50` 

#### 접근법 

주어진 수열의 i번째 원소의 위치에서 가질 수 있는 LIS의 값을 구해보자. LIS 값을 저장하는 배열을 dp라고 하겠다. 

ex) `LIS[2]`의 의미는 2번째 원소에서 가질 수 있는 LIS를 의미한다.

1) 첫 번째 원소 
: 10 이라는 원소가 가질 수 있는 LIS 값은 그 자체의 길이인 1을 LIS값으로 가진다. 그래서 LIS[1] = 1이 된다.

![image](https://user-images.githubusercontent.com/64796257/155066422-1a66211f-9975-4e14-b425-eee84f9fc437.png)

2) 두 번째 원소 

: 20 이라는 원소가 가질 수 있는 LIS 값은 그 자체의 길이인 1을 가지는 걸로 시작한다.  

이때, 20은 이전 원소인 10보다 증가한 값이기 때문에 LIS값은 2가 되어야 한다.

![image](https://user-images.githubusercontent.com/64796257/155066570-284f038f-4d62-4041-9ce0-a5595a50b1fa.png)

3) 세 번째 원소 

: 10 이라는 원소가 가질 수 있는 LIS 값은 그 자체의 길이인 1을 LIS값으로 가진다. 그래서 LIS[1] = 1이 된다.

이전 원소인 10, 20 보다 크지 않기 때문에 숫자는 더 이상 증가하지 않는다.

![image](https://user-images.githubusercontent.com/64796257/155066665-a4778b62-be43-4a52-81dc-b7c897c6a458.png)

4) 네 번째 원소 

: 30 이라는 원소가 가질 수 있는 LIS 값은 그 자체의 길이인 1을 LIS값으로 가진다. 

이때, 30은 이전에 있던 원소 10, 20 보다 크기 때문에 LIS값은 3이 되어야 한다.

![image](https://user-images.githubusercontent.com/64796257/155066794-b5484e3a-ae30-40fd-aa89-d799b1d3b69f.png)

여기서 네 번째 원소의 값을 설정하는 과정에 대해서 살펴보겠다. 

일단 맨 처음에는 자신의 길이인 1을 LIS 값으로 설정하는 것 부터 시작한다. 

![image](https://user-images.githubusercontent.com/64796257/155067581-faa36b95-aa7b-44e4-ba83-221e4e95b206.png)

30 이라는 원소값은 1번째 원소(10)에 대해 증가하는 부분 수열이 될 수 있기 때문에 dp[1] + 1은 dp[4]가 될 수 있다. 

마찬가지로 2번째 원소(20), 3번째 원소(10)에 대해서도 증가하는 부분 수열이 될 수 있기 때문에 dp[2] + 1, dp[3] + 1 역시 dp[4]가 될 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/155067855-1b0b8afe-36af-4ef8-99ea-2d5e42d56338.png)

최종적으로 dp[4]의 값이 될 수 있는 건 dp[1] + 1, dp[2] + 1, dp[3] + 1 중에서 최댓값인 dp[2] + 1이 된다.

![image](https://user-images.githubusercontent.com/64796257/155067964-d5810332-4ce8-431d-8dee-a9c6fbedd419.png)

다른 부분의 dp 값도 같은 원리로 값이 설정된다.

이를 가지고 코드를 작성하면 아래와 같이 작성할 수 있다. 

``` java
import java.io.IOException;

import java.io.InputStreamReader;

import java.io.BufferedReader;

import java.util.StringTokenizer;

import java.util.Arrays;

public class LIS {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static Integer[] answer;
	static int[] A;
	
	public static void main(String[] args) throws IOException{
		
		// 자료 개수 N개
		int N = Integer.parseInt(br.readLine());
		
		A = new int[N+1]; answer = new Integer[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 자료 입력 
		// 입력한 자료는 A라는 배열에 저장했다. 
		for(int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		// 최장 부분 수열의 길이를 max라는 변수에 저장하도록 했다. 
		int max = 1;
		
		for (int i = 1; i <= N; i++) {
			// 초기값은 1로 설정한다.
			answer[i] = 1;

			// i번째 이전에 있는 값들을 탐색한다.
			// 이전의 값들을 j로 가리킬 때 
			// j번째 값이 i번째 값보다 작다면 이는 수열 안에서 증가하는 형태를 가진다고 할 수 있다.
			
			// 고로, answer[i]의 값은 자기 자신의 값과 answer[j] + 1 중에서 큰 값을 가지도록 한다.
			// answer[j] + 1은 i번째 값이 j번째 값보다 크기 때문에 answer[j]의 값에 1을 더한 것이다. 
			for (int j = 1; j <= i; j++) {
				if(A[j] < A[i]) answer[i] = Math.max(answer[i], answer[j] + 1); 
			}
			max = Math.max(max, answer[i]);
		}

		System.out.println(max);
	}
}

```

이중 for문을 기반으로 동작하기 때문에 시간 복잡도는 `O(n²)`이 된다.
