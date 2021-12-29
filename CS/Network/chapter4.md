### 4.1 서버로부터 request 결과를 전달하는 상태코드

클라이언트가 서버에 request를 보낼 때 서버에서 그 결과가 어떻게 처리되었는지 알려주는 것이 상태 코드의 역할이다.

상태 코드를 통해 서버가 request를 정상적으로 처리했는지 에러가 발생했는지를 알 수 있다.

![image](https://user-images.githubusercontent.com/64796257/147633348-550a3cb2-f824-4e08-8dbe-566dcfb9fed5.png)

- 상태 코드 클래스

|  | 클래스 | 설명 |
| --- | --- | --- | 
| 1xx | informational | request를 받아들여 처리중 | 
| 2xx | Success | request를 정상적으로 처리 | 
| 3xx | Redirection | request를 완료하기 위한 추가 동작 필요 | 
| 4xx | Client Error | server에서 request를 이해할 수 없음 | 
| 5xx | Server Error | server에서 request 처리 실패 |

클래스의 정의만 지킨다면 RFC2616에서 정의된 상태 코드를 변경하거나 서버만의 상태 코드를 만들어도 상관없다.

### 4.2 2xx 성공(Success) : 2xx response는 request가 정상적으로 처리되었음을 의미

1) 200 OK

![image](https://user-images.githubusercontent.com/64796257/147633955-637eea48-f138-4b37-8d96-8f9201adc490.png)

response에서 상태 코드와 함께 되돌아 오는 정보는 메소드에 따라 다르다. 

GET 메소드이면 request된 리소스에 대응하는 엔티티가 response로 보내지고 

HEAD 메소드이면 request된 리소스에 대응하는 엔티티 헤더 필드가 메시지 바디를 동반하지 않고 response로 되돌아온다. 

2) 204 No Content 

![image](https://user-images.githubusercontent.com/64796257/147634100-d6ef0a18-3501-4de9-9b24-70c62cb73ebf.png)

서버가 request를 받아서 처리하는데는 성공했지만 response에 엔터티 바디를 포함하지 않는다. 

이는 클라이언트가 서버에 정보를 보내는 것에 목적을 두면서 client에게 새로운 정보를 보낼 필요가 없을 때 사용된다. 

3) 206 Partial Content 

