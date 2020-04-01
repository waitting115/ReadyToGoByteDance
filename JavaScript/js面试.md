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

## 3.ES5与ES6几种声明方式

ES5有两种：var与function

ES6新增4种：let   const    class    import（导包时使用）

注意：let   const   class不会发生声明提升，全局变量与也不再会变为全局对象（window）的属性。

## 4.闭包的概念？优缺点？

闭包就是能够读取其他函数内部变量的函数。

优点：

- 避免全局变量污染
- 希望一个变量可以长期存储在内存中（缓存变量）

缺点：

- 内存泄漏
- 常驻内存，增加内存使用量

## 5.浅拷贝与深拷贝

### 浅拷贝：

#### Object.assign()

该方法用于将所有可枚举的属性值从一个或多个源对象复制到目标对象。返回该目标对象。

~~~js
const target = { a: 1, b: 2};
undefined
const source = { b:4, c: 5};
undefined
const returnedTarget = Object.assign(target, source);//（源对象，目标对象）
undefined
target
{a: 1, b: 4, c: 5}
source
{b: 4, c: 5}
returnedTarget
{a: 1, b: 4, c: 5}
~~~

**如果目标对象中的属性具有相同的键，则属性将被源对象中的属性覆盖。后面的源对象的属性将类似地覆盖前面的源对象的属性。**

`Object.assign` 方法只会拷贝源对象自身的并且可枚举的属性到目标对象。**该方法使用源对象的`[[Get]]`和目标对象的`[[Set]]`，所以它会调用相关 getter 和 setter**。因此，它分配属性，而不仅仅是复制或定义新的属性。如果合并源包含getter，这可能使其不适合将新属性合并到原型中。为了将属性定义（包括其可枚举性）复制到原型，应使用[`Object.getOwnPropertyDescriptor()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/getOwnPropertyDescriptor)和[`Object.defineProperty()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/defineProperty) 。

[`String`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/String)类型和 [`Symbol`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Symbol) 类型的属性都会被拷贝。

在出现错误的情况下，例如，如果属性不可写，会引发[`TypeError`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/TypeError)，如果在引发错误之前添加了任何属性，则可以更改`target`对象。

注意，`Object.assign` 不会在那些`source`对象值为 [`null`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/null) 或 [`undefined`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/undefined) 的时候抛出错误。

 **针对深拷贝，需要使用其他办法，因为 `Object.assign()`拷贝的是属性值。假如源对象的属性值是一个对象的引用，那么它也只指向那个引用**。 

#### Array.prototype.slice()

该方法返回一个新的数组对象，这一对象是一个由 `begin` 和 `end` 决定的原数组的**浅拷贝**（包括 `begin`，不包括`end`）。原始数组不会被改变。 

如果不传参则直接拷贝一遍原数组。

#### 扩展运算符 . . .

 它好比 rest 参数的逆运算，**将一个数组转为用逗号分隔的参数序列。** 

它是浅拷贝：

~~~js
let arr = [1,2,3];
undefined
let obj = {array: arr};
undefined
let obj2 = {...obj};
undefined
obj2
{array: Array(3)}array: (3) [1, 2, 3]__proto__: Object
arr.push(4);
4
obj2.array
(4) [1, 2, 3, 4]//这里也会变化
~~~

### 深拷贝：

#### JSON.parse(JSON.stringify(obj))

~~~js
let arr = [1,2,3];
undefined
let obj = {array : arr, wa: 'wa'};
undefined
let obj2 = JSON.parse(JSON.stringify(obj));
undefined
obj2
{array: Array(3), wa: "wa"}
arr.push(4);
4
obj2.array
(3) [1, 2, 3]
~~~

#### 递归函数：

~~~js
function cloneObj(obj) {
    let newObj = {};
    if(typeof obj !== 'object') {
        return obj;
    } else {
        for(let attr in obj) {
            newObj[attr] = cloneObj(obj[attr]);
        }
    }
    return newObj;
}
~~~

