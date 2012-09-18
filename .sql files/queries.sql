
														APPENDIX C

select count(*) from (select  CIRCLEID from CSETEAM61.CIRCLES where OWNER = 'cbalawat' order by CIRCLEID)
select CIRCLEID from CSETEAM61.CIRCLES where OWNER = 'cbalawat'

select FRIENDID from CSETEAM61.FRIEND where USERID = 'vision' and FRIENDID not in (select USERID from CSETEAM61.CIRCLEMEMBER where CIRCLEID = '2011')

insert into CSETEAM61.CIRCLEMEMBER values ('2011', 'wsong')

select DISTINCT A.USERID, B.EMAILID from CSETEAM61.SALES A, CSETEAM61.USERS B  where A.ADVTID = '1' and A.USERID = B.USERID
select count(*) from (select DISTINCT A.USERID, B.EMAILID from CSETEAM61.SALES A, CSETEAM61.USERS B  where A.ADVTID = '1' and A.USERID = B.USERID)

select NOOFAVAILUNIT from CSETEAM61.ADVERTISEMENTS where ADVTID = '2'

update CSETEAM61.ADVERTISEMENTS  set NOOFAVAILUNIT = NOOFAVAILUNIT - '3' where ADVTID = '4' 

insert into CSETEAM61.SALES values ('4', '1', '2012-03-19', '3', 'vision')

select max(TRANSACTIONID) from CSETEAM61.SALES

select * from CSETEAM61.ADVERTISEMENTS where NOOFAVAILUNIT <> 0 
select count(*) from CSETEAM61.ADVERTISEMENTS where NOOFAVAILUNIT <> 0 

select count(*) from (select USERID from CSETEAM61.SIPMEMBER where SIPID = 'brfc' and USERID not in(Select USERID from CSETEAM61.SIPMODERATOR where SIPID = 'brfc'))
select USERID from CSETEAM61.SIPMEMBER where SIPID = 'brfc' and USERID not in(Select USERID from CSETEAM61.SIPMODERATOR where SIPID = 'brfc')

select count(*) from (select USERID from CSETEAM61.SIPMODERATOR WHERE SIPID = 'brfc')
select USERID from CSETEAM61.SIPMODERATOR WHERE SIPID = 'brfc'

Insert into CSETEAM61.CIRCLES values ('abcd', 'efgh', 'cbalawat')

Insert into CSETEAM61.CIRCLEMEMBER values ('abcd', 'vision')

select max(COMMENTID) from CSETEAM61.COMMENTS

insert into CSETEAM61.COMMENTS values ('6', '2012-03-19', 'hello', 'vision', '1')

select max(COMMENTID) from CSETEAM61.SIPCOMMENTS

insert into CSETEAM61.SIPCOMMENTS values ('6', '2012-03-19', 'hello', 'vision', '1')

select * from CSETEAM61.COMMENTS where POSTID = '1'
select count(*) from CSETEAM61.COMMENTS where POSTID = '1'

select * from CSETEAM61.SIPCOMMENTS where SIPOSTID = '1'
select count(*) from CSETEAM61.SIPCOMMENTS where SIPOSTID = '1'

insert into CSETEAM61.SIPMEMBER values ('brfc', 'wsong')

select count(*) from CSETEAM61.SIPREQUEST where SIPID = 'brfc' and USERID = 'vision'

delete from CSETEAM61.SIPREQUEST where SIPID = 'brfc' and USERID = 'vision'

delete from CSETEAM61.SIPINVITE where SIPID = 'brfc' and USERID = 'brfc'

select count(*) from CSETEAM61.SIPMEMBER where SIPID = 'brfc' and USERID = 'brfc'

select max(ADVTID) from CSETEAM61.ADVERTISEMENTS

insert into CSETEAM61.ADVERTISEMENTS values ('8', 'clothing', '2012-03-19', 'levi', 'jeans', '23.45', '76')

insert into CSETEAM61.SIPAGES values ('abcd', 'efgh')

insert into CSETEAM61.SIPTOPAGE values ('5', 'abcd')

insert into CSETEAM61.SIPMEMBER values ('abcd', 'cbalawat')

insert into CSETEAM61.SIPMODERATOR values ('abcd', 'vision')

select ADVTID, COMPANY, ITEMNAME from CSETEAM61.ADVERTISEMENTS
select count(*) from (select COMPANY, ITEMNAME from CSETEAM61.ADVERTISEMENTS)

