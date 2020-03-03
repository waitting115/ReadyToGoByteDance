# 44道题

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
| radix  | 可选。表示要解析的数字的基数。该值介于2~36之间。如果省略该参数或为0，则该数将以10为基础解析它。如果字符串以0X或0x开头，将以16为基数。如果该参数小于2或者大于36，则parseInt将返回NaN |

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

4.



















