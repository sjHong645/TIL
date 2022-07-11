## Background

- `Demand paging` : 프로세스가 내용을 요구할 때 프레임을 할당하는 방식 ⇒ 이 방식을 통해서 `utilization`을 높일 수 있다.

Code를 실행하기 위해서 코드 전체를 메모리에 load 하지만 정작 자주 쓰이는 부분은 극히 일부다. 즉, 동시에 전체 코드를 필요로 하지 않는다.

그렇다면 OS 입장에서 `자주 쓰지 않은 부분`은 `load 하지 않아도 괜찮지 않을까` 라고 생각할 수 있다.

하지만, 프로그래머의 입장을 보면 내가 작성한 모든 코드가 load가 되어야 하는게 당연하다. 

확률은 낮지만 error가 일어날 수 있고 그 error를 대비하기 위한 코드를 다 짜놨기 때문이다.

많이 안 쓴다는 이유로 그 코드를 load 하지 않으면서 error가 발생해도 아무런 조치를 취하지 못할 수 있는 문제가 발생한다.

그래서 이런 방법을 고안했다. 

프로그래머가 작성한 모든 코드를 load 했다고 하면서 사실은 `물리 메모리`에 `load 하지 않`는 거다.

만약에 `자주 쓰지 않은 부분`을 `필요`로 한다면 곧바로 `그 부분을 메모리에 부여`해주고 그 이후에도 사용하지 않는다면 곧바로 해제해버리는 방식을 생각할 수 있다.

이렇게 동작하면 `프로그래머 입장`에서는 자신이 작성한 모든 코드를 다 수행할 것이기 때문에 상관없고

`메모리 차원`에서도 자주 사용하는 코드 위주로 프로그램이 돌아갈 것이기 때문에 메모리를 효율적으로 활용할 수 있게 된다. 

그렇다면, 이를 어떻게 구현할 수 있을까?? 바로 `virtual memory`라는 개념을 이용할 것이다. 대충 그림을 그리면 아래와 같다.

## Virtual memory 

![image](https://user-images.githubusercontent.com/64796257/147846923-47ba4b0d-1912-4e3b-b818-0de0d324ee33.png)

- LAS : Logical Address Space
- VAS : Virtual Address Space
- PAS : Physical Address Space

`LAS`에서 내가 작성한 모든 코드를 `PAS`에 전달했다고 생각하겠지만 사실은 모두 `VAS`에 전달한다.

`VAS`는 내가 작성한 모든 코드를 전달받고 프로세스를 동작하면서 `자주 사용하는 부분`만 `PAS에 전달`하고 그렇지 않은 부분은 해제한다.

그러다가 자주 사용하지 않은 부분을 사용하려고 하면 `VAS`에서 `PAS`에게 `해당 부분에 대한 데이터`를 부여한다. 다 쓰고나서 일정 시간 동안 쓰지 않는다면 다시 뺀다.

⇒ 이러한 과정을 `on-demand`라고 한다. 

### Valid-Invalid Bit 

![image](https://user-images.githubusercontent.com/64796257/147846988-94d35a3e-7cda-441e-8dfc-932b3bdca2c2.png)

`Valid-invalid bit`의 값이 `v`는 `1`을 의미 = `해당 페이지`는 `물리 메모리에 할당`되어 있다는 뜻이다.

ex. 위와 같이 page table의 0페이지가 4번 프레임에 할당되었다는 의미하고 이때 해당 페이지의 bit의 값은 `v`다.

bit의 값이 i, 즉 0이라면 `해당 페이지`는 `물리 메모리에 할당`되어 있지 `않다`는 뜻이다.

ex. page table의 1페이지는 어떤 프레임도 할당하지 않았다는 것을 나타내는 것이 i값이다.

### Page fault 

이때, `valid-invalid bit의 값 = i`인 페이지에 접근하려고 하는 상황을 `page fault`라고 한다.  
이러한 page fault를 처리하는 방식의 큰 과정을 살펴보면 아래의 그림과 같다.

![image](https://user-images.githubusercontent.com/64796257/147847011-e7694958-857b-42de-819d-698345cb1290.png)

1) `load` 라는 명령어를 통해서 메모리에 대한 reference(참조)를 하고 싶다.
2) page-table의 `valid-invalid bit = invalid`이니까 page fault가 발생한다.
3) 이러한 문제가 생겼다는 사실을 `trap`을 통해서 OS에게 전달한다.(`SW 인터럽트` 형태로)

이 과정 이후에는 여러가지 과정이 파생된다.

기억해야 하는 건, 결과적으로 page fault를 처리하기 위한 목적은 `프레임을 할당하기 위한 것`이다. 

`free frame`이 있는지 없는지에 따라 프레임을 할당하는 방법이 달라진다.

#### free frame이 없는 경우 ⇒ 이 부분은 좀 더 뒤에서 자세히 다루겠다.

