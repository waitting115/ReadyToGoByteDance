# ajax

利用XMLHttpRequest对象建立连接并实现数据传输。

demo：

~~~js
let xhr = new XMLHttpRequest();

xhr.open('GET','https://baidu.com?name=jingwei%age=18',true);

xhr.send();

xhr.onreadystatechange = function () {
    if(xhr.readyState === 4 && xhr.status === 200) {
        //do something...
        //xhr.responseText
    }
}

&&
    
let xhr2 = new XMLHttpRequest();

xhr2.open("POST",'https://baidu.com',true);

xhr2.setRequestHeader('Content-Type','application/x-www-form-urlencoded');

xhr2.send('name=jingwei&age=18');

xhr2.onreadystatechange = function () {
    if(xhr2.readyState === 4 && xhr2.status === 200) {
        ///do something...
        //xhr.responseText
    }
}
~~~

优点：异步传输数据，不用刷新页面即可获得数据并更新到页面上，属于异步传输数据的元老，也是axios的原理

缺点：几个互相联系的ajax请求容易导致回调地狱。

# axios

**ajax + promise = axios**

promise是es6新增元素，也就是说不支持es6的也不会支持axios。

解决了ajax容易导致回调地狱的问题。

也是现在更优的选择。

简单大体实现（实际axios有很多功能）：

~~~js
let myAxios = function () {
    return new Promise ((resolve, reject) => {
        let xhr = new XMLHttpRequest();
        xhr.open('get',url,true);
        xhr.send();
        xhr.onreadystatechange = function () {
            if(xhr2.readyState === 4 && xhr2.status === 200) {
                resolve(this.responseText);
            } else {
                reject();
            }
        }
    })
}
~~~



demo：

~~~js
//先引入 axios，然后使用
axios.get('/user', {
    param: {
        name: 'jingwei',
        age: 18
    }
})
.then(function (response) {
    console.log(response);
})
.catch(function (error) {
    console.log(error);
});

&&
    
axios.post('/user', {
    firstName:'jing',
    lastName:'wei'
})
.then(function (response) {
    console.log(response);
})
.catch(function (error) {
    console.log(error);
});

//多并发
axios.all([axios1,axios2,...])
  .then(axios.spread(function (acct, perms) {
    // 两个请求现在都执行完成
  }));
~~~

优点：解决了ajax的多并发问题，并且使用更加方便，加上了默认值，拦截器，错误处理机制以及取消等功能api，整体更加完善，推荐使用。

缺点：需要安装或者引入外部文件（这也不算是大缺点），不能运行在不支持ES6的平台上面

# fetch

 [Fetch](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) 是一个现代的概念, 等同于 [XMLHttpRequest](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest)。它提供了许多与XMLHttpRequest相同的功能，但被设计成更具可扩展性和高效性。本文介绍了 Fetch API的一些基本概念。 

fetch是一种HTTP数据请求方式，是xhr的一种替代方案，fetch不是ajax的进一步封装，而是原生js。fetch函数就是原生js，没有使用xhr对象。

 特点
1、第一个参数是URL:
2、第二个是可选参数，可以控制不同配置的 init 对象
3、使用了 [JavaScript Promises](https://links.jianshu.com/go?to=https%3A%2F%2Fdavidwalsh.name%2Fpromises) 来处理结果/回调: 

demo：

~~~js
fetch('https://www.baidu.com')
  .then((response) => {
    console.log(response);
})
  .catch((error) => {
    console.log(error);
})
~~~

优点：是js原生方法，即写即用；方便简洁；

缺点：在服务器返回4xx、5xx状态码时不会抛出错误，需要手动添加；默认不会接收或发送cookie。