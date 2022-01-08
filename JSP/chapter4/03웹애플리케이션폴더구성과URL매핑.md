JSP를 이용해서 웹 어플리케이션을 개발하려면 기초 문법 뿐만 아니라 웹 어플리케이션의 폴더 구조에 대해서도 알아야 한다. 

서블릿/JSP 규약은 웹 어플리케이션의 특정 폴더 구조를 따르도록 하기 때문에 폴더 구조를 모르면 제대로 동작하는 코드를 작성할 수 없다.

### 3.1 웹 애플리케이션 폴더와 URL의 관계 

톰캣에서는 웹 애플리케이션을 [톰캣]\webapps 폴더에 위치시킨다.

```
http://localhost:8080/chap04/autoFlushTrue.jsp
```

위 주소가 의미하는 건 C:\apache-tomcat-8.5.73\webapps\chap04 폴더에 있는 autoFlushTrue.jsp 파일을 실행한다는 뜻이다. 

위 주소에서 /chap04 를 컨텍스트 경로(context path)라고 한다. 

위 주소를 가지고 해석을 해보면...

루트 웹 애플리케이션 폴더인 ROOT를 기준으로 /chap04/autoFlushTrue.jsp 파일을 찾는다. 

즉, webapps\ROOT\chap04\autoFlushTrue.jsp 파일을 찾아서 실행한다.

만약에 chap04의 하위 폴더로 chap04222 라는 폴더를 만들고 거기에 auto.jsp를 실행시키고 싶다면
```
http://localhost:8080/chap04/chapter04222/auto.jsp
```

라고 입력해서 실행하면 된다. 
