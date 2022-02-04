# Tips

## 1. Scanner 클래스는 import java.util.Scanner 를 해줘야 한다.

## 2. BufferedReader 와 BufferedWriter는 빠른 속도로 읽고 출력하는 방법이다. 

⇒ 이때, import 해야 하는 패키지는 다음과 같다.  

``` java
import java.io.IOException; 

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
```

그리고 Scanner로만 읽을 꺼면 Scanner만 읽어야 하고 BufferedReader로 읽을 꺼면 BufferedReader로만 읽어야 한다.

## 3. String형 → char형 & String형 → 배열 & char형 → int형(알파벳)

- String형 → char형
``` java
String input = "안녕하세요";

char c = input.charAt(0) # input이라는 string의 0번째 값 indexing 

# 따라서, `안` 이라는 글자가 c에 저장된다.
```

- String형 → 배열

``` java
int number1 = 12223; 

String number = String.valueof(number1); # int형 값을 String 형으로 변환

char[] digit = number.toCharArray() # string형인 number의 값을 배열로 저장.
                                    # 이때, 각 요소는 char형.
                                    
```
- char형 → int형

``` java
String str = "baekJoon";

char example = str.charAt(2); # str이라는 string형 문자의 2번째 값을 example에 저장
                              # 여기서는 'e'가 저장

int any = example - 'a'; 
# 알파벳을 숫자로 바꾸고 싶다면 'a'를 빼주면된다. 아스키 코드를 활용한 방법이다.

```


## 4. 집합을 의미하는 Set 프레임워크
``` java
import java.util.Set; 
import java.util.HashSet;

```
선언은 아래와 같이 한다.
``` java

Set<원하는 자료형> 이름 = new HashSet<>(); 

```

여기서 지정한 이름을 set이라 한다면 

```
set.add(원하는 내용) # '원하는 내용'을 집합 set에 추가한다.

set.size() # 집합의 크기를 return

```

집합은 1) 저장 순서가 상관없고 2) 데이터의 중복 저장을 허용하지 않을 때 고려해볼만 한 클래스이다.

## 5. StringTokenizer 

``` java
import java.util.StringTokenizer; 

StringTokenizer st = new StringTokenizer(입력값); # 입력값에 넣은 문자열을 '띄어쓰기' 단위로 나눠서 읽어들인다. 

st.nextToken() # 띄어쓰기 단위로 나눠서 읽어들인 st의 각 요소를 읽는 메서드


```

- countTokens() : StringTokenizer로 구분해 놓은 Token들의 개수를 구한다.

## 6. 스트림을 이용한 배열 내의 최댓값 찾기

``` java
import java.util.Arrays 

Arrays.stream(배열 이름).max(),getAsInt() 

# `배열 이름`에 있는 값들의 최대값을 찾아서 그 값을 Int 형으로 변환한다.

```

## 7. 소수점 자리수 지정 ⇒ DecimalFormat 클래스 사용 & 올림 ⇒ Math.ceil() 사용 & 원하는 자리수 출력

- 소수점 자리수 지정

``` java
DecimalFormat form = new DecimalFormat("#.###"); 

form.format(값); # 원하는 값을 소수점 3자리까지 노출하도록 한다. 

                # 하지만, return 값의 type은 string이 된다.

```

- 올림 : Math.ceil() 사용하면 됨

- 저장이 아닌 `출력`만 하고 싶다면 System.out.println()을 사용하면 된다.

ex. System.out.println("%.6f", 숫자); // 원하는 숫자를 소수점 6번째 자리까지 출력한다.

## 8. 숫자 거꾸로 뒤집는 알고리즘

ex. 734 ⇒ 437 

``` java

static int Converse(int num) { 

  int result = 0; 
  
  while(num != 0) {
  
    result = result * 10 + num % 10;
    num /= 10;
  
  }
  
  return result;

} 

```

## 9. 배열의 길이 ⇒ 배열.length / 문자열의 길이 ⇒ 문자열.length()

## 10. 큰 정수 ⇒ BigInteger 사용

``` java

import java.math.BigInteger;

BigInteger A = new BigInteger(원하는 값); 

# 이러면 원하는 값이 `큰 수`를 나타낼 때 문제없이 A에 저장할 수 있다.

```

## 11. 소수 판별

- n이라는 숫자가 소수라면 n과 √n 이하의 모든 자연수들을 나눴을 때 나눠떨어지는 값이 없어야 한다.

ex. 15가 root 15이하의 자연수인 1,2,3이랑 나눴을 때 나눠떨어지는 값이 없다면 15는 소수라고 할 수 있다.

``` java

boolean isPrime(int number) { 

  if(number < 2) return false; 
  
  if(number == 2) return true;
  
  for(int i = 2; i <= Math.sqrt(number); i++) { 
    
    if(number % i == 0) return false; 
  
  }
  
  return true;

} 

```

- 에라토스테네스의 체 : k = 2 부터 root N 이하까지 반복적으로 N 이하의 자연수들 중 k의 배수들을 제외시킨다.

ex. N = 1000 ⇒ 1000이하의 자연수들 중 2의 배수, 3의 배수, 5의 배수 등등을 제외시켜서 남은 값들은 `소수`가 된다. 

⇒ 이런 방식으로 소수들을 알아내는 방법이 `에라토스테네스의 체`이다. 

``` java

void makePrime(int N) { 

  prime = new Boolean[N + 1] // 소수가 아닌 index는 true, 소수인 index는 false가 되도록 할 것임
  
  prime[0] = prime[1] = true; // 0과 1은 소수가 아니니까 true로 지정
  
  if(N < 2) return; // 2 이하일 때는 소수 판별을 할 필요가 없어서 종료
  
  for(int i = 2; i <= Math.sqrt(N); i++) { 
  
    if(prime[i] == true) continue; // 이미 true이면 다음 반복문으로 넘어간다.
    
    for(int j = i * i; j < prime.length; j = j+1) {
    
      prime[j] = true; 
      
    } 
  
  }

} 

```

## 12. 정수 자리수 구하기 ⇒ (int)(Math.log10(숫자) + 1)

## 13. 구조체 배열

Java는 `구조체`라는 용어가 아닌 `클래스`라는 개념으로 설명한다.

ex) 

``` java

class bulk { 

  int x;
  int y;
  int rank;

} 

bulk[] bul = new bulk[N] # 여기서 N은 임의의 상수

for ( ~ ) { 

  bul[i] = new bulk(); # 이 과정이 꼭 필요함.
  
                       # bul이라는 클래스 배열에 있는 각각의 index에 대해 
                       # bulk 클래스에 대한 생성자를 호출해야 클래스 배열을 사용할 수 있다.
}

```

![image](https://user-images.githubusercontent.com/64796257/147908004-8017084d-5304-4052-9100-d56f491cac9d.png)

## 14. 여러 개 숫자 중 최댓값 찾기 

`Math.max()` 함수를 이용하면 된다.

ex) 
``` java
Math.max(first, Math.max(second, third));
```
second와 third를 비교해서 최댓값을 return 한다. 

그 최댓값과 first를 비교해서 또 다른 최댓값을 return한다.

즉, first, second, third 이 3개의 숫자의 최댓값을 구한 것과 동일한 동작을 한다.
