# 常用命令

### 打开数据库：

 mysql -u root -p  然后输入密码，如果没有密码则直接回车

### 展示所有数据库：

SHOW DATABASES

### 创建数据库：

CREATE DATABASE RUNOOB

### 进入某数据库：

 USE RUNOOB

### 创建数据表：

CREATE TEBLE allUser(

​	openID VARCHAR(100) NOT NULL,

​	userName VARCHAR(100) NOT NULL,

​	PRIMARY KEY (openID)

​	)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

(用InnoDB引擎，默认编码为utf-8)

### 查看数据表：

show tables

### 查看表结构：

desc runoob

### 删除数据表：

DROP TABLE runoob

### 插入数据：

```mysql
INSERT INTO alluser
-> (openID,userName,headPortraitUrl,recommendation,meFocus,focusMe,myCollection)
-> VALUES
-> ("five","five","../../iamges/userHead/cat.jpg","'考研资料',’球类运动','租好物'","'snwn','xxg','hx','ch','lsiy'","'snwn','hx','fzzf'","'lsjy-7','snwn-8','hx-14','hx-16'");
```
### 查询数据：

​	SELECT * FROM alluser WHERE openID='five';

### 更新数据表：

​	 UPDATE alluser SET userName='Five' WHERE openID='five';

### 删除表数据：

​	DELETE FROM alluser WHERE openID='five';

LIKE语句：

​	 select * from alluser where openID like '%ve';

。。。

### 添加主外键关系：

​	alter table **goods** add constraint FK_ID foreign key(**openID**) references **alluser(openID)**;

### 删除主外键关系：

​	先找到要删除外键的表，然后  show create table **goods**;

​	然后找到   CONSTRAINT  后面引号里面的约束名

​	然后   alter table **goods** drop foreign key **FK_ID**;

​	就ok了。

### 删除主键约束：

​	alter table **goods** drop primary key;

### 添加主键约束：

​	 alter table **goods** add primary key (**userName**);

### 删除某一字段：

​	alter table **goods** drop **openID**;

如果想添加复合主键，需要将连接该表的主外键关系先清除，然后将主键删除约束，然后：

​	alter table **mymessage** add primary key (**openID**,**userName**);

​	就可以了。

### 创建表：

create table user(

username char(20) primary key,

password char(20) not null

);

### 插入数据：

insert into user  values (1,1);

### 查询数据：

select * from user where username=1 and password=1;

### 查看数据表：

select *from user;

### 展示一下数据表的信息(编码方式)：

show create table user;

### 精确查询：

select *from st_grade where st_name=1;

### 模糊查询：

select *from st_grade where st_name like '1%';

### 更新数据：

update st_grade set math=2,english=3 where st_name=1;





​	所谓SQL注入，就是通过把SQL命令插入到Web表单递交或输入域名或页面请求的查询字符串，最终达到欺骗服务器执行恶意的SQL命令。 

​	我们永远不要信任用户的输入，我们必须认定用户输入的数据都是不安全的，我们都需要对用户输入的数据进行过滤处理。

防止SQL注入，我们需要注意以下几个要点：

- 1.永远不要信任用户的输入。对用户的输入进行校验，可以通过正则表达式，或限制长度；对单引号和 双"-"进行转换等。
- 2.永远不要使用动态拼装sql，可以使用参数化的sql或者直接使用存储过程进行数据查询存取。
- 3.永远不要使用管理员权限的数据库连接，为每个应用使用单独的权限有限的数据库连接。
- 4.不要把机密信息直接存放，加密或者hash掉密码和敏感的信息。
- 5.应用的异常信息应该给出尽可能少的提示，最好使用自定义的错误信息对原始错误信息进行包装
- 6.sql注入的检测方法一般采取辅助软件或网站平台来检测，软件一般采用sql注入检测工具jsky，网站平台就有亿思网站安全平台检测工具。MDCSOFT SCAN等。采用MDCSOFT-IPS可以有效的防御SQL注入，XSS攻击等。

