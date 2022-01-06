# JSP 2.3 웹프로그래밍 책 정리

### Chapter 2. 웹 프로그래밍 기초
[01 웹과 웹 프로그래밍](/JSP/chapter2/01웹과웹프로그래밍.md)


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
