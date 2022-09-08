## 이번 챕터에서 정리할 내용
![image](https://user-images.githubusercontent.com/64796257/187579251-fceb8d0f-b160-425c-89b5-265650ac0720.png)

## 01 데이터 모델링과 데이터 모델의 개념

데이터베이스(DB)는 `현실 세계`에 있는 데이터 중 `필요한 데이터`를 `컴퓨터`에 저장한 것이다.

ex) 현실 세계의 병원 ⇒ 컴퓨터 세계의 DB 

![image](https://user-images.githubusercontent.com/64796257/187579509-e8a38ba9-5e14-49ff-a75b-244e4625839a.png)

이렇게 `현실 세계의 정보`를 `컴퓨터의 DB`로 `변환`한 과정을 `데이터 모델링(Data Modeling)`이라 한다.

### 데이터 모델링

현실 세계의 정보를 있는 그대로 컴퓨터에는 넣을 수는 없다. `DB에 저장`해서 `관리할 만한 가치`가 있는 `중요 데이터`만을 찾아내야 한다.  
이러한 작업을 `추상화(abstraction)`라 한다.

ex) 코끼리 

![image](https://user-images.githubusercontent.com/64796257/187579882-5be061a3-bb97-49ff-bb72-c71e1105229a.png)

위와 같이 `현실 세계`의 데이터를 `컴퓨터 세계`의 데이터로 한 번에 옮기는 건 어렵다.  
그래서 아래와 같이 `2개의 단계`를 거쳐서 변환을 진행한다. 

![image](https://user-images.githubusercontent.com/64796257/187580332-64f8d585-7bbd-49a8-9ee5-e3e3a8fb4078.png)

- 개념적 모델링(Conceptual Modeling) : `현실 세계`의 중요 데이터를 추출 ⇒ `개념 세계`로 변환하는 작업
- 논리적 모델링(Logical Modeling) : `개념 세계`의 데이터 ⇒ `DB`에 저장할 구조로 표현하는 작업

물론, 이 둘을 명확하게 구분하지는 않고 합쳐서 `데이터 모델링`이라 부른다. 이는 `DB 설계`의 핵심과정이다. 

### 데이터 모델

: `데이터 모델링의 결과물`을 표현해서 데이터 모델링을 보다 쉽게 할 수 있도록 도와주는 도구

- 개념적 데이터 모델 : `현실 세계`를 `개념적 모델링`해서 `DB의 개념적 구조`로 표현하는 도구   
                      대표적으로 개체-관계 모델(E-R Model)을 많이 사용함
                      
- 논리적 데이터 모델 : `개념적 구조`를 `논리적 모델링`해서 `DB의 논리적 구조`로 표현하는 도구    
                      대표적으로 관계 데이터 모델(relational data Model)을 많이 사용함

- 구성 : 자료구조, 연산, 제약조건 

![image](https://user-images.githubusercontent.com/64796257/187581830-38999092-1fe9-46bd-abce-8cdd1ce834d1.png)

ex) 아파트 짓는 일 - 데이터 모델링과 데이터 모델의 개념 설명 예시

1) 사람들의 요구 사항을 반영할 수 있도록 `설계도를 꼼꼼히 작성` 
  ⇒ 사람들이 원하는대로 설계도를 그리는 과정 = `개념적 데이터 모델링`
  ⇒ 설계도를 그릴 때 사용하는 방법이나 도구 = `개념적 데이터 모델`
  
2) 완성된 설계도를 토대로 `모델하우스`를 통해 요구 사항이 제대로 반영되었는지 확인
  ⇒ 설계도를 토대로 모델하우스를 만드는 과정 = `논리적 데이터 모델링`
  ⇒ 모델하우스를 만들 때 사용하는 방법이나 도구 = `논리적 데이터 모델`

## 02 E-R 모델 (개체-관계 모델) 

: `개체(Entity)`와 `개체 간의 관계(Relationship)`를 이용해 현실 세계를 `개념적 구조`로 표현하는 방법

