/*
REQUIREMENT: MySQL
项目运行前先执行下sql代码，在命令行中cd到该脚本所在的目录，接着进入mysql命令行，输入'source create_db.sql;'
要创建新表最好按照下面的格式补充，注意大小写以及反引号，减少不必要的问题，最好能保证第三范式
*/
CREATE DATABASE IF NOT EXISTS `LIBRARY`;
USE LIBRARY;
DROP TABLE IF EXISTS `READER`;
CREATE TABLE `READER` 
(
    `READER_ID` VARCHAR(10) NOT NULL, 
    `READER_NAME` VARCHAR(20) NOT NULL, 
    `PASSWORDD` VARCHAR(20) NOT NULL,
    PRIMARY KEY (READER_ID)
);
