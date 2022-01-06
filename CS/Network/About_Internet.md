### 인터넷

![image](https://user-images.githubusercontent.com/64796257/148381487-d9bcf620-2315-4d60-a95f-bc52e702340f.png)

컴퓨팅 장비들 : 호스트(host), end-system(네트워크 끝에 있는 장비들) ex. PC, 스마트폰, IoT장비

링크 : 각각의 장비를 연결해주는 것들 ex. 구리선, 광케이블, 무선, 위성 등등

- 전화 또는 라우터에 접근하는 망을 `액세스 네트워크`라 하는데  
망을 새로깔수는 없으니까 집에서는 전화선, 캠퍼스나 회사는 LAN선과 공유기를 통해 인터넷에 접속한다.

- company network를 보면 라우터와 라우터를 서로 연결하는 장치도 있다. 
- 빨간 상자를 보면 라우터와 서버를 직접 연결했다.  
  왜냐하면, 그 부분에 설치하면 접속하기도 수월하고 서비스 돼서 나가는 데이터가 빠른 속도로 퍼지는 장점이 있기 때문이다.   
  ⇒ 대용량 서비스가 가능해진다.
  
- 그래서 아예 라우터를 건물 안에 들여놓고 라우터에 서버를 설치해 서버실을 관리한다.  
  이렇게 건물을 세워 서버를 설치해 사업자들에게 인터넷 서비스 하는 장소를 IDC(Internet Data Center)라고 한다.
  
### 네트워크 구조

- Network Edge : application and hosts (ex. PC, 노트북, 스마트폰, 서버)

![image](https://user-images.githubusercontent.com/64796257/148381769-7ebd1502-fc13-47ed-88d9-f6ec099a0d3a.png)

엔드 시스템에 접속하는 라우터를 `네트워크 엣지 라우터`라 한다.

엣지 라우터의 역할은 트래픽의 양을 조절하는 `레귤레이션(regulation)`의 역할을 한다. 

마치 무제한 요금제에는 데이터를 무제한으로 제공하고 4GB만 주는 요금제는 데이터를 4GB를 제공하는 걸 생각하면 된다.

![image](https://user-images.githubusercontent.com/64796257/148381827-61c6b83d-d598-41ed-9e0c-684768feff1a.png)

각 끝에 있는 엣지들이 서로 통신할 때 2가지 방법이 있다.

1) client – sever model  
: 예를 들어, 게임 서버가 있고 그 서버에 접속하는 클라이언트가 있다.  
  그렇게 접속하는 수많은 클라이언트들이 서버에 접속해 동시에 게임을 즐긴다.  
  즉, 클라이언트가 서버에 요청을 하면 서버에서 그 요청을 받아 결과를 돌려주는 방식을 말한다. ex. Web browser/server, email client/server

2) peer-peer model(P2P)  
: 서버 없이 단말들끼리 서로 접속하는 걸 말한다. 클라이언트 역할(ex. 컴퓨터)과 동시에 서버 역할을 한다.  
  그렇다보니 비용이 더 저렴하다. 다만, 서비스의 안정성에는 문제가 있다.
  
- access Network, Physical Media : communication links – 유/무선 / 이 부분이 네트워크 사이즈의 80% 차지

![image](https://user-images.githubusercontent.com/64796257/148382021-3c3af8ba-0d7a-4b2a-a7ea-c5fb65ec4ced.png)

⇒ 라우터와 End-System이 연결된 선을 의미한다.

1) Residential Access  
- point to point access  
![image](https://user-images.githubusercontent.com/64796257/148382200-c86d52c0-59ba-4657-b3da-07cb69a61e24.png)

전화선을 이용하는 방식을 `ADSL`이라 한다.  
이는 지난 강의에서 얘기했던 300m 내에 있는 중개기에 라우터를 설치해서 인터넷을 이용하는 방법을 말한다.  
아파트는 경비실, 주택은 맨홀 같은 곳에 라우터를 설치했다.

- cable modems

![image](https://user-images.githubusercontent.com/64796257/148382263-1426bdb9-5086-4bd1-9802-e5d286b5190e.png)

이웃 간의 거리가 먼 미국은 다른 방식을 사용한다. 바로 `HFC(Hybrid Fiber Coax)`이다.  
가정 근처에는 fiber를 이용하고 각 가정은 기존에 있던 유선TV를 위해 사용했던 동축 케이블을 이용하는 방식이다.

단점은 각 케이블을 공유하는 방식이어서 누군가가 많이 쓰면 누군가는 느려지는 현상이 발생한다.

2) Companu Access
- Local Access Network(LAN) 

![image](https://user-images.githubusercontent.com/64796257/148382358-a82aca80-0653-42b0-aab0-545b4db940f6.png)
 
LAN은 설치가 수월하다. 토목공사가 필요 없다. 그 중 이더넷(Ethernet)이 랜(LAN)을 표준으로 해서 시장을 점유했다.  
이더넷은 공유된 링크가 엔드 시스템과 라우터를 연결하는 동작을 한다.

- Wireless Access Network(WAN)

![image](https://user-images.githubusercontent.com/64796257/148382442-253e7bc6-f123-4944-a171-ad1ff1838ebc.png)

대표적인 wireless access network로 wireless LANs가 있다. 
Wi-fi라고 불리는 프로토콜이 작동해 사용자가 휴대전화를 통해 인터넷을 이용한다.












