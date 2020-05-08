所谓代码，当你随便命名一个变量：var name = "ukerxi"; 就是一句代码；但当你的代码写出来后，对于后续维护及阅读的人，就可以看出代码是否，易读，易理解；优雅的代码总是遵守一定的规范，这篇文章就说说几种命名空间的运用，运用好了，可以**有利于多人开发，模块化代码，代码解耦**有一定的作用！

先来看看一个错误的示范：

```js
// 定义一些数据
var name = "ukerxi";
var version = "1.0.0";
var hobby = "riding";
sessionStorage.data = 'test';
sessionStorage.text = 'test';
sessionStorage.name = 'test';
```



**利用命名空间进行重构：**

```js
var men = {
  name: 'ukerxi',
  version: '1.0.0',
  hobby: 'riding'
}
// 由于 sessionStorage 只支持字符型的数据，所以必须将数据进行JSON化处理 
sessionStorage.message = JSON.stringify({
  data: 'test',
  text: 'test',
  name: 'test',
});
```

重构后只产生了两个全局变量，**减少了命名冲突的概率**，对于全局变量的使用，必须尽量减少，特别是单页面开始时；而sessionStorage等本地存储的使用更加注意，由于它们在多页面之间可以互相访问，很容易造成命名冲突；一下更具体的介绍几种命名空间的使用：

#  命名空间的使用方式

## 一、使用单例对象进行包裹（单例模式）

```js
var myObj = {
    name: "ukerxi"
};
myObj.fn = function{
   // do something
};
```

**多人开发时，可以根据自己的名字进行命名一个对象**，然后自己写的变量及函数都放进这个命名空间中，这样就避免与他人冲突；在实际开发时，还会遇到多个文件之间共享一个命名空间，可以使用**命名空间检测**，避免重复或覆盖；例如：



```js
// 方法一
if (typeof myObj === "undefined") {
    var myObj = {};
}

// 方法二（推荐）
var myObj = myObj || {};
```



当你只是想对一个命名空间进行扩展，形如，对插件进行扩展，在JQuery.extend() 方法的扩展；

```js
var myObj=(function(o){
        o.addFn = ""; // 实现扩展属性功能
        return  o;
})(window.myObj || {});  // 必须或上空对象，以便在没有事先声明而使用引起的错误
```

 

## 二、闭包实现变量的包裹，减少全局变量



```js
var myObj = (function () {
    // 私有变量
    var name = "ukerxi";

    // 返回get方法
    return {
        getName: function () {
            return name;
        }
    };
}());

// 可以变通成更加通用的命名空间
var commonObject = (function(){
    var _data = {
        name: "ukerxi",
        version: "1.0"
    }
    //返回get/set方法
    return {
        get: function(key){
            return _data[key];
        },
        set: function(key, value){
            // 检测是否存在要设置的值在不在原本的数据中，这样做是不添加新数据
            //如果要添加新数据，这句可以不加
            if(typeof _data[key] != "undefined"){
                _data[key] = value;
            }else{
                return false;
            }
        }
    }
}());
```

通过返回的getter、setter方法进行读取及设置值，这样就不用担心变量命名冲突；

 

## 三、使用构造函数&原型方法，可以构造出更加强大的命名空间

```js
function CommonObj(name) {
    // 私有变量
    // 此处可以不用定义内部的_name变量，因为参数也是私有变量
    var _name = name;
    // 公用方法
    this.getName = function () {
        return _name;
    };
}
CommonObj.prototype = (function () {
    //静态私有变量，所有实例方法共享这个数据
    var _static = "static";
    // 
    return {
        getStatic: function () {
            return _static;
        }
    };
}());

var newObj1 = new CommonObj("obj1");
var newObj2 = new CommonObj("obj2");
// 测试输出
console.log(newObj1.getName()); // -- "obj1"
console.log(newObj2.getName()); // -- "obj2"
console.log(newObj1.getStatic); // -- "static"
console.log(newObj2.getStatic); // -- "static"
```



最终每次实例化 CommonObj 构造函数时可以进行赋值，然而原型上的属性是共享的；上面代码看起来还是分散的，可以进一步进行改进；

```js
var CommonObj = (function () {
    // 静态私有变量
    var _static = "";
    function _fn(name) {
         // 公用方法
        this.getName = function () {
            return name;
        };
    }
    
    // 定义原型方法
    _fn.prototype ={
         getStatic: function () {
            return _static;
        }
    }
    // 返回真正的构造函数
    return _fn;
   
}());


var newObj1 = new CommonObj();
var newObj2 = new CommonObj();
// 测试输出
console.log(newObj1.getName()); // -- "obj1"
console.log(newObj2.getName()); // -- "obj2"
console.log(newObj1.getStatic); // -- "static"
console.log(newObj2.getStatic); // -- "static"
```

 

## 四、函数的静态调用与非静态调用

主要特性是，通过类自身定义的属性是不会被实例化函数拥有及继承的，故也可以看做是一种命名空间，后续结合设计模式的“建造者”模式，可以创造出强大的一种运用形式；

```js
// 构造函数
var MyObj = function (name) {
    this.name = name;
};

// 静态方法
MyObj.getName = function () {
    console.log("test");
};

// 原型方法
MyObj.prototype.getName = function () {
    console.log(this.name);
};

// 静态调用
MyObj.getName(); // "test"
// 实例化调用
var fn = new MyObj("ukerxi");
fn.getName(); // ukerxi
一种特别的自执行方式形成的命名
```

 

## 五、最后再介绍一种特别的自执行方式形成的命名空间

```js
var CommonObj = ({
    fn: function() {console.log("test");},
    name: "ukerxi",
    init: function(){
        console.log(this.name);
        return this;
    }
}).init();
```

上面例子相当于新建一个对象，然后执行 init方法，最后返回 this 指向这个对象，最后CommonObj 就包含了新建对象的引用；当然实际运用中不建议这样用，只是用来装大神的；