### 리스트의 이해 
: `list`라는 자료구조는 구현방법에 따라서 크게 2가지로 나뉜다. 하지만 구현방법의 차이만 있을 뿐 둘의 ADT는 동일하다.  
물론 각각의 특성에 있어 차이가 있기 때문에 ADT에 차이를 두기도 한다.

1. 순차 리스트(ArrayList) : `배열`을 기반으로 구현된 리스트. 구현이 간단하지만 저장할 수 있는 item의 개수가 제한된다.
		
2. 연결 리스트(LinkedList) : `Node(노드)`를 기반으로 구현된 리스트. 크기가 제한되지는 않지만 세심하게 구현할 필요가 있다.

리스트의 ADT 정의를 위해서 리스트 자료구조의 가장 기본적이고 중요한 특성은 다음과 같다.

“리스트 자료구조는 데이터를 나란히 저장한다. 그리고 중복된 데이터의 저장을 막지 않는다.”

⇒ 이러한 특성을 기반으로 ADT를 정의하면 아래와 같다. 

## 순차 리스트(ArrayList) 

- 주요 연산 및 부가 연산

![image](https://user-images.githubusercontent.com/64796257/150050256-a4fcd563-33ee-445d-9e36-3366a633bb9a.png)

강의에서 다룰 배열을 이용한 리스트(ArrayList)의 전체적인 구조는 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/150083685-18db32f7-f184-45eb-a3f7-69b2c834b95e.png)

data라는 이름의 배열로 리스트의 자료들을 저장한다. length는 int형 변수로 리스트의 마지막 item의 다음 인덱스를 나타낸다.  
여기서는 MLS 대신에 매개변수로 원하는 만큼 리스트의 size값을 받도록 했다. 

즉, size 변수가 MLS의 역할을 대신한다.


기본적인 변수와 생성자, empty, full 은 아래와 같이 정의할 수 있다.

``` java
package free;

public class ListArray {
	
	int[] data;
	int length;
	int size;
	
	public ListArray(int size) {
		data = new int[size]; // 전달받은 size 만큼의 배열 생성
		
		length = 0; 	  // 리스트가 비어있으니까 length는 0에서 시작
		this.size = size; // size값 저장. 
				      
	}
	
	public boolean empty() {
		return length == 0; // length가 0이라는 건 배열에 아무것도 없다는 것
	}
	
	public boolean full() {
		return length == size; // 배열의 크기와 length가 동일하면 꽉 찬 것
	}
	
	
}
```

- empty / full

