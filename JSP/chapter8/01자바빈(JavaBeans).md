## 01 자바빈(JavaBeans) 

자바빈은 속성(데이터), 변경 이벤트, 객체 직렬화를 위한 표준이다. 이 중에서 JSP에서는 속성을 표현하기 위한 용도로 사용된다.  
아래 코드는 자바빈 규약을 따르는 클래스의 구조를 보여준다.

``` java
public class BeanClassName implementes java.io.Serializable { 
  
  /* 값을 저장하는 필드 */
  private String value;
  
  /* BeanClassName의 기본 생성자 */
  public BeanClassName() { } 
  
  /* 필드의 값을 읽어오는 값 */
  public String getValue() { 
    return value;
  }
  
  /* 필드의 값을 변경하는 값 */
  public void setValue(String value) { 
    this.value = value;
  } 
} 
```

자바빈 규약을 따르는 클래스를 자바빈이라 부른다. JSP 프로그래밍에서 사용하는 자바빈 클래스는  
위 코드처럼 데이터를 저장하는 필드, 데이터를 읽어올 때 사용하는 메서드 그리고 값을 저장할 때 사용하는 메소드로 구분된다.

### 1 자바빈 프로퍼티(property)

프로퍼티는 자바빈에 저장되는 값을 나타낸다. 메서드 이름을 사용해서 프로퍼티의 이름을 결정하게 된다.

- 프로퍼티의 값을 변경하는 메서드는 프로퍼티의 이름 중 첫 글자를 대문자로 바꾸고 ⇒ set을 붙인다. ex) setValue()
- 프로퍼티의 값을 읽어오는 메서드는 프로퍼티의 이름 중 첫 글자를 대문자로 바꾸고 ⇒ get을 붙인다. ex) getValue()

프로퍼티의 이름과 필드 이름은 같지 않아도 된다. 

예를 들어, maxAge 프로퍼티 값을 실제로 저장하는 필드와 maxAge 프로퍼티 값을 읽고 저장하는데 사용되는 메서드는 다음과 같이 코딩할 수 있다.

``` java
private int maximumAge = 0;

public void setMaxAge(int maxAge){
  maximumAge = maxAge;
}

public int getMaxAge(){
  return maximunAge;
} 
```

프로퍼티 값을 저장하는 필드 이름이 maximumAge로 프로터피 이름인 maxAge와 다른 것을 알 수 있다.

프로퍼티의 타입이 boolean이라면 get대신 is를 붙일 수 있다. 

``` java
public boolean isFinished() { 

  ... 
  
}
```

- 정리 예제 

⇒ Temperature 자바빈 클래스를 정의한다. 

섭씨 온도를 읽고 쓰는 메서드와 섭씨 온도로부터 화씨온도를 계산해서 알려주는 기능이 필요한 클래스라 하자.

``` java

public class Temperature{ 
  private double celsius;
  
  public double getCelsius() { 
    return celsius;
  
  }
  
  public void setCelsius(double celsius){ 
    this.celsius = celsius;
  }
  
  public double getFarenheit() { 
    return celsius * 9.0/5.0 + 32.0;
  }


}
```

















