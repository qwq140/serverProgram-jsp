# JSP 유저리스트 게시판

## 평가내용
- Apache Tomcat WAS를 설치하고 웹 포트번호는 8000로 설정되었다.
- WAS의 utf-8 설정을 위한 필터가 적용되었다.
- WAS의 세션 타임아웃 시간이 30분으로 설정되었다.
- model 패키지에 beans 클래스가 작성되었다.
- DAO(Data Access Object) 클래스가 작성되었다.
- DAO 클래스에서 Connection이 완료된 객체를 종료시켰다.
- Injection 공격에 대비되었다.
- 세션을 이용한 로그인 기반 서비스가 구현되었다.
- 관리자 화면에서 모든 게시물을 삭제할 수 있는 권한을 부여하였다.
- JDBC를 이용한 데이터 처리가 가능하다.


## MySQL 데이터베이스 생성 및 사용자 생성

```sql
create user 'boarduser'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'boarduser'@'%';
create database board;
```

## MySQL 테이블 생성

- boarduser 사용자로 접속
- use board; 데이터 베이스 선택

```sql
CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    email varchar(100) not null,
    userRole varchar(20)
);
```

## MySQL 관리자 계정 임의로 INSERT

```sql
INSERT INTO user (username, password, email, userRole) VALUES('admin','1234','admin@gmail.com','admin');
```

