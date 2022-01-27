include 디렉티브도 `<jsp:include>` 액션 태그처럼 지정한 페이지를 현재 JSP 페이지 위치에 포함시키는 기능을 제공한다.

하지만, `<jsp:include>` 액션 태그와 include 디렉티브는 포함하는 방식에 차이가 있다. 

`<jsp:include>` 액션 태그는 다른 JSP로 실행흐름을 이동시켜서 실행 결과를 현재 위치에 포함하는 방식이다.  

반면, `include 디렉티브`는 다른 파일의 내용을 현재 위치에 삽입한 후에 JSP 파일을 자바 파일로 변환하고 컴파일하는 방식이다.

### 1 include 디렉티브의 처리 방식과 사용법

include 디렉티브의 사용방법은 다음과 같다. 

``` jsp
  <%@ include file="포함할 파일" %>
```

file 속성은 include 디렉티브를 사용해서 포함할 파일의 경로를 지정한다.  
include 디렉티브를 사용하면 JSP 파일을 자바 파일로 변환하기 전에 include 디렉티브에서 지정한 파일의 내용을 해당 위치에 삽입하고 그 결과로 생긴 자바 파일을 컴파일한다. 

- 그림 

![image](https://user-images.githubusercontent.com/64796257/148861463-2b26218a-a823-45dd-9060-54b2e154584d.png)

- 예제 - includer.jsp

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>include 디렉티브</title></head>
<body>

<%
	int number = 10;
%>

<%@ include file="/includee.jspf" %> <%-- includee.jspf 라는 파일의 내용을 포함한다.--%>

공통변수 DATAFOLDER = "<%= dataFolder %>"

</body>
</html>
```

cf) include 디렉티브를 통해 다른 JSP에 포함되는 JSP 파일의 경우 일반 JSP 파일과 구분하기 위해 확장자로 jspf를 사용하는 편이다.(물론 jsp를 써도 된다)

- includee.jspf 내용
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
includer.jsp에서 지정한 번호: <%= number %>
<p>
<%
	String dataFolder = "c:\\data";
%>
```

include.jsp의 include 디렉티브가 처리된 상태의 코드 
``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>include 디렉티브</title></head>
<body>

<%
	int number = 10;
%>

<%-- <%@ include file="/includee.jspf" %> // includee.jspf 라는 파일의 내용을 포함한다. --%>
includer.jsp에서 지정한 번호: <%= number %>
<p>
<%
	String dataFolder = "c:\\data";
%>

공통변수 DATAFOLDER = "<%= dataFolder %>"

</body>
</html>
```

위와 같이 include 디렉티브는 코드 차원에서 삽입이 이뤄지기 때문에  
삽입된 파일(includee.jspf)에서 선언한 변수(dataFolder)를 삽입한 JSP(includer.jsp)에서 사용할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148862201-8636a055-9965-4863-bcda-fa3b83d34785.png)

### 2 include 디렉티브의 활용 

include 디렉티브는 코드 차원해서 다른 JSP를 포함하기 때문에 `<jsp:include>` 액션 태그와는 다른 용도로 사용된다. 

`<jsp:include>` 액션 태그는 layout의 한 구성 요소를 module화 하기 위해 사용되는 반면  

include 디렉티브는 다음의 2가지 목적으로 주로 사용된다. 
- 모든 JSP 페이지에서 사용하는 변수 지정
- 저작권 표시와 같이 모든 페이지에서 중복되는 간단한 문장

include 디렉티브를 사용하면 편리하게 공통 변수를 선언할 수 있다.  

예를 들어, 구축하려는 웹 어플리케이션의 JSP 페이지가 application 기본 객체나 session 기본 객체에 저장한 속성값을 읽어와 사용한다고 하자.

이 경우 각 JSP 페이지는 다음과 같이 코드의 시작 부분에 속성값을 읽어와 변수에 저장하는 코드를 추가한다.

``` jsp 
<%
  String memberID = (String) session.getAttribute("MEMBERID");
  File tempFolder = (File) application.getAttribute("TEMPFOLDER");
%>
...

<%= memberId %> 

...

<% 
  fw = new FileWriter(tempFolder, "name.tmp");
  ...
%>

...

```

기본 객체로부터 특정 값을 읽어와 변수에 저장한 후 그 변수를 사용하는 JSP 페이지가 많다면 코드 중복이 많이 발생한다.  
이런 상황에서는 다음과 같이 변수를 지정하는 부분을 별도의 파일에 작성하고 그 파일을 include 디렉티브로 포함시키는 것이 더 좋다.


``` jsp 

<%-- 포함되는 부분 --%>
<%--
<%
  String memberID = (String) session.getAttribute("MEMBERID");
  File tempFolder = (File) application.getAttribute("TEMPFOLDER");
%>
...

--%>
<%-- 포함되는 부분 --%>

<%@ include file="commonVariable.jspf %>

...

<%= memberId %> 

...

<% 
  fw = new FileWriter(tempFolder, "name.tmp");
  ...
%>

...

```

뿐만 아니라 간단한 문장을 포함하고 있는 파일 역시 include 디렉티브로 읽어오기에 적합하다.

### 3 코드 조각 자동 포함 기능 

JSP는 include 디렉티브를 사용하지 않고 JSP의 앞/뒤에 지정한 파일을 삽입하는 기능을 제공하고 있다.  
예를 들어, 모든 JSP 페이지가 소스 코드의 위/아래에 다음과 같이 include 디렉티브를 사용해서 공통 코드를 삽입한다고 하자.

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ include file="/common/varable.jspf" %>
<html>

... 

...

<%@ include file="/common/footer.jspf" %>

```

이와 같이 include 디렉티브를 사용해서 소스의 위/아래에 코드를 삽입한다면  
삽입해야 할 파일의 개수가 많아질때 include 디렉티브의 중복 문제가 발생할 수 있다.

JSP는 이러한 코드 중복을 제거하는 방법을 제공하고 있다. 그것은 바로 web.xml의 `<include-prelude>`와 `<include-coda>` 설정을 사용하는 거다.

ex) 
``` xml
<jsp-config>
  <jsp-property-group>
    <url-pattern>/view/*</url-pattern>
    <include-prelude>/common/variable.jspf</include-prelude>
    <include-coda>/common/footer.jspf</include-coda>
  </jsp-property-group>
</jsp-config>
```
각 태그의 의미는 다음과 같다. 
- `<jsp-property-group>` : JSP의 property를 포함한다.
- `<url-pattern>` : property를 적용할 JSP 파일의 URL 패턴을 지정
- `<include-prelude>` : url-pattern 태그에 지정한 패턴에 해당하는 JSP 파일의 앞에 삽입할 파일 지정
- `<include-coda>` : url-pattern 태그에 지정한 패턴에 해당하는 JSP 파일의 뒤에 삽입할 파일 지정

