### 문제의 종류 

![image](https://user-images.githubusercontent.com/64796257/150669410-65d23cf6-f8c0-4681-a44b-8ba8bd5829fa.png)

해결책은 있지만 `practical time`이내에 풀 수 없는 문제에 대해서 집중적으로 다룰 것이다.

`NP-Complete`는 현실적인 시간에 풀수 없다고 `추정`되는 문제들을 다룬다. (추정이기 때문에 `증명`은 아니다. 다만 강력하게 추정할 뿐이다)

### Practical Time 

⇒ `practical time`은 `poly-nomial time을 의미`한다. 즉, `다항식으로 표현`할 수 있는 시간복잡도를 의미한다.

![image](https://user-images.githubusercontent.com/64796257/150669450-76c9b3cc-02a2-4ba8-ac1a-154d976a1f52.png)

그렇다고 `k = 100` 이 되면 `impractical time`이 된다.  
그래서, `k ≤ 6` 라는 범위에 k값이 존재할 때 practical time이라고 정의한다.

그리고 다항식에 `log 식`을 곱하는 것도 practical time이라 할 수 있다. 왜냐하면, `(n^k)logn ≤ n^(k+1)` 라고 표현할 수 있기 때문이다. 

그래서 지수 k의 범위를 만족한다면 다항식과 log 식을 곱한 시간복잡도도 practical time이 될 수 있다.

### Decision Problem 
⇒ 문제는 요구하는 대답의 종류에 따라 2가지로 분류할 수 있다.
- Decision Problem : 이 문제를 해결할 수 있는가? ⇒ 대답은 `Yes/No`
- Optimization Problem : 이 문제의 최적해는 무엇인가? 

ex. 최단 경로 문제
- Decision Problem : Node u에서 v로 가는 경로가 존재하는가? ⇒ `Yes or No`
- Optimization Problem : Node u에서 v로 가는 `최단 경로`는 무엇인가?

Traveling Salesman Problem
- Decision Problem : 모든 노드를 지나면서 다시 시작노드로 도착하는 경로가 존재하는가? ⇒ `Yes or No`
- Optimization Problem : 모든 노드를 지나면서 다시 시작노드로 도착하는 `최단 경로`는 무엇인가?

⇒ 여기서 `NP-Complete`는 `Decision Problem`에 초점을 맞출 것이다.  

즉, Practical time 이내에 문제를 해결할 수 있는지에 대한 답을 찾으려고 할 것이다.  
시간 내에 풀 수 있다면 Yes / 시간 내에 풀 수 없다면 No를 답할 것이다.

### NP 
전체적으로 문제의 집합을 2개로 나눌 수 있다.
- P : poly-time 내에 decision problem을 해결할 수 있는 문제군(群) ⇒ 여기서 `문제를 해결한다`는 건 `명확하게 Yes/No`를 말할 수 있는 문제를 말한다

- NP : Non-deterministic Polynomial의 약자. 좀 더 쉽게 정의하면 아래와 같다.  
       `decision problem의 해답`이 `Yes`라는 `근거`가 주어졌을 때 `poly-time 내에` 그것이 `옳다는 것을 확인`해줄 수 있는 문제  
       
마치, 수학문제를 풀다가 모르는 문제가 나왔을 때 답지를 보고나서 금방 답이 맞다고 확인할 수 있는 문제를 생각하면 된다.

ex. `n개의 양의 정수`로 이루어진 `집합 S`가 있다. 그리고 `양의 정수 t`를 주었을 때 `원소의 합이 t`인 `집합 S의 부분집합`이 있는가??

⇒ 이 문제는 `NP`이다. 왜 그런가?  
1) 이 문제를 `Decision Problem`으로 접근한다.

2) 이때 Decision Problem이 `Yes가 되도록` 하는 `Hint`를 주었다고 하자.  
Hint : 입력한 집합 S = {1, 2, 3, 4, 5}이고 t = 12라고 할 때 힌트가 되는 집합 A = {3, 4, 5}가 있다는 힌트.