1) 이미 해당 프레임을 가지고 있는 프로세스에서 뺏어오는 방법 `page replacement` 방식이 있다.

2) page replacement도 안되서 줄 수 있는 물리 메모리가 없을 때, `O.O.M(Out of Memory)`를 요청해서 해당 프레임의 참조를 요청한 프로세스를 종료시킨다.

#### free frame이 있는 경우 = 비어있는 프레임이 있는 경우 

1) 프로세스에서 요청하는 명령어가 `새로운 메모리 공간`을 `요청`한 경우 (ex. malloc을 하고 나서 write 작업을 할 때) 

   ⇒ 이러면 원하는 위치의 메모리에 garbage값이 있는지 여부는 중요하지 않다. 그냥 `해당 공간을 할당`해주기만 하면 된다.  
   ⇒ 이때는 storage에 접근할 필요 없이 해당 페이지에 해당하는 `물리 메모리`의 `free frame`에 `공간을 할당`하면 된다.  
   ⇒ 그렇게 하고 나서 page table을 재설정해준다.

2) 프레임 안에 `값이 존재`해야 하는 경우(ex. read를 할 경우)인데 `크게 2가지`로 나눠볼 수 있다. 

   2-1) `필요한 데이터`가 `storage`에 있는 경우 

      ⇒ `직접` storage로 `접근`해서 LAS에 근거해서 해당 프레임에 대한 데이터를 가져와야 한다.(시간이 굉장히 오래 걸린다.)  
      ⇒ 데이터를 storage에서 가져옴 ⇒ free frame에 데이터를 적재 ⇒ page table을 재설정

   2-2) storage에 있기는 한데 실행파일의 이미지가 아닌 `swap area`에 있는 경우 

      ex. malloc을 통해서 3번 페이지의 9번 프레임에 데이터를 저장했다고 하자. 

      이때, 해당 데이터를 더 이상 사용하지 않아서 OS에서 9번 프레임을 invalid도 바꿨다. 즉, 물리 메모리에 존재하지 않게 되었다.

      ⇒ 그럼 그 프레임은 어디에 저장했을까?? `storage의 swap area`에 저장된다. (swap area에는 동적으로 할당한 데이터를 저장하는 공간이다.)

      만약에 이렇게 swap area에 저장한 데이터에 다시 접근하려고 한다면?  
      swap area에서 해당 데이터를 찾음 ⇒ free frame에 다시 적재 ⇒ page table을 재설정해준다. 

      page table 재설정까지 모두 완료한 상황. 

      이제 6번 restart instruction 과정을 실행하면 된다.

      이 과정은 instruction을 실행하면서 증가했던 pc(program counter)를 원래대로 복원시켜서 다시 명령어를 실행하면 된다.

### Performance of Demand Paging 

Page fault가 발생할 때 storage에 접근하기 때문에 이로 인한 엄청난 시간 낭비가 일어난다.  

ex. 메모리에 접근하는 시간이 200ns, page-fault를 처리하는 시간이 8ms, p = page fault가 일어날 확률
cf) 1ms = 100,000 ns

⇒ EAT = (1-p) * 200 + p * 8,000,000 = 200 * p + 7,999,800

만약에 page fault가 1000번 중에 1번 일어났다면 p = 0.001이 된다. --> EAT = 8.2ms가 된다.

이는 p = 0일 때의 값과 비교하면 무려 40배의 차이가 난다. 이렇게 `page fault`는 `굉장한 risk`를 안고 있다. 

만약에 performance 감소율을 10% 이하로 줄이고 싶다면, p값은 어느 정도여야 할까??

200 + 200 * 0.1 > 200 + 7,999,800 * p ==> p < 0.0000025 의 범위를 가지게 된다. 

즉, 40만번의 메모리 접근 중에서 한 번의 page fault가 일어나야 performance 감소를 10% 내외로 줄일 수 있다.

이러한 수치는 현실적으로 가능하다. 실제로 page fault는 거의 일어나지 않기 때문이다.  
이게 가능한 이유는 locality라는 특성을 이용했기 때문이다.

### Page Replacement ==> Free Frame이 없는 경우에 발생 

#### FIFO 알고리즘

