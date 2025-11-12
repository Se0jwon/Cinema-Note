# Cinema-Note

## 1\. 📝 프로젝트 개요 (Project Overview)

**Cinema-Note**는 영화 감상 기록을 위한 소셜 노트 서비스입니다. 사용자는 영화에 대한 리뷰(**Post**)를 작성하고, 다른 사용자의 리뷰를 공유하고 조회할 수 있습니다. TMDB API와 연동하여 영화 정보를 제공하며, 사용자 활동에 기반한 **뱃지 시스템**을 통해 영화 리뷰 경험에 재미를 더했습니다.

## 2\. 🛠️ 기술 스택 (Tech Stack)

| 구분 | 기술 | 버전 / 상세 |
| :--- | :--- | :--- |
| **Backend Framework** | Spring Boot | 3.4.5 |
| **Language** | Java | 24 |
| **Data Access** | Spring Data JPA | - |
| **Database** | H2 (개발) / MySQL (운영) | H2 Console: `/h2-console` |
| **Security** | Spring Security, JWT (JJWT) | Stateless 인증 |
| **External API** | TMDB API | 영화 정보 검색 및 추천 |
| **Tool** | Gradle | 8.13 |

## 3\. 🏗️ 아키텍처 및 주요 설계 (Architecture & Design)

* **Layered Architecture**: Controller-Service-Repository의 전통적인 3계층 구조를 따르는 모놀리식 백엔드입니다.
* **인증 방식**: Spring Security와 JWT를 활용한 **Stateless** 인증 방식을 채택했습니다. 로그인 성공 시 Access Token (1시간)과 Refresh Token (2주)을 발급합니다.
* **DB Auditing**: `BaseTimeEntity`를 상속하여 엔티티의 생성 및 수정 시간을 자동으로 관리합니다.
* **영화 데이터 관리**: 사용자가 리뷰를 작성할 때 TMDB API를 검색하고, 검색된 영화는 중복 저장 방지를 위해 로컬 DB(`Movie` 엔티티)에 캐싱하여 관리합니다.

## 4\. 🔑 핵심 기능 상세 (Core Features)

| 기능 분류 | 상세 내용 |
| :--- | :--- |
| **회원/인증** | 이메일 기반 회원가입 및 로그인. 프로필 정보 (닉네임, 비밀번호, 전화번호) 수정 및 프로필 이미지 업로드 기능 제공. |
| **영화 리뷰 (Post)** | 영화 제목으로 검색된 영화에 대한 리뷰(제목, 내용)와 별점(`rating`)을 작성합니다. 별점은 **0.5 단위**로만 입력 가능하도록 유효성 검사 로직이 적용되어 있습니다. 동일 영화에 대한 리뷰는 덮어쓰기(수정)됩니다. |
| **뱃지 시스템** | 사용자가 특정 장르의 영화에 대해 일정 횟수 이상(예: 5회) 리뷰를 작성하면 해당 장르의 뱃지(예: '감성왕')를 자동으로 부여합니다. |
| **검색** | 게시글 제목 및 내용 기반 키워드 검색 기능을 제공합니다. |

## 5\. 🚀 시작하기 (Getting Started)

### 5.1. 환경 설정 (Prerequisites)

* Java Development Kit (JDK) 24
* Gradle 8.13

### 5.2. API Key 설정

`src/main/resources/application.yml` 파일에 TMDB API Key와 JWT Secret Key를 설정해야 합니다.

```yaml
tmdb:
  api-key: d1fb72b17c2d0b9c33bded6239232410 # 여기에 TMDB API Key를 입력하세요

jwt:
  secret: iugILs1RnXBzm7UcPeMFzhwFuEoIHAn/yEheLYVl3PA= # 여기에 JWT Secret Key를 입력하세요
```

### 5.3. 애플리케이션 실행

```bash
# Unix/macOS
./gradlew bootRun

# Windows
.\gradlew.bat bootRun
```

애플리케이션은 기본적으로 `8080` 포트로 실행됩니다.

### 5.4. 초기 테스트 계정 (Dev Only)

`InitDataService`에 의해 테스트용 계정이 자동으로 생성됩니다.

| 이메일 | 비밀번호 (평문) | 닉네임 |
| :--- | :--- | :--- |
| `1234@example.com` | `1234` | `대건우` |
| `5678@example.com` | `1234` | `소건우` |

## 6\. 🌐 주요 API 엔드포인트 (Key API Endpoints)

| 기능 | HTTP Method | URI | 권한 |
| :--- | :--- | :--- | :--- |
| **회원가입** | `POST` | `/api/members/signup` | Public |
| **로그인** | `POST` | `/api/auth/login` | Public |
| **내 게시글** | `GET` | `/api/mypage/posts` | Bearer Token 필요 |
| **프로필 수정** | `PUT` | `/api/mypage/profile` | Bearer Token 필요 |
| **리뷰 작성** | `POST` | `/api/posts` | Bearer Token 필요 |
| **영화 검색** | `GET` | `/api/movies/search?query={query}` | Public |
| **장르 추천** | `GET` | `/api/movies/recommend?genres={ids}` | Public |

## 7\. 🧐 품질 및 개선 방향 (Quality & Future)

### 7.1. 품질 관리 (Quality Management)

* **데이터 유효성**: `spring-boot-starter-validation`을 이용해 API 요청 DTO에 대한 유효성 검사를 수행합니다. 특히 별점(`rating`) 필드에 0.5 단위 검증 로직이 포함되어 데이터 무결성을 높였습니다.
* **비즈니스 예외**: 중복 이메일 가입(`DuplicateEmailException`) 등 비즈니스 로직에서 발생하는 오류를 사용자 정의 예외 클래스로 분리하여 명확한 오류 처리를 유도합니다.

### 7.2. 향후 개선 방향 (Future Enhancements)

* **JWT Refresh Token 관리**: 현재 구현된 Refresh Token 발급 로직을 확장하여, Redis 등 외부 저장소를 활용한 Refresh Token 유효성 검증 및 만료 관리를 통해 보안을 강화해야 합니다.
* **배포 환경 전환**: 개발용 H2 DB 대신 MySQL 또는 PostgreSQL과 같은 상용 DB로 전환하고, CI/CD 파이프라인을 구축하여 안정적인 운영 환경을 확보해야 합니다.
* **프론트엔드 연동**: RESTful API를 활용하여 영화 목록/상세 페이지, 리뷰 조회/작성 등 사용자 인터페이스를 구현하는 작업이 필요합니다.