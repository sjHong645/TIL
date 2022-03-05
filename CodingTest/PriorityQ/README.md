### 우선순위 큐(Priority Queue) 

⇒ 우선순위를 가진 데이터를 저장해서 우선순위가 높은 데이터를 먼저 나가게 하는 자료구조

우선순위 큐의 핵심 연산은 큐와 마찬가지로 2가지 `enqueue`와 `dequeue`가 있다. 다만, 연산의 결과에 있어서 큐와 차이가 있다.

큐는 연산의 결과로 먼저 들어간 데이터가 먼저 나오지만(FIFO), 

우선순위 큐의 연산결과는 들어간 순서에 상관없이 `우선순위가 높은 데이터`가 먼저 나온다. 

그래서 `큐`는 줄서기에 비유한다면... `우선순위 큐`는 응급상황에 비유할 수 있겠다.  
응급상황에서는 더 위급한 환자를 먼저 치료하는 우선순위가 있듯이 말이다. 이렇듯 우선순위 큐에서 중요한 것은 `우선순위`이다.

![image](https://user-images.githubusercontent.com/64796257/150444318-1eaab382-5530-481d-8776-743281d9afd1.png)

우선순위 큐는 `배열`을 이용해서 구현할 수 있고 `연결 리스트`를 이용해서 구현할 수도 있다.

하지만, `힙(heap)`을 이용할 경우 insert와 remove를 둘 다 효율적으로 사용할 수 있다.

그 중에서 `이진 힙(Binary heap)`을 기반으로한 우선순위 큐에 대해서 알아보려고 한다.

### 이진 힙(Binary heap)을 이용한 우선순위 큐 연산
일단, `힙(heap)`에 대해서 먼저 알아야 한다.
- 힙(Heap) : heap property를 만족하는 `완전 이진 트리`를 말함 (binary heap) ⇒ 여기서는 key값을 우선순위 값(priority)와 동일시했다.

1) heap property ==> 강의에서는 `최대 우선순위 큐(Max Heap)` 대해서 중점적으로 다룬다.

![image](https://user-images.githubusercontent.com/64796257/150444479-243e3c2c-ca13-4671-a699-9cf2700fe217.png)
![image](https://user-images.githubusercontent.com/64796257/150444483-24b8d74b-9313-4746-b835-2bb4084f9260.png)

Max Heap은 간단히 말하면 우선순위 값(key 값)이 커질수록 우선순위가 높다고 여기는 힙을 의미한다.

2) 완전 이진 트리(Complete Binary Tree) 

완전 이진 트리의 조건은 다음과 같다. 트리의 높이가 h라고 할 때 
- 레벨 1 ~ h-1 까지는 모두 노드가 채워져 있어야 한다.
- 레벨 h(맨 아래 레벨)에서는 노드가 왼쪽에서 부터 오른쪽으로 순서대로 채워져야 한다.

![image](https://user-images.githubusercontent.com/64796257/150444740-e35fd558-7300-4999-b893-3c3de9d614cd.png)

이와 같이 완전 이진 트리는 중간에 비어 있는 요소가 없기 때문에 배열로 구현하는 것이 더 좋다. 간단한 구현을 위해 root의 index는 1로 할당한다.

![image](https://user-images.githubusercontent.com/64796257/150444773-4aebad22-5a03-4112-b4d3-9c8f20b9f845.png)

- 기본 구성(클래스 이름은 MaxHeap)
``` java
import java.util.NoSuchElementException;

public class MaxHeap {
	
	static final int HEAP_SIZE = 200; // 힙의 크기를 200개로 설정
	static final int root_index = 1;  // root를 나타내는 index는 1로 설정
	
	private int[] nodes = new int[HEAP_SIZE]; // 데이터를 저장할 배열
	private int size;
	
  // 이진 트리의 성질을 이용해서 만들어낸 메소드 
  // java에서도 나눗셈 결과에 나머지가 있을 때 소수점 부분을 자동으로 절삭함
	private int LEFT(int i) { return i * 2; } 
	private int RIGHT(int i) { return i * 2 + 1; }
	private int PARENT(int i) { return i / 2; }
	
	public MaxHeap() {
		size = 0;
	}
	
	public boolean empty() { return this.size == 0; } // size가 0이면 비어있는 거다.
	
  // 배열의 시작을 1부터했기 때문에 HEAP_SIZE에서 1을 뺀 값이 size랑 같다면 꽉찬거다.
	public boolean full() { return this.size == HEAP_SIZE - 1; } 
	
  // 현재 root에 있는 자료값을 반환한다.
	public int find() {
		if(empty()) throw new NoSuchElementException("Error: heap is empty");
		return nodes[root_index];
	}
  
  // java는 call-by-reference가 지원되지 않아서 c와 c++과 달리 swap 함수를 구현하는게 어렵다.
	// 어차피 힙에서는 배열이라는 컨테이너 객체 내부의 원소 값을 바꾸면되서 아래와 같이 구현했다.
  /* public void swap(int front, int rear) {
		int temp = front;
		front = rear;
		rear = temp;
	} */
  
  public void swap(int[] nodes, int front, int rear) {
		int temp = nodes[front];
		nodes[front] = nodes[rear];
		nodes[rear] = temp;
	}
  
  ...
  
}
```
cf) [Java에서 swap 함수를 다르게 구현해야 하는 이유](https://ecsimsw.tistory.com/entry/%EC%9E%90%EB%B0%94-%EA%B9%8A%EC%9D%B4-%EC%95%8C%EA%B8%B0-Swap-%ED%95%A8%EC%88%98%EC%99%80-%EB%A9%94%EB%AA%A8%EB%A6%AC)

#### Insert 
1) Heap의 제일 마지막에 삽입하고자 하는 내용을 담은 node를 추가한다.

2) 추가할 node가 있어야할 위치를 찾기 위해서 부모 노드와 우선순위를 비교한다. 
⇒ 부모노드와 자식 노드가 제대로 위치한다면 거기서 멈추면 되고 그렇지 않다면 서로 위치를 바꾸고 2) 과정을 반복한다.

