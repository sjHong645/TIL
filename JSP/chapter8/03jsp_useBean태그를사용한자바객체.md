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
<jsp:useBean id=
```
