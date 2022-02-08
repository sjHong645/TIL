### 문제

#### 문제 설명 

H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 

어느 과학자의 H-Index를 나타내는 값인 `h`를 구하려고 합니다. 위키백과에 따르면, H-Index는 다음과 같이 구합니다.

어떤 과학자가 발표한 논문 `n`편 중, `h번을 초과`해서 인용된 논문이 `h편 이상`이고  
나머지 논문이 `h번 이하` 인용되었다면 `h의 최댓값`이 이 과학자의 H-Index입니다.

어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때,  
이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

#### 제한 사항 

- 과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
- 논문별 인용 횟수는 0회 이상 10,000회 이하입니다.

#### 입출력 예

| citations | return |
| --- | --- |
| [0, 1, 2] | 1 |
| [9, 9, 9, 12] | 4 |

### 입출력 예 설명 

1) 입력 - [0, 1, 2] / 출력 1

`인용 횟수 1`보다 더 많이 인용된 논문은 `1개`(2번 인용된 논문)다.  
나머지 논문(0, 1번 인용된 논문)은 모두 1번 이하로 인용되었다.

따라서, h의 최댓값은 1이다.

2) 입력 - [9, 9, 9, 12] / 출력 4

`인용 횟수 4`보다 더 많이 인용된 논문은 `4개`(9, 9, 9, 12번 인용된 논문)다.  
나머지 논문은 없다. 

따라서, h의 최댓값은 4이다.

### 정답 코드 

``` java
import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int[] citations) {	
		// int[] citations = {3, 0, 6, 1, 5};
		// int[] citations = {0, 0, 0, 0, 0};
		
		// int[] citations = {0, 0, 0, 0, 1};
		// int[] citations = {9, 9, 9, 12};
		// int[] citations = {9, 7, 6, 2, 1};
		// int[] citations = {10, 8, 5, 4, 3};
		// int[] citations = {25, 8, 5, 3, 3};
		
		
		List<Integer> ans = new ArrayList<>();
		
    // 입력 배열 정렬 
		Arrays.sort(citations); 
		
    // 입력 배열의 길이를 n에 저장했다.
		int n = citations.length;
		
		int h = 0; // 논문의 인용 횟수를 h로 나타내도록 했다.
		
    
		while(h <= 10000) {
			int cnt = 0; // h보다 인용 횟수가 많은 논문의 개수를 저장할 변수
			
      // 인용 횟수 h보다 큰 논문이 있다면 cnt를 1씩 증가
			for(int i = 0; i < n; i++) {
				if(citations[i] > h) cnt++;	
			}
			
      // 인용 횟수 h보다 많이 인용된 논문의 개수가 h라면 그 값은 일단 저장
      // 데이터가 자료구조의 뒤에서 추가되기 때문에 
      // 맨 뒤에 있는 값이 최종적으로 return할 값이 된다.
			if(cnt == h) ans.add(h);
			
      // h의 값이 배열에 있는 성분들의 모든 값보다 커진다면
      // 더 이상 작업할 필요가 없기 때문에 멈춘다.
			if(h > Arrays.stream(citations).max().getAsInt()) break;
			
			h++;
		}

		// 배열이 비어있으면 0
    // 그렇지 않으면 최댓값을 return하도록 했다.
		if(!ans.isEmpty()) return ans.get(ans.size() - 1);
		
		else return 0;
	}
    
}
```