## 6.DOM 事件有哪些阶段？谈谈对事件代理的理解

**三个事件阶段：**

- **捕获阶段**

  -  事件从根节点流向目标节点，途中流经各个DOM节点，在各个节点上触发捕获事件，直到达到目标节点。 

  - **那么捕获阶段的作用是什么呢**？

      捕获阶段的主要任务是建立传播路经，在冒泡阶段根据这个路经回溯到文档根节点

- **目标阶段**

  -  事件到达目标节点时，就到了目标阶段，事件在目标节点上被触发 

- **冒泡阶段**

  -  事件在目标节点上触发后，不会终止，一层层向上冒，回溯到根节点。 

  - 阻止事件的冒泡：

    当我们在某个DOM节点绑定了某事件监听器，本来是想当该DOM节点触发事件，再执行回调函数。结果是该节点的某个子节点触发事件，由于事件冒泡，该DOM节点事件也会触发，执行了回调函数，这样就违背了最初的本意了。

    所以需要阻止冒泡：   **event.stopPropagation()**  

    ~~~js
    //阻止冒泡封装
    function stopBub(event) {
        event = event || window.event;
            if(event&&event.stopPropagation){
                event.stopPropagation();
            }else{
                event.cancelBubble = true;
        }
    
    }
    ~~~

    ————————————————
    版权声明：本文为CSDN博主「LJqqqqqqqq」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/weixin_42681490/java/article/details/82228115

**事件代理：**

​	**简单来说就是，事件不直接绑定到某元素上面，而是绑定到该元素的父元素上，进行触发事件操作时（例如click），再通过条件判断，执行事件触发后的语句（例如：alert（e.target.innerHTML））**

​	**好处：1.使代码更简洁；2.节省内存开销。**

## 7.js执行机制，事件循环

 JavaScript 语言的一大特点就是**单线程**，同一个时间只能做一件事。单线程就意味着，所有任务需要排队，前一个任务结束，才会执行后一个任务。如果前一个任务耗时很长，后一个任务就不得不一直等着。JavaScript 语言的设计者意识到这个问题，将所有任务分成两种，一种是**同步任务（synchronous），另一种是异步任务（asynchronous）**，**在所有同步任务执行完之前，任何的异步任务是不会执行的。**
   当我们打开网站时，网页的渲染过程就是一大堆同步任务，比如页面骨架和页面元素的渲染。而像加载图片音乐之类占用资源大耗时久的任务，就是异步任务。关于这部分有严格的文字定义，但本文的目的是用最小的学习成本彻底弄懂执行机制，所以我们用导图来说明：
