# 弹性布局

块级元素与行内元素都可以使用

display: flex;

display: inline-flex

**注意：**

**设为flex布局之后，子元素的float ，clear，vertical-align属性将失效。**



## display: flex;

# 容器的属性：

## flex-direction :

​	决定主轴方向：row  row-reverse   column   column-reverse

## flex-wrap:

​	决定是否换行：nowrap   wrap  wrap-reverse

## flex-flow: 

​	以上两个属性的简写：flex-direction  || flex-wrap;

## justify-content:

​	定义了项目在主轴上的对齐方式：flex-start  flex-end   center   space-between   space-around

## align-items：

​	定义了项目在交叉轴上如何对齐：flex-start   flex-end	center	baseline	stretch

## align-content：

​	定义了多跟轴线的对齐方式，如果项目只有一根轴线，则此属性不起作用：

​	flex-start	flex-end	center	space-between	space-around	stretch

# 项目的属性

## order

定义项目排列顺序，数值越小，排列越靠前，默认为0。

![img](http://www.ruanyifeng.com/blogimg/asset/2015/bg2015071013.png)

## flex-grow

定义项目的放大比例，默认为0，如果存在剩余空间也不放大。

 如果所有项目的`flex-grow`属性都为1，则它们将等分剩余空间（如果有的话）。如果一个项目的`flex-grow`属性为2，其他项目都为1，则前者占据的剩余空间将比其他项多一倍。 

![img](http://www.ruanyifeng.com/blogimg/asset/2015/bg2015071014.png)

## flex-shrink

定义项目的缩小比例，默认为1，如果空间不足，项目将缩小。

如果所有项目的`flex-shrink`属性都为1，当空间不足时，都将等比例缩小。如果一个项目的`flex-shrink`属性为0，其他项目都为1，则空间不足时，前者不缩小。

负值对该属性无效。

![img](http://www.ruanyifeng.com/blogimg/asset/2015/bg2015071015.jpg)