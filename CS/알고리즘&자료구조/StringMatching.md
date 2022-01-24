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

: 문자열의 비교를 `수치 비교로 전환`해서 매칭하는 방법

#### 문자열 패턴을 숫자로 바꾸는 것만 생각하기
그렇다면 어떻게 `문자열 패턴`을 `숫자`로 바꿀 수 있을까?  

`X[i]`를 `문자열 X의 i번째 값`이라고 하자. 그리고 `p`는 `패턴 P`로부터 구한 숫자다.  
패턴 `P[1 ... m]`를 10진수로 바꾸면 다음과 같다.  
(여기서 패턴 P는 내가 찾으려고 하는 문자열이다)

ex)  
![image](https://user-images.githubusercontent.com/64796257/150736157-4122347d-65ee-49ce-8f98-b277f4d504fa.png)

이걸 일반화하면 아래와 같은 식을 세울 수 있다.  
p = ![image](https://user-images.githubusercontent.com/64796257/150736222-a63dd9cc-9e8d-4d85-a5be-43ce748b0288.png)

`a_i`를 부분 문자열 `A[i ... i+m-1]`로 부터 구한 숫자라고 하자. (문자열 A는 주어진 문자열이다)

![image](https://user-images.githubusercontent.com/64796257/150736268-1264705e-c182-4cf8-8214-32822a58917e.png)

이런 상황을 일반화하면  
![image](https://user-images.githubusercontent.com/64796257/150736304-30d30fe2-068c-4f89-8aec-0d847a8ad8e1.png)

하지만, 이런식으로 `p`와 `a_i`를 구한다면 시간이 오래걸린다.  
그래서 `빠르게 계산`할 수 있는 방법을 알아냈는데 그럴 수 있는 이유는 구하는 과정에서 `패턴이 존재`하기 때문이다.

![image](https://user-images.githubusercontent.com/64796257/150736362-265df9cd-b493-402a-9d00-7db0816c8ec7.png)

![image](https://user-images.githubusercontent.com/64796257/150736368-9a863cf2-9930-409e-9110-eb74835e04f7.png)

이런 방식으로 `길이가 m인 문자열`을 `숫자`로 바꾸면 `O(m)`이라는 상수시간 만큼 소요된다.

즉, `패턴 P의 길이가 m`일 때 `O(m)`이라는 상수시간만큼 소요된다는 것을 알 수 있다.

전체 `문자열 A`의 길이가 `n`이라면 `O(m)이 소요되는 동작`을 `총 n번 반복`하게 되므로 시간 복잡도는 `O(mn)`이 된다.  
나름 효율적이라고 생각했지만 원시적인 알고리즘의 시간복잡도와 결국에는 똑같은 시간복잡도를 소요한다.

하지만 `a_i`를 일일이 계산할 필요는 없다. `a_i`와 `a_(i-1)`의 관계는 다음과 같기 때문이다.

![image](https://user-images.githubusercontent.com/64796257/150738324-24c0c48d-a04d-446e-88d8-ba98b836da73.png)
어떻게 이게 성립하는지 예시를 보자.

![image](https://user-images.githubusercontent.com/64796257/150738348-dcb4679a-525e-4a1d-a8e1-6880703a1cd2.png)

a = 0, b = 1, c = 2, d = 3, e = 4와 같이 알파벳들을 숫자로 바꿔서 생각한다.

이때, 알파벳은 5개로 이루어져 있으니까 10 대신에 5를 곱한다.

![image](https://user-images.githubusercontent.com/64796257/150738415-12fffac9-d0fe-49f0-b1d9-fc541087d8b6.png)

![image](https://user-images.githubusercontent.com/64796257/150738428-3b7f2c91-d7ec-450d-b116-8ea3e83493cb.png)

이 방식을 일반화 하면 다음과 같다.

`Σ`를 문자 집합을 나타내는 기호라고 하자. 

ex) 알파벳 ⇒ Σ = {a, b, c, ..., z} ⇒ |Σ| = 26 

즉, 10진수에서 10은 `|Σ|` 값 중 하나의 값이라고 생각하면 된다. `|Σ| = d`라고 하면 10대신에 d라는 숫자로 바꾸면 된다.

![image](https://user-images.githubusercontent.com/64796257/150738780-a8563e78-59fd-4748-b984-689505b84828.png)

하지만, 이러한 방식은 `|Σ|`의 크기와 `m의 길이`가 너무 크다면 `overflow`가 발생할 수 있다.

#### Rabin-Karp 알고리즘의 등장 

`Rabin – Karp 알고리즘`은 수치화 알고리즘의 골격을 사용해서 문제를 해결한 알고리즘이다.  
`a_i`를 직접 다루는 대신에 `모듈러(mod) 연산의 결과`를 이용한다.

컴퓨터 레지스터가 감당할 수 있는 범위에서 `충분히 큰 소수 q`를 하나 잡아서  
`a_i` 대신에 `b_i = a_i mod q`(b_i = a_i를 q로 나눴을 때 나머지)를 이용한다. 

`b_i`와 `b_(i-1)`의 관계는 `a_i`와 `a_(i-1)`의 관계와 `유사`하다.

![image](https://user-images.githubusercontent.com/64796257/150739830-5a90c170-05ec-42d5-b02b-c58117cefdf7.png)
에서 q로 나눈다고 생각하면 된다. 

![image](https://user-images.githubusercontent.com/64796257/150739909-1fd63611-74e1-4002-b665-c43a3f7ea626.png)

![image](https://user-images.githubusercontent.com/64796257/150739919-b681b520-5e9f-4fa0-bf22-e29d96fcfd23.png)

작동 과정을 그림으로 보면 위와 같다. 여기서는 q = 113이라 함.

앞서 살펴봤던 알고리즘에서 모든 값을 q라는 소수로 나눴다고 생각하면 된다. 의사코드를 보자.

![image](https://user-images.githubusercontent.com/64796257/150739940-94892ca3-2271-4070-bd2f-55034c419a70.png)

빨간 상자를 통해서 `for 루프`를 `n-m+1번 반복`하는데 `m < n`이므로 이에 대한 시간복잡도는 `Θ(n)`이 된다.

녹색 상자는 `p값`과 `b_i의 값`이 `참`이 될 때 패턴의 길이들이 진짜로 전부 맞는지 `확인하는 작업`을 수행한다.  
이는 `패턴의 길이인 m`만큼 일어나는데 `매치가 일어나는 횟수를 k`라고 한다면 시간복잡도는 `Θ(km)`이 된다.

따라서, 알고리즘의 총 수행시간은 `Θ(n + km)`이 된다. 하지만, 대부분의 k는 상수 값을 가지므로 `Θ(n + m)`이라고 할 수 있겠다.

### KMP 알고리즘 

intuition은 오토마타 알고리즘이랑 비슷하다. 다만, 오토마타처럼 전처리 비용이 많이 들지는 않는다. 

`KMP 알고리즘`의 핵심은 패턴의 각 위치에 대해 `매칭을 실패`했을 때  
`돌아갈 곳`을 알려주는 `1차원 배열`을 준비하고 이를 이용해서 `텍스트 문자열`을 훑어나간다는 것이다.

ex. 패턴 문자열 “abcdabcwz”를 찾으려 한다.

`abcdabcwz`를 찾는데 이에 대한 patial match table `π`가 있다고 하자.  
`π[j]`의 값은 `j번째 index`에서 matching을 `실패`했을 때 `패턴 P가 돌아가야할 위치`를 의미한다.

![image](https://user-images.githubusercontent.com/64796257/150742420-cf776858-352d-422a-be3f-43ea8d85e1c1.png)

![image](https://user-images.githubusercontent.com/64796257/150742427-84b59341-cf68-4505-8ed3-35494216f4a5.png)

만약에 `패턴의 index = 8번`에서 매칭을 `실패`했다면 `index = 4번`에서 `다시 매칭을 시작`한다.

#### 전체적인 탐색 과정 
입력 : A = document string / P = 찾고자 하는 패턴 / π = partial match table  
	(i = `문자열 A`를 가리키는 인덱스 / j = `문자열 P`를 가리키는 인덱스)

A를 `왼쪽`에서 `오른쪽`으로 순차적으로 반복하면서 아래와 같은 경우들을 다루게 된다.  
1) Match case : `A[i] = P[j]` ⇒ `A` 와 `P` 둘 다 다음 index로 넘어간다.
2) MisMatch case : `A[i] ≠ P[j]`⇒ `P`에서 `π[j]`에 있는 값으로 넘어간다. 그리고 나서 계속 matching을 진행한다.
3) Full-Match case : j가 P의 끝까지 다 왔다면, 패턴은 문자열 A에서 자신과 똑같은 문자열을 찾은 것이다.

![image](https://user-images.githubusercontent.com/64796257/150743155-c36a8725-54c1-4a2f-a183-86724b225157.png)
![image](https://user-images.githubusercontent.com/64796257/150743168-dbf44ea3-caa0-42e2-8cfd-79a67853b3a6.png)

![image](https://user-images.githubusercontent.com/64796257/150743544-371a6aa9-e70c-43dc-a9a0-3ba479a5df1a.png)
![image](https://user-images.githubusercontent.com/64796257/150743551-b503530e-d29e-44cc-bb05-1c5b2b4751c6.png)

![image](https://user-images.githubusercontent.com/64796257/150743593-05f07c69-4126-447b-b3b6-199f1aa0b58f.png)
![image](https://user-images.githubusercontent.com/64796257/150743605-b500d4e0-b8a4-4496-b6dd-6d2ad70d4e5b.png)

이 과정을 `i = 8`이고 `j = 7`일 때까지 반복한다.

![image](https://user-images.githubusercontent.com/64796257/150743686-8007fcae-a113-437f-bd78-7ff0c136d37b.png)
![image](https://user-images.githubusercontent.com/64796257/150743694-04309853-b92e-4983-95f0-041bb757ae4d.png)

![image](https://user-images.githubusercontent.com/64796257/150743757-fb4ff63d-8b29-4bb5-8942-c0a0ddc9553c.png)
![image](https://user-images.githubusercontent.com/64796257/150743760-2dcc2109-4c1e-4b79-b97f-96f8b88dd4a2.png)

![image](https://user-images.githubusercontent.com/64796257/150743801-8e7413e6-7bf9-4936-979f-5f3539f719c8.png)
![image](https://user-images.githubusercontent.com/64796257/150743810-cf4e74a1-8304-46b2-b450-b589b4bc6a73.png)

![image](https://user-images.githubusercontent.com/64796257/150743843-7cfb9e00-6927-4039-9c0c-3877b12d8e7a.png)
![image](https://user-images.githubusercontent.com/64796257/150743859-704a402d-e21d-4c24-86d7-0fa1ec757447.png)

이 과정을 `i = 14`이고 `j = 9`일 때까지 반복한

![image](https://user-images.githubusercontent.com/64796257/150743930-8e7992e7-1c55-4006-b7dc-0b1d449982a6.png)
![image](https://user-images.githubusercontent.com/64796257/150743936-6cb49d7d-006a-49ea-b410-2eb6cd8cffbf.png)

![image](https://user-images.githubusercontent.com/64796257/150743965-38c2dee9-ee58-40da-8a22-037d67ea7506.png)
![image](https://user-images.githubusercontent.com/64796257/150743973-40d30ee5-1e6e-49e8-abb0-3db055e5a329.png)

![image](https://user-images.githubusercontent.com/64796257/150744048-ba3d9081-6a3f-4a32-8411-ff19c4babab1.png)
![image](https://user-images.githubusercontent.com/64796257/150744060-68a33f00-9b94-4c96-974a-055eff1a4f90.png)

![image](https://user-images.githubusercontent.com/64796257/150744105-3cb1dfff-c6f8-4d1c-b17b-f112d1720a8e.png)
![image](https://user-images.githubusercontent.com/64796257/150744111-86a5817a-1681-4fae-aefb-1c431daff29c.png)

`시간복잡도`는 아래와 같이 구하면 된다. 
![image](https://user-images.githubusercontent.com/64796257/150744256-21308611-4c3d-4e73-9237-d93aa3935f06.png)

시간복잡도는 `while문의 반복 횟수`에 따라 달라진다.  
`시간복잡도`를 좀 더 쉽게 파악하기 위해서 `i + (i-j)`라는 임의의 변수를 새로 만들었다.

`빨간 상자`에서는 `i`와 `j`가 동시에 `1씩 증가`한다.  
그래서 `i + (i-j)` ⇒ (i+1) + ((i+1) - (j+1)) = `(i+1) + (i-j)`가 되므로 `i + (i-j)`는 `1`씩 증가한다.

`녹색 상자`에서는 `i는 그대로`고 `j의 값은 π[j]의 값`으로 `감소`한다. 그래서 `i + (i-j)`는 `1이상`으로 증가한다.

따라서, 시간 복잡도는 i + (i-j) ≤ i + i = 2i ≤ 2n = `O(n)`이 된다.

#### Partial Match Table 만들기 

##### 용어 정의 

- Prefix : `문자열 t = ps`일 때 `p`는 문자열 t의 `prefix`이다. ex. A, AB는 ABC라는 문자열의 proper prefix가 된다.

- Suffix : `문자열 t = ps`일 때 `s`는 문자열 t의 `suffix`이다. ex. C, BC는 ABC라는 문자열의 proper suffix이다.

##### Naive 접근 

![image](https://user-images.githubusercontent.com/64796257/150745924-5472bd19-a141-4710-bc10-b6a70f6d71ae.png)
![image](https://user-images.githubusercontent.com/64796257/150745931-71aa3090-245b-4bc5-9610-671960062800.png)

![image](https://user-images.githubusercontent.com/64796257/150745975-4d927a62-5c1e-4618-9acd-c7b2f3265bef.png)
![image](https://user-images.githubusercontent.com/64796257/150745981-3b52dc4c-9c50-4bdb-8b5b-4a7d192cdc75.png)

![image](https://user-images.githubusercontent.com/64796257/150746002-4948c903-1baf-4ee0-a0fd-71b7333ccdd5.png)

![image](https://user-images.githubusercontent.com/64796257/150746030-0150522a-42dc-4e0d-923d-a62c143e3799.png)
![image](https://user-images.githubusercontent.com/64796257/150746035-b0b4aa21-2cd3-4be5-a36e-a0ae777e6c13.png)

![image](https://user-images.githubusercontent.com/64796257/150746166-fee79b4e-e4fc-4d2b-b687-4a824b9d7fff.png)
![image](https://user-images.githubusercontent.com/64796257/150746171-25717007-3640-4cd0-90cd-c484e42bf259.png)

![image](https://user-images.githubusercontent.com/64796257/150746232-b31ab31e-13f3-4bf9-8cf3-69bab1871ec3.png)
![image](https://user-images.githubusercontent.com/64796257/150746236-ee1bb49c-80d5-4bbf-8a52-58639eeb83e7.png)

![image](https://user-images.githubusercontent.com/64796257/150746264-72376a65-dff2-4663-9baf-040c8037e831.png)
![image](https://user-images.githubusercontent.com/64796257/150746271-a5facbdf-3265-43e6-8a58-23efe2cee41d.png)

위 방식의 시간 복잡도를 살펴보자.  
![image](https://user-images.githubusercontent.com/64796257/150746317-40be4f42-158f-4c56-b79c-43c143e7df8a.png)
![image](https://user-images.githubusercontent.com/64796257/150746328-aba0b5d6-22d9-40da-87cf-b3201d113cb7.png)

이는 너무 오래걸린다. 그래서 연구한 최적의 방식은 아래와 같다. 

문제: abcdabcwz 패턴을 문자열에서 찾는다.

![image](https://user-images.githubusercontent.com/64796257/150746423-e388d74c-0f31-4b0d-86d0-91419d6a6ef9.png)
![image](https://user-images.githubusercontent.com/64796257/150746428-97cf307e-b623-4cbe-a39e-6264e7da9712.png)

![image](https://user-images.githubusercontent.com/64796257/150746437-23684142-5e63-422e-ac2c-a0236fea1fcb.png)
![image](https://user-images.githubusercontent.com/64796257/150746441-ef39491e-e47b-4f2f-9667-fb06f3183b3f.png)

![image](https://user-images.githubusercontent.com/64796257/150746470-fea379b3-25fa-4cbd-b7c1-20787cc8b49d.png)
![image](https://user-images.githubusercontent.com/64796257/150746475-a6992088-1491-4958-92cc-787b9b24636a.png)

![image](https://user-images.githubusercontent.com/64796257/150746547-aac2b2cb-d954-4dd2-a657-b32bd31aff75.png)
![image](https://user-images.githubusercontent.com/64796257/150746555-77d0092c-6400-49ab-b1e8-aed639f51b23.png)

![image](https://user-images.githubusercontent.com/64796257/150746582-e5cb68fa-8654-4b41-8631-32387929a4b0.png)
![image](https://user-images.githubusercontent.com/64796257/150746591-778ae749-5fd7-4d80-82d1-1a50005f2f7e.png)

![image](https://user-images.githubusercontent.com/64796257/150746652-17344284-5d12-4c9b-8193-352df5fcb319.png)
![image](https://user-images.githubusercontent.com/64796257/150746666-2653ac92-a6b6-4755-9713-7bb8c30cae1c.png)

![image](https://user-images.githubusercontent.com/64796257/150746691-be42dfc8-cdf8-4d6b-a221-9332891231eb.png)
![image](https://user-images.githubusercontent.com/64796257/150746754-e140481e-3895-4968-959a-b8ef61726ea2.png)

![image](https://user-images.githubusercontent.com/64796257/150746788-f945e152-a83d-4d01-99f9-5cf8144eb5e4.png)
![image](https://user-images.githubusercontent.com/64796257/150746805-672731ad-bcac-4c21-ab02-44a41c96c406.png)

![image](https://user-images.githubusercontent.com/64796257/150746858-6715b367-7dee-47e0-ae85-49b606061b8c.png)
![image](https://user-images.githubusercontent.com/64796257/150746862-734c8bca-c855-46ad-b504-6402244c2e56.png)

![image](https://user-images.githubusercontent.com/64796257/150746907-e94d352c-9881-4cfe-ba66-de4bcffaabcb.png)
![image](https://user-images.githubusercontent.com/64796257/150746955-301e5dc9-0551-4c4f-b1a6-29e7744d4d36.png)

![image](https://user-images.githubusercontent.com/64796257/150746980-b337267e-c5da-4218-aeb3-3d4644fe3069.png)
![image](https://user-images.githubusercontent.com/64796257/150746985-047f203c-7a3f-402c-8c23-77237d01f3a6.png)

![image](https://user-images.githubusercontent.com/64796257/150747025-8ff68996-8c67-4247-ae94-bf727fd34703.png)
![image](https://user-images.githubusercontent.com/64796257/150747030-a7f29fae-57d8-4131-8724-702f55e45c74.png)

이 방식의 시간복잡도를 계산하자.

이를 알아보기 위해 `j + (j-k)`라는 변수를 설정하자. 이후에 시간 복잡도를 구하는 과정은 search phase와 동일하다.

`k = 0`이고 `P[j] = P[k]` 인 상태에서 `j`와 `k`는 똑같이 `1`씩 증가한다. ⇒ 그래서 `j + (j-k)`는 `1`씩 증가한다.

그렇지 않을 때 `k의 값`은 이전에 가지고 있던 값보다 작은 `π[k]`의 값을 가진다.

이런식으로 `k`는 `j보다 작은 값`을 갖기 때문에 j + (j-k) ≤ j + j = 2j ≤ 2m = `O(m)`이 성립한다.  
여기서 `m`은 `패턴 배열 P의 길이`라고 했다. 

따라서, `시간 복잡도`는 `O(m)`이 되겠다.

그래서 `KMP 알고리즘`을 통해 `테이블을 설계`하고 설계한 테이블을 바탕으로 `pattern matching`까지 완료하면  
`O(m + n)`만큼의 `시간복잡도`가 걸린다는 것을 알 수 있다.

이를 통해 만큼의 시간복잡도가 걸리는 오토마다 방법보다 `더 빠른 방법`이라는 것을 알 수 있다.
