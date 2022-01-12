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


























