## Minimun Spanning Tree(MST) - 최소 신장 트리 

### 개념

: 트리도 그래프의 한 유형이다. 

![image](https://user-images.githubusercontent.com/64796257/150917639-9eb06357-5612-48ae-bff5-64c1fa46a335.png)

위 그래프를 보고 정점 `B`에서 `D`로 가는 모든 `경로(path)`를 열거해보자.

`B-A-D` / `B-A-C-D` / `B-C-D`/ `B-C-A-D` 이렇게 4가지 경로가 있다.  

- 경로 : 두 개의 정점을 잇는 간선을 순서대로 나열한 것
- 단순 경로 : 동일한 간선을 중복하지 않은 경로

그렇다면 위 그림의 그래프를 대상으로 구성한 경로인 `A-B-C-A` 역시 `단순 경로`이다.   

여기서 `시작`과 `끝`이 `같은 단순경로`를 `사이클(cycle)`이라 한다. 그림을 보면 쉽게 이해할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150917833-cc4c0149-6b4c-4445-a9fa-954f976cadb6.png)

지금까지 `경로`, `단순 경로`, `사이클`에 대해서 살펴봤다.  
이러한 개념들을 소개한 이유는 이번에 공부할 내용이 `사이클`을 형성하지 않는 그래프이기 때문이다.  

대강 이런 정도의 느낌이라 보면 되겠다.  
![image](https://user-images.githubusercontent.com/64796257/150917882-f31960fd-368c-40e0-85a3-6b8b71a1cec3.png)

이들을 `90도 또는 180도 회전`하면 `트리`라고 할 수 있다.  
이와 같이 사이클을 `형성하지 않는 그래프`를 가리켜 `신장 트리(spanning Tree)`라 한다.

### 이해와 적용 
신장 트리의 특징은 2가지가 있다.
- 그래프의 모든 정점이 간선에 의해 하나로 연결되어 있다.
- 그래프 내에서 `사이클`을 형성하지 않는다.

이뿐만 아니라 `가중치 그래프`와 간선에 방향성이 부여된 `방향 그래프`를 대상으로도 신장 트리를 구성할 수 있다.

ex) 가중치 무방향 그래프   
![image](https://user-images.githubusercontent.com/64796257/150921545-130ced7b-74c1-40a8-a539-52c029780b47.png)

그리고 `신장 트리`의 모든 `간선의 가중치 합`이 `최소`인 그래프를 가리켜 `최소 신장 트리`라 한다. 

위 그래프를 통해 `최소 신장 트리(줄여서 MST)`를 구성하면 다음과 같이 할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150921644-b45aa979-6ec7-48c9-9981-3a45ddea7d8c.png)

그렇다면 `MST`가 어떤 형태로 활용될 수 있을까? 이에 대한 얘기를 아래에서부터 해보자.

