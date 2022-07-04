### CPU: Synchronization 
동기화를 간단하게 나타내는 예제를 소개하겠다.

생산자-소비자 문제 : 공유하고 있는 buffer에 대해 한쪽에서는 생산해서 자료를 버퍼에 넣고 다른 한쪽에서는 소비를 위해 버퍼에 있는 자료를 꺼낸다.  
   
이때, 버퍼에 몇 개의 데이터가 있는지를 counter를 통해서 표시하고 관리한다.

![image](https://user-images.githubusercontent.com/64796257/147861977-7f1f0b70-4766-47f7-80d7-b25179b3cc0a.png)

producer 내용: counter가 buffer_size랑 똑같아질 때  
즉, 버퍼가 꽉 차게 되면 여러 작업을 하고 나서 counter의 값을 1씩 증가시킨다. 왜냐하면, 버퍼에 자료가 하나 늘어났기 때문이다.

consumer 내용: counter가 0이 된다면 동작을 하지 않아야 한다. 왜냐하면 꺼내 쓸 데이터가 없기 때문이다.  
만약에 counter가 0이 아니라면 데이터가 있다는 거고 그걸 다 쓰고나면 counter의 값을 1씩 감소시키면 된다.

여기서 counter ++, counter --는 CPU에서는 아래와 같이 처리한다.

![image](https://user-images.githubusercontent.com/64796257/147862078-5c80651b-2201-49cc-8914-ba003f539e93.png)

이렇다 보니 counter++, counter — 를 처리할 때 CPU에서 실행하는 코드의 순서에 따라서 counter의 값이 바뀔 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/147862080-acabb9e8-955a-4f3e-9298-f040563b1a2c.png)

이와 같이 공유자원을 두고 여러 개의 프로세스를 같이 사용할 때 동기화 문제가 발생할 확률이 있는 상황을 `race condition`이라 한다.

즉, 어쩔 때는 원하는 대로 동작하지만 어쩔 때는 또 원하지 않는 방향으로 코드가 동작할 수 있다는 의미다.

이때, counter++, counter-- 와 같이 공유하고 있는 자원을 변경하는 부분을 `critical section`이라 한다.  
⇒ 이 부분 때문에 동기화 문제가 발생하는데  
이를 해결하기 위해서 어느 한쪽에서 critical section이 동작하는 동안 다른 쪽에 있는 critical section이 동작하지 못하도록 보호해야 한다.

⇒ 이를 구현하기 위해서 entry section, exit section이라는 개념을 사용한다.

#### Entry section & Exit section

ex. 
![image](https://user-images.githubusercontent.com/64796257/147862122-b51f09c3-2ee0-409f-8fdb-0b98620d4692.png) 

대략적으로 예시를 만들어보면 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/147862126-1617cced-bbc8-4987-b5d0-369346762db9.png)

remainder section: critical section을 제외한 나머지 부분

이러한 critical section을 보호하기 위한 3가지 조건이 있다. (가정) 모든 프로세스들은 멈추지 않고 계속 진행한다.

1. `Mutual Exclusion(상호 배타성)` : `P_i 프로세스`가 `critical section을 수행`하는 동안 `다른 프로세스`들은 `critical section`은 `수행하지 않`는다.

2. `Progress` : 여러 프로세스들이 아무도 critical section을 `동작하지 않을 때`    
       `어떤 프로세스`가 `critical section`에 들어가려고 한다면, 해당 프로세스는 critical section에 `들어갈 수 있어야` 한다.

3. `Bounded Waiting` : `대기시간`이 `유한`해야 한다. 대기 시간이 무한정으로 길어지면 안된다.

ex) `turn`의 값은 `0`으로 초기화.  
   `turn = 0`일 때 `P0`에서 `critical section`이 실행된다고 하자.  
   `turn = 1`일 때 `P1`에서 `critical section`이 실행된다고 하자.(critical section = c.s)

![image](https://user-images.githubusercontent.com/64796257/147862149-256b677c-7e1b-4b41-9ac1-d3f51dde42b4.png)

대략적으로 살펴보면,
1) `turn = 0`이기 때문에 `P0`에서 `c.s`을 실행한다. 
2) `P0의 c.s`이 다 끝나면 `turn = 1`이 되면서 `P1의 c.s`이 실행될 수 있다.
3) `P1에서 c.s`를 다 `수행`하고 나면 `turn = 0`으로 바뀌면서 다시 `P0의 c.s`를 수행할 수 있다.

위와 같은 동작을 의도해서 만든 건데 이 동작은 과연 3가지 조건을 만족할까??
1) `Mutual Exclusion` : 어느 한 쪽이 critical section에 들어갔다는 건 `turn값`이 `0또는 1`이라는 의미. 따라서, 다른 한 쪽에서는 들어가지 못한다. 따라서, `만족`	

2) `Bounded Waiting` : 계속해서 두 프로세스가 `번갈아가면서 실행`되기 때문에 어느 한 쪽의 프로세스가 무한히 기다리는 일은 없다.

3) `Progress` : `P1`을 보면, `P0에서 c.s`를 수행하고 나서 `turn = 1`로 바뀌고 나서야 `P1의 c.s`가 `수행`된다.  
	      즉, `P1의 의지`와 `상관없이` P1의 c.s를 수행할 수 있는 상황이 되더라도   
	      P0에서 turn값을 바꾸지 않아서 P1의 c.s를 수행하지 못하는 상황이 발생할 수 있다. 따라서, progress는 만족하지 못한다.

