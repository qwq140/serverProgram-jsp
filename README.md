# JSP 유저리스트 게시판

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

