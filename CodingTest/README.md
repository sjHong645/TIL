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

public class ~~ {
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
} 
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

# char 형 숫자를 int형 숫자로 바꾸고 싶다면 '0'을 빼주면 된다. 마찬가지로 아스키 코드를 활용한 방법이다.

```

- String ⇒ int 형

``` java
Integer.parseInt(원하는 문자열);
```

- 숫자 String ⇒ 숫자 int형 배열

``` java
import java.util.stream.Stream;

String numbers = "10323"; 

int[] arrNum = Stream.of(numbers.split("")).mapToInt(Integer::parseInt).toArray();

// 출력 결과 arrNum = {1, 0, 3, 2, 3)

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

## 6. 배열 또는 List 내의 최댓값 찾기

- 배열 ⇒ 스트림 이용하는 방법이 있음
``` java
import java.util.Arrays 

Arrays.stream(배열 이름).max(),getAsInt() 

// `배열 이름`에 있는 값들의 최대값을 찾아서 그 값을 Int 형으로 변환한다.
```

- List 형 

Collections.max() 메서드 이용하는 방법이 있음.

``` java
List<Integer> numbers = List.of(4, 0, 5, 2, 7, 1, 8, 6, 9, 3);

int max =  Collections.max(numbers);

```


## 7-1. 소수점 자리수 지정 ⇒ DecimalFormat 클래스 사용 
## 7-2. 올림 ⇒ Math.ceil() 사용

double Math.ceil(double a) : 입력받은 `a`보다 크거나 같은 `정수` 중 `최소 정수`를 return 한다.

ex) Math.ceil(2.4) : `2.4`보다 크거나 같은 정수들 중 최소 정수인 `3`을 return한다. 

Math.ceil(-1.3) : `-1.3`보다 크거나 같은 정수들 중 최소 정수인 `-1`을 return한다.

