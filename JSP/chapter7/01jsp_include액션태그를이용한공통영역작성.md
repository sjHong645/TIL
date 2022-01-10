## 01 `<jsp:include>` 액션 태그를 이용한 공통 영역 작성

보통 하나의 웹 사이트를 구성하는 페이지는 동일한 상단/좌측 메뉴, 하단 푸터(footer)를 갖는다.  
이런 공통 구성 요소를 위한 코드를 모든 JSP 페이지마다 작성한다면 코드 중복이 발생하게 된다.

게다가 공통 구성 요소를 위한 코드를 모든 JSP 페이자마다 작성한다면 수정하고 싶을 때 모든 JSP 페이지를 수정해야 한다는 부담이 있다.  

이러한 구성요소의 코드 중복 문제를 없앨 때 사용할 수 있는 것이 바로 `<jsp:include>` 액션 태그이다.

`<jsp:include>` 액션 태그는 위치한 부분에 지정한 페이지를 포함한다. `<jsp:include>` 액션 태그의 동작 방식은 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/148727671-377cfd82-b223-49a7-aa7f-6c07d2f74331.png)

그림을 통해서 처리 순서를 살펴보자.
1. main.jsp가 웹 브라우저의 요청을 받는다.
2. [출력내용 A]를 출력 버퍼에 저장한다.
3. `<jsp:include>`가 실행되면 요청 흐름을 sub.jsp로 이동한다.
4. [출력내용 B]를 출력 버퍼에 저장한다.
5. sub.jsp의 실행이 끝나면 요청 흐름이 다시 main.jsp의 `<jsp:include>`로 돌아온다.
6. `<jsp:include>` 이후 부분인 [출력내용 C]를 출력 버퍼에 저장한다.
7. 출력 버퍼의 내용을 응답 데이터로 전송한다. 

이러한 처리 순서에 따라 `<jsp:include>` 액션 태그는 현재 JSP 페이지에 포함시킬 실행 결과를 `<jsp:include>` 액션 태그가 위치한 곳에 포함시킨다.

### 1.1 `<jsp:include>` 액션 태그 이용법

`<jsp:include>` 액션 태그의 기본적인 사용방법은 다음과 같다. 
```
<jsp:include page = "포함할 페이지" flush = "true" />
```

`<jsp:include>` 액션 태그의 두 속성은 다음과 같다. 
- page : 포함할 JSP 페이지의 경로 지정
- flush : 지정한 JSP 페이지를 실행하기 전에 출력 버퍼를 플러시할지 여부 지정.(true = 출력 버퍼 플러시 / false = 플러시하지 않음 - 기본값)

출력 버퍼를 플러시한다는 건 위에서 본 그림을 가지고 얘기해보면  
main.jsp에서 `<jsp:include>` 액션 태그를 실행하는 시점에서 출력 버퍼에 저장된 [출력내용 A]를 플러시한 뒤에 sub.jsp 페이지로 흐름이 이동한다는 것을 의미한다. 

출력 버퍼를 플러시하면 응답 상태 코드와 HTTP 응답 헤더가 웹 브라우저에 함께 전송된다. 

따라서, flush 속성을 true로 지정하면 이후에 새로운 헤더 정보를 추가해도 반영되지 않게 된다. 

ex) main.jsp

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>main</title></head>
<body>

main.jsp에서 생성한 내용. # sub.jsp 이전에 생성되는 내용

<jsp:include page="sub.jsp" flush="false" /> # sub.jsp로 요청 처리 흐름이 이동함

include 이후의 내용. # sub.jsp 이후에 생성되는 내용

</body>
</html>
```

sub.jsp의 내용은 다음과 같다. 
``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>

<p>
sub.jsp에서 생성한 내용.
</p>

```
이러한 상황에서 main.jsp를 실행하면 아래와 같은 결과가 나온다. 

![image](https://user-images.githubusercontent.com/64796257/148728977-4a3d6b6d-d46a-4cf0-b318-86e1c9070451.png)

그림을 보면 `<jsp:include>` 액션 태그가 위치한 부분에 sub.jsp가 생성한 내용이 포함된 것을 알 수 있다.

위 그림의 출력 결과를 HTML 코드로 보면 좀 더 확실히 `<jsp:include>` 의 실행 결과를 확인할 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/148729153-56f1870f-e6f3-42af-bc4b-f1d79a351ee0.png)

### 1.2 `<jsp:include>` 액션 태그를 이용한 중복 영역 처리 

