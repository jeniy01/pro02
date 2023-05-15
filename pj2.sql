create table user1(id varchar2(20) primary key, pw varchar2(300) not null, name varchar2(20), tel varchar2(13), addr varchar2(300), email varchar2(50), rdate date);
alter table user1 modify rdate date default sysdate;
desc user1;
commit;

create table goods1(gcode varchar2(20) primary key, gname varchar2(50) not null, gram varchar2(20), price number(20), mung varchar2(500), amount number(20) default 1);
--alter table product add utburl varchar2(500);
--alter table product add proidx varchar2(800);

create table order1(ocode varchar2(20) primary key, id varchar2(20) not null, gcode varchar2(20), amount number(20) default 1, price number(20), ostate varchar2(20) default '배송중', tel varchar2(13), dname varchar2(20), addr varchar2(300), dcode varchar2(20));
alter table order1 add constraint fk_id foreign key(id) references user1 (id);
alter table order1 add constraint fk_gcode foreign key(gcode) references goods1 (gcode);
rename order1 to buy;
commit;
alter table buy drop column ostate;
alter table buy drop column tel;
alter table buy drop column dname;
alter table buy drop column addr;
alter table buy drop column dcode;
alter table buy add odate date default sysdate;
alter table buy add ostate varchar2(20) default '배송전';
alter table buy add tel varchar2(13);
alter table buy add dname varchar2(30);
alter table buy add addr varchar2(300);
alter table buy add dcode varchar2(20);
alter table buy drop primary key;
alter table buy add primary key(ocode);
commit;
select * from buy;

create table basket1(bcode varchar2(20) primary key, id varchar2(20) not null, gcode varchar2(20), amount number(20) default 1);
alter table basket1 add constraint fk_id2 foreign key(id) references user1 (id);
alter table basket1 add constraint fk_gcode2 foreign key(gcode) references goods1 (gcode);
rename basket1 to basket;
alter table basket rename column bcode to bnum;
select * from basket;
commit;

create table pay1(pcode varchar2(20) primary key, id varchar2(20) not null, ocode varchar2(20), p_sd varchar2(20), p_bun varchar2(50), price number(20), pdate date);
alter table pay1 add constraint fk_id3 foreign key(id) references user1 (id);
alter table pay1 add constraint fk_ocode foreign key(ocode) references order1 (ocode);
rename pay1 to payment;
alter table payment rename column pcode to pnum;
alter table payment rename column p_sd to ptype;
alter table payment rename column p_bun to ptnum;
alter table payment rename column price to pprice;

select * from payment;
commit;

create table review1(rcode varchar2(20) primary key, id varchar2(20) not null, ocode varchar2(20), rdate date, hg varchar2(500), str varchar2(20));
alter table review1 add constraint fk_id4 foreign key(id) references user1 (id);
alter table review1 add constraint fk_ocode2 foreign key(ocode) references order1 (ocode);
alter table review rename column rdate to resdate;
alter table review drop column resdate;
alter table review drop column hg;
alter table review add resdate date default sysdate;
desc review;
rename review1 to review;
--drop table review;
commit;
create table review(rcode varchar2(8) primary key, id varchar2(20), ocode varchar2(8), resdate date default sysdate, rcontent varchar2(500) not null, rpoint int default 5, constraint usr_fk foreign key (id) references user1(id), constraint byr_fk foreign key (ocode) references buy(ocode));
--create table review(rcode varchar2(8) primary key, id varchar2(20), ocode varchar2(8), resdate date default sysdate, rcontent varchar2(500) not null, rpoint int default 5, constraint usr_fk foreign key (id) references user1(id), constraint byr_fk foreign key (ocode) references buy(ocode));
select * from review;

drop table review1;
drop table pay1;
drop table basket1;
drop table order1;
drop table goods1;
drop table user1;

commit;

