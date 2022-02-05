## 관련 tip 

### 스택(Stack) 과 큐(Queue) 

- 스택(Stack) : `LIFO(Last-In-First-Out)` = 먼저 저장된 데이터가 나중에 빠져나간다.

![image](https://user-images.githubusercontent.com/64796257/152630674-37b3410b-fbbf-4d8d-8afc-e3e18ca95e8c.png)

- 큐(Queue) : `FIFO(First-In-First-Out)` = 먼저 저장된 데이터는 가장 먼저 빠져나간다. 

![image](https://user-images.githubusercontent.com/64796257/152630686-3ada4a8b-f651-4a6e-b5ca-6a6fa4a95a6f.png)

### `Queue<E>` 인터페이스와 큐(Queue)의 구현
   
`큐` 자료구조를 위한 `Queue<E> 인터페이스`를 대표하는 3가지 메소드는 다음과 같다.
- boolean add(E e) : 넣기
- E remove() : 꺼내기
- E element() : 확인하기

⇒ `remove`는 인스턴스의 참조 값을 반환하면서 해당 인스턴스를 저장소에서 삭제한다.   
    `element`는 인스턴스의 참조 값을 반환하지만 삭제하지는 않는다.

위 3개의 메소드는 꺼낼 인스턴스가 없거나 저장 공간이 부족할 때 예외를 발생시킨다. 

하지만 아래의 3개의 메소드는 동일한 상황에서 `예외`를 `발생시키지 않고` 해당 상황을 알리기 위한 특정 값을 반환한다.
- boolean offer(E e) : 넣기, 넣을 공간이 부족하면 false를 반환
- E poll() : 꺼내기, 꺼낼 대상이 없으면 null 반환
- E peek() : 확인하기, 확인할 대상이 없으면 null 반환

그래서 일반적으로는 offer, poll, peek를 많이 사용한다. 비어있는 상황도 예외가 아닌 프로그램의 정상적인 흐름으로 간주하기 때문이다.  
`Queue<E>`를 구현하는 대표적인 컬렉션 클래스를 다음 예제를 통해서 살펴보자.

![image](https://user-images.githubusercontent.com/64796257/152630756-1d4e3d48-3d37-41d2-94fe-3405bd1e706e.png)
![image](https://user-images.githubusercontent.com/64796257/152630759-55949176-25ce-4b12-b75f-c973a8b26fc0.png)

### 스택(Stack)의 구현 

스택 자료구조는 컬렉션 클래스 `Stack<E>`를 통해 지원하고 있다.
``` java
public class Stack<E> extends Vector<E>
```

그러나 `Stack<E>`는 자바 초기에 정의된 클래스로써 지금은 이전 코드와의 호환성 유지를 위해 존재하는 클래스일 뿐이다.  

`Stack<E>`는 동기화된 클래스로 `멀티 쓰레드`에는 `안전`하지만, 그만큼 `성능의 저하`가 발생한다. 

때문에 `스택 클래스`를 사용하는 것을 권하지 않는다.  
대신에 자바 6부터 스택을 대신할 `덱(Deque)`라는 자료구조가 포함되었다. 이를 위해 다음 인터페이스를 정의하였다.
``` java 
public class Deque<E> extends Queue<E>
```

덱은 외형 구조가 `큐`와 유사하다.  
`큐`는 `한쪽` 방향으로만 넣고 꺼내는 방식이지만 `덱`은 `양쪽` 끝에서 넣고 빼는 것이 가능한 자료구조다.  

따라서 `덱`은 `스택`처럼 사용하는 것이 가능하다.(물론 덱을 `큐`처럼 사용하는 것도 가능하다)

![image](https://user-images.githubusercontent.com/64796257/152630832-c8a45de6-95c0-4858-9bcc-6a02862a6d15.png)

`Deque<E>`의 대표적인 메소드는 다음과 같다.

1) 앞에서 넣고, 꺼내고, 확인하기 
- void addFirst(E e) : 넣기
- E removeFirst() : 꺼내기
- E getFirst() : 확인하기 

2) 뒤에서 넣고, 꺼내고, 확인하기
- void addLast(E e) : 넣기
- E removeLast() : 꺼내기
- E getLast() : 확인하기

하지만, 위 메소드들은 꺼낼 대상이 없거나 공간이 부족하면 `예외`를 `발생`시킨다.  
아래의 메소드들은 동일한 상황에서 예외를 발생시키지 않고 특정 값을 반환한다.

1) 앞에서 넣고, 꺼내고, 확인하기 
- boolean offerFirst : 넣기, 공간이 부족하면 false 반환
- E pollFirst() : 꺼내기, 꺼낼 대상이 없으면 Null 반환
- E peekFirst() : 확인하기, 대상이 없으면 Null 반환

2) 뒤에서 넣고, 꺼내고, 확인하기
- boolean offerLast : 넣기, 공간이 부족하면 false 반환
- E pollLast() : 꺼내기, 꺼낼 대상이 없으면 Null 반환
- E peekLast() : 확인하기, 대상이 없으면 Null 반환

`스택`이 필요하면 `Deque<E>`을 구현한 컬렉션 클래스의 인스턴스를 대상으로 다음과 같이 쌍을 이루어서 메소드들 호출하면 된다. 
- offerFirst & pollFirst : 앞으로 넣고 앞에서 꺼내기
- offerLast & pollLast : 뒤로 넣고 뒤에서 꺼내기

다음 예제를 통해 `Deque<E>`를 구현하는 `ArrayDeque<E>` 클래스의 인스턴스를 스택처럼 활용하는 예시를 살펴보자.

![image](https://user-images.githubusercontent.com/64796257/152630884-ee22aabb-21a6-491e-9c0b-cf79c06811a3.png)
![image](https://user-images.githubusercontent.com/64796257/152630888-3fbc1f65-11e2-4922-ad58-d87d69850909.png)

여기서 
``` java
Deque<String> deq = new ArrayDeque<>();(배열을 기반으로 덱 구성)
```
부분은 

``` java
Deque<String> deq = new LInkedList<>();(리스트를 기반으로 덱 구성) 
```

로 대체할 수 있다.       

왜냐하면, `LinkedList<E>` 클래스는 `Deque<E>`, `List<E>`, `Queue<E>`를 모두 구현하기 때문이다. 

따라서, 어느 타입의 참조변수로 참조하느냐에 따라서 `LinkedList<E>`는 그 성격이 결정된다. 

스택에 대한 내용을 덧붙이면 스택의 두 기능이 있다. 넣기는 pull, 꺼내기는 pop이라 한다.  
그런데 앞서 보인 예제에서는 덱을 스택처럼 사용했기 때문에 코드상에서 이것이 덱인지 스택인지 구분하기 어렵다.  
그래서 스택으로 사용하려 했는데 앞으로 넣고 뒤로 꺼내는 실수를 할 수 있다.

따라서 스택이 필요한 경우에는 다음과 같이 별도의 클래스를 정의하여 사용할 것을 권한다.
![image](https://user-images.githubusercontent.com/64796257/152630931-04b24aa1-0394-4148-a8a8-e043e28bc330.png)
![image](https://user-images.githubusercontent.com/64796257/152630932-fd043efc-b86a-47d3-97d0-f87900f87d14.png)

`빨간 상자`를 통해 인터페이스를 `선언`하고

`주황 상자`를 통해서 `덱`을 이용해서 `스택`을 구현하기 때문에 표현상 헷갈리지 않게   
생성자와 push와 pop 메소드라는 이름으로 `넣기`, `꺼내기` 동작을 모두 구현했다. 






