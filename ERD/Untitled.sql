use board;

desc user;

desc post;

select * from user;

SELECT count(*)
        FROM post p join user u on p.author = u.name
        WHERE p.author = 'dsadsa' and p.pri_no = 65;
        
SELECT count(*)
        FROM post p join user u on p.author = u.name
        WHERE u.id = '' and p.pri_no = 65;
        
select * from comment;

select * from comment;
select * from user;

select count(*) from comment c join user u on c.cmt_name = u.name where c.cmt_name = 'aaaa' and post_pri_no = 71;

select * from comment where cmt_name = 'dsadsa' and post_pri_no = 71;

desc comment;
desc user;
