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
        
        
select cmt_no, post_pri_no, count(*) from comment group by post_pri_no;
select * from comment;
select * from user;
select * from post;

select * from comment where cmt_name = 'dsadsa' and post_pri_no = 71;

insert into comment values(66, 'dsadsa', 'test', 75, '2023-11-27 18:52', 1, 57);

-- ALTER TABLE comment
-- ADD CONSTRAINT fk_comment_post1 FOREIGN KEY (post_pri_no)
-- REFERENCES post(pri_no) ON DELETE CASCADE ON UPDATE CASCADE;

SELECT *
FROM comment
WHERE post_pri_no = 75
ORDER BY COALESCE(cmt_group, cmt_no), depth, create_date;


select * from comment where post_pri_no = 75 and cmt_name = 'dsadsa';



alter table comment add column cmt_group int default null;



desc post;
desc comment;
desc user;
