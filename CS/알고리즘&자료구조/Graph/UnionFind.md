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

즉, index값에 해당하는 노드가 가리키는 부모 노드의 값을 value로 가지고 있다. ⇒ `index`가 `value`를 가리킨다.

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

S_u와 S_v를 union하는 상황. 이 상황은 아래와 같이 2가지 경우로 나눌 수 있다. 

1) S_v의 루트 노드가 S_u의 루트 노드를 가리키는 상황

![image](https://user-images.githubusercontent.com/64796257/192262922-9116a838-c7a0-4809-a0d5-683e63117e68.png)

2) S_u의 루트 노드가 S_v의 루트 노드를 가리키는 상황

![image](https://user-images.githubusercontent.com/64796257/192262935-f65f486e-e021-434b-b60f-216de2af5a75.png)

1)의 상황과 같이 `높이가 큰 트리의 루트 노드`가 `높이가 작은 트리의 루트 노드`를 가리키면 `높이가 증가`한다. 

반면, 2)의 상황과 같이 `높이가 작은 트리의 루트 노드`가 `높이가 큰 트리의 루트 노드`를 가리키면 `높이가 유지`되는 걸 확인할 수 있다. 

그렇다고 매번 union 할 때마다 트리의 높이를 측정하는 건 비효율적이기 때문에 각각의 노드에 `rank`를 저장하도록 했다. 

```
노드 u의 rank = 노드 u가 갖고 있는 서브트리의 높이값 
```

이를 통해 psuedo-code를 작성하면 아래와 같다. 

```

// u_r = u 노드를 갖고 있는 집합의 루트 노드
// v_r = v 노드를 갖고 있는 집합의 루트 노드

def union(u, v) : 
  u_r <- find-set(u) 
  v_r <- find-set(v) 
  
  if(rank[u_r] > rank[v_r]) # u노드 트리가 v노드 트리보다 높이가 크다
    p[v_r] <- u_r # 그래서 v노드 트리가 u 노드 트리를 가리키도록 한다. 
  
  else :
    p[u_r] <- v_r # 그렇지 않으면 반대로 설정 
    
    # 두 노드 트리의 높이가 똑같다면 
    # u또는 v를 가지고 있는 트리에 붙이면서 높이가 1 증가한다. 
    if(rank[u_r] == rank[v_r]) rank[v_r] <- rank[v_r] + 1 
  
```

예시 

![image](https://user-images.githubusercontent.com/64796257/192264112-1e502f7c-71f3-4722-9566-8fab67b8e5af.png)

### 2. Path Compression 

union-by-rank 방식을 봤을 때... `트리의 높이`가 `동일`하다면 전체 트리의 높이가 늘어날 수 있다. 

이 경우에 대해서도 트리의 높이를 최대한 줄일 수 있는 방법이 `Path Compression`이다. 

이전에 `find-set`을 하기 위해서는 해당 노드로부터 시작해서 하나씩 올라가면서 `search` 했어야 했는데  
여기서는 곧바로 `root node`를 찾도록 코드를 작성할 것이다. 

그렇게 되면 아래와 같이 루트 노드를 찾을 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/192264953-0a7d9660-9b8d-4167-b282-434f9ad672d5.png)

어떻게 이게 가능한지 살펴보자. 

- 이전에 짰던 find-set 함수  
``` 
def find-set(u) : 
  if u is p[u] : 
      return u
  else : 
      return find-set(p[u]) 
```

u가 root node라면 u를 반환하고 / 아니면 위로 한 칸 올라가서 해당 노드가 root-node인지 파악하는 방식 

⇒ 즉, 1칸, 1칸 올라가면서 root node인지 파악하는 방식으로 동작한다. 

- path compression 방식 

``` 
def find-set(u) : 
    if(p[u] != u) : 
        // p[u] = v => u가 v를 가리킨다. 
        
        // find-set(p[u]) : u가 가리키는 부모노드의 루트 노드 
        // 즉, u가 곧바로 루트 노드를 가리키도록 설정
        p[u] <- find-set(p[u]) 
    
    return p[u] 
```

구체적인 동작 방식은 아래와 같다. 

![image](https://user-images.githubusercontent.com/64796257/192265891-93db8537-e16b-4fc2-8aea-ac6a63becc52.png)

### 복잡도 

#### 1. union-by-rank를 사용할 때, root 노드의 rank값 = k ⇒ 트리의 총 노드 개수는 최소 $2^k$개다. 

- 증명 

1) rank = 0 ⇒ $2^0$ = 1개의 노드만 있는 트리가 생성됨
2) rank = r ⇒ $2^r$ 개의 노드를 가진다고 가정 

그때 rank값이 r+1이 될 때 노드의 개수가 최소  $2^{r+1}$ 개가 되는지 확인하자.

`rank값이 1 증가`할 때는 `똑같은 rank의 두 트리`가 `합`쳐질 때다. 

방금 전 가정에서 rank = r ⇒ $2^r$ 개의 노드라고 했다. 

따라서, rank = r+1일 때의 노드 개수는 rank = r인 트리 2개를 합치면 된다.   
즉, $2^r + 2^r = 2^{r+1}$이 되면서 1번 정리는 성립한다는 걸 알 수 있다. 

#### 2. union-by-rank를 사용할 때, 집합의 노드 개수 = n개 ⇒ 집합의 root node의 rank = O(logn)이 된다. 

1번 정리에서 `root 노드의 rank값 = k ⇒ 트리의 총 노드 개수는 최소 2^k개`라고 했다. 

여기서 $2^k$ 은 `노드의 총 개수`의 `최소값`이기 때문에 $n ≥ 2^k$ 라는 부등식을 만들 수 있다. 

따라서, `n ≥ 2^k` ⇒ `log₂n ≥ k`가 성립하므로 root node의 rank는 `O(logn)`이 된다.

그래서, find-set, union 각각의 시간복잡도는 `높이`와 비례하기 때문에 `Θ(logn)`이 된다. 

#### 3. Path Compression 

앞서 make-set, find-set, union으로 이뤄진 `m개의 연산`을 하고 make-set은 `n번` 연산할 때 시간복잡도는 `O(mlog* n)`이 된다. 

- log* n의 뜻 

![image](https://user-images.githubusercontent.com/64796257/192268219-023ddc11-2117-4a01-8a07-e723c572c795.png)

`log* n`은 n의 값을 굉장히 작게 만들어주기 때문에 무시해도 무방하다. 그래서 `O(m)`이라 해도 된다. 

## Union-find를 이용해서 Cycle 찾기 

ex) `3번 노드 -> 2번 노드` 방향으로 연결하는 상황  
![image](https://user-images.githubusercontent.com/64796257/192268719-9eeabeab-b127-4c2d-9dfa-e288f8982119.png)

연결하기 전에 두 노드의 `root node`를 비교한다. 

두 노드는 애초에 같은 집합에 있던 노드이기 때문에 두 노드의 root node는 `0번 노드`로 동일하다. 

이를 통해서 두 노드를 연결하게 되면 `cycle`이 생긴다는 걸 파악할 수 있다. 











