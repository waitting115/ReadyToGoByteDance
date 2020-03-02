# JavaScript面试题汇总

## 1.JavaScript有几种类型

5种基本数据类型：

- null
- undefined
- string
- number
- Boolean
- Symbol（es6的新数据类型）
  - Symbol是什么呢？
  - Symbol可以生成一个全局唯一的值
  -  https://zhuanlan.zhihu.com/p/22652486 
  - 每个从Symbol()返回的值都是唯一的，一个Symbol值能作为对象属性的标识符，这是该类型仅有的目的
  - 任何两个Symbol值都不相等，如同null一样；
  - Symbol(arg)中传入的参数并不会影响此Symbol的值，参数更像是一个注释。

引用类型：

- Object
- Array
- Function（三者统称为Object）

## 2.数据类型检测

typeof对于基本数据类型来说，除了null都会显示正确的类型。（null会显示Object）

typeof对于对象来说，除了函数都会显示Object。





















