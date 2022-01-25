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

이로써 `MST`가 완성되었다. `정점의 수 + 1 = 간선의 수` 를 만족하기 때문에 `MST가 완성`되었음을 확인할 수 있다.  
두 번째 크루스칼 알고리즘의 핵심을 정리하면 다음과 같다.

- 가중치를 기준으로 간선을 `내림차순으로 정렬`한다.
- 높은 가중치의 간선부터 시작해서 하나씩 그래프에서 `제거`한다.
- 두 정점을 연결하는 다른 경로가 없을 경우 해당 간선은 제거하지 않는다.
- `정점의 수 + 1 = 간선의 수` 라는 식을 만족할 때 MST는 완성된다.

간선이 연결되었는지 확인하기 위해서 DFS 또는 BFS를 활용해야 한다.











