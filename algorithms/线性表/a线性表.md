## 线性表 √

线性表

一个线性表是由n个具有相同特性的数据元素的有序序列。

**特点：**

- 集合中只有唯一一个“第一元素”和“最后元素”；
- 最后元素除外，其余元素均有唯一“后继”
- 第一元素除外，其余元素均有唯一“前驱”

### 数组 √

数组是我们在编程中用的最多的数据结构，一般都是定长数组。

#### 动态数组

很多时候定长的数组并不能很好的满足我们的需求，于是我们用动态数组。

在java中有个API——ArrayList，就是一个动态数组，在C++中有一个vector也是动态数组，我们可以直接使用。

但是有时候我们需要自己定制一个动态数组，以便更好的解决我们的问题。

实现动态数组有两种方法：

- 用定长数组实现（顺序表）
- 用引用（链表）实现

这里用定长数组实现：

实现思路：

​		首先申请一个定长的数组空间Original，定义一个int型的len，用来记录当前已使用的空间大小，也就是动态数组的长度。然后在增加元素的时候，先将len与Original.length比较，如果len < Original.length，则直接往Original中添加就好，然后len++；如果len >= Original.length，则新开辟一个为Original.length()*2大小的空间，先将Original中的数据依次放里面，然后将新添加的元素放到新开辟的空间中。

定长数组长度不足时：

