## Topological - 위상 정렬 

### 개념 

완수해야 할 여러 가지 일이 있고 이들 간에 상호 선후 관계가 있다.

예를 들어, 한 사람이 라면을 끓일 때는 어떤 순서로 일을 해야 할까?  
라면 끓이는데 필요한 작업의 `선후관계`를 `그래프`로 나타내면 아래와 같이 그릴 수 있겠다.

![image](https://user-images.githubusercontent.com/64796257/150925992-038fbe98-0be9-4611-9be7-7103a2957195.png)
![image](https://user-images.githubusercontent.com/64796257/150926003-972b9367-abea-43c9-bdd0-51fe383175c9.png)

이런 식으로 한 사람이 `한 번에 1가지 작업`만 할 수 있다고 할 때 `선후 관계`에 기초해서 다음 순서로 라면 끓이기를 할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150926102-345d2571-f92e-4046-8a0d-4162d3670ac7.png)
![image](https://user-images.githubusercontent.com/64796257/150926114-ae9af151-d22c-479c-b09e-8dce1519dcfa.png)
![image](https://user-images.githubusercontent.com/64796257/150926122-95a7aa7a-d597-468e-abcc-9687c72556e0.png)
![image](https://user-images.githubusercontent.com/64796257/150926134-ca09f701-9264-4456-a3a8-9eeaa6663749.png)

이를 통해, `작업 i`와 `작업 j` 사이에 `간선 (i,j)`가 존재한다면 `작업 i`는 반드시 `작업 j`보다 `먼저 수행된다`는 걸 나타낸다.  
위 그림과 같이 모든 간선에 대해서 이 성질을 만족한다면 `어떤 순서라도 상관없다`.

이러한 성질을 만족하는 정렬을 `위상 정렬(Topological Sorting)`이라 한다.

위상 정렬은 `사이클이 없는` `방향성이 있는` 그래프 `G = (V, E)`에서 `V의 모든 정점을 정렬`하되 다음 성질을 만족해야 한다.  
⇒  `간선 (i, j)`가 존재하면 정렬 결과에서는 반드시 `정점 i`가 `정점 j`보다 `앞`에 있어야 한다.  
    (이때, `간선 (i,j)`는 `i⇒j`를 가리키는 `방향성이 있는` 간선을 말한다)
    

### Kahn's Algorithm 

#### 개념 

알고리즘의 내용을 파악하기 전에 용어를 하나 정의하고 가겠다. 

![image](https://user-images.githubusercontent.com/64796257/150926691-f79b8869-45d9-45d9-8da3-389af30819a0.png)
![image](https://user-images.githubusercontent.com/64796257/150926700-132ad181-03ec-405e-8d5b-dca2aec5179b.png)

이때, `in-coming edges`가 `없는` 노드인 `냄비에 물 붓기라는 노드`는 `다른 노드들` 보다 `선행된 노드`여야 한다.
⇒ 이 개념이 `Kahn alogrithm`의 본질이다.

![image](https://user-images.githubusercontent.com/64796257/150926825-fcad9f31-91bb-4fac-a5c7-37f83ac4ac43.png)

예시와 함께 살펴보자. 

![image](https://user-images.githubusercontent.com/64796257/150926846-6a1b4df9-8712-4c91-b31e-50ef527b2a51.png)
![image](https://user-images.githubusercontent.com/64796257/150926854-9232dda7-9eba-4067-8858-b1a44c9ad99e.png)

![image](https://user-images.githubusercontent.com/64796257/150926882-14cd804b-5bb9-4f95-9c8e-13ac8607ac76.png)
![image](https://user-images.githubusercontent.com/64796257/150926894-214b4fbb-94b0-4b08-875f-d00fc2257682.png)

![image](https://user-images.githubusercontent.com/64796257/150926924-1cde3c82-9984-4200-b406-117e5d137cf7.png)
![image](https://user-images.githubusercontent.com/64796257/150926940-2a301c21-eb59-45fe-be21-5c1e3913bb7f.png)

![image](https://user-images.githubusercontent.com/64796257/150926961-4ed26dce-8707-405c-9110-182e8d7a7cb7.png)
![image](https://user-images.githubusercontent.com/64796257/150926967-26d49b28-1957-474e-94ca-a8efdcb1047c.png)

이 과정을 계속 반복한다. 그러면 Q에는 아래와 같이 위상 정렬이 완료된다.

![image](https://user-images.githubusercontent.com/64796257/150927000-b3c6b760-c725-463d-959a-10005881dfe6.png)

#### 의사코드 

![image](https://user-images.githubusercontent.com/64796257/150927063-33b0b714-b03b-4a0e-9ea8-2263c670af4d.png)

`Q`라는 이름으로 `큐`를 선언. `S`는 in-coming edges가 없는 노드의 집합

`빨간상자`를 보면 `u`는 `S`로 부터 `노드 하나`를 꺼낸다. 그리고 나서 `Q`에다가 `u 노드`를 집어넣는다.

`녹색상자`를 보면 `u 노드`가 가리키는 노드들(`u 노드`의 out-going edges)이 있을 것이다.  

그 노드들을 `각각 v`라고 하면서 하나씩 가리킬 때 

- `u`에서 `v`로 향하는 간선(`u → v`)를 `제거`한다.
- `간선`을 `제거`하고 나서 `v 노드`에 더 이상 `in-coming edges가 없다`면 `v 노드`를 `S`에 집어넣는다.

⇒ 이를 통해서 `위상 정렬`된 `큐 Q`를 출력하게 된다.

#### 시간 복잡도 

![image](https://user-images.githubusercontent.com/64796257/150927482-1a21c787-6364-46c1-835e-5e0341bbb37b.png)
![image](https://user-images.githubusercontent.com/64796257/150927487-06d139b7-9917-478c-8d95-ed8bf9f5a9b5.png)

그래서 while문 안에서는 ![image](https://user-images.githubusercontent.com/64796257/150927538-a740a19f-c0be-41f8-b749-1553a4676a50.png)
만큼의 시간 복잡도가 소요된다. 

이걸 그래프의 정점의 개수만큼 반복해서 총 `O(n+m)`의 시간복잡도가 소요된다.

### DFS를 기반으로한 알고리즘(Algorithm based on DFS) 

예시와 함께 살펴보자. 

![image](https://user-images.githubusercontent.com/64796257/150928091-d1892d9a-ddfd-49ca-a9e9-9567c6c70cbb.png)
![image](https://user-images.githubusercontent.com/64796257/150928098-ba14d479-29c1-4db5-aab1-8bd936d965b1.png)

![image](https://user-images.githubusercontent.com/64796257/150928120-9177b62d-e3c0-4461-b543-ad116dbb21a4.png)
![image](https://user-images.githubusercontent.com/64796257/150928130-e5b52b00-d6d1-4245-bc55-525993040628.png)

똑같이 한다.  
![image](https://user-images.githubusercontent.com/64796257/150928186-c007dcb9-efc9-42bb-83df-7a3204b3b733.png)

![image](https://user-images.githubusercontent.com/64796257/150928196-c523bd72-dfba-4f33-a04d-90f4d4bac16d.png)
![image](https://user-images.githubusercontent.com/64796257/150928204-9166a12d-612c-4b57-ab23-44368e25f707.png)

그리고 나서 자신을 가리켰던 `점화` 노드로 돌아간다.

![image](https://user-images.githubusercontent.com/64796257/150928284-f3fde7ec-a035-47a0-9c16-5c5ff7c23669.png)
![image](https://user-images.githubusercontent.com/64796257/150928298-0d34b3de-d675-4f9c-8cf3-7c76ea386050.png)

![image](https://user-images.githubusercontent.com/64796257/150928334-be3fbda3-2ace-4904-a3d6-4e8135406b20.png)
![image](https://user-images.githubusercontent.com/64796257/150928342-011a3196-a2db-4cab-9ed6-fbe01f05e2d3.png)

그리고 나서 자신을 가리켰던 `점화` 노드로 돌아간다.

![image](https://user-images.githubusercontent.com/64796257/150928392-1264a96a-3839-48db-bd6c-3c96b1c97d78.png)

`점화` 노드는 가리키지 않았던 `수프 넣기`를 가리킨다.

![image](https://user-images.githubusercontent.com/64796257/150928433-8f20a925-6cc9-487b-b762-c8aa473e7667.png)
![image](https://user-images.githubusercontent.com/64796257/150928441-b1e41aec-f035-4117-8f0a-471cc5a11b40.png)

그러고 나서 자신을 가리켰던 `점화` 노드로 돌아간다.  
돌아가고 나면 `점화` 노드의 `out-going edges`는 전부 `DFS를 완료`했기 때문에 `점화` 노드는 `DFS를 완료`하고 `스택 S에 삽입`된다.

그러고 나서 `점화` 노드를 가리킨 `냄비에 물 붓기` 노드로 돌아간다.  

돌아가고 나면 `out-going edges`는 전부 `DFS를 완료한 노드`를 가리키기 때문에 `냄비에 물 붓기` 노드 역시 `DFS를 완료`하고 `스택 S에 삽입`된다.

그렇게 하고 나서 남아있던 노드인 `라면 넣기` 를 가리키는데 그 `노드의 out-going edges` 는 `모두 DFS를 완료`한 노드를 가리킨다.  

따라서, `라면 넣기` 노드 역시 `DFS를 완료`하고 `스택 S에 삽입`하면 되겠다.

![image](https://user-images.githubusercontent.com/64796257/150928669-ba431891-ea1d-49c6-8261-14bba60be884.png)

시간 복잡도는 `O(n + m)`