ex)  
![image](https://user-images.githubusercontent.com/64796257/150446352-92dd21f4-f393-49fc-a6e3-c62df462556f.png)

- 구현 코드 
``` java 
	public void insert(int priority) {
    // 꽉 차있다면 에러 메시지 출력
		if(full()) throw new NoSuchElementException("Error: heap is full");
		
		size++; // 삽입했으니까 size를 1 증가
		int i = size; // size값은 바꿔서말하면 현재 배열에 저장되어 있는 데이터 중 가장 끝에 있는 index 라고 할 수 있다. 
		nodes[i] = priority; // 그래서 삽입의 1)단계를 이 코드로 구현할 수 있다. Heap의 가장 마지막에 내용을 삽입하기 때문이다.
		
		up_heap(i); // 이제 2)단계. 즉, 삽입한 node가 있어야할 위치를 찾는 과정이 필요하다.
	}
	
	private void up_heap(int i) {
		if(i != root_index) {
			if(nodes[i] > nodes[PARENT(i)]) { // i번째 노드의 값이 부모노드의 값보다 크다는 건
                                        // 우선순위가 더 크다는 걸 의미하니까 서로 위치가 바뀌어야 한다.
				swap(nodes, i, PARENT(i)); // i번째 노드와 PARENT(i)번째 노드를 서로 바꾸기
				up_heap(PARENT(i));        // 재귀적으로 up_heap 동작을 실행.
			}
		}
	}
```

#### remove : 우선순위가 가장 높은 원소인 ‘루트 노드’를 삭제하는 과정

1) 맨 마지막 레벨의 가장 오른쪽에 있는 원소를 루트 노드로 대체한다.
2) 그렇게 대체한 루트를 시작으로 양쪽 자식 노드와 우선순위를 비교한다.
⇒ 부모와 자식이 순서가 제대로 되어 있다면 멈춘다. 그렇지 않다면 부모와 자식 노드가 서로 위치를 바꾸고 2) 과정을 반복한다.

![image](https://user-images.githubusercontent.com/64796257/150448668-b3421c99-1960-40d1-9b63-922a844f1f5f.png)

- 구현 코드 
``` java
	public int remove() {
    // 비어있다면 오류 메시지 출력
		if(empty()) throw new NoSuchElementException("Error : heap is empty");
		
		int highest = nodes[root_index]; // 우선순위가 가장 높은 원소를 highest에 저장
                                     // 그 원소는 맨 위에 있으니까 nodes[root_index]값이 된다.
                                     
		nodes[root_index] = nodes[size--]; // 맨 위에 있는 값을 가장 마지막에 있는 값으로 대체하고
                                       // size값을 1 감소시킨다.
		
		down_heap(root_index); // root_index 부터 down_heap을 진행한다.
		return highest; // 다 바꾸고 나서 원래 가장 우선순위가 높았던 값을 반환한다.
	}
	
	private void down_heap(int i) {
		int left = LEFT(i);   // i의 왼쪽 노드의 인덱스
		int right = RIGHT(i); // i의 오른쪽 노드의 인덱스
		int largest = i;      // i를 largest에 저장
		
    // left가 범위를 넘지 않고 
    // left 인덱스에 있는 노드 값이 larget 인덱스에 있는 노드 값보다 크다면
    // 우선순위가 더 큰거니까 largest 인덱스 값을 left로 바꾼다.
		if(left <= size && nodes[left] > nodes[largest]) largest = left;
		
    // right가 범위를 넘지 않고 
    // right 인덱스에 있는 노드 값이 larget 인덱스에 있는 노드 값보다 크다면
    // 우선순위가 더 큰거니까 largest 인덱스 값을 right로 바꾼다.
		if(right <= size && nodes[right] > nodes[largest]) largest = right;
		
    // largest의 값이 변경이 되었다면 if 블록 안에 있는 코드를 실행한다.
		if(largest != i) {
			swap(nodes, i, largest); // nodes 배열의 i번째 인덱스에 해당하는 값과 largest번째 인덱스에 해당하는 값을 바꾼다.
			down_heap(largest);      // 재귀적으로 down_heap을 진행한다.
		}
		
	}
```

### 복잡도 

![image](https://user-images.githubusercontent.com/64796257/150450061-173507b7-cb75-4da9-954e-c23fef871280.png)

### 다른 연산자들 

여기서 item은 `<key, priority>`로 구성된다. key는 노드 별로 고유하다. ![image](https://user-images.githubusercontent.com/64796257/150450179-53e70b15-4a59-4c15-9589-73667e076e5a.png)

#### decrease-key 
1) key를 가지는 노드의 index를 찾음
2) 해당 노드의 priority을 decrement만큼 감소(decrement = 감소량)
3) 해당 index로부터 down-heap을 함

