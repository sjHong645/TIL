## State Space Tree(상태 공간 트리) 

: 문제 해결 과정의 중간 상태(state)를 각각 하나의 노드로 나타낸 트리.

ex) TSP : `해밀턴 cycle`에서 `가장 짧은 경로`를 가진 `해밀턴 cycle`을 의미한다.

cf) 해밀턴 경로 : 그래프의 모든 경로를 꼭 한 번씩 지나는 경로 

![image](https://user-images.githubusercontent.com/64796257/150938841-3e54a6cb-9050-47f8-bc8b-e422e3ba2837.png)

여기서는 `왼쪽`의 방향 그래프의 TSP는 `오른쪽`에 표시한 경로가 되겠다.

위의 그래프에서 `TSP`를 찾는 `모든 경우의 수`를 `트리`로 나타내면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/150938939-7a655dc6-d6e1-48f7-951c-e9efaaa12dc5.png)

`9번`을 보면 `1-2-5`라고 되어 있다. `정점 1`에서 출발해서 `2`를 거쳐 `5`에 도달한 `상태를 나타낸 노드`다.

이와 같이 `문제풀이가 진행되는 상태`를 각각의 `노드`로 나타낸 트리를 `상태 공간 트리(State Space Tree)`라고 한다.

## BackTracking(백트래킹) 

: `탐색`을 하다가 더 갈 수 없으면 `왔던 길을 되돌아가` 다른 길을 찾는 방식이다.  
: `백트래킹 알고리즘`은 `상태 공간 트리(SST)`를 명시적으로 만드는 건 아니지만,  
알고리즘의 진행과정은 `SST의 루트(root)`에서 시작해서 `DFS 방식`으로 `탐색`해 나가는 것과 대응된다.

### 1. 미로 찾기 문제(Maze Problem) 

#### 개념
![image](https://user-images.githubusercontent.com/64796257/150939328-382e7d6a-bf68-4f8e-a8e9-dce1c77e5ebd.png)

⇒ `s에서 시작`해서 `t에 도착`하는 경로를 찾고 싶다.  
`분기점`은 `동그라미`로 표시하고 `끝나는 지점`은 `네모`로 표시했다. 이를 통해서 아래와 같은 `SST`를 만들어 볼 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150940484-9f8662ed-cd5a-4ed3-ab15-9c27bf05e3d5.png)
![image](https://user-images.githubusercontent.com/64796257/150940497-6a97949c-3fa3-4b16-b265-6ed4bf2da8a3.png)


#### 의사코드 

![image](https://user-images.githubusercontent.com/64796257/150940644-f41fb7ac-16d7-4432-a710-e19c9cd76fdc.png)

`for each v in N(u)`: `u 노드와 이웃한 노드`를 `v`에 하나씩 넣어가면서 `for문 안에 있는 코드`를 `실행`한다는 뜻이다.

### 2. 색칠 문제 
: 그래프를 색칠하는데 `인접한 노드`끼리는 `같은 색깔`로 `색칠하지 않는` 문제다.

`그래프의 인접관계`를 그래프로 표현하면 아래와 같은 과정을 통해 표현할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150940863-39b17a99-ac81-410e-928f-4f60dace79ca.png)

그래서 `각각의 노드`에 `색칠`하는 건 결국에는 Map의 각 `구역`에 `색칠`하는 것과 `동치`다.

이 문제도 `back-tracking 기법`을 이용해서 해결할 수 있다. 알고리즘을 작성하면 아래와 같다.  
여기서 `c`는 `색깔`을 의미한다. `c = 1`이면 `빨간색`, `c = 2`면 `녹색`이다. `i`는 그래프의 각각의 `노드`를 의미한다.

![image](https://user-images.githubusercontent.com/64796257/150941038-0d8120ce-4238-4e78-a07c-3b052e86a6c9.png)

`k-Coloring(i,c)`를 호출하는 시점에는 `정점 1부터 i-1`까지 `k개 또는 그 이하의 색깔`이 `이미 칠해져 있는` 상황이다.  
이 상태에서 `정점 i`에 `색깔 c`를 칠하려고 하는 것이 가능한지를 알아보려 한다.

1) 일단 `정점 i`에서 `색깔 c`를 칠하려면 지금까지 칠해놓은 정점들과 `색이 충돌하지 않는지` 체크한다.  
⇒ 이걸 `valid(i,c)`를 통해서 구현했다.  
⇒ `i번째 정점`의 `이웃한 정점`들의 `색깔` 중에서 `이미 색깔 c`가 있다면 이는 충돌이 일어난 거니까 `false`를 return한다.

2) 만약에 `충돌하지 않는다면` `정점 i`에 `색깔 c`를 칠한다. ⇒ `color[i] <- c` 부분
3) `정점 i`가 `마지막 노드`라면 `true`를 return 하고 마지막 노드가 아니라면 이 과정을 i+1번째에서도 반복한다. 

