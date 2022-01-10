## 05. Exception 타입별로 에러 페이지 지정

JSP 페이지에서 발생하는 Exception 종류별로 에러 페이지를 지정할 수도 있다. `<error-code>` 태그 대신에 `<exception-type>` 태그를 사용하면 된다.

``` jsp 
<error-page>
  <exception-type>java.lang.NullPointerException</exception-type> 
  
  <location>/error/errorNullPointer.jsp </location>
</error=page>
```

