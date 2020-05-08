Canvas绘图
HTML5 Canvas绘图手册：https://www.w3school.com.cn/html5/html5_ref_canvas.asp

　　标签只是图形容器，必须使用脚本来绘制图形。

Canvas - 图形
创建一个画布，一个画布在网页中是一个矩形框，通过 <canvas> 元素来绘制。**默认情况下 元素没有边框和内容。**
1

~~~html
<canvas id="myCanvas" width="200" height="100" style="border:1px solid #000000;"></canvas>
~~~



　　标签通常需要指定一个id属性 (脚本中经常引用), width 和 height 属性定义的画布的大小，使用 style 属性来添加边框。你可以在HTML页面中使用多个 <canvas> 元素

**使用Javascript来绘制图像，canvas 元素本身是没有绘图能力的**。所有的绘制工作必须在 JavaScript 内部完成

~~~html
<script>
　　var c=document.getElementById("myCanvas");
　　var ctx=c.getContext("2d");
　　ctx.fillStyle="#FF0000";
　　ctx.fillRect(0,0,150,75);
</script>
~~~



　　**getContext("2d") 对象**是内建的 HTML5 对象，拥有多种绘制路径、矩形、圆形、字符以及添加图像的方法。

　　设置 fillStyle 属性可以是CSS颜色，渐变，或图案。fillStyle默认设置是#000000（黑色）。fillRect(x,y,width,height) 方法定义了矩形当前的填充方式。意思是：在画布上绘制 150x75 的矩形，从左上角开始 (0,0)。　

**Canvas - 路径**
在Canvas上画线，我们将使用以下两种方法：

moveTo(x,y) 定义线条开始坐标

lineTo(x,y) 定义线条结束坐标

绘制线条我们必须使用到 "ink" 的方法，就像stroke()。
<script>
    var c=document.getElementById("myCanvas");
    var ctx=c.getContext("2d");
    ctx.moveTo(0,0);
    ctx.lineTo(200,100);
    ctx.stroke();
</script>
　　　　定义开始坐标(0,0), 和结束坐标 (200,100). 然后使用 stroke() 方法来绘制线条

**Canvas - 文本**
使用 canvas 绘制文本，重要的属性和方法如下：

　　font - 定义字体

　　fillText(text,x,y) - 在 canvas 上绘制实心的文本

　　strokeText(text,x,y) - 在 canvas 上绘制空心的文本

使用 fillText():
var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
ctx.font="30px Arial";
ctx.fillText("Hello World",10,50);
　　使用 "Arial" 字体在画布上绘制一个高 30px 的文字（实心）

**Canvas - 渐变**
渐变可以填充在矩形, 圆形, 线条, 文本等等, 各种形状可以自己定义不同的颜色。

以下有两种不同的方式来设置Canvas渐变：

　　createLinearGradient(x,y,x1,y1) - 创建线条渐变

　　createRadialGradient(x,y,r,x1,y1,r1) - 创建一个径向/圆渐变

当我们使用渐变对象，必须使用两种或两种以上的停止颜色。

addColorStop()方法指定颜色停止，参数使用坐标来描述，可以是0至1.

使用渐变，设置fillStyle或strokeStyle的值为渐变，然后绘制形状，如矩形，文本，或一条线。

var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");

// Create gradient
var grd=ctx.createLinearGradient(0,0,200,0);
grd.addColorStop(0,"red");
grd.addColorStop(1,"white");

// Fill with gradient
ctx.fillStyle=grd;
ctx.fillRect(10,10,150,80);
　　创建了一个线性渐变，使用渐变填充矩形

**Canvas - 图像**
　　把一幅图像放置到画布上, 使用 drawImage(image,x,y) 方法

var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
var img=document.getElementById("scream");
ctx.drawImage(img,10,10);
把一幅图像放置到了画布上