이러한 과정을 `SST`를 이용해서 표현하면 아래와 같다. 색깔은 3개의 색깔을 칠한다고 하자.

![image](https://user-images.githubusercontent.com/64796257/150942126-1f7e8759-1485-48d2-bb93-17d3180d4023.png)
![image](https://user-images.githubusercontent.com/64796257/150942137-138b417e-1e85-454c-b340-6929f050d484.png)

## Bounded Branch(한정 분기) - 가지치기

![image](https://user-images.githubusercontent.com/64796257/150942295-9fe6a374-de3b-4226-9554-5ea1e14ef7da.png)
![image](https://user-images.githubusercontent.com/64796257/150942302-9b1c4986-3f10-4ecc-9cde-9d30d5712d20.png)

Bounded Branch에는 두 가지 핵심적인 요소가 있다.  
1) `모든 경우의 수`를 나열하는 방법이 있어야 한다.
2) 분기를 `더 이상 할 필요가 없음`을 판단할 수 있는 방법을 제공해야 한다.

### ex) TSP 

#### 용어 정의 
![image](https://user-images.githubusercontent.com/64796257/150942479-be602ba4-f38e-4f3a-9242-046fbced771a.png)
![image](https://user-images.githubusercontent.com/64796257/150942488-a7d78472-c46a-4445-ab24-fc2f8b145666.png)

![image](https://user-images.githubusercontent.com/64796257/150942658-dd7961bf-bb22-4a78-adb8-df17263067d6.png)

⇒ `37`이라는 값은 `해밀턴 cycle`이 `완성`될 때 `최소 cost`를 의미한다.

⇒ `24`는 `1-2-3`순으로 노드를 탐색할 때 `1-2의 노드의 cost 10` 과 `2-3의 노드의 cost 14` 를 더한 값을 의미한다.  
⇒ `13`은 `노드 3`의 `out-going edge`의 `cost의 최소값 7` 과 `노드 4`의 `out-going edge`의 `cost의 최소값 3`  
`노드 5`의 `out-going edge`의 `cost의 최소값 3` 을 다 합한 값이다.

따라서, `37`이라는 값은 `1-2-3` 순으로 `해밀턴 cycle`을 이미 돌았을 때 나올 수 있는 `최소 cost 값`이다.

#### 과정 

![image](https://user-images.githubusercontent.com/64796257/150943291-6e07b700-ea9e-4e90-ad3d-60288db4cee3.png)

##### 1단계
![image](https://user-images.githubusercontent.com/64796257/150944009-9ee3e3d7-1d25-46c3-8ef4-09cc42edf0fd.png)
![image](https://user-images.githubusercontent.com/64796257/150944026-88b73192-0c12-4ee3-b550-8870e5a46f75.png)

##### 2단계
![image](https://user-images.githubusercontent.com/64796257/150944133-d11f8c08-9422-4482-ba8e-d3a8583b0602.png)
![image](https://user-images.githubusercontent.com/64796257/150944144-db345034-fba6-4bc1-9eb4-84b112961fc2.png)

`1-2`의 `lower bound 33`은 `1->2로 가는 경로의 cost 10`에   
나머지 다른 노드들(2,3,4,5번 노드들) 의 최소 out-going 경로의 `cost합이 23`이기 때문에 `총합이 33`이 된다.

여기서 2, 3, 4, 5번 노드들의 최소 out-going 경로 cost는 각각 10, 7, 3, 3이 되겠다.

`1-4`의 `lower bound 53`은 `1->4로 가는 경로의 cost 30`에  
나머지 다른 노드들(2,3,4,5번 노드)의 최소 out-going 경로의 `cost합이 23`이기 때문에 `총합이 53`이 된다.

마찬가지로 2, 3, 4, 5번 노드들의 최소 out-going 경로 cost는 각각 10, 7, 3, 3이 되겠다.

##### 3단계
`최소값`을 가지는 `1-2 경로`를 먼저 `탐색`하겠다. 

![image](https://user-images.githubusercontent.com/64796257/150944667-584d8578-efdf-4513-ab38-e4e68228c82f.png)

이제 `1-2 경로`에서 파생될 수 있는 경로에서 `lower bound가 가장 작은 값`을 또 고른다.  
여기서는 `1-2-5`가 가장 작으니까 이 경로를 먼저 선택한다.

1-2-5의 lower bound를 구해보면 

`1->2`로 가는 경로의 `cost 10`, `2->5`로 가는 경로의 `cost 10`, 나머지 다른 노드들(3,4번 노드)의 최소 out-going 경로의 `cost 합 13` 

따라서, `총합은 33`이 된다.

여기서 3,4번 노드들의 최소 out-going 경로 cost는 각각 10, 3이 된다.

##### 4단계
![image](https://user-images.githubusercontent.com/64796257/150945305-95377c90-d4a5-4117-99c1-6e915e883a8e.png)

`1-2-5` 경로에서 파생될 수 있는 경로에서 `lower bound 값이 가장 작은` 경로는 `1-2-5-4`가 되겠다.

최종적으로 `가장 적은 cost`를 가진 `해밀턴 cycle의 경로`는 `1-2-5-4-3-1`이 된다걸 알 수 있다.

##### 5단계
그런데, 이 값이 진짜 가장 작은 값일까? 다른 경로에서는 40보다 작은 값이 나오지 않을까?

이걸 알아보기 위해서 경로의 lower bound가 `40보다 작은` `1-2-3`을 먼저 탐색한다.

![image](https://user-images.githubusercontent.com/64796257/150945572-cf53abcc-a640-4382-96d9-319ce63d520a.png)

`1-2-3`을 가지고 나올 수 있는 `모든 경로의 lower bound`를 보니 `40보다 크다`. 따라서 경로의 `최소 cost`는 아직까지 `40`이다. 

##### 6단계
`1-2-4`는 볼 필요도 없다. 최소 경로의 합이 44이기 때문에 최소값을 가질 수가 없기 때문이다.

![image](https://user-images.githubusercontent.com/64796257/150945675-36ee72c4-b1cf-46c4-9132-8ecaa83f77b6.png)

그래서 `1-2-4`는 `X표시`했다.

##### 7단계
이제 `1-* 부분`으로 돌아가서 `1-2`과 똑같은 lower bound을 가진 `1-3`에서 `cost`를 구해보자.

![image](https://user-images.githubusercontent.com/64796257/150945797-80fb5b9c-0434-4c1f-b49c-5a9c2f778339.png)

##### 8단계
`1-3-*` 중에서 가장 작은 lower bound를 가진 `1-3-4`를 먼저 살펴본다.

![image](https://user-images.githubusercontent.com/64796257/150945920-7068502b-29bc-440a-a5fe-b3ceef92d42b.png)

`1-3-4-*` 에서 각각의 경로의 `cost 합`을 계산한 결과 `40보다 작은 값은 없었다`. 따라서, 여전히 `최소 cost`는 `40`이다.

##### 9단계
`1-3-*` 중에서 `두 번째`로 작은 lower bound를 가진 `1-3-5`를 보자.

![image](https://user-images.githubusercontent.com/64796257/150946027-b6dd186b-b1ed-4b6a-9a80-538381d94526.png)

`1-3-5-*`에서 각각의 최소경로를 구해본 결과 40보다 크다. 따라서, 여전히 최소 cost는 40이다.

##### 10단계
`1-3-*` 중에서 `1-3-2`는 lower bound가 `40보다 크다`. 그래서 탐색을 굳이 할 필요 없으므로 `X 표시`했다.

##### 11단계
`1-4`, `1-5`의 lower bound는 `40보다 크기` 때문에 마찬가지로 탐색을 할 필요가 없어서 `X 표시`했다.

![image](https://user-images.githubusercontent.com/64796257/150946152-d1fc9607-d4d6-4990-a93a-d88945f03500.png)

##### 결론 

최종적으로 `최소 경로 cost`는 `40`이라는 것을 확인했다.

⇒ `bounded branch 방식`을 사용할 때 `8개의 case`만을 탐색했다.

하지만, `brute-force 방식`을 사용할 때는 `24개의 case`를 탐색할 때와 비교했을 때 훨씬 효율적인 알고리즘이라 할 수 있다.


















