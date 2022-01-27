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
MemberInfo info = new MemberInfo(); <%-- MemberInfo 클래스의 객체를 생성해서 이름이 info인 변수에 할당 --%>

request.setAttribute("info", info); <%-- request 기본 객체의 "info" 속성의 값으로 생성된 객체를 저장 --%>
```

`<jsp:useBean>` 액션 태그는 지정한 영역에 이미 id 속성에서 지정한 이름의 속성값이 존재하면 객체를 새로 생성하지 않고 기존에 존재하는 객체를 그대로 사용한다.  
즉, 위에서 다룬 `<jsp:useBean>` 액션 태그의 내용을 다시쓰면 아래와 같다.

``` java
MemberInfo info = (MemberInfo)request.getAttribute("info"); 
// info 라는 이름의 속성값이 존재하지 않으면 MemberInfo의 info는 null이 된다. 
// 그렇지 않으면 그대로 사용한다. 

if(info == null) { 
  MemberInfo info = new MemberInfo(); // MemberInfo 클래스의 객체를 생성해서 이름이 info인 변수에 할당

  request.setAttribute("info", info); // request 기본 객체의 "info" 속성의 값으로 생성된 객체를 저장
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
<%-- 이름이 member인 객체를 생성해서 request 기본 객체에 저장한다. --%>
<%-- 클래스는 chap08/member 폴더에 있는 MemberInfo.java 파일 즉, MemberInfo 클래스를 사용한다. --%>

<%
	member.setId("madvirus"); 
	member.setName("최범균"); // member 객체의 id는 "madvirus"로 name은 "최범균"으로 설정했다.
%>
<jsp:forward page="/useObject.jsp" /> <%-- member 객체의 id, name을 설정하고 나서 useObject.jsp로 이동 --%>
```

이제 useObject.jsp는 `<jsp:useBean>` 액션 태그를 사용해서 makeObject.jsp가 request 기본 객체에서 생성한 객체인 member를 사용한다.  
코드는 아래와 같다.

- useObjcet.jsp
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<jsp:useBean id="member" scope="request" class="chap08.member.MemberInfo" />

<%-- 앞서 makeObject.jsp에서 useObject.jsp로 forward 했다. --%>
<%-- 이제 useObject.jsp는 <jsp:useBean> 액션 태그를 사용해서 makeObject.jsp가 생성한 객체인 member를 사용할 것이다. --%>
<%-- 여기서 request 기본 객체를 사용할 거라서 scope를 request로 설정했다. --%>

<%-- 이를 코드로 나타낸게 위에 있는 <jsp:useBean> 부분이다. --%>
   
<html>
<head><title>인사말</title></head>
<body>

<%-- member 객체의 name과 id값을 출력하도록 했다 --%>
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

`<jsp:setProperty>` 액션 태그를 사용하면 생성한 자바빈 객체의 property 값을 변경할 수 있다.  
`<jsp:setProperty>` 액션 태그의 구문은 다음과 같다.

``` jsp
  <jsp:setProperty name = "[자바빈]" property = "[이름]" value = "[값]" /> 
```

`<jsp:setProperty>` 액션 태그의 각 속성은 다음과 같은 의미를 가진다.

- name : property 값을 변경할 자바빈 객체의 이름을 지정한다. `<jsp:useBean>` 액션 태그의 id 속성에서 지정한 값을 사용한다.
- property : 값을 지정할 property 값을 지정한다.
- value : property 값을 지정한다. 표현식(`<%= 값 %>`)이나 EL(${값}) 을 사용할 수 있다.

ex) 자바빈 객체의 name 프로퍼티의 값을 "최범균"으로 지정하고 싶다.

``` jsp 
<jsp:useBean id = "member" class="chap08.member.MemberInfo" /> 
<%-- 이름이 member인 MemberInfo 형 객체를 생성했다 --%>

<jsp:setProperty name = "member" property = "name" value = "최범균" /> 
<%-- member 객체의 name이라는 프로퍼티가 있는데 그 값을 "최범균"으로 설정했다 --%>
```

value 대신 param 속성을 사용할 수도 있다. param 속성은 파라미터 값을 프로퍼티 값으로 지정할 때 사용된다.

ex) memberId 파라미터의 값을 자바빈 객체의 id 프로퍼티의 값으로 지정하고 싶다.

``` jsp 
<jsp:setProperty name = "member" property = "id" param = "memberId" /> 
```

property 속성의 값을 `*`로 지정하면 각 프로퍼티의 값을 같은 이름을 갖는 파라미터의 값으로 설정한다.

ex) 
``` jsp
  <jsp:useBean id = "member" class = "chap08.member.MemberInfo" />
  <jsp:setProperty name = "member" property = "*" />
```

name 요청 파라미터와 email 요청 파라미터가 있다고 하자.  

이 경우 위 코드는 name 파라미터의 값을 naem 프로퍼티 값으로 설정하고 email 파라미터 값을 email 프로퍼티 값으로 지정한다. 

이 기능은 폼에 입력한 값을 자바빈 객체에 담을 때 유용하게 사용할 수 있다. 예제는 뒤에서 살펴보겠다.

`<jsp:getProperty>` 액션 태그는 자바빈 객체의 프로퍼티 값을 출력할 때 사용한다. 구문은 아래와 같다.
``` jsp 
<jsp:getProperty name = "자바빈이름" property = "프로퍼티이름" />
``` 

각 속성은 다음과 같다. 
- name : `<jsp:useBean>`의 id 속성에서 지정한 자바빈 객체의 이름을 지정한다
- property : 출력할 프로퍼티의 이름을 지정한다.

ex) 
``` jsp 
<jsp:getProperty name = "member" property = "name" />
``` 

member라는 자바빈 객체에서 name이라는 이름을 가진 프로퍼티의 값을 return 한다.

ex) 회원가입 양식 폼을 만드는 jsp 

- membershipForm.jsp : 회원 가입 양식을 보여주는 JSP 페이지 
``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>회원가입 입력 폼</title></head>
<body>
<form action="/chap08/processJoining.jsp" method="post">
<table border="1" cellpadding="0" cellspacing="0">
<tr>
	<td>아이디</td>
	<td colspan="3"><input type="text" name="id" size="10"></td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="name" size="10"></td>
	<td>이메일</td>
	<td><input type="text" name="email" size="10"></td>
</tr>
<tr>
	<td colspan="4" align="center">
	<input type="submit" value="회원가입">
	</td>
</tr>
</table>
</form>
</body>
</html>
```
- 결과 창 

![image](https://user-images.githubusercontent.com/64796257/149043786-307cb119-84af-4b20-82dd-1cd3e25c5f47.png)

[회원가입] 버튼을 누르면 입력한 데이터를 POST 방식으로 proecssJoining.jsp에 전달한다.  
proecssJoining.jsp에 전달한 데이터를 MemberInfo 자바빈 클래스의 객체에 저장해서 화면에 출력한다.

- processJoining.jsp : [회원가입] 버튼을 눌렀을 때 나오는 JSP 페이지

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	// 읽어올 파라미터의 캐릭터 인코딩을 UTF-8로 지정함
%>
<jsp:useBean id="memberInfo" class="chap08.member.MemberInfo" />
<%-- MemberInfo 형 자바빈 클래스르 memberInfo 라는 이름의 객체로 생성했다. --%>

<jsp:setProperty name="memberInfo" property="*" />
<%-- 파라미터의 값을 memberInfo 객체의 프로퍼티로 저장할 거다. --%>
<%-- 즉, membershipForm.jsp에서 전달한 id, name, email 파라미터의 값을 memberInfo 객체의 id, name, email 프로퍼티로 설정했다. --%>

<jsp:setProperty name="memberInfo" property="password"
				 value="<%= memberInfo.getId() %>" />
<%-- memberInfo 객체에 password라는 프로퍼티가 있는데 그 값을 <%= memberInfo.getId() %>로 지정한다 --%>
<%-- 위에서 정의한 membershipForm.jsp에는 비밀번호를 설정하는 부분이 따로 없어서 --%>
<%-- 아이디와 비밀번호를 똑같이 설정한다고 가정했을 때 이와 같은 코드를 작성할 수 있다. --%>
				 
<html>
<head><title>가입</title></head>
<body>

<table width="400" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td>아이디</td>
	<td><jsp:getProperty name="memberInfo" property="id" /></td>
	<%-- memeberInfo에 id라는 프로퍼티의 값을 return해줌 --%>
	
	<td>암호</td>
	<td><jsp:getProperty name="memberInfo" property="password" /></td>
	<%-- memeberInfo에 password라는 프로퍼티의 값을 return해줌 --%>
</tr>
<tr>
<tr>
    <td>이름</td>
    <td><jsp:getProperty name="memberInfo" property="name" /></td>
    <%-- memeberInfo에 name 이라는 프로퍼티의 값을 return해줌 --%>
    
    <td>이메일</td>
    <td><jsp:getProperty name="memberInfo" property="email" /></td>
    <%-- memeberInfo에 email 이라는 프로퍼티의 값을 return해줌 --%>
    
</tr>
</table>

</body>
</html>
```

membershipForm.jsp에서 실행한 화면에 값을 입력하면 그에 맞는 결과화면이 출력된다.  

![image](https://user-images.githubusercontent.com/64796257/149045131-75ad1bd5-a72c-4ef5-bbc1-215112e6ff22.png)

![image](https://user-images.githubusercontent.com/64796257/149045147-0eaba275-5281-4d8f-b5ac-50db9d9b319f.png)

이번 예제를 통해 `<jsp:setProperty>` 액션 태그를 사용함으로써 요청 파라미터의 값을 간단하게 자바빈 객체의 프로퍼티에 저장할 수 있다는 것을 알 수 있다.

그렇지 않으면 여러 줄에 걸쳐 처리해야 하기 때문에 `<jsp:setProperty>` 액션 태그를 사용할 수 있도록  
파라미터의 이름과 프로퍼티의 이름을 맞춰줘야 한다.

### 3 자바빈 프로퍼티 타입에 따른 값 매핑 

ex) 자바빈 프로퍼티 타입이 int인 경우 

``` jsp
<jsp:setProperty name = "someBean" property = "width" value = "100" />
```

width 프로퍼티의 타입이 int라면 value에 입력한 값 "100"을 int 타입으로 변환해서 저장한다. 이와 같이 값에 있는 타입을 알아서 변환해준다.

cf) 스프링 프레임워크처럼 MVC를 지원하는 프레임워크의 도입과 표현 언어(EL)의 사용으로  
`<jsp:useBean>` 액션 태그, `<jsp:getProperty>` 액션 태그, `<jsp:setProperty>` 액션 태그를 잘 사용하지 않는다.