일반적인 웹사이트는 아래 그림과 같이 상단 메뉴, 좌측 메뉴, 중앙 내용, 하단 메뉴 등의 요소로 구성되어 있다.  
![image](https://user-images.githubusercontent.com/64796257/148729404-e1ac3306-0cf3-4309-9e98-3bd519c159c4.png)

위 그림과 같은 화면을 생성하려면 각 JSP 페이지마다 다음과 같이 공통된 영역을 위한 코드를 포함해야 한다.

![image](https://user-images.githubusercontent.com/64796257/148729493-a258624d-86d8-475c-87a0-4706a7749525.png)

1개의 JSP 페이지만 위와 같이 코딩하면 상관없지만 수십/수백 개의 JSP 페이지를 위와 같이 코딩해야 한다면 개발 및 유지보수 과정에서 많은 비용이 발생한다.

`<jsp:include>` 액션 태그는 이러한 문제를 해결해준다.  
`<jsp:include>` 액션 태그는 다른 JSP 페이지의 결과 화면을 포함하므로 아래 그림과 같이 공통 부분에 대해 별도의 JSP 페이지를 작성해서  
중복 문제를 해결할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148730163-732b3496-1786-43d7-879a-f5dc498bd014.png)

위 그림과 같이 공통 영역을 별도의 JSP로 작성한다.  
공통 영역을 사용하려는 JSP 페이지는 `<jsp:include>` 액션 태그를 사용해서 공통 영역을 포함할 수 있다.

공통 영역을 생성하는 코드가 1개의 JSP 페이지에 위치하므로 공통 영역을 위한 코드가 중복되는 것을 방지할 수 있다. 

ex) layout.jsp
``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>layout1</title></head>
<body>

<table width="400" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td colspan="2">
		<jsp:include page="/module/top.jsp" flush="false" /> 
    # 상단 영역의 코드 - 직접 코드를 작성하지 않고 관련 영역 생성
	</td>
</tr>
<tr>
	<td width="100" valign="top">
		<jsp:include page="/module/left.jsp" flush="false" />
    # 좌측 영역의 코드 - 직접 코드를 작성하지 않고 관련 영역 생성
	</td>
	<td width="300" valign="top">
		<!-- 내용 부분: 시작 -->
		레이아웃 1
		<br><br><br>
		<!-- 내용 부분: 끝 -->
	</td>
</tr>
<tr>
	<td colspan="2">
		<jsp:include page="/module/bottom.jsp" flush="false" />
    # 하단 영역의 코드 - 직접 코드를 작성하지 않고 관련 영역 생성
	</td>
</tr>
</body>
</html>
```

상단/좌측/하단 영역의 코드를 직접 생성하지 않고 `<jsp:include>` 액션 태그를 사용해서 관련 영역을 생성했다.

layout.jsp를 실행하면 아래와 같이 공통부분이 출력된다. 

![image](https://user-images.githubusercontent.com/64796257/148731487-aca17e68-bb98-42b7-b5e5-ca059c0b4d5a.png)

cf) top.jsp, left.jsp, bottom.jsp는 모두 /chap07/module 폴더 안에 있다.

layout.jsp와 동일한 공통 부분을 사용한 JSP 페이지인 layout2.jsp를 만들었다.  
아래 결과를 통해 공통 영역에 출력된 내용이 layout.jsp의 결과와 동일한 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148731981-8d5f31b8-b48f-4ca0-99c6-804daa5c7ef8.png)

### 1.3 `<jsp:param>`으로 포함할 페이지에 파라미터 추가하기

`<jsp:include>` 액션 태그는 `<jsp:param>` 태그를 이용해서 포함할 JSP 페이지에 파라미터를 추가할 수 있다. 다음과 같이 사용한다.

``` jsp
<jsp:include page = "/module/top.jsp" flush = "true" />
  
   <jsp:param name = "param1" value = "value1" />
   <jsp:param name = "param2" value = "value2" />
   
</jsp:include>

```

위와 같이 `<jsp:param>` 액션 태그는 `<jsp:include>` 액션 태그의 자식 태그로 추가된다. 

name 속성과 value 속성은 각각 포함할 페이지에 새로 추가할 파라미터의 이름과 값을 입력한다.  
(이때, value 속성에는 값을 직접 입력하거나 표현식을 이용해서 값을 지정한다)

ex) info.jsp - `<jsp:param>` 액션 태그를 사용해서 infoSub.jsp에 파라미터 추가

- info.jsp
``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>INFO</title></head>
<body>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td>제품번호</td> <td>XXXX</td> # 제품번호 XXXX
</tr>
<tr>
	<td>가격</td> <td>10,000원</td> # 가격 10,000
</tr>
</table>

<jsp:include page="infoSub.jsp" flush="false">
	<jsp:param name="type" value="A" /> 
</jsp:include>

# infoSub.jsp에 이름이 type이고 값이 A인 파라미터를 추가했다.

</body>
</html>
```

- infoSub.jsp - 제품을 타입별로 추가 정보를 출력해주는 화면
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	String type = request.getParameter("type");
	if (type == null) {
		return;           # type이 null이면 돌려보냄
	}
%>
<br>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td>타입</td>
	<td><b><%= type %></b></td>
</tr>
<tr>
	<td>특징</td>
	<td>
