2021년 3회 3번 문제 

■ DDL, DCL, DML - section 118 ~ 123 
① DDL (Data Define Language, 데이터 정의어) : DB를 구축하거나 수정할 목적으로 사용하는 언어 

- CREATE : SCHEMA, DOMAIN, TABLE, VIEW, INDEX를 정의 <==> DROP : 그것들을 삭제
- ALTER : TABLE에 대한 정의를 변경 

(1) CREATE - 잘 모르는 부분의 예시로 정리함

ex) 

소유권자의 사용자 id가 `홍길동`인 스키마 `대학교`를 정의하는 SQL문
``` sql 

CREATE SCHEMA 대학교 AUTHORIZATION 홍길동; 

```

`성별`을 `남` 또는 `여`와 같이 정해진 1개의 문자로 표현되는 도메인 SEX를 정의하는 SQL문
``` sql 

CREATE DOMAIN SEX CHAR(1) 
       DEFAULT ‘남’ 
       CONSTRAINT VALID-SEX CHECK(VALUE IN('남', '여'));

```

(2) ALTER 

`학생` 테이블에 최대 3문자로 구성되는 `학년` 속성을 추가함
``` SQL 

ALTER TABLE 학생 ADD 학년 VARCHAR(3); 

```

`학생` 테이블의 `학번` 필드의 데이터 타입과 크기를 `VARCHAR(10)`으로 정하고 NULL 값이 입력되지 않도록 함
``` SQL

ALTER TABLE 학생 ALTER 학번 VARCHAR(10) NOT NULL; 

```

(3) DROP 

- CASCADE : 제거할 요소를 참조하는 `다른 모든 개체들`을 함께 제거
- RESTRICT : 다른 개체가 제거할 요소를 `참조 중일 때` 제거를 취소 

② DCL (Date Control Language, 데이터 제어어) : 데이터의 보안, 무결성, 회복, 병행 제어 등을 정의하는데 사용하는 언어 

| 명령어 | 기능 | 
| --- | --- | 
| COMMIT | 명령에 의해 수행된 결과를 `실제 물리 디스크`에 저장 & DB 조작 작업 정상 완료를 관리자에게 알려줌 | 
| ROLLBACK | DB 조작 작업이 `비정상적으로 종료`되었을 때 원래 상태로 `복구` | 
| GRANT | DB 사용자에게 `사용 권한`을 `부여` | 
| REVOKE | DB 사용자의 사용 권한을 `취소` | 

(1) GRANT, REVOKE 

- 사용자 등급 

| 등급 | 설명 | 
| --- | --- | 
| DBA | 데이터베이스 관리자 |
| RESOURCE | 데이터베이스 및 테이블 생성 가능자 |
| CONNECT | 단순 사용자 |

- WITH GRANT OPTION : 부여받은 권한을 다른 사용자에게 다시 부여할 수 있는 권한을 부여 
- GRANT OPTION FOR : 다른 사용자에게 권한을 부여할 수 있는 권한을 취소 
- CASCADE : 권한 취소 시 권한을 부여받았던 사용자가 다른 사용자에게 부여한 권한도 연쇄적으로 취소 

사용자 ID가 `NABI`인 사람에게 데이터베이스 및 테이블을 생성할 수 있도록 하는 권한(`RESOURCE`)을 부여하는 SQL문
``` SQL 
GRANT RESOURCE TO NABI 
```

사용자 ID가 `NABI`인 사람에게 `<고객>` 테이블에 관한 모든 권한과 다른 사람에게 권한을 부여할 수 있는 권한까지 부여하는 SQL문 

``` SQL 

GRANT ALL ON 고객 TO NABI WITH GRANT OPTION; 

```

사용자 ID가 `STAR`인 사람에게 부여한 `<고객>` 테이블에 대한 권한 중 `UPDATE` 권한을 다른 사람에게 부여할 수 있는 권한만 취소하는 SQL문 

``` SQL 

REVOKE GRANT OPTION FOR UPDATE ON 고객 FROM STAR;

```

(3) DML (Data Manipulation Language, 데이터 조작어) : 데이터를 실질적으로 관리하는데 사용되는 언어 

| 명령문 | 기능 | 
| --- | --- | 
| SELECT | 테이블에서 튜플을 `검색` |
| INSERT | 테이블에서 튜플을 `삽입` |
| DELETE | 테이블에서 튜플을 `삭제` |
| UPDATE | 테이블에서 튜플의 내용을 `갱신` |

