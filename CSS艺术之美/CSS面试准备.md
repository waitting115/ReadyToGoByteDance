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

这里主要涉及BFC的问题

### 面试题

**介绍一下标准的CSS的盒子模型？与低版本IE的盒子模型有什么不同？**

标准盒子模型：

- 宽度 = 内容的宽度（content） + border + padding + margin

低版本IE的盒子模型：

- 宽度 = 内容的宽度（content + border + padding） + margin

![img](https://img2018.cnblogs.com/blog/1691302/201907/1691302-20190716102838609-184623210.jpg)



















*

