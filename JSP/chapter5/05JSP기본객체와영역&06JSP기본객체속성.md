## 05 JSP 기본 객체와 영역 

웹 어플리케이션은 다음 4가지 영역(scope)으로 구성된다. 

- PAGE 영역 : 하나의 JSP 페이지를 처리할 때 사용되는 영역
- REQUEST 영역 : 하나의 HTTP 요청을 처리할 때 사용되는 영역
- SESSION 영역 : 하나의 웹 브라우저와 관련된 영역
- APPLICATION 영역 : 하나의 웹 어플리케이션과 관련된 영역 

1. PAGE 영역

한 번의 클라이언트 요청에 대해서 JSP 페이지를 범위로 가진다.  
웹 브라우저의 요청이 오면 JSP 페이지를 실행하는데 이때 JSP 페이지를 실행하는 범위가 하나의 PAGE 영역이 된다. 

2. REQUEST 영역 

한 번의 웹 브라우저 요청과 관련된다.  
웹 브라우저의 주소에 URL을 입력하거나 링크를 클릭해서 페이지를 이동할 때, 웹 브라우저가 웹 서버에 전송하는 요청이 하나의 REQUEST 영역이 된다.  

웹 브라우저가 요청을 보낼때 마다 새로운 REQUEST 영역이 생성된다.  
PAGE 영역은 오직 하나의 JSP 페이지만을 포함하지만 REQUEST 영역은 하나의 요청을 처리하는데 사용하는 모든 JSP 페이지를 포함한다.

3. SESSION 영역 

하나의 웹 브라우저와 관련된 영역이다.  
세션이 생성되면 하나의 웹 브라우저와 관련된 모든 요청은 하나의 SESSION 영역에 포함된다.

4. APPLICATION 영역 

웹 어플리케이션과 관련된 전체 영역을 포함한다.

예를 들어, 이 장에서 사용하고 있는 /chap05 웹 어플리케이션에 포함된 모든 JSP 페이지, 
이 웹 어플리케이션을 사용하는 모든 요청 그리고 브라우저의 세션은 모두 하나의 APPLICATION 영역에 속하게 된다.

각각의 영역은 관련된 기본 객체를 가지고 있는데 관계는 다음과 같다.

- PAGE 영역 : pageContext 기본 객체
- REQUEST 영역 : request 기본 객체
- SESSION 영역 : session 기본 객체
- APPLICATION 영역 : application 기본 객체 

각 영역의 관계는 다음과 같이 표현할 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/148710468-4dfda99a-89a7-4c6a-a64e-25c4a0fb1069.png)

웹 브라우저의 요청을 처리하는 1,2,3,4번 페이지는 새로운 PAGE 영역에 속하고 그에 해당하는 pageContext 기본 객체를 가진다.

웹 브라우저에서 한 번의 요청은 하나의 request 기본 객체와 관련된다. 웹 브라우저가 결과를 받으면 그 요청과 관련된 request 기본 객체는 사라진다.

즉, 웹 브라우저가 요청을 할 때마다 새로운 request 기본 객체가 생성되고 새로운 REQUEST 영역이 생성되는 거다.

하나의 요청을 처리하는데 2개 이상의 JSP가 사용될 수도 있다.  
예를 들어, 웹 브라우저가 호출한 JSP 페이지가 다른 JSP를 include 하거나 forward 할 수 있는데 이 경우 두 JSP 페이지는 같은 요청 범위에 속하게 된다.  
즉, 같은 request 기본 객체를 공유하게 된다.

하나의 웹 브라우저는 하나의 세션과 관련된다.  
서로 다른 두 개의 웹 브라우저가 같은 JSP 페이지를 사용하더라도  
두 웹 브라우저는 서로 다른 SESSION 영역에 포함되며 서로 다른 session 기본 객체를 사용하게 된다.

모든 JSP는 1개의 application 기본 객체를 공유하며 application 기본 객체는 APPLICATION 영역에 포함된다.

## 06 JSP 기본 객체의 속성(Attribute) 사용하기

4개의 기본 객체인 pageContext, request, session, application은 속성을 갖는다. 

각 기본 객체가 존재하는 동안 기본 객체의 속성을 사용할 수 있다. 속성은 JSP 페이지 사이에서 정보를 주고받거나 공유하기 위한 목적으로 사용된다.

속성은 `<속성이름, 값>` 의 형태를 가진다. 4개의 기본 객체는 서로 다른 이름을 갖는 속성을 여러 개 포함할 수 있다. 

- 속성 처리 메서드  
| 메서드 | 리턴 타입 | 설명 | 
| --- | --- | --- | 
| setAttribute(String name, Object value) | void | 이름이 name인 속성의 값을 value로 지정한다 | 
| getAttribute(String name) | Object | 이름이 name인 속성의 값을 구한다. 존재하지 않으면 null을 리턴 | 
| removeAttribute(String name) | void | 이름이 name인 속성을 삭제한다 | 
| getAttributeNames() | `Enumeration<String>` | 속성의 이름 목록을 구한다.(pageContext 기본 객체는 이 메서드 제공안함) | 

ex. setApplication.jsp 

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%
	String name = request.getParameter("name"); // URL에서 전달하는 파라미터 name의 값을 name에 저장
	String value = request.getParameter("value"); // URL에서 전달하는 파라미터 value의 값을 value에 저장
	
	if (name != null && value != null) {
		application.setAttribute(name, value);
    // name과 value가 설정되었다면 설정된 값을 application 기본 객체의 속성으로 설정한다.
	}
%>

<html>
<head><title>application 속성 지정</title></head>
<body>
<%
	if (name != null && value != null) {
%>
application 기본 객체의 속성 설정:
 <%= name %> = <%= value %>
<%
	} else {
%>
application 기본 객체의 속성 설정 안 함
<%
	}
