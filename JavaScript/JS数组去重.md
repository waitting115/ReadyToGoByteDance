JS数组去重是前端面试，常考察的一道题，你会几种方法呢？如果你有更好的方法，可以在文章下放评论区提供。
 接下来看看8种数组去重方法，每个方法都在一个块级作用域中，有方法和测试用例。方便你更好的测试和理解。

### 方法一 for嵌套for 使用splice去重更改原数组 正向遍历循环

> 遇到删掉 原数组递减1



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct (arr) {
        for(let i = 0; i < arr.length; i++) {
            for(let j = i + 1; j < arr.length ; j++) {
                if(arr[i] === arr[j]) {//===
                    arr.splice(j, 1)
                    j--;
                }
            }
        }
    } 
    
    distinct(arr)
    console.log(arr) // [1, "true", true, 15, false, undefined, null, NaN, NaN, "NaN", 0, "a", {…}, {…}]
}
```

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`，返回的是去重后的原数组。
- 缺点：不能过滤掉 `NaN`、`Object` (NaN !== NaN   {} !== {})

### 方法二 For嵌套for 使用splice去重更改原数组 逆向遍历循环

> 逆向循环遍历 遇到重复的直接删掉



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct (arr) {
        for(let i = arr.length; i > 0; i--) {
            for(let j = i - 1; j > -1 ; j--) {
                if(arr[i] === arr[j]) {
                    console.log(arr[j])
                    arr.splice(j, 1)
                }
            }
        }
    } 
    
    distinct(arr)
    console.log(arr) // [1, "true", true, 15, false, null, NaN, NaN, "NaN", 0, "a", {…}, {…}]
}
```

优缺点同方法一：

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`，返回的是去重后的原数组。
- 缺点：不能过滤掉 `NaN`、`Object` 

### 方法三：includes去重 返回新数组



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct (arr) {
        let newArr = []
        for(let i = 0; i < arr.length; i++) {
            if(!newArr.includes(arr[i])) {
                newArr.push(arr[i])
            } 
        }
        return newArr
    }
    
    console.log(distinct(arr)) // [1, "true", true, 15, false, undefined, null, NaN, "NaN", 0, "a", {…}, {…}]
}
```

优点是可以过滤重复的`NaN`了，但是返回的是个新数组，对比方法一二，该方法多消耗了一些存储空间。

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`、`NaN`，返回的是去重后的新数组。
- 缺点：不能过滤掉 `Object` （[1,1,2,3,NaN,{}].includes({})）

### 方法四 indexOf去重 返回新数组



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct (arr) {
        let newArr = []
        for(let i = 0; i < arr.length; i++) {
            if(newArr.indexOf(arr[i]) < 0) {
                newArr.push(arr[i])
            } 
        }
        return newArr
    }
    
    console.log(distinct(arr)) //  [1, "true", true, 15, false, undefined, null, NaN, NaN, "NaN", 0, "a", {…}, {…}]
}
```

该方法类比includes，不能过滤掉`NaN`。

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`，返回的是去重后的新数组。
- 缺点：不能过滤掉 `NaN`、`Object` 

### 方法五 利用对象的属性key唯一的特性去重！！！



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct(arr) {
        let obj = {}
        let newArr = []
        for(let i = 0; i < arr.length; i++) {
            if(!obj[arr[i]]){
                obj[arr[i]] = 1
                newArr.push(arr[i])
            }
        }
        return newArr
    }

    console.log(distinct(arr)) // [1, "true", 15, false, undefined, null, NaN, 0, "a", {…}]
}
```

**该方法不不仅可以过滤掉重复的`NaN`,还是可以过滤掉`Object`。**

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`、`NaN`、`Object`，返回的是去重后的原数组。
- 缺点：针对 NaN和'NaN', 对象的key会视为一个key，**区分不了NaN和'NaN'，以及true和'true'**。

### 方法六 利用ES6的Set数据结构的特性

> Set集合里的所有的元素都是唯一的



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct(arr) {
        return Array.from(new Set(arr))
    }

    console.log(distinct(arr)) // [1, "true", true, 15, false, undefined, null, NaN, "NaN", 0, "a", {…}, {…}]
}
```

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`、`NaN`，返回的是去重后的新数组。
- 缺点：**不能过滤重复的`Object`。**

### 方法七 利用ES6的Map数据结构的特性去重

可以以任何值为键的`对象`。

```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct(arr) {
        let map = new Map()
        let newArr = []
        for(let i = 0; i < arr.length; i++) {
            if(!map.has(arr[i])) {
                map.set(arr[i])
                newArr.push(arr[i])
            }
        }
        return newArr
    }

    console.log(distinct(arr)) // [1, "true", true, 15, false, undefined, null, NaN, "NaN", 0, "a", {…}, {…}]
}
```

- 优点：该方法可以顾虑到重复的 `String`、`Boolean`、 `Number`、`undefined`、`null`、`NaN`，返回的是去重后的新数组。
- 缺点：**不能过滤重复的`Object`。**

### 方法八 利用sort()去重



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct(arr) {
        let sortArr = arr.sort()
        let newArr = []
        for(let i = 1; i < sortArr.length; i++) {
            if(sortArr[i] !== sortArr[i-1]) {
                newArr.push(arr[i])
            }
        }
        return newArr
    }

    console.log(distinct(arr)) // [1, 15, NaN, NaN, "NaN", {…}, {…}, "a", false, null, "true", true, undefined]
}
```

该方法的缺陷很明显，针对`'true','true',true,true,undefined,undefined, null,null，NaN, NaN,0, 0,{},{}` **都不能很好的过滤去重**，不建议使用该方法去重。

### 方法九 reduce数组去重



```javascript
{
    let arr = [1,1,'true','true',true,true,15,15,false,false, undefined,undefined, null,null, NaN, NaN,'NaN','NaN', 0, 0, 'a', 'a',{},{}];
    function distinct(arr) {
        return arr.sort().reduce((init, current) => {
            if(init.length === 0 || init[init.length-1] !== current) {
                init.push(current);
            }
            return init;
        }, []);
    }

    console.log(distinct(arr)) // [0, 1, 15, NaN, NaN, "NaN", {…}, {…}, "a", false, null, "true", true, undefined]
}
```

该方法先对数组进行排序，在进行去重过滤，针对不能过滤重复的 `NaN`和`Object`。