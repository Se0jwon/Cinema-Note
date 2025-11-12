# Cinema-Note

## 🎥 프로젝트 소개

**Cinema-Note**는 영화 감상 기록을 위한 소셜 노트 서비스입니다. 사용자는 영화에 대한 \*\*리뷰(Post)\*\*를 작성하고, 다른 사용자의 리뷰를 볼 수 있습니다. **TMDB API**와 연동하여 영화 정보를 제공하며, 사용자 활동에 기반한 **뱃지 시스템**을 통해 영화 리뷰 경험에 재미를 더했습니다.

-----

## 🛠️ 주요 기능

* **회원 및 인증**
    * 이메일 기반 회원가입/로그인.
    * \*\*JWT(JSON Web Token)\*\*를 활용한 인증 및 권한 관리.
    * 마이페이지: 프로필 정보 수정 (닉네임, 비밀번호, 전화번호, 프로필 이미지 URL) 및 이미지 업로드.
* **영화 검색 및 추천**
    * 영화 제목으로 **TMDB API**를 검색하고 상세 정보를 저장.
    * 장르 ID 목록 기반으로 영화를 추천/조회.
* **리뷰 및 게시글 (Post)**
    * 영화에 대한 제목, 내용, \*\*별점(Rating: 0.5\~5.0)\*\*을 포함한 리뷰 작성.
    * 작성된 리뷰 조회 (전체, 상세, 사용자별) 및 키워드 검색.
* **뱃지 시스템**
    * 사용자의 리뷰 활동(예: 특정 장르 리뷰 5개 이상)에 따라 **뱃지**를 평가하고 부여 (예: "감성왕" 뱃지).
    * 획득한 뱃지 목록을 마이페이지에서 조회.

-----

## 💻 기술 스택

| 구분 | 기술 | 버전 / 상세 |
| :--- | :--- | :--- |
| **Backend Framework** | Spring Boot | 3.4.5 |
| **Language** | Java | 24 |
| **Data Access** | Spring Data JPA | - |
| **Database** | H2 (개발용) / MySQL (배포용) | - |
| **Security** | Spring Security, JWT (JJWT) | - |
| **External API** | TMDB API 연동 | - |

-----

## ⚙️ 환경 설정 및 실행 방법

### 1\. 필수 설정


```yaml
tmdb:
  api-key: d1fb72b17c2d0b9c33bded6239232410

jwt:
  secret: iugILs1RnXBzm7UcPeMFzhwFuEoIHAn/yEheLYVl3PA=
```

* API 키와 시크릿 키는 보안을 위해 실제 키 대신 환경 변수나 외부 설정을 사용을 권장합니다.

### 2\. 프로젝트 실행

```bash
# Gradle을 사용하여 프로젝트 실행
./gradlew bootRun
```

* 애플리케이션은 기본적으로 8080 포트에서 실행됩니다.

### 3\. 초기 데이터 (Development Only)

애플리케이션 실행 시 `InitDataService`에 의해 테스트용 회원 데이터가 자동으로 생성됩니다.

| 이메일 | 비밀번호 (평문) | 닉네임 |
| :--- | :--- | :--- |
| `1234@example.com` | `1234` | `대건우` |
| `5678@example.com` | `1234` | `소건우` |
| `9012@example.com` | `1234` | `ㄱㄱㅇ` |

-----

## 🌐 주요 API 엔드포인트

| 기능 | HTTP Method | URI | 설명 |
| :--- | :--- | :--- | :--- |
| **회원가입** | `POST` | `/api/members/signup` | 신규 회원 등록|
| **로그인** | `POST` | `/api/auth/login` | JWT 토큰 발급|
| **영화 검색** | `GET` | `/api/movies/search?query={query}` | TMDB에서 영화 검색 결과 조회|
| **장르 추천** | `GET` | `/api/movies/recommend?genres={ids}` | 장르 ID 목록 기반 영화 추천|
| **리뷰 작성** | `POST` | `/api/reviews` | 특정 영화에 대한 리뷰 및 별점 저장|
| **내 게시글** | `GET` | `/api/mypage/posts` | 로그인한 사용자의 작성 글 목록 조회|
| **프로필 수정** | `PUT` | `/api/mypage/profile` | 사용자 프로필 정보 수정|
| **내 뱃지** | `GET` | `/api/mypage/badges` | 획득한 뱃지 목록 조회|
| **게시글 검색**| `GET` | `/api/posts/search?keyword={keyword}` | 제목 또는 내용 기반 게시글 검색|