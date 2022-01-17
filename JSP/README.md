# JSP 2.3 웹프로그래밍 책 정리

## Part 1 웹 프로그래밍 기초

### Chapter 2. 웹 프로그래밍 기초
[01 웹과 웹 프로그래밍](/JSP/chapter2/01웹과웹프로그래밍.md)

### Chapter 3. JSP로 시작하는 웹 프로그래밍

[01 JSP에서 HTML 문서를 생성하는 기본 코드 구조](/JSP/chapter3/01JSP에서HTML문서생성하는기본코드구조.md)

[02 JSP 페이지의 구성 요소](/JSP/chapter3/02JSP페이지구성요소.md)

[03 page 디렉티브](/JSP/chapter3/03page디렉티브.md)

[04 스크립트 요소](/JSP/chapter3/04스크립트요소.md)

[05 request 기본 객체](/JSP/chapter3/05request기본객체.md)

[06 response 기본 객체 & 07 JSP 주석](/JSP/chapter3/06response기본객체&07JSP주석.md)

### Chapter 4. 필수 이해 요소

[01 JSP 처리 과정](/JSP/chapter4/01JSP처리과정.md)

[02 출력 버퍼와 응답](/JSP/chapter4/02출력버퍼와응답.md)

[03 웹 어플리케이션 폴더 구성과 URL 매핑](/JSP/chapter4/03웹애플리케이션폴더구성과URL매핑.md)


## Part 2 필수 습득

### Chapter 5. 기본 객체와 영역

[01 기본 객체 & 02 out 기본 객체 & 03 pageContext 기본 객체](/JSP/chapter5/01기본객체&02out기본객체&03pageContext기본객체.md)

[04 application 기본 객체](/JSP/chapter5/04application기본객체.md)

[05 JSP 기본 객체와 영역 & 06 JSP 기본 객체의 속성(Attribute) 사용하기](/JSP/chapter5/05JSP기본객체와영역&06JSP기본객체속성.md)

### Chapter 6. 에러 처리

[01 익셉션 직접 처리하기](/JSP/chapter6/01익셉션직접처리.md)

[02 에러 페이지 지정 & 03 에러 페이지 작성하기](/JSP/chapter6/02에러페이지지정&03에러페이지작성.md)

[04 응답 상태 코드별로 에러 페이지 지정하기](/JSP/chapter6/04응답상태코드별에러페이지지정.md)

[05 익셉션 타입별로 에러 페이지 지정하기](/JSP/chapter6/05Exception타입별에러페이지지정.md)

[06 에러 페이지의 우선순위와 에러 페이지 지정 형태 & 07 버퍼와 에러 페이지의 관계](/JSP/chapter6/06에러페이지우선순위와에러페이지지정형태&07버퍼와에러페이지의관계.md)

### Chapter 7. 페이지 모듈화와 요청 흐름 제어

[01 `<jsp:include>` 액션 태그를 이용한 공통 영역 작성]

[02 include 디렉티브를 이용한 중복된 코드 삽입]

[03 `<jsp:forward>` 액션 태그를 이용한 JSP 페이지 이동 & 04 `<jsp:include>` 와 `<jsp:forward>` 액션 태그의 page 속성 경로 & 05 기본 객체의 속성을 이용해서 값 전달하기](/JSP/chapter7/03jsp_forward액션태그를이용한JSP페이지이동&05기본객체의속성을이용해서값전달.md)


### Chapter 8. 자바빈과 `<jsp:useBean>` 액션 태그

[01 자바빈(JavaBeans)](/JSP/chapter8/01자바빈(JavaBeans).md)

[02 예제에서 사용할 자바빈 클래스](/JSP/chapter8/02예제로쓸자바빈클래스.md)

[03 `<jsp:useBean>` 태그를 이용한 자바 객체 사용](/JSP/chapter8/03jsp_useBean태그를사용한자바객체.md)

### Chapter 9. 클라이언트와의 대화 1 : 쿠키

[01 쿠키 사용하기](/JSP/chapter9/01쿠키사용하기.md)

[02 쿠키 처리를 위한 유틸리티 클래스](/JSP/chapter9/02쿠키처리를위한유틸리티클래스.md)

### Chapter 10. 클라이언트와의 대화 2 : 세션

[01 세션 사용하기 : session 기본 객체](/JSP/chapter10/01세션사용하기_seesion기본객체.md)

### Chapter 11. 표현 언어(Expression Language)

[01 표현 언어란?](/JSP/chapter11/01표현언어.md)

[02 EL 기초](/JSP/chapter11/02EL기초.md)

[03 EL에서 객체의 메서드 호출](/JSP/chapter11/03EL에서객체의메서드호출.md)

[05 EL에서 정적 메서드 호출](/JSP/chapter11/05EL에서정적메서드호출.md)

[06 람다식 사용하기](/JSP/chapter11/06람다식사용하기.md)

[07 스트림 API 사용하기](/JSP/chapter11/07스트림API사용.md)

### Chapter 12. 표준 태그 라이브러리(JSTL)

[01 JSTL이란](/JSP/chapter12/01JSTL이란.md)

[02 코어 태그](/JSP/chapter12/02코어태그.md)

[03 국제화 태그](/JSP/chapter12/03국제화태그.md)

<기본 내용> 

![image](https://user-images.githubusercontent.com/64796257/148363964-96783ee3-42d8-4886-bb2d-3d422d4800b8.png)

Server-side programming은 말 그대로 서버 쪽에 있는 프로그램을 말한다.

즉, 사용자의 요구에 맞게 dynamic document 혹은 active document를 생성할 때 서버 쪽에서 만드는 프로그램을 말한다.

server-side programming은 웹을 기반으로 하기 때문에  
결과적으로 http request message를 수신하면 서버에서 GET 방식이든 POST 방식이든 유저가 요청하는 정보를 가지고 코드를 실행한다.

JSP는 server-side-programming 중 하나이다. 그래서 이 내용을 README 파일에 넣었다.

JSP(Java Server Page) 방식 

![image](https://user-images.githubusercontent.com/64796257/148364167-751cc4ca-995c-434d-b651-35017c878d38.png)

웹 서버가 JSP container를 이용하는 방식. 이 방법은 데이터 전달에 문제가 없다.

JSP 방식은 말 그대로 Java 언어를 기반으로 했다.

그래서 사용자가 홈페이지에 접근하면 jsp container가 jsp 페이지에 접근한다.  
그러면 jsp container가 복잡한 과정을 거치게 되는데 애가 servlet 이라는걸 만들고 이걸 다시 컴파일해서 .class를 만듭니다.  
이걸 실행해서 html 파일 만든다. 

다시 말해서 페이지에 접근할 때 servlet으로 변환하고 컴파일해서 실행한 다음에 html 파일 만들어서 보냅니다

이러한 과정을 거치다보니 속도가 굉장히 느리다. 그래서 JSP는 느리다.

장점이 있다면 Java에 익숙한 사람이라면 이 방법이 편할 것이다. 말 그대로 Java 언어를 기반으로 했기 때문이다.
