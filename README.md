# UniFarm

# API

## USERS

| 메소드 | 경로                            | 설명                     |
| ------ | ------------------------------- | ------------------------ |
| POST   | /users/login                    | 로그인                   |
| POST   | /users/signup                   | 회원가입                 |
| GET    | /users/check?email={email}      | 중복 이메일 체크         |
| GET    | /users/authNumber?email={email} | 인증 번호 메일 발송 요청 |
| DELETE | /users/deleteAccount            | 회원 탈퇴                |
| PUT    | /users/modifyAccount            | 회원 정보 수정           |

## PROGRAMS

| 메소드 | 경로                                            | 설명                                  |
| ------ | ----------------------------------------------- | ------------------------------------- |
| GET    | /programs                                       | 전체 프로그램 조회                    |
| GET    | /programs/{userIdx}                             | 사용자 신청 프로그램 조회             |
| GET    | /programs/{userIdx}/recommend?major={major}     | 사용자 전공 기준 프로그램 조회        |
| GET    | /programs/{userIdx}/recommend?keyword={keyword} | 사용자 관심 키워드 기준 프로그램 조회 |
| POST   | /programs                                       | 프로그램 신청                         |

## SEARCHES

| 메소드 | 경로                        | 설명 | 비고                     |
| :----- | --------------------------- | ---- | ------------------------ |
| /GET   | /searches?keyword={keyword} | 검색 | 검색 키워드 DB 저장 필요 |

## BOOKMARKS

| 메소드 | 경로                 | 설명        |
| ------ | -------------------- | ----------- |
| GET    | /bookmarks/{userIdx} | 북마크 조회 |
| POST   | /bookmarks           | 북마크 등록 |

## KEYWORDS

| 메소드 | 경로      | 설명                         | 비고                |
| ------ | --------- | ---------------------------- | ------------------- |
| GET    | /keywords | 가장 많이 검색된 키워드 조회 | ex)Top 5, Top 10... |
