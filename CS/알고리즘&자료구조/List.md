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

### 단순 연결 리스트(Singly LinkedList) 

![image](https://user-images.githubusercontent.com/64796257/150093075-cfa83e78-ca93-4170-88dc-5b1d3104f692.png)

하나의 노드에 대해서

`데이터를 저장할 장소`와 `다른 노드를 가리키기 위한 장소`를 서로 구분한다.

그렇게 정의해놓은 노드가 서로를 연결하면서 하나의 리스트를 만드는 것이 `단순 연결 리스트`이다.


### 이중 연결 리스트(Doubly LinkedList)

### 원형 연결 리스트(Circular LinkedList)























