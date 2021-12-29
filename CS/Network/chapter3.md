### 3.1 HTTP 메시지 

HTTP에서 교환하는 정보는 HTTP 메시지라고 하는데 

request 쪽에서 보내면 request message, response 쪽에서 보내면 response message라고 한다. 

HTTP 메시지는 크게 `메시지 헤더`와 `메시지 바디`로 구분된다. 두 부분은 개행문자(\n)로 구분된다. 

![image](https://user-images.githubusercontent.com/64796257/147628862-43a7fa0e-3ab4-493e-989b-12cb568b830c.png)

### 3.2 request message와 response message의 구조

![image](https://user-images.githubusercontent.com/64796257/147628909-ce1e6793-941e-48c8-a594-82f3115b72e8.png)

큰 요소들을 정리하면 다음과 같다.

| 요소 | 설명 | 
| --- | --- |
| 리퀘스트 라인 | 리퀘스트에 사용하는 메소드, 리퀘스트 URI, 사용하는 HTTP 버전이 포함됨 | 
| 상태 라인 | 리스폰스 결과를 나타내는 상태코드와 설명, 사용하는 HTTP 버전이 포함됨 | 
| 헤더 필드 | 리퀘스트와 리스폰스의 여러 조건과 속성 등을 나타내는 각종 헤더 필드 | 
| 그 외 | HTTP의 RFC에는 없는 헤더 필드(쿠기 등)가 포함되는 경우가 있다 | 

### 3.3 전송 효율을 높이는 인코딩

HTTP로 데이터를 전송할 때 인코딩을 실시함으로써 전송 효율을 높일 수 있다.

전송할 때 인코딩을 하면 다량의 액세스를 효율적으로 처리할 수 있지만 컴퓨터에서 인코딩을 처리해야하기 때문에 CPU 등의 리소스를 더 많이 소비한다.

#### 1) 메시지 바디와 엔티티 바디

- 메시지(message) : HTTP 통신의 기본 단위로 옥텟 시퀀스(Octet sequence, octet = 8bits) 로 구성되고 통신을 통해 전송된다.

- 엔티티(entity) : request, response의 payload(부가물)로 전송되는 정보로 엔티티 헤더 필드와 엔티티 바디로 구성된다.

HTTP 메시지 바디의 역할은 request와 response에 관한 엔티티 바디를 운반하는 일이다. 

기본적으로 메시지 바디와 엔티티 바디는 같지만 전송 코딩이 적용된다면 엔티티 바디의 내용이 변하는 과정에서 메시지 바디와 달라진다.

#### 2) 콘텐츠 코딩 









