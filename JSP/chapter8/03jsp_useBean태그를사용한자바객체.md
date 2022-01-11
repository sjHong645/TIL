## 03 `<jsp:useBean>` 태그를 이용한 자바 객체 사용

JSP 페이지의 주된 기능은 데이터를 보여주는 기능이다. 게시판의 글 목록 보기, 글 읽기, 회원 정보 보기 등의 기능이 이에 해당한다.

JSP에서 이런 종류의 데이터들은 자바빈과 같은 클래스에 담아서 값을 보여주는 것이 일반적이다.

예를 들어, 회원 정보를 보여주어야 하는 경우 다음과 같이 회원 정보를 나타내는 자바빈 클래스의 객체를 사용해서 정보를 사용한다.

``` jsp
<% 
  MemberInfo mi = new MemberInfo();
  mi.setId("madvirus");
  mi.setName("최범균");
%>

이름 - <%= mi.getName() %>, 아이디 - <%= mi.getId() %>
```

JSP에서는 JSP 페이지에서 자주 사용하는 자바빈 객체를 위한 액션 태그를 제공하고 있다.  
대표적으로 `<jsp:useBean>`, `<jsp:setProperty>`, `<jsp:getProperty>` 액션 태그를 사용해서 JSP 페이지에서 자바빈 객체를 사용하는 방법에 대해 살펴보겠다.

### 1 `<jsp:useBean>` 액션 태그를 사용해서 객체 생성하기

`<jsp:useBean>` 액션 태그는 JSP 페이지에서 사용할 자바빈 객체를 지정할 때 사용한다. 기본 구문은 다음과 같다.

``` jsp
<jsp:useBean id= "[빈 이름]" class = "[자바빈 클래스 이름]" scope="[범위]" /> 
```

각 속성은 다음과 같다.
- id : JSP 페이지에서 자바빈 객체에 접근할 때 사용할 이름을 지정
- class : 패키지 이름을 포함한 자바빈 클래스의 완전한 이름 입력
- scope : 자바빈 객체를 저장할 영역 지정. page, request, session, application 중 하나의 값을 갖는다. 기본값은 page.

ex) 
``` jsp
<jsp:useBean id= "info" class = "chap08.member.MemberInfo" scope="request" /> 
```

위 코드는 MemberInfo 클래스의 객체를 생성해서 이름이 info인 변수에 할당한다. 

그리고 request 기본 객체의 "info" 속성의 값으로 생성된 객체를 저장한다. 즉, 다음과 비슷한 코드가 실행된다.

``` jsp
MemberInfo info = new MemberInfo(); # MemberInfo 클래스의 객체를 생성해서 이름이 info인 변수에 할당

request.setAttribute("info", info); # request 기본 객체의 "info" 속성의 값으로 생성된 객체를 저장
```

`<jsp:useBean>` 액션 태그는 지정한 영역에 이미 id 속성에서 지정한 이름의 속성값이 존재하면 객체를 새로 생성하지 않고 기존에 존재하는 객체를 그대로 사용한다.  
즉, 위에서 다룬 `<jsp:useBean>` 액션 태그의 내용을 다시쓰면 아래와 같다.

``` jsp
MemberInfo info = (MemberInfo)request.getAttribute("info"); 
# info 라는 이름의 속성값이 존재하지 않으면 MemberInfo의 info는 null이 된다.
# 그렇지 않으면 그대로 사용한다.

if(info == null) { 
  MemberInfo info = new MemberInfo(); # MemberInfo 클래스의 객체를 생성해서 이름이 info인 변수에 할당

  request.setAttribute("info", info); # request 기본 객체의 "info" 속성의 값으로 생성된 객체를 저장
}
```

`<jsp:useBean>` 액션 태그에서 중요한 점은 객체를 생성할 뿐만 아니라 지정한 영역에 저장하고 이미 영역에 객체가 존재한다면 그 객체를 사용한다는 것이다.

`<jsp:useBean>` 액션 태그의 scope 속성값에 따라 객체는 서로 다른 기본 객체에 저장된다. 
- page : pageContext 기본 객체
- request : request 기본 객체
- session : session 기본 객체
- application : application 기본 객체

`<jsp:useBean>` 액션 태그를 사용하면 영역별로 공유할 데이터를 쉽게 저장할 수 있다.

ex) makeObject.jsp - `<jsp:useBean>` 액션 태그를 사용해서 MemberInfo 객체를 생성한 후 `<jsp:forward>`를 사용해서 다른 페이지로 흐름을 이동시킨다.

``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<jsp:useBean id="member" scope="request" class="chap08.member.MemberInfo" />
# 이름이 member인 객체를 생성해서 request 기본 객체에 저장한다. 
# 클래스는 chap08/member 폴더에 있는 MemberInfo.java 파일 즉, MemberInfo 클래스를 사용한다.

<%
	member.setId("madvirus"); 
	member.setName("최범균"); # member 객체의 id는 "madvirus"로 name은 "최범균"으로 설정했다.
%>
<jsp:forward page="/useObject.jsp" /> # member 객체의 id, name을 설정하고 나서 useObject.jsp로 이동
```

이제 useObject.jsp는 `<jsp:useBean>` 액션 태그를 사용해서 makeObject.jsp가 request 기본 객체에서 생성한 객체인 member를 사용한다.  
코드는 아래와 같다.

- useObjcet.jsp
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<jsp:useBean id="member" scope="request" class="chap08.member.MemberInfo" />

# 앞서 makeObject.jsp에서 useObject.jsp로 forward 했다. 
# 이제 useObject.jsp는 <jsp:useBean> 액션 태그를 사용해서 makeObject.jsp가 생성한 객체인 member를 사용할 것이다.
# 여기서 request 기본 객체를 사용할 거라서 scope를 request로 설정했다.

# 이를 코드로 나타낸게 위에 있는 <jsp:useBean> 부분이다.
   
<html>
<head><title>인사말</title></head>
<body>

<-- member 객체의 name과 id값을 출력하도록 했다 -->
<%= member.getName() %> (<%= member.getId() %>) 회원님
안녕하세요.

</body>
</html>
```

이제 makeObject.jsp를 실행하면 아래와 같은 결과가 출력된다. 

![image](https://user-images.githubusercontent.com/64796257/148908045-58ad287f-04df-459c-9141-feec631fe3ec.png)

만약에 useObject.jsp를 사용한다면 request 기본 객체에 member 속성이 존재하지 않기 때문에 새로운 MemberInfo 클래스의 객체가 생성된다. 

이 경우에 객체의 property 값들이 변경되지 않았으니까 모두 null값이 될 것이다. 

`<jsp:useBean>` 액션 태그에서 class 속성 대신에 type 속성을 사용할 수도 있다.  
ex) 
``` jsp
<jsp:useBean id="member" scope="request" type="chap08.member.MemberInfo" />
```

차이점은 다음과 같다. 
만약에 request 기본 객체의 member 속성에 MemberInfo 객체가 존재하지 않는다면  

class 속성으로 지정했을 때는 새로운 MemberInfo 객체를 생성하지만  
type 속성으로 지정했을 때는 에러가 발생한다. 

type 속성으로 지정했을 때는 아래의 코드와 같은 의미를 갖는다.

``` jsp
MemberInfo info = (MemberInfo)request.getAttribute("info"); 


if(info == null) { 
  // 에러 발생
}
```

### 2 `<jsp:setProperty>` 액션 태그와 `<jsp:getProperty>` 액션 태그