![image](https://user-images.githubusercontent.com/64796257/147634823-fee7840d-ac68-466a-a342-cb4695bf52a3.png)

Range에 의해서 범위가 지정된 request에 의해서 서버가 부분적 GET request를 받았음을 나타낸다. 

### 4.3 3xx Redirection 

3xx response는 request가 정상적으로 처리를 종료하기 위해 브라우저 측에서 특별한 처리를 수행해야 함을 나타낸다.

#### 1) 301 Moved Permanently

![image](https://user-images.githubusercontent.com/64796257/147634940-1e71c24e-458f-47c6-8e9f-d9c3c010798e.png)

request된 리소스에는 새로운 URI가 부여되어 있기 때문에 이후에는 새로 추가하고 싶은 리소스를 참조하는 URI를 사용해야 한다는 것을 나타낸다. 

북마크를 한 상황에서는 Location 헤더 필드에서 가리키고 있는 URI에 다시 북마크를 하는 것이 좋다. 

#### 2) 302 Found

![image](https://user-images.githubusercontent.com/64796257/147635124-dbd64346-1e52-415e-9c94-ef5afb92ffbc.png)

request된 리소스에는 새로운 URI가 할당되어 있기 때문에 그 URI를 참조해 주길 바란다는 의미를 나타낸다.

여기서 302는 301과 달리 일시적으로 이동했다는 것을 의미한다.

#### 3) 303 See Other

![image](https://user-images.githubusercontent.com/64796257/147635275-27e8f679-5fdd-4511-9a57-e6ee1343064d.png)

request에 대한 리소스는 다른 URI에 있기 때문에 GET 메소드를 사용해서 얻어야 한다는 것을 나타낸다. 

302 Found와 같은 기능이지만 redirect 장소를 GET 메소드로 얻어야 한다고 명시되어 있다는 점에서 302와 다르다.

ex. POST 메소드로 액세스한 CGI 프로그램을 실행했다.

그렇게 실행하고 나서 처리 결과를 별도의 URI에 GET 메소드로 redirect 시키고 싶은 경우에 303이 사용된다.


#### 4) 304 Not Modified 

![image](https://user-images.githubusercontent.com/64796257/147635484-14b716b8-c8bc-45cb-a61b-2ec12e7e0127.png)

클라이언트가 조건부 request를 했을 때 리소스에 대한 엑세스는 허락하지만 조건이 충족되지 않았음을 표시한다. 

304를 되돌려 줄 경우에는 response body에 어떤 것도 포함되어 있으면 안된다. 참고로 304는 리다이렉트와는 관련이 없다.

#### 5) 307 Temporary Redirect 

302 Found와 같은 의미를 지닌다. 하지만, 302는 POST로 부터 GET으로 치환을 금지하는 것이 원칙이지만 구현상으로는 POST에서 GET으로 치환하는 것이 가능하다. 

307에서는 브라우저의 사양에 따라 POST에서 GET으로 치환하지 않는다. 

### 4.4 4xx Client Error

4xx response는 client 때문에 에러가 발생했음을 나타낸다.

#### 1) 400 Bad Request 

![image](https://user-images.githubusercontent.com/64796257/147635774-6eac7f10-e194-4543-83e5-ed33d3c1948b.png)

request 구문이 잘못되었음을 나타낸다. 

이 에러가 발생하면 request 내용을 재검토하고 나서 재송신해야 한다. 브라우저는 이를 200 OK와 같이 취급한다.

#### 2) 401 Unauthorized 

![image](https://user-images.githubusercontent.com/64796257/147635866-c6ffaa8e-9937-406c-b6d7-46f9051ce7a6.png)

송신한 request에 HTTP 인증 정보(BASIC 인증, DIGEST 인증)가 필요하다는 것을 의미한다. 

이미 처음의 request가 이뤄졌다면 유저 인증에 실패했음을 표시한다.

401을 포함한 response를 return 하는 경우 request된 리소스에 적용되는 challenge를 포함한 WWW-Authenticate 헤더 필드를 포함할 필요가 있다.

브라우저에서 처음 401 response를 받았다면 인증을 위한 dialog가 표시된다.

#### 3) 403 Forbidden

![image](https://user-images.githubusercontent.com/64796257/147636058-9fec63a7-88c5-4d01-8032-9477f5f48f18.png)

request된 리소스의 액세스가 거부되었음을 나타낸다. 

거부의 이유를 명확하게 하기 위해서 그 이유를 엔티티 바디에 작성하고 유저에게 표시한다.

대표적으로 403이 발생하는 상황은 파일 시스템의 permission이 부여되지 않은 경우와 액세스 권한에 문제가 있는 경우이다.

#### 4) 404 Not Found 

![image](https://user-images.githubusercontent.com/64796257/147636183-a201620e-6b35-4fb4-935f-6fc4baf6c8f7.png)

request한 리소스가 서버에 없다는 것을 나타낸다. 

그 외에 서버 측에서 해당 request를 거부하고 싶은 이유를 분명히 하고 싶지 않은 경우에도 사용할 수 있다.

### 4.5 5xx Server Error 

5xx response는 서버 때문에 에러가 발생했음을 나타낸다.

#### 1) 500 Internal Server Error 

![image](https://user-images.githubusercontent.com/64796257/147636299-2e25372d-ca17-4ba4-a637-e552f0348cfd.png)

서버에서 request를 처리하는 도중에 에러가 발생했음을 나타낸다. 

웹 애플리케이션에 에러가 발생한 경우 또는 일시적으로 발생할 수 있다.

#### 2) 503 Service Unavailable 

![image](https://user-images.githubusercontent.com/64796257/147636613-87da8add-bbfa-47e6-a936-bc258365c37f.png)

일시적으로 서버가 과부하되거나 점검 중이어서 현재 request를 처리할 수 없음을 나타낸다. 

이 상태가 해소되기까지 시간이 걸린다면 Retry-After 헤더 필드에 따라 client에 전달하는 것이 바람직하다.
