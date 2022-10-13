[출처](https://coding-factory.tistory.com/827)

[출처2](https://coding-factory.tistory.com/828)

## JVM(Java Virtual Machine) 이란??

`자바 가상 머신(JVM)`이란 자바 프로그램 실행환경을 만들어주는 SW다.  

```
자바 코드 ⇒ 컴파일 ⇒ .class 바이트 코드 ⇒ JVM에서 실행
```

### Java는 특정 플랫폼에 영향을 받지 않는다. 

`JVM`의 가장 큰 장점은 `하나의 바이트 코드(.class)`를 가지고 `모든 플랫폼에서 동작`하도록 할 수 있다는 거다. 

ex) C언어  
![image](https://user-images.githubusercontent.com/64796257/195471890-4e46d107-1ed2-4fbb-900d-a4099b3d5aaf.png)

C언어로 `Test.c`를 작성했다고 하자. 

- 윈도우 컴파일러를 사용해서 컴파일 하면 `Test.exe`가 생성된다. ⇒ 다른 플랫폼이 아닌 오직 `윈도우`에서만 실행할 수 있는 파일이다.

이와 같이 C와 C++은 `컴파일 플랫폼`과 `타켓 플랫폼`이 다르면 프로그램이 동작하지 않는다.  
만약, `Test.exe`를 Linux에서 실행하고 싶다면 Linux 운영체제에 맞는 `새로운 실행 파일`을 만들어야 한다

ex) Java  
![image](https://user-images.githubusercontent.com/64796257/195472154-bc1ab8e1-e3c5-430e-b48c-ce1ac0c75efe.png)

Java 언어로 작성된 `Test.java`를 컴파일하면 `Test.class`가 생성된다.  
생성된 `바이트 코드`를 각 운영체제에 설치되어 있는 `JVM`이 OS에 맞는 실행파일로 바꿔준다.  

Java는 C언어와 달리 각각의 플랫폼에 맞게 컴파일을 따로따로 해줘야 할 필요가 없다.

이렇게 Java는 컴파일된 바이트 코드를 가지고 `어떤 JVM`에서도 동작시킬수 있기 때문에 플랫폼에 의존하지 않는다.  
하지만, `JVM`은 플랫폼에 의존적이다. 

### JVM은 플랫폼에 의존적이다. 

즉, 윈도우의 JVM, 리눅스의 JVM은 다르다.  

Java로 작성된 모든 프로그램은 `JVM`에서만 실행될 수 있어서 `Java 프로그램을 실행`하려먼 `JVM이 반드시 설치되어` 있어야 한다. 

### Java 프로그램의 실행 과정과 JVM 

![image](https://user-images.githubusercontent.com/64796257/195473702-9c311feb-0421-48ee-be2c-74098f2b149e.png)

1) Java로 `.java 코드`를 작성 
2) 파워쉘 또는 터미널에 있는 `자바 컴파일러 javac`에 컴파일 명령
3) `.class 파일(바이트 코드)` 생성 
4) `클래스 로더`를 통해 `JVM Runtime Data Area`로 로딩됨 & 로딩된 `.class 파일`을 `JVM`에게 전달
5) 프로그램을 실행할 때 `JVM`이 `바이트 코드`를 읽어들이면서 그때그때 `기계어`로 해석한다 

## 바이트 코드를 읽는 방식 

바이트 코드를 한 줄, 한 줄 해석하고 실행하는 `인터프리터(Interpreter)` 방식을 초기에 사용했다.  
하지만, 속도가 매우 느리다는 단점이 있다. 

이를 보완하기 위한 방법이 `Just In TIme` 컴파일러다.  
`바이트 코드`를 `JIT 컴파일러`를 이용해 프로그램을 실행하는 시점(바이트 코드를 실행하는 시점)에 `OS에 맞는 Native Code로 변환`하여 실행 속도를 개선했다. 

하지만, `바이트 코드`를 `Native Code로 변환`하는데도 비용이 소요되므로  
인터프리터 방식을 사용하다가 일정 기준이 넘어가면 JIT 컴파일 방식으로 명령어를 실행한다. 

### JIT(Just In Time) 컴파일러 

