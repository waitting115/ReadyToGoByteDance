## String.fromCharCode()

通过一串Unicode创建字符。

~~~js
String.fromCharCode(189,43,290,61);
"½+Ģ="
~~~

## String.fromCodePoint()

通过一串`码点`创建字符串

~~~js
console.log(String.fromCodePoint(9731, 9733, 9842, 0x2F804));
//☃★♲你
~~~

## String.raw()-模板创字符

通过模板字符串创建字符串

~~~js
String.raw`Hi\n${2+3}!`;
// 'Hi\n5!'，Hi 后面的字符不是换行符，\ 和 n 是两个不同的字符

String.raw `Hi\u000A!`;             
// "Hi\u000A!"，同上，这里得到的会是 \、u、0、0、0、A 6个字符，
// 任何类型的转义形式都会失效，保留原样输出，不信你试试.length

let name = "Bob";
String.raw `Hi\n${name}!`;             
// "Hi\nBob!"，内插表达式还可以正常运行


// 正常情况下，你也许不需要将 String.raw() 当作函数调用。
// 但是为了模拟 `t${0}e${1}s${2}t` 你可以这样做:
String.raw({ raw: 'test' }, 0, 1, 2); // 't0e1s2t'
// 注意这个测试, 传入一个 string, 和一个类似数组的对象
// 下面这个函数和 `foo${2 + 3}bar${'Java' + 'Script'}baz` 是相等的.
String.raw({
  raw: ['foo', 'bar', 'baz'] 
}, 2 + 3, 'Java' + 'Script'); // 'foo5barJavaScriptbaz'
~~~

## charAt()-位置找字符

返回特定位置的字符。

## charCodeAt()-位置找编码

返回特定位置的字符的Unicode值。

## concat()-拼接

连接两个字符串文本并返回新文本。

## includes()-查找字符

判断字符串中是否有给定字符，第二个参数可以指定从哪开始查询。

## endsWith()-是否指定结尾

判断字符串是否是以给定字符结尾，返回布尔值。

## startsWith()-是否指定开头

 判断字符串的起始位置是否匹配其他字符串中的字符。 

~~~js
const str1 = 'Saturday night plans';

console.log(str1.startsWith('Sat'));
// expected output: true

console.log(str1.startsWith('Sat', 3));
// expected output: false
~~~



## indexOf()-找字符位置

返回给定字符在字符串中首次出现的位置，没找到返回-1。

## lastIndexOf()倒找字符

从后向前找给定字符，返回其位置 || -1。

## match()-正则比较

使用正则表达式与字符串相比较。

## padEnd()-尾部填充

在当前字符串尾部填充字符串，直到填充到指定数量，返回新串。

~~~~js
'abc'.padEnd(10);          // "abc       "
'abc'.padEnd(10, "foo");   // "abcfoofoof"
'abc'.padEnd(6, "123456"); // "abc123"
'abc'.padEnd(1);           // "abc"
~~~~

## padStart()-头部填充

在当前字符串头部填充字符串，直到填充到指定数量，返回新串。

## repeat()-重复

返回指定字符串重复n次的字符串副本。

~~~js
"abc".repeat(-1)     // RangeError: repeat count must be positive and less than inifinity
"abc".repeat(0)      // ""
"abc".repeat(1)      // "abc"
"abc".repeat(2)      // "abcabc"
"abc".repeat(3.5)    // "abcabcabc" 参数count将会被自动转换成整数.
"abc".repeat(1/0)    // RangeError: repeat count must be positive and less than inifinity
~~~

## replace()-替换

 返回一个由替换值（`replacement`）替换一些或所有匹配的模式（`pattern`）后的新字符串。 

~~~js
const p = 'The quick brown fox jumps over the lazy dog. If the dog reacted, was it really lazy?';

const regex = /dog/gi;

console.log(p.replace(regex, 'ferret'));
// expected output: "The quick brown fox jumps over the lazy ferret. If the ferret reacted, was it really lazy?"

console.log(p.replace('dog', 'monkey'));
// expected output: "The quick brown fox jumps over the lazy monkey. If the dog reacted, was it really lazy?"

~~~

## search()-正则找字符位置

 对正则表达式和指定字符串进行匹配搜索，返回第一个出现的匹配项的下标。 

~~~js
const paragraph = 'The quick brown fox jumps over the lazy dog. If the dog barked, was it really lazy?';

// any character that is not a word character or whitespace
const regex = /[^\w\s]/g;

console.log(paragraph.search(regex));
// expected output: 43

console.log(paragraph[paragraph.search(regex)]);
// expected output: "."
~~~

## slice()-截取字符串

截取字符串。

## split()-字符串转数组

字符串转数组。

## substring()-截取字符串

 返回在字符串中指定两个下标之间的字符。 

## toLowerCase()-str转小写

将字符串转换成小写并返回。

toLocaleLowerCase()，会根据当前地区设置将字符串转换成小写并返回。

## toUpperCase()-str转大写

将字符串转换成大写并返回。

toLocaleUpperCase()，会根据当前地区设置将字符串转换成大写并返回。

## toSource()-创建新对象

 返回一个对象文字代表着特定的对象。你可以使用这个返回值来创建新的对象。

## toString()-对象转字符串

返回用特定字符串表示的对象。

## trim()-去除首尾空格

去除字符串首尾空格。

## trimStart()-去除首空格

去除首空格。

## trimEnd()-去除尾空格

去除尾空格。

## valueOf()-对象原始值

返回特定对象的原始值。

## slice() && subString()

slice() 第一个参数代表开始位置,第二个参数代表结束位置的下一个位置,截取出来的字符串的长度为第二个参数与第一个参数之间的差;若参数值为负数,则将该值加上字符串长度后转为正值;若第一个参数等于大于第二个参数,则返回空字符串.

substring() 第一个参数代表开始位置,第二个参数代表结束位置的下一个位置;若参数值为负数,则将该值转为0;两个参数中,取较小值作为开始位置,截取出来的字符串的长度为较大值与较小值之间的差.