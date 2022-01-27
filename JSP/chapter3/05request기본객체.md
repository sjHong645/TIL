request 기본 객체는 JSP 페이지에서 가장 많이 사용하는 기본 객체로써 웹 브라우저의 요청과 관련이 있다. 

웹 브라우저에서 웹 사이트의 주소를 입력하면 웹 브라우저는 해당 웹 서버에 연결한 후 요청 정보를 전송하는데 이 요청 정보를 제공하는 것이 바로 request 기본 객체이다. 

request 기본 객체가 제공하는 기능은 다음과 같다. 각각에 대해서 살펴보도록 하겠다. (쿠키와 속성 처리는 뒷 장에서)

- 클라리언트(웹 브라우저)와 관련된 정보 읽기
- 서버와 관련된 정보 읽기
- 클라이언트가 전송한 요청 파라미터 읽기
- 클라이언트가 전송한 요청 헤더 읽기
- 클라이언트가 전송한 쿠키 읽기
- 속성 처리

### 5.1 클라이언트 정보 및 서버 정보 읽기

request 기본 객체는 웹 브라우저, 즉 `클라이언트가 전송한 정보`와 `서버 정보`를 구할 수 있는 메서드를 제공하고 있다. 메서드는 아래와 같다.


| 메서드 | 리턴 타입 | 설명 |
| ------------- | ------------- | ------------- |
| getRemoteAddr()  | String  | 웹 서버에 연결한 클라이언트의 IP 주소를 구한다.   |
| getContentLength()  | long  | 클라이언트가 전송한 요청 정보와 길이를 구한다. 데이터의 길이를 알 수 없다면 -1을 반환한다.  |
| getCharacterEncoding()  | String  | 클라이언트가 요청 정보를 전송할 때 사용한 캐릭터의 인코딩을 구한다.  |
| getContentType()  | String  | 클라이언트가 요청 정보를 전송할 때 사용한 컨텐츠의 타입을 구한다.  |
| getProtocol()  | String  | 클라이언트가 요청한 프로토콜을 구한다.  |
| getMethod()  | String  | 웹 브라우저가 정보를 전송할 때 사용한 방식을 구한다.  |
| getRequestURI()  | String  | 웹 브라우저가 요청한 URL에서 경로를 구한다.  |
| getContextPath()  | String  | JSP 페이지가 속한 웹 애플리케이션의 컨텍스트 경로를 구한다.  |
| getServerName()  | String | 연결할 때 사용한 서버 이름을 구한다.  |
| getServerPort()  | int | 서버가 실행중인 포트 번호를 구한다.  |

ex. requestInfo.jsp

```
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>클라이언트 및 서버 정보</title></head>
<body>

클라이언트IP = <%= request.getRemoteAddr() %> <br>
요청정보길이 = <%= request.getContentLength() %> <br>
요청정보 인코딩 = <%= request.getCharacterEncoding() %> <br>
요청정보 컨텐츠타입 = <%= request.getContentType() %> <br>
요청정보 프로토콜 = <%= request.getProtocol() %> <br>
요청정보 전송방식 = <%= request.getMethod() %> <br>
요청 URI = <%= request.getRequestURI() %> <br>
컨텍스트 경로 = <%= request.getContextPath() %> <br>
서버이름 = <%= request.getServerName() %> <br>
서버포트 = <%= request.getServerPort() %> <br>

</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/147538789-ac77d765-67d3-49d7-888e-c45f87d057af.png)

위 출력 결과를 보면 몇 가지 정보는 URL로 부터 추출되는 것을 확인할 수 있다. 

  http://localhost:8080/chap03/requestInfo.jsp
  
- localhost : request.getServerName() 
- 8080 : request.getServerPort()
- chap03/requestInfo.jsp : request.getRequestURI()

### 5.2 요청 파라미터 처리 

#### 1. HTML 폼과 요청 파라미터 

ex. 

```
<form action = "/chap03/viewParameter.jsp" method = "post">
이름 : <input type = "text" name = "name" size = "10"> <br>
주소 : <input type = "text" name = "address" size = "30"> <br>
좋아하는 동물 : <input type = "checkbox" name = "pet" value = "dog"> 강아지
               <input type = "checkbox" name = "pet" value = "cat"> 고양이
               <input type = "checkbox" name = "pet" value = "pig"> 돼지
