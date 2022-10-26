[출처](https://velog.io/@haero_kim/LRU-Cache-이해하기)

### 캐시(Cache) 

[관련 내용](https://github.com/sponbob-pat/TIL/blob/main/CS/Network/chapter5.md#53-%EB%A6%AC%EC%86%8C%EC%8A%A4%EB%A5%BC-%EB%B3%B4%EA%B4%80%ED%95%98%EB%8A%94-%EC%BA%90%EC%8B%9Ccache)

### LRU(Least Recently Used) 

OS의 페이지 교체 알고리즘 중 하나. 페이지를 교체할 때 `가장 오랫동안 사용되지 않은` 페이지를 우선적으로 교체하는 걸 말한다.

### LRU Cache 

```
캐시에 공간이 부족할 때 가장 오랫동안 사용하지 않은 캐시 데이터를 제거해서 새로운 데이터로 배치하는 걸 말한다.
```
이 방식을 택하면 `cache hit`의 비율을 높게 유지할 수 있고 지금까지 많이 사용되는 알고리즘이다. 

#### 구현 방식 

`Double Linked List`를 통해 구현된다. 

- `head`에 가까운 데이터 ⇒ 최근 데이터
- `tail`에 가까운 데이터 ⇒ 오래된 데이터

따라서, 새로운 데이터를 삽입할 때 `tail`이 가리키는 변수를 `삭제`하고 `head`에 데이터를 삽입하도록 한다.  
이 과정에 소요되는 시간복잡도는 `O(1)`이다. 
