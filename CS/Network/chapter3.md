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

#### 2) 콘텐츠 코딩 (Content Codings)

콘텐츠 코딩은 엔티티에 적용하는 인코딩을 가리키는데 엔티티 정보를 유지한 채로 압축한다. 

그렇게 코딩된 엔티티는 수신한 클라이언트 측에서 디코딩된다. 

![image](https://user-images.githubusercontent.com/64796257/147630467-a6dc1dc8-5412-467e-b7e6-2221fc20eb09.png)

#### 3) 청크 전송 코딩 (Chunked Transfer Coding)

사이즈가 큰 데이터를 전송하는 경우 데이터를 분할해서 조금씩 표시할 수 있다. 이렇게 엔티티 바디를 분할하는 기능을 청크 전송 코딩이라 한다. 

![image](https://user-images.githubusercontent.com/64796257/147630833-7878139a-5656-48e0-9ec2-612c3dc53589.png)

청크 전송 코딩은 엔티티 바디를 청크(덩어리)로 분해한다. 다음 청크 사이즈를 16진수를 사용해서 단락을 표시하고 엔티티 바디의 끝에는 "0(CR+LF)"를 기록한다.

청크 전송 코딩된 엔티티 바디는 수신한 클라이언트 측에서 원래의 엔티티 바디로 디코딩된다. 

### 3.4 여러 데이터를 보내는 멀티파트

![image](https://user-images.githubusercontent.com/64796257/147631138-99b5eda8-24fa-4141-b25f-5c8e2c3c5a57.png)

메일의 경우 `메일의 본문`과 `복수의 첨부 파일`을 붙여서 함께 보낼 수 있다. 

이를 위해 MIME(Multipurpose Internet Mail Extensions)라 불리는 텍스트나 영상, 이미지와 같은 여러 데이터를 다루는 기능을 사용하고 있다.

MIME는 이미지 등의 바이너리 데이터를 아스키(ASCII) 문자열에 인코딩하는 방법과 데이터 종류를 나타내는 방법 등을 규정하고 있다. 

MIME의 확장 사양에 있는 멀티파트(Multipart) 라고 하는 여러 다른 종류의 데이터를 수용하는 방법을 사용하고 있다.

HTTP도 멀티파트에 대응하고 있어 하나의 메시지 바디에 엔티티를 여러 개 포함시킬 수 있다. 주로 이미지나 텍스트 파일 등을 업로드할 때 사용한다.

멀티파트에는 다음과 같은 것들이 있다.

- multipart/form-data : Web 폼으로부터 파일 업로드에 사용된다.

ex. 

![image](https://user-images.githubusercontent.com/64796257/147631403-a5481a62-7f4a-4332-8710-c68fff7dc58b.png)

- multipart/byteranges : 상태 코드 206(Partial Content) 리스폰스 메시지가 복수 범위의 내용을 포함할 때 사용한다. 

ex. 

![image](https://user-images.githubusercontent.com/64796257/147631418-d4079de9-9b3a-436e-a533-69a73a7bf87f.png)
![image](https://user-images.githubusercontent.com/64796257/147631453-abef79c1-5da6-4159-9419-955e923c3c7f.png)

HTTP 메시지로 멀티파트를 사용할 때는 Content-type 헤더 필드를 사용해야 한다. 

멀티파트 각각의 엔티티를 구분하기 위해서는 "boundary" 문자열을 사용해야 한다. 

예를 들어 boundary=AaB03x 라고 한다면 

엔터티의 선두에는 `--AaB03x` 라고 입력해야 한다. 멀티파트의 마지막 부분에는 `--AaB03x--`라고 입력하면서 마무리해야 한다. 

멀티파트는 파트마다 헤더 필드가 포함된다. 또한 파트 중간에 멀티파트를 만드는 것과 같이 파트를 내부에 포함할 수도 있다. 

### 3.5 레인지 리퀘스트(Range Request) 

광대역 네트워크를 이용하기 전에는 대용량 이미지와 데이터를 다운로드하기가 힘들었다. 

왜냐하면 다운로드 중에 커넥션이 끊어지면 처음부터 다시 다운로드를 해야했기 때문이다. 

이러한 문제를 해결하기 위해서 일반적인 resume 이라는 기능을 통해 이전에 다운로드를 한 곳에서 다시 다운로드를 재개하고자 했다.

이 기능을 실현하기 위해서는 엔터티의 범위를 지정해서 다운로드할 필요성이 있었다.  
이때, 범위를 지정해서 request를 하는 것이 Range request이다.

ex. 전체 10,000 바이트에서 5001~10000 바이트의 범위 만을 request한 상황

![image](https://user-images.githubusercontent.com/64796257/147631873-a03265ec-4189-4fe5-b88a-0f282bbe9a80.png)

Range request를 할 때 Range 헤더 필드를 사용해서 리소스의 byte 범위를 지정한다. 다음과 같은 형식으로 지정할 수 있다.

- 5001 ~ 10000 바이트 ⇒ Range : bytes = 5001-10000
- 5001 바이트 이상 ⇒ Range : bytes = 5001-
- 처음부터 3000 바이트까지, 그리고 5000 ~ 7000 바이트까지의 범위 ⇒ Range : bytes = -3000, 5000-7000

Range request에 대한 response는 상태 코드 206 Partial Content 라는 response 메시지로 되돌아온다. 

복수 범위의 Range request에 대한 response는 multipart/byteranges로 response가 돌아온다.

서버가 range request를 지원하지 않는다면 상태 코드 200 OK라는 response message로 완전한 엔터티가 돌아온다.

### 3.6 콘텐츠 네고시에이션(Content Negotiation) 

같은 콘텐츠여도 여러 개의 페이지를 가진 웹 페이지가 있다.

ex. 내용은 같이잠 영어판과 한국어판이 같이 표시되는 언어가 서로 다른 웹 페이지 

이러한 웹 페이지는 서로 다른 언어를 주로 사용하는 브라우저가 같은 URI에 액세스할 때에 각각 영어판 웹 페이지와 한국어판 웹 페이지를 표시한다. 

이러한 구조를 Content Negotiation이라 한다. 

![image](https://user-images.githubusercontent.com/64796257/147632612-1b624999-1cbe-46fe-a4bb-27e6c1e84c3b.png)

Content Negotiation : client와 server가 제공하는 리소스의 내용에 대해 교섭하는데 이는 client에 더욱 적합한 리소스를 제공하기 위한 구조다.

Content Negotiation은 제공하는 리소스를 언어와 문자 세트, 인코딩 방식을 기준으로 판단한다. 

판단 기준은 아래와 같은 request 헤더 필드이다.
- Accept 
- Accept-Charset
- Accept-Encoding
- Accept-Language
- Content-Language

Content Negotiation에는 다음과 같은 종류가 있다.

1) Server-driven Negotitation : 서버 쪽에서 content Negotitation을 하는 방식. 서버 쪽에서 request 헤더 필드의 정보를 참고해 자동으로 처리한다. (브라우저에서 보내는 정보를 근거로 하기 때문에 사용자에게 적합한 것인지는 알 수 없다)

2) Agent-driven Negotitation : 클라이언트 측에서 content Negotitation을 하는 방식. 사용자가 원하는 방식을 수동으로 선택한다. 

3) Tranparent Negotitation : 서버와 클라이언트가 각각 content Negotitation을 하는 방식 








