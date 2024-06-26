## 💟 하트베라 (HeartVera)

프로그램 역할 : 익명으로 연애 고민을 털어놓을 수 있는 사이트
* 프로젝트 기간 : 2024.06.19 ~ 2024.06.25 (7일)
*  🛠️ Tech Stack : <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/>
* 버전: JDK 17
* 개발 환경: IntelliJ

<br>




## 👩‍💻👨‍💻 팀원 구성
|                                                                                  이가은                                                                                   |                                             김예찬                                              |                                             한해정                                              |                                             장재현                                              |                                             홍성도                                              |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------:|
| <img src="https://github.com/HeartVera/HeartVera/assets/130989670/0e177a86-8de0-4838-affb-a7cd3d75c790" height="150"/> | <img src="https://github.com/HeartVera/HeartVera/assets/130989670/339e546e-eb4a-42ab-9f40-a84a8dcae3ad" height="150"/> | <img src="https://github.com/HeartVera/HeartVera/assets/130989670/b55735bd-6cb1-41e2-ac48-f1d5eb2df94a" height="150"/> | <img src="https://github.com/HeartVera/HeartVera/assets/130989670/4179ad93-1105-443f-a1d8-0944be3a2cfc" height="150"/> | <img src="https://github.com/HeartVera/HeartVera/assets/130989670/a0794b54-fec8-4f16-a13b-9dc7898b0e57" height="150"/> |
|                                                          [@GaEun1216](https://github.com/GaEun1216)                                                          |                             [@wondo8449](https://github.com/wondo8449)                             |                          [@HaejungHan](https://github.com/HaejungHan)                          |                         [@JangJaehyeonn](https://github.com/JangJaehyeonn/)                         |                              [@hsd9681](https://github.com/hsd9681)                              |
<br>

## 프로젝트 소개
<details>
<summary> 📑 요구사항 정의 </summary>

* 공통 조건


    * 전체 공통 조건
      
      클라이언트는 Postman이고 프론트엔드는 별도 구현하지 않습니다.


      
    * 사용자 인증 기능 공통 조건
      
      username, password를 클라이언트에서 전달 받습니다.
      
      Spring Security와 JWT를 사용하여 설계 및 구현합니다.
      
      JWT는 Access Token, Refresh Token을 구현합니다.
      
      Access Token 만료 시 : 유효한 Refresh Token을 통해 새로운 Access Token과 Refresh Token을 발급 
      
      Refresh Token 만료 시 : 재로그인을 통해 새로운 Access Token과 Refresh Token을 발급 

      API를 요청할 때는 Access Token을 사용합니다.

      Access Token, Refresh Token은 동시에 전달을 하는게 아니고 용도에 맞게 사용되어야 합니다.
      
</details>
<details>
<summary> ⚙ 기능 명세서 </summary>

<br>

*✔ 필수 기능 / ➕ 추가 기능*
<br>
* 사용자 인증 기능

  ✔ 회원가입

  ✔ 로그인

  ✔ 로그아웃



* 프로필 관리 기능

  ✔ 비밀번호 수정
  
  ✔ 프로필 수정



* 뉴스피드 게시물 CRUD 기능

  ✔ 게시물 작성
  
  ✔ 게시물 조회
  
  ✔ 게시물 수정
  
  ✔ 게시물 삭제
  
  ✔ 페이지네이션
  


* 댓글 CRUD 기능

  ✔ 댓글 작성
  
  ✔ 댓글 수정
  
  ✔ 댓글 삭제

  
* 백오피스 기능

  ➕ 전체 사용자 목록 조회
  
  ➕ 사용자 권한 수정
  
  ➕ 게시글과 댓글 전체 조회
  
  ➕ 게시글 수정 및 삭제


* 좋아요 기능

  ➕ 게시글 좋아요 추가
  
  ➕ 게시글 좋아요 삭제
  
  ➕ 댓글 좋아요 추가
  
  ➕ 댓글 좋아요 삭제


* 팔로우 기능

  ➕ 특정 사용자 팔로우
  
  ➕ 특정 사용자 팔로우
  
  ➕ 뉴스피드에 팔로우하는 사용자의 게시물 조회
  

* 소셜 로그인 기능

  ➕ 네이버 로그인
  
  ➕ 카카오 로그인

</details>
<br>

### ✍🏻 API 명세
> <img src="https://velog.velcdn.com/images/wondo8449/post/eec944ea-e7cb-4a2c-86d8-005eeaad82a9/image.png" width="750px">
> <img src="https://velog.velcdn.com/images/wondo8449/post/53e59ed4-2779-4ef7-8255-e81e135c1df0/image.png" width="750px">
<br>

### 🧬 ERD
> ![image](https://github.com/HeartVera/HeartVera/assets/130989670/eccbb80c-686c-40dd-9bca-a5e591b26708)
<br>


## 🐱 Github Rules
> <img src="https://velog.velcdn.com/images/wondo8449/post/4f4d9075-adf5-41d3-8e0b-48bfac89310b/image.jpg" width="500px">
> <img src="https://velog.velcdn.com/images/wondo8449/post/8cf3bdf8-c5ab-4499-a3a0-9c6b561bce7c/image.jpg" width="500px">

### Issue 예시
> <img src="https://velog.velcdn.com/images/wondo8449/post/ea78fb56-283a-461c-ae17-76f238fd2f77/image.png" width="500px">

### PR 예시
> <img src="https://velog.velcdn.com/images/wondo8449/post/2f8a66df-273c-4d68-a499-37aa2f4dd774/image.png" width="500px">

## 😫 트러블 슈팅
> <img src="https://file.notion.so/f/f/83c75a39-3aba-4ba4-a792-7aefe4b07895/312c2eb2-7b77-42f5-8668-ea2c0fa471d4/Untitled.png?id=c231be88-dd98-4376-b22e-f55097f5b8ab&table=block&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&expirationTimestamp=1718114400000&signature=Hn_dCFF0Tb9SLUK_jWfg_3vgSRiZypzITUW8UQ3g_8I&downloadName=Untitled.png" width="500px">
> <img src="https://file.notion.so/f/f/83c75a39-3aba-4ba4-a792-7aefe4b07895/35c5c602-e1cb-4594-8415-169dc18b587a/Untitled.png?id=a22aa717-75f2-4052-8caa-73cea4d2efeb&table=block&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&expirationTimestamp=1718114400000&signature=qht7j9KFCOiZTuv3C8TVy85xNSczF83UzqewuWsajmA&downloadName=Untitled.png" width="500px">

## 🙌 프로젝트 후기
#### 🤡 이가은
전 프로젝트에서는 주로 CRUD 기능 구현에 집중하여 코드를 작성했다면, 이번 프로젝트에서는 한 단계 더 나아가 관심사 분리에 집중하고, 리프레쉬 토큰 발행 등 이전 프로젝트에서 구현해보지 못한 부분을 맡아서 진행할 수 있어 매우 뜻깊었습니다.
이전 프로젝트에서의 CRUD 경험이 바탕이 되어, 더 효율적이고 유지보수 가능한 코드를 작성하는 데 많은 도움이 되었습니다. 특히, 코드의 가독성과 재사용성을 높이는 방법에 대해 많이 배울 수 있었고, 이러한 경험이 앞으로의 개발에 큰 자산이 될 것이라 생각합니다.
팀원분들 모두 각자의 역할에서 최선을 다해주었고, 덕분에 프로젝트를 성공적으로 마칠 수 있었습니다. 다들 진심으로 고생 많으셨습니다!

#### 🔨 김예찬
직전에 진행했던 프로젝트와 비슷한 부분이 많았는데 그런 부분들은 더 빠르게 개발할 수 있게 되어 좋은 경험이었고 백오피스라는 새로운 부분을 개발하면서
관리자의 기능들을 어떻게 구성하는 것이 좋은지 생각해볼 수 있는 좋은 시간이었습니다!

#### 🌳 한해정
이번 팀 프로젝트는 저에게는 언젠가는 넘어야 할 숙제 같았고, 이번 경험을 통해서 다음 숙제의 디딤돌이 되었으리라 믿으며 최선을 다했던 것 같습니다.
많이 배우고, 느꼈습니다. 이번 계기로 조금 더 성장한 내가 되었으면 하는 바램입니다. 

#### 👾 홍성도
소셜 로그인 기능을 구현하면서 resttemplate와 o.auth를 이해하는데 오래 걸려 코드를 구현하는 과정에서 많은 어려움이 있었고
팀원 분들과 자주 소통하면서 작성한 코드에 대한 리펙토링을 자주 하면서 많은 것들을 배울 수 있었습니

#### 🍔 장재현
프로젝트를 시작할 때 예비군 참여로 인해 뒤늦게 팀프로젝트에 합류하게 되어서 조금의 어려움이 있었지만 팀원들과 튜터님의 도움 덕분에 잘 해결할 수
있었던 것 같습니다
개발하면서 토글 기능을 구현하며 상황에 맞춰 적절한 방법을 선택하고 활용하는 경험이 유익했습니다