在脚本语言，如Perl和PHP你可以对用户输入的数据进行转义从而来防止SQL注入。 

PHP的MySQL扩展提供了mysqli_real_escape_string()函数来转义特殊的输入字符。

like查询时，如果用户输入的值有"_"和"%"，则会出现这种情况：用户本来只是想查询"abcd_"，查询结果中却有"abcd_"、"abcde"、"abcdf"等等；用户要查询"30%"（注：百分之三十）时也会出现问题。 

在PHP脚本中我们可以使用addcslashes()函数来处理以上情况。



# my.ini  文件

~~~ini
[mysqld]

设置3306端口

port=3306

设置mysql的安装目录

basedir=D:\MySQL\mysql-8.0.18-winx64\

设置mysql数据库的数据的存放目录

datadir=D:\MySQL\mysql-8.0.18-winx64\Data

允许最大连接数

max_connections=200

允许连接失败的次数。这是为了防止有人从该主机试图攻击数据库系统

### 

max_connect_errors=10

服务端使用的字符集默认为UTF8

character-set-server=utf8

创建新表时将使用的默认存储引擎

default-storage-engine=INNODB

默认使用“mysql_native_password”插件认证

default_authentication_plugin=mysql_native_password

[mysql]

设置mysql客户端默认字符集

default-character-set=utf8
[client]

设置mysql客户端连接服务端时默认使用的端口

port=3306
default-character-set=utf8


~~~





# 在node中操作mysql

~~~js

// var mysql = require('mysql');

// var connection = mysql.createConnection({//配置mysql
//     host: 'localhost',
//     user: 'root',
//     password: '',
//     database: 'haibin'
// })

// connection.connect();//连接

// connection.on('error', function (err) {
//     console.error(err);
// })

//增加数据
// let addSql = 'INSERT INTO message(registeredNum,productsShelvesNum,removedShelvesNum,userLoginChangeData,goodsClassDistributionData,topSearch,adminID) VALUES(?,?,?,?,?,?,?)';
// let addSqlParams = [4,5,6,'user','goods','topSearch','admin'];

// connection.query(addSql, addSqlParams, (err, result) => {
//     if(err) {
//         console.error('增加失败---', err.message);
//         return;
//     }

//     console.log('--------------------------ADD----------------------------');
//     console.log('增加成功 ID：', result.insertId);
//     console.log('增加成功:', result);
//     console.log('--------------------------ADD----------------------------');

// })
// connection.end();

//删除数据
// let delSql = 'DELETE FROM message WHERE registeredNum = ?';
// let delSqlParams = [4];

// connection.query(delSql, delSqlParams, (err, result) => {
//     if(err) {
//         console.error('删除数据失败---', err.message);
//         return;
//     }
//     console.log('--------------------------DELETE----------------------------');
//     console.log('删除数据成功：', result.affectedRows);
//     console.log('--------------------------DELETE----------------------------');
// });
// connection.end();

//更新数据
// let modSql = 'UPDATE message SET productsShelvesNum = ?, removedShelvesNum = ? WHERE registeredNum = 1';//更新第一条数据
// let modSqlParams = [8, 8, 1];
// connection.query(modSql, modSqlParams, (err, result) => {
//     if(err) {
//         console.error('更新数据失败---', err.message);
//         return;
//     }
//     console.log('--------------------------UPDATE----------------------------');
//     console.log('更新成功：', result.affectedRows);
//     console.log('--------------------------UPDATE----------------------------');
// });
// connection.end();

//查数据
// var sql = 'SELECT * FROM message';
// connection.query(sql, (err, result) => {
//     if(err) {
//         console.error('查找数据失败---', err.message); 

//         return;
//     }
//     console.log('--------------------------SELECT----------------------------');
//     console.log('查找成功：', result);
//     console.log('--------------------------SELECT----------------------------');
// });
// connection.end();
~~~



















