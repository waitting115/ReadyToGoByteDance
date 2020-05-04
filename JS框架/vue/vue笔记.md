所有的vue组件都是vue实例。

## vue响应系统

当一个 Vue 实例被创建时，它将 `data` 对象中的所有的属性加入到 Vue 的**响应式系统**中。当这些属性的值发生改变时，视图将会产生“响应”，即匹配更新为新的值。

```js
// 我们的数据对象
var data = { a: 1 }

// 该对象被加入到一个 Vue 实例中
var vm = new Vue({
  data: data
})

// 获得这个实例上的属性
// 返回源数据中对应的字段
vm.a == data.a // => true

// 设置属性也会影响到原始数据
vm.a = 2
data.a // => 2

// ……反之亦然
data.a = 3
vm.a // => 3
```

当这些数据改变时，视图会进行重渲染。**值得注意的是只有当实例被创建时就已经存在于 `data` 中的属性才是`响应式`的**。也就是说如果你添加一个新的属性，比如：

```js
vm.b = 'hi'
```

**那么对 `b` 的改动将不会触发任何视图的更新。**如果你知道你会在晚些时候需要一个属性，但是一开始它为空或不存在，那么你仅需要**设置一些初始值**。比如：

```js
data: {
  newTodoText: '',
  visitCount: 0,
  hideCompletedTodos: false,
  todos: [],
  error: null
}
```

这里唯一的例外是使用 `Object.freeze()`，**这会阻止修改现有的属性**，也意味着响应系统无法再*追踪*变化。

```js
var obj = {
  foo: 'bar'
}

Object.freeze(obj)

new Vue({
  el: '#app',
  data: obj
})
<div id="app">
  <p>{{ foo }}</p>
  <!-- 这里的 `foo` 不会更新！ -->
  <button v-on:click="foo = 'baz'">Change it</button>
</div>
```

vue阻止冒泡

.stop

## 计算属性缓存 vs 方法

计算属性（computed）会缓存，适用于需要大量计算且重复使用的数据；

方法（methods）没有缓存，比较轻量级。

## 计算属性的setter

计算属性默认只有setter，但必要的时候也可以为它加上getter：

~~~js
computed: {
	fullName: {
		//getter
		get: function () {
			return this.firstName + " " + this.lastName
		},
		//setter
         set: function (newValue) {
			this.firstName = newValue;
         }
	}
}

//运行下面代码时setter会被调用，相关数据也会对应更新
vm.fullName = 'jignwei';
~~~

## v-for与v-if 的优先级

当它们处于同一个节点时，**v-for的优先级比v-if更高**，这意味着v-if将重复运行于每个v-for中。

但不推荐二者同时作用于同一个元素。



*