웹 브라우저에 정보를 보관할 때 쿠키를 사용한다면, 세션(session)은 웹 컨테이너에 정보를 보관할 때 사용한다.

세션은 오직 서버에서만 생성된다. 기본적인 개념은 아래 그림과 같다.

![image](https://user-images.githubusercontent.com/64796257/149153437-17d20aee-ddf6-48c5-ad08-b2efa0ad2b32.png)

웹 컨테이너는 기본적으로 하나의 웹 브라우저마다 하나의 세션을 생성한다.  
예를 들어, 위 그림에서 JSP1과 JSP2가 세션을 사용한다고 하자.  

이때, 웹 브라우저 1이 JSP1과 JSP2를 실행하면 웹 브라우저 1과 관련된 세션1을 사용한다. 웹 브라우저 2가 JSP1과 JSP2를 실행하면 웹 브라우저 2와 관련된 세션2를 사용한다. 

즉, 같은 JSP 페이지라도 웹 브라우저에 따라 서로 다른 세션을 사용한다.

이와 같이 웹 브라우저마다 세션이 따로 존재하기 때문에 세션은 웹 브라우저와 관련된 정보를 저장하기에 알맞다.  
즉, 쿠키가 클라이언트 쪽의 데이터 저장소라면 세션은 서버 쪽의 데이터 저장소라고 할 수 있다.

### 1 세션 생성

JSP에서 세션을 생성하려면 아래와 같이 page 디렉티브의 session 속성을 `true`로 지정하면 된다.
``` jsp
<%@ page contentType = ... %>
<%@ page session = "true" %>
<% 
  session.setAttribute("userInfo", userInfo);
%>
```

여기서 page 디렉티브의 session 속성은 기본값이 true이기 때문에 별도로 "false"를 지정하지 않는 이상 세션은 기본적으로 생성된다.  
세션이 생성되면 session 기본 객체를 통해서 세션을 사용할 수 있다.

아래 그림과 같이 세션을 사용하는 서버 프로그램에 웹 브라우저가 처음 접속할 때 세션을 생성하고 이후에는 이미 생성한 세션을 사용한다.

![image](https://user-images.githubusercontent.com/64796257/149154602-4b85482c-b478-449a-81f7-6d880869e4ce.png)

### 2 session 기본 객체 

세션을 사용한다는 건 session 기본 객체를 사용한다는 것을 의미한다.  

session 기본 객체는 request 기본 객체와 마찬가지로 속성을 제공하므로 setAttribute(), getAttribute() 등의 메서드를 사용해서 속성값을 저장하거나 읽어온다.  
추가적으로 세션은 세션만의 고유 정보를 제공하며 이러한 정보를 구할 때 사용하는 메서드는 아래 표와 같다.

| 메서드 | 리턴 타입 | 설명 | 
| --- | --- | --- | 
| getId() | String | 세션의 고유 ID(세션 ID)를 구한다 | 
| getCreationTime() | long | 세션이 생성된 시간을 구한다(1970.1.1 이후 흘러간 시간/ 단위는 1/1000초) | 
| getLastAccessedTime() | long | 웹 브라우저가 가장 마지막에 세션에 접근한 시간을 구한다(단위와 시간은 위와 동일) | 

앞에서 웹 브라우저마다 별도의 세션을 갖는다고 했다.

이때 각 세션을 구분하기 위해 세션마다 고유 ID를 할당하는데 그 아이디를 세션 ID라고 한다. 웹 서버는 웹 브라우저에 세션 ID를 전송한다.  
웹 브라우저는 웹 서버에 연결할 때 마다 매번 세션 ID를 보내서 웹 서버가 어떤 세션을 사용할지 판단할 수 있게 된다.

웹 서버는 세션 ID를 이용해서 웹 브라우저를 위한 세션을 찾기 때문에 웹 서버와 웹 브라우저는 세션 ID를 공유할 수 있는 방법이 필요하다.

이를 위해 사용하는 방법이 쿠키이다. 쿠키 목록 중에 이름이 JSESSIONID인 쿠키가 있는데 이 부분이 바로 세션 ID를 공유할 때 사용하는 쿠키이다.

ex) sessionInfo.jsp : 현재 사용 중인 세션 정보를 보여주는 JSP 페이지

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page session = "true" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	Date time = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<html>
<head><title>세션정보</title></head>
<body>
세션ID: <%= session.getId() %> <br> # 세션 ID를 출력
<%
	time.setTime(session.getCreationTime()); # 세션이 생성된 시간 출력
%>
세션생성시간: <%= formatter.format(time) %> <br>
<%
	time.setTime(session.getLastAccessedTime()); # 세션의 마지막 접근 시간 출력
%>
최근접근시간: <%= formatter.format(time) %>

</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/149156381-26107888-f6e6-440b-8d39-3d474c086c47.png)

