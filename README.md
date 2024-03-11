# Han-Commerce

<br>

## 🖥️ 프로젝트 소개
<br>

**Han-Commerce**는 E-commerce 도메인의 기본 기능들을 SpringBoot 기반 RestAPI 개인 프로젝트입니다.

<br>

Swagger UI : [http://13.125.133.7:8080](http://3.39.231.19:8080/swagger-ui/index.html)

<br>

### 프로젝트 순서

1. 프로젝트로 얻고자 하는 목표 설정
2. 사용 기술 , 개발 환경 , 도메인 등 결정
3. 기능 구현 목록 정리 후 기반 삼아 도메인 모델링 설계
4. ERD 데이터 베이스 설계
5. 코드 작성(이전 단게의 부족한 부분들을 보완하면서 진행) , 테스트
6. 배포 , 운영
  
<br>

## 🕰️ 프로젝트를 하게 된 이유 
<br>

JPA를 학습 후 실무에서 사용할 수 있는 상황이 없어 프로젝트를 구현하며 동작원리를 이해하고 발생하는 문제들을 해결하는 경험을 위해 진행

<br>

**CI/CD PipeLine을 적용하여 배포 자동화 경험** 

<br>

보안 부분에서 Spring Security 와 JWT의 흐름을 파악하고 적용 해보기 위해 진행

<br>

간단한 테스트(Junit) 코드도 진행해볼 것(CI/CD 자동화 적용 후 유지보수 때 진행)

<br>

swagger를 통해 restAPI의 문서화를 진행



<br>

## 🧑‍🤝‍🧑 기능 소개
 - 고객
 - 로그인 / 회원가입 / 비밀번호 변경
 - 글작성 / 글수정 / 댓글 CRUD / 상세 페이지
 <br>


## ⚙️ 개발 환경 및 사용 기술
- `Java 11`
- **IDE** : intellij 
- **Framework** : SpringBoot
- **Database** : MySQL
- **ORM** : JPA
- aws , SpringSecurity , HTML , CSS , Javascript , ajax , Github
<br>


## 📌 내가 구현한 기능 설명
#### [ 메인 페이지 ]  
- 새글 쓰기 , 로그인 , 회원가입으로 넘어가는 페이지
- 전체 글 목록이 조회 되고 내 글만 보기를 눌렀을 때는 자신의 글만 볼 수 있습니다.
- 글 상세보기로 넘어가는 페이지를 구현했습니다.
<br>
<img width="400" alt="스크린샷 2024-01-01 오후 7 06 04" src="https://github.com/gkstjr/self-Develop/assets/99389922/59db843c-e76f-4674-8243-23ad614b8589"> 
<img width="400" alt="스크린샷 2024-01-01 오후 7 06 11" src="https://github.com/gkstjr/self-Develop/assets/99389922/30009232-73e3-4999-8cd1-a2de780240d6">

#### [ 로그인 / 회원가입 페이지 ]
- 계정을 로그인하거나 회원가입할 수 있는 페이지입니다.
- 로그인을 안 하고 글을 쓰거나 댓글을 쓰려고 하면 로그인 창으로 넘어가게 구현했습니다.
- Vaildation을 이용해 정규식 검사를 구현했습니다.
- 페이지네이션을 이용해 게시글 페이지를 구현하였습니다.
<br>
<img width="400" alt="스크린샷 2024-01-01 오후 7 17 14" src="https://github.com/gkstjr/self-Develop/assets/99389922/f9025d79-c5cc-4a4e-971f-7eb95bb6baba">
<img width="400" alt="스크린샷 2024-01-01 오후 7 06 21" src="https://github.com/gkstjr/self-Develop/assets/99389922/5c5ea623-6200-41e2-b140-713ab59a764c">

#### [ 글작성 / 상세 페이지 ]
- 글 작성자가 본인일 경우 글 변경 , 글 삭제가 가능 합니다.
- 댓글 등록이 가능합니다. 댓글 작성자가 본인일 경우 댓글 수정 , 삭제도 가능합니다.
<br>
<img width="400" alt="스크린샷 2024-01-01 오후 7 06 37" src="https://github.com/gkstjr/self-Develop/assets/99389922/f7194df1-71f0-421c-9f9f-643fbc1d0ed3">
<img width="400" alt="스크린샷 2024-01-01 오후 7 06 40" src="https://github.com/gkstjr/self-Develop/assets/99389922/8e5a8faf-909b-45e7-8f50-ce906ff1ec4a">

#### [ 댓글 ]
- 글 상세보기에서 댓글 작성 / 변경 / 삭제를 할 수 있습니다. 
<br>
<img width="400" alt="스크린샷 2024-01-01 오후 7 21 31" src="https://github.com/gkstjr/self-Develop/assets/99389922/3da5be21-efd3-43e8-a8c9-5e35412efd19">

<br>

## 💡 프로젝트를 하게 된 이유와 경험
- 이번 개인프로젝트의 주 목표는 많진 않더라도 실제 사용자들의 트래픽을 경험하고 싶어 지인들이 편하게 사용할 수 있는 사이트를 구현하기 위해 자기개발 커뮤니티를 생각하였습니다.

- 이전까지의 프로젝트들은 spring legacy(maven) + mybatis + jsp 로 진행해왔기 때문에 Spring Boot(Gradel) + JPA + thymeleaf 의 동작원리를 학습하고 적용해보는 것의 초점을 두었습니다. 그리고 직접 서버를 배포하는 경험을 꼭 해보고 싶어 AWS를 이용한 배포까지가 주 목표였습니다.

- 이전까지는 팀 프로젝트만 진행을 하다 내가 맡은 기능이 아니면 프로젝트가 완료하여도 내가 할 줄 모른다는 것이 아쉬웠는데 개인 프로젝트를 해보니 모든 기능을 다 직접 경험해보고 만들어 볼 수 있어 실력향상의 큰 도움이 되었던 거 같습니다.
<br>

## ⚙️ ERD 

<img src="https://github.com/gkstjr/self-Develop/assets/99389922/e5118d65-74b4-4eac-a746-b9190faf5139" width="1000" height="500"/>


