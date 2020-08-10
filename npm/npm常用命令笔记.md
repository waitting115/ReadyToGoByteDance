### 1.查看Npm配置

- 基本配置

```
npm config list       //查看npm主要配置包含：npm仓库地址，cwd路径，根目录等配置信息
```

- 详细配置

```
npm config ls -l     //查看全部npm默认配置
```

- 获取配置值

```
npm config get key     //查看config中key对应的配置项
```

- 设置配置值

```
npm config set key=value     //设置config中key对应的配置项值为value
```

### 2.切换仓库到淘宝镜像.

- 一次性：

```
npm --registry https://registry.npm.taobao.org install express    // 修改当前项目的仓库位置
```

- 永久性：

```
npm config set registry https://registry.npm.taobao.org    //修改全局配置到淘宝镜像
```

设置完成后可以通过以下命令查看是否配置成功

```
npm config get registry
或 
npm info express
```

### 3. 添加cnpm 指令

设置完淘宝镜像之后可以通过如下命令定义cnpm命令。用于执行淘宝仓库中的npm指令

```
npm install -g cnpm --registry=https://registry.npm.taobao.org
```

### 4.切换回Npm官方

**发布Npm包时需要先切换回官方地址**

```
npm config set registry http://registry.npmjs.org 
```

### 5.Npm包到官方仓库

先执行login命令登录到官方仓库中。

```
npm login
```

切换到生成目录(默认=dist)目录下之后执行，将生成的包推送到官方仓库

```
npm publish --access=public //public表示发布的包的访问级别为public。
```

### 6.安装npm包

- npm install / npm i 
  此命令会将包安装到node_modules中，但是不会修改package.json，执行npm install时也不会自动安装。示例：

```
npm install @angular/core
```

- npm install -g
  此命令会将包安装到全局目录中(npm config get prefix所对应的目录)，不修改package.json，执行npm install不会自动安装

```
npm install -g @angular/core
```

- npm install --save
  此命令会将包安装到node_modules中，同时修改package.json文件，添加到dependencies节点。执行npm install 时会自动安装这个包。运行npm install --production或者注明NODE_ENV变量值为production时，会自动下载模块到node_modules目录中。

```
npm install --save @angular/core
```

- npm install --save-dev
  此命令会将包安装到node_modules中，同时修改packgage.json，将包添加到devDependencies节中，执行npm install 会自动安装这个包到node_modules中，运行npm install --production或者注明NODE_ENV变量值为production时，不会自动下载模块到node_modules目录中。

```
npm install --save-dev @angular/core
```

| 命令                   | node_modules | package.json    | npm install | npm install --production |
| :--------------------- | :----------- | :-------------- | :---------- | :----------------------- |
| npm install            | 是           | 否              | 否          | 否                       |
| npm install -g         | 否           | 否              | 否          | 否                       |
| npm install --save     | 是           | dependencies    | 是          | 是                       |
| npm install --save-dev | 是           | devDependencies | 是          | 否                       |

### 7.查看Npm版本

```
npm -v
```

### 8.查看当前安装的包依赖关系

```
npm ls
```

### 9.卸载包

```
npm uninstall style-loader css-loader
```

### 10.帮助

```
npm -help (aliase -h)         // 查看帮助信息
npm install -h   // 查看安装相关的命令帮助
```