# 📝 bbc-post-service

> BookBookClub-MSA의 **게시글(피드)** 기능을 담당하는 마이크로서비스입니다.  
> 사용자의 책 감상글을 CRUD 및 좋아요, 검색, 블라인드 처리 등 다양한 기능을 제공합니다.

<br>

----

## 📌 프로젝트 링크

- **🧱 모놀리식 버전**: [BookBookClub (Monolith)](https://github.com/ddururiiiiiii/bookbookclub)
- **📁 MSA 버전**: [BookBookClub-MSA](https://github.com/ddururiiiiiii/BookBookClub-MSA)
- **📄 도메인별 **:
-   [bbc-user-service](https://github.com/ddururiiiiiii/bbc-user-service)
-   [bbc-post-service](https://github.com/ddururiiiiiii/bbc-post-service)


<br>

----

## ⚙️ 사용 기술

| 분류        | 기술                             |
|-------------|----------------------------------|
| Language    | Java 17                          |
| Framework   | Spring Boot, Spring Security     |
| DB          | MySQL, Redis                     |
| Query       | QueryDSL                         |
| API 통신    | OpenFeign (UserService 연동용)   |
| Build Tool  | Gradle                           |

<br>

----
## 📌 주요 기능

### 📄 피드 관리
- 피드 등록 / 조회 / 수정 / 삭제
- Feed에는 이미지 없이 텍스트 기반 감상글만 저장
- Feed → User / Book은 ID만 저장 (연관관계 X)

### 👍 좋아요 기능
- 좋아요/좋아요 취소 (토글 방식)
- 피드별 좋아요 수, 좋아요한 유저 목록 조회
- Redis에 좋아요 랭킹 (주간/월간/연간) 관리

### 🔍 검색
- 제목, 저자, 출판사, 내용에 대한 통합 검색 (QueryDSL)
- 페이징 + 정렬 지원 (최신순, 인기순)

### 🚫 신고/블라인드
- 피드 신고 기능
- 일정 횟수 이상 신고된 피드는 자동 블라인드 처리
- 블라인드된 피드는 조회 API에서 제외됨

<br>

----

## ✅ 완료된 구현

- [x] 피드 CRUD
- [x] 좋아요 토글 및 카운트
- [x] 인기 피드 랭킹 캐시
- [x] 블라인드 처리 및 필터링
- [x] QueryDSL 기반 검색
- [x] 유저 정보 연동 (Feign Client)

<br>

----

## 📂 패키지 구조
~~~bash
com.bookbookclub.bbc_post_service
├── controller # 피드 및 좋아요 API
├── dto # 요청/응답 DTO
├── entity # Feed, Like 등 도메인 엔티티
├── repository # JPA 및 QueryDSL 리포지토리
├── service # 비즈니스 로직
├── global # 공통 설정, 예외 처리
└── client # Feign Client (UserService 통신)
~~~

<br>

----

## 💬 참고

- 유저 정보를 직접 가지지 않으며, `UserService`와의 통신은 Feign Client로 처리합니다.
- `bbc-common` 모듈에서 공통 DTO 및 응답 객체를 사용합니다.

## 💬 참고

- 유저 정보를 직접 가지지 않으며, `UserService`와의 통신은 Feign Client로 처리합니다.
- `bbc-common` 모듈에서 공통 DTO 및 응답 객체를 사용합니다.
