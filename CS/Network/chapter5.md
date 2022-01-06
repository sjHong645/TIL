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

![image](https://user-images.githubusercontent.com/64796257/148383330-df2a877a-8354-430c-b384-77348f7b0f62.png)

client가 DNS server에게 주소를 요청했다. 예를 들어 a.csu.edu에 대해 요청했다.

그러면 DNS 서버가 recursive 또는 iterative 방식을 통해서 a.csu.edu에 대한 ip주소를 찾아서 client에게 전달한다.

여기서 Caching을 이용한다. DNS가 정답이 되는 요청을 client에게 주기 전에 그 정보를 캐시에 넣어둔다.

그러고 나서 똑같은 요청이 들어왔다.  
그러면 이전에 거쳤던 과정 필요없이 캐시를 먼저 살펴보고 나서 바로 정보를 주면 된다.  

그렇게 되면 훨씬 빠른 답을 받을 수 있고 DNS 서버 입장에서는 로드(load)가 훨씬 감소하게 된다.  
그래서 Caching을 이용하면 속도도 빨라지고 네트워크의 로드가 줄어들면서 비용도 줄이는 1석 2조의 효과가 있다.

![image](https://user-images.githubusercontent.com/64796257/148383484-94f1692c-77cb-49f4-b6b8-af0012b78086.png)

그럼 캐싱을 하기 위해선 어떤 점이 중요할까??  

- 캐싱한 DNS 주소가 과거의 데이터일 수 있다. 즉, 내가 caching한 데이터에 대해서 권한을 갖지는 않지만 아마 이게 맞을 거라고 알려준다.

- 그러면 캐시된 정보와 authority를 갖고 있는 정보는 서로 차이가 날 수 있다. 
 
이러한 차이를 없애기 위해 2가지 기법을 사용한다.

1. Authoritative server의 경우 Mapping 결과를 돌려줄 때 TTL(Time-to-live)를 추가한다.
⇒ 즉, 어느 정도의 시간 동안은 이 데이터가 진짜이고 그 사이에 데이터를 바꾸지 않는다는 걸 알려준다.

만약 caching 된 데이터의 TTL이 expired되면 캐시된 걸 돌려주지 않고 authority가 있는 데이터를 server에게 다시 물어보도록 한다.

2. DNS server가 caching 데이터 중 TTL이 expired 된 거를 주기적으로 제거한다.

#### caching에 대해서 굉장히 중요한 부분.

속도가 느리거나 느린 곳에 병목현상이 있을 때 속도 향상을 위해 항상 `캐싱 기법`을 쓸 수 있는지 꼭 생각해보자.

![image](https://user-images.githubusercontent.com/64796257/148383757-361ebcc0-4009-49d9-8acd-a94eb0a6c977.png)


cf) 폰 노이만 아키텍처는 모든 프로그램을 메모리로 저장할 수 있다는 게 기본적인 내용이다. 그리고 메모리에 있는 건 CPU로 가져와서 실행한다. 

예를 들어 폰 노이만 아키텍쳐에서 CPU메모리와 I/O 장치가 있다면 CPU와 메모리 간에 bottleneck이 존재할 수 밖에 없다. 

즉, memory로부터 데이터를 읽어오는 구조라서 cpu가 연산할 때 속도가 떨어질 수 밖에 없다.

이때 CPU 캐시를 둔다.  
memory에 접근하기 전에 캐시에 해당하는 주소에 대한 데이터가 있다면 캐시를 활용해서 접근하기 때문에 CPU 속도가 더 향상된다. 

Memory I/O 장치를 보자. I/O 장치는 CPU에 비해 속도가 굉장히 떨어진다.   
그래서 CPU가 한 번 I/O 장치에 접근할 때 CPU 입장에선 I/O 장치가 너무 느려서 엄청 놀아야 한다.  
그래서 한 번 접근할 때 한 sector에 있는 데이터를 통째로 읽어서 I/O 버퍼에 저장한다.

그래서 I/O 장치에 접근할 때 먼저 해당되는 데이터에 대한 정보가 I/O 버퍼에 있는지 보고  
만약 없다면 직접 장치에 접근해서 그 정보를 I/O 버퍼에 저장한다. 그래서 I/O 버퍼도 일종의 캐시이다.

오른쪽 위 부분을 보자. client는 유저가 사용하는 PC이다. Web server는 네이버 컴퓨터라 보면 된다.  
원래 web server는 cluster 시스템이지만 개념적으로 하나로 취급하자.

client가 웹서버에 웹페이지를 요청하면 웹서버가 그 내용을 가져다 주는데  
이 과정에서 Response가 많아지면 트래픽 양이 많아지고 웹 서버 입장에선 처리해야 할 request가 많아진다.  
그렇다 보니 bottleneck이 존재하게 되는데 이를 어떻게 줄일까?

그에 대한 방법으로 Proxy server라는 게 고안되었다. client가 직접 웹 서버에게 요청하는게 아니라 proxy server에게 요청한다.  
그러면 이 proxy server가 client에게 요청받은 페이지를 살펴보고  
내용이 있다면 client에게 주고 내용이 없다면 web server에게 요청해서 데이터를 받아온다.  

그렇게 받아온 데이터를 proxy server가 저장해서 다음에도 똑같은 요청이 들어오면 그 정보를 client에게 준다.

⇒ 이렇게 되면 수많은 client가 웹서버에게 보내는 request 없이 proxy server와 web server 사이에 한 두 번만 오고가면 되니까  
bottleneck이 줄어 들고 웹 서버 입장에선 로드에 대한 부담도 많이 줄어든다. 하지만, 요즘에는 proxy server를 쓰지 않는다.

이렇게 웹 데이터를 caching 하는 서버를 client 쪽에서 proxy server라고 부른다. proxy 역시 하나의 캐싱 테크닉.

정리하면, 위와 같이 속도가 느린 메모리, I/O 장치, client 서버 사이의 병목현상을 해결하고 속도를 향상하고 싶을 때 캐싱 technique을 생각해볼 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148384300-37b2affc-f673-4d1b-856b-49527a5bfa9d.png)

그런데 캐싱 기법이 효과를 발휘하기 위해서는 반드시 데이터 자체가 `locality of reference` 가 있어야 한다. 

이 지역성(locality)은 2가지로 나뉜다. Temporal(시간적) locality, Spatial(공간적) locality가 있다.

1. Temporal locality  
만약 A라는 데이터에 접근해서 읽고나서 짧은 시간 내에 또 A 데이터에 접근한다면 이는 `temporal locality`가 성립하는 상황이다.  
그러니까, 최근에 참조했던 데이터를 또 참조하는 일이 빈번하게 일어나면 `temporal locality` 가 있는 것이다.

2. Spatial Locality  
만약 100번지를 읽었다면, 주변 101번지 또는 108번지를 짧은 시간 내에 또 읽는다면 이는 `spatial locality`가 성립하는 상황이다.  
그래서 CPU에서 사용하는 캐쉬, I/O 버퍼, proxy server는 다 Locality of Reference가 있다.  
proxy server의 경우 spatial locality는 없지만 temporal locality가 있다.

![image](https://user-images.githubusercontent.com/64796257/148384434-b08a82f6-c89d-4dc1-a673-520ecf910af3.png)

캐싱을 사용하게 되면 overhead가 발생한다.

overhead란 어떤 걸 사용하지 않으면 생기지 않는 상황이지만 그걸 사용하면서 발생하는 상황을 말한다.

만약에 캐시가 없었다면 데이터는 메모리에 한 군데에서만 저장되어 있는 건데 캐시는 메모리에 있는 데이터를 복사한 것이다.  

즉, 두 군데 이상에 데이터가 존재하니까 `캐시에 있는 데이터`와 `메모리에 있는 데이터`의 `consistency 문제`가 발생할 수 밖에 없다.  
그래서 캐시가 없었다면 consistency 문제가 없었을 것이다. 

이런 상황이 캐싱 기법을 적용할 때 발생하는 overhead이다. 

이런 문제 때문에 앞서 언급한 Proxy server를 사용하지 않는다.  
1. 웹 서버에서도 데이터가 계속 업데이트 되는 경우가 많아지면서 proxy server가 별다른 효과를 발휘하기 어렵고
2. 요즘은 웹서버에서 동적으로 파일이 새롭게 생성되는 데이터가 늘었다. 그래서 방금 가져온 데이터가 1초 뒤에는 안쓰는 데이터가 되면서 Temporal Locality가 없어진다.(ex. 시간)

이러한 문제 때문에 요즘엔 proxy server를 잘 사용하지 않는다.


























