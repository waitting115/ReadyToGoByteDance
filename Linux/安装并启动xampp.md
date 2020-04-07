# xampp安装及启动

首先在自己的电脑上去xampp官网下载Linux安装包。

下载完成之后，打开xftp，将下载好的.run文件拖拽到服务器根目录下的opt文件下，等待传输。

传输完成后，在xftp中直接打开xShell，运行命令：

> cd opt
>
> chmod -R 777 xampp.run
>
> ./xampp.run
>
> 接下来都选y
>
> /opt/lampp/lampp stop  默认安装完就启动，所以先停止
>
> /opt/lampp/lampp start   启动
>
> /opt/lampp/lampp reload   或者直接重启

**一定要在服务器安全组中开放80端口，不然外网无法访问此处服务器，还有ftp需要的21端口也开放。**

启动之后，在浏览器输入服务器的公网ip，会进入到Apache。