<br>
<input type="submit" value = "전송">
</form>
```

위 HTML 태그에서 <input> 태그는 폼의 입력 요소를 생성한다. 웹 브라우저는 아래와 같은 입력 폼을 보여준다.

![image](https://user-images.githubusercontent.com/64796257/147540035-05467883-b837-4c9a-aa10-35b8d74c1d43.png)

HTML 폼의 각 입력 요소는 이름을 가진다. 

여기서는 이름 입력을 위한 <input> 태그의 name 속성의 이름을 "name", 주소 입력을 위한 <input> 태그의 name 속성의 이름을 "address" 라고 했다.

입력 요소의 이름은 웹 브라우저가 서버에 전송하는 요청 파라미터의 이름으로 사용된다. 

ex. 이름 입력 요소에는 "홍길동", 주소 입력 요소에는 "아차곡"을 입력한 뒤에 [전송] 버튼을 눌렀다고 하자. 

그러면 아래 그림과 같이 웹 브라우저가 파라미터 목록들을 웹 서버에 전송한다. 

![image](https://user-images.githubusercontent.com/64796257/147540475-31f52a98-3bbd-4f5b-8f41-ba0a204f85ec.png)

요청 URL을 처리할 웹 프로그램은 웹 브라우저가 전송한 요청 파라미터를 이용해서 알맞은 기능을 구현해야 한다. 

위 그림을 예로 들면 서버쪽의 프로그램은 name 요청 파라미터와 address 요청 파라미터의 값을 읽고 알맞게 기능을 구현해야 한다.

#### 2. request 기본 객체의 요청 파라미더 관련 메서드 

⇒ request 기본 객체는 웹 브라우저가 전송한 파라미터를 읽어올 수 있는 메서드를 제공하고 있다. 대표적인 메서드는 아래와 같다.

| 메서드 | 리턴 타입 | 설명 |
| ------------- | ------------- | ------------- |
| getParameter(String name) | String | 이름이 name인 파라미터의 값을 구한다. 존재하지 않으면 null을 리턴한다. |
| getParameterValues(String name) | String[] | 이름이 name인 모든 파라미터의 값을 배열로 구한다. 존재하지 않으면 null을 리턴한다. |
| getParameterNames() | java.util.Enumeration | 웹 브라우저가 전송한 파라미터의 이름 목록을 구한다. |
| getParameterMap() | java.util.Map | 웹 브라우저가 전송한 파라미터의 이름 맵을 구한다. 맵은 <파라미터 이름, 값> 쌍으로 구성된다.|

실제로 파라미터를 어떻게 읽어올 수 있는지 확인하기 위해서 폼에 입력한 값을 출력해주는 JSP 페이지를 만들어보자 

ex. form.jsp
```
<%@ page contentType = "text/html; charset=utf-8"%>
<html>
<head><title>폼 생성</title></head>
<body>

<form action="/chap03/viewParameter.jsp" method="post">ㅁ
이름: <input type="text" name="name" size="10"> <br>
주소: <input type="text" name="address" size="30"> <br>
좋아하는 동물:
	<input type="checkbox" name="pet" value="dog">강아지
	<input type="checkbox" name="pet" value="cat">고양이
	<input type="checkbox" name="pet" value="pig">돼지
<br>
<input type="submit" value="전송">
</form>
</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/147541761-2bd08844-8a88-4f8f-b357-c8ad2b78070a.png)

위 폼에 데이터를 입력하고 [전송] 버튼을 클릭하면 웹 브라우저는 폼에 입력한 데이터를 viewParameter.jsp에 전송한다. 

ex. viewParameter.jsp
```
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Map" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head><title>요청 파라미터 출력</title></head>
<body>
<b>request.getParameter() 메서드 사용</b><br>
name 파라미터 = <%= request.getParameter("name") %> <br>
address 파라미터 = <%= request.getParameter("address") %>
<p>
<b>request.getParameterValues() 메서드 사용</b><br>
<%
	String[] values = request.getParameterValues("pet");
	if (values != null) {
	for (int i = 0 ; i < values.length ; i++) {
%>
	<%= values[i] %>
<%
		}
	}
%>
<p>
<b>request.getParameterNames() 메서드 사용</b><br>
<%
	Enumeration paramEnum = request.getParameterNames();
	while(paramEnum.hasMoreElements()) {
		String name = (String)paramEnum.nextElement();
%>
		<%= name %>
<%
	}
%>
<p>
<b>request.getParameterMap() 메서드 사용</b><br>
<%
	Map parameterMap = request.getParameterMap();
	String[] nameParam = (String[])parameterMap.get("name");
	if (nameParam != null) {
%>
name = <%= nameParam[0] %>
<%
	}
%>
</body>
</html>
```