![image](https://user-images.githubusercontent.com/64796257/195475006-c59a103b-6b58-4823-b01f-87f8b55af5c3.png)

JIT 컴파일러는 같은 코드를 매번 해석하지 않고 프로그램을 `실행할 때 컴파일`하면서 해당 코드를 `캐싱`한다.  
이후에는 `바뀐 부분`만 컴파일하고 `나머지`는 캐싱된 코드를 사용한다. 

이렇게 JIT 컴파일러는 `OS에 맞게 .class 파일로 변환`하여 실행하기 때문에 Interpreter에 비해 10~20배 정도의 좋은 성능을 보이고 있다.

## JVM 내부 구조와 메모리 구조 

### JVM의 동작방식 - 좀 더 자세하게 살펴보자 

![image](https://user-images.githubusercontent.com/64796257/195475420-4720befd-59e2-4582-9691-b69b7578a369.png)

1. `자바로 개발된 프로그램`을 실행하면 JVM은 OS로부터 `메모리를 할당`한다. 
2. `자바 컴파일러 javac`가 `.java`를 `.class`로 컴파일한다. 
3. `Class Loader`를 통해 `JVM Runtime Data Area`로 로딩한다.
4. `Runtime Data Area`에 로딩된 `.class(바이트 코드)` 들은 `Execution Engine`을 통해 해석한다.
5. 해석된 바이트 코드는 Runtime Data Area의 각 영역에 배치되어 수행된다.   
    이 과정에서 `Execution Engine`에 의해 `GC의 작동`과 `스레드 동기화`가 이뤄진다. 

이제 JVM를 구성하는 `구조`에 대해서 하나씩 살펴보겠다. 

### JVM의 구조 

#### 클래스 로더(Class Loader) 

![image](https://user-images.githubusercontent.com/64796257/195476696-69e17eed-54f5-4256-ab30-a870c1fb2c85.png)

자바는 동적으로 클래스를 읽어온다. 때문에 프로그램이 실행 중인 `runtime`에서 `모든 코드가 JVM과 연결`된다.  
이렇게 `동적으로 클래스를 로딩`해주는 역할을 하는 것이 `클래스 로더`이다. 

자바에서 소스를 작성하면 .java파일이 생성되고 .java소스를 컴파일러가 컴파일하면 .class파일이 생성되는데  
`클래스 로더`는 `.class 파일`을 묶어서 JVM이 OS로부터 할당받은 메모리 영역인 `Runtime Data Area로 적재`한다. 

#### 실행 엔진(Execution Engine) 

`클래스 로더`에 의해 JVM으로 로드된 `.class 파일`들이 `Runtime Data Area의 Method Area`에 배치된다.  

배치된 이후에 `JVM`은 `Method Area의 바이트 코드`를 `실행 엔진`이라는 런타임 모듈에 제공해서 `바이트 코드를 실행`시킨다.  

#### [가비지 컬렉터](https://github.com/sponbob-pat/TIL/blob/main/GoodToKnow/GarbageCollection.md)

#### 런타임 데이터 영역(Runtime Data Area) 

![image](https://user-images.githubusercontent.com/64796257/195477900-c658e1bc-f0f3-4aa9-a0af-5d5bda8a0a02.png)

런타임 데이터 영역 : JVM의 메모리 영역. 자바 애플리케이션을 실행할 때 사용되는 데이터들을 적재하는 영역. 

- 모든 스레드가 공유해서 사용하는 영역 : Heap area, Method area
- 스레드 마다 하나씩 생성해서 사용하는 영역 : stack area, PC register, Native Method Stack 

각 영역에 대해서 살펴보자. 

1) 메서드 영역 (Method Area) 

클래스 멤버 변수의 이름, 데이터 타입, 접근 제어자 정보와 같은 각종 필드 정보들과  
메서드 정보, 데이터 Type 정보, Constant Pool, static변수, final class 등이 생성되는 영역

2) 힙 영역 (Heap Area)  
- `new 키워드`로 생성된 객체와 배열이 생성되는 영역
- 주기적으로 `Garbage Collector`가 제거하는 영역 

⇒ [관련 내용](https://github.com/sponbob-pat/TIL/blob/main/GoodToKnow/GarbageCollection.md)

3) 스택 영역 (Stack Area) 

지역변수, 파라미터, 리턴 값, 연산에 사용되는 임시 값 등이 생성되는 영역

4) PC Register 

Thread가 생성될 때마다 생성되는 영역. 
프로그램 카운터(PC), 즉 `현재 스레드`가 `실행되는 부분의 주소`와 `명령`을 저장하고 있는 영역

5) Native Method Stack 

- 자바 이외의 언어(C, C++, 어셈블리 등)로 작성된 `네이티브 코드`를 실행할 때 사용되는 메모리 영역.  
  일반적인 C 스택을 사용

- 보통 `C/C++ 등의 코드`를 `수행하기 위한 스택`을 말하며 (Java Native Interface; JNI)  
  자바 컴파일러에 의해 변환된 자바 바이트 코드를 읽고 해석하는 역할을 하는 것이 자바 인터프리터(interpreter)다




















