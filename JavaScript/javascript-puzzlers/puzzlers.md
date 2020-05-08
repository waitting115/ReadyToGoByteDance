# 44道题

 https://javascript-puzzlers.herokuapp.com/ 

## 1.考察map()与parseInt()

这个表达式的结果是什么？（或多个）

```js
 ["1", "2", "3"].map(parseInt)   
```

- A.["1", "2", "3"]
- B.[1,2,3]
- C.[0,1,2]
- D[1,NaN,NaN]

我选的B，因为parseInt函数可以将字符串转化为数字

但正确答案是D，因为parseInt()函数接收两个参数（val，基数），但是map()函数会向parseInt()函数传递三个参数（元素，索引，数组）；所以实际情况是这样的：

~~~js
parseInt('1',0,["1", "2", "3"])
1
parseInt('2',1,["1", "2", "3"])
NaN
parseInt('2',1);
NaN
parseInt('3',2,["1", "2", "3"])//第三个参数无论传什么都不会影响到parseInt函数
NaN
parseInt('3',2);//表示2进制里面的3，但2进制里面没有3，所以返回NaN
NaN
parseInt('3',3)//3进制里面也没有3
NaN
parseInt('3',4)//4进制里面有3
3
parseInt('3',36)
3
parseInt('3',37)//最高到36
NaN
parseInt('11',2)//2进制里面的1 1，不就是3嘛
3
~~~

理由：

Javascript中parseInt（）函数可以接收两个参数（string，radix）：

| 参数   | 描述                                                         |
| ------ | ------------------------------------------------------------ |
| string | 必须。要被解析的字符                                         |
| radix  | 可选。表示要解析的数字的基数。该值介于2~36之间。如果省略该参数**或为0**，则该数将以10为基础解析它。如果字符串以0X或0x开头，将以16为基数。如果该参数小于2或者大于36，则parseInt将返回NaN |

## 2.考察typeof与null

这个表达式的结果是什么？（或多个）

[typeof null, null instanceof Object]

- A.['object',false]
- B.[null, false]
- C.['object', true]
- D.other

我选的A，**因为typeof用来检测基本数据类型，有个特例就是会将null检测为object，因为特殊值null会被认为是空的对象指针！**

typeof操作符一共会返回6种结果：

- ‘string’——字符串
- ‘number’——数字
- ‘boolean’——布尔值
- ‘undefined’——值未定义
- **‘object’——对象或null**
- ‘function’——函数

~~~js
null == undefined//因为undefined是派生自null的
true
null === undefined//但它们两个还是不同的
false
null == null
true
null === nul//虽然书上说两个null值不相等，但是浏览器证明了二者是相等的
true
undefined == undefined
true
undefined === undefined
true
let a = null,b = null;
undefined
a == b
true
a === b//看吧，是相等的
true
~~~

## 3.考察reduce()与Math.pow()

这个表达式的结果是什么？

[ [3,2,1].reduce(Math.pow),   [].reduce(Math.pow)]

- A.an error
- B.[9, 0]
- C.[9, NaN]
- D.[9, undefined]

我选的C，因为第一表达式返回9毫无问题，第二个表达式Math方法处理了非数字应该会返回NaN吧

答案是A

reduce():

- 迭代数组中所有的项，接收两个参数：一个在每一项上执行的函数，和（可选）作为归并基础的初始值。

- 函数接收四个参数：**前一项，当前项，**（可选）当前项的index，（可选）数组（prev，cur，index，arr）

- ~~~js
  [1,2,3,4,5].reduce((prev,cur) => prev + cur);
  15
  ~~~

Math.pow()

- ~~~js
  Math.pow();//无参返回NaN
  NaN
  Math.pow(1)//一个参返回NaN
  NaN
  Math.pow(1,1)//两个参正常执行
  1
  哪种情况都不会报错
  ~~~

所以这道题的问题就出现在reduce(fun)里面的fun上面，reduce函数至少要保证调用它的数组有值传给fun，哪怕是一个；fun是有两个必须的参数，如果数组里面只有一个元素，那么函数体并不会执行，而是直接返回那个元素

~~~js
[].reduce(function (prev,cur) {//空数组直接报错，因为fun里面传不进去参数
    console.log('prev',prev);
    console.log('cur',cur);
});
VM2322:1 Uncaught TypeError: Reduce of empty array with no initial value
    at Array.reduce (<anonymous>)
    at <anonymous>:1:4
                     
[1].reduce(function (prev,cur) {//数组中就一个元素，那么函数体并不会执行，而是直接返回那个1
    console.log('prev',prev);
    console.log('cur',cur);
});
1

[1,2].reduce(function (prev,cur) {//数组中有两个元素及其以上，可以满足fun的两个必须参数prev和cur，正常执行
    console.log('prev',prev);
    console.log('cur',cur);
})
VM2315:2 prev 1
VM2315:3 cur 2

[1].reduce(()=>{})//事实证明如果数组中只有一个元素，无论fun的函数体是什么都不会执行，只是直接返回那个元素
1
~~~

## 4.考察=与三目运算符的优先级

这个表达式的结果是什么？

> var val = 'smtg';
>
> console.log('Value is ' + (val === 'smtg') ? 'Something' : 'Nothing');

- A.Value is Something
- B.Value is Nothing
- C.NaN
- D.other

