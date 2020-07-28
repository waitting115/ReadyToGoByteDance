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