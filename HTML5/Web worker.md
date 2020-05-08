# Web Worker

教程： http://www.ruanyifeng.com/blog/2018/07/web-worker.html 

当在 HTML 页面中执行脚本时，页面的状态是不可响应的，直到脚本已完成。

　　**web worker 是运行在后台的 JavaScript，独立于其他脚本，不会影响页面的性能。您可以继续做任何愿意做的事情：点击、选取内容等等，而此时 web worker 在后台运行。**

　　首先检测浏览器是否支持 Web Worker

```js
`if``(``typeof``(Worker)!==``"undefined"``){`` ``// 是的! Web worker 支持!`` ``// 一些代码.....`` ``}``else``{`` ``// //抱歉! Web Worker 不支持`` ``}`
```

　　下面的代码检测是否存在 worker，如果不存在，- 它会创建一个新的 web worker 对象，然后运行 "demo_workers.js" 中的代码

```js
`if``(``typeof``(w)==``"undefined"``)`` ``{`` ``w=``new` `Worker(``"demo_workers.js"``);`` ``}`
```

 

　　然后我们就可以从 web worker 发送和接收消息了。向 web worker 添加一个 "onmessage" 事件监听器：

```js
`w.onmessage=``function``(event){``document.getElementById(``"result"``).innerHTML=event.data;``};`
```

　　当 web worker 传递消息时，会执行事件监听器中的代码。event.data 中存有来自 event.data 的数据。当我们创建 web worker 对象后，它会继续监听消息（即使在外部脚本完成之后）直到其被终止为止。

如需终止 web worker，并释放浏览器/计算机资源，使用 terminate() 方法。

## 　完整的 Web Worker 实例代码

```html
<!DOCTYPE html>
<html>
<body>

<p>Count numbers: <output id="result"></output></p>
<button onclick="startWorker()">Start Worker</button> 
<button onclick="stopWorker()">Stop Worker</button>
<br><br>

<script>
var w;

function startWorker()
{
if(typeof(Worker)!=="undefined")
{
  if(typeof(w)=="undefined")
    {
    w=new Worker("demo_workers.js");
    }
  w.onmessage = function (event) {
    document.getElementById("result").innerHTML=event.data;
  };
}
else
{
document.getElementById("result").innerHTML="Sorry, your browser does not support Web Workers...";
}
}

function stopWorker()
{ 
w.terminate();
}
</script>

</body>
</html>
```



　　创建的计数脚本，该脚本存储于 "demo_workers.js" 文件中

```js
var i=0;

 function timedCount()
 {
 i=i+1;
 postMessage(i);
 setTimeout("timedCount()",500);
 }

 timedCount(); 
```