我选的A，三目运算符判断肯定是true这毫无疑问，所以应该返回Value isSomething

答案是D，因为 **+ 运算符的优先级高于三目运算符**；所以实际上js引擎会先计算‘Value is + （val === 'smtg')的值，它的值是‘Value is true’，非空字符串的布尔值是true，所以现在才执行三目运算符，所以只输出Something。

## 5.考察声明提升与作用域

这个表达式的结果是什么？

> var name = 'World!';
>
> (function () {
>
> ​	if(typeof name === 'undefined') {
>
> ​		var name = 'Jack';
>
> ​		console.log('GoodeBye ' + name);
>
> ​	} else {
>
> ​		console.log('Hello ' + name);
>
> ​	}
>
> })();

- A.GoodBye Jack
- B.Hello Jack
- C.Hello undefined
- D.Hello world!

我选的D，因为typeof优先级肯定是大于===的，所以typeof   ’World!‘ 的结果是string，不等于undefined，所以走else，输出Hello World！

答案是A，因为**立即执行函数是一个独立的作用域**，if里面有var name = ’Jack'；所以会**声明提升**，提升到这个立即执行函数作用域的最顶端，所以当if里面检查name的类型的时候，由于自己作用域里面有name，所以就不会用外部的name，但是此时只是声明提升，name的值是undefined，所以走if语句，最终输出GoodBye Jack。

~~~js
var name = 'World!';
undefined

(function () {
    if(typeof name === 'undefined') {
        var name = 'Jack';
        console.log('Goodobye ' + name);
    } else {
        console.log('Hello ' + name);
    };
})();
VM3356:4 Goodobye Jack
undefined

(function () {
    console.log(name);//undefined，用的是自己作用域里面var的name
    if(typeof name === 'undefined') {
        var name = 'Jack';//声明提升
        console.log('Goodobye ' + name);
    } else {
        console.log('Hello ' + name);
    };
})();
VM3415:2 undefined		//看到这里了就一目了然了吧
VM3415:5 Goodobye Jack
undefined

(function () {
    console.log(name);
    if(typeof name === 'undefined') {
        console.log('Goodobye ' + name);//name声明提升，但else语句没有执行，所以值为undefined
    } else {
        var name = 'Jone';//无论是if还是else语句里面声明的name，都会声明提升
        console.log('Hello ' + name);
    };
})();
VM3447:2 undefined
VM3447:4 Goodobye undefined
undefined
~~~

## 嗯