<%  if (type.equals("A")) { %> # type이 A이면 "강한 내구성"을 출력
	강한 내구성.
<%  } else if (type.equals("B")) { %> # type이 B이면 "뛰어난 대처 능력"을 출력
	뛰어난 대처 능력
<%  } %>
	</td>
</tr>
</table>
```

두 파일의 내용을 종합하면 info.jsp에서 type의 값으로 A를 전달했기 때문에 결과 창에서 "특징"으로 "강한 내구성"이 나올 것이다.

![image](https://user-images.githubusercontent.com/64796257/148733380-1ee113dc-09e1-4dcc-b03f-97d37a772ee3.png)

`<jsp:param>` 태그는 이미 동일한 이름의 파라미터가 존재하면 기존 파라미터의 값을 유지하면서 새로운 값을 추가한다.

![image](https://user-images.githubusercontent.com/64796257/148733693-e95f3047-e47f-4a3c-a357-1c0fb41293e5.png)

위 그림에서 요청 URL에 `?name=cbk`가 포함되어 있으므로 body_main.jsp는 이름이 "name"이고 값이 "cbk"인 파라미터를 한 개 갖는다.

이 상태에서 `<jsp:param>`을 이용해서 이름이 "name"인 파라미터를 포함한 페이지를 추가한다면  
그림의 오른쪽과 같이 기존 파라미터를 유지하면서 새로운 파라미터를 추가한다.

이때, `<jsp:param>` 액션 태그로 추가한 파라미터는 기존 파라미터보다 우선한다.  
따라서, 포함되는 body_sub.jsp에서 request.getParameter("name")을 실행하면  
기존 파라미터가 아닌 `<jsp:param>` 액션 태그로 추가한 파라미터 값을 사용하게 된다. 

body_sub.jsp에서 request.getParameter("name") 메서드를 실행하면 `<jsp:param>`으로 추가한 파라미터 값과 이미 존재한 파라미터 값을 모두 return한다.

즉, 위 그림에서 해당 메서드를 실행하면 ["최범균", "cbk"]를 파라미터 값 목록으로 return한다.

ex) body_main.jsp - `<jsp:param>`을 실행하기 전/후 파라미터 값에 변화가 발생하는지 여부를 확인해보자.

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head><title>INFO</title></head>
<body>

include 전 name 파라미터 값: <%= request.getParameter("name") %> # URL에서 전달한 name 파라미터 값을 출력할 것이다.

<hr>

<jsp:include page="body_sub.jsp" flush="false">
	<jsp:param name="name" value="최범균" /> # body_sub.jsp에 name 파라미터 값으로 "최범균"을 전달
                                          # 우선순위에 의해 목록은 [최범균, cbk] 순서로 출력될 것이다.
</jsp:include>

<hr/>

include 후 name 파라미터 값: <%= request.getParameter("name") %> # <jsp:param>에 의해서 파라미터값을 전달하지 않았기 때문에
                                                                # URL에서 전달한 name 파라미터 값을 출력한다.

</body>
</html>
```

- body_sub.jsp 
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
body_sub에서 name 파라미터 값: <%= request.getParameter("name") %>
<br/>
name 파라미터 값 목록:
<ul>
<%
	String[] names = request.getParameterValues("name"); #name 파라미터의 목록을 names 배열에 저장
	for (String name : names) {
%>
	<li> <%= name %> </li> # 파라미터의 값들을 하나씩 출력한다.
<%
	}
%>
</ul>
```

GET 방식으로 http://localhost:8080/chap07/body_main.jsp?name=cbk 라고 입력했다. 실행 결과는 아래와 같다.  
![image](https://user-images.githubusercontent.com/64796257/148735275-ff8b2cbe-668d-4129-9719-6e390884a008.png)

실행 결과를 보면 body_sub.jsp에서 request.getParameter("name")로 구한 파라미터 값이 `<jsp:param>`을 통해 새로 추가한 값임을 알 수 있다.  

그리고 request.getParameterValues("name")로 구한 파라미터 값 목록을 보면  
새로 추가한 파라미터 값("최범균")과 기존 파라미터 값("cbk")이 함께 포함된 것을 확인할 수 있다.

### 1.4 `<jsp:param>` 액션 태그와 캐릭터 인코딩

body_main.jsp를 보면 request.setCharacterEncoding() 메서드를 통해서 요청 파라미터의 캐릭터 셋을 지정하고 있는 것을 알 수 있다.

``` 
  request.setCharacterEncoding("utf-8");
```

`<jsp:param>` 액션 태그는 포함할 페이지에 전달할 요청 파라미터의 값을 인코딩할 때  
request.setCharacterEncoding() 메서드로 설정한 캐릭터 셋을 사용한다.

이를 알맞게 지정하지 않으면 `<jsp:param>`으로 설정한 값이 올바르게 전달되지 않는다.