: 현실 세계를 `E-R 모델`을 이용해 개념적으로 모델링해 그림으로 표현한 것을 `개체-관계 다이어그램`이라 한다.

- 주요 개념 : 개체, 속성, 관계 

### 개체(Entity) 

: 저장할 만한 가치가 있는 중요 데이터를 가지고 있는 사람이나 사물. 개념적 모델링의 중요 요소.  
ex) 현실 세계의 서점 ⇒ 중요 데이터를 가진 `고객(사람)`과 `책(사물)`이 각각 개체가 될 수 있음

ex) 학교 운영 ⇒ 중요 데이터로 `학과`나 `과목`이 개체가 될 수 있음

`개체`는 다른 개체와 구별되는 `이름`을 갖고 있고 각 개체만의 고유한 특성이나 상태인 `속성`을 하나 이상 갖고 있다.  
`개체`를 고유의 이름과 속성으로 정의한 것을 `개체 타입`이라 한다.

ex) 서점의 고객 객체 => 이름,주소,연락처,적립금의 `속성`으로 구성된다면 아래와 같이 정의할 수 있다

![image](https://user-images.githubusercontent.com/64796257/187627752-4255a768-4509-4b4a-b570-c8fd81221042.png)

- 개체 인스턴스 : 개체를 구성하고 있는 속성이 `실제 값`을 가짐으로써 실체화된 개체

E-R 다이어그램에서는 `개체`를 `사각형`으로 표현하고 그 안에 `개체의 이름`을 표기한다. 

![image](https://user-images.githubusercontent.com/64796257/187628197-6819d0f4-a762-486e-90a0-8c5a81dc5c6f.png)

### 속성(Attribute) 

: `개체`가 가지고 있는 고유의 특성. `관련 있는 속성들`을 모아 `개체를 구성`하면 중요한 의미를 표현할 수 있다.

E-R 다이어그램에서는 `속성`을 `타원`으로 표현하고 그 안에 `속성의 이름`을 표기한다. 

![image](https://user-images.githubusercontent.com/64796257/187631009-672fdd06-c31e-4990-8b70-086a9c634e22.png)

속성은 아래와 같이 다양한 기준으로 분류할 수 있다.  
![image](https://user-images.githubusercontent.com/64796257/187631130-e787d383-26be-4440-baf7-6253aba2b764.png)

- 속성 값 개수 
  - 특정 개체를 구성하는 속성 값이 `하나`이면 `단일 값 속성`
  - 특정 개체를 구성하는 속성 값이 `여러 개`면 `다중 값 속성`

![image](https://user-images.githubusercontent.com/64796257/187631698-62163a4e-24b4-4bdb-9752-66a497335178.png)

- 의미 분해 가능성 
  - 의미를 더는 분해할 수 없는 `단순 속성`
  - 의미를 분해할 수 있어 여러 개의 의미를 포함한 `복합 속성`

![image](https://user-images.githubusercontent.com/64796257/187631762-03d4c96b-4e77-465a-9672-be44995b82cd.png)

- 기존 속성 값에서 유도되어 결정되는 `유도 속성`

![image](https://user-images.githubusercontent.com/64796257/187632123-91f52ecf-363d-416e-addd-3722de2f285d.png)

- 널(null) 속성 : null 값이 허용되는 속성

- 키(key) 속성 : 개체 집합에 존재하는 각 개체의 인스턴스들을 `식별`하는데 사용

### 관계(Relationship)

: `개체와 개체`가 맺고 있는 의미 있는 연관성. 개체 집합들 사이의 대응 관계, 즉 매핑(mapping)을 의미한다.

ex) 고객은 책을 구매한다. ⇒ `고객`과 `책`사이의 관계로 `구매한다`라는 동작이 관계가 된다.

E-R 다이어그램에서 `관계`는 `마름모`로 표현된다. 

![image](https://user-images.githubusercontent.com/64796257/187633895-5077eefe-ad9e-4c62-bb33-889c71e47793.png)  
1) `고객`과 `책` 사이에 정의되는 `구매 관계`
2) `구매 관계`가 가지고 있는 `구매일자`, `결제방식` 속성 

#### 관계 유형 

- 일대일(1:1) 관계 : 개체 A의 인스턴스가 개체 B의 인스턴스가 서로 `하나`와 관계를 맺을 수 있는 경우 

![image](https://user-images.githubusercontent.com/64796257/187635211-d729240f-ea1c-48ce-8958-1d7c999fab81.png)

- 일대다(1:n) 관계 : 개체 A의 인스턴스가 개체 B의 인스턴스 `여러 개`와 관계를 맺지만  
                    개체 B의 인스턴스는 개체 A의 인스턴스 `하나`와 관계를 맺을 수 있는 경우
                    
![image](https://user-images.githubusercontent.com/64796257/187635661-cad8c7d4-b536-4faf-98e6-3c6475c5ccae.png)


- 다대다(n:m) 관계 : 개체 A의 인스턴스가 개체 B의 인스턴스 `여러 개`와 관계를 맺고  
                    개체 B의 인스턴스가 개체 A의 인스턴스 `여러 개`와 관계를 맺을 수 있는 경우

![image](https://user-images.githubusercontent.com/64796257/187635828-079401ac-bd5b-4dd7-a01a-63808279755e.png)

#### 관계의 참여 특성

개체 A와 B 사이의 관계에서 

`개체 A의 모든 인스턴스`가 `관계`에 반드시 참여해야 하는 상황을 개체 A가 `관계`에 `필수적 참여한다(또는 전체 참여한다)`라고 한다. 

`일부`만 참여한다면 `선택적 참여한다(또는 부분 참여한다)`라고 한다. 

ex) 고객 개체와 책 개체 사이의 구매관계

=> 구매 관계에서 `모든 고객`이 `책`을 반드시 사야 한다면 `고객 객체`는 `구매 관계`에 필수적 참여한다고 할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/187636821-5d6bc1f2-54c7-4b82-98e9-fd0d88b3ad56.png)

#### 관계의 종속성

`개체 B`가 `개체 A`의 존재 여부에 따라 `의존적`이라면 `개체 B`는 `개체 A`에 `종속되어 있다`고 한다.

`개체 B`가 `개체 A`에 종속되면 `개체 A`가 있어야 `개체 B`가 존재할 수 있는 것이기 때문에  
`개체 A`가 삭제된다면 `개체 B`도 함께 삭제된다.

이때, 개체 B = 약한 개체 / 개체 A = 강한 개체 라 한다.

![image](https://user-images.githubusercontent.com/64796257/187638503-a0d8a8c3-79d9-43d2-b9a0-8ba1c6ae6565.png)

### 04 E-R 다이어그램

![image](https://user-images.githubusercontent.com/64796257/187638752-8dc26deb-ea0b-4072-be15-5b9405d4aa2b.png)

## 03 논리적 데이터 모델 

### 개념 및 특성 

E-R 모델의 경우 `현실 세계`를 `개념적 구조`로 모델링하는데 사용하기 때문에 어떤 `DBMS(DB 관리 시스템)`로 DB를 구축해도 상관없다.  

하지만, `논리적 데이터 모델링`에서는 DBMS의 종류가 중요하다.  
앞에서 구한 `개념적 구조`를 어떤 DB에 표현할 것인지 결정해야 하기 때문이다. 

`논리적 데이터 모델`이란...  
선택한 DBMS에 따라 사용자 입장에서 표현한 `개념적 구조`를 `DB에 저장할 형태`로 표현한 논리적인 구조를 의미한다. 

`논리적 데이터 모델`로 표현된 DB의 논리적 구조가 바로 `DB의 스키마`이다. 이러한 `논리적 구조`는 `사용하는 DBMS`에 따라 달라진다.

- 종류 : DB에 있는 데이터들 간의 `관계를 표현하는 방법`에 따라 다양한 논리적 데이터 모델이 존재

1) 관계 데이터 모델 ⇒ `2차원 테이블 형태` ⇒ 지금은 이 형태를 많이 사용하고 있다.
2) 계층 데이터 모델
3) 네트워크 데이터 모델






























