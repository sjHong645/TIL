## 이진탐색트리(Binary Search Tree; BST) : 탐색을 위해 특화된 이진트리

### 구성 

- key : 해당 노드를 다른 노드와 구분짓게 하는 식별자 (identifier) 
- value : key에 대응되는 데이터 값 

앞으로 설명할 때는 BST라는 개념에 더 집중하기 위해서 key와 value가 동일하다고 가정한다.

![image](https://user-images.githubusercontent.com/64796257/150255830-19f73774-7b22-4f64-a836-b2e74e596110.png)

### 정의 

1) 모든 노드는 유일한 key를 가진다.
2) 왼쪽 서브트리의 key들은 루트의 key보다 작다.
3) 오른쪽 서브트리의 key들은 루트의 key보다 크다. ⇒ 이러한 정의가 재귀적으로 만족된다.

ex)  
![image](https://user-images.githubusercontent.com/64796257/150256170-43fa7eab-aea1-4ba1-af95-99ae2d2b85c8.png)

전체 트리의 루트 노드인 19를 기준으로 왼쪽 오른쪽을 나눌 수 있다.  

그래서 왼쪽 서브트리의 루트 노드는 7, 오른쪽 서브트리의 루트 노드는 26를 가진다.

이러한 정의가 재귀적으로 만족된다.

### 구현 

구현하기에 앞서 Node를 정의해야 하는데 이는 tree에서 정의한 노드와 거의 똑같다.  
data를 넣을 공간에 key값과 value값이 둘 다 들어가도록 해주면 된다. 

#### BinarySearchNode 
: Tree.md에서 정의한 BinaryNode 클래스에서 key와 value 그리고 자식노드의 개수 여부를 묻는 내용을 추가했다.
``` java
class BinarySearchNode{
	
	// private int data;
    private int key; 
    private int value; 
  
	private BinarySearchNode left;
	private BinarySearchNode right;
	
	public BinarySearchNode() {
        this.key = 0;
        this.value = 0;
		    this.left = null;
		    this.right = null;
	}
	
	public BinarySearchNode(int key, int value, BinarySearchNode left, BinarySearchNode right) {
		// this.data = data;
		this.key = key;
		this.value = value;
    
		this.left = left;
		this.right = right;
	}
	
	public void setKey(int key) { this.key = key;}
	
	public int getKey() { return this.key; }
	
	public void setValue(int value) { this.value = value;}
	
	public int getValue() { return this.value; }
	
	public void setNode(int key, int value) { 
		this.key = key; 
		this.value = value; 
	}

	public void setLeft(BinarySearchNode left) { this.left = left; }
	
	public BinarySearchNode getLeft() { return this.left;}
	
	public void setRight(BinarySearchNode Right) { this.right = Right; }
	
	public BinarySearchNode getRight() { return this.right;}
	
	public boolean isLeaf() { return this.left == null && this.right == null; }
	
	public boolean hasTwoChildren() { // 현재 노드의 자식노드가 2개 있는지 파악한다.
		return this.left != null && this.right != null;
	}
	
	public boolean hasOneChildren() { // 현재 노드의 자식노드가 1개 있는지 파악
		boolean hasOnlyLeft = this.left != null && this.right == null;
		
		boolean hasOnlyRight = this.left == null && this.right != null;
		
		return hasOnlyLeft || hasOnlyRight; 
	}
	
}
```

#### BinarySearchTree 
이제 BinarySearchTree 클래스를 구현하겠다. 

[BinaryTree 부분](https://github.com/sponbob-pat/TIL/blob/main/CS/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%26%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0/Tree.md#%EA%B8%B0%EB%B3%B8%EC%A0%81%EC%9D%B8-binarytree-%EA%B5%AC%ED%98%84)

##### 1) Search : 원하는 key값을 가진 노드의 value를 알고 싶다.

![image](https://user-images.githubusercontent.com/64796257/150275285-061bf534-3e4a-4b62-9c03-ab32104f6c6d.png)

``` java
public class BinarySearchTree extends BinaryTree{
	
	private BinarySearchNode root; // BinaryTree에 있는 root는 BinaryNode 형이다. 
                                 // 그래서 원하는 자료형의 root 변수를 따로 설정했다.
                                 // 그 외의 메소드는 BinaryTree에서 가져왔다.
	
  public int search(int key) { // main 함수에서 사용할 search 함수
		BinarySearchNode node = search(this.root, key); // 루트에서부터 시작해서 원하는 key값을 가진 노드의 value를 알고 싶다.
		if(node == null) throw new NoSuchElementException("Error : out-of-key");
		return node.getValue();
	}
  
	private BinarySearchNode search(BinarySearchNode node, int key) {
  
    // 내가 원하는 key값을 가진 노드를 찾았다면 해당 node를 반환
		if(node == null || key == node.getKey()) { return node; } 
		
    // 내가 원하는 key값이 현재 노드보다 작다면 node의 왼쪽에서 search 시작
		else if(key < node.getKey()) return search(node.getLeft(), key); 
				
    // 내가 원하는 key값이 현재 노드보다 크다면 node의 오른쪽에서 search 시작
		else return search(node.getRight(), key);
	}

...

}
```

##### 2) Insert 

``` java
public class BinarySearchTree extends BinaryTree{
	
		public void insert(int key, int value) {
		if(empty()) this.root = new BinarySearchNode(key, value); // 트리가 비어있다면 루트 노드에 새 노드를 삽입해야 한다.
		
		else insert(this.root, key, value); // 비어있지 않다면 삽입 동작 시작
	}
	
	private void insert(BinarySearchNode node, int key, int value) {
		if(key == node.getKey()) node.setValue(value); // 내가 원하는 노드의 key값을 가진 노드가 이미 존재한다면 
                                                   // 해당 노드에 value만 새로 설정해주면 된다.
		
		else if(key < node.getKey()) { // 내가 삽입하고자 하는 노드의 key값이 
                                   // 현재 노드의 key값보다 작은 경우의 동작
                                   
      // 현재 노드의 왼쪽 서브 트리가 없다면
      // 내가 삽입하고자 하는 key값과 value를 가진 노드를 현재 노드의 왼쪽에 삽입해주면 된다.
			if(node.getLeft() == null) { 
				node.setLeft(new BinarySearchNode (key, value));
			}
      
      // 그렇지 않다면 현재 노드의 왼쪽 노드에서 insert 작업을 수행하면 된다.
			else insert(node.getLeft(), key, value);
		}
		
		else { // 내가 삽입하고자 하는 노드의 key값이 
           // 현재 노드의 key값보다 큰 경우의 동작
     
      // 현재 노드의 오른쪽 서브 트리가 없다면
      // 내가 삽입하고자 하는 key값과 value를 가진 노드를 현재 노드의 오른쪽에 삽입해주면 된다.
			if(node.getRight() == null) {
				node.setRight(new BinarySearchNode (key, value));
			}
			
      // 그렇지 않다면 현재 노드의 오른쪽 노드에서 insert 작업을 수행하면 된다.
			else insert(node.getRight(), key, value);
		}
		
		
	}

...

}
```

##### 3) Remove 
삭제 과정은 case에 따라 동작하는 방식이 좀 다르다. 그래서 각각에 대해서 살펴볼 필요가 있다.  
1. 삭제하려고 하는 노드가 leaf node(자식노드가 없는 노드) 일 때
2. 삭제하려고 하는 노드가 왼쪽 또는 오른쪽 서브트리 중 하나만 자식으로 가지고 있을 때
3. 삭제하려고 하는 노드가 두 개의 서브트리를 가지고 있을 때

전체 구현 코드 
``` java
public void remove(int key) {
		BinarySearchNode node = remove(this.root, null, key); // 삭제할 노드를 여기에 가져온다.
		if(node == null) throw new NoSuchElementException("Error : out-of-key");
		node = null; // 여기서 삭제할 노드를 삭제한다.
	}
	
	private BinarySearchNode remove(BinarySearchNode node, BinarySearchNode parent, int key) {
		if(node == null) return null; // 삭제할 노드가 null 이면 null을 return
    
    // 삭제할 노드의 key값이 현재 노드보다 작다면 현재 노드의 왼쪽 서브트리에서 삭제 동작을 실행한다.
		if(key < node.getKey()) return remove(node.getLeft(), node, key); 
    
    // 삭제할 노드의 key값이 현재 노드보다 크다면 현재 노드의 오른쪽 서브트리에서 삭제 동작을 실행한다.
		else if(key > node.getKey()) return remove(node.getRight(), node, key);	
    
    // 삭제할 노드의 key값이 현재 노드일 때 상황에 따라서 3가지로 나뉜다.
		else {		
			// case 3 - 자식 노드가 2개 있을 때
			if(node.hasTwoChildren()) {
				BinarySearchNode succ = leftmost(node.getRight());
				node.setKey(succ.getKey());
				node.setValue(succ.getValue());
				
				succ = this.remove(node.getRight(), node, succ.getKey());
				return succ;
			}
			// case 2 - 자식 노드가 1개 있을 때
			else if(node.hasOneChildren()) {
				BinarySearchNode child = (node.getLeft() != null) ? 
										node.getLeft() : node.getRight();
				if(node == this.root) this.root = child;
				
				else {
					if(parent.getLeft() == node) parent.setLeft(child);
					else parent.setRight(child);
				}
				
				return node;
			}
			// case 1 - 자식 노드가 없을 때
			else {
				if(node == this.root) this.root = null;
				
				else {
					if(parent.getLeft() == node) parent.setLeft(null);
					else parent.setRight(null);
				}
				
				return node;
			}
		}
	}
```

case 1) 자식 노드가 없을 때

``` java
else {
	if(node == this.root) this.root = null;
				
	else {
    // 현재 node가 부모 노드의 왼쪽 노드라서 부모 노드의 왼쪽 노드는 null이 되도록 했다.
		if(parent.getLeft() == node) parent.setLeft(null); 
    
    // 현재 node가 부모 노드의 오른쪽 노드라서 부모 노드의 오른쪽 노드는 null이 되도록 했다.
		else parent.setRight(null);
	}
  // 삭제할 노드 반환
  return node; 
}
```
![image](https://user-images.githubusercontent.com/64796257/150283360-09eace19-c83b-47f0-89e1-3f2c7fc39664.png)

삭제하려고 하는 노드를 node로 표시했다.

node의 부모 노드가 null을 가리키도록 하고 나서 public remove 메서드에서 해당 노드를 삭제하면 된다. 

case 2) 자식 노드가 1개 있을 때 

``` java
else if(node.hasOneChildren()) {
  // 삭제하려는 노드의 자식 노드가 1개 있기 때문에 왼쪽 또는 오른쪽에 있을 거다.
  // 그렇게 왼쪽 또는 오른쪽에 있는 서브 트리 노드를 child가 가리키도록 했다.
  
  // 이 child 노드가 node를 삭제하고 나서 기존의 node 자리를 대체할 노드이다. 
	BinarySearchNode child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
  
  // 삭제할 노드가 root 노드였다면 위에서 구한 child를 root 노드로 대체한다.
	if(node == this.root) this.root = child;
	
  // 그렇지 않은 경우
	else {
    // 삭제하려고하는 node가 parent 노드의 왼쪽 자식 노드였다면
    // parent 노드의 왼쪽 자식 노드를 child 노드로 설정한다.
		if(parent.getLeft() == node) parent.setLeft(child);
    
    // 삭제하려고하는 node가 parent 노드의 오른쪽 자식 노드였다면
    // parent 노드의 오른쪽 자식 노드를 child 노드로 설정한다.
		else parent.setRight(child);
	}
		
	return node;
}
```

![image](https://user-images.githubusercontent.com/64796257/150284111-f2501880-523d-4e51-b6d3-9d864dcd3734.png)

26 node를 삭제하려고 한다.

“26 node의 부모 노드(parent)”가 “26 node의 서브트리의 루트 노드(child)”를 가리키게 하면 된다.

여기서는 26 node가 왼쪽 자식 노드(node.getLeft())를 가지고 있었기 때문에 

`26 node의 부모 노드(parent)`는 `26 node의 node->left(chlid)`를 자신의 자식노드로 가리키도록 했다.

만약에, 삭제할 노드가 전체 트리의 루트 노드라면? 그 노드의 서브트리의 루트 노드(child)가 전체 트리의 루트 노드가 되도록 하면 된다.

case 3) 자식 노드가 2개 있을 때

``` java
// 이진 트리의 특성상 계속해서 왼쪽 노드로 이동해서
// 끝까지 가면 그 트리에서 가장 작은 key를 가진 노드를 구할 수 있다.
// 그 성질을 이용한 메서드가 leftmost이다.
	public BinarySearchNode leftmost(BinarySearchNode node) {
		while(node.getLeft() != null) node = node.getLeft();
		
		return node;
	}
  
			if(node.hasTwoChildren()) {
      // 대체할 노드를 succ이 가리키도록 했다.
      // 이때 대체할 노드는 오른쪽 서브트리에서 제일 작은 key를 가진 노드이다.
				BinarySearchNode succ = leftmost(node.getRight()); 
        
        // succ이 가리키는 노드의 key값과 value값이 삭제할 노드인 node의 key와 value값이 되도록 한다.
				node.setKey(succ.getKey());
				node.setValue(succ.getValue());
				
        // 대체가 끝난후 대체한 노드를 가리킨 succ 노드를 삭제한다.
        // 오른쪽 서브트리에 있었으니까 node.getRight()에서 시작한다. 
        // 그러면 부모노드는 자연스레 node가 되고 찾으려고 하는 key는 succ의 key이니까 succ.getKey()로 표현하면 된다.
				succ = this.remove(node.getRight(), node, succ.getKey());
        
				return succ;
			}
```
![image](https://user-images.githubusercontent.com/64796257/150285386-e4f2937f-43f0-4566-bd16-3cfd6337d532.png)

노드를 삭제하고 나서 대체할 노드를 찾아야 한다. 대체할 노드는 두 가지가 있다.
1) 왼쪽 서브 트리의 제일 큰 key를 가진 노드
2) 오른쪽 서브 트리의 제일 작은 key를 가진 노드

==> 둘 중 아무거나 사용해도 상관 없지만 여기서는 2) 경우를 선택했다. 

전체적인 삭제 과정
1) 대체할 노드를 찾는다. 
2) `대체할 노드의 key와 value`를 `삭제할 노드의 key와 value`에 대입한다.
3) 이제 대체를 완료했으니 처음에 대체한 노드에 위치한 노드를 삭제해주고 나서 반환해주면 된다.

### 시간 복잡도 

![image](https://user-images.githubusercontent.com/64796257/150286617-e8be5a1b-0de7-4ba3-9dfa-dbcdba8dc149.png)
![image](https://user-images.githubusercontent.com/64796257/150286638-05258fce-071a-44b0-a5fa-bd58fefb0e43.png)

![image](https://user-images.githubusercontent.com/64796257/150286656-dbc8b6fc-a0fb-4af4-8446-9521f0ecbfc6.png)

![image](https://user-images.githubusercontent.com/64796257/150286746-009062fd-efe1-4d48-ab43-addfe1ffa583.png)





