## 이번 챕터에서 다룰 내용

`논리적 데이터 모델`중 가장 많이 사용하는 `관계 데이터 모델`에 대해서 살펴보겠다.

![image](https://user-images.githubusercontent.com/64796257/187815343-3085077f-0db8-4d85-acfc-ebd62c0429dd.png)

## 01 관계 데이터 모델의 개념

### 기본 용어

![image](https://user-images.githubusercontent.com/64796257/187815854-97c20715-1d55-41d5-a688-10cd91d0126f.png)

- 속성(Attribute) = 열 ⇒ 해당 파일의 `필드(field)`에 대응되는 개념 ⇒ 전체 개수를 `차수(degree)`
- 튜플(Tuple) = 행 ⇒ 해당 파일의 `레코드(record)`에 대응되는 개념 ⇒ 전체 개수를 `cardinality`
- 도메인(Domain) : 속성 하나가 가질 수 있는 모든 값의 집합 ex) `등급` 속성의 vip, gold, silver, bronze = `등급 속성의 도메인`  
                : 또는 선언된 변수의 `데이터 타입` ex) INT, CHAR(20) 등등

### Relation과 DB의 구성

- Relation 스키마 & 인스턴스
![image](https://user-images.githubusercontent.com/64796257/187816524-9775983a-8d05-4e2a-963c-dd6fa7bf0605.png)

- DB 스키마 & 인스턴스
![image](https://user-images.githubusercontent.com/64796257/187818974-11be5373-adb8-4945-ad5b-22bb7bebbb10.png)

### Relation 특성

- tuple의 유일성 : 하나의 relation에 동일한 튜플은 없다
- tuple의 무순서 : 튜플의 순서는 무의미하다
- 속성의 무순서 : 속성 사이의 순서는 무의미하다
- 속성의 원자성 : 하나의 속성에는 원자 값을 가져야 한다.  
ex) 아래와 같은 상황이 있으면 안된다  
![image](https://user-images.githubusercontent.com/64796257/187819553-9607cfcb-cc6c-48b1-a8da-c9959c3f81e0.png)

### 키의 종류 

![image](https://user-images.githubusercontent.com/64796257/187819628-17b58e9c-546e-4c54-b268-341516f83db0.png)

```
유일성(uniqueness) : 하나의 relation에서 key로 지정된 속성 값은 튜플마다 달라야 한다
최소성(minimality) : 꼭 필요한 최소한의 속성들로만 키를 구성하는 특성
```

![image](https://user-images.githubusercontent.com/64796257/187820619-512a635a-9841-4ea1-b265-50a275049465.png)

- 슈퍼키 : `유일성`을 만족하는 속성 또는 속성들의 집합
- 후보키 : `유일성` + `최소성`을 만족하는 속성 또는 속성들의 집합
- 기본키(`PK` - Primary Key) : 여러 개의 `후보키` 중에서 `튜플을 구별`하기에 가장 `적합`하다고 판단되는 키
  - 기본키를 선택할 때 고려해야 할 기준
    - 기본키는 null 값을 가지면 안된다
    - 기본키는 값이 자주 변경되면 안된다
    - 가장 단순한 후보키를 선택하는 것이 좋다

![image](https://user-images.githubusercontent.com/64796257/187820027-b5b0ca79-bfa3-441c-98fb-2f5062e5c758.png)

- 대체키 : `기본키`로 선택받지 못한 `후보키`
- 외래키(`FK` - Foreign Key) : 어떤 `relation에 소속된` 속성 또는 속성 집합이 `다른 relation의 기본키`가 되는 키

ex) ![image](https://user-images.githubusercontent.com/64796257/187820658-fee841b0-0c7c-4499-bc58-f86e1d83e5cb.png)

`스키마`에 `인스턴스`들을 붙여서 표현하면 아래와 같다.

![image](https://user-images.githubusercontent.com/64796257/187821335-04a2cedb-d6bb-4112-b161-a8e36452df94.png)

## 02 관계 데이터 모델의 제약 

: `관계 데이터 모델`에서 정의하고 있는 기본 제약 사항은 `키`와 관련한 `무결성 제약조건`이다.  
: `무결성(Integrity)`이란 데이터에 결함이 없는 상태, 즉 데이터가 정확하고 유효하게 유지된 상태를 말한다.  

: `무결성 제약 조건`의 목적은 DB에 저장된 `데이터의 무결성을 보장`하고 DB의 상태를 `일관되게 유지`하는 것이다.

: 관계 데이터 모델이 기본으로 포함하고 있는 `무결성 제약 조건`에는 `개체 무결성 제약 조건`과 `참조 무결성 제약 조건`이 있다. 

### 개체 무결성 제약 조건 : `기본키`를 구성하는 모든 속성은 null 값을 가질 수 없다.

⇒ 만약에 `null 값`을 가진다면 `튜플의 유일성`을 판단할 수 없게 되면서 `기본키의 본래 목적을 상실`함

### 참조 무결성 제약 조건 : `외래키`는 참조할 수 없는 값을 가질 수 없다.

ex)

![image](https://user-images.githubusercontent.com/64796257/187822304-afa2881a-2adf-40d3-abcc-28cbf3c2b146.png)

`고객 relation`에는 `cherry`라는 고객아이디가 없다. 즉, `cherry`는 참조할 수 없는 값이다.  
따라서, 이 상황은 `참조 무결성 제약 조건`을 위반한 상황이다.

하지만, `null값`이 있다고 해서 `참조 무결성 제약 조건`을 무조건 위반했다고는 볼 수 없다. 

ex) 

![image](https://user-images.githubusercontent.com/64796257/187822648-32ff57e3-df7d-4d4d-8f11-0f4df21ec1c1.png)

`null값`은 `주문한 고객`이 `누구인지 모를 뿐` 존재하지 않는 고객이라고 판단할 수는 없기 때문이다.

이러한 `참조 무결성 제약 조건` 때문에 `DB의 상태`가 빈번하게 변경된다면 만족시키기 어렵다.
- case 
  1) 삽입 : 새로운 튜플을 삽입할 때 `고객 아이디 속성값`에 존재하는 값 또는 null이 `주문 고객 속성값`에 꼭 있어야 한다.  
그렇지 않으면 제약 조건을 위반한다.
  2) 삭제 : `주문 relation`에서 `주문 고객이 apple`인 주문 정보를 삭제한다면  
`고객 relation`에서 `apple`을 참조하고 있던 튜플은 `부정확한 정보`를 가진 튜플이 된다.
  3) 기본키 변경 : `고객 relation`에서 이름이 `김현준`인 고객이 `고객아이디`를 `grape`로 바꾸는 것 자체는 문제가 없다.  
하지만, `바꾼 값 grape`가 `주문 relation`의 `주문고객` 정보에는 없는 값이므로 문제가 발생한다.

이런 여러 가지 상황이 생길 수 있지만 관련 작업들은 `DBMS`에서 자동으로 수행된다. 














