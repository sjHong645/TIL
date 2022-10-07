[출처](https://blog.back4app.com/top-10-server-side-frameworks/#Top_10_Server_Side_Frameworks)

## Django

- Python 기반의 `MTV(Model-Template-View) 패턴`을 사용하는 프레임워크
- 재사용성, CRUD 인터페이스 등을 Python 프로젝트에서 유용하게 사용할 수 있음
- 최근에 많은 스타트업, 전문가, 대기업 들이 `Django`를 많이 선택해서 사용하고 있음

- 대표적으로 Disqus, Instagram, Bitbucket, Mozilla에서 사용하고 있다.

### 특징 
- 자유롭다(versatile Framework)  
  블로그, 관리 시스템, SNS을 만들 수 있을 뿐만 아니라 XML, JSON, JSON과 같은 프론트엔드 언어와 통합하기 쉽다.

- 높은 보안성(Highly Secure)  
  clickjacking, XSS, forgery(위조) 뿐만 아니라 사용자의 로그인 보안까지 커버할 수 있다. 

- 확장성(Scalable)  
  Django를 이용하면 인스타와 같은 웹사이트를 쉽게 만들 수 있고 `Python 라이브러리`를 활용할 수도 있다. 

- 검색 엔진 최적화  
  IP주소와 숫자 코드보다 쉽게 이해할 수 있는 `URL`을 사용함으로써 빠르게 검색 엔진을 최적화 할 수 있다.

- 수많은 커뮤니티  
  `Django`는 커뮤니티 활동이 활발한 프레임워크 중 하나다.  
  `Django`에 대한 정보를 커뮤니티, 깃허브, stack overflow 등에서 쉽게 알아 볼 수 있다.  

## Flask

- 2010년에 만들어진 `Python` 기반의 프레임워크 
- 외부 toolset과 라이브러리에 의존하지 않아서 가볍다.
- 러닝커브가 쉽고 유연한 특징 때문에 초보자들이 쉽게 접근할 수 있다. 

- 1천 여개의 회사가 이 도구를 사용하고 있고 대표적인 회사로 Neflix, Reddit 등이 있다. 

### 특징 
- 간단한 개발  
  쉽게 이해하고 배워서 사용할 수 있다.  
  웹 어플리케이션을 만들고 다룰 때 `완벽한 통제권`을 `개발자에게 부여`해준다.  
  그리고, 개발팀은 `다양한 페이지`에 있는 `똑같은 UI`를 Ninja 템플릿 엔진을 이용해서 활용할 수 도 있다.
  
- 확장성(Highly Scalable)  
  모든 사이즈의 프로젝트에 대해서 `확장성`이 있다. 
  Pinterest의 경우에는 트래픽 문제를 해결하기 위해서 Django가 아닌 Flask를 사용한다. 

- 단위 테스트  
 `단위 테스트` 뿐만 아니라 `빠른 디버깅`과 `테스트 함수`를 지원한다.  
  또한, 디버깅의 결과를 시각적으로 제공한다는 장점도 있다.

## Spring

- 2002년에 만들어진 `Java` 기반의 서버사이드 프레임워크 
- `Java EE platform`에서 제공하는 싱글톤, 팩토리 메소드, 템플릿 패턴을 따른다. 

- 대표적으로 Zilow, Accenture, Okta에서 사용하고 있다. 

### 특징
- AOP(Aspect-Oriented Programming) = 관점 지향 프로그래밍 [의미](https://engkimbs.tistory.com/746)  
  AOP를 제공함으로써 가독성과 유지보수를 더 쉽게 할 수 있다.  
  또한, 로깅, 인증, 트랜잭션 관리, 캐싱 등의 문제들을 극복할 수 있다. 

- 용이한 테스트  
  TestContext, Spring MVC test, Mock Object를 지원함으로써 더 쉽게 테스트를 할 수 있도록 한다. 

- IoC (Inversion of Control) [의미](https://jobc.tistory.com/30)  
  Spring은 `의존 주입(dependency injection)`을 자동으로 해결하기 위해서 IoC 컨테이너를 사용한다.  
  이 특징을 이용해서 개발을 더욱 빠르게 하고 테스트와 유지보수를 더욱 발전시킬 수 있다. 

- 보안 & 빠른 속도  
  Spring은 `정기적인 업데이트`와 외부 의존에 대한 `지속적인 점검` 덕분에 높은 보안 수준을 유지하고 있다.  
  이러한 보안은 `Spring Security FW`를 이용해서 더욱 발전시킬 수 있다. 
  그리고 `Spring Initializer`를 통해 개발 과정을 빠르게 유지할 수 있다. 
  
## Express JS

- `Node.js`에 사용되는 프론트엔드/백엔드 웹 프레임워크 
- `유연한 cross-platform app과 API`를 만드는 도구로 유명하다. 
- 강력한 routing, templating, 디버깅 특징 때문에 Express JS를 찾는 사람이 점점 많아지고 있다. 

- 대표적으로 IBM, Paypal, Twitter, Trustpilot, Fox Sport에서 사용하고 있다. 

### 특징 
- Routing & Templating   
  `발전된 라우팅 구조`를 제공하고 URL을 통해서 웹 페이지를 저장할 수 있도록 한다.  
  개발자들은 Express JS의 템플릿 엔진을 통해 모바일/웹 어플리케이션에서 `동적 콘텐츠`를 만들 수 있다. 

- 빠른 개발(Quick Development)  
  Express JS는 프로그래머들이 `코드를 작성하지 않도록` 함으로써 시간을 단축시킨다.  
  주로, `Node.js`를 위해서 Express JS를 사용한다면 더 빠르게 개발할 수 있다.
  
- 배우기 쉽다  
  Express JS는 JS를 기반으로 했기 때문에 JS를 공부했다면 쉽게 배울 수 있다.

- 디버깅이 쉽다  
  
## Ruby On Rails

- `Ruby`를 이용해서 애플리케이션을 만들 때 사용할 수 있는 프레임워크 
- `MVC 패턴`을 따르며 CSS, HTML, JS UI와 호환성이 있다. 

- 대표적으로 shopify, Airbnb, SoundCloud, Github, Dribble and Bloomberg에서 사용한다.

### 특징 
- 효율적  
  개발자들에게 제공하는 [젬](https://ideveloper2.tistory.com/80)(gem)은 개발 속도를 빠르게 하고 비용을 절감시켜준다. 
  
- 많은 커뮤니티  
  `Rails`는 커뮤니티가 많은 서버 사이드 프레임워크이다. 때문에 Rails와 관련된 다양한 정보를 쉽게 찾아볼 수 있다. 

- Active Record  
  `Active Record`라는 라이브러리를 통해 `DB`와 `데이터 쿼리`를 좀 더 쉽게 다룰 수 있다. 

## ASP.NET

- MicroSoft에서 2002년에 발매한 프레임워크. 

- 대표적으로 Alibaba Travels, Slack, HBO, CNN 에서 사용하고 있다. 

### 특징 

- Cross platform & open source  
  다양한 OS에서 ASP.NET을 사용할 수 있고 `오픈 소스`라는 장점이 있다. 때문에, `.NET`에 대한 정보를 쉽게 찾아볼 수 있다.

- Dependency Injection  

- 보안 
  `CSRF`를 통해 사용자들을 보호한다. 그래서 기업들이 banking 또는 이와 관련된 애플리케이션을 만들 때 사용하면 좋다.




























