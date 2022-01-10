application 기본 객체는 4장에서 다룬 웹 어플리케션과 관련된 기본 객체이다. 

특정 웹 어플리케이션에 포함된 모든 JSP 페이지는 하나의 application 기본 객체를 공유하게 된다.

application 기본 객체는 웹 어플리케이션 전반에 걸쳐서 사용되는 정보를 담고 있다.  
application 기본 객체를 가지고 초기 설정 정보를 읽어올 수 있고 서버 정보를 읽어올 수 있고 웹 어플리케이션이 제공하는 자원(파일)을 읽어올 수 있다.

### 1. 웹 어플리케이션 초기화 파라미터 읽어오기

서블릿 규약은 웹 어플리케이션 전체에 걸쳐서 사용할 수 있는 `초기화 파라미터`를 정의하고 있다.  
웹 어플리케이션에서 사용할 수 있는 초기화 파라미터는 WEB-INF/web.xml 파일에 `<context-param>` 태그를 사용해서 추가한다.

``` 
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


