# ES6中class类的全方面理解

JavaScript是基于原型的面向对象语言。

原型对象的特点就是将自身的对象共享给新对象。

如果要生成一个对象实例，需要先定义构造函数，然后通过new操作符来完成。

构造函数实例：

~~~js
function Person (name, age) {
    this.name = name;
    this.age = age;
}
Person.prototype.sayName = function (){
    return this.name;
}

let jignwei = new Person('jignwei',18);
jignwei.sayName();//jignwei
~~~

构造函数生成实例的过程：

> ```
> 1.当使用了构造函数，并且new 构造函数(),后台会隐式执行new Object()创建对象;
> 2.将构造函数的作用域给新对象，（即new Object()创建出的对象），而函数体内的this就代表new Object()出来的对象。
> 3.执行构造函数的代码。
> 4.返回新对象（后台直接返回）;
> ```

ES6引入了class的概念，通过class可以定义类。

该关键字的出现，使得其在对象的写法上更清晰，更像是一种面向对象的语言。

如果将之前的代码用ES6的方法写：

~~~js
class Person {//类
    constructor(name, age) {//构造方法
        this.name = name;
        this.age = age;
    }
    say() {//方法，千万不要写function
        return '我的名字叫：' + this.name + '我的年龄是：' + this.age;
    }
}
var jignwei = new Person('jingwei', 18);
jingwei.say();
~~~

**注意事项：**

> 1. 在类中声明方法时，千万不要写function关键字；
> 2. 方法之间不要用逗号分隔，否则会报错

由下面代码可以看出，**js里面的类实质上就是一个函数，类自身指向的就是构造函数**。

所以**在ES6中的类其实就是构造函数的另一种写法！**

~~~js
console.log(typeof Person);//function
console.log(Person == Person.prototype.constructor);//true
~~~

下面代码说明，构造函数的prototype属性，在ES6中依然存在着。

~~~js
console.log(Person.prototype);//{constructor: calss Person; say: f say()}
~~~

**实际上类的所有方法都定义在prototype属性上**，因为在上面一行代码中，say方法就在Person.prototype里面，如果在原型上定义同名say()，将会实现覆盖。

当然**你也可以通过prototype为类定义方法**：

~~~js
Person.prototype.sayAge = function () {
    return this.age;
}
let you = new Person ('zhangsan', 30);
console.log(you.sayAge());//30
~~~

**还可以用Object.assign方法来为对象动态增加方法**：

~~~js
Object.assign(Person.prototype,{
    getName: function (){
        return this.name;
    },
    getAge: function () {
        return this.age;
    }
})
let he = new Person('lisi', 20);
console.log(he.getName());//lisi
console.log(he.getAge());//20
~~~

**constructor方法是类的构造函数默认的方法，通过new命令生成对象实例时，自动调用该方法**：

~~~js
class Test {
    constructor () {
        console.log('test test test');//当实例化对象时，该方法自动执行
    }
}
let test = new Test();//test test test
~~~

**如果没有显示定义constructor方法，js会隐式生成一个constructor方法。**所以即使你没有添加构造方法，构造方法也是存在的，**它默认返回实例对象this**。但是也可以指定constructor方法返回一个全新的对象，让返回的实例对象不是该类的实例。

~~~js
class Again {

}
let again = new Again();
console.log(again);//Again {}
~~~



~~~js
class Desk {
    constructor () {
        console.log('我是Desk！');
    }
}
class Box {
    constructor () {
        return new Desk();//这里直接返回一个全新的对象
    }
}
let box = new Box();//我是Desk！
~~~

**constructor中定义的属性为实例属性（即定义在this对象上的），constructor外定义的属性为原型属性（即定义在class上的）。**

**hasOwnPrototype()函数用来判断该属性是否是实例属性，true为是，false为否。**

**in操作符会在能够通过实例对象访问到该属性时返回true，无论该属性在实例上还是在原型上。**

~~~js
//上面已经定义过Person类
let she = new Person('she', 16);
console.log(she.hasOwnProperty('name'));//true   name为实例属性
console.log(she.hasOwnProperty('age'));//true
console.log(she.hasOwnProperty('say'));//false  say不是实例属性
console.log('name' in she);//true	实例可以访问到实例属性
console.log('age' in she);//true
console.log('say' in she);//true   实例可以访问原型属性
console.log('shawanyi' in she);//false    实例和原型中都没有shawanyi属性
~~~

**类的所有实例共享一个原型对象，就是Person.prototype，所以它们的\_proto\_属性相等。**

~~~js
console.log(me.__proto__ == you.__proto__);//true  注意是两横
//都是Person类的实例
~~~

**也可以通过实例的\_proto\_属性为构造函数的原型添加属性，这样会改变原始class类，影响到所有实例，所以不推荐使用。**

~~~js
he.__proto__.add = function () {
    console.log('这是在he上面加到构造函数原型的方法');
}
he.add();//这是在he上面加到构造函数原型的方法
she.add();//这是在he上面加到构造函数原型的方法
me.add();//这是在he上面加到构造函数原型的方法
~~~

**class不存在变量提升，所以需要先定义再使用。**

因为ES6不会把类的声明提升到代码头部，但是ES5会这样做，它可以先使用后定义。

~~~js
let a = new A();
function A () {

}
let b = new B();//Uncaught ReferenceError: Cannot access 'B' before initialization at test.html:86
class B{

}
~~~