이걸 어느 정도 해결하기 위해서 아래와 같이 코드를 작성했다.  

![image](https://user-images.githubusercontent.com/64796257/147862190-6913c48a-b7cf-4b64-8906-b87b4732efa4.png)

`flag`라는 `boolean 배열`을 만들어서 flag[0] = flag[1] = False를 초기값으로 가지도록 했다.

1) `flag[0] = true`라면 `P0`는 `c.s를 수행`할 수 있고 
2) `flag[1] = true`라면 `P1`이 `c.s를 수행`할 수 있다.

`Flag[0] = True`라면 P0는 c.s에 `들어갈 수` 있는데 이와 동시에 `flag[1] = True`라면 `못 들어가`게 했다.  
P0 에서 c.s에 접근할 때 P1에서는 c.s에 접근하면 안되기 때문에 while(flag[1])이라는 조건문을 걸었다.

이 프로세스는 3가지 조건을 만족할지 살펴보자.
1) `Mutual Exclusion` : 두 프로세스에서 동시에 c.s에 들어갈 일은 없다.  
왜냐하면 `flag[0] = flag[1] = false`여도… `각 프로세스의 1번 문장`은 무조건 지나고 나서 while문의 조건문을 만나기 때문에  
3)의 상황이 아닌 이상 `flag[0] ≠ flag[1]`이 성립한다.
⇒ 따라서, 1번 조건은 `만족`한다.

2) `Bounded waiting` : `flag[0] = flag[1] = false`인 상황에서 `P0`가 `먼저 자신의 1번 문장`을 실행했다고 하자.  
① `flag[0] == TRUE` & `flag[1] == False`이므로 `P0의 c.s`을 `수행`한다.  
② `flag[0] == False`가 되고 나머지 부분을 수행하던 와중에 `P1`는 `자신의 1번 문장`을 `실행`한다.  
③ `flag[0] == False` & `flag[1] == True`이기 때문에 `P1`의 `critical section`을 `수행`할 수 있다.

이러한 상황이 `번갈아` 일어나기 때문에 무한정으로 기다리는 상황은 발생하지 않는다.  
⇒ 따라서, 2번 조건은 `만족`한다.

3) `Progress` : P0와 P1이 실행하는데 두 프로세스가 각각의 1번 문장을 실행했다.

① `while문`을 만나면 `flag[0] = flag[1] = True`이기 때문에 `두 프로세스`는 `c.s`을 `수행하지 못`한다. 이를 맞잡고 있다고 표현하고 `데드락`이라 한다.

cf) 데드락(DeadLock) : 두 개 이상의 프로세스가 동시에 서로 다른 프로세스가 끝나기를 기다리는데 이러한 기다림이 무한히 발생하는 상황

어떻게 해결해야할까?? 이를 `해결`하는 대표적인 방법이 `Peterson’s Solution`이다.

초기화 상황 : turn = 0, flag[0] = flag[1] = False 

`flag[0] = true` 이고 `turn = 0`이면 ⇒ P0의 c.s를 수행  
`flag[1] = true` 이고 `turn = 1`이면 ⇒ P1의 c.s를 수행