select count(*) from CSETEAM61.ADVERTISEMENTS
select * from CSETEAM61.ADVERTISEMENTS

select CIRCLEID, CIRCLENAME from CSETEAM61.CIRCLES where OWNER = 'cbalawat'
select count(*) from (select CIRCLEID, CIRCLENAME from CSETEAM61.CIRCLES where OWNER = 'cbalawat')

delete from CSETEAM61.POSTS where POSTID = '3'

delete from CSETEAM61.WALL where POSTID = '3'

delete from CSETEAM61.COMMENTS where POSTID = '3'

delete from CSETEAM61.SIPOSTS where SIPOSTID = '3'

delete from CSETEAM61.SIPCOMMENTS where SIPOSTID = '3'

select FRIENDID from CSETEAM61.FRIEND where USERID = 'cbalawat'
select count(FRIENDID) from CSETEAM61.FRIEND where USERID = 'cbalawat'

select CIRCLEID, CIRCLENAME from CSETEAM61.CIRCLES where OWNER = 'cbalawat'
select count(*) from (select CIRCLEID, CIRCLENAME from CSETEAM61.CIRCLES where OWNER = 'cbalawat')

delete from CSETEAM61.SIPMODERATOR where SIPID = 'brfc' and USERID = 'wsong'

delete from CSETEAM61.UMESSAGES where MESSAGEID = '4'

delete from CSETEAM61.MSGRECEIVER where MESSAGEID = '4'

select A.SIPOSTID, A.DATENTIME, A.CONTENT, A.POSTAUTHOR FROM CSETEAM61.SIPOSTS A, CSETEAM61.SIPTOPAGE B WHERE A.PAGEID = B.PAGEID AND A.PAGEID = '1'
select count(*) from (select A.SIPOSTID, A.DATENTIME, A.CONTENT, A.POSTAUTHOR FROM CSETEAM61.SIPOSTS A, CSETEAM61.SIPTOPAGE B WHERE A.PAGEID = B.PAGEID AND A.PAGEID = '1')

select SIPID from CSETEAM61.SIPTOPAGE where PAGEID = '1'

delete from CSETEAM61.CIRCLEMEMBER where CIRCLEID = '2011' and USERID = 'vision'

delete from CSETEAM61.ADVERTISEMENTS where COMPANY = 'levi' and ITEMNAME = 'jeans'

delete from CSETEAM61.FRIEND where FRIENDID = 'wsong' and USERID = 'wsong'

delete from CSETEAM61.CIRCLEMEMBER where CIRCLEID = '2011'

delete from CSETEAM61.CIRCLES where CIRCLEID = '2011'

insert into CSETEAM61.SIPMEMBER values ('abcd', 'wsong')

delete from CSETEAM61.SIPREQUEST where SIPID = 'abcd' and USERID = 'wsong'

delete from CSETEAM61.SIPINVITE where SIPID = 'abcd' and USERID = 'wsong'

Insert into CSETEAM61.SIPMODERATOR values ('abcd', 'wsong')

select count(*) from CSETEAM61.SIPMEMBER where SIPID = 'abcd' and USERID = 'wsong'

select count(*) from CSETEAM61.SIPINVITE where SIPID = 'abcd' and USERID = 'wsong'

insert into CSETEAM61.SIPINVITE values ('abcd', 'wsong')

select USERID from CSETEAM61.CIRCLEMEMBER where CIRCLEID = '2011' and USERID <> 'cbalawat'

select count(USERID) from CSETEAM61.CIRCLEMEMBER where CIRCLEID = '2011' and USERID <> 'cbalawat'

select USERID from CSETEAM61.USERS where FIRSTNAME = 'chiranjeevi' and LASTNAME = 'balawat'

insert into CSETEAM61.FRIEND values ('cbalawat','vision')

delete from CSETEAM61.FRIENDREQ where USERID = 'cbalawat' and FRIENDID = 'vision'

select USERID from CSETEAM61.USERS where FIRSTNAME = 'ajay' and LASTNAME = 'joshi'

SELECT * FROM CSETEAM61.SALES WHERE ADVTID IN (SELECT ADVTID FROM CSETEAM61.ADVERTISEMENTS) ORDER BY NOOFUNITSOLD
select count(*) from (SELECT * FROM CSETEAM61.SALES WHERE ADVTID IN (SELECT ADVTID FROM CSETEAM61.ADVERTISEMENTS) ORDER BY NOOFUNITSOLD)

