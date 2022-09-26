## Disjoint-Set(서로소 집합) 

: `서로 겹치지 않는 집합`(non-overlapping set)을 다룰 때 사용하는 자료구조 ⇒ 이러한 자료구조를 `union-find`라고도 부른다. 

- ADT  
1) make-set(u) : u라는 원소를 포함하는 `새로운 집합`을 만드는 함수
2) find-set(u) : u라는 원소를 포함하는 집합을 `반환`하는 함수
3) union(u, v) : `u를 가진 집합`과 `v를 가진 집합`을 `합치`는 함수

- 표현방법 

1) parent pointer tree 

![image](https://user-images.githubusercontent.com/64796257/192210330-7d928f9a-bff2-41c1-96ae-b8c4cfa4be6d.png)

왼쪽과 같이 `자식 노드`가 `부모 노드`를 `가리키는` 트리를 parent pointer tree라 한다.   
이때, root node는 자기 자신을 가리킨다. root node 자체가 집합을 나타내기 때문이다. 

2) 벤 다이어그램 

![image](https://user-images.githubusercontent.com/64796257/192210579-a17ac0dd-7c89-4463-a346-60b17064861b.png)
![image](https://user-images.githubusercontent.com/64796257/192210586-9b91428d-e912-4b8a-acac-20f0293c075b.png)

3) 1차원 배열 - 이름을 p라 하자. 

![image](https://user-images.githubusercontent.com/64796257/192210632-302f91d1-8e69-4114-8365-b78d21919848.png)

- index값 = node의 key값 
- 배열의 값(value) = 가리키고 있는 부모 노드의 키 값

즉, index값에 해당하는 노드가 가리키는 부모 노드의 값을 value로 가지고 있다. 

ex) `index = 2`의 `value는 3`이다. ⇒ `2번 노드`는 부모노드로 `3번 노드`를 가리키고 있는 걸 나타낸다.


