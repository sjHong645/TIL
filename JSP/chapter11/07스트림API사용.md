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

ex) 
``` jsp
$ { vals = {20, 17, 30, 2, 9, 23} ; sortedVals = vals.stream().sorted().toList() } 
```

vals라는 배열이 있다. 그 배열에 대해서 stream()을 통해 스트림을 생성해서 sorted() 메서드를 이용해 오름차순으로 정렬한 다음에 toList() 메서드를 가지고 List로 변환시켰다.

물론 sorted() 메서드가 모든 타입의 원소를 정렬시킬 수 있는 건 아니다.

Integer, Long, String 과 같은 타입은 이미 오름차순에 맞게 Comparable 인터페이스가 구현되어 있기 때문에 sorted()를 실행하면 오름차순으로 정렬된 결과를 구할 수 있다.

그렇다면 Comparable 인터페이스가 구현되어 있지 않거나 오름차순이 아닌 다른 순서로 정렬하고 싶다면 어떻게 해야할까??  
sorted() 메서드에 값을 비교할 때 사용할 람다식을 전달하면 된다. 

ex) 람다식을 이용해서 숫자 값을 가지는 리스트를 `내림차순` 정렬했다.
``` jsp
$ { vals = {20, 17, 30, 2, 9, 23} ; sortedVals = vals.stream().sorted((x1,x2) -> x1 < x2 ? 1 : -1 ).toList() } 
```
sorted() 의 람다식이 2개의 파라미터를 갖도록 했다.  
첫 번째 파라미터와 두 번째 파라미터를 비교해서 두 번째 파라미터값이 더 크면 1을 return하고 그렇지 않으면 -1을 return하도록 했다.  

이렇게 람다식을 설정하면 내림차순으로 정렬할 수 있게 된다.  

ex) Member라는 객체가 있다. 여기에 이름(name)과 나이(age)를 저장할 수 있다.

``` jsp 
<%
     List<Member> memberList = Arrays.asList(new Member("김씨", 20), new Member("이씨", 54), 
                                             new Member("박씨", 19), new Member("정씨", 20));
     request.setAttribute("members", memberList);
%>

${ sortedMem = members.stream().sorted((m1, m2) -> m1.age.compareTo(m2.age)).toList(); '' }
```
sorted() 메서드에 m1과 m2라는 이름을 가진 Member 객체가 전달된다. 

이때 두 객체의 age 값을 비교해서 age 값에 따른 오름차순 정렬을 진행한다.

cf) A.compareTo(B) ⇒ A가 B보다 크면 1 / A가 B보다 작으면 -1 / A와 B가 같으면 0

### 6 limit()을 이용한 개수 제한

limit()은 지정한 개수를 갖는 새로운 스트림을 생성한다. 

ex) 
``` jsp
${nums.stream().sorted().limit(3).toList() } 
```
nums라는 배열에 대해 스트림을 생성(stream() 메서드)하고 정렬(sorted() 메서드)한 다음에 앞에 있는 3개(limit(3) 메서드)의 원소만을 가지고 스트림을 새로 생성했다.

### 7 toList()와 toArray()를 이용한 결과 생성

`toList()`는 자바 리스트 객체를 생성하고 `toArray()`는 자바 배열 객체를 생성한다.

EL에서 리스트타입의 경우 각 원소의 값을 문자열로 변환해서 출력한다.  
ex) 
``` jsp
<%
     List<Member> memberList = Arrays.asList(new Member("김씨", 24), new Member("이씨", 33), 
                                             new Member("박씨", 15), new Member("정씨", 59));
     request.setAttribute("members", memberList);
%>

$ { members.stream().map(m -> m.name).toList(); ⇒ ["김씨", "이씨", "박씨", "정씨"] 출력

$ { members.stream().map(m -> m.name).toArray(); ⇒ [Ljava.lang.Object; ~~ ] 출력
```

### 8 count()를 이용한 개수 확인

ex) `${ members.stream().count()}` ⇒ 스트림의 원소 개수를 return 한다. 타입은 Long이다.

### 9 Optional 타입

average(), max(), min()과 같이 결과값이 존재하거나 존재하지 않는 경우가 있을 때 사용하는 타입이 Optional이다.  
Optional은 아래와 같은 메서드를 제공한다.

- get() : 값이 존재할 경우 값을 return한다. 값이 존재하지 않으면 ELException을 발생한다.
- orElse(other) : 값이 존재하면 해당 값을 return하고 그렇지 않으면 other를 return 한다.
- orElseGet( (() -> T) other) : 값이 존재하면 해당 값을 return하고 그렇지 않으면 람다식 other를 실행한 결과를 return한다.
- ifPresent( ( ((x) -> void) consumer ) : 값이 존재하면 람다식 consumer를 실행한다. 존재하는 값을 람다식의 파라미터로 전달한다.

ex) max() 는 최대값을 구할 때 사용하는데 이는 Optional을 결과로 return하기 때문에 결과값을 구하기 위해 get() 메서드를 사용할 수 있다.

``` jsp
${ [1, 2, 3].stream().max().get() } ⇒ 최대값 3을 return한다.
```

