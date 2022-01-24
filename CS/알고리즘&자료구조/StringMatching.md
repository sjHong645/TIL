## String Matching 

### 개요 

Input : 전체적인 문장은 `A[1 ... n]`라고 하자. 이때 `n`은 `문서의 글자 수`가 되겠다.

찾고자 하는 문자열 즉, `pattern`은 `P[1 ... m]`라고 하자. 이때 m은 패턴의 글자 수다. (∵ m <= n)

Output : 찾고자 하는 문자열 `P`가 `A안에 존재할 때` `값을 출력`한다. 이때 값은 아래의 그림에서 보자.  
![image](https://user-images.githubusercontent.com/64796257/150729991-4846e023-20be-458f-aa67-ff9dab4a8eb0.png)

### 문자열 매칭의 4가지 방법 

#### 1. Naive Match : 원시적인 매칭 방법 

![image](https://user-images.githubusercontent.com/64796257/150730969-2c8a5e0c-f472-45d4-812b-4dda12ebb332.png)
![image](https://user-images.githubusercontent.com/64796257/150730973-bd96ffa8-65d8-4672-be36-3988480f26f5.png)

![image](https://user-images.githubusercontent.com/64796257/150731028-f99b3907-80ec-4643-bf87-eea48a95cb2f.png)

`Naive 방식`을 어떻게 개선할 수 있을까??  
![image](https://user-images.githubusercontent.com/64796257/150731051-a4564520-618f-40cf-b527-13abaad83b5a.png)

이 방식으로 인해 생길 수 있는 문제는 `P 문자열 일부`가 `A 문자열 일부`랑 `맞지 않을` 때 다른 건 다 맞는데 하나만 안 맞는 상황이다.  
그러면 `Naive 방식`에 따라 `P 문자열`을 `한 칸 씩 이동`하면서 계속 비교해줘야 한다. 아래의 그림을 보자.

![image](https://user-images.githubusercontent.com/64796257/150731214-03ca022c-d657-4fc2-a14c-af4837d55b1f.png)

그런데 잘 보면 `P 문자열의 일부분`은 `A의 부분적인 문자열`과 `match`가 된다. 그래서 아래와 같은 방식을 취할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150731255-5d8f8d7f-86c6-4f52-b3aa-ce1d175d6557.png)

굳이 `다 비교할 필요 없이` `A 문자열`에 있는 `d`앞에 `abc`는 `P와 동일`하니까  
`1, 2, 3, 4, 5`를 `다 할 필요 없이` 곧바로 1이 안되면 5로 넘어가면 된다.

#### 2. Automata Matching 
원리를 여기서 줄줄 쓰는 것 보다는 예시를 함께 보면 이해가 쉽다. 예시를 보자.

예시) 여기서 `ababaca`라는 문자열의 위치를 찾고 싶다.  

현재 `A`가 가리키는 문자는 `t`이다. `a`라는 문자가 아니기 때문에 다음으로 넘어가지 않는다.

맨 처음 A[1] 부터 시작한다.  
여기서 빨간상자 부분을 보면 그 부분을 가리키는 문자가 `a`가 오면 다음 문자열로 넘어가고 그렇지 않으면 계속 그곳에 머물도록 했다.

![image](https://user-images.githubusercontent.com/64796257/150731529-6bfaefce-e988-4eda-b18c-4a5b3d12bc05.png)

현재 `A`가 가리키는 문자는 `a`다. 그래서 `오토마타의 녹색 상자 부분(0번째)`에서 `빨간상자 부분(1번째)`으로 넘어갔다.

![image](https://user-images.githubusercontent.com/64796257/150731578-2ce41bbb-0e74-4ac8-ac82-7fb145fe7557.png)

현재 `A`가 가리키는 문자는 `c`다.   
원래 오토마타는 `녹색 상자 부분`에 있었는데 `a`도 아니고 `b`도 아닌 문자가 왔기 때문에 `맨 처음 부분`인 `빨간 상자 부분으로` 돌아갔다.

![image](https://user-images.githubusercontent.com/64796257/150731754-6661ea4f-74e4-4846-b9c3-22c305d509ce.png)

그러고 나서 `문자열 A`에는 `ababa`를 차례대로 가리킨다. 이는 `원하는 문자열 패턴`이기 때문에 오토마타에서 하나씩 하나씩 잘 넘어간다.

![image](https://user-images.githubusercontent.com/64796257/150731821-429a2d8b-fad1-4d91-a94e-a62533cc78c5.png)

그 다음 `b`를 가리키는데 원하는 문자열 패턴은 `ababaca` 였기 때문에 문자열이 맞지 않는다.

원래는 오토마타에서 `빨간 상자 부분`을 가리켰다.  
하지만, 문자열 `A`에서 c가 아닌 `b`가 나오면서 가리키는 화살표대로 `4번으로 다시` 돌아가게 되었다.

![image](https://user-images.githubusercontent.com/64796257/150731923-309ac32e-b9dd-4747-8249-bc26cde614e7.png)

그러고 나서 순서대로 `a, c`가 나온다.

![image](https://user-images.githubusercontent.com/64796257/150731937-0bb04835-7b17-41a3-9e0c-6a0eec641303.png)

마지막 `a`를 가리키면서 찾고자 하는 문자열을 찾게 된다.

![image](https://user-images.githubusercontent.com/64796257/150731978-32c75e08-05ce-46c1-868b-bd67c56b4f49.png)

그렇게 찾고 나면, 일치한 문자열의 `A문자열`에서 P문자열과 일치하는 첫 번째 문자열의 index값을 return 한다.

`A의 i번째에서` 끝났다고 했을 때 `P의 길이는 m`이기 때문에 `i-m+1`이라는 값을 `출력`한다.

이렇게 하나를 찾고 나서 그 다음 부터는 위에서 살펴본 과정을 똑같이 진행하면 되겠다.

![image](https://user-images.githubusercontent.com/64796257/150732127-a665ce31-4d71-494d-9379-d36dc02424aa.png)

`n개의 문자열`을 반복하면서 진행하기 때문에 시간 복잡도는 `O(n)`이 되겠다.

오토마타를 직접 만든다면 시간복잡도가 달라지지만 이미 만들어져 있는 오토마타를 가지고 비교만 수행한다면 O(n)의 시간복잡도를 가진다.

### Rabin-Karp 알고리즘








### KMP 알고리즘 

















