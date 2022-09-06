## 이번 챕터에서 다룰 내용

![image](https://user-images.githubusercontent.com/64796257/188530576-d8f0dd5f-2e04-4818-bb29-6d03938df3ff.png)

## 01 보안 

DB의 보안을 유지해서 데이터를 보호하는 방법은 일반적으로 3가지 유형으로 구분한다.

1) 물리적 환경에 대한 보안 - 자연 재해 등으로 부터 보호
2) 권한 관리를 통한 보안 - 권한이 `없는` 사용자로부터 보호
3) 운영 관리를 통한 보안 - 권한이 `있는` 사용자로부터 보호 ⇒ `데이터 무결성`을 유지하기 위한 보안

3)에 대한 내용은 앞절에서 살펴봤다. 이번 절에서는 특히 `2)의 경우`를 살펴보겠다.

## 02 권한 관리 

### 권한 관리의 개념 

![image](https://user-images.githubusercontent.com/64796257/188531325-7756dd69-93d8-4667-a79a-dee8ce4d609d.png)

1) 로그인 

DBMS는 DB의 보안을 유지하기 위해 계정이 발급된 사용자가 `로그인에 성공`했을 경우에만 DB에 접근할 수 있도록 `접근 제어 기능`을 제공한다.  
그러므로, 모든 사용자는 자신에게 발급된 계정으로 `로그인`해야 `DB`에 접근할 수 있다.

2) 권환 확인 

`로그인`을 통해 DB에 접근할 수 있더라도 DB안에 있는 모든 데이터를 사용할 수 있는 건 아니다. 

DBMS는 사용자별로 `DB의 사용 범위`와 `수행 가능한 작업`을 제한할 수 있어, 각 사용자는 `자신에게 허용된 권한` 내에서만 DB를 사용할 수 있다.

테이블이나 뷰와 같이 DB에 존재하는 모든 객체는 기본적으로 `해당 객체를 생성한 사용자`만 `사용 권한`을 가진다.  
즉, `객체`가 `사용자`별로 관리되므로 DB에 접근이 허락된 사용자더라도 `자신이 생성하지 않은 객체`는 `사용할 수 없다`.

그런데, DB는 여러 사용자가 `공유`할 목적으로 만들어졌기 때문에 `다른 사용자`가 생성한 `객체`에도 `필요에 따라` 접근할 수 있어야 한다.  
이를 위해 `DB 객체 소유자`는 필요에 따라 `다른 사용자`에게 자신이 소유한 객체에 대한 `사용 권한`을 `부여`할 수 있다.  
이는 `SQL문`을 이용해서 권한을 `부여`하거나 `취소`한다.

![image](https://user-images.githubusercontent.com/64796257/188532222-532ccbc8-f8a5-4875-bc4e-5d220943c9e7.png)

### 권한 부여 - GRANT

ex) 고객 테이블에 대한 검색 권한을 사용자 Hong에게 부여 
``` sql
GRANT SELECT ON 고객 TO Hong; 

⇒ 고객 테이블에 대한 SELECT라는 권한을 Hong에게 부여함
```

ex) 고객 테이블에 대한 삽입,삭제 권한을 모든 사용자에게 부여 
``` sql
GRANT INSERT, DELETE ON 고객 TO PUBLIC; 

⇒ 고객 테이블에 대한 INSERT, DELETE라는 권한을 모두에게 부여함
```

ex) 고객 테이블을 구성하는 속성중 등급과 적립금 속성에 대한 수정 권한을 Park에게 부여
``` sql
GRANT UPDATE(등급, 적립금) ON 고객 TO Park; 
```

ex) 고객 테이블에 대한 검색 권한을 사용자 Lee에게 부여 (WITH GRANT OPTION을 사용)
``` sql
GRANT SELECT ON 고객 TO Lee WITH GRANT OPTION; 
```

`WITH GRANT OPTION`을 사용하면 사용자 Lee는   
1) 데이터 검색(SELECT) 뿐만 아니라
2) 검색 권한을 GRANT 문을 통해 다른 사용자에게 부여할 수 있음

ex) 테이블을 생성할 수 있는 시스템 권한을 사용자 Song에게 부여
``` sql
GRANT CREATE TABLE TO Song; 
```

ex) 뷰를 생성할 수 있는 시스템 권한을 사용자 Shin에게 부여
``` sql
GRANT CREATE VIEW TO Shin; 
```

- 보안 강화를 위해 `뷰(VIEW)`를 사용할 수도 있음 

`뷰`를 이용하면 사용자가 테이블에 직접 접근하지 못하게 하면서 사용자에게 `필요한 테이블 일부`만 제공할 수 있다.  
따라서, 사용자와 관련 없는 `테이블의 다른 부분`을 `숨겨` 보안을 유지하는데 도움이 된다.

