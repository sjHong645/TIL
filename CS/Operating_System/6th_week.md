■ Abstraction(추상화) : 복잡한 자료, 모듈, 시스템 등으로부터 핵심적인 개념 또는 기능을 간추려 내는 것

ex. 스마트폰

사실 스마트폰이라는 것은 이 세상에 없다. 진짜 이름은 아이폰, 갤럭시로 묶을 수 있고 좀 더 자세히 들어가면 각 기계의 시리얼 넘버에 따라 분류된다.

그렇다면 어떤 기계를 `스마트폰`이라 부를 수 있을까?  
이를 만족하는 여러 조건들이 있을 텐데 이들을 만족하는 기계를 통칭해서 `스마트폰`이라 추상화 한 것이다. 

⇒ OS는 아래와 같이 추상화를 했다. 

| 기능  | 추상화 |
| ------------- | ------------- |
| 명령어의 집합(sequences of instructions) | 프로그램 |
| CPU, memory | 프로세스 |
| Storage | 파일 |
| 네트워크 | 소켓 |

⇒ 이렇게 추상화를 하게 되면 각각의 동작들이 구체적으로 어떻게 동작하는지는 몰라도  
애플리케이션을 편하게 사용할 수 있고 수많은 하드웨어들의 공통된 특징만 뽑아내서 동작하기 때문에 OS를 관리하기가 더 쉬워진다. 

■ HAL(Hardware Abstraction Layer) 

: 하드웨어의 구체적인 특성을 숨기고 추상화된 하드웨어로 윗 계층에 기능을 일관되게 제공하는 역할을 수행한다. 
   
  지금은 단순히 추상화된 HW를 제공한다는 개념만 존재한다. 
  
  소프트웨어가 다양한 종류의 하드웨어 상에서 별 차이 없게 동작할 수 있도록 한다. 이는 OS의 커널 또는 장치 드라이버에서 호출될 수 있다.

![image](https://user-images.githubusercontent.com/64796257/147616433-c9b525ef-c640-4a2f-bece-a60e16a5b755.png)

리눅스의 경우 입출력 장치를 아래와 같이 HW를 추상화해서 지원한다.

1) character device : 데이터를 전달할 때 한 번에 character 단위로 데이터를 전달 ex. 키보드, 마우스
2) block device : 데이터를 전달할 때 정해진 block 사이즈 단위로 데이터를 전달 ex. HDD, SSD
3) pipe
4) socket : 프로세스간의 소통을 원활하게 함. pipe와는 달리 한 번에 많은 프로세스들을 가지고 소통할 수 있음

`추상화`에 대한 개념을 좀 더 자세히 살펴보기 위해 `저장장치`를 좀 더 살펴보겠다.

storage는 CPU와 memory와 달리 하드웨어 장치의 특성이 뚜렷하게 다르기 때문에 abstraction이 꼭 필요하다. 

먼저, block storage device를 추상화하면 크게 두 가지로 나눌 수 있다. HDD vs SSD

![image](https://user-images.githubusercontent.com/64796257/147616755-5ec14302-65da-4240-be97-0561cbeeb71f.png)

| HDD | 자성 물질로 덮인 'platter'를 회전시키고 그 위에 헤드를 접근시켜 platter 표면의 자기 배열을 변경하는 방식으로 데이터를 읽고 쓰는 저장장치 |
|---|---|
| SSD | 반도체 메모리에 데이터를 기록하는 저장장치. 전원을 꺼도 데이터가 남아있는 비휘발성 특징을 가지고 있다. |

![image](https://user-images.githubusercontent.com/64796257/147616848-c83b74c4-8c39-46f2-9c6f-bb83824f1419.png)

이렇듯 똑같은 storage device이지만 하드웨어의 구조부터 시작해서 데이터를 저장하는 방법까지 완전히 다르다. 그렇기 때문에 더더욱 abstraction이 필요하다. 

어떻게 abstraction이 이뤄졌는지 살펴보자.

- 추상화 1단계 : block storage 

⇒ 파일이 균일한 블록에 저장되는 데이터 저장소 

⇒ 데이터를 block 단위로 쪼개서 별도로 분리해서 저장하는 것을 말한다. 이후 데이터를 요청할 때 나누어진 데이터를 재결합해 제공, 응답한다. 

![image](https://user-images.githubusercontent.com/64796257/147616982-a2ca4553-473b-47cc-ab5f-6acc919901c0.png)

- 추상화 2단계 : a file system

⇒ 사용자들은 1단계에서 추상화한 block storage 개념을 직접 사용하지 않고 파일이라는 개념을 사용해서 데이터를 저장하고 사용한다. 

(1) 파일 : 이름, 크기, 소유자 등등의 특성들의 집합 ex. /home/ubuntu/os0.c
- 애플리케이션은 데이터를 저장하기 위해 block storage를 직접 사용하지 않고 File을 사용한다.
- 파일 시스템은 File과 다른 추상화(디렉토리, pseudo file, link 등등)을 제공한다.

(2) 장점
- 파일 시스템을 사용할 때 block storage를 직접 사용하지 않고 File을 이용하기 때문에 애플리케이션은 block storage를 잘 몰라도 데이터를 저장하고 접근할 수 있다. 
- 사용자 데이터에 편리하게 접근할 수 있고 관리할 수 있다.

■ Abstraction of CPU Background Concept and definition

- Abstraction of CPU  

⇒ CPU에는 여러 회사가 있는데 각각의 CPU를 구분하는 기준은 ISA(Instruction Set Architecture)이다.  

 만약에 동일한 ISA를 가진 두 개의 CPU가 있다면 그 CPU는 동일한 CPU이다.

OS에서는 CPU가 간단하게 2가지 동작만 하면 된다.

1) Computation(연산) : 주어진 operation을 흐름에 따라 처리한다.
2) Control : event(interrupt)가 발생하면 정해진 동작에 따라 처리한다. 

⇒ 이러한 두 가지 동작은 어떤 CPU가 오던지 간에 실행되는 동작이다.

- Background

1) program 

