# GITHUB

### 分布式版本控制软件

自己代码--( git add ) -->暂存区--(git commit)-->版本库（分支）--(git push)-->git hub--(git pull)-->版本库（分支）

**下载网站**：<https://git-scm.com/downloads>

**安装时：**Use git from git bash only ... 其他默认下一步

**配置：**path: bin

**配置git：**桌面右键选择  Git Bash Here

​		配置本地用户名和邮箱：git config --global user.name "jingwei"

​							git config --global user.email "..."

**查看用户名及邮箱：**C盘->用户-> wei -> .gitconfig(记事本就可以打开)

**配置免秘钥登录：**ssh key

​	**配置ssh：**先在本地配置，然后发给远程

​		依然打开Git Bash Here，输入： ssh-keygen -t rsa -C 1151042726@qq.com   然后一直回车（中间有个y/n的选项，选y），出现方块一样的东西即成功。

在github中点头像->settings->ssh and gpg keys -> new ssh

​	title任意，key中输入本地ssh（id_rsa.pub）打开 复制 粘贴（注意要去除回车，以免出错）
id_rsa.pub文件路径为：C:\Users\wei\.ssh

**测试连通性：**ssh -T git@github.com ->yes  出现access即成功，而且多出个known_hosts文件，即已经连通，如果失败，多试几次，检查是否多余回车符。





**在本地新建Git项目并发送给远程：**

​	在项目文件夹中右键  Git Bash Here

​		git init  

​	在github中建立新项目并记住ssh标识，或者在已有项目上处理，也要记住ssh标识，而且这里要注意，你的本地文件夹中的文件要与你仓库中的文件相同，如果不同，先将不同的文件下载到本地 ，要保证两处文件相同

**本地项目与远程项目相关联：**

​	git remote add origin 你要关联的仓库的ssh标识

**发布项目：**

​	git add .      //本地到暂存区  .代表当前目录所有文件

​	git commit -m "注释内容"   //暂存区到本地版本库分支

​	git push -u origin master

**下载项目：**

​	git clone ssh标识

**远程更新到本地：**

​	git pull	
