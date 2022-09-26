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

### 주요 연산 

![image](https://user-images.githubusercontent.com/64796257/192213950-49615721-6b83-4b75-9796-654e0dc57463.png)

- make-set(u) : u라는 원소 하나를 갖는 `서로소 집합 하나`를 만든다. 

```
def make-set(u) : 
  p[u] <- u
```


- find-set(u) : `u라는 원소를 포함`하는 있는 `root node값`을 return 
⇒ root node를 반환하는 이유는 `root node`가 `집합을 대표`하는 노드이기 때문이다.  
⇒ 함수의 동작을 보면 `아래`에서부터 `위`로 `한칸` 씩 올라가면서 `원하는 값`이 있는지 찾는다.  
⇒ `원하는 값`을 찾는다면 `해당 값의 root node` 값을 반환한다. 
``` 
def find-set(u) : 
  if u is p[u] return u; 
  
  else return find-set(p[u])
```

- union(u, v) : `u를 가진 집합`과 `v를 가진 집합`을 `합치`는 함수

⇒ 이때, `v의 부모노드`가 `u`가 되도록 합친다. `v`가 `u를 가리키도록` 합친다.  
⇒ 그래서 아래와 같이 코드를 짤 수 있다. 

```
def union(u, v) : 
  p[find-set(v)] <- find-set(u)
```

### 복잡도 

- 공간복잡도 : `n개의 원소를 저장하는 배열`을 사용하기 때문에 `Θ(n)`의 공간복잡도를 가진다. 
- 시간복잡도 

![image](https://user-images.githubusercontent.com/64796257/192223708-545c1a3c-7cc0-4f90-8237-3a264b9d3650.png)

`find-set`과 `union 함수`는 `트리의 높이`에 따라 시간복잡도가 달라질 수 있다.  
높이가 커지면 그만큼 연산이 오래걸리게 되는 구조이기 때문이다.

그래서 트리의 노드개수가 n개일 때 최악의 경우 트리의 높이가 n이 될 수 있다.   
그렇다면 최악의 경우 `Θ(n)`의 시간복잡도를 가진다. 

## 어떻게 효율성을 높일 수 있을까?? 

⇒ 즉, 어떻게 하면 `트리의 높이`를 줄일 수 있을까?   
⇒ `트리의 높이`를 줄인다면 find-set, union 함수의 시간복잡도를 많이 단축시킬 수 있다. 

### 1. Union by rank 

- 트리의 높이는 언제 증가될까?? 

ex) S_u : u라는 원소가 포함된 집합 

S_u와 S_v를 union하는 상황.  



























