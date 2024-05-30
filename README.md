# ClipStudioDummyDataGenerator
#### ClipStudio 테스트 목적으로 사용자, 동영상, 시청 기록 등의 더미 데이터를 랜덤 생성하는 프로그램입니다.
## 개발 환경 및 사용 기술
![JAVA21 badge](https://img.shields.io/badge/Java-21-d9381e?style=for-the-badge)
![JDBC templates badge](http://img.shields.io/badge/JDBC%20templates-0f52ba?style=for-the-badge)

## 사용 예시
아래 코드의 numOfNewUsersDaily, numOfNewVideosDaily, date 변수를 원하는 값으로 변경합니다.
```java
  int numOfNewUsersDaily = 100; 
  int numOfNewVideosDaily = 10000; 
  LocalDate date = LocalDate.parse("2024-01-30");

  // 일일 신규 유저 insert
  UserDataInsertion.insertRandomUsers(numOfNewUsersDaily, date, jdbcTemplate);

  // 일일 신규 동영상, 광고 insert (기존/신규 유저 모두 동영상 업로드하도록 함, 초기 조회수는 0)
  VideoDataInsertion.insertRandomVideos(numOfNewVideosDaily, date, jdbcTemplate);

  // 일일 동영상 조회수 update (등록된 모든 동영상에 random 조회수를 추가)
  VideoDailyViewsUpdate.updateDailyViewsRandomly(date, jdbcTemplate);
```

## 프로젝트 구조
- **DummyDataGenerator**
    - JDBC template으로 DML 구현
    - 개발자가 지정하는 개수의 사용자 데이터, 동영상 데이터 랜덤 생성
    - 랜덤 사용자의 랜덤 동영상 시청 기록 업데이트
