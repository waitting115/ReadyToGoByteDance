# WordPress

#### 代码下载并上传到服务器

1. 在github上直接下载压缩包：

 https://github.com/WordPress/WordPress 

2. 然后用xftp拖拽到opt/lampp/htdocs   (不知道是不是固定的)

3. 接下来上宝塔面板中，点击文件，找到放压缩包的目录，然后解压缩到opt/lampp/htdocs中

#### 安装WordPress

浏览器输入 http://182.92.197.210/WordPress/wp-admin （自己服务器的外网ip）

会进入wordpress安装文档

先进入宝塔面板更改一下phpmyadmin权限：

/opt/lampp/etc/extra/httpd-xampp.conf

将  Require local 改为 Require all granted  （应该是在第20行）

然后重启apache，在xshell中。

然后浏览器输入182.92.197.210

进入phpmyadmin

新建名为wordpress的数据库

然后回到 http://182.92.197.210/WordPress/wp-admin ，点击Let's go

输入相关信息，点击提交

如果此时出错说不能写入，那么去宝塔中给wordpress文件一个写入的权限。然后回来点击后重新输入信息

然后点击安装。

然后输入博客的名字，博客的用户名及密码，电子邮件等

然后安装wordpress

显示成功！

最后千万不要忘了恢复phpmyadmin的权限，不能让其他人轻易的访问你服务器上面的数据库。

宝塔中进入：

/opt/lampp/etc/extra/httpd-xampp.conf

将 Require all granted 改为 Require local （应该是在第20行）

然后重启apache

#### 使用wordpress

首先进入自己的wordpress：

浏览器输入 182.92.197.210/WordPress/wp-admin

登录，创作自己的博客吧！

2.自定义外观/主题样式

3.小工具的使用

4.菜单使用

5.用户管理

6.评论查看，以及管理

7.文章管理（写文章）

*