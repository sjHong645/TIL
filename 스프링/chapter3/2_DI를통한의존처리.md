## DI(Dependency Injection, 의존 주입) 

의존하는 객체를 직접 생성하는 대신 `의존 객체`를 `전달받는` 방식 

ex) MemberRegisterService 의 일부 
``` java
public class MemberRegisterService {
	private MemberDao memberDao;
  
  // 직접 의존 객체를 생성하지 않고 
  // 생성자를 통해서 MemberRegisterService가 의존하고 있는 MemberDao 객체를 주입 받는다. 
  
  // 아래 코드는 생성자를 통해 의존 객체를 전달받기 때문에 DI 패턴을 따르고 있다. 
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	... 
}
```

만약에 `MemberRegisterService 클래스`를 사용한다면 다음과 같이 `MemberRegisterService 객체`를 생성할 때 생성자에 `MemberDao 객체`를 전달해야 한다. 
``` java
MemberDao dao = new MemberDao(); 

// 의존 객체를 생성자를 통해 주입한다. 
MemberRegisterService svc = new MemberRegisterService(dao); 
```

그런데 이렇게까지 코드를 길게 써가면서 `의존 주입`을 하는 이유는 뭘까? 바료 `변경의 유연함` 때문이다. 
