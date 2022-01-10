## 05. Exception 타입별로 에러 페이지 지정

JSP 페이지에서 발생하는 Exception 종류별로 에러 페이지를 지정할 수도 있다. `<error-code>` 태그 대신에 `<exception-type>` 태그를 사용하면 된다.

``` jsp 
<error-page>
  <exception-type>java.lang.NullPointerException</exception-type> 
  
  <location>/error/errorNullPointer.jsp </location>
</error-page>
```

위 코드는 JSP 페이지에서 NullPointerException이 발생한 경우 /error/errorNullPointer.jsp를 에러 페이지로 보여준다는 것을 의미한다.

errorNullPointer.jsp는 아래와 같이 작성했다. 

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>NULL 에러 발생</title></head>
<body>

<strong>서비스 처리 과정에서 널(NULL) 에러가 발생하였습니다.</strong>

</body>
</html>
``` 

여기서 readParamter2.jsp를 실행해보겠다. 

- readParamter2.jsp 
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>파라미터 출력</title></head>
<body>

name 파라미터 값: <%= request.getParameter("name").toUpperCase() %>

</body>
</html>
```

여기서 readParameter2.jsp를 실행하는데 name 파라미터를 전달하지 않았다. 그러면 NullPointerException이 발생한다.  
그렇다면 위에서 작성한 errorNullPointer.jsp에 의해서 아래와 같은 화면이 출력된다.

![image](https://user-images.githubusercontent.com/64796257/148724994-720af52c-7eae-4a8a-b981-c0b8a5877495.png)


