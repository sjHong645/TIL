[문제](https://programmers.co.kr/learn/courses/30/lessons/17687)

## 주요 내용 

``` 
n = 진수 
t = 미리 구할 숫자 개수
m = 게임에 참가하는 인원 
p = 내 순서 
```

1) 정수를 `0`부터 차례대로 n진수로 변환 
2) 변환한 n진수를 1자리씩 탐색하는데 내가 `말해야 할 순서`가 된다면 `말해야 할 숫자`를 StringBuilder에 저장
3) 숫자 t개가 될 때까지 실행

ex) 

![image](https://user-images.githubusercontent.com/64796257/174746529-37656020-1854-4c6c-b5e8-7e26b2a7cf3b.png)

위에서 `네모`로 표시한 값들을 하나씩 `StringBuilder`에 저장해서 길이가 t가 되면 출력한다.


### 진법 변환 

```
Integer.toString(int n, int radix); // 정수 n을 radix진수 값으로 변환
```

[출처](https://cornarong.tistory.com/48)
``` java
public class Solution {
    public static void main(String[] args) {
        // 테스트를 위한 10진수 값 = 25
        int a = 25;

        System.out.println("10진수 -> 2진수");
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toString(a,2)); // 10진수 -> 2진수

        System.out.println(Integer.toString(a,3)); // 10진수 -> 3진수

        System.out.println(Integer.toString(a,4)); // 10진수 -> 4진수

        System.out.println(Integer.toString(a,5)); // 10진수 -> 5진수

        System.out.println(Integer.toString(a,6)); // 10진수 -> 6진수

        System.out.println(Integer.toString(a,7)); // 10진수 -> 7진수

        System.out.println(Integer.toOctalString(a));
        System.out.println(Integer.toString(a,8)); // 10진수 -> 8진수

        System.out.println(Integer.toHexString(a)); 
        System.out.println(Integer.toString(a,16)); // 10진수 -> 16진수
        
    }
}
```

### char를 대/소문자로 바꾸기 

[출처](https://jamesdreaming.tistory.com/160)

``` java

Character.toUpperCase('t'); // 소문자 t가 대문자 T로 바뀐다. 

```


## 정답 코드 

``` java
import java.lang.StringBuilder;

class Solution {
    // n = 진수 
    // t = 미리 구할 숫자 개수
    // m = 게임에 참가하는 인원 
    // p = 내 순서 
    public String solution(int n, int t, int m, int p) {
        
        // 인원이 12명인데 내가 해야할 순서가 5번째 = 5번째, 17번째, 32번째 ...        
        // 인원이 m명인데 내가 해야 할 순서가 p번째 = p, p + m, p + 2m, p + 3m ....
        // 바꿔 말하면 (순서) % m == p 인 경우
                
        String number; 
        
        StringBuilder sb = new StringBuilder(); // 내가 말할 숫자들
        
        int order = 1; // 숫자들을 n진수로 변환해서 쭉 나열했을 때 각각의 자리를 가리키는 인덱스 
        int i = 0; // 주어진 숫자
        while(true) { 
            
            // i값을 n진수로 변환
            number = Integer.toString(i, n); 
            
            // 변환한 숫자 길이만큼 반복문을 실행
            for(int j = 0; j < number.length(); j++) { 
                
                // 내가 말할 숫자들을 모두 구했다면 break
                if(sb.toString().length() == t) break;
                
                // n진수로 나열한 숫자들의 각 자리를 가리키는 인덱스(order)가 
                // 내가 말해야 할 순서라면 그 숫자를 sb에 추가한다. 
                if(order % m == p) {
                    
                    if(Character.isDigit(number.charAt(j))) sb.append(number.charAt(j));
                    
                    // 10진수 이상으로 변환될 때 문자로 표현된 숫자들은 대문자로 바꿔줘야 함
                    else sb.append(Character.toUpperCase(number.charAt(j)));
                    
                }
                
                // m = 2, p = 2인 상황과 같이 m과 p가 같다면 위에 있는 if문을 만족하는 건 불가능하다. 
                // 2로 나누는데 나머지가 2가 될 수 없기 때문이다. 
                
                // 그래서, 별도의 조건을 설정했다.
                else if(m == p && (order % m == 0)) { 
                    
                    if(Character.isDigit(number.charAt(j))) sb.append(number.charAt(j));
                    
                    // 10진수 이상으로 변환될 때 문자로 표현된 숫자들은 대문자로 바꿔줘야 함
                    else sb.append(Character.toUpperCase(number.charAt(j)));
                    
                }
                
                // 1자리씩 넘어갈 때 마다 인덱스 order를 1씩 증가
                order++;
            }
            
            if(sb.toString().length() == t) break;
            
            i++;
            
        }
        return sb.toString();
    }
}
```
