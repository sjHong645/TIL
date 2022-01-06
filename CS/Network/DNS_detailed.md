## DNS(Domain Name System) 

예를 들면, www.naver.com 이런 주소를 바로 도메인 네임이라고 한다. 하지만, 컴퓨터가 주소를 인식하는 방법은 ip주소이다.  
그래서 시스템적으로 www.naver.com와 같은 주소를 입력하면 매핑되는 ip주소로 바꿔주는 시스템이 필요한데 그게 바로 `DNS`이다.

DNS를 사용하는 이유를 상식적으로 생각해보면 255.201.20.3과 같은 숫자보다는 google.com이 더 기억하기가 쉽다. 

그렇다면 어떻게 숫자와 이름을 매핑할까??  
1. 숫자와 이름이 매핑되는 파일을 컴퓨터 안에 하나의 파일로 유지하는 방법.  
⇒ 이 방법의 문제는 컴퓨터의 숫자가 많아질수록 저장해야 하는 파일의 크기가 점점 커지고 만약에 어느 한 쪽에서 IP주소와 도메인 네임이 변경된다면 

여러 개의 파일이 각자 다른 컴퓨터에 저장되어 있어서 다른 컴퓨터에 있는 내용도 다 바꿔야하는 consistency의 차원의 문제가 발생한다.  
그래서 두 번째 방법을 고안한다.

2. 중앙 서버에 host file을 저장하고 주소를 사용할 때마다 중앙 서버에 접근하는 방식이다.

3. 2번 방법을 분산 시스템으로 확장한 방식인 DNS 방식도 있다.

각각의 방식에 대해서 살펴보도록 하자.

① 컴퓨터 안에 host file을 유지하는 방법(PC가 도메인 이름과 IP주소가 매핑된 파일을 가지고 있는 방식)  
![image](https://user-images.githubusercontent.com/64796257/148336595-a640caa9-0ff2-44a3-b9bf-0ade9f33a92f.png)

지금은 이 방식을 안쓴다. 
ex) `ftp nclab.chonbuk.ac.kr`을 입력하면 host file에 해당 주소가 있는지 확인하고 그 주소와 매핑된 ip주소를 가져온다.

그렇게 가져온 IP주소를 통해 접속을 요청한다.

다만 문제점이 있었다.
- 인터넷 전체 IP와 이름을 하나의 PC가 저장하기에는 너무 벅찼다.
- 바뀐 IP주소와 이름간의 매핑을 유지하기 어렵다. 

② 그래서 중앙 서버에 Host file을 유지하는 방식을 고안한다  
![image](https://user-images.githubusercontent.com/64796257/148336792-d3f9936d-d262-4875-94bc-68acc9328560.png)

중앙 서버가 하나의 host-file을 갖고 있다면 `ftp nclab.chonbuk.ac.kr`라고 입력할 때  
PC하나가 도메인 네임의 IP주소를 요청하면 중앙서버의 host file에 있는 걸 가져다 주면 된다.

그 IP주소를 가지고 접속하는 방식이다. 이는 1번 방식의 문제점을 모두 보완하는 방식이지만 문제가 있다.  
중앙서버 하나가 전 세계에 있는 PC를 감당할 수 없는 건 당연하다. 때문에 Scalability 문제가 발생하고 병목현상이 발생한다는 문제도 있었다.

③ DNS(Domain Name Server) - 현재 사용하고 있는 방식  
![image](https://user-images.githubusercontent.com/64796257/148336897-5db6942a-1fea-4c91-8b95-e50ecef35305.png)

수많은 서버들이 계층적 구조를 이루고 있다. 구체적인 내용은 아래에서 다룬다.  
이는 앞서 언급한 1,2번 방식의 문제점을 보완함으로써 현재까지 사용하는 방식이다.

DNS(Domain Name Server)에 대해서 자세히 살펴보자. 크게 두 가지 내용을 살펴본다. 
1) 도메인 네임을 짓는 방법 2) 어떤 과정에 의해 도메인 이름을 주면 IP주소를 돌려주는지 

### 1) 도메인 이름을 짓는 방법

