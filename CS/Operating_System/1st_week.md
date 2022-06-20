● 운영체제

운영체제란... `컴퓨터 하드웨어를 잘 관리해서 성능을 높이고 사용자에게 편의를 제공하는 프로그램`

OS의 목적은 

1. 애플리케이션의 요청에 따라서 자원을 할당하고 (Allocates resources to applications according to their requirements)

2. 자원을 효율적으로 관리하는 것이다. (Efficient management of resources)

이때 하드웨어를 관리해주는 OS는 2가지로 나뉜다.

1) 커널(kernel) : 실질적으로 하드웨어를 관리하는 프로그램

2) 명령 해석기 : 커널을 동작하기 위한 프로그램 

   ex. 내가 Linux에서 배운건 리눅스 운영체제가 아닌 ubuntu를 통한 리눅스 사용법

Shell : 커널(kernel)과 사용자 간의 다리 역할을 하는 프로그램. ==> 여기서의 ‘명령 해석기’라 할 수 있겠다.

![image](https://user-images.githubusercontent.com/64796257/147618895-fd509217-a393-4163-8607-f3f6f00836e9.png)


● 운영체제 서비스 - 여러 가지 서비스가 있지만 이번 운영체제 강의에서는 ‘프로세스 관리’, ‘메모리 관리’에 대해서 중점적으로 다룰 것이다.
1. 프로세스 관리(Process Management)
- 프로세스 : `메인 메모리`에서 `실행 중`인 프로그램

- CPU에서는 메인 메모리에 올라온 프로그램 즉, `프로세스`를 다루기 때문에 CPU와 관련해서는 `프로세스 관리`라는 이름을 붙인다.

![image](https://user-images.githubusercontent.com/64796257/147618930-be733ca5-a335-4418-bf21-830fd2dbfb8e.png)

2. 주기억장치 관리(Main Memory Management)

![image](https://user-images.githubusercontent.com/64796257/147618943-d14fcb0a-0096-4fc2-8fde-6a07fcdc5a3f.png)


3. 보조기억장치 관리(Secondary Storage Management) - 하드 디스크, 플래시 메모리 etc.
- 주요기능
 1. 빈 공간 관리(Free Space Management) : 어떤 공간이 사용 중이고 비어있는지 관리한다.
 2. 저장공간 할당(Storage Allocation) : 빈 공간 중 어느 공간에 메모리를 할당할지 관리한다.
 3. 디스크 스케쥴링(Disk Scheduling) : 디스크는 head가 움직이면서 데이터를 읽는다. 
                                      이때 어떻게 하면 head의 움직임을 최소화할 수 있는지 관리한다. 

● 시스템 콜 : 애플리케이션 또는 프로세서가 운영체제에게 서비스를 받기 위해서 보내는 호출.




