### 권한 취소 - REVOKE 

ex) 권한 부여 상황  

![image](https://user-images.githubusercontent.com/64796257/188533302-429d92ff-b18c-4ae9-80e6-7d68847858d1.png)

ex) 위와 같이 권한이 부여된 상황에서  
- Kim이 Hong에게 부여한 고객 테이블에 대한 검색 권한을 취소하면서
- Hong이 다른 사용자에게 부여한 고객 테이블에 대한 검색 권한도 함께 취소한다.

``` sql
REVOKE SELECT ON 고객 FROM Hong CASCADE; ⇒ 이는 Kim이 Hong에게 준 권한을 취소하기 위해 작성한 sql 
                                         ⇒ 이때, Hong이 다른 사용자들에게도 부여한 권한도 취소하기 위해서 CASCADE라는 옵션을 추가함
```

ex) 위와 같이 권한이 부여된 상황에서  
- Hong이 다른 사용자에게 권한을 부여한 적이 없는 경우에만
- Kim이 Hong에게 부여한 고객 테이블에 대한 검색 권한을 취소하는 명령문 
``` sql
REVOKE SELECT ON 고객 FROM Hong RESTRICT;
```

현재 상황에서는 Hong이 Park에게 권한을 부여했기 때문에 `Kim`은 `Hong`에게 부여했던 권한을 취소할 수 없다. 

ex) Hong에게 부여한 테이블 생성 권한을 취소
``` sql
REVOKE CREATE TABLE FROM Hong;
```

### 역할 부여와 취소 

#### 역할 개념

DB 객체의 `소유자`는 조직의 업무를 수행하기 위해 자신이 소유한 객체에 대한 `권한`들을 `여러 사용자`에게 부여하는 경우가 많다.

권한을 부여할 때 마다 `GRANT 문`을 따로 작성해서 부여하기가 매우 번거롭다.  
그리고, 여러 사용자에게 동일하게 부여했던 많은 권한을 `취소`할 때도 신중해야 한다.  

이렇게 `여러 사용자들`에게 `동일한 권한`을 `부여`하고 `취소`하는 번거로운 작업을 편하게 수행할 수 있도록 도움을 주는 것이 `역할(role)`이다.

ex) 사용자 Kim이 자신의 고객테이블에 대한 SELECT, INSERT, UPDATE 권한을 3명에게 부여

![image](https://user-images.githubusercontent.com/64796257/188535245-9717971f-9335-4f82-849f-06bb3e47a251.png)

위와 같이 GRANT를 반복해서 쓰거나

``` SQL
GRANT SELECT, INSERT, UPDATE ON 고객 TO Hong; 
```

과 같이 GRANT문을 써줄 수 있으나 이는 복잡한 GRANT문이다. 

이때 유용하게 사용할 수 있는 게 `역할`이라는 개념이다.

#### 역할 sql문

ex1) role_1이라는 이름의 역할 생성 
``` sql
CREATE ROLE role_1;
```

ex2) role_1에 고객 테이블에 대한 삽입, 삭제, 검색 권한을 넣음

``` sql
GRANT SELECT, INSERT, DELETE ON 고객 TO role_1; 
```

ex3) 고객 테이블에 대한 검색, 삽입, 삭제 권한을 포함하고 있는 role_1 역할을 사용자 Hong에게 부여 

``` sql
GRANT role_1 TO Hong; 
```

원래는 `GRANT SELECT, INSERT, DELETE ON TO Hong`으로 쓰던 걸 위와 같이 간단하게 쓸 수 있게 됨.

![image](https://user-images.githubusercontent.com/64796257/188537053-184a71f7-ae21-432b-a6f6-c052e852ae89.png)

만약에 위 3명의 사용자에게 `UPDATE` 권한을 추가하고 싶다면 어떻게 해야 할까? 

각 사용자에게 직접 권한을 부여할 필요는 없다. 아래와 같이 `GRANT 문`을 통해 role_1 역할에 UPDATE 권한을 추가해주면 된다.

``` SQL
GRANT UPDATE ON 고객 TO role_1; 
```

ex4) 사용자 Hong에게 부여한 role_1 역할을 취소 
``` sql
REVOKE role_1 FROM Hong; 
```

이러면, 사용자 Hong은 고객 테이블에 대한 검색/삽입/삭제 연산을 수행할 수 없게 된다.

ex5) role_1 역할을 제거 
``` sql
DROP ROLE role_1; 
```

이러면, role_1 역할을 부여받았던 모든 사용자는 고객 테이블에 대한 검색/삽입/삭제 연산을 더 이상 수행할 수 없게 된다. 



































