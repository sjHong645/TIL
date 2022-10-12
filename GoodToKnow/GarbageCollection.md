[출처](https://coding-factory.tistory.com/829)

## 가비지 컬렉션(Garbage Collection; GC) 

Java의 메모리 관리 방법 중 하나로  
`JVM의 Heap 영역`에서 `동적으로 할당했던 메모리 영역` 중 `필요 없는 메모리 영역`을 주기적으로 `삭제`하는 프로세스 

Java는 `JVM`에 탑재되어 있는 `가비지 컬렉터(Garbage Collector)`가 메모리 관리를 수행하기 때문에  
개발자로 하여금 메모리 관리, 메모리 누수 문제에 대해 크게 신경쓰지 않게 해준다. 

### 단점 

1. 개발자가 메모리가 `언제 해제되는지` 정확히 알 수 없다.

2. GC가 동작하는 동안 `다른 동작`이 `중지`되면서 overhead가 발생한다. (이때 작업을 중지하는 걸 `stop-the-world`라 한다)  
⇒ 이 때문에 GC를 너무 자주 실행하면 SW의 성능이 떨어질 수 있다. 

### GC의 대상이 되는 객체 

![image](https://user-images.githubusercontent.com/64796257/195343104-4c155c10-54f8-4750-a74d-7d45e0e07131.png)

`객체`들은 `Heap 영역`에서 생성된다.  
Method Area, Stack Area 에는 `Heap 영역에 생성된 객체`의 `주소만 참조`하는 형식으로 구성된다. 

그래서 위 그림의 `빨간색`과 같이 Heap 영역에 `객체가 생성`되었지만  
여러 이벤트들로 인해 정작 해당 객체의 `주소`를 갖고 있는 `참조 변수`가 삭제된다면  
해당 객체에 접근하지 못하는 `unreachable`한 상황이 발생한다. 

이렇게 `접근하지 못하는 객체들`을 `가비지 컬렉터`가 주기적으로 제거한다. 

### Mark And Sweep 알고리즘 

![image](https://user-images.githubusercontent.com/64796257/195346122-0cdc575e-f1a3-4be8-aabe-8dcce2e3c34c.png)

`Mark and Sweep` 알고리즘은 GC가 동작하는 원리다.  
`루트`에서부터 해당 객체가 접근 가능한지에 따라 메모리를 해제할 지 말지 결정한다.  
위 그림과 같이 총 3가지 과정으로 나뉘어져 있다. 

- Mark 과정 : `Root`에서 부터 `그래프 순회`를 통해 연결된 객체를 찾아 각각 어떤 객체를 참조하고 있는지 찾아서 마킹한다. 
- Sweep 과정 : `Unreachable`한 객체들을 Heap에서 `제거`한다.
- Compact 과정 : 여러 부분에 `흩어진 객체들`을 `Heap의 시작 주소`로 모아 메모리가 `할당된 부분`과 `그렇지 않은 부분`으로 압축한다.   
  (종류에 따라 compact 과정은 수행하지 않을 수 있음) 

### GC의 대상이 되는 Heap 영역 

![image](https://user-images.githubusercontent.com/64796257/195347126-2d7d55df-8397-478d-be08-38eec16e1c58.png)

Heap 영역은 위와 같이 `Eden`, `Survival`, `Old generation`으로 나뉜다.  

### GC 동작 과정 

#### 1번째 

![image](https://user-images.githubusercontent.com/64796257/195347325-638a5bca-5b24-4239-9742-f628993f0b81.png)

객체가 처음 생성되고 `Heap영역의 Eden`에 `age-bit 0`으로 할당된다.  
이 `age-bit`는 Minor GC에서 살아남을 때마다 1씩 증가한다.  

#### 2번째 

![image](https://user-images.githubusercontent.com/64796257/195347578-3a9f153f-7bfa-43f6-b41c-3d4e536f2618.png)

작업이 진행되면서 `Heap 영역의 Eden 영역`에 객체가 다 쌓일 때 `Minor GC`가 `1번` 일어난다.  
`참조 정도`에 따라 `Survival 0 영역으로 이동`하거나 `회수`된다. 

#### 3번째 

![image](https://user-images.githubusercontent.com/64796257/195347982-4c672561-39c5-4fc2-bb4c-3d1027b515b1.png)

작업이 진행되면서 `Heap 영역의 Eden 영역`에 또 다시 객체가 쌓였다.  
`Young Generation(Eden + Survival) 영역에 있는 객체들`을 비어있는 survival 영역인 `survival1` 영역으로 이동시킨다.  
이동한 객체들은 `age`가 1씩 증가한다. 

#### 4번째 

![image](https://user-images.githubusercontent.com/64796257/195351138-e406ee3b-7dac-452a-b10e-178522eb4daa.png)

작업이 진행되면서 `Heap 영역의 Eden 영역`에 또 다시 객체가 쌓였다.  
`Young Generation(Eden + Survival) 영역에 있는 객체들`을 비어있는 survival 영역인 `survival0` 영역으로 이동시킨다.  
이동한 객체들은 `age`가 1씩 증가한다. 

#### 5번째 

![image](https://user-images.githubusercontent.com/64796257/195351405-94017b84-5224-4d96-b742-6e2676ab565c.png)

이 과정을 반복하면 `JVM이 설정`해 놓은 `age-bit 값`에 도달하게 될 것이다. (여기서는 3이라 설정했다)  
JVM이 설정한 age-bit에 도달하게 되면 `오랫동안 쓰일 객체`라고 판단하여 해당 객체를 `Old generation` 영역으로 이동시킨다.  
이 과정을 `Promotion`이라 한다. 

#### 마지막 

![image](https://user-images.githubusercontent.com/64796257/195351815-2c9541b9-3c40-4038-b984-aafad7966d0d.png)

위 과정들을 반복하다보면 `Old generation` 영역도 꽉 차면서 메모리의 허용치를 넘게 될 것이다.  
이때, 모든 객체들을 검사하여 참조되지 않은 객체들을 삭제하는 `GC`가 이뤄진다.  
이렇게 `Old generation` 영역의 메모리를 회수하는 GC를 `Major GC`라 한다.  

`Major GC`를 실행할 때 앞서 `GC`에 대해 언급한 것처럼 `GC 스레드`를 제외한 모든 스레드의 작업을 멈춘다.  
이런 작업이 빈번하면 프로그램의 성능에 악영향을 끼칠 수 있다. 













