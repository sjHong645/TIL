### 2.1 HTTP는 클라이언트(client)와 서버(server) 간에 통신한다. 

TCP/IP에 있는 많은 프로토콜과 마찬가지로 HTTP 역시 클라이언트와 서버 사이에서 통신한다. 

클라이언트는 텍스트와 이미지와 같은 리소스가 필요하다고 요구하는 쪽이고 서버는 이러한 리소스를 제공하는 쪽이다. 

![image](https://user-images.githubusercontent.com/64796257/147529630-b8b8c24a-0d53-4939-a26b-2baa3f53ae55.png)

HTTP를 사용해서 2대의 컴퓨터로 통신을 한다면 한번 통신을 할 때 마다 반드시 한 쪽은 클라이언트가 되고 다른 한 쪽은 서버가 된다.

HTTP는 클라이언트와 서버의 역할을 명확하게 구별한다.

### 2.2 Request와 Response 교환

HTTP는 client로부터 request가 송신되고 그 결과를 server로 부터 response로 되돌아온다. 

즉, client 쪽에서 통신이 시작되고 server 쪽에서 request를 받고 나서야 response를 송신한다. server 쪽은 request를 받지 않는다면 response가 발생하지 않는다. 

ex. 

![image](https://user-images.githubusercontent.com/64796257/147529862-5c5208f2-f5e5-4c0c-902f-19493d2cfe37.png)

- client 에서 HTTP server로 송신한 request 내용
``` 
GET /index.html HTTP /1.1
Host : www.naver.com
```

| 요소  | 내용 |
| ------------- | ------------- | 
| GET  | 서버에 요구하는 종류 ⇒ `메소드`라고 한다.  |
| /index.html  | 요구 대상이 되는 리소스 ⇒ `리스트 URI`라고 한다.  |
| HTTP /1.1  | 클라이언트의 기능을 식별하기 위한 `HTTP 버전 번호`  |

⇒ request의 내용을 정리하면 HTTP 서버 상에 있는 /index.html 라는 소스가 필요하다는 request이다. 

이와 같이 request 메시지는 메소드, URI, 프로토콜 버전, 옵션 리퀘스트 헤더 필드와 엔터티로 구성되어 있다.

![image](https://user-images.githubusercontent.com/64796257/147530391-4f162142-f80f-44be-859f-cdd7937e73c9.png)

request를 받은 server는 request 내용을 처리한 결과를 response로 client에 돌려준다. 

![image](https://user-images.githubusercontent.com/64796257/147530470-f19478c1-6c45-4991-9294-be157db64b63.png)

| 요소  | 내용 |
| ------------- | ------------- | 
| HTTP /1.1  | 서버의 HTTP 버전을 나타냄  |
| 200 OK  | request의 처리 결과를 나타내는 상태 코드와 설명 |
| Data: ~  | response가 발생한 시간을 나타낸다. `헤더 필드`라고 불리는 것 중 하나. |
| 빈 줄  | head와 body를 구분하는 경계  |

빈 줄 아래의 body 부분은 리소스의 본체가 된다.

이와 같이 response 메시지는 프로토콜 버전, 상태코드와 상태 코드에 대한 설명, 옵션의 response 헤더 필드와 바디 로 구성되어 있다. 

### 2.3 상태를 유지하지 않는 HTTP 

HTTP는 상태를 계속 유지하지 않는 stateless 프로토콜이다. 
HTTP 프로토콜 레벨에서는 이전에 보냈던 request나 이미 돌려준 response에 대해서는 전혀 기억하고 있지 않는다. 

![image](https://user-images.githubusercontent.com/64796257/147530797-86a2bf25-289e-4d7e-89c8-559955a3e853.png)

이는 많은 데이터를 매우 빠르고 확실하게 처리하는 scalability(확장성)을 확보하기 위해서 간단하게 설계되어 있는 거다. 

하지만, 웹이 진화함에 따라 stateless 특성만으로 처리하기 어려운 일이 증가하게 되었다. 
예를 들면, 사이트에 로그인했을 때 다른 페이지로 이동하더라도 로그인 상태를 계속 유지할 수 있어야 한다. 

이를 위해서는 누가 어떤 request를 보냈는지 파악하기 위해서 상태를 유지해야 할 필요가 있다. 이에 고안된 방법이 바로 쿠키(Cookie) 이다.

### 2.4 Request URI로 리소스를 식별

HTTP는 URI를 가지고 인터넷 상에 리소스를 지정하고 인터넷 상의 특정 장소에 있는 리소스도 호출할 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/147531126-c22b8ad6-6680-4755-b1d1-cc7c033ff1d0.png)

request URI를 지정하는 방법은 여러 가지가 있다. 

![image](https://user-images.githubusercontent.com/64796257/147531317-49eeb4e2-57a2-482a-bbe5-48dba0b30f1b.png)

### 2.5 server에 임무를 부여받는 HTTP 메소드 ⇒ HTTP /1.1에서 사용할 수 있는 메소드에 대해 알아보자.

- GET : 리소스 획득 

⇒ GET 메소드는 request URI로 식별된 리소스를 가져올 수 있도록 한다. 가져올 리소스 내용은 지정된 리소스를 서버가 해석한 결과다.

![image](https://user-images.githubusercontent.com/64796257/147531461-a7eb81d9-61a3-4ce6-8927-a7d51b03e17a.png)

![image](https://user-images.githubusercontent.com/64796257/147531477-c9cb93df-78ce-4965-9119-f3b4904c173c.png)

- POST : 엔티티 전송 

⇒ response에 의한 엔티티를 획득하는 것 뿐만 아니라 엔티티를 전송하기 위해서 사용한다. 

![image](https://user-images.githubusercontent.com/64796257/147531576-614fd06d-9f49-45fb-8ed1-db3a37c3c0ee.png)

![image](https://user-images.githubusercontent.com/64796257/147531587-df02e974-58e3-4caa-b99d-83ebdc94c0af.png)

- PUT : 파일 전송

⇒ request에 포함된 엔티티를 request URI가 지정한 곳에 보존하도록 한다. 

다만, HTTP /1.1 PUT에 자체적인 인증 기능이 없어서 보안 상의 문제 때문에 일반적인 웹 사이트에서는 사용하지 않는다. 

그래서 웹 애플리케이션 등에 의한 인증 기능과 짝을 이루거나 REST와 같이 웹 끼리 연계하는 설계 양식을 사용할 때 PUT 메소드를 이용한다. 

![image](https://user-images.githubusercontent.com/64796257/147532001-5fed43da-582a-404a-aca1-f1240ffcea45.png)

cf) 204 No Content ==> 클라이언트의 요청은 정상적이다. 하지만 컨텐츠를 제공하지 않는다. 라는 의미가 있음

- HEAD : 메시지 헤더 취득 

⇒ GET과 같은 기능을 하지만 메시지 바디는 돌려주지 않는다. 보통 URI 유효성과 리소스 갱신 시간을 확인하는 목적으로 사용된다. 

![image](https://user-images.githubusercontent.com/64796257/147532153-57fdfe49-aa32-4731-8c44-b8d671fccc5f.png)

![image](https://user-images.githubusercontent.com/64796257/147532167-0a4ae966-411d-4bbf-be2d-fddf9ec54693.png)

- DELETE : 파일 삭제 

⇒ PUT과 반대로 동작하며 request URI로 지정된 리소스를 삭제하도록 한다. 

다만, HTTP /1.1의 DELETE에 자체적인 인증 기능이 없어서 보안 상의 문제 때문에 일반적인 웹 사이트에서는 사용하지 않는다. 

그래서 웹 애플리케이션 등에 의한 인증 기능과 짝을 이루거나 REST와 같이 웹 끼리 연계하는 설계 양식을 사용할 때 DELETE 메소드를 이용한다. 

![image](https://user-images.githubusercontent.com/64796257/147532615-feea63c7-db50-4dda-9de7-e5b96d267d5e.png)

- OPTIONS : 제공하고 있는 메소드 문의 

⇒ request URI로 지정한 리소스가 제공하고 있는 메소드를 조사하기 위해서 사용한다. 

![image](https://user-images.githubusercontent.com/64796257/147533230-be7e67f9-4b76-4996-970e-6be03f681a22.png)






