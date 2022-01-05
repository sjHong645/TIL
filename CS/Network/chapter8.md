# 8. 누가 액세스 하고 있는지 확인하는 인증

## 8.1 인증이란? 

컴퓨터는 네트워크 너머에 있는 사람은 물론 지금 자기 앞에 앉아있는 사람도 누구인지 알 수 없다.

그래서 서버에 접근하고 있는 사람이 누구인지 알려면 상대방 client에게 이름을 알려달라고 해야 한다.

다만, 액세스 하는 쪽에서 이름을 알려준다고 해도 그 이름이 진짜 해당 client의 이름인지는 알 수 없다.  
이를 위해서는 `등록되어 있는 본인만이 알고 있는 정보`와 `등록한 본인만이 알고 있는 정보` 등을 통해 확인할 필요가 있다.

이러한 정보에는 다음과 같은 것들이 이용되고 있다.

- 패스워드 : 나만 알고 있는 문자열 정보
- 원타임 토큰(One-Time Token) : 나만 가지고 있는 기기 등에 표시되는 1회용 패스워드와 같은 정보
- 전자 증명서 : 나(단말기)만 가지고 있는 정보
- 바이오 매트릭스 : 지문이나 홍채 등 본인의 신체 정보
- IC 카드 : 나만 가지고 있는 정보

HTTP에서 사용하는 인증 방법은 다음과 같은 것들이 있다.

- BASIC 인증
- DIGEST 인증
- SSL 클라이언트 인증
- 폼 베이스 인증

## 8.2 BASIC 인증

BASIC 인증은 웹 서버와 이에 대응하는 클라이언트 사이에서 이뤄지는 인증 방식이다.

### BASIC 인증 순서