- 이름 공간  
![image](https://user-images.githubusercontent.com/64796257/148345644-13f2f50f-3dd9-46db-ba88-823d49c7048c.png)

Flat Name Space는 이름을 내 마음대로 unique하게 짓는 방식이다.

Hierarchical Name Space에 대해 살펴보자. 

![image](https://user-images.githubusercontent.com/64796257/148345774-0f3287fb-7c1a-4096-9d51-fffddf9f40c1.png)

두 대학과 한 회사가 challenger라는 이름을 PC에 붙이고자 한다.

이때, 계층구조에 의해서 challenger라는 이름 뒤에 여러 가지 옵션이 붙게 되는데 위와 같이
- challenger.fhda.edu
- challenger.berkeley.edu 
- challenger.smart.com

⇒ 전체 주소 이름을 보면 다른 이름이니까 각 주소는 고유성을 유지했다. 이 방식이 정말 좋은 방식인지 살펴보자.

cf) 노드(node) : 컴퓨터 과학에 쓰이는 기초 단위로 대형 네트워크에서는 장치나 데이터 지점(data point)을 의미한다.  
개인용 컴퓨터, 휴대전화, 프린터와 같은 정보처리 장치들이 노드이다.

Hierarchical Name space를 위해서는 Domain Name Space에 대해 알아봐야 한다. 먼저 Label라는 개념에 대해 알아보자.  

Label이란 도메인 네임은 계층 구조로 이루어져 있는데 계층 구조에는 노드들이 있다. 그 노드가 가질 수 있는 이름을 label이라 한다.   
ex. www.naver.com이라 하면 www/ naver/ com이 각각 하나의 레이블(label) 이다.

그리고 Domain name은 각 노드마다 도메인 이름이 있는데 www.naver.com 에서는 com / naver.com/ www.naver.com 이 각각 도메인 네임이다.

![image](https://user-images.githubusercontent.com/64796257/148346346-0696f470-8e4f-413d-95e0-7a4b5992bc82.png)

Hierarchical name space를 위해 도메인 네임 스페이스가 설계되었다는 의미는 domain을 기반으로 name space가 존재한다는 뜻

![image](https://user-images.githubusercontent.com/64796257/148346372-58dd0419-5ffe-4540-b340-e0e533adcf51.png)

슬라이드의 벤다이어그램을 보자. D5(=domain 5)라는 제일 큰 도메인이 있다.  
이 안에 D2, D4가 포함되어 있고 D2는 D1을 포함하고 D4는 D3를 포함한다.  
여기서 주목할 점은 각각의 도메인이 포함관계에 놓여 있다는 것이다.(위의 오른쪽 그림도 같이 보자)  
이를 구현하는 방법이 label을 갖는 역-트리(Inverted-tree)이다.

![image](https://user-images.githubusercontent.com/64796257/148346490-b8ab1964-c5fd-43e9-a7fa-479a4e7f171e.png)

끝에서부터 읽는다. 그래서 `역-트리`이다. Null string을 기준으로 한 단계 씩 내려갈때마다 레벨이 하나씩 증가한다.

각 노드(동그라미)의 arpa, com 같은 것이 label이다. 이때 이름은 63자 이하여야 한다.  
동일 레벨에서는 label이 각각 달라야(unique) 한다. 다른 레벨에서는 상관없다.

![image](https://user-images.githubusercontent.com/64796257/148346632-e2c73f22-e714-4d0a-b9da-d3be70901d85.png)

예를 들어, challenger라는 노드를 하나 잡았다. 그 노드를 기준으로 한 단계씩 올라가면 된다.  
맨 밑에 있는 노드의 label = challenger ⇒ 다음 노드의 label = atc ⇒ 다음 노드의 label = fhda ⇒ 다음 노드의 label = edu ⇒ Root

그래서 1번 노드의 도메인 네임은 `challenger.atc.fhda.edu.`가 된다. 
2번 노드에서 시작하면 도메인 네임은 `atc.fhda.edu.` 3번 노드에서 시작하면 `fhda.edu.` 가 된다.(도메인 네임의 마지막에는 원래 점(.)이 있다.)

이러한 역트리 방식으로 도메인 네임이 구현된다.

cf) FQDN & PQDN 

![image](https://user-images.githubusercontent.com/64796257/148346862-bd922fa8-93bf-49f3-ae50-b839db285420.png)

슬라이드를 보면 DNS 서버는 FQDN만을 ip주소로 매핑할 수 있다고 했다. 그럼 어떻게 PQDN을 읽을까??

PQDN을 입력하고 나서 resolver가 나머지 suffix를 채워주기 때문이다. 그래서 `naver`라고만 입력해도 알아서 `www.`랑 `.com`이 붙는다.  
cf) resolver : DNS에게 도메인 네임을 물어보는 프로그램

맨 마지막에 .을 붙이지 않은 것도 PQDN이다. 원래는 붙여야 한다.

도메인은 포함관계를 맺고 있다고 했다. 그리고 계층 구조로 도메인의 이름을 나눌 수 있다고 했는데   
그렇다면 이 이름을 실제로 어떻게 서버에 저장해야 할까?? 

만약에 1대의 컴퓨터에 저장한다면 병목현상(bottleneck)이 발생하고 안정성이 떨어진다.

그래서 `분산`하도록 했다. 
- 동일한 데이터를 여러 대에 분산하고
- 여러 서버가 각자의 의무를 분산한다.

여러 서버에서 각자의 의무를 갖도록 분산하는 구조를 취하고 있는 이유는 처음부터 도메인 이름이 계층을 나눠서 이름을 설계했기 때문이다.  
그래서 서버도 여기에 맞춰서 구현해주면 구조적으로 잘 맞아 떨어지기 때문에 이런 방식을 택했다.

![image](https://user-images.githubusercontent.com/64796257/148347470-0cfb71fc-0c9a-420f-bd87-17fccefd24ba.png)

이름을 담당하는 서버의 최상위에 Root 서버가 있다. 그 밑에 edu, com 서버가 있고 이 중 edu는 fhda, bk 등등의 이름을 관할한다.  
com은 mcgraw, irwin 의 이름을 관할한다.

도메인 자체가 이러한 계층 구조이기 때문에 이 구조에 맞춰서 서버를 실제로 하나씩 갖다 놓는 방식은 타당하다.

여기서 가장 중요한게 root server인데 이 서버가 다운되면 모든 서버가 다운된다.  
그래서 전 세계 인터넷 망을 마비시키려면 루트 서버를 마비시키면 된다. 그렇게 되면 dns 서버가 동작을 하지 않게 되는 것이다.

예를 들어 www.naver.com 라고 입력해도 IP주소를 돌려주지 못하기 때문에 인터넷이 작동하지 않게 된다.
기본적인 domain name server의 구조는 이러하다. 

정리하면, root 서버는 전체 이름을 담당하는 서버이고   
그 밑에 있는 예를 들어 edu server는 edu를 포함하는 모든 도메인 네임을 담당하는 서버이고  
그 밑에 있는 bk.edu는 bk.edu를 포함하는 모든 도메인 네임을 담당하는 서버라고 생각하면 된다.  
⇒ 이렇게 되면 각자에게 의무가 분산되는 `Load Balancing(로드 분산)`이 된다고 할 수 있다.

![image](https://user-images.githubusercontent.com/64796257/148348036-a2709507-1e93-4ae0-86f1-b4f3ca3c4d54.png)

첨언)  
- 루트 서버 : 정말 중요한 서버여서 서버의 ip주소는 비밀이다. 
- 주 서버
- 보조 서버: 주 서버가 다운될 경우를 대비해 만들어진 서버.

정리 : primary server는 디스크 파일로부터 zone에 대한 정보를 생성, 갱신하는 것.  
secondary server는 주 서버로부터 정보를 가져와서 사용하는 서버

도메인을 영역별로 나누면 Generic / Country / Inverse domain 인데 각각에 대해서 살펴보자.
- Generic Domain

![image](https://user-images.githubusercontent.com/64796257/148348224-1b2598f8-4c52-4e5c-949c-8002a35d2e2b.png)

Generic domain은 org, edu, com와 같은 걸로 끝난다.

generic domain은 나라와 무관하게 사용할 수 있는 범용적인 도메인이다. 과거에는 7개만 있었지만 지금은 필요에 따라서 계속 늘어나고 있다.

- Country Domain 

![image](https://user-images.githubusercontent.com/64796257/148348292-bd5190df-c328-4878-b13e-d72477438a78.png)

나라를 표시하는 도메인이다. us, kr, jp, zw 등등이 있다.

- Inverse Domain 

![image](https://user-images.githubusercontent.com/64796257/148348325-17f5e602-2f43-49a7-92c8-130c5b4f0366.png)

inverse domain은 목적이 반대다. 
즉, IP 주소를 줬을 때 이에 매핑되는 도메인 이름을 알기 위해서 사용하는 거다.  
예를 들면, 122.131.45.11의 도메인 네임을 알고 싶을 때 사용하는 게 inverse domain이다.

즉, 원래는 도메인 네임을 주면 IP를 주는 거지만 inverse는 IP주소를 주면 도메인 이름으로 매핑하기 위한 것이다.

### 2) 어떤 과정에 의해 도메인 이름을 주면 IP주소를 돌려주는지 

- Resolver 
![image](https://user-images.githubusercontent.com/64796257/148348868-65fb16d3-859f-4588-b19c-340174701c0d.png)

Resolver에는 DNS 클라이언트, FTP 클라이언트가 있다. 유저로부터 도메인 이름을 받는 모든 client 프로그램들은 당연히 resolver를 포함해야 한다.

- Resolution
살펴보기 전에 몇 가지 내용을 정리하면 DNS 서버는 분산 구조로 되어 있다고 했다.  
그리고 client가 요청하는 건 domain name 이거나 IP주소일 수 있다.  
그래서 만약 domain name을 요청하면 IP주소로 응답하고, IP주소를 요청하면 domain name으로 응답한다.

![image](https://user-images.githubusercontent.com/64796257/148349008-c8b10b3a-42f0-4523-af41-0fc681c1ab2f.png)

상황은 이렇다. clinet가 DNS 서버에게 뭔가를 요청했다.  
만약에 그 서버가 해당 DNS 이름 또는 IP주소에 대해 authority를 가졌다면 DB를 검색해서 결과를 바로 client에게 전달한다.

그런데 authority를 가지지 않았다면 처리하는 방식에 따라 2가지로 나뉜다.

1. Recursive Resolution
해당 서버가 authority를 가지지 않았다면 그 서버의 상위에 있는 server에게 요청을 넘긴다.  
만약 상위에 있는 서버도 authority를 갖지 않는다면 또 상위로 요청한다. 최악의 경우 root 서버까지 올라간다.

ex. 내가 요청한 도메인 네임이 csu.edu와 관련된 주소라 하자.  
그럼 처음에 a에게 요청을 했을 때 a는 authority가 없으니까 상위에 있는 b에게 요청을 하고  
b는 c가 해당 도메인 네임에 대한 authority를 갖고 있기 때문에 c에게 요청을 넘긴다.  
c는 이에 대한 응답을 하고 그 응답은 요청을 했던 b에게 주고 그 응답을 다시 a에게 전달한다. 

이러한 방식이 `Recursive Resolution`이다. 

이 방법의 특징은 client가 한 번만 요청하면 된다는 점이다.

단점이 있다면 client는 한 번 요청하면 끝이지만 DNS 서버들은 요청에 대한 정보를 기록하고 요청에 대한 답장이 올 때까지 기다려야 한다.  
문제는 이런 요청이 1개의 client에서 끝나는 게 아니라는 것이다.  

수많은 클라이언트에게 요청을 받게 되는데 그러면 처리해야할 요청이 쌓이면서 DNS 서버의 부담이 커질 수 밖에 없다. 상위로 올라가면 더 커진다.
이러한 Scalability 문제가 발생한다.

정리하면, 이 방법은 client 입장에서는 매우 편하지만 DNS server 입장에선 scalability 문제에 대한 부담이 있다.

2. Iterative Resolution  
client가 어떤 요청을 하는데 그 요청이 f에 요청해야 해결 할 수 있다고 하자.
그 사실을 모르는 client는 먼저 d에 요청한다. d는 이 요청에 대해 처리할 수 없으므로 d보다 상위에 있는 서버인 e에게 요청하라는 신호를 전달한다. 

그렇게 client는 e에게 요청하고 e는 ‘그 요청은 f가 할 수 있다’고 알려주는 신호를 전달하고 client는 그 요청을 받아 f에게 요청한 후 이에 대한 정답을 받게 된다. 즉, client가 정답을 알 때까지 반복해서 요청한다. 

이렇게 되면 client가 DNS 서버에 여러 번 요청해야 해서 불편하다.  
하지만 DNS 서버는 한 번 요청하고 답하면 처리가 끝나기 때문에 1번에 비해 로드가 줄어든다.  
그래서 DNS의 Scalability 입장에선 이 방식이 유리하다. 





