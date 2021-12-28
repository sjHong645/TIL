## 06. response 기본 객체 

response 기본 객체는 request 기본 객체와 반대의 기능을 수행한다. 

request 기본 객체가 웹 브라우저가 전송한 요청 정보를 담는다면 response 기본 객체는 웹 브라우저에 보내는 응답 정보를 담는다.

response 기본 객체가 응답 정보와 관련해서 제공하는 대표적인 기능은 다음과 같다.
- 헤더 정보 입력
- 리다이렉트 

각각에 대해서 살펴보자.

#### 1. 웹 브라우저에서 헤더 정보 전송

response 기본 객체는 응답 정보에 헤더를 추가하는 기능을 제공한다. 이와 관련된 메서드는 아래와 같다. 리턴 타입은 모두 void이다.

| 메서드 | 설명 |
| ------------- | ------------- |
| addDateHeader(String name, long date) | name 헤더에 date를 추가한다.  |
| addHeader(String name, String value) | name 헤더에 value를 값으로 추가한다. |
| addIntHeader(String name, int value) | name 헤더에 정수 값 value를 추가한다. |
| setDateHeader(String name, long date) | name 헤더의 값을 date로 설정한다. |
| setHeader(String name, String value) | name 헤더의 값을 value로 설정한다. |
| setIntHeader(String name, int value) | name 헤더의 값을 정수 값 value로 설정한다. |
| containsHeader(String name) | 이름이 name인 헤더를 포함하고 있을 경우 true, 아니면 false를 리턴한다 |

#### 2. 웹 브라우저에서 캐시(cache) 제어를 위한 응답 헤더 입력

JSP를 비롯한 웹 애플리케이션을 개발하다 보면 새로운 내용을 DB에 추가하더라도 웹 브라우저에 출력되는 내용이 바뀌지 않는 경우가 있다.

이런 현상이 발생하는 이유 중 하나는 웹 브라우저가 서버가 생성한 결과를 출력하지 않고 캐시에 저장된 데이터를 출력하기 때문이다.

내용이 자주 바뀌지 않는다면 웹 브라우저의 캐시를 사용해서 보다 빠른 응답을 제공할 수 있지만...

내용이 자주 바뀐다면 웹 브라우저 캐시가 적용되면서 변경된 내용을 수시로 확인할 수 없게 된다.

HTTP는 특수한 응답 헤더를 통해 웹 브라우저가 응답 결과를 캐시할 것인지 여부를 설정할 수 있다. 이와 관련된 헤더는 다음과 같다. 

| 응답 헤더 | 설명 |
| ------------- | ------------- |
| Cache-Control | 이 헤더의 값을 "no-cache"로 지정하면 웹 브라우저는 응답 결과를 캐시하지 않는다. 다만, "no-cache"로 지정하더라도 뒤로가기 버튼을 클릭하면 캐시 저장소에 보관된 응답 내용을 사용하기도 한다. 응답 결과가 캐시 저장소 자체에 보관되지 않도록 하려면 "no-store"를 추가해줘야 한다. |
| Pragma | 이 헤더의 값을 "no-cache"로 지정하면 웹 브라우저는 응답 결과를 캐시에 저장하지 않는다 |
| Expires | 응답 기간의 만료일을 지정한다. 만료일을 현재 시간보다 이전으로 설정함으로써 캐시에 보관되지 않도록 할 수 있다. |

ex. 
```
<%
  response.setHeader("Cache-Control", "no-cache");
  response.addHeader("Cache-Control", "no-store");
  response.setHeader("Pragma", "No-
  response.setDateHeader("Expires", 1L); # 1L은 1970년 1월 1일 0시 0분 0.001초를 의미한다.
```

#### 3. 리다이렉트를 이용한 페이지 이동

리다이렉트는 웹 서버가 웹 브라우저에게 다른 페이지로 이동하라고 응답하는 기능이다.

ex. 사용자가 로그인에 성공한 후 메인 페이지로 자동으로 이동하는 것 역시 리다이렉트 기능이다.