값이 존재하지 않은 상태에서 get()을 사용하면 ELException을 발생하기 때문에  
값이 존재하지 않을 수 있다면 get() 대신 orElse()를 사용해서 값이 없을 때 다른 값을 사용하도록 해야 한다.

ex)  
``` jsp
${ [].stream().min().orElse('없음') } ⇒ 값이 없으므로 '없음'을 출력한다.
${ [1, 2, 3].stream().min().orElse('없음') } ⇒ 값이 존재하기 때문에 결과값 1을 출력한다.
```

orElse()가 값을 출력한다면 orElseGet()은 값이 없을 때 람다식을 출력하도록하는 메서드이다.  
ex)
``` jsp
${ [].stream().min().orElseGet(()->-1)} ⇒ 값이 존재하지 않기 때문에 -1을 출력한다
```

값이 존재할 때 코드를 실행하고 싶다면 ifPresent()를 사용하면 된다. ifPresent는 결과값을 받아 코드를 실행할 람다식을 파라미터로 갖는다.

ex) 
``` jsp
$ {minValue = '-' ; ''} # minValue 변수에 '-'를 저장했다.

$ { [1, 2, 3].stream().min().ifPresent(x -> (minValue = x)) } #[1, 2, 3]의 최소값을 1이다. 
                                        # 1이라는 값이 존재하기 때문에 ifPresent()에 의해 minValue 변수에 1이 저장된다.
                                        # 만약에 값이 없었다면 minValue 변수는 이전에 저장한 것과 똑같이 '-'를 가지고 있었을 것이다.

최소값은 ${minvalue} 입니다.
```

### 10 sum()과 average()
- sum() : 스트림이 숫자로 구성된 경우 sum()을 이용해서 합을 구한다.
``` jsp
$ {[1, 2, 3, 4, 5].stream().sum()} ⇒ 15 출력
```

- average() : 값의 평균을 구한다. EL의 Optional 타입을 return 한다. 이때 Optional은 Double 타입 값을 갖는다.
``` jsp
$ {[1, 2, 3, 4, 5].stream().average().get() } ⇒ 2.5 출력
```

### 11 min()과 max()를 이용한 최소/최대 구하기

스트림 원소가 Long이나 String과 같이 Comparable을 구현하고 있다면 min(), max()를 통해 최소값과 최대값을 구할 수 있다.

둘 다 Optional 타입을 return한다.

크기를 구하는 규칙이 달라야 한다면 sorted() 처럼 크기 비교를 위한 람다식을 사용할 수 있다.

ex) age 프로퍼티 값이 가장 큰 객체를 구하고 싶을 때
``` jsp
<%
     List<Member> memberList = Arrays.asList(new Member("김씨", 24), new Member("이씨", 33), 
                                             new Member("박씨", 15), new Member("정씨", 59));
     request.setAttribute("members", memberList);
%>

$ {maxAgeMemOpt = members.stream().max((m1, m2) -> m1.age.compareTo(m2.age)) ; '' }

# 객체 자레로는 최대/최소값을 알아낼 수 없다.
# 여기서는 객체의 age 값의 최대값을 알고 싶기 때문에 위와 같은 람다식을 설정했다.
```

### 12 anyMatch(), allMatch(), noneMatch() 를 이용한 존재 여부 확인

ex) anyMatch() 
``` jsp
$ {lst = [1, 2, 3, 4, 5] ; '' }

<%-- 4보다 큰 원소가 존재하는지 확인 --%>
${ matchOpt = lst.stream().anyMatch( v -> v > 4); '' } # lst 배열에 있는 모든 값들 중에서 하나라도 4보다 큰 값이 있다면 true를 return하고 
                                                       # 모든 값이 다 4보다 작다면 false를 return한다.
$ {matchOpt.get() } # 여기서는 true를 return한다.
```

ex) allMatch()
``` jsp
$ {lst = [1, 2, 3, 4, 5] ; '' }

<%-- 4보다 큰 원소가 존재하는지 확인 --%>
${ matchOpt = lst.stream().allMatch( v -> v > 4); '' } # lst 배열에 있는 모든 값이 4보다 크다면 true 를 return하고 
                                                       # 그렇지 않으면 false를 return한다.
$ {matchOpt.get() } # 여기서는 false를 return한다.
```

ex) noneMatch() 
``` jsp
$ {lst = [1, 2, 3, 4, 5] ; '' }

<%-- 4보다 큰 원소가 존재하는지 확인 --%>
${ matchOpt = lst.stream().noneMatch( v -> v > 4); '' } # lst 배열에 있는 모든 값이 4보다 크다는 조건을 만족하지 않으면 true를 return하고 
                                                       # 그렇지 않으면 false를 return한다.
$ {matchOpt.get() } # 여기서는 false를 return한다.
```

anyMatch(), allMatch(), noneMatch() 모두 빈 스트림에 대해 값이 없는 Optional을 return한다.  
따라서, 위에서 소개한 세 메서드를 사용할 때 스트림에 값이 없을 가능성이 있다면 get() 대신에 orElse() 또는 ifPresent() 등을 사용해서 에러가 발생하지 않도록 해야 한다.