그 중에서 잘 모르는 `JOIN`에 관해서 정리하도록 하겠다. JOIN은 크게 `INNER JOIN`, `OUTER JOIN`으로 구분됨. 

- INNER JOIN

ex) 
![image](https://user-images.githubusercontent.com/64796257/174506267-6d8f22fa-c235-41cf-872e-11227fe2d546.png)

`<학생>` 테이블과 `<학과>` 테이블에서 `학과코드`값이 같은 튜플을 JOIN 해서 `학번`, `이름`, `학과코드`, `학과명`을 출력하는 sql문 
``` sql 
SELECT 학번, 이름, 학생.학과코드, 학과명 FROM 학생, 학과 WHERE 학생.학과코드 = 학과.학과코드; 
```

``` sql 
SELECT 학번, 이름, 학생.학과코드, 학과명 FROM 학생 NATURAL JOIN 학과; 
```

``` sql 
SELECT 학번, 이름, 학생.학과코드, 학과명 FROM 학생 JOIN 학과 USING(학과코드); 
```

![image](https://user-images.githubusercontent.com/64796257/174506970-7b6a79ad-5d9f-4427-b2f7-4860a5f77faf.png)

  
`<학생>` 테이블과 `<성적등급>` 테이블을 JOIN해서 각 학생의 `학번`, `이름`, `성적`, `등급`을 출력하는 sql문 

``` SQL 
SELECT 학번, 이름, 성적, 등급 FROM 학생, 성적등급 WHERE 학생.성적 BETWEEN 성적등급.최저 AND 성적등급.최고; 
```

![image](https://user-images.githubusercontent.com/64796257/174506983-ec0693da-c605-4d19-b2a1-a5e0f1fbbdc4.png)


- OUTER JOIN : JOIN 조건에 만족하지 않는 튜플도 결과로 출력하기 위한 JOIN 방법 

`<학생>` 테이블과 `<학과>` 테이블에서 `학과코드`값이 같은 튜플을 JOIN해서 `학번`, `이름`, `학과코드`, `학과명`을 출력하는 sql문.  
이때, `학과코드`가 입력되지 않은 학생도 출력한다. 

``` SQL 
SELECT 학번, 이름, 학생.학과코드, 학과명 
      FROM 학생 LEFT OUTER JOIN 학과 
      ON 학생.학과코드 = 학과.학과코드; 
```


``` SQL 
SELECT 학번, 이름, 학생.학과코드, 학과명 FROM 학생, 학과
      WHERE 학생.학과코드 = 학과.학과코드(+); 
```


``` SQL 
SELECT 학번, 이름, 학생.학과코드, 학과명 
      FROM 학생 RIGHT OUTER JOIN 학과 
      ON 학생.학과코드 = 학과.학과코드; 
```


``` SQL 
SELECT 학번, 이름, 학생.학과코드, 학과명 
      FROM 학생, 학과
      ON 학생.학과코드(+) = 학과.학과코드; 
```

![image](https://user-images.githubusercontent.com/64796257/174507016-3d593466-4e6a-40b6-8ace-1811f56ce28b.png)

`<학생>` 테이블과 `<학과>` 테이블에서 `학과코드`값이 같은 튜플을 JOIN해서 `학번`, `이름`, `학과코드`, `학과명`을 출력하는 sql문.  
이때, `학과코드`가 입력되지 않은 학생이나 학생이 없는 `학과코드`도 출력한다. 

``` SQL 
SELECT 학번, 이름, 학과.학과코드, 학과명 
    FROM 학생 FULL OUTER JOIN 학과
    ON 학생.학과코드 = 학과.학과코드; 
```

![image](https://user-images.githubusercontent.com/64796257/174507154-a9a6ab9a-5b74-44f8-8184-c18638051dbe.png)

* 주요 용어 정리 - 물론 자세한 내용을 알면 좋지만 차후에 정리하도록 하자.

- 프로시저(Procedure) : SQL을 사용해 작성한 일련의 작업을 저장해서 원할 때마다 저장한 작업을 수행하도록 하는 절차형 SQL 
- 트리거(Trigger) : 시스템에서 데이터의 삽입, 갱신, 삭제 등의 `이벤트`가 발생할 때마다 관련 작업이 자동으로 수행되도록 하는 절차형 SQL 