select* from user1;
desc user1;
select* from goods1;
desc goods1;
select* from order1;
desc order1;
select* from basket1;
desc basket1;
select* from pay1;
desc pay1;
select* from review1;
desc review1;
/*
insert into order1 values('1001','kbs','4192',1,68000,'배송완료','010-4321-4321','CJ대한통운','경기도 고양시 일산동구','c123456');
insert into order1 values('1002','sbs','1352',1,28000,'배송완료','010-3333-3333','로젠택배','경기도 김포시 통진읍','r781245');
insert into order1 values('1003','kfm','8162',2,96000,'배송전','010-7979-7979','','경기도 고양시 일산서구','');
insert into order1 values('1004','kbs','8138',1,35000,'배송중','010-4321-4321','한진택배','경기도 고양시 일산동구','h697812');
*/
insert into order1 values('1001','lmk','4192',1,68000,'배송완료','010-4321-4321','CJ대한통운','경기도 고양시 일산동구','c567891');
insert into order1 values('1002','ljn','1352',1,28000,'배송완료','010-3333-3333','롯데택배','경기도 파주시 문산읍','r123456');
insert into order1 values('1003','pjs','8162',2,96000,'배송전','010-7979-7979','','경기도 고양시 일산서구','');
insert into order1 values('1004','lmk','8138',1,35000,'배송중','010-4321-4321','한진택배','경기도 고양시 일산동구','h895622');

--insert into buy values('10001','kbs','4192',1,25000,default,'배송완료','010-4321-4321','CJ대한통운','경기도 고양시 일산동구', 'c123456');
--insert into buy values('10002','sbs','1352',1,15000,default,'배송완료','010-3333-3333','로젠택배','경기도 김포시 통진읍', 'r781245');
--insert into buy values('10003','kfm','8162',2,54000,default,'배송전','010-7979-7979','CJ대한통운','경기도 고양시 일산서구', 'c421357');
--insert into buy values('10004','kbs','8138',1,39000,default,'배송중','010-4321-4321','한진택배','경기도 고양시 일산동구', 'h697812');
select * from buy;
update buy set odate=default, ostate='배송완료', tel='010-1111-1111', dname='CJ대한통운', addr='경기도 고양시 일산동구', dcode='c567891' where id='lmk';
update buy set odate=default, ostate='배송완료', tel='010-3333-3333', dname='로젠택배', addr='경기도 파주시 문산읍', dcode='r123456' where id='ljn';
update buy set odate=default, tel='01055555555', dname='CJ대한통운', addr='경기도 고양시 일산서구', dcode='c421357' where id='pjs';
update buy set odate=default, ostate='배송중', tel='010-1111-1111', dname='한진택배', addr='경기도 고양시 일산동구', dcode='h895622' where ocode='1004';
update buy set ocode='10001' where dcode='c567891';
update buy set ocode='10002' where dcode='r123456';
update buy set ocode='10003' where dcode='c421357';
update buy set ocode='10004' where dcode='h895622';

update payment set ocode='10002' where id='ljn';
update payment set ocode='10003' where id='pjs';
update payment set ocode='10004' where ptype='체크카드';
update payment set ocode='10001' where pprice=68000;
commit;

/*
insert into basket1 values('10001','mbc','6186',1);
insert into basket1 values('10002','kbs','5072',1);
insert into basket1 values('10003','jtb','8138',1);
insert into basket1 values('10004','sbs','8162',1);
*/
insert into basket1 values('10001','ldh','6186',1);
insert into basket1 values('10002','lmk','5072',1);
insert into basket1 values('10003','njm','8138',1);
insert into basket1 values('10004','ljn','8162',1);

/*
insert into pay1 values('10001','kbs','1001','신용카드','123-1234-123456',68000,'2023-04-19');
insert into pay1 values('10002','sbs','1002','계좌이체','083-21-467895',28000,'2023-04-19');
insert into pay1 values('10003','kfm','1003','신용카드','462-127-9123458',96000,'2023-04-20');
insert into pay1 values('10004','kbs','1004','체크카드','214-31-415617',35000,'2023-04-20');
*/
insert into pay1 values('10001','lmk','1001','신용카드','123-1234-123456',68000,'2023-04-19');
insert into pay1 values('10002','ljn','1002','계좌이체','083-21-467895',28000,'2023-04-19');
insert into pay1 values('10003','pjs','1003','신용카드','462-127-9123458',96000,'2023-04-20');
insert into pay1 values('10004','lmk','1004','체크카드','214-31-415617',35000,'2023-04-20');

/*
insert into review1 values('10001','kbs','1001','2023-04-20','상품 잘 받았습니다.','5');
insert into review1 values('10002','sbs','1002','2023-04-20','효과있어요.','4');
*/
insert into review values('10001','lmk','10001',default,'상품 잘 받았습니다.','4');
insert into review values('10002','ljn','10002',default,'효과있어요.',default);

commit;




