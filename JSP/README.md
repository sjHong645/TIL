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

[04 application 기본 객체]()

[05 JSP 기본 객체와 영역]()

[06 JSP 기본 객체의 속성(Attribute) 사용하기]()



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
