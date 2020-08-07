

## 跨域的产生

原因：网络的同源策略

- 两个同源的网页之间才能进行数据交互

同源：

- 即两个网页的协议、域名、端口号都相同。

## 跨域

即非同源的两个网页进行数据交互。

## 手动解决跨域（JSONP）

jsonp与json毫无关系

由于src带有跨域性质，那么就可以利用src实现跨域通信。

步骤：

- 创建script标签
- 声明回调函数（如success）
- 设置script标签的src属性：
  - url+ ? + callback=success + & key=value  & key=value
  - 如：https://www.baidu.com?callback=success&name=jingwei&age=18
- 将script标签追加到页面中

ajax部分正常请求即可。

注意事项：

- callback必须写在最前面
- jsonp只能使用GET方式提交请求，因此安全性不够高



## jsonp实现原理

新创建的script标签会去src里面的域名，带着回调函数，然后回调函数会在该域名的服务器执行一遍，也就会将回调函数的参数找到并传进去，然后返回到前端，（src带着回调函数溜达一圈，回调函数盆满钵满的回来）