sessionInfo.jsp를 실행하면 위 그림과 같이 생성된 세션 정보를 출력한다. 세션 ID는 세션이 새로 생성될 때마다 새로운 값을 사용한다.

한 번 더 session.jsp를 실행해보겠다.

![image](https://user-images.githubusercontent.com/64796257/149156811-f766ddb3-0cef-4f7c-9252-764aafed83c9.png)

세션 생성 시간과 최근 접근 시간이 달라진 것을 확인할 수 있다.

### 3 기본 객체의 속성 사용 

한 번 생성된 세션은 지정한 유효시간 동안 유지된다.  
따라서, 웹 어플리케이션을 실행하는 동안 지속적으로 사용해야 하는 데이터의 저장소로 세션이 적당하다.

request 기본 객체가 하나의 요청을 처리하는데 사용되는 JSP 페이지 사이에 공유된다면,  
session 기본 객체는 웹 브라우저의 여러 요청을 처리하는 JSP 페이지 사이에서 공유된다.

따라서, 로그인한 회원 정보 등 웹 브라우저와 일대일로 관련된 값을 저장할 때에는 쿠키 대신 세션을 사용할 수 있다.

세션에 값을 저장할 때는 속성을 사용한다. 속성에 값을 저장하려면 `setAttribute() 메서드`를 사용하고 속성값을 읽으려면 `getAttribute() 메서드`를 사용한다.

ex) setMemberInfo.jsp : 사용자 정보 중 하나인 회원 아이디와 이름을 저장하는 코드

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	# session의 속성에 정보를 저장했다.
	
	session.setAttribute("MEMBERID", "madvirus");
	session.setAttribute("NAME", "최범균");
%>
<html>
<head><title>세션에 정보 저장</title></head>
<body>

세션에 정보를 저장하였습니다.

</body>
</html>
```

setMemberInfo.jsp를 사용하고 나면 session 기본 객체에 저장한 두 속성을 사용할 수 있게 된다. 

이와 같이 session 기본 객체를 설정하고 나면 세션이 종료될 때까지 다음과 같이 속성값을 사용할 수 있게 된다.

``` jsp 
<% 
  String name = (String)session.getAttribute("NAME"); 
  # 이와 같이 작성하면 NAME이라는 이름을 가진 세션의 속성을 사용할 수 있다.
  # 여기서는 "최범균" 이라는 값이 name에 저장된다.
%>
```

### 4 세션 종료

세션을 유지할 필요가 없다면 session.invalidate() 메서드를 사용해서 세션을 종료한다.  
세션을 종료하면 현재 사용 중인 session 기본 객체를 삭제하고 session 기본 객체에 저장했던 속성 목록도 함께 삭제한다.

ex) closeSession.jsp 
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	session.invalidate();
%>
<html>
<head><title>세션 종료</title></head>
<body>

세션을 종료하였습니다.

</body>
</html>
```

세션을 종료하면 기존 session 객체를 제거하고 세션을 종료한 후 다음 요청에서 session을 사용한다면 새로운 session 기본 객체를 생성한다.

1) seesionInfo.jsp 실행 - 새로운 세션 생성

![image](https://user-images.githubusercontent.com/64796257/149247254-7c2e780c-701f-4c9c-b9ed-c0f5040169db.png)

2) closeSession.jsp 실행 - 세션 종료

![image](https://user-images.githubusercontent.com/64796257/149247556-646fdd56-8077-4bae-bc6e-24d48dc34ced.png)

3) seesionInfo.jsp 실행 - 다시 새로운 세션 생성

