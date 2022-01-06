## HTTP 

http는 client와 Web-server 사이에서 웹 데이터를 주고받기 위한 프로토콜이다.  
이때, 웹 데이터의 형식이 WWW 즉, html 파일이다. 지금부터는 이에 대한 내용을 다루려고 한다.

HTTP 프로토콜은 well-known 포트 80을 사용한다.  
물론 설정을 바꾸면 포트번호를 바꿀 수 있지만 상대방도 똑같이 바꿔줘야 하기 때문에 공식적인 웹 서버들은 전부 80번 포트를 이용한다.

- HTTP transaction 

![image](https://user-images.githubusercontent.com/64796257/148357412-8d786645-71f5-465b-b087-c558b906e537.png)

HTTP tranaction란 client와 server가 서로 주고받는 `request`와 `response`. 이러한 2가지 operation을 지칭한다.

- Request message  
HTTP request message, HTTP response message 둘 다 애플리케이션 프로토콜이기 때문에 아스키 코드로 되어 있습니다. 
즉 우리가 눈으로 보고 읽을 수 있는 영어로 되어있다.

![image](https://user-images.githubusercontent.com/64796257/148357963-b5c2cb4f-2dc1-429d-af9f-ddce5e71759f.png)

http request 형식은 위와 같다. 먼저 Request line이 있고 그 다음 줄에 header가 나온다. 
그리고 한 줄 띄우고 또 한 줄 띄운 다음에 body가 나온다. 
웹 프로그래밍을 할 때 이 구조를 잘 알고 있어야 한다.
 
정리하면, Request line, 한 줄 띄고 header, 한 줄 띄고 한 줄 또 띄면 body.

여기서 Request line의 구조는 다음과 같다.  
![image](https://user-images.githubusercontent.com/64796257/148358077-efe9bacf-9c9a-43af-ab25-7c9209c3a486.png)

Request type(method), URL, HTTP version이 있는데 

Request type은 GET, POST와 같은 것을 의미한다. HTTP version은 HTTP/1.1이라고 하면 1.1 버전의 HTTP를 의미한다.

method에 대한 내용은 좀 더 뒤에서 알아보고 먼저 URL에 대해서 살펴보겠다.

![image](https://user-images.githubusercontent.com/64796257/148358679-d41d67f2-4971-4a70-9cef-95ed5d77201d.png)

Method(메소드)는 어떤 방법으로 접근할지 나타낸 것이다. ex. http, https, ftp  
그 다음 ://가 나오고 Host(ex. www.example.com)가 나온다. 그 다음에 :가 나온다.  
이제 port가 나오는데 여기선 2030번 포트를 쓴다고 했지만 기본적으로는 80을 쓴다.  
80을 쓰고 싶다면 생략하거나 직접 80을 쓰면 된다. 그 다음 path(ex. test/hello.html)가 나온다.   

이런 형태로 URL이 구성된다.

예제 GET http://google.com/index.html HTTP/1.1를 살펴보자.
GET은 `Request type`이고 http://google.com/index.html은 `URL부분`이고 HTTP/1.1이 `HTTP version`이 된다.  

URL 부분에서 http 메소드로 접근하며 호스트는 google.com/ 이고 포트번호는 생략되었으니까 80번, path는 index.html라는 것을 알 수 있다.  
이 부분은 웹 프로그래밍을 하다보면 꼭 알아야 되는 부분이기 때문에 잘 알아야 한다.

- Response Message  

![image](https://user-images.githubusercontent.com/64796257/148359230-0dfbd8c5-a826-4dbe-836c-3324271000df.png)

처음에 Status line이 나온다. 그리고 header가 온다. 그러고 한 줄 띄고 또 한 줄 띄면 body 부분이 된다.  
(request message처럼 두 번 줄바꿈하면 body가 온다)

status Line의 구조
![image](https://user-images.githubusercontent.com/64796257/148359333-0d51a5b3-0f03-4394-af30-f2dff3225a19.png)

Header의 구조 

![image](https://user-images.githubusercontent.com/64796257/148359366-7f340211-7c5d-44bf-89fc-a6ab214053ae.png)

![image](https://user-images.githubusercontent.com/64796257/148359378-94c794e3-7fcc-440b-b638-5227c3b0cc41.png)

헤더의 포맷은 왼쪽 슬라이드를 보면 된다. (ex. Content-Length: 168)  
오른쪽 슬라이드는 header가 general header, request header, entity header로 나뉜다는 걸 표현한 것이다.