![image](https://user-images.githubusercontent.com/64796257/147579559-43999216-91bd-4087-8ce5-3726f41b212d.png)

response 기본 객체는 다음 메서드를 이용해서 웹 브라우저가 리다이렉트 하도록 지시할 수 있다.

- response.sendRedirect(String location) ⇒ 주로 다음과 같은 형태로 사용된다.

``` 
<%@ page import = "java.sql.&" %>
...
<%
  // JSP 페이지에서 필요한 코드
  
  response.sendRedirect("이동할 페이지"); 

%>
```

ex. login.jsp
```
<%@ page contentType="text/html; charset=utf-8" %>
<%
	String id = request.getParameter("memberId");
	if (id != null && id.equals("madvirus")) {
		response.sendRedirect("/chap03/index.jsp");
	} else {
%>
<html>
<head><title>로그인에 실패</title></head>
<body>
잘못된 아이디입니다.
</body>
</html>
<%
	}
%>
```

코드를 보면 id는 memberID라는 파라미터의 값을 저장할 변수이다. 

그 id가 null이 아니면서 madvirus라면 /chap03/index.jsp 페이지로 이동한다. 

그렇지 않으면, if문을 제끼고 "잘못된 아이디입니다" 라는 문구를 화면에 출력할 것이다.

다음과 같이 입력하면 로그인에 성공한다. 

```
  http://localhost:8080/chap03/login.jsp?memberId=madvirus
```

그렇지 않고 
```
  http://localhost:8080/chap03/login.jsp?memberId=hong
  
  또는
  
  http://localhost:8080/chap03/login.jsp
```
라고 입력하면 로그인에 실패한다. 

만약에 같은 서버 주소가 아닌 다른 서버 주소에 위치한 페이지로 이동시키고 싶다면 리다이렉트의 매개변수에 전체 URL을 입력하면 된다. 

ex. response.sendRedirect("http://javacan.tistory.com/entry/Event-Sourcing-PT");

알파벳이나 숫자 그리고 몇몇 기호를 제외한 나머지 글자들을 URL에 포함시키고 싶다면 적절하게 인코딩해줘야 한다.

만약에 이름이 name인 파라미터의 값을 '자바'로 지정해서 리다이렉트 하고 싶다고 해보자. 

그렇다면, '자바'를 인코딩한 형태를 URL에 입력해야 하는데 이를 실제로 개발자가 입력하는 것은 너무 번거롭다. 그래서 이를 계산해줄 메소드가 존재한다. 그 메소드는 바로 URLEncoder.encode() 메소드이다.

ex. URLEncoder.encode() 메소드를 사용해서 파라미터의 값으로 사용할 문자열을 UTF-8로 인코딩한다. - redirectEncodingTest.jsp

```
<%@ page pageEncoding="utf-8" %>
<%@ page import = "java.net.URLEncoder" %>
<%
	String value = "자바";
	String encodedValue = URLEncoder.encode(value, "utf-8");
	response.sendRedirect("/chap03/index.jsp?name=" + encodedValue);
%>
```

위 코드는 "자바" 라는 문자열을 UTF-8로 인코딩해서 encodedValue에 저장했다. 

웹 서버에 전송할 파라미터 값은 알맞게 인코딩해야 하기 때문에

```
response.sendRedirect("/chap03/index.jsp?name=자바"); 

가 아니라

response.sendRedirect("/chap03/index.jsp?name=%EC%9E%90%EB%BO%94);

를 입력해줘야 한다. 
```

그래서 "자바"라는 문자열을 UTF-8로 인코딩해서 =%EC%9E%90%EB%BO%94로 바꾼 다음에 URL에 넣은 것이다.

#### 4. 주석

- 스크립트릿과 선언부의 코드 블럭 ⇒ 해당 부분은 자바 코드이기 때문에 자바 형식의 주석을 쓰면 된다.

```
<% 
  // 주석 내용~~~
  
  코드~~~
%>
```

- JSP 코드 
```
<%-- 원하는 주석 내용 --%>
```


