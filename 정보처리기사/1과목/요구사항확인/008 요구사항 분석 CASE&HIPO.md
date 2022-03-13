## CASE - 요구사항 분석을 위한 자동화 도구 

: 요구사항을 자동으로 분석하고 요구사항 분석 명세서를 기술하도록 개발된 도구를 의미 

- 장점 

1) 표준화와 보고를 통한 문서화 품질 개선
2) DB가 모두에게 이용 가능하다는 점에서 분석가들 간의 적절한 조정
3) 변경이 주는 영향 추적의 용이성 

- 종류 - 대표적으로 SADT, SREM, PSL/PSA, TAGS 등이 있다. 

1) SADT(Structured Analysis and Design Technique) 

: `SoftTech 사`에서 개발한 것으로 시스템 정의, SW 요구사항 분석, 시스템/SW 설계를 위해 널리 이용되어 온 `구조적 분석 및 설계 도구`

: 구조적 요구 분석을 하기 위해 `블록 다이어그램`을 채택한 자동화 도구 

2) SREM(Software Requirements Engineering Methodology) = RSL/REVS 

: `실시간 처리 SW 시스템`에서 요구사항을 명확히 기술할 목적으로 개발된 것. RSL과 REVS를 사용하는 자동화 도구. 

- RSL(Requirement Statement Language) : 요소, 속성, 관계, 구조들을 기술하는 요구사항 기술 언어

- REVS(Requirement Engineering and Validation System)  
: RSL로 기술된 요구사항들을 자동으로 분석해 `요구사항 분석 명세서`를 `출력`하는 요구사항 분석기 

- PSL/PSA : PSL과 PSA를 사용하는 자동화 도구 

1) PSL(Problem Statement Language) : 문제 기술 언어
2) PSA(Problem Statement Analysis) : PSL로 기술한 요구사항을 `자동으로 분석`해 `다양한 보고서`를 `출력`하는 문제 분석기 

- TAGS(Technology for Automated Generation of Systems) 

: 개발 주기의 전 과정에 이용할 수 있는 통합 자동화 도구 

: IORL, 요구사항 분석과 IORL 처리를 위한 도구, 기초적인 TAGS 방법론으로 구성되어 있다. 

## HIPO(Hierarchy Input Process Output) 

: 시스템의 분석 및 설계나 문서화할 때 사용되는 기법. 시스템의 실행 과정인 입력, 처리, 출력의 기능을 나타낸다. 

- 기본 시스템 모델은 입력, 처리, 출력으로 구성되며 하향식 SW 개발을 위한 문서화 도구 
- 체계적인 문서 관리가 가능
- 기호, 도표 등을 사용하므로 보기 쉽고 이해하기도 쉽다. 
- 시스템의 기능을 `여러 개의 고유 모듈들`로 `분할`해 이들 간의 인터페이스를 `계층 구조`로 표현한 것을 HIPO Chart라고 한다. 

### HIPO Chart의 종류 

- 가시적 도표 (Visual Table of Contents) : 시스템의 전체적인 기능과 흐름을 보여주는 계층 구조도 

- 총체적 도표 (Overview Diagram) : 프로그램을 구성하는 기능을 기술한 것. 입력, 처리, 출력에 대한 전반적인 정보를 제공

- 세부적 도표 (Detail Diagram) : 총체적 도표에 표시된 기능을 구성하는 기본 요소들을 상세히 기술하는 도표 































