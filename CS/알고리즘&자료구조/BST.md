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
