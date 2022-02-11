그래프 탐색. 즉, 그래프의 모든 정점을 돌아다니기 위한 알고리즘 2가지.
- DFS
- BFS

## DFS (Depth First Search; DFS) - 깊이 우선 탐색 

### 개념 

다음은 5학년 3반 어린이들의 비상연락망이다.

![image](https://user-images.githubusercontent.com/64796257/150914693-1796c019-1442-4263-8909-c791fc3612ed.png)
![image](https://user-images.githubusercontent.com/64796257/150914702-f6b3f618-b5b9-48dc-8be4-831caf1649f2.png)

이런 식으로 한 사람에게만 메시지를 전달한다는 생각을 모든 사람이 동일하게 가지고 있다고하면,   
메시지는 아래와 같은 과정을 거쳐서 `수정`이에게 전달된다.

![image](https://user-images.githubusercontent.com/64796257/150914785-606d353e-720c-473c-b1b1-19a8fd98f8eb.png)
![image](https://user-images.githubusercontent.com/64796257/150914789-b707dffc-945c-456d-b14e-0ec3c1adb574.png)

`DFS 알고리즘`은 누구를 선택하던지 잘 동작하며, 누구를 우선적으로 선택할 것인지에 대한 기준은 구현하는 사람이 결정해도 된다.  
지금까지의 그림을 보면 `동수`에게서 시작된 메시지가 `수정`이까지 전달되었다.  

그런데 `수정`이와 연결되어 있는 `민석`이는 이미 메시지를 받은 상태이다. 

그래서 `수정`이는 자신에게 연락을 준 `정희`에게 다음과 같이 얘기한다.  

“나(수정)랑 연결된 애들은 다 연락을 받았다. 그러니 너랑 연결된 애들 중 연락을 못 받은 애가 있으면 그쪽으로 연락을 줘라.”

하지만, `정희`도 더 이상 연락을 취할 사람이 없다.  

그래서 자신에게 연락을 준 `민석`이에게 연락을 해서 `수정` 이랑 똑같은 얘기를 한다. 
그래서 `민석`이는 연락을 받지 못한 `지율`이에게 연락한다. 이러한 전달과정을 그림으로 표현하면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150914973-643cb7e9-f11a-4199-920b-ce1ff89550a1.png)
![image](https://user-images.githubusercontent.com/64796257/150914977-0f0802ce-d628-4c97-86eb-dded28131bb1.png)

앞서 언급한 수정이의 메시지를 그대로 따른다.

“나랑 연결된 애들은 다 연락을 받았다. 그러니 너랑 연결된 애들 중 연락을 못 받은 애가 있으면 그쪽으로 연락을 줘”

그래서 `지율`이는 자신에게 연락을 했던 `민석`이에게 연락을 전달하고  
`민석`이는 자신에게 연락을 준 `지민`이에게 다시 연락을 주고 `지민`이는 자신에게 연락을 준 `동수`에게 다시 연락을 준다.

이를 통해 `동수`는 모든 사람에게 연락이 왔다고 확신할 수 있게 된다. 그림으로 보면 아래와 같다.  

![image](https://user-images.githubusercontent.com/64796257/150915088-a523b5bf-afbb-4d72-921f-2a0ce292c0ee.png)

지금까지 설명한 `DFS`의 `핵심 3가지`를 비상연락망에 빗대어 정리하면 다음과 같다.

- `1명`에게만 연락한다.
- 연락할 사람이 `없다`면, 자신에게 연락을 한 사람에게 이를 알린다.
- 처음 연락을 시작한 사람의 위치에서 연락은 끝이 난다.

### 구현모델 

![image](https://user-images.githubusercontent.com/64796257/150915643-74ac42ed-0835-4f6e-9382-6637b35417e8.png)
![image](https://user-images.githubusercontent.com/64796257/150915647-1f53e0d4-ae08-4dcf-81fa-36a7c4cfe610.png)

위 그림에서 보이듯 시작과 동시에 `동수`는 방문 상태이다. 따라서, `동수`를 방문한 상태로 표시해 두었다.  
이어서 다음 단계를 보자. 다음 단계에서는 `지민`이에게 방문한다. 그 결과는 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150915806-a7fb23cf-e663-47aa-b4e9-499b99e4c9f8.png)
![image](https://user-images.githubusercontent.com/64796257/150915808-5edc17e9-e871-4c75-98bb-f0cb5b698676.png)

이렇듯 `방문한 정점`을 `지나갈 때` 지나가는 정점의 정보를 `스택`에 쌓아야 한다.  
따라서, `동수`를 시작으로 `수정`이까지 방문한 결과는 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150916089-941d51dd-86f3-4fa0-b7e4-04d7c08087bc.png)
![image](https://user-images.githubusercontent.com/64796257/150916096-4bf688ec-18c7-4989-8ba4-799f705d1fa0.png)

다음 과정 

![image](https://user-images.githubusercontent.com/64796257/150916144-b61187cc-8cae-42e4-9145-69dd74fbcdb1.png)
![image](https://user-images.githubusercontent.com/64796257/150916151-05bd2619-f8aa-496b-a5ba-ff2ac8fdf701.png)

![image](https://user-images.githubusercontent.com/64796257/150916176-d769f2b2-a703-47fa-80bf-e8fe3b8708a4.png)

이제 `민석`이가 연락할 기회가 왔다. `민석`이는 연락할 대상인 `지율`이가 있다.  
따라서 연락 대상인 `지율`이에게 연락을 취하여 다음 상태가 된다.

![image](https://user-images.githubusercontent.com/64796257/150916227-17523473-62c7-482d-83a4-1b550a2443d8.png)
![image](https://user-images.githubusercontent.com/64796257/150916246-ed5d315f-c0ad-4b2f-84bf-c5cf18027fbd.png)

이제 `동수`에게 돌아가면 된다.  
이 과정은 지금껏 해왔듯이 `연락할 대상이 없으면` 스택의 맨 끝에서 정보를 꺼내서 이동을 하는 과정을 반복하면 된다.

![image](https://user-images.githubusercontent.com/64796257/150916319-7ca78581-b632-434a-b665-6b64a9662453.png)

### DFS의 의사코드 
![image](https://user-images.githubusercontent.com/64796257/153613747-28812bd8-6f28-464b-85b9-d7ff910dc752.png)


## BFS (Breadth First Search; BFS) - 너비 우선 탐색 
`BFS`는 자신에게 연결된 모든 사람에게 연락을 취하는 방식이다. 그림을 보자.

![image](https://user-images.githubusercontent.com/64796257/150916608-ab536afa-a20e-4668-b507-e911acbe873e.png)
![image](https://user-images.githubusercontent.com/64796257/150916609-f535e5d0-595d-42d4-b6a8-db7f606fc851.png)

마찬가지로 `동수`와 `민석`이도 자신에게 연결된 모든 사람들에게 연락을 취한다.  
여기서 누가 먼저 연락을 하느냐는 신경쓰지 않아도 된다. 

물론 누가 먼저 연락을 하느냐에 따라 흐름에는 차이가 날 수 있지만 `BFS 알고리즘`의 관점에서는 문제가 되지 않는다.

따라서, 순서를 다음과 같이 결정하기로 했다. 참고로 이는 어디까지나 BFS의 특징을 `부각`시키기 위한 선택이다.  
“동수가 먼저 주변인에게 연락을 하고 이어서 민석이가 주변인에게 연락을 한다.”

그리하여 다음 그림에서 보이듯이 `동수`가 먼저 주변인에게 연락한다.  
![image](https://user-images.githubusercontent.com/64796257/150916715-0d887ffd-6ce1-464c-84fd-526006c5129e.png)
![image](https://user-images.githubusercontent.com/64796257/150916722-79a63c9e-d2d5-47b5-9718-ac7e460ee9ae.png)

그래서 아래와 같이 `민석`이 연락을 취한다.  
![image](https://user-images.githubusercontent.com/64796257/150916773-70b5e3b7-7d6d-4298-9ba9-1ec3645fec29.png)
![image](https://user-images.githubusercontent.com/64796257/150916779-0d109731-dccb-4c27-b5df-a1a4bed00581.png)

`정희`가 `명석`이에게 연락을 취하고 `명석`이 연락할 기회를 가짐으로써 `BFS`는 `끝`난다.  
![image](https://user-images.githubusercontent.com/64796257/150916881-0517c6fa-efee-4f1f-a667-9637a7735aa0.png)

### 구현 모델 

![image](https://user-images.githubusercontent.com/64796257/150916931-19fa129d-ea3a-4f0c-b9ce-69b006727e47.png)
![image](https://user-images.githubusercontent.com/64796257/150916940-4f53be89-fc64-4f8f-a637-5b98b7a33cbf.png)

다음 단계에서 `지율`이에게 연락을 받은 두 사람의 이름이 `큐`에 순서대로 들어간다.
(위에서 얘기한 것처럼 누구에게 먼저 연락했는지는 신경쓰지 않아도 된다. 흐름의 차이만 있을 뿐 결국 똑같다)

![image](https://user-images.githubusercontent.com/64796257/150917145-6141a995-0ac9-4421-b3e4-542367000092.png)
![image](https://user-images.githubusercontent.com/64796257/150917151-1486d44e-253a-4c4b-bda7-5596976a7f6e.png)

이제 큐에서 이름을 하나 꺼낸다. `queue`는 `선입선출`이니까 `동수`의 정보를 먼저 꺼낸다.  
그래서 `동수`와 연결된 `지민`이에게 연락한다. 아래의 그림을 보자.  

![image](https://user-images.githubusercontent.com/64796257/150917214-e51c8b48-3e04-45b4-b766-d811abf0747f.png)
![image](https://user-images.githubusercontent.com/64796257/150917221-1a5dbe58-f565-4c6b-8ae0-2dc89cf306d1.png)

다음 과정에서는 동수 다음에 `민석`이를 꺼냈다. 그래서 `민석`이는 자신과 연결된 `수정`이와 `정희`에게 연락을 취했다.

![image](https://user-images.githubusercontent.com/64796257/150917280-c4c860b9-fc83-46f7-971f-a814dbd942c5.png)
![image](https://user-images.githubusercontent.com/64796257/150917287-b085cab8-e0fd-4cbc-b170-db3d4646c358.png)

![image](https://user-images.githubusercontent.com/64796257/150917315-2817de79-2dc7-4d31-8f4d-a56f6876fe42.png)
![image](https://user-images.githubusercontent.com/64796257/150917320-716da3d1-e8cd-4011-a448-2fec293d7a69.png)

### BFS의 의사코드 
![image](https://user-images.githubusercontent.com/64796257/153613778-81247b98-78e0-4318-af85-b3ecd3b0f759.png)


















