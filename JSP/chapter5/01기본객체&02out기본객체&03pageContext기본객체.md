## 01 기본 객체 

3장에서는 request 기본 객체와 response 기본 객체에 대해서 살펴봤다. 이외에도 9가지 기본 객체를 제공하고 있다.

| 기본 객체 | 실제 타입  | 설명 |
| ------------- | ------------- | ------------- |
| request  | javax.servlet.http.HttpServletRequest  | 클라이언트의 요청 정보를 저장 |
| response | javax.servlet.http.HttpServletResponse | 응답 정보 저장 |
| pageContext | javax.servlet.jsp.PageContext | JSP 페이지에 대한 정보 저장 |
| session | javax.servlet.http.HttpSession | HTTP 세션 정보 저장 |
| application  | javax.servlet.ServletContext | 웹 애플리케이션에 대한 정보 저장 |
| out  | javax.servlet.jsp.JspWriter | JSP 페이지가 생성하는 결과를 출력할 때 사용하는 출력 스트림 |
| config | javax.servlet.ServletConfig | JSP 페이지에 대한 설정 정보 저장 |
| page | java.lang.Object | JSP 페이지를 구현한 자바 클래스 인스턴스 |
| exception | java.lang.Throwable | 익셉션 객체. 에러 페이지에서만 사용 |

exception 객체는 에러 페이지에서만 사용할 수 있고 나머지 8개는 JSP 페이지에서 사용할 수 있다.

## 02 out 기본 객체 

JSP 페이지가 생성하는 모든 내용은 out 기본 객체를 통해 전송된다. JSP 페이지 내에서 사용하는 스크립트가 아닌 요소들(HTML 코드 & 텍스트)은 out 기본 객체에 그대로 전달된다.

값을 출력하는 표현식의 결과값도 out 기본 객체에 전달된다.

![image](https://user-images.githubusercontent.com/64796257/147647830-415999cc-b7a5-4833-bb61-329c67033daf.png)

out 기본 객체는 웹 브라우저에 데이터를 전송하는 출력 스트림(stream) 으로써 JSP 페이지가 생성한 데이터를 출력한다. 

위 그림에서 각각의 HTML 태그 및 표현식은 다음과 같은 자바 코드를 실행하는 것과 동일하다.
```
out.println("<html>");
out.println("<head>");
...

out.println(someValue);
...
```

ex. useOutObject.jsp - out 기본 객체를 JSP 페이지의 스크립트릿에서 직접 사용할 수 있다.
```
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>out 기본 객체 사용</title></head>
<body>

<%
	out.println("안녕하세요?");
%>
<br>
out 기본 객체를 사용하여 
<%
	out.println("출력한 결과입니다.");
%>

</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/147648276-25467c73-ee36-45c4-8b0e-6303fba701ea.png)

out.println() 메서드를 통해 "안녕하세요?"와 "출력한 결과입니다."를 출력했다. 

물론 이와 같이 out 기본 객체를 사용해서 응답 결과를 생성하는 경우는 많지 않다. 코드 블럭을 사용하면서 입력할 코드가 늘어나기 때문이다.

보통은 조건에 따라 알맞은 값을 출력해야 하는 경우 out 기본 객체를 사용하면 코드를 덜 복잡하게 만들 수 있다.

### 1. out 기본 객체의 출력 메서드
- print() : 데이터 출력
- println() : 데이터 출력 + 줄 바꿈
- newLine() : 줄바꿈 문자(\r\n 또는 \n) 출력

### 2. out 기본 객체와 버퍼의 관계

앞서 4장에서 page 디렉티브의 buffer 속성을 이용해서 JSP 페이지의 버퍼 크기를 조절할 수 있다고 했다.  
JSP 페이지에서 사용하는 버퍼는 실제로 out 기본 객체가 내부적으로 사용하고 있는 버퍼이다.

ex. <%@ page buffer="16kb" %> ==> out 기본 객체는 16kb 크기의 버퍼를 내부적으로 사용한다.

out 기본 객체는 버퍼와 관련해서 아래와 같은 메서드를 제공한다. 

| 메서드 | 리턴 타입  | 설명 |
| ------------- | ------------- | ------------- |
| getBufferSize() | int | 버퍼의 크기를 구한다 | 
| getRemaining() | int | 현재 버퍼의 남은 크기를 구한다 | 
| clear() | void | 버퍼의 내용을 비운다. 이미 플러시했다면 IOException을 발생한다 | 
| clearBuffer() | void | 버퍼의 내용을 비운다. 플러시하더라도 IOException을 발생시키지 않는다 | 
| flush() | void | 버퍼를 플러시한다 ⇒ 버퍼의 내용을 클라이언트에 전송한다 | 
| isAutoFlush() | boolean | page 디렉티브의 autoFlush의 값을 출력한다 | 

ex. bufferInfo.jsp 
``` 
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page buffer="8kb" autoFlush="false" %>
<html>
<head><title>버퍼 정보</title></head>
<body>

버퍼 크기: <%= out.getBufferSize() %> <br>
남은 크기: <%= out.getRemaining() %> <br>
auto flush: <%= out.isAutoFlush() %> <br>

</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/147649051-e788a721-fd23-49e7-a66c-2a4463ed7fc7.png)

