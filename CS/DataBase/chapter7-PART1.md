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

#### 기본

ex1) `고객` 테이블에서 고객아이디, 고객이름, 등급 속성을 검색

``` sq1
SELECT 고객아이디, 고객이름, 등급 FROM 고객; 
```

![image](https://user-images.githubusercontent.com/64796257/188297171-64b488de-292a-4ac1-a9ad-99a9f3466310.png)

ex2) `고객` 테이블에 존재하는 모든 속성 검색

``` sq1
SELECT * FROM 고객; 
```

![image](https://user-images.githubusercontent.com/64796257/188297181-e8c0c0b9-6e08-4479-80e6-a2c0f738d0d2.png)

ex3) `제품` 테이블에서 `제조업체` 검색

- ALL 사용한 경우

``` sq1
SELECT ALL 제조업체 FROM 제품;
```

![image](https://user-images.githubusercontent.com/64796257/188297212-955f1474-f63b-4979-a29a-2470b01fcf2d.png)

- DISTINCT 사용한 경우 - `중복되는 값`을 빼고 출력
``` sq1
SELECT DISTINCT 제조업체 FROM 제품;
```

![image](https://user-images.githubusercontent.com/64796257/188297228-6f9f5a6b-d816-463c-87db-983157e5cc65.png)

- 둘 다 없는 경우
``` sq1
SELECT 제조업체 FROM 제품;
```

![image](https://user-images.githubusercontent.com/64796257/188297203-aa2095d5-4229-40ca-83e0-4bd8eed1709f.png)


ex4) `제품` 테이블에서 `제품명`과 `단가`를 검색하되 `단가`를 `가격`이라는 새 이름으로 출력

``` sq1
SELECT 제품명, 단가 AS 가격 FROM 제품;
```

![image](https://user-images.githubusercontent.com/64796257/188297261-a7398bb6-1269-474b-94ad-c6f0e6eeb0c4.png)

ex5) `제품` 테이블에서 `제품명`과 `단가`를 검색하되 `단가`에 500원을 더해 `조정단가`라는 새 이름으로 출력

``` sq1
SELECT 제품명, 단가 + 500 AS 조정단가 FROM 제품;
```

![image](https://user-images.githubusercontent.com/64796257/188297277-f02fc520-c146-4474-8bd0-a69cadc73526.png)

#### WHERE 사용하는 경우

ex1) 제품 테이블에서 한빛제과가 제조한 제품의 제품명, 재고량, 단가를 검색
``` sql
SELECT 제품명, 재고량, 단가 FROM 제품 WHERE 제조업체='한빛제과';
```

![image](https://user-images.githubusercontent.com/64796257/188297406-57e5f4cd-0786-4efb-9493-5159c541e8c7.png)

ex2) 주문 테이블에서 apple 고객이 15개 이상 주문한 주문제품, 수량, 주문일자 검색
``` sql
SELECT 주문제품, 수량, 주문일자 FROM 주문 WHERE 주문고객='apple' AND 수량 >= 15; 
```

![image](https://user-images.githubusercontent.com/64796257/188297399-6d1505ee-4740-4456-96d2-cddd34c6c5c3.png)


ex3) 주문 테이블에서 apple 고객이 주문했거나 15개 이상 주문된 제품의 주문제품, 수량, 주문일자, 주문고객 검색
``` sql
SELECT 주문제품, 수량, 주문일자, 주문고객 FROM 주문 WHERE 주문고객='apple' OR 수량 >= 15;
```

![image](https://user-images.githubusercontent.com/64796257/188297428-69f25724-d425-47f4-9efb-78d4a7b93924.png)

ex4) 제품 테이블에서 단가가 2000원 이상이면서 3000원 이하인 제품의 제품명, 단가, 제조업체 검색
``` sql
SELECT 제품명, 단가, 제조업체 FROM 제품 WHERE 단가 >= 2000 AND 단가 <= 3000; 
```

![image](https://user-images.githubusercontent.com/64796257/188297448-122db7b3-a8ce-48a0-af18-fa5b3083da30.png)

#### LIKE를 사용한 경우

ex1) 고객 테이블에서 성이 `김`씨인 고객의 고객이름, 나이, 등급, 적립금을 검색 

``` sql
SELECT 고객이름, 나이, 등급, 적립금 FROM 고객 WHERE 고객이름 LIKE '김%';
```

![image](https://user-images.githubusercontent.com/64796257/188298156-9296c3fc-fe3f-4572-a9dd-0ea557967495.png)

ex2) 고객 테이블에서 `고객아이디`가 5자인 고객의 고객아이디, 고객이름, 등급을 검색

``` sql
SELECT 고객아이디, 고객이름, 등급 FROM 고객 WHERE 고객아이디 LIKE '_____'; // _가 5개 있는 상황
```

![image](https://user-images.githubusercontent.com/64796257/188298194-2f3332a2-b9a4-4fef-bff1-43e0da5e2883.png)

#### NULL을 이용한 검색 

ex1) 고객 테이블에서 나이가 아직 입력되지 않은 고객이름을 검색 

``` sql
SELECT 고객이름 FROM 고객 WHERE 나이 IS NULL;
```
![image](https://user-images.githubusercontent.com/64796257/188298227-c3908cf9-08d6-4f20-a5d0-f387889b45c8.png)

ex2) 고객 테이블에서 `나이`가 이미 입력된 고객이름 검색 

``` sql
SELECT 고객이름 FROM 고객 WHERE 나이 IS NOT NULL;
```

![image](https://user-images.githubusercontent.com/64796257/188298242-81e7ff1c-63a9-4bd0-813a-243be353b175.png)


#### 정렬 검색 

ex1) 고객 테이블에서 고객이름, 등급, 나이를 검색하되 나이를 기준으로 내림차순 정렬

``` sql
SELECT 고객이름, 등급, 나이 FROM 고객 ORDER BY 나이 DESC;
```

![image](https://user-images.githubusercontent.com/64796257/188298277-1955aae3-da71-4401-a080-a90a1e425625.png)

ex1-1) 오름차순 정렬

``` sql
SELECT 고객이름, 등급, 나이 FROM 고객 ORDER BY 나이 ASC;

또는 

SELECT 고객이름, 등급, 나이 FROM 고객 ORDER BY 나이;
```

ex2) 주문 테이블에서 수량이 10개 이상인 주문의 주문고객, 주문제품, 수량, 주문일자를 검색.  
(단, 주문제품 기준으로 오름차순 정렬 & 같은 제품이라면 수량을 기준으로 내림차순 정렬) 

``` sql
SELECT 주문고객, 주문제품, 수량, 주문일자 
        FROM 주문
        WHERE 수량 >= 10
        ORDER BY 주문제품 ASC, 수량 DESC; 
```

![image](https://user-images.githubusercontent.com/64796257/188298347-5f9eb29e-d316-422e-a4ea-f62d11824975.png)

#### 집계 함수 

| 함수 | 의미 | 사용가능한 속성의 타입 | 
| --- | --- | --- | 
| COUNT | 속성 값 개수 | 모든 데이터 | 
| MAX | 속성 최댓값 | 모든 데이터 | 
| MIN | 속성 최솟값 | 모든 데이터 | 
| SUM | 속성 값의 합계 | 숫자 데이터 | 
| AVG | 속성 값의 평균 | 숫자 데이터 | 

⇒ SELECT 절이나 HAVING 절에서만 사용할 수 있음 

ex1) 제품 테이블에서 모든 제품의 단가 평균 검색 

``` sql
SELECT AVG(단가) FROM 제품;
```

![image](https://user-images.githubusercontent.com/64796257/188298451-b51c1333-71ed-4053-bbbb-a13ebdec1b97.png)

ex2) 한빛제과에서 제조한 제품의 재고량 합계를 제품 테이블에서 검색 

``` sql
SELECT SUM(재고량) FROM 제품 WHERE 제조업체='한빛제과';
```

![image](https://user-images.githubusercontent.com/64796257/188298480-73f0fdaa-ca11-41de-be8f-4e861fc117d8.png)

ex3) 고객 테이블에서 고객이 몇 명 등록되어 있는지 검색

``` sql
SELECT COUNT(고객아이디) AS 고객수 FROM 고객;  // 고객 아이디 속성을 이용해 계산 
                                             // 결과값 7
```

``` sql
SELECT COUNT(나이) AS 고객수 FROM 고객;  // 나이 속성을 이용해 계산 
                                        // 결과값 6
                                        // 6이 나온 이유는 '나이' 속성에 NULL값이 하나 포함되어 있기 때문이다
```


``` sql
SELECT COUNT(DISTINCT 제조업체) AS 고객수 FROM 고객;  // 제조업체 속성을 이용해 계산 (단, 중복없음)
                                                    // 결과값 3
```

#### GROUP BY 

ex1) 주문 테이블에서 주문제품별 수량의 합계를 검색 