[출처](https://selfdevelope.tistory.com/643)
``` java 

System.out.println((double) 12000 / 5000); // 2.4 출력 

int n = (int) Math.ceil((double) 12000 / 5000); // 2.4에서 소수점 1번째 자리에서 '올림'
System.out.println(n); // 따라서, 3 출력 


```

## 7-3. 원하는 자리수 출력 ⇒ System.out.println() 설정

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

[출처](https://st-lab.tistory.com/81)

- `n`이라는 숫자가 `소수`라면 `√n 이하의 모든 자연수들`을 나눴을 때 나눠떨어지는 값이 없어야 한다.

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

## 15. 배열의 인덱스 출력하기 

[출처](https://technote-mezza.tistory.com/99)

배열에서는 indexOf()를 지원하지 않고   

ArrayList 자료구조에서만 지원하기 때문에 asList()를 통해 배열을 List 형태로 변환한 다음에 indexOf() 메소드를 사용하면 된다.

``` java
import java.util.Arrays;

public class IndexOfTest {
    public static void main(String[] args) {
		String[] arr = {"a","b","c"};
		System.out.println(Arrays.asList(arr).indexOf("b")); //1이 출력된다.
}
```

## 16. 한꺼번에 출력하기 - StringBuilder 이용 

[출처](https://ecsimsw.tistory.com/entry/Java-StringBuilder-%EB%AA%A8%EC%95%84%EC%84%9C-%EC%B6%9C%EB%A0%A5-vs-%EB%A7%A4%EB%B2%88-%EC%B6%9C%EB%A0%A5)

[출처](https://onlyfor-me-blog.tistory.com/317)

System.out.print는 바로바로 출력하는 방법 중 하나이다. 하지만, 이 방법은 상황에 따라서 많은 시간을 소요하도록 한다.

때문에 출력할 내용을 곧바로 출력하지 않고 모았다가 한 번에 출력하면 훨씬 시간을 단축시킬 수 있다.

이를 위한 방법 중 하나가 `StringBuilder`이다.

원래 자바에서는 String 객체를 변경하는 것은 불가능하다. 즉, 서로 다른 문자열끼리 합쳐지기 위해서는 여러가지 부가적인 과정들이 필요하다.

StringBuilder는 String 객체와 달리 변경할 수 있는 문자열을 만들어주기 때문에  
String 뿐만 아니라 출력하고자 하는 내용을 한꺼번에 출력하고 싶을 때 유용하게 사용할 수 있다.

- 사용법

``` java

import java.lang.StringBuilder;

public class Main
{
    public static void main(String[] args)
    {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        list.add("첫 번째, ");
        list.add("두 번째, ");
        list.add("세 번째, ");
        list.add("네 번째, ");
        list.add("다섯 번째");
        for (int i = 0; i < list.size(); i++)
        {
            stringBuilder.append(list.get(i));
        }
        System.out.println(stringBuilder);
    }

}
// >> 첫 번째, 두 번째, 세 번째, 네 번째, 다섯 번째
```

## 17. 숫자 비교 

int 자료형의 범위는 -2³¹ ~ 2³¹ - 1

int형 자료는 4bytes 크기의 데이터를 표현하는데 4bytes = 32bits이므로 2³² 개의 숫자를 표현할 수 있다.

이때, int형은 음수까지 표현하기 때문에 2³²을 절반으로 나눠서 반은 음수 반은 양수를 표현하도록 했다. 

10^9 ≤ 2³¹ - 1 ≤ 10^10

## 18. String 비교 

`String`은 기본 자료형이 아니라 `객체`이다. 

그래서 `==`를 이용해서 두 `String`을 비교하려고 한다면 실제 두 값이 같은지 여부가 아니라 `두 값이 위치한 주소`가 같은지를 따진다.

그렇기 때문에 문자열을 비교하기 위해서는 `.equals()` 메소드를 사용해야 한다.

ex) 
``` java
str = "hello"; 

str2 = "hello"; 

if(str.equals(str2)) { 
	
  System.out.println("Match!);
} 

else System.out.println("Not");

```

## 19. int형 vs Integer 형 

큰 차이는 없지만 `int형`은 `null`로 초기화할 수 없고 `null`인지 아닌지를 check할 수 없다.  
반면에 `Integer형`은 `null`로 초기화할 수 있고 check도 가능하다.

## 20. 기본적인 조합 코드 

[출처](https://bcp0109.tistory.com/15)

예를 들어, 1, 2, 3, 4라는 4개의 자료가 있는 배열이 있다 하자. 그 중 2개를 뽑는 경우의 수를 구하고 싶다. 

그렇다면 이렇게 생각할 수 있다. 

1) 맨 앞에 있는 `1`을 포함한 `나머지 1개` 뽑기 (나머지 = 2, 3, 4)
2) 그 다음에 있는 `2`를 포함한 `나머지 1개` 뽑기 (나머지 = 3, 4)
3) 그 다음에 있는 `3`을 포함한 `나머지 1개` 뽑기 (나머지 = 4)
4) 종료 

ex) `4개의 자료`를 갖고 있는 arr배열에서 `2개를 뽑는` 조합의 경우의 수
``` java
    public static void main(String[] args) {
    	  	
    	int[] arr = {1, 2, 3, 4}; // 주어진 배열
    	boolean[] visited = new boolean[arr.length];
    	
    	int n = 4; int r = 2; 
    	
    	combination(arr, visited, 0, n, r);
    	
    }
    
    
    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
    	
	// r == 0 이라는 건 더 이상 뽑을 수 있는 원소가 없다는 것.
	// 그래서 여기서 멈추고 원하는 동작(ex. 출력)을 하면 된다.
        if(r == 0) {
            print(arr, visited, n); // 방문한 원소들을 출력 
            return;
        } 
	
	
        for(int i = start; i < n; i++) {
            visited[i] = true; // i번째 원소 방문
            combination(arr, visited, i + 1, n, r - 1); // i+1 번째 이후의 원소들 중에서 나머지 뽑기
            visited[i] = false; // i번째 원소 방문 종료
        }
    } 
    
    
    static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
    
```









