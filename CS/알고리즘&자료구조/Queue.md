### 큐(Queue)의 이해

줄 서는 걸 생각하면 된다. 예를 들어, 버스를 타려고 줄을 설 때 가장 먼저 온 사람이 가장 먼저 버스에 탄다.   

이러한 `선입선출(First-In-First-Out; FIFO)`의 구조를 가지는 자료구조가 `큐(Queue)`이다. 

![image](https://user-images.githubusercontent.com/64796257/149857127-52edfcf1-a0a5-4ede-8e91-93af71ca50df.png)

### 큐의 ADT 정의

큐의 ADT는 정형화된 편이다. 핵심이 되는 연산은 두 가지라 할 수 있다.

1. enqueue : 큐에 데이터를 넣는 연산
2. dequeue : 큐에서 데이터를 꺼내는 연산. ⇒ 스택에서의 push와 pop을 생각하면 되겠다.

![image](https://user-images.githubusercontent.com/64796257/149857658-0b9a9497-ea6e-4887-af82-75989cb8489f.png)

이제 큐를 구현한 함수들을 살펴보자. 

### 큐의 배열 기반 구현

- 큐의 구현 논리

이를 제대로 이해하기 위해 그림을 그려가면서 이해해보자. 
길이가 4인 배열을 대상으로 `enqueue 연산`을 해보자. 큐의 머리는 F가 가리키고 큐의 꼬리는 R가 가리킨다.

![image](https://user-images.githubusercontent.com/64796257/149857779-ba1168de-9540-43ab-acd7-37b1619763fe.png)

A, B, C를 차례대로 큐에 집어넣는 상황이다. 

새로운 자료를 넣을 때마다 R은 가리키던 칸의 다음 칸을 가리키고 그 칸에 새로운 자료를 저장한다.

줄을 설 때 나중에 들어온 사람이 그 줄의 맨 뒤에 붙는 걸 생각하면 된다.

그럼 `dequeue 연산`을 할 때는 어떤 데이터를 반환하고 소멸해야 할까?

![image](https://user-images.githubusercontent.com/64796257/149857854-8db569fa-db48-4f13-808d-e402f85f09c1.png)

줄을 섰을 때 가장 앞에 있는 사람이 먼저 나간다. 

따라서 F가 가리키고 있는 데이터를 반환하고 소멸해야 한다.  
하지만, 왼쪽과 같이 동작을 하려면 저장된 데이터를 전체적으로 한 칸씩 옮겨야 한다는 단점이 있다. 

그래서 실제로 적용해야하는 `dequeue 연산`은 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/149857923-2767d658-b7be-44ad-ba8d-c2d2b18790b3.png)

이 그림에서는 `dequeue 연산`을 할 때 마다 F를 이동시키고 있다.  
이렇게 되면 데이터를 이동할 필요 없이 그저 F가 가리키는 위치만 한 칸씩 옮기면 된다.

하지만 이 방법은 완전하지 않다. 다음과 같은 상황이 발생할 수 있기 때문이다.

![image](https://user-images.githubusercontent.com/64796257/149857999-5f2e1ce8-5b3a-456d-9adc-b9d42d87cb61.png)

문자 D가 배열의 끝에 저장된 상황이다. 때문에 더 이상 R을 오른쪽으로 이동할 수 없다. 

추가적인 enqueue 연산이 가능하기 위해선 R을 배열의 앞부분으로 이동시키면 된다. 여기서 얘기할 수 있는게 바로 `원형 큐(Circular Queue)`이다. 

## 원형 큐(Circular Queue)

R과 F를 회전시키는 배열을 그림으로 그려보면 다음과 같이 나타낼 수 있다.

![image](https://user-images.githubusercontent.com/64796257/149858086-05636fc9-2572-4bb4-8ccb-a53c4318c4cf.png)

처음에 A를 넣었을 때 F와 R은 A를 가리켰다. 여기서 B와 C를 추가하면서 R이 한 칸씩 이동한 상황이다. 

과정을 살펴보면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/149858117-52c71350-e9f0-46ab-8751-f3c05cf4617a.png)

그렇다면, dequeue를 두 번 실행한다면, 어떻게 되는지 살펴보자. F가 가리키던 데이터를 삭제하고 F를 한 칸 이동한다.

![image](https://user-images.githubusercontent.com/64796257/149858133-3452b35a-b86c-4495-82e4-5b14bf7d8179.png)

여기서 생각해볼 문제가 있다. 큐가 꽉 차 있는 상황과 텅 비어 있는 두 가지 상황에 대해서 생각해보자.
- 꽉 차있을 때

![image](https://user-images.githubusercontent.com/64796257/149858156-256904ab-e1b9-4bed-98a8-5e738b71192c.png)

이 상황에서 문자 D에 대해 enqueue 연산을 진행했다. 결과는 아래와 같다. 

![image](https://user-images.githubusercontent.com/64796257/149858181-b63c5575-6f8e-4155-b9d8-d5bd84f97b50.png)

- 텅 비어있을 때

![image](https://user-images.githubusercontent.com/64796257/149858196-f7371a2c-b3d6-4963-94c1-fa62eb33a670.png)

이 상황에서 문자 C에 대해 dequeue 연산을 진행했다. 결과는 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/149858221-064773bb-7d16-4136-9849-910ea8cf3f04.png)

꽉 차있는 경우와 텅 비어있는 경우를 보면 F가 R보다 한 칸 앞 선 위치를 가리킨다는 걸 알 수 있다.  
그래서 F와 R의 위치만을 가지고 해당 큐가 꽉 찼는지 텅 비었는지 확인할 수 없다.

이를 해결하기 위해서 `배열을 꽉 채우지 않는 방법`을 선택했다.  
즉, 배열의 길이가 N이면 데이터가 N-1개 채워졌을 때 이를 꽉 찬 것으로 간주한다는 의미이다.

이를 통해서 큐가 채워지는 과정을 한 번 더 정리해보자. 현재는 큐가 처음 생성되어서 텅 빈 상태이다.

![image](https://user-images.githubusercontent.com/64796257/149858298-2b472631-2fba-4d38-8d41-4a499b9b6ad5.png)

지금은 F와 R이 같은 위치를 가리키고 있다.  
이전에 다뤘던 건 데이터를 하나 채울 때 해당 데이터는 F와 R이 가리키고 있던 위치에 채워졌지만

지금은 공간을 하나 비우기로 했기 때문에 데이터는 아래와 같이 채워진다.

![image](https://user-images.githubusercontent.com/64796257/149858344-5ceb787d-7ec8-43a5-8304-aaa0e39b5bec.png)

이러한 과정을 통해서 두 가지 사실을 정리할 수 있다.  
1. enqueue 연산 시 원래 R이 가리키는 위치를 한 칸 이동시켜서 해당 위치에 데이터를 저장한다.
2. dequeue 연산 시 원래 F가 가리키는 위치를 한 칸 이동시켜서 해당 위치에 있는 데이터를 반환 및 소멸한다.  
   (즉, F가 가리키는 어떤 데이터를 가리키게 되면 그 데이터는 없어지는 것이다)
   
이를 통해서 큐의 두 가지 특성을 얘기할 수 있다.  
1. 원형 큐가 텅 빈 상태 ⇒ F와 R이 똑같은 위치를 가리킬 때
2. 원형 큐가 꽉 찬 상태 ⇒ R이 가리키는 위치의 앞을 F가 가리킨다.

![image](https://user-images.githubusercontent.com/64796257/149858536-9f6dbc5e-4626-4ee8-b6f9-036d2662c6c4.png)

MQS는 원형 큐의 길이이다. 위 예시에서는 MQS = 8이 되겠다.

### 원형 큐의 구현(배열을 이용했다)

``` java
package prob91;

//This program implements the concept of CircularQueue in Java
//Link to the concept: (https://en.wikipedia.org/wiki/Circular_buffer)
public class CircularQueue {

    int[] arr;
    int rear;
    int front;
    int size;

    public CircularQueue(int size) { 
        arr = new int[size]; // 매개변수로 전달하는 size에 따라 배열 설정
        rear = 0; 
        front = 0;
        this.size = size;
    }

    public boolean isEmpty() {
        return (front == rear) ;
    }

    public boolean isFull() {
        return ((rear + 1) % size == front);
    }

    public void enQueue(int value) {
    
        if (isFull()) { 
        // 큐가 꽉 차있으니까 이에 대한 메시지 출력
            System.out.println("The Queue is full!");
        } else {
        	
        	rear = (rear + 1) % size;
        	arr[rear] = value; // 변경된 rear에 해당하는 index 위치에 데이터를 저장한다.
            System.out.println(value + " has been successfully inserted!");
        }
    }
    public int deQueue() {
        if (isEmpty()) { // 비어 있으면 이에 대한 메시지 출력
            System.out.println("The Queue is Empty!");
            return -1;
        } else {
            front = (front + 1) % size;           
            return arr[front];
        }
    }
    public int peek() {
        if (isEmpty()) {
            System.out.println("The Queue is Empty!");
            return -1;
        } else {
            return arr[(front + 1) % size];
        }
    }

    public void deleteQueue() {
        arr = null;
        System.out.println("The Queue is deleted!");
    }


    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);
        System.out.println(cq.isEmpty());
        System.out.println(cq.isFull());
        cq.enQueue(1);
        cq.enQueue(2);
        cq.enQueue(3);
        cq.enQueue(4);
        cq.enQueue(5);

        System.out.println(cq.deQueue());
        System.out.println(cq.deQueue());
        System.out.println(cq.deQueue());
        System.out.println(cq.deQueue());
        System.out.println(cq.deQueue());
        System.out.println(cq.isFull());
        System.out.println(cq.isEmpty());
        
        cq.enQueue(6);
        cq.enQueue(7);
        cq.enQueue(8);
        System.out.println(cq.peek());
        System.out.println(cq.peek());
        
        System.out.println(cq.deleteRear());
        
        cq.addFront(400);
        System.out.println(cq.peek());
        
        cq.deleteQueue();

    }
}
```

- main 함수 실행 결과  

![image](https://user-images.githubusercontent.com/64796257/149885696-c9d5b632-f594-4251-9aef-c04374c33de0.png)

## 덱(Deque) - 배열을 이용한 덱

: 원형 큐 클래스를 확장해서 `원형 덱`을 만들 수 있다.

![image](https://user-images.githubusercontent.com/64796257/149872406-7b3750b9-666c-4423-8fed-203945e45024.png)

- 덱의 연산

![image](https://user-images.githubusercontent.com/64796257/149872493-dfc51f70-b897-4127-a175-37581ec9eac5.png)

여기서 addRear는 위에서 다룬 enqueue와 똑같은 동작이고 deleteFront는 dequeue와 똑같은 동작이다. 

getFront는 peek과 똑같은 동작이다.

그래서 덱에서 추가되는 연산은 deleteRear(), addFront(), getRear()가 되겠다.

1) deleteRear() ⇒ 현재 rear가 가리키던 데이터를 삭제하고 나서 rear를 한 칸 뒤로 옮긴다.

![image](https://user-images.githubusercontent.com/64796257/149872718-bf20ebce-6de0-4f07-98d6-26b079949a2d.png)

``` java
// 여기서 사용한 isEmpty()와 size는 위에서 구현한 CircularQueue.java에 있는 메서드와 변수다.
public int deleteRear() {
    	if(isEmpty()) { // 비어 있다면 이에 대한 오류 메시지를 출력
    		System.out.println("The Queue is Empty!");
    		return -1;
    	}
    	else {
    		int ret = arr[rear]; // rear에 위치한 값을 삭제하고 출력할 거니까 해당 데이터를 res에 저장
    		rear = (rear -1 + size) % size;
    	   return ret;
      }
}
```

rear가 가리키고 있던 데이터를 삭제하는 함수다.  
1. rear가 가리키던 데이터를 임시변수 ret에 저장한다.
2. rear를 한 칸 뒤로 옮긴다.

⇒ 뒤로 옮길 때 배열의 범위를 넘어선다면 원형 큐가 되도록 topOfQueue에 size값을 저장한다.

⇒ 기존에 rear가 가리키던 값에서 한 칸 앞으로 가는 동작을 구현했다.

2) addFront() ⇒ 현재 front가 가리키고 있는 위치에 데이터를 넣고 front를 한 칸 뒤로 이동 시킨다.

![image](https://user-images.githubusercontent.com/64796257/149878083-1fe5bcda-0252-4ed3-b83c-602bdb5ca6f4.png)

``` java
public void addFront(int value) { // 전단에 삽입
    	if(isFull()) {
    		System.out.println("The Queue is full!");
    	}
    	else{
    		arr[front] = value;
    		front = (front - 1 + size) % size;
    		System.out.println(value + " has been successfully inserted!");
    	}
}
```

`데이터`를 `front가 가리키고 있는 곳`에 추가시킨다.

1. front가 가리키고 있던 곳에 데이터를 넣고
2. front를 한 칸 뒤로 이동시킨다. ⇒ front를 한 칸 뒤로 이동시키는 동작은 deleteRear에서 작성한 코드의 내용과 똑같이 작성해주면 된다.

### 연결리스트를 이용한 큐






