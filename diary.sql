create database diary default character set utf8;

use diary;

drop table member;
drop table board;

/* 회원 */
CREATE TABLE member (
	memberid varchar(50) primary key, /* 회원ID */
    password varchar(50) NOT NULL, /* 비밀번호 */
	nickname varchar(50) NOT NULL unique, /* 닉네임 */
	email varchar(50) NOT NULL, /* 이메일 */
	regdate datetime default now() /* 가입날짜 */
)engine=InnoDB default character set = utf8;

/* 게시판 */
CREATE TABLE board (
	board_no INT auto_increment primary key, /* 글번호 */
	memberid varchar(50) NOT NULL, /* 회원ID */
	nickname varchar(50) NOT NULL, /* 닉네임 */
	title varchar(255) NOT NULL, /* 글제목 */
	content text, /* 글내용 */
    photo varchar(255), /* 사진파일 */
	regdate datetime default now(), /* 작성날짜 */
	read_count int /* 조회수 */
)engine=InnoDB default character set = utf8;
    
    select * from member;
    select * from board;
    desc member;
    
    select count(*) from board;
    select max(board_no) from board;
    
 