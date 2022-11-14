-- 유저 테이블
drop table users;
drop table projectImgs;
drop table project;
drop table board;
drop table reply;
drop table notice;
drop table noticeImgs;
CREATE TABLE users (
  id varchar2(100) not null,
  name varchar2(255),
  password varchar2(200),
  identity number(1),
  deparment varchar2(30),
  grade_number number(1),
  class_number number(1),
  profile_img varchar2(200),
  constraint users_pk primary key (id)
);


insert into users values ('SD@D32PO','박유빈','1234',2,'소프트웨어개발과',3,1,'profile01');
insert into users values ('QED@BS*D','강지우','1234',2,'소프트웨어개발과',3,1,'profile02');
insert into users values ('QED@BS*A','관리자','1234',0,'','','','');
insert into users values ('QED@BS@D','선생님','1234',1,'소프트웨어개발과',3,1,'profile02');


-- 작품 테이블 
CREATE TABLE project (
  id number(10) not null,
  title varchar2(200),
  content varchar2(500),
  writer_id varchar2(100),
  write_date varchar2(200),
  heart number(10),
  constraint project_pk primary key (id)
);
select n.id as id, n.content as content, nImg.imgname as img
from notice n, noticeImgs nImg where n.id = nImg.noticeId;

insert into project values (1,'title','content','SD@D32PO','2022-11-08 08:36:22',0);
create table projectImgs(
	id number(10) not null,
	projectId number(10),
	imgName varchar2(200),
	constraint pImg_pk primary key (id)
);

insert into projectImgs values(1, 1, 'test.png');

create table board (
	id number(10) not null,
	title varchar2(200),
	content varchar2(500),
  	writer_id varchar2(100),
  	write_date varchar2(200),
  	constraint board_pk primary key (id)
);

insert into board values(1,'새로 게시판 개설함','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(2,'새로 게시판 개설함2','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(3,'새로 게시판 개설함3','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(4,'새로 게시판 개설함4','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(5,'새로 게시판 개설함5','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(6,'새로 게시판 개설함6','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(7,'새로 게시판 개설함7','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(8,'새로 게시판 개설함8','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(9,'새로 게시판 개설함9','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');

insert into board values(10,'새로 게시판 개설함10','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(11,'새로 게시판 개설함11','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(12,'새로 게시판 개설함12','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(13,'새로 게시판 개설함13','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');


insert into board values(14,'새로 게시판 개설함14','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(15,'새로 게시판 개설함15','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(16,'새로 게시판 개설함16','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(17,'새로 게시판 개설함17','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(18,'새로 게시판 개설함18','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(19,'새로 게시판 개설함19','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(20,'새로 게시판 개설함20','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(21,'새로 게시판 개설함21','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');
insert into board values(22,'새로 게시판 개설함22','공지사항 잘 읽어주세요','SD@D32PO','2022-08-08');


create table reply(
	id number(10) not null,
	content varchar2(500),
	board_id number(10),
  	writer_id varchar2(100),
  	write_date varchar2(200),
  	constraint reply_pk primary key (id)
);




--로그인할 때 졸업생인 경우 이름과 생년월일을 적으면 인증 가능
create table honor(
	id number(10) not null,
	content varchar2(150),
	writer_id varchar2(100),
	writer_date varchar2(100),
	constraint honor_pk primary key (id)
);

insert into honor values(1, '기능반 정말 좋아요','QED@BS*A', '2022-11-07');
insert into honor values(2, '기능반 정말 좋아요2','QED@BS*A', '2022-11-07');
insert into honor values(3, '기능반 정말 좋아요3','QED@BS*A', '2022-11-07');
insert into honor values(4, '기능반 정말 좋아요4','QED@BS*A', '2022-11-07');
insert into honor values(5, '기능반 정말 좋아요5','QED@BS*A', '2022-11-07');
create table HonorReply (
	id number(10) not null,
	content varchar2(500),
	honor_id number(10),
  	writer_id varchar2(100),
  	write_date varchar2(200)
);



insert into HonorReply values (1, '이렇게 저렇게 할 수 있나요?', 1,'SD@D32PO','2022-10-31'  );
insert into HonorReply values (2, '이렇게 저렇게 할 수 있나요?2', 2,'SD@D32PO','2022-10-31'  );
insert into HonorReply values (3, '멋지네오', 2,'SD@D32PO','2022-10-31'  );

create table notice(
	id number(10) not null,
	notice_type varchar2(100),
	title varchar2(100),
	content varchar2(500),
	writer_id varchar2(100),
	write_date varchar2(200),
	constraint notice_pk primary key (id)
);

create table noticeImgs(
	id number(10) not null,
	noticeId number(10),
	imgName varchar2(200),
	constraint nImg_pk primary key (id)
);
insert into noticeImgs values(1, '1', 'test.png');


create table noticeType (
	id number(10) not null,
	selection varchar2(100),
	constraint notice_id_pk primary key (id)
);

insert into noticeType values (1, '도서관');	
insert into noticeType values (2, '급식');	
SELECT * FROM  noticeType WHERE selection IN ('도서관');

