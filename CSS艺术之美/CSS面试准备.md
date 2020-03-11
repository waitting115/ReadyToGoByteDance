# CSS面试准备

## 1.盒子模型

### 盒子模型的4个属性：

- 内容（content）

- 边框（border）

  - 边框样式（border-style）：

    - 有很多边框样式，具体见HTML实例，很多边框覆盖不到的地方是用background填充的，但这不能证明内容包住了边框，**边框永远包围着内容与内边距，不会占它们的地方！**

  - 边框颜色（border-color）：

  - 边框宽度（border-width）：

    - ~~~css
      border-top-width:0.2em;         /*定义顶部边框的宽度为元素内字体大小的 0.2倍*/
      
      border-width:2px;                  /* 定义4个边都为2px*/
      border-width:2px 4px;              /* 定义上下边为2px,左右边为4px*/
      border-width:2px 3px 4px;          /* 定义上边为2px,左右边为3px,下边为4px*/
      border-width:2px 3px 4px 5px;      /* 定义上边2px,右边为 3px,下边为 4px，左边为5px*/
      ~~~

    - 颜色和宽度等属性都是建立在有样式的基础上的

- 内边距（padding）

  - **是指边框与内容的距离**

- 外边距（margin）

  - **是指边框与相邻元素的距离**

### 盒子水平居中

可以让一个盒子水平居中，但需要满足两个条件：

- 必须是块级元素
- 必须指定宽度

然后就**给左右的外边距设置为auto**，就可以了，详情请见div2

### 清除元素的默认内外边距

为了更方便的控制网页元素，之作网页时，可以这样清除元素的内边距：

~~~css
* {
    padding: 0;
    margin: 0;
}
~~~

**行内元素只有左右外边距，没有上下外边距。**内边距在ie6等老版本浏览器中也有问题，所以我们尽量不要给行内元素指定上下外边距。

### 外边距垂直塌陷

#### 1.相邻块元素垂直外边距合并

也称外边距合并。**当上下两个块级元素相遇时，如果上面元素有下外边距margin-bottom，下面元素有上外边距margin-top，则它们之间的距离不是margin-bottom + margin-top之和，而是二者中最大的那一个。**

