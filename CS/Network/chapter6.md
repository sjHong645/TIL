# HTTP 헤더

## 6.1 HTTP 메시지 헤더 

![image](https://user-images.githubusercontent.com/64796257/147999717-90845e89-c7a3-44da-a796-2191e05f019b.png)

HTTP 프로토콜의 request와 response에는 반드시 메시지 헤더가 포함되어 있다.

메시지 헤더에는 클라이언트와 서버가 request나 response를 처리하기 위한 정보가 들어있다.

- Request의 HTTP 메시지 : 메소드, URI, HTTP 버전, HTTP 헤더 필드 등으로 구성됨

![image](https://user-images.githubusercontent.com/64796257/147999792-c3b5a803-b893-4927-a26d-a03370a397c8.png)

- Response의 HTTP 메시지 : HTTP 메시지, HTTP 버전, 상태 코드, HTTP 헤더 필드 등으로 구성됨

![image](https://user-images.githubusercontent.com/64796257/147999881-3d56c77c-15f3-466f-9f25-9653045911a8.png)

이 중에서 `HTTP 헤더 필드`는 가장 다양한 정보를 가지고 있는 요소다. 

헤더 필드는 request와 response 양쪽에 모두 존재하는데 HTTP 메시지에 관한 정보를 가지고 있다.  
이번 장에서는 HTTP/1.1과 일반적으로 자주 사용되고 있는 부분에 대해서 다룬다.

## 6.2 HTTP 헤더 필드 

### 1 중요한 정보를 전달하는 HTTP 헤더 필드 

헤더 필드는 HTTP 프로토콜 중에서 클라이언트와 서버 간의 통신에서 request, response에 둘 다 사용되고 있고 중요한 정보를 전달하는 역할을 담당하고 있다.

뿐만 아니라 메시지 바디의 크기, 사용하고 있는 언어, 인증 정보 등을 브라우저나 서버에 제공하기 위해서도 사용하고 있다.

ex.  
![image](https://user-images.githubusercontent.com/64796257/148000382-e5e587e2-c478-44d8-8151-f3ac40166cba.png)

### 2. HTTP 헤더 필드의 구조 

HTTP 헤더 필드는 `헤더 필드 이름`과 `필드 값`으로 구성되어 있고 `:`으로 나뉘어져 있다. 

``` 
  헤더 필드 명 : 필드 값
  
  ex. Content-Type:text/html 
```
예시를 보면 `Content-Type`이 헤더 필드 명이고 `text/html`이 필드 값이 된다. 

또한 하나의 HTTP 헤더 필드가 여러 개의 필드 값을 가질 수 있다.
```
 Keep-Alive:timeout=15,max=100
```

### 3. 4종류의 HTTP 헤더 필드 ⇒ 용도에 따라 4가지로 분류

- 일반적 헤더 필드(General Header Fields) : request 메시지와 response 메시지에 둘 다 사용되는 헤더

- 리퀘스트 헤더 필드(Request Header Fields) : 클라이언트 측에서 서버 측으로 송신된 request 메시지에 사용되는 헤더  
  request의 부가적인 정보와 클라이언트의 정보, response의 content에 관한 우선 순위 등의 내용을 담고 있다.

- 리스폰스 헤더 필드(Response Header Fields) : 서버 측에서 클라이언트 측으로 송신한 response 메시지에 사용되는 헤더  
  response의 정보와 서버의 정보, client의 추가 정보 등의 내용을 담고 있다.
  
- 엔티티 헤더 필드(Entity Header Fields) : request 메시지와 response 메시지에 포함된 엔티티에 사용되는 헤더  
  콘텐츠 갱신 기간과 같은 엔티티와 관련된 정보를 담고 있다.

### 4. HTTP/1.1 헤더 필드 한눈에 보기

- 일반 헤더 필드 

| 헤더 필드 명 | 설명 | 
| --- | --- | 
| Cache-Control | 캐싱 동작 지정 | 
| Connection | Hop-by-hop 헤더, 커넥션 관리 | 
| Date | 메시지 생성 날짜 | 
| Pragma | 메시지 제어 | 
| Trailer | 메시지의 끝에 있는 헤더 일람 | 
| Transfer-Encoding | 메시지 바디의 전송 코딩 형식 지정 | 
| Upgrade | 다른 프로토콜에 업그레이드 | 
| Via | 프록시 서버에 관한 정보 | 
| Warning | 에러 통지 | 

- 리퀘스트 헤더 필드 

| 헤더 필드 명 | 설명 | 
| --- | --- | 
| Accept | User Agent가 처리 가능한 미디어 타입 | 
| Accept-Charset | 문자셋 우선 순위 | 
| Accept-Encoding | 콘텐츠 인코딩 우선 순위 | 
| Accept-Language | 언어(자연어) 우선 순위 | 
| Authorization | 웹 인증을 위한 정보 | 
| Expect | 서버에 대한 특정 동작의 기대 | 
| From | 유저의 메일 주소 | 
| Host | 요구된 리소스의 호스트 | 
| If-Match | 엔티티 태그의 비교 | 
| If-Modified-Since | 리소스의 갱신 시간 비교 | 
| If-None-Match | 엔티티 태그의 비교(If-Match의 반대) | 
| If-Range | 리소스가 갱신되지 않는 경우 엔티티의 바이트 범위의 요구를 송신 | 
| If-Unmodified-Since | 리소스 갱신 시간 비교(If-Modified-Since의 반대) | 
| Max-Forwards | 최대 전송 Hop 개수 | 
| Proxy-Authorization | 프록시 서버의 클라이언트 인증을 위한 정보 | 
| Range | 엔티티 바이트 범위 요구 | 
| Referer | 리퀘스트의 URI를 취득하는 곳 | 
| TE | 전송 인코딩의 우선 순위 | 
| User-Agent | HTTP 클라이언트의 정보 | 

- 리스폰스 헤더 필드 

| 헤더 필드 명 | 설명 | 
| --- | --- | 
| Accept-Ranges | 바이트 단위의 요구를 수신할 수 있는지 여부 | 
| Age | 리소스의 지정 경과 시간 | 
| Etag | 리소스를 특정하기 위한 정보 | 
| Location | 클라이언트를 지정한 URI에 redirect | 
| Proxy-Authenticate | 프록시 서버의 클라이언트 인증을 위한 정보 | 
| Retry-After | request 재시행의 타이밍 요구 | 
| Server | HTTP 서버 정보 | 
| Vary | 프록시 서버에 대한 캐시 관리 정보 | 
| WWW-Authenticate | 서버의 클라이언트 인증을 위한 정보 | 

- 엔티티 헤더 필드 

| 헤더 필드 명 | 설명 | 
| --- | --- | 
| Allow | 리소스가 제공하는 HTTP 메소드 | 
| Content-Encoding | 엔티티 바디에 적용되는 콘텐츠 인코딩 | 
| Content-Language | 엔티티의 자연어 | 
| Content-Length | 엔티티 바디의 사이즈(byte 단위) | 
| Content-Location | 리소스에 대응하는 대체 URI | 
| Content-MD5 | 엔티티 바디의 메시지 다이제스트 | 
| Content-Range | 엔티티 바디의 범위 위치 | 
| Content-Type | 엔티티 바디의 미디어 타입 | 
| Expires | 엔티티 바디의 유효기간 날짜 | 
| Last-Modified | 리소스의 최종 업데이트 날짜 |

### 5. HTTP/1.1 이외의 헤더 필드 

HTTP 헤더 필드는 RFC2616에서 정의한 47종류만 있는 건 아니다. Set-Cookie, Content-Disposition와 같이 여러 비표준 헤더 필드 역시 존재한다.

### 6. End-to-end 헤더, Hop-by-hop 헤더 

HTTP 헤더 필드는 캐시와 비캐시 프록시의 동작을 정의하기 위해 2가지로 분류된다.

- End-to-end 헤더 : 여기에 분류된 헤더는 request나 response의 최종 수신자에게 전달된다.  
  프록시는 End-to-end 헤더가 수정되지 않은 상태에서 재전송해야 하며 캐시(cache)는 이를 반드시 저장해야 한다.

- Hop-by-hop 헤더 : 프록시에 의해서 재전송되거나 캐시(cache) 되어서는 안된다. 

⇒ 여기에 속한 헤더 필드는 8가지가 있고 나머지는 모두 End-to-end 헤더에 분류된다.

Connection, Keep-Alive, Proxy-Authenticate, Proxy-Authorization, Trailer, TE, Transfer-Encoding, Upgrade

## 6.3 HTTP/1.1 일반 헤더 필드 

: 일반 헤더 필드는 request 메시지와 response 메시지 양족에서 모두 사용되는 헤더이다.

### 1 Cache-Control : 지정된 디렉티브의 명령에 따라 캐싱 동작을 수행한다. 

![image](https://user-images.githubusercontent.com/64796257/148010807-15ce28ce-ecf6-4b5e-a10a-60e92cfb0941.png)

지정한 디렉티브에는 `파라미터`가 있을 수도 있고 없을 수도 있다. 여러 개의 디렉티브를 지정하는 경우 `,`로 구분한다.

ex) 
```
  Cache-Control: private, max-age=0, no-cache
```

#### Cache-Control 디렉티브 들 

1) cache request 디렉티브

| 디렉티브 | 파라미터 | 설명 | 
| --- | --- | --- | 
| no-cache | 없음 | origin server에 강제적인 재확인 요구 |
| no-store | 없음 | 캐시는 request, response의 내용들을 저장하면 안 됨 |
| max-age = [초] | 필수 | response의 최대 age 값 |
| max-stae(=[초]) | 생략 가능 | 기한이 지난 response 수신 |
| min-fresh = [초] | 필수 | 지정한 시간 이후에 변경된 response 보냄 |
| no-transform | 없음 | 프록시는 미디어 타입을 변환하면 안됨 |
| only-if-cached | 없음 | 캐시에서 리소스 취득 |
| cache-extension | - | 새로운 디렉티브를 위해 토큰 |

2) cache response 디렉티브

| 디렉티브 | 파라미터 | 설명 | 
| --- | --- | --- | 
| public | 없음 | 어딘가에 response 캐시 가능 |
| private | 생략 가능 | 특정 유저에 대해서만 response |
| no-cache | 생략 가능 | 유효성의 재확인 없이 캐시 사용 불가 |
| no-store | 없음 | 캐시는 request, response의 내용들을 저장하면 안 됨 |
| no-transform | 없음 | 프록시는 미디어 타입을 변환하면 안됨 |
| must-revalidate | 없음 | 캐시 가능하지만 오리진 서버에 리소스의 재확인 요구 |
| proxy-revalidate | 없음 | 중간 캐시 서버에 대해 캐시했던 response의 유효성 재확인 요구 |
| max-age = [초] | 필수 | 리스폰스의 최대 Age 값 |
| s-maxage = [초] | 필수 | 공유 캐시 서버의 response 최대 Age 값 |
| cache-extension | - | 새로운 디렉티브를 위한 공간 |

각각에 대해서 살펴보겠다. 

##### 캐시가 가능한지 여부를 나타내는 디렉티브 

- public 디렉티브 ⇒ Cache-Control: public  

⇒ public 디렉티브를 사용하면 다른 유저들도 사용할 수 있는 캐시를 해도 좋다는 것을 명시한다.

- private 디렉티브 ⇒ Cache-Control: private

![image](https://user-images.githubusercontent.com/64796257/148012918-0401eb6f-fda3-4d64-a045-337983cabdc4.png)

⇒ private 디렉티브를 사용하면 특정 유저만을 대상으로 response가 캐시되어야 한다는 것을 나타낸다. 

이때, 캐시 서버는 특정 유저를 위해서 리소스를 캐시할 수 있지만, 다른 유저로부터 같은 request가 온다면 그 캐시를 반환하지 않는다.

- no-cache 디렉티브 

![image](https://user-images.githubusercontent.com/64796257/148013354-d6d752ad-652d-410d-925c-157769070517.png)

⇒ Cache-Control: no-cache 

no-cache 디렉티브는 캐시로부터 업데이트되지 않은 오래된 리소스가 반환되는 것을 막기 위해 사용된다.

이는 캐시된 복사본을 사용자에게 보여주기 전에 재검증을 위한 요청을 오리진 서버(origin server)에 보내도록 강제한다.

##### 캐시로 저장할 수 있는지 여부를 제어하는 디렉티브

- no-store 디렉티브 ⇒ Cache-Control: no-store 

no-store 디렉티브를 사용했다면 캐시를 하지 못하도록 한다는 것을 의미한다.

##### 캐시 기한이나 검증을 지정하는 디렉티브 

- s-maxage 디렉티브 ⇒ Cache-Control: s-maxage=604800(초 단위) 

s-maxage 디렉티브의 기능은 max-age 디렉티브의 기능과 동일하지만 차이점이 있다. 여러 유저가 사용할 수 있는 공유 캐시 서버에만 적용된다는 점이다.  
그리고 s-maxage 디렉티브를 사용한다면 Expires 헤더 필드와 max-age 디렉티브는 무시된다.

- max-age 디렉티브 ⇒ Cache-Control: max-age=604800(초 단위) 

![image](https://user-images.githubusercontent.com/64796257/148014767-d62a6313-59c2-413d-ab55-af71c9102807.png)

client의 request로 max-age 값을 지정했다면 캐시 서버에 요청한 데이터가 지정한 시간보다 수명이 작다면 해당 데이터를 받아들일 수 있도록 한다.

server의 respone에서 max-age 값을 지정했다면 캐시 서버는 지정한 시간을 초과하지 않을 때까지 오리진 서버에 유효성을 재확인하지 않고 캐시할 수 있다.

- min-fresh 디렉티브 ⇒ Cache-Control: min-fresh=60(초 단위)

![image](https://user-images.githubusercontent.com/64796257/148015427-9ff181b5-3197-4e48-81aa-a8118679c42a.png)

min-fresh 디렉티브를 사용하면 캐시된 리소스가 지정된 시간동안 변경되지 않는 응답을 원한다는 것을 의미한다.

ex. Cache-Control: min-fresh=60(초 단위) = 컨텐츠가 지금을 기준으로 60초 동안 변경되지 않는 응답을 원한다는 것을 의미한다.

- max-stale 디렉티브 ⇒ Cache-Control: max-stale=3600(초 단위)

max-stale 디렉티브를 사용하면 캐시된 리소스의 유효기간이 끝났더라도 받아들일 수 있음을 나타낸다.

디렉티브의 값이 지정됐다면 client는 유효 기한이 지난 이후로 지정한 시간 값 만큼 지난 response를 받아들인다. 

값이 지정되지 않았다면 client는 유효기간으로부터 시간이 많이 지나도 response를 받아들일 수 있다.

- only-if-cached 디렉티브 ⇒ Cache-Control: only-if-cached 
 
only-if-cached 디렉티브를 사용한다면 client는 캐시 서버에 들어있는 리소스만을 반환하도록 한다.

즉, 캐시 서버에서 response의 reload와 유효성을 재확인하지 않도록 한다. 

- must-revalidate 디렉티브 ⇒ Cache-Control: must-revaildate 

must-revaildate 디렉티브를 사용하면 response의 캐시가 현재 유효한지 아닌지 여부를 origin server가 확인하도록 한다.  
max-stale 디렉티브가 있어도 무시하고 must-revalidate 디렉티브의 동작을 실행한다.

- proxy-revalidate 디렉티브

proxy-revalidate 디렉티브를 사용하면 모든 캐시 서버에 대해서 response를 반환할 때 반드시 유효성을 재확인하도록 한다.

- no-transform 디렉티브 

no-transform 디렉티브를 사용하면 request, response 양쪽에서 캐시가 엔티티 바디의 미디어 타입을 변경하지 않도록 지정한다.

이렇게 해서 캐시 서버 등에 의해 이미지가 압축되는 것을 방지한다.

### 2 Connection

Connection 헤더 필드는 2가지 역할을 한다. 각각의 역할에 대해 알아보자.

- 프록시에 더 이상 전송하지 않는 헤더 필드 지정

![image](https://user-images.githubusercontent.com/64796257/148022987-97b51699-235c-4997-a34b-ffb70fb32a7f.png)

```
  Connection: 더 이상 전송하지 않는 헤더 필드 이름
```

위와 같이 client의 request 또는 server의 response에서 Connection 헤더 필드를 사용해서 proxy 서버에 더 이상 전송하지 않는 헤더 필드를 지정할 수 있다.

- 지속적 접속 관리

![image](https://user-images.githubusercontent.com/64796257/148023171-906e38b6-c1f7-4aa0-8aea-070f1abde8df.png)

``` 
  Connection : Close
```

server 측에서 명시적으로 접속을 끊고 싶을 때 Connection 헤더 필드에 Close를 지정한다.

지속적으로 접속을 하고 싶다면 Connection : Keep-Alive 라고 입력하면 된다.

### 3 Date 

Date 헤더 필드는 HTTP 메시지를 생성한 날짜를 나타낸다. 

HTTP/1.1에서는 RFC1123에 다음과 같은 날짜 포맷이 지정되어 있다.

```
  Date : Tue, 03 Jul 2012 04:40:59 GMT
  
  # 2012년 7월 3일 화요일 4시 40분 59초에 만들어진 HTTP 메시지
```

### 4 Pragma 

Pragma 헤더 필드는 HTTP/1.0 과의 호환성을 위해서 정의되어 있는 헤더 필드이다.

지정할 수 있는 형식은 1개밖에 없다. Pragma: no-cache 

Pragma 헤더 필드는 일반 헤더 필드이지만 client의 request에서만 사용할 수 있다. client가 캐시된 리소스의 response를 원하지 않을 때 사용한다.

물론 앞서 살펴본 바와 같이 Cache-control: no-cache를 하면 되겠지만 중간 서버의 HTTP 버전이 어떻게 될 지는 알 수 없기 때문에 다음과 같이 작성해준다.

```
  Cache-Control : no-cache
  Pragma : no-cache
```

### 5 Trailer

![image](https://user-images.githubusercontent.com/64796257/148023806-6ef7c488-b078-4e4d-9298-7ac3d20a62a4.png)

Trailer 헤더 필드는 메시지 바디의 뒤에 기술되어 있는 헤더 필드는 미리 전달한다.

이는 HTTP/1.1에 구현되어 있는 청크 전송 인코딩을 사용하고 있다면 사용할 수 있다.

ex.  
![image](https://user-images.githubusercontent.com/64796257/148024504-06d730ed-8d67-4336-a1d6-446a10eec2d7.png)

Trailer 헤더 필드에 Expires를 지정해서 메시지 바디의 뒤에 Expires 헤더 필드가 나타난다는 것을 보여준다.

### 6 Transfer-Encoding 

![image](https://user-images.githubusercontent.com/64796257/148024647-89a252e6-5429-4df6-9741-3d2a28432ca1.png)

Transfer-Encoding 헤더 필드는 메시지 바디의 전송 코딩 형식을 지정하는 경우에 사용한다.

ex.  
![image](https://user-images.githubusercontent.com/64796257/148024717-80abd977-4e26-476a-bde5-81f14289138b.png)

Transfer-Encoding 헤더 필드의 값을 위와 지정한 것과 같이 전송 코딩 형식으로 `청크 전송`을 정의했다. 

### 7 Upgrade 

Upgrade 헤더 필드는 HTTP 및 다른 프로토콜의 새로운 버전이 통신에 이용되는 경우에 사용된다. 

![image](https://user-images.githubusercontent.com/64796257/148025207-686bd482-d186-45ac-b10a-77660e185206.png)

### 8 Via

Via 헤더 필드는 client와 server 간의 request 혹은 response 메시지의 경로를 알기 위해서 사용한다.

proxy 혹은 gateway는 자신의 서버 정보를 Via 헤더 필드에 추가한 뒤에 메시지를 전송한다. proxy를 경유하는 경우에는 반드시 추가되어야 하는 정보다. 

![image](https://user-images.githubusercontent.com/64796257/148026055-1bcbc2d9-e925-4f95-8961-fbdc07143f1d.png)

프록시 서버 A에서 Via 헤더 필드에 `1.0 gw.hackr.jp(Squid/3.1)` 이라는 문자열이 추가된다.  
프록시 서버 B에서 Via 헤더 필드에 `1.1 a1.example.com(Squid/2.7)` 이라는 문자열이 동일한 원리로 추가된다. 

이와 같이 각각의 프록시 서버는 자신의 정보를 Via 헤더에 추가한다. 

이러한 Via 헤더는 리소스 전송 경로를 파악하기 위해 TRACE 메소드와 연계해서 자주 사용된다. 

### 9 Warning 

Warning 헤더는 response에 관한 추가 정보를 전달한다. Warning 헤더 형식은 다음과 같다 

![image](https://user-images.githubusercontent.com/64796257/148026495-0d05dae2-13e6-419c-8a0f-9329189ee231.png)

HTTP/1.1에는 7개의 경고 코드가 정의되어 있다. 권장사항이고 이후에 코드를 추가할 수 있다.

| 코드 | 경고문 | 설명 |
| --- | --- | --- | 
| 110 | Response is state | 프록시가 유효기간이 지난 리소스를 반환했다 | 
| 111 | Revalidation failed | 프록시가 리소스의 유효성 재확인에 실패했다 | 
| 112 | Disconnection operation | 프록시가 네트워크와 연결이 끊겨있다. | 
| 113 | Heuristic expiration | response가 24시간 경과하고 있는 경우 | 
| 199 | Miscellaneous warning | 임의의 경고문 | 
| 214 | Transformation applied | 프록시가 인코딩과 미디어 타입 등에 대응해서 무언가를 처리중 | 
| 299 | Miscellaneous persistent warning | 임의의 경고문 | 

## 6.4 Request 헤더 필드 

Request 헤더 필드는 client 측에서 server 측으로 송신된 request 메시지에 사용되는 헤더이다. 

![image](https://user-images.githubusercontent.com/64796257/148027083-ecc2fcd2-4f37-4730-ba95-f9e76175cc00.png)

### 1 Accept 

![image](https://user-images.githubusercontent.com/64796257/148028905-8133f39b-e481-4355-a8c0-b657f74ef548.png)

Accept 헤더 필드는 user agent에 처리할 수 있는 미디어 타입과 미디어 타입의 상대적인 우선 순위를 전달하기 위해 사용된다.

미디어 타입의 지정은 `타입/서브 타입` 과 같이 입력해서 한번에 여러 개 설정할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148029152-199aeaff-de4d-409f-ba02-d7cef913c256.png)

ex. 브라우저가 PNG 이미지를 표시하지 못한다면 

Accept에 image/png를 지정하지 않고 image/gif 또는 image/jpeg 등을 지정해야 한다.

표시하는 미디어 타입에 우선 순위를 붙이고 싶다면 `;`으로 구분하고 `q=`로 표시할 품질 지수를 덧붙힌다. 품질 계수는 0~1 범위에 있는 수치로 1이 높은 값이다. default로 ;q=1.0을 나타낸다. 

ex. application/html;q = 원하는 가중치

서버가 복수의 content를 반환할 수 있다면 가장 높은 품질 계수의 미디어 타입으로 반환한다.

ex.  
``` 
  Accept: text/html, application/xhtml+xml, application/xml; q = 0.9, */*;q = 0.8 
```
받을 수 있는 타입 text/html, application/xhtml+xml, application/xml, */*(모든 타입)

그 중에서 application/xml, */*(모든 타입)은 각각 가중치 0.9와 0.8을 할당받았다. 나머지는 default값으로 가중치가 1이다.

### 2 Accept-Charset

![image](https://user-images.githubusercontent.com/64796257/148029917-097c8a99-057e-436f-9ba3-2fe3cc77db30.png)

Accept-Charset 헤더 필드는 user agent에서 처리할 수 있는 문자셋으로 문자셋의 상대적인 우선 순위를 전달하기 위해 사용된다.  
물론, 한 번에 여러 개를 지정할 수 있다.

Accept 헤더 필드와 마찬가지로 품질 지수에 의해서 상대적인 우선 순위를 표시할 수도 있다. 

ex.  
```
  Accept-Charset: iso-8859-5, unicode-1-1; q=0.8
```

처리할 수 있는 문자셋은 iso-8859-5, unicode-1-1가 있다. 그 중에서 unicode-1-1은 가중치로 0.8을 부여받았다.

### 3 Accept-Encoding 

![image](https://user-images.githubusercontent.com/64796257/148030336-c3c9874e-8730-4013-b402-98022a63ce39.png)

Accept-Encoding 헤더 필드는 user agent에서 처리할 수 있는 콘텐츠 코딩과 콘텐츠 코딩의 상대적인 우선 순위를 전달하기 위해서 사용된다.  
물론, 한 번에 여러 개를 지정할 수 있다.

- gzip, compress, deflate, identity  
![image](https://user-images.githubusercontent.com/64796257/148030506-75e09e7a-2b94-4c72-966b-6ec6ec56868a.png)

Accept 헤더 필드와 같이 품질 지수에 의해서 상대적인 우선순위를 표시하고 `*`를 통해서 모든 인코딩 포맷을 가리킬 수도 있다.

### 4 Accept-Language 

![image](https://user-images.githubusercontent.com/64796257/148030711-108e66b0-ff7b-4497-afbb-c01bc58edb3f.png)

Accept-Language 헤더 필드는 user-agent가 처리할 수 있는 자연어 세트와 자연어 세트의 상대적인 우선 순위를 전달하기 위해서 사용된다.  
한 번에 여러 개를 지정할 수 있다.

``` 
  Accept-Language: ko-kr, en-us; q=0.7, en;q=0.3
```

처리할 수 있는 문자셋은 ko-kr, en-us, en이 잇고 en-us와 en은 가중치로 각각 0.7, 0,3을 부여받았다.

### 5 Authorization 

![image](https://user-images.githubusercontent.com/64796257/148032014-2408a8bd-ead4-4a27-aa6a-481742878328.png)

Authorization 헤더 필드는 user agent의 인증 정보를 전달하기 위해 사용된다. 

일반적으로 서버에 인증을 받으려 하는 user agent는 상태 코드 401 response 다음에 request를 보낼 때 Authorization 헤더 필드를 포함한다.

### 6 Expect 

![image](https://user-images.githubusercontent.com/64796257/148032962-ecd3748b-fa05-49fc-894b-7687a1744465.png)

Expect 헤더 필드는 client가 server에 특정 동작 요구를 전달할 때 사용한다.

기대한 요구에 서버가 응답하지 못해서 에러가 발생할 때 417 Expectation Failed를 반환한다. 

HTTP/1.1 사양에서는 100-continue 만 정의되어 있다.  
그래서 상태코드 100 response를 가진 client는 request를 할 때 Expect: 100-continue를 지정해야 한다.

### 7 From 

![image](https://user-images.githubusercontent.com/64796257/148033314-c143acda-89e3-42e5-a173-b6f81fee6f29.png)

From 헤더 필드는 user agent가 사용하고 있는 유저의 메일 주소를 전달한다.

### 8 Host

![image](https://user-images.githubusercontent.com/64796257/148068093-0aa8e7af-f5d0-4c2d-900c-22cf8421c7df.png)

Host 헤더 필드는 request한 리소스의 인터넷 호스트와 포트 번호를 전달한다. 

Host 헤더 필드가 존재하는 이유는 1대의 서버에서 복수의 도메인을 할당할 수 있는 가상 호스트의 구조와 깊은 관련이 있다.

request가 서버에 오면 호스트 이름을 IP 주소로 변환해서 request가 처리된다.  
이때 같은 IP 주소로 복수의 도메인이 적용되어 있다면 어떤 도메인에 대한 request인지 알 수 없다.

그래서 Host 헤더 필드에 request를 받을 호스트 이름을 명확하게 해둘 필요가 있다. 

### 9 If-Match

![image](https://user-images.githubusercontent.com/64796257/148068914-f9a9064f-de91-4027-bb8f-73a528365f14.png)

If-xxx 라는 서식의 request 헤더 필드를 `조건부 리퀘스트`라고 한다. 

조건부 리퀘스트를 받은 서버는 지정된 조건에 맞는 경우에서만 request를 받는다. 

![image](https://user-images.githubusercontent.com/64796257/148069205-e4d33cca-6f62-4458-84a0-cc76514b17d6.png)

If-Match 헤더 필드는 조건부 리퀘스트 중 하나로 서버 상의 리소스를 특정하기 위해서 엔티티 태그(ETag) 값을 전달한다. 

서버는 `If-Match의 필드 값`과 `리소스의 ETag 값`이 `일치`하는 경우에만 request를 받아들인다.  
일치하지 않으면 상태코드 412 Precondition Failed 리스폰스를 반환한다.

If-Match의 필드 값을 `*`로 지정했다면 ETag 값에 상관없이 request를 처리할 수 있다.

### 10 If-Modified-Since

![image](https://user-images.githubusercontent.com/64796257/148069527-5a4a2341-af62-486c-90a3-3454e8ec6de0.png)

ex. If-Modified-Since : Thu, 15 Apr 2004 00:00:00 GMT

If-Modified-Since 헤더 필드는 리소스의 갱신 날짜가 필드 값 이후의 값이라면 request를 받아들이겠다는 내용을 전달한다.

필드 값 이후에 갱신되지 않았다면 상태코드 304 Not Modified 리스폰스를 반환한다.

위 그림의 첫 번째 부분의 내용을 보면 갱신 날짜가 4/29 즉, 필드 값인 4/15 이후에 갱신되었기 때문에 request를 받아들인 것이다. 

### 11 If-None-Match

![image](https://user-images.githubusercontent.com/64796257/148070144-62131d71-4549-404d-88ac-abd2bc1898bd.png)

If-None-Match 헤더는 If-Match 헤더와 반대로 동작한다. 

If-None-Match의 `필드 값에 지정된 ETag 값`이 `지정된 리소스의 ETag 값`과 `일치하지 않으면` request를 받아들인다.

GET과 HEAD 메소드에서는 If-None-Match 헤더 필드를 통해 최신 리소스를 요구한다.

### 12 If-Range

![image](https://user-images.githubusercontent.com/64796257/148070507-b5b3aa4e-c2c7-4bc6-a239-2f483904eb3d.png)

If-Range 헤더 필드는 If-Range로 지정한 필드 값과 리소스의 ETag 값 혹은 날짜가 일치하면 리소스의 원하는 범위를 Range 를 통해 전달한다.  
일치하지 않으면 리소스 전체를 반환하도록 한다.

위 그림을 보면 If-Match를 통해 ETag 값이 일치하면 원하는 범위인 5001~10000을 보내도록 했다.  
만약에 ETag 값이 일치하지 않으면 리소스 전체를 보낸다.

서버 쪽에 있는 리소스가 갱신되어 있다면 If-Range를 사용하지 않아야 한다.

왜냐하면 client 쪽에서 가지고 있는 것들은 서버쪽에서 새로 갱신된 리소스와는 당연히 맞지 않기 때문에 Range 리퀘스트 역시 무효가 되기 때문이다.


### 13 If-Unmodified-Range

If-Modified-Range의 반대 동작이다. 지정된 리소스가 필드 값에 지정된 날짜 이전에 갱신되어 있다면 request를 받아들이도록 한다.

이를 만족하지 않으면 상태코드 412 Precondition Failed 리스폰스를 반환한다.

### 14 Max-Forwards

![image](https://user-images.githubusercontent.com/64796257/148142613-2942f19f-a7cc-424f-8d32-d5c48dae1a84.png)

Max-Forwards 헤더 필드는 TRACE 또는 OPTIONS 메소드에 대한 request를 할 때 전송할 수 있는 서버의 개수의 최대 값을 10진수 정수로 지정한다.

서버는 다음 서버에 request를 전달할 때 마다 Max-Forwards 값을 1씩 뺀다. 그렇게 최종적으로 0이 되었을 때 서버에서 response를 반환한다.

HTTP를 사용한 통신에서는 request가 프록시 서버 등과 같은 여러 대의 서버를 경유해 가는 경우가 있다.  
전달 도중에 프록시 서버에서 알 수 없는 원인으로 request 전송을 실패한다면 client는 이에 대한 response가 되돌아오지 않기 때문에 전송을 실패했는지 여부를 알 수 없다.

이러한 문제가 발생한 경우에 원인을 조사하기 위해서 Max-Forwards 헤더 필드가 활용된다.

필드 값이 0이 된 서버에서 response를 반환하기 때문에 해당 서버까지의 상황을 파악할 수 있게 된다.

### 15 Proxy-Authorization

Proxy-Authorization 헤더 필드는 프록시 서버에서 인증 요구를 받아들일 때 인증에 필요한 클라이언트의 정보를 전달한다.

이는 클라이언트와 프록시 사이에서 인증이 이뤄진다. 

### 16 Range

ex. Range: bytes=5001-10000

앞서 얘기한 If-Range, If-Unmodified-Range 에서 사용되었다.

### 17 Referer

![image](https://user-images.githubusercontent.com/64796257/148143257-c64778f9-8fe7-467b-b28e-24d1e1fe28ab.png)

Referer 헤더 필드는 request의 URI가 어떤 웹 페이지로부터 발행되었는지를 나타낸다.

기본적으로는 Referer 헤더 필드를 보내야 하지만 주소창에 직접 URI를 입력하는 건 보안상 바람직하지 않다고 판단할 수 있어서 꼭 보내지 않아도 된다.

### 18 TE

ex. TE: gzip, deflate;q=0.5

TE 헤더 필드는 response로 받을 수 있는 전송 코딩 형식과 상대적인 우선 순위를 전달한다.

위 예시는 gzip, deflate를 받을 수 있는데 gzip의 가중치는 q=1이고 deflate의 가중치는 q=0.5로 gzip의 우선순위가 더 높다.

### 19 User-Agent

![image](https://user-images.githubusercontent.com/64796257/148144460-6f6b6160-235c-4391-8a69-20b3d01c8379.png)

User-Agent 헤더 필드는 request를 생성한 브라우저와 user agent의 이름 등을 전달하기 위한 헤더 필드이다.

## 6.5 Response 헤더 필드 

Response 헤더 필드는 `서버` 에서 `클라이언트`로 송신되는 response 메시지에 적용되는 헤더로 

response의 부가 정보, 서버 정보, client에 요구하는 부가적인 정보 등을 담고있다.

![image](https://user-images.githubusercontent.com/64796257/148144651-e4b3af9e-87d0-4ae4-ac64-3a85b35485ca.png)

### 1 Accept-Ranges

![image](https://user-images.githubusercontent.com/64796257/148144682-4cc61ff9-5b37-4d74-a3f2-efdf6342f014.png)

Accept-Ranges 헤더 필드는 서버가 resource의 일부분만 지정해서 취득할 수 있는 Range 리퀘스트를 접수할 수 있는지 여부를 나타낸다.

서버가 range 리퀘스트를 수신할 수 있다면 bytes, 수신할 수 없다면 none 이라고 지정한다.

### 2 Age

![image](https://user-images.githubusercontent.com/64796257/148144849-f0f37184-faa0-4869-84db-0de26a64b3c8.png)

Age 헤더 필드는 서버에서 response가 생성되고 나서 지난 시간을 의미한다. 

즉, Age: 600 의미는 response가 생성되고 나서 600초 = 10분이 지났다는 것을 의미한다.

만약에 response한 서버가 캐시 서버라면 캐시된 response가 origin 서버에서 리소스를 가져오고나서 얼마나 오랫동안 가지고 있었는지를 나타낸다.

프록시가 response를 생성했다면 Age 헤더 필드는 필수적으로 있어야 한다.

### 3 ETag

![image](https://user-images.githubusercontent.com/64796257/148145206-941e0211-b5fa-4858-a0ea-b86bfce30160.png)

ETag 헤더 필드는 엔티티 태그라고 불리고 리소스를 특정하기 위한 문자열을 전달한다. 서버는 리소스마다 ETag 값을 할당한다.

그리고 리소스가 갱신이 되면 ETag의 값도 바뀐다. 

![image](https://user-images.githubusercontent.com/64796257/148145528-1b35ed2c-685e-408c-bd95-3b187dfe5635.png)

리소스를 캐시할 때 리소스를 임의로 정하고 싶을 때가 있다.

똑같은 www.google.com 에 접속해도 한국어 버전의 브라우저를 사용해서 접근하면 한국어 리소스가 반환되지만 영어 버전의 브라우저를 사용하면 영어 리소스를 반환한다.

이렇게 URI는 같지만 URI 만으로 캐시했었던 리소스를 특정하기가 어려울 수 있다. 때문에 ETag 헤더 필드를 추가해서 리소스를 특정한다.

### 4 Location

![image](https://user-images.githubusercontent.com/64796257/148145728-5100941e-a8c8-41be-a937-e5290b04b44c.png)

Location 헤더 필드는 response의 수신자에게 다른 리소스로 접근하도록 유도하는 경우에 사용된다. 이는 기본적으로 `3xx: Redirection` 으로 응답한다.

ex. Location: www.naver.com/sample.html 이라 하면

클라이언트가 요청한 URI의 sample.html을 response하지 않고 Location 헤더 필드에서 지정한 www.naver.com/sample.html 을 response한다. 

### 5 Proxy-Authenticate

Proxy-Authenticate 헤더 필드는 프록시 서버에서의 인증 요구를 client에 전달한다.

### 6 Retry-After

Retry-After 헤더 필드는 client가 일정 시간 후에 request를 다시 시행해야 한다는 것을 전달한다.

ex. Retry-After : 120 ⇒ 서버에서 다음과 같은 내용을 보냈다면 client는 120초=2분후에 다시 그 서버에 request를 하면 된다.

주로 상태 코드 503 Service Unavailable 리스폰스나 3xx Redirect 리스폰스와 함께 사용된다.

### 7 Server

![image](https://user-images.githubusercontent.com/64796257/148146346-7e9094aa-e576-43ea-99be-bc88d2a84e9c.png)

Server 헤더 필드는 서버에 설치되어 있는 HTTP 서버의 SW를 전달한다.

ex. Server: Apache/2.2.17(Unix)

### 8 Vary

![image](https://user-images.githubusercontent.com/64796257/148146472-ed1cb6cd-a2ac-40fc-b1d8-74140eec39ab.png)

Vary 헤더 필드는 캐시를 control 하기 위해서 사용한다. 
origin server가 proxy server에 로컬 캐시를 사용하는 방법에 대한 지시를 전달한다.

위 그림을 통해 좀 더 살펴보자.

origin server에서 Vary: Accept-Language라고 했다. 

이러한 response를 받은 프록시 서버는 캐시된 때의 request에서 받았던 Accept-Language: en-us를 가진 request에 대해서만 캐시를 반환한다.

같은 Accept-Language 를 가지고 있다면 캐시에서 response하고 다르면 origin server로 부터 리소스를 가져와야 한다.

### 9 WWW-Authenticate

WWW-Authenticate 헤더 필드는 HTTP 액세스 인증에 사용된다. 

Request-URI에 지정했던 리소스에 적용할 수 있는 인증 스키마와 파라미터를 나타내는 challenge를 전달한다.

## 6.6 Entity 헤더 필드

엔티티 헤더 필드는 request 메시지와 response 메시지에 모함된 엔티티에 사용되는 헤더이다. 

콘텐츠의 갱신 시간과 같이 엔티티와 관련된 정보를 포함한다. 

### 1 Allow

![image](https://user-images.githubusercontent.com/64796257/148148321-fcc013a6-72cb-4012-95ff-519226f54fb1.png)

Allow 헤더 필드는 Request-URI에 지정된 리소스가 제공하는 메소드를 지정한다. 

서버가 받을 수 없는 메소드를 수신한다면 상태코드 405 Method Not Allowed 리스폰스와 함께 수신 가능한 메소드의 내용을 담은 Allow 헤더 필드를 반환한다.

### 2 Content-Encoding

Content-Encoding 헤더 필드는 서버가 엔티티 바디에 대해서 실시한 콘텐츠 코딩 형식을 전달한다. 

ex. Content-Encoding : gzip ⇒ 콘텐츠의 코딩을 압축할 것을 지시함.

### 3 Content-Language

: 엔티티 바디에 사용된 자연어를 전달한다. ex. Content-Language : en

### 4 Content-Length

: 엔티티 바디의 크기를 byte 단위로 전달한다. ex. Content-Length: 15000

### 5 Content-Location

ex. Content-Location : www.naver.com/index.html

Content-Location 헤더 필드는 메시지 바디에 대응하는 URI를 전달한다. 

Location 헤더 필드와 달리 Content-Location은 메시지 바디로 반환된 리소스의 URI를 나타낸다.

ex. Accept-Language 헤더 필드를 사용한 서버 구동형 request는 실제로 요구한 오브젝트와는 다른 페이지가 반환되었을 때

Content-Location 헤더 필드에 있는 URI에 접근한다.

### 6 Content-MD5

![image](https://user-images.githubusercontent.com/64796257/148149649-2a0fc378-122a-4211-871e-d8eec6c9e83e.png)

Content-MD5 헤더 필드는 메시지 바디가 변경되지 않고 도착했는지 확인하기 위해 MD5 알고리즘에 의해 생성된 값을 전달한다.

메시지 바디에 MD5 알고리즘을 적용해서 얻은 128bit의 binary 값에 Base64 인코딩을 해서 필드 값에 기록한다.

유효성을 확인하기 위해서 수신한 client의 메시지 바디에 똑같이 MD5 알고리즘을 실행한다.  
여기서 출력된 값과 기존에 보냈던 Content-MD5의 필드 값을 비교해서 메시지 바디가 변경되지 않았는지 확인한다.

### 7 Content-Range

![image](https://user-images.githubusercontent.com/64796257/148149907-a69ed00e-159e-41ae-a1ae-b8238281aa97.png)

Content-Range 헤더 필드는 범위를 지정해서 일부분을 request 하는 Range 리퀘스트에 대해 response할 때 사용한다.

response로 보낸 엔티티가 어느 부분에 해당하는가를 전달한다. 

ex. Content-Range : bytes 5001-10000/10000 

⇒ 전체 10000바이트 중에서 5001-10000 바이트 부분을 보내준다는 의미이다.

### 8 Content-Type

ex. Content-Type: text/html; charset=UTF-8

Content-Type 헤더 필드는 엔티티 바디에 포함되는 오브젝트의 미디어 타입을 전달한다.  
여기서는 타입은 text, 서브 타입으로는 html 타입이 되도록 했다.

charset 파라미터를 통해서 UTF-8 문자셋을 처리하도록 했다.

### 9 Expires

![image](https://user-images.githubusercontent.com/64796257/148150399-e880cc47-2d01-4fc6-a5c6-4b3f8bcb4a38.png)

ex. Expires: Wed, 04 Jul 2012 08:26:05 GMT

Expires 헤더 필드는 리소스의 유효 기한 날짜를 전달한다. 

캐시 서버가 Expires 헤더 필드를 포함한 리소스를 수신했다면 필드 값으로 지정된 날짜까지 response의 복사본을 유지한다.  
위에 있는 예시를 가지고 설명하면 2012년 7월 4일까지 response의 복사본을 유지한다.

### 10 Last-Modified 

![image](https://user-images.githubusercontent.com/64796257/148150647-8d668de6-4139-48e1-aced-541cb63729ec.png)

Last-Modified 헤더 필드는 리소스가 마지막으로 갱신된 날짜 정보를 전달한다. 

## 6.7 Cookie와 관련된 헤더 필드

쿠키는 유저 식별과 상태 관리에 사용되고 있는 기능이다. 

웹 사이트가 유저의 상태를 관리하기 위해서 웹 브라우저를 통해 유저의 컴퓨터에 일시적으로 데이터를 기록하고 그 유저가 웹 사이트에 다시 액세스헸을 때 이전에 발행했던 쿠키를 이용한다.

쿠키와 관련된 헤더 필드는 Set-Cookie, Cookie가 있다. 

| 헤더 필드 명 | 설명 | 헤더 종류 | 
| --- | --- | --- |
| Set-Cookie | 상태 관리 개시를 위한 쿠키 정보 | response | 
| Cookie | 서버에서 수신한 쿠키 정보 | request | 

![image](https://user-images.githubusercontent.com/64796257/148151051-56f6ba21-db31-4e37-8f60-3fa0c85f08d2.png)

각각에 대해서 살펴보자.

### 1 Set-Cookie
```
ex) Set-Cookie: status-enable; expires-Tue, 05 Jul 2011 07:26:31 GMT; =>path=/;domain=.hack.jp; 
```

서버가 클라이언트에 대해서 상태 관리를 시작할 때 다양한 정보를 전달한다. 필드 값은 다음과 같은 정보가 기록된다.

- Set-Cookie 필드 속성 

| 속성 | 설명 | 
| --- | --- |
| NAME=VALUE | 쿠키에 부여된 이름과 값 | 
| Expires=DATE | 쿠키 유효 기간(지정하지 않았다면 브라우저를 닫을때까지 쿠키가 유효하다) | 
| Path=PATH | 쿠키의 적용 대상이 되는 서버상의 디렉토리(지정하지 않았다면 document와 같은 디렉토리이다) | 
| Domain=도메인 이름 | 쿠키의 적용 대상이 되는 도메인 이름(지정하지 않았다면 쿠키를 생성한 서버의 도메인) | 
| Secure | HTTPS로 통신하고 있는 경우에만 쿠키글 송신 | 
| HttpOnly | 쿠키를 JavaScript에서 access 하지 못하도록 제한 | 

1) Expires 속성 : 브라우저가 쿠키를 송출할 수 있는 유효 기간을 지정한다.

이 속성값을 생략한 경우에는 브라우저가 닫힐 때 까지 쿠키가 유효하게 된다. 유효기간이 지났다면 쿠키를 덮어써서 클라이언트 측의 쿠키를 삭제한다.

2) Path 속성 : 쿠키를 송출하는 범위를 특정 디렉토리로 한정시킨다. 

3) Domain 속성 

ex. example.com 으로 지정했다면 `example.com` 이외에 `www.example.com`, `www2.example.com` 등에서도 쿠키가 송출된다. 

그래서 의도적으로 여러 도메인에 쿠키를 송출하지 않는 이상 domain 속성은 잘 지정하지 않는다.

4) Secure 속성 : 웹 페이지가 HTTPS에서 열렸을 때만 쿠키 송출을 할 수 있도록 제한한다.

이와 같이 secure 속성을 사용한다. ex. Set-Cookie: name=value; secure

위와 같이 설정하면 `https://www.example.com/`과 같이 HTTPS일 때만 쿠키를 보낼 수 있다.

5) HttpOnly 속성 : 자바스크립트를 경유해서 쿠키를 획득하지 못하도록 하는 속성 

이는 XSS로 부터 쿠키를 훔치지 못하도록 하는 것을 목적으로 한다.

이와 같이 HttpOnly 속성을 사용한다. ex. Set-Cookie: name=value; HttpOnly

### 2 Cookie 

Cookie 헤더 필드는 클라이언트가 HTTP의 상태 관리 지원을 원할 때 서버로 수신한 쿠키를 앞으로 보낼 request에 포함해서 전달한다.

## 6.8 그 이외 

### 1 X-frame-Option

ex. X-frame-Option : DENY 

X-frame-Option 헤더 필드는 다른 웹 사이트의 프레임에서 표시되는 것을 제어하는 HTTP response 헤더이다.  
클릭 재킹(click jacking) 이라는 공격을 막는데 목적을 두고 있다.

지정할 수 있는 값은 다음과 같다. 

- DENY : r거부
- SAMEORIGIN : top-level-browsing-context가 일치하는 경우에만 프레임 표시를 허가한다.

ex. `http://hackr.jp/sample.html`이 SAMEORIGIN을 지정하고 있다면 `hackr.jp` 상의 페이지를 프레임에 읽어 들이는 것은 가능하다.

하지만, `example.com`과 같은 도메인의 페이지에서는 읽어들일 수 없다.

### 2 X-XSS-Protection

X-XSS-Protection 헤더 필드는 브라우저의 XSS 보호 기능을 제어하는 HTTP response 헤더이다. 지정할 수 있는 값은 아래와 같다.

- 0 : XSS 필터 무효
- 1 : XSS 필터 유효

### 3 DNT

![image](https://user-images.githubusercontent.com/64796257/148152803-084a76c5-8aea-4c55-a95e-5a5cf7cf1b85.png)

DNT(Do Not Track) 헤더 필드는 개인 정보 수집을 거부하는 의사를 나타내는 HTTP request 헤더 이다.  
타깃 광고 등에 이용되는 tracking의 거부 의사를 나타내느 방법 중 하나다.

ex. DNT: 1

- 0 : 트래킹 동의
- 1 : 트래킹 거부

### 4 P3P

P3P 헤더 필드는 웹 사이트 상의 프라이버시 정책에 P3P(The Platform for Privacy Preferences)를 사용하는 것으로  
프로그램이 읽을 수 잇는 형태로 나타내기 위한 HTTP response 헤더이다.

P3P 설정은 다음과 같은 순서로 진행된다.

1) P3P 정책 작성
2) P3P 정책 참조 파일을 작성해서 `/w3c/p3p.xml`에 배치
3) P3P 정책으로부터 compact 정책을 작성하고 HTTP response 헤더에 출력

cf) HTTP의 비표준 파라미터는 `X-` 를 붙이도록 했는데 이 방식은 폐지되었다. 다만, 이미 구현된 `X-` 파라미터는 변경되지 않는다.



























