[출처](https://coding-factory.tistory.com/829)

## 가비지 컬렉션(Garbage Collection; GC) 

Java의 메모리 관리 방법 중 하나로  
`JVM의 Heap 영역`에서 `동적으로 할당했던 메모리 영역` 중 `필요 없는 메모리 영역`을 주기적으로 `삭제`하는 프로세스 

Java는 `JVM`에 탑재되어 있는 `가비지 컬렉터(Garbage Collector)`가 메모리 관리를 수행하기 때문에  
개발자로 하여금 메모리 관리, 메모리 누수 문제에 대해 크게 신경쓰지 않게 해준다. 

### 단점 

