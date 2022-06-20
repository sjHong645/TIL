## 재귀호출 : 함수 안에서 함수가 자기자신을 호출하는 방식. 알고리즘을 구현할 때 유용하다.

ex. 팩토리얼 

![image](https://user-images.githubusercontent.com/64796257/148895636-c332e77b-68fe-424c-89eb-abc96414e5e4.png)

n!을 재귀적으로 정의하면 아래와 같다. 

![image](https://user-images.githubusercontent.com/64796257/148895643-bbb0bef0-1d83-4482-89c5-39d57a7094a2.png)

여기서 `if(n==1) 부분`과 같이 해당 함수를 `멈출 수 있는 부분`을 만들어주는 것이 중요하다.  
그렇지 않으면 무한으로 함수를 호출하기 때문에 시스템 오류가 발생한다.

![image](https://user-images.githubusercontent.com/64796257/148895659-7a4f5e94-44ac-45eb-9be2-9d6ce7459a30.png)

ex. 거듭제곱

![image](https://user-images.githubusercontent.com/64796257/148895775-a1e67451-1c78-4d1d-8add-d8ecf2b080fa.png)

![image](https://user-images.githubusercontent.com/64796257/148895779-1d7450d3-3fa5-4412-988e-ad27e3fa757e.png)

- 시간 복잡도 계산

![image](https://user-images.githubusercontent.com/64796257/148895882-22617d3a-47f3-41ba-8914-2fb994c9d094.png)

이러한 과정을 통해서 시간복잡도 `T(n) = O(n)`이라는 것을 알 수 있다.

이때, power 함수를 다른 방법으로 분할해서 생각해볼 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148896056-71a3c7f6-a22c-475e-bac7-abdf9790f65d.png)

![image](https://user-images.githubusercontent.com/64796257/148896062-94d1b9b1-0f14-4890-bde2-20bd86781a56.png)

시간 복잡도 계산 과정을 좀 더 자세하게 살펴보면

![image](https://user-images.githubusercontent.com/64796257/148896462-0eada5e0-9f8f-4989-99bc-c228851d5b57.png)

그렇다면 부등식을 다음과 같이 정리해볼 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148896536-1bba0b27-beda-487a-8dfe-c7f3a151d7b6.png)

### 대표 문제 

#### 피보나치 수열 문제 

![image](https://user-images.githubusercontent.com/64796257/148896694-f2791e8e-848b-496d-8f29-8d8e6cef5c0b.png)

이를 그대로 구현하면 다음과 같이 구현할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148896738-f97ed762-924a-4d82-ae31-6782a2b306e2.png)

하지만 이 방식은 비효율적이다. 시간복잡도를 계산해보면 알 수 있다.

`fib(n)`의 시간복잡도를 `T(n)`이라고 한다면 다음과 같이 계산할 수 있다.  
![image](https://user-images.githubusercontent.com/64796257/148896787-d61a5b0d-03a3-43c2-9470-6c49da3c680c.png)

이와 같이 시간복잡도라 `O(2ⁿ)`이 나온다. 같은 항을 중복해서 계산하기 때문에 이와 같은 결과가 나온다.

- 해결 방법  
1) Memoziation : 이미 계산한 피보나치 수를 저장하고 재활용하기  
![image](https://user-images.githubusercontent.com/64796257/148896930-8acff4be-af66-449e-b9e5-2c211300ed02.png)

``` C
int F[100] = {0, }; 

int fib(int n) { 

  if(n <= 1) return n;
  if(F[n] != 0) return F[n];
  F[n] = fib(n-1) + fib(n-2);
  return F[n];
}
```

저장을 위해서 F라는 배열을 선언한다.

F[n]의 값이 0이 아니라는 건 이미 계산이 완료되었다는 것을 의미하니까 해당 값을 곧바로 return 해주면 된다.

그렇지 않으면 fib(n-1) + fib(n-2)를 통해 계산한 값을 F[n]에 넣어주면 된다.

시간 복잡도와 공간 복잡도는 다음과 같다.

① 시간 복잡도 : ![image](https://user-images.githubusercontent.com/64796257/148897009-5c272b4f-69e9-44eb-8a8f-9e1fb006728e.png)  
② 공간 복잡도 : n개의 피보나치 수를 저장해야 하니까 `O(n)`의 메모리가 요구된다.

2) Iterative : fib(n)을 사용하는 목적은 n번째 피보나치 수가 무엇인지 알고 싶은 거다.

즉, n번째 이전의 숫자들은 n번째 숫자를 구하기 위해 필요할 뿐 저장하거나 중복호출하는 것은 어찌보면 낭비라는 생각이 들 수 있다.  
이를 해결한 방법이 바로 Iterative 방법이다. 

이 방법은 코드를 보면 금방 이해할 수 있다.  
``` C 
int fib(int n) { 

  if(n <= 1) return n;
  int Fn_2 = 0, Fn_1 = 1, Fn;
  
  for(int i = 2; i <= n; i++) { 
    Fn = Fn_1 + Fn_2;
    Fn_2 = Fn_1;
    Fn_1 = Fn;
  }
} 
```
이렇게 코드를 작성하면 중복호출도 하지 않고 1)과 같은 방식처럼 O(n) 크기의 배열을 필요로 하지 않는다.

시간복잡도는 반복문을 `n번 반복`하니까 `O(n)`이 된다.

#### 하노이 탑 문제
![image](https://user-images.githubusercontent.com/64796257/148897581-6ba4e9a4-843d-4b5d-8a5e-1572555f8bc3.png)

이 과정을 단순화 시키면  
1) 맨 밑에 있는 원반을 제외한 녹색, 노랑색, 빨간색 원반들은 먼저 A에서 B로 가야한다. 
2) 맨 밑에 있는 원반인 파란색 원반을 A에서 C로 옮긴다. 
3) 나머지 원반들을 B에서 C로 옮긴다. 

좀 더 자세하게 쓰면  
1) 맨 밑에 있는 원반을 제외한 녹색, 노랑색, 빨간색 원반들은 먼저 A에서 (C를 거쳐서) B로 가야한다. 
2) 맨 밑에 있는 원반인 파란색 원반을 A에서 C로 옮긴다. 
3) 나머지 원반들을 B에서 (A를 거쳐서) C로 옮긴다. 

이 내용을 그대로 코드로 옮겨보면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/148897652-2269cd40-86d2-43fd-8968-6da21c448cfc.png)

빨간색 부분은 1)의 과정을 그대로 표현한 거다.   
맨 밑에 있는 것들을 제외한 나머지(n-1)들을 from(A)에서 시작해서 to(C)를 거쳐서 temp(B)에 옮기기

파란색 부분은 맨 밑에 있는 원반(n)을 from(A)에서 to(C)로 옮기기

녹색 부분은 나머지 원반들(n-1)을 temp(B)에서 시작해서 from(A)를 거쳐서 to(C)에 옮기기

시간복잡도는 다음과 같다.  
![image](https://user-images.githubusercontent.com/64796257/148897739-533968d1-60f3-4f4b-9801-f0a57dae8b5d.png)




















