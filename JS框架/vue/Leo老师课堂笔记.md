# 5.11

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

# 5.12

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

# 5.13



vue的计算属性



过滤器：

{{msg | capitalize}}

vue内置过滤器：

- capitalize-首字母变大写
- uppercase-所有英文字母变大写
- lowercase-所有英文字母变小写
-  currency-变成货币形式
  - currency '￥' '3'	传参
- pluralize-英文变复数
- debounce-延迟执行
- limitBy-截取数组
  - 第一个参数选几个
  - 第二个参数从哪开始截取
- filterBy-过滤（给出关键字）
  - 第一个参数过滤有它的：filterBy  "h" 	就过滤出包含h的所有值
- orderBy-排序
  - orderBy  1	升序
  - orderBy   -1   降序

自定义过滤器：

~~~js
Vue.filter('wei', function (val) {
	//...
	return val
})
~~~

自定义双向过滤器：

~~~
Vue.filter('meng', {
	read: function (val) {
		//do something...
		return val;
	},
	write: function (val) {
		//do something...
		return val;
	}
})
~~~