![image](https://user-images.githubusercontent.com/64796257/150084227-bea09a0a-641e-458b-b3e7-0b1f7530fedd.png)

empty라는 건 데이터가 없는 걸 의미하니까 length 값이 0이 된다.

full이라는 건 데이터가 꽉 찼다는 걸 의미하니까 length는 배열의 길이인 size와 동일하다.

- insert 

![image](https://user-images.githubusercontent.com/64796257/150084299-81485bcf-0f08-4a4b-af96-ea2a732b3c0e.png)

insert(2, N)이라고 하면

index = 2 위치에 있는 배열에 `N`이라는 데이터를 넣는다는 것을 의미한다.

그렇게 하기 위해서는 위 그림과 같이 기존에 있던 [2] 자리부터 한 칸씩 자리를 옮기고 나서 N을 넣어야 한다.

이를 구현하면 아래와 같다. 

``` java
public void insert(int pos, int item) {

	// 리스트가 꽉 찼다면 에러 메시지 출력
	if(full()) throw new NoSuchElementException("List is full!");
	
	// 삽입하려는 범위를 벗어난다면 에러 메시지 출력
	if(pos < 0 || pos > length) {
		throw new NoSuchElementException("Error : out-of-index");
	}
		
	// 원하는 자리에 데이터를 삽입하기 위해서 
	// 데이터를 한 칸씩 오른쪽으로 이동한다.
	for(int i = length; i > pos; i--) {
		data[i] = data[i-1];
	}
		
	// 이동을 완료하고 나서 원하는 자리에 원하는 데이터를 넣는다.
	data[pos] = item;
	
	// length를 1 증가시킨다.
	length++;
}
```

- remove 

![image](https://user-images.githubusercontent.com/64796257/150085362-c973083f-7dda-43b6-8fb8-3dc76e517560.png)

remove(2)라고 하면 index = 2에 위치하는 데이터 값을 제거하기 위해서 index = 2부터 앞에 있는 값들을 한 칸씩 앞으로 땡겨오면 된다.

이를 구현하면 아래와 같다. 

``` java
public void remove(int pos) {

	// 리스트가 비어있다면 에러 메시지 출력
	if(empty()) throw new NoSuchElementException("List is empty!");
		
	// 삭제 범위를 넘어선다면 에러 메시지 출력
	if(pos < 0 || pos > length) {
		throw new NoSuchElementException("Error : out-of-index");
	}
	
	// 삭제를 원하는 index 부터 배열에 있는 값들을 한 칸씩 앞으로 땡겨온다.
	// pos = 2이면 data[2]부터 배열에 있는 값들을 하나씩 앞으로 땡겨온다.
	
	// 이렇게 하면 기존에 data[2]에 있던 값은 자연스럽게 삭제된다.
	for(int i = pos + 1; i < length; i++) {
		data[i-1] = data[i];
	}
	
	// 그렇게 하고 나서 length를 1 감소시킨다.
	length--;
}
```

- 기타 등등 
![image](https://user-images.githubusercontent.com/64796257/150086182-879b0c45-cd65-4d91-9d9d-932cb2914921.png)

``` java
public int get(int pos) { return data[pos]; } // 원하는 위치(pos)에 있는 데이터 반환
	
public int size() { return length; } // 현재 리스트에 있는 자료 개수 반환
	
public int find(int item) { // 매개변수로 전달한 item이 위치한 배열의 index를 찾아서 반환
	for(int i = 0; i < length; i++) {
		if(data[i] == item) return i;
	}
		
	throw new NoSuchElementException("Error : Cannot find the item");
}
	
public void replace(int pos, int item) { // 원하는 위치(pos)에 원하는 값(item)을 넣는 동작
	if(pos < 0 || pos >= length) {
		throw new NoSuchElementException("Error : out-of-Index");
	}
		
	data[pos] = item;
}
```

- 분석 

![image](https://user-images.githubusercontent.com/64796257/150087172-ff3ea9b4-c25b-4d6f-93f3-045d2f2fb2d2.png)
![image](https://user-images.githubusercontent.com/64796257/150087179-702ab23a-d00b-498c-85ad-85bef1992a8a.png)

## 연결 리스트(LinkedList)


자바에는 `포인터`라는 개념이 없다. 그래서 `가리킨다`라는 표현보다는 변수에 `저장한다`라는 개념이 더 적합하다고 생각한다.  
하지만, 연결 리스트를 표현함에 있어서 `가리킨다`라는 말이 좀 더 잘 와닿기 때문에 변수에 `저장한다`라는 말 대신  
`가리킨다`라는 표현을 계속해서 사용하겠다.

### 단순 연결 리스트(Singly LinkedList) 

![image](https://user-images.githubusercontent.com/64796257/150093075-cfa83e78-ca93-4170-88dc-5b1d3104f692.png)

하나의 노드에 대해서

`데이터를 저장할 장소`와 `다른 노드를 가리키기 위한 장소`를 서로 구분한다.

그렇게 정의해놓은 노드가 서로를 연결하면서 하나의 리스트를 만드는 것이 `단순 연결 리스트`이다.

- 기본 요소 : 노드 

``` java
public class SinglyLinkedList {

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
	
	Node head; // 맨 앞에 있는 노드를 가리킬 변수
	int cnt; // 리스트의 원소 개수
	
	public SinglyLinkedList() { // 생성자 
		head = null; // head가 null을 가리키도록 하고
		cnt = 0;     // 개수는 0개로 초기화한다.
	}
	
	Node getHead() { return head; } // head가 가리키는 노드를 반환
	
	Node getNode(int pos) { // pos번째에 있는 Node를 반환해준다.
				// pos=2라면 2번째에 있는 Node를 반환한다.
		Node p = head;
		
		for(int i = 0; i < pos; i++) {
			if(p == null) break; // p가 가리키는 게 null 이 아닌이상 
			p = p.next; // 계속해서 한 칸씩 다음 노드로 p를 이동한다.
		}
		return p;
	}
}
```

- insert 

ex) insert(2, 100); == 2번째 자리에 '100' 이라는 데이터를 삽입하고 싶다.

![image](https://user-images.githubusercontent.com/64796257/150096260-60b2499a-0de8-495e-89c5-6079c2993157.png)

``` java
public void insert(int pos, int data) {
		
	// 삽입하고자 하는 위치인 pos가 범위를 넘어서면 이에 대한 오류 메시지를 출력
	if(!(pos >= 0 && pos <= cnt)) {
		throw new NoSuchElementException("Error : out-of-range");
	}
		
	// pos 앞에 있는 노드를 prev라고 했다.
	// pos = 2라고 했을 때
	// 삽입을 하고 나면 기존에 2번째에 있던 노드가 
	// 새로 삽입한 노드 앞에 위치해야 한다.
	Node prev = this.getNode(pos);
	
	if(prev != null) {
		Node p = new Node(data); // 원하는 데이터를 갖는 노드를 생성해서 p가 가리키도록 했다.
		p.next = prev.next;      // p.next는 prev.next가 가리키던 노드를 가리키도록 한다.
		prev.next = p;		 // p는 prev.next가 가리키는 노드가 된다.
		this.cnt++;		 // 개수 하나 증가
	}
}
```

![image](https://user-images.githubusercontent.com/64796257/150100253-b5b8a1f8-098d-4215-95fc-a16281aee7e3.png)

- remove 

ex) remove(2) == 2번째 노드를 삭제하고 싶다.

![image](https://user-images.githubusercontent.com/64796257/150100368-bf81ca4a-1a87-4868-8fca-774b945080de.png)

``` java
public void remove(int pos) {
		
	if(empty() || !(pos >= 0 && pos <= cnt)) {
		throw new NoSuchElementException("Error : out-of-range");
	}
		
	// 삭제할 노드가 pos번째 위치한 노드라서
	// 앞에 있는 노드는 (pos-1)번째 노드여야 한다.
	Node prev = this.getNode(pos - 1); 
		
	if(prev != null) {
		Node removed = prev.next;
		prev.next = removed.next;
		removed = null;
		this.cnt--;
	}
		
}
```

![image](https://user-images.githubusercontent.com/64796257/150103210-ea9ae89d-784c-4fa4-8359-ea446992e05a.png)

- 기타 등등 (find / replace / get)

find : 매개변수로 전달한 데이터가 위치한 index값 반환  
replace : 원하는 위치에 원하는 값을 넣고 싶을 때 사용  
get : 원하는 위치에 있는 데이터 값  

``` java
public int find(int query) {
	int idx = 0;
		
	for(Node p = getHead(); p != null; p = p.next) {
		if(p.data == query) return idx;
		idx++;
	}
	throw new NoSuchElementException("Error : Cannot find the item");
}
	
public void replace(int pos, int data) {
		
	if(empty() || !(pos >= 0 && pos <= cnt)) {
		throw new NoSuchElementException("Error : empty or out-of-range");
	}
		
	Node node = getNode(pos);
	node.data = data;
}
	
public int get(int pos) {
	if(empty() || !(pos >= 0 && pos <= cnt)) {
		throw new NoSuchElementException("Error : out-of-range");
	}
		
	Node node = getNode(pos);
	return node.data;
}	
```

### 이중 연결 리스트(Doubly LinkedList)

![image](https://user-images.githubusercontent.com/64796257/150104981-dea05cd4-b3fc-4071-bb87-549f31d46b24.png)

![image](https://user-images.githubusercontent.com/64796257/150104992-83f3943f-33e5-4235-ab22-db08e00648ed.png)

![image](https://user-images.githubusercontent.com/64796257/150105014-a12f7e4b-f36a-4ec3-ac19-de9e42241ba1.png)

![image](https://user-images.githubusercontent.com/64796257/150105027-84f1a4a6-7460-4e44-a6cd-11d68fa7a7d2.png)

![image](https://user-images.githubusercontent.com/64796257/150105126-b81a26bd-1722-4aa6-96ef-0140bb9c872c.png)

![image](https://user-images.githubusercontent.com/64796257/150105137-f79d8bd6-955a-46f5-9827-04c721b4cf98.png)



### 원형 연결 리스트(Circular LinkedList)

![image](https://user-images.githubusercontent.com/64796257/150104746-a4a33fb1-2aa2-4870-86f7-8dd6639c51ce.png)

![image](https://user-images.githubusercontent.com/64796257/150104755-95d75d1c-f6cb-4d9d-b9bf-f4b7c704986b.png)

remove_rear가 효율적이지 못한 이유는

각각의 노드들은 그 노드의 다음 노드만을 가리키기 때문에   
한 노드의 이전 노드에 접근하기 위해서는 처음부터 이전 노드까지 탐색을 해야만 접근할 수 있는 구조를 가졌기 때문이다. 

이렇게 처음부터 이전 노드까지 탐색을 하는 과정에서 많은 비용이 발생한다.





















