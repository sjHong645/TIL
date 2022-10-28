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
import spring.MemberRegisterService;

// 스프링 설정 클래스를 의미하는 @Configuration
// 이 Annotation을 붙여야 스프링 설정 클래스로 사용할 수 있다
@Configuration
public class AppCtx {

  	// @Bean 어노테이션은 해당 메소드가 생성한 객체를 스프링 빈이라 설정한다. 
	// 여기서는 3개의 메서드에서 각각 1개의 bean 객체를 생성한다.
  	// 이때, 메서드의 이름을 bean 객체의 이름으로 사용한다.
	// ex) memberDao() 메서드를 이용해서 생성한 빈 객체는 "memberDao"라는 이름으로 스프링에 등록된다.
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
	
		// memberDao() 메소드를 호출함
		// memberDao() 가 생성한 객체를 MemberRegisterService 생성자를 통해 주입함
		return new MemberRegisterService(memberDao()); 
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		
		// memberDao() 메소드를 호출함
		// memberDao() 가 생성한 객체를 ChangePasswordService의 setter를 통해 주입함
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
}
```

`설정 클래스`를 만드는 것에서 끝나지 않는다. 

`객체를 생성`하고 `의존 객체를 주입`하는 건 `스프링 컨테이너`이므로 설정 클래스를 이용해 `컨테이너를 생성`해야 한다.  

컨테이너는 `AnnotationConfigApplicationContext 클래스`를 사용하면 `스프링 컨테이너`를 생성할 수 있다. 

chapter2에 있는 `AnnotationConfigApplicationContext 클래스`와 관련된 내용을 정리하고 넘어가자. 

`스프링의 핵심 기능`은 `객체를 생성`하고 `초기화`하는 것이다.  
이와 관련된 기능은 `ApplicationContext 인터페이스`에 정의되어 있다.  
`AnnotationConfigApplicationContext 클래스`는 이 인터페이스를 알맞게 구현한 클래스 중 하나다. 

`AnnotationConfigApplicationContext 클래스`는 자바 클래스에서 정보를 읽어와 객체 생성과 초기화를 수행한다.  
XML 파일이나 Groovy 설정 코드를 이용해서 객체 생성 및 초기화를 수행하는 클래스도 존재한다. 

![image](https://user-images.githubusercontent.com/64796257/198516036-23161930-7495-43aa-af20-1bc265b525bf.png)

`AnnotationConfigApplicationContext 클래스`의 계층도 일부를 나타낸 위 그림  
가장 상위에 `BeanFactory 인터페이스` / 위에서 3번째에 `ApplicationContext 인터페이스`  
가장 하단에 `AnnotationConfigApplicationContext 클래스`가 위치한 걸 볼 수 있다. 

- BeanFactory 인터페이스 : `객체 생성`과 `검색`에 대한 기능을 정의  
예를 들어, 생성된 객체를 검색하는데 필요한 `getBean() 메서드`가 `BeanFactory`에 정의되어 있다.  
객체 검색 뿐만 아니라 `싱글톤/프로토타입 빈인지 확인`하는 기능도 제공한다. 

- ApplicationContext 인터페이스 : 메시지, 프로필/환경 변수 등을 처리할 수 있는 기능을 추가로 정의함 
- AnnotationConfigApplicationContext : `Java Annotation`을 이용해 `클래스로부터` 객체 설정 정보를 가져온다. 

어떤 구현 클래스를 사용하던지 간에 `설정 정보`로부터 `Bean`이라 불리는 `객체를 생성`하고 그 객체를 `내부에 보관`한다.  
그리고 `getBean()` 메소드를 실행하면 해당하는 `Bean 객체`를 제공한다. 

ex)   
``` java
// 1. 설정 정보를 이용해서 Bean 객체를 생성한다. 
AnnotationConfigApplicationContext ctx = 
	new AnnotationConfigApplicationContext(AppContext.class); 
	
// 2. ctx.getBean을 이용해서 Bean 객체를 제공한다. 
Greeter g = ctx.getBean("greeter", Greeter.class); 
```

`ApplicationContext(또는 BeanFactory)`는 Bean 객체의 생성, 초기화, 보관, 제거 등을 관리하고 있어서  
`ApplicationContext`를 `컨테이너(container)`라고도 부른다. 

![image](https://user-images.githubusercontent.com/64796257/198521059-ca0a186e-8b34-4b0e-a964-aa6f729d9e54.png)

***

다시 chapter3의 내용으로 돌아가자. 

앞서, 컨테이너는 `AnnotationConfigApplicationContext 클래스`를 사용하면 `스프링 컨테이너`를 생성할 수 있다고 했다.  

ex) 
``` java
ApplicationContext ctx = new AnnotationConfigApplicationContext(Appctx.class); 
```

위와 같이 `컨테이너를 생성`하면 `getBean() 메서드`를 이용해서 사용할 객체를 구할 수 있다.  
ex) 
``` java
// 컨테이너 ctx에서 이름이 memberRegSvc인 Bean 객체를 구한다. 
MemberRegisterService regSvc = 
	ctx.getBean("memberRegSvc", MemberRegisterService.class); 
