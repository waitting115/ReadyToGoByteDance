## 排序算法 √

### 十大排序算法分析

#### 图

#### 文章

这或许是东半球分析十大排序算法最好的一篇文章：https://www.cxyxiaowu.com/725.html

十大经典排序算法动画与解析，看我就够了！（配代码完全版）：https://www.cxyxiaowu.com/2026.html

#### 术语说明：

- **稳定**：如果a原本在b前面，而a = b，排序之后a仍在b的前面
- **不稳定**：如果a原本在b前面，而a = b，排序之后a有可能会出现在b的后面
- **内排序**：所有排序操作都在内存中完成
- **外排序**：由于数据太大，因此把数据放在磁盘中，而排序通过磁盘和内存的数据传输才能进行
- **时间复杂度**：一个算法执行所消耗的时间
- **空间复杂度**：运行玩一个程序所需内存的大小

#### 算法总结：

![img](https://pic4.zhimg.com/80/v2-bb4d0a4e62e92e3e9fcf71f4e9c6f91a_hd.jpg)

图片名次解释：

- n：数据规模
- k：“桶”的个数
- In-place：占用常数内存，不占用额外内存
- Out-place：占用额外内存

#### 算法分类

![img](https://pic3.zhimg.com/80/v2-eb7982279998a40e7056b2834553ced8_hd.jpg)

#### 比较和非比较的区别

常见的**快速排序**、**归并排序**、**堆排序**、**冒泡排序**等属于**比较排序**。

**在排序的最终结果里，元素之间的次序依赖于它们之间的比较。每个数都必须和其他数进行比较，才能确定自己的位置**。

在冒泡排序之类的排序中，问题规模为n，又因为需需要比较n次，所以平均时间复杂度为**O(n²)**。

在归并排序和快速排序之类的排序中，问题规模通过**分治法**消减为logN次，所以时间复杂度平均为**O(nlogn)**。

**比较排序的优势是**，适用于各种规模的数据，也不在乎数据的分布，都能进行排序。可以说，**比较排序适用于一切需要排序的情况**。

**基数排序**、**计数排序**、**桶排序**则属于**非比较排序**。

**非比较排序是通过确定每个元素之前，应该有多少个元素来排序。**针对数据arr，计算arr[i]之前有多少个元素，则唯一确定了arr[i]在排序后数组中的位置。

非比较排序只要确定每个元素之前的已有的元素个数即可，所有一次遍历即可解决。

算法时间复杂度**O(n)**。

**非比较排序时间复杂度低，但由于非比较排序需要占用空间来确定唯一位置。所以对数据规模和数据分布有一定的要求**。



### 冒泡排序

名字的由来：排序会使越小的元素会经过交换慢慢“浮”到数列的顶端。

算法描述：自己悟吧

动图演示：

![img](https://pic2.zhimg.com/50/v2-33a947c71ad62b254cab62e5364d2813_hd.webp)

代码实现

~~~js
let arr = [ 8, 4, 12, 20, 36, 88, 1, 8, 22, 18];
	//冒泡排序
	function bubbleSort(arr) {
		let len = arr.length;
		if(len == 0) {
			return arr;
		};
        for(let i = 0; i < len; i ++) {//循环len次
            for(let j = 0; j < len - i - 1; j ++) {//j<len - i - 1 (因为每个数都不和自己比较，自然会少一次，所以减一)
                if(arr[j + 1] < arr[j]) {
                    let temp = arr[j];
                    arr[j] = arr[j + 1]; 
                    arr[j + 1] = temp;
                }
            }
        }
		return arr;
	}

	console.log('冒泡排序升序排序结果：', bubbleSort(arr));
~~~

**算法分析**：

​	最佳情况：本身就是升序或降序的数组，则时间复杂度为O(n)

​	最差情况：未省下每一个比较和交换，则时间复杂度为O(n²)

​	平均情况：T(n) = O(n²)

​	稳定性：稳定（因为如果二者相同则一定不发生交换）

**适用场景：**

冒泡排序代码简单，思路简单，很适合小型数据排序。但是由于算法复杂度较高，在数据量大的时候不适用。

#### 冒泡优化

在数据完全有序的时候展现出最优时间复杂度，为O(n)。其他情况下几乎总是O(n²)。

所以要此算法不浪费时间，需要做一些改进，增加一个swap标志，当前一轮没有进行交换时，则说明此数组已经有序了，没必要再进行下一轮循环，可以直接退出。

~~~js
		let arr = [4, 2, 8, 8, 10, 18, 20, 22, 36, 88];
		//冒泡排序
		function bubbleSort(arr) {//数组，排序规则（升还是降）
			let len = arr.length;
			if(len == 0) {
				return arr;
			};
			let swap,a = 0;
			for(let i = 0; i < len; i ++) {//循环十次
				swap = false;
				for(let j = 1; j < len - i; j ++) {//俩俩比较
					a ++;
					if(arr[j] < arr[j - 1]) {
						let temp = arr[j];
						arr[j] = arr[j - 1]; 
						arr[j - 1] = temp;
						swap = true;
					}
				}
				if(swap == false) {//如果上一轮没有数据位置交换，则说明数组已经有序了，所以直接break
					break;
				}
			}
			console.log(a);
			return arr;
		}
		console.log('冒泡排序升序排序结果：', bubbleSort(arr, 'rise'));
~~~



### 选择排序

表现最稳定的排序算法之一（但它不稳定），因为**无论什么数据进去都是O(n²)的时间复杂度**，所以用到它的时候，**数据规模越小越好**。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。

动图演示：

![img](https://pic1.zhimg.com/50/v2-1c7e20f306ddc02eb4e3a50fa7817ff4_hd.webp)

代码实现：

~~~js
let arr = [ 8, 4, 12, 20, 36, 88, 1, 54, 22, 18];

function selectionSort (arr, rule) {
		let len = arr.length;
		if(len == 0) {
			return arr;
		};
		let location = 0;
		for(let i = 0; i < len; i ++) {
			//第一步，循环整个数组，找到最小的或者最大的，将它的位置标记下来，循环一遍完成后将最小的与第一个元素交换位置
			//第二步，循环除了第一个元素的余下数字，找到最小的或最大的，记下位置，循环完成后将最小的与第二个元素交换位置
			//。。。依次循环，直至完成
			location = i;//最小数字的位置
			for(let j = i; j < len; j ++) {
				if(arr[j] < arr[location]) {
					location = j;
				}
			}
			//循环一遍之后location保存的就是最小数字的位置，接下来将它与i位置的数字交换位置即可
			let temp = arr[location];
			arr[location] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}

	console.log('选择排序算法升序排序结果：', selectionSort(arr));
~~~

**算法分析：**

- 最佳情况 = 最差情况 = 平均情况 = O(n²)

- 稳定性：用数组实现的选择排序是不稳定的，用链表实现的选择排序是稳定的；不过一般提到排序算法时，大家往往默认是数组实现，所以选择排序是**不稳定**的。

**适用场景：**

选择排序实现也比较简单，并且由于各种情况下时间复杂度波动较小，因此**一般是优于冒泡排序的**。

但是，固有的O(n²)时间复杂度，选择排序在海量数据面前显得力不从心。因此，**它适用于简单的数据排序**。

### 插入排序

工作原理是通过构建有序序列，对于未排序的数据，在已排序序列中从后向前扫描，找到相应的位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序）。

动图演示：

![img](https://pic4.zhimg.com/50/v2-91b76e8e4dab9b0cad9a017d7dd431e2_hd.webp)

代码实现：

~~~js
//插入排序
function insertSort (arr) {
    if(arr.length == 0) {
        return arr;
    };
    //思路：第一次循环，把第一个元素当做一个有序数组，从第二个元素开始依次往里面插入元素，和数组最后一个元素比较，如果比它小，则调换位置，反之则不变
    for(let i = 1; i < arr.length; i ++) {//i为要比较的数字
        for(let j = i - 1; j >= 0; j --) {//j为i要依次比较的数字
            if(arr[i] < arr[j]) {//如果后者比前者小，就互换位置
                let temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i --;//既然互换位置了，那么要比较的数字就向前移动了一位，所以i此时也要移动一位
            } else {//如果if条件不成立，则说明到位置了，就没必要继续再比了
                break;
            }
        }
    }
    return arr;
}

console.log('插入排序算法升序排序结果：', insertSort(arr));
~~~

~~~java
//插入排序另一种思路（更优一点）
	function insertSort2 (arr) {
		if(arr.length == 0) {
			return arr;
		};
		let current;
		for(let i = 0; i < arr.length - 1; i ++) {// - 1
			current = arr[i + 1];//即要依次比较的数，先将值保存下来
			let preIndex = i; //即要被依次比较的数的下标
			while(preIndex >= 0 && current < arr[preIndex]) {//如果后<前
				arr[preIndex + 1] = arr[preIndex];//将被比较的数向后移一位，此时要比较的数在current变量中保存着
				preIndex --;//继续向前比较下一个
			}
			arr[preIndex + 1] = current;//当while循环条件不满足的时候，也就是说找到了比较数的位置，则将current移动到preindex的下一位。
		}
		return arr;
	}
	console.log('插入排序算法升序排序结果：', insertSort2(arr));
~~~

算法分析：

- 最佳情况：T(n) = O(n)
- 最坏情况：T(n) = （1 + 2 + 3 + 。。。 + n-1）即 n - 1 的累加（这里也有点问题）
- 官方最坏情况：T(n) = O(n²)
- 平均情况：T(n) = O(n²)   也就是最坏情况

**稳定性：**

​	由于只需要找到不大于当前数的位置而不需要发生交换，所以直接插入排序也是一种**稳定**的排序。

**适用场景：**

插入排序由于O(n²) 的复杂度，**在数组较大的时候不适用**。

但是，**在数据较少的时候，是一个不错的选择，一般作为快速排序的扩充**。

 例如，在STL的sort算法和stdlib的qsort算法中，都将插入排序作为快速排序的补充，用于少量元素的排序。又如，在JDK 7 java.util.Arrays所用的sort方法的实现中，当待排数组长度小于47时，会使用插入排序。 



#### 折半插入排序

插入排序的一种改良算法，就是找到要比较的数然后在此前面的数组中折半查找来找到它的位置。

直接上代码：

~~~js
	//折半插入排序
	function binaryInsertSort(arr) {
		//思路：大体思路和插入排序相同，就是后面为元素找位置的时候用折半查找
		let len = arr.length,
			temp = 0,
			low = 0,
			high = 0;
		for(let i = 1; i < len; i ++) {
			low = 0, high = i - 1,temp = arr[i];//temp为比较数
			while(low <= high) {//<=
				mid = parseInt((low + high) / 2);
				if(temp < arr[mid]) {
					high = mid - 1;
				} else {//<  =两种情况都这样处理
					low = mid + 1;
				}
			}
			for(let j = i - 1; j >= low; j --) {
				arr[j +1] = arr[j];
			}
			arr[low] = temp;
		}
		return arr;
	}

	console.log('折半插入排序算法升序序列结果：', binaryInsertSort(arr));
~~~



### 希尔排序

插入排序的改良版。

过程演示：

![img](https://pic3.zhimg.com/80/v2-241063fa07f1f3ecf0df0cf8b51e8ab8_hd.jpg)

代码演示：

~~~js
//希尔排序
	function hillSort (arr) {
		//思路：使用希尔增量，初始增量则为length/2,然后每次除以2，用希尔增量将原始数组分为若干个数组，然后将每个数组使用插入排序，依次类推
		let len = arr.length;
		if(len == 0) {
			return arr;
		};
		let hillNum = parseInt(len / 2);//希尔增量
		while(hillNum > 0) {
			for(let i = hillNum; i < len; i ++) {//i正好是第二个，直接将前一个比较就好了
				let temp = arr[i];//要比较的数字
				let preIndex = i - hillNum;//被比较的数的下标
				while(preIndex >= 0 && temp < arr[preIndex]) {
					arr[preIndex + hillNum] = arr[preIndex];
					preIndex -= hillNum;//继续找前一个
				}
				arr[preIndex + hillNum] = temp;
			}
			hillNum = parseInt(hillNum/2);
		}
		return arr;
	}

	console.log('希尔排序升序排序结果：', hillSort(arr));
~~~

算法分析：

- 最佳情况：O(nlog2n)
- 最坏情况：O(nlog2n)
- 平均情况：O(nlog2n)

**稳定性：**

我们都知道插入排序是稳定的排序算法，但是，Shell排序是一个多次插入的过程，在一次插入的过程中我们能保证不移动相同元素的顺序，但是在多次的插入中，相同的元素完全有可能在不同的插入轮次被移动，最后稳定性被破坏，因此，shell排序**不是一种稳定的排序算法**。

**适用场景：**

Shell排序虽然快，但毕竟是插入排序，其数量级并没有快排快。

**在大量的数据面前，Shell排序不是一个好的选择，但是中小型规模的数据完全可以用它。**

#### 与插入排序的关系

就是改良了的插入排序，插入排序更适用于数少的或者是本身就有点顺序的。

而希尔排序则在此基础上改良了一下，使之可以操作大量的数据排序，和毫无顺序的数组。



### 归并排序

和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(nlogn)的时间复杂度。代价是需要额外的内存空间。

该算法是采用**分治法**的一个非常典型的应用。

**分治法**可以理解为：把一片领土分解，分解为若干块小部分，然后一块一块的占领征服。

分治法精髓：

- 分——将问题分解为模块更小的子问题；
- 治——将这些规模更小的子问题逐个击破；
- 合——将已解决的子问题合并，最终得出母问题的解。

归并排序是以一种稳定的排序方法。

动图演示：

![img](https://pic2.zhimg.com/50/v2-cdda3f11c6efbc01577f5c29a9066772_hd.webp)

归并排序的流程：

![img](https://upload-images.jianshu.io/upload_images/7789414-b410a7c0fea50eba.png?imageMogr2/auto-orient/strip|imageView2/2/w/1141/format/webp)

合并两个有序数组的流程：

![img](https://upload-images.jianshu.io/upload_images/7789414-4b8f4cb3cb5f0a9f.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

另一种流程图：

![图片 1.png](https://pic.leetcode-cn.com/f6ce6814cfdd54942615711d1c3a2e90592dd53deff0a48bd5db537cc578261e-%E5%9B%BE%E7%89%87%201.png)

代码展示：

~~~js
	//归并排序
	function mergeSort (arr) {
		//思路：两步，第一步将数组一分为二，再将每一个一分为二。。。以此类推，最后分到每一部分只有一个数；第二步将两个部分排序，将另外两部分排序，将这两个有序的序列排序，以此类推，直到将整个数组排序
		if(arr.length < 2) {//分到每部分只有一个数
			return arr;
		}
		let mid = parseInt(arr.length / 2);
		let left = arr.slice(0, mid);
		let right = arr.slice(mid, arr.length);
		return merge(mergeSort(left), mergeSort(right));//递归
	}
	function merge(leftArr, rightArr) {
		let result = [];//一个空间复杂度就在这里
		let leftLen = leftArr.length;
		let rightLen = rightArr.length;
		let len = leftLen + rightLen;//总长度
		for(let index = 0, i = 0, j = 0; index < len; index ++) {//index为result数组的下标，i为leftArr数组的下标，j为rightArr数组的下标；用来将left、right两数组中的数字按顺序放到result中
            if(i >= leftLen) {//如果满足这个条件说明只剩下最后一个元素没有添加到result中了，而且这个元素就是rightArr中的最后一个元素，这里有个细节，要先判断是否剩下了最后一个元素，如果只剩最后一个了，另一个必然是空的，继续找leftArr[i]和rightArr[j]来比较就会出错，因为有一个会取undefined
				result[index] = rightArr[j ++];
			} else if (j >= rightLen) {
				result[index] = leftArr[i ++];
			} else if (leftArr[i] > rightArr[j]) {
				result[index] = rightArr[j ++];
			} else {
				result[index] = leftArr[i ++];
			}
		}
		return result;
	}

	console.log('归并排序算法升序排序结果：', mergeSort(arr));
~~~

**算法分析：**

- 最佳情况：T(n)=O(n)
- 最差情况：T(n)=O(nlogn)
- 平均情况：T(n)=O(nlogn)

**稳定性：**

因为我们在遇到相等的数据的时候必然是按顺序“抄写”到辅助数组上，所以，归并排序同样是**稳定**的算法。

**适用场景：**

归并排序在数据量较大的时候也有出色表现（效率上），但是由于其空间复杂度O(n)，使得它在面对数据量特别大的时候（例如，1千万数据）几乎不可接受。

而且，考虑到有些机器内存本来就小，因此，**采用归并排序的时候一定注意**。

### 堆排序

它是选择排序的一种，可以利用树的特点快速定位指定索引的元素，堆分为大顶堆和小顶堆，是完全二叉树。

二叉树延伸：

- 完全二叉树：除了最后一层之外的其他每一层都被完全填充，并且所有节点都保持向左对齐。
- ![img](https://user-gold-cdn.xitu.io/2018/3/24/1625739a03779fa3?w=312&h=227&f=png&s=2300)
- 完全二叉树有个特性：左边子节点的位置 = 当前父节点的两倍 +1，右边子节点的位置 = 当前父节点的两倍 + 2。
- ![img](https://user-gold-cdn.xitu.io/2018/3/24/1625739a05283914?w=390&h=261&f=png&s=96574)
- 满二叉树：除了叶子结点之外的每一个节点都有两个孩子，每一层（包含最后一层）都被完全填充。
- ![img](https://user-gold-cdn.xitu.io/2018/3/24/1625739a038d7d2b?w=316&h=219&f=png&s=33521)
- 完满二叉树：除了叶子结点之外的每一个节点都有两个孩子节点。
- ![img](https://user-gold-cdn.xitu.io/2018/3/24/1625739a039716c0?w=191&h=226&f=png&s=1894)

堆排序是指利用堆这种数据结构所设计的的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足堆的性质：即子节点的键值或索引总是小于（或者大于）它的父节点。

动图演示：

![img](https://pic2.zhimg.com/50/v2-7073c729230e1a2c3c3c9207b25f6b43_hd.webp)

代码实现：

~~~js
//堆排序
	function heapSort(arr, size) {
		//思路：首先从数组最后一个元素开始建堆，如果此元素没有子元素则直接跳过，找到有子元素的元素，然后放到创建大顶堆的函数里，然后继续向前找下一个元素放到函数里，直到该元素为数组第一个元素，执行完后此时数组为一个大顶堆，接下来将第一个元素和最后一个元素交换位置，然后节点总数减一，继续循环上面的步骤，直到节点总数为1的时候完成排序
        if(arr.length == 0) return arr;
        
		// let size = arr.length;
		if(size == undefined) {//只有首次调用才会触发此if
			size = arr.length;
		}
		for(let i = size - 1; i >= 0; i --) {
			heapify(arr, i, size);
		}
		//完成一次完完全全建堆之后，将数组首末互换，然后size - 1
		let temp = arr[0]; 
		arr[0] = arr[size - 1];
		arr[size - 1] = temp;

		size --;
		// console.log('size',size);
		if(size > 0) {
			heapSort(arr, size);
		}
		//当size=0的时候就说明原数组就剩下第一个元素了，自然是最小的，所以完成了堆排序
		return arr;
	}
	//建大顶堆的函数(只是把以currentRootNode为父节点的堆建立为大顶堆)
	function heapify(arr, currentRootNode, size) {//arr为完全二叉树；currentRootNode为父节点位置；size为节点总数
		// if(currentRootNode < size){};// if(currentRootNode < size){};//我看的原代码是这样做的，我改进了一下，因为叶子结点根本没必要做以下的操作，改进之后下面的代码执行次数从71-->33
		//这里我优先判断该父节点是否有左子树，如果有才继续执行
		if(2 * currentRootNode + 1 <= size - 1) {//size-1,currentRootNode为下标，size为长度
			//左子树和柚子树的位置
			let left = 2 * currentRootNode + 1;
			let right = 2 * currentRootNode + 2;

			//先把父节点看做是最大的存起来
			let max = currentRootNode;
			// console.log('cur',currentRootNode);
			// console.log(arr[currentRootNode]);
			// console.log(2 * currentRootNode + 2);
			// console.log(size - 1);

			//如果左、右子树比父节点大，记录它的位置max
			//记住这里一定要判断left、right与size的关系，因为有时候会将后面已经排好序的数组当做父节点的子节点拽进来继续操作，这是很要命的，别忘了自己就在这卡了好久！！！！
			if( left < size && arr[left] > arr[max]) {
				max = left;
			}
			if( right < size && arr[right] > arr[max]) {
				max = right;
			}

			//判断max是否有变化
			if(max != currentRootNode) {
				// console.log(arr[max]);
				// console.log(arr[currentRootNode]);
				let temp = arr[max];
				arr[max] = arr[currentRootNode];
				arr[currentRootNode] = temp;

				//既然发生变化了，那么就不能保证下面的节点是否还是大顶堆，所以要以max为父节点继续比较，直到完成一次建堆
				heapify(arr, max, size);
			}
		} else {//则说明该节点为叶子结点
			return;
		}
	}

	console.log('堆排序算法升序序列结果：', heapSort(arr));
~~~

干净代码：

~~~js
//堆排序
	function heapSort(arr, size) {
		if(size == undefined) {//只有首次调用才会触发此if
			size = arr.length;
		}
		for(let i = size - 1; i >= 0; i --) {
			heapify(arr, i, size);
		}
		//完成一次完完全全建堆之后，将数组首末互换，然后size - 1
		let temp = arr[0]; 
		arr[0] = arr[size - 1];
		arr[size - 1] = temp;

		size --;

		if(size > 0) {
			heapSort(arr, size);
		}
		//当size=0的时候就说明原数组就剩下第一个元素了，自然是最小的，所以完成了堆排序
		return arr;
	}
	//建大顶堆的函数(只是把以currentRootNode为父节点的堆建立为大顶堆)
	function heapify(arr, currentRootNode, size) {//arr为完全二叉树；currentRootNode为父节点位置；size为节点总数
		// if(currentRootNode < size){};//我看的原代码是这样做的，我改进了一下，因为叶子结点根本没必要做以下的操作，改进之后下面的代码执行次数从71-->33
		//这里我优先判断该父节点是否有左子树，如果有才继续执行
		if(2 * currentRootNode + 1 <= size - 1) {//size-1,currentRootNode为下标，size为长度
			//左子树和右子树的位置
			let left = 2 * currentRootNode + 1;
			let right = 2 * currentRootNode + 2;

			//先把父节点看做是最大的存起来
			let max = currentRootNode;

			//如果左、右子树比父节点大，记录它的位置max
			//记住这里一定要判断left、right与size的关系，因为有时候会将后面已经排好序的数组当做父节点的子节点拽进来继续操作，这是很要命的，别忘了自己就在这卡了好久！！！！
			if( left < size && arr[left] > arr[max]) {
				max = left;
			}
			if( right < size &&arr[right] > arr[max]) {
				max = right;
			}

			//判断max是否有变化
			if(max != currentRootNode) {
				let temp = arr[max];
				arr[max] = arr[currentRootNode];
				arr[currentRootNode] = temp;

				//既然发生变化了，那么就不能保证下面的节点是否还是大顶堆，所以要以max为父节点继续比较，直到完成一次建堆
				heapify(arr, max, size);
			}
		} else {//则说明该节点为叶子结点
			return;
		}
	}

	console.log('堆排序算法升序序列结果：', heapSort(arr));
~~~



算法分析：

 **最佳情况：T(n) = O(nlogn) **

**最差情况：T(n) = O(nlogn) **

**平均情况：T(n) = O(nlogn)** 

**稳定性：**

堆排序存在大量的筛选和移动的过程，属于**不稳定**的排序算法。

**适用场景：**

如果你需要 ’排序‘ ，那么绝大多数情况下应该使用快速排序。

但是如果你要在大数据中找几个TOP K（也就是最大、最小元素的前几名），此时快排就不适用了，而堆排序更适用此种场景；这种问题快排的时间复杂度为O(n)，而堆排序为O(N log K) 。

另外一个适用于堆排的场景是优先队列，需要在一组不停更新的数据中找最大、最小值，快排也不适合。

### 快速排序

**快速排序是一个知名度极高的一种排序算法，其对于大数据的优秀排序性能和相同复杂度的算法中相对简单的实现使它注定得到比其他排序算法更多的宠爱。**

算法描述：

- 从数列中挑出一个元素，称为“基准”；（一般都选第一个元素）
- 然后重新排序数列，将所有比基准小的放在基准前面，大的放在后面，相同的随便。在这个分区退出之后，该基准就处于数列的中间位置。这个成为分区操作。
- 递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。

动图演示：

![img](https://pic1.zhimg.com/50/v2-c411339b79f92499dcb7b5f304c826f4_hd.webp)

代码实现：

~~~js
let arr = [ 8, 4, 36, 20, 88, 12, 7, 1, 2, 8, 22, 18];
//快速排序
function fastSort(arr, low = 0 ,high = arr.length - 1) {
    if(arr.length == 0) return;
    //思路：看上面的链接
    //处理一下只有一个参数的情况
    if(low < high) {
        //寻找基准数据的索引位置，返回回来
        let index = getIndex(arr, low ,high);

        fastSort(arr, low, index - 1);
        fastSort(arr, index + 1, high);
    }

    return arr;
}
function getIndex(arr, low, high) {//寻找基准数据的索引位置
    let tmp = arr[low];//把基准的值保存下来
    while(low < high) {
        //先从后向前筛选high数据；当队尾的元素大于等于基准数据时，向前挪动hight指针
        while(low < high && arr[high] >= tmp) {
            high --;
        }
        //循环出来时的high位置的数字就是小于基准的数字，需要直接与low位置数据交换（此时low位置的数据已保存了）
        arr[low] = arr[high];
        //然后开始从前向后筛选low的数据
        while(low < high && arr[low] <= tmp) {
            low ++;
        }
        //循环出来时的low位置的数字就是大于基准的数字，需要直接与high位置数据交换
        arr[high] = arr[low]
    }
    //循环结束后的low和high相等，此时的low或high的位置就应该是基准的位置
    arr[low] = tmp;//将基准放在它该在的地方
    return low;//最后别忘了把基准的位置返回出去
}
console.log('快速排序算法升序序列结果：',fastSort(arr));
~~~

**算法分析：**

- 最佳情况： T(n) = O(nlogn) 
- 最差情况：T(n) = O(n2) 
- 平均情况：T(n) = O(nlogn) 

**稳定性：**

快速排序并不稳定，这是因为我们无法保证相等的数据会按顺序被扫描和按顺序存放。

**适用场景：**

**快速排序适用于大多数场景，尤其在数据量大的时候其优越性更明显。**

但是在必要的时候，需要考虑一下优化以提高其在最坏情况下的性能。

### 计数排序

计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。**作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。**

计数排序是一种**稳定**的排序算法。

计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。

它只能对整数进行排序。

**算法描述：**

- 找出待排序的数组中最大和最小的元素；
- 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
- 对所有的计数累加；
- 反向填充目标数组：将每个元素i放在新数组的第C(i)项，没放一个元素就将C（i）减去1.

动图演示：

![img](https://pic2.zhimg.com/50/v2-3c7ddb59df2d21b287e42a7b908409cb_hd.webp)

代码实现：

~~~js
	//计数排序
	function countSort(arr) {
		//思路：首先找到该数组A的最大最小值，并存下二者的差值+1；然后创建新数组B，数组B的长度就是差值 + 1，数组B每一项都设为0；然后循环A数组，存下每个数字减去min的值，然后将B数组【差值】位置上的数字++；整个循环结束后，循环B数组，存下每个下标加上min的值，判断此位置上的数组值是否为0，如果不为0，则将该数依次放到A数组，然后B数组该位置上的数字--，继续判断，如果为0，则循环B数组的下一位，直到B数组末尾。
        if(arr.length == 0) return arr;
        
		let max = arr[0],
			min = arr[0],
			dif = 0,
			len = arr.length,
			temp = 0;
		for(let i = 1; i < len; i ++) {
			if(arr[i] > max) {
				max = arr[i];
			}
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		dif = max - min + 1;//0~87需要88个位置来放

		//数组B
		// let array = new Array(dif);
		let array = [];
		for(let i = 0; i < dif; i ++) {//如果上面不+1，这里要<=
			array[i] = 0;
		}
		// console.log(array)
		//A-->B
		for(let i = 0; i < len; i ++) {
			temp = arr[i] - min;
			array[temp] ++;
		}
		//B-->A
		let arrayLen = array.length;
		let arrNum = 0;//表示A数组下标，从0开始填数字
		for(let i = 0; i < arrayLen; i ++) {
			while(array[i] > 0) {
				arr[arrNum] = i + min;
				arrNum ++;
				array[i] --;
			}
		}
		return arr;
	}
	console.log('计数排序算法升序序列结果：', countSort(arr));
~~~

**算法分析：**

**当输入的元素是n个0到k之间的整数时，它的运行时间是O(n+k)。**

**计数排序不是比较排序，排序的速度快于任何比较排序算法。**

**由于用来计数的数组B的长度取决于带排序数组中的数据范围，这使得计数排序对于数据范围很大的数组，需要大量的时间和内存。**

 最佳情况：T(n) = O(n+k) 

最差情况：T(n) = O(n+k) 

平均情况：T(n) = O(n+k) 

**稳定性：**

A-->B的时候，相同的值会在B中（1,2,3，...)来存储，先来的后来的顺序没有被破坏；

B-->A的时候，相同的值会在B中把1位置的出去，然后数字-1，然后再将1位置的出去，以此类推；

所以说B中的元素是先进先出，**没有破坏其稳定性**。

**适用场景：**

计数排序需要占用大量的内存空间，**它比较适用于数据较集中且最大最小值的差值不大的情况。**

### 桶排序（或叫箱排序）

**桶排序是计数排序的升级版。**

它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。

**桶排序适用于元素值分布较为集中的序列。**

它也利用了分治法。

图片演示：

![img](https://pic3.zhimg.com/80/v2-e0647e96a2c8b70d2715f77bce099019_hd.jpg)

代码实现：

~~~js
//桶排序
	function bucketSort(arr) {
		//思路：首先根据数组A的数据确定桶的个数以及桶能装元素范围；然后创建辅助数组B，for循环为数组B添加若干个数组（也就是桶）；然后循环A向B中添加元素；然后根据桶里的数据确定每个桶内用哪种排序方法；然后每个桶内数据排好序之后，将数组B中的数据放回A数组。
		if(arr.length == 0) return arr;
        
		let max = arr[0],
			min = arr[0],
			dif = 0,
			len = arr.length,
			temp = 0;
		for(let i = 1; i < len; i ++) {
			if(arr[i] > max) {
				max = arr[i];
			}
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		dif = max - min;

		let range = 10,//每个桶的范围
			bucketNum = Math.ceil(dif / range);//桶的个数
		//B
		let arrB = [];
		for(let i = 0; i < bucketNum; i ++) {
			arrB[i] = [];
		}
		//A-->B(这里是个难点哦)
		for(let i = 0; i < len; i ++) {
			temp = parseInt((arr[i] - min) / len);//这里一定要减min因为每个桶的范围是从2~12,12~22,...并不是我们想象的1~10,10~20...
			arrB[temp].push(arr[i]);
		}
		// console.log(arrB);

		//操作每个桶，这里就可用其他的排序方法了
		let arrBlen = arrB.length;
		for(let i = 0; i < arrBlen; i ++) {
			if(arrB[i].length > 1) {//略掉空桶和只有一个元素的桶
				fastSort(arrB[i]);
			}
		}
		// console.log(arrB);

		//B-->A
		let num = 0;
		for(let i = 0; i < arrBlen; i ++) {
			if(arrB[i].length > 0) {//不是空桶
				let arrBiLen = arrB[i].length;
				for(let j = 0; j < arrBiLen; j ++) {//把非空桶中的元素依次放入A数组
					arr[num] = arrB[i][j];
					num ++;
				}
			}
		}

		return arr;
	}

	console.log('桶排序算法升序序列的结果：', bucketSort(arr));
~~~

算法分析：

**桶排序的时间复杂度，取决于对各个桶之间数据进行排序的时间复杂度。**

**很显然，桶划分的容量越小，各个桶之间的数据越少，排序所用的时间也会越少；但相应的空间消耗就会增大。**

 最佳情况：T(n) = O(n+k) 

 最差情况：T(n) = O(n+k) 

 平均情况：T(n) = O(n2)　 

**稳定性：**

由于每个桶里面我们使用的是其他算法，所以**它是不稳定的排序算法**。

**适用场景：**

**桶排序可用于最大值和最小值相差较大的情况，但是桶排序要求数据的分布必须均匀**，否则可能导致大多数数据都集中在一个桶中。比如[134,178,1125,177,10000] 。

### 基数排序

基本数排序也是**非比较的排序算法**，对每一位进行排序，从最低位开始排序，复杂度为**O(kn)**，n为数组长度，k为数组中数的最大位数。

动图演示：

![img](https://pic4.zhimg.com/50/v2-3a6f1e5059386523ed941f0d6c3a136e_hd.webp)

代码实现：

~~~js
let array = [10, 1, 25678, 108, 155, 2718, 888, 6666, 125,67890];
	//基数排序
	function radixSort(arr) {
		//思路：首先找到arr中最大数，并取得其位数N；创建辅助数组B，其内容为0~9十个数字；循环arr，从个位开始，用每个数字的位数来计数排序，个位循环完，按顺序放回arr中，然后开始十位循环，以此类推，最大数位数N决定此循环的次数；整个循环完成后，排序也就完成了。

		let N = 0,//最大数的位数
			max = 0,
			len = arr.length;
			//取得最大值
		for(let i = 0; i < len; i ++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		//取得最大值的位数
		while(max > 0) {
			N ++;
			max = parseInt(max / 10);//这里要注意，要取整
		}
		
		let num = 1;//用于取余
		for(let i = 0; i < N; i ++) {
			//B(要保证每次大循环的时候B是空的，所以它放里面)
			let arrB = [];
			for(let i = 0; i < 10; i ++) {
				arrB[i] = [];
			}
			//A-->B
			for(let j = 0 ;j < len; j ++) {
				arrB[parseInt(arr[j] / num) % 10].push(arr[j]);
			}
			//B-->A(先进的后出)
			let index = 0;
			for(let j = 0; j < 10; j ++) {
				if(arrB[j].length > 0) {
					for(let k = 0; k < arrB[j].length; k ++) {
						arr[index] = arrB[j][k];
						index ++;
					}
				}
			}
			num *= 10;
		}
		return arr;
	}
	console.log(array);
	console.log('基数排序算法的升序排序结果：', radixSort(array));
~~~

**算法分析：**

 最佳情况：T(n) = O(n \* k) 

最差情况：T(n) = O(n \* k) 

**稳定性：**

从图中可以看到，每一轮的映射和收集操作，都是从左向右的进行，而且而且是先进先出的情况，所以相同的元素一直保持着它们在原数组中的顺序，所以**基数排序是稳定的排序算法**。

**适用场景：**

 基数排序要求较高，元素必须是整数，整数时长度10W以上，最大值100W以下效率较好。

**但是基数排序比其他排序好在可以适用字符串，或者其他需要根据多个条件进行排序的场景，例如日期，先排序日，再排序月，最后排序年 ，其它排序算法可是做不了的。** 

基数排序有两种方法：

MSD从高位开始进行排序；

LSD从低位开始进行排序。