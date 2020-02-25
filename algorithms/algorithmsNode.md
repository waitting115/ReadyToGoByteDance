

# 数据结构与算法



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

```
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
```





### 栈 √

是一种**后进先出**的数据结构。

可以这样想想，这是一个客栈，但是只有一条走廊，先来的就要住到最里面的房间，后来的就往外住，第二天走的时候外面的不走里面的出不来，所以最先来的要最后走。

哈哈哈，我真是太有才了。

**栈顶插入，删除元素；**

栈底存储的是最先入栈的元素。

```
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
```



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

```
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
```

当队尾指针指向了数组的最后一个位置，而队头指针并没有在-1 的位置上时，就会出现**假溢出**现象，因为数组出去过数据，所以有位置，但是默认队列已经满了。

为了解决这个问题， 循环队列应运而生，即将队头和队尾连接起来，利用循环解决空间浪费问题。

#### 循环队列

![这里写图片描述](https://img-blog.csdn.net/20170801165226110?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaXNtYWh1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**代码实现：**

```
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
```

循环队列模型类似于生产——消费的关系，这也是很多消息队列的思想和应用。这种队列可以调节生产和消费的关系。

当然，也可以生产一个产品被多个消费者消费，这又产生了线程并发的问题。我们如何保证线程安全呢？这就需要并发队列。基于数组的循环队列+CAS原子操作，可以很好的实现无锁并发队列。

#### 阻塞队列

阻塞队列与普通队列的区别在于：

- 当队列是空的时，从队列中获取元素的操作将会被阻塞；
- 当队列是满的时，往队列里添加元素的操作会被阻塞；

试图在空的阻塞队列中获取元素的线程将会被阻塞，直到其他的线程往空的队列插入新的元素。

同样，试图往已满的阻塞队列中添加新元素的线程同样也会被阻塞，直到其他线程使队列重新变得空闲起来。

代码（java版）：

```
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
```

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

### 链表

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

```
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
```



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

```java
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
```



#### 循环链表 √

一张图就明白，实现机制和单链表无异样

![img](https://img2018.cnblogs.com/blog/1037399/201810/1037399-20181021190856316-1970840347.png)

##### 约瑟夫环问题

约瑟夫环问题是一个经典的循环链表的问题，题意是：已知n个人（依次以1,2,3,4...,n排序）围坐在圆桌周围，以编号为k的人从1开始顺时针报数，数到m的那个人出列；他的下一个人还是从1开始顺时针报数，数到m的那个人又出列，依次重复下去，要求找到最后出列的那个人的编号。

例如有5个人，要求从编号3的人开始数，数到2的那个人出列：

![img](https://upload-images.jianshu.io/upload_images/16823531-c88b83feb6be6d66.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/400/format/webp)

```java
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
```



#### 双向循环链表 √

![img](https://img2018.cnblogs.com/blog/1330328/201906/1330328-20190610181836026-1029124884.png)

![img](https://img2018.cnblogs.com/blog/1330328/201906/1330328-20190610183040561-162080257.png)



java代码实现：

```java
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
```

## 哈希表（散列表）√

### 哈希函数与哈希表

根据关键码值（key value) 直接进行访问的数据结构，也就是说，它通过吧关键码值映射到表中一个位置来访问记录，以加快查找速度，**这个映射函数叫做哈希函数，存放记录的数组叫做哈希表**。

哈希表的优点：**增删查  时间复杂度O(1)；**

​				缺点：1.占用内存较大；2.元素没有任何顺序

### 哈希函数的构造方法

- 除留余数法（最常用）
  - 用关键字除以一个不大于哈希表长度m的正整数p（素数）所得的余数作为哈希地址的一种方法
  - h(key) = key % p;
  - 哈希函数：f(key) = key mod p (p <= m);
- 平方取中法
  - 假设关键字是1234，那么它的平方就是1522756，在抽取中间的三位就是227，用作散列地址。
  - 平方取中法适用于不知道关键字的分布，而位数又不是很大的情况
- 直接定址法
  - 取关键字的某个线性函数为哈希地址
  - 比如要统计1980年及其以后的每年出生人数，那么就可以用每年年份减去1980来做此年的哈希地址来存储，优点就是不但可以找到是否有此数据，还可以根据key值与value值算出哪一年出生了多少人。
  - 适用于查找表较小且连续的情况，下面力扣中的题就是用的直接定址法
- 折叠法
- 随机数法
- 数字分析法

构造哈希函数的方法有很多，实际工作中要根据不同的情况选择合适的方法，总的原则是尽可能少的产生冲突。

通常考虑的因素有关键字的长度和分布情况、哈希值的范围等。

### 如何确定除余法中的素数？

**即选择不大于数组长度的最大的素数！**

### 哈希函数为什么选择对素数求余？

当哈希表的大小为素数时，简单的取模运算会使哈希的结果更加均匀，这也是此问题的来源。

常用的hash函数是选一个数m取模（余数），这个数在课本中推荐m是素数，但是经常见到选择m=2n，因为对2n求余数更快，并认为在key分布均匀的情况下，key%m也是在[0,m-1]区间均匀分布的。但实际上，key%m的分布同m是有关的。
  证明如下： key%m = key - xm，即key减掉m的某个倍数x，剩下比m小的部分就是key除以m的余数。显然，x等于key/m的整数部分，以floor(key/m)表示。假设key和m有公约数g，即key=ag, m=bg, 则 key - xm = key - floor(key/m)m = key - floor(a/b)m。由于0 <= a/b <= a，所以floor(a/b)只有a+1中取值可能，从而推导出key%m也只有a+1中取值可能。a+1个球放在m个盒子里面，显然不可能做到均匀。
  由此可知，一组均匀分布的key，其中同m公约数为1的那部分，余数后在[0,m-1]上还是均匀分布的，但同m公约数不为1的那部分，余数在[0, m-1]上就不是均匀分布的了。**把m选为素数，正是为了让所有key同m的公约数都为1，从而保证余数的均匀分布，降低冲突率。**

### 哈希冲突

即不同的key值产生相同的地址。

### 哈希冲突的解决方法

- 开放定址法
  - 当冲突发生时，按照某种方法探测表中的其他存储单元，直到找到空位置位置
  - （1）线性探测法：
    - di = 1,  2,  3， ...，m-1
  - （2）二次探测法：
    - di = 1²,  -1²,  2²,  -2²,  3²,  -3²,  ...
  - 双重哈希法：
    - 一旦发生冲突，应用第二个哈希函数以获取备用位置
- 链地址法
  - 将所有关键字为同义词的节点链接在同一个单链表中。
- 公共溢出区法
- 再散列法

### 哈希表的实现（Java）

~~~java
//先寻找出那个素数，然后再操作，还是用java
public class Hash {
	private int[] hashTable;
	private int maxSize = 10;
	private int[] arr;
	private int p = 11;//默认的素数

	public Hash () {
		this.hashTable = new int[maxSize];
	}

	public Hash (int[] arr) {
		this.maxSize = arr.length;
		this.hashTable = new int[maxSize];
		this.arr = arr;
		//寻找不大于数组长度的最大的素数，素数一定是奇数，所以这就可以排除一半
		int n = arr.length;
		if(arr.length % 2 == 0) {
			n = arr.length - 1;
		}
		for(int i = n; i > 1; i -= 2) {
			for(int j = n - 2; j > 1; j -=2) { //不是素数奇数的两个公约数也一定是奇数
				if(i % j == 0) {
					break;
				}
			}
			if(j == 1) {//说明找到了要找的素数
				this.p = i;
				break;
			}
		}


	}

	//给定数组创建哈希表
	// public int[] crateHashTable (int[] arr) {

	// }
	//向哈希表中添加数据
	public void addHashTable (int n) {
		//使用除余法添加，用线性探测法解决哈希冲突
		int num = n % p;
		while(hashTable[num] == null) {
			if(num >= maxSize - 1) {//如果找到头也没找到空位置，则从0开始继续找
				num = 0;
			} else {
				num ++;
			}
		}
		hashTable[num] = n;
	}
	
	//查找哈希表中的数据
	public boolean findHashTable (int n) {
		int num = n % p;
		int time = 0//查找次数，防止无限循环
		while(hashTable[num] != n && time < maxSize) {
			if(num >= maxSize - 1){//如果找到头也没吵到，则从0开始继续找
				num = 0;
			} else {
				num ++;
			}
			time ++
		}
		if(time == maxSize) {//说明没找到
			return false;
		} else {
			return true;
		}
	}
}
~~~



### 哈希表的删除

 首先链地址法是可以直接删除元素的，但是开放定址法是不行的，拿前面的双探测再散列来说，假如我们删除了元素1，将其位置置空，那 23就永远找不到了。**正确做法应该是删除之后置入一个原来不存在的数据，比如-1** 

### 位图 √

#### 1.什么是位图

就是bitmap的缩写，所谓bitmap就是用每一位来存放数据的某种状态。适**用于大规模数据，但数据状态又不是很多的情况**。通常用来判断某个数据是否存在。

#### 2.位图的用途

位图主要用于海量数据处理，索引，数据压缩等方面

#### 3.位图的结构

位图的结构类似于哈希，通常用0,1来表示一个数的状态。

比如，我现在有一个文件，这个文件有[1,5,123345]，我们就在位图中把第1位，第5位，第123345位改为状态1，其余位都为0。

![位图结构](http://img.blog.csdn.net/20180302214426709?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzg2NDY0NzA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

#### 4.位图的应用

- 快速排序
  - 就类似于计数排序
- 快速去重
  - 创建一个位图，出现过的数据，将位图中此位置的数据变为1，循环过后将位图放回原数据
- 快速查询
  - 比如，现有一个40亿个无符号整数，给你一个无符号整数，让你查找这整数是否在这40亿个数中

### 动态扩容 √

在无参构造hash表的情况下，因为此时hash表的大小是默认的大小，如果一直往里面添加元素， 开放定址法（线性探测法、二次探测法、双重哈希法）解决哈希冲突的情况下，就会导致hash表容量不够用；链表法会导致查找的时间复杂度增加。

所以此时我们要动态扩容hash表。

有两种方法来动态扩容：

1.第一种是普通的方法：

​	即构造一个二倍于前一个hash表大小的hash表，然后将前一个hash表中的数据用新的hash函数来存到新的hash表中，然后将新插入的数据插入到新hash表中。    缺点就是当hash表满的时候插入下一个数据的时候等待时间过长

2.第二种方法比第一种更高效些：

![img](https://ask.qcloudimg.com/http-save/yehe-3470542/jsvccqzp2c.jpeg?imageView2/2/w/1620)

这样每次迁移时插入一个数据，没有集中一次性迁移数据那样耗时，不会形成明显的阻塞。

由于迁移过程中，有新旧两个hash表，查找数据时，现在新表中查找，如果没有，再到旧hash表中查找。

## 树

### 二叉树

#### 二叉查找树

#### 平衡二叉树

#### 平衡二叉查找树

##### ALV树

##### 红黑树

 https://www.jianshu.com/p/e136ec79235c 

#### 完全二叉树

### 多路查找树

#### B树

#### B+树

#### 2-3树

#### 2-3-4树

### 堆

#### 小顶堆

#### 大顶堆

#### 二项堆

#### 优先队列

#### 斐波那契堆

## 图

### 图的存储

#### 邻接矩阵

#### 邻接表

### 关键路径

### 最小生成树

### 最短路径

### 拓扑排序

##  基本算法思想 √

### 动态规划 √

**我们先考虑能否把大问题分解成小问题，分解的问题也存在最优解，如果把小问题的最优解组合起来能否使整个问题的最优解，这就是动态规划的最优解。**

主要解决的问题：

- 多阶段决策过程的最优化

其主要思想是：将最优化的决策过程分为互相联系的几个阶段，每个阶段需要做出一个决策，并且当前阶段的决策会影响下一阶段的决策，从而影响到整个过程的活动路线。

作为整个过程的最优策略具有这样的性质：

- 无论过去的状态和决策如何，对前面的决策所形成的状态而言，余下的诸决策必须构成最优策略。

动态规划解题步骤：

1. 正确划分阶段，用阶段变量表示，比如月份，年份等等
2. 正确选择状态变量xk，  使它既能描述过程的状态，又要满足无后效性。所谓**无后效性**是指：如果某段状态给定，则在这段以后过程的发展不受前面各阶段状态的影响。 
3. **确定决策变量uk及每段的允许决策集合。**
4. **写出状态转移方程**，表示由k段到k+1段的整体转移规律，第k段的状态和决策确定，则第k+1段的状态也应该确定下来。
5. **确定指标函数和递推关系式**，它是定义在全过程和所有后部子过程上的数量函数。



**经典例题：**

**最优路线问题：**

 给出一个线路网络， 从A点要铺设一条管道到G点，其两点之间连线上的数字表示两点间的距离；要求选择一条由A到G的铺管线路，使总距离为最短。 

![img](https://img2018.cnblogs.com/blog/1608161/201907/1608161-20190704215615748-1030981036.png)



**爬楼梯问题：**（LeeCode 70）

 	假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？注意：给定 n 是一个正整数。  每一阶段可以选择1步或者两步，f(n)=f(n-1)+f(n-2)，其实这是一个**斐波那契数列第n项问题**。 

```
var climbStairs = function(n) {
            //要理解，通往第i个元素的有两个，第i - 1个元素和第i - 2个元素，所以通往第i个元素的方法就是这通往这两个元素的方法之和。（和斐波那契数列一样）
            if(n < 3) return n;
            let Fibonacci = [0,1,2];
            for(let i = 3; i <= n; i ++) {
                Fibonacci.push(Fibonacci[i - 1] + Fibonacci[i - 2]);
            }
            return Fibonacci[n];
        };
        console.log(climbStairs(10))
```





**最大子序和**（LeeCode 53）

 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 ![img](https://img2018.cnblogs.com/blog/1608161/201907/1608161-20190704220041732-194371155.png)

```

```

**打家劫舍**（LeeCode 198）

 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。  解答：对于此题，如果只有两家或者以下，我们选择金额最大的。如果2家以上，那我们打劫到第 i 家的时候，就要考虑，要不要打劫这一家，也就是（这一家的价值+打劫到 i - 2家的最大价值）和（打劫到上一家（i - 1）的最大价值），比较这两个值，选较大值作为打劫到第 i 家的最大价值。最后输出最后一家就可以了。 **dp[i] = max(dp[i-2]+nums[i], dp[i-1])** 

```

```



### 贪心算法 √

**贪心算法的定义：**

​		贪心算法是指在对问题求解时，总是做出在当前看来是最好的选择。也就是说不从整体最优上加以考虑，只做出某种意义上的局部最优解。贪心算法不是对所有问题都能得到整体最优解，关键是贪心策略的选择，**选择的贪心策略必须具备无后效性，即某个状态以前的过程不会影响以后的状态，只与当前状态有关**。

**解题的一般步骤：**

- 建立数学模型来描述问题
- 把求解的问题分成若干个子问题
- 对每一个子问题局部求解，得到子问题的局部最优解
- 把子问题的局部最优解合成大问题的解

最优解问题大部分都可以拆分成一个个的子问题，把解空间的遍历视作对子问题树的遍历，则以某种形式对树整个的遍历一遍就可以求出最优解，大部分情况下这是可行的。

贪心算法和动态规划本质上是对子问题树的一种修剪，**两种算法要求问题都具有的一个性质就是子问题最优性(**组成最优解的每一个子问题的解，对于这个子问题本身肯定也是最优的)。动态规划方法代表了这一类问题的一般解法，我们自底向上构造子问题的解，对每一个子树的根，求出下面每一个叶子的值，并且以其中的最优值作为自身的值，其它的值舍弃。

**而贪心算法是动态规划方法的一个特例**，**可以证明每一个子树的根的值不取决于下面叶子的值，而只取决于当前问题的状况**。**换句话说，不需要知道一个节点所有子树的情况，就可以求出这个节点的值。由于贪心算法的这个特性，它对解空间树的遍历不需要自底向上，而只需要自根开始，选择最优的路，一直走到底就可以了。** 



**经典例题：**

1.哈夫曼树：

在森林中，选取两棵权值最小的树组成一棵新树，再在森林中选取两棵权值最小的树组成一棵新树，直到森林中只剩下最后一棵树，即为权值最小的树。

![img](https://upload-images.jianshu.io/upload_images/4889651-e4b93aa07c192a6c)

 2.钱币找零问题 	这个问题在我们的日常生活中就更加普遍了。假设1元、2元、5元、10元、20元、50元、100元的纸币分别有c0, c1, c2, c3, c4, c5, c6张。现在要用这些钱来支付K元，至少要用多少张纸币？

​		用贪心算法的思想，很显然，每一步尽可能用面值大的纸币即可。在日常生活中我们自然而然也是这么做的。在程序中已经事先将Value按照从小到大的顺序排好。 



### 回溯算法 √

回溯算法相当于穷举搜索的巧妙实现，对比蛮力的穷举搜索，回溯算法可以对一些不符合要求或者重复的情况进行裁剪，不再对其进行搜索，以减少搜索提高效率。

比如图在运用回溯算法的深度优先搜索遍历中，会对已搜索遍历过的顶点进行标记，避免下次回溯搜索中对再次出现的该顶点进行重复遍历。

经典例题：

八皇后问题，在搜索-->深度优先遍历中有提及

### 分治算法 √

**分治算法不是简单的递归，而是将大的问题递归解决较小的问题，然后从子问题的解构建原来问题的解。**

比如快速排序和归并排序都是分治算法，而图的递归深度搜索和二叉树的递归遍历则不是分治算法的应用。

分治算法运行时间的计算有一重要原理：

![img](https://img2018.cnblogs.com/blog/1637843/201906/1637843-20190601211242816-1872276994.png)





### 枚举算法 √

1，枚举算法的定义： 在进行归纳推理时，如果逐个考察了某类事件的所有可能情况，因而得出一般结论，那么该结论是可靠 的，这种归纳方法叫做枚举法。 2，枚举算法的思想是： 将问题的所有可能的答案一一列举，然后根据条件判断此答案是否合适，保留合适的，舍弃不合适的。 3，使用枚举算法解题的基本思路如下： （1）确定枚举对象、范围和判定条件。 （2）逐一枚举可能的解并验证每个解是否是问题的解。 4，枚举算法步骤： （1）确定解题的可能范围，不能遗漏任何一个真正解，同时避免重复。 （2）判定是否是真正解的方法。 （3）为了提高解决问题的效率，使可能解的范围将至最小， 5，枚举算法的流程图如下所示：                                       

二，枚举算法实例

例一：百钱买白鸡 1，问题描述： 公鸡每只5元，母鸡每只3元，三只小鸡1元，用100元买100只鸡，问公鸡、母鸡、小鸡各多少只？ 2，算法分析： 利用枚举法解决该问题，以三种鸡的个数为枚举对象,分别设为mj,gj和xj，用三种鸡的总数 （mj+gj+xj=100）和买鸡钱的总数（1/3*xj+mj*3+gj*5=100）作为判定条件，穷举各种鸡的个数。

## 复杂度分析 √

### 空间复杂度 √

- **多开常数空间（不开）：O(1)**
- **多开一个辅助数组的空间：O(N)**
- **多开一个二维辅助数组的空间：O(N2)**

**递归调用是有空间代价的，递归的深度是多少，其空间复杂度就是多少！**

### 时间复杂度 √

常用的时间复杂度量级：

- 常数阶：O(1)
- 线性阶：O(n)
- 平方阶：O(n²)
- 对数阶：O(logn)
- 线性对数阶：O(nlogn)

![看动画轻松理解时间复杂度（一）](http://www.cxyxiaowu.com/wp-content/uploads/2019/10/1571058263-eaced6bd6a2ee0c.jpeg)

#### O(1)

**图解：**

![看动画轻松理解时间复杂度（一）](http://www.cxyxiaowu.com/wp-content/uploads/2019/10/1571058263-8c2f5424e95ac82.gif)

**解释：**

无论代码执行了多少行，其他区域不会影响到操作，这个代码的时间复杂度都是O(1)。

也可以理解为此代码只执行一遍，没有任何循环。

**举例：**

~~~js
function swapTwoInts(a, b) {
    let temp = a;
    a = b;
    b = temp;
}
~~~



#### O(n)

**图解：**

![看动画轻松理解时间复杂度（一）](http://www.cxyxiaowu.com/wp-content/uploads/2019/10/1571058264-eedba53ae5b37bc.gif)

**解释：**

for循环n遍的代码，它消耗的时间是随着n的变化而变化的。

**举例：**

~~~js
let num = 0, len = 100;
for(let i = 0; i < 100; i ++) {
	num ++;
}
console.log(num);
~~~

#### O(n²)

**图解：**

![看动画轻松理解时间复杂度（一）](http://www.cxyxiaowu.com/wp-content/uploads/2019/10/1571058264-4419dde13986262.gif)

**解释：**

当存在双重循环的时候，也就是把时间复杂度为O(n)的代码又循环运行了n遍，也就变成了O(n²)。

**举例：**

~~~js
let len = 100;//n
let index = 0;
for(let i = 0; i < len; i ++){
    for(let j = i; j < len; j ++) {
        index ++;
    }
})
console.log(index);
~~~

这里我们推导一下：

- i= 0， j循环循环n次
- i= 1， j循环循环（n-1）次
- ...
- i= n，j循环循环 0 次

总循环次数就等于：

- n + (n - 1) + (n - 2) + ... + 0 = n * (n + 1) / 2 = O(n²)

但也不是所有的双重循环都是O(n²)，比如下面这段输出20次n的程序：

~~~js
let n = 10;
for(let i = 0 ; i < n;ii ++) {
    for(let j = 0; j < 2; j ++) {
        console.log('a');
    }
}
~~~



#### O(logn)

**图解：**

![看动画轻松理解时间复杂度（一）](http://www.cxyxiaowu.com/wp-content/uploads/2019/10/1571058264-f15712706d19b10.gif)

**解释：**

在折半查找（又称二分查找）的程序中，通过while循环，成2倍数的缩小搜索范围，也就是说需要经过log2^n次即可跳出循环。

**举例：**

~~~js
//折半查找
let a = [1,2,3,4,5,6,7,8,9,9];
function binarySearch(arr, n) {
    let low = 0, high = arr.length - 1, mid = 0;
    while(low < high) {
        mid = parseInt((low + high) / 2);
        if(arr[mid] == n) {
            return mid;
        } else if(arr[mid] > n) {
            high = mid - 1;
        } else{
            low = mid + 1;
        }
    }
    return -1;
}
console.log('折半查找结果：', binarySearch(a, 5));
~~~

同样的还有下面两段代码也是O(logn)级别的时间复杂度。

~~~js
function intoString(num) {
    let s = '';
    //n经过几次除以10的操作后等于0 成倍减小
    while(num) {
        s += '0' + num % 10;
        num = parseInt(num / 10);
    }
    return s;
}
console.log(intoString(1234));
~~~

#### O(nlogn)

**解释：**

将时间复杂度为O(logn) 的程序循环n遍，它的时间复杂度就为n * O(logn)，也就是O(nlogn)了。

**举例：**

~~~js
let n = 10;
for(let i = 0; i < n; i ++) {
    let k = 1;
    while(k < i) {
        k *= 2;//成倍增长
    }
}
~~~

#### 分类

##### 最好

在最理想的情况下，执行这段代码的时间复杂度。

##### 最坏

在最糟糕的情况下，执行这段代码的时间复杂度。

##### 平均

用代码在所有情况下执行的次数的加权平均值表示，也叫加权平均时间复杂度或者期望时间复杂度

##### 均摊

在代码执行的所有复杂度情况中，绝大部分是低级的复杂度，个别情况是高级复杂度且发生具有时序关系时，可以将个别高级复杂度均摊到低级复杂度上。基本上均摊的结果就等于低级别复杂度。

## 搜索 √

### 广度优先搜索（BFS）√

Bread first search

![这里写图片描述](https://img-blog.csdn.net/20180805163135680?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNjgxMjQx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

迷宫最短路径问题

定义一个二维数组：

~~~java
int maze[5][5] = {

0, 1, 0, 0, 0,

0, 1, 0, 1, 0,

0, 0, 0, 0, 0,

0, 1, 1, 1, 0,

0, 0, 0, 1, 0,
};
~~~

它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的最短路线。

~~~js
let maze = [
        [0,0,1,0,0,0,1,0],
        [0,0,1,0,0,0,1,0],
        [0,0,0,0,1,1,0,0],
        [0,1,1,1,0,0,0,0],
        [0,0,0,1,0,0,0,0],
        [0,1,0,0,0,1,0,0],
        [0,1,1,1,0,1,1,0],
        [1,0,0,0,0,0,0,0]
		];
		function migong (arr) {
			//解题的思想：以[0,0]为首节点建树，判断当前节点的上下左右四个方向是否有"路"可走，也就是是否有0，如果有，那么就作为它的子节点放入树中，然后将这个子节点中的0 改为-1（或其他数字），一遍循环过后就能确定“树”的下一行，继续此循环，最先找到“终点”的，肯定从首节点到此路径是最短的，也就是我们要求的啦！
			class Node {//像单链表一样的node节点，只不过它保存的是上一位的指针和数据域
				constructor (data) {
					this.prev = null;
					this.data = data;
				}
			}
			let maxX = arr[0].length, maxY = arr.length;//迷宫的墙壁处，用于判断是否碰壁，也用于判断是否到终点
			let x = 0, y = 0;//横纵坐标
			let finallyNode;//最终的节点，用于保存结果
			let headNode = new Node([x, y]);//头结点
			let nowNode = headNode;//当前节点
			let queue = [headNode];//用来存放这一行的节点，哪一行呢？就是图上最新发黑完整的那一行，用于遍历出这一行的每个节点的子节点
			cycle:while(true) {
				let smallQueue = [];//临时队列，用来存queue里面的所有节点的子节点，也就是说这是'树'中queue的下一行,for循环结束后，就要找它们的下一行了，所以最下面要将它赋值给queue
				for(let i = 0; i < queue.length; i ++) {//遍历队列
					nowNode = queue[i];
					x = nowNode.data[0];
					y = nowNode.data[1];
					if(x - 1 >= 0 &&arr[x - 1][y] == 0) {//上
						let node = new Node([x - 1, y]);//将该位置当做数据域
						node.prev = nowNode;
						smallQueue.push(node);//将该节点放入临时队列
						arr[x - 1][y] = -1;
						if(x - 1 == maxX - 1 && y == maxY - 1) {
							finallyNode = node;
							break cycle;
						}
					}
					if(x + 1 < maxX && arr[x + 1][y] == 0) {//下
						let node = new Node([x + 1, y]);
						node.prev = nowNode
						smallQueue.push(node);
						arr[x + 1][y] = -1;
						if(x + 1 == maxX - 1 && y == maxY - 1) {
							finallyNode = node;
							break cycle;
						}
					}
					if(y - 1 >= 0 && arr[x][y - 1] == 0) {//左
						let node = new Node([x, y - 1]);
						node.prev = nowNode
						smallQueue.push(node);
						arr[x][y - 1] = -1;
						if(x == maxX - 1 && y - 1 == maxY - 1) {
							finallyNode = node;
							break cycle;
						}
					}
					if(y + 1 < maxY && arr[x][y + 1] == 0) {//右
						let node =  new Node([x, y + 1]);
						node.prev = nowNode;
						smallQueue.push(node);
						arr[x][y + 1] = -1;
						if(x == maxX - 1 && y +1 == maxY - 1) {
							finallyNode = node;
							break cycle;
						}
					}
				}
				if(smallQueue.length == 0) return -1;//如果一个叶子结点也没有，那就证明走到了我们创建的树的底端，也就说明此时都没找到出口，那这个迷宫就是没有出口的
				queue = smallQueue;
			}
			//循环退出后，finallyNode就是出口坐标节点，它有一个prev属性指向着上一位，最后连着入口，所以遍历这条链表就可以了，然后反向插入result[]
			let result = [];
			while(true) {
				result.unshift(finallyNode.data);//向前插入元素
				if(finallyNode.data[0] == 0 && finallyNode.data[1] == 0) break;
				finallyNode = finallyNode.prev;
			}
			return result;
		}
		console.log(migong(maze));
~~~

**BFS的优缺点：**

- 优点：解决最短或最少问题很有效，而且寻找深度小，找到就是最优解；
- 缺点：内存耗量大，因为需要开辟大量的空间来存储状态



### 深度优先搜索（DFS）√

回溯算法与DFS的关系：回溯算法是DFS的一种应用，DFS更像是一种工具。

Depth first search

![这里写图片描述](https://img-blog.csdn.net/20180805184446735?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNjgxMjQx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

深度优先遍历：

> A --> B --> E --> F --> C --> D --> G

**八皇后问题：**

 在8*8的国际象棋棋盘上，要求在每一行(或者每一列)放置一个皇后，且能做到在水平方向、竖直方向和斜方向都没有冲突。请列出所有解法。 

~~~js
function theQueen(num) {
    // let aRowChessBoard = new Array(num).fill(0);
    // let chessBoard = new Array(num).fill(aRowChessBoard);//这种方法是不可取的，因为aRowChessBoaord是一个对象，你fill进去的是一个引用，最后导致大数组里面的每个小数组都是同一个数组
    
    // let chessBoard = [[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0]];//如果只算八皇后可以这样来减小算法的时间复杂度
    //let aRowChessBoard = [];
    let chessBoard = [];//8*8的数组一定要这样建
    for(let i = 0; i < num; i ++) {
        let aRowChessBoard = [];
        for(let j = 0; j < num; j ++) {
            aRowChessBoard[j] = 0;
        }
        chessBoard[i] = aRowChessBoard;
    }
    let total = 0;//一共多少种方法总计

    findQueen(0);//找皇后
    return total;

    //找row行的皇后
    function findQueen(row) {//这个函数是灵魂所在，也是难点！！！
        // console.log('num',num);
        if(row > num - 1) {//如果行数大于最后一行的位置（本例中为7），就说明找到了一种方法
            total ++;
            console.log(total)
            // printOne();//输出当前的一种方法
            return;//退出函数
        }
        for(let col = 0; col < num; col ++) {//找row行的8个列，依次从左到右找可以放皇后的地方
            if(check(row,col)) {//如果找到了可以放皇后的地方
                chessBoard[row][col] = 1;//将放皇后的地方设为1
                findQueen(row + 1);//趁热找下一行的皇后位置，递归算法
                chessBoard[row][col] = 0;//这里很精髓，如果上面一行一直递归，永远执行不到这一行，如果有一个row使上面一行执行完了一整个函数（也就是说没有在if里面break，而且在下面的for循环循环完成也没找到check[row, col]为true的位置），那么就说明row行下面一行没有能放皇后的位置，所以说此时的[row,col]不能放皇后，这样是行不通的，所以要将上一行的上一行赋值为1改回来赋值为0，然后执行结束（不一定此次之行findQueen函数在哪次递归里面呢）
            }
        }
    }

    //判断一个位置是否可以方皇后
    function check(row, col) {
        //这里有一个优化点，正常的思路肯定是查找此位置的“米”字型方位是否有1，如果有1则返回false，如果没有则返回true，但是我们可以想到，其实“米”字的下半身此时并没有皇后，所以不可能出现1，而且每一行只能有一个皇后，所以它所在的这一行也不可能出现1，所以只要判断“米”字上半身就可以了
        //判断“半竖”
        for(let i = 0; i < row; i ++) {
            if(chessBoard[i][col] == 1)
                return false;
        }
        //判断“半丿半捺”，双指针法
        let L = col - 1, R = col + 1;
        for(let i = row - 1; i >= 0; i --) {
            if(chessBoard[i][L] == 1 || chessBoard[i][R] == 1)
                return false;
            if(L >= 0) L --;
            if(R < num) R ++;
        }
        //上面条件都满足
        return true;
    }
    //输出当前方法
    function printOne() {
        console.log(chessBoard);
    }
}


console.log(theQueen(8));//八皇后
~~~

优秀的解析八皇后问题的文章：

 https://www.jianshu.com/p/65c8c60b83b8 

**DFS的优缺点：**

- 对于解决遍历和求所有问题有效，对于深度小的问题处理迅速，然而深度很大的情况下效率并不高。

## 查找 （ ！）

### 顺序查找 √

**介绍：**

​	顺序查找属于**无序查找**算法。

**时间复杂度分析：**

​	O(n)

**代码实现：**

~~~js
function orderFind(arr, n) {
    let len = arr.length;
    for(let i = 0; i < len; i ++) {
        if(arr[i] == n) {
            return i;
        }
    }
    return -1;
}
~~~

**适用范围：**

适用于存储结构为顺序存储或链接存储的线性表。

### 分块查找 √

又称索引顺序查找，它是顺序查找的一种改进方法。

**算法思想：**

将数据划分为m块儿（类似于桶排序的思想），每块中的节点不必有序，但块与块之间必须“按块有序”，即第一块中任意元素都必须小于第二块儿中任意元素。然后找到每一块中的最大值，把最大值放一个数组中，用二分查找或者顺序查找，找到精确的哪一块，然后在已确定的块中用顺序法进行查找。

### 二分查找√

**介绍：**

也称折半查找，属于**有序的查找算法**。

前提是元素必须是有序的。

**时间复杂度分析：**

​	O(logn)

**代码实现：**

~~~js
let arr = [1,2,3,4,5,6,7,8,9], n = 5;
function BinarySearch (arr, n) {
    let len = arr.length, low, high, mid; 
    for(let i = 0; i < len; i ++) {
        low = 0, high = len - 1;
        while(low < high) {
            mid = parseInt((low + high) / 2);
            if(arr[mid] == n) {
                return mid;
            } else if(arr[mid] > n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
console.log(BinarySearch(arr,n));
~~~

**适用范围：**

适用于有序的静态查找表的查找；

对于需要频繁执行插入和删除操作的数据集来说，维护有序的排序会带来不小的工作量，那就不建议使用。	——《大话数据结构》

### 差值查找（  ！）

**情景带入：:happy:**

是否有这样一种情况，如果数组是从1到100的100个数字，让你查找5，那么人为的思想肯定是从前往后找。如果用二分法查找呢？50,25,12,6,3,4,5这样一个顺序来找，是不是就有点憨憨，哈哈哈​ :happy:

**介绍：**

二分法查找点的计算：

mid = low + 1/2 * (hide -  low);

通过类比，我们可以查找的点改进如下：

**mid = low + (n - arr[low]) / (arr[high] - arr[low]) * (high - low);(这里不理解)**

也就是将上述的1/2改为字适应的值，根据关键字在整个有序表中所处的位置，让mid值的变化更靠近要查找的数值n，这样也就间接的减少了查找次数。

**时间复杂度分析：**

查找成功或者失败的时间复杂度均为**O(loglogn)**。

**代码实现：**

~~~js
let arr = [1,3,4,6,7,9,11,13,14,15,16];
let n = 2;
function interpolationFind(arr, n) {
    let time = 0;
    let low, high, mid, len = arr.length;
    low = 0, high = len - 1;
    while(low < high) {
        console.log('比较次数：', ++ time);
        //这里的parseInt只能放这个位置，放其他位置会导致出错，亲测！
        mid = low + parseInt((n - arr[low]) / (arr[high] - arr[low]) * (high - low));
        console.log(mid);2
        // mid = low + parseInt((high - low) / 2);
        if(arr[mid] == n) {
            return mid;
        } else if (arr[mid] > n) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return -1;
}

console.log('n的位置：', interpolationFind(arr, n));
~~~

**适用范围：**

对于表长较大，数据分布比较均匀的查找表来说，差值查找比二分查找性能要好的多。

反之，如果数组数据分布非常不均匀，那么差值查找未必是个合适的选择。

### 斐波那契查找（ ！）



### 树表查找（ ！）

#### 二叉树查找算法

**介绍：**

先对需要排序的数据进行生成树，确保树左分支的值小于右分支的值，然后进行查找。

这种算法的查找效率很高，但是要先创建树。



**二叉查找树**（又称二叉搜索树、二叉排序树）的性质：

- 若其左子树不空，则数值小于根节点值
- 若其右子树不空，则数值大于根节点值
- 任意节点的左右子也为二叉查找树

**对齐进行中序遍历（或叫中根遍历），即可得到有序序列。**

拓展：

- 先序遍历：根-->左-->右
- 中序遍历：左-->根-->右
- 后序遍历：左-->右-->根

**时间复杂度分析：**

它和二分查找一样，插入和查找的时间复杂度均为**O(logn)**，**但在最坏的情况下仍然有O(n)的时间复杂度**，原因在于插入和删除元素的时候树并没有保持平衡，比如在下图中查找17，我们就需要进行n次操作（因为此树并不平衡）。

我们追求的是在最坏的情况下仍然有较好的时间复杂度，这就是平衡查找树设计的初衷。

![img](https://pic1.zhimg.com/80/v2-f28b193d1589c4cfb184d48fb2080058_hd.jpg)

#### 2-3查找树

直接上图，简单明了：

![img](https://pic1.zhimg.com/80/v2-6ccac7896663c7720586999426e1dcd8_hd.jpg)

这就是2-3查找树。

**性质：**

- 如果中序遍历2-3查找树，则可以得到有序序列；
- 在一个完全平衡的2-3查找树找那个，根节点到每一个为空的节点的举例都相同。（这也是平衡树中“平衡”的概念。

**复杂度分析：**

2-3查找树的查找效率与树的高度是息息相关的。

- 最坏的情况下，也就是所有的节点都是2-node节点，查找效率为log2n
- 最好的情况下，也就是所有的节点都是3-node节点，查找效率为log3n，约等于0.631log2n.

#### 红黑树

它是一种简单实现2-3树的数据结构。

红黑树具体内容参见树篇

#### B树和B+树

同上，在树篇会有深入的讲解。

#### 树表查找总结：

二叉查找树平均性能不错，为O(logn)，但最坏的情况下会退化为O(n)。

在二叉查找树的基础上进行优化，我们可以使用平衡查找树。

平衡查找树的2-3查找树，这种数据结构在插入图之后能够进行自平衡处理，从而保证了树的高度在一定的范围内，最坏的情况的时间复杂度也有了保证。

但是2-3查找树实现起来比较困难，红黑树是2-3树的一种简单高效的实现，它巧妙地使用颜色标记来替代2-3树种比较难处理的3-node节点问题。

**红黑树是一种比较高效的平衡查找树，应用非常广泛**，很多编程语言的内部实现都或多或少的采用了红黑树。

除此之外，2-3查找树的另一个扩展——B/B+平衡树，在文件系统和数据库系统中有着广泛的应用。

### 哈希查找（散列查找）√

**算法流程：**

1. 用给点的哈希函数构造哈希表
2. 根据选择的冲突处理方法解决地址冲突
3. 在哈希表的基础上执行哈希查找

哈希表示一个在时间和空间上做出权衡的经典例子。如果没有内存限制，可以直接将键作为数组的索引，那么所有的查找时间复杂度为（1）；如果没有时间限制，那么我们可以使用无序数组并进行顺序查找，这样只需要很少的内存。哈希表使用了适度的时间和空间赖在这两个极端之间找到了平衡。只需要调整哈希函数算法即可在时间和空间上作出或取舍。

**复杂度：**

单纯论查找复杂度：对于无冲突的Hash表而言，查找复杂度为O(1)，但是要注意我们在这之前要构建Hash表。

**使用Hash，我们付出了什么？**

Hash是一种典型的以空间换时间的算法。

比如在有一个长度为100的数组，占用了100byte的空间，那么我们需要一个固定长度的Hash表，仍然是100byte的数组，假设我们需要的100byte用来记录键与位置的关系，那么总空间为200byte，而且用于记录规则的表大小会根据规则，大小可能是不定的。

举例：

~~~java
//输入一个数组arr和一个数字num，找到该数组arr中的两个数之和为num的两个数
//如果此题用暴力解决方法一定不是个好选择，哈希查找就是牺牲空间换取时间，时间复杂度在理想情况下是O(1)
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();//创建hash表
        for(int i = 0; i < nums.length; i ++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {//hash查找
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);//向哈希表中插入key-value
        }
    }
    throw new IllegalArgumentException("No two solution");
}
~~~





## 字符串匹配（ ！）

字符串匹配问题的形式定义：

- **文本（Text）**是一个长度为 n 的数组 T[1..n]；
- **模式（Pattern）**是一个长度为 m 且 m≤n 的数组 P[1..m]；
- T 和 P 中的元素都属于有限的**字母表 Σ 表**；
- 如果 0≤s≤n-m，并且 T[s+1..s+m] = P[1..m]，即对 1≤j≤m，有 T[s+j] = P[j]，则说模式 P 在文本 T 中出现且位移为 s，且称 s 是一个**有效位移（Valid Shift）**。

![img](https://images0.cnblogs.com/blog/175043/201410/032208464871566.png)

比如上图中，目标是找出所有在文本 T = abcabaabcabac 中模式 P = abaa 的所有出现。该模式在此文本中仅出现一次，即在位移 s = 3 处，位移 s = 3 是有效位移。

### BF算法 （暴力匹配） √

也叫朴素的字符串匹配算法。

是最常想到的，也是最好实现的，所以在简单情况下可以直接使用。

首先从原字符串最左端开始匹配子字符串，如果第一个字符与子字符串匹配，则继续看第二个字符与子字符串第二个字符是否匹配。。。如果不匹配，则找原字符串下一位与子字符串第一位相匹配，以此类推

~~~js
let arr = 'hello world';
		let sunArr = 'or';
		function matchingStr (arr, sunArr) {
			for(let i = 0; i < arr.length - sunArr.length + 1; i ++) {
			//如任意个长度的查找3个长度的子串，大字符串的最后3-1个长度没必要比的
				let j = 0;//用来判断查找的长度
				while(j < sunArr.length) {//只要查找长度不大于子串长度就可以继续比
					if(arr[i + j] != sunArr[j]) break;//循环的过程中只要有一个字符不符合，就退出
					j ++;//下一位
				}
				if(j == sunArr.length) return i;//完全符合
			}
			reutrn -1;
		}
		console.log(matchingStr(arr,sunArr));
~~~

其实一个循环就可以解决：

~~~js
function matchingAtr(arr, sunArr) {
			let i = 0, j = 0;//i表示arr匹配的元素位置，j为sunArr匹配的元素位置
			while(i < arr.length && j < sunArr.length) {
				if(arr[i] == sunArr[j]) {
					i ++;
					j ++;
				} else {
					i = i - j + 1;//因为要下一位，所以+1
					j = 0;
				}
			}
			if(j == sunArr.length) {
				return i - j;
			} else {
				return -1;
			}
		}
		console.log(matchingAtr('hello world', 'rld'));
~~~



### KMP算法 √

下面，我用自己的语言，试图写一篇比较好懂的KMP算法解释。

1.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050103.png)

首先，字符串"BBC ABCDAB ABCDABCDABDE"的第一个字符与搜索词"ABCDABD"的第一个字符，进行比较。因为B与A不匹配，所以搜索词后移一位。

2.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050104.png)

因为B与A不匹配，搜索词再往后移。

3.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050105.png)

就这样，直到字符串有一个字符，与搜索词的第一个字符相同为止。

4.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050106.png)

接着比较字符串和搜索词的下一个字符，还是相同。

5.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050107.png)

直到字符串有一个字符，与搜索词对应的字符不相同为止。

6.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050108.png)

这时，最自然的反应是，将搜索词整个后移一位，再从头逐个比较。这样做虽然可行，但是效率很差，因为你要把"搜索位置"移到已经比较过的位置，重比一遍。

7.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050107.png)

一个基本事实是，当空格与D不匹配时，你其实知道前面六个字符是"ABCDAB"。KMP算法的想法是，设法利用这个已知信息，不要把"搜索位置"移回已经比较过的位置，继续把它向后移，这样就提高了效率。

8.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050109.png)

怎么做到这一点呢？可以针对搜索词，算出一张《部分匹配表》（Partial Match Table）。这张表是如何产生的，后面再介绍，这里只要会用就可以了。

9.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050107.png)

已知空格与D不匹配时，前面六个字符"ABCDAB"是匹配的。查表可知，最后一个匹配字符B对应的"部分匹配值"为2，因此按照下面的公式算出向后移动的位数：

> 　　移动位数 = 已匹配的字符数 - 对应的部分匹配值

因为 6 - 2 等于4，所以将搜索词向后移动4位。

10.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050110.png)

因为空格与Ｃ不匹配，搜索词还要继续往后移。这时，已匹配的字符数为2（"AB"），对应的"部分匹配值"为0。所以，移动位数 = 2 - 0，结果为 2，于是将搜索词向后移2位。

11.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050111.png)

因为空格与A不匹配，继续后移一位。

12.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050112.png)

逐位比较，直到发现C与D不匹配。于是，移动位数 = 6 - 2，继续将搜索词向后移动4位。

13.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050113.png)

逐位比较，直到搜索词的最后一位，发现完全匹配，于是搜索完成。如果还要继续搜索（即找出全部匹配），移动位数 = 7 - 0，再将搜索词向后移动7位，这里就不再重复了。

14.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050114.png)

下面介绍《部分匹配表》是如何产生的。

首先，要了解两个概念："前缀"和"后缀"。 "前缀"指除了最后一个字符以外，一个字符串的全部头部组合；"后缀"指除了第一个字符以外，一个字符串的全部尾部组合。

15.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050109.png)

"部分匹配值"就是"前缀"和"后缀"的最长的共有元素的长度。以"ABCDABD"为例，

> 　　－　"A"的前缀和后缀都为空集，共有元素的长度为0；
>
> 　　－　"AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；
>
> 　　－　"ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
>
> 　　－　"ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
>
> 　　－　"ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；
>
> 　　－　"ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2；
>
> 　　－　"ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。

自己的看法：

其实这样实现起来个人觉得会很吃力，我倒有一个简易一点的算法：

- 新建一个“部分匹配值”数组pmv = [] （partial matching value）；

- 从第二个元素开始for遍历搜索词，看是否与第一个元素相等，如果不相等，则将pmv的此位置设为0，i++进入下一for循环；如果二者相等，则进入一个while循环，先将pmv的此位置值设为1，然后比较当前位置的下一位是否与第二位相等，如果相等则将pmv的此位置设为2，直到不相等，退出while循环，继续for循环。    for循环完成后将pmv返回即为所求！

~~~js
function computePMV(str) {
			let pmv = [0];
			for(let i = 1; i < str.length;) {
				let j = 0, time = 1;
				if(str[i] != str[j]) {
					pmv[i] = 0;
					i ++;
				} else {
					while(str[i] == str[j]) {
						pmv[i] = time;
						i ++;
						j ++;
						time ++;
					}
				}
			}
			return pmv;
		}
		console.log(computePMV('abcdabd'));
~~~

16.

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050112.png)

**"部分匹配"的实质是，有时候，字符串头部和尾部会有重复。比如，"ABCDAB"之中有两个"AB"，那么它的"部分匹配值"就是2（"AB"的长度）。搜索词移动的时候，第一个"AB"向后移动4位（字符串长度-部分匹配值），就可以来到第二个"AB"的位置。**

**KMP算法的时间复杂度为：O(m+n)**

实现KMP算法：

~~~js
//求next[]
function computePMV(str) {
    let pmv = [0];
    for(let i = 1; i < str.length;) {
        let j = 0, time = 1;
        if(str[i] != str[j]) {
            pmv[i] = 0;
            i ++;
        } else {
            while(str[i] == str[j]) {
                pmv[i] = time;
                i ++;
                j ++;
                time ++;
            }
        }
    }
    return pmv;
}
let bigArr = 'bbc abcdab abcdabcdabde'
let smallArr = 'abcdabd';
function KMP(bigArr,smallArr) {
    let next = computePMV(smallArr);
    for(let i = 0, j = 0; i < bigArr.length; ) {
        if(bigArr[i] != smallArr[j] && j == 0) i ++;//j不等于0的时候i不能动
        while(bigArr[i] == smallArr[j]) {//如果元素相等则开始while循环判断后续
            i ++;
            j ++;
        }
        if(j == smallArr.length) return i - j;
        if(j != 0) j = next[j - 1];
    }
    return -1;
}
console.log(KMP(bigArr, smallArr));
~~~



### BM算法 （ ！）

 一般文本编辑器中的查找功能都是基于它实现的 。

基于它的原理，通常搜索关键字越长，算法速度越快。

它的效率来自这样一个事实：

- **对于每一次失败的匹配尝试，算法都能够使用这些信息来排除尽可能多的无法匹配的位置**。

它是基于以下两个规则让模式串每次向右移动 **尽可能大** 的距离。

- 坏字符规则（**bad-character shift**）：当文本串中的某个字符跟模式串的某个字符不匹配时，我们称文本串中的这个失配字符为坏字符，此时模式串需要向右移动，移动的位数 = 坏字符在模式串中的位置 - 坏字符在模式串中最右出现的位置。此外，如果"坏字符"不包含在模式串之中，则最右出现位置为 -1。**坏字符针对的是文本串。**
- 好后缀规则（**good-suffix shift**）：当字符失配时，后移位数 = 好后缀在模式串中的位置 - 好后缀在模式串上一次出现的位置，且如果好后缀在模式串中没有再次出现，则为 -1。**好后缀针对的是模式串。**

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOUibOB9A3HchUOsMX1ibevb9veM0mKibFNVOuYbKQdXaKDqPtZkNwgKM0A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

#### **坏字符规则**



坏字符出现的时候有两种情况进行讨论。

1、**模式串中没有出现了文本串中的那个坏字符**，将模式串直接整体对齐到这个字符的后方，继续比较。

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSO9j5L5wxLnfOb1ibL9ibIRtB0tPhY4yZib7gJQLHTrDqq48wz68t5jnibww/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOWVtlia4Xu9fA2rEzVG5sHxXLmAicQfx8C1WGWLt2zMlYkINbxOWibibVmQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

2、模式串中有对应的坏字符时，让模式串中 **最靠右** 的对应字符与坏字符相对。

这句话有一个关键词是 **最靠右**。

思考一下为什么是 **最靠右**？

看图！

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOibtrP4xfFOYWzibIkxS3ibvs4XMiaLWqBu8UZuicuFHeuZKf0PuZ8Awhh2w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOjaiaIl70JvWzIskn1gMP7bmoRPicfiaaQLNA4JY505hOhRvxhdVEoibATA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOribLfMfdwkp89IoKic2bvBFEdIBib3EpbFJ8ZBYicrL1xibcjrATUtoPHpQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSO7Zvp4aMTeYGJkz3Xd4mDfOozykwRL9F1fFhIib4MQzO9x8ABGtCq0vA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

**坏字符规则实现：**

~~~js

~~~





#### 好后缀规则



1、如果模式串中存在已经匹配成功的好后缀，则把目标串与好后缀对齐，然后从模式串的最尾元素开始往前匹配。

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOXYADHf5OZYe8vmQwfCQJKxxgkoprYZkFtdMzMeS10quic74CzWloICQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOtib5Uq1tEeSGQwUVnFrZAiafdHoZiaxwu6FgrTcOROjaGkibx74dlBpRCw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

2、如果无法找到匹配好的后缀，找一个匹配的最长的前缀，让目标串与最长的前缀对齐（如果这个前缀存在的话）。**模式串[m-s，m] = 模式串[0，s]** 。

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOC3vGs2J6z1S9SJorJDpg0E3fqiaWedBylvaKINamV4sKCITWdERueYw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![image](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQtsCvsEDTRu5N8R4tNFKSOQhb7twqBicB5icibbbgYqeicZmdBrHdrLriaVicBohm2lFoibPFsBWVdrY9Ng/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

3、如果完全不存在和好后缀匹配的子串，则右移整个模式串。





### Trid树（字典树）√

- Trie树，也叫字典树、字母树、前缀树，它是一种树形结构。是一种专门处理字符串匹配的数据结构，**用来解决在一组字符串的集合中快速查找某字符串**
- Trie树本质，利用字符串之间的公共前缀，将重复的前缀合在一起。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190624192438469.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzIxMjAxMjY3,size_16,color_FFFFFF,t_70)

比如在 cod,  code,  cook,  five,  file,  fat   这个字符串集合中查找某个字符串是否存在。如果每次查找都依次比较的话，效率会很低，但是用Trid树来解决就会更高效。

建树：

![img](https://pic3.zhimg.com/80/v2-d82b7d102ad949dce0bfb92af3d41a11_hd.jpg)

建树的过程中，将字符串的最后一个字符标记为**橙色**。

然后查找的时候，查到最后要判断最后字符是否为橙色，如果不是橙色然后也查找到了，那就说明它只是一个单词的中间子串，所以结果为不存在此字符串。

通过上图，可以发现**Trid树的三个特点**：

- 根节点不包括字符，除根节点外，每一个节点都只包含一个字符；
- 从根节点到某一节点，路径上字符连接起来，即为该节点的字符串；
- 每个节点的所有子节点包含的字符都不相同。



**Trie树的插入操作：**

比如要插入新单词cook，就有下面几步：

- 插入第一个字母 c，发现 root 节点下方存在子节点 c，则共享节点 c
- 插入第二个字母 o，发现 c 节点下方存在子节点 o，则共享节点 o
- 插入第三个字母 o，发现 o 节点下方不存在子节点 o，则创建子节点 o
- 插入第三个字母 k，发现 o 节点下方不存在子节点 k，则创建子节点 k
- 至此，单词 cook 中所有字母已被插入 Trie树 中，然后设置节点 k 中的标志位，



**Trie树的删除操作：**

- 从根节点开始查找第一个字符h
- 找到h子节点后，继续查找h的下一个子节点i
- i是单词hi的标志位，将该标志位去掉
- i节点是hi的叶子节点，将其删除
- 删除后发现h节点为叶子节点，并且不是单词标志位，也将其删除
- 这样就完成了hi单词的删除操作

**Trie树的局限性** 

如前文所讲，**Trie的核心思想是空间换时间**，利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。 假设字符的种数有m个，有若干个长度为n的字符串构成了一个 Trie树 ，则每个节点的出度为 m（即每个节点的可能子节点数量为m），Trie树 的高度为n。很明显我们浪费了大量的空间来存储字符，此时Trie树的最坏空间复杂度为O(m^n)。也正由于每个节点的出度为m，所以我们能够沿着树的一个个分支高效的向下逐个字符的查询，而不是遍历所有的字符串来查询，此时Trie树的最坏时间复杂度为O(n)。 这正是空间换时间的体现，也是利用公共前缀降低查询时间开销的体现。

实现Trie树：

~~~js
		//Trie树
		let charArr = ['cod', 'code', 'cook', 'five', 'file', 'fat'];
		let patternStr = 'cod';
		//建Trie树
		function insertHash(tree, patternStr) {//向Tree树中插入字符
			// let hashHead = tree;
			let pLen = patternStr.length;
			let nowNode = tree;
			for(let i = 0; i < pLen; i ++) {
				let now = patternStr[i];//要比较的字符
				if(nowNode[now] == undefined) {//不存在此字符
					let  hash = {orange: false};
					nowNode[now] = hash;//新建一个hash给patternStr
				}
				if(i == pLen - 1) //如果是字符的最后一位，则变为橙色
					    nowNode[now].orange = true;
				nowNode = nowNode[now];
			}
		}
		//查找模式串是否在字符集中
		function Trie(charArr,patternStr) {
			//根据charArr建树
			let TrieTree = {};//Trie树
			let len = charArr.length;
			for(let i = 0; i < len; i ++) {
				insertHash(TrieTree, charArr[i]);
			}
			// console.log(TrieTree);//哈哈哈我真是太棒了！！
			//根据Trie树查找模式串
			let nowNode = TrieTree;//当前比较的
			for(let i = 0; i < patternStr.length; i ++) {
				let now = patternStr[i];
				if(nowNode[now]  != undefined) //说明有这个字符
					nowNode = nowNode[now];
				else //没有这个字符则返回没找到，false
					return false;
			}
			//循环完之后没有return，则说明Trie树中有此字符，但也有可能是其他字符的子串，所以要看字符最后一位的orange是否为true
			if(nowNode.orange == false) 
				return false;
			else 
				return true;
		}
		console.log(Trie(charArr, patternStr));
~~~



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
			} else(rightArr[j] > leftArr[i]) {
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

一篇好文章：

代码实现：

~~~js
//快速排序
	function fastSort(arr, low ,high) {
		if(arr.length == 0) return arr;
		//思路：看上面的链接
		//处理一下只有一个参数的情况，也就是最底下调用的时候
		if(low == undefined && high == undefined) {
			low = 0;
			high = arr.length - 1;
		}
		if(low < high) {
			//寻找基准数据的索引位置，返回回来
			let index = getIndex(arr, low ,high);

			fastSort(arr, 0, index - 1);//此时调用是3个参数
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

## 其他

### 查并集

### 迪杰斯塔拉

### 拓扑





# Java——泛型 √

## 一、Java泛型基本理解

java泛型其实你可以将它理解为一种强大的工具，比如你要写一个排序方法，要求该方法能够同时对整型数组、字符型数组甚至别的任何类型进行排序，一个方法就要实现这样的不同类型的排序；按常理来说这个要求需要写多个方法，分别来对他们排序，但是不用，java泛型可以用一个方法就实现这些要求。

字面理解就是：用户可以给出任意的参数类型，我都能有这个方法处理你的参数。

## 二、java泛型之泛型方法

### 1.普遍的泛型方法

泛型方法可以接受用户提供的不同类型的参数，下面是定义泛型方法的规则：

a. 所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前。

b. 每一个类型参数声明部分把傲寒一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，适用于指定一个泛型类型名称的标识符

c. 类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符。

d. 泛型方法体的声明和其他方法一样。注意类型参数只能代表引用型类型，不能是原始类型（如int，double，clar等），所以才有了之前看不懂的E，T之类的类型。

实例：

用泛型方法打印不同字符串的元素：

~~~java
//普遍的泛型方法
public class test {
	//泛型方法
	public static < E > void printElement(E[] array) {//这里的E表示可以输入任何类型的数组
		//输出数组元素
		for(E element : array) {//E类型的element用来遍历array
			System.out.println('%s', element);
		}
		System.out.println();
	} 

	public static void main (String[] args) {
		//创建不同类型的数组
		Integer[] intArray = {1,2,4,66,7,9};
		Double[] doubleArray = {1.1,2.2,4.4,6.6,7.7,9.9};
		Character[] charArray = {'H', 'E', 'L', 'L', 'O'};

		//正常调用泛型方法
		System.out.println('整型数组元素为：');
		printElement(intArray);//传递一个整型数组

		System.out.println('\n双精度型数组元素为：');
		printElement(doubleArray);

		System.out.println('\n字符型数组元素为：');
		printElement(charArray);
	}
}
~~~

### 2.深入了解java泛型的泛型方法

java泛型的泛型方法不仅是可以接受任何类型的参数，他还可以将泛型方法定义为只接收部分类型的参数，**这就是有界类型参数**。

要声明一个有界类型参数，首先列出类型参数的名称（如T），然后名称后跟extends关键字后紧跟着这个参数的上界；如：<T extends Compareble>，它的意思是只接收那种可以用来比较的类型。

下面用实例详细说明，下面的例子中的泛型方法返回三个可比较对象的最大值。

~~~java
//泛型有界类型参数
pubic class MaxiumTest {
	//比较三个值并返回最大值
	public static <T extends Comparable<T>> maxium(T x, T y, T z) {
		//它的意思是只接收那种可以比较大小的参数类型
		//下面只是普通的比较算法
		T max = x;
		if(y.compareTo( max ) > 0) {//number.compareTo(number)判断参数与指定的数的大小，如果参数与指定的数相等返回0；参数比指定的数大则返回-1；参数比指定的数小则返回1；
			max = y;
		}
		if(z.compareTo( max ) > 0) {
			max = z;
		}
	}
	public static void main (String[] args) {
		System.out.pringln('%d,%d,%d中最大的数为：%d\n', 3,6,8,maxium(3,6,8));
		System.out.pringln('%.1f,%.1f,%.1f中最大的数为：%.1f\n', 3.3,6.3,8.3,maxium(3,6,8));
		System.out.pringln('%s,%s,%s中最大的数为：%s\n', 'apple','banana','orange',maxium('apple','banana','orange'));
	}
}
~~~

## 三、java泛型之泛型类

### 1.基本的泛型类

泛型类和非泛型类声明差不多，泛型类在非泛型类的声明基础之上添加了类型参数的声明。此类型参数的声明和泛型方法的参数声明方式一样。

下面是泛型类的定义实例：

~~~java
//基本的泛型类
public class GenericClass<T> {
	private T t;

	public void setValue(T t) {
		this.t = t;
	}
	public T getValue() {
		return t;
	}

	public static void main(String[] args) {
		GenericClass<Integer> intValue = new GenericClass<Integer>();
		GenericClass<String> strValue = new GenericClass<String>();

		intValue.add(new Integer(3));
		strValue.add(new String('hello'));

		System.out.printf("整数类型实例化：%d\n",intValue.get());
		System.out.printf("字符串类型实例化：%d\n", strValue.get());

	}
}
~~~

### 2.深入了解泛型类之类型通配符

用问号"?"代替具体的类型参数，如List<?>.

下面用实例展示类型通配符的用法：

~~~java
import java.util.*;

public class GenericTest {
	public static void main(String[] args) {
		//用同一个方法实例化了三种不同类型的对象。因为getData()方法的参数是List类型，而name，age，number都是List的一种，所以三者都可以作为这个方法的实参，这就是类型通配符的好处。
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();

		name.add('jignwei');
		age.add(18);
		number.add(15532779298);

		getData(name);
		getData(age);
		getData(number);
	}
	
	public static void getData(List<?> data) {//类型通配符的用法
		System.out.println('data:' + data.get(0));
	}	
}
~~~

### 3.类型通配符的上下限

和泛型方法的有界类型参数类似。

类型通配符的上限用extends关键字，如List<? extends Number>，它的意思是通配符泛型值接收Number及其下层的子类类型（接收是有上限的，比接收Number的父类）；

类型通配符的上限用super关键字来定义，如List<? super Number>，表示通配符泛型值只接收Number及其三层父类类型，如Object类型的实例。

实例：

~~~java
public class GenericTest {
	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();

		name.add('jingwei');
		age.add(18);
		number.add(15532779298);

		//getUperNumber(name);//该行代码会导致错误，因为String不是Number的子类型
		getUperNumber(age);
		getUperNumber(number);
	}
	public static void getData(List<?> data) {
		System.out.pringln('data:' + data.get(0));
	}

	publilc static void getUperNumber(List<? extends Number> data) {
		//List<? extends Number> 定义上限为Number类型
		System.out.pringln("data:" + data.get(0));
	}
}
~~~

















