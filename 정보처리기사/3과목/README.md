3장은 문제 위주로 푼다.

## SQL 분류 

1) DDL(Date Define Language, 데이터 정의어) 

- `논리적 데이터 구조`와 `물리적 데이터 구조`의 사상을 정의

- 3가지 유형 

| 명령어 | 기능 | 
| --- | --- | 
| CREATE | SCHEMA, DOMAIN, TABLE, VIEW, INDEX를 `정의` | 
| ALTER | TABLE에 대한 정의 변경 | 
| DROP | SCHEMA, DOMAIN, TABLE, VIEW, INDEX를 `삭제` | 

2) DML(Data Manipulation Language, 데이터 조작어) 

- 4가지 유형 

| 명령어 | 기능 | 
| --- | --- | 
| SELECT | 테이블에서 `조건에 맞는` 튜플을 `검색` | 
| INSERT | 테이블에`새 튜플`을 `삽입` | 
| DELETE | 테이블에서 조건에 맞는 튜플을 `삭제` | 
| UPDATE | 테이블에서 조건에 맞는 튜플을 `변경` |

3) DCL(Data Control Language, 데이터 제어어) 

- 종류 

| 명령어 | 기능 | 
| --- | --- | 
| COMMIT | 명령에 의해 수행된 결과를 실제 `물리적 디스크`에 저장하고 `DB 조작 작업`이 정상적으로 `완료`되었음을 관리자에게 공지 | 
| ROLLBACK | DB 조작 작업이 `비정상적`으로 `종료`되었을 때 `원상태로 복구` | 
| GRANT | DB 사용자에게 `사용 권한`을 `부여` | 
| REVOKE | DB 사용자의 `사용 권한`을 `취소` | 




