내용을 보면 URL이 `/view/`로 시작하는 모든 JSP 파일의 앞과 뒤에  
각각 `/common/variable.jspf`와 `/common/footer.jspf` 파일을 삽입하라고 지정했다.

- variable.jspf 내용

``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	java.util.Date CURRENT_TIME = new java.util.Date();
%>
```

- footer.jspf의 내용

``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<!--
	소스 코드 작성: madvirus.net
-->

```

web.xml에서는 `/view/*`로 들어오는 요청에 대해 `variable.jspf`와 `footer.jspf`를 include 디렉티브 방식으로 삽입하도록 설정했다.  
실제로 이에 따라 작성하는지 테스트해보겠다. 

ex) autoInclude.jsp 
``` jsp

<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>자동 Include 실행</title></head>
<body>

현재 시간은 <%= CURRENT_TIME %> 입니다.

</body>
</html>

```

![image](https://user-images.githubusercontent.com/64796257/148864679-b545b936-5837-4fe6-a207-f36a0ecb0b4e.png)

footer의 내용은 주석문으로 표시했기 때문에 해당 페이지의 소스코드를 확인해서 footer.jspf의 내용이 삽입되었는지 확인하자.

![image](https://user-images.githubusercontent.com/64796257/148864753-e4fcc878-a762-415f-956f-c02153748e56.png)

다음과 같이 2개 이상의 `<jsp-property-group>` 태그를 이용해서 자동으로 삽입될 파일을 지정할 수도 있다. 

ex) 
``` xml
<jsp-config>
  <jsp-property-group>
    <url-pattern>/view/*</url-pattern>
    <include-prelude>/common/variable.jspf</include-prelude>
    <include-coda>/common/footer.jspf</include-coda>
  </jsp-property-group>
  
  <jsp-property-group>
    <url-pattern>*.jsp</url-pattern>
    <include-prelude>/common/variable2.jspf</include-prelude>
    <include-coda>/common/footer2.jspf</include-coda>
  </jsp-property-group>
</jsp-config>
```

2개 이상 `<jsp-property-group>` 태그를 설정한 경우 패턴에 일치하는 설정이 차례대로 적용된다. 

예를 들어, `/view/autoInclude.jsp`를 요쳥했다고 하자. 이 요청은 위의 두 URL 패턴에 모두 해당한다.(view 폴더에도 있고 jsp 파일이다)

이 경우 autoInclude.jsp 앞에는 variable.jspf와 variable2.jspf가 차례대로 삽입되고 뒤에는 footer.jspf와 footer2.jspf가 차례대로 삽입된다.

### 4 `<jsp:include>` 액션 태그와 include 디렉티브 비교 

| 비교 항목 | `<jsp:include>` | include 디렉티브 | 
| --- | --- | --- | 
| 처리 시간 | 요청 시간에 처리 | JSP 파일을 자바 소스로 변환할 때 처리 | 
| 기능 | 별도의 파일로 요청 처리 흐름이 이동 | 현재 파일에 삽입시킴 | 
| 데이터 전달 방법 | request 기본 객체나 `<jsp:param>`을 이용해서 파라미터 전달 | 페이지 내에 변수를 선언하고 변수에 값 저장 | 
| 용도 | 화면의 레이아웃의 일부분을 모듈화할 때 주로 사용 | 다수의 JSP 페이지에서 공통으로 사용되는 변수를 지정하는 코드가 간단한 문장을 포함한다 | 













