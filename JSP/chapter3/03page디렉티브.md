page 디렉티브는 JSP 페이지에 대한 정보를 입력하기 위해서 사용된다.

page 디렉티브를 사용하면 JSP 페이지가 어떤 문서를 생성하는지, 어떤 Java 클래스를 사용하는지, 세션(session)에 참여하는지와 같은 JSP 페이지를 실행하는데 필요한 정보를 입력할 수 있다. 

예시) 
``` jsp
<%@ page contentType = "text/html; charset = utf-8" %>
<%@ page import = "java.util.Data" %>
```

page 디렉티브에서 contentType 속성, import 타입 속성을 사용해서 JSP 페이지에 필요한 정보를 설정하고 있다. 

이 두 가지 속성 이외에도 페이지 정보를 설정하는데 필요한 속성을 추가적으로 제공하고 있다. 

| 속성 | 설명 | 기본값 |
| ---- | ---- | ---- |
| contentType | JSP가 생성할 문서의 MIME 타입과 캐릭터 인코딩을 지원 | text/html |
| import | JSP 페이지에서 사용할 Java 클래스를 지정 | |
| session | JSP 페이지가 세션을 사용할지 여부를 지정한다. "true" 일 경우 세션을 사용하고 "false"일 경우 세션을 사용하지 않는다. | true | 
| buffer | JSP 페이지의 출력 버퍼 크기를 지정한다. "none" 일 경우 출력 버퍼를 사용하지 않고 "8kb"인 경우 8킬로바이트 크기의 출력 버퍼를 사용한다. | 최소 8kb | 
| autoFlush | 출력 버퍼가 다 찼을 경우 자동으로 버퍼에 있는 데이터를 출력 스트림에 보내고 비울지 여부를 나타낸다. "true" 인 경우 버퍼의 내용을 웹 브라우저에 보낸 후 버퍼를 비우고 "false"인 경우 에러를 발생시킨다.| true | 
| info | JSP 페이지에 대한 설명을 입력한다. | | 
| errorPage | JSP 페이지를 실행하는 도중에 에러가 발생할 때 보여줄 페이지를 저장한다. | | 
| isErrorPage | 현재 페이지가 에러가 발생할 때 보여주는 페이지인지 여부를 지정한다. "true" 인 경우 에러 페이지이고 "false" 인 경우 에러 페이지가 아니다. | false | 
| pageEncoding | JSP 페이지 소스 코드의 캐릭터 인코딩을 지정한다. | | 
| isELIgnored | "true"일 경우 표현 언어를 해석하지 않고 문자열로 처리하고 "false" 인 경우 표현 언어를 지원한다. | false | 
| deferredSyntaxAllowedAsLiteral | #{ 문자가 문자열 값으로 사용되는 것을 허용할지의 여부를 지정한다. | false | 
| trimDirectiveWhitespaces | 출력 결과에서 템플릿 텍스트의 공백 문자를 제거할지 여부를 지정한다. | false |

### 3.1 contentType 속성과 캐릭터 셋

page 디렉티브의 contentType 속성은 JSP 페이지가 생성할 문서의 타입을 지정한다. contentType 속성의 값은 다음과 같이 구성된다.

``` 
TYPE

또는

TYPE; charset = 캐릭터 셋
```

contentType은 JSP가 생성할 문서의 MIME 타입을 입력한다. 

JSP에서 주로 사용하는 MIME 타입은 `text/html`이고 필요에 따라 `text/xml`, `application/json` 등의 MIME 타입을 사용한다.

ex. <%@ page contentType = "text/html" %> (contentType 속성의 기본값은 text/html이다.)

cf) [MIME](https://ko.wikipedia.org/wiki/MIME)

contentType의 속성 값 중에서 `; charset = 캐릭터 셋` 부분은 옵션이다. 즉, 생략할 수 있는 부분이다. 

기본값으로 ISO-8859-1을 사용하지만, 한국어를 표현하기 위해서는 UTF-8 이나 EUC-KR을 사용해야 한다.

``` jsp
<%@ page contentType = "text/html; charset = UTF-8" %>
 
또는 
 
<%@ page contentType = "text/html; charset = utf-8" %>
```

p.58~59 ⇒ 예시 부분

### 3.2 import 속성

Java에서 import를 사용하듯이 JSP에서 유사하게 page 디렉티브의 import 속성을 사용할 수 있다. 

``` jsp
<%@ page import = "java.util.Calendar" %>
<%@ page import = "java.util.Date" %>

또는

<%@ page import = "java.util.Calendar, java.util.Date" %>

또는 

<%@ page import = "java.util.*" %>
```

ex) 오늘 날짜 표시 - useImportCalendar.jsp
``` js[
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.util.Calendar" %>
<html>
<head><title>Calendar 클래스 사용</title></head>
<body>
<%
	Calendar cal = Calendar.getInstance();
%>
오늘은 
	<%= cal.get(Calendar.YEAR) %>년 
	<%= cal.get(Calendar.MONTH) + 1 %>월
	<%= cal.get(Calendar.DATE) %>일
입니다.
</body>
</html>

```

