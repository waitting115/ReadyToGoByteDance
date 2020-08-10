# webpack--模块加载器

## 安装webpack

通过node的包管理器安装：

1.安装webpack：

> npm i webpack --save-dev -g

2.安装小服务器：

> npm i webpack-dev-server -g

3.验证是否安装成功：

> webpack -v
>
> webpack-dev-server -v

## webpack组成

- 入口、出口
- loader（加载器）
- plugins（插件）

## webpack配置文件

webpack.config.js:

~~~~js
module.exports = {
    entry: './index.js', //入口文件
    output: {
        filename: 'bundle.js' //出口文件
    }
}
~~~~

用于配置webpack的具体内容。

index.html:

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="./dist/bundle.js"></script>
</head>
<body>
    
</body>
</html>
~~~

index.js:

~~~js
alert(1);
~~~

## webpack打包命令

> webpack

打包完成后会在当前文件夹中多出一个dist文件，里面就有自己设置的出口文件bundle.js。

bundle.js：

~~~js
!function(e){var t={};function r(n){if(t[n])return t[n].exports;var o=t[n]={i:n,l:!1,exports:{}};return e[n].call(o.exports,o,o.exports,r),o.l=!0,o.exports}r.m=e,r.c=t,r.d=function(e,t,n){r.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},r.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},r.t=function(e,t){if(1&t&&(e=r(e)),8&t)return e;if(4&t&&"object"==typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(r.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)r.d(n,o,function(t){return e[t]}.bind(null,o));return n},r.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return r.d(t,"a",t),t},r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r.p="",r(r.s=0)}([function(e,t){alert(1)}]);
~~~

webpack持续监听并打包（-w  == -watch），就不用每次更改index.js都要在cmd里面webpack 打包了：

> webpack -w

webpack压缩打包：

> webpack -p

webpack持续监听并压缩打包：

> webpack -pw

## 初始化webpack环境：

前提条件是当前文件夹必须为英文名！

> npm init -y

会出现一个package.json文件：

~~~json
{
  "name": "webpack-config-react",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC"
}

~~~

## webpack安装所需模块：

### 解析.css文件：

#### 安装style-loader:

> npm i style-loader -D

-D（--save-dev）的意思是开发所需，在package.json中会在devDependencies属性中显示相应模块的信息。

package.json:

~~~json
{
  "name": "webpack-config-react",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "style-loader": "^1.2.1"
  }
}

~~~

#### 安装css-loader：

> npm i css-loader -D

package.json:

~~~json
{
  "name": "webpack-config-react",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "css-loader": "^4.2.1",
    "style-loader": "^1.2.1"
  }
}
~~~

#### 配置webpack.config.js:

~~~js
module.exports = {
    entry: './index.js',
    output: {
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']//顺序不能反
            }
        ]
    }
}
~~~

### 解析JSX：

#### 下载所需模块：

- babel-core（核心）
- babel-loader（转换器）
- babel-preset-es2015（es6）

> npm i babel-core babel-loader@7.1.5 babel-preset-es2015 -D

package.json:

~~~json
{
  "name": "webpack-config-react",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "babel-core": "^6.26.3",
    "babel-loader": "^8.1.0",
    "babel-preset-es2015": "^6.24.1",
    "css-loader": "^4.2.1",
    "style-loader": "^1.2.1"
  }
}
~~~

#### 配置webpack.config.js

~~~js
module.exports = {
    entry: './index.js',
    output: {
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']//顺序不能反，从右往左读，先认识css在认识sytle
            },
            {
                test: /\.js$/,
                use: ['babel-loader']
            }
        ]
    }
}
~~~

#### 预设：

在当前文件夹新建.babelrc文件夹，可以用VSCode直接建并编辑，也可以在cmd中用type nul>.babelrc新建，然后在文件夹里打开显示隐藏文件（默认.babelrc文件是隐藏的）然后编辑。

.babelrc:

~~~babelrc
{
    "presets": ["es2015"]
}
~~~

#### error

(上面已经修改了，所以按照这个来做不会出现这个错误的)

**Error: Cannot find module '@babel/core'**

原因：babel-loader版本为8.0.0 不符合

解决：卸载重装7.1.5版本的babel-loader

> npm uninstall babel-loader
>
> npm i babel-loader@7.1.5 -D

### 配置react：

所需的模块：

- react
- react-dom
- babel-preset-react
- react-hot-loader（热更新）

#### 安装所需模块：

> npm i react react-dom babel-preset-react react-hot-loader@1.3.1 -D

package.json:

~~~json
{
  "name": "webpack-config-react",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "babel-core": "^6.26.3",
    "babel-loader": "^7.1.5",
    "babel-preset-es2015": "^6.24.1",
    "babel-preset-react": "^6.24.1",
    "css-loader": "^4.2.1",
    "react": "^16.13.1",
    "react-dom": "^16.13.1",
    "react-hot-loader": "^1.3.1",
    "style-loader": "^1.2.1"
  },
  "dependencies": {}
}
~~~

#### 配置webpack.config.js:

~~~js
module.exports = {
    entry: './index.js',
    output: {
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']//顺序不能反，从右往左读，先认识css在认识sytle
            },
            {
                test: /\.js$/,
                use: ['react-hot-loader','babel-loader'], //顺序不能反
                exclude: /node_modules/  //排除该文件夹
            }
        ]
    }
}
~~~

#### 预设：

在刚才建立的.babelrc文件夹里继续配置react：

.babelrc:

~~~babelrc
{
    "presets": [["es2015"],["react"]]
}
~~~

### 使用react：

详见笔记