![image](https://user-images.githubusercontent.com/64796257/149247612-c6072934-cc81-44ef-a921-3c434d9613db.png)

### 5 세션 유효 시간

세션은 최근 접근 시간을 갖는다. session 기본 객체를 사용할 때마다 세션의 최근 접근 시간이 갱신된다. 

이를 알아내기 위해서 사용한 메서드가 `session.getLastAccessedTime()` 이다.

`session.getLastAccessedTime()` 메서드는 최근에 session 기본 객체에 접근한 시간을 나타낸다.

세션은 마지막 접근 이후 일정 시간 이내에 다시 세션에 접근하지 않는 경우 자동으로 세션을 종료하는 기능을 갖고 있다.  
예를 들어, 세션의 유효 시간이 30분이라고 해보자.

아래 그림과 같이 마지막 접근 시간에서 30분이 지나면 자동으로 세션을 종료하고 이후 세션을 요청하면 새로운 세션을 생성한다.

![image](https://user-images.githubusercontent.com/64796257/149248049-b9c69ccd-8336-47d1-92a9-382f7c1f8fa0.png)

세션 유효 시간은 2가지 방법으로 설정할 수 있다.  

1) WEB-INF\web.xml 파일에 아래와 같이 `<session-config>` 태그를 사용해서 세션 유효 시간을 지정하는 방법

``` xml
<?xml version="1.0" encoding="euc-kr"?>

...

  <session-config>
	  <session-timeout> 50 </session-timeout>
	  # 세션의 타임아웃 시간을 50분으로 설정했다.
	  
  <session-config>
...

```

2) session 기본 객체가 제공하는 setMaxInactiveInterval() 메서드를 사용하는 방법

세션의 유효시간을 60분으로 지정하고 싶다면 다음과 같이 메서드를 사용하면 된다.

``` jsp 
<% 
   session.setMaxInactiveInterval(60 * 60); 
   # 초 단위로 유효시간을 설정한다.
%>
```

유효시간을 0 또는 음수로 설정한다면 session.invalidate() 메서드를 호출하기 전까지 세션 객체는 서버에 유지된다.  

즉, 유효시간이 없는 상태에서 session.invalidate() 메서드가 실행되지 않는다면 세션 객체는 계속 메모리에 남게 되어 메모리 부족 현상이 발생한다.

![image](https://user-images.githubusercontent.com/64796257/149248648-a4266a25-08f5-4519-ab16-84c5c16af5fe.png)

### 6 request.getSession() 을 이용한 세션 생성

HttpSession을 생성하는 또 다른 방법은 request 기본 객체의 getSession() 메서드를 사용하는 것이다.

request.getSession() 메서드는 현재 요청과 관련된 session 객체를 return한다.

ex) request.getSession()을 이용해서 세션을 구하므로 page 디렉티브의 session 속성값은 false로 지정한다.
``` jsp
<%@ page session = "false" %>
<%
   HttpSession httpSession = request.getSession(); # session이 존재하면 해당 session을 return하고 
   						   # 존재하지 않으면 새롭게 session을 생성해서 return한다.
   List list = (List)httpSession.getAttribute("list");
   list.add(productId);
%>
```

세션이 생성된 경우에만 session 객체를 구하고 싶다면 request.getSession(false)를 실행하면 된다. (만약에 세션이 없다면 null을 return한다)


### 세션에 여러 속성을 사용해서 연관 정보를 저장할 때

이 경우에 문제점을 줄일 수 있는 방법으로 클래스를 사용하는 방법이 있다. 예를 들어 아래와 같이 회원과 관련된 정보를 클래스에 묶어서 저장한다고 하자.
``` java
public class MemberInfo { 

  private String id;
  private String name;
  private String email;
  private boolean male;
  private int age;

} 
```

연관된 정보를 클래스로 묶어서 저장하면 각 정보를 개별 속성으로 저장하지 않고 다음과 같이 1개의 속성을 이용해서 저장할 수 있게 된다.

``` jsp
<% 
  MemberInfo memberInfo = new MemberInfo(id, name);
  session.setAttribute("memberInfo", memberInfo); # 위에서 지정한 memberInfo라는 이름의 객체를 
  						  # "memberInfo"라는 이름의 세션의 속성으로 저장했다
%>
```

연관된 정보를 한 객체에 담아서 저장하기 때문에 세션에 저장한 객체를 사용할 때도 다음과 같이 객체를 가져온 뒤 객체로부터 필요한 값을 읽어올 수 있다.

``` jsp
<% 
  MemberInfo memberInfo = (MemberInfo) seesion.getAttribute("memberInfo");
  
  # "memberInfo"라는 세션으로부터 속성값을 불러온다. 앞서 이 값은 MemberInfo 형으로 지정된 값이었다.
%>
```










