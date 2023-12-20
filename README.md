 mysql 

 
create database db_coding;
create user pch@localhost identified by '1234';
grant all privileges on db_coding.* to pch@localhost;

이거 안되면 create user user_pch@localhost identified by '1234';
