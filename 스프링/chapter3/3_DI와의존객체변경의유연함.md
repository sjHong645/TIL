- MemberRegisterService 클래스 

의존객체 `MemberDao`를 직접 생성한다고 하자. 

``` java
public class MemberRegisterService { 
  private MemberDao memberDao = new MemberDao(); 
  
  ... 
  
}
```

- 회원의 `암호 변경 기능`을 제공하는 ChangePasswordService 클래스 

의존객체 `MemberDao`를 직접 생성한다고 하자. 

``` java
public class ChangePasswordService { 
  private MemberDao memberDao = new MemberDao(); 
  
  ... 
  
}
```

이때, `MemberDao 클래스`는 회원 데이터를 DB에 저장한다고 하자.  
이 상태에서 회원 데이터의 빠른 조회를 위해 `캐시`를 적용해야 하는 상황이 발생했다.  
그래서 `MemberDao 클래스`를 상속받는 `CachedMemberDao` 클래스를 만들었다. 

``` java
public class CachedMemberDao extends MemberDao { 
  ... 
} 
```



*** 

이 상태에서 `ChangePasswordService`가 `CachedMemberDao`를 사용하려면 어떻게 해야 할까??   
직접 생성한 `MemberDao`를 `CachedMemberDao`로 바꿔줘야 할 것이다. 

``` java
public class MemberRegisterService { 
  // private MemberDao memberDao = new MemberDao(); 
  private MemberDao memberDao = new CachedMemberDao(); 
  
  ... 
  
}
```

``` java
public class ChangePasswordService { 
  // private MemberDao memberDao = new MemberDao(); 
  private MemberDao memberDao = new CachedMemberDao(); 
  
  ... 
  
}
```

만약 `MemberDao 객체`가 필요한 클래스가 4개였다면 4개의 클래스를 모두 동일하게 소스 코드를 변경해야 한다. 

*** 

이번에는 `DI`를 사용해보자. 

``` java
public class MemberRegisterService { 
  private MemberDao memberDao;
  
  public MemberRegisterService(MemberDao memberDao) { 
    this.memberDao = memberDao;
  }
  
  ... 
  
}
```

``` java
public class ChangePasswordService { 
  private MemberDao memberDao;
  
  public ChangePasswordService (MemberDao memberDao) { 
    this.memberDao = memberDao;
  }
  
  ... 
  
}
```

이 두 클래스를 생성하는 어떤 클래스가 있다고 하자. 그렇다면 다음과 같이 각각의 객체를 생성할 수 있다.  
``` java
MemberDao memberDao = new MemberDao(); 
MemberRegisterService regSvc = new MemberRegisterService(memberDao); 
ChangePasswordService pwdSvc = new ChangePasswordService(memberDao); 
```

이제 `CachedMemberDao`를 사용하도록 수정하자. 한 곳만 수정하면 된다.  
아래와 같이 `MemberDao 객체를 생성`하는 코드만 변경하면 된다. 

``` java
// MemberDao memberDao = new MemberDao(); 
MemberDao memberDao = new CachedMemberDao(); 

MemberRegisterService regSvc = new MemberRegisterService(memberDao); 
ChangePasswordService pwdSvc = new ChangePasswordService(memberDao); 
```

앞서 의존 객체를 `직접 생성`했던 방식에 비해 변경할 코드가 `한 곳으로 집중`되는 걸 알 수 있다. 

