``` sql
SELECT 주문제품, SUM(수량) AS 총주문수량 
                FROM 주문
                GROUP BY 주문제품;
```

![image](https://user-images.githubusercontent.com/64796257/188298631-a7bd1182-8c09-47d8-a036-ce7fb53e5aeb.png)

ex2) 
```
제품 테이블에서 제조업체별로 제조한 제품의 개수와 제품 중 가장 비싼 단가를 검색하되
제품의 개수는 제품수라는 이름으로 출력하고 가장 비싼 단가는 최고가라는 이름으로 출력
```

``` sql
SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가 
                    FROM 제품
                    GROUP BY 제조업체;
```

![image](https://user-images.githubusercontent.com/64796257/188298694-11967cf0-7bec-4057-be75-59873f1e1a6c.png)

- `GROUP BY`에 대한 조건문은 `HAVING`을 이용한다.

ex3) 제품 테이블에서 제품을 3개 이상 제조한 제조업체별로 제품의 개수와 제품 중 가장 비싼 단가를 검색

``` sql
SELECT 제조업체, COUNT(*) AS 제품개수, MAX(단가) AS 최고가 
                        FROM 제품
                        GROUP BY 제조업체 HAVING COUNT(*) >= 3; 
```

![image](https://user-images.githubusercontent.com/64796257/188298754-668d0a13-184e-4bcf-a58d-6999b2260d62.png)

ex4) 주문 테이블에서 각 주문고객이 주문한 제품의 총주문수량을 주문제품별로 검색 

⇒ `주문제품`을 기준으로 1차 검색 ⇒ 각 주문제품을 `주문 고객`을 기준으로 2차 검색 

``` sql
SELECT 주문고객, 주문제품, SUM(수량) AS 총주문수량 
                        FROM 주문
                        GROUP BY 주문제품, 주문고객; 
```

![image](https://user-images.githubusercontent.com/64796257/188298815-c6f48eb3-0a97-498b-85e1-4360281f279c.png)

#### 조인 검색 

ex1) 판매 데이터베이스에서 banana 고객이 주문한 제품의 이름

``` sql
SELECT 제품.제품명 FROM 제품, 주문
        WHERE 판매.주문고객 = 'banana' AND 제품.제품번호 = 주문.주문제품;
```

ex2) 판매 데이터베이스에서 나이가 30세 이상인 고객이 주문한 제품의 주문제품과 주문일자를 검색

``` sql
SELECT 주문.주문제품, 주문.주문일자 
                        FROM 고객,주문
                        WHERE 고객.나이 >= 30 AND 고객.고객아이디 = 주문.주문고객;
                        
```
