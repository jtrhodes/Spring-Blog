
USE adlister_db;
CREATE USER springblog_user@localhost IDENTIFIED BY 'password';
GRANT ALL ON springblog_db.* TO springblog_user@localhost;