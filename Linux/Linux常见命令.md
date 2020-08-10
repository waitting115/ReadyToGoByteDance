# Windows命令

#### 进入桌面：

>  cd Desktop

#### 查看该目录下的所有文件及文件夹：

> dir

#### 新建文件夹：

> mkdir aaa



#### 新建文件：

> type nul>webpack.config.js



# Linux命令

#### 清空屏幕：

> clear

#### 进入指定目录：

（/是服务器根目录）

> cd  ...

#### 查看当前目录下的文件、文件夹信息：

> ls

#### 服务器根目录下各文件介绍：

- /bin 可执行的二进制文件目录
- /dev 设备文件（访问文件相当于访问设备）
- /lib  函数目录
- /mnt  光盘默认挂载点
- /root  系统管理员目录
- /sbin  放置系统管理员可执行命令
- /www 存放网页数据
- /usr 用户程序目录
- /etc 系统配置
- /home 存放非root以外用户信息
- /opt  主机安装软件额外摆放的位置

#### 新建文件夹：

> mkdir xxx

#### 创建文件：

> touch xxx.xx

#### 编辑文件：

:wq写入并保存   i 写入模式   esc 退出此时的模式

> vim xxx.xx

#### 删除此目录下的一切：

无法找回（核弹威力）

> rm -rf *

#### 设置权限：

> chmod -R 777

Linux中7位最高权限

第一位7代表管理员可写可读，

第二位7代表所有者可写可读，

第三位7代表游客可写可读。

#### 解压.tar结尾的文件：

-x解开，-v显示所有过程，-f（必须）要处理的文件名

> tar -xvf xxx 

#### 移动/重命名：

> mv xxx xxx

如：

> mv node  nodejs  (将node改为nodejs)

#### Linux中文件的三种权限：

- 系统管理员
- 所有者
- 游客

#### 名字补全：

tab

#### 查看某端口被什么进程占用

lsof -i:端口号

#### 查看进程

比如说我想查看我的python运行了哪些进程程序

在命令行敲入此命令：ps -ef |grep python

![img](https://img-blog.csdn.net/20180607092058665?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMyNTAyNTEx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

这样就可以查看到了当前python正在运行的程序

同理要查看其它的进程也是一样的 ps -ef |grep 进程名

#### 结束进程

结束进程的话直接 kill -9 进程id就可以了

# IOS命令

#### 创建文件：

> touch webpack.config.js









*