create table notice(idx int primary key, title varchar2(100) not null, content varchar2(1000), author varchar2(20), file1 varchar2(200), resdate date default sysdate);

create sequence noti_seq;

insert into notice values (noti_seq.nextval, '더미 제목1', '더미1에 대한 내용입니다.', '관리자', '', default);
insert into notice values (noti_seq.nextval, '더미 제목2', '더미2에 대한 내용입니다.', '관리자', '', default);
insert into notice values (noti_seq.nextval, '더미 제목3', '더미3에 대한 내용입니다.', '관리자', '영양성분.pdf', default);

select * from notice;
drop table notice;
drop sequence noti_seq;

commit;

alter table notice add readcnt int default 0;

--모든 컬럼 검색 쿼리
select * from notice;

--특정 레코드 검색 쿼리
select * from notice where idx=?;

--레코드 추가
insert into notice values (noti_seq.nextval, ?, ?, ?, ?, default, default);

--레코드 갱신 쿼리
update notice set title=?, content=?, file1=?, resdate=sysdate where idx=?;

--레코드 삭제 쿼리
delete from notice where idx=?;

commit;

update notice set file1 = 'data/영양성분.pdf' WHERE title='더미 제목3';

desc user1;
select * from user1;
insert into user1 values('admin','1234','관리자','010-1234-1234','경기도 파주시 야당동','admin@nate.com','2023-04-15',default,default);
commit;

update user1 set pw='HnUx6KGntl0kvHgOlr9WudQvtIF8JneNwO5AcseGQ2LL6M3NeRc7K4TDKKkzJYLRq1Bx6A==' where id='lmk';
update user1 set pw='Xvj2C70tbqVrXggKFgSnVn6zRT6MRzSIYMDD0SLlvbhSfn1krh8Na3aq62V18uFcCWbnyA==' where id='ldh';
update user1 set pw='Eni77UgCnS2RkTs1ayfoGXXO3g+gZE97pm756SJoLGvXCVTy42fv0bzGfP59uz6V44M+dA==' where id='ljn';
update user1 set pw='JtVYgXMs8VAZgRLm03Fbv+J1xRy7wCRPMAEjLTiy/bBRcf4nVSP9ynDYIpn2J3OthVqZFg==' where id='njm';
update user1 set pw='yPaTreMWGPIRkeVi6QJnXWxALWN8VoDTy3FX9FdAbg0yiADrNHr6H4LJCk87IpBWmMFRyw==' where id='pjs';
update user1 set pw='8bZ8JlVxwmrawly8Qge2x7M0P66kQMaFJv6u1F8vj1RZnEGyinYOb7uhinRC88g86F0szA==' where id='admin';
commit;

-- 회원 로그인
select * from user1 where id=? and pw=?;

-- 회원 로그인 시 방문횟수 증가
update user1 set visted=visted+1 where id=?

-- 아이디 중복 체크
select * from user1 where id=?

-- 회원가입
insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?);

-- 회원정보수정
update user1 set pw=?, name=?, email=?, tel=?, addr=? where id=?;

-- 회원 탈퇴
delete from user1 where id=?;


select * from user1;

select * from user1 order by regdate desc;

select * from user1 where rownum >= 1 and rownum <= 5;

select * from user1 order by regdate desc where rownum >= 1 and rownum <= 5;

-- 회원 목록을 페이징 처리하는 경우 startNum과 endNum을 받아서 rownum으로 처리
select * from (select * from user1 order by regdate desc) where rownum >= 1 and rownum <= 5;




select * from goods1;
-- 상품 테이블에 카테고리 번호 추가
alter table goods1 add cate varchar2(6);

-- 카테고리 테이블 만들기
create table category(cate varchar2(6) primary key, categroup varchar2(50) not null, catename varchar2(100));
drop table category;

-- 카테고리 데이터 추가
insert into category values('0101','기초영양','프로폴리스');
insert into category values('0102','기초영양','코엔자임큐텐');
insert into category values('0103','기초영양','트리플러스맨');
insert into category values('0104','기초영양','트윈스징크씨');
insert into category values('0105','기초영양','토탈케어');
update category set catename='기초영양' where categroup='기초영양';
update category set catename='프로폴리스' where cate='0101';
update category set catename='코엔자임큐텐' where cate='0102';
update category set catename='트리플러스맨' where cate='0103';
update category set catename='트윈스징크씨' where cate='0104';
update category set catename='토탈케어' where cate='0105';

