# Fetch

（socket也是交互的东西）

**IE不支持！**

**也是基于ES6的Promise**

**但它是原生js的实现，而不是xhr对象的实例**

**也遵循同源策略**


## GET

~~~js
fetch(`http://localhost:2333/login?name=${name}&password=${password}`, {
    method:'GET',
    //...
})
  .then((response) => {
    response.json(() => {
        
    })
      .then((res) => {//成功
        //do something...
    })
      .catch((res) => {//失败
        //do something...
    })
})

//或这样：
let url = 'http://localhost:2233/get';
fetch(url).then(res => res.json()).then((res) => {console.log(res)});//默认get
~~~

## POST

~~~js
fetch('http://localhost:2333/login', 
     {
    	method: 'POST',
    	headers: {
            'Content-Type':'application/x-www-form-urlencoded'
        },
    	body: `name=${name}&password=${password}`
})
  .then(response => response.json())
	.then(json => {
    	//do something...
	})
	.catch(error => {
    	//do something...
	})
})
~~~

上传文件：

~~~js
`<input type='file' id='fl' />`

let fd = new FormData();
fd.append('pic',fl.files[0])

fetch('http://localhost:2333/login', 
     {
    	method: 'POST',
    	body: fd
})
  .then(response => response.json())
	.then(json => {
    	//do something...
	})
	.catch(error => {
    	//do something...
	})
})
~~~


