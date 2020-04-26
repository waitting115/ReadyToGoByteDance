# Weh Storage

**使用HTML5可以在本地存储用户的浏览数据。**早些时候,本地存储使用的是cookies。但是Web 存储需要更加的安全与快速. 这些数据不会被保存在服务器上，但是这些数据只用于用户请求网站数据上.它也可以存储大量的数据，而不影响网站的性能。数据以 键/值 对存在, web网页的数据只允许该网页访问使用。

客户端存储数据的两个对象为：

- - localStorage - 没有时间限制的数据存储
  - sessionStorage - 针对一个 session 的数据存储, 当用户关闭浏览器窗口后，数据会被删除。

　　在使用 web 存储前,应检查浏览器是否支持 localStorage 和sessionStorage

```js
if(typeof(Storage)!=="undefined")
   {
   // 是的! 支持 localStorage  sessionStorage 对象!
   // 一些代码.....
   }
 else
   {
   // 抱歉! 不支持 web 存储。
   }
```

 

　　不管是 localStorage，还是 sessionStorage，其使用的API都相同，常用的有如下几个（以localStorage为例）：

- - 保存数据：localStorage.setItem(key,value);
  - 读取数据：localStorage.getItem(key);
  - 删除单个数据：localStorage.removeItem(key);
  - 删除所有数据：localStorage.clear();
  - 得到某个索引的key：localStorage.key(index);