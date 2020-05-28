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

# 5.14

## 实例方法：

$mount：就是el，但是没有el级别高

$data：就是data

$el：获取作用于原生的DOM对象（可以通过app.$el操作DOM）

$options：获取其私有属性，自定义属性，正常属性

$log()：快速看到vue里面data的所有值，但选不到具体的值也无法更改

$destroy：销毁该vue实例（该实例还存在，但是里面的东西都是null）



## track-by="$index"

允许渲染重复数据



## v-cloak

这个属性不会渲染出来，也就是说，它只在该元素渲染之前存在，那么我们就可以为他设置display:none;来防止vue还没加载完就渲染出来该模块然后vue加载完之后该模块瞬间变化导致的用户体验不佳。



## 自定义指令

单项数据绑定：

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="vue.min.js"></script>
    <style>
        .color {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
    <div id="app">
        <input type="text" v-model="color">
        <div class="color" v-color="color"></div>

    </div>

    <script>
        Vue.directive('color', function (color) {
            this.el.style.background = color;
        })
        let app = new Vue({
            el: '#app',
            data: {
                color: 'red'
            },
            methods: {
                
            }
        })
    </script>
</body>
</html>
~~~

双向数据绑定：

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="vue.min.js"></script>
    <style>
        .color {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
    <div id="app">
        <input type="text" v-model="color">
        <div class="color" v-color="color"></div>

    </div>

    <script>
        Vue.directive('color', {
            bind() {
                // 不接受参数，代表该自定义指令作用的元素初始的样子
                this.el.style.background = 'blue';
            },
            update(color) {
                //接收参数，监听该自定义指令作用的元素的改变
                this.el.style.background = color;
            }
        })
        let app = new Vue({
            el: '#app',
            data: {
                color: 'blue'
            },
            methods: {
                
            }
        })
    </script>
</body>
</html>
~~~

## 生命周期

~~~html
	<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="vue.min.js"></script>
    <style>

    </style>
</head>
<body>
    <div id="app">

    </div>

    <script>
        let app = new Vue({
            el: '#app',
            data: {
                msg:'wei'
            },
            methods: {
                
            },
            created() {
                console.log('实例已创建');
            },
            beforeCompile() {
                console.log('实例编译之前');
            },
            compiled() {
                console.log('实例编译之后');
            },
            ready() {
                //可以理解成window.onload
                console.log('数据已经渲染完成')
            },
            beforeDestroy() {
                console.log('vue实例销毁之前')
            },
            destroyed() {
                console.log('vue实例销毁之后')
            }
        })
        document.onclick = function () {
            app.$destroy();
        }
    </script>
</body>
</html>
~~~

## vue动画

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="vue.min.js"></script>
    <style>
        .trans {
            width: 100px;
            height: 100px;
            background-color: greenyellow;
        }
        .wei-transition {
            opacity: 1;
            transition: 1s;
            transform: translate(0,0);
        }
        .wei-leave {
            opacity: 0;
            transform: translateX(90px);
        }
        .wei-enter {
            opacity: 0;
            transform: translateY(90px);
        }
    </style>
</head>
<body>
    <div id="app">
        <button class="btn btn-default" @click='move'>动起来</button><br><br>
        <div class="trans" v-show="show" transition='wei'></div>
    </div>

    <script>
        let app = new Vue({
            el: '#app',
            data: {
                msg:'wei',
                show: true
            },
            methods: {
                move: function () {
                    this.show = !this.show;
                }
            }
        })
    </script>
</body>
</html>
~~~

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="vue.min.js"></script>
    <style>
        .trans {
            width: 100px;
            height: 100px;
            background-color: greenyellow;
        }
        .wei-transition {
            opacity: 1;
            transition: 1s;
            transform: translate(0,0);
        }
        .aaa {
            opacity: 0;
            transform: translateX(90px);
        }
        .bbb {
            opacity: 0;
            transform: translateY(90px);
        }
    </style>
</head>
<body>
    <div id="app">
        <button class="btn btn-default" @click='move'>动起来</button><br><br>
        <div class="trans" v-show="show" transition='wei'></div>
    </div>

    <script>
        let app = new Vue({
            el: '#app',
            data: {
                msg:'wei',
                show: true
            },
            methods: {
                move: function () {
                    this.show = !this.show;
                }
            },
            transitions: {//s
                'wei': {
                    enterClass: 'bbb',
                    leaveClass: 'aaa'
                }
            }
        })
    </script>
</body>
</html>
~~~

## 用animate.css框架实现动画

用bower下载：bower install animate.css

~~~html

~~~



# 5.28

路由  --SPA 单页web应用  （根据不同地址显示不同页面）

插件： vue-router















































