## 5.11

### vue与jQuery

- vue  数据驱动  （angular也是）
- jQuery  事件驱动  

vue只能在ie8+才能兼容。

v-show：控制元素的display

### v-for

{{$index}}=>索引值

{{$key}}=>键值

item=>值

{{}}里面可以计算

### v-model

双向数据绑定

### v-on  /  @

如果需要传参，而且还要获取事件对象，那么：

~~~
@click='change(10,$event)'

change: function (arg, e) {
	console.log(arg, e);
} 
~~~

只渲染一次：

~~~
{{*msg}}
~~~

解析HTML：

~~~
{{{msg}}}
~~~

{{}} ==> innerText

{{{}}} ==> innerHTML (比较危险)



一旦使用vue的方法或命令，就不不用使用vue的模板{{}}了，原生的要用{{}}绑定属性。

### vue 操作属性：

v-bind:属性名="数据"  v-bind: => :

## nodejs

### npm install（安装包）

安装前端包管理器bower

npm install bower -g

bower 只是npm包管理器其中的一个

### npm info （查看包信息）

npm info vue

bower也有info方法

## bower i （安装包）

bower i  vue   （i是install的缩写）这样安装默认安装vue最高版本

如果想要指定版本的包：

bower i vue#1.0.8

## vue交互

vue1.x用插件vue-resource(也是采用的Promise)

vue2+弃用了vue-resource，采用axios。bower i axios





