%>
</body>
</html>
```

다음과 같이 URL을 입력했다. 
```
http://localhost:8080/chap05/setApplicationAttribute.jsp?name=spongebob&value=squarepants
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/148711591-9dd4834f-3dfb-444c-b118-35caea8d724c.png)

이번엔 속성값을 보여주는 JSP 페이지를 작성해보겠다. 

ex. viewApplicationAttribute.jsp 

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.util.Enumeration" %>
<html>
<head><title>application 기본 객체 속성 보기</title></head>
<body>
<%
	Enumeration<String> attrEnum = application.getAttributeNames();
	while(attrEnum.hasMoreElements() ) {
		String name = attrEnum.nextElement();
		Object value = application.getAttribute(name);
%>
application 속성 : <b><%= name %></b> = <%= value %> <br>
<%
	}
%>
</body>
</html>
```

- 결과  

![image](https://user-images.githubusercontent.com/64796257/148711813-5c89e715-7020-4d68-aac8-25f5dcd1fa0d.png)

위와 같이 application 기본 객체에 저장된 모든 속성의 이름과 값이 출력된다.

출력 결과를 보면 setApplicationAttribute.jsp에서 설정한 application 기본 객체의 속성이 표시된 것을 확인할 수 있다. 

새로운 웹 브라우저를 열고서 viewApplicationAttribute.jsp 를 실행해도 같은 결과가 출력된다. 

이렇게 서로 다른 JSP 페이지와 서로 다른 웹 브라우저에서 동일한 application 기본 객체의 속성을 사용하는 이유는  
웹 어플리케이션 내에 있는 모든 JSP가 1개의 application 기본 객체를 공유하기 때문이다.

### 6.1 속성의 값 타입 

속성의 이름은 문자열을 나타내는 String 타입이지만, 값은 모든 클래스의 타입이 올 수 있다.  
기본 객체의 속성값을 지정하고 읽어오는 메서드는 다음과 같다. 

``` java
public void setAttribute(String name, Object value)
public Object getAttribute(String name)
```

setAttribute 메서드의 value 파라미터의 타입은 Object이고 getAttribute 메서드의 return 타입도 Object이다.

이는 모든 클래스 타입의 속성 값을 사용할 수 있다는 것을 의미한다.

ex) 
``` java
session.setAttribute("session_start", new java.util.Data());
session.setAttribute("memberid", "madvirus");
application.setAttribute("application_temp", new File("c:\\temp")); 
```

위 코드는 차례대로 Date, String, File 타입의 객체를 속성값으로 사용했다. 
이렇게 다양한 타입의 값을 속성값으로 지정할 수 있으므로 getAttribute() 메서드를 사용해서 속성의 값을 읽을 때에는  
아래와 같이 속성값을 지정할 때 사용한 타입으로 알맞게 변환해야 한다. 

``` java 
Data date = (Date)Session.getAttribute("session_start");

String memberID = (String)session.getAttribute("memberid");

File tempDir = (File)application.getAttribute("application_temp");
```

기본 데이터 타입의 값은 Wrapper 타입을 사용해야 한다. ex. int 값을 사용하고 싶으면 Integer를 사용해야 한다.

ex) 

``` java
request.setAttribute("age", new Integer(20));  // age라는 변수를 Integer 타입 20으로 설정함.

Integer ageAttr = (Integer) request.getAttribute("age"); // age의 속성값을 ageAttr에 저장

int ageValue = ageAttr.intValue(); // ageAttr의 값은 Integer 형이어서 int형 으로 바꿔서 값을 ageValue에 저장

```

자바는 `기본 데이터 타입`과 `Wrapper 타입` 간의 변환을 자동으로 처리하는 Auto boxing/Auto unboxing 기능을 제공한다.  
그래서 setAttribute() 메서드에 기본 데이터 타입의 값을 전달하거나  
getAttribute()에서 Wrapper 타입으로 읽어온 값을 기본 데이터에 할당 할 수 있다.

``` java

// int 값 20이 자동으로 Integer로 변환되서 속성값으로 저장 

request.setAttribute("age", 20); 

// Integer 타입의 값이 자동으로 int 타입으로 변환됨

int age = (Integer) request.getAttribute("age"); 

```

### 6.2 속성의 활용 

기본 객체별 속성의 용도는 아래와 같다. 

| 기본 객체 | 영역 | 쓰임새 | 
| --- | --- | --- | 
| pageContext | PAGE | (한 번의 요청을 처리하는) 하나의 JSP 페이지 내에서 공유할 값을 저장한다. 주로 커스텀 태그에서 새로운 변수를 추가할 때 사용한다.  | 
| request | REQUEST | 한 번의 요청을 처리하는데 사용되는 모든 JSP 페이지에서 공유할 값을 저장한다. 주로 하나의 요청을 처리하는데 사용하는 JSP 페이지 사이에서 정보를 전달하기 위해 사용된다.  | 
| session | SESSION | 한 사용자와 관련된 정보를 JSP 사이에 공유하기 위해 사용한다. 사용자의 로그인 정보와 같은 것들을 저장한다. | 
| application | APPLICATION | 모든 사용자를 위해 공유할 정보를 저장한다. 임시 폴더 경로와 같이 웹 어플리케이션의 설정 정보를 주로 저장한다 | 

속성을 저장하기 위해 가장 많이 사용되는 객체는 request 기본 객체와 session 기본 객체이다.  

MVC(Model-View-Controller) 패턴에 기반해서 웹 어플리케이션을 개발할 때 request 기본 객체의 속성을 자주 사용한다. 

session 기본 객체 속성은 로그인, 로그아웃과 같이 사용자의 인증정보를 저장할 때 사용한다. 

















