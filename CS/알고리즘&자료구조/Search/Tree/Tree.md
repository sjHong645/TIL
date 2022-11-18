## 트리 

### 트리의 개념과 용어 

트리는 계층적 관계를 표현하는 자료구조이다

트리는 데이터와 저장과 삭제가 아닌 `표현`에 초점이 맞춰져 있다.  
그래서 다음과 같이 바라봐야 한다. ⇒ “트리의 구조로 이뤄진 무엇인가를 표현하기에 적절히 정의되어 있는가”

#### 트리가 표현할 수 있는 것들  
예를 들면, 컴퓨터의 디렉토리 구조, 집안의 족보, 기업 및 정부의 조직도가 있겠다.

![image](https://user-images.githubusercontent.com/64796257/150241562-8560521e-57aa-4fd1-bbf3-071dd3acca3e.png)
![image](https://user-images.githubusercontent.com/64796257/150241583-aeffbba0-f358-456d-bdf8-2723772dfec9.png)


이러한 구조들을 표현하기 위해서 `트리`를 공부하려고 한다. 
다시 한 번 말하자면, 트리를 이용해서 `무엇인가를 표현하는 방법`에 대해서 살펴볼 것이다.

#### 트리 관련 용어  
![image](https://user-images.githubusercontent.com/64796257/150241675-6f1c1fda-a5ee-47ed-9988-92d04ed2db96.png)

- 노드(node) : 트리의 구성요소에 해당하는 A,B,C,D,E,F와 같은 요소
- 간선(edge) : 노드와 노드를 서로 연결하는 선
- 루트 노드(Root Node) : 트리 구조의 최상위에 있는 노드 ⇒ A가 해당된다.
- 단말 노드(terminal Node) = 잎사귀 노드 : 단말(端末) = 끝에 있는 노드 ⇒ E,F,C,D가 해당된다.
- 내부 노드(internal Node) = 비단말 노드: 단말 노드를 제외한 나머지 노드 ⇒ A,B가 해당된다.

노드를 서로 간에 부모, 자식, 형제 관계를 통해 표현할 수도 있다.

- 부모 노드(parent Node) : A는 B,C,D의 부모노드이다.
- 자식 노드(child node) : B,C,D는 A의 자식노드이다.
- 형제 노드(sibling node) : 이때, B,C,D는 서로가 서로에게 형제 노드이다.

#### 이진 트리와 서브 트리  
##### 서브 트리(sub tree)에 대해 살펴보자.

![image](https://user-images.githubusercontent.com/64796257/150242017-eb5a22ba-5eb6-48e2-81a1-02c804e3a7a5.png)

위와 같이 큰 트리에 속하는 작은 트리들을 `서브 트리`라 한다.

여기서 왼쪽 서브 트리를 보면 그 안에서도 더 작은 서브트리가 있다고 할 수 있다.

##### 이진 트리(binary tree)에 대해 살펴보자.  
![image](https://user-images.githubusercontent.com/64796257/150242072-57c51925-db0a-4481-9fdf-91509ff3d0b5.png)

이와 같은 트리를 `이진 트리(binary tree)`라 한다. 이진 트리가 되려면 두 가지 조건을 만족해야 한다.
1. 루트 노드를 중심으로 `2개`의 서브 트리로 나뉘어진다.
2. 나뉘어진 두 서브 트리 역시 이진 트리여야 한다.

조건을 위와 같이 정했다면 아래의 트리는 이진 트리라고 할 수 없을 것 같다.  
![image](https://user-images.githubusercontent.com/64796257/150242140-9e377848-275e-47e7-be0a-35dc3c1d40c7.png)

하지만, 이것도 이진 트리가 맞다. 왜냐하면, 이진 트리와 관련해서 추가로 정의되는 내용이 있기 때문이다.

노드가 위치할 수 있는 곳에 노드가 존재하지 않는다면, 공집합 노드가 존재하는 것으로 간주한다.  
이때 공집합 노드 역시 이진 트리에서 노드로 인정된다. 

라는 내용이다. 

공집합 노드를 그림으로 표현하면 아래와 같다.  
![image](https://user-images.githubusercontent.com/64796257/150242317-ccf7e598-80a6-41e6-b7b5-6233accff3b7.png)

이러한 공집합 노드 때문에 위에서 표현한 트리는 이진 트리라고 할 수 있다. 그래서 아래와 같은 노드도 이진 트리라 할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150242351-25cab5ae-b1eb-450f-8810-f262f050d9d5.png)

##### 포화(Full) 이진 트리, 완전(Complete) 이진 트리, Degenerate tree

이에 대한 개념을 소개하기에 앞서 `레벨(Level)`과 `높이(height)`의 개념에 대해서 먼저 알아보자.  
트리의 각 층을 `레벨`이라 하고 이때 가장 높은 레벨의 값을 `높이`라 한다. 그림으로 표현하면 다음과 같다.  
![image](https://user-images.githubusercontent.com/64796257/150242570-f1957c03-f06a-469c-9f58-359fb4ed89b6.png)

###### 포화 이진 트리(Full Binary Tree) : 모든 레벨이 꽉 차 있는 이진 트리. 즉, 공집합 노드가 없는 이진 트리.

![image](https://user-images.githubusercontent.com/64796257/150242651-2ea45a6d-56c1-4411-a2f3-7a883f903029.png)

레벨 0,1,2를 각각 살펴보면 모든 레벨에서 노드가 꽉 차있다는 걸 알 수 있다.

###### 완전 이진 트리(Complete Binary Tree) : 모든 레벨이 꽉 차있는 건 아니지만 노드가 위에서 아래로, 왼쪽에서 오른쪽의 순서로 꽉 채워진 트리.

ex) ![image](https://user-images.githubusercontent.com/64796257/150242783-daa4b7a3-1165-431c-b74d-43e8874d7448.png)

이를 만족하지 못한 트리는 아래와 같다.  
![image](https://user-images.githubusercontent.com/64796257/150242808-dc909b59-00f6-44dd-bc12-4d30940b3193.png)

위 그림이 완전 이진 트리가 아닌 이유는 자료 D가 왼쪽이 아닌 오른쪽에 저장되었기 때문이다.

완전 이진 트리는 포화 이진 트리에서 오른쪽 부분부터 제거해 나간 트리라고 생각하면 되겠다.  
만약에 자료 D가 자료 B의 왼쪽에 위치했다면 완전 이진 트리가 된다.

###### Degenerate tree : 노드가 n개 있을 때 높이가 n-1인 트리 

![image](https://user-images.githubusercontent.com/64796257/150243209-a123ca21-6bee-4511-ba09-75c91ba51f21.png)

이처럼 트리는 많은 종류가 있다. 그 중에서 가장 많이 사용하는 `이진 트리`에 대해서 집중적으로 다뤄볼 것이다. 

### 이진 트리(binary Tree)의 성질 

1) 노드의 개수가 n개라면 간선의 개수는 n-1개

루트 노드를 제외하고 각 노드 마다 부모에서 오는 간선이 하나씩 있으므로 총 n− 1 개의 간선이 있다.

만약에 노드의 개수가 9개면 간선의 개수는 8개가 된다.

![image](https://user-images.githubusercontent.com/64796257/150243372-723ac983-8e5e-4325-9fd5-1e19966406bc.png)

2) 이진 트리의 `높이`를 `h`라고 했을 때 

![image](https://user-images.githubusercontent.com/64796257/150243394-30fb9691-04cd-4268-b07d-22812417814a.png)

3) 이진 트리의 `노드`가 `n`개 있을 때 

![image](https://user-images.githubusercontent.com/64796257/150243413-5f9388e7-1e6f-43f8-884c-5da7f65ee310.png)

![image](https://user-images.githubusercontent.com/64796257/150243799-8ec684ca-24a8-41ba-b1f2-b40e7bd77709.png) 에서 사용한 함수는 천장함수이다.  
예를 들어, 천장함수 안에 3.6이 들어있다면 결과값은 4가 된다. 3.6보다 큰 정수 값 중 최소 정수 값은 4이기 때문이다.


### 이진 트리의 표현 

- 배열 

![image](https://user-images.githubusercontent.com/64796257/150243596-96eefec5-46e9-4a6f-bff5-2dd8d4861ded.png)

여기서 ![image](https://user-images.githubusercontent.com/64796257/150243665-1bcd1113-ebee-4f6a-95be-9304cb486ac5.png)에서 사용한 기호는 바닥함수이다.   
가우스 기호의 의미를 생각하면 된다. 

- 연결리스트 

![image](https://user-images.githubusercontent.com/64796257/150243931-cb4f3e5b-9a29-483b-acb8-748a5afcdeeb.png)

- 장/단점 

![image](https://user-images.githubusercontent.com/64796257/150244135-6ef0a416-4d8e-4a80-bcc0-667831204427.png)

여기서는 일단 연결리스트를 이용해서 구현하려 한다. 연결리스트는 Node로 구성되어 있는데 일단 Node에 대한 정의를 먼저 하고 시작한다.

![image](https://user-images.githubusercontent.com/64796257/150244901-e3c4f109-2157-4296-b18c-7ecc6d99fcef.png)

#### BinaryNode 구현 

``` java
class BinaryNode{
	
	private int data; // data를 저장할 곳. 일단은 int라고 했고 추후에 데이터를 일반화 할 것이다.
	private BinaryNode left; // 왼쪽 자식 노드를 가리킬 노드 
	private BinaryNode right; // 오른쪽 자식 노드를 가리킬 노드 
	
	public BinaryNode() {
		this.data = 0;
		this.left = null;
		this.right = null;
	} 
	
  // 원하는 데이터, 원하는 왼쪽 자식노드, 원하는 오른쪽 자식노드를 설정.
	public BinaryNode(int data, BinaryNode left, BinaryNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
  // 원하는 데이터를 설정하기 위한 메서드
	public void setData(int data) { this.data = data;}
	
  // 노드의 데이터를 반환하기 위햔 메서드
	public int getData() { return this.data; }
	
  // 원하는 왼쪽 자식 노드를 설정하기 위한 메서드
	public void setLeft(BinaryNode left) { this.left = left; }
	
  // 노드의 왼쪽 자식 노드를 반환하기 위햔 메서드
	public BinaryNode getLeft() { return this.left;}
	
  // 원하는 오른쪽 자식 노드를 설정하기 위한 메서드
	public void setRight(BinaryNode Right) { this.right = Right; }
	
  // 노드의 오른쪽 자식 노드를 반환하기 위햔 메서드
	public BinaryNode getRight() { return this.right;}
	
  // 리프 노드인지 아닌지 판별하는 메서드 
	public boolean isLeaf() { return this.left == null && this.right == null; } 
	
}
``` 

#### 기본적인 BinaryTree 구현 

``` java
public class BinaryTree {

	private BinaryNode root; // 루트 노드
	
	public BinaryTree() { this.root = null; } // 루트 노드에서 모든게 시작된다.
	
	public boolean empty() { return this.root == null; } // 이진트리가 비어있는지 확인
	
	public void setRoot(BinaryNode node) { this.root = node; } // 원하는 노드를 이진트리의 루트노드로 설정
	
	public BinaryNode getRoot() { return this.root; } // 현재 이진트리의 루트노드를 알고싶을 때 사용 

}
```

### 순회 

이진 트리에서 순회는 크게 4가지 방식이 있다. 전위 순회, 중위 순회, 후위 순회, 레벨 순회. 각각에 대해서 살펴보겠다. 

루트 노드를 `앞`에서 순회하면 `전위 순회`, 루트 노드를 `중간`에서 순회하면 `중위 순회`, 루트 노드를 `뒤`에서 순회하면 `후위 순회`

- 전위 순회 : 루트 출력 ⇒ 왼쪽 서브 트리 ⇒ 오른쪽 서브 트리 

![image](https://user-images.githubusercontent.com/64796257/150246704-ca047b76-d463-4770-9712-70aa5d66b937.png)

``` java
public void preOrder() { // main 함수에서 사용할 함수 
		System.out.print("preOrder: ");
		preOrder(this.root); // 순회는 root 노드부터 시작하기 때문에 root 노드를 매개변수로 전달한다.
		System.out.println();
	}
	
private void preOrder(BinaryNode node) {
		if(node != null) {
			System.out.print(node.getData() + " "); // 루트 출력
			preOrder(node.getLeft());               // 왼쪽 서브 트리
			preOrder(node.getRight());              // 오른쪽 서브 트리
		}
	}
``` 

- 중위 순회 : 왼쪽 서브 트리 ⇒ 루트 출력 ⇒ 오른쪽 서브 트리 
![image](https://user-images.githubusercontent.com/64796257/150248275-d5ceba6c-20ac-44ed-a0cf-04000417f538.png)

``` java
	public void inOrder() {  // main 함수에서 사용할 함수 
		System.out.print("inOrder: ");
		inOrder(this.root);
		System.out.println();
	}
	
	private void inOrder(BinaryNode node) {
		if(node != null) {
			inOrder(node.getLeft());               // 왼쪽 서브 트리
			System.out.print(node.getData() + " "); // 루트 출력
			inOrder(node.getRight());              // 오른쪽 서브 트리 
		}
	}
```

- 후위 순회 : 왼쪽 서브 트리 ⇒ 오른쪽 서브 트리 ⇒ 루트 출력 
![image](https://user-images.githubusercontent.com/64796257/150248620-88bf0c27-2222-4997-a288-e2cc5a299bd1.png)

 ``` java
 	public void postOrder() { // main에서 사용할 함수 
		System.out.print("postOrder: ");
		postOrder(this.root);
		System.out.println();
	}
	
	private void postOrder(BinaryNode node) { 
		if(node != null) {
			postOrder(node.getLeft());               // 왼쪽 서브 트리
			postOrder(node.getRight());              // 오른쪽 서브 트리
			System.out.print(node.getData() + " "); // 루트 출력
		}
	}
 ```

- 레벨 순회 : 레벨(Level) 순서로 노드 

![image](https://user-images.githubusercontent.com/64796257/150249086-a7569742-547c-46bf-bc35-9e25abe5cd5e.png)

Root를 시작점으로 해서 BFS를 하면 된다. (앞서 배운 큐를 이용하자)

[관련 내용](https://github.com/sponbob-pat/TIL/blob/main/CodingTest/11LeetCode%EB%AC%B8%EC%A0%9C/Easy/2Group/111.MinimumDepthofBT.md)

노드를 출력한다는 것 빼고는 BFS와 동일하다.

### 순회 원리를 이용한 메소드들 

1) 노드 개수 세기 : getCount  
⇒ 루트 노드를 기준으로 왼쪽/오른쪽 서브 트리의 노드수를 다 구한 다음에 자기 자신의 개수를 합하고 반환한다.

![image](https://user-images.githubusercontent.com/64796257/150249779-5e632ed2-1554-4af2-aad0-78500696e23d.png)

``` java
	public int getCount() {
		return (this.empty()) ? 0 : getCount(this.root);
	}
	
	private int getCount(BinaryNode node) {
		if(node == null) return 0;
		
		int cnt = getCount(node.getLeft()); // 왼쪽 서브 트리의 노드 개수 
		cnt += 1;                           // 루트 노드 1개 추가
		cnt += getCount(node.getRight());   // 오른쪽 서브 트리의 노드 개수 추가
		
		return cnt; // 최종 노드 개수 반환
	}
```

2) 리프노드 개수 세기 : getLeafCount 

![image](https://user-images.githubusercontent.com/64796257/150252190-4646eed8-94a9-44ba-842a-11011e4cbaf0.png)

``` java
	public int getLeafCount() { 
		return (this.empty()) ? 0 : getLeafCount(this.root); 
	}
	
	private int getLeafCount(BinaryNode node) {
		if(node == null) return 0; // node가 null 이면 없는 거다
		
		if(node.isLeaf()) return 1; // 리프노드이면 1을 반환
		
    // 왼쪽 서브트리의 리프 노드와 오른쪽 서브트리의 리프 노드 개수를 구해서 더한다.
		return getLeafCount(node.getLeft()) + getLeafCount(node.getRight()); 
	}
```

3) 트리의 높이 : getHeight 

![image](https://user-images.githubusercontent.com/64796257/150254522-cc68f3fe-2f24-459f-8a55-ee5f62780b73.png)

``` java
	public int getHeight() {
		return (this.empty()) ? 0 : getHeight(this.root);
	}
	
	private int getHeight(BinaryNode node) {
		
		if(node.isLeaf()) return 0;
		
		int hLeft = getHeight(node.getLeft()); // 노드의 왼쪽 서브 트리의 높이 구함
		int hRight = getHeight(node.getRight()); // 노드의 오른쪽 서브 트리의 높이 구함
		
		return (hLeft > hRight) ? hLeft + 1 : hRight + 1; // 둘 중에서 더 큰 값에 1을 더한 값이 트리의 높이가 된다.
	}
```












