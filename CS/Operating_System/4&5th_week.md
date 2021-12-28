### OS Design & Structure (OS 설계와 구조)

(1) Design Considerations 

- View of OS Services ⇒ OS가 아닌 모든 SW들은 user program이라 하겠다. 따로 System program이라는 구분을 두지 않았다.

![image](https://user-images.githubusercontent.com/64796257/147516418-d5db34ec-23b5-43cb-8b35-60a5367cd0e6.png)

- GUI, Batch, Command Line은 OS 커널 영역에 있는 것들이 아니다. ⇒ 이들은 User area에서 사용하는 User program이라 생각하면 된다.

- Kernel은 시스템 콜 아래에 있는 부분이다. OS에서는 Kernel에 속한 여러 서비스들 중에서 CPU와 Memoryu에 대해서 알아볼 것이다.

그림을 보면 알 수 있듯이 Kernel area와 user area는 각자 다른 영역의 메모리를 차지하고 있다.

user mode에 해당하는 프로그램은 Kernel area에 접근할 권한을 가지고 있지 않는다. 오로지 `시스템 콜`을 통해서만 Kernel area에 있는 서비스들을 실행할 수 있다.

(반면, kernel mode에 해당하는 프로그램은 user area에 접근할 권한을 가진다)

- Design Consideration : OS는 규모가 매우 크고 복잡한 SW이기 때문에 어떤 구조를 통해서 동작이 이뤄질지 신중히 고려해야 한다.

⇒ 좋은 설계가 이뤄지만 개발(develop), 수정 및 디버깅(modify & debug), 유지 보수(maintain), 확장(extend)을 하기가 유리해진다.

설계를 위해서는 아래와 같은 다양한 요소들을 고려한다.

| 요소 | 상세설명 |  | 요소 | 상세설명 |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| Fairness  | 자원 배분의 공평성  |  | Real-time  |  |
| High performance  | 높은 성능  |  | Usability  |  |
| Scalability  | 규모의 확장성  |  | Extensibility  | 기능의 확장성  |
| Stability/Reliability/Robustness  | 코드의 신뢰도와 연관됨  |  | Secruity/Integrity | 보안 |
| Compatibility  | 호환성 |  | Energy consumpution  | 배터리 사용량 |

cf) 특수 목적을 위해서 OS를 만들었다면 위에서 제시한 요소들 보다 더 중요한 요소가 있을 것이다. 그에 맞게 OS를 설계해주면 됨

그리고 위 요소들을 모두 동시에 만족시킬 수 없다. 그래서 주어진 조건 하에서 요소들의 균형을 취해줘야 하는데 이를 `trade-off`라고 한다.

ex. 공평성을 높인다면 그만큼 성능이 저하될 수 있다.

- Policy & Mechanism(정책 & 기법) 

⇒ 정책은 설계를 하는 일종의 방향 설정

⇒ 기법은 그러한 방향을 어떻게 구현할지 결정하는 것

- Layering & Modularity : 둘 다 OS의 복잡도를 낮추기 위한 방법

⇒ Layering : 대규모의 작업을 각각의 계층으로 역할을 구분해서 그 작업을 같이 할 수 있게 함으로써 복잡도를 낮추는 방법. 
이때, 각 Layer는 자신과 인접한 Layer 끼리 통신할 수 있다.

⇒ Modularity : 프로그램을 분할하는 방법. 마치 Kernel area에서 수행할 수 있는 service들을 다양하게 세분화한 것을 생각하면 된다. 

⇒ 보통 대규모의 작업을 할 때 각각의 역할 분담을 Layering 해서 서로의 역할을 나눠서 개발함과 동시에 각각의 Layer에서 Modularity를 진행함으로써 OS의 복잡도를 낮춘다. 

### System call(시스템 콜) : 시스템 호출. 즉, OS를 호출하는 걸 말한다.

