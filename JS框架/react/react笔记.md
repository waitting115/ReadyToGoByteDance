## 三大框架对比

### angular

- 更新迭代差别很大（angular2与angular1基本上是两种框架）
- 很多东西都有限制，写死了（$http)

### vue

- 上手容易，学习曲线平滑
- MVVM框架
- 推荐插件（vue-resource、axios等）
- vue2.x+也使用了虚拟DOM

### react

- 更加专注于视图层
- 有虚拟DOM
- 性能高（因为js操作DOM时很消耗性能）
- 可以解决一些终端问题
- 但react能做的事并不多，更多的要依赖react的插件（react全家桶）



## React

主要文件：

- react.js ——核心js
- react-dom.js ——虚拟DOM
- bable.js ——解析jsx

react-dom：

​	ReactDOM.render(jsx组件，放到哪个元素里)

## React生命周期

react的生命周期都是基于组件的：

- componentWillMount（）—— 组件挂载前
- componentDidMount（）—— 组件挂载后
- componentWillUpdate（）—— 组件更新前
- componentDidUpdate（）—— 组件更新后
- componentWillUnmount（）——组件卸载前

## React阻止事件冒泡

首先，要知道再react中的合成事件和原生事件之间的区别。
 1、合成事件

在jsx中直接绑定的事件，如



```xml
<a ref="aaa" onClick={(e)=>this.handleClick(e)}>更新</a>
```

这里的handleClick事件就是合成事件

2、原生事件

通过js原生代码绑定的事件，如



```tsx
document.body.addEventListener('click',e=>{
// 通过e.target判断阻止冒泡
    if(e.target&&e.target.matches('a')){
return;
    }
console.log('body');
})
或
this.refs.update.addEventListener('click',e=>{
            console.log('update');
        });
```

3、阻止冒泡事件分三种情况

A、阻止合成事件间的冒泡，用e.stopPropagation();



```jsx
import React,{ Component } from 'react';
import ReactDOM,{findDOMNode} from 'react-dom';

class Counter extends Component{
constructor(props){
super(props);
this.state = {
count:0,
        }
    }

handleClick(e){
// 阻止合成事件间的冒泡
        e.stopPropagation();

this.setState({count:++this.state.count});
    }

testClick(){
console.log('test')
     }

render(){
return(
<div ref="test" onClick={()=>this.testClick()}>
<p>{this.state.count}</p>
<a ref="update" onClick={(e)=>this.handleClick(e)}>更新</a>
</div>
        )
    }
}

var div1 = document.getElementById('content');

ReactDOM.render(<Counter/>,div1,()=>{});
```

B、阻止原生事件与最外层document上的事件间的冒泡，用



```jsx
e.nativeEvent.stopImmediatePropagation();

import React,{ Component } from 'react';
import ReactDOM,{findDOMNode} from 'react-dom';

class Counter extends Component{
constructor(props){
super(props);

this.state = {
count:0,
        }
    }

handleClick(e){
// 阻止原生事件与最外层document上的事件间的冒泡
        e.nativeEvent.stopImmediatePropagation();

this.setState({count:++this.state.count});
    }

render(){
return(
<div ref="test">
<p>{this.state.count}</p>
<a ref="update" onClick={(e)=>this.handleClick(e)}>更新</a>
</div>
        )
    }

componentDidMount() {
document.addEventListener('click', () => {
console.log('document');
        });
    }
}

var div1 = document.getElementById('content');

ReactDOM.render(<Counter/>,div1,()=>{});
```

C、阻止合成事件与除最外层document上的原生事件上的冒泡，通过判断e.target来避免



```jsx
import React,{ Component } from 'react';
import ReactDOM,{findDOMNode} from 'react-dom';

class Counter extends Component{
constructor(props){
super(props);

this.state = {
count:0,
        }
    }

handleClick(e){
this.setState({count:++this.state.count});
    }
render(){
return(
<div ref="test">
<p>{this.state.count}</p>
<a ref="update" onClick={(e)=>this.handleClick(e)}>更新</a>
</div>
        )
    }

componentDidMount() {
document.body.addEventListener('click',e=>{
// 通过e.target判断阻止冒泡
            if(e.target&&e.target.matches('a')){
return;
            }
console.log('body');
        })
    }
}

var div1 = document.getElementById('content');

ReactDOM.render(<Counter/>,div1,()=>{});
```



作者：泡芙小姐110
链接：https://www.jianshu.com/p/e0894bd588f4
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