![image](https://user-images.githubusercontent.com/64796257/150450556-c81e9f2e-5fbf-40b4-8f0e-41992d207463.png)

실제로 이 메서드를 적용하려면 다시 기본 구성부터 싹 다 바꿔야 되서 코드의 흐름을 파악하는데 중점을 두겠다.

``` java
void decrease_key(int key, int decrement) { 
  int i = indices[key];            // key에 해당하는 index를 찾아서 i에 저장
  nodes[i].priority -= decrement;  // i번째 노드의 우선순위를 decrement 만큼 감소시킴
  down_heap(i);                    // i번째 노드에 대해 down_heap을 진행시킨다.
} 
```
![image](https://user-images.githubusercontent.com/64796257/150450711-ea3d3623-a364-49bc-8b96-0dcd4b7e5a4a.png)
![image](https://user-images.githubusercontent.com/64796257/150450720-34b5fff5-08f4-4fc6-b6f0-38a1f5c4509f.png)
![image](https://user-images.githubusercontent.com/64796257/150450726-70073a68-2772-489c-92b3-875b46c93eb4.png)

#### increase-key
1) key를 가지는 노드의 index를 찾음
2) 해당 노드의 priority를 increment만큼 더함(increment = 증가량)
3) 해당 index로부터 up-heap을 함

![image](https://user-images.githubusercontent.com/64796257/150450934-8ebef722-7231-4360-9f20-a0b027f9d3a7.png)

``` java
void increase_key(int key, int increment) { 
  int i = indices[key];            // key에 해당하는 index를 찾아서 i에 저장
  nodes[i].priority += increment;  // i번째 노드의 우선순위를 increment 만큼 증가시킴
  up_heap(i);                      // i번째 노드에 대해 up_heap을 진행시킨다.
} 
```
![image](https://user-images.githubusercontent.com/64796257/150451103-d070a42e-8922-4072-8e59-ff8b0bfdc40d.png)
![image](https://user-images.githubusercontent.com/64796257/150451117-a0b7036b-e3f5-44f0-8398-7291ac8b6d9b.png)
![image](https://user-images.githubusercontent.com/64796257/150451123-da05995d-8493-47a4-bf42-a0ebff062196.png)

### 힙 정렬(Heapsort) 

![image](https://user-images.githubusercontent.com/64796257/150451166-2e70117a-605a-4fbd-9d1d-e7f31b3c4b6f.png)

Max heap은 가장 큰 값이 먼저 오기 때문에 내림차순을 구현할 수 있고 
Min heap은 가장 작은 값이 먼저 오기 때문에 오름차순을 구현할 수 있다.

이미 구현해놨던 heap에 자료를 insert 하고나서 heap에서 remove를 실행하면 된다.

``` java
	void heapsort(int A[], int n) {
  
    // heap에 자료들을 넣고
		for(int i = 0; i < n; i++) {
			insert(A[i]); 
		}
		
    // 하나씩 삭제하면서 꺼낸다. 
		for(int i = 0; i < n; i++) {
			int key = remove();
			A[i] = key;
		}
	}
```
그러면 자연스럽게 Max_heap이냐 Min_heap이냐에 따라 내림차순 또는 오름차순으로 정렬이 되어 있을 것이다. 

ex) 
``` java
public static void main(String[] args) {
		
		MaxHeap heap = new MaxHeap();
		int[] A = {10, 5, 30, 8, 9, 3, 7, 300};
		heap.heapsort(A, 8);
		
		for(int i = 0; i < 8; i++) {
			System.out.print(A[i] + " ");
		}
		
	}
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/150451718-b0db43d1-12d5-4417-b4aa-e0d77dfd9306.png)

























