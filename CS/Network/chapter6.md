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



























