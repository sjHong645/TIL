application 기본 객체는 4장에서 다룬 웹 어플리케션과 관련된 기본 객체이다. 

특정 웹 어플리케이션에 포함된 모든 JSP 페이지는 하나의 application 기본 객체를 공유하게 된다.

application 기본 객체는 웹 어플리케이션 전반에 걸쳐서 사용되는 정보를 담고 있다.  
application 기본 객체를 가지고 초기 설정 정보를 읽어올 수 있고 서버 정보를 읽어올 수 있고 웹 어플리케이션이 제공하는 자원(파일)을 읽어올 수 있다.

### 1. 웹 어플리케이션 초기화 파라미터 읽어오기

서블릿 규약은 웹 어플리케이션 전체에 걸쳐서 사용할 수 있는 `초기화 파라미터`를 정의하고 있다.  
웹 어플리케이션에서 사용할 수 있는 초기화 파라미터는 WEB-INF/web.xml 파일에 `<context-param>` 태그를 사용해서 추가한다.

``` xml
<context-param> 
  <description>파라미터 설명(필수 아님) </description>
  <param-name>파라미터 이름 </param-name>
  <param-value>파라미터 값 </param-value>
</context-param>
```
cf) web.xml 파일은 웹 어플리케이션을 위한 설정 정보를 담고 있는 파일이다. 서블릿 2.5/JSP 2.1 까지는 필수였지만 서블릿 3.0/JSP 2.2 버전부터는 필요할 때만 작성하면 된다.  
    그리고 그 파일은 [웹 어플리케이션 폴더]/WEB-INF 폴더에 위치시켜야 한다.

web.xml 파일에 초기화 파라미터를 추가하면 JSP application 기본 객체가 제공하는 메서드를 사용해서 초기화 파라미터를 사용할 수 있다.

application 기본 객체는 초기화 파라미터를 읽어올 수 있는 메서드를 아래와 같이 제공한다 .

| 메서도 | 리턴 타입 | 설명 | 
| --- | --- | --- | 
| getInitParameter(String name) | String | 이름이 name인 웹 어플리케이션 초기화 파라미터 값을 읽어온다. 없으면 null을 리턴한다 | 
| getInitParameterNames() | `Enumeration<String>`| 웹 어플리케이션 초기화 파라미터의 이름 목록을 리턴한다. | 

위 메서드를 사용하려면 초기화 파라미터를 web.xml 파일에 추가해야 한다. 해당 파일을 아래와 같이 작성했다.

