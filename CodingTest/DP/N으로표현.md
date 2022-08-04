[문제](https://school.programmers.co.kr/learn/courses/30/lessons/42895)

[정답 출처](https://small-stap.tistory.com/65)

## 주요 흐름

1) 숫자를 어떻게 만들지 생각하는게 아니라 `원하는 숫자(number)`가 `몇 개의 N`으로 이뤄졌는지에 초점을 둔다.

ex) N을 4번 사용한 숫자 

- (N을 `3번` 사용한 숫자) + (N을 `1번` 사용한 숫자)
- (N을 `2번` 사용한 숫자) + (N을 `2번` 사용한 숫자)
- (N을 `1번` 사용한 숫자) + (N을 `3번` 사용한 숫자)

2) Map 설정
``` 
key = N을 사용한 개수 
value = key개수 만큼 사용해서 만들어진 값의 집합(중복되지 않기 위해 Set으로 선언)
```



## 정답 코드
``` java
// 1차 - 7/13(수)
// 2차 - 7/16(토) - 여전히 모르겠다.
// 3차 - 7/23(토) - 여전히 모르겠다.
// 4차 - 8/1(월)
// 5차 - 8/4(목) - 답을 봤음

import java.util.Map;
import java.util.HashMap;

import java.util.Set; 
import java.util.HashSet; 

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for(int i = 1; i <= 9; i++) map.put(i, new HashSet<>());
        
        // 초기 설정
        map.get(1).add(N); // N을 1번 쓴 숫자는 'N' 하나밖에 없음
        
        // 이제 N을 2~8번 사용했을 때 나올 수 있는 값들을 구한다
        for(int i = 2; i <= 8; i++) {
           
            for(int j = 1; j < i; j++) {
                
                // N을 i번 사용한 경우의 수
                // (N을 j번 사용한 경우의 수) + (N을 i-j번 사용한 경우의 수)
                
                for(int preValue : map.get(j)){ // N을 j번 사용한 값들
                    
                    for(int postValue : map.get(i - j)) { // N을 i-j번 사용한 값들
                        
                        // (N을 i번 사용한 값) = (N을 j번 사용한 값) + (N을 i-j번 사용한 값)
                        map.get(i).add(preValue + postValue);
                        map.get(i).add(preValue - postValue);
                        map.get(i).add(preValue * postValue);
                        
                        if(preValue != 0 && postValue != 0) {
                            map.get(i).add(preValue / postValue);    
                        }
                        
                        
                    }
                }
                
                // N을 i번 반복한 숫자 추가
                map.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i)));
                
            }
            
            
        }
        
        // number가 속해있는 key값이 있다면 return 
        // 없으면 -1 return
        for(int k : map.keySet()) {
            for(int sub : map.get(k)) {
                if(sub == number) return k;
            }
            
        }
        
        
        return -1;
    }
}
```
