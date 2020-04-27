## 1. 内联首屏关键CSS（Critical CSS）

性能优化中有一个重要的指标——**首次有效绘制**（First Meaningful Paint，简称FMP）即指页面的首要内容（primary content）出现在屏幕上的时间。这一指标影响用户看到页面前所需等待的时间，而**内联首屏关键CSS（即Critical CSS，可以称之为首屏关键CSS）**能减少这一时间。

大家应该都习惯于通过link标签引用外部CSS文件。但需要知道的是，**将CSS直接内联到HTML文档中能使CSS更快速地下载**。而使用外部CSS文件时，需要在HTML文档下载完成后才知道所要引用的CSS文件，然后才下载它们。所以说，**内联CSS能够使浏览器开始页面渲染的时间提前**，因为在HTML下载完成之后就能渲染了。

既然内联CSS能够使页面渲染的开始时间提前，那么是否可以内联所有的CSS呢？答案显然是否定的，这种方式并不适用于内联较大的CSS文件。因为[初始拥塞窗口](https://link.juejin.im/?target=https%3A%2F%2Ftylercipriani.com%2Fblog%2F2016%2F09%2F25%2Fthe-14kb-in-the-tcp-initial-window%2F)3存在限制（TCP相关概念，通常是 **14.6kB**，压缩后大小），如果内联CSS后的文件超出了这一限制，系统就需要在服务器和浏览器之间进行更多次的往返，这样并不能提前页面渲染时间。因此，我们应当**只将渲染首屏内容所需的关键CSS内联到HTML中**。

既然已经知道内联首屏关键CSS能够优化性能了，那下一步就是如何确定首屏关键CSS了。显然，我们不需要手动确定哪些内容是首屏关键CSS。Github上有一个项目[Critical CSS](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Ffilamentgroup%2FcriticalCSS)4，**可以将属于首屏的关键样式提取出来**，大家可以看一下该项目，结合自己的构建工具进行使用。当然为了保证正确，大家最好再亲自确认下提取出的内容是否有缺失。

**不过内联CSS有一个缺点，内联之后的CSS不会进行缓存，每次都会重新下载。不过如上所说，如果我们将内联后的文件大小控制在了14.6kb以内，这似乎并不是什么大问题。**

如上，我们已经介绍了为什么要内联关键CSS以及如何内联，那么剩下的CSS我们怎么处理好呢？建议使用外部CSS引入剩余CSS，这样能够启用缓存，除此之外还可以异步加载它们。

## 2. 异步加载CSS

**CSS会阻塞渲染，在CSS文件请求、下载、解析完成之前，浏览器将不会渲染任何已处理的内容。**有时，这种阻塞是必须的，因为我们并不希望在所需的CSS加载之前，浏览器就开始渲染页面。那么**将首屏关键CSS内联后，剩余的CSS内容的阻塞渲染就不是必需的了，可以使用外部CSS，并且异步加载。**

那么如何实现CSS的异步加载呢？有以下四种方式可以实现浏览器异步加载CSS。

第一种方式是使用JavaScript动态创建样式表link元素，并插入到DOM中。

```js
// 创建link标签
const myCSS = document.createElement( "link" );
myCSS.rel = "stylesheet";
myCSS.href = "mystyles.css";
// 插入到header的最后位置
document.head.insertBefore( myCSS, document.head.childNodes[ document.head.childNodes.length - 1 ].nextSibling );
```

第二种方式是将link元素的`media`属性设置为用户浏览器不匹配的媒体类型（或媒体查询），如`media="print"`，甚至可以是完全不存在的类型`media="noexist"`。对浏览器来说，如果样式表不适用于当前媒体类型，其优先级会被放低，会在不阻塞页面渲染的情况下再进行下载。

当然，这么做只是为了实现CSS的异步加载，别忘了在文件加载完成之后，将`media`的值设为`screen`或`all`，从而让浏览器开始解析CSS。

```html
<link rel="stylesheet" href="mystyles.css" media="noexist" onload="this.media='all'">
```

与第二种方式相似，我们还可以通过`rel`属性将`link`元素标记为`alternate`可选样式表，也能实现浏览器异步加载。同样别忘了加载完成之后，将`rel`改回去。

```html
<link rel="alternate stylesheet" href="mystyles.css" onload="this.rel='stylesheet'">
```

上述的三种方法都较为古老。现在，[rel="preload"](https://link.juejin.im/?target=https%3A%2F%2Fwww.w3.org%2FTR%2Fpreload%2F)5这一Web标准指出了如何异步加载资源，包括CSS类资源。

```html
<link rel="preload" href="mystyles.css" as="style" onload="this.rel='stylesheet'">
```

**as 来指定将要预加载的内容的类型** 

rel预加载介绍： https://blog.csdn.net/qq_38652871/article/details/87075283 

注意，`as`是必须的。忽略`as`属性，或者错误的`as`属性会使`preload`等同于`XHR`请求，浏览器不知道加载的是什么内容，因此此类资源加载优先级会非常低。`as`的可选值可以参考上述标准文档。

看起来，`rel="preload"`的用法和上面两种没什么区别，都是通过更改某些属性，使得浏览器异步加载CSS文件但不解析，直到加载完成并将修改还原，然后开始解析。

但是它们之间其实有一个很重要的不同点，那就是**使用preload，比使用不匹配的`media`方法能够更早地开始加载CSS**。所以尽管这一标准的支持度还不完善，仍建议优先使用该方法。

该标准现在已经是候选标准，相信浏览器会逐渐支持该标准。在各浏览器的支持度如下图所示。

 

![preload浏览器支持度](https://user-gold-cdn.xitu.io/2018/8/1/164f3b1b1df0672d?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

从上图可以看出这一方法在现在的浏览器中支持度不算乐观，不过我们可以通过[loadCSS](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Ffilamentgroup%2FloadCSS%2Ftree%2Fv2.0.1%23loadcss)6进行polyfill，所以支持不支持，这都不是事儿。

## 3. 文件压缩

性能优化时有一个最容易想到，也最常使用的方法，那就是文件压缩，这一方案往往**效果显著**。

文件的大小会直接影响浏览器的加载速度，这一点在网络较差时表现地尤为明显。相信大家都早已习惯对CSS进行压缩，现在的构建工具，如webpack、gulp/grunt、rollup等也都支持CSS压缩功能。压缩后的文件能够明显减小，可以大大降低了浏览器的加载时间。

## 4. 去除无用CSS

虽然文件压缩能够降低文件大小。但CSS文件压缩通常只会去除无用的空格，这样就限制了CSS文件的压缩比例。那是否还有其他手段来精简CSS呢？答案显然是肯定的，如果压缩后的文件仍然超出了预期的大小，我们可以试着**找到并删除代码中无用的CSS**。

一般情况下，会存在这两种无用的CSS代码：一种是不同元素或者其他情况下的重复代码，一种是整个页面内没有生效的CSS代码。对于前者，在编写的代码时候，我们应该尽可能地提取公共类，减少重复。对于后者，在不同开发者进行代码维护的过程中，总会产生不再使用的CSS的代码，当然一个人编写时也有可能出现这一问题。而这些无用的CSS代码不仅会增加浏览器的下载量，还会增加浏览器的解析时间，这对性能来说是很大的消耗。所以我们需要找到并去除这些无用代码。

当然，如果手动删除这些无用CSS是很低效的。**我们可以借助[Uncss](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Funcss%2Funcss)7库来进行**。Uncss可以用来移除样式表中的无用CSS，并且支持多文件和JavaScript注入的CSS。

## 5.CSS编写细节

###  1.减少css嵌套

最好不要套三层以上，一般情况下块级元素加上类，里面的内联元素不用加，css写的时候块级class套内联tag，这样不仅可以减少css文件大小，还能减少性能浪费。 

### 2.不要在ID选择器前面进行嵌套 

因为ID选择器本身就是唯一的，而且人家权值那么大，嵌套完全是浪费性能。

### 3. 建立公共样式类 

 把效果相同的样式提取出来作为公共类使用，比如我们常用的清除浮动，单行超出显示省略号等等等，当然如果你使用sass，继承会让你更加方便。

### 4. 缩写css 

 其中包括缩写maigin，padding，颜色值等等，要是有两个或者两个以上的margin-****，写成margin: * * * *有助于文件大小。  

### 5. 减少通配符*或者类似[hidden="true"]这类选择器的使用 

 挨个查找所有...这性能能好吗？当然重置样式这些必须的东西是不能少的。 

### 6.class前面不要加标签

因为类名在全局范围内应该是唯一的，所以没必要在前面再加上标签名来提高精确度，这只能浪费性能。

### 7. 巧妙运用css的继承机制 

 在css中很多属性是可以继承的比如颜色字体等等，父节点定义了，子节点就无需定义。 

### 8.少用CSS表达式

 可能这个用的比较少，但是要记住的是无论我们怎么炫酷，到了最后都是静态的，所以表达式只是让你的代码显得更加炫酷，但是**它对性能的浪费可能是超乎你的想象的**，因为它并不只是计算一次，一些小的事件可能都会增加它为了有效准确而进行计算求值的次数。 

### 9. cssSprite（雪碧图/精灵图）

 合成所有icon图片，用宽高加上bacgroud-position的背景图方式显现出我们要的icon图，这是一种十分实用的技巧，极大减少了http请求。 

### 10.慎重选择高消耗的属性

 0 .绘制前需要进行大量计算的属性
1 .border-radius
2 .box-shadow
3 .transform
4 .css filtres 

### 11.减少重排、重绘

 浏览器为了重新渲染部分或整个页面，重新计算页面元素位置和几何结构的进程叫做重排（reflow）。

导致reflow发生的情况：

```kotlin
1 .改变窗口的大小
2 .改变文字的大小
3 .添加，删除样式表
4 .内容的改变，输入框输入内容也会
5 .伪类的激活
6 .操作class属性
7 .脚本操作dom
8 .计算offsetWidth和offsetHeight
9 .设置style属性
```

###  12.不要使用@import引入css文件

不建议使用@import主要有以下两点原因。

首先，使用@import引入CSS会影响浏览器的并行下载。使用@import引用的CSS文件只有在引用它的那个css文件被下载、解析之后，浏览器才会知道还有另外一个css需要下载，这时才去下载，然后下载后开始解析、构建render tree等一系列操作。这就导致浏览器无法并行下载所需的样式文件。

其次，多个@import会导致下载顺序紊乱。在IE中，@import会引发资源文件的下载顺序被打乱，即**排列在@import后面的js文件先于@import下载，并且打乱甚至破坏@import自身的并行下载**。

所以不要使用这一方法，使用link标签就行了。