String[] values = request.getParameterValues("pet"); 

⇒ request.getParameterValues() 메서드를 사용해서 pet 파라미터의 값을 String 배열로 읽어온다. 

이에 해당하는 부분은 아래와 같다. 앞서 form.jsp의 일부분을 가져왔다.

```
좋아하는 동물:
	<input type="checkbox" name="pet" value="dog">강아지
	<input type="checkbox" name="pet" value="cat">고양이
	<input type="checkbox" name="pet" value="pig">돼지
```

String 배열로 읽어오는 이유는 pet 파라미터의 값이 1개 이상 전달될 수 있기 때문이다. form.jsp를 보면 pet 파라미터 값을 3개까지 전달할 수 있다.

같은 name 값을 가지는 <input>이 존재하면 같은 이름의 파라미터로 전송된다. 

예를 들어, 좋아하는 동물 부분에 `강아지`, `고양이`를 선택했다고 하자. 이러면 pet 파라미터가 2개 전송되고 각각의 값은 "dog" 와 "cat"이다.

이렇게 같은 이름으로 전송되는 파라미터는 request.getParameterValues() 메서드를 이용해서 같은 이름으로 전송된 파라미터 값들을 배열로 가져오면 된다. 

form.jsp의 실행 결과 화면에 아래와 같이 입력하고 나서 [전송] 버튼을 눌렀다.

