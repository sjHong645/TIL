## HTTP 

http는 client와 Web-server 사이에서 웹 데이터를 주고받기 위한 프로토콜이다.  
이때, 웹 데이터의 형식이 WWW 즉, html 파일이다. 지금부터는 이에 대한 내용을 다루려고 한다.

HTTP 프로토콜은 well-known 포트 80을 사용한다.  
물론 설정을 바꾸면 포트번호를 바꿀 수 있지만 상대방도 똑같이 바꿔줘야 하기 때문에 공식적인 웹 서버들은 전부 80번 포트를 이용한다.

### HTTP transaction 

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

### Request type GET & POST 

이 부분은 반드시 알고 있어야 하는 부분이다. 서버쪽의 코딩하는 내용 역시 꼭 알아야 한다.

웹 서비스를 위한 전체적인 시스템의 구성은 아래와 같다. client가 있고 server가 있는 구조로 되어있다. 이때 server는 웹서버다.

client는 보통 웹 브라우저또는 App일 수도 있다.

![image](https://user-images.githubusercontent.com/64796257/148361284-14d832be-f322-43bb-a45c-b08804b5dead.png)

client 쪽에서 유저가 웹서버로부터 원하는 정보를 입력하면 그 정보가 http 요청 메시지(request message)로 전환돼서 웹 서버로 간다. 

그러면 웹서버가 데이터베이스에 접근해 웹페이지를 구성한다. 

그런 다음에 웹서버에서 응답 메시지(response message)를 보내는데 데이터 형태는 보통 XML, JSON 형태가 된다.

#### GET 방식 

![image](https://user-images.githubusercontent.com/64796257/148361655-9f946e25-fb86-4cdd-a189-72597886e7b6.png)

![image](https://user-images.githubusercontent.com/64796257/148361663-9cc61dcb-bfae-4271-ae7c-0eef0f8f0d8f.png)

GET 방식은 입력정보가 http request message(요청 메시지)의 헤더에 들어간다.

이 정보는 웹 서버의 환경변수(environment variable) 쪽에 저장되고  
⇒ 이 환경 변수를 PHP나 JSP가 가져와서 데이터베이스에 접근해서 표준출력한다.

⇒ 이렇게 출력된 데이터는 http 응답 메시지(response message)의 body 부분에 저장되어 client에게 전송되어 화면에 출력된다.

예시) 전북대학교 홈페이지.  
메인홈페이지에서 `대학/대학원`을 클릭하면 이에 해당하는 추가적인 내용이 나온다. ex. 간호대학, 공과대학....

그 중 `간호대학`을 클릭하면서 웹서버에게 정보를 요청하기 때문에  
웹브라우저가 client가 되고 / 그 client가 http request message를 웹서버에 보낸다.

그래서 `요기`에 해당하는 부분이 바로 이전에 배웠던 request message의 형태인 (a)가 된다.

그럼 request line이 어떻게 구성되어 있을까? 3개의 component로 이루어져 있다.

![image](https://user-images.githubusercontent.com/64796257/148361997-6913cf87-0f6e-4940-ae31-2b46a62dbc9f.png)

1. GET은 써져 있는 그대로다.
2. URL : chonbuk.ac.kr/kor~ _title : 이 부분이 url 부분이다.
3. HTTP 버전 : HTTP/1.1

⇒ 이렇게 각 구성요소로 입력되면서 http request message가 만들어지고 웹서버로 전송된다.

그런데 방금 전에 `유저 입력정보`에 대해서 말하다가 다른 걸 설명하고 있었다. `유저 입력 정보`란 무엇일까?

홈페이지를 보면 `간호대학`의 정보를 원할 수도 있고 `공과대학`의 정보를 원할 수도 있다.  
그리고 `대학/대학원` 카테고리 뿐만아니라 `취업/입학`에 있는 내용을 원할 수도 있다.

그래서 이 홈페이지를 디자인한 사람은 각각의 key값을 menuID, pid라고 설정하고 각각에 대한 value를 10, 254로 했을 때 어떤 곳을 들어갈 수 있도록 디자인 했다는 걸 알 수 있다.

물론 key값이 꼭 menuID, pid일 필요가 없고 value값이 10,254일 필요도 없다. 디자인하는 사람 마음이다.

그 부분을 따로 떼어서 보면 ![image](https://user-images.githubusercontent.com/64796257/148362216-98208477-798e-4fd4-baaa-ae3f95cfff72.png) 이 부분이다.

이 부분이 유저가 server한테 요청하는 구체적인 입력정보가 된다. 이 정보에 URL이 포함되어 서버에 전송된다.  
이 부분의 일반적인 형태는 다음과 같다.

![image](https://user-images.githubusercontent.com/64796257/148362468-c91c805c-05ed-4935-a7b1-5f00e45604e4.png)

꼭 ?(물음표)로 시작해야 한다. 그리고 여러 개의  key = value를 입력하고 싶다면 &를 통해서 정보를 연결한다.

GET 방식의 특징이 또 있다면, URL 창에 이러한 유저 정보가 모두 보인다는 점이다. 

![image](https://user-images.githubusercontent.com/64796257/148362565-0a8cf876-9521-4dff-9237-8a1c01aa282c.png)

http request message를 server에 보낼 때 server가 그 메시지를 받는 과정에 대해 자세히 알아보자.  
예시에서 보여준 홈페이지의 request line은 다음과 같다고 했다.

![image](https://user-images.githubusercontent.com/64796257/148362609-889274a8-746f-4ae6-a446-67182733409f.png)

서버에서 필요한 정보는 사용자가 요청한 입력정보인데 빨간 부분으로 표시된 부분이다.  
이 부분을 읽기 위해서는 일단 이 정보를 저장할 곳이 필요하다.

`GET 방식`에서는 UNIX environment 변수에 빨간색으로 표시된 부분 즉, 유저정보가 저장된다.

그렇게 저장하고 나서 JSP, PHP와 같은 프로그램들이 environment로부터 정보를 읽어 온다. 
정보를 읽어 올 때 &에 따라서 pharsing해서 쪼개고나서 = 에 따라서 쪼갠다.  
(그래서 PHP, JSP 같은 프로그램에서 pharsing 하는 library를 제공한다.)

이를 통해 (key, value) 짝을 추출한다. 이렇게 하고나서 PHP가 데이터베이스에 접근해서 데이터를 갖고 온 다음 standard output을 통해 출력해서 화면에 띄운다.

학교 홈페이지를 예를 들면, menuID가 10이면 `대학/대학원`, pid가 254면 `간호대학`을 출력하는 정보를 데이터베이스에 넣어놔야 한다.

그런데, environment 변수는 운영체제에 따라 달라진다.  

즉, 운영체제가 어떻게 구현되느냐에 따라 크기가 달라진다. 
데이터가 짧다면 별 문제가 없지만 데이터의 길이가 길다면 environment 변수에 저장하는 건 불가능하다.
(불가능한 이유 – 10주차 대본 p.5 / 슬라이드에 나와있음)

이렇게 데이터의 용량이 커지는 경우에는 GET 방식으로 전달할 수 없다. 이 때는 POST 방식으로 전달해야 한다.

#### POST 방식 

![image](https://user-images.githubusercontent.com/64796257/148362938-9a46c51f-ec75-46ae-b2ed-07fb7847dc6a.png)

POST 방식은 유저의 입력정보가 GET 방식과 다르게 `http request message의 바디(body) 부분`에 실려서 전송된다.

만약에 학교 홈페이지를 POST 방식으로 구현한다면  
GET 방식에서 URL 창에 표시되었던 유저 입력정보(?menuID=10&pid=254#dept_title)가 보이지 않는다.

그럼, 이 정보는 어디로 갔을까?? 그림을 같이 살펴보자.

![image](https://user-images.githubusercontent.com/64796257/148363005-952f5a7a-b314-453c-8981-b8a237ebfdd9.png)

request line에 http://chonbuk.ac.kr/kor/HTTP/1.1 이 저장된다.

이때 유저 입력 정보는 body 부분 즉, payload 부분에 실려 있다.

payload의 크기는 얼마든지 커질 수 있기 때문에 유저 정보의 크기가 아무리 크더라도 문제가 없다.  
그래서 POST 방식은 큰 데이터를 보내더라도 문제가 없다.

![image](https://user-images.githubusercontent.com/64796257/148363169-d7c253de-e0af-449c-a5af-41b2471db574.png)

그러면 server 부분에서 request message를 어떻게 처리할까?? 
POST 방식은 표준입력에 저장된다. (GET 방식은 unix shell의 environment에 저장된다)  
데이터의 방식은 같지만 표준 입력에 저장된다.  

즉, standard input으로부터 pharsing 해서 GET과 똑같은 방식으로 처리한다.  
표준 입력은 입력받을 수 있는 크기가 무한대에 가까워서 크기가 얼마든지 커도 상관없다. 마치 C언어의 scanf를 통해서 읽는 것처럼

그리고 POST 방식은 브라우저 창에 입력정보가 보이지 않기 때문에 보안 측면에 있어 더 안전하다. 
그렇다고 100% 안전한건 아니다. (물론 GET 방식보다 분석하는 건 어렵지만) 엄연히 패킷에는 입력정보가 있기 때문이다.  

정리하면, GET 방식과 POST 방식은 데이터를 저장하는 곳의 차이가 있을 뿐 처리하는 방식은 똑같다.
그래서 이미지나 동영상은 GET과 마찬가지로 MIME 인코딩이나 URL 인코딩을 해줘서 보내야 한다.

cf) 웹 서버를 개발할 때 GET 방식은 웹 브라우저 입력창에 입력 정보가 다 나오기 때문에 테스트 용도로 하기 좋다.
이렇게 GET 방식으로 먼저 개발한 다음에 release 할 때 POST 방식으로 변환해서 출시한다.

![image](https://user-images.githubusercontent.com/64796257/148363372-9c663342-3bc4-4c29-827a-7a881ec41a5a.png)

GET과 POST 방식을 한 줄로 정리하면 위와 같다.

http request를 header에 넣으면 GET, body에 넣으면 POST

입력정보를 environment 변수에 저장하면 GET , 표준입력에 저장하면 POST. 어차피 처리방식은 동일하다.

### Browser Architecture 

![image](https://user-images.githubusercontent.com/64796257/148363632-5be025f7-4388-4f05-8b90-9614052c87cb.png)

Browser Architecture 말 그대로 웹 브라우저의 구조에 대해 살펴보자.

웹 브라우저는 키보드 입력을 받으면 브라우저 내에서 여러 가지 내용을 화면에 출력한다.  
http 프로토콜, ftp(파일 전송), e-mail 등 여러 가지 프로토콜을 다 지원한다. 그래서 상당히 복잡하고 중요한 프로그램이다.

웹 브라우저는 html 문서를 받으면 그 내용을 화면에 출력해주는 기능이 있다. 

html 문서는 논리적 구성에 관련된 내용만 담은 문서이고 웹 브라우저는 그 논리적 구성을 해석해서 화면에 출력한다.  
그렇기 때문에 웹브라우저가 어떤 것이냐에 따라 출력되는 형태가 달라질 수 있다.  

그리고 시간이 지나면서 html 문서만 보여주는 것을 넘어서 코드를 보여주기 위해서 Java가 등장한다.  
이러한 다양한 정보를 한꺼번에 웹 문서에 포함해서 전송하다보니 복잡한 구조를 가지게 된다.

그리고 웹브라우저는 이러한 html 문서를 해석해서 보여주기 때문에 interpreter라는 말을 쓰기도 한다.







