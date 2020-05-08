## CoffeeScript——简洁的JavaScript

**CoffeeScript 是一门编译到 JavaScript 的小巧语言.** 在 Java 般笨拙的外表下, JavaScript 其实有着**一颗华丽的心脏.** CoffeeScript 尝试用简洁的方式展示 JavaScript 优秀的部分.

CoffeeScript 的指导原则是: **"她仅仅是 JavaScript"**。代码一一对应地编译到 JS, 不会在编译过程中进行解释. 已有的 JavaScript 类库可以无缝地和 CoffeeScript 搭配使用, 反之亦然. 编译后的代码是可读的, 且经过美化, 能在所有 JavaScript 环境中运行, 并且应该和对应手写的 JavaScript 一样快或者更快.

**最新版本:** [1.7.1](http://github.com/jashkenas/coffee-script/tarball/1.7.1)

```shell
sudo npm install -g coffee-script
```

## 概览

上边是 CoffeeScript, 下边是编译后输出的 JavaScript.

```js
#CoffeeScript：

# 赋值:
number   = 42
opposite = true

# 条件:
number = -42 if opposite

# 函数:
square = (x) -> x * x

# 数组:
list = [1, 2, 3, 4, 5]

# 对象:
math =
  root:   Math.sqrt
  square: square
  cube:   (x) -> x * square x

# Splats:
race = (winner, runners...) ->
  print winner, runners

# 存在性:
alert "I knew it!" if elvis?

# 数组 推导(comprehensions):
cubes = (math.cube num for num in list)


#编译后：

var cubes, list, math, num, number, opposite, race, square,
  __slice = [].slice;

number = 42;

opposite = true;

if (opposite) {
  number = -42;
}

square = function(x) {
  return x * x;
};

list = [1, 2, 3, 4, 5];

math = {
  root: Math.sqrt,
  square: square,
  cube: function(x) {
    return x * square(x);
  }
};

race = function() {
  var runners, winner;
  winner = arguments[0], runners = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
  return print(winner, runners);
};

if (typeof elvis !== "undefined" && elvis !== null) {
  alert("I knew it!");
}

cubes = (function() {
  var _i, _len, _results;
  _results = [];
  for (_i = 0, _len = list.length; _i < _len; _i++) {
    num = list[_i];
    _results.push(math.cube(num));
  }
  return _results;
})();
```

run: cubes



## 安装

CoffeeScript 编译器本身[是 CoffeeScript 写的](http://coffee-script.org/documentation/docs/grammar.html), 使用了 [Jison parser generator](http://jison.org/). 命令行版本的 `coffee` 是一个实用的 [Node.js](http://nodejs.org/) 工具. 不过[编译器](http://coffee-script.org/extras/coffee-script.js)并不依赖 Node, 而是能运行于任何 JavaScript 执行环境, 比如说在浏览器里(看上边的"试一试 CoffeeScript").

安装前你需要最新稳定版 [Node.js](http://nodejs.org/), 和 [npm](http://npmjs.org/) (Node Package Manager). 借助 npm 可以安装 CoffeeScript:

```
npm install -g coffee-script
```

(如果不想全局安装可以去掉 `-g` 选项.)

如果你希望安装 **master** 分支上最新的 CoffeeScript, 你可以从[源码仓库](http://github.com/jashkenas/coffee-script) 克隆 CoffeeScript, 或直接下载[源码](http://github.com/jashkenas/coffee-script/tarball/master). 还有通过 npm 方式安装 master 分支最新的 CoffeeScript 编译器:

```
npm install -g http://github.com/jashkenas/coffee-script/tarball/master
```

或者你想将其安装到 `/usr/local`, 而不用 npm 进行管理, 进入 `coffee-script` 目录执行:

```
sudo bin/cake install
```

## 用法

安装之后, 你应该可以运行 `coffee` 命令以执行脚本, 编译 `.coffee` 文件到 `.js` 文件, 和提供一个交互式的 REPL. `coffee` 命令有下列参数:

| `-c, --compile`      | 编译一个 `.coffee` 脚本到一个同名的 `.js` 文件.              |
| -------------------- | ------------------------------------------------------------ |
| `-m, --map`          | 随 JavaScript 文件一起生成 source maps. 并且在 JavaScript 里加上 `sourceMappingURL` 指令. |
| `-i, --interactive`  | 启动一个交互式的 CoffeeScript 会话用来尝试一些代码片段. 等同于执行 `coffee` 而不加参数. |
| `-o, --output [DIR]` | 将所有编译后的 JavaScript 文件写到指定文件夹. 与 `--compile` 或 `--watch` 搭配使用. |
| `-j, --join [FILE]`  | 编译之前, 按参数传入顺序连接所有脚本到一起, 编译后写到指定的文件. 对于编译大型项目有用. |
| `-w, --watch`        | 监视文件改变, 任何文件更新时重新执行命令.                    |
| `-p, --print`        | JavaScript 直接打印到 **stdout** 而不是写到一个文件.         |
| `-s, --stdio`        | 将 CoffeeScript 传递到 STDIN 后从 STDOUT 获取 JavaScript. 对其他语言写的进程有好处. 比如: `cat src/cake.coffee | coffee -sc` |
| `-l, --literate`     | 将代码作为 Literate CoffeeScript 解析. 只会在从 **stdio** 直接传入代码或者处理某些没有后缀的文件名需要写明这点. |
| `-e, --eval`         | 直接从命令行编译和打印一小段 CoffeeScript. 比如: `coffee -e "console.log num for num in [10..1]"` |
| `-b, --bare`         | 编译到 JavaScript 时去掉[顶层函数的包裹](http://coffee-script.org/#lexical-scope). |
| `-t, --tokens`       | 不对 CoffeeScript 进行解析, 仅仅进行 lex, 打印出 token stream: `[IDENTIFIER square] [ASSIGN =] [PARAM_START (]` ... |
| `-n, --nodes`        | 不对 CoffeeScript 进行编译, 仅仅 lex 和解析, 打印 parse tree:`Expressions  Assign    Value "square"    Code "x"      Op *        Value "x"        Value "x"` |
| `--nodejs`           | `node` 命令有一些实用的参数, 比如 `--debug`, `--debug-brk`, `--max-stack-size`, 和 `--expose-gc`. 用这个参数直接把参数转发到 Node.js. 重复使用 `--nodejs` 来传递多个参数. |

**例子:**

- 编译一个 `.coffee` 文件的树形目录 `src` 到一个同级 `.js` 文件树形目录 `lib`:
  `coffee --compile --output lib/ src/`
- 监视一个文件的改变, 每次文件被保证时重新编译:
  `coffee --watch --compile experimental.coffee`
- 合并一组文件到单个脚本:
  `coffee --join project.js --compile src/*.coffee`
- 从一个 one-liner 打印编译后的 JS:
  `coffee -bpe "alert i for i in [0..10]"`
- 现在全部一起, 在你工作时监视和重复编译整个项目:
  `coffee -o lib/ -cw src/`
- 运行 CoffeeScript REPL (`Ctrl-D` 来终止, `Ctrl-V` 激活多行):
  `coffee`

## Literate CoffeeScript

除了被作为一个普通的编程语言, CoffeeScript 也可以在 "literate" 模式下编写。 如果你以 `.litcoffee` 为扩展名命名你的文件, 你可以把它当作 Markdown 文件来编写 — 此文档恰好也是一份可执行的 CoffeeScript 代码, 编译器将会把所有的缩进块 (Markdown 表示源代码的方式) 视为代码, 其他部分则为注释.

Just for kicks, a little bit of the compiler is currently implemented in this fashion: See it [as a document](https://gist.github.com/jashkenas/3fc3c1a8b1009c00d9df), [raw](https://raw.github.com/jashkenas/coffee-script/master/src/scope.litcoffee), and [properly highlighted in a text editor](http://cl.ly/LxEu).

I'm fairly excited about this direction for the language, and am looking forward to writing (and more importantly, reading) more programs in this style. More information about Literate CoffeeScript, including an [example program](https://github.com/jashkenas/journo), are [available in this blog post](http://ashkenas.com/literate-coffeescript).

## 语言手册： http://coffee-script.org/ 