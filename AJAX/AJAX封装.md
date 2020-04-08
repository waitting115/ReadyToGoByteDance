~~~js
function ajax (method, url, data, success) {
    let xhr = new XMLHttpRequest();
    
    if(method === 'GET' || method === 'get') {
        if(data) {
			url += '?';
             url += data;
        }
        xhr.open('get', url, true);
        xhr.send();
    } else if (method === 'POST' || method === 'post') {
        xhr.open('post', url, true);
        if(data) {
            xhr.send(data);
        } else {
            xhr.send();
        }
    } else {
        throw new Error('method is not defined!');
    }
    
    xhr.onreadystatechange = function () {
        if(this.status === 200 && this.readystate === 4) {
            if(success) {
                success(this.responseText);
            }
            return this.responseText;
        }
    }
}

let backData = ajax('get', './test1.php','name=jingwei&age=18', function (data) {
    //doSomething...
    console.log(data);
})
~~~

