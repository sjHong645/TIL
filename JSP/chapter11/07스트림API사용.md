EL 3.0 버전에는 컬렉션 객체를 위한 스트림 API가 추가되었다. 스트림 API를 사용하면 다음과 같이 간단한 코드로 합을 구할 수 있다.

ex) 
``` jsp
<c:set var="lst" value = "<%= java.util.Arrays.asList(1,2,3,4,5) %>" />
# lst라는 이름의 Array를 만들었다. 이 Array는 1,2,3,4,5라는 값들을 가지고 있다.

<c:set var="sum" value = "${lst.stream().sum()}" />
# lst라는 Array의 값을 스트림을 이용해서 계산했다. 그 값을 sum이라는 변수에 저장했다.
```

여기에 세미콜론과 할당 연산자를 함께 사용하면 JSTL 태그를 사용하지 않고 EL 만의 합을 sum 변수에 할당할 수 있다.

``` jsp
${lst = [1,2,3,4,5]; sum = lst.stream().sum() ; ''}
```
lst라는 이름의 배열을 생성했다. 그 배열은 {1,2,3,4,5}로 이뤄져있다.

그러한 lst의 총합 15를 sum이라는 변수에 저장하는데 이 결과를 출력하고 싶지는 않기 때문에 세미콜론 연산자를 이용해서 빈 문자열이 출력되도록 했다.

### 1 스트림 API 기본
- 스트림 API의 기본 형태 

![image](https://user-images.githubusercontent.com/64796257/149303325-26136517-a31c-46b0-8a2d-594a23233c53.png)

ex) 리스트 lst에서 짝수인 값만 골라 제곱한 결과를 List로 표현하려 한다.
``` jsp
${lst.stream()
     .filter(x -> x % 2 == 0) # lst의 원소 값 중에서 짝수인 값만 필터링
     .map(x -> x * x) # 각 원소들을 제곱해서
     .toList() }  # 리스트로 출력
```

대부분 자바에서 사용하는 스트림과 유사하다. 

### 2 stream()을 이용한 스트림 생성

stream() 메서드는 실제로 자바 컬렉션 API에서 제공하는 메서드가 아니라 EL에서 제공하는 메서드이다. 

즉, 다음 코드에서 스크립트릿의 lst.stream()은 자바 8의 stream() 메서드를 실행하는 반면에 EL의 lst.stream()은 EL의 stream() 메서드를 실행한다.

``` jsp
<%
  List<String> lst = Arrays.asList("1", "2", "3"); 
  Stream<String> str = lst.stream(); // 자바 8의 stream
  request.setAttribute("lst", lst); 
%>

${ lst.stream() } <%-- EL의 stream --%>
```

EL은 Map 타입의 값에 대해 stream()을 지원하지 않는다.  
Map에 stream() 메서드를 사용하고 싶다면 다음과 같이 Map.entrySet()과 같이 Map에 대한 컬렉션 타입 객체를 생성한 다음에 그 객체에 stream() 메서드를 적용하면 된다.

``` jsp
<% 
  java.util.Map<String, String> map = new java.util.HashMap<> (); 
  map.put("code1", "코드1");
  map.put("code2", "코드2");
  request.setAttribute("map", map);
%>
${map.entrySet().stream().map(entry -> entry.value).toList()}

# Map 타입의 값에 stream()을 지원하지 않는다. 
# 그래서 map.stream()이 아니라 map.entrySet().stream()으로 작성해줘야 한다.
  
```

Map의 entrySet()은 java.util.Entry의 집합을 return하므로 EL의 stream() 메서드가 생성하는 스트림 객체의 원소 타입은 Entry가 된다.

### 3 filter()

### 4 map()

map()은 원소를 변환한 새로운 스트림을 생성한다. map() 메서드는 람다식을 파라미터로 갖는다.

map() 메서드는 스트림의 각 원소에 대해 람다식을 실행하고 람다식의 결과로 구성된 새로운 스트림을 생성한다.

![image](https://user-images.githubusercontent.com/64796257/149306930-2ec04c97-5e2a-477b-8679-f5298ce62de3.png)

ex) 
``` jsp
$ { ageList = members.stream().map(mem->mem.age).toList()}
```

members는 회원 데이터 목록을 갖고 있는 리스트 객체이다. 이 리스트 객체가 갖고 있는 각 원소에 age라는 필드가 있다.  
그러한 age값 즉, 회원들의 나이를 원소로 갖는 리스트를 생성해서 ageList라는 이름으로 저장하는 EL이다.

### 5 sorted() 정렬 



