![image](https://user-images.githubusercontent.com/64796257/147862254-6fe6869c-68a5-49fc-85bc-5e432b18be56.png)

turn = 0이면 0번 프로세스가 들어갈 차례 / turn = 1이면 1번 프로세스가 들어갈 차례

flag[0]과 flag[1]의 의미는 각각의 프로세스가 `c.s에 들어갈 의지`를 나타낸다고 비유하면 이해가 쉽다.

① `P0`를 `먼저` 실행한다고 하면, `flag[0] = true`이다. 즉, 0번 프로세스는 c.s에 들어갈 의지가 있다.  
② `turn = 1`이 되면서 `1번 프로세스`가 들어갈 수 있도록 차례를 주었다.  
③ 하지만, `flag[1] = false` & `turn = 1`이기 때문에 `P0의 c.s`를 수행한다. ⇒ 수행 후 `flag[0] = false`가 되었다.

④ `P1`가 `실행`되었다. `flag[1] = true`가 되었다. 즉, 1번 프로세스는 c.s에 들어갈 의지가 있다.  
⑤ `turn = 0`이 되면서 `0번 프로세스`가 들어갈 수 있도록 차례를 주었다.  
⑥ 하지만, `flag[0] = false` & `turn = 0`이기 때문에 `P1의 c.s`를 수행하게 된다. ⇒ 수행 후 `flag[1] = false`가 되었다.  

이러한 상황이 각각의 프로세스에서 번갈아가면서 일어난다.

따라서, bounded waiting, mutual exclusion의 조건은 `Peterson's solution`에서도 여전히 만족한다.

그렇다면, `progress`는? P0, P1 둘 다 1번 문장을 `동시에 실행`했다고 하자. 그러면 `flag[0] = flag[1] = true`가 된다.
이제 2번 문장을 실행하게 되는데 두 프로세스 중에서 `P0의 2번 문장`을 `먼저 수행`했다고 하자. 그러면 `turn = 1`이었다가 `P1의 2번 문장`을 실행하면서 `turn = 0`이 된다.

실행결과 `turn = 0`이기 때문에 `P0의 c.s`을 수행한다. 반대로 P1의 2번 문장을 먼저 수행했다고 하면 P1의 c.s을 수행하게 된다. 

따라서, 한 쪽이 실행하지 않으면 다른 한 쪽이 실행할 수 있는 상황이 만들어졌으므로 `progress 조건`도 `만족`한다.

이와 같은 `SW기반의 해결책`은 HW차원의 지원이 있지 않으면 제대로 동작하기 힘들다.  
지금부터는 동기화를 위한 하드웨어 차원에서 어떤 기능을 제공하는지 살펴볼 것이다.

#### 하드웨어 차원에서의 기능

1. Atomic 방식 

![image](https://user-images.githubusercontent.com/64796257/147862360-5638b7da-bf10-4319-85cb-41910e0950e4.png)

```
turn = 1; 
```

위 코드를 `instruction 단위`로 쪼개서 보면 앞에서 counter++가 수행되듯이 `여러 줄의 instruction`이 수행된다.

ex. ![image](https://user-images.githubusercontent.com/64796257/147862379-1af29870-2cc7-4bd3-80a7-494961be5c40.png)

counter++는 한 줄에서 실행되는 것처럼 보이지만 이와 같이 3줄의 instruction이 동작하는 것이다.

즉, 다른 프로세스에서의 동작에 따라 `각각의 instruction`이 `따로따로 수행`될 수 있다.

그래서 하드웨어(CPU)에서 저 3줄이 `한꺼번`에 `실행`될 수 있도록 기능을 제공한다 = Atomic 하게 instruction을 제공한다고 한다.  
이를 기반으로 c.s 해결책을 짤 수 있다.

2. 하드웨어 명령어 

- test_and_set() 

![image](https://user-images.githubusercontent.com/64796257/147862388-57f45dfe-d1a7-4027-aeda-799bfa7fb4e2.png)

- 코드 설명
boolean 자료형을 가리킬 수 있는 `target`이라는 포인터 변수를 매개변수로 받는다. 
`rv값`은 `기존에 target이 가리킨 값`을 `저장`하고 `target이 가리키는 값`은 새롭게 `TRUE`로 바꾼다. 

그렇게 하고 나서 `기존에 target이 가리키는 값(rv)`을 return한다. 

test_and_set() 명령어를 통해서 `mutual exclusion`을 구현할 수 있다.

ex. 공유하는 boolean 자료형 `lock`이 있고 `False`로 초기화했다고 하자.

![image](https://user-images.githubusercontent.com/64796257/147862410-17c5ecae-6476-4e4f-a38b-e9a3a67eb2d6.png)

이와 같이 코드를 P0와 P1에서 만들었다고 하자.  
① `P0`에서 코드를 수행해서 `test_and_set(&lock)`의 `return 값`으로 `기존에 lock` 값인 `false`를 반환한다.  
② 하지만, 함수 내부에서 `lock = true`가 되었다.  
③ `P0`에서 c.s를 수행하는 동안 `P1`에서 `c.s`을 실행하려고 while문에 도달했다.
④ 하지만, `lock = true`이기 때문에 `P1`의 `test_and_case`의 `return값 = true`여서 while문 앞에서 `대기`한다.

즉, P0가 수행되는 동안 P1는 수행되지 못하는 `mutual exclusion`이 구현된다.

- compare_and_swap()

![image](https://user-images.githubusercontent.com/64796257/147862443-0aeb35d1-d91a-431c-b63b-ba3dcfc54159.png)

매개 변수로 가지는 value가 가리키는 값을 temp에 저장  
`value가 가리키는 값`이 `expected 값`과 `동일`하면 `value가 가리키는 값`을 `new_value`로 바꾼다.  
그렇게 바꾸고 나면 함수의 `return 값`은 `기존에 value`가 가리키던 값을 반환한다.

compare_and_swap(CAS)을 통해서도 mutual exclusion을 구현할 수 있다. 

ex. int lock = 0; 으로 초기화함

![image](https://user-images.githubusercontent.com/64796257/147862452-bc3ed944-2d21-4c8a-af8f-e44343c01f06.png)

이와 같은 코드를 P0, P1에서 만들었다고 하자.  
① `P0`에서 이 코드를 수행해서 P0의 CAS의 1번째 매개변수는 lock 변수의 주소값을 전달했다. 
② 만약에 `지금 lock이 가리키는 값`이 `2번째 매개변수의 값`과 같다면 lock이 가리키는 값을 3번째 매개변수의 값으로 바꾼다.  
③ 현재, `P0`에서는 그 조건을 `만족`한다. (`*lock = 0` ,`expected = 0`)  
④ 따라서, P0의 CAS를 수행하면서 `lock = 1`이 되었지만, `함수의 return값`은 `기존 lock값`인 `0`이 return 된다.  
④ 그래서 `P0`는 `c.s를 수행`한다. 이와 동시에 P1에서 c.s를 수행하기 위해서 while문을 지나간다.

⑤ 현재 `lock = 1`이므로 `P1의 CAS의 return값`은 `1`이다. 따라서, while문에서 `대기`한다.  

⇒ `P0`에서는 `c.s를 실행`하지만, `P1`에서는 `c.s를 실행하지 못`하는 `상호 배제`가 구현되었다.
    bounded waiting도 이와 같은 방식으로 구현할 수 있다고 한다. - 이건 나중에.

이와 같이 `mutual exclusion`은 HW의 지원을 통해서 쉽게 구현할 수 있지만,  
그 밖에 progress, bounded waiting은 SW적으로 구현해서 문제를 해결해야 한다.

지금까지는 하드웨어를 기반으로 한 해결책에 대해 살펴봤는데, 방금 말했듯이 하드웨어를 기반으로 해서는 bounded waiting, progress 문제를 해결하기가 어렵다.

이러한 문제를 해결하기 위한 SW 구현 방법 2가지를 알아보겠다.

#### Bounded waiting, Progress를 해결하는 2가지 방법

1. Mutex Lock(Spin-Lock) : 이 방법은 acquire(), release() 함수를 통해서 구현하는데 어떤 방식인지 살펴보자.

![image](https://user-images.githubusercontent.com/64796257/147862554-e73a351d-1b30-4de7-a40c-cfa7692c1dab.png)

Mutex Lock은 available이라는 boolean 변수를 통해서 lock의 여부를 표시한다. 
lock이 허용되면(= available 값이 true이면) acquire()의 호출은 성공하면서 내부적으로 availabe 값을 다시 false로 바꾼다. 

⇒ 이렇게 available을 false로 만들면서 lock을 걸었기 때문에 다른 프로세스에서 c.s를 실행하는 걸 막을 수 있다

release() 함수는 c.s이 다 수행되고 나서 실행되는데 이때 available의 값을 true로 바꿔준다.

이 방식의 단점은 available의 값이 어떤 값이 되는지 계속해서 확인하면서 대기하는 busy wait를 실행한다는 것이다.

그래서 c.s이 길지 않고 context-switching에 의해서 소모되는 시간이 실제로 프로세스가 실행되는 시간보다 훨씬 클 때 사용하는 것이 적합하다.

cf) 여기서 길지 않은 시간 = 짧은 시간이라는 건 어떤 기준일까??   
lock을 기다리는 스레드는 2번의 context-switching이 필요하다.  
1) 스레드를 대기 상태로 옮길 때 2) lock이 사용 가능할 때 대기 중인 스레드를 복원할 때

일반적으로 `lock이 유지되는 시간`이 `context-switching`하는 시간보다 짧다면 mutex lock을 사용한다.

2. Semaphore(세마포) : Mutex locks 보다 더 정교한 동기화할 수 있는 방법. 여러 개의 프로세스를 동기화할 수 있는 방법  

S라는 정수 값과 wait(), signal()이라는 두 가지 연산을 통해서 동기화 작업을 수행한다. wait와 signal을 정의하면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/147862602-5fbb2a95-ca73-4e2b-a27a-740b2acb88aa.png)

- wait를 호출할 때 S가 0보다 작거나 같으면 대기. 만약에 S가 양수라면 1을 감소시키면서 지나가게 한다.
- signal을 호출하면 S가 1 증가하게 했다.

세마포는 코게 2가지로 나눌 수 있다.

① Counting 세마포 

- Busy-waiting일 때 

S = 3을 초기값으로 가진다고 하자. 그리고 3개 이상의 프로세스가 동작하고 있다고 할 때 가장 먼저 P0 프로세스가 실행된다고 하자.

![image](https://user-images.githubusercontent.com/64796257/147862668-2b597c91-4f47-4e9f-8150-a211b3f3e5a3.png)

모든 프로세스가 위와 같이 수행된다고 하자.

0번 프로세스 ⇒ S의 값은 원래 3이었는데 wait의 조건인 s<=0을 만족하지 않으므로 wait을 빠져나와 c.s를 실행한다. 그래서 S값은 2가 된다.  
1번 프로세스 ⇒ S의 값 2에서 wait 조건을 만족하지 않아서 c.s를 실행한다. 그러면서 S의 값은 1이 된다.  
2번 프로세스 ⇒ S의 값 1에서 wait 조건을 만족하지 않아서 c.s를 실행한다. 그러면서 S의 값이 0이 된다.

3번 프로세스 ⇒ S의 값이 0이기 때문에 wait의 while문 조건을 만족하므로 busy wait을 하게 된다. 

따라서, 현재 수행중인 프로세스는 3개가 된다는 것을 알 수 있다.

이때 0번 프로세스의 c.s의 동작이 모두 끝나서 signal()을 지났다. 그러면 원래 S값에서 1을 더해서 S = 1이 된다.  
S = 1이 되었으니까 기다리고 있던 3번 프로세스가 실행되면서 c.s을 실행한다.

⇒ 이를 통해서 S의 값은 c.s를 실행할 수 있는 개수를 나타낸다는 것을 알 수 있다.  
⇒ 이와 같이 숫자를 세면서 c.s에 진입할 수 있도록 하는 방법을 counting 세마포라 한다.

- No-BusyWaiting 일 때 ⇒ 이 방식을 주로 `세마포(Semaphore)`라고 한다.

No-BusyWaiting이기 때문에 wait()와 signal()을 다른 방식으로 정의했다.

![image](https://user-images.githubusercontent.com/64796257/147862703-65442bd4-88b2-4cdb-8f93-24eedaa36e91.png)

wait를 보면 S가 가리키는 value의 값을 1 감소시키고 그 값이 0보다 작을 때 이를 list에 추가시키고 잠들게 해버린다.(= block())

signal을 보면 S가 가리키는 value의 값을 1 증가시키고 그 값이 0보다 작거나 같다면 list에 있던 값을 불러오면서 list에서는 그 값을 삭제한다.

![image](https://user-images.githubusercontent.com/64796257/147862712-26a7e39f-a13b-42ce-98c4-caf6dba7bc1d.png)

프로세스가 위와 같이 진행되고 S = 2라고 하자.

0번 프로세스는 S = 2 라서 wait을 실행할 때 S가 1 감소하고 나면 S = 1이 된다.  
그렇게 하고 나서 조건문을 보니, 조건문을 만족하지 않으므로 c.s를 실행하게 된다.

1번 프로세스는 S = 1 이라서 wait을 실행할 때 S가 1 감소하면서 S = 0이 된다.  
그렇게 하고 나서 조건문을 보니, 조건문을 만족하지 않으므로 c.s를 실행하게 된다.

2번 프로세스는 S = 0 이라서 wait을 실행할 때 S가 1 감소하면서 S = -1이 된다.  
그렇게 하고 나서 조건문을 보니, 조건문을 만족한다. 그래서 해당 S의 값은 리스트에 저장되면서 block() 된다.

그러던 와중에 1번 프로세스의 c.s 작업이 끝나 signal을 지나면서 S의 값은 다시 0이 되었다.  
signal의 조건문을 보면 S의 값이 0보다 작거나 같을 때 리스트에 있는 S값을 불러오면서 wakeup() 해준다고 한다.

그렇게 실행하지 못했던 2번 프로세스의 c.s를 실행할 수 있게 된다.

⇒ 이를 통해서 S의 값은 동시에 실행할 수 있는 프로세스의 개수를 의미한다. 그래서 c.s에 진입하는데 오래 걸리는 경우에는 semaphore를 사용한다.

② Binary 세마포 ⇒ 동시에 실행할 수 있는 프로세스의 개수가 1개인 동기화 기법.  

- BusyWaiting : 이 방법은 Mutex lock가 똑같다. 하나의 process를 busyWaiting을 이용해서 동기화하는 방법  
⇒ c.s에 진입하는데 오래 걸리지 않는다면 mutex를 사용하면 되겠다.

- No-BusyWaiting --> 이건 안 다뤘다.

#### 동기화 과정에서 생길 수 있는 문제 

1. DeadLock : 앞에서 얘기해서 여기서 추가로 얘기하지는 않겠다.

2. Starvation : 동기화 과정을 처리하는 과정에서 c.s에 진입하지 못하는 프로세스가 있을 수 있다. 이러한 상황을 starvation이라 한다.

⇒ 이를 해결하기 위해서는 priority-based 스케쥴링과 같이 aging과 같은 전략을 통해서 해결해줘야 한다.

3. Priority Inversion : 높은 우선순위를 가진 프로세스가 낮은 우선순위를 가진 프로세스가 작업하고 있는 공유자원 때문에 실행하지 못하는 문제 

ex. 프로세스 A가 있다고 하자. 그리고 A가 공유자원 S를 사용하고 있었다. 

그런데 높은 우선순위인 B 프로세스가 생성되면서 OS는 우선순위에 따라 B에게 cpu를 할당해줬다.

A가 공유자원 S를 사용하고 있다보니 B는 해당 공유자원에 접근하지 못해 작업을 하지 못하게 된다.  
분명 B의 우선순위가 A의 우선순위보다 높지만 A로 인해서 B를 수행하지 못하는 상황을 priority inversion이라 한다  
⇒ priority inheritance 방식을 통해 문제를 해결한다.

#### 동기화 예제 

- 고전적인 동기화 문제들 

1. Bounded-Buffer problem
소비자와 생산자는 다음과 같은 자료구조를 공유한다. 

buffer는 1차원 배열로 구현되며 Boolean Buffer[n];으로 선언한다. Buffer[0 ~ n-1] = not used;로 초기화 한다.  
(해당 공간을 사용하면 used, 아니면 not used)

생산자 operation : Buffer 배열 중 not used인 index를 찾아서 used로 바꾼다.  
소비자 operation : Buffer 배열 중 used인 index를 찾아서 not used로 바꾼다.  
semaphore full = 0; semaphore empty = n; semaphore mutex = 1; 

empty : 버퍼 내에 저장할 공간이 있음을 표시 ⇒ 생산자의 진입을 관리한다.  
full : 버퍼 내에 소비할 아이템이 있음을 표시 ⇒ 소비자의 진입을 관리한다.  
mutex : 버퍼에 대한 접근을 관리 ⇒ 생산자, 소비자가 empty, full 세마포에 진입한 경우 buffer의 상태값을 변경하기 위한 세마포  
		 ⇒ 만약에 생산자, 소비자가 둘 다 진입하려고 한다면 하나만 접근하도록 관리

![image](https://user-images.githubusercontent.com/64796257/147862831-4dcf3467-5231-4255-9b94-08e956349579.png)

먼저 생산자 프로세스를 실행한다고 하자.  
buffer에 넣을 아이템을 생산했는데 wait(empty)를 통해서 buffer가 비어있는지 확인한다. 

현재 empty값은 n. 즉, 비어있는 버퍼의 개수가 0개보다 크니까 아이템을 집어넣을 수 있다.   
이제 buffer에 아이템을 집어넣을 것이기 때문에 empty의 값은 1만큼 감소한 n-1이 되겠다.

wait(mutex)를 지나는데 mutex의 값은 1로 초기화되어 있다. 즉, 현재 buffer를 건드릴 수 있다는 뜻이다.  
그렇게 wait(mutex)를 지나면서 mutex를 0으로 바꾼다. (현재 생산자에서 buffer에 손대고 있다는 걸 표시)

그렇게 생산했던 아이템을 buffer에 추가시키고 나서 signal(mutex)를 지나 mutex를 다시 1로 바꿔준다. 이제 다른 프로세스가 buffer에 접근할 수 있게 되었다.

그 다음에 signal(full)을 지나면서 full을 1 증가시킨다. 즉, buffer의 공간 중 1개의 공간에 데이터가 들어있다는 것을 표시하면서 하나의 동작을 마무리한다.

이러한 상태에서 소비자 process를 실행한다고 하자.  
wait(full)을 지나는데, full = 1이다. 즉, 1개의 buffer 공간에 데이터가 있다는 뜻.  

그러면, 소비자 입장에서는 이용할 데이터가 존재한다는 것을 의미하기 때문에 wait(full)을 지날 수 있게 된다.  
그러면서 full은 기존의 값에서 1 감소한 0이 된다. 왜냐하면, 데이터를 하나 이용할 거라서 기존에 있는 데이터는 1개 사라지기 때문이다.

이제 wait(mutex)를 지난다. 현재 mutex의 값은 1이다.  
즉, buffer에 접근하고 있는 프로세스가 없는 상황이니까 mutex의 값을 0으로 바꾸면서 wait(mutex) 코드를 지나간다.

그렇게 buffer에 있는 데이터를 제거하면서 해당 데이터를 가져온다.

이제 buffer에 대한 접근이 끝났다. 그래서 signal(mutex)를 통해 mutex = 1로 바꿔주었다.

그러고 나서 signal(empty)을 지나는데.. 이때 empty의 값을 1 증가시킨다.  
왜냐하면, buffer의 공간 중 하나를 제거하면서 빈 공간이 하나 늘었기 때문이다. 그러면 empty는 n이 될 것이다.

이러한 과정을 계속 반복하면서 동기화 문제를 해결할 수 있게 되었다. 

2. Reader-Writers problem : 여러 Reader와 Writer가 하나의 공유 데이터를 읽거나 쓰기 위해서 접근할 때 발생하는 문제.

- Reader : 공유 데이터를 읽는다. 이때, 여러 Reader가 동시에 데이터를 읽을 수 있다.
- Writer : 공유 데이터를 쓴다. 이때, Writer가 데이터를 수정할 때 reader 혹은 다른 writer가 작업을 하면 안된다.

![image](https://user-images.githubusercontent.com/64796257/147862866-88f93bad-dd05-4240-9627-6c287429a906.png)

Reader Writer를 문제를 해결하기 위해서 여러 가지 시도들이 있었다. 각각의 시도에 대해 살펴보자. 

첫 번째 해결책은 다른 reader들이 데이터를 모두 읽을 때까지 writer가 동작하지 못하도록 설정했다.  
⇒ 그러다보니 writer에 대한 starvation이 발생한다.

첫 번째 해결책의 내용을 살펴보겠다.

int Readcount; // 버퍼에 현재 몇 개의 Reader가 접근 중인지 표시  
Semaphore Wrt; // Writers 사이의 동기화 관리  
Semaphore Mutex; // Readcount와 wrt로 접근할 때 원자적으로 수행됨을 보장하기 위한 세마포어

초기값 ==> mutex = 1, wrt = 1, readcount = 0

![image](https://user-images.githubusercontent.com/64796257/147862959-99b72f89-a900-4e5b-a2e3-2f6fbee96969.png)

wait(mutex) ~ signal(mutex) : 이 사이에 있는 일련의 코드들이 atomic 하게 실행될 수 있도록 일종의 block을 만드는 방법이라고 생각하면 되겠다.

![image](https://user-images.githubusercontent.com/64796257/147862979-4f9960b3-d6d1-4410-9bc8-46a79144d677.png)

현재 buffer에 접근해서 write를 할 수 있는지 파악하는 wait(wrt);를 지나야 writing을 수행할 수 있다.

초기값의 wrt는 1이기 때문에 wait(wrt); 를 실행하면 wrt를 1 감소시키면서 writing 작업을 실행한다.

그렇게 작업을 끝내고 나서 signal(wrt)를 통해 wrt를 1 증가시킨다.

![image](https://user-images.githubusercontent.com/64796257/147862982-6c8b8938-b09d-4a79-9624-5816126e33ec.png)

wait(mutex);를 실행하는데... 현재 mutex = 1이다. mutex를 1 감소시키면서 밑에 있는 작업을 수행한다.  

readcount의 값을 1 증가시킨다. 그런데 readcount의 초기값은 0이어서 값이 1로 변경되었다.

이때, if문의 조건에 걸리게 된다.   
만약에 기존의 wrt 값이 0이라면 현재 writer가 buffer에서 동작을 수행하고 있다는 것이다. 

여기서는 wrt 값이 1인 상태에서 조건문을 실행했다고 하자.  
그러면 busy wait를 하지 않으면서 wrt값을 1 감소시키고 나서 다음 동작들을 수행하게 된다.  

여기서 wrt값이 0이 되면서 다른 writer 프로세스가 작업을 하지 못하도록 배제했다.  
그렇게 wait(mutex) ~ signal(mutex)를 통해 atomic 하게 명령문들을 수행하고 나서 reading이 진행된다. 

다 읽고 나면 readcount값을 1 감소시키면서 아래의 동작을 수행한다.  
readcount가 0이되면 이제 writer 프로세스가 write 동작을 할 수 있도록 signal(wrt)를 호출한다.

그런데 여기서 문제가 되는게 readcount가 0이 안 될 수 있다는 점이다.  
writer가 동작하기 위해서는 reader가 모든 데이터를 다 읽어야지만 동작할 수 있다는 것.

이 때문에 writer에 대한 starvation 이 나타날 수 있다. 그래서 2번째 해결책을 모색했다.

두 번째 해결책은 writer가 객체에 접근하려고 기다리고 있다면 새로운 reader들은 읽기를 시작하지 못한다.  
그래서 reader에 대한 starvation이 발생한다는 문제점이 있다. 

두 번째 해결책의 내용을 살펴보겠다.

![image](https://user-images.githubusercontent.com/64796257/147863043-4290cb13-17a3-4872-a120-fcfdbe45c811.png)

초기값 rmutext, wmutex, wrt, read = 1 // readcount, writecount = 0

- writer 부분 

![image](https://user-images.githubusercontent.com/64796257/147863045-afe0bd51-008a-4155-a23b-e2a879fa06d6.png)

이제 writer를 할 거니까 writecount를 1씩 증가시키고  
write은 하나의 프로세스만 동작할 수 있기 때문에 writecount가 1일 때 원하는 동작을 수행하도록 조건을 걸었다.  
즉, 데이터를 write하려고 할 때 read의 값을 1 감소시킴으로써 reader 프로세스의 접근을 막는다.

그렇게 하고 나서 writing 동작을 수행한 다음에 writing 동작을 다 하고나서 writecount을 1 감소시킨다.  

그렇게 writecount가 0. 즉, 더 이상 write를 위해 접근하는 프로세스가 없다면 그제서야 read의 값을 1 증가해주면서 reader 프로세스의 접근을 허용해준다.

⇒ 이렇다보니, writer 프로세스가 동작을 하는 과정에서 writecount가 0으로 내려가지 않는다면...  
reader 프로세스들은 하염없이 writer가 끝날 때까지 기다려야 하는 starvation 상태가 발생하는 문제점이 생긴다.

- reader 부분 

![image](https://user-images.githubusercontent.com/64796257/147863075-81e1f737-221e-4866-8df3-a55e1a78bdbe.png)

맨 처음에 wait(read); signal(read); 를 넣은 이유는 writer 쪽에서 reader 프로세스의 buffer 접근을 관리하기 위해서 작성한 것이다.

만약에 buffer에 writer 프로세스의 접근은 없고 reader만 동작하고 있다면 이 부분은 그닥 의미가 없을 것이다.

하지만, writer 쪽에서 wait(read);를 수행함으로써 read의 값을 1 감소시켜 놓은 상태에서 reader 프로세스를 동작하려고 한다면

맨 처음에 있는 wait(read); 부분에서 read의 값이 0보다 커질 때까지 대기하게 될 것이다. 

이렇게 writer에서 reader 프로세스의 접근을 관리하기 위한 코드이다. ⇒ 그래서 reader 프로세스의 starvation 문제가 발생하게 된다.

세 번째. 앞에서 제기된 모든 문제점을 해결한 해결책

![image](https://user-images.githubusercontent.com/64796257/147863108-25d21ebb-8cea-4937-a68a-bcd9ced446d2.png)

rc, wc, rwc, wwc = 0 // wrt, read = 0 // mutex = 1

- writer 부분 

![image](https://user-images.githubusercontent.com/64796257/147863116-c01358d9-9c97-41e7-b336-e957e05dbe97.png)

조건문을 먼저 보면...
rc > 0 : 현재 작업중인 reader 프로세스가 있다  
wc > 0 : 현재 작업중인 writer 프로세스가 있다  
rwc > 0 : 현재 대기중인 reader 프로세스가 있다  
wwc > 0 : 현재 대기중인 writer 프로세스가 있다  

⇒ 작업 중인 프로세스가 있거나 대기 중인 프로세스가 있다면 writing 작업을 하고자 할 때 대기하도록 했다.  
wwc를 1 증가시키고 writing을 기다리기 위해서 wait(wrt)를 실행시킨다.

그렇다면 if문을 만족하지 않았다고 하자. 그러면 writing을 할 프로세스이기 때문에 wc를 1 증가시킬 것이다.  
그렇게 writing을 수행하고 나서 wc는 1 감소시킨다. 

writing 동작이 끝났으니까 rwc > 0 즉, read를 기다리고 있는 프로세스가 존재한다면  
대기하고 있는 reader 프로세스가 read 동작을 수행할 수 있도록 하기 위해서 대기 프로세스 개수만큼 signal(read);를 수행한다.

또는 rwc = 0인데 wwc > 0이다. 즉, read를 기다리는 프로세스는 없지만, writing을 기다리던 프로세스가 있다면  
대기하고 있던 writer 프로세스가 write 동작을 수행할 수 있도록 signal(wrt);를 수행해준다.

이러면 이전의 writing process가 wait(wrt)에서 멈춰있던 동작이 순서대로 수행될 것이다.

그리고 reader 프로세스에서 signal(wrt);를 통해 wait(wrt)에서 멈춰있던 write 동작을 수행하도록 할 수도 있다.

그러면 이전 write 프로세스의 wwc를 1 감소시키면서 순서대로 writing 동작을 수행하게 될 것이다.

![image](https://user-images.githubusercontent.com/64796257/147863158-84edb504-9268-4ef5-aa89-f3701b7224a4.png)

wwc > 0 : write를 대기하는 프로세스가 있다.  
wc > 0 : write 작업을 하고 있는 프로세스가 있다.

⇒ 이 조건을 만족하면 read 작업을 하면 안되기 때문에 해당 프로세스는 `대기`를 해야한다.  
   그래서 rwc를 1 증가시키고 wait(read);를 통해서 read 작업을 대기하도록 했다.

만약에 if문 조건을 모두 만족하지 않는다면, read 작업을 해도 되는 거니까 이를 수행한다.  
이제 read 작업을 할 거니까 rc를 1 증가시키고 reading 작업을 수행한다.  
그렇게 reading 작업을 다 하고나면 rc를 1 감소시키고 if문을 만난다. 

rc == 0 && wwc > 0 : 작업하고 있는 reader 프로세스가 없고 writing을 하기 위해 대기하는 프로세스가 존재할 때  
			signal(wrt)를 통해 대기하고 있는 writing 프로세스가 write 동작을 할 수 있도록 wrt를 1 증가시켰다. 

만약에 wwc > 0을 만족하지 않는다면... 그냥 reader 프로세스 하나가 완료된 것이다.

3. Dining - Philosophers Problem : 5명의 철학자들이 하나의 식탁에서 함께 밥을 먹고 있는 상황

![image](https://user-images.githubusercontent.com/64796257/147863191-8d6db387-34a4-41de-a727-38203cbe5a7f.png)

이때, 옆 젓가락은 왼쪽 또는 오른쪽이 될 수 있다.

만약에 모든 사람들이 자신의 오른쪽 또는 왼쪽 젓가락을 동시에 집는다면 Deadlock과 starvation이 발생한다.

![image](https://user-images.githubusercontent.com/64796257/147863194-b8c61e97-e054-4b08-b980-18e638fd3c4b.png)

해결책 1. 

chopstick[5]의 값을 모두 1로 초기화. ⇒ 각각의 젓가락에 대한 동기화 관리

![image](https://user-images.githubusercontent.com/64796257/147863201-b268b80e-63fa-4883-b9aa-2b6a9c52660e.png)

![image](https://user-images.githubusercontent.com/64796257/147863203-9ecbc284-a783-4016-87bb-7a3287724752.png)

i번째 젓가락을 집으면 i+1 번째 젓가락을 집는 동작을 wait(chopstick[i]), wait(chopstick[(i+1) % 5])를 통해서 구현한다.

여기서 % 5의 의미는 5명이 하나의 식탁에 둘러앉은 상황이기 때문에 6번째는 1번째와 똑같고 7번째는 2번째와 똑같다.  
이는 그 숫자를 5로 나눴을 때 나머지 값이 되겠다.

그리고 여기서 변하는 값은 i값이 아닌 chopstick[i]값이라는 것을 헷갈리지 말자. 

그렇게 i번째와 i+1번째 젓가락을 집고 나서 두 개의 signal을 통해 다시 젓가락을 내려놓는다.

하지만, 5개의 프로세스가 동시에 wait(chopstick[i])를 실행한다면 deadlock이 발생한다.

해결책 2.

양쪽의 젓가락을 한꺼번에 집는 방법 ⇒ 젓가락의 상태를 미리 검사해서 양쪽의 젓가락이 모두 사용 가능할 때만 젓가락을 집도록 한다.

State[5] : 각 철학자들의 상태를 기록(THINKING, HUNGRY, EATING)

mutex : 젓가락을 집거나 놓는 수행을 c.s로 관리하기 위한 세마포(초기값은 1)  

Self[5] : 각각의 철학자 들이 젓가락 2개를 잡기를 기다리는 세마포  
⇒ 모든 원소는 0으로 초기값을 가진다. self[i] = 철학자 i가 HUNGRY 상태여도 다른 젓가락 하나를 사용할 수 없다면 자기 스스로를 waiting 하기 위한 세마포

![image](https://user-images.githubusercontent.com/64796257/147863256-2a055beb-0886-438c-8440-9ae2f2d4b052.png)

think를 하고 있다가 i번째 철학자가 젓가락을 들어서(take_chopsticks)

eat를 하고 나서 젓가락을 내려놓는다(put_chopsticks).

여기서 take_chopsticks와 put_chopsticks는 어떻게 구현되는지 살펴보자.

![image](https://user-images.githubusercontent.com/64796257/147863259-5507e832-5544-4372-a4d9-655e56de8ee6.png)

젓가락을 드는 작업을 하는 take_chopsticks

Mutex를 통해 진입한다. 젓가락을 들었다는 건 현재 철학자는 배고픈 상태. 그래서 state[i]에 HUNGRY라는 값을 넣었다.  
그러고 나서 test(i)를 실행하는데 이는 양쪽의 철학자의 상태를 검사한 후 자신의 먹을 차례를 기다린다. 

그렇게 양쪽 철학자가 모두 EATING 상태가 아닌지 확인하고 나서 나머지 코드들을 실행한다. 이제 i번째 철학자는 음식을 먹을 예정이기 때문에 self[i]의 값을 1 감소시켰다.

![image](https://user-images.githubusercontent.com/64796257/147863262-cba2ad16-5b21-455c-9794-06fd003c9c28.png)

take_chopsticks와 같이 Mutex를 통해 진입한다.

젓가락을 내려놓았다는 건 먹는 상태는 다 끝난 상태. 그래서 state[i]에 THINKING이라는 값을 넣었다.

test(LEFT), test(RIGHT)를 통해 양쪽 철학자의 상태를 검사한 다음에 먹을 차례를 기다리는 철학자에게 signal을 보낸다.

![image](https://user-images.githubusercontent.com/64796257/147863266-2484f34b-0d65-4b0d-a873-f1a0431ae8b6.png)

test 함수. 
i번째 철학자가 HUNGRY이면서 // 왼쪽에 있는 철학자의 상태가 EATING이 아니고 // 오른쪽에 있는 철학자의 상태가 EATING이 아니라면

i번째 철학자를 기준으로 왼쪽, 오른쪽에 젓가락을 모두 이용할 수 있는 상황이 된다. 

그래서 해당 조건을 만족하면 i번째 철학자의 상태 state[i]를 EATING으로 만든다.

그렇게 하고 나서 음식을 먹었기 때문에 self[i]의 값을 다시 1 증가시킨다.