- 위치  
![image](https://user-images.githubusercontent.com/64796257/148707131-d9c46dc3-721c-4683-b959-564514a4b103.png)

- 코드  
``` xml
<?xml version="1.0" encoding="UTF-8"?>

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
             http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

	<context-param>
		<description>로깅 여부</description>
		<param-name>logEnabled</param-name>
		<param-value>true</param-value>
	</context-param>

	<context-param>
		<description>디버깅 레벨</description>
		<param-name>debugLevel</param-name>
		<param-value>5</param-value>
	</context-param>

</web-app>
```
코드를 잠깐 살펴보면 
- logEnabled라는 이름의 파라미터는 true로 초기화
- debugLevel이라는 이름의 파라미터는 5로 초기화

위와 같이 웹 어플리케이션 초기화 파라미터를 추가했다면 아래와 같이 application 기본 객체를 사용해서 초기화 파라미터를 읽어올 수 있다.

- readInitParameter.jsp
``` JSP
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.util.Enumeration" %>
<html>
<head><title>초기화 파라미터 읽어오기</title></head>
<body>

초기화 파라미터 목록:
<ul>
<%
    Enumeration<String> initParamEnum = application.getInitParameterNames(); 
    // application.getInitParameterNames()를 통해서 파라미터의 목록을 initParamEnum에 저장한다.
    
	while (initParamEnum.hasMoreElements()) { // initParamEnum의 모든 목록을 읽을 때까지 반복
		String initParamName = initParamEnum.nextElement();
		// 파라미터의 값을 하나씩 읽어서 initParamName에 저장
%>
<li><%= initParamName %> = 
    <%= application.getInitParameter(initParamName) %>
    
    <%-- initParamName의 문자열 값과 그 이름을 가진 파라미터의 초기화 값을 출력 --%>
<%
	}
%>
</ul>
</body>
</html>
```

- 결과  
![image](https://user-images.githubusercontent.com/64796257/148707747-3e5d0d30-2c58-480b-ad61-e13d6e2eda0f.png)

### 4.2 서버 정보 읽어오기 

application 기본 객체는 현재 사용 중인 웹 컨테이너에 대한 정보를 읽어오는 메서드를 제공하고 있다. 메서드는 아래와 같다.

| 메서드 | 리턴 타입 | 설명 | 
| --- | --- | --- | 
| getServerInfo() | String | 서버 정보를 구한다 | 
| getMajorVersion() | String | 서버가 지원하는 서블릿 규약의 메이저 버전을 출력함. 버전의 정수 부분을 리턴 | 
| getMinorVersion() | String | 서버가 지원하는 서블릿 규약의 마이너 버전을 출력함. 버전의 소수 부분을 리턴 | 

위에 표시한 메서드를 사용하면 현재 서버 정보 및 지원하는 서블릿 API의 버전을 출력할 수 있다.  

ex. viewServerInfo.jsp 

``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>서버 정보 출력</title></head>
<body>

서버정보: <%= application.getServerInfo() %> <br>
서블릿 규약 메이저 버전: <%= application.getMajorVersion() %> <br>
서블릿 규약 마이너 버전: <%= application.getMinorVersion() %>

</body>
</html>
```

- 결과  
![image](https://user-images.githubusercontent.com/64796257/148707864-c545a973-7e61-40f5-964f-7762e4e5bd09.png)

### 4.3 로그 메시지 기록 

application 기본 객체는 웹 컨테이너가 사용하는 로그 파일에 로그 메시지를 기록할 수 있다. 아래의 메서드는 해당 기능을 제공한다.

| 메서드 | 리턴 타입 | 설명 | 
| --- | --- | --- | 
| log(String msg) | void | msg를 로그로 남긴다 | 
| log(String msg, Throwable throwable) | void | msg를 로그로 남긴다. Exception 정보도 함께 로그에 기록한다 | 

ex. useApplicationLog.jsp  
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>로그 메시지 기록</title></head>
<body>

<%
	application.log("로그 메시지 기록");
%>
로그 메시지를 기록합니다.

</body>
</html>
```

위 파일을 실행하면 `[톰캣 설치파일]/logs`에 로그 메시지가 기록된다. localhost.2022-01-10에 기록되어 있다.

![image](https://user-images.githubusercontent.com/64796257/148708118-3028a68f-84cf-4a71-8846-78f62968bd8f.png)

```
10-Jan-2022 09:48:04.369 정보 [http-nio-8080-exec-8] org.apache.catalina.core.ApplicationContext.log 로그 메시지 기록
```

application 기본 객체가 제공하는 log() 메서드 뿐만 아니라 JSP 페이지가 제공하는 log() 메서드를 이용해도 로그 메시지를 기록할 수 있다.  

ex. useJspLog.jsp 
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>로그 메시지 기록2</title></head>
<body>

<%
	log("로그 메시지 기록2");
%>
로그 메시지를 기록합니다.

</body>
</html>
```

- 결과 ⇒ 마찬가지로 localhost.2022-01-10에 있다.
```
10-Jan-2022 09:53:16.509 정보 [http-nio-8080-exec-1] org.apache.catalina.core.ApplicationContext.log jsp: 로그 메시지 기록2
```

### 4.4 웹 어플리케이션 자원 구하기 

JSP 페이지에서 웹 어플리케이션 폴더에 위치한 파일을 사용해야 할 때가 있다.  
예를 들어, chap05 웹 어플리케이션 폴더의 하위 폴더에 위치한 message/notice.txt 파일의 내용을 그대로 응답으로 출력하고 싶다고 하자.

아래와 같은 코드를 작성해서 지정한 자원을 읽어올 수 있다. 

#### 절대 경로 사용해서 읽기 - readFileDirectly.jsp 

``` jsp 
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>절대 경로 사용하여 자원 읽기</title></head>
<body>

<%
	char[] buff = new char[128];
	int len = -1;
	
	String filePath = "C:\\apache-tomcat-8.5.73\\webapps\\chap05"+
		"\\message\\notice.txt"; 
		// 파일 경로를 filePath에 저장
		
	try(InputStreamReader fr = new InputStreamReader(new FileInputStream(filePath), "UTF-8")) {
	// 파일 경로 filePath에 있는 내용을 읽어서 fr에 저장 
	
		while ( (len = fr.read(buff)) != -1) { // fr에 있는 내용을 배열 buff에 저장
		
			out.print(new String(buff, 0, len));
			// 배열 buff에 있는 내용을 0~len-1 까지 출력
		}
	} catch(IOException ex) {
		out.println("익셉션 발생: "+ex.getMessage());
	}
%>

</body>
</html>
```

- 경로에 있는 파일 notice.txt

``` 
본 사이트는 예제 사이트입니다.<br>
이 내용은 <b>notice.txt</b>에 있습니다.
```

- 결과  
![image](https://user-images.githubusercontent.com/64796257/148708875-ecf8905c-dbb4-4a18-a778-a7462a179aa9.png)


- 단점  
절대 경로를 사용해서 파일을 읽어오면 유지보수에 문제가 생길 수 있다. 파일의 폴더 이름이 갱신될 때마다 해당 파일에 접속해서 내용을 일일이 바꿔줘야 하는 번거로움이 존재한다.

이러한 문제를 해결하도록 하는 메서드를 아래와 같이 제공한다.

#### 자원 접근 메소드를 이용 - readFileUsingApplication.jsp

메소드는 다음과 같다. 

| 메서드 | 리턴 타입 | 설명 |
| --- | --- | --- |
| getRealPath(String path) | String | 웹 어플리케이션 내에서 지정한 경로에 해당하는 자원의 시스템 상에서의 경로 리턴 |
| getResource(String path) | java.net.URL | 웹 어플리케이션 내에서 지정한 경로에 해당하는 자원의 시스템 상에서의 URL 객체 리턴 |
| getResourceAsStream(String path) | java.io.inputStream | 웹 어플리케이션 내에서 지정한 경로에 해당하는 자원으로부터 데이터를 읽어올 수 있는 InputStream을 리턴 |

위 메서드를 사용하면 readFileDirectly.jsp에서와 같이 절대 경로를 사용하지 않고도 웹 어플리케이션 폴더의 파일에 접근할 수 있다

ex. readFileUsingApplication.jsp

``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>application 기본 객체 사용하여 자원 읽기</title></head>
<body>

<%
	String resourcePath = "/message/notice.txt"; // webapps 내의 경로 지정
%>
자원의 실제 경로:<br>
<%= application.getRealPath(resourcePath) %> <%-- 자원의 실제 경로를 구한다. --%>
<br>
----------<br>
<%= resourcePath %>의 내용<br> 
----------<br>
<%
	char[] buff = new char[128];
	int len = -1;
	
	try(InputStreamReader br = new InputStreamReader(
				application.getResourceAsStream(resourcePath), "UTF-8")) {
	// 해당 경로에 있는 데이터를 읽어올 수 있는 스트림 br을 생성
				
		while ( (len = br.read(buff)) != -1) { // br로 부터 읽어온 내용을 배열 buff에 저장
			out.print(new String(buff, 0, len));
			// buff에 있는 내용을 0 ~ len-1 까지 
		}
	} catch(IOException ex) {
		out.println("익셉션 발생: "+ex.getMessage());
	}
%>

</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/148709630-abff8639-6f13-49bc-937c-d11940807a50.png)

URL 객체를 리턴하는 application.getResource() 메서드를 사용하면 아래와 같은 코드를 사용해서 자원에 접근할 수 있다. 

ex. readFileUsingURL.jsp 
``` jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.net.URL" %>
<html>
<head><title>application 기본 객체 사용하여 자원 읽기2</title></head>
<body>

<%
	String resourcePath = "/message/notice.txt";
	char[] buff = new char[128];
	int len = -1;
	URL url = application.getResource(resourcePath); // resourcePath에서 지정한 자원의 URL을 생성
	
	try (InputStreamReader br = new InputStreamReader(url.openStream(), "UTF-8")) {
	// 지정한 URL 객체인 url에서 데이터를 읽어 스트림 br에 저장
	
		while ( (len = br.read(buff)) != -1) {
			out.print(new String(buff, 0, len));
		}
	} catch(IOException ex) {
		out.println("익셉션 발생: "+ex.getMessage());
	}
%>

</body>
</html>
```

- 결과 

![image](https://user-images.githubusercontent.com/64796257/148709760-934e26bd-e1dd-44c2-b5ad-8407fc90543e.png)

readFileUsingApplication.jsp와 readFileUsingURL.jsp는  
application 기본 객체를 통해서 자원에 접근하기 때문에 웹 어플리케이션 폴더를 변경하더라도  
코드에서 자원의 경로를 수정할 필요는 없다. 
