시스템 콜을 이용하는 이유는 user mode에서 동작할 수 없고 kernel mode에서만 실행할 수 있는 프로그램을 실행하기 위해서다.(ex. Privileged instruction)

보통의 user program에서는 직접 시스템 콜을 이용하지 않고 High-level Application Programming Interface(API)를 이용한다. 

- 시스템 콜의 전반적인 과정

![image](https://user-images.githubusercontent.com/64796257/147517588-da7a892a-5e28-4254-96cd-2c7e1cf41c0e.png)

User application에서 C Library를 통해서 open이라는 시스템 콜을 했다. 

그러면, CPU에서 시스템 콜을 했다는 것을 인지하고 user mode를 kernel mode로 바꿔준다.

그 다음에 CPU가 인지한 시스템 콜에 따라 어떤 동작을 해야 할 지 정해놓은 코드를 모아놓은 테이블에 접근한다.

여기서는 open이라는 시스템 콜을 했으므로 open이라는 시스템 콜이 호출되었을 때 실행하는 코드를 테이블에서 찾고나서 그 코드를 실행한다.

그렇게 코드를 실행하고 나서 OS는 kernel mode 에서 user mode로 변경시키고 수행중이던 user application에서 open 다음에 있던 코드를 실행한다.

이때, 시스템 콜을 이용하기 위해서 `Trap(SW Interrupt)`를 사용한다.

Trap을 사용하는 이유는 시스템 콜에서 trap을 사용하면 user mode에서 kernel mode로 전환할 수 있게 되면서 OS에서만 동작할 수 있는 여러 프로그램들을 실행할 수 있기 때문이다.

(INT 0x80이라는 인터럽트는 `시스템 콜`을 의미하는 인터럽트로 정의되어 있다)

![image](https://user-images.githubusercontent.com/64796257/147517871-3b8f068b-e776-411f-af8d-2cdc63457f76.png)

IDT = Interrupt Descriptor Table : 인터럽트를 어떻게 처리할 지 정의한 테이블(Main Memory의 kernel area에 있음)

전체적인 과정을 살펴보자.

fork()라는 High-Level API를 통해서 시스템 콜을 사용했다. 이때, fork()를 구현한 라이브러리에서 INT 0x80 이라는 코드를 실행한다. 

그리고 해당 코드의 바로 앞부분에서 mov1 2, %eax를 통해 %eax에 2를 저장했다.

그런 다음 IDT로 가서 해당 인터럽트가 어떤 역할을 하는지 찾아본다. 테이블을 찾아본 결과 INT 0x80이라는 인터럽트는 `시스템 콜`을 의미하는 인터럽트라는 것을 알게 된다. 

그 다음에 INT 0x80 인터럽트를 처리하는 인터럽트 핸들러를 호출한다.

시스템 콜 핸들러를 보면, call ~~ 부분이 있는데 여기에 앞서 저장한 %eax값을 이용한다. 2라고 저장했었다.

이제 여러 개의 시스템 콜들을 목록화한 시스템 콜 테이블(sys_call_table)에서 2번 시스템 콜인 sys_fork()를 호출한다. 

그렇게 해당 함수의 동작이 끝나면 위에서 표시한 INT 0x80 다음인 `여기` 부분으로 돌아간다. 그렇게 fork() 함수를 다 수행하고 나서 user process로 돌아간다. 

![image](https://user-images.githubusercontent.com/64796257/147518344-15931732-da2a-4a14-96c7-e0c510c7d41f.png)

### Kernel Structures(커널 구조) 

- 핵심 내용 

1) Monolithic kernel 

커널의 모든 서비스(또는 코드)가 `같은 주소 공간`에 위치한다 

= 모든 커널 프로그램은 하나의 프로그램이다 

= 모든 코드가 하나의 공간에서 하나의 프로그램으로 실행된다. 

2) Micro kernel 

커널의 모든 서비스(또는 코드)가 각각의 `독립된 주소 공간`에 위치한다. 

= 각각의 커널 프로그램은 서로 다른 프로그램이다.

= 커널의 아주 작은 기능만 남기고 나머지 프로그램은 각자 다른 프로그램으로써 실행시킨다.

- 주소 공간(Address Space) : 논리적인 실체나 물리적인 실체에 대응되는 주소의 범위를 정의한 공간

프로그램 A,B가 있다고 하자. 이때, 각각의 프로그램에 `가상 주소 공간(Virtual Address Space)`을 별도로 부여한다. 
이는 물리적으로 존재하는 메인 메모리가 부여하는 주소공간과 대응되는 별개의 존재이다.

마치 아파트 단지가 있는데 프로그램이 하나씩 존재할 때 마다 해당 프로그램에게 아파트 1개동을 부여하는 것과 같은 맥락이라고 보면 되겠다. 
A는 101동, B는 102동을 부여받은 것이다.

그래서 A 프로그램 안에서는 B 프로그램을 만날 수 없다. 처음부터 다른 공간을 받고 시작했기 때문이다. 마찬가지로 B 프로그램 안에서는 A 프로그램을 만날 수 없다. 

이 내용이 메모리를 관리하는 기본적인 개념인 `가상 주소 공간`이고 이를 통해서 프로세스들을 서로 `분리` 시켜준다. 

- IPC (Inter-Process Communication) : 프로세스들 간에 이뤄지는 의사소통 ex. socket, signal, shared memory 

프로세스들은 서로 분리되어 있지만 각 프로세스들 간에 통신할 수 있는 방법은 있어야 하기에 이를 OS가 마련해주었다.

다만, 프로그램 내에서 데이터를 주고받는 것 보다는 속도가 느릴 수밖에 없다. 

A 프로그램 내에서는 프로그램 안에서 정의한 함수, 변수의 내용을 곧바로 가져올 수 있다. 

하지만, A 에서 B 프로그램에 있는 내용을 가져오려면 일단 OS를 불러서 OS에게 B 프로그램에 있는 함수에 필요한 인자를 전달하고 그 인자를 바탕으로 B 프로그램에 있는 함수를 실행하고 나서 그 결과를 다시 A에게 전달해야 한다. 

이렇다보니 A, B 프로그램 사이에서 데이터를 주고 받으면 시간이 많이 걸릴 수 밖에 없게 된다.

### Monolithic Kernel

⇒ 커널의 모든 서비스가 같은 주소 공간에 위치해서 서비스 간에 데이터를 주고받거나 서버 호출이 같은 주소공간에서 일어나기 때문에 속도가 빠르다.

⇒ 애플리케이션은 자신의 주소공간에 커널 코드 영역을 매핑해서 커널 서비스를 이용한다.

앞서 프로그램마다 아파트 1개 동을 부여한다고 했다. 여기서 아파트 맨 위층에 kernel이 살고 있다는 것을 의미한다. 

그래서 kernel에 접근할 수는 있지만 cpu에게 mode를 바꿔달라고 요청하고 나서야 접근할 수 있다. 중요한 건 kernel과 프로그램이 같은 주소 공간 안에 있다는 것이다. 

그림을 같이 보겠다. 

![image](https://user-images.githubusercontent.com/64796257/147519443-f32a7a53-2347-47e3-91b5-904c990edbd1.png)

컴퓨터에서 프로그램 4개가 동시에 실행되고 있는 모습이다. 

그리고 물리적으로 가지고 있는 메모리의 용량과 상관없이 32bits 환경에서는 각각의 프로그램에게 4GB의 가상 주소 공간을 부여한다. 

이때, 3G ~ 4G 영역은 kernel이 사용하고 그 윗부분은 user application이 사용한다.

즉, 0G ~ 3G는 user area이고 3G ~ 4G는 kernel area라는 것을 알 수 있다. 그런데 kernel은 하나인데 어떻게 모든 프로그램마다 각각 존재할 수 있는 걸까? 

8GB 메모리 공간이 있다고 하자.

![image](https://user-images.githubusercontent.com/64796257/147519580-7b135dce-ecb4-4ae6-899d-e97accdb7cf9.png)

프로그램 A와 B는 자신이 위치한 물리 메모리의 주소는 모른채 자신이 취한 주소가 0~2라고 생각한다.

A가 kernel에 접근하려 한다. A의 입장에서는 주소 2.5에 접근한다고 하자.
그러면 CPU는 A의 입장에서 주소 2.5에 접근한다는 내용을 OS가 물리적으로 위치한 주소 7.5에 접근한다고 판단해서 이에 대한 매핑을 한다.

B도 마찬가지다. B의 입장에서 주소 2.8에 접근한다고 하자. 
그러면 CPU는 이를 OS가 물리적으로 위치한 7.8에 접근한다고 판단해서 이에 대한 매핑을 한다. 

즉, 하나의 OS를 두고 각각의 프로그램이 필요할 때마다 공유하는 방식으로 사용하고 있다. 그래서 IPC 보다는 속도가 빠르다.

속도를 정리하면

(유저 영역 내에서 상호작용하는 속도) > (유저 영역과 kernel 영역에 상호작용하는 속도) > (다른 프로그램에 있는 유저 영역과 상호작용하는 속도) 
순으로 정리할 수 있다.

이렇듯 Monolithic kernel 방식은 모든 애플리케이션과 커널 서비스가 같은 주소공간에 위치하기 때문에 데이터 전달 속도가 빠르다.

### Micro Kernel 

하지만, 오랜 시간이 지나면서 kernel의 코드 크기가 수천만 ~ 억 line 이상으로 거대해지고 kernel 코드가 모든 프로그램에 유기적으로 연결되면서 
일부분의 수정이 전체에 영향을 끼치게 되었고 코드의 크기가 커지면서 유지/보수가 어려워졌다. 또한 한 모듈의 버그가 시스템 전체에 영향을 끼칠 수도 있게 되었다. 

이러한 Monolithic이 가지고 있는 단점을 보완하기 위해서 고안된 기술이 Micro Kernel이다. 

가장 핵심적인 kernel의 기능인 IPC만 남겨두고 나머지는 모두 user program으로 옮겨놓았다. 

![image](https://user-images.githubusercontent.com/64796257/147520117-1268401c-3cc5-41fd-af97-113418c3042e.png)

위 그림을 예로 들면 각각의 애플리케이션에서 kernel은 IPC 기능만을 수행하고 memory management, CPU scheduling과 같은 여러 작업들은 user area로 올렸다. 

그렇게 해서 각각의 module로 만들었다. 이러한 동작들은 kernel mode에서만 동작할 수 있는 프로그램들이 되겠다.

이렇게 각각의 기능을 수행하는 모듈을 `서버`라고 하고 서버들은 독립된 프로세스로 구현된다. 그래서 커널의 동작은 서버들 간의 통신을 통해서 수행된다. 

Micro kernel의 장점은 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/147520273-fcfda37b-3f7b-40db-92d1-4cbb3c717ec7.png)

하지만, 구조적으로 Monolithic kernel 보다 낮은 성능을 보일 수 밖에 없다는 단점이 있다. 

그림과 같이 살펴보자. 

![image](https://user-images.githubusercontent.com/64796257/147520311-97d0980c-b227-48a8-b782-9f7abde6c50a.png)

그림을 보면 같은 동작을 수행하더라도 왜 micro kernel이 monolithic kernel 보다 더 느릴수 밖에 없는지 확인할 수 있다. 

그래서 micro kernel은 현재 많이 사용하고 있지는 않고 monolithic을 기반으로 해서 micro kernel에서 연구한 부분을 일부 이용해서 사용하고 있다.
