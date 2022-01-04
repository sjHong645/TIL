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
| max-state(=[초]) | 생략 가능 | 기한이 지난 response 수신 |
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
| s-message = [초] | 필수 | 공유 캐시 서버의 response 최대 Age 값 |
| cache-extension | - | 새로운 디렉티브를 위한 공간 |

각각에 대해서 살펴보겠다. 

##### 캐시가 가능한지 여부를 나타내는 디렉티브 

- public 디렉티브 ⇒ Cache-Control: public  
⇒ public 디렉티브를 사용하면 response는 어떤 캐시에 의해서든 캐시된다는 것을 의미한다.

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

no-store 디렉티브를 사용했다면 캐시를 하지 못하게 했다는 것을 의미한다.

##### 캐시 기한이나 검증을 지정하는 디렉티브 

- s-maxage 디렉티브 ⇒ Cache-Control: s-maxage=604800(초 단위) 

s-maxage 디렉티브의 기능은 max-age 디렉티브의 기능과 동일하지만 차이점이 있다. 여러 유저가 사용할 수 있는 공유 캐시 서버에만 적용된다는 점이다.  
그리고 s-maxage 디렉티브를 사용한다면 Expires 헤더 필드와 max-age 디렉티브는 무시된다.

- max-age 디렉티브 ⇒ Cache-Control: max-age=604800(초 단위) 

![image](https://user-images.githubusercontent.com/64796257/148014767-d62a6313-59c2-413d-ab55-af71c9102807.png)

client의 request로 max-age 값을 지정했다면 캐시 서버에 요청한 데이터가 지정한 시간보다 오래되었다면 해당 데이터를 받아들일 수 있도록 한다.

server의 respone에서 max-age 값을 지정했다면 캐시 서버는 지정한 시간을 초과하지 않을 때 까지 오리진 서버에 유효성을 재확인하지 않고 캐시할 수 있다.

- min-fresh 디렉티브 ⇒ Cache-Control: min-fresh=60(초 단위)

![image](https://user-images.githubusercontent.com/64796257/148015427-9ff181b5-3197-4e48-81aa-a8118679c42a.png)

min-fresh 디렉티브를 사용하면 캐시된 리소스가 지정된 시간동안 변경되지 않는 응답을 원한다는 것을 의미한다.

ex. Cache-Control: min-fresh=60(초 단위) = 컨텐츠가 지금을 기준으로 60초 동안 변경되지 않는 응답을 원한다는 것을 의미한다.

- max-state 디렉티브

- only-if-cached 디렉티브

- must-revalidate 디렉티브

- proxy-revalidate 디렉티브

- no-transform 디렉티브 













