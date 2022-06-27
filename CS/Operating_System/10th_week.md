## Multi-Processor Scheduling

<용어 정리>
1) `socket` : CPU를 지칭하는 하드웨어 이름 ⇒ 지금까지는 1 ~ 4개까지 장착할 수 있다.
2) `core` : 하나의 socket에서 쓰레드를 처리할 수 있는 장치 ⇒ 서버 프로세서의 경우 하나의 socket에 24개 정도까지 있다고 한다.
3) `SMT`(Simultaneous multi-threading) : 하나의 코어에서 여러 개의 쓰레드를 동시에 처리할 수 있도록 하는 기술(아직까지는 2개가 최대)

ex. 8C2T = 8개의 core 와 2개의 SMT = 1개의 socket이 8개의 core가 있고 각각의 core는 2개의 SMT가 있다.  
				     = 1 × 8 × 2 = 16개의 cpu(프로세싱 유닛)가 있다고 할 수 있다.
             
### 멀티 프로세서라는 구조가 나온 이유

이전까지는 시간이 지남에 따라 `프로세스를 처리하는 속도`가 `꾸준히 증가`했다.

그러다가 `4GHz 이후`로 더 이상 속도를 `발전시킬 수 없었`다. 왜냐하면, 빠른 동작에 의해 발생하는 열을 제 때 배출할 수 없었기 때문이다. 

그래서 `single-processor`에서 `mulit-processor`로 발전 방향을 선회해서 발전하게 되었다.

현재는 기본적으로 `Homogeneous 한 프로세서` 끼리 `SMP 방식`으로 동작하고 있다.

- Homogeneous processors : 동일한 종류의 프로세서(ex. 같은 Intel 프로세서)끼리 동작하도록 한다.
- SMP(Symmetric multiprocessing) : 모든 프로세서가 각각 작업을 하는 방식

cf) 이에 배치되는 방식이 Asymmetric multiprocessing: 하나의 프로세서가 전체적인 시스템을 관리하는 방법

● Processor Affinity : 프로세스는 현재 동작하고 있는 프로세서에 붙어있으려 한다.

만약에 4개의 CPU가 있는데 `하나의 CPU`에서 `D, E 프로세스`가 `CPU-bound` 동작을 하고

다른 `3개의 CPU`에서는 `A, B, C 프로세스`가 각각 동작하지만 `I/O bound` 동작을 해서 사실상 CPU가 아무 동작을 하지 않는다고 하자.

그러면, 이런 생각을 할 수 있다. 바쁘게 동작하고 있는 `D, E 프로세스`를 `다른 CPU로` 보낼 수는 없을까??  
(사실, 다른 CPU로 옮기는게 아니라 ready-q를 옮기는 동작이다.)

이때, 두 가지 정책을 통해 이러한 동작의 방향성을 결정하는데…

`Soft affinity`는 D, E 프로세스를 다른 CPU에서 동작하도록 보내줄 수 있는 정책이고  
`Hard affinity`는 절대 다른 CPU로 보낼 수 없도록 하는 정책이다.

프로세스를 다른 CPU에서 동작하도록 하면 `load balancing`  
하나의 프로세서에 일이 집중되면서 생기는 과도한 작업 부하를 최대한 줄일 수 있게 된다.

즉, `놀고 있는 CPU`에 `일을 할당`함으로써 하나의 CPU가 일하는 양을 줄임과 동시에 다른 프로세스가 동작하도록 할 수 있다. 

하지만, 프로세스를 다른 CPU로 옮기면서 동작할 때 생기는 단점은 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/147734710-e93cef1b-70bd-4f7a-8448-608dd954aeaa.png)

CPU A에서 동작하는 프로세스가 있다고 하자. 

효율적인 스케쥴링을 위해 ready_q를 CPU B에서 관리하는 ready_q로 바꿨다고 하자(이와 같이 바뀌는 과정을 migration이라 한다)

이렇게 `옮겨진 프로세스`가 `기존에 있던 CPU A`에서의 `M.M`에 접근하고자 한다면…

Cpu A에서 A의 M.M에 접근할 때보다 CPU B에서 A의 M.M에 접근하는 `속도가 훨씬 느리기` 때문에 이에 따른 `overhead가 발생`한다는 단점이 있다.

## Parallelism vs Concurrency
- Concurrency : single-core 시스템에서 `여러 개의 쓰레드`를 `굉장히 빠르게 switching` 해서   
  마치 4개의 쓰레드가 동시에 실행하는 것처럼 보이는 방식이 Concurrency이다. (그만큼 switching의 overhead가 발생한다)

