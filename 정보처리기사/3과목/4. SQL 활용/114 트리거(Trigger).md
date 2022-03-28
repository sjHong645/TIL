## 트리거(Trigger) 

: DB 시스템에서 데이터의 삽입, 갱신, 삭제 등의 `이벤트`가 발생할 때마다 `작업이 자동`으로 수행되는 `절차형 SQL` 

- 트리거는 `DB`에 저장되고 `데이터 변경` 및 `무결성 유지`, `로그 메시지 출력` 등의 목적으로 사용됨
- 트리거 구문에는 DCL을 사용하지 않음. 

## 트리거의 구성 

![image](https://user-images.githubusercontent.com/64796257/160497259-cc8a1fcb-1410-4bc7-b2aa-9658484626e2.png)

## 트리거의 생성 - CREATE 

⇒ 트리거를 생성하기 위해서 CREATE TRIGGER 명령어를 사용하면 됨.

- 문법 

![image](https://user-images.githubusercontent.com/64796257/160497524-36e0bf63-9634-48d7-b89a-31590e76524e.png)

- 예제 

`<학생>` 테이블에 `새로운 튜플`을 `삽입`할 때 삽입되는 튜플에 `학년` 정보가 `누락`됐으면  
`학년` 필드에 `신입생`을 치환하는 트리거를 `학년정보_tri`라는 이름으로 정의하시오. 

``` sql

CREATE TRIGGER 학년정보_tri BEFORE INSERT ON 학생  -- 1
REFERENCING NEW AS new_table                      -- 2
FOR EACH ROW                                      -- 3
WHEN (new_table.학년 is NULL)                     -- 4
  BEGIN 
    :new_table.학년 := '신입생';                   -- 4-1
  END; 

```

1. `학생` 테이블에 튜플을 `삽입(INSERT)`하기 전에 `학년정보_tri` 트리거를 생성한다.
2. 새로 추가될 튜플의 집합 NEW의 별명을 `new_table`이라 지정함
3. `모든 튜플`을 대상으로 진행
4. `새로 추가`될 튜플 값의 속성 중 `학년`값이 `NULL`이라면 아래 동작을 진행한다.
 4-1. `새로 추가`될 튜플 값의 속성 중 `학년` 값이 `신입생`이라는 값을 가지도록 한다

## 트리거의 삭제 - DROP

⇒ 트리거를 제거하기 위해 사용하는 명령어 

- 문법 

``` 
DROP TRIGGER 트리거이름; 
```

EX) `학년정보_tri`라는 트리거를 제거하는 SQL 문

``` sql  
DROP TRIGGER 학년정보_tri;
```



