select * from CSETEAM61.ADVERTISEMENTS
select count(*) from (select * from CSETEAM61.ADVERTISEMENTS)

SELECT B.COMPANY, B.ITEMNAME, A.NOOFUNITSOLD FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID ORDER BY A.NOOFUNITSOLD
select count(*) from (SELECT B.COMPANY, B.ITEMNAME, A.NOOFUNITSOLD FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID ORDER BY A.NOOFUNITSOLD)

select distinct(B.ADVTTYPE) from CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B where A.ADVTID = B.ADVTID and A.USERID = 'cbalawat'

select * from CSETEAM61.ADVERTISEMENTS where ADVTTYPE = 'clothing'

Select * from CSETEAM61.LOGININFO where USERID = 'cbalawat' and PASSWORD = 'chiru'

select count(*) from (select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIEND where USERID = 'cbalawat'))
select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIEND where USERID = 'cbalawat')

select count(*) from (select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIENDREQ where USERID = 'cbalawat'))
select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIENDREQ where USERID = 'cbalawat')

select PAGEID from CSETEAM61.USERTOPAGE where USERID = 'cbalawat'

insert into CSETEAM61.POSTS values ('6', '2012-03-19', 'hello', 'vision', '1')

insert into CSETEAM61.WALL values ('6', 'cbalawat', '2012-03-19', 'hello')

select max(POSTID) from CSETEAM61.POSTS

select USERID from CSETEAM61.USERS where FIRSTNAME = 'chiranjeevi' and LASTNAME = 'balawat'

select PAGEID from CSETEAM61.SIPTOPAGE where SIPID = 'brfc'

insert into CSETEAM61.SIPOSTS values ('6', '2012-03-19', 'hello', 'vision', '1')

select max(SIPOSTID) from CSETEAM61.SIPOSTS

select count(*) from CSETEAM61.SIPMODERATOR where USERID = 'cbalawat'

select SIPID from CSETEAM61.SIPMODERATOR where USERID = 'cbalawat'

select distinct SIPID from CSETEAM61.SIPREQUEST where SIPID in (select SIPID from CSETEAM61.SIPMODERATOR where USERID = 'cbalawat')
select count(distinct SIPID) from CSETEAM61.SIPREQUEST where SIPID in (select SIPID from CSETEAM61.SIPMODERATOR where USERID = 'vision')

select SIPID from CSETEAM61.SIPINVITE where USERID = 'wsong'
select count(*) from (select SIPID from CSETEAM61.SIPINVITE where USERID = 'wsong')

Select  USERID, sum(rev) as total from ( select B.USERID, B.NOOFUNITSOLD* A.UNITPRICE as rev from CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A where A.ADVTID = B.ADVTID) group by USERID order by total DESC FETCH FIRST 1 ROWS ONLY

Select  ITEMNAME, sum(rev) as total from ( select A.ITEMNAME, B.NOOFUNITSOLD* A.UNITPRICE as rev from CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A where A.ADVTID = B.ADVTID) group by ITEMNAME order by total DESC FETCH FIRST 1 ROWS ONLY

SELECT B.USERID FROM CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A WHERE A.ADVTID = B.ADVTID AND ITEMNAME = 'jeans'
select count(*) from (SELECT B.USERID FROM CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A WHERE A.ADVTID = B.ADVTID AND ITEMNAME = 'jeans')

SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ITEMNAME = 'jeans'

SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ADVTTYPE = 'clothing'

SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND A.USERID = 'cbalawat'

select COUNT(USERID) from CSETEAM61.USERS WHERE USERID = 'cbalawat'

SELECT COUNT(DISTINCT ITEMNAME) FROM CSETEAM61.ADVERTISEMENTS WHERE ITEMNAME = 'jeans'

SELECT COUNT(DISTINCT ADVTTYPE) FROM CSETEAM61.ADVERTISEMENTS WHERE ADVTTYPE = 'clothing'