![image](https://user-images.githubusercontent.com/64796257/147847125-60c05986-ee45-45e5-84cd-48f87f6449de.png)

7,0,1,2,0,3,0... 와 같이 숫자들은 어떤 페이지를 접근할지에 대한 순서다.

이때, 다른 프로세스에서 프레임을 뺏지 않고 자신의 프로세스에서 동작하고 있는 프레임을 새로 실행하려고 하는 데이터랑 대체할 것이다.

여기서 처리하는 알고리즘은 FIFO다. 위의 예시를 보자.

7이 들어온다. page frame은 아무것도 없는 상황이었기 때문에 page fault가 발생한다.

0이 들어온다. 마찬가지로 아무것도 없는 상황이어서 page fault가 발생한다.

1이 들어온다. 마찬가지로 아무것도 없는 상황이어서 page fault가 발생한다.

2가 들어온다. 현재 page 2번에 대해서 할당된 프레임이 없다. 그래서 가장 먼저 들어온 7을 바꾼다.

⇒ 이렇게 가장 먼저 들어온 page 번호를 먼저 보내는 FIFO를 적용한 것이다. 이는 상황에 따라서 굉장히 잦은 page fault를 유발할 수 있다.

이러한 한계점을 어느 정도 보완한 알고리즘이 LRU(Least Recently Used) Algorithm이다.

##### LRU 알고리즘

![image](https://user-images.githubusercontent.com/64796257/147847160-30146828-6192-4e25-a8c6-fd0f63745cf1.png)

체크한 부분을 보자. 3번 페이지를 프레임에 할당하려고 하는데 page fault가 발생했다.

이때 history를 살펴본다. 

체크한 부분을 이전에 0, 2, 1 순으로 사용되었다. 

즉, 1이 가장 오래전에 사용했기 때문에 1번 페이지를 할당한 프레임을 지우고 3번 페이지를 그 자리에 할당한다.

하지만, 구현할 수 없다.

reference string을 하나씩 순회하고 있고 해당 자료구조를 계속 유지해야 하는데 저장되는 장소가 메모리이기 때문에 메모리에 접근해야 한다. 그리고 페이지 테이블의 크기가 굉장히 크다. 

그렇다면 실제로 어떻게 구현해야 할까?? 가장 대표적인 방법은 second-chance algorithm을 사용하고 있다.

#### Policy

- Allocation of Frame

10개의 프로세스가 있고 50개의 frame이 있다고 하자.  
1) fixed allocation : 각각의 프레임에게 똑같은 프레임을 할당한다.
2) priority allocation : 프로세스마다 중요도가 있다면 중요도가 높은 프로세스에게 더 많은 프레임을 할당한다.

⇒ 이러한 부분들은 정책적이다. 

- Global Replacement : 프로세스가 replacement를 할 프레임을 고르는데 다른 프로세스에게 할당된 프레임으로 대체할 수 있도록 하는 정책  
⇒ 이러면 해당 프로세스의 성능은 낮아질 수 있지만 시스템 전체의 성능은 높아질 수 있다  
⇒ General purpose에서는 전체적인 시스템의 성능이 중요해서 해당 방식을 주로 채택한다.

- Local Replacement : 프로세스가 replacement를 할 프레임을 고르는데 자신에게만 할당된 프레임으로 대체할 수 있도록 하는 정책 
 
⇒ 이러면 해당 프로세스의 성능은 높아지지만 시스템 전체의 성능은 낮아질 수 있다.

### Thrashing : page replacement가 계속해서 일어나는 상황

==> 초기의 페이징 시스템에서는 CPU 이용율을 높이기 위해서 degree of multi-programming을 높인다.  
    이때, global replacement를 사용해서 어떤 프로세스의 페이지인지 상관없이 replace를 수행한다.

    이제 어떤 프로세스가 새로운 실행 단계에 진입해서 더 많은 프레임이 필요하다고 가정하자.
    
    이때, 그 프로세스에 대해 page fault가 발생한다면 다른 프로세스로부터 프레임들을 가져올 것이다.
    
    그런데, 교체된 페이지들이 해당 프로세스에게 필요한 것이었다면 교체된 프로세스 역시 page fault가 발생하게 되서 또 다른 프로세스에서 프레임을 가져온다. 

이러한 프로세스들이 page swap in, page swap out을 하기 위해서 wait queue에 들어가게 되는데 

이는 모든 프로세스들에게 발생할 것이기 때문에 결국에는 모든 프로세스가 page fault를 해결할 때 까지 wait queue에 들어가있는 상황이 발생한다. 

때문에 CPU의 utilization은 떨어질 것이다. 이러한 상황을 `thrashing`이라 한다.

⇒ 프로세스에 대한 메모리가 부족해지면서 I/O 과정이 빈번하게 일어나게 되는데 이렇게 모든 프로세스가 wait queue에서 page fault handling을 기다리는 상황

![image](https://user-images.githubusercontent.com/64796257/147847248-464b31d9-4f91-4e20-8117-2b0403772956.png)

thrashing이 발생하는 이유는 어느 한 시점에서 사용하려고 하는 메모리의 크기가 전체 메모리 크기보다 큰 경우에 발생한다.


그렇다면 어떻게 적절한 지점을 찾을 수 있을까?? ⇒ 기본적으로 Locality를 이용할 것이다.

- Locality를 기반으로 한 working set model
- Page-fault Frequency
- TLB Reach




