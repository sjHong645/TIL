JSP 코드를 작성하는 이유는 웹 브라우저에 보여줄 HTML 문서를 생성하는 것이다.

JSP를 사용해서 파일 다운로드를 구현할 수 있고 XML과 같은 다른 종류의 문서를 응답으로 제공할 수도 있지만 대부분의 JSP 코드는 HTML을 생성한다.

HTML 문서를 생성하는 JSP 코드는 크게 `설정 부분`과 `응답 생성 부분`으로 구성된다. 아래 예시를 보자.

``` jsp
<%@ page contentType = "text/html; charset = utf-8" %>
<html>
<head>
  <title> HTML 문서의 제목 </title>
</head>
<body>
<% 
  String bookTitle = "JSP 프로그래밍"; 
  String author = "김수현";
%>
<b> <% = bookTitle %> </b> (<% = author %>) 입니다.
</body>
</html>

```
첫 번째 줄은 JSP 페이지에 대한 정보를 입력하는 설정 부분 / 그 이외 부분은 HTML 문서를 생성하는 생성 부분 으로 나눈다.

- 설정 부분 : 페이지에 대한 정보가 있고 다음과 같은 정보를 입력한다.

1) JSP 페이지가 생성하는 문서의 타입
2) JSP 페이지에서 사용할 커스텀 태그
3) JSP 페이지에서 사용할 자바 클래스 지정

⇒ 이외에도 다양한 설정 정보를 입력할 수 있다.

<%@ page contentType = "text/html; charset = utf-8" %> 를 해석하면 다음과 같다.

`JSP 페이지에서 생성할 문서는 HTML 이고 문서의 character set은 UTF-8이다.`
