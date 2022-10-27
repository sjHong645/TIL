- 지금까지 살펴본 내용
1) `의존`이 무엇인가
2) `DI`를 이용해 `의존 객체를 주입`하는 방법에 대해
3) 객체 생성 & 의존 주입을 이용해 객체를 서로 연결해주는 `조립기`에 대해 

`DI`에 관한 내용과 `조립기`에 대해 이해했다면 스프링을 사용하기 위해 알아야 할 기본 중 하나를 이해한 셈이다.  
스프링은 앞서 구현한 `조립기`와 유사한 기능을 제공한다. 즉, Assembler 클래스의 생성자 코드처럼 `필요한 객체를 생성`하고 생성한 객체에 `의존을 주입`한다. 

### 1. 스프링을 이용한 객체 조립과 사용 

이제 Assembler 대신 `스프링을 사용한 코드`를 작성해보자.  
스프링을 사용하려면 스프링이 `어떤 객체를 생성`하고 `의존을 어떻게 주입할지`를 정의한 설정 정보를 작성해야 한다. 

- /main/config/AppCtx.java
``` java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

// 스프링 설정 클래스를 의미하는 @Configuration
// 이 Annotation을 붙여야 스프링 설정 클래스로 사용할 수 있다
@Configuration
public class AppCtx {


  // @Bean 어노테이션은 해당 메소드가 생성한 객체를 스프링 빈이라 설정한다. 
	// 여기서는 3개의 메서드에서 1개의 bean 객체를 생성한다.
  // 이때, 메서드의 이름을 bean 객체의 이름으로 사용한다.
	// memberDao() 메서드를 이용해서 생성한 빈 객체는 "memberDao"라는 이름으로 스프링에 등록된다.
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
}
```
































