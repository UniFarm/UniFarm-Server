# UniFarm-Server
# API

## USERS

| 메소드 | 경로                       | 설명            |      |
| ------ | -------------------------- | --------------- | ---- |
| POST   | /login                     | 로그인          |      |
| POST   | /users                     | 회원가입        |      |
| GET    | /users/check?email={email} | 이메일 체크     |      |
| GET    | /users/{userIdx}           | 마이페이지 조회 |      |
|        |                            |                 |      |
|        |                            |                 |      |
|        |                            |                 |      |
|        |                            |                 |      |
|        |                            |                 |      |

## ALARMS

| 메소드 | 경로                        | 설명         |      |
| ------ | --------------------------- | ------------ | ---- |
| GET    | ~~/users/{userIdx~~}/alarms | 내 알람 조회 |      |

## BOARDS

| 메소드 | 경로                        | 설명           |      |
| ------ | --------------------------- | -------------- | ---- |
| GET    | /boards                     | 램덤 피드 조회 |      |
| GET    | /boards/{boardIdx}          | 기록하기 조회  |      |
| POST   | ~~/users/{userIdx}~~/boards | 기록하기 작성  |      |
| PUT    | /boards/{boardIdx}/open     | 공개, 비공개   |      |
|        |                             |                |      |

## LIKES

| 메소드 | 경로                      | 설명            |      |
| ------ | ------------------------- | --------------- | ---- |
| POST   | boards/{boardIdx}/likes   | 기록하기 좋아요 |      |
| POST   | courses/{courseIdx}/likes | 코스 좋아요     |      |

## COMMENTS

| 메소드 | 경로                          | 설명                 |      |      |
| ------ | ----------------------------- | -------------------- | ---- | ---- |
| GET    | /boards/{boardIdx}/comments   | 기록하기 댓글들 조회 |      |      |
| GET    | /courses/{courseIdx}/comments | 코스 댓글들 조회     |      |      |
| POST   | /boards/{boardIdx}/comments   | 기록하기 댓글 작성   |      |      |
| POST   | /courses/{courseIdx}/comments | 코스 댓글 작성       |      |      |

## ADDRESSES

| 메소드 | 경로                         | 설명           |      |
| ------ | ---------------------------- | -------------- | ---- |
| GET    | /addresses?address={address} | 주소 검색      |      |
| POST   | /addresses                   | 신규 주소 등록 |      |
|        |                              |                |      |

## SCRAPS

| 메소드 | 경로                                          | 설명                      |      |
| ------ | --------------------------------------------- | ------------------------- | ---- |
| GET    | ~~/users/{userIdx}~~/scraps                   | 사용자 스크랩 게시글 조회 |      |
| POST   | ~~/users/{userIdx}~~/scraps/boards/{boardIdx} | 게시글 스크랩             |      |
| DELETE | ~~/users/{userIdx}~~/scraps/boards/{boardIdx} | 게시글 스크랩 삭제        |      |
|        |                                               |                           |      |

## SHARES

| 메소드 | 경로 | 설명      |      |
| ------ | ---- | --------- | ---- |
| POST   |      | 공유      |      |
| DELETE |      | 공유 해제 |      |
|        |      |           |      |

## SEARCHES

| 메소드 | 경로                        | 설명 |      |
| :----- | --------------------------- | ---- | ---- |
| /GET   | /searches?keyword={keyword} | 검색 |      |
|        |                             |      |      |
|        |                             |      |      |

## HASHTAGS

| 메소드 | 경로                | 설명          |      |
| ------ | ------------------- | ------------- | ---- |
| GET    | /hashtags?tag={tag} | 해시태그 조회 |      |
| POST   | /hashtags           | 해시태그 등록 |      |
|        |                     |               |      |

