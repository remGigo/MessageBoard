CREATE DATABASE day17; -- 创建数据库
USE day17; 			   -- 使用数据库
CREATE TABLE USER(   -- 创建表
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20) NOT NULL,
	gender VARCHAR(5),
	age INT,
	address VARCHAR(32),
	qq	VARCHAR(20),
	email VARCHAR(50)
);

INSERT  INTO `user`(`id`,`name`,`gender`,`age`,`address`,`qq`,`email`) VALUES (1,'张三','男',13,'陕西','12345','zhangsan@itcast.cn');
INSERT  INTO `user`(`id`,`name`,`gender`,`age`,`address`,`qq`,`email`) VALUES (NULL,'李四','女',15,'北京','88888','ls@itcast.cn');