insert into category values('0201','혈행개선','달맞이꽃종자유');
insert into category values('0202','혈행개선','오메가3');
insert into category values('0203','혈행개선','오메가3울트라');
insert into category values('0204','혈행개선','울트라트윈');
update category set catename='혈행개선' where categroup='혈행개선';
update category set catename='달맞이꽃종자유' where cate='0201';
update category set catename='오메가3' where cate='0202';
update category set catename='오메가3울트라' where cate='0203';
update category set catename='울트라트윈' where cate='0204';
delete from category where cate='0203';
update category set cate='0203' where catename='울트라트윈';
select * from category;
commit;

insert into category values('0301','아이건강','키즈프로바이오틱스');
insert into category values('0302','아이건강','키즈아연');
insert into category values('0303','아이건강','키즈락추로스');
insert into category values('0304','아이건강','키즈면역구미');
update category set catename='아이건강' where categroup='아이건강';
update category set catename='키즈프로바이오틱스' where cate='0301';
update category set catename='키즈아연' where cate='0302';
update category set catename='키즈락추로스' where cate='0303';
update category set catename='키즈면역구미' where cate='0304';

insert into category values('0401','눈건강','수퍼바이오틱스 60캡슐');
insert into category values('0402','눈건강','트리플러스 우먼 90캡슐');
insert into category values('0403','눈건강','밀크씨슬');
insert into category values('0404','눈건강','마그네슘');
update category set catename='종합비타민' where categroup='눈건강';
update category set categroup='종합비타민' where catename='종합비타민';
update category set catename='종합비타민' where categroup='종합비타민';
update category set catename='수퍼바이오틱스' where cate='0401';
update category set catename='트리플러스우먼' where cate='0402';
update category set catename='밀크씨슬' where cate='0403';
update category set catename='마그네슘' where cate='0404';

insert into category values('0501','항산화','트리플러스맨');
insert into category values('0502','항산화','트윈세트');
update category set catename='항산화' where categroup='항산화';
update category set catename='트리플러스' where cate='0501';
update category set catename='트윈세트' where cate='0502';

commit;

select * from category;
select * from user1;

-- 데이터 변경을 위해서는 참조 무결성 해제(외래키 제거)를 해야함
ALTER TABLE order1 DROP CONSTRAINT fk_gcode;
ALTER TABLE buy DROP CONSTRAINT fk_gcode;
ALTER TABLE basket1 DROP CONSTRAINT fk_gcode2;
ALTER TABLE pay1 DROP CONSTRAINT fk_ocode;
ALTER TABLE review1 DROP CONSTRAINT fk_ocode2;
commit;

-- 상품 테이블에 바뀐 상품코드와 카테고리코드를 추가
select * from goods1;
update goods1 set cate='0401', gcode='04010001', pic1='./img/04010001.jpg', pic2='./img/04010001_1.jpg', pic3='./img/04010001_2.jpg' where gcode='4192';
update goods1 set cate='0402', gcode='04020001', pic1='./img/04020001.jpg', pic2='./img/04020001_1.jpg', pic3='./img/04020001_2.jpg' where gcode='6186';
update goods1 set cate='0403', gcode='04030001', pic1='./img/04030001.jpg', pic2='./img/04030001_1.jpg', pic3='./img/04030001_2.jpg' where gcode='5072';
update goods1 set cate='0404', gcode='04040001', pic1='./img/04040001.jpg', pic2='./img/04040001_1.jpg', pic3='./img/04040001_2.jpg' where gcode='1352';
update goods1 set cate='0101', gcode='01010001', pic1='./img/01010001.jpg', pic2='./img/01010001_1.jpg', pic3='./img/01010001_2.jpg' where gcode='8138';
update goods1 set cate='0102', gcode='01020002', pic1='./img/01020002.jpg', pic2='./img/01020002_1.jpg', pic3='./img/01020002_2.jpg' where gcode='8162';
delete from product where cate='0102';
insert into product values('8162','코엔자임큐텐','35g',48000,'건강기능식품 코엔자임큐텐 (60일분)',6,'','','','');
update product set cate='0101', gcode='01020002', pic1='./img/01020002.jpg', pic2='./img/01020002_1.jpg', pic3='./img/01020002_2.jpg' where gcode='8162';
delete from product where cate='0101';
insert into product values('8138','프로폴리스','36g',35000,'건강기능식품 프로폴리스 (60일분)',5,'','','','');
insert into product values('8162','프로폴리스 스프레이','25g',16500,'건강기능식품 프로폴리스 1통',6,'','','','');
update product set cate='0101', gcode='01010001', pic1='./img/01010001.jpg', pic2='./img/01010001_1.jpg', pic3='./img/01010001_2.jpg' where gcode='8138';
update product set cate='0101', gcode='01010002', pic1='./img/01010002.jpg', pic2='./img/01010002_1.jpg', pic3='./img/01010002_2.jpg' where gcode='8162';
commit;

