JSP로 간단한 웹 프로그램을 만들어보겠다. 현재 시간을 출력해주는 프로그램을 만들어보자.

<절차>
1. /apache-tomcat-8.5.73/webapps/chapter02 폴더 만들기
2. 편집기를 사용해서 chapter02 폴더에 time.jsp 파일 작성하기
3. 톰캣 실행
4. 웹 브라우저에 http://localhost:8080/chapter02/time.jsp 주소 입력
5. 결과 확인

폴더를 만들고 나서 time.jsp 파일을 작성하면 된다. 내용은 다음과 같다. 
```
<%@ page contentType = "text/html; charset=UTF-8" %>
<html>
<head>
<title> 현재 시간 </title>
  </head>
  <body>
  지금 = <%= new.java.util.Data() %>
  </body>
</html>
```

이렇게 작성하고 나서 chapter02 폴더에 파일을 저장한다. 
- 파일 형식은 `모든 파일`
- 인코딩은 `UTF-8`
- 이름은 `time.jsp`

위와 같은 방식으로 저장하고 나서 톰캣을 실행한다. 

톰캣을 실행하고 나서 http://localhost:8080/chapter02/time.jsp 를 실행하자.
<img src = " ">