![img](https://img-blog.csdn.net/20180707180201140?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsZXh3eW0=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

定长数组长度足够时：

![img](https://img-blog.csdn.net/20180707180416668?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsZXh3eW0=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

细节：

- 将待存储的数据定义为Object，因为Object是所有类的父类，因此我们就可以往这个数组中加入任何类型的数据。
- 有时候我们要求数组中只能存储某一种数据类型，不能掺杂其他数据；有时候我们又希望要求数组中可以存储任何数据类型。    要满足这两个看似有些矛盾的要求就只能用java中的**泛型**了。泛型不是引用类型也不是基本数据类型；泛型是一种特殊符号，它**泛指Java中任何一种引用类型。**泛型起到了一个占位符的作用，之后在使用的过程中可以根据项目的具体需求来指定占位符的数据类型。

动态数组需要实现哪些功能：

- add()  添加数据
- get()  获取任意位置的数据
- length()  返回当前动态数组的长度
- insert()  在任意位置插入数据
- delet()  删除任意位置的数据
- unite()  合并其他数组

具体代码实现：

~~~java
public class DynamicArray<E> {
	//申请一个数组，定长为100
	Object[] original;
	private int startLength = 100;
	private int len = 0;//当前动态数组的长度

	//构造方法，重载是否给长度两种情况
	public DynamicArray() {
		original = new Object[startLength];
	}

	public DynamicArray(int n) {
		this.startLength = n;
		original = new Object[startLength];
	}
	//判断数组是否已满 isFull
	public boolean isFull () {
		return len == original.length;
	}
	//判断数组是否为空 isEmpty
	public boolean isEmpty () {
		return len == 0;
	}
	//已满的情况下扩充数组 expand
	public void expand() {
		//创建新数组，长度为原数组的2倍，完全可以不是2倍，这里增大多少完全由于你的项目需求
		Object[] newArray = new Object[original.length * 2];
		//将原数组的数据放入新数组
		for(let i = 0; i < len; i ++) {//这里用len而不是original.length 的原因是没必要每次都求一遍原数组的长度，浪费资源
			newArray[i] = original[i];
		}
		//将新数组赋值给原数组
		original = newArray;
		//释放内存
		newArray = null;
	}
	//添加数据的方法 add
	public void add(E data) {
		if(isFull()) {
			//扩充数组
			expand();
		}
		//将新数据放到新数组中
		original[len] = data;
		len ++;
	}
	//获得某一位置的数据 get
	public E getData(int location) {
		return original[location];
	}
	//输出动态数组大小 size
	public int size() {
		return len;
	}
	//在某一位置插入数据 insert
	public void insertData(int location, int data) {
		if(isFull()) {
			//扩充数组
			expand();
		}
		//将要插入的位置及其后面的元素均向后移动一位
		for(int i = len - 1; i >= location; i --) {
			original[i + 1] = original[i];
		}
		original[location] = data;
		len ++;
	}
	//删除某一位置的数据
	public void delData(int location) {
		if(location < len) {
			for(int i = location; i < len; i ++) {
				original[i] = original[i + 1];
			}
		} else {
			System.out.println('此位置没有数据！');
		}
	}
	//合并另一个同类动态数组
	public void merge (DynamicArray ArrayB) {
		//如果当前数组装不下两个数组，就增大一定空间，若还小，继续增大 。。。
		while(ArrayB.size() + len > original.length) {
			expand();
		}
		//合并
		private int ArrayBLen = ArrayB.length;
		for(int i = 0; i < ArrayBLen; i ++) {
			original[len] = ArrayB[i];
			len ++;
		}
	}
}
~~~





### 栈 √

是一种**后进先出**的数据结构。

可以这样想想，这是一个客栈，但是只有一条走廊，先来的就要住到最里面的房间，后来的就往外住，第二天走的时候外面的不走里面的出不来，所以最先来的要最后走。

哈哈哈，我真是太有才了:muscle:。

**栈顶插入，删除元素；**

栈底存储的是最先入栈的元素。

~~~java
//java实现栈
public class stack {
	private int maxSize;//栈大小
	private int top;//栈顶，插入和删除元素，总是指向最外面一个元素
	private int[] arr;//用于存放数据，模拟栈

	public stack(int maxSize) {
		this.maxSize = maxSize;
		top = -1;
		arr = new Array(maxSize);
	}
	//是否满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//是否空
	public boolean isEmpty() {
		return top == -1;
	}
	//入栈
	public void addStack(int n) {
		if(isFull()) {
			System.out.println('队列已满！');
			return;
		}
		top ++;
		arr[top] == n;
	}
	//出栈
	public int delStack() {
		if(isEmpty()) {
			System.out.println('队列已空！');
			return;
		}
		top --;
		return arr[top + 1];
	}
	//输出栈中所有数据
	public void showStack() {
		if(isEmpty()) {
			System.out.println('队列已空！');
			return;
		}
		for(int i = 0; i <= top; i ++) {
			System.out.println('arr[%d] = %d', i, arr[i]);
		}
	}
}
~~~



### 队列 Queue √

是一种**先进先出**的数据结构。

就好比好多人在排队买老婆 饼，最前面的肯定最先买完最先走，最后来的最后出去。

**队尾插入，队头删除。**

队列是一个有序表，可以用数组或是链表来实现。

思路：

![这里写图片描述](https://img-blog.csdn.net/20170801164820996?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaXNtYWh1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下面这种情况也是可以的，换汤不换药，都是一个原理。

![img](https://bkimg.cdn.bcebos.com/pic/cdbf6c81800a19d8116a4d8030fa828ba71e46ce?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U4MA==,xp_5,yp_5)

#### 普通队列

实现第一个图（Java版）：

~~~java
public class ArrayQueue {
    private int maxSize; //此队列的最大容量
    private int front; //队头指针，总是指向先进来的元素的下一位
    private int rear; //队尾指针，总是指向最后一个进来的元素
    private int[] arr; //用于存放数据，模拟队列
    
    //初始化队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
    }
    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }
    //添加数据
    public void addQueue(int n) {
        //先判断队列是否已满
        if(isFull()) {
            System.out.println('队列已满！');
            return;
        }
        rear ++;
        arr[rear] = n;
    }
    //删除数据并返回
    public int delQueue() {
        //先判断队列是否为空
        if(isEmpty()) {
            System.out.println('队列已空！');
            return;
        }
        front ++;
        return arr[front];
    }
    //显示队列中的所有数据
    public void showQueue() {
        //先判断队列是否为空
        if(isEmpty()) {
            System.out.println('队列已空！');
            return;
        }
        for(int i = font + 1; i <= rear; i ++){
            System.out.println("arr[%d] = %d", i , arr[i]);
        }
    }
}
~~~

当队尾指针指向了数组的最后一个位置，而队头指针并没有在-1 的位置上时，就会出现**假溢出**现象，因为数组出去过数据，所以有位置，但是默认队列已经满了。

为了解决这个问题， 循环队列应运而生，即将队头和队尾连接起来，利用循环解决空间浪费问题。

#### 循环队列

![这里写图片描述](https://img-blog.csdn.net/20170801165226110?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaXNtYWh1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**代码实现：**

~~~java
public class CircularQueue {
    private int size;  //循环队列的大小
    private int front; //队头指针
    private int rear;  //队尾指针
    private int[] arr; //用于存放数据，模拟队列
    
    //构造函数
    public void CircularQueue(int size) {
        this.size = size;
        front = -1;
        rear = -1;
        arr = new Array(size);
    }
    //判断队列是否已满
    public boolean isFull() {
        return front == (rear + 1) % size;//这里要理解为何取余，因为当出现普通队列的’假溢出‘现象时rear要变为0，然后继续加一，再加一...
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return rear == (front + 1) % size;
    }
    //插入元素
    public void addQueue(int n) {
        //判断队列是否已满
        if(isFUll()) {
            System.out.println('队列已满！');
            return;
        }; 
        rear = (rear + 1) % size;
        arr[rear] = n;
    }
    //删除元素并返回
    public int delQueue() {
        //判断队列是否为空
        if(isEmpty()) {
            System.out.println('队列已空！');
            return;
        }
        front = (front + 1) % size;
        return add[front];
    }
    //显示队列中所有元素
    public void showQueue() {
        //判断队列是否为空
        if(isEmpty()) {
            System.out.println('队列已空！');
            return;
        }
        for(int i = 0; i < size; i ++) {//这里和普通队列有区别，因为此时font与rear之间大小并不确定
            System.out.println("arr[%d] = %d", i , arr[i]);
        }
    }
}
~~~

循环队列模型类似于生产——消费的关系，这也是很多消息队列的思想和应用。这种队列可以调节生产和消费的关系。

当然，也可以生产一个产品被多个消费者消费，这又产生了线程并发的问题。我们如何保证线程安全呢？这就需要并发队列。基于数组的循环队列+CAS原子操作，可以很好的实现无锁并发队列。

#### 阻塞队列

阻塞队列与普通队列的区别在于：

- 当队列是空的时，从队列中获取元素的操作将会被阻塞；
- 当队列是满的时，往队列里添加元素的操作会被阻塞；

试图在空的阻塞队列中获取元素的线程将会被阻塞，直到其他的线程往空的队列插入新的元素。

同样，试图往已满的阻塞队列中添加新元素的线程同样也会被阻塞，直到其他线程使队列重新变得空闲起来。

代码（java版）：

~~~java
//虽然懂了阻塞队列的大概原理，然鹅并不懂代码
public class BlockingQueue {
    private List queue = new LinkdList();
    private int limit = 10;
    
    public BlockingQueue(int limit) {
        this.limit = limit;
    }
    public synchronized void enqueue(Object item)
        throws InterruptedException {
        while(this.queue.size()) === this.limit) {
            wait();
        }
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }
    
    public synchronized Object dequeue()
        throws InterruptedException {
        
        while(this.queue.size() == 0) {
            wait();
        }
        if(this.queue.size() == this.limit) {
            notifyAll();
        }
        return this.queue.remove(0);
    }
}
~~~

 必须注意到，在enqueue和dequeue方法内部，只有队列的大小等于上限（limit）或者下限（0）时，才调用notifyAll方法。如果队列的大小既不等于上限，也不等于下限，任何线程调用enqueue或者dequeue方法时，都不会阻塞，都能够正常的往队列中添加或者移除元素。 

#### 并发队列 qeque

（只是简单了解）

ConcurrentLinkedQeque：是一个适用于高并发场景下的队列，通过无锁的方式，实现了高并发状态下的高性能，通常ConcurrentLinkedQueue性能好于BlockingQueue。它是一个基于链接节点的无界线程安全队列。该队列的元素遵循先进先出的原则。头是最先加入的，尾是最近加入的，该队列不允许null元素。

#### 双端队列 deque

双端队列是一种具有队列和栈性质的数据结构，即可在线性表的两端进行插入和删除操作。

依然是front与rear两个指针变量分别指向两端，但是有了4种操作而非两种：

- push  将元素插入表头 front--
- pop  删除头部元素  front++
- inject  将元素插入到表位 rear ++
- eject  删除尾部元素 rear --

我们初始化时，rear = front = 0；那push如何操作？front--呀，但不是出界了吗？

别急，由于是循环队列，front = (front) % 数组大小，这样就不会出界了。由于这样的操作，我们第一次push，元素会在数组的尾部，举个例子：

- 可以理解为中间站一个人
- push和pop在他左边进行
- inject和eject在他右边进行

此外，我们有 -- 操作，可能会出现负值，所以需要+size再取余的操作！

### 链表 √

#### 单链表 √

单链表的基本节点结构：

![img](https://img-blog.csdnimg.cn/20190526100232806.png)

![img](https://user-gold-cdn.xitu.io/2018/6/20/1641a579e288345c?w=668&h=87&f=png&s=8183)

三个重要概念：

- 头结点：
  - 有时，在链表的第一个节点之前会额外增设一个节点，节点的数据域一般不存放数据（有些情况存放链表长度等信息），此点被称为头结点。
  - 若头结点的指针域为空，则说明此链表是空表。
  - 头结点不是必须的，但是有时候有头结点会使问题变简单。
- 首元结点：
  - 链表中第一个元素所在的节点，它是头结点后面的第一个节点
- 头指针：
  - 永远指向链表中第一个节点的位置（如果有头结点则指向头结点，如果没有则指向首元结点。

![img](http://data.biancheng.net/uploads/allimg/170719/2-1FG915025H28.png)

单链表中可以没有头结点，但是不能没有头指针。

头结点的引入能使链表对第一个元素的删除和插入与其他元素相同，不用另外说明，使得代码更加简洁。

代码实现单链表：

~~~java
//节点
public class Node<AnyType> {
	public AnyType data;
	public Node<AnyType> next;

	public Node (AnyType data, Node<AnyType> next) {
		this.data = data;
		this.next = next;
	}
}

//单链表
public class myLinkList<AnyType> {
	//头结点
	private Node<AnyType> first;
	//头指针
	private Node<AnyType> head;
	//链表长度
	private int size;

	publilc myLinkList () {
		this.first = new Node<>(null, null);
		this.head = new Node<>(null, first);
		this.size = 0;
	}

	//判断链表是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	//获取指定节点并返回
	public Node<AnyType> getNode(AnyType data) {
		Node<AnyType> rende = first;
		while(rende.data != data) {
			rende = rende.next;
		}
		return rende;
	}

	//向指定元素后面添加节点
	public void addNode(AnyType data, AnyType addData) {
		Node<AnyType> addData = new Node<>(null, addData);//新节点
		Node<AnyType> node = getNode(data);//位置节点
		addData.next = node.next;
		node.next = addData;
		size ++;
	}

	//删除指定节点
	public void delNode(AnyType data) {
		//找到指定节点的上一个节点
		Node<AnyType> rende = first;
		while(rende.next.data != data) {
			rende = rende.next;
		}
		rende.next = rende.next.next;
		size --;
	}
	//求链表长度
	public int len() {
		int length = 1;
		Node<AnyType> node = first;
		while(node.next != null) {
			length ++;
			node = node.next;
		}
		return length;
	}
}
~~~



#### 双向链表 √

单链表有其局限性，它不能反向遍历，而且无法操作一个节点的前一个节点，所以，双向链表应运而生！

![这里写图片描述](https://img-blog.csdn.net/20180403095812960?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1dlaUppRmVuZ18=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

它的特点就是：

- 每个节点都有一个数据域和两个指针域，一个指向其前驱结点，一个指向其后继节点

但它也有其不足之处：每次插入和删除操作时都要处理四个节点的引用，而不是两个了。

头插入：

![这里写图片描述](https://img-blog.csdn.net/20180403105316163?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1dlaUppRmVuZ18=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

中间插入：

![这里写图片描述](https://img-blog.csdn.net/2018040311301324?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1dlaUppRmVuZ18=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

删除节点：

![这里写图片描述](https://img-blog.csdn.net/20180403113242598?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1dlaUppRmVuZ18=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

代码实现双向链表：

~~~java
//双向链表的节点类
public class Link<T>  {
	public T data;//数据域
	public Link<T> prev;//前驱指针
	public Link<T> next;//后继指针

	public Link(T data) {
		this.data = data;
	}
}

//初始化一个双向链表：
public class doubleLinkList<T> {
	Link<T> head = new Link<> (null);

	//链表是否为空
	public boolean isEmpty () {
		return head.next == null;
	}
	//查找指定节点并返回
	public Link<T> findNode(T data) {
		Link<T> node = head;
		while(node.data != data && node.data != null) {
			node = node.next;
		}
		return node;
	}
	//头插部插入节点
	public void headInsertNode(T data) {
		Link<T> node = new Link<>(data);
		head.next = node;
		node.prev = head;
		node.next = null;
	}
	//指定元素后面插入节点
	public void midInsertNode(T data, T insertData) {
		Link<T> node = new Link<>(insertData);
		Link<T> find = new findNode(data);//指定元素
		node.prev = find;
		node.next = find.next.next;
		find.next.next.prev = node;
		find.next = node;
	}
	//头部删除节点
	public void headDelNode() {
		if(isEmpty()) {
			head.next.next.prev = head;
			head.next = head.next.next;
		} else {
			System.out.println('双向链表为空！');
		}
	}

	//指定元素后面删除节点
	public void midDelNode(T data) {
		Link<T> node = findNode(data);
		node.next.next.prev = node;
		node.next = node.next.next;
	}
}
~~~



#### 循环链表 √

一张图就明白，实现机制和单链表无异样

![img](https://img2018.cnblogs.com/blog/1037399/201810/1037399-20181021190856316-1970840347.png)

##### 约瑟夫环问题

约瑟夫环问题是一个经典的循环链表的问题，题意是：已知n个人（依次以1,2,3,4...,n排序）围坐在圆桌周围，以编号为k的人从1开始顺时针报数，数到m的那个人出列；他的下一个人还是从1开始顺时针报数，数到m的那个人又出列，依次重复下去，要求找到最后出列的那个人的编号。

例如有5个人，要求从编号3的人开始数，数到2的那个人出列：

![img](https://upload-images.jianshu.io/upload_images/16823531-c88b83feb6be6d66.png?imageMogr2/auto-orient/strip|imageView2/2/w/400/format/webp)

~~~java
//节点类
public class Node {
	public int data;
	public Node next;

	public Node (int data) {
		this.data = data;
	} 
}

//链表类
public class Link {
	Node head = new Node(null);
	Node first = new Node(1);//第一个元素已经插入进去
	head.next = first;
	first.next = first;
	int size = 1;

	//找到对应节点
	public Node findNode(Node node) {
		Node nodeResult = first;
		int time = 0
		while(nodeResult != node && time < size) {
			nodeResult = nodeResult.next;
			time ++;
		}
		if(time == size) {
			throw new Exception("未找到node节点！");
		}
		return nodeResult;
	}

	//删除指定节点
	public void delNode(Node node) {
		Node prevNode = first;//要删除的节点的上一个节点
		int time = 0;
		while(prevNode.next != node && time < size) {
			prevNode = prevNode.next;
		}
		if(time == size) {
			throw new Exception("未找到指定节点！");
		}
		prevNode.next = prevNode.next.next;
	}

	//解决问题的函数
	public int lastNode(int n, int k, int m) {//一共n个人，以编号k的人开始，从1开始数，数到m，m的那个人出列，m+1的人继续从1开始数到m...
		//构建k个循环链表
		for(int i = 2; i <= n; i ++) {
			Node lastNode = first;//代表最后一个节点
			Node node = new Node(i);//要插入的节点
			node.next = first;
			lastNode.next = node;
			lastNode = lastNode.next;
			size ++;
		}
		while(size > 1) {//这个条件可以保证循环出来之后链表里就剩下一个节点，也就是我们所需的节点
			Node kNode = findNode(int k);
			for(int i = k; i < k + m - 1; k ++) {
				kNode = kNode.next;
			}
			delNode(Node kNode);
			size --;
		}
		return head.next.data;//此时首节点即为所求
	}
}
~~~



#### 双向循环链表 √

![img](https://img2018.cnblogs.com/blog/1330328/201906/1330328-20190610181836026-1029124884.png)

![img](https://img2018.cnblogs.com/blog/1330328/201906/1330328-20190610183040561-162080257.png)



java代码实现：

~~~java
//节点类
public class Node {
	public int data;
	public Node next;

	public Node (int data) {
		this.data = data;
	} 
}

//链表类
public class Link {
	Node head = new Node(null);
	Node first = new Node(1);//第一个元素已经插入进去
	head.next = first;
	first.next = first;
	int size = 1;

	//找到对应节点
	public Node findNode(Node node) {
		Node nodeResult = first;
		int time = 0
		while(nodeResult != node && time < size) {
			nodeResult = nodeResult.next;
			time ++;
		}
		if(time == size) {
			throw new Exception("未找到node节点！");
		}
		return nodeResult;
	}

	//删除指定节点
	public void delNode(Node node) {
		Node prevNode = first;//要删除的节点的上一个节点
		int time = 0;
		while(prevNode.next != node && time < size) {
			prevNode = prevNode.next;
		}
		if(time == size) {
			throw new Exception("未找到指定节点！");
		}
		prevNode.next = prevNode.next.next;
	}

	//解决问题的函数
	public int lastNode(int n, int k, int m) {//一共n个人，以编号k的人开始，从1开始数，数到m，m的那个人出列，m+1的人继续从1开始数到m...
		//构建k个循环链表
		for(int i = 2; i <= n; i ++) {
			Node lastNode = first;//代表最后一个节点
			Node node = new Node(i);//要插入的节点
			node.next = first;
			lastNode.next = node;
			lastNode = lastNode.next;
			size ++;
		}
		while(size > 1) {//这个条件可以保证循环出来之后链表里就剩下一个节点，也就是我们所需的节点
			Node kNode = findNode(int k);
			for(int i = k; i < k + m - 1; k ++) {
				kNode = kNode.next;
			}
			delNode(Node kNode);
			size --;
		}
		return head.next.data;//此时首节点即为所求
	}
}
~~~