3) 그렇게 `Decision Problem`이 `Yes가 되도록` 하는 `Hint`를 주었을 때 Decision Problem이 `맞다는 것`을 `poly-time에 확인`할 수 있는가? `그렇다`.

집합 A에 있는 모든 원소를 더해보면 12가 나오는데 모든 원소를 더하는 과정에서 소요되는 시간복잡도는 O(n)이다. 

⇒ 따라서, 해당 문제는 NP가 된다.

### Poly-time Reduction

![image](https://user-images.githubusercontent.com/64796257/150669758-12460137-ba15-4664-9218-abc43d5b160a.png)

이때, 문제를 해결한다는 건 `decision problem`을 해결한다는 뜻이다. 그래서 문제의 답이 `Yes or No`로 나뉜다

`문제 A, B`가 있고 각각의 문제의 `instance를 α, β`라고 하자. 

이때, `β`를 `α`로 `poly-time에 변환`시키고 그렇게 `변환시킨 α`를 `문제 A`에 입력했을 때 

`그냥 α`를 넣었을 때와 답이 똑같다면 `β`를 `α`로 `변환시킨 과정`을 `poly-time reduction`이라 한다. (![image](https://user-images.githubusercontent.com/64796257/150669802-e9ecd66c-3683-4d5b-b79a-715d4a1acf15.png)
라고 표현한다)

이는 어떤 의미가 있냐면

`A`라는 문제가 `practical time에 해결`할 수 있는 문제라고 하자.  
이때, `문제 B에 대한 입력 instance`를 `A의 input`으로 `poly-time reduction`을 했다.

⇒ 그러면 `B의 instance`를 `A의 instance`로 `변환`시키고 해당 instance는 `A라는 문제를 통해서 해답`이 나온다.  
⇒ 이를 통해 `문제 B`를 `손쉽게 해결할 수 있게 된다`는 측면에서 의의를 가진다.

ex. 여기서 문제는 `decision problem`이라는 것을 잊지 말자.

해밀턴 cycle 문제 : 그래프의 모든 정점을 한 번씩만 방문하고 출발점으로 돌아오는 경로가 존재하는지 묻는 문제.

⇒ 입력 : un-directed graph G = (V, E) // 질문 : 그래프 G에 해밀턴 cycle이 존재하는가?

TSP(Traveling Salesman Problem) : 가중치가 있는 완전 그래프에서 최단 경로의 해밀턴 사이클을 찾는 문제  
(완전 그래프 : 모든 정점 사이에 간선이 있는 그래프)

이걸 가지고 문제를 만들어보면  
⇒ 입력 : 양의 가중치를 가지는 un-directed graph G = (V, E)와 양의 실수 K   
   질문 : 그래프 G에 가중치의 합이 K 이하인 해밀턴 cycle이 존재하는가?

`해밀턴 cycle 문제의 instance`를 `TSP 알고리즘으로 해결`하기 위해서  
`해밀턴 cycle의 instance`를 `TSP의 instance`로 `poly-time 내에 바꿀 수 있는지` 따져보려 한다.

그러면, `해밀턴 cycle 문제`의 질문인 `해밀턴 cycle이 존재하는가?` 라는 문제는  
`TSP 문제`의 `길이가 |V| 이하인 해밀턴 cycle이 존재하는가?` 라는 질문으로 아래와 같이 바꿀 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150669939-f0aa3978-8e0d-4355-86cc-d9b403980b75.png)

`해밀턴 cycle의 instance`였던 왼쪽 그림을 `O(n²)`의 시간을 소요해서 오른쪽 그림으로 바꿨다.

여기서 `TSP를 쉽게 푸는 알고리즘`이 있다면 이 변환으로 `해밀턴 cycle`을 보다 쉽게 해결할 수 있게 된다.

### NP-Complete 
- NP-Hard 
: 문제 A에 대한 `NP-Hard`는 다음과 같이 정의한다.  
  `NP 문제`가 `문제 A로 poly-time reduction`이 되는 모든 문제를 `문제 A에 대한 NP-Hard`라고 한다.

수식으로 표현하면 ![image](https://user-images.githubusercontent.com/64796257/150670025-5f517728-0028-4e30-84a8-626c4fbec88d.png)
 일 때 `A는 NP-Hard`이다. 
 
수식을 있는 그대로 해석하면 `NP 문제인 L`을 `poly-time` 내에 `A`로 `reduction`할 수 있을 때 `문제 L`을 `A에 대한 NP-Hard 문제`라 한다.

- NP Complete 
: 문제 A에 대해서 `해당 문제가 A가 NP`이고 `NP-Hard`일 때 문제 A를 `NP-Complete`라고 한다.

관계성을 그림으로 표현하면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/150670035-271d7d16-06bb-44f2-8440-7049c6ff3554.png)

`문제 A가 NP`라는 건, 문제 A를 해결할 근거를 봤을 때 그 근거를 바탕으로 `해당 문제 A를 poly-time 내에 해결`할 수 있다는 것을 의미한다.

그리고 `문제 A에 대한 NP-Hard`라는 건 NP 문제를 poly-time내에 문제 A로 바꿀 수 있는 모든 NP문제를 의미한다.

이렇듯 서로간의 정의가 엄연히 다르다. 하지만, 이를 둘 다 만족하는 문제가 있을 수 있는데 그 문제를 `NP-Complete`라고 한다.

하지만 `NP-Hard`의 정의를 있는 그대로 따르는 건 어렵다. 그래서 NP-Hard를 다른 방식으로 정의한다.

⇒ `NP-Hard 문제 B`가 있다고 하자. `문제 B`를 `poly-time 내에 A로 바꿀 수 있다`(![image](https://user-images.githubusercontent.com/64796257/150670180-be9a988e-8032-435c-a44a-7a671a391ee3.png)
)면 `문제 A는 NP-Hard`이다.
				
왜냐하면, `B가 NP-Hard`이기 때문에 ![image](https://user-images.githubusercontent.com/64796257/150670186-15125774-d1d0-4bef-8d0a-8a539aad6401.png)
 를 만족한다. 

앞서 ![image](https://user-images.githubusercontent.com/64796257/150670193-44780bbf-5934-429a-b1ba-07a843f8f002.png)
를 만족한다고 했기 때문에 다음과 같은 식을 세울 수 있다. ![image](https://user-images.githubusercontent.com/64796257/150670201-1fe5182a-45bc-4bc4-b82b-6fe8414beeac.png)

따라서, ![image](https://user-images.githubusercontent.com/64796257/150670215-e66d26b6-c0c2-4397-8d68-2daf5df9e708.png)
라는 관계식을 세울수 있다. 이는 문제 A가 NP-Hard라는 것이라는 `정의`다.

이러한 정의의 의미는...

`NP-Hard로 알려진 문제`를 `poly-time 내에` 다른 문제로 `변환`할 수 있다면 `그 문제 역시 NP-Hard`라는 것을 의미한다

문제 A가 `NP-Complete`인지 아닌지 증명하려면 2가지를 보이면 된다. 

1) 문제 A가 `NP-Hard`인지 보인다.  
⇒ 이를 위해 `NP-Hard로 알려진 문제`가 `문제 A`로 `poly-time reduction이 이뤄지는지` 살펴본다. (앞에서 정의한 NP-Hard의 정의의 다른 버전)

2) 문제 A가 `NP`인지 보인다.  
⇒ 문제 A가 `맞다는 hint`를 봤을 때 문제 A가 `맞다는 것을 확인`하는데 `poly-time이 걸리는지 확인`하면 된다.(이는 NP의 정의를 이용해서 보인 것이다)

그렇다면 알려진 `NP-Hard 문제`는 무엇이 있는가??  
⇒ 대표적으로 `GSAT`이라는 문제가 있다. GSAT은 NP-Complete의 대표적인 문제다.

![image](https://user-images.githubusercontent.com/64796257/150670387-ae353062-1a1c-452f-a4f8-2a8698d6bfc5.png)

여기서는 트리의 구조로 나와있지만

트리의 연결상 멀리 있는 `SAT`를 `poly-time reduction`했을 때 `SUBSET-SUM 문제`로 바꿀 수 있다.

즉, 위에서 제시한 각각의 `NP-Complete 문제`들은 각각의 문제들에 대해 `poly-time reduction`을 할 수 있다.

ex. TSP가 `NP-Complete`인지 보여라.
- 해밀턴 cycle은 `NP-Hard`라고 하자.

![image](https://user-images.githubusercontent.com/64796257/150670415-30c64197-ace9-4a33-8766-3a24d85a66f8.png)

1) TSP가 `NP-Hard`인가?   
해밀턴 Cycle이 `NP-Hard`라고 했는데 위에서 보였듯이 `O(n²)` 으로 해밀턴 cycle ![image](https://user-images.githubusercontent.com/64796257/150670452-5d9cc0ce-a508-4cb6-8322-40fafa7d232e.png)
TSP가 성립한다고 했다.

이때, `해밀턴 Cycle`이 `NP-Hard`이기 때문에 `TSP` 역시 `NP-Hard`가 된다.(NP-Hard의 alternative 정의)

2) TSP는 `NP`인가?
- TSP는 `decision problem`으로 놓고  
- `TSP 문제의 instance`와 그 instance가 `Yes가 나올 수 있는 hint`가 주어진다. 그 hint가 위에서 그린 빨간 직선이다. 
- `빨간 직선(hint)`으로 확인해본 결과 `O(n)` 시간만에 TSP의 instance의 정답이 Yes라는 것을 확인했다.

⇒ 따라서, TSP는 `NP`가 된다. 이 두 가지를 모두 보였으므로 `TSP는 NP-Complete`가 된다.

### 왜 NP-Complete 문제인지 따져야 하는가? 

1. 풀어야 하는 문제를 `decision problem`으로 변환한다.
2. 만약에 그 문제가 `NP-Complete`라면 poly-time 내에 풀 수 있는 알고리즘이 없다는 걸 의미한다.

⇒ 이를 통해 많은 천재들도 아직까지 poly-time 내에 문제를 해결할 수 있는 알고리즘을 개발하지 못했다는 걸 알 수 있다.  
때문에 다른 대안을 생각해야 한다는 의사결정을 할 수 있도록 도와준다.

### NP = P

NP = P라고 하자.
그러면 `문제 A`가 `NP`이면서 `모든 NP 문제` ![image](https://user-images.githubusercontent.com/64796257/150670656-b5b9d9dc-254c-46f2-8356-268df911491c.png)
A가 성립한다고 할 때 `문제 A는 NP-Complete`가 성립한다.

앞서 NP = P라고 했으니까 아래와 같이 쓸 수 있다. 

`A는 NP(= P)`이면서 `모든 NP(= P) 문제`![image](https://user-images.githubusercontent.com/64796257/150670656-b5b9d9dc-254c-46f2-8356-268df911491c.png)A가 성립한다. 

그래서 `NP-Complete였던 문제 A`는 곧 `P가 된다`는 것을 알 수 있다. 그래서 아래의 그림을 그릴 수 있다.  
![image](https://user-images.githubusercontent.com/64796257/150670689-5e14663b-afa5-454c-97b6-23245bf5b2a1.png)

때문에 다음과 같은 얘기를 할 수 있다.

만약에 효율적인 알고리즘이 `NP-Complete 문제`를 `poly-time`에 해결할 수 있게 된다면 `모든 NP 문제`를 `poly-time에 해결`할 수 있게 된다.  
왜냐하면, `NP-Complete 문제`들은 `poly-time reduction`으로 전부 `연결`되어 있기 때문이다.

앞서 `NP-Complete 문제들`은 `서로서로 poly-time reduction`을 할 수 있다고 했다.  
즉, `하나만 풀려도` poly-time reduction을 통해 `다른 NP-Complete 문제`를 `연쇄적으로 해결`할 수 있게 된다.

그러면 NP 문제는 왜 해결될까?   

기본적으로 `NP-Complete 문제`는 `NP-Hard라는 조건`을 갖고 있다. 문제 `A`가 `NP-Complete`라면  ![image](https://user-images.githubusercontent.com/64796257/150670763-1b3c0ce1-d81f-4354-9bc0-ddd24b24f39a.png)
라는 `NP-Hard 조건`을 갖게 된다.

이때, `NP-Complete인 문제 A`가 해결되면서 그냥 `NP문제 였던 L`은 `A로 poly-time reduction`을 할 수 있다고 했으니까 `모든 NP문제`도 같이 해결할 수 있게 된다.

### NP-Complete 증명 연습 : Longest Path Problem을 통한 연습 

- 큰 맥락 

![image](https://user-images.githubusercontent.com/64796257/150670849-26573584-4f2d-4fd1-8387-38fb6f1c70dc.png)

1)을 통해서 `문제 A가 NP`인지 보이고   
2), 3), 4)를 통해서 `문제 A가 NP-Hard`인지 보이면서 결과적으로 `NP-Complete인지` 보일려고 하는 거다.

4)를 부연설명하면, 문제 A의 instance의 결과와 문제 B의 instance의 결과가 똑같은지를 보이는 과정이다.

#### Longest Path는 NP-Complete인가? 

이를 증명하기 위해서 이미 `NP-Hard문제`로 증명된 `해밀턴 2point 경로`를 이용할 것이다.

일단 각각의 문제를 정의하면

- Longest Path 문제  
입력 : 양의 가중치를 가지면서 방향성이 없는 그래프 G / 두 개의 노드 s, t / 양의 상수 K

문제 : 그래프 G에서 `경로의 합`이 K보다 크거나 같은 `s에서 t로 향하는 simple path`가 존재하는가?

- 해밀턴 2-point 경로  
입력 : 방향성이 없는 그래프 G와 두 개의 노드 s, t
      
문제 : s에서 t로 향하는 해밀턴 경로가 존재하는가?
ex.  ![image](https://user-images.githubusercontent.com/64796257/150670902-c937013a-21ea-4070-9920-0443a97bd20c.png)

##### 1단계. Longest-Path는 NP인가?
1. `Longest-Path`는 `Decision Problem`이다.
2. 이 문제의 `답이 Yes`가 되도록 하는 hint를 제공한다.  

⇒ instance : 그래프 / Hint : 노드 s에서 t로 향하는데 이때 cost의 합은 K보다 크거나 같은 simple path가 있다.

3. 그렇다면 실제로 `노드 s에서 t로` 향할 때 `cost의 합`이 `K보다 크거나 같은지` 확인하면 된다.  
이때는 그냥 그래프의 `노드 s에서 t`로 하나 하나 `경로를 진행`할 때 `각 경로의 cost들`을 `하나씩 더하면` 된다.  
`그래프의 노드 개수가 n`이 된다면 이를 확인하는 시간복잡도는 `O(n)`이 되겠다.

##### 2단계. NP-Hard인 다른 문제를 골라라. ⇒ 여기서는 해밀턴 2-Points 경로를 선택했다.

##### 3단계. 해밀턴 2-Point 경로에서 Longest-Path 문제로 poly-time reduction을 진행하자.

`그래프 G`를 `poly-time reduction`할 때 각각의 `간선의 가중치 값을 1씩 부여`해서 `그래프 G’`을 만든다.

이때, poly-time reduction을 하는데 걸리는 시간 복잡도는 `O(n²)`이 된다.  
(`O(n²)`인 이유는 `노드의 개수가 n개`일 때 `간선의 개수`는 `n²`를 넘어가지 않기 때문이다)

![image](https://user-images.githubusercontent.com/64796257/150671132-cbe120bc-f3a1-485a-9712-b9cd6d8d4844.png)

##### 4단계. 각각의 instance가 똑같은 결론을 내는지 보여라.
⇒ 여기서는 `해밀턴 2-Point`를 이용해서 `Longest-Path의 문제`를 해결하려고 한다.

이때, `해밀턴 2-Point 문제를 B`라고 하고 `Longest-Path 문제를 A`라고 하겠다.

그래서 `해밀턴 2-Point의 instance`와 `Longest-Path의 instance`가 둘 다 같은 결론을 내는지 확인하려고 한다.

그래프 G의 instance B : 노드 s에서 t로 향하는 해밀턴 경로가 존재한다.

그래프 G’의 instance A : 노드 s에서 t로 향하는 비용의 합이 n-1보다 큰 경로가 존재한다.  
(instance A를 이와 같이 설정한 건 3단계에서 해밀턴 2-Point를 poly-time reduction 한 결과이기 때문이다)

그렇다면, 이 둘은 진짜 같은 결과를 낼까? 즉, `동치`인지 보여야 한다.

1) B ⇒ A
`노드 s에서 t로 향하는` 해밀턴 경로가 있다고 하자. 이때, `그래프 G의 모서리(edge)의 개수`는 노드의 개수가 n개일 때 `n-1개` 이다. 

`해밀턴 경로`는 모든 노드를 거치는 경로이기 때문에 `n-1개의 간선`을 이용한다.  
이것은 변환된 `Longest-path`에서는 `정점 s에서 t로` 향하는 `길이가 n-1인 단순 경로`가 된다. 

따라서, `정점 s에서 t로 향하는` `길이 n-1 이상인 단순 경로`가 존재한다.

2) A ⇒ B
`정점 s에서 t로 향하는` 길이 n-1이상인 단순 경로 그래프 G’에 존재한다고 하자.

그래프의 `단순 경로`는 그래프가 가지고 있는 `간선을 1번씩만 이용`해야 하기 때문에  
`그래프 G’`에서 지나는 `n-1개의 간선`보다 더 많은 cost를 `가질 수 없다`. 따라서 `cost = n-1`이 된다.

따라서, `정점 s에서 t로` 향하는 `길이가 n-1`인 단순 경로는 `s에서 t로 향하는` `해밀턴 2-Point 경로`와 `동일`하다.

따라서, 4번을 보임으로써 문제 A인 `Longest Path는 NP-Complete` 하다고 할 수 있다.

### NP Hardness of Optimization problem

`NP-Hard`는 `decision problem` 뿐만아니라 `optimization problem`도 포함한다.  
![image](https://user-images.githubusercontent.com/64796257/150671318-333d39d8-1af1-46f5-85fe-1f1bd5ac7151.png)

일반적으로 `NP-Hard의 decision problem`은 `optimization problem`으로 확장할 수 있다.

ex. TSP

OPT-TSP : 양의 가중치와 방향성이 없는 완전 그래프 G가 주어졌을 때 그래프 G에서 해밀턴 cycle의 최단 경로는 얼마인가?

TSP : 그래프 G와 양의 상수 K가 있을 때 K보다 작은 경로의 합을 가진 해밀턴 cycle이 있는가?

여기서 OPT-TSP는 NP-Hard인가? ⇒ 자세한 과정은 모르지만 그렇다고 한다. 다른 NP-Complete 문제도 비슷하게 보일 수 있다고 한다.




























