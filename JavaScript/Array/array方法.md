# Array——方法

## Array.from()

从类数组对象或者可迭代对象中创建一个新的，浅拷贝的数组实例。

~~~js
console.log(Array.from('foo'));//['f','o','o']

console.log(Array.from([1,2,3], x => x + x);//[2,4,6]
~~~

语法：

Array.from(arrayLike, mapFn, thisArg);

（伪数组，新数组每个元素都会执行一遍该函数（可选），执行回调函数mapFn时的this对象（可选））

## Array.isArray()

用来判断某个变量是否是一个数组对象。

## Array.of()

根据一组参数来创建新的数组实例，支持任意的参数数量和类型。



# Array——修改器方法

下面这些方法会改变其自身的值。

## .copyWithin()

 **该方法浅复制数组的一部分到同一数组中的另一个位置，并返回它，不会改变原数组的长度。** 

语法：

**arr.copyWithin(target, start, end);**

## .fill()

 **fill()**方法用一个固定值填充一个数组中从起始索引到终止索引内的全部元素。不包括终止索引。 

~~~js
const array1 = [1, 2, 3, 4];

// fill with 0 from position 2 until position 4
console.log(array1.fill(0, 2, 4));
// expected output: [1, 2, 0, 0]

// fill with 5 from position 1
console.log(array1.fill(5, 1));
// expected output: [1, 5, 5, 5]

console.log(array1.fill(6));
// expected output: [6, 6, 6, 6]
~~~

**语法：**

fill(value, start, end);

**参数：**

value

用来填充数组元素的值。

`start` 可选

起始索引，默认值为0。

`end` 可选

终止索引，默认值为 `this.length`。

## .pop()

删除数组中最后一个元素并返回。

## .push()

在数组末尾添加新元素，并返回数组新长度。

## .reverse()

反转数组。

## .shift()

## .unshift()

在数组开头添加新元素，并返回数组新长度。

## .sort()

对数组进行排序。

## .splice()

在任意位置给数组添加或删除元素。

语法：

arr.splice(start, deleteCount, add1, add2, ...);

# Array——访问方法

此类型方法不会改变原数组的值，只会返回一个新数组。

## concat()

合并多个数组。

语法：

var array = arr1.concat(value1, value2, ..., valueN);value可以是值也可以是数组

## includes()

判断当前数组是否包含某指定值，若包含返回true，不包含返回false。

## join()

数组转字符串。

## slice()

数组切片。

语法：

slice(start, end);  end可选

## toString()

 返回一个由所有数组元素组合而成的字符串。遮蔽了原型链上的 [`Object.prototype.toString()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/toString) 方法。 

## toLocaleString()

 返回一个由所有数组元素组合而成的本地化后的字符串。遮蔽了原型链上的 [`Object.prototype.toLocaleString()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/toLocaleString) 方法。 

## indexOf()

在数组中寻找一个元素，若找到了返回其索引，若没找到则返回-1。

# Array——迭代方法

 在下面的众多遍历方法中，有很多方法都需要指定一个回调函数作为参数。在每一个数组元素都分别执行完回调函数之前，数组的length属性会被缓存在某个地方，所以，如果你在回调函数中为当前数组添加了新的元素，那么那些新添加的元素是不会被遍历到的。此外，如果在回调函数中对当前数组进行了其它修改，比如改变某个元素的值或者删掉某个元素，那么随后的遍历操作可能会受到未预期的影响。总之，**不要尝试在遍历过程中对原数组进行任何修改，虽然规范对这样的操作进行了详细的定义，但为了可读性和可维护性，请不要这样做。** 

## forEach()

为数组每一个元素执行一次回调函数。

## entries()

这是一个实验性的API。

 **entries()**方法返回一个新的**Array Iterator**对象，该对象包含数组中每个索引的键/值对。 

~~~js
const array1 = ['a', 'b', 'c'];

const iterator1 = array1.entries();

console.log(iterator1.next().value);
// expected output: Array [0, "a"]

console.log(iterator1.next().value);
// expected output: Array [1, "b"]
~~~

## every()

如果数组中的所有元素都满足回调函数，则返回true，反之则返回false。

## some()

如果数组中至少有一个元素满足回调函数，就返回true，反之返回false。

## filter()

将所有在过滤函数中返回true的数组元素放到一个新数组中返回。

## find()

找到第一个满足回调函数的元素并返回，如果没找到返回undefined。

## findIndex()

找到第一个满足回调函数的元素并返回其索引，如果没找到返回-1。

## keys()

返回一个数组迭代器对象，该迭代器会包含所有数组元素的键。

## values()

返回一个数组迭代器对象，该迭代器会包含所有数组元素的值。

## map()

返回一个由回调函数返回值所组成的新数组。

## reduce()

 **从左到右**为每个数组元素执行一次回调函数，并把上次回调函数的返回值放在一个暂存器中传给下次回调函数，并返回最后一次回调函数的返回值。 

语法：

reduce(prev,  cur, index, arr);

## reduceRight()

**从右到左**为每个数组元素执行一次回调函数，并把上次回调函数的返回值放在一个暂存器中传给下次回调函数，并返回最后一次回调函数的返回值。 	

























*