update goods1 set pic1='./img/04010001.jpg', pic2='./img/04010001_1.jpg', pic3='./img/04010001_2.jpg' where gcode='04010001';
update goods1 set pic1='./img/04020001.jpg', pic2='./img/04020001_1.jpg', pic3='./img/04020001_2.jpg' where gcode='04020001';
update goods1 set pic1='./img/04030001.jpg', pic2='./img/04030001_1.jpg', pic3='./img/04030001_2.jpg' where gcode='04030001';
update goods1 set pic1='./img/04040001.jpg', pic2='./img/04040001_1.jpg', pic3='./img/04040001_2.jpg' where gcode='04040001';
update goods1 set pic1='./img/01010001.jpg', pic2='./img/01010001_1.jpg', pic3='./img/01010001_2.jpg' where gcode='01010001';
update goods1 set pic1='./img/01020002.jpg', pic2='./img/01020002_1.jpg', pic3='./img/01020002_2.jpg' where gcode='01020002';
update product set pic1='./img/01010001.jpg', pic2='./img/01010001_1.jpg', pic3='./img/01010001_2.jpg' where gcode='01010001';
update product set pic1='./img/01010002.jpg', pic2='./img/01010002_1.jpg', pic3='./img/01010002_2.jpg' where gcode='01010002';
commit;

-- 장바구니 테이블에 담긴 상품 정보 중에서 상품코드 변경
select * from basket1;
update basket1 set gcode='04020001' where gcode='6186';
update basket1 set gcode='04030001' where gcode='5072';
update basket1 set gcode='01010001' where gcode='8138';
update basket1 set gcode='01020002' where gcode='8162';
delete from basket1 where bcode='10004';
insert into basket1 values('10004','ljn','8162',1);
update basket1 set gcode='01010002' where gcode='8162';

-- 주문 테이블에 담긴 상품 정보 중에서 상품코드 변경
select * from order1;
update order1 set gcode='04010001' where gcode='4192';
update order1 set gcode='04040001' where gcode='1352';
update order1 set gcode='01020002' where gcode='8162';
update order1 set gcode='01010001' where gcode='8138';
delete from order1 where ocode='1003';
insert into order1 values('1003','pjs','8162',2,33000,'배송전','010-7979-7979','','경기도 고양시 일산서구','');
update order1 set gcode='01010002' where gcode='8162';

select * from pay1;

commit;

rename  goods1  to  product;
select * from product;
commit;

--insert into product values('4192','밀크씨슬 3통 세트','1통 당 : 54g',84000,'건강기능식품 밀크씨슬 (180일분)',12,'','','','');
insert into product values('5050','코엔자임큐텐','35g',36000,'건강기능식품 코엔자임큐텐 (60일분)',6,'','','','');
update product set cate='0102', gcode='01020001', pic1='./img/01020001.jpg', pic2='./img/01020001_1.jpg', pic3='./img/01020001_2.jpg' where gcode='5050';
update product set pic1='./img/01020001.jpg', pic2='./img/01020001_1.jpg', pic3='./img/01020001_2.jpg' where gcode='01020001';

insert into product values('5051','트리플러스맨','65.4g',49000,'건강기능식품 남성 트리플러스맨 (45일분)',12,'','','','');
update product set cate='0103', gcode='01030001', pic1='./img/01030001.jpg', pic2='./img/01030001_1.jpg', pic3='./img/01030001_2.jpg' where gcode='5051';
update product set pic1='./img/01030001.jpg', pic2='./img/01030001_1.jpg', pic3='./img/01030001_2.jpg' where gcode='01030001';

