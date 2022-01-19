### 스택(stack)의 이해
스택은 한쪽은 막혀있고 다른 한쪽이 뚫려있는 초코볼 통에 비유할 수 있다.  
그래서 `먼저 들어간 것이 나중에 나오는` 구조를 가지고 있다. 이는 `LIFO(Last-In-First-Out) or 후입선출`이라 한다.

예를 들어, 검정색 -> 노란색 -> 빨간색 -> 주황색 순으로 초코볼을 넣었다고 하면...   
꺼내는 순서는 들어간 순서의 역순이 된다. “주황색 -> 빨간색 -> 노란색 -> 검정색” 

![image](https://user-images.githubusercontent.com/64796257/150016911-2ffa3705-b12b-4058-ba29-c2689dc6f2e0.png)

### 스택 ADT의 정의
앞서 이야기한 스택의 성질을 통해서 ADT를 정의하면 다음과 같다.
- 초코볼 통에 초코볼을 넣는다. ⇒ push
- 초코볼 통에서 초코볼을 꺼낸다. ⇒ pull
- 이번에 꺼낼 초코볼의 색이 무엇인지 통 안을 들여다 본다. ⇒ peek

말 그대로 스택에 자료를 `넣고`, `빼고`, `들여다 보는` 연산을 각각 push, pull, peek이라 한다.

### 배열을 이용한 스택 

구현에 앞서 각 함수들이 어떤 흐름으로 동작하는지 그림으로 그려보자.

![image](https://user-images.githubusercontent.com/64796257/150040322-8f1751f8-ab6b-4bd4-86bc-a77df4b95910.png)

여기서 주목할 점은 두 가지이다.
- `스택의 바닥`에 해당하는 자료의 index값을 0으로 정의했다.
- 마지막에 저장된 데이터의 위치를 Top이라는 변수를 통해 가리킨다.

index가 0인 값을 `스택의 바닥`로 정의하면 배열의 길이에 관계없이 언제나 index가 0인 값은 스택의 바닥이 됨.

마지막에 저장된 데이터의 위치를 Top이라는 변수로 가리키고 해당 인덱스 값을 변수 topIndex에 저장했다.

⇒ 그렇기 때문에 스택이 비어있다면(empty) top의 값이 –1이고 스택이 꽉 차있다면(full) top의 값은 (전체 배열의 길이) - 1이 된다.

이를 통해서 push, pop이 해야하는 일은 다음과 같이 말할 수 있다.
- push : Top을 위로 한 칸 올리고, Top이 가리키는 위치에 데이터를 저장
- pop : Top이 가리키는 데이터를 반환하고, Top을 아래로 한 칸 내린다.

- 코드 구현

``` java
package free;

import java.util.NoSuchElementException;

public class StackArray {
	
	int[] data; // 데이터를 저장할 배열
	int top;  // 스택의 맨 위의 index
	int size; // 지정해놓은 배열의 크기
	
	public StackArray(int size) { // size 매개변수 크기만큼의 스택 생성
		data = new int[size];
		top = -1;
		this.size = size;
	}
	
	public boolean empty() { // 비어있는지 여부 확인
		return top == -1;
	}
	
	public boolean full() { // 꽉 차있는지 여부 확인
		return top == size - 1; 
	}
	
	public int size() { // 현재 스택에 담겨있는 데이터의 개수
		return top + 1;
	}
	
	public void push(int value) { // 맨 위에 항목을 삽입한다.
		if(full()) {
			throw new NoSuchElementException("Stack is full!");
		}
		// 기존의 top의 값을 1 더한다.
		// 그렇게 변경된 top의 값을 가진 index에 해당하는 배열에 데이터를 추가한다. 
		data[++top] = value;
	}
	
	public int pop() { // 맨 위에 있는 항목을 삭제하고 반환한다.
		if(empty()) {
			throw new NoSuchElementException("Stack is empty!");
		}
		// 현재 top이 가리키는 값을 return해주고
		// top의 값은 1 감소시킨다.
		return data[top--];
	}
	
	public int peek() { // 맨 위에 있는 항목을 반환한다.(삭제는 안함)
		if(empty()) {
			throw new NoSuchElementException("Stack is empty!");
		}
		// 현재 top이 가리키는 값을 return한다.
		return data[top];
	}
}
```

### 연결리스트를 이용한 스택 

ADT는 동일하다. 구현방식에 있어 차이가 있다.

- 연결 리스트 기반 스택의 논리

그림을 그리면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150045080-db43aaf5-644d-4e1a-8005-bc4825e2337c.png)

자바에는 `포인터`라는 개념이 없다. 그래서 `가리킨다`라는 표현보다는 변수에 `저장한다`라는 개념이 더 적합하다고 생각한다.  
하지만, 연결 리스트를 표현함에 있어서 `가리킨다`라는 말이 좀 더 잘 와닿기 때문에 변수에 `저장한다`라는 말 대신  
`가리킨다`라는 표현을 계속해서 사용할 예정이다.

- 노드 정의 

``` java
class Node {

        int data;
        Node next;

        public Node() {
            this(0);
        }

        public Node(int data) {
            this(data, null);
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
```

- 초기화, empty 메서드 

``` java
public class StackLinkedList { 
  
  ... Node 정의 ... 
  
  Node top; // 맨 앞에 있는 노드를 가리킬 변수
	
	public StackLinkedList() {
		top = null; // 일단 top은 null을 가리키면서 스택이 시작
	}
	
	public boolean empty() {
		return top == null; // top이 null이라면 비어있는 거다.
	}
  
  ... 
}
```

- push 함수 

![image](https://user-images.githubusercontent.com/64796257/150044153-7f665246-1e6c-4982-a9d7-8dbcfab1ecff.png)

``` java
	public void push(int value) {
		if(empty()) { // 스택이 비어있으면
			top = new Node(value); // 새로운 노드를 생성해서 top이 가리키도록 한다.
		}
		else { // 스택이 비어있지 않다면
			Node p = new Node(value); // 새로운 노드를 생성해서 p가 가리키도록 함
			p.next = top; // 지금 top이 가리키는 노드를 p의 next가 가리키도록 한다.
			top = p; // 그러고 나서 top은 p가 가라키고 있는 노드를 가리킨다.
		}
	}
```



