결과 

![image](https://user-images.githubusercontent.com/64796257/147457650-0ce3f639-faea-4240-98fc-7512b3c7504b.png)

### 3.3 trimDirectiveWhitespaces 속성을 이용한 공백 처리

chapter02/time.jsp 파일을 실행하고 나서 페이지 소스를 보면 다음과 같이 나타나는 것을 볼 수 있다.

![image](https://user-images.githubusercontent.com/64796257/147458007-4b0ffb11-79bc-4ecb-a041-bd4a952e0c2f.png)

1행에 불필요한 공백이 있다는 걸 볼 수 있다. 이러한 공백을 제거하려면 trimDirectiveWhitespaces 속성의 값을 true로 지정하면 된다.

그래서 기존의 time.jsp를 아래와 같이 변경했다.

``` jsp
<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces = "true" %> <%-- 추가한 부분 --%>
<html>
<head>
<title>현재 시간</title>
 </head>
 <body>
 지금 : <%= new java.util.Date() %>
 </body>
</html>

```

위와 같이 변경하고 나서 웹 브라우저에서 time.jsp를 실행한 뒤 소스 코드를 확인하면 공백문자가 보이지 않는다.

![image](https://user-images.githubusercontent.com/64796257/147458285-90328e75-b30c-4505-b1ff-23a801a68a28.png)

### 3.4 JSP 페이지의 인코딩과 pageEncoding 속성 

웹 컨테이너가 JSP 페이지를 읽어올 때 사용할 캐릭터 셋을 결정하는 기본 과정은 다음과 같다.

1. 파일이 [BOM](https://ko.wikipedia.org/wiki/%EB%B0%94%EC%9D%B4%ED%8A%B8_%EC%88%9C%EC%84%9C_%ED%91%9C%EC%8B%9D)으로 시작하지 않는 경우

   A. 기본 인코딩을 이용해서 파일을 처음부터 읽고, page 디렉티브의 pageEncoding 속성을 검색한다. (단, pageEncoding 속성을 찾기 전에 ASCII 문자 이외의 글자가 포함되어 있지 않은 경우에만 적용한다)
   
   B. pageEncoding 속성이 값을 가지고 있다면 파일을 읽어올 때 속성값을 캐릭터 셋으로 사용한다.
   
   C. pageEncoding 속상이 없다면 contentType 속성을 검색한다. contentType 속성이 존재하고 charset을 이용해서 캐릭터 셋을 지정했다면, 파일을 읽어올 때 사용할 캐릭터 셋으로 charset에 지정한 값을 사용한다. (단, contentType 속성을 찾기 전에 ASCII 문자 이외의 글자가 포함되어 있지 않은 경우에만 적용한다)
   
   D. 모두 해당하지 않으면 ISO-8859-1을 캐릭터 셋으로 사용한다.
   
2. 파일이 [BOM](https://ko.wikipedia.org/wiki/%EB%B0%94%EC%9D%B4%ED%8A%B8_%EC%88%9C%EC%84%9C_%ED%91%9C%EC%8B%9D)으로 시작할 경우

   A. BOM을 이용해서 결정된 인코딩을 이용해서 파일을 읽고 page 디렉티브의 pageEncoding 속성을 검색한다.
   
   B. 만약 pageEncoding 속성의 값과 BOM을 이용해서 결정된 인코딩이 서로 다르면 에러를 발생시킨다.
   
3. 1 또는 2 과정을 통해 설정된 캐릭터 셋을 이용해서 JSP 소스 코드를 읽는다.

⇒ 정리하면, JSP 파일을 읽을 때 page 디렉티브의 pageEncoding 속성과 contentType 속성을 이용해서 캐릭터 인코딩을 결정한다는 내용이다.

파일이 유니코드가 아니거나 BOM으로 시작하지 않는다면, 먼저 pageEncoding 속성을 검색하고 나서 contentType 속성의 charset의 값을 검색한다.

그래서 만약에 pageEncoding 속성을 지정하지 않고 contentType 속성의 charset의 값을 잘못 지정한다면 인코딩이 잘못되면서 깨진 문자가 출력된다.

* pageEncoding 속성에서 지정한 인코딩과 contentType 속성에서 지정한 인코딩이 서로 다를 수 있다.

ex. JSP 파일은 UTF-8로 작성 & 응답 결과는 EUC-KR로 생성하고 싶다면..

pageEncoding 속성은 utf-8로 지정하고 contentType 속성은 euc-kr로 지정하면 된다.

``` jsp
<%@ page contentType="text/html; charset=euc-kr" %> <%-- contentType 속성 --%>
<%@ page pageEncoding="utf-8" %> <%-- pageEncoding 속성 --%>

... 
```
