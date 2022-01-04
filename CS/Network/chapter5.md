## 5장 HTTP와 연계하는 웹 서버

### 5.1 1대로 멀티 도메인을 가능하게 하는 가상 호스트

HTTP/1.1은 하나의 HTTP 서버에 여러 개의 웹 사이트를 실행할 수 있다. 

ex. 웹 호스팅을 제공하고 있는 사업자 ⇒ 1대의 서버에 여러 고객의 웹 사이트를 넣을 수 있음.

고객마다 다른 도메인을 가지고 다른 웹 사이트를 실행할 수 있는데 이를 위해서 `가상 호스트(Virtual Host)`라는 기능을 사용한다.

![image](https://user-images.githubusercontent.com/64796257/147995461-327b673f-77db-4f79-a62d-3ea0f60f0f91.png)

HTTP를 사용해서 클라이언트가 서버에 액세스할 때는 `www.naver.com` 과 같이 `호스트 이름`이나 `도메인 이름`을 가지고 접근한다. 
인터넷에서 `도메인 이름`은 IP 주소로 변환되고 나서 액세스하게 된다.

결국 request가 서버에 도착할 때는 `IP 주소`를 기준으로 액세스(access)하게 된다. 이 내용은 앞서 DNS를 다룰 때 얘기했던 내용이다.

이 때 1대의 서버 안에 두 개 이상의 도메인이 있을 경우 어느 쪽에 대한 액세스를 해야 할 지 알 수 없다.

ex. 1대의 서버에 www.tricorder.jp , www.hackr.jp 라는 도메인이 있다고 하자.

![image](https://user-images.githubusercontent.com/64796257/147995868-f21f24df-7013-45f5-bd4d-d9c489f58e40.png)

이와 같이 같은 웹 서버 상에서 www.tricorder.jp , www.hackr.jp 이 실행되고 있고 DNS로 이름을 해결한다면

도메인 이름은 다르지만 똑같은 IP주소를 가지게 되면서 서로 다른 도메인 이름의 수신자는 같아진다.

이와 같이 같은 IP 주소에서 다른 호스트 이름과 도메인 이름을 가진 여러 개의 웹 사이트가 실행되고 있는 가상 호스트 시스템에서는
HTTP 리퀘스트를 보내는 경우에 `호스트 이름`과 `도메인 이름`을 완전하게 포함한 URI를 지정하거나  
반드시 Host 헤더 필드에서 지정해야 한다.

### 5.2 통신 중계 프로그램 : 프록시, 게이트웨이, 터널

HTTP는 클라이언트와 서버 이외에 프록시(Proxy), 게이트웨이(Gateway), 터널(Tunnel)과 같은 통신을 중계하는 프로그램과 서버를 연계하는 것이 가능하다.

이러한 프로그램은 서버와 그 다음에 있는 다른 서버의 request를 중계하고 그 서버로 부터 받은 response를 client에게 반환하는 역할을 한다.

#### 1. 프록시(Proxy) 

: 서버와 클라이언트의 양쪽 역할을 중계하는 프로그램. client에서 보낸 request를 서버에 전송하고 server에서 보낸 response를 client에게 전송한다.

- 흐린 부분은 `클라이언트`를 의미한다.  
![image](https://user-images.githubusercontent.com/64796257/147996749-169e198f-4f2e-4eee-a0d5-31814b3997b3.png)

기본적인 동작은 클라이언트로부터 받은 request를 다른 서버에 전송하는 것이다.  
클라이언트로부터 받은 request URI를 변경하지 않으면서 그 다음의 resource를 가지고 있는 서버에 보낸다.

resource 본체를 가진 서버를 오리진 서버(Origin Server)라고 한다.  
오리진 서버로부터 되돌아온 response는 proxy server를 경유해서 client에게 돌아간다.

![image](https://user-images.githubusercontent.com/64796257/147997044-011489c8-fe81-4eda-a946-49ed612a293a.png)

HTTP 통신을 할 때 proxy server를 여러 대 경유하는 것도 가능하다. 

체인과 같이 프록시 서버를 여러 대 경유해서 request랑 response를 중계한다. 중계할 때는 Via 헤더 필드에 경유한 호스트 정보를 추가해야 한다.

그렇다면, 프록시 서버는 왜 사용할까?? 

1) 캐시를 사용해서 네트워크의 대역 등을 효율적인 사용
2) 조직 내의 특정 웹 사이트에 대한 엑세스 제한
3) 액세스 로그를 획득하는 정책을 철저히 지키기 위함

이러한 대표적인 이유들을 위해서 프록시 서버를 사용한다. 

프록시 사용 방법은 크게 2가지로 분류된다.  cache를 하는지 여부 / 메시지 변경 여부

- 캐싱 프록시(caching proxy) : 프록시로 response를 중계할 때 프록시 서버 상에 resource 캐시를 보존해두는 타입의 프록시 

⇒ 만약에 같은 리소스가 다시 request로 왔다면 origin 서버를 거치지 않고 저장했던 cache 내용을 response로 되돌려 준다.

- 투명 프록시(Transparent proxy) : 프록시로 request와 response를 중계할 때 메시지 변경을 하지 않는 타입의 프록시 

#### 2. 게이트웨이(Gateway) 

![image](https://user-images.githubusercontent.com/64796257/147997683-8faa98a3-6295-44d3-ac0a-a2fff1c105ab.png)

게이트웨이 다음에 있는 서버는 HTTP 서버 이외의 서비스를 제공하는 서버다. 

클라이언트와 게이트웨이 사이를 암호화해서 안전하게 접속함으로써 통신의 안정성을 높이는 역할을 한다. 

ex. 쇼핑 사이트에서 신용 카드 결제 시스템과 연계할 때 

#### 3. 터널(Tunnel) 

![image](https://user-images.githubusercontent.com/64796257/147997882-7ef45d92-5e0c-4cf7-b0f8-2a3eea84fa19.png)

터널은 요구에 따라 다른 서버와의 통신 경로를 만들어낸다.  
이때 클라이언트는 SSL과 같은 암호화 통신을 통해 서버와 안전하게 통신하기 위해서 터널을 사용한다.

터널 자체는 HTTP request를 해석하려고 하지 않는다. 

### 5.3 리소스를 보관하는 캐시(cache)

==> 이건 학교에서 배운 내용으로 정리하자. 



















