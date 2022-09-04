- SQL(Structured Query Language) : `DB를 위한 표준 질의어`로 많이 사용되는 언어

![image](https://user-images.githubusercontent.com/64796257/188293773-2ee1c764-52e0-44d5-978e-0479f4d0e793.png)

- 데이터 정의어 : `테이블`을 `생성`하고 `변경/제거`하는 기능 제공
- 데이터 조작어 : 테이블에 `새 데이터`를 `삽입`하거나 테이블에 저장된 데이터를 `수정/삭제/검색`하는 기능 제공
- 데이터 제어어 : `보안`을 위해 데이터에 대한 `접근 및 사용 권한`을 사용자 별로 `부여 및 취소`하는 기능 제공. 

``` 
SQL 언어는 Live SQL에 맞춰 입력되었다.
```

## 02 데이터 정의어 

| 기능 | SQL문 | 
| --- | --- | 
| 테이블 생성 | CREATE TABLE | 
| 테이블 변경 | ALTER TABLE | 
| 테이블 삭제 | DROP TABLE | 

### 테이블 생성 - CREATE TABLE

주저리 주저리 개념 설명을 하는 것보다 `예제`를 통해 코드를 보는 게 더 좋다.

ex1)  
```
고객 테이블은 고객아이디, 고객이름, 나이, 등급, 직업, 적립금 속성으로 구성되고 고객아이디 속성은 기본키이다.   
고객이름과 등급 속성은 반드시 값을 입력해야 하고 적립금 속성은 값을 입력하지 않으면 0이 기본으로 입력되도록 고객 테이블을 생성
```

``` sql
CREATE TABLE 고객 ( 
    고객아이디 VARCHAR(20) NOT NULL, 
    고객이름 VARCHAR(10) NOT NULL, 
    나이 INT, 
    등급 VARCHAR(10) NOT NULL, 
    직업 VARCHAR(20), 
    적립금 INT DEFAULT 0, 
    PRIMARY KEY(고객아이디) 
)
```

ex2)  
```
제품 테이블은 제품번호, 제품명, 재고량, 단가, 제조업체 속성으로 구성되고 제품번호 속성이 기본키이다.
재고량이 항상 0개 이상 10000개 이하를 유지하도록 제품 테이블을 생성
```

``` sql
CREATE TALBE 제품(
    제품번호 CHAR(3) NOT NULL,
    제품명 VARCHAR(20), 
    재고량 INT,
    단가 INT, 
    제조업체 VARCHAR(20), 
    PRIMARY KEY(제품번호), 
    CHECK(재고량 >= 0 AND 재고량 <= 10000)
);
```

ex3) 
```
주문 테이블은 주문번호, 주문고객, 주문제품, 수량, 배송지, 주문일자 속성으로 구성되고 주문번호 속성이 기본키다.
주문고객 속성이 고객 테이블의 고객아이디 속성을 참조하는 외래키이고, 
주문제품 속성이 제품 테이블의 제품번호 속성을 참조하는 외래키가 되도록 주문 테이블을 생성
```

``` sql
CREATE TALBE 주문 (
    주문번호 CHAR(3) NOT NULL, 
    주문고객 VARCHAR(20), 
    주문제품 CHAR(3),
    수량 INT,
    배송지 VARCHAR(30),
    주문일자 DATE,
    FOREIGN KEY(주문고객) REFERENCES ON 고객(고객아이디),
    FOREIGN KEY(주문제품) REFERENCES ON 제품(제품번호)
);
```

### 테이블 변경 - ALTER TABLE

ex1) 고객 테이블에 `가입날짜` 속성을 추가 

``` sql
ALTER TABLE 고객 ADD 가입날짜 DATE;
```

ex2) 고객 테이블의 `가입날짜` 속성을 삭제

``` sql
ALTER TABLE 고객 DROP COLUMN 가입날짜;
```

ex3) 고객 테이블에 20세 이상의 고객만 가입할 수 있다는 데이터 무결성 제약조건을 추가

``` sql
ALTER TABLE 고객 ADD CONSTRAINT CHK_AGE CHECK(나이 >= 20);
```

ex4) 고객 테이블에 20에 이상의 고객만 가입할 수 있다는 데이터 무결성 제약조건 삭제

``` sql
ALTER TABLE 고객 DROP CONSTRAINT CHK_AGE;
```

### 테이블 삭제 - DROP TABLE 

ex) 배송업체 테이블 삭제 

``` sql
DROP TABLE 배송업체;
```

## 03 데이터 조작 

| 기능 | SQL문 | 
| --- | --- | 
| 데이터 검색 | SELECT | 
| 데이터 삽입 | INSERT | 
| 데이터 수정 | UPDATE | 
| 데이터 삭제 | DELETE |

### 데이터 검색 - SELECT 



























