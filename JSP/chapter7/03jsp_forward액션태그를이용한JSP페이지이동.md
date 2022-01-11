`<jsp:forward>` 액션 태그는 하나의 JSP 페이지에서 다른 JSP 페이지로 요청 처리를 전달할 때 사용한다. 

어떻게 요청 흐름이 이동하는지 그림을 통해 보자.

![image](https://user-images.githubusercontent.com/64796257/148868465-b4c318b9-445c-44f4-af5c-c94056252480.png)

from.jsp에서 to.jsp로 요청 흐름이 이동한 것을 볼 수 있다. 
웹 브라우저의 요청을 최초로 전달받은 것은 from.jsp인데 `<jsp:forward>` 액션 태그로 인해 다음과 같은 순서로 실행흐름이 이동한다.

1. 웹 브라우저의 요청을 from.jsp에 전달한다.
2. from.jsp는 `<jsp:forward>` 액션 태그를 실행한다.
3. `<jsp:forward>` 액션 태그를 실행하면 요청 흐름이 to.jsp로 이동한다
4. 요청 흐름이 이동할 때 from.jsp에서 사용한 request 기본 객체와 response 기본 객체가 to.jsp로 전달된다.
5. to.jsp가 응답 결과를 생성한다.
6. to.jsp가 생성한 결과가 웹 브라우저에 전달된다

이러한 흐름에서 다음 사실이 중요하다.
- from.jsp가 아닌 to.jsp가 생성한 응답 결과가 웹 브라우저에 전달된다는 사실
- from.jsp에서 사용한 request, response 기본 객체를 to.jsp에 그대로 전달한다는 사실

cf) 여기서 from.jsp에서 to.jsp로 요청 흐름이 이동하면서 to.jsp에서 처리하는 이유는 간결하고 구조적으로 웹/JSP 프로그래밍을 하고 싶기 때문이다.  

웹 어플리케이션을 개발하다 보면 다양한 조건에 따라 다른 처리를 해야 하는 상황이 발생하는데 
이럴 때 `<jsp:forward>` 액션 태그를 사용하면 각 조건을 처리하는 JSP를 분리해서 기능별로 모듈화할 수 있게 된다.

### 1 `<jsp:forward>` 액션 태그의 사용법
- 기본 문법

``` jsp
  <jsp:forward page="이동할 페이지" />
```

이동할 페이지는 웹 어플리케이션 내에서의 경로다. 다음과 같이 값을 지정하거나 표현식의 결과를 값으로 사용할 수 있다.

``` jsp
<jsp:forward page="/to/to.jsp" />

<% 
  String uri = "/to/to.jsp"; 
%>

<jsp:forward page="<%= uri %>" />
```

ex) from.jsp & to.jsp 
- from.jsp 

``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<%--
	<jsp:foward> 액션 태그를 실행하면 from.jsp에서 생성했던 출력 결과는 모두 제거된다.
  
  이에 대한 내용은 뒤에서 다룬다
--%>
<html>
<head><title>from.jsp의 제목</title></head>
<body>

이 페이지는 from.jsp가 생성한 것입니다.

<jsp:forward page="/to/to.jsp" />

</body>
</html>
```

- to.jsp
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>to.jsp의 실행 결과</title></head>
<body>

이 페이지는 to.jsp가 생성한 것입니다.

</body>
</html>
```

- from.jsp 실행 결과 

![image](https://user-images.githubusercontent.com/64796257/148869405-64bf2528-0f0e-483e-a9d6-56a4c51a80a2.png)

실행 결과를 통해 다음과 같은 내용을 알 수 있다.
- from.jsp에서 `<jsp:forward>`를 사용해서 이동한 to.jsp가 생성한 결과가 웹 브라우저에 출력된다.
- 웹 브라우저의 주소는 from.jsp 그대로이기 때문에 to.jsp로 redirect 하지 않았다는 것을 알 수 있다.

웹 브라우저 주소는 변경되지 않았으므로 웹 브라우저는 from.jsp가 생성한 결과로 인식한다.  
하지만, 실제 출력 결과는 to.jsp가 생성한 것이다.

### 2 `<jsp:forward>` 액션 태그와 출력 버퍼의 관계

그렇다면 왜 `<jsp:forward>` 액션 태그를 사용했던 from.jsp의 출력 결과는 웹 브라우저에 전송되지 않았을까?  
이는 `출력 버퍼` 때문이다.

`<jsp:forward>`가 실행되면 아래 그림과 같이 출력 버퍼의 내용을 버리고 이동한 페이지의 출력 결과를 출력 버퍼에 저장하게 된다.

![image](https://user-images.githubusercontent.com/64796257/148869829-f9c14b6d-c07f-444c-96eb-3912f9e696fc.png)

`<jsp:forward>`를 실행하기 전에 위 그림과 같이 출력 버퍼를 비우기 때문에  
`<jsp:forward>`를 실행하기 이전에 저장했던 내용은 웹 브라우저에 전송되지 않는다.

물론 `<jsp:forward>` 액션 태그 뒤에 있던 코드들은 실행조차 되지 않는다. 아예 다른 파일로 넘어갔기 때문이다.

`<jsp:forward>` 액션 태그가 올바르게 동작하기 위해서는 `<jsp:forward>` 액션 태그를 실행하기 전에 웹 브라우저에 데이터가 전송되면 안 된다.  
만약 출력 버퍼를 flush 한 이후에 `<jsp:forward>`를 실행하면 실행 흐름을 이동하는데 실패한다.
이와 유사하게 buffer 속성을 none으로 해서 버퍼를 사용하지 않는 경우에도 `<jsp:forward>` 액션 태그는 제대로 동작하지 않는다.
해당 내용은 JSP 규약에 따라 정의된 내용이다.


















