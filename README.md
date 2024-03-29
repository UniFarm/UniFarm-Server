# UniFarm Project Server Repository

# ABOUT Unifarm

### NH 디지털혁신캠퍼스 챌린지 해커톤 장려상 수상 프로젝트

### 참여한 팀원
PM + 디자인 : 김혜지 / Android 개발 : 김무현 양승희 / 서버 개발 : 박영우 성누리

### 서비스 개괄
![유니팜_개괄](https://user-images.githubusercontent.com/23696493/65023932-d5510f00-d96e-11e9-8e9f-4f4acdd414cd.jpg)

# ERD
![unifarm_20190906_58_56](https://user-images.githubusercontent.com/23696493/64415351-1116e980-d0d0-11e9-9f39-dca52f02f7e0.png)

# API

## USERS

| 메소드 | 경로                       | 설명             |
| ------ | -------------------------- | ---------------- |
| POST   | /users/login               | 로그인           |
| POST   | /users/signup              | 회원가입         |
| GET    | /users/check?email={email} | 중복 이메일 체크 |
| DELETE | /users/deleteAccount       | 회원 탈퇴        |
| PUT    | /users/modifyAccount       | 회원 정보 수정   |

## PROGRAMS

| 메소드 | 경로                          | 설명                                  |
| ------ | ----------------------------- | ------------------------------------- |
| GET    | /programs                     | 전체 프로그램 조회                    |
| GET    | /programs/{userIdx}           | 사용자 신청 프로그램 조회             |
| GET    | /programs/{userIdx}/major     | 사용자 전공 기준 프로그램 조회        |
| GET    | /programs/{userIdx}/keyword   | 사용자 관심 키워드 기준 프로그램 조회 |
| POST   | /programs                     | 프로그램 신청                         |
| GET    | /programs/popular?date={date} | 실시간 인기 프로그램 조회             |

## SEARCHES

| 메소드 | 경로                        | 설명 |
| :----- | --------------------------- | ---- |
| GET   | /searches?keyword={keyword} | 검색 |

## BOOKMARKS

| 메소드 | 경로                 | 설명        |
| ------ | -------------------- | ----------- |
| GET    | /bookmarks/{userIdx} | 북마크 조회 |
| POST   | /bookmarks           | 북마크 등록 |

## KEYWORDS

| 메소드 | 경로      | 설명                         | 비고                |
| ------ | --------- | ---------------------------- | ------------------- |
| GET    | /keywords | 가장 많이 검색된 키워드 조회 | ex)Top 5, Top 10... |