```

앞서 Java Configuration에서 다음과 같이 코드를 설정했다. 이름이 `memberRegSvc`인 Bean 객체는 여기서 온 것이다. 
``` java
@Bean
public MemberDao memberDao() {
	return new MemberDao();
}
	
@Bean
public MemberRegisterService memberRegSvc() {
	
	// memberDao() 메소드를 호출함
	// memberDao() 가 생성한 객체를 MemberRegisterService 생성자를 통해 주입함
	return new MemberRegisterService(memberDao()); 
}
```

그렇다면 Assembler 클래스를 이용해서 작성한 `MainForAssembler 클래스`를 `스프링 컨테이너`를 사용하도록 변경하자.  
``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForSpring {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
	
		// AnnotationConfigApplicationContext를 사용해서 스프링 컨테이너를 생성한다. 
		// 스프링 컨테이너는 Assembler와 동일하게 객체를 생성하고 의존 객체를 주입한다. 
		// 다만, AnnotationConfigApplicationContext는 설정 파일을 
		// "AppCtx 클래스"로 부터 생성할 객체와 의존 주입 대상을 정한다. 
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			// ... 코드 생략
			printHelp();
		}
	}

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		
		// 스프링 컨테이너로 부터 이름이 memberRegSvc인 Bean 객체를 구한다.
		MemberRegisterService regSvc = 
				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		// ... 코드 생략
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		
		// 스프링 컨테이너로 부터 이름이 changePwdSvc인 Bean 객체를 구한다.
		ChangePasswordService changePwdSvc = 
				ctx.getBean("changePwdSvc", ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	// ... 코드 생략

}
```

### 2. DI 방식 1 : 생성자 방식 

앞서 작성한 `MemberRegisterService 클래스`를 보면 아래 코드처럼 `생성자`를 통해 `의존 객체를 주입받아` 필드를 할당했다. 
``` java

private MemberDao memberDao;

// 생성자를 통해 의존 객체를 주입받음 
// 생성자를 통해 memberDao라는 객체를 이용한다. 
public MemberRegisterService(MemberDao memberDao) {

	// 주입 받은 객체를 필드에 할당 
	this.memberDao = memberDao;
}

public Long regist(RegisterRequest req) {

	// 주입 받은 의존 객체의 메서드를 사용 
	Member member = memberDao.selectByEmail(req.getEmail());
	... 
	memberDao.insert(newMember);
	return newMember.getId();
}
```

스프링 자바 설정에서는 `생성자를 이용`해서 `의존 객체 주입`을 위해 해당 설정을 담은 메서드를 호출했다. 

``` java
@Bean
public MemberDao memberDao() {
	return new MemberDao();
}
	
@Bean
public MemberRegisterService memberRegSvc() {
	return new MemberRegisterService(memberDao());
}
```

지금까지 본 코드들은 `생성자에 전달할 의존 객체`가 `1개`인 경우다.  
전달할 의존 객체가 `2개 이상`이어도 `똑같은 방식`으로 주입하면 된다. - 책 p.82 ~ 84

### 3. DI 방식 2 - setter 메서드 방식 

앞서 `setter 메서드`를 이용해서 객체를 주입받기도 했다. - 책 p.84 ~ 87

``` java
@Bean
public MemberDao memberDao() {
	return new MemberDao();
}
	
... 
	
@Bean
public ChangePasswordService changePwdSvc() {
		
	// memberDao() 메소드를 호출함
	// memberDao() 가 생성한 객체를 ChangePasswordService의 setter를 통해 주입함
	ChangePasswordService pwdSvc = new ChangePasswordService();
	pwdSvc.setMemberDao(memberDao());
	return pwdSvc;
}
```

### 생성자 방식 vs setter 메서드 방식 

특별히 더 좋은 방법은 없다. 상황에 따라 두 방식을 혼용해서 사용하면 되겠다.  

- 각 방식의 장점 
1) 생성자 방식 : `Bean 객체`를 생성하는 시점에 `모든 의존 객체 주입 가능`
2) setter 방식 : `setter 메서드의 이름`을 통해 `어떤 의존 객체가 주입되는지` 쉽게 알 수 있다. 

- 각 방식의 단점 
1) 생성자 방식 : 파라미터 개수가 많을 때 각각의 인자가 `어떤 의존 객체를 설정`했는지 알고 싶다면 생성자의 코드를 확인해야 한다. 
2) setter 방식 : setter 메서드를 사용하기 때문에 필요한 의존 객체를 전달하지 않아도 `Bean 객체가 생성`되서 객체를 사용하는 시점에 NullPointerException이 발생할 수 있다

### 4. 기본 데이터 타입 값 설정 

int, long과 같은 기본 데이터 타입과 String 타입 값은 일반 코드처럼 값을 설정하면 된다. 

예시 - p.89 ~ 90












