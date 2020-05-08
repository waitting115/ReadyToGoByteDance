# 函数节流

函数节流（throttle）：**当持续触发事件时，保证一定时间段内只调用一次事件处理函数。**节流通俗解释就比如我们水龙头放水，阀门一打开，水哗哗的往下流，秉着勤俭节约的优良传统美德，我们要把水龙头关小点，最好是如我们心意按照一定规律在某个时间间隔内一滴一滴的往下滴。如下图，持续触发scroll事件时，并不立即执行handle函数，每隔1000毫秒才会执行一次handle函数。

![img](https://images2018.cnblogs.com/blog/1022151/201806/1022151-20180613144342847-660853255.jpg)

函数节流主要有两种实现方法：时间戳和定时器。接下来分别用两种方法实现throttle~



## **节流throttle代码（时间戳）：**

```js
var throttle = function(func, delay) {            
　　var prev = Date.now();//为什么第一次会立即执行？因为这行代码绑定事件函数时就已经运行了   
　　return function() {                
　　　　var context = this;                
　　　　var args = arguments;                
　　　　var now = Date.now();//而这里是触发事件时才执行，一般二者间隔会大于delay（但不排除少数情况）         
　　　　if (now - prev >= delay) {                    
　　　　　　func.apply(context, args);                    
　　　　　　prev = Date.now();                
　　　　}            
　　}        
}        
function handle() {            
　　console.log(Math.random());        
}        
window.addEventListener('scroll', throttle(handle, 1000));
```

当高频事件触发时，**第一次会立即执行**（给scroll事件绑定函数与真正触发事件的间隔一般大于delay，如果你非要在网页加载1000毫秒以内就去滚动网页的话，我也没办法o(╥﹏╥)o），**而后再怎么频繁地触发事件，也都是每delay时间才执行一次**。而当最后一次事件触发完毕后，事件也不会再被执行了 （最后一次触发事件与倒数第二次触发事件的间隔小于delay，为什么小于呢？因为大于就不叫高频了呀(*╹▽╹*)）。

## 节流throttle代码（定时器）：

```js

// 节流throttle代码（定时器）：
var throttle = function(func, delay) {            
    var timer = null;            
    return function() {                
        var context = this;               
        var args = arguments;                
        if (!timer) {                    
            timer = setTimeout(function() {                        
                func.apply(context, args);                        
                timer = null;                    
            }, delay);                
        }            
    }        
}        
function handle() {            
    console.log(Math.random());        
}        
window.addEventListener('scroll', throttle(handle, 1000));
```

当触发事件的时候，我们设置一个定时器，再次触发事件的时候，如果定时器存在，就不执行，直到delay时间后，定时器执行执行函数，并且清空定时器，这样就可以设置下个定时器。**当第一次触发事件时，不会立即执行函数**，而是在delay秒后才执行。而后再怎么频繁触发事件，也都是每delay时间才执行一次。**当最后一次停止触发后，由于定时器的delay延迟，可能还会执行一次函数。**

节流中用时间戳或定时器都是可以的。**更精确地，可以用时间戳+定时器**，当第一次触发事件时马上执行事件处理函数，最后一次触发事件后也还会执行一次事件处理函数。

## 节流throttle代码（时间戳+定时器）：

```js
// 节流throttle代码（时间戳+定时器）：
var throttle = function(func, delay) {     
    var timer = null;     
    var startTime = Date.now();     
    return function() {             
        var curTime = Date.now();             
        var remaining = delay - (curTime - startTime);             
        var context = this;             
        var args = arguments;             
        clearTimeout(timer);              
        if (remaining <= 0) {                    
            func.apply(context, args);                    
            startTime = Date.now();              
        } else {                    
            timer = setTimeout(func, remaining);              
        }      
    }
}
function handle() {      
    console.log(Math.random());
} 
window.addEventListener('scroll', throttle(handle, 1000));
```

在节流函数内部使用开始时间startTime、当前时间curTime与delay来计算剩余时间remaining，当remaining<=0时表示该执行事件处理函数了（保证了第一次触发事件就能立即执行事件处理函数和每隔delay时间执行一次事件处理函数）。如果还没到时间的话就设定在remaining时间后再触发 （保证了最后一次触发事件后还能再执行一次事件处理函数）。当然在remaining这段时间中如果又一次触发事件，那么会取消当前的计时器，并重新计算一个remaining来判断当前状态。