![image](https://user-images.githubusercontent.com/64796257/148164482-3ec45cd7-d97d-4caf-8e27-392e38800a36.png)

① BASIC 인증이 필요한 리소스에 request가 있을 경우  
  서버는 상태코드 401 Authorization Required와 함께 인증 방식(BASIC)과  
  Request-URI의 보호 공간을 식별하기 위한 문자열(realm)을 WWW-Authenticate 헤더 필드에 포함에서 response에 반환한다.  
  ⇒ WWW-Authenticate: Basic realm="Input Your ID and Password" 부분이 그 내용
  
② 상태 코드 401을 받은 클라이언트는 BASIC 인증을 위한 유저 ID와 패스워드를 서버에 송신해야 한다.  
송신하는 문자열은 `ID:패스워드` 형식의 문자열을 Base64 방식으로 인코딩한 값이다.  
유저 ID가 `guest`이고 패스워드가 `guest`이면 `guest:guest`가 되고 이를 Base64 방식으로 인코딩하면 `Z3Vlc3Q6Z3Vlc3Q=`가 된다.

이 문자열을 Authorization 헤더 필드에 포함시켜서 request를 보낸다. ⇒ `Authorization: Basic Z3Vlc3Q6Z3Vlc3Q=` 부분

③ Authorizatino 헤더 필드를 포함한 request를 수신한 서버는 인증 정보가 정확한지 판단한다.  
인증 정보가 정확하면 Request-URI 리소스를 포함한 response를 반환한다.

### 문제점

Base64 인코딩 방식을 사용하고 있지만 이는 암호화가 아니기 때문에 손쉽게 디코딩을 통해 ID와 패스워드를 알아낼 수 있다.  

그리고 한번 BASIC 인증을 하면 일반 브라우저에서는 로그아웃할 수 없다.

그래서 BASIC 인증은 사용상의 문제가 많아서 많이 사용되고 있지는 않는다.

## 8.3 DIGEST 인증 

BASIC 인증의 약점을 보완하면서 HTTP/1.1에 소개되었다.  
DIGEST 인증은 challenge-response 방식을 사용하고 있어서 BASIC과 같이 패스워드를 있는 그대로 보내지 않는다.

challenge-response 방식은 최초에 상대방에게 인증 요구를 보내고 
상대방 측에서 받은 챌린지 코드를 사용해서 response 코드를 계산한다.  
이 값을 상대에게 송신해서 인증하는 방법이다.

![image](https://user-images.githubusercontent.com/64796257/148165696-7124f88d-f91f-4e2c-a7fb-3b0c493ee51b.png)

response 코드는 패스워드와 challenge 코드를 이용해서 계산한 결과를 상대에게 보내기 때문에  
BASIC 인증과 같은 방식에 비하면 패스워드가 유출될 가능성은 줄어든다.

### DIGEST 인증 순서 

![image](https://user-images.githubusercontent.com/64796257/148165873-1d2f67d4-0bfa-484b-b4ba-918ea6796d68.png)

① 인증이 필요한 리소스에 request가 있을 경우  
서버는 상태코드 401 Authorization Required와 함께 challenge-response 방식의 인증에 필요한 챌린지 코드(nonce)를 WWW-Authenticate 헤더 필드에 포함해서 response를 반환한다. 

nonce는 401 response를 반환할 때마다 생성되는 유일한 문자열이다. 

⇒ WWW-Authenticate: Digest realm="DIGEST" nonce = "~~"

② 상태 코드 401을 수신한 클라이언트는 DIGEST 인증을 필요한 정보를 Authorization 헤더 필드에 포함해서 response를 반환한다.  
Authorization에 필요한 정보는 `username`, `realm`, `nonce`, `uri`, `response`가 있는데 그 중에서 `realm`과 `nonce`는 서버에서 받은 걸 사용한다.

- username : 지정된 realm에서 인증 가능한 사용자 이름
- uri(digest-uri) : Request-URI에 있는 URI이지만 프록시에 의해 Request-URI가 변경되는 경우도 있어서 여기에 복사한다.
- response : Request-Digest라고 불린다. 패스워드 문자열을 MD5로 계산한 것으로 이 값이 `response code`이다.

③ Authorization 헤더 필드를 포함한 request를 받은 서버는 인증 정보가 정확한지 판단한다.  

인증 정보가 정확하다면 Request-URI의 리소스를 포함한 response를 반환한다.  
이때, 서버는 Authentication-Info 헤더 필드에 성공한 인증에 대한 몇 가지 정보를 추가할 수도 있다.

### 장/단점
DIGEST 인증은 BASIC 인증에 비해 높은 보안 등급을 제공하고 있지만, HTTPS 클라이언트 인증과 비교하면 보안 등급이 낮다.

그래서 DIGEST 인증도 BASIC 인증과 마찬가지로 문제가 많아서 널리 사용하고 있지는 않는다.

## 8.4 SSL 클라이언트 인증 

유저 ID와 패스워드를 사용한 인증 방식은 이 2가지 정보가 정확할 때 본인이라고 인증할 수 있다.

하지만, 이 정보를 도난당했을 때 제 3자가 `위장`을 하는 경우가 있다. 이를 방지하기 위한 대책 중 하나로 `SSL 클라이언트 인증`을 사용할 수 있다.

SSL 클라리언트 인증은 HTTPS의 클라이언트 인증서를 이용한 인증 방식이다.

### SSL 클라이언트 인증 순서 

SSL 클라이언트 인증을 할 때는 사전에 클라이언트에 클라이언트 증명서를 배포하고 설치를 해놔야 한다.

① 인증이 필요한 리소스의 request가 있을 경우 서버는 클라이언트에게 클라이언트 증명서를 요구하는 `Certificate Request`라는 메시지를 송신한다.

② 유저는 송신할 클라이언트 증명서를 선택한다. 그리고 클라이언트는 클라이언트 증명서를 `Client Certificate`라는 메시지와 함께 송신한다.

③ 서버는 클라이언트 증명서를 검증해서 검증 결과가 정확하다면 클라이언트 공개 키를 획득하고 HTTPS에 의한 암호를 연다.

### SSL 클라이언트 인증은 2-factor 인증에서 사용됨


