SELECT COUNT(*) FROM (SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ITEMNAME = 'jeans'
SELECT COUNT(*) FROM (SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ADVTTYPE = 'clothing'
SELECT COUNT(*) FROM (SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND A.USERID = 'cbalawat'

select TRANSACTIONID, ADVTID, DATENTIME, NOOFUNITSOLD, USERID from CSETEAM61.SALES where DATENTIME like '_____03___'
select count(*) from (select TRANSACTIONID, ADVTID, DATENTIME, NOOFUNITSOLD, USERID from CSETEAM61.SALES where DATENTIME like '_____03___')

select USERID from CSETEAM61.USERS where FIRSTNAME = 'chiranjeevi'

insert into CSETEAM61.FRIENDREQ values ('cbalawat','wsong')

Insert into CSETEAM61.SIPREQUEST values ('brfc' ,'cbalawat')

select count(*) from CSETEAM61.SIPMEMBER where SIPID = 'brfc' and USERID = 'cbalawat'

select count(*) from CSETEAM61.SIPREQUEST where SIPID = 'brfc' and USERID = 'cbalawat'

select USERID from CSETEAM61.SIPREQUEST where SIPID = 'brfc'

select count(USERID) from CSETEAM61.SIPREQUEST where SIPID = 'brfc'

select count(*) from CSETEAM61.UMESSAGES

insert into CSETEAM61.UMESSAGES values ('4', '2012-03-19', 'hi', 'hello', 'cbalawat')

insert into CSETEAM61.MSGRECEIVER values ('4', 'vision')

select USERID from CSETEAM61.USERS where FIRSTNAME = 'chiranjeevi' and LASTNAME = 'balawat'

insert into CSETEAM61.ZIPLOCATION values ('11795', 'karnataka', 'India')
insert into CSETEAM61.USERS values ('a', 'abcd', 'efgh', 'M', 'abcdefgh@gmail.com', '1990-02-02', 'NY', '11794' )
insert into CSETEAM61.PREFERENCES values ('a', 'running')
insert into CSETEAM61.LOGININFO values ('a', 'b')
insert into CSETEAM61.PHONENUMBER values ('a','home','7623748823')
insert into CSETEAM61.USERTOPAGE values ('8', 'a')
select max(PAGEID) from CSETEAM61.USERTOPAGE

select A.PAGEID, A.SIPID from CSETEAM61.SIPTOPAGE A, CSETEAM61.SIPMEMBER B where A.SIPID = B.SIPID AND B.USERID = 'cbalawat'
select count(*) FROM (select A.PAGEID, A.SIPID from CSETEAM61.SIPTOPAGE A, CSETEAM61.SIPMEMBER B where A.SIPID = B.SIPID AND B.USERID = 'cbalawat'

select count(USERID) from CSETEAM61.USERS where USERID = 'cbalawat'
select * from CSETEAM61.SALES where USERID = 'cbalawat' order by ADVTID
select * from CSETEAM61.SALES where ADVTID in (select ADVTID from CSETEAM61.ADVERTISEMENTS  where ITEMNAME = 'jeans')
select count(*) from (select * from CSETEAM61.SALES where USERID = 'cbalawat' order by ADVTID)
select count(*) from (select * from CSETEAM61.SALES where ADVTID in (select ADVTID from CSETEAM61.ADVERTISEMENTS  where ITEMNAME = 'jeans'))

select count(*) from (select MESSAGEID, DATENTIME, SUBJECT, CONTENT, SENDER from CSETEAM61.UMESSAGES where MESSAGEID in (select MESSAGEID from CSETEAM61.MSGRECEIVER where RECEIVER = 'vision'))
select MESSAGEID, DATENTIME, SUBJECT, CONTENT, SENDER from CSETEAM61.UMESSAGES where MESSAGEID in (select MESSAGEID from CSETEAM61.MSGRECEIVER where RECEIVER = 'vision')

select C.POSTID, C.WALLOWNER, C.DATENTIME, C.CONTENT, A.POSTAUTHOR from CSETEAM61.POSTS A, CSETEAM61.USERTOPAGE B, CSETEAM61.WALL C where  A.POSTID = C.POSTID and C.WALLOWNER = 'cbalawat' and B.USERID = 'cbalawat'
select count(*) from (select C.POSTID, C.WALLOWNER, C.DATENTIME, C.CONTENT, A.POSTAUTHOR from CSETEAM61.POSTS A, CSETEAM61.USERTOPAGE B, CSETEAM61.WALL C where  A.POSTID = C.POSTID and C.WALLOWNER = 'cbalawat' and B.USERID = 'cbalawat')