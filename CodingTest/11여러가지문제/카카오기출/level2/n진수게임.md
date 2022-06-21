[문제](https://programmers.co.kr/learn/courses/30/lessons/17687)

## 주요 내용 

``` 
n = 진수 
t = 미리 구할 숫자 개수
m = 게임에 참가하는 인원 
p = 내 순서 
```

내가 `m명`이 참가하는 `n진수 게임`에서 `p번째`에 숫자를 외칠 것이다. 내가 말해야 할 숫자를 `t개` 구해놓을 예정이다. 



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
        String answer = "";
        
        // 내가 구할 개수가 16개인데 인원이 3명이라면... 총 48개의 숫자가 필요하다. 
        // 즉, 최소 t * m 이상은 있어야 한다. 
        
        int k = 1; // 자릿수
        int sum = n; // 1의 자리는 n진수 만큼 있다.
        while(true) { 
            
            if(t * m <= sum) break;
            
            k++;
            sum += k * (int)Math.pow(n, k-1) * (n-1);
        }
        
        // 이를 통해 n진수 숫자를 k자리까지만 구해주면 된다. 
        // ex) 16진수 숫자를 2자리까지만 구해준다. 
        // ex) 2진수 숫자를 3자리까지만 구해주면 된다. 
        
        // 인원이 12명인데 내가 해야할 순서가 5번째 = 5번째, 17번째, 32번째 ...        
        // 인원이 m명인데 내가 해야 할 순서가 p번째 = p, p + m, p + 2m, p + 3m ....
        // 바꿔 말하면 (순서) % m == p 인 경우
        
        // 진법 변환 
        // https://cornarong.tistory.com/48
        // Integer.toString(원하는 숫자, 진수);
        
        // k = 최대 자리수 
        // 그렇다면, 10진수에서는 n^k - 1 까지의 값을 변환해주면 된다.
        
        String number; 
        int order = 1; // 순서 
        StringBuilder sb = new StringBuilder(); 
        
        for(int i = 0; i < (int)Math.pow(n, k); i++) { 
            
            number = Integer.toString(i, n); // i값을 n진수로 변환한 값
            // System.out.println("변환한 숫자 = " + number);
            
            // for(char digit : number) { 
            for(int j = 0; j < number.length(); j++) { 
                
                if(sb.toString().length() == t) break;
                
                // if(order % m == p) sb.append(digit);
                if(order % m == p) {
                    
                    if(Character.isDigit(number.charAt(j))) sb.append(number.charAt(j));
                    
                    // 10진수 이상으로 변환될 때 문자로 표현된 숫자들은 대문자로 바꿔줘야 함
                    else sb.append(Character.toUpperCase(number.charAt(j)));
                    
                }
                
                // m = 2, p = 2인 상황에서 위에 있는 if문을 만족하는 건 불가능하다. 
                // 2로 나누는데 나머지가 2가 될 수 없기 때문이다. 
                // 그래서, 별도의 조건을 설정했다.
                else if(m == p && (order % m == 0)) { 
                    
                    if(Character.isDigit(number.charAt(j))) sb.append(number.charAt(j));
                    
                    // 10진수 이상으로 변환될 때 문자로 표현된 숫자들은 대문자로 바꿔줘야 함
                    else sb.append(Character.toUpperCase(number.charAt(j)));
                    
                }
                
                order++;
                
            }
            
            if(sb.toString().length() == t) break;   
        }
        return sb.toString();
    }
}
```
