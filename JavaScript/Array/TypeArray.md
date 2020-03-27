# TypeArray

##  1.起源

**TypedArray是一种通用的固定长度缓冲区类型，允许读取缓冲区中的二进制数据。**
**其在WEBGL规范中被引入用于解决Javascript处理二进制数据的问题。**
TypedArray已经被大部分现代浏览器支持（IE9及以下浏览器不支持），例如可以用下面方法创建TypedArray：

//创建一个8-byte的ArrayBuffer IE9及以下浏览器，不支持ArrayBuffer
**var b = new ArrayBuffer(8);**
//创建一个b的引用，类型是Uint8，其实位置在0，结束位置为缓冲区尾部
**var v1 = new Uint8Array(b);**
//创建一个b的引用，类型是Int32，其实位置在4，结束位置为缓冲区尾部
**var v2 = new Int32Array(b, 4);**
//创建一个b的引用，类型是Int16，其实位置在2，总长度为2
**var v3 = new Int16Array(b, 2, 2);**
console.info(b); // ArrayBuffer {}
console.info(v1);// [0, 0, 0, 0, 0, 0, 0, 0]
console.info(v2);// [0]
console.info(v3);// [0, 0]

则缓冲和创建的引用布局为：


变量索引



 
字节数


b =
0
1
2
3
4
5
6
7


 
索引数


v1 =
0
1


v2 =
 
0
1
2
3
4
5


v3 =
 
0
1
 



**这表示Int32类型的v1数组的第0个元素是ArrayBuffer类型的b的第0-3个字节**，如此等等。

## 2.构造函数

**上面我们通过ArrayBuffer来创建TypedArray，而实际上，TypedArray提供了3个构造函数来创建他的实例。**


构造函数

TypedArray(unsigned long length)创建一个新的TypedArray，length是其固定长度。


TypedArray(TypedArray array)TypedArray(type[] array)创建一个新的TypedArray，其每个元素根据array进行初始化，元素进行了相应的类型转换。


TypedArray(ArrayBuffer buffer, optional unsigned long byteOffset, optional unsigned long length)创建一个新的TypedArray，使其作为buffer的一个引用，byteOffset为其起始的偏移量，length为其长度。



所以通常我们用下面的方式创建TypedArray：

var array = new Uint8Array(10); //初始化空数组，默认值都是0

或者：

var array = new Uint8Array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);

## 3.数据操作

TypedArray提供了setter、getter、set和subarray四个方法进行数据操作。


方法

getter type get(unsigned long index)
返回指定索引的元素。



setter void set(unsigned long index, type value)
设置指定索引的元素为指定值。



void set(TypedArray array, optional unsigned long offset)void set(type[] array, optional unsigned long offset)
根据array设置值，offset为偏移位置。



TypedArray subarray(long begin, optional long end)
返回一个新的TypedArray，起始位为begin，结束位为end。




 代码实例：

var array = new Uint8Array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
console.info(array); //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
//获取元素
console.info(array[4]); //5
//设置元素
array[4] = 12;
console.info(array[4]); //12
//获取一个新的TypeArray
var array2 = array.subarray(0);
var array3 = array.subarray(1, 3);
console.info(array2);//[1, 2, 3, 4, 12, 6, 7, 8, 9, 10]
console.info(array3);//[2, 3]
//设置集合内容,指定偏移量
array3.set(new Uint8Array([0],0));
console.info(array3); //[0, 3]

## 4.数组类型


类型大小描述Web IDL类型C 类型



**Int8Array**
1
**8位有符号整数**
byte
signed char


**Uint8Array**
1
**8位无符号整数**
octet
unsigned char


**Uint8ClampedArray**
1
**8位无符号整数 (clamped)**
octet
unsigned char


**Int16Array**
2
**16位有符号整数**
short
short


**Uint16Array**
2
**16位无符号整数**
unsigned short
unsigned short


**Int32Array**
4
**32位有符号整数**
long
int


**Uint32Array**
4
**32位无符号整数**
unsigned long
unsigned int


**Float32Array**
4
**32位IEEE浮点数**
unrestricted float
float


**Float64Array**
8
**64位IEEE浮点数**
unrestricted double
double



玩过canvas的可能会觉得很眼熟。
因为ImageData中用于存储图像数据的数组便是Uint8ClampedArray类型的。

var canvas = document.getElementById(‘canvas1‘);
var context = canvas.getContext(‘2d‘);
context.fillStyle = ‘red‘;
context.fillRect(0, 0, canvas.width, canvas.height);
//var imageData = context.createImageData(100, 100); // 创建空数据Uint8ClampedArray
var imageData = context.getImageData(0, 0, 100, 100);// 获取上线文中的图片数据Uint8ClampedArray
console.info(imageData); //ImageData {data: Uint8ClampedArray[40000]}

## 5.为什么要用TypedArray

**存储图片**：

**我们知道Javascript中数字是64位浮点数。则对于一个二进制图片（图片每个像素点是以8位无符号整数存储的），如果要将其数据在Javascript数组中使用，相当于使用了图片8倍的内存来存储一个图片的数据，这显然是不科学的。而TypedArray能帮助我们只使用原来1/8的内存来存储图片数据。
或者对于WebSocket，如果用base64进行传输也是一个花费较高的方式，转而使用二进制传送可能是更好的方式。
当然，TypedArray还有更多好处，比如具有更好的性能** 