![img](https://img2018.cnblogs.com/blog/1691302/201907/1691302-20190716094036631-1922003228.png)

解决办法：避免就好了，只给上面元素添加margin-bottom或只给下面元素添加margin-top，不必为两个元素同时加，详情见div3，div4.

#### 2.嵌套块元素垂直外边距合并

对于两个嵌套关系的块元素，**如果父元素没有上内边距padding-top和边框border**，那么它的上外边距margin-top会和子元素的上外边距margin-top发生合并，合并后的值为较大者。即使父元素的上外边距为0，也会发生合并。

![img](https://img2018.cnblogs.com/blog/1691302/201907/1691302-20190716094247769-2034947904.png)

解决方法：

- 可以为父元素添加1px 的上边框border-top或上内边距padding-top
- 可以为父元素添加overflow：hidden

这里主要涉及BFC的问题，详见BFC.html

### 面试题

**介绍一下标准的CSS的盒子模型？与低版本IE的盒子模型有什么不同？**

标准盒子模型：

- 宽度 = 内容的宽度（content） + border + padding + margin

低版本IE的盒子模型：

- 宽度 = 内容的宽度（content + border + padding） + margin

![img](https://img2018.cnblogs.com/blog/1691302/201907/1691302-20190716102838609-184623210.jpg)

## 2.box-sizing

该属性定义了user agent应该如何计算一个元素的总宽度和总高度。

**它用来控制元素的盒子模型的解析模式。**

有两个值：

- content-box：W3C标准盒子模型。设置元素的width/height属性是指content部分的宽/高。
- border-box：IE传统盒子模型。设置元素的width/height属性是指content+padding+border部分的宽/高。

### user agent（用户代理）

**用户代理是代表一个人的计算机程序。例如，一个在web上的浏览器。**

除了浏览器之外，用户代理还可以是抓取网页的机器人、下载管理器或是可以访问web的其他应用程序。

随着向服务器发送的每个请求，浏览器包含可表明自己身份的User-Agent HTTP的协议头，叫做**用户代理字符串**。此字符串通常标识其浏览器、及其版本号和主机操作系统。

垃圾邮件机器人、下载管理器和一些浏览器通常会发送一个假的UA字符串来宣称自己是不同的客户端。这被称为**用户代理欺骗**。

用户代理字符串可以被JavaScript在客户端中使用navigator.userAgent获取。

典型的UA字符串如下所示：

 `"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:35.0) Gecko/20100101 Firefox/35.0"`.  

## 3.CSS选择器

### CSS选择器分类

#### 1.通配选择器 *

*就是一个通配选择器，它可以匹配任意类型HTML元素。

在配合其他选择器时，没有\*也会有一样的效果。 如\*.warning和.warning效果相同

在CSS3中，**通配选择器可以与命名空间组合使用**：

- ns|*: 会匹配ns命名空间中的所有元素
- *|\*:  会匹配所有命名空间中的所有元素
- |*: 会匹配所有没有命名空间的所有元素

**它是性能最低的选择器！**

#### 2.类型选择器 input

按照给定节点的名称，选择所有匹配的元素。

#### 3.类选择器 .class

按照给定的class属性值，选择所有匹配的元素

#### 4.ID选择器 #id

按照ID属性选择与之匹配的元素。要注意的是，每个文档中每个ID属性都应该是唯一的

#### 5.属性选择器 [attr]

按照给定的属性，选择所有匹配的元素。

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>[attr]</title>
	<style>
		a {
			width: 200px;
			height: 100px;
		}
		/*存在title属性的a元素*/
		a[title] {
			background: red;
		}
		/*href属性为“https://example.org”的a元素*/
		a[href="https://example.org"] {
			background: #ccc;
		}
		/*href属性值有example的a元素*/
		a[href*="example"] {
			font-size: 2em;
		}
		/*href属性值以'.org'结尾的a元素*/
		a[href$='.org'] {
			font-size: italic;
		}
		/*class属性中包含logo的a元素 它和*不同，~需要是一个独立的值*/
		a[class~='logo'] {
			border: 2px solid #f40;
		}
		/*href属性值以#开头的a元素*/
		a[href^='#'] {
			background: yellow;
		}
		/*href属性值中存在insensitive字符的a元素，不区分大小写*/
		a[href*="insensitive" i] {
			color: cyan;
		}
		/*href属性值中存在cAsE字符的a元素，区分大小写，但是这里并没有抓取到，这是个试验性的api，暂时不能再开发中使用*/
		a[href*="cAsE" S] {
			color: pink;
		}
		/*所有包含lang属性的div元素*/
		div[lang] {
			font-weight: bold;
		}
		/*所有lang属性中包括usa值的div元素*/
		div[lang~='usa'] {
			color: blue;
		}
		/*所有lang值为zh或以zh作为前缀的div元素*/
		div[lang|='zh'] {
			color: #F40;
		}
	</style>
</head>
<body>
	<a href="" title="">a[title]</a><br><br>
	<a href="https://example.org">a[href="https://example.org"]</a><br><br>
	<a href="" class="logo">a[class~='logo']</a><br><br>
	<a href="#">a[href^='#']</a><br><br>
	<a href="InsensitivEaabc">a[href*="insensitive" i]</a><br><br>
	<a href="cAsEaabc">a[href*='cAsE' s]</a><br><br>
	<div lang="">lang</div><br><br>
	<div lang="usa lang2">div[lang~='usa']</div><br>
	<div lang="zh-cn">div[lang|='zh']</div>
	<div lang="zh">div[lang|='zh']</div>
</body>
</html>
~~~

#### 6.选择器列表 ，

~~~css
/*选择所有的span和div元素*/
span, div {
    border: 1px solid #ccc;
}
~~~

##### 选择器列表无效化

**使用选择器列表的一个缺点是，在选择器列表中如果有一个选择器不被支持，那么整条选择器列表都会无效。**

解决这个问题的办法就是使用 **:is()选择器**，它会忽视它参数列表中无效的选择器。

**但是由于:is()会影响优先级的计算公式，这样做的代价就是其中所有的选择器都恢复拥有相同的优先级**

#### 7.组合器

- 后代组合器：

  - ~~~css
    /*div元素之内的所有span元素*/
    div span {
        color:red;
    }
    ~~~

- 子代组合器：

  - ~~~css
    /*div元素的直接子元素span，（孙子元素及其以后的元素不会匹配到，只找自己的儿子）*/
    div>span {
        color: DodgerBlue;
    }
    ~~~

- 











*