insert into product values('7050','달맞이꽃종자유','100.8g',42000,'건강기능식품 감마리놀렌산 (30일분)',5,'','','','');
update product set cate='0201', gcode='02010001', pic1='./img/02010001.jpg', pic2='./img/02010001_1.jpg', pic3='./img/02010001_2.jpg' where gcode='7050';
update product set pic1='./img/02010001.jpg', pic2='./img/02010001_1.jpg', pic3='./img/02010001_2.jpg' where gcode='02010001';

insert into product values('7051','오메가3','94.24g',28000,'건강기능식품 오메가-3 (31일분)',11,'','','','');
update product set cate='0202', gcode='02020001', pic1='./img/02020001.jpg', pic2='./img/02020001_1.jpg', pic3='./img/02020001_2.jpg' where gcode='7051';
update product set pic1='./img/02020001.jpg', pic2='./img/02020001_1.jpg', pic3='./img/02020001_2.jpg' where gcode='02020001';

insert into product values('7052','오메가3울트라','55g',37800,'건강기능식품 오메가-3울트라 (50일분)',2,'','','','');
update product set cate='0202', gcode='02020002', pic1='./img/02020002.jpg', pic2='./img/02020002_1.jpg', pic3='./img/02020002_2.jpg' where gcode='7052';
update product set pic1='./img/02020002.jpg', pic2='./img/02020002_1.jpg', pic3='./img/02020002_2.jpg' where gcode='02020002';

insert into product values('1352','마그네슘','90g',28000,'건강기능식품 마그네슘 (90일분)',13,'','','','');
update product set cate='0404', gcode='04040001', pic1='./img/04040001.jpg', pic2='./img/04040001_1.jpg', pic3='./img/04040001_2.jpg' where gcode='1352';
update product set pic1='./img/04040001.jpg', pic2='./img/04040001_1.jpg', pic3='./img/04040001_2.jpg' where gcode='04040001';

select * from product;
commit;

-- 전체 상품 검색
select * from product order by cate desc;

-- 해당 카테고리 상품만 검색
select * from product where cate=?;

-- 해당 대분류 카테고리 상품군 검색
select * from product where cate like ?||'%';

-- 해당 상품만 검색
select * from product where pcode=?

-- 상품명으로 검색
select * from product where pname like '%'||?||'%';

-- 대분류 카테고리 로딩
select distinct substr(cate,1,2) as ct, categroup from category group by substr(cate,1,2), categroup order by ct;

-- 소분류 카테고리 로딩
select cate, catename from category where substr(cate,1,2)=? order by cate;
select cate, catename from category where substr(cate,1,2)='01' order by cate;

-- 해당 상품 카테고리에서 가장 최근의 등록한 상품코드 로딩
select pcode from (select * from product where cate='0304' order by pcode desc) where rownum = 1;
 
select * from product where cate='0304' order by pcode desc;

commit;

select * from product;
desc buy;
delete from product where gram='90.5g';
delete from product where gname='프로폴리스 스프레이';
delete from product where gname='달맞이꽃종자유';
commit;

select * from category;
select * from user1;
desc user1;

update user1 set pw='HnUx6KGntl0kvHgOlr9WudQvtIF8JneNwO5AcseGQ2LL6M3NeRc7K4TDKKkzJYLRq1Bx6A==' where id='lmk';
update user1 set pw='Xvj2C70tbqVrXggKFgSnVn6zRT6MRzSIYMDD0SLlvbhSfn1krh8Na3aq62V18uFcCWbnyA==' where id='ldh';
update user1 set pw='vpSuKjREot5g/rBkLFX4gI4YbMWNOElKj1BlOO4g54xu5hc9+2TZ3fNgtutPcpKaqCeg4Q==' where id='ljn';
update user1 set pw='JtVYgXMs8VAZgRLm03Fbv+J1xRy7wCRPMAEjLTiy/bBRcf4nVSP9ynDYIpn2J3OthVqZFg==' where id='njm';
update user1 set pw='yPaTreMWGPIRkeVi6QJnXWxALWN8VoDTy3FX9FdAbg0yiADrNHr6H4LJCk87IpBWmMFRyw==' where id='pjs';
update user1 set pw='1mkHre2YXueO2E9RtdjzZnCvP3fwXSEP8fPIGSrAIFKDrTmjJ/L8PlY15OOckeKWtuDv4g==' where id='admin';
commit;

select * from sales;
select * from basket;
desc basket;
select * from payment;
select * from buy;
desc buy;

select * from review;
alter table buy modify ostate varchar2(100);
alter table buy modify dname varchar2(100);
commit;