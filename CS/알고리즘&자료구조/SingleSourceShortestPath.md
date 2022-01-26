## Single Source Shortest Path (단일 출발지 최단경로) 

일단 `Shortest Path`에 대해서 살펴보자. 

![image](https://user-images.githubusercontent.com/64796257/151081097-24f05093-1ed8-45d2-9068-2347e764f61d.png)

`s에서 t로 향하는` 경로는 여러가지가 있지만 그 중에서 `최소 경로`라 함은 `지나가는 경로`의 `가중치의 합`이 `가장 작은` 경로를 뜻한다.  
따라서, 최소 경로의 가중치는 `23`이 되겠다.

그렇다면 `Single Source Shortest Path`는 무엇일까.  
![image](https://user-images.githubusercontent.com/64796257/151081192-72adc2dd-2992-4efd-a23f-b4840dc6b44d.png)

`하나의 출발점`에서 각 `정점`에 도달하는데 드는 `비용을 계산`하여 `최단경로`를 구하는 것을 말한다. 여기서는 `출발점`을 `s`라고 했다.

이렇게 `가중치 그래프`에서 `한 정점`에서 `다른 정점`으로 갈 때,  
`가중치 합`이 `최소`가 되는 최단 경로를 찾는 대표적인 알고리즘을 2가지 배울 것이다. 

### Dijkstra's Algorithm(다익스트라 알고리즘) 

#### 개념 
: 입력 그래프의 `간선`들의 `가중치`가 `모두 음수가 아닌 경우`에 그래프의 `최단 경로`를 구할 수 있는 알고리즘이다. `Prim 알고리즘`과 비슷하다.

직관적으로 보면  
`s ⇒ t로 가는 경로`가 `최단경로`라고 하자. 이때 `s`에서 `t`로 가는 중간에 `x`라는 경로를 `지난다`고 하자.

`s ⇒ t로 가는 경로`가 `최단경로`라면, `s ⇒ x로 가는 경로` 역시 최단 경로라는 사실을 이용해서 알고리즘을 작성한다.

ex. 그림을 보자.

![image](https://user-images.githubusercontent.com/64796257/151081501-b6ade635-2286-4c03-a11f-a9ff71b8ecf6.png)
![image](https://user-images.githubusercontent.com/64796257/151081509-f29da5a1-9579-4696-b0fa-45a529379535.png)

#### 용어 정의 
- w(u, v) : `u`와 `v`를 이은 `간선의 가중치(weigth)`
- S : `shortest path tree`에 포함되어 있는 `노드들의 집합`
 
⇒ 집합 `S`안에 `노드가 있으면` 해당 노드에는 `sure`라는 표시를 해준다. 그렇지 않으면 `not-sure`라는 표시를 해준다.

- V : `그래프 G`에 포함되어 있는 `노드들의 집합` ⇒ 그렇다면 `S ⊂ V` 이 되겠다.

- D[u] : `시작 노드 s`에서 `u 노드`까지 `도달`하는 `최소 경로`

⇒ 대상이 되는 `노드 u`가 `sure`라면 `D[u]`는 `진짜 최소경로`가 되는 것이다.  
⇒ 아니라면, `최소 경로의 추정치`가 되겠다(D[u]보다 더 짧은 경로가 있을 수 있다)

#### 동작 방식 

예시와 함께 어떤 방식으로 동작하는지 살펴보겠다.

![image](https://user-images.githubusercontent.com/64796257/151081801-a68717ef-90d9-442d-a1c7-a65ef75c77fd.png)
![image](https://user-images.githubusercontent.com/64796257/151081805-48e30009-5487-4218-87c9-14dd75c1d490.png)

![image](https://user-images.githubusercontent.com/64796257/151081846-64e10de5-4da7-452f-a686-0ef4826e60f0.png)
![image](https://user-images.githubusercontent.com/64796257/151081853-d3ed8f81-2819-4192-81dc-93eb2119c4c1.png)

![image](https://user-images.githubusercontent.com/64796257/151081878-69ca0bcc-1724-4294-90fd-432b171b6c5a.png)
![image](https://user-images.githubusercontent.com/64796257/151082031-0d4e267f-4132-4679-b365-4a607730b2e8.png)

그러면, 위 그림과 같이 `1`과 `25`라는 값을 가질 것이다.

![image](https://user-images.githubusercontent.com/64796257/151082061-470d4ee6-cd19-4a08-b12c-5c56f6b1ec9c.png)

동작을 끝내고나서 가리키고 있던 `노드 u`를 `집합 S에 추가`시키면서 `sure`라고 표시해준다.

![image](https://user-images.githubusercontent.com/64796257/151082090-3041317f-590a-4f13-9a57-352d7b5b3b72.png)
![image](https://user-images.githubusercontent.com/64796257/151082099-32261d6b-98d1-4b5d-9e17-ef55e1001a25.png)

![image](https://user-images.githubusercontent.com/64796257/151082139-f2d227b0-65d1-4b3f-8fdc-eb5c1e2b9a02.png)

`가리킨 노드(u 노드)`와 연결되어 있는 `V-S`에 속한 노드들을 각각 `v`로 선택한다.

`1번 노드(v1)`를 v로 가리킬 때 `D[v]의 값`인 `무한대` // `D[u] + w(u,v)의 값`인 `1+1 = 2` 중에서 작은 값은 `2`이니까  
v가 가리키던 1번 노드의 `D[v]`의 값은 `2`로 갱신된다.

`2번 노드`를 v로 가리키는데, `D[v]`의 값이었던 `25` // `D[u] + w(u,v) = 1 + 22 = 23`  
이 중에서 작은값은 `23`이니까 v가 가리키던 2번 노드의 `D[v]`의 값은 `23`으로 갱신된다.

![image](https://user-images.githubusercontent.com/64796257/151082386-42cd556e-4834-4950-8fdb-c886b545e551.png)

위 과정을 하고 나서 `u로 가리키던 노드`를 `집합 S`에 `포함`시킨다.

또 다시 반복한다. `V-S에 속한 노드들` 중에서 `D 값`이 `가장 작은` 노드를 선택한다.

![image](https://user-images.githubusercontent.com/64796257/151082432-bc42f24f-5afc-40d3-88c8-29dffd4a26ae.png)

그 노드를 `u`로 가리키겠다.

![image](https://user-images.githubusercontent.com/64796257/151082443-cb1e098d-7ea7-4669-a59f-83851dcb89cd.png)

`가리킨 노드(u노드)`와 `이웃한 노드들(v노드)` 중에서 `V-S`에 속하는 노드는 `1번 노드`가 되겠다.  
1번 노드를 `v`로 가리키면 `D[v]`의 값인 `무한대` // `D[u] + w(u,v) = 2 + 4 = 6` 중에서 작은 값은 `6`이다.

따라서, `D[v]의 값`은 `6`으로 갱신된다.

![image](https://user-images.githubusercontent.com/64796257/151082608-4306b20d-b6ae-403a-a924-ac45a4c52435.png)

그렇게 하고 나서 가리키던 `u 노드`를 `집합 S`에 `포함`시킨다.

또 반복한다. 
![image](https://user-images.githubusercontent.com/64796257/151082643-e2b7e94b-ea9d-40d2-8123-76c48fbd7f4b.png)
![image](https://user-images.githubusercontent.com/64796257/151082670-472a9711-f150-4427-921e-72f1ea383f50.png)

그러고 나서 위에서 `u로 가리키던 노드`를 `집합 S`에 `포함`시킨다.

![image](https://user-images.githubusercontent.com/64796257/151082705-fafb3765-c88e-40c7-8320-2db7126e6b80.png)

`V-S` 중에서 `가장 작은 노드`를 `u`로 가리키는데 `u`와 `이웃`한 `V-S에 속한 노드`는 더 이상 `존재하지 않는다`.

![image](https://user-images.githubusercontent.com/64796257/151082752-26e27834-144f-43b4-8964-f2adeb6ea6b6.png)

그래서 가리키던 `u 노드`는 곧바로 `집합 S`에 포함시킨다.

![image](https://user-images.githubusercontent.com/64796257/151082829-75fb4b50-e597-44fe-ab3f-5d5c5bc9659c.png)

이를 통해서 아래와 같은 shortest path tree가 만들어 진다.

![image](https://user-images.githubusercontent.com/64796257/151082858-0f1089c7-2c81-4230-a3b7-89f2c69d0402.png)

#### 의사코드 

![image](https://user-images.githubusercontent.com/64796257/151082871-235b599b-5b37-4ed5-95c8-e6a125c56e8a.png)
![image](https://user-images.githubusercontent.com/64796257/151082882-b1ecdbd2-01c8-47c5-9a35-36ed7fa12812.png)

이걸 구현하는데 있어서 좀 더 구체적인 내용으로 들어가면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/151082961-a153117e-1c91-4562-be58-fb047e5c4a83.png)
![image](https://user-images.githubusercontent.com/64796257/151082968-c67e0f18-59dd-438d-8360-3b6768038533.png)

Q.decrease-key(v, D[v]) : `v라는 노드`의 `key값`을 `D[v]로 변경`하는 코드.

코드를 살펴보니까 `S`라는 집합의 개념을 뺐다.  
`R`이라는 집합의 노드에 `속해있느냐 그렇지 않느냐`로 나누었기 때문에 S라는 집합을 이용해서 표현하지는 않았다.

시간, 공간 복잡도는 `Prim 알고리즘`과 똑같다.

#### 단점 : 음수 가중치를 허용하지 않는다. 

### Bellman-Ford Algorithm

: 간선의 가중치값이 `음수`여도 `shortest path`를 찾을 수 있도록 하는 알고리즘이다.

#### 의사코드 

![image](https://user-images.githubusercontent.com/64796257/151083380-11122afe-3b2d-48d8-93d0-a9294c9074f4.png)

Step 0) `그래프 G`에 있는 노드들을 전부 `초기화` 한다. 이때, 처음 시작하는 `노드 s`에 대해서는 `D값`을 `0`으로 하고 `자기 자신`을 가리키도록 했다.

Step 1) 어떤 동작을 `i = 1부터 n-1`까지 `반복`한다. 여기서 `n`은 `노드의 개수`를 의미한다.  
그리고 그 안에서 또 다른 반복문이 동작한다.   
`for each u ∊ V` : 그래프에 있는 모든 노드에 대해서 반복한다. 이때 `각각의 노드`는 `u`로 가리킨다.  

`u`로 가리킨 `노드의 D값`이 `∞`가 아니라면 `u 노드`와 `이웃한 노드`들을 `v`로 가리키면서 반복문을 진행한다. 
	
만약에 `D[u] + w(u,v) < D[v]`라는 조건을 만족하면  
`D[v]의 값`은 `D[u] + w(u,v)의 값`으로 `갱신`되고 `v의 부모노드`는 `u`가 된다.(v는 u를 가리킨다)

예시와 함께 과정을 자세하게 살펴보자. 

#### 과정 

##### 0단계
일단 처음에 Step 0. 각 노드의 D값을 초기화 한다.  
![image](https://user-images.githubusercontent.com/64796257/151083926-0ccbb95b-0dfd-4f14-8d83-59a25fb67299.png)

![image](https://user-images.githubusercontent.com/64796257/151083935-ea884cc0-0214-4a9d-9f99-a7f2e40a6f60.png)
![image](https://user-images.githubusercontent.com/64796257/151083940-ff04b65d-bb19-4bc3-9fa7-194f29c85a15.png)

##### 1단계 
![image](https://user-images.githubusercontent.com/64796257/151084010-c831c1b7-f471-4244-90bc-fd599c81d20a.png)  
`i = 1`일 때, 즉 `첫 번째 반복`을 실행한다.

![image](https://user-images.githubusercontent.com/64796257/151084050-153d735a-f81c-454f-a718-056693f084b6.png)
![image](https://user-images.githubusercontent.com/64796257/151084053-e2cf6f6f-7426-42b7-b0be-fa2b3ccce0a9.png)

for each u ∊ V: (해당 반복문 동작 중)
`1번 노드`. 이 노드는 `D값`은 `0`이다.   

`D값이 ∞가 아니다` 라는 `조건을 만족`하므로 `u 노드의 이웃한 노드`들을 `v`로 가리키면서 반복문을 수행한다.  

v를 통해 가리킬 노드는 2, 3번 노드가 된다.

- 2번 노드 `D[u] + w(u,v) = 0+10 = 10`이 `D[2번 노드] = ∞`보다 작다. 따라서, `D[2번 노드]`는 `10`으로 갱신된다.  
그리고 `2번 노드`는 `u 노드(1번 노드)`를 가리키게 된다.

- 3번 노드 `D[u] + w(u,v) = 0–3 = -3`이 `D[3번 노드] = ∞`보다 작다. 따라서, `D[3번 노드]`는 `-3`으로 갱신된다.  
그리고 `3번 노드`는 `u 노드(1번 노드)`를 가리키게 된다.

2번 노드, 3번 노드, 4번 노드, 5번 노드  
⇒ `1번 노드에 대한 작업`을 하면서 `2,3번 노드`의 D값은 갱신이 되었지만   
여기서 저장한 값은 `갱신되기 이전의 값`이므로 `모든 노드`는 `if D[u] is not ∞` 조건을 만족하지 않는다.

⇒ 그래서 여기서는 `1번 노드`에서만 작업을 실행했다.

##### 2단계
![image](https://user-images.githubusercontent.com/64796257/151085000-d2da3fc0-ddf2-4e45-a16c-0d672d569924.png)

`i = 2`일 때, 이제 2번째 반복을 실행한다.  

아래와 같이 그림을 그릴 때 `V`에서 `D값`이 `∞`가 아닌건 `1,2,3번 노드`가 되겠다.  
그래서 `1,2,3번 노드`에 대해서만 `for each u ∊ V 반복문` 을 실행한다.

먼저 `1번 노드`에서 실행한다. ⇒ 이건 앞서 했기 때문에 끝났다.  

![image](https://user-images.githubusercontent.com/64796257/151085117-c1783789-3462-4cae-8236-dfc9d4bcefff.png)

`2번 노드`에서 실행한다.  

![image](https://user-images.githubusercontent.com/64796257/151085144-fdf37785-1b57-4a67-ac13-bd573057c946.png)
![image](https://user-images.githubusercontent.com/64796257/151085164-65d0a7ea-fc4e-42d6-902c-81fc3e2a195f.png)

- 1번 노드 : `D[u] + w(u,v) = 10 + 10 = 20` 이 `D[1번 노드] = 0`보다 크다. 따라서, `조건문`을 만족하지 못해서 `pass`

- 3번 노드 : `D[u] + w(u,v) = 10 + 2 = 12`는 `D[3번 노드] = -3` 보다 크다. 따라서, `조건문`을 만족하지 못해서 `pass`

- 4번 노드 : `D[u] + w(u,v) = 10 + 1 = 11`는 `D[4번 노드] = ∞` 보다 작다. 따라서 `D[4번 노드]의 값`은 `11`로 갱신된다.  
그리고 `4번 노드`는 `u 노드(2번 노드)`를 지목한다.

`3번 노드`에서 실행한다.  
![image](https://user-images.githubusercontent.com/64796257/151085500-3376bb46-0a0a-428f-b587-d79e6bcf434e.png)
![image](https://user-images.githubusercontent.com/64796257/151085502-e33aaed1-462e-4708-8787-6a0a3dd485a9.png)

- 2번 노드 : `D[u] + w(u,v) = -3 + 2 = -1` 은 `D[2번 노드] = 10` 보다 작다. 따라서 `D[2번 노드]`의 값은 `-1`로 갱신된다.  
그리고 `2번 노드`는 `u 노드(3번 노드)`를 지목한다.

- 5번 노드 : `D[u] + w(u,v) = -3 + 10 = 7` 는 `D[5번 노드] = ∞` 보다 작다. 따라서 `D[5번 노드]`의 값은 `7`로 갱신된다.  
그리고 `5번 노드`는 `u 노드(3번 노드)`를 지목한다.

##### 3단계 

![image](https://user-images.githubusercontent.com/64796257/151086861-c8e462a3-a24a-49cc-a37a-62943efc7dc8.png)

i = 3일 때, 아래의 그림과 같이 모든 노드가 무한대가 아니다. 그래서 모든 노드에 대해 `for each u ∊ V` `반복문 내용`을 실행한다.

1번 노드일 때 : 이전 상황과 달라진게 없어서 통과.  

![image](https://user-images.githubusercontent.com/64796257/151086903-9f79191f-73c2-44a8-a123-4a5a1ab9414a.png)

2번 노드일 때

![image](https://user-images.githubusercontent.com/64796257/151086919-bfc5b22e-b952-4df1-a689-417b0fbded21.png)
![image](https://user-images.githubusercontent.com/64796257/151086925-dd49ccc4-e016-40b5-a160-ca62f659c941.png)

- 1번 노드 : `D[u] + w(u,v) = -1 + 10 = 9`는 `D[1번 노드] = 0`보다 크다. 따라서, `조건문`을 만족하지 못해서 `pass`

- 3번 노드 : `D[u]+w(u,v) = -1 + 2 = 1`은 `D[3번 노드] = -3`보다 크다. 따라서, `조건문`을 만족하지 못해서 `pass`

- 4번 노드 : `D[u]+w(u,v) = -1 + 1 = 0`은 `D[4번 노드] = 11`보다 작다. 따라서, `D[4번 노드]`의 값은 `0`으로 갱신된다.  
그리고 `4번 노드`는 `u 노드`를 지목한다.

3번 노드일 때

![image](https://user-images.githubusercontent.com/64796257/151087716-10c3ee34-1f7c-434f-8f41-5b59328e3ca6.png)
![image](https://user-images.githubusercontent.com/64796257/151087727-738e3ce1-05b7-4da4-8ad0-3e0606979b3f.png)

- 2번 노드 : `D[u]+w(u,v) = -3 + 2 = -1`은 `D[2번 노드] = -1`과 똑같다. 따라서, `조건문`을 만족하지 못해서 `pass`

- 5번 노드 : `D[u]+w(u,v) = -3 + 10 = 7`은 `D[5번 노드] = 7`과 똑같다. 따라서, `조건문`을 만족하지 못해서 `pass`

4번 노드일 때

![image](https://user-images.githubusercontent.com/64796257/151087833-19144f82-12f0-4646-aea4-ed553c04fa77.png)
![image](https://user-images.githubusercontent.com/64796257/151087836-046f8f17-b0c8-430c-ba8d-233159423e6a.png)

- 2번 노드 : `D[u] + w(u,v) = 0 + 1 = 1`은 `D[2번 노드] = -1`보다 크다. 따라서, `조건문`을 만족하지 못해서 `pass`

5번 노드일 때

![image](https://user-images.githubusercontent.com/64796257/151087961-fecc369c-9a9e-49d4-9f43-ec030e88d207.png)
![image](https://user-images.githubusercontent.com/64796257/151087967-ddf0d430-44cf-4229-922d-3a8d17c16919.png)

- 4번 노드 : `D[u] + w(u,v) = 7 + 4 = 11`은 `D[4번 노드] = 7`보다 크다. 따라서, `조건문`을 만족하지 못해서 `pass`

##### 4단계 

![image](https://user-images.githubusercontent.com/64796257/151088057-679e5fd3-2ab0-4d70-81ad-75cc9612089a.png)

i = 4일 때 4번째 반복. 마지막 반복을 실행한다.  
모든 노드가 무한대가 아니기 때문에 `모든 노드`에 대해 `for each u ∊ V` `반복문 내용`을 실행한다.

1번 노드일 때 ⇒ 이전과 상황이 똑같다. 그래서 pass

![image](https://user-images.githubusercontent.com/64796257/151088561-4b591a15-e67c-42b8-b77b-8450914b3968.png)

2번 노드일 때 ⇒ i = 3일 때 상황에서 변한게 없다. 그래서 pass

![image](https://user-images.githubusercontent.com/64796257/151088575-426431ac-6e86-4c2d-9296-5439157701a5.png)

3번 노드일 때 ⇒ i = 3일 때 상황에서 변한게 없다. 그래서 pass

![image](https://user-images.githubusercontent.com/64796257/151088590-3d3c0938-4a4f-4ec5-bf91-67840f6c1b75.png)

4번 노드일 때 ⇒ i = 3일 때 상황에서 변한게 없다. 그래서 pass

![image](https://user-images.githubusercontent.com/64796257/151088597-4c8836a9-ffb3-476f-9a38-7167daf4e1e2.png)

5번 노드일 때 ⇒ i = 3일 때 상황에서 변한게 없다. 그래서 pass

![image](https://user-images.githubusercontent.com/64796257/151088617-4a3c5806-6cba-481f-a2f7-15d5dabf171f.png)

이로써 `Bellman-Ford` 알고리즘을 통해 `Shortest path tree`가 만들어졌다.

![image](https://user-images.githubusercontent.com/64796257/151088683-0fa11df6-a174-4380-9d1d-2258d4638dc0.png)

#### 시간복잡도 

![image](https://user-images.githubusercontent.com/64796257/151088779-5bd60121-2c3d-4e3d-851d-3a320e40135c.png)

`빨간 상자`는 `i`를 `노드의 개수`만큼 `n번 반복`하는 반복문이다. `녹색 상자`는 `간선의 개수`만큼 `m번 반복`하는 반복문이다.

⇒ 초기화 과정은 `상수 시간`만큼 소요 되므로 시간복잡도 계산에서 제외된다.

따라서, 시간 복잡도는 `O(nm)`이다.

그리고 Bellman-Ford 알고리즘은 `Negative Cycle`을 찾아낼 수 있다. 이 내용을 포함한 의사코드는 다음과 같다.

`Step 1`까지 다 끝내고 나서도 `아래에 있는 반복문`을 충족시킨다면, 이는 `음의 가중치`로 이뤄진 `간선의 cycle`이 `존재`한다고 할 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/151088894-0d234ad0-7b4c-49d5-9176-b0ec241a22c5.png)













