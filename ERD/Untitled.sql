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