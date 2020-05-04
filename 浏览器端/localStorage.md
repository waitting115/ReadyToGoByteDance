# Window localStorage 属性



## 实例

使用 localStorage 创建一个本地存储的 name/value 对，name="lastname" value="Smith", 然后检索 "lastname" 的值，并插入到 id="result" 的元素上

~~~js
// 存储 
localStorage.setItem("lastname", "Smith");

// 检索 
document.getElementById("result").innerHTML = localStorage.getItem("lastname");
~~~

------

## **定义和使用**

**localStorage 和 sessionStorage 属性允许在浏览器中存储 key/value 对的数据。**

**localStorage 用于长久保存整个网站的数据，保存的数据没有过期时间，直到手动去删除。**

**localStorage 属性是只读的。**

**提示: 如果你只想将数据保存在当前会话中，可以使用 [sessionStorage](https://www.runoob.com/jsref/prop-win-sessionstorage.html) 属性， 该数据对象临时保存同一窗口(或标签页)的数据，在关闭窗口或标签页之后将会删除这些数据。**

------

## 语法

```js
window.localStorage
```

保存数据语法：

```js
localStorage.setItem("key", "value");
```

读取数据语法：

```js
var lastname = localStorage.getItem("key");
```

删除数据语法：

```js
localStorage.removeItem("key");
```

------

## 技术细节

| 返回值: | 一个存储对象 |
| ------- | ------------ |
|         |              |

## 实例

以下实例用于记录点击按钮的次数:

~~~js
if (localStorage.clickcount) {
    localStorage.clickcount = Number(localStorage.clickcount) + 1;
} else {
    localStorage.clickcount = 1;
}
document.getElementById("result").innerHTML = "你在按钮上已经点击了 " +
localStorage.clickcount + "次。";
~~~

