JSP는 스크립트릿과 표현식 등의 스크립트 코드와 HTML 코드가 뒤섞이게 되는데 이렇게 뒤섞인 JSP 코드는 알아보기 쉽지 않다.

JSP는 실행 코드와 화면을 구성하는 HTML 코드를 쉽게 섞을 수 있어서 개발이 편리하지만  
반대로 스크립트 코드와 HTML 코드가 섞이면서 코드의 가독성은 도리어 나빠진다.

JSP 페이지에서 많이 사용되는 논리적인 판단, 반복 처리, 포맷 처리를 위한 커스텀 태그를 표준으로 만들어서 정의한 것이 있는데  
그것이 바로 `JSTL(JSP Standard Tag Library)`이다.

### 1 JSTL이 제공하는 태그의 종류

| 라이브러리 | 주요 기능 | 접두어 | 관련 URI | 
| --- | --- | --- | --- | 
| 코어 | 변수지원, 흐름제어, URL 처리 | c | http://java.sun.com/jsp/jstl/core | 
| XML | XML 코어, 흐름제어, XML 변환 | x | http://java.sun.com/jsp/jstl/xml | 
| 국제화 | 지역, 메시지 형식, 숫자 및 날짜 형식 | fmt | http://java.sun.com/jsp/jstl/fmt | 
| 데이터베이스 | SQL | sql | http://java.sun.com/jsp/jstl/sql | 
| 함수 | 컬렉션 처리, String 처리 | fn | http://java.sun.com/jsp/jstl/functions | 

### 2 JSTL 라이브러리 받기

JSTL을 사용하려면 JSTL 1.2를 구현한 jar 파일을 다운로드 받아야 한다. 

https://mvnrepository.com/artifact/javax.servlet/jstl/1.2 에서  
jstl-1.2.jar 파일을 다운로드 받고 WEB-INF/lib 디렉토리에 복사한다. 그러면 JSP 페이지에서 JSTL이 제공하는 태그 라이브러리를 사용할 수 있다.

