# Map

Map 是 ES6 中新增的**数据结构**，Map 类似于对象，但普通对象的 key 必须是字符串或者数字，**而 Map 的 key 可以是任何数据类型...**
 Map 的用法和普通对象基本一致，先看一下它能用非字符串或者数字作为 key 的特性。



```js
const map = new Map();
const obj = {p: 'Hello World'};

map.set(obj, 'OK')
map.get(obj) // "OK"

map.has(obj) // true
map.delete(obj) // true
map.has(obj) // false
```

需要使用new Map()初始化一个实例，下面代码中set get has delete顾名即可思义（下文也会演示）。其中，map.set(obj, 'OK')就是用对象作为的 key （不光可以是对象，任何数据类型都可以），并且后面通过map.get(obj)正确获取了。

## Map 实例的属性和方法：

size：获取成员的数量
 set：设置成员 key 和 value
 get：获取成员属性值
 has：判断成员是否存在
 delete：删除成员
 clear：清空所有



```js
const map = new Map();
map.set('aaa', 100);
map.set('bbb', 200);

map.size // 2

map.get('aaa') // 100

map.has('aaa') // true

map.delete('aaa')
map.has('aaa') // false

map.clear()
```

## Map 实例的遍历方法：

- keys()：返回键名的遍历器。
- values()：返回键值的遍历器。
- entries()：返回所有成员的遍历器。
- forEach()：遍历 Map 的所有成员。
- const map = new Map();
- map.set('aaa', 100);
- map.set('bbb', 200);



```js
for (let key of map.keys()) {
  console.log(key);
}
// "aaa"
// "bbb"

for (let value of map.values()) {
  console.log(value);
}
// 100
// 200

for (let item of map.entries()) {
  console.log(item[0], item[1]);
}
// aaa 100
//...
```