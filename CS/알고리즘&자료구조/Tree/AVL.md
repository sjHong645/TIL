## AVL의 이해

### 자동으로 균형을 잡는 AVL 트리와 균형 인수(Balance Factor)

AVL 트리는 노드가 추가/삭제될 때 트리의 균형상태를 파악해서 스스로 그 구조를 변경해서 균형을 잡는 트리이다.  
AVL 트리에서는 균형의 정도를 파악하기 위해 `균형 인수`라는 것을 사용하는데 다음과 같이 계산된다. 

⇒ `균형 인수 = 왼쪽 서브 트리의 높이 – 오른쪽 서브 트리의 높이` 

예시를 통해 계산결과를 보면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150292800-ba3e336a-3d13-40a4-9fa5-c362dee8bab5.png)
![image](https://user-images.githubusercontent.com/64796257/150292803-bd97d09e-ec3a-40c0-b99a-836e1d43c350.png)

균형인수의 절댓값이 크면 클수록 그만큼 트리의 균형이 무너진 상태를 나타낸다.  
따라서 AVL 트리는 균형 인수의 절댓값이 2 이상인 경우에 균형을 잡기 위한 트리의 재조정이 진행된다.

이러한 재조정을 `리밸런싱(rebalancing)`이라 한다.

AVL 트리의 균형이 무너지는 상태는 4가지로 정리된다. 각 상태별 리밸런싱 방법에 차이가 있으니 각각에 대해 알아보자. 

### 리밸런싱이 필요한 1번째 상태와 LL회전 

![image](https://user-images.githubusercontent.com/64796257/150293037-80af6a54-f42c-4b26-8429-a8d1cb2a8de9.png)

그림의 왼쪽을 보면 루트 노드의 균형 인수는 +2이다. 이 상황을 다음과 같이 설명할 수 있다.  

5가 저장된 노드의 `왼쪽에` 3이 저장된 자식 노드가 하나 존재하고, 그 자식 노드의 `왼쪽에` 1이 저장된 자식 노드가 또 하나 존재한다.

⇒ 즉, 자식 노드 2개가 왼쪽으로 연달아 연결되어서 균형인수 값으로 +2가 나왔다.  
이렇게 균형인수 값 +2가 연출된 상황을 가리켜 `LL 상태(Left Left)`라고 한다.  
이러한 LL상태의 불균형 해소를 위해 등장한 리밸런싱 방법이 `LL회전`이다.

오른쪽의 그림은 LL회전의 방법과 결과를 보여주고 있다. 
⇒ `균형 인수가 +2인 노드(값이 5인 노드)`를 `균형 인수가 +1인 노드(값이 3인 노드)`의 `오른쪽 자식 노드`가 되도록 하는 것이다.

이를 대략적인 코드로 정리하기 위해 아래와 같은 그림의 상황이라 가정하자.  
![image](https://user-images.githubusercontent.com/64796257/150295446-cd02758e-0da4-4a93-b6f8-66ab6d5d0dd6.png)

pNode가 5를 가진 노드를 가리키고 cNode가 3을 가진 노드를 가리킨다고 할 때 

ChangeRightSubTree(cNode, pNode); 로 얘기할 수 있다. // pNode의 오른쪽 서브트리가 cNode가 되도록 한다.

이제 LL상태를 일반화하겠다. 

![image](https://user-images.githubusercontent.com/64796257/150295528-b961fbb9-2633-4877-98b1-d652312d0b77.png)

T1, T2, T3, T4 모두 높이가 동일한 서브 트리라고 하자.  
따라서, 기존에 있던 노드의 균형인수에 영향을 끼치지 않는다. 즉, 이 상태 역시 `LL상태`라고 할 수 있다.

그럼 이 상태에서 LL회전을 위해 추가로 고민해야할 내용은 뭘까?  
바로 T3이다. T1, T2, T4는 고민하지 않아도 된다. 각 트리의 부모노드가 이동하면서 늘 붙어다니기 때문이다. 

하지만, T3은 다르다. T3의 부모 노드는 루트 노드가 되야하기 때문에 T3는 기존에 있던 자리와 다른 자리로 이동해야 한다.

그림을 기준으로 보면 T3는 `5가 저장된 노드(pNode가 가리키는 노드)`에게 자리를 양보해야 한다.  
그렇다면 T3은 어디로 가야할까?? `5가 저장된 노드`에게 자리를 양보하고 나면 남는 자리가 보인다.

바로 5가 저장된 노드의 왼쪽 자식 노드 위치이다. 이 자리로 이동하더라도 여전히 이진 탐색 트리의 기준에 부합된다.  
따라서 회전의 결과는 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/150295741-e2352e3d-1293-48db-8601-f9b3bfd6d2e5.png)

위와 같이 `cNode가 가리키는 노드`의 오른쪽 서브 트리를 `pNode가 가리키는 노드`의 왼쪽 서브 트리로 옮기려면  
다음 문장을 실행해야 한다. 

ChangeLeftSubTree(pNode, GetRightSubTree(cNode)); // cNode의 오른쪽 서브트리를 pNode의 왼쪽 서브트리로 바꾸기

따라서, LL회전을 위해서는 다음 두 문장을 순서대로 실행해야 한다.
``` 
ChangeLeftSubTree(pNode, GetRightSubTree(cNode)); // pNode의 왼쪽 서브트리를 cNode의 오른쪽 서브트리(T3)로 바꾸기 
ChangeRightSubTree(cNode, pNode);                 // 그렇게 바꾸고나서 cNode의 오른쪽 서브트리가 pNode가 되도록 한다.
```

### 리밸런싱이 필요한 두 번째 상태와 RR회전
RR상태와 LL상태 그리고 RR회전과 LL회전은의 차이점은 `방향`이다.  

`LL상태`가 왼쪽으로 길게 늘어진 모습이였다면, `RR상태`는 오른쪽으로 길게 늘어진 모습이다.  
`LL회전`이 `LL상태`를 바로잡기 위해 회전하는 거라면, `RR회전`은 마찬가지로 `RR상태`를 바로잡기 위해 회전하는 것이다.

RR상태와 RR회전의 결과를 보면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150297501-15284709-72ea-4961-b274-c675b307f685.png)

여기서 주목할 점은 2가지가 있다. 
1. `5가 저장된 노드(pNode가 가리키는 노드)`가 `7을 저장한 노드(cNode가 가리키는 노드)`의 왼쪽 자식 노드가 되었다.
2. T3은 `5가 저장된 노드(pNode가 가리키는 노드)`의 오른쪽 서브 트리가 되었다.

이를 구현하기 위해 함수를 써보면...
```
ChangeRightSubTree(pNode, GetLeftSubTree(cNode)); // pNode의 오른쪽 서브트리를 cNode의 왼쪽 서브트리(T3)로 바꾸기
ChangeLeftSubTree(cNode, pNode)                   // 그렇게 바꾸고나서 cNode의 왼쪽 서브트리가 pNode가 되도록 한다.
```
`pNode가 가리키는 노드`의 오른쪽 서브 트리가 T3이 되도록 한 다음  
`cNode가 가리키는 노드`의 왼쪽 서브 트리가 `pNode가 가리키는 노드`가 되도록 했다.

### 리밸런싱이 필요한 세 번째 상태와 LR회전.

LR상태는 다음과 같은 상태를 의미한다.

![image](https://user-images.githubusercontent.com/64796257/150297906-9106c686-d825-42de-b736-b4dcbf77d11a.png)

왼쪽은 간단한 `LR 상태`를 나타내고 오른쪽은 이를 `일반화한 LR상태`이다.

`LR회전`은 이러한 `LR상태`를 리밸런싱하는 방법을 뜻한다. 그러면 어떻게 해야할까?

`LR상태`는 앞서 보인 `LL상태`와 `RR상태`와 달리 한 번의 회전으로 균형을 잡을 수 없다. 그래서 다음 방법을 사용한다.

⇒ 일단 `LR상태`를 `LL상태` 또는 `RR상태`로 바꾼다.

그렇다면, 어떻게 `LL상태` 또는 `RR상태`로 바꿀 수 있을까?? 

`LR상태`는 `RR회전`을 통해서 `LL상태`가 되게 할 수 있다. `RR회전`을 하면 다음과 같은 결과를 얻는다.  
![image](https://user-images.githubusercontent.com/64796257/150298161-9411b99c-a58b-43b7-b116-f80aaacc18fe.png)

여기서 9가 저장된 노드를 NULL로 치환하면 아래와 같은 상황이 된다.

![image](https://user-images.githubusercontent.com/64796257/150298184-21cfd0d7-0571-4f13-95f3-baf3a05e3085.png)

이와 같은 상황에서 `RR회전`을 하면 5와 7이 저장된 두 노드의 관계를 아래와 같이 바꿀 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150298225-26b1cd1d-f32d-4995-b9ed-e9f28e132598.png)

부모 노드와 자식 노드의 관계가 바뀌었다. 그리고 원래는 오른쪽으로 형성된 부모/자식 관계가 왼쪽으로 바뀌었다.  
이것이 RR회전을 통해 얻을 수 있는 부수적인 효과이다.

다음 그림을 보자.  
![image](https://user-images.githubusercontent.com/64796257/150299010-b4e364e5-d945-4de7-b96b-5a7c2fbfe054.png)

위 그림은 앞서 설명한 `RR회전`의 부수적인 효과를 이용해서 `LR상태`를 `LL상태`로 바꾸는 과정이다.  
저렇게 `LL상태`로 바꾸고 나서 `LL회전`을 통해 트리의 균형을 잡아주면 된다. 전체적인 과정을 정리하면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150299339-70a3f047-37fe-4cb7-aaeb-1e26f7790be4.png)

`부분적으로 RR회전`을 해서 전체적인 트리가 `LL상태`가 되도록 하고 그 트리를 `LL회전` 해서 균형을 맞춰준다.

### 리밸런싱이 필요한 4번째 상태와 RL회전

LR상태와 RL상태를 비교하면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150299522-59755ac6-b8a8-4168-a2f8-89962af0f5d7.png)

이와 같이 `LR상태`와 `RL상태`는 방향의 차이만 있다.  
즉, `RL회전`은 `부분적으로 LL회전`을 한 다음 `RR회전`을 해서 `RL상태`를 해결하는 방법이다.

LL회전이 어떻게 부수적인 효과가 나오는지 간단하게 보겠다.

![image](https://user-images.githubusercontent.com/64796257/150299662-26e1a5eb-54d8-4319-9ab3-e05fd4cb45cc.png)

여기서 1이 저장된 노드를 NULL로 치환한다면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/150299694-41c720f8-7b6f-448b-9964-26c563649975.png)

위 그림에서 보이는 `LL회전`의 부수적인 효과를 정리하면 아래의 그림과 같이 나온다.

![image](https://user-images.githubusercontent.com/64796257/150299742-f82f6729-18ce-4f3b-8624-605e3e1b64ac.png)

즉, `LL회전`을 통해 부모 노드와 자식 노드의 관계를 바꾸고 왼쪽으로 형성되었던 부모/자식의 관계를 오른쪽으로 바꿨다는 걸 보여준다.

`RL상태`의 트리를 `RR상태`로 바꾸는 과정을 한 번에 살펴보면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150299822-f11f165b-39da-43d5-9f72-623ac7fb7477.png)

따라서, RL회전의 전체적인 과정은 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150299870-19940565-917c-4aee-847b-4277736d9132.png)














