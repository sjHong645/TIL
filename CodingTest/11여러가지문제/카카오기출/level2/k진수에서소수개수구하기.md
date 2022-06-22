[문제](https://programmers.co.kr/learn/courses/30/lessons/92335#)

## 주요 내용 

1) `n`을 `k진수의 n`으로 바꾼다.
2) 거기서 0을 제외한 `모든 숫자들`을 `list`에 집어넣는다.
3) list에 있는 숫자들이 `소수인지 판별`한다. 

주의! : list에 있는 원소를 숫자로 바꿀 때 `int`로 저장할 수 없는 `큰 숫자`일 수도 있기 때문에 `long` 형태로 변환해서 소수인지 판별했다.

## 정답 코드 

``` java
import java.util.List;
import java.util.ArrayList; 

import java.lang.StringBuilder;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        List<String> list = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        
        // 주어진 n을 k진수로 바꿈
        String result = Integer.toString(n, k);
        
        // 일단 조건에 맞는 숫자들을 찾는다. - 그 숫자들은 0을 포함하지 않음
        for(int i = 0; i < result.length(); i++) { 
            
            // 0이 아니라면 sb에 쌓아놓는다.
            if(result.charAt(i) != '0') sb.append(result.charAt(i));
            
            // 0을 만났을 때 
            else if((result.charAt(i) == '0') && sb.length() != 0){
                
                list.add(sb.toString()); // 지금까지 쌓아놓은 sb의 내용을 저장하고
                sb.setLength(0); // sb를 초기화한다.                    
                
            }
            
        }
        
        // sb가 0을 만나지 못한채로 끝날수도 있다.
        // 이때, sb에 남아있는 값이 있을 수 있다. 
        if(sb.length() != 0) list.add(sb.toString());
        
        
        // 그 숫자들이 소수인지 확인한다.
        for (String temp : list) {
            
            if(temp.length() == 0) continue;
            
            // 소수이면 추가
            // 그 숫자가 int로 저장할 수 없는 크기가 큰 숫자일 수 있다.
            // 그래서 long으로 변환했다.
            if(isPrime(Long.parseLong(temp))) answer++;
            
        }
        
        return answer;
    }
    
    // n이라는 숫자가 소수라면 
    // √n 이하의 모든 자연수들을 나눴을 때 나눠떨어지는 값이 없어야 한다
    static boolean isPrime(long number) { 
        
        if(number < 2) return false; 

        if(number == 2) return true;

        for(int i = 2; i <= (int)Math.sqrt(number); i++) { 

            if(number % i == 0) return false; // 하나라도 나눠떨어진다면 소수가 아니다.

        }

        return true;
        
    }
}
```
