## JS防抖

在进行窗口的resize、scroll，输入框内容校验等操作时，如果事件处理函数调用的频率无限制，会加重浏览器的负担，导致用户体验非常糟糕。此时我们可以采用debounce（防抖）和throttle（节流）的方式来减少调用频率，同时又不影响实际效果。 

 

### 函数防抖

函数防抖（debounce）：当持续触发事件时，一定时间段内没有再触发事件，事件处理函数才会执行一次，如果设定的时间到来之前，又一次触发了事件，就重新开始延时。如下图，持续触发scroll事件时，并不执行handle函数，当1000毫秒内没有触发scroll事件时，才会延时触发scroll事件。

![img](https://images2018.cnblogs.com/blog/1022151/201806/1022151-20180613144209623-862434090.jpg)

一起来实现个简单的debounce~

防抖debounce代码：

```
// 防抖
function debounce(fn, wait) {    
    var timeout = null;    
    return function() {        
        if(timeout !== null)   clearTimeout(timeout);        
        timeout = setTimeout(fn, wait);    
    }
}
// 处理函数
function handle() {    
    console.log(Math.random()); 
}
// 滚动事件
window.addEventListener('scroll', debounce(handle, 1000));
```

当持续触发scroll事件时，事件处理函数handle只在停止滚动1000毫秒之后才会调用一次，也就是说在持续触发scroll事件的过程中，事件处理函数handle一直没有执行。



---



在理解防抖之前，先来了解下出现抖动的场景:

开发搜索功能的时候，输入字符执行查询操作，network 中会瞬间出现无数的ajax请求
进行页面适配，调整窗口大小的时候适配不同的布局，如果适配业务复杂，浏览器可能出现卡顿现象
抖动：频繁触发而导致不可预测后果的现象。

防抖：防止在短时间内频繁触发，一段时间内只触发一次。

我们常常遇到的一些频繁触发的事件有：

输入框的 keyup / keydown
调整窗口大小的 resize
页面滚动的 scroll
鼠标滑动的 mousedown / mousemove
抖动现象
接下来以实现输入关键字搜索功能为例：

index.html代码如下:

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>debounce</title>
  </head>
  <body>
    <div>
      <input id="search" type="text" />
      <div id="result"></div>
      <script>
        let count = 0;
        let result = document.getElementById("result");
        let search = document.getElementById("search");
        search.addEventListener("keyup", onSearch);

        function onSearch() {
          console.log(this);
          result.innerHTML = count++;
          // TODO: 发送ajax请求
        }
      </script>
    </div>
  </body>
</html>
运行这个示例，我们可以看到，每次敲一个字符都会触发事件，对于简单的逻辑没有问题，但如果每次触发都发送ajax请求，那么这个页面就凉凉了，直接导致页面卡顿。为了解决这个问题，我们来写个防抖函数。

实现防抖
防抖的原理：尽管触发事件，让业务代码控制在 n 秒后才执行。

v0.1-基础功能
function debounce(func, wait = 1000) {
    let timeout;
    return function() {
        clearTimeout(timeout);
        timeout = setTimeout(func,wait);
    }
}
那么，调用的代码就改成如下：

let count = 0;
let result = document.getElementById("result");
let search = document.getElementById("search");
search.addEventListener("keyup", debounce(onSearch, 1000));
function onSearch() {
    console.log(this);
    result.innerHTML = count++;
}
此时，你会发现，在1s内，只会触发一次业务代码，暂时达到了效果。

v0.2-指向this和e
如果我们在onSearch函数中使用console.log(this)和 console.log(e)

在不使用 debounce 的情况下输出如下：

// this
<input id="search" type="text" />
// event
KeyboardEvent {isTrusted: true, key: "2", code: "Digit2", location: 0, ctrlKey: false, …}
加上 debounce 后的输出情况如下：

// this
Window {parent: Window, postMessage: ƒ, blur: ƒ, focus: ƒ, close: ƒ, …}
// event
undefined
显然后者的指向是有问题的，我们接下来做第二次改动:

function debounce(func, wait = 1000) {
    let timeout;
    return function() {
        let that = this; // 保存this指向
        let args = arguments; // 保存arguments
        clearTimeout(timeout);
        timeout = setTimeout(function() {
            func.apply(that, args); // 传递给func内部
        }, wait);
    };
}
此时，this 和 e 的指向就正确了！

扩展个知识点：我们也可以用call/bind来代替apply。

func.apply(that, args); // 所有参数都必须放在一个数组里面传进去
func.call(that, args);  // 参数是直接放进去的，通过逗号分割
func.bind(that, args)();// 和 call的效果一样
v0.3-立刻执行
如果希望立刻执行函数，然后等到停止触发n秒后，才可以重新触发执行，该如何执行？ 实际的业务开发中，笔者很少见到这种场景。我们可以增加immediate来控制。

function debounce(func, wait = 1000, immediate = false) {
    let timeout;
    return function() {
        let that = this;
        let args = arguments;
        if (timeout) clearTimeout(timeout);
        // 立刻执行
        if (immediate) {
            let rightNow = !timeout; // 通过该参数控制只执行一次
            timeout = setTimeout(function() {
                timeout = null;
            }, wait);
            if (rightNow) {
                func.apply(that, args);
            }
        } else {
            timeout = setTimeout(function() {
                func.apply(that, args);
            }, wait);
        }
    };
}
v0.4-取消执行
试想一下，如果设置了immediate为true，只有等n秒后才能能重新触发，现在希望能够取消防抖，让它能够立刻恢复执行。

function debounce(func, wait = 1000, immediate = false) {
    let timeout;
    let debounce = function() {
	    let that = this;
	    let args = arguments;
	    if (timeout) clearTimeout(timeout);
	    // 立刻执行
	    if (immediate) {
	        let rightNow = !timeout; // 通过该参数控制只执行一次
	        timeout = setTimeout(function() {
	            timeout = null;
	        }, wait);
	        if (rightNow) {
	            func.apply(that, args);
	        }
	    } else {
	        timeout = setTimeout(function() {
	            func.apply(that, args);
	        }, wait);
	    }
    };
    // 取消
    debounce.cancel = function() {
        clearTimeout(timeout);
        timeout = null;
    }
    return debounce;
}
接下来，修改下调用的地方:

let count = 0;
let result = document.getElementById("result");
let search = document.getElementById("search");
let cancel = document.getElementById("cancel");
let doSearch = debounce(onSearch, 10000, true)
search.addEventListener("keyup", doSearch); // 执行搜索
cancel.addEventListener("click", doSearch.cancel); // 取消搜索
function onSearch(e) {
    console.log(this);
    console.log(e);
    result.innerHTML = count++;
}
到此为止，我们已经逐步衍化出一个可以使用的防抖函数了，但是和lodash中的_.debounce还是有一定差距的，有兴趣深入的同学可以参考。