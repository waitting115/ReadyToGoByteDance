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

#### 7.组合器    > ~ +

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

- 一般兄弟选择器

  - ~~~html
    <!DOCTYPE html>
    <html>
    <head>
    	<title></title>
    	<style>
            /*code元素后面的所有span兄弟元素*/
    		code~span {
    			color: red;
    		}
    	</style>
    </head>
    <body>
    	<div>
    		<p>here is some code</p>
    		<span>this is not red</span>
    		<code>here is some code</code>
    		<span>me is red!</span>
    	</div>
    </body>
    </html>
    ~~~

- 紧邻兄弟选择器

  - ~~~css
    /*p元素的后一个紧邻元素，二者共享同一个父节点*/
    p+span {
        color: blue;
    }
    ~~~

#### 8.伪选择器

- 伪类选择器

  - ：active

    - 匹配被用户激活的元素（如被用户正在点击的a链接）

  - ：checked

    - 匹配处于选中状态的元素（如被用户选中的radio单选按钮）

  - ：default

    - 表示一组相关元素中的默认表单元素

  - ：defined

    -  https://developer.mozilla.org/zh-CN/docs/Web/CSS/:defined 

    - 它会抓取任何已定义的元素，可以给它作用到某个元素上面，控制该元素未定义和已定义时的两种状态，如下例的自定义组件。

    - 在自定义元素或组件时会用到，防止你的组件没有加载到当前页面时出现未知的难看元素

    - ~~~css
      simple-custom:not(:defined) {
          display: none;
      }
      simple-custom:defined {
          display: block;
      }
      ~~~

    - 在simple-custom组件加载完成之前都是不可见的，直到它加载完成才可见

  - ：disable

    - 表示任何被禁用的元素，比如购物付款界面，如果不勾选协议无法付款，此时的付款按钮就是禁用的状态。
    - 它与   ：enabled 相反

  - ：empty

    - 代表没有子元素的元素，这里的子元素只可以是元素节点或文本（包括空格！）

    - ~~~html
      <!DOCTYPE html>
      <html>
      <head>
      	<title></title>
      	<style>
      		.box {
      			width: 80px;
      			height: 80px;
      			background: pink;
      		}
      		.box:empty {
      			background: lime;
      		}
      	</style>
      </head>
      <body>
      	<div class="box"><!-- I will be lime --></div>
      	<div class="box">I will be pink</div>
      	<div class="box">
      		<!-- I will be red because of the whitespace around this comment -->
      	</div>
      	<script type="text/javascript">
      		
      	</script>
      </body>
      </html>
      ~~~

  - ：enable

    - 表示任何被启用的元素。

    - 如果一个元素能够被激活（如选择、点击或文本输入），或者能够获取焦点，则该元素是启用的。

    - 它与 ：disable相反

    - ~~~css
      /*选择任何被启用的div*/
      input:enabled {
          color: pink;
      }
      ~~~

  - ：first-child

    - 表示一组兄弟元素中的第一个元素

    - ~~~html
      <!DOCTYPE html>
      <html>
      <head>
      	<title></title>
      	<style>
      		p:first-child {
      			color: lime;
      			background-color: black;
      			padding: 5px;
      		}
      	</style>
      </head>
      <body>
      	<div>
      		<p>This text is selected!</p>
      		<p>This text is not selected!</p>
      	</div>
      	<div>
      		<h2>This text is not selected, because it's nt 'p'</h2>
      		<p>This text is not selected</p>
      	</div>
      </body>
      </html>
      ~~~

  - ：first-of-type

    - 表示一组兄弟元素中其类型的第一个元素。

    - ~~~html
      <!DOCTYPE html>
      <html>
      <head>
      	<title></title>
      	<style>
      		article :first-of-type {
      			color: red;
      		}
      	</style>
      </head>
      <body>
      	<article>
      		<div>This `div` is first div!</div>
      		<div>This<span>nested `span` is first</span>!</div>
      		<div>This<em>nested `em` is first</em>, but this <em>nested `em` is last</em>!</div>
      		<div>This<span>nested `span` gets styled</span>!</div>
      		<b>This `b` qualifies!</b>
              <span>This `span` is first!</span>
      		<div>This is the final `div`</div>
      	</article>
      </body>
      </html>
      ~~~

    - 这很有趣

  - ：focus

    - 表示获得焦点的元素（如表单输入）。当用户点击或触摸元素或通过键盘的tab键选择时它会触发

  - ：focus-within

    - 表示一个元素获得焦点，或其后代获得焦点。换句话说，元素自身或者某个后代匹配：focus伪类。

    - ~~~html
      <!DOCTYPE html>
      <html>
      <head>
      	<title></title>
      	<style>
      		form {
      			border: 1px solid #000;
      			color: gray;
      			padding: 4px;
      		}
      		form:focus-within {
      			background: #ff8;
      			color: #000;
      		}
      		input {
      			margin: 4px;
      		}
      	</style>
      </head>
      <body>
      	<p>试试在这个表单中输入点什么！</p>
      	<form action="">
      		<label for="given_name">Given Name:</label>
      		<input type="text" id="given_name">
      		<br>
      		<label for="falimy_name">Family Name;</label>
      		<input type="text" id="family_name">
      	</form>
      </body>
      </html>
      ~~~

    - ：host
    
      - 这允许你从其shadow DOM中选择一个自定义元素
    
    - ：hover
    
      - 适用于用户使用指示设备虚指一个元素（鼠标放上面 不点击）。这个样式会被任何一个与链接相关的伪类重写，像：link，：visited和：active等。为了确保生效，：hover规则需要放在：link和：visited规则之后，但在：active之前，按照LVHA的循序声明    : link -->:  visited -->:hover -->:  active，分别是：未访问的链接，已访问的链接，当有鼠标悬停在链接上，被选择的链接。
    
    - ：indetereminate
    
      - 表示状态不确定的表单元素。
    
    - ：inrange
    
      - 代表一个<input>元素，其当前值处于属性min和max的限定范围之内。
      - 该伪类可以给用户一个可视化提示，表示输入域的当前值是否处于允许范围内。
      - 