![image](https://user-images.githubusercontent.com/64796257/147617530-aa0321aa-2f23-4d26-9d28-d9a87e57f662.png)

ⅰ. 소스코드 (.c 파일) : 프로그램이 수행하고자 하는 작업을 프로그래밍 언어로 표현

ⅱ. 컴파일러(compiler) : 소스코드를 컴퓨터(CPU)가 이해할 수 있는 기계어로 표현된 Object file로 변환 

ⅲ. 오브젝트(.o 파일) : 컴퓨터가 이해할 수 있는 기계어로 구성된 파일. 이 파일 자체로는 어떤 동작을 할 수 없고 프로세스로 변환되기 위한 정보가 삽입되어야 한다.  
                       ⇒ 이는 relocatable address로 표현된다. (심볼들의 주소를 상대적인 값으로 표현한다)

ⅳ. 링커(Linker) : 오브젝트 파일과 library를 연결해서 Main Memory로 load 될 수 있는 하나의 실행파일로 작성

ⅴ. 실행파일(.exe) : 특정 환경에서 수행될 수 있는 파일. 프로세스로 변환하기 위한 header, 작업 내용이 들어간 text, 필요한 data를 포함한다.  
                       ⇒ 이는 absolute address로 표현된다. (심볼들의 주소를 절대적인 값으로 표현한다)

2) process : 메인 메모리에서 실행 중인 프로그램

⇒ 이는 resource sharing의 추상화다. 
1) CPU 및 다른 자원을 공유하기 위한 단위로 사용한다.
2) 각각의 프로세스는 컴퓨터 시스템을 자기가 독점해서 사용하는 것으로 인식한다.

⇒ 상호 간의 간섭 및 침범은 불가하다.

프로세스를 구성하는 개념은 2가지가 있다. Contexts와 Resources 
1. Contexts : 수행 흐름 - 프로그램의 시작부터 끝까지 수행되는 흐름을 말한다.  
여러 개의 수행 흐름이 존재할 수 있다. 이러한 context를 관리하기 위한 abstraction이 Thread이다. 

2. Resources : 프로세스에 할당된 자원

- Memory : virtual address space 
- storage : 파일
- network : 소켓 으로 추상화했다.

3) Thread(쓰레드) 

ex. 워드를 사용하는 경우

: 워드에서 글자를 입력하는 동안 `파일을 주기적으로 자동저장`하고 `내용을 프린터에 출력`하고 입력하는 동안 `자동으로 맟춤법 검사`를 수행한다.

이때, 글자를 입력받는 쓰레드, 파일을 디스크에 저장하는 쓰레드, 출력할 내용을 프린터에 보내는 쓰레드, 입력하는 동안 맞춤법 검사를 하는 쓰레드가 있다고 할 수 있다. 

⇒ 즉, 워드라는 큰 프로세스 하나에 여러 개의 쓰레드가 모여있는 것이다.

실제로 프로세스는 하나의 address space를 가지고 있다. 이러한 address space에 여러 개의 쓰레드가 함께 동작할 수 있다. 

그래서 프로세스는 쓰레드에 대한 일종의 컨테이너 역할을 한다. 

![image](https://user-images.githubusercontent.com/64796257/147618307-cb1a0cc7-713a-496f-b010-f68bf4aa3448.png)

쓰레드는 하나의 프로세스 안에서 정의하는 `실행단위(execution unit)`라고 할 수 있다. ⇒ OS에서 context를 관리하기 위한 구체적 실체

4) address space의 메모리 배치 ⇒ 4가지 section을 나눠서 구분됨.

⇒ 프로세스가 바구니 역할을 할 수 있도록 하는 resource이다. 데이터, 쓰레드 등등을 모두 이곳에 저장한다.

![image](https://user-images.githubusercontent.com/64796257/147618403-cd7d98df-6ed2-434b-b116-f74fb6217d14.png)

크게 보면 static하게 할당, dynamic하게 할당으로 볼 수 있다. 여기서 text 부분은 code 부분이라고도 한다.

- stack : 지역 변수, 함수 인자, return address와 같이 일시적인 데이터를 저장하는 공간

- heap : malloc과 같은 함수를 통해 메모리를 요청할 때 부여해주는 공간이다.

- data : 전역 변수 또는 data를 저장하는 곳이다.

- text(code) : 프로세스가 생성되고 실행될 때 변하지 않는 값들

![image](https://user-images.githubusercontent.com/64796257/147618436-460c7ca6-bd63-423b-b79e-81a9c6324030.png)

작성한 코드 부분(text)은 메모리에서 text segment에 그대로 복사된다. 그래서 실행중에 크기가 변하지 않는다.

program image에 있는 data 값들도 프로세스가 실행될 때 그대로 프로세스의 data 영역에 복사된다. 전역 변수와 같은 것들이 이곳에 저장된다. 그래서 실행중에 크기가 변하지 않는다. 

프로세스가 실행되면서 크기가 바뀌는 영역은 heap과 stack이다.  
둘 다 프로세스가 실행되면서 어느 정도의 크기를 가지는지 알 수 없기 때문에  
stack은 위에 heap은 아래에 두면서 유동적으로 크기를 가질 수 있도록 구조를 설계했다.

만약에 두 영역이 서로 만나 더 이상 저장할 공간이 없다면 더 이상 데이터를 받지 않는다. 







