webSocket是一项可以让服务器将数据主动推送给客户端的技术。前几天写了一个日志功能，日志数据需要实时更新。正好项目中有封装好的WebSocket组件，且接口支持webSocket，就用它实现了。也是第一次用，简单研究了一下，分享出来。

文章示例代码：[https://github.com/neroneroffy/webSocketDemo](https://link.zhihu.com/?target=https%3A//github.com/neroneroffy/webSocketDemo)

## **什么是WebSocket**

首先需要明白webSocket的概念，下边是维基百科的解释

> WebSocket是一种通信协议，可在单个TCP连接上进行全双工通信。WebSocket使得客户端和服务器之间的数据交换变得更加简单，允许服务端主动向客户端推送数据。在WebSocket API中，浏览器和服务器只需要完成一次握手，两者之间就可以建立持久性的连接，并进行双向数据传输。

首先，要明白WebSocket是一种通信协议，区别于HTTP协议，HTTP协议只能实现客户端请求，服务端响应的这种单项通信。
而WebSocket可以实现客户端与服务端的双向通讯，说白了，最大也是最明显的区别就是可以做到服务端主动将消息推送给客户端。


其余的特点有：

- 握手阶段采用 HTTP 协议。
- 数据格式轻量，性能开销小。客户端与服务端进行数据交换时，服务端到客户端的数据包头只有2到10字节，客户端到服务端需要加上另外4字节的掩码。HTTP每次都需要携带完整头部。
- 更好的二进制支持，可以发送文本，和二进制数据
- 没有同源限制，客户端可以与任意服务器通信
- 协议标识符是ws（如果加密，则是wss），请求的地址就是后端支持websocket的API。

## **几种与服务端实时通信的方法**

我们都知道，不使用WebSocket与服务器实时交互，一般有两种方法。AJAX轮询和Long Polling长轮询。

### **AJAX轮询**

AJAX轮询也就是定时发送请求，也就是普通的客户端与服务端通信过程，只不过是无限循环发送，这样，可以保证服务端一旦有最新消息，就可以被客户端获取。

### **Long Polling长轮询**

Long Polling长轮询是客户端和浏览器保持一个长连接，等服务端有消息返回，断开。
然后再重新连接，也是个循环的过程，无穷尽也。。。

客户端发起一个Long Polling，服务端如果没有数据要返回的话，
会hold住请求，等到有数据，就会返回给客户端。客户端又会再次发起一次Long Polling，再重复一次上面的过程。

### **缺点**

上边这两种方式都有个致命的弱点，开销太大，被动性。假设并发很高的话，这对服务端是个考验。
而WebSocket一次握手，持久连接，以及主动推送的特点可以解决上边的问题，又不至于损耗性能。

## **WebSocket连接过程**

客户端发起HTTP握手，告诉服务端进行WebSocket协议通讯，并告知WebSocket协议版本。服务端确认协议版本，升级为WebSocket协议。之后如果有数据需要推送，会主动推送给客户端。

连接开始时，客户端使用HTTP协议和服务端升级协议，升级完成后，后续数据交换遵循WebSocket协议。我们看看Request Headers

```json
Accept-Encoding: gzip, deflate, br
Accept-Language: zh,zh-TW;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6
Cache-Control: no-cache
Connection: Upgrade
Host: 127.0.0.1:3000
Origin: http://localhost:3000
Pragma: no-cache
Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits
Sec-WebSocket-Key: bwb9SFiJONXhQ/A4pLaXIg==
Sec-WebSocket-Version: 13
Upgrade: websocket
```

重点字段是这些：

- Connection: Upgrade 表示要升级协议
- Upgrade: websocket 要升级协议到websocket协议
- Sec-WebSocket-Version 表示websocket的版本。如果服务端不支持该版本，需要返回一个Sec-WebSocket-Versionheader，里面包含服务端支持的版本号。
- Sec-WebSocket-Key 对应服务端响应头的Sec-WebSocket-Accept，由于没有同源限制，websocket客户端可任意连接支持websocket的服务。这个就相当于一个钥匙一把锁，避免多余的，无意义的连接。

再看看看服务端响应的 Response Headers

```json
Connection: Upgrade
Sec-WebSocket-Accept: 2jrbCWSCPlzPtxarlGTp4Y8XD20=
Upgrade: websocket
```

关键是这个字段

- Sec-WebSocket-Accept: 用来告知服务器愿意发起一个websocket连接， 值根据客户端请求头的Sec-WebSocket-Key计算出来

## **WebSocket API**

客户端若想要与支持webScoket的服务器通信，可以使用WebSocket构造函数返回WebSocket对象。

```text
const ws = new WebSocket("ws://localhost:3000/websocket");
```

这样，客户端就会与服务端开始连接。

返回的实例对象的属性：

- WebSocket.onopen： 连接成功后的回调
- WebSocket.onclose： 连接关闭后的回调
- WebSocket.onerror： 连接失败后的回调
- WebSocket.onmessage： 客户端接收到服务端数据的回调
- webSocket.bufferedAmount： 未发送至服务器的二进制字节数
- WebSocket.binaryType： 使用二进制的数据类型连接
- WebSocket.protocol ： 服务器选择的下属协议
- WebSocket.url ： WebSocket 的绝对路径
- WebSocket.readyState： 当前连接状态，对应的四个常量

WebSocket.CONNECTING: 0

WebSocket.OPEN: 1

WebSocket.CLOSING: 2

WebSocket.CLOSED: 3

方法：

- WebSocket.close() 关闭当前连接
- WebSocket.send(data) 向服务器发送数据

## **示例**

讲了那么多概念以后，终于可以看看怎么用了。实现WebSocket通信，需要客户端和服务端配合。

自己写了一个例子，服务端在开始连接后，利用定时器主动向客户端发送随机数，客户端也可以发给服务器消息，然后服务器返回这条消息给客户端。客户端就是js+html，服务端用了express + express-ws来实现。


代码在这里：[https://github.com/neroneroffy/webSocketDemo](https://link.zhihu.com/?target=https%3A//github.com/neroneroffy/webSocketDemo) 可以clone下来，安装依赖，npm start运行看下效果。

![img](https://pic3.zhimg.com/v2-ee4939222b0343a58ece9c11eca20196_b.jpg)

### **客户端**

前端页面，最终效果如以上效果图：

```html
<body>
  <div class="websocket">
    <div class="receive">
      <p>服务端返回的消息</p>
      <div id="receive-box"></div>
    </div>
    <div class="send">
      <textarea type="text" id="msg-need-send"></textarea>
      <p>
        <button id="send-btn">点击发消息给服务端</button>
      </p>
    </div>
    <button id="exit">关闭连接</button>
  </div>
</body>
```

js，使用webSocket的代码都在这里。做的事情就是给页面的元素绑定事件。
然后创建WebSocket对象，监听对象的连接、接收消息、关闭等事件，将数据反馈到页面中

```js
const msgBox = document.getElementById("msg-need-send")
const sendBtn = document.getElementById("send-btn")
const exit = document.getElementById("exit")
const receiveBox = document.getElementById("receive-box")

// 创建一个webSocket对象
const ws = new WebSocket("ws://127.0.0.1:3000/websocket/test")
ws.onopen = e => {
  // 连接后监听
  console.log(`WebSocket 连接状态： ${ws.readyState}`)
}

ws.onmessage = data => {
  // 当服务端返回数据的时候，放到页面里
  receiveBox.innerHTML += `<p>${data.data}</p>`
  receiveBox.scrollTo({
    top: receiveBox.scrollHeight,
    behavior: "smooth"
  })
}

ws.onclose = data => {
  // 监听连接关闭
  console.log("WebSocket连接已关闭")
  console.log(data);
}

sendBtn.onclick = () => {
  // 点击发送按钮。将数据发送给服务端
  ws.send(msgBox.value)
}
exit.onclick = () => {
  // 客户端主动关闭连接
  ws.close()
}
```

### **服务端**

考虑到了模块化开发，没有直接把代码放到直接创建服务的文件中。而是使用了路由，给webSocket服务分配一个单独的接口

```js
const express = require("express");
const expressWs = require("express-ws")
const router = express.Router()
expressWs(router);

router.ws("/test", (ws, req) => {
  ws.send("连接成功")
  let interval
  // 连接成功后使用定时器定时向客户端发送数据，同时要注意定时器执行的时机，要在连接开启状态下才可以发送数据
  interval = setInterval(() => {
    if (ws.readyState === ws.OPEN) {
      ws.send(Math.random().toFixed(2))
    } else {
      clearInterval(interval)
    }
  }, 1000)
  // 监听客户端发来的数据，直接将信息原封不动返回回去
  ws.on("message", msg => {
    ws.send(msg)
  })
})


module.exports = router
```

最后看一下数据交互的过程

![img](https://pic3.zhimg.com/v2-9761138ce5848b9c81b83a1966a46762_b.webp)

## **总结**

上边简单实现了一个webSocket通信。实际的东西还有很多，比如webSocket扩展，心跳检测，数据加密，身份认证等知识点。但自己也需要再去研究，所以先不做介绍了。