- 伪元素选择器



## 6.居中问题

如何居中div？

~~~css
border: 1px solid;
width: 100px;
height: 100px;
margin: 0 auto;/*左右外边距auto是重点*/
~~~

如何居中一个浮动元素？（上下左右居中）

~~~css
border: 1px solid;
width: 200px;
height: 100px;
float: left;
position: absolute;/*绝对定位*/
top: 50%;
left: 50%;
margin-top: -50px ;
margin-left: -100px;
~~~

如何居中一个div里面的元素？（上下左右居中）

~~~css
.div1 {
    width: 300px;
    height: 300px;
    border: 1px solid;
}
.div2 {
    border: 1px solid;
    width: 200px;
    height: 100px;
    position: relative;/*相对定位*/
    top: 50%;
    left: 50%;
    margin-top: -50px ;
    margin-left: -100px;
}

<div class="div1">
	<div class="div2"></div>
</div>
~~~

绝对定位的左右居中？

~~~css
width: 200px;
height: 100px;
border: 1px solid;
position: absolute;
left: 0;
right: 0;
margin: 0 auto;
~~~

还有更优雅的居中方式，就是flexbox，以后会写相关的笔记。

## 7.display值及作用

display有两个作用：

- 一是定义元素类型（块级元素或行内元素），规定元素的流式布局；
- 二是控制其子元素的布局（grid或flex）





- inline——内联
- none——隐藏（从DOM树中将其移除）
- block——块显示
- table——表格显示
- list-item——项目列表
- inline-block——行内块元素

## 8.position值

- static——默认值，按照正常文档流进行排列
- relative——相对定位，不脱离文档流，参考自身静态位置通过top，bottom，left，right定位
- absolute——绝对定位，参考最近的一个不为static的父级元素通过top，bottom，left，right定位
- fixed——固定定位，所固定的参考对象是可视窗口

## 9.CSS3新特性

1. RGBA和透明度
2. - background-image 
   - background-origin(content-box/padding-box/border-box)
     - 该属性规定background-position属性相对于什么来定位
     - padding-box：（默认值）背景图像相对于内边距框来定位
     - border-box：背景图像相对于边框盒来定位
     - content-box：背景图像相对于内容框来定位
   - background-size
   - background-repeat

3. - word-wrap：允许长单词或url地址换到下一行
     - normal：默认值，只在允许的断字点处换行
     - break-word：在长单词或url地址内部进行换行

4. 文字阴影：text-shadow：5px 5px 5px #F00;(水平阴影，垂直阴影，模糊距离，阴影颜色)
5. font-face属性：定义自己的字体
6. 圆角：border-radius
7. 边框图片：border-image：url(border.png) 30 30 round;(用图片做边框)
8. 盒子阴影：box-shadow：10px 10px 5px #888;
9. 媒体查询：定义两套css，当浏览器尺寸变化时会采用不同的属性

## 10.flex布局

请介绍一下flex布局以及适用场景，这个会单独介绍。

适用场景：适用于移动前端开发，在Android和ios上也完美契合。

## 11.css创建三角形

用纯css创建三角形的原理是什么？

把元素宽高设为0，然后设置边框样式。

~~~css
width: 0;
height: 0;
border-top: 100px solid transparent;/*透明*/
border-left: 100px solid transparent;
border-right: 100px solid transparent;
border-bottom: 100px solid #f40;
~~~

## 12.满屏品字布局设计

第一种真正的品字

- 三块的宽高是固定的
- 上面那块用margin(0 auto)居中
- 下面两块用float或inline-block使其不换行
- 用margin调整位置使其居中

第二种全屏的品字布局

- 上面那块宽度100%
- 下面两块宽度50%，然后用float或inline-block使其不换行

## 13.常见的兼容性问题

1. 不同浏览器默认标签默认的margin和padding不一样。*{margin:0; padding:0}

2. IE6双边距bug：块属性标签float后，又有横行的margin情况下，在IE6显示margin比设置的大。 解决： display：inline；将其转化为行内属性。

3. 渐进识别方式，从总体中逐渐排出局部。首先，巧妙的使用“9”这一标记，将IE浏览器从所有情况中分离出来。

   1. ~~~css
      {
      background-color:#f1ee18;/*所有识别*/
      .background-color:#00deff\9; /*IE6、7、8识别*/
      +background-color:#a200ff;/*IE6、7识别*/
      _background-color:#1e0bd1;/*IE6识别*/
      }
      ~~~

4. 设置较小高度标签（一般小于10px），在IE6/7中高度会超出自己预设的高度。解决：给超出高度的标签设置overflow：hidden或者设置行高line-height小于你设置的高度。

5. IE下可以使用常规方法获取自定义属性，也可以使用getAttribute()方法获取；FireFox只能用getAttribute()获取。解决：统一使用getAttribute()获取自定义属性。

6. Chrome中文界面下会将小于12px的文本强制按照12px显示。解决：加入css属性：-webkit-text-size-adjust:none可解决。

7. 超链接访问过后hover样式就不出现了，被点击访问过的链接样式不再具有hover和active了。解决：改变CSS属性的排列顺序：L-V-H-A：a:link{}; a:visited{}; a:hover{};  a:active{}.







*

