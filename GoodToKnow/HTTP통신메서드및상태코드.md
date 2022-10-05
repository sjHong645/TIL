[출처1](./CS/Network/chapter2.md)

[출처2](./CS/Network/chapter4.md)

[출처3](https://medium.com/@lyhlg0201/http-method-d561b77df7)

[출처4](https://www.whatap.io/ko/blog/40/)

[출처5](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#redirection_messages)

## HTTP 메소드 
```
 `client`가 `웹 서버`에게 사용자 요청의 목적이나 종류를 알리는 수단. 
```
 
### GET

⇒ request URI로 식별된 resource를 가져올 수 있도록 한다. 가져올 리소스 내용은 지정된 리소스를 서버가 해석한 결과다.   
⇒ 서버에게 resource를 보내도록 요청할 때 사용한다. 즉, 서버의 resource를 읽어들일 때 사용한다. 

ex)   
- request
```
GET /index.html HTTP/1.1
Host: www.hackr.jp
```
- response  
request URI로 식별한 `index.html`이라는 resource를 서버에서 반환한다. 

### POST 

⇒ 서버에게 `입력 데이터`를 `전송`하기 위해 사용한다. 

![image](https://user-images.githubusercontent.com/64796257/193962405-b4a5c57e-1c42-4c33-8142-d7e7c4475814.png)

① 브라우저에서 데이터를 입력받음  
② 입력받은 데이터를 `Request Message`의 `Body 부분`에 실어보낸다.  
③ 서버에서 입력받은 데이터에 대해서 작업을 한다.  
④ 작업을 완료하고 나서의 결과를 `Response Message`로 보낸다.

### PUT

⇒ 서버에 `문서 또는 파일`을 `작성`할 때 사용한다. 

ex)  
- request
``` 
PUT /example.html HTTP/1.1
Host: ~~
Content-type : text/html
Content-Length: 1560 (1560 Bytes 데이터) 
```

- response 
```
// 서버에는 example.html이 작성되면서 아래와 같은 상태코드를 response로 보낸다.
204 No Content 
```

### HEAD 

⇒ `GET`과 같은 기능을 하지만 `메시지 body`를 return하지 않는다.

- Resource를 받지 않고 `찾기`만 원할 때 
- object가 존재할 경우 `응답의 상태 코드`를 확인할 때
- 서버의 응답 헤더를 통해서 `resource의 수정 여부`를 확인할 때 

ex)  
- request
```
HEAD /index.html HTTP/1.1
Host: ~~ 
```

- response ⇒ `index.html`과 관련된 `response header`를 반환한다. 

### DELETE 

⇒ `PUT`과 반대로 요청한 resource를 `삭제`하도록 요청  

ex)  
- request
```
DELETE /example.html HTTP/1.1 
Host: ~~ 
```

- response 
```
// 서버에는 example.html이 삭제되면서 아래와 같은 상태코드를 response로 보낸다.
204 No Content 
```

- 다만, HTTP 규격에는 client의 요청을 서버가 무효화 시킬 수 있도록 정의되어 있음
- DELETE method가 항상 보장되지는 않음

## HTTP 상태코드 

수많은 `HTTP 상태코드`중에서 많이 보이는 것들 위주로 정리하겠다. 

- 기본적인 내용  

|  | 클래스 | 설명 |
| --- | --- | --- | 
| 1xx | informational(정보 제공) | request를 받아들여 처리중 | 
| 2xx | Success | request를 정상적으로 처리 | 
| 3xx | Redirection | request를 완료하기 위한 추가 동작 필요 | 
| 4xx | Client Error(클라이언트 요청 오류) | server에서 request를 이해할 수 없음 | 
| 5xx | Server Error(서버 응답 오류) | server에서 request 처리 실패 |

### 1xx : 정보 제공 

: 서버가 요청을 받았을 때 서버와 연결된 클라이언트는 `작업을 계속 진행`하라는 의미. 

#### 100 Continue : `진행 중`임을 의미한다. 

현재까지의 진행상태에 문제가 없고 클라이언트가 계속해서 요청을 하거나 이미 요청을 완료한 경우 무시해도 된다는 걸 알려준다. 

### 2xx : 성공

#### 200 OK : 요청이 성공적으로 수행됨. 

정보는 요청에 따른 응답으로 반환됨. 

ex1) GET 메소드 ⇒ 요청된 리소스에 대응하는 엔터티가 response로 반환된다.  
ex2) HEAD 메소드 ⇒ 요청된 리소스에 대응하는 엔터티가 메시지 바디를 동반하지 않고 response로 반환된다.  

#### 204 No content : 요청 성공. response로 전송할 콘텐츠는 없음

#### 206 Partial Content 

클라이언트에서 `여러 개의 스트림`을 `분할 다운로드`하기 위해 `Range 헤더`를 전송할 때 사용된다.  
클라이언트가 `이어받기`를 시도하면 웹 서버가 이에 대한 응답 코드로 `206 Partial Content`와 함께 `Range 헤더`에 명시된 데이터 부분부터 전송한다. 

### 3xx : 리다이렉션

#### 301 Moved Permanently 

요청한 리소스의 URI가 `변경되었음`을 의미. 새로운 URI가 응답에서 주어질 수도 있음 

#### 302 Found 

요청한 리소스의 URI가 `일시 변경`되었음을 의미. 새롭게 변경된 URI는 나중에 만들어질 수 있음.  
때문에, 클라이언트는 이후의 요청도 반드시 `동일한 URI`로 해야 함

#### 303 See Other 

클라이언트가 GET 요청을 사용해서 다른 URI로 이동해야 할 때 사용한다. 

#### 304 Not Modified 

- 캐시를 목적으로 사용된다.
- 클라이언트에게 `응답`이 `수정되지 않았음`을 알려주며, 클라이언트는 계속해서 응답의 캐시된 버전을 사용할 수 있다.


### 4xx : 클라이언트 요쳥 오류

#### 400 Bad Request : request 구문이 잘못되었음을 의미함. 

#### 401 Unauthorized 

`전송한 request`에 `HTTP 인증 정보`가 `필요`하다는 걸 의미함. 

- 처음으로 401 Response를 받았다면... `인증을 위한 dialog`가 표시된다.
- 그리고 나서 401 Response를 또 받았다면 `유저 인증에 실패`했음을 의미한다.

#### 403 Forbidden : 요청한 resource 액세스 거부 

왜 거부했는지는 Entity body에 작성되어 유저에게 표시된다. 

#### 404 Not Found : 요청한 resource가 서버에 없다

뿐만 아니라 서버 측에서 해당 request를 거부하고 싶은 이유를 명확히 밝히고 싶지 않을 때도 사용한다.

### 5xx : 서버 응답 오류

#### 500 Internal Server Error 

서버에서 request를 처리하는 과정에서 `에러가 발생`했음을 나타낸다. SQL에서 문제가 발생한 경우를 의미하기도 한다.

#### 503 Service Unavailable 

서버가 아직 요청을 처리할 수 없을 때 나타난다. 

일시적으로 서버가 과부하되거나 점검 중이어서 request를 처리할 수 없음을 나타낸다.  
이 상태가 해소되기까지 시간이 걸린다면 `Retry-After` 헤더 필드를 response에 포함시킬 수 있다. 






