buffer 크기를 8KB로 정했기 때문에 1024 × 8 = 8192가 버퍼의 크기가 된다.

남은 버퍼의 크기를 계산하는 out.getRemaining()

page 디렉티브의 autoFlush 속성의 값이 false이므로 out.isAutoFlush() return값은 false이다.

## 03 pageContext 기본 객체 

pageContext 기본 객체는 JSP 페이지와 일대일로 연결된 객체로 다음 기능을 제공한다.
- 기본 객체 구하기
- 속성 처리
- 페이지 흐름 제어
- 에러 데이터 구하기

### 1 기본 객체 접근 메서드 

pageContext 기본 객체는 다른 기본 객체에 접근할 수 있는 아래의 메서드를 제공한다. 

| 메서드 | 리턴 타입  | 설명 |
| ------------- | ------------- | ------------- |
| getRequest() | ServletRequest | request 기본 객체를 구한다 |
| getResponse() | ServletResponse | response 기본 객체를 구한다 |
| getSession() | HttpSession | session 기본 객체를 구한다 |
| getServletContext() | ServletContext | application 기본 객체를 구한다 |
| getServletConfig() | ServletConfig | config 기본 객체를 구한다 |
| getOut() | JspWriter | out 기본 객체를 구한다 |
| getException() | Exception | exception 기본 객체를 구한다 |
| getPage() | Object | page 기본 객체를 구한다 |

getException() 메서드는 JSP 페이지가 에러 페이지인 경우에만 의미가 있다.

JSP는 웹 환경에서 사용되기 때문에 getRequest(), getResponse()가 실제로 리턴하는 객체 타입은 각각 HttpServletRequest, HttpServletResponse이다.

ex. usePageContext.jsp - 위에서 소개한 메서드를 가지고 request 기본 객체와 out 기본 객체 사용하기(실제로 이렇게 구하지는 않음) 

```
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>pageContext 기본 객체</title></head>
<body>

<%
	HttpServletRequest httpRequest = 
		(HttpServletRequest)pageContext.getRequest();
%>

request 기본 객체와 pageContext.getRequest()의 동일여부:

<%= request == httpRequest %>

<br>

pageContext.getOut() 메서드를 사용한 데이터 출력:

<% pageContext.getOut().println("안녕하세요!"); %>
</body>
</html>

```

- 결과

![image](https://user-images.githubusercontent.com/64796257/147650159-f261dc17-9ed8-43af-b3ce-7087127bdb05.png)

<%= request == httpRequest %> 의 값이 true라고 나왔다.

이를 통해서 pageContext.getRequest()를 통해 얻은 httpRequest는 `request 기본 객체`라는 것을 확인할 수 있다.

pageContext.getOut().println("안녕하세요!") 가 정상적으로 동작했다.

out.println("안녕하세요!")로 입력해도 똑같은 결과가 나온다.  
이를 통해서 pageContext.getOut()을 통해서 out 기본 객체를 얻었다는 것을 확인할 수 있다.





