![js执行机制](https://img2018.cnblogs.com/blog/1062623/201908/1062623-20190804122236943-502816731.jpg)

导图要表达的内容用文字来表述的话：
   同步和异步任务分别进入不同的执行"场所"，同步的进入主线程，异步的进入 Event Table 并注册函数。当**指定的事情完成时**，Event Table 会将这个函数移入 Event Queue。主线程内的任务执行完毕为空，会去 Event Queue 读取对应的函数，进入主线程执行。上述过程会不断重复，也就是常说的 **Event Loop(事件循环)。**
   我们不禁要问了，那怎么知道主线程执行栈为空啊？js 引擎存在 monitoring process 进程，会持续不断的检查主线程执行栈是否为空，一旦为空，就会去 Event Queue 那里检查是否有等待被调用的函数。换一张图片也许更好理解主线程的执行过程：

![img](https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3568080110,3758711556&fm=173&app=25&f=JPEG?w=639&h=354&s=5A203C6292FE6588147FCCC40200E0B3)
   上图用文字表述就是：主线程从"任务队列"中读取事件，这个过程是循环不断的，所以整个的这种运行机制又称为 Event Loop（事件循环）。只要主线程空了，就会去读取"任务队列"，这就是 JavaScript 的运行机制。

   说完 JS 主线程的执行机制，下面说说经常被问到的 JS 异步中 宏任务（macrotasks）、微任务（microtasks）执行顺序。**JS 异步有一个机制，就是遇到宏任务，先执行宏任务，将宏任务放入 Event Queue，然后再执行微任务，将微任务放入 Event Queue，但是，这两个 Queue 不是一个 Queue。当你往外拿的时候先从微任务里拿这个回调函数，然后再从宏任务的 Queue 拿宏任务的回调函数**。如下图：
![JS异步](https://img2018.cnblogs.com/blog/1062623/201908/1062623-20190804122144531-1357636710.jpg)

宏任务：整体代码 script，setTimeout，setInterval

微任务：Promise，process.nextTick

参考链接：[这一次，彻底弄懂 JavaScript 执行机制](https://juejin.im/post/59e85eebf265da430d571f89)

## 8.介绍下Promise.all

Promise.all()方法将多个Promise实例包装成一个Promise对象（p），接受一个数组（p1,p2,p3）作为参数，数组中不一定需要都是Promise对象，但是一定具有Iterator接口，如果不是的话，就会调用Promise.resolve将其转化为Promise对象之后再进行处理。
   使用Promise.all()生成的Promise对象（p）的状态是由数组中的Promise对象（p1,p2,p3）决定的。

1. 如果所有的Promise对象（p1,p2,p3）都变成fullfilled状态的话，生成的Promise对象（p）也会变成fullfilled状态，
   p1,p2,p3三个Promise对象产生的结果会组成一个数组返回给传递给p的回调函数。
2. 如果p1,p2,p3中有一个Promise对象变为rejected状态的话，p也会变成rejected状态，第一个被rejected的对象的返回值会传递给p的回调函数。
   Promise.all()方法生成的Promise对象也会有一个catch方法来捕获错误处理，但是如果数组中的Promise对象变成rejected状态时，
   并且这个对象还定义了catch的方法，那么rejected的对象会执行自己的catch方法。
   并且返回一个状态为fullfilled的Promise对象，Promise.all()生成的对象会接受这个Promise对象，不会返回rejected状态。

## 9.async 和 await

主要考察宏任务和微任务，搭配promise，询问一些输出的顺序

原理：**async 和 await 用了同步的方式去做异步，async 定义的函数的返回值都是 promise，await 后面的函数会先执行一遍，然后就会跳出整个 async 函数来执行后面js栈的代码**

## 10.ES6 的 class 和构造函数的区别

**class 的写法只是语法糖**，和之前 prototype 差不多，但还是有细微差别的，下面看看：

##### 1. 严格模式

**类和模块的内部，默认就是严格模式**，所以不需要使用`use strict`指定运行模式。只要你的代码写在类或模块之中，就只有严格模式可用。考虑到未来所有的代码，其实都是运行在模块之中，**所以 ES6 实际上把整个语言升级到了严格模式。**

##### 2. 不存在提升

类不存在变量提升（hoist），这一点与 ES5 完全不同。

```js
new Foo(); // ReferenceError
class Foo {}
```

##### 3. 方法默认是不可枚举的

ES6 中的 class，它的方法（包括静态方法和实例方法）默认是不可枚举的，而构造函数默认是可枚举的。细想一下，这其实是个优化，让你在遍历时候，不需要再判断 hasOwnProperty 了

##### 4. class 的所有方法（包括静态方法和实例方法）都没有原型对象 prototype，所以也没有[[construct]]，不能使用 new 来调用。

##### 5. class 必须使用 new 调用，否则会报错。这是它跟普通构造函数的一个主要区别，后者不用 new 也可以执行。

##### 6. ES5 和 ES6 子类 this 生成顺序不同

ES5 的继承先生成了子类实例，再调用父类的构造函数修饰子类实例。ES6 的继承先生成父类实例，再调用子类的构造函数修饰父类实例。这个差别使得 ES6 可以继承内置对象。

##### 7. ES6可以继承静态方法，而构造函数不能

## 11.transform、translate、transition 分别是什么属性？CSS 中常用的实现动画方式

三者属性说明
transform 是指变换、变形，是 css3 的一个属性，和 width，height 属性一样；
translate 是 transform 的属性值，是指元素进行 2D(3D)维度上位移或范围变换;
transition 是指过渡效果，往往理解成简单的动画，需要有触发条件。

这里可以补充下 transition 和 animation 的比较，前者一般定义开始结束两个状态，需要有触发条件；而后者引入了关键帧、速度曲线、播放次数等概念，更符合动画的定义，且无需触发条件

## 12.介绍一下rAF(requestAnimationFrame)

**专门用来做动画，不卡顿**，用法和setTimeout一样。对 rAF 的阐述 [MDN 资料](https://developer.mozilla.org/zh-CN/docs/Web/API/Window/requestAnimationFrame)

   定时器一直是 js 动画的核心技术，但它们不够精准，**因为定时器时间参数是指将执行代码放入 UI 线程队列中等待的时间，如果前面有其他任务队列执行时间过长，则会导致动画延迟，效果不精确等问题。**
   所以处理动画循环的关键是知道延迟多长时间合适：时间要足够短，才能让动画看起来比较柔滑平顺，避免多余性能损耗；时间要足够长，才能让浏览器准备好变化渲染。这个时候 rAF 就出现了，采用系统时间间隔(大多浏览器刷新频率是 60Hz，相当于 1000ms/60≈16.6ms)，保持最佳绘制效率，不会因为间隔时间过短，造成过度绘制，增加开销；也不会因为间隔时间太长，使用动画卡顿不流畅，让各种网页动画效果能够有一个统一的刷新机制。并且 rAF 会把每一帧中的所有 DOM 操作集中起来，在一次重绘或回流中就完成。

详情：[CSS3动画那么强，requestAnimationFrame还有毛线用？](https://www.zhangxinxu.com/wordpress/2013/09/css3-animation-requestanimationframe-tween-动画算法/)

## 13.javascript 的垃圾回收机制讲一下



定义：指一块被分配的内存既不能使用，又不能回收，直到浏览器进程结束。

像 C 这样的编程语言，具有低级内存管理原语，如 malloc()和 free()。开发人员使用这些原语显式地对操作系统的内存进行分配和释放。
而 JavaScript 在创建对象(对象、字符串等)时会为它们分配内存，不再使用对时会“自动”释放内存，这个过程称为垃圾收集。

内存生命周期中的每一个阶段:

**分配内存** —  内存是由操作系统分配的，它允许您的程序使用它。在低级语言(例如 C 语言)中，这是一个开发人员需要自己处理的显式执行的操作。然而，在高级语言中，系统会自动为你分配内在。
**使用内存** — 这是程序实际使用之前分配的内存，在代码中使用分配的变量时，就会发生读和写操作。
**释放内存** — 释放所有不再使用的内存,使之成为自由内存,并可以被重利用。与分配内存操作一样,这一操作在低级语言中也是需要显式地执行。

##### 四种常见的内存泄漏：全局变量，未清除的定时器，闭包，以及 dom 的引用

1. 全局变量 不用 var 声明的变量，相当于挂载到 window 对象上。如：b=1; 解决：使用严格模式
2. 被遗忘的定时器和回调函数
3. 闭包
4. 没有清理的 DOM 元素引用

## 14.对前端性能优化有什么了解？一般都通过那几个方面去优化的？

[前端性能优化的七大手段](https://www.cnblogs.com/xiaohuochai/p/9178390.html)

1. 减少请求数量
2. 减小资源大小
3. 优化网络连接
4. 优化资源加载
5. 减少重绘回流
6. 性能更好的API
7. webpack优化





*





