[freecodecamp.cn](https://freecodecamp.cn/)

[freecodecamp.com](http://www.lixuejiang.me/2016/11/01/JavaScript的练习网站收集/freecodecamp.com)的中文版，可以学习前端，后端，数据可视化等知识

[es6katas.org/](http://es6katas.org/)

一个用tdd学习es6的网站，学完之后对es6的各种特性会有深入的了解

牛客网的练习题

[leetcode.com/](https://leetcode.com/)

有名的oj系统，听说有人通过这个网站刷题进了google

[nodeschool.io](https://nodeschool.io/zh-cn/#workshoppers)

nodeschool,有很多js和es6以及web相关的教程

[www.hackerrank.com](https://www.hackerrank.com/)

一个英文的学习网站，类似oj的形式，有数据结构算法的课程，也有正则表达式等课程，质量还不错

## 6.考察JS的Math最大数字

这个表达式的结果是什么？

>var end = Math.pow(2,53);
>
>var start = end - 100;
>
>var count = 0;
>
>for(var i = start ; i <= end; i ++) {
>
>​	count ++;
>
>}
>
>console.log(count);

- A.0
- B.100
- C.101
- D.other

我选的A，认为for循环不会执行

答案是D，在JavaScript中，Number.MAX_SAFE_INTEGER常量表示最大的安全整数，也就是2**53 - 1。

因为JavaScript的数字存储使用了IEEE 754中规定的 双精度浮点数 数据类型，而这一数据类型能够安全存储-(2\*\*53 - 1)到(2\*\*53 - 1)之间的数值（包含边界值）

这里安全存储的意思是指能够准确区分两个不相同的值，例如：

~~~js
 Number.MAX_SAFE_INTEGER + 1 === Number.MAX_SAFE_INTEGER + 2
true
~~~

所以上述题目会进入无限循环，因为2^53是js中的最大数字，2^53 + 1给出 2^53，所以i永远不会变的更大。

## 7.考察filter()的执行细节

这个表达式的结果是什么？

> var arr = [0,1,2];
>
> arr[10] = 10;
>
> arr.filter(function (x) { return  x === undefined;});

- A.[undefined * 7]
- B.[0,1,2,10]
- C.[]
- D.[undefined]

我选的A，这题没什么思路

答案是C

arr.filter()函数传参一个函数，将arr里面的数据依次传入函数，**执行函数返回true 的元素放到一个新数组中，最后返回这个新的数组，如果没有任何数组元素通过了函数，则返回一个空数组**。函数接收3个参数（element，index，arr）。**这个函数只会在已经赋值的索引上被调用，对于那些已经删除或从未被赋值的索引不会被调用**。

本题中，arr前三位都不满足函数，都会返回false，所以都没有进去新数组，第四位到第十位都是undefined，函数直接跳过，到arr[10]，函数也会返回false ，所以最后返回了空数组。



所以arr.filter()函数可以**用来过滤JSON中的无效条目**：

~~~js
var arr = [
	{ id : 10 },
    { id : 5 },
    { id : 0 },
    { id : -1 },
    { id : null },
    { },
    { id : undefined },
    { id : NaN }
]
function isNumber(obj) {
    return obj !== undefined && typeof obj === 'number' && !isNaN(obj);
}

function filterById (item) {
    if(isNumber(item.id) && item.id != 0) {
        return true;
    }
    return false;
}

var arrByID = arr.finter(filterById);

console.log(arrByID);
//[{ id : 10 },{ id : 5 },{ id : -1 },]
~~~

## 8.考察精确数字

这个表达式的结果是什么？

> var one = 0.1;
>
> var two = 0.2;
>
> var six = 0.6;
>
> var eight = 0.8;
>
> [two - one == one , eight - six == two]

- A[true, true]
- B.[false ,fasle]
- C.[true, fasle]
- D.[other]

我选的B，因为不要相信任何js中的浮点数的运算，因为我知道0.1 + 0.2 = 0.3000000000004

答案是C，JavaScript中没有精确的数学，尽管它有时候可以正确运行

~~~js
0.2 - 0.1
0.1
0.8 - 0.6
0.20000000000000007
0.1 + 0.2
0.30000000000000004
~~~

## 9.考察switch-case原理

这个表达式的结果是什么？

~~~js
function showCase (value) {
    switch (value) {
        case 'A':
            console.log('A');
            break;
        case 'B':
            console.log('B');
            break;
        case undefined:
            console.log('undefined');
            break;
        default:
            console.log('Do not know');
    }
}
showCase(new String('A'));
~~~

- A. A
- B. B
- C. undefined
- D. Do not know

我选的C，这题思路并不清晰

答案是D，**因为switch内部是===全等运算符，不发生隐式转换**；new String('A')是一个对象，它不全等于‘A'，也不是undefined。

~~~js
let arr = new String('A');
undefined

arr
String {"A"}

let arr2 = 'A'
undefined

arr  == arr2;
true

arr === arr2;//答案在这里
false

typeof arr;
"object"

typeof arr2;
"string"
~~~

## 10.考察String（）

What is the result of this expression?

~~~js
function showCase (value) {
    switch (value) {
        case 'A':
            console.log('A');
            break;
        case 'B':
            console.log('B');
            break;
        case undefined:
            console.log('undefined');
            break;
        default:
            console.log('Do not know');
    }
}
showCase(String('A'));
~~~

- A. A
- B. B
- C. undefined
- D. Do not know

我选的A，因为String（）不会创建对象，只是创建了个基本数据类型String。

答案是A

~~~js
et arr3 = String('A');
undefined

arr3 == 'A'
true

arr3 === 'A'
true

typeof arr3
"string"
~~~

## 11考察取模%

What is the result of this expression?

~~~js
function isOdd(num) {
    return num % 2 == 1;
}
function isEven(num) {
    return num % 2 == 0;
}
function isSane(num) {
    return isOdd(num) || isEven(num);
}
let value = [7,4,'13',-9,Infinity];
value.map(isSane);
~~~

- A.[true,true,true,true,true]
- B.[true, true, true, true, false]
- C.[true, true, true, false,false]
- D.[false, false, false, false , false]

我选的B，我以为取模是忽略正负号的

答案是C，**取模运算符保留符号**，因此结果仅与0相比是可靠的

~~~js
'13' % 2
1

-9 % 2
-1

Infinity % 2
NaN
~~~

## 12.考察parseInt()

What is the result of this expression?

~~~js
parseInt(3,8);
parseInt(3,2);
parseInt(3,0);
~~~

- A.3, 3, 3
- B.3, 3, NaN
- C. 3, NaN, NaN
- D.other

我选的C，因为基数为2的里面没有3，只有01；基数为0的更会是NaN了

答案是D，parseInt()函数的第二个参数是可选的，不传参或为0，js引擎都会按10进制来处理。

~~~js
parseInt(3,8)
3

parseInt(3,2)
NaN

parseInt(3,0);//按10进制处理
3
~~~

## 13.考察array的prototype

What is the result of this expression?

~~~js
Array.isArray(Array.prototype);
~~~

- A.true
- B.false
- C.error
- D.other

我选的B，因为数组的原型肯定是个对象呀

答案是A，**数组的原型是数组！**

~~~js
//鲜为人知的事实：Array.prototype本身也是个Array
Array.isArray(Array.prototype);
true;
~~~

- Array.prototype.constructor
  - 所有的数组实例都继承了这个属性，它的值就是Array，表明了所有数组都是有Array构造出来的。
- Array.prototype.length
  - 因为Array.prototype也是个数组，所以它也有length属性，这个值为0，因为它是个空数组。

~~~js
let arr = [1,2,3];
undefined

arr.__proto__
[constructor: ƒ, concat: ƒ, copyWithin: ƒ, fill: ƒ, find: ƒ, …]//看吧是个数组

arr.__proto__.length//这个数组里面有个length属性，它的值为0
0

let str = 'qwe';
undefined

str.__proto__
String {"", constructor: ƒ, anchor: ƒ, big: ƒ, blink: ƒ, …}//str 的就是对象了
~~~

## 14.考察 [0]

What is the result of this expression?

~~~js
let arr = [0];
if([0]) {
    console.log(arr == true)
} else {
    console.log('wut')
}
~~~

- A.true
- B.false
- C.wut
- D.other

我选的A，**[0]转化为布尔值是true**，那么arr==true一定会成立

答案是B，这是我怎么也想不到的答案。**在比较中使用它时，它会以不同的方式转换。**

~~~js
!![0]
true

[0] == true;
false

[0] == true;
false
[1] == true//就这里返回了true
true
[] == true
false
[2] == true
false
[3] == true
false
~~~

## 15.考察==与[]

What is the result of this expression?

~~~js
[] == []
~~~

- A.true
- B.false
- C.error
- D.other

我选的A，因为==会有隐式类型转换

答案是B，那么请看下面：

~~~js
[] == []
false
//数组是引用数据类型，是对象，所以这个比的是两个空数组的内存地址是否相等，自然是不等的

[] == ![]
true
//这里就有一点复杂了，涉及到==不同类型值的比较，由下表可知，由于!将[]转换为布尔值false，所以该问题转化为对象与布尔值的比较，所以要将对象和布尔值都转换为数字，方法就是先使用toString（）方法将对象转化为str，然后用Number（）方法将字符串转化为数字；然后将第二个布尔值用Number()转化为数字进行比较
//[] == false
//'' == false
//0 == false
//0 == 0
//true

[] == {}//原理与第一个相同
false
~~~

**不同类型之间的==比较**

| 类型           | 类型      | 比较方式                                                     |
| -------------- | --------- | ------------------------------------------------------------ |
| 对象           | 对象      | 比较内存地址是否相同                                         |
| 对象           | 字符串    | 用toString（）将对象转化为字符串与另一个字符串比较是否相等   |
| 对象           | 布尔值    | 二者都要转化为数字进行比较，用toString（）将对象转化为字符串，然后用Number（）将字符串转化为数字；然后将布尔值用Number（）转化为数字。二者进行数字上的比较 |
| 对象           | 数字      | 将对象转化为数字进行比较（toString（），Number（）），数组对象直接用Number（） |
| 数字           | 布尔值    | 布尔值转数字（true1，false0），进行数字上的比较              |
| 数字           | 字符串    | 字符串转数字（Number（）），进行数字上的比较                 |
| 布尔值         | 字符串    | 都转换为数字（Number（）），进行数字上的比较                 |
| null           | undefined | true（原因是null是衍生自undefined 的）                       |
| null/undefined | 其他类型  | 都是false                                                    |
| NaN            | NaN       | false（两个非数字也是不相等的）                              |

**自身做布尔运算时（ ！），除了 “ ”、0、NaN、null、undefined、false为false，其余的都为true。**

## 16.考察String/Number + -

What is the result of this exppression?

~~~js
'5' + 3
'5' - 3
~~~

- A.'53' 2
- B.8  2
- C.error
- D.other

我选的B，我以为都会转为字符串

答案是A，**字符串知道+并会使用它，但它是不认识 - ，因此str会转为num。**

## 17.考察1+ - + + + - + 1

What is the result of this expression?

~~~js
1 + - + + + - + 1
~~~

- A. 2
- B. 1
- C. error
- D. other

我选的A，我以为+-就会抵消，最后剩下1 +++ 1，自然是2

答案是A，虽然我选对了，但是我还是错了。

~~~js
1 + - 1
0

1 + - + 1
0

1 + - + - 1
2

1 + + -  + + - + + - + -1
2
~~~

实践证明，这样的式子中，**- 与- 会抵消，+的多少不影响结果，所以结果取决于-是奇数个还是偶数个**，奇数个，就是二者相减，偶数个就是二者相加。

## 18.考察arr.map()

What is the result of this expression?

~~~js
let arr = new Array(3);
arr[2] = 10;
arr.map(function (elem) { return '1' })
~~~

- A. [2,1,1]
- B.['1',1'1']
- C.[2,'1','1']
- D.other

我选的D，因为**map（）不会对没有初始化的值调用函数**，所以结果应该是['1',undefined*2]

答案是D，我的思路是对的。

map()会为每一个已初始化的数组元素调用回调函数，将函数返回的结果放进一个新数组并返回。

## 19.考察arguments

What is the result of this expression?

~~~js
function sidEffection (arr) {
    arr[0] = arr[2];
}
function bar(a,b,c) {
    c = 10;
    sideEffection(arguments);
    return a + b + c;
}
bar(1,1,1);
~~~

- A. 3
- B.12
- C.error
- D.other

我选的A，我认为改变了c不会改变arguments

答案是D，结果是21，**因为JavaScript中变量与arguments关联，因此即使arguments与变量不在同一范围，更改变量后arguments也会更改，，更改参数也会更改全局变量。**

## 20.考察js数字精度

What is the result of this expression?

~~~js
var a = 111111111111111111110000,
    b = 1111;
a + b;
~~~

- A.1111111111111111111
- B.1111111111111110000
- C.NaN
- D.Infinity

我选的D，因为a已经超出了2^53 - 1(js中最大的有效数字)，所以会返回Infinity

答案是B，**JavaScript中数字的精度不足会影响大小数字。**



~~~js
var a = 111111111111111110000,
    b = 1111;
a + b;//在js中一个超出精度的数字进行加减运算无效，结果依然是自己。
111111111111111110000

var c = 123;
undefined

a + c
111111111111111110000

a * 2
222222222222222230000//进行乘除运算会失去精度，结果并不可靠！
a
111111111111111110000
a * 3
333333333333333400000
~~~

## 21.考察x = [].reverse

What is the result of this expression?

~~~js
var x = [].reverse;
x();
~~~

- A. []
- B.undefined
- C.error
- D.other

我选的C，因为第一行reverse调用没有()，也就是说没有立即执行它，所以**x指向的是数组的reverse函数**；第二行调用了reverse方法，但是没有在数组上调用，自然会报错

答案是C，我是对的

## 22.考察Number.MIN_VALUE

What is the result of this expression?

~~~js
Number.MIN_VALUE > 0
~~~

- A.false
- B.true
- C.error
- D.other

我选的A，因为最小值肯定比0小呀

答案是B，因为**Number.MIN_VALUE代表大于零的最小值**，-Number.MIN_VALUE也就是小于0 的最大值

~~~js
-1 < -Number.MIN_VALUE
true
~~~

## 23.考察1<2<3连比

What is the result of this expression?

~~~js
[1<2<3,3<2<1]
~~~

- A.true,true
- B.true,false
- C.error
- D.other

我选的B，因为我觉得是从左向右比的

答案是A，上式相当于：[(1<2)<3,(3<2)<1]，

- 第一个先比较1<2返回true，true=1，然后是1<3，又是true；
- 第二个先比较3<2返回false，false = 0，然后是0<1，又是true

~~~js
1<2<0<1
true
//1<2==>true->1
//1<0==>false->0
//0<1==>true
//所以最终结果为true
~~~

## 24.考察2==[[[2]]]

What is the result of this expression?

~~~js
2 == [[[2]]]
~~~

- A.true
- B.false
- C.undefined
- D.other

我选的B，因为2是number，[[[2]]]是数组，二者怎么可能相等呢

答案是A，[[[2]]]是一个数组，数组是引用类型，是对象，所以问题就转化为对象与数组比较，根据15题里面提供的表，这二者比较要将对象转化为数字后与数字进行比较

~~~js
2 == [[[2]]]
//Number([[[2]]])-->2
2 == 2
//true
~~~

## 25.考察3..toString()   √

What is the result of this expression?

~~~js
3.toString()
3..toString()
3...toString()
~~~

- A.'3',error,error
- B.'3','3.0',error
- C.error,'3',error
- D.other

我选的A，这题没什么思路

答案是C， `3.x`是定义尾数为的“ 3”的有效语法`x`，`toString`不是有效数字，但空字符串为。 这是官方解释

经过查资料，最后搞懂了这道题：

**在JavaScript中呢，3  3.   .3都是合法数字**

**3.toString()这个表达式中的3.是表示什么呢？是数字！！因为js中3.是合法数字，这个表达式也就相当于3toString()，这不出错哪跑！**

**3..toString()这个表达式呢，因为3.是合法数字，但3..不是，这就相当于3.这个数字调用了toString()，这没毛病**

**3...toString()，这是个啥玩意！**

还有个有趣的情况：

~~~js
let a = 3;
undefined

a.toString()
"3"

3.toString()
VM491:1 Uncaught SyntaxError: Invalid or unexpected token
~~~

这是不是很无奈？哈哈哈 ！

## 26.考察var a=b=1

What is the result of this expression?

~~~js
(function () {
    var a = b = 1;
})()
console.log(b);
console.log(a);
~~~

- A.1,1
- B.error,error
- C.1,error
- D.other

我选的B，我自认为（）（）里面的变量外部是访问不到的

答案是C，因为a是局部变量，b是全局变量，var a = b = 1可表示为

~~~js
var a = b = 1;
//var a;（a确实是局部变量，外部访问不到）
//b = 1;-->js中未声明的变量都会变成全局变量
//a = b;
~~~

## 27.考察reg == reg

What is the result of this expression?

~~~js
var a = /abc/,
    b = /abc/;
a == b;
a === b;
~~~

- A.true,true
- B.true,false
- C.false,false
- D.other

我选的C，因为a，b都是正则对象，两个不同的对象是不会相等的，哪怕它们的内容相同。

答案是C，没错我做对了，哈哈哈哈！

## 28.考察数组比较大小

What is the result of this expression?

~~~js
var a = [1,2,3],
	b = [1,2,3],
	c = [1,2,4];
a == b;
a === b;
a > c;
a < c;
~~~

- A.false,false,false,true
- B.false,false,false,false
- C.true,true,false,true
- D.other

我选的B，前两个毫无疑问false，因为两个数组对象不可能相等；后两个呢，数组怎么比较大小？false吧

答案是A，前两个毫无疑问false，后两个。。。数组还真能比较大小，比较大小的方法就是将每一位都转化为字符串，按位比较大小（注意，不是比较数字大小，如‘2’就是大于‘11’）。

~~~js
[1,2,3] > [1,2,3]//二者相等
false

[1,2,3] < [1,2,4]//因为‘1’=‘1’，‘2’=‘2’，‘3’<'4'  true
true

[2,2,3] > [10,20,40]//因为'2' > '10'   true
true

[2,2,3] > [21,1,1]//因为‘2’ < '21'   false
false
~~~

## 29.考察Object.prototype

~~~js
var a = {},b = Object.prototype;
[a.prototype === b, Object.getPrototypeOf(a) === b]
~~~

- A.false,true
- B.true,true
- C.fale,false
- D.other

我选的B，感觉任何一个对象的原型都是Object的原型，而且a的原型也是Object.prototype。

答案是A，一个对象实例的prototype是唯一的，这是它定义的时候就带的，并不是Object.prototype；

**Object.getPrototypeOf()方法返回给定对象的原型**

var a = {}  === var a = new Object();

所以a 的原型就是Object.prototype

## 30.考察function.prototype

What is the result of this expression?

~~~js
function f() {};
var a = f.prototype, b = Object.getPrototypeOf(f);
a === b
~~~

- A.true
- B.false
- C.null
- D.other

我选的B，因为这道题绝不会那么容易！（就是蒙的）

答案是B，首先我们要明白：**f.prototype不是用来创建f的，换句话说，f不是f.prototype创建来的，而是它的父类创建了f，而f.prototype是用来创建f的子类的（对象）；Object.getPrototypeOf(f)函数返回f继承层次中的父类。**

这真的太厉害了！听君一席话胜读十年书！

## 31.考察function.name

What is the result of this expression?

~~~js
function foo () {};
var oldName = foo.name;
foo.name = 'bar';
[oldName, foo.name]
~~~

- A.error
- B.' ',' '
- C.'foo', 'foo'
- D.'foo', 'bar'

我选的D，因为函数本身就有name属性，就是函数名，所以oldName为foo毫无疑问，后面将foo.name='bar'，所以foo.name变为了‘bar'

答案是C，因为**函数的name是只读属性！！**

## 32.考察str.replace()

What is the result of this expression?

~~~js
'1 2 3'.replace(/\d/g, parseInt);
~~~

- A.'1 2 3'
- B.'0 1 2'
- C.'NaN 2 3'
- D.'1 NaN 3'

我选的A，众所周知replace()函数会找到匹配的str然后替换掉，很明显会找到1 2 3三个str，parseInt（3/2/1）都是本身，所以A

答案是D，**replace()并不是你想像的那么简单**，它接收2个参数（**str/Reg，str/Fun）**

- 第一个参数是str，就是查找字符串中首次出现的str，然后用第二个参数将它替换掉

- 第一个参数是reg，找到字符串中匹配正则的字符串，然后用第二个参数将它替换掉

- 第二个参数是str，替换掉第一个参数找到的字符串

- **第二个参数是Fun，这是重头戏**

  - **replace（）的第二个参数如果是函数，那么会给它传5个参数，然后用函数的返回值替换掉第一个参数找到的字符串**：

    - | 变量名            | 代表的值                                                     |
      | ----------------- | ------------------------------------------------------------ |
      | `match`           | 匹配的子串。（对应于上述的$&。）                             |
      | `p1,p2, ...`      | 假如replace()方法的第一个参数是一个[`RegExp`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/RegExp) 对象，则代表第n个括号匹配的字符串。（对应于上述的$1，$2等。）例如，如果是用 `/(\a+)(\b+)/` 这个来匹配，`p1` 就是匹配的 `\a+`，`p2` 就是匹配的 `\b+`。 |
      | `offset`          | 匹配到的子字符串在原字符串中的偏移量。（比如，如果原字符串是 `'abcd'`，匹配到的子字符串是 `'bc'`，那么这个参数将会是 1） |
      | `string`          | 被匹配的原字符串。                                           |
      | NamedCaptureGroup | 命名捕获组匹配的对象                                         |

    - ~~~js
      '1 2 3'.replace(/\d/g,function () {console.log(arguments);return 0;});
      
      VM2271:1 Arguments(3) ["1", 0, "1 2 3", callee: ƒ, Symbol(Symbol.iterator): ƒ]
      
      VM2271:1 Arguments(3) ["2", 2, "1 2 3", callee: ƒ, Symbol(Symbol.iterator): ƒ]
      
      VM2271:1 Arguments(3) ["3", 4, "1 2 3", callee: ƒ, Symbol(Symbol.iterator): ƒ]
      
      "0 0 0"
      ~~~

    - **看arguments可以看出，第一个参数就是匹配到的字符串，第二个参数是该字符串在原字符串中出现的位置（类似数组下标），第三个参数是原数组**

    - 所以原题中，parseInt（）函数被传进的参数依次是

      - parseInt（’1‘， 0，’1 2 3‘）
      - parseInt（’2‘， 2，’1 2 3‘）
      - parseInt（’3‘， 4，’1 2 3‘）

    - 但是parseInt只会利用前两个参数

      - ~~~js
        parseInt('1',0)//10进制的1
        1
        
        parseInt('2',2)//2进制中没有2，所以NaN
        NaN
        
        parseInt('3',4)//4进制的3
        3
        ~~~



## 33.考察eval()

What is the result of this expression?

~~~js
function f() {};
var parent = Object.getPrototypeOf(f);
console.log(f.name)
console.log(parent.name)
console.log(typeof eval(f.name));
console.log(typeof eval(parent.name))
~~~

答案是:

> "f"
>
> ""
>
> "function"
>
> undefined 

~~~js
f
ƒ f() {}//f是个函数

eval(f.name);//和上面一样的执行
ƒ f() {}

typeof eval(f.name);//自然是function
"function"

parent//构造f（）的函数，可以看到它是个匿名函数，没有name属性
ƒ () { [native code] }

parent.name//空
""

""
""

eval("")//执行“”
undefined

eval()//执行 empty，二者都是undefined，在以前是报错的
undefined

typeof eval("");//undefined的类型自然是undefined
"undefined"

typeof eval(parent.name)//同上一个效果
"undefined"
~~~

## 34.考察reg.test()

What is the result of this expression?

~~~js
var reg = /^[a-z]+$/;
reg.test(null);
reg.test();
~~~

答案是 true true

为什么呢？因为**test（）函数会先调用toString（）方法将参数变为字符串**，所以就变成了：toString（null） === “null”，toString（undefined） === ‘undefined’，所以都是true。

## 35.考察[,,,].join(',')

What is the result of this expression?

~~~js
[,,,].join(',')
~~~

答案是    ",,"

为什么呢？因为JavaScript在定义数组时允许逗号结尾，因此[,,,]里面只有3个empty。

~~~js
[,,,].join(",")
",,"

[0,0,0,].join(",")//0就是可视化了上面的empty
"0,0,0"
~~~

## 36.考察保留字

What is the result of this expression?

~~~js
var a = {class: "Animal", name: 'Fido'};
a.class
~~~

这道题答案**因浏览器而异**，**因为class是保留字，IE8及其以下版本并不允许这样做**，甚至第一行就会报错（亲测），但是**其他浏览器如Chrome，Firefox，IE8及其以上版本会正确返回 "Animal"**。

## 37.考察new Date()

What is the result of this expression?

~~~js
var a = new Date('epoch');
~~~

答案是：**“Invalid Date”**，这是一个实际的Date对象（a instanceof Date  is  true）。但是无效，这是因为时间在内部保留为“Number”，在这种情况下为NaN。

~~~js
var a = new Date('epoch')//传入一个磨磨唧唧字符串
undefined

a
Invalid Date//a的值为Invalid Date   （无效的Date）

a instanceof Date//Invalid Date依然是Date对象
true

a.getTime();//但是它的值为NaN
NaN

var b = new Date();//一个正常的Date对象
undefined

b
Sun Mar 08 2020 09:10:50 GMT+0800 (中国标准时间)//如new Date()不传参数则返回当前日期

b instanceof Date;//理所当然b是date对象
true

b.getTime()//返回1970年1月1日0:0:0到现在的毫秒数（格林威治时间数值）
1583629850806

var c = Date();//没有new操作符
undefined

c
"Sun Mar 08 2020 09:11:55 GMT+0800 (中国标准时间)"//返回时间的字符串形式

typeof c//对的它是个str
"string"

c.getTime()//str怎么会有getTime方法呢，所以这里报错了
VM868:1 Uncaught TypeError: c.getTime is not a function
    at <anonymous>:1:3
~~~

## 38.考察Function.length

~~~js
var a = Function.length,
    b = new Function().length;
a === b
~~~

答案是false，因为**Function.length 被定义为1，而function实例的length被定义为0.**

~~~js
Function.length//Function构造函数的长度是1
1

new Function().length//function实例的长度是0，这肯定很有趣
0

function fun() {};//一个函数实例
undefined

fun.length//看吧，是0！
0
~~~

## 39.考察new Date()与Date()

What is the result of this expression?

~~~js
var a = Date(0);
var b = new Date(0);
var c = new Date();
a === b;
a === c;
b === c;
~~~

答案是：false，false，false

~~~js
Date(0)
"Sun Mar 08 2020 09:25:09 GMT+0800 (中国标准时间)"//返回当前时间的字符串形式

new Date(0)
Thu Jan 01 1970 08:00:00 GMT+0800 (中国标准时间)//返回元年时间

new Date()
Sun Mar 08 2020 09:26:06 GMT+0800 (中国标准时间)//返回当前时间的Data对象
~~~

## 40.考察Math.min()与Math.max()

What is the result of this expression?

~~~js
var min = Math.min(),max = Math.max();
min < max;
~~~

答案是：false

~~~js
Math.min()
Infinity//Math.min()返回正无穷

Math.max()
-Infinity//Math.max()返回负无穷

Math.min() < Math.max()
false

Math.max() < Math.min()//这才是对的
true
~~~

其实这里的结果很匪夷所思，但是经过查资料，谜团终于揭开：

**首先我们不能望文生义，Math.min()方法是返回参数中的最小值，Math.max()是返回参数中的最大值，如果我们手写一个Math.min()我们要怎么写？或者说，下面的空你应该怎么填？**

~~~js
function max(arr) {
    var max = ____;
    arr.forEach((a) => {
        if(a > max) {
            max = a;
        }
    })
    return max;
}
~~~

**这里填什么最合理呢？很容易就想到要填一个比任何数都小的数，也就是-Infinity，这才能保证第一次比较无论第一个数字多么小也会将值赋值给max。	这样一想呢，Math.max()返回-Infinity也就很合理了！**

## 41.考察正则/g  √

What is the result of this expression?

~~~js
function captureOne(re, str) {
    var match = re.exec(str);
    return match && match[1];
}
var numRe = /num = (\d+)/ig,
    wordRe = /word = (\w+)/i,
    a1 = captureOne(numRe, 'num=1'),
    a2 = captureOne(wordRe, 'word=1'),
    a3 = captureOne(numRe, 'NUM=1'),
    a4 = captureOne(wordRe, 'WORD=1');
a1 === a2
a3 === a4
~~~

答案是：true，false

**regexp.exec(string)方法返回正则在string中查找到的字符，若没找到则返回null。**

\d   一个数字字符

\w	一个大写字符、小写字符、数字、_   相当于[a-zA-Z0-9_]

\+	一个或多个前面的字符   相当于{1，}

\*	0个或多个前面的字符     相当于{0，}

?	0或1个前面的字符			相当于{0,1}

i	ignoreCase  表示忽略大小写

g	global 表示全局匹配

m	multiline  表示多行匹配

~~~js
function captureOne(re, str) {
    var match = re.exec(str);
    return match && match[1];
}
var numRe = /num=(\d+)/ig,
    wordRe = /word=(\w+)/i,
    a1 = captureOne(numRe, 'num=1'),
    a2 = captureOne(wordRe, 'word=1'),
    a3 = captureOne(numRe, 'NUM=1'),
    a4 = captureOne(wordRe, 'WORD=1');
undefined

a1
"1"

a2
"1"

a3//why? 看下面
null

a4
"1"
~~~

如果使用/g标志定义JavaScript中的正则表达式，**那么它将在整个匹配项中携带这个g的状态，即使它们实际用于不同的字符串(lastIndex属性)**。这意味着**a3将为空**，**因为正则表达式是从最后一个匹配字符串的索引开始应用的，即使它是一个不同的字符串。**

~~~js
var reg = /num=(\d+)/ig;//init reg
undefined

reg.lastIndex//now lastIndex = 0
0

reg.exec('num=1')// The first time find true
(2) ["num=1", "1", index: 0, input: "num=1", groups: undefined]

reg.lastIndex//now lastIndex = 5,so next time will 从5开始往后面找
5
reg.exec('num=1')//再找就找不到了
null

reg.lastIndex//当它找不到一次之后呢，lastIndex属性就自己归零，这就很神奇！
0

reg.exec('num=1')//诶，再找就又找到了
(2) ["num=1", "1", index: 0, input: "num=1", groups: undefined]

reg.lastIndex//看，找到一次之后lastIndex就变了
5

reg.exec('NUM=1')//这就找不到了，因为是从5之后开始找到，此时reg.lastIndex为5，哪怕查找的不是同一个str
null

reg.lastIndex//找不到一次之后就又变为了0
0
~~~

## 42.考察new Date()

What is the result of this expression?

~~~js
var a = new Date('2014-03-19');
var b = new Date(2014, 03, 19);
a.getDay() === b.getDay();
a.getMonth() === b.getMonth();
~~~

答案是：false，false

~~~js
new Date(2014, 03, 19);//这个表达式是创建了一个年份为2014，月份为03，日期为19的Date对象，但我们不要忘了，Date对象中month是以0开始计数的，也就是说三月是2，一月是0，所以说该表达式创建了一个‘2014-4-19’，这很重要，所以：

new Date('2014-03-19')
Wed Mar 19 2014 08:00:00 GMT+0800 (中国标准时间)

new Date(2014,03,19)
Sat Apr 19 2014 00:00:00 GMT+0800 (中国标准时间)

new Date('2014-03-19').getDay()//返回星期
3

new Date(2014,03,19).getDay()//返回星期，不同月份的同一天基本不会是同一个星期
6

new Date('2014-03-19').getMonth()
2

new Date(2014,03,19).getMonth()
3
~~~

## 43.考察string.match()

What is the result of this expression?

~~~js
if('http://giftwrapped.com/picture.jpg'.match('.gif')) {
    console.log('a gif file')
} else {
    console.log('not a gif file')
}
~~~

答案是：'a gif file'

因为String.prototype.match(reg)方法要传入正则表达式做参数，**如果传入字符串，则隐式调用new RegExp(str)**，所以原式中传入的'.gif'会变为 /.gif/，而在正则中.是匹配任意单个字符（除了换行和行结束符）的元字符，这自然可以匹配到  '/gif'。

## 44.考察arguments

What is the result of this expression?

~~~js
function foo(a) {
    var a;
    return a;
}
function bar(a) {
    var a = 'bye';
    return a;
}
foo('hello');
bar('hello';)
~~~

答案是：hello，bye

**因为a在foo和bar中都已经隐式声明，所以var a会被删除。  而 var a = 'bye'中var a依然会被删除，但是a = 'bar'赋值保留。**

~~~js
var c = 'cc';
undefined

c
"cc"

var c;//并不会起作用，更不会用undefined将'cc'给替换掉
undefined

c
"cc"//c依然是'cc'

var c = 'd';
undefined

c
"d"
~~~

 **悬挂了变量声明，但是在这种情况下，由于变量已经存在于作用域中，因此将它们完全删除。在`bar()`变量声明中已删除，但赋值仍然保留，因此它起作用。** 















