![image](https://user-images.githubusercontent.com/64796257/147547923-aedd733e-c59b-47b8-98d2-0711a74869e7.png)

그러면 전송 대상인 viewParameter.jsp의 실행결과는 다음과 같이 출력된다.

![image](https://user-images.githubusercontent.com/64796257/147547972-f21e4f6e-4200-4970-94d0-ca99924f128b.png)

만약에 좋아하는 동물의 체크박스를 선택하지 않았다면 당연히 getParameterValues() 의 값은 출력되지 않았을 것이다.

#### 3. GET 방식 전송과 POST 방식 전송

위에서 살펴본 form.jsp는 POST 방식을 사용했다. (method = "post" 부분)

	<form action="/chap03/viewParameter.jsp" method="post">

GET 방식과 POST 방식의 차이점은 `전송 방식`에 있다. 

GET 방식은 요청 URL에 파라미터를 붙여서 전송한다. 위에서 언급한 method를 "post"가 아닌 "get"으로 입력하고 나서 웹 브라우저의 주소 부분을 보겠다. 

- method = "post" 일 때

	http://localhost:8080/chap03/viewParameter.jsp
	
- method = "get" 일 때
	
	http://localhost:8080/chap03/viewParameter.jsp?name=%EC%A0%95%EB%8B%A4%EA%B2%BD&address=%EC%95%BC%EC%B0%A8%EA%B3%A1&pet=dog&pet=pig

GET 방식은 위와 같이 URL의 경로 뒤에 ?와 함께 파라미터를 붙여 전송하는 쿼리 문자열(query string)을 표시한다. 

쿼리 문자열의 형식은 다음과 같다. 

       이름1=값1&이름2=값2&...&이름n=값n 
       
각각의 파라미터는 &로 구분하고 이름과 값은 =로 구분한다. 

name의 값으로 %EC%A0%95%EB%8B%A4%EA%B2%BD가 표시되어 있는데 이는 RFC 2396 규약에 따라 '정다경' 이라는 값을 인코딩한 결과이다.

address의 값인 %EC%95%BC%EC%B0%A8%EA%B3%A1도 같은 원리이다.

GET 방식은 URL의 쿼리 문자열로 전송되기 때문에 form.jsp와 같이 폼을 작성하지 않아도 파라미터를 전송할 수 있다. 

다음과 같이 웹 브라우저 주소란에 직접 URL을 작성해보자.

	http://localhost:8080/chap03/viewParameter.jsp?name=cbk&address=seoul&pet=cat
	
- 그 결과

![image](https://user-images.githubusercontent.com/64796257/147549688-05d6891c-9ce7-44d7-857c-e49da04fc58f.png)

웹 브라우저가 GET 방식으로 데이터를 전송하는 경우, 웹 브라우저에서 웹 서버로 전달되는 데이터는 아래와 같은 형식을 가진다.

[<헤더 확인 하는 방법>](https://paulaner80.tistory.com/entry/%ED%81%AC%EB%A1%AC%EC%97%90%EC%84%9C-request-response-header-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0)

- GET 방식을 이용해서 파라미터 전송 시 request header에 파라미터가 포함된다.

![image](https://user-images.githubusercontent.com/64796257/147550730-5b9c8a57-863d-4293-91fa-ba6596fcd452.png)

웹 브라우저는 HTTP 프로토콜에 맞춰 위 코드와 같은 데이터를 전송한다. 

GET 방식으로 요청 파라미터를 전송한다면 파라미터가 URI와 함께 전송되는 것을 확인할 수 있다.

- POST 방식을 이용해서 파라미터 전송할 때

POST 방식은 GET 방식과 달리 데이터 영역을 이용해서 파라미터를 전송한다. 

![image](https://user-images.githubusercontent.com/64796257/147550989-c1e47638-c91c-4961-823f-7b1b2428a285.png)

15행이 데이터 영역에 해당하며 데이터 영역에 파라미터 데이터가 전송되는 것을 확인할 수 있다.

이러한 차이점들 때문에...

GET 방식은 웹 브라우저, 웹 서버 또는 웹 컨테이너에 따라 전송할 수 있는 파라미터 값의 길이가 제한될 수 있다.

POST 방식은 데이터 영역을 이용해서 데이터를 전송하기 때문에 웹 브라우저나 웹 서버 등에 상관없이 전송할 수 있어서 길이에 제한이 없다.

#### 4. 요청 파라미터 인코딩

웹 브라우저는 웹 서버에 파라미터를 전송할 때 알맞은 캐릭터 셋(charset)을 이용해서 파라미터 값을 인코딩(encoding) 한다. 

웹 서버는 알맞은 캐릭터 셋을 이용해서 웹 브라우저가 전송한 파라미터 데이터를 디코딩(decoding) 한다. 

ex. 웹 브라우저가 `UTF-8` 을 이용해서 파라미터 값을 인코딩 ⇒ 웹 서버는 `UTF-8`을 이용해서 파라미터 값을 디코딩해서 올바른 파라미터값 사용

![image](https://user-images.githubusercontent.com/64796257/147553087-f5077fcf-79be-4dcc-afb4-a9819bbd852f.png)

어떤 캐릭터 셋을 사용할 지 여부는 전송방식(GET 방식, POST 방식)에 따라 달라진다. 

POST 방식은 입력 폼을 보여주는 응답 화면이 사용하는 캐릭터 셋을 사용한다. 만약에 응답 결과에 사용한 캐릭터 셋이 UTF-8이라면 UTF-8을 이용해서 파라미터 값을 인코딩한다. 

ex. form.jsp
```
<%@ page contentType = "text/html; charset=utf-8"%> <%-- 응답결과를 생성할 때 사용할 캐릭터 셋을 UTF-8로 설정 --%>
...

<form action="/chap03/viewParameter.jsp" method="post">
이름: <input type="text" name="name" size="10"> <br>
주소: <input type="text" name="address" size="30"> <br>
...
```

form.jsp가 생성한 응답 결과 화면의 입력 폼에서 name의 파라미터 값으로 '홍길동'을 입력하면 아래와 같이 변환되어 서버에 전송된다.

아래의 값은 UTF-8 캐릭터 셋을 이용해서 인코딩한 결과다.

	name = %ED%99%8D%EA%B8%B8%EB%8F%99
	
서버에서 파라미터 값을 알맞게 사용하려면 웹 브라우저가 파라미터 값을 인코딩할 때 사용한 캐릭터 셋을 이용해서 디코딩해야 한다.

JSP는 request 기본 객체의 setCharacterEncoding() 메서드를 사용해서 파라미터 값을 디코딩할 때 사용할 캐릭터 셋을 지정할 수 있다.

ex. viewParameter.jsp 

```
<%
	request.setCharacterEncoding("utf-8");
%>
```

위 코드는 파라미터 값을 디코딩할 때 사용할 캐릭터 셋을 UTF-8로 지정한 것이다.

request.getParameter() 메서드나 request.getParameterValues() 메서드는 요청 파라미터의 값을 읽어올 때 request.setCharacterEncoding() 을 가지고 지정한 캐릭터 셋을 이용해서 디코딩한다. 별도로 지정하지 않으면 default로 ISO-8599-1 캐릭터 셋을 이용한다.

여기서는 코드의 시작부분에서 request.setCharacterEncoding()을 가지고 utf-8로 캐릭터 셋을 지정했기 때문에 그 이후에 나오는 request.getParameter() 메서드나 request.getParameterValues() 메서드에서 요청 파라미터 값을 읽어올 때 UTF-8 캐릭터 셋을 이용했다.

GET 방식으로 파라미터를 전송하는 방법은 아래와 같이 3가지가 있다. 각 방법에 따라 파라미터 값을 인코딩할 때 사용하는 캐릭터 셋이 달라질 수 있다.

| GET 방식 이용 시 파라미터 전송 방법  | 인코딩 결정 |
| ------------- | ------------- |
| `<a>` 태그의 링크 태그에 쿼리 문자열 추가  | 웹 페이지 인코딩 사용 |
| HTML 폼의 method 속성값을 GET으로 지정해서 폼을 전송  | 웹 페이지 인코딩 사용 |
| 웹 브라우저의 주소에 직접 쿼리 문자열을 포함하는 URL 입력 | 웹 브라우저 마다 다름 |


1) `<a>` 태그 사용
```
  <a href="viewParameter.jsp?name=홍길동&address=아차곡">링크</a>
```
	
대부분의 웹 브라우저에서는 사용자가 링크를 클릭하면 웹 페이지의 캐릭터 셋을 이용해서 파라미터를 인코딩한다.
	
즉, 웹 페이지의 인코딩이 UTF-8이면 UTF-8 캐릭터 셋을 이용해서 파라미터 값을 인코딩한다는 뜻이다.
	
2) HTML 폼 사용

ex. form.jsp 일부
	
```
<%@ page contentType = "text/html; charset=utf-8"%>
...
<form action="/chap03/viewParameter.jsp" method="get">
이름: <input type="text" name="name" size="10"> <br>
주소: <input type="text" name="address" size="30"> <br>
...
<input type="submit" value="전송">
</form>
</body>
</html>
```

위 코드에서 생성하는 웹 페이지의 인코딩은 UTF-8인데 생성된 웹 페이지에서 폼을 전송하면 웹 브라우저는 UTF-8 캐릭터 셋을 이용해서 파라미터 값을 인코딩 한다. 여기서의 파라미터 값들은 name, address를 의미한다. 

3) 직접 쿼리 문자열 작성 - 앞서 다뤘으므로 생략
	
### 5.3 요청 헤더 정보 처리

HTTP 프로토콜은 헤더 정보에 부가적인 정보를 담도록 하고 있다. 

ex. 웹 브라우저는 웹 브라우저의 종류, 선호하는 언어에 대한 정보를 헤더에 담아서 전송한다. 크롬 브라우저의 헤더의 일부를 살펴보자.
```
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Referer: http://localhost:8080/chap03/form.jsp
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
```
User-Agent, Accept, Accept-Language 등이 헤더 이름이고 콜론(:) 뒤에 위치한 문자열들은 헤더 값이 된다.

request 기본 객체는 이러한 헤더 정보를 읽어 올 수 있는 기능을 제공하고 있다. 헤더와 관련된 메소드는 다음과 같다.

| 메서드 | 리턴 타입 | 설명 |
| ------------- | ------------- | ------------- |
| getHeader(String name) | String | 지정한 이름의 헤더 값을 구한다. |
| getHeaders(String naem) | java.util.Enumeration | 지정한 이름의 헤더 목록을 구한다 |
| getHeaderNames() | java.util.Enumeration | 모든 헤더의 이름을 구한다 |
| getIntHeader(String name) | int | 지정한 헤더의 값을 정수형으로 읽어온다 |
| getDateHeader(String name) | long | 지정한 헤더 값을 시간 값으로 읽어온다. |

ex. viewHeaderList.jsp - getHeaderNames(), getHeader() 메서드를 사용했다. 
```
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.util.Enumeration" %>
<html>
<head><title>헤더 목록 출력</title></head>
<body>
<%
	Enumeration headerEnum = request.getHeaderNames();
	while(headerEnum.hasMoreElements()) {
		String headerName = (String)headerEnum.nextElement();
		String headerValue = request.getHeader(headerName);
%>
<%= headerName %> = <%= headerValue %> <br>
<%
	}
%>

</body>
</html>
```

실행 결과 

![image](https://user-images.githubusercontent.com/64796257/147577192-1975eccc-c1f6-4fcc-a474-1c5e23e5c3ae.png)

코드를 읽어보면 쉽게 알 수 있다. 

request.getHeaderNames()를 통해서 모든 헤더의 이름을 headerEnum에 저장했다. 

while 문을 돌려서 headerEnum에 저장되어 있는 header의 이름과 그 이름에 해당 값들을 각각 headerName, headerValue에 저장하고

<%= headerName %> = <%= headerValue %> 를 통해서 각각의 값들을 출력한다.