강원, 경기, 경북, 울산, 전북을 직선으로 연결하는 물류에 특화된 도로를 건설한다고 가정하자.  
그러면 다음과 같은 모든 지역이 직선으로 연결된 이상적인 환경을 기대할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150921701-f1e0b1fc-e5fc-479b-9c85-438da6ab7cbe.png)
![image](https://user-images.githubusercontent.com/64796257/150921712-3c461148-991f-4a1b-8d48-b1cec5294243.png)

![image](https://user-images.githubusercontent.com/64796257/150921752-03a7ba2f-9dec-4216-8e31-277da3d8cff2.png)
![image](https://user-images.githubusercontent.com/64796257/150921762-47d7a12a-2a42-4f4c-923e-e0b71a8308b1.png)

### MST의 구성에 사용되는 대표적인 알고리즘 

크루스칼(Kruskal) 알고리즘  
⇒ `가중치`를 기준으로 `간선을 정렬`한 후 `MST가 될 때까지` 간선을 하나씩 선택 또는 삭제해 나가는 방식.

### 크루스칼 알고리즘(Kruskal Algorithm)

#### 개념 

##### 방법 1
![image](https://user-images.githubusercontent.com/64796257/150921978-f9bbb158-dd57-440d-8112-cdabf6323b9b.png)
![image](https://user-images.githubusercontent.com/64796257/150921987-d8af4ac1-90fd-4ee4-a067-34ec55dfcdef.png)

이제 가중치가 낮은 간선들을 하나씩 추가해보자. 가중치가 2, 3, 4, 6인 간선을 하나씩 추가하면 아래와 같은 그림이 된다.  
![image](https://user-images.githubusercontent.com/64796257/150922035-71c6c2e9-ea95-4afb-a951-4e565e1e3735.png)
![image](https://user-images.githubusercontent.com/64796257/150922041-b3742f34-56c5-4133-a5fa-eed58d0b0f97.png)

`MST`에서는 `사이클`이 형성되면 안되니까 `가중치가 7인 간선`은 추가되지 않는다.  
그래서 가중치가 8인 간선으로 넘어간다. 이를 추가해도 사이클이 형성되지 않기 때문에 다음과 같이 그릴 수 있다

![image](https://user-images.githubusercontent.com/64796257/150922148-8b4be378-21e3-4b7b-ba81-c5d5698c6a07.png)
![image](https://user-images.githubusercontent.com/64796257/150922157-d1463470-7884-4230-b498-338ab5581531.png)

최종 결과  
![image](https://user-images.githubusercontent.com/64796257/150922194-5d260618-e1b3-4405-a5f3-b8e728ba1629.png)
![image](https://user-images.githubusercontent.com/64796257/150922208-dc185c8d-d9c0-4301-8a52-83c35bf666e8.png)

지금까지는 `오름차순` 정렬에 대해서 살펴봤는데 `내림차순`으로 정렬된 상황에서도 적용할 수 있는 모델도 있다.  
이에 대해 알아보자.

##### 방법 2 
이 방식은 높은 가중치의 간선을 하나씩 빼는 방식으로 알고리즘이 전개된다. 다음 그림을 보면서 알아보자.

![image](https://user-images.githubusercontent.com/64796257/150922353-7af2d20a-141a-407f-9187-300e108698b4.png)
![image](https://user-images.githubusercontent.com/64796257/150922363-027a7a33-f147-4810-9c4d-1b7bdeb8e343.png)

![image](https://user-images.githubusercontent.com/64796257/150922394-779b6fd0-236b-489d-aacd-e6727ca5bb6d.png)
![image](https://user-images.githubusercontent.com/64796257/150922399-f7560796-cb77-496b-9654-7ee9b6e3bfdf.png)

![image](https://user-images.githubusercontent.com/64796257/150922422-f9fcabca-5873-4a91-b761-93a8347c554d.png)

이어서 `가중치가 8인 간선`을 `삭제`할 차례이다. 그런데 이 간선을 `삭제`하면 다음과 같은 문제가 발생한다.  
“가중치가 8인 간선을 삭제하면 정점 `A`와 `D`는 어떤 경로를 통해서든 `닿지 않는다`.”

그래서 `가중치가 8인 간선`은 삭제하지 않고 다음 `가중치가 7인 간선`으로 넘어간다.

`가중치가 7인 간선`은 삭제할 수 있다. `삭제`를 해도 정점 `C`와 `E`는 `C-D-E 경로`를 통해 서로 이어져 있기 때문이다.  
따라서, 최종적인 상태는 다음과 같이 완성된다.

![image](https://user-images.githubusercontent.com/64796257/150922574-9d611957-6386-4099-ad60-07aa5dc2a224.png)

이로써 `MST`가 완성되었다. `간선의 수 + 1 = 정점의 수` 를 만족하기 때문에 `MST가 완성`되었음을 확인할 수 있다.  
두 번째 크루스칼 알고리즘의 핵심을 정리하면 다음과 같다.

- 가중치를 기준으로 간선을 `내림차순으로 정렬`한다.
- 높은 가중치의 간선부터 시작해서 하나씩 그래프에서 `제거`한다.
- 두 정점을 연결하는 다른 경로가 없을 경우 해당 간선은 제거하지 않는다.
- `간선의 수 + 1 = 정점의 수` 라는 식을 만족할 때 MST는 완성된다.

간선이 연결되었는지 확인하기 위해서 DFS 또는 BFS를 활용해야 한다.

![image](https://user-images.githubusercontent.com/64796257/150932186-84b2eb4e-f21a-4173-8c18-d8f2ec8f20d5.png)


### 프림 알고리즘(Prim Algorithm) 


#### 개념 

: `시작 정점`에서부터 `출발`해서 `신장 트리` 집합을 `단계적으로 확장`해나가는 방법.

1) `시작 단계`에서는 `시작 정점만`이 MST 집합에 `포함`된다.
2) 앞 단계에서 만들어진 `MST 집합에 인접한 정점`들 중에서 `최소 간선으로 연결된 정점`을 선택해서 트리를 `확장`
3) 위의 과정을 `트리`가 `N-1개의 간선을 가질 때까지` 반복한다.

#### 의사코드 및 구현모델 

![image](https://user-images.githubusercontent.com/64796257/150929912-d1d9f9ab-2734-4471-87d0-d8175fe1f72f.png)
![image](https://user-images.githubusercontent.com/64796257/150929917-fe5bbc22-7277-43d1-86df-2a4c1ffa0819.png)

extract-min(Q, c[]) : `Q`라는 그래프에 있는 노드 중에서 `최소 비용을 가지는 노드`를 반환한다.

즉, prim(G, r)의 의미는 `그래프 G`에 있는 `r 노드`에서 `MST`를 찾기 시작할 거라는 얘기다.

예제를 따라가면서 의사코드에 적힌 내용을 이해해보자.

![image](https://user-images.githubusercontent.com/64796257/150930093-bad5c467-fb8d-4ac8-b76b-d6956f6fb753.png)
![image](https://user-images.githubusercontent.com/64796257/150930101-0b22e6e6-6e29-4494-8794-be7a4e46833f.png)

![image](https://user-images.githubusercontent.com/64796257/150930142-b49867ab-2c8e-4ba5-a342-dd5e3272f261.png)
![image](https://user-images.githubusercontent.com/64796257/150930149-ca915f03-b941-4e00-94ab-c4aedf3b93fc.png)

![image](https://user-images.githubusercontent.com/64796257/150930170-1e8ff80f-6028-469c-80e4-b3bd3646a0fd.png)
![image](https://user-images.githubusercontent.com/64796257/150930180-557d8ba7-185e-4960-b24b-ad3372343fbd.png)

이제 while 반복문을 시작한다.   
![image](https://user-images.githubusercontent.com/64796257/150930435-5a877d93-19b7-4204-b969-1d014b344b38.png)
![image](https://user-images.githubusercontent.com/64796257/150930450-b849bcd7-e274-4ba5-b453-4f1a2ef064cf.png)

기존에 `S`는 `공집합`이었다. 그러면 `V-S`는 아직 `그래프 전체`가 된다. 

그래프 전체 중에서 `가장 작은 값`을 가지는 `노드`를 `u`로 가리킨 다음에 해당 노드가 `S의 원소`가 되도록 했다.

그러고 나서 `for문`을 실행하는데 `u가 가리키는 노드`의 `이웃한 노드`들을 각각 가리키도록 하고

이때, `u에 이웃`한 노드들이 `V-S의 원소`이면서 `u와 연결된 간선의 가중치 값`보다 `이웃한 노드들이 가지고 있는 값`이 더 크다면
(V-S는 S를 제외한 나머지 부분이라고 이해하면 되겠다)

`u와 연결된 간선의 가중치 값`이 `노드의 값`이 `되도록` 하고 `해당 노드`는 `u 노드`를 가리키도록 한다.

이 과정을 한 번 거치면 아래와 같은 그림을 그릴 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150931751-ed588dbc-571e-4899-86f5-fb5da110f210.png)

이후에도 계속해서 `while 문`을 실행한다. 

S를 제외한 가장 작은 값을 가진 `노드 8`을 `u`가 가리킨다.

`u`가 현재 가리키는 노드 = `8을 가진 노드`의 `이웃한 노드`가  

`V-S`에 속하면서 `그 노드의 값`이 `u와 연결된 간선의 가중치 값`보다 `크다`면

`해당 노드의 값`이 `u와 연결된 간선의 가중치 값`이 `되도록`하고 `해당 노드`는 `u 노드`를 가리킨다.

이 과정을 반복하면 어느 순간 `S = V`가 되고 `각각의 노드`가 `가리키고 있는 경로`가 남아있게 되는데 `그 경로가 MST`가 된다.

#### 구현할 때 필요한 상황 

![image](https://user-images.githubusercontent.com/64796257/150932341-09ebf205-6cd3-4902-b045-2673d4218e8f.png)

`R`이라는 집합을 설정한다. 이때 `R`은 `V에서 S를 뺀` `나머지 집합`이다.   
`Prim algorithm`을 보면 알겠지만, 그래프 전체와 같았던 `V`가 `하나씩 줄어`들면서 `S가 V가 될 때까지` 알고리즘이 지속된다.

여기서 `V가 S와 똑같아진다`는 건, `나머지 집합 R`이 `하나씩 없어진다`는 것으로도 얘기할 수 있다.

그래서 while문의 조건문인 `S is not V` 와 `R is not empty`는 `동치`가 된다.

delete-min(R, c) : 집합 R에서 cost가 가장 작은 노드의 값을 return하고 해당 노드를 집합 R에서 제거한다.

![image](https://user-images.githubusercontent.com/64796257/150932578-ebef87f7-a3e1-4996-a6c6-1e9a1fd50b49.png)
![image](https://user-images.githubusercontent.com/64796257/150932583-b7a76317-d76c-4b23-947f-a0242299af83.png)

앞서 살펴본 과정을 `표현만 달리한 것`이라 생각하면 되겠다.  

앞에서는 최소값을 가지는 노드를 MST에 해당하는 `집합 S`에 `하나씩 추가`한 것이라면  
여기서는 최소값을 가지는 노드를 MST에 `해당하지 않는 집합 R`에서 `하나씩 삭제`하는 과정이라 생각하면 되겠다.

#### 시간/공간 복잡도 

![image](https://user-images.githubusercontent.com/64796257/150932766-d331a507-9b37-4682-9ce7-c4d13a60d571.png)

m = 간선의 개수 / n = 노드의 개수 ![image](https://user-images.githubusercontent.com/64796257/150932805-17521e09-65f2-4a66-95a1-0174bec66387.png)

### 사용하기에 적합한 경우

1) 그래프가 dense(밀집되어 있다면) Prim 알고리즘을 사용하는 것이 더 좋다.

2) 그래프가 sparse(듬성듬성)하거나 edge들이 어느 정도 정렬되어 있다면... Kruskal 알고리즘을 사용하는 것이 더 좋다.
