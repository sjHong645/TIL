## 이번 챕터에서 다룰 내용

![image](https://user-images.githubusercontent.com/64796257/188041400-45af0599-f1de-4fb9-a62a-13a253bec3c6.png)

- 관계 대수(Relation Algebra) : `원하는 결과`를 얻기 위해 데이터의 처리 과정을 `순서대로 기술`하는 `절차 언어`
- 관계 해석(Relation Calculus) : `원하는 결과`를 얻기 위해 `처리를 원하는 데이터`가 무엇인지만 `기술`하는 `비절차적 언어`

⇒ 현재 상용화된 관계 데이터베이스에서는 실제로 사용되지 않는 개념이라고 함.  
⇒ 다만, `새로운 데이터 언어`가 나타날 때 `언어의 유용성`을 `검증하는 기준`이 됨

관계 대수나 관계 해석으로 기술할 수 있는 모든 질의를 `새로운 데이터 언어`로 `기술`할 수 있다면 `관계적으로 완전`하다고 함

## 02 관계 대수

### 일반 집합 연산자 

| 연산자 | 기호 | 표현 | 의미 | 
| --- | --- | --- | --- | 
| 합집합 | ∪ | R∪S | R과 S의 합집합 | 
| 교집합 | ∩ | R∩S | R과 S의 교집합 | 
| 차집합 | - | R-S | R과 S의 차집합 | 
| 카티션 프로턱트 | × | R×S | R과 S의 카티션 프로덕트 | 

![image](https://user-images.githubusercontent.com/64796257/188042532-4b55dab6-1718-4c63-ace0-dfa0352be613.png)

- 합집합

![image](https://user-images.githubusercontent.com/64796257/188042593-3eff2f2c-518c-4bad-a7c2-5494d4aa154a.png)

- 교집합

![image](https://user-images.githubusercontent.com/64796257/188042638-e42041a8-728b-4fb1-90a4-f972b1f87ffb.png)

- 차집합

![image](https://user-images.githubusercontent.com/64796257/188042658-716c7d08-48cb-46e8-b959-40664cda511c.png)

- 카티션 프로덕트 

![image](https://user-images.githubusercontent.com/64796257/188042689-a0884b15-bdee-4e3a-8c87-bece45ecb52d.png)

### 순수 관계 연산자 

| 연산자 | 기호 | 표현 | 의미 | 
| --- | --- | --- | --- | 
| select | σ | σ_(조건) (R) | 릴레이션 R에서 조건을 만족하는 튜플 반환 | 
| project | π | π_(속성리스트) (R) | 릴레이션 R에서 주어진 속성들의 값으로만 구성된 튜플 반환 | 
| join | ▷◁ | R▷◁S | 공통 속성을 이용해 릴레이션 R과 S의 튜플들을 연결해 새로운 튜플 반환 | 
| division | ÷ | R÷S | S의 모든 튜플과 관련있는 R의 튜플들 반환 | 

- select 

$σ_{조건식} (릴레이션)$

ex) 고객 relation에서 `등급 = gold`이고 `적립금 ≥ 2000`인 튜플 검색 ⇒ $σ_{등급 = 'gold' and 적립금 ≥ 2000} (고객)$ 

- project 

$π_{속성리스트} (릴레이션)$

ex) `고객 릴레이션`에서 `고객이름, 등급, 적립금`을 검색하시오 ⇒ $π_{고객이름, 등급, 적립금} (고객)$ 

- join 

ex) 고객 ▷◁ 주문

![image](https://user-images.githubusercontent.com/64796257/188044282-e7ace7f6-fb37-4194-8eba-bd6288c38c8c.png)

위와 같은 `조인`을 `자연 조인`이라 하고 좀 더 일반화한 조인인 `세타 조인`도 있다. 여기서 `세타`는 `비교연산자(＞, ≥, ＜, ≤, =, ≠)`를 의미한다.

- division

![image](https://user-images.githubusercontent.com/64796257/188044807-10e775e2-8ec9-4bd0-a8b3-e8d64cf5be18.png)

![image](https://user-images.githubusercontent.com/64796257/188044835-ad3f1dc6-f86f-42d0-820a-a6531ba00029.png)

- 여러 가지 연산 복합적으로 사용 

ex1) `등급 = gold`인 고객의 이름과 나이 검색

$π_{고객이름, 나이}(σ_{등급='gold'} (고객))$

![image](https://user-images.githubusercontent.com/64796257/188045226-7e85355f-7f0d-49f2-92f0-cabf64b1e306.png)

ex2) `고객 이름 = 원유선`인 고객의 등급과 원유선 고객이 주문한 주문제품, 수량을 검색

$π_{등급, 주문제품, 수량}(σ_{고객이름='원유선'} (고객 ▷◁ 주문))$

![image](https://user-images.githubusercontent.com/64796257/188045370-e1d20e01-1d99-461f-a66a-418f22879665.png)

ex3) 주문 수랑이 10개 미만인 주문 내역을 제외하고 검색 

주문 - $σ_{주문수량<10} (주문)$





















