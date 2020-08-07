## GET

~~~js
let xhr = new XMLHttpRequest();

xhr.open('GET','请求路径?name=jingwei&age=18', true/false);

xhr.send();

//可选
xhr.onprogress = function () {
    console.log(this.readystate);//这里就是readystate === 3的情况
}

xhr.onload = function () {
    if(this.status === 200) {
        //dosomething...
    }
}
//&&
xhr.onreadystatechange = function () {
    if(this.status === 200 && this.readystate === 4) {
		//dosomething...
    }
}
~~~

## POST

~~~js
let xhr = new XMLHttpRequest();

xhr.open("POST",'请求路径', true/false);

xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded ');

xhr.send('name=jingwei&age=18');

//可选
xhr.onprogress = function () {
    console.log(this.readystate);//这里就是readystate === 3的情况
}

xhr.onload = function () {
    if(this.status === 200) {
        //dosomething...
    }
}
//&&
xhr.onreadystatechange = function () {
    if(this.status === 200 && this.readystate === 4) {
		//dosomething...
    }
}
~~~

## 二者的区别

POST比GET安全性更高，因为GET向后台传数据时会暴露给浏览器。

POST要有请求头：xhr.setRequestHeader('Content-type',' application/x-www-form-urlencoded  ');

## 状态码



// readyState 状态码：
				// 0：请求未初始化
				// 1：服务器连接已建立
				// 2：请求已接收
				// 3：请求处理中
				// 4：请求已完成，且响应已就绪
				

// http 状态码：
			// 200：服务器成功返回页面
			// 404：页面不存在
			// 502：服务器暂时不可用