![image](https://user-images.githubusercontent.com/64796257/147734823-1b3f7bd3-899f-49fe-8422-83fff5d0ca78.png)

- Parallelism : `여러 개의 쓰레드`를 `2개 이상의 core를 이용`해서 `동시에 동작`하는 방식이다.

![image](https://user-images.githubusercontent.com/64796257/147734830-1323ff40-77c3-4170-a064-b18205990ed3.png)

cf) 하나의 프로세서에 T1, T3 / T2, T4만 동작하고 있는데 이는 affinity을 적용했기 때문에 위와 같이 동작하는 것이다.

## Amdahl’s Law

Parallel : 동시에 할 수 있는 동작 / Serial : 순서대로 실행해야 하는 동작

전체 동작의 75%는 Parallel 이고 25%는 serial 하다고 하자.  

즉, 전체 동작의 75%는 동시에 할 수 있는 동작이고 25%는 순서대로 실행해야 하는 동작인 상황

이때, 코어의 개수를 N개라고 할 때 어느 정도까지 속도가 빨라질 수 있는지를 공식으로 표현한 것이 Amdahl’s Law다.

![image](https://user-images.githubusercontent.com/64796257/147735019-31067eb4-d797-4d48-9e2e-d11ef936a3f2.png)

## Real-Time Scheduling

1. Soft real-time systems : real-time이 조금 어긋나도 상관없는 시스템 ex. streaming player
2. Hard real-time systems : deadline 내에 무조건 task를 수행해야 하는 시스템 ex. 수술로봇, 무기 시스템

### Real-time 스케쥴링에 영향을 미치는 2가지 요소 

1) Interrupt latency : 말 그대로 `Interrupt`로 인해 생기는 `지연 시간`을 말한다.  

⇒ 인터럽트가 오면 그 인터럽트가 어떤 인터럽트인지 파악하고 ⇒ 이에 대한 context switching을 하는데 걸리는 시간을 의미한다.

⇒ 여기서 interrupt latency의 정확한 시간을 파악하는 것은 힘들기 때문에 Hard real-time 스케쥴링에서는 interrupt를 허용하지 않는다.

왜냐하면, deadline을 엄수하지 못하는 상황이 발생할 수 있기 때문이다.

2) Dispatch latency : `switching`을 하는 과정에서 발생하는 지연시간을 의미한다.

ex. `A 프로세스`가 어떤 파일을 열고 작업을 하다가 wait_q로 들어갔다고 하자.  
    이때, `B 프로세스`가 그 파일을 실행하려고 하는데 `A 프로세스`가 이미 열어놓은 파일이기 때문에 작업을 못하는 상황이 발생하는데 이러한 상황을 `conflict`라 한다.

그러한 conflict 상황을 OS가 알아내고 conflict를 해결하기 위해서는 B를 CPU에서 내리고 A를 CPU로 올려서 A가 그 파일을 닫을 때까지 기다려야 한다. 

이렇게 conflict를 처리하는데 어느 정도의 시간이 걸릴지 모르기 때문에 hard real-time에서는 dispatch를 허용하지 않는다.

## Priority-based Scheduling (real-time Scheduling은 아님)

: Real-time process에게 최우선순위를 부여해서 해당 real-time 프로세스가 deadline을 지킬 수 있는 확률을 높여주는 방식.  
다만 해당 real-time 프로세스가 deadline을 제때 지킬 수 있다는 보장은 없다.
 
![image](https://user-images.githubusercontent.com/64796257/147735633-a660f23b-a4d4-4b70-8622-6006712807dd.png)
  
대표적인 real-time scheduling인 Rate Monotonic Scheduling과 EDF에 대해 살펴보자.

### Rate Monotonic Scheduling : Rate(= 주기의 역수)를 기준으로 우선순위를 할당하는 스케쥴링

⇒ `주기`가 `짧`을수록 `우선순위`를 `높`게 책정하고 주기가 길수록 우선순위를 낮게 책정한다.

ex. P1의 burst time은 20, P2의 burst time 35라고 함  
![image](https://user-images.githubusercontent.com/64796257/147735713-e78e9dd0-5e27-4c83-947b-3068908e791e.png)

하지만, 이 방식은 hard real-time에 적합하지 않다. Deadline을 놓칠 여지가 많기 때문이다.

P1의 주기는 50 / busrt-time은 25  
P2의 주기는 80 / burst-time은 35

![image](https://user-images.githubusercontent.com/64796257/147735850-9f27cc4e-b034-4e4f-8e79-41fecbde4ca0.png)

P1이 첫 번째 주기에서 다 실행하고 나서 P2가 자신의 첫 번째 주기에서 실행하던 도중 

P1의 주기가 끝남과 동시에 P1이 자신의 두 번째 주기에서 동작하도록 했다. 

그렇게 P1이 다 동작하고 나서 P2는 자신의 첫 번째 주기에서 실행하지 못했던 동작을 마저하려는데 미처 동작을 다 하기 전에 자신의 주기가 끝나버린다. 

이러한 상황이 발생할 수 있어서 아래의 방법을 고안해냈다.

### Earlist Deadline First Scheduling (EDF) : deadline을 기준으로 우선순위를 책정하는 스케쥴링

![image](https://user-images.githubusercontent.com/64796257/147736033-8bd6c4ed-889b-4f5c-82e2-21c69d1580e0.png)

## IPC : InterProcess Communication

: 프로세스는 기본적으로 서로 독립적인 주소공간(Address Space)을 부여받기 때문에 협업을 위해서는 이와 같은 기법이 필요하다.

IPC의 모델로는 크게 2가지가 있다. 각각에 대해서 살펴보자.
1) Shared Memory

![image](https://user-images.githubusercontent.com/64796257/147736130-afc0020f-04c0-4db4-bf86-cfbb97e10e79.png)

2) Message Passing 

![image](https://user-images.githubusercontent.com/64796257/147736218-085b4979-dc3b-4897-80fb-ea5039edee30.png)

- Socket : 데이터를 주고받는 공간을 `네트워크`가 담당하는 방법
- Signal : 내용을 전달하는게 아니라 `어떤 시그널`인지 전달하면서 정보를 전달하는 방법
- RPC(Remote Procedure Calls) : 다른 프로세스에 있는 함수를 호출하는 서비스









