## A* 알고리즘 

: 그래프의 `시작점`에서 `도착점`에 이르는 `최단 경로`를 찾는 알고리즘이다.

: 최단 경로를 찾는 또 다른 알고리즘인 `Dijkstra 알고리즘` 보다 훨씬 `속도가 빠르다`. 하지만, 최단 경로를 찾기 위한 `guide`가 있어야 한다.

[`A*`와 다익스트라의 차이를 보여주는 영상](https://www.youtube.com/watch?v=g024lzsknDo)

### 개념 

시작점 `s` 에서 도착점 `t`에 도달하려는 상황이다.

![image](https://user-images.githubusercontent.com/64796257/151089467-4526088e-382c-44ae-9ba3-5c2ae2dd7046.png)
![image](https://user-images.githubusercontent.com/64796257/151089471-3706da02-7e4f-411e-be19-09b80a9dfe07.png)

![image](https://user-images.githubusercontent.com/64796257/151089501-b01425a8-a677-41e2-9161-020b07abb85e.png)
![image](https://user-images.githubusercontent.com/64796257/151089506-ca3c8ab4-7fbd-45b9-b612-ee7c407bf333.png)

정확하게 얼마나 남아있는지를 측정할 수는 없어서 대략적인 측정을 위해 아래와 같은 방법을 사용한다.

![image](https://user-images.githubusercontent.com/64796257/151089558-c39666b7-5ff8-4642-bfb6-5351301ba1e3.png)

앞서 언급했듯이 `x에서 t`로 향하는 `경로`를 정확하게는 알 수 없다. 

그래서 `guide`는 `x에서 t`까지의 `최소한의 거리`를 알려준다. 여기서는 `20km`라고 알려줬다.  
마찬가지로, `y에서 t`까지의 `최소한의 거리`는 `10km`라고 알려줬다.

그렇다면 `s`를 기준으로 `x`와 `y` 중에서 `y를 고르는 것`이 `더 좋은 선택`이라는 것을 알 수 있다.

이렇게 guide가 알려준 `나머지 거리의 최솟값`을 토대로 `최단 거리를 선택`하는 방식이 `A* 알고리즘`이다.

### 용어 정의 

![image](https://user-images.githubusercontent.com/64796257/151090303-2ff03141-18fc-4b98-936f-b95517b8a492.png)

### 동작 방식 

![image](https://user-images.githubusercontent.com/64796257/151090449-776f83a0-1ce4-47f5-bdd0-df57e2a2754a.png)

`s에서 시작`해서 `t로 향하는` `최단 경로`를 구할 것이다. 점선 상자 안에는 `t`로 향하는 `추정 거리`를 `guide`로 구해놓은 상황이다.

#### 0단계 

![image](https://user-images.githubusercontent.com/64796257/151090498-f39d7516-caf7-48c1-8324-8aad6ce170ba.png)

각 모서리의 `동그라미의 값`은 `s`에서 시작해서 `해당 모서리`로 향하는 `최소 cost`를 의미한다.

`()값` = `동그라미 값` + `해당 모서리에서 시작해서 t에 도착하기 위한 추정값`

`s 노드` 자체를 보면 `s에서 시작`해서 `s로 도착`하는 `cost는 0`이다.(D[x]의 값) 그래서 `동그라미 안`에 `0`이 들어있다.

`()` 안에 있는 값은 `최소 cost의 값`과 `추정치`를 더한 `52( = 0 + 52)`를 넣었다.(f[x] =  D[x] + h[x] = 0 + 52)

⇒ 이렇게 초기화한 상태에서 알고리즘을 시작한다.

#### 1단계 

그래프에 있는 남아있는 노드들 중에서 `가장 f[x]의 값`이 `작은` 노드를 고른다.

![image](https://user-images.githubusercontent.com/64796257/151090785-7496d929-36b8-4a2e-b6e2-68ab345e5f2e.png)
![image](https://user-images.githubusercontent.com/64796257/151090789-6c74807f-f587-4018-a7a7-9e4ac4bf44fe.png)

앞서 선택한 노드의 `이웃한 노드들`의 값들을 갱신한다. 

`선택한 노드`를 `u`라고 하고 `u 노드에 이웃한 노드들`을 `v`라고 하자.  
`D[u] + w(u,v) < D[v]`를 `만족`한다면 `v의 노드의 값들`을 갱신한다. 

![image](https://user-images.githubusercontent.com/64796257/151090936-6063c6b3-aacc-4687-b16a-509963ee575a.png)

위와 같이 갱신해주면 된다. 어떻게 갱신되었는지 예시와 함께 살펴보겠다.

ex. `1번 노드`를 `v`가 가리킨다고 하면 `D[u] + w(u,v) = 0 + 17 ` < `D[v] = ∞`가 성립하므로 `D[v] = 17`이 된다.  
   (이 부분은 다익스트라 알고리즘을 생각하면 된다)

그리고 `f[v]의 값`도 `갱신`해줘야 하는데 조건은 `D[u] + w(u,v) < D[v]` 를 만족할 때 같이 갱신하면 된다.  
`f[v]`의 값은 `D[v] + h[v] = 17 + 68 = 85`로 갱신해주면 된다.

이런 식으로 `u`와 `이웃`한 나머지 노드들의 값들을 갱신해줬다. 그렇게 갱신해주고 나서 기존에 선택했던 `u 노드`는 기존의 집합에서 제외된다.

이때, `A* 알고리즘`은 `다익스트라 알고리즘`과 다르게 `노드의 값`이 작은 것을 큐에 넣지 않고  

각각의 노드의 `f값`을 `큐`에 넣어서 `f값이 작은 노드들`을 `하나씩 가리키면서` 알고리즘을 진행한다.

#### 2단계

그래서 `f[x]의 값`이 `가장 작은 노드`를 고른다. 

![image](https://user-images.githubusercontent.com/64796257/151091267-64fb15f4-ca43-453b-acee-60f2900381fb.png)

`가리키는 노드`와 `그에 이웃한 노드들`을 `갱신`하는데 `D[u] + w(u,v) < D[v]`를 만족하는 노드들만 갱신한다.

![image](https://user-images.githubusercontent.com/64796257/151091368-e97a7631-73ec-40ca-a82a-3cbeb35f8327.png)

그래서 여기서는 `빨간색 화살표`로 가리킨 노드만 갱신한다. 

이웃한 노드들 중 

노드의 값이 25인 노드 : `D[u] + w(u,v) = 23 + 28 = 51`이고 `D[v] = 25`이기 때문에 `조건`을 만족하지 않는다.

`빨간색 화살표`로 가리킨 `노드`를 살펴보자.  
`D[u] + w(u,v) = 23 + 18 = 41`이고 `D[v] = ∞` 이기 때문에  
`D[v]의 값`은 `D[u] + w(u,v) = 23 + 18 = 41`로 갱신되고 `f[v]의 값`은 `D[v] + h[v] = 41 + 19 = 60`으로 갱신된다.

그렇게 갱신작업을 하고 나서 나머지 노드들 중에서 `f값`이 `가장 작은 노드`를 가리키고 해당 노드에 대해서 똑같은 작업을 수행한다.

#### 3단계

![image](https://user-images.githubusercontent.com/64796257/151091578-1e94633f-a844-4626-afa5-61ed29ca7abe.png)

![image](https://user-images.githubusercontent.com/64796257/151091614-152bc77f-7ed2-453a-b41a-0b47d7c9ecb7.png)

가리키던 노드와 이웃한 노드는 `빨간색 화살표`로 가리킨 노드이다. 

마찬가지로 `D[u] + w(u,v) < D[v]`를 `만족`하기 때문에 갱신할 수 있다. 

`D[u] + w(u,v) = 41 + 20 = 61`이고 `D[v] = ∞`여서  
`D[v]의 값`은 `D[u] + w(u,v) = 41 + 20 = 61`로 갱신된다. `f[v]의 값`은 `D[v] + h[v] = 61 + 0 = 61`이 된다.

#### 마무리

이로써 `s에서 t로 가는 최단 경로`를 구할 수 있게 된다.

⇒ `A* 알고리즘`의 `시간 복잡도`는 `min-heap`을 쓸 때 최악의 경우 `O(mlogn)`이 걸린다. ex. 일직선 경로.

하지만, `A*`는 single-pair shortest path 문제를 해결할 때 `다익스트라`보다 훨씬 `속도가 빠르다`.

![image](https://user-images.githubusercontent.com/64796257/151091760-18f18bad-2c80-4a74-9769-6ac8ca0e5b77.png)

### `A*` 알고리즘을 SSS(State Space Search)에 적용 

#### 용어 정의 

![image](https://user-images.githubusercontent.com/64796257/151091901-b0360307-3c15-4949-a209-4ee4976327b7.png)

표현은 앞서 배운 SSS와 거의 비슷하다.  
`나머지의 최소값`을 `h(x)`로 표현했고 `전체적인 기준`이 되는 값을 `f`로 표현했다. 

동작 방식에 차이가 있는데 이에 대해 살펴보자. 

![image](https://user-images.githubusercontent.com/64796257/151092230-2013cb2d-e4d3-4cdd-9071-189166495ac5.png) 

![image](https://user-images.githubusercontent.com/64796257/151092564-657e214b-4fa2-454f-924a-5fdaaf42af03.png) 
![image](https://user-images.githubusercontent.com/64796257/181383432-717e1e37-0bc8-4d52-a1c4-28dae48439a3.png)

이제 `1번 노드`와 `이웃한 노드들`을 가지고 만들 수 있는 `경로`에 대해 값들을 갱신한다. 

![image](https://user-images.githubusercontent.com/64796257/151092598-82e9b397-282c-47a5-9105-cc46e8c97602.png)

`방문하지 않은 노드들` 중에서 `가장 작은 f값` 가진 `1-2 경로`를 먼저 방문한다.

![image](https://user-images.githubusercontent.com/64796257/151092646-abff6e39-5efa-4ad8-b523-46babcc3de0a.png)

`1-2`를 방문했으니 1-2의 이웃들 즉, `1-2-*`의 경로들의 값들을 갱신한다.

![image](https://user-images.githubusercontent.com/64796257/151092679-e35768f1-9c03-4787-8458-68c2c3fa84d1.png)

`방문하지 않은 경로` 중 `가장 작은 f값`을 가진 노드를 방문한다.

![image](https://user-images.githubusercontent.com/64796257/151092698-1190d414-1f2e-4c0b-8f23-8a9681a0fead.png)

가리킨 경로가 `1-2-5`니까 `1-2-5-*의 경로`의 값들을 update한다. 

(여기서 헷갈리면 안되는게 `3번 노드`를 가리킨 이유는  
`2번 노드의 자식 노드들 중`에서 `값`이 가장 작아서 고른게 아니라 `아직까지 방문하지 않은 노드들` 중에서 `f값이 가장 작은 노드`였기 때문에 `3번 노드를 방문`한 것이다)

그래서 `1-2-5의 이웃한 노드들`은 `f값`들을 각각 `45, 40`이니까 `방문하지 않은 노드들 중`에서는 `가장 작은 값이 아니니까` 방문하지 않았다.

![image](https://user-images.githubusercontent.com/64796257/151092818-aee6be35-0cfb-4a75-b132-20448345bf91.png)

이 상태에서 `방문하지 않은 노드들` 중 `가장 작은 f값`을 가진 노드를 선택한다.

![image](https://user-images.githubusercontent.com/64796257/151092986-09b5c36b-f216-4ea5-b200-f78e6b64ce84.png)

똑같은 작업을 한다. 1-3 노드의 이웃. `1-3-* 노드의 값`들을 갱신한다.

![image](https://user-images.githubusercontent.com/64796257/151093004-5b0aa75c-6c35-4067-8563-14e2fec6a24d.png)

`방문하지 않은 노드들`중에서 `가장 작은 f값을 가진 노드`를 가리키고 그 노드와 이웃한 노드들의 값을 갱신한다.

![image](https://user-images.githubusercontent.com/64796257/151093063-676b0f50-d69d-4271-bc0c-ef28e4efdf37.png)

`방문하지 않은 노드들`중에서 `가장 작은 f값을 가진 노드`를 가리키고 그 노드와 이웃한 노드들의 값을 갱신한다.

![image](https://user-images.githubusercontent.com/64796257/151093094-d61c9311-3d45-4498-a134-d8786054d4ea.png)

`방문하지 않은 노드들`중에서 `가장 작은 f값을 가진 노드`를 가리키고 그 노드와 이웃한 노드들의 값을 갱신한다.

![image](https://user-images.githubusercontent.com/64796257/151093115-be920484-e832-4e38-a730-055bc8fe643a.png)

`방문하지 않은 노드들` 중에서 `가장 작은 f값을 가진 노드`를 가리킨다.  
이때 가장 작은 f값을 가진 노드가 `leaf 노드`이므로 최단 경로를 찾는 과정을 `종료`한다.

![image](https://user-images.githubusercontent.com/64796257/151093210-5904a352-20f7-4809-b60f-83a644532f14.png)

그렇다면, 이렇게 결론 내리는 것이 맞는 건가? 괜찮다.

![image](https://user-images.githubusercontent.com/64796257/151093241-b1cd38b1-a2fa-40ee-96e3-41672b802caa.png)

`w 노드`를 `A* 알고리즘`에서 `처음 방문한 leaf node`라고 하면 `2개의 그룹`으로 나눌 수 있다. 

1) 이미 `계산이 완료`된 leaf 노드 

⇒ `w 노드`를 맨 처음 방문한 이유는 `다른 모든 leaf 노드들`과 비교했을 때 가장 작은 값이기 때문에 `w 노드`를 가장 먼저 선택한 거다.  
따라서, `계산이 완료된 leaf 노드`들 중에서는 `w 노드`가 가장 `f값`이 작다.

2) 아직 `계산하지 않은` leaf 노드 
 
⇒ 이 그룹에 있는 `leaf 노드들`이 `계산되지 않은 이유`는 각 노드들의 `조상 노드`의 `f값`이 `w 노드보다 작기 때문`이다.   
이 그룹의 `leaf 노드의 조상의 f값`이 `w 노드의 f값`보다 `크기` 때문에 당연히 `해당 그룹에 있는 노드들의 f값`은 `w 노드`보다 `클` 수 밖에 없다.

따라서, 맨 처음에 방문한 leaf node는 `가장 작은 cost(f값)`를 가진 경로라고 할 수 있다.


























