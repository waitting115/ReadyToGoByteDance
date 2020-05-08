# Window sessionStorage 属性

## 实例

使用 sessionStorage 创建一个本地存储的 name/value 对，name="lastname" value="Smith", 然后检索 "lastname" 的值，并插入到 id="result" 的元素上:

~~~js
// 存储
sessionStorage.setItem("lastname", "Smith");
// 检索
document.getElementById("result").innerHTML = sessionStorage.getItem("lastname");
~~~

------

## 定义和使用

localStorage 和 sessionStorage 属性允许在浏览器中存储 key/value 对的数据。

sessionStorage 用于临时保存同一窗口(或标签页)的数据，**在关闭窗口或标签页之后将会删除这些数据。**

**提示:** 如果你想在浏览器窗口关闭后还保留数据，可以使用 localStorage 属性， 该数据对象没有过期时间，今天、下周、明年都能用，除非你手动去删除。

------

## 语法

```js
window.sessionStorage
```

保存数据语法：

```js
sessionStorage.setItem("key", "value");
```

读取数据语法：

```js
var lastname = sessionStorage.getItem("key");
```

删除指定键的数据语法：

```js
sessionStorage.removeItem("key");
```

删除所有数据：

```js
sessionStorage.clear();
```

------

## 技术细节

| 返回值: | 一个存储对象 |
| ------- | ------------ |
|         |              |

## 实例

以下实例用于记录点击按钮的次数:

~~~js
if (sessionStorage.clickcount) {
    sessionStorage.clickcount = Number(sessionStorage.clickcount) + 1;
} else {
    sessionStorage.clickcount = 1;
}
document.getElementById("result").innerHTML = "你在按钮上已经点击了 " +
sessionStorage.clickcount + " 次。";
~~~