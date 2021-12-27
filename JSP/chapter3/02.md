JSP 페이지에는 총 7가지의 요소가 있다.
- 디렉티브(Directive)
- 스크립트 : 스크립트릿(Scriptlet), 표현식(Expression), 선언부(Declaration)
- 표현 언어(Expression Language)
- 기본 객체(Implicit Object)
- 정적 데이터
- 표준 액션 태그(Action Tag)
- 커스텀 태그(Cumstom Tag)와 표준 태그 라이브러리(JSTL)

이들에 대해서 간단하게 알아보도록 하자.

### 2.1 디렉티브

디렉티브(Directive)는 JSP 페이지에 대한 설정 정보를 지정할 때 사용되며 다음과 같은 구문을 통해서 디렉티브를 선언할 수 있다.

```
<%@ 디렉티브이름 속성1 = "값1" 속성2 = "값2" ... %>
```

위 문장은 디렉티브의 큰 구조를 의미하는데 01.md에서 썼던 디렉티브를 살펴보자.

```
<%@ page contentType = "text/html; charset = uft-8" %>
```

디렉티브의 이름은 `page`가 되고 `contentType` 이라는 속성을 사용했고 contentType의 속성 값은 "text/html; charset = uft-8"이다.

(물론 디렉티브는 page 말고 taglib, include 도 있지만 이에 대한 자세한 내용은 뒷장에서 다루도록 하겠다)

### 2.2 스크립트 요소

JSP에서 문서의 내용을 동적으로 생성하기 위해 사용되는 것이 스크립트 요소이다.

스크립트 요소를 사용하면 사용자가 폼에 입력한 정보를 데이터베이스에 저장할 수 있으며 데이터베이스로부터 게시글 목록을 읽어와 출력할 수도 있다.

또한, Java가 제공하는 다양한 기능도 사용할 수 있다. JSP의 스크립트 요소는 다음과 같이 3가지가 있다.

- 표현식(Expression) : 값을 출력
- 스크립트릿(Scriptlet) : Java 코드 실행
- 선언부(Declaration) : Java 메서드 생성

### 2.3 기본 객체

request, response, session, application, page 등 다수의 기본 객체가 존재하는데 

이들은 각각 요청 파라미터 읽어오기, 응답 결과 전송하기, 세션 처리하기, 웹 어플리케이션 정보 읽어오기 등의 기능을 제공한다. 

이 중에서 request 기본 객체, session 기본 객체, response 기본 객체를 주로 사용한다. 

### 2.4 표현 언어

JSP의 스크립트 요소는 자바 문법을 그대로 사용할 수 있기 때문에 자바 언어의 특징을 그대로 사용할 수 있다. 

하지만, 스크립트 요소를 사용하면 JSP 코드가 다소 복잡해진다. 예시와 함께 보자.

``` 
<% 
  int a = Integer.parseInt(request.getParameter("a")); 
  int b = Integer.parseInt(request.getParameter("b")); 
%>  
a * b = <% = a * b &>
```

위 코드는 두 개의 문자열 (a, b)을 정수로 변환한 뒤, 두 숫자를 곱한 값을 응답으로 출력하는 코드이다. 

위 코드를 표현 언어를 사용하면 다음과 같이 작성할 수 있다.

```
a * b = ${param.a * param.b}
```

표현 언어는 `%{`와 `}` 사이에 정해진 문법을 따르는 식을 입력한다. JSP는 이 식을 분석해서 알맞은 기능을 실행한다. 

이와 같이 표현 언어를 사용하면 코드를 보다 간결하고 이해하기 쉽게 만들어준다는 장점이 있다. 

### 2.5 표준 액션 태그와 태그 라이브러리

액션 태그는 JSP 페이지에서 특별한 기능을 제공한다. 아래의 코드를 보자.

```
<%@ page contentType = "text/html; charset = utf-8" %>
<html>
...
<jsp: include page = "header.jsp" flush = "true" />
...
</html>
```

위 코드에서 사용한 액션 코드는 <jsp: include>이다. 이 액션 태크는 특정 페이지의 실행 결과를 현재 위치에 포함시킬 때 사용한다.

이와 같이 액션 태크는 <jsp: 액션태그이름> 의 형태를 가지며 액션 태그의 종류에 따라 서로 다른 속성과 값을 가진다.

커스텀 태그는 JSP를 확장시켜주는 기능으로서 액션 태그와 마찬가지로 태그 형태로 기능을 제공한다. 다만, 커스텀 태그는 개발자가 직접 개발해줘야 한다. 이에 대한 내용은 뒷장에서 자세히 배운다.

커스텀 태그들 중에서 자주 사용하는 것들을 별도로 표준화한 태그 라이브러리가 있는데 이를 JSTL(JavaServer Pages Standard Tag Library)이라 한다.

JSTL은 if-else 조건문, for 문과 같은 반복 처리 등등을 커스텀 태그를 이용해서 구현할 수 있도록 한다. 
