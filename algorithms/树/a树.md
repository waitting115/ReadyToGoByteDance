## 树 √

树是n个节点的有限集。

**普通的树：**

![img](https://upload-images.jianshu.io/upload_images/7043118-2c735a2733887dc3.png?imageMogr2/auto-orient/strip|imageView2/2/w/502/format/webp)

**节点的度：**

![img](https://upload-images.jianshu.io/upload_images/7043118-cfa7c45bb8f1e332.png?imageMogr2/auto-orient/strip|imageView2/2/w/535/format/webp)

**节点的关系：**

孩子节点、双亲结点、兄弟节点。

**节点层次：**

![img](https://upload-images.jianshu.io/upload_images/7043118-7c9318a6f5c1349d.png?imageMogr2/auto-orient/strip|imageView2/2/w/652/format/webp)

**树的深度：**

树中节点的最大层次数即树的深度。

### 二叉树 √

![img](https://upload-images.jianshu.io/upload_images/7043118-797eb7ba417745b2.png?imageMogr2/auto-orient/strip|imageView2/2/w/455/format/webp)

**二叉树的特点：**

- 每个节点最多有两棵子树
- 左子树和右子树是有顺序的，不能随意颠倒
- 即使某节点只有一棵子树，也要分左、右子树

**二叉树的性质：**

- 二叉树的第i层最多有 2**(i-1)个节点
- 二叉树深度为k，那么最多有2**(k) - 1个节点
- 在完全二叉树中，具有n个节点的完全二叉树的深度为[log2n] + 1，其中[log2n]向下取整
- 若对有n个节点的完全二叉树从上到下、从左到右依次由1~n编号，则对任意一个i节点有如下性质：
  - 若i = 1，则为根节点，无双亲；若i ！= 1，则编号i/2为其双亲节点
  - 若2i > n，则该节点无左孩子，否则，编号为2i的节点为其左孩子节点
  - 若2i + 1 > n，则该节点无右孩子，否则，编号为2i + 1的节点为其右孩子节点

#### **斜树 √**

- 所有的节点都只有左节点的二叉树叫左斜树
- 所有的节点都只有右节点的二叉树叫右斜树
- 二者统称为斜树

![img](https://upload-images.jianshu.io/upload_images/7043118-a512316455261ec7.png?imageMogr2/auto-orient/strip|imageView2/2/w/373/format/webp)

![img](https://upload-images.jianshu.io/upload_images/7043118-352190ff8558efcb.png?imageMogr2/auto-orient/strip|imageView2/2/w/342/format/webp)

#### **完全二叉树 √**

![img](https://upload-images.jianshu.io/upload_images/7043118-132fd0379f34bcc1.png?imageMogr2/auto-orient/strip|imageView2/2/w/404/format/webp)

**特点：**

- 叶子结点只能出现在最底层和次底层
- 最底层的叶子结点集中在左部
- 倒数第二层如果有叶子结点，则一定在右部
- 如果某节点的度为1，则它一定只有左孩子，没有右孩子
- 同样节点数目的二叉树，完全二叉树层数最小

**满二叉树一定是完全二叉树，反过来则不一定成立**



#### **满二叉树 √**

所有分支节点都有左子树和右子树，而且所有叶子结点都在同一层上的二叉树叫满二叉树

**满二叉树的特点：**

- 叶子结点只能出现在最底层，出现在其它层就不可能达到平衡
- 非叶子结点的度一定为2
- 在同样深度的二叉树中，满二叉树的节点个数最多，叶子结点个数最多

![img](https://upload-images.jianshu.io/upload_images/7043118-c7a557dda4ffc7da.png?imageMogr2/auto-orient/strip|imageView2/2/w/392/format/webp)

#### **二叉树的存储结构 √**

- **顺序存储**
  - 二叉树的顺序存储结构就是使用一维数组来存储二叉树中的节点，并且节点的存储位置，就是数组的下标

![img](https://upload-images.jianshu.io/upload_images/7043118-3293242769696303.png?imageMogr2/auto-orient/strip|imageView2/2/w/441/format/webp)

上图的完全二叉树采用顺序存储的方式，如下图：

![img](https://upload-images.jianshu.io/upload_images/7043118-e916580c061a1139.png?imageMogr2/auto-orient/strip|imageView2/2/w/596/format/webp)

上图可看出，如果树为完全二叉树，则节点刚好填满数组。

当二叉树不为完全二叉树时采用顺序存储：

![img](https://upload-images.jianshu.io/upload_images/7043118-92d8a8d61c2aace7.png?imageMogr2/auto-orient/strip|imageView2/2/w/440/format/webp)

其中浅色的节点为不存在，上图的存储如下：

![img](https://upload-images.jianshu.io/upload_images/7043118-d6cd02856b386d6d.png?imageMogr2/auto-orient/strip|imageView2/2/w/448/format/webp)

^处为空节点存储，现在可以看到，已经出现了空间浪费的情况。

那么对于右斜树这种极端情况的存储是什么情形呢？

![img](https://upload-images.jianshu.io/upload_images/7043118-0ada42b04e0861a8.png?imageMogr2/auto-orient/strip|imageView2/2/w/700/format/webp)

**二叉链表存储：**

既然顺序存储不能满足二叉树的所有存储形式，那么可以采用链式存储。

由二叉树的定义可知，二叉树的节点最多有两个子节点，所以每个节点的数据结构可以定义为一个数据域和两个指针域：

![img](https://upload-images.jianshu.io/upload_images/7043118-95cd18e8cc20316e.png?imageMogr2/auto-orient/strip|imageView2/2/w/315/format/webp)

定义节点代码：

~~~c
typedef struct BiTNode{
    TElemType data;//数据
    struct BiTNode *lchild, *rchild;//左右孩子指针
} BiTNode, *BiTree;
~~~

![img](https://upload-images.jianshu.io/upload_images/7043118-73ae201506a7adc9.png?imageMogr2/auto-orient/strip|imageView2/2/w/688/format/webp)

上图采用链表形式存储二叉树，这种链表称为二叉链表。

#### **二叉树的遍历 √**

定义：

- 从根节点触发，按某种次序依次访问二叉树中的所有节点，使得每个节点都被访问依次，且仅被访问一次。

二叉树访问次序可以分为4种：

- 前序遍历
- 中序遍历
- 后序遍历
- 层序遍历

**前序遍历：**

根-->左-->右

![img](https://upload-images.jianshu.io/upload_images/7043118-df454c0a574836de.png?imageMogr2/auto-orient/strip|imageView2/2/w/441/format/webp)

前序遍历结果：ABDHIEJCFG

 **中序遍历：**

左-->根-->右

中序遍历结果：HDIBJEAFCG

**后序遍历：**

左-->右-->根

后序遍历结果：HIDJEBFGCA

虽然二叉树的遍历过程看似繁琐，但由于二叉树是一种递归定义的结构，所以使用递归的方式遍历二叉树的代码十分简单：

~~~c++
/*二叉树的前序遍历算法*/
void PreOrderTraverse(BiTree T) {
    if(T == NULL)
        return;
    printf("%c",T->data);/*显示数据*/
    PreOrderTraverse(T->lChild);
    PreOrderTraverse(T->rChild);
}

/*二叉树的中序遍历算法*/
void InOrderTraverse(BiTree T) {
    if(T == NULL)
        return;
    InOrderTraverse(T->lChild);
    printf("%c",T->data);/*显示数据*/
    InOrderTraverse(T->rChild);
}

/*二叉树的后序遍历算法*/
void PostOrderTraverse(BiTree T) {
    if(T == NULL)
        return;
    PostOrderTraverse(T->lChild);
    PostOrderTraverse(T->rChild);
    printf("%c",T->data);/*显示数据*/
}
~~~

**层次遍历：**

层次遍历就是按树的层次自上而下遍历二叉树，

层次遍历结果：ABCDEFGHIJK

二叉树常考考点：

已知前序遍历和中序遍历，求二叉树

已知中序遍历和后序遍历，求二叉树

但是已知前序遍历和后序遍历，不可唯一确定一棵二叉树



#### 二叉查找树 √

又称**二叉搜索树**，亦称**二叉排序树**。

**设x为二叉排序树中的一个节点，则x的左孩子节点一定小于等于x节点，x的右孩子节点一定大于等于x节点。**

**性质：**

- 若左子树不空，则左子树中所有节点的值一定小于根节点的值
- 若右子树不空，则右子树中所有节点的值一定大于根节点的值
- 左子树与右子树也均为二叉查找树

如：

![图2.2.1](https://images.xiaozhuanlan.com/photo/2019/b6127a1a685f51775370658146bcdd82.png)

**节点结构：**

![img](https://images.xiaozhuanlan.com/photo/2019/935a76056b93ad4b620c01be0cf31311.png)

**创建二叉搜索树：**

现有序列：A = {61, 87, 59, 47, 35, 73, 51, 98, 37, 93}。根据此序列构造二叉搜索树过程如下：

  （1）i = 0，A[0] = 61，节点61作为根节点；
  （2）i = 1，A[1] = 87，87 > 61，且节点61右孩子为空，故81为61节点的右孩子；
  （3）i = 2，A[2] = 59，59 < 61，且节点61左孩子为空，故59为61节点的左孩子；
  （4）i = 3，A[3] = 47，47 < 59，且节点59左孩子为空，故47为59节点的左孩子；
  （5）i = 4，A[4] = 35，35 < 47，且节点47左孩子为空，故35为47节点的左孩子；
  （6）i = 5，A[5] = 73，73 < 87，且节点87左孩子为空，故73为87节点的左孩子；
  （7）i = 6，A[6] = 51，47 < 51，且节点47右孩子为空，故51为47节点的右孩子；
  （8）i = 7，A[7] = 98，98 < 87，且节点87右孩子为空，故98为87节点的右孩子；
  （9）i = 8，A[8] = 93，93 < 98，且节点98左孩子为空，故93为98节点的左孩子；创建完毕后如图2.4中的二叉搜索树：

![图2.4](https://images.xiaozhuanlan.com/photo/2019/345efd0ae02be18cbc25b92c518d156b.png)

**查找：**

- 若树是空的，则查找结束，无匹配
- 若当前节点等于要查找的节点值，则查找成功
- 若要查找的值大于当前节点的值，则递归查找右子树
- 若要查找的值小于当前节点的值，则递归查找左子树

**代码：**

~~~js
//递归的方式查找
function searchTree（Node, data）{//Node为当前查找的节点，data为要查找的数据
    if(Node == null)
        return false;
    if(Node.data == data)
        return true;
    if(data > Node.data)
        serarchTree(Node.rChild,data)
    if(data < Node.data)
        searchTree(Node.lChild,data)
}
~~~

平均时间复杂度为O(log2n)

**插入节点：**

图解：

![img](https://images.xiaozhuanlan.com/photo/2019/3f7bd1b7062cbbffecc5bcf38ef020ab.png)

![img](https://images.xiaozhuanlan.com/photo/2019/a83dc468dc9598784a68cbf33f4a9b20.png)

![img](https://images.xiaozhuanlan.com/photo/2019/3717a705b9dfe0a39f7553937195e209.png)

**代码实现：**

~~~js
class Node{
    constructor(data) {
		this.data = data;
        this.lChild = null;
        this.rChile = null;
    }
}

function insertNode(treeNode, data) {
    if(data == treeNode.data) return false;//如果相等则表示存在此节点，无需添加
    if(data > treeNode.data) {
        if(treeNode.rChild == null) {
            let node = new Node(data);
            treeNode.rChild = node;
            return true;
        } else {
            insertNode(treeNode.rChild.data)
        }
    } else {
        if(treeNode.lChild == null) {
            let nodenew Node(data);
            treeNode.lChild = node;
            return true;
        } else {
            insertNode(treeNode.lChild,data)
        }
    }
}
~~~

**删除节点：**

- 若该节点为叶子结点，则直接删除即可
- 若该节点有左右子树，则找到左子树中最小的节点或右子树中最大的节点替换掉要删除的节点

代码：

~~~js

function delTreeNode(TreeNode, data) {
    let TreeNode = findTreeNode(Tree,data);//查找节点并返回
    if(TreeNode == null) return false;//没有该节点，无法删除
    let replaceNode = null;//替换删除节点的节点
    if(TreeNode.lChild != null) {
        let node = TreeNode.lChild;
        while(node.rChild != null) {
            node = node.rChild;
        }
		replaceNode = node.rChild;
        node.rChild = null;
    } else if(TreeNode.rChild != null) {
        let node = TreeNode.rChild;
        while(node.lChild != null) {
            node = node.lChild;
        }
        replaceNode = node.lChild;
        node.lChild = null;
    } else {
        TreeNode = null;
    }
    //交接工作
    replaceNode.lChild = TreeNode.lChild;
    replaceNode.rChild = TreeNode.rChild;
    //换人
    TreeNode = replaceNode;
    return true;//删除成功
}
~~~



#### 平衡二叉树 √

二叉搜索树一定程度上可以提高搜索效率，但当原序列有序，例如序列A={1,2,3,4,5,6},构造二叉搜索树如下图所示，依据此序列构造的二叉搜索树为右斜树，同时二叉树退化成单链表，搜索效率降低为O(n)。

![图3.1](https://images.xiaozhuanlan.com/photo/2019/c23f52a549d1bc70dc64e7a901ebdd24.png)

在这个图中查找6需要查找6次。二叉查找树的效率取决于树的高度，因此保证树的高度最小，即可保证查找效率。同样的序列A，按照下图的方式存储，查找6只需查找3次，效率提升了一倍！

![img](https://images.xiaozhuanlan.com/photo/2019/72686dbdfe520a06d5ff6fc133b76810.png)

可以看出，当树的节点数目一定时，保持左右两端平衡，树的查找效率最高。

这种左右子树高度不相差1的树为平衡树。

非平衡二叉树

![非平衡二叉树](https://images.xiaozhuanlan.com/photo/2019/82f2367ec04e1ee753eeb5dece1edf1c.png)

平衡二叉树；

![平衡二叉树](https://images.xiaozhuanlan.com/photo/2019/f2d26b71e2ff26f79c4b7ccf0116a20c.png)

**平衡因子：**

定义：某节点的左右子树高度差即为平衡因子。

平衡二叉树中不能出现平衡因子大于1的节点。



#### 平衡二叉查找树 √

##### ALV树 √

AVL树的特点：

1.本身首先是一棵二叉查找树

2.带有平衡条件：每个结点的左右子树的高度之差的绝对值不超过1，**也就是说，AVL树，本质上是带了平衡功能的二叉查找树**



对于给定节点数为n的ALV树，最大高度为O(log2n)。



ALV树节点的定义：

~~~js
class ALVTreeNode {
    constructor(data) {
        this.data = data;
        this.height = 0;//节点的高度
        this.leftTree = null;
        this.rightTree = null;
    }
}
~~~

树的高度、深度和平衡因子：

![img](https://img2018.cnblogs.com/blog/1590962/201908/1590962-20190811094415965-1541024368.jpg)

![img](https://img2018.cnblogs.com/blog/1590962/201908/1590962-20190812105349161-137592344.jpg)

**平衡因子：**

定义：某节点的左右子树高度差即为平衡因子。

平衡二叉树中不能出现平衡因子大于1的节点。

![img](https://img2018.cnblogs.com/blog/1590962/201908/1590962-20190812105620385-690446851.jpg)





**左旋：**

如果所示二叉树：

![img](https://images.xiaozhuanlan.com/photo/2019/8e6ff094642e31da191436b6d3e2deec.png)

在此平衡二叉树中插入62，树结构变为：

![img](https://images.xiaozhuanlan.com/photo/2019/d46d86ed6a22896b82e5e5c2c0f7c9ff.png)

可以得出40节点的左子树深度为1，右子树深度为3，平衡因子为1 - 3 = -2，树失去平衡。

此时要对40节点做出左旋操作，因为右子树高于左子树，操作如下：

- 节点的右孩子代替此节点的位置
- 右孩子的左子树变为该节点的右子树
- 该节点变为右孩子的左子树

图解过程：

![img](https://images.xiaozhuanlan.com/photo/2019/c620190798fdd2cad4761e9b4b3e0f79.png)

![img](https://images.xiaozhuanlan.com/photo/2019/c068581a1ba17cf5a0c2982ed7cad89d.png)



**右旋：**

当某节点的左子树高度高于右子树1层以上时

- 该节点的左孩子代替此节点的位置
- 该节点左孩子的右子树变为该节点的左子树
- 该节点变为其左孩子的右子树

图解过程：

![img](https://images.xiaozhuanlan.com/photo/2019/e67b88f8ee337bed83c6a1727dafd76a.png)

![img](https://images.xiaozhuanlan.com/photo/2019/aa8e86fd58e6bf9bc944c604729fe6bb.png)

**插入：**

假设一棵ALV树的某个节点A，有四种操作会使该左右子树高度相差大于1，从而破坏平衡性。

![img](https://img2018.cnblogs.com/blog/1590962/201908/1590962-20190812163000350-1194306251.png)

- A的左孩子的左子树插入节点（**L**L）
  - A的左孩子代替A的位置
  - A的左孩子的右子树变为A的左子树
  - A变为它左孩子的右子树
- A的左孩子的右子树插入节点（L**R**）
  - A的左孩子的右孩子带替A的位置
  - A的左孩子的右孩子的左孩子（如果有）变为A左孩子的右孩子
  - A变为它的右子树
  - A的左孩子的右孩子的右孩子（如果有）变为现A的左孩子
- A的右孩子的左子树插入节点（R**L**）
  - A的右孩子的左孩子代替A的位置
  - A的右孩子的左孩子的右孩子（如果有）变为A的右孩子的左孩子
  - A变为它的左子树
  - A的右孩子的左孩子的左孩子（如果有）变为现A的右孩子
- A的右孩子的右子树插入节点（**R**R）
  - A的右孩子带胎A的位置
  - A的右孩子的左孩子变为A的右子树
  - A变为它右孩子的左子树

![img](https://img2018.cnblogs.com/blog/1590962/201908/1590962-20190812165353068-843602761.png)



**删除：**

- 若删除的是叶子结点，则直接删除节点，然后判断其父节点是否失衡，若失衡调整
- 若删除的节点有子节点，则找其左孩子的右子树的最右下角的节点（或找其右孩子的左子树的最左下角的节点）来替换要删除节点的位置，然后判断最右下角的节点的父节点是否失衡，若失衡调整



##### 红黑树 √

一级棒的红黑树文章： https://www.jianshu.com/p/e136ec79235c 

红黑树是一种有红黑节点并能自平衡的二叉查找树。

**它必须满足以下性质：**

- 1.每个节点要么是黑色，要么是红色
- 2.根节点是黑色
- 3.每个叶子结点（NIL）是黑色
- 4.每个红色节点的两个子节点一定是黑色
- **5.任意一个节点到每个叶子结点的路径都包含数量相同的黑节点**
  - 性质5可以推出：
  - 如果一个节点存在黑子节点，那么该节点肯定有两个子节点

下图就是一个简单的红黑树，其中NiL为叶子结点，并且它是黑色的：

![img](https://upload-images.jianshu.io/upload_images/2392382-4996bbfb4017a3b2.png?imageMogr2/auto-orient/strip|imageView2/2/w/526/format/webp)

红黑树并不是一棵完美平衡二叉树，从上图可看出，根节点的p的左子树显然比右子树高，但左子树和右子树的黑节点的层数是相等的。即任意一个节点到每个叶子结点的路径上都包含数量相同的黑节点。所以我们将红黑树的这种平衡为**黑色完美平衡**。

红黑树的一些节点叫法：

![img](https://upload-images.jianshu.io/upload_images/2392382-abedf3ecc733ccd5.png?imageMogr2/auto-orient/strip|imageView2/2/w/772/format/webp)

前面讲到红黑树能自平衡，它靠的是什么？三种操作：左旋、右旋和变色。

-  **左旋**：以某个结点作为支点(旋转结点)，其右子结点变为旋转结点的父结点，右子结点的左子结点变为旋转结点的右子结点，左子结点保持不变。如图3。
-  **右旋**：以某个结点作为支点(旋转结点)，其左子结点变为旋转结点的父结点，左子结点的右子结点变为旋转结点的左子结点，右子结点保持不变。如图4。
-  **变色**：结点的颜色由红变黑或由黑变红。

![img](https://upload-images.jianshu.io/upload_images/2392382-a95db442f1b47f8a.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

![img](https://upload-images.jianshu.io/upload_images/2392382-0676a8e2a12e2a0b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

**红黑树总是通过旋转和变色达到自平衡**。

**红黑树的查找：**

因为红黑树是一颗二叉平衡树，并且查找不会破坏树的平衡，所以查找跟二叉平衡树的查找无异：

1. 从根结点开始查找，把根结点设置为当前结点；
2. 若当前结点为空，返回null；
3. 若当前结点不为空，用当前结点的key跟查找key作比较；
4. 若当前结点key等于查找key，那么该key就是查找目标，返回当前结点；
5. 若当前结点key大于查找key，把当前结点的左子结点设置为当前结点，重复步骤2；
6. 若当前结点key小于查找key，把当前结点的右子结点设置为当前结点，重复步骤2；

![img](https://upload-images.jianshu.io/upload_images/2392382-07b47eb3722981e6.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

非常简单，但简单不代表它效率不好。正由于红黑树总保持黑色完美平衡，所以它的查找最坏时间复杂度为O(2lgN)，也即整颗树刚好红黑相隔的时候。能有这么好的查找效率得益于红黑树自平衡的特性，而这背后的付出，红黑树的插入操作功不可没～

**红黑树的插入：**

插入操作包括两部分步骤：

- 查找位置
- 插入后自平衡

从根结点开始查找；

1.若根结点为空，那么插入结点作为根结点，结束。

2.若根结点不为空，那么把根结点作为当前结点；

3.若当前结点为null，返回当前结点的父结点，结束。

4.若当前结点key等于查找key，那么该key所在结点就是插入结点，更新结点的值，结束。

5.若当前结点key大于查找key，把当前结点的左子结点设置为当前结点，重复步骤4；

6.若当前结点key小于查找key，把当前结点的右子结点设置为当前结点，重复步骤4；

![img](https://upload-images.jianshu.io/upload_images/2392382-7521866b50683a24.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

ok，插入位置已经找到，把插入的节点放到正确的位置就可以了，但插入的节点应该是什么颜色呢？答案是：     **红色**。答案很简单，红色在父节点为黑色时，红黑树的黑色平衡不会被破坏，不需要做自平衡。如果插入节点为黑色，那么插入节点所在的子树黑色节点总是多1，必须做自平衡。

所有插入场景如图所示：

![img](https://upload-images.jianshu.io/upload_images/2392382-fa2b78271263d2c8.png?imageMogr2/auto-orient/strip|imageView2/2/w/1033/format/webp)

![img](https://upload-images.jianshu.io/upload_images/2392382-9ac3d6b69ef7ead3.png?imageMogr2/auto-orient/strip|imageView2/2/w/662/format/webp)

**插入情景1：红黑树为空树**

直接将插入节点作为根节点，设为黑色。

**插入情景2：插入节点的key已经存在**

把I设置为当前节点的颜色，更新当前节点的值为插入节点的值。

**插入情景3：插入节点的父节点为黑节点**

由于插入的节点时红色的，插入到黑节点下面并不会影响平衡，所以直接插入即可

**插入情景4：插入节点的父节点为红节点**

再次回想红黑树性质2：根节点为黑色。**如果插入节点的父节点为红色，那么该父节点不可能为根节点，所以插入的节点总是存在祖父节点的**。这一点很重要，因为后续的旋转操作一定需要祖父节点的参与。

插入情景4又分很多情况：

​	**插入情景4.1：叔叔节点存在并且为红节点**

从红黑树的性质4可知，该节点的祖父节点一定为黑节点，因为不可能出现两个相连的红节点。那么此时该插入子树的红黑层数情况为：黑 红 红。显然最简单的处理方式就是将其改为： 红 黑 红。如下图所示：

![img](https://upload-images.jianshu.io/upload_images/2392382-9f2c746bf0769f49.png?imageMogr2/auto-orient/strip|imageView2/2/w/656/format/webp)

- 将P和S设为黑色
- 将祖父节点PP设为红色
- 把PP设为当前插入节点（继续做自平衡处理）

可以看到，我们把PP结点设为红色了，如果PP的父结点是黑色，那么无需再做任何处理；但如果PP的父结点是红色，根据性质4，此时红黑树已不平衡了，所以还需要把PP当作新的插入结点，继续做插入操作自平衡处理，直到平衡为止。

试想下PP刚好为根结点时，那么根据性质2，我们必须把PP重新设为黑色，那么树的红黑结构变为：黑黑红。换句话说，从根结点到叶子结点的路径中，黑色结点增加了。**这也是唯一一种会增加红黑树黑色结点层数的插入情景**。

我们还可以总结出另外一个经验：**红黑树的生长是自底向上的**。这点不同于普通的二叉查找树，普通的二叉查找树的生长是自顶向下的。

 **插入情景4.2：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的左子结点** 

单纯从插入前来看，也即不算情景4.1自底向上处理时的情况，叔叔结点非红即为叶子结点(Nil)。因为如果叔叔结点为黑结点，而父结点为红结点，那么叔叔结点所在的子树的黑色结点就比父结点所在子树的多了，这不满足红黑树的性质5。后续情景同样如此，不再多做说明了。

前文说了，需要旋转操作时，肯定一边子树的结点多了或少了，需要租或借给另一边。插入显然是多的情况，那么把多的结点租给另一边子树就可以了。

**插入情景4.2.1：插入结点是其父结点的左子结点**
**处理：**

- **将P设为黑色**
- **将PP设为红色**
- **对PP进行右旋**

![img](https://upload-images.jianshu.io/upload_images/2392382-ab4097b750826870.png?imageMogr2/auto-orient/strip|imageView2/2/w/670/format/webp)

 咦，可以把P设为红色，I和PP设为黑色吗？答案是可以！  看过《算法：第4版》的同学可能知道，书中讲解的就是把P设为红色，I和PP设为黑色。但把P设为红色，显然又会出现情景4.1的情况，需要自底向上处理，做多了无谓的操作，既然能自己消化就不要麻烦祖辈们啦～ 
 **插入情景4.2.2：插入结点是其父结点的右子结点**
 这种情景显然可以转换为情景4.2.1，如图12所示，不做过多说明了。

**处理：**

- **对P进行左旋**
- **把P设置为插入结点，得到情景4.2.1**
- **进行情景4.2.1的处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-fbfc4f299941cb8b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1024/format/webp)

图12 插入情景4.2.2

**插入情景4.3：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的右子结点**
 该情景对应情景4.2，只是方向反转，不做过多说明了，直接看图。

**插入情景4.3.1：插入结点是其父结点的右子结点**
 **处理：**

- **将P设为黑色**
- **将PP设为红色**
- **对PP进行左旋**

![img](https:////upload-images.jianshu.io/upload_images/2392382-2bc24a78b68dae51.png?imageMogr2/auto-orient/strip|imageView2/2/w/622/format/webp)

图13 插入情景4.3.1

**插入情景4.3.2：插入结点是其父结点的右子结点**
 **处理：**

- **对P进行右旋**
- **把P设置为插入结点，得到情景4.3.1**
- **进行情景4.3.1的处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-ee1a9027ddcc210a.png?imageMogr2/auto-orient/strip|imageView2/2/w/1016/format/webp)

**红黑树删除**

 红黑树插入已经够复杂了，但删除更复杂，也是红黑树最复杂的操作了。 

 红黑树的删除操作也包括两部分工作：一查找目标结点；而删除后自平衡。 

二叉树删除结点找替代结点有3种情情景：

- 情景1：若删除结点无子结点，直接删除
- 情景2：若删除结点只有一个子结点，用子结点替换删除结点
- 情景3：若删除结点有两个子结点，用后继结点（大于删除结点的最小结点）替换删除结点，或前驱结点

 一种找前驱和后继结点的直观的方法： **把二叉树所有结点投射在X轴上，所有结点都是从左到右排好序的，所有目标结点的前后结点就是对应前继和后继结点**。 

 ![img](https://upload-images.jianshu.io/upload_images/2392382-dc4f0ab5d111ff96.png?imageMogr2/auto-orient/strip|imageView2/2/w/806/format/webp) 

讲一个重要的思路：**删除结点被替代后，在不考虑结点的键值的情况下，对于树来说，可以认为删除的是替代结点！**话很苍白，我们看下图。在不看键值对的情况下，下图的红黑树最终结果是删除了Q所在位置的结点！这种思路非常重要，大大简化了后文讲解红黑树删除的情景！

 ![img](https://upload-images.jianshu.io/upload_images/2392382-f45799daa674d0ad.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp) 

 基于此，上面所说的3种二叉树的删除情景可以相互转换并且最终都是转换为情景1！ 

情景2：删除结点用其唯一的子结点替换，子结点替换为删除结点后，可以认为删除的是子结点，若子结点又有两个子结点，那么相当于转换为情景3，一直自顶向下转换，总是能转换为情景1。（对于红黑树来说，根据性质5.1，只存在一个子结点的结点肯定在树末了）

情景3：删除结点用后继结点（肯定不存在左结点），如果后继结点有右子结点，那么相当于转换为情景2，否则转为为情景1。

 综上所述，**删除操作删除的结点可以看作删除替代结点，而替代结点最后总是在树末。**有了这结论，我们讨论的删除红黑树的情景就少了很多，因为我们只考虑删除树末结点的情景了。 



同样的，我们也先看一下删除操作的所有情景：

![img](https://upload-images.jianshu.io/upload_images/2392382-edaf96e55f08c198.png?imageMogr2/auto-orient/strip|imageView2/2/w/1035/format/webp)

![img](https://upload-images.jianshu.io/upload_images/2392382-db3468a5977ad998.png?imageMogr2/auto-orient/strip|imageView2/2/w/1004/format/webp)![img](https:////upload-images.jianshu.io/upload_images/2392382-db3468a5977ad998.png?imageMogr2/auto-orient/strip|imageView2/2/w/1004/format/webp)

上图删除操作结点的叫法约定

上图的字母并不代表结点Key的大小。R表示替代结点，P表示替代结点的父结点，S表示替代结点的兄弟结点，SL表示兄弟结点的左子结点，SR表示兄弟结点的右子结点。灰色结点表示它可以是红色也可以是黑色。

值得特别提醒的是，**R是即将被替换到删除结点的位置的替代结点，在删除前，它还在原来所在位置参与树的子平衡，平衡后再替换到删除结点的位置，才算删除完成。**

万事具备，我们进入最后的也是最难的讲解。

**删除情景1：替换结点是红色结点**

我们把替换结点换到了删除结点的位置时，由于替换结点时红色，删除也了不会影响红黑树的平衡，只要把替换结点的颜色设为删除的结点的颜色即可重新平衡。

**处理：颜色变为删除结点的颜色**

**删除情景2：替换结点是黑结点**

当替换结点是黑色时，我们就不得不进行自平衡处理了。我们必须还得考虑替换结点是其父结点的左子结点还是右子结点，来做不同的旋转操作，使树重新平衡。

**删除情景2.1：替换结点是其父结点的左子结点**
 **删除情景2.1.1：替换结点的兄弟结点是红结点**
 若兄弟结点是红结点，那么根据性质4，兄弟结点的父结点和子结点肯定为黑色，不会有其他子情景，我们按图21处理，得到删除情景2.1.2.3（后续讲解，这里先记住，此时R仍然是替代结点，它的新的兄弟结点SL和兄弟结点的子结点都是黑色）。

**处理：**

- **将S设为黑色**
- **将P设为红色**
- **对P进行左旋，得到情景2.1.2.3**
- **进行情景2.1.2.3的处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-1e4c3388491b588f.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

图21 删除情景2.1.1

**删除情景2.1.2：替换结点的兄弟结点是黑结点**
 当兄弟结点为黑时，其父结点和子结点的具体颜色也无法确定（如果也不考虑自底向上的情况，子结点非红即为叶子结点Nil，Nil结点为黑结点），此时又得考虑多种子情景。

**删除情景2.1.2.1：替换结点的兄弟结点的右子结点是红结点，左子结点任意颜色**
 即将删除的左子树的一个黑色结点，显然左子树的黑色结点少1了，然而右子树又又红色结点，那么我们直接向右子树“借”个红结点来补充黑结点就好啦，此时肯定需要用旋转处理了。如图22所示。

**处理：**

- **将S的颜色设为P的颜色**
- **将P设为黑色**
- **将SR设为黑色**
- **对P进行左旋**

![img](https:////upload-images.jianshu.io/upload_images/2392382-7eea721cbb855876.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

图22 删除情景2.1.2.1

平衡后的图怎么不满足红黑树的性质？前文提醒过，R是即将替换的，它还参与树的自平衡，平衡后再替换到删除结点的位置，所以R最终可以看作是删除的。另外图2.1.2.1是考虑到第一次替换和自底向上处理的情况，如果只考虑第一次替换的情况，根据红黑树性质，SL肯定是红色或为Nil，所以最终结果树是平衡的。如果是自底向上处理的情况，同样，每棵子树都保持平衡状态，最终整棵树肯定是平衡的。后续的情景同理，不做过多说明了。

**删除情景2.1.2.2：替换结点的兄弟结点的右子结点为黑结点，左子结点为红结点**
 兄弟结点所在的子树有红结点，我们总是可以向兄弟子树借个红结点过来，显然该情景可以转换为情景2.1.2.1。图如23所示。

**处理：**

- **将S设为红色**
- **将SL设为黑色**
- **对S进行右旋，得到情景2.1.2.1**
- **进行情景2.1.2.1的处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-dc29605ce9889973.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

图23 删除情景2.1.2.2

**删除情景2.1.2.3：替换结点的兄弟结点的子结点都为黑结点**
 好了，此次兄弟子树都没红结点“借”了，兄弟帮忙不了，找父母呗，这种情景我们把兄弟结点设为红色，再把父结点当作替代结点，自底向上处理，去找父结点的兄弟结点去“借”。但为什么需要把兄弟结点设为红色呢？显然是为了在P所在的子树中保证平衡（R即将删除，少了一个黑色结点，子树也需要少一个），后续的平衡工作交给父辈们考虑了，还是那句，当每棵子树都保持平衡时，最终整棵总是平衡的。

**处理：**

- **将S设为红色**
- **把P作为新的替换结点**
- **重新进行删除结点情景处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-75293515d8d87024.png?imageMogr2/auto-orient/strip|imageView2/2/w/778/format/webp)

图24 情景2.1.2.3

**删除情景2.2：替换结点是其父结点的右子结点**
 好啦，右边的操作也是方向相反，不做过多说明了，相信理解了删除情景2.1后，肯定可以理解2.2。

**删除情景2.2.1：替换结点的兄弟结点是红结点**
 处理：

- **将S设为黑色**
- **将P设为红色**
- **对P进行右旋，得到情景2.2.2.3**
- **进行情景2.2.2.3的处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-387664c771b21f1b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

图25 删除情景2.2.1

**删除情景2.2.2：替换结点的兄弟结点是黑结点**
 **删除情景2.2.2.1：替换结点的兄弟结点的左子结点是红结点，右子结点任意颜色**
 **处理：**

- **将S的颜色设为P的颜色**
- **将P设为黑色**
- **将SL设为黑色**
- **对P进行右旋**

![img](https:////upload-images.jianshu.io/upload_images/2392382-b1ea52c823ce0b0b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

图26 删除情景2.2.2.1

**删除情景2.2.2.2：替换结点的兄弟结点的左子结点为黑结点，右子结点为红结点**
 **处理：**

- **将S设为红色**
- **将SR设为黑色**
- **对S进行左旋，得到情景2.2.2.1**
- **进行情景2.2.2.1的处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-edcb4ea6ac87e342.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

图27 删除情景2.2.2.2

**删除情景2.2.2.3：替换结点的兄弟结点的子结点都为黑结点**
 **处理：**

- **将S设为红色**
- **把P作为新的替换结点**
- **重新进行删除结点情景处理**

![img](https:////upload-images.jianshu.io/upload_images/2392382-6559c4cccf3df81c.png?imageMogr2/auto-orient/strip|imageView2/2/w/748/format/webp)

图28 删除情景2.2.2.3

综上，红黑树删除后自平衡的处理可以总结为：

1. 自己能搞定的自消化（情景1）
2. 自己不能搞定的叫兄弟帮忙（除了情景1、情景2.1.2.3和情景2.2.2.3）
3. 兄弟都帮忙不了的，通过父母，找远方亲戚（情景2.1.2.3和情景2.2.2.3）

哈哈，是不是跟现实中很像，当我们有困难时，首先先自己解决，自己无力了总兄弟姐妹帮忙，如果连兄弟姐妹都帮不上，再去找远方的亲戚了。这里记忆应该会好记点～

请画出下图的删除自平衡处理过程。

![img](https://upload-images.jianshu.io/upload_images/2392382-d3231b6890ab76a3.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

过程：

![img](https://upload-images.jianshu.io/upload_images/2392382-b037e4c29cbffc4d.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

思考：

黑节点可以同时包含一个黑子节点和红子节点吗？

答案是，可以，如下图：

![img](https://upload-images.jianshu.io/upload_images/2392382-3e64f9f3481b209d.png?imageMogr2/auto-orient/strip|imageView2/2/w/880/format/webp)



### 多路查找树 √

多路查找树（muitl-way search tree)，其每一个节点的孩子数可以多于两个，且每个节点处可以存储多个元素。

主要有4种特殊形式：



#### B树 （B-树）√

B树是一种平衡的多路查找树。2-3树和2-3-4树都是B树的特例。

节点最大的数组称为B树的阶，因此，2-3树是3阶B-树，2-3-4树是4阶B-树。

![这里写图片描述](https://img-blog.csdn.net/20150610154312527)

比如说要查找7，首先从外存读取得到根节点3,5,8三个元素，发现7不在，但是5、8之间，因此就通过A2再读取外存的6,7节点找到结束。

B树的插入和删除和2-3树、2-3-4树类似。

**B树的结构是为内外存的数据交互准备的。**当要处理的数据量很大时，无法一次全部装入内存，这时对B树进行调整，使得B树的阶数与硬盘存储的页面大小相匹配。比如一棵B树的阶为1001（即一个节点包含1001个关键字），高度为2它可以存储超过10亿个关键字，只要让根节点持久的保存在内存中，那么，在这棵树上，寻找某个关键字至多需要两次的硬盘读取即可。

对于n个关键字的m阶B树，最坏情况查找次数计算
第一层至少1个节点，第二层至少2个节点，由于除根节点外每个分支节点至少有⌈m/2⌉棵子树，则第三层至少有2x⌈m/2⌉个节点。。。这样第k+1层至少有2x(⌈m/2⌉)^(k-1),实际上，k+1层的节点就是叶子节点。若m阶B树有n个关键字，那么当你找到叶子节点，其实也就等于查找不成功的节点为n+1，因此
n+1>=2x(⌈m/2⌉)^(k-1),即

![这里写图片描述](https://img-blog.csdn.net/20150610171356518)

在含有n个关键字的B树上查找时，从根节点到关键字节点的路径上涉及的节点数不超多
![这里写图片描述](https://img-blog.csdn.net/20150610171834132)



#### B+树 √

　下图B树，我们要遍历它，假设每个节点都属于硬盘的不同页面，我们为了中序遍历所有的元素，页面2-页面1-页面3-页面1-页面4-页面1-页面5.而且我们每经过节点遍历时，都会对节点中的元素进行一次遍历，糟糕！有没有可能让遍历时每个元素只访问一次呢？
　　![这里写图片描述](https://img-blog.csdn.net/20150610185334255)
　　**B+树是应文件系统所需而出的一种B树的变形树**，在B树中，每一个元素树中只出现一次，而B+树中，出现在分支节点中的元素会被当做他们在该分支节点位置的中序后继者（叶子节点）中再次列出。另外，每一个叶子节点都会保存一个指向后一叶子节点的指针。
　　下图就是B+树，灰色关键字，在根节点出现，在叶子节点中再次列出。
　　![这里写图片描述](https://img-blog.csdn.net/20150610172402765)
　　![这里写图片描述](https://img-blog.csdn.net/20150610172509321)

　　**B+树适合随机查找，只不过查到后是索引，不能提供实际记录的访问，还需要到达包含此关键字的终端节点**。非叶结点仅具有索引作用，跟记录有关的信息均存放在叶结点中。B+树适合带有范围的查找。B+树插入、删除类似B树。


#### 2-3树 √

定义：除了叶子结点，每个节点都具有两个孩子（称为2节点），或三个孩子（称为3节点）。并且2-3树中的所有孩子节点都在同一层上。

一个2节点包含一个元素，并且有2个孩子（或者没有孩子），左子树所有元素小于2节点的元素；

一个3节点包含一大一小两个元素，并且有3个孩子（或者没有孩子），左子树所有元素小于3节点的小元素，右子树所有元素大于3节点的大元素，中子树所有元素都介于3节点的小元素与大元素之间。

![这里写图片描述](https://img-blog.csdn.net/20150610145221185)

##### **2-3树的插入**

- 对于一个空树，直接插入一个2节点即可。

- 插入节点到一个2节点的叶子上。由于本身就只有一个元素，所以只需要将其升级为3节点即可。
  - ![这里写图片描述](https://img-blog.csdn.net/20150610145523097)

- 插入节点到一个3节点的叶子上。因为3节点本身最大容量，因此需要拆分，且将树中两元素或者插入元素的三者中选择其一向上移动一层。
  三种情况：
  - 升级父节点
    - ![这里写图片描述](https://img-blog.csdn.net/20150610145911255)
  - 升级根节点
    - ![这里写图片描述](https://img-blog.csdn.net/20150610145953393)
  - 增加树高度
    - ![这里写图片描述](https://img-blog.csdn.net/20150610150016361)

##### **2-3树的删除**

- 所删除的数位于3节点的叶子结点上，直接删除，不会影响树的结构。
  - ![这里写图片描述](https://img-blog.csdn.net/20150610150237431)
- 所删除元素位于2节点上，直接删除会破坏树的结构，所以要调整树结构。分为4种情况：
  - 此节点的双亲也是2节点，且拥有一个3节点的右孩子
    - ![这里写图片描述](https://img-blog.csdn.net/20150610150940805)
  - 此节点的双亲也是2节点，且拥有一个2节点的右孩子
    - ![这里写图片描述](https://img-blog.csdn.net/20150610151004917)
  - 此节点的双亲是3节点
    - ![这里写图片描述](https://img-blog.csdn.net/20150610151021133)
  - 当前树是一个满二叉树，降低树高
    - ![这里写图片描述](https://img-blog.csdn.net/20150610151032896)
- 所删除节点位于非叶子的分支节点。此时按树的中序遍历找到其前驱或后继元素，补位即可
  - 分支节点是2节点
    - ![这里写图片描述](https://img-blog.csdn.net/20150610151252544)
  - 分支节点是3节点
    - ![这里写图片描述](https://img-blog.csdn.net/20150610151307204)

#### 2-3-4树 √

2-3-4树是2-3树的扩展，包括了4节点的是使用，一个4节点包含小中大三个元素，其有4个孩子（或没有孩子）

**2-3-4树插入的实现：**

构建一个数组为[7,1,2,5,6,9,8,4,3]的2-3-4树的过程

![这里写图片描述](https://img-blog.csdn.net/20150610151712079)

**2-3-4树的删除实现：**

删除顺序为：1,6,3,4,5,2,9的元素

![这里写图片描述](https://img-blog.csdn.net/20150610151744141)



### 堆 √

堆就是用数组实现的二叉树，根据堆属性排序，堆属性决定了树中节点的位置。

![img](https://img2018.cnblogs.com/blog/1590962/201903/1590962-20190318210706226-1501863648.png)



#### 大顶堆 √

代码实现：

~~~js
function bigPileTop(arr) {
			//思路：首先找到最后一个有左子节点的节点，将以该节点为堆顶的堆变为大顶堆，然后向上找，直到找到最后一位
			const len = arr.length;
            if(len < 2) return arr;
			let i;
			for(i = len - 1; i >= 0; i --) {
				if(arr[2 * i + 1] != undefined) break;
			}
			for(; i >= 0; i --) {
				buildBigPileTop(i)
			}
			return arr;
			
			function buildBigPileTop(i) {
				if(arr[2 * i + 1] == undefined) return;
				let fatherNode = arr[i];
				let leftChild = arr[i * 2 + 1];
				let rightChild = arr[i * 2 + 2];//有肯能是undefined
				if(leftChild > fatherNode) {
					let temp = arr[i];
					arr[i] = arr[i * 2 + 1];
					arr[i * 2 + 1] = temp;
					buildBigPileTop(i * 2 + 1);//由于变化后并不能保证下面的节点依然是大顶堆，所以要递归一下
				}
				if(rightChild != undefined && rightChild > fatherNode) {
					let temp = arr[i];
					arr[i] = arr[i * 2 + 2];
					arr[i * 2 + 2] = temp;
					buildBigPileTop(i * 2 + 2);
				}
			}
		}
		const arr = [2,7,5,8,1,10,4];
		console.log(arr);
~~~



#### 小顶堆 √

代码实现：

~~~js
function smallPileTop(arr) {
			const arrLen = arr.length;
			if(arrLen < 2) return arr;
			let i = arr.length - 1;
			for( ; i >= 0; i --) {
				if(arr[i * 2 + 1] != undefined) break;
			}
			for(;i >= 0; i --) {
				buildSmallPileTop(i);
			}
			return arr;

			function buildSmallPileTop(i) {
				if(arr[i * 2 + 1] == undefined) return;
				let fatherNode = arr[i];
				let leftChild = arr[i * 2 + 1];
				let rightChild = arr[i * 2 + 2];
				if(leftChild < fatherNode) {//唯一与大顶堆实现的区别就是 <
					let temp = arr[i];
					arr[i] = arr[i * 2 + 1];
					arr[i * 2 + 1] = temp;
					buildSmallPileTop(i * 2 + 1);
				}
				if(rightChild < fatherNode) {//唯一与大顶堆实现的区别就是 <
					let temp = arr[i];
					arr[i] = arr[i * 2 + 2];
					arr[i * 2 + 2] = temp;
					buildSmallPileTop(i * 2 + 2);
				}
			}
		}
		const arr = [2,7,5,8,1,10,4];
		console.log(arr);
		console.log(smallPileTop(arr))
~~~



#### 二项堆 √

介绍二项堆之前，先说一下二项树：

##### 二项树 √

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Binomial_Trees.svg/500px-Binomial_Trees.svg.png)

（二项树，从左到右度依次为0~3）

**二项树的性质：**

- 度为k的二项树一共有2**k个节点
- 高度为k
- 有一个根节点，根节点有个k个子女，每个子女的度分别为k-1，k-2,...2,1,0
- 度为k的二项树很容易从两棵度为k-1的二项树合并得到：把一棵根节点大的二项树作为另一棵根节点小的二项树的最左子树即可。这一性质是二项堆用于堆合并的基础。

##### 二项堆 √

二项堆是指满足以下性质的二项树的集合：

- 每棵二项树都满足最小堆性质，即**节点关键字大于等于其父节点的值**
- 不能有两颗或以上的二项树有相同的度。换句话说，**度为k的二项树只能有0或1个**

以上第一个性质保证了**二项树的根节点包含了整棵树最小的关键字**；

第二个性质保证了 **节点数为n的二项堆最多只能有logn棵二项树**。

实际上，包含n个节点的二项堆的构成情况，由n的二进制保证唯一确定，其中每一位对应于一棵二项树。

**例如，13的二进制表示为1101，2³+2²+2º，因此由13个节点构成的二项堆由度为3,2,0的三棵二项树构成：**

![Example of a binomial heap](https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Binomial-heap-13.svg/325px-Binomial-heap-13.svg.png)

由于并不需要对二项树的根节点进行随机存取，因而这些节点可以存成**链表结构**。

##### 合并 √

两个度相同的二项树合并：

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Binomial_heap_merge1.svg/200px-Binomial_heap_merge1.svg.png)

代码：

~~~js
function mergeTree(p, q) {
	if(p.root >= q.root) {//比出根节点数据小的做合并后的根节点
		return q.addSubTree(p);
	} else {
		return p.addSubTree(q);
	}
}
~~~

两个二项堆的合并：

将两堆中相同度为k的二项树用上述方法进行合并成k+1的二项树：

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Binomial_heap_merge2.svg/300px-Binomial_heap_merge2.svg.png)

~~~
function merge(p, q)
    while not (p.end() and q.end())
        tree = mergeTree(p.currentTree(), q.currentTree())
        
        if not heap.currentTree().empty()
            tree = mergeTree(tree, heap.currentTree())
        
        heap.addTree(tree)
        heap.next(); p.next(); q.next()
~~~

##### 插入 √

- 创建一个只包含要插入元素的二项堆
- 将该二项堆与要插入的二项堆进行合并即可

##### 查找最小关键字所在节点 √

- 由于满足最小堆性质，只需查找二项树的最小根节点即可
- 一共有logn棵树，所用时间为O(logn)
- 可以保存一个指向最小元素的指针，使得时间复杂度变为O(1)，但在执行某些操作时要更改该指针

##### 删除最小关键字所在节点 √

- 找到最小关键字所在节点，将其删除
- 将子树看做一个独立的二项堆，与大堆进行合并即可

##### 减小特定节点的值 √

- 找到特定节点并减小其值
- 判断是否满足最小堆性质，若不满足，与其父节点进行交换
- 若依然不满足，父节点与祖父节点进行交换，直到根节点

##### 删除 √

- 将需要删除的节点的值减小到负无穷大（比二项堆中其他节点的值都小即可）

- 向上一层一层换到根节点，将根节点删除
- 将子树组成的二项堆与原来的堆进行合并即可

##### 运行时间 √

以下对于二项堆操作的运行时间都为O(logn)：

- 在二项堆中插入新结点
- 查找最小关键字所在结点
- 从二项堆中删除最小关键字所在结点
- 减小给定结点关键字的值
- 删除给定结点
- 合并两个二项堆

#### 优先队列 √

 　优先队列是一种用来维护一组元素构成的结合S的数据结构，其中每个元素都有一个关键字key，元素之间的比较都是通过key来比较的。优先队列包括**最大优先队列**和**最小优先队列**，优先队列的应用比较广泛，比如作业系统中的调度程序，当一个作业完成后，需要在所有等待调度的作业中选择一个优先级最高的作业来执行，并且也可以添加一个新的作业到作业的优先队列中。Java中，PriorityQueue的底层数据结构就是堆（默认是小堆） 

 **优先队列的实现中，我们可以选择堆数据结构**，最大优先队列可以选用大堆，最小优先队列可以选用小堆来实现。

##### 插入

 插入操作是将一个元素插入到集合S中，**首先把该元素放入所有元素的下一位置，然后执行“上浮”操作**，如下图示例 

![PriorityQueue_offer.png](https://images2015.cnblogs.com/blog/939998/201605/939998-20160512205600890-346195840.png)

##### 删除

 优先队列中，在队列非空情况下移除集合中第一个元素，也就是下标为0的元素，然后**将集合中最后一个元素移到下标为0位置，在将下标为0的新元素执行“下沉”操作**。 

![PriorityQueue_poll.png](https://images2015.cnblogs.com/blog/939998/201605/939998-20160512205634609-402016454.png)

代码实现：

~~~java
package priorityheap;

import java.util.Arrays;

/**
 * 优先队列类（最大优先队列）
 */
public class PriorityHeap {

    // ------------------------------ Instance Variables

    private int[] arr;
    private int size;

    // ------------------------------ Constructors

    /**
     * 优先队列数组默认大小为64
     */
    public PriorityHeap() {
        this(64);
    }

    public PriorityHeap(int initSize) {
        if (initSize <= 0) {
            initSize = 64;
        }
        this.arr = new int[initSize];
        this.size = 0;
    }

    // ------------------------------ Public methods

    public int max() {
        return this.arr[0];
    }

    public int maxAndRemove() {
        int t = max();

        this.arr[0] = this.arr[--size];
        sink(0, this.arr[0]);
        return t;
    }
    public void add(int data) {
        resize(1);
        this.arr[size++] = data;
        pop(size - 1, data);
    }

    // ------------------------------ Private methods

    /**
     * key下沉方法
     */
    private void sink(int i, int key) {
        while (2 * i <= this.size - 1) {
            int child = 2 * i;
            if (child < this.size - 1 && this.arr[child] < this.arr[child + 1]) {
                child++;
            }
            if (this.arr[i] >= this.arr[child]) {
                break;
            }

            swap(i, child);
            i = child;
        }
    }

    /**
     * key上浮方法
     */
    private void pop(int i, int key) {
        while (i > 0) {
            int parent = i / 2;
            if (this.arr[i] <= this.arr[parent]) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    /**
     * 重新调整数组大小
     */
    private void resize(int increaseSize) {
        if ((this.size + increaseSize) > this.arr.length) {
            int newSize = (this.size + increaseSize) > 2 * this.arr.length ? (this.size + increaseSize) : 2 * this.arr.length;
            int[] t = this.arr;

            this.arr = Arrays.copyOf(t, newSize);
        }
    }

    /**
     * Swaps arr[a] with arr[b].
     */
    private void swap(int a, int b) {
        int t = this.arr[a];
        this.arr[a] = this.arr[b];
        this.arr[b] = t;
    }
}
~~~



#### 斐波那契堆 √

它是计算机科学中树的集合，**它比二项堆有更好的平摊分析性能**，**可用于实现合并优先队列。**

不涉及删除元素的操作有O(1)的平摊时间。

斐波那契堆(Fibonacci heap)是计算机科学中最小堆有序树的集合。它和二项式堆有类似的性质，但比二项式堆有更好的均摊时间。堆的名字来源于斐波那契数，它**常用于分析运行时间**。

   ![img](https://images0.cnblogs.com/blog2015/472792/201504/281936168969804.jpg)

 

 

**堆结构介绍**

　　基本术语介绍：

　　关键字：堆节点储存的用于比较的信息

　　度数：堆节点拥有的孩子数(注意，不包括孩子的孩子)

　　左兄弟：节点左边的兄弟节点

　　右兄弟：节点右边的兄弟节点

　　mark:是否有孩子节点被删除

 

　　斐波那契堆是一系列无序树的集合，每棵树是一个最小堆，满足最小堆的性质。(注意，树是无序的，所以不要纠结树该怎么排序)。堆保存了堆中所有节点的数目，保存了最小关键字的节点(这是整个堆的唯一入口，根据这个最小节点可以获取整个堆的任何节点)。

　　**堆的节点是堆的最小单位，它是双向链表的节点**，意味着它保存了上下节点的信息，如下图,(也能看出树的根节点排列是无序的)。

　　![img](https://images0.cnblogs.com/blog2015/472792/201504/281939267086148.png)

　　它主要有如下性质：

　　1、关键字

　　2、度数

　　3、左兄弟

　　4、右兄弟

　　5、父节点

　　6、孩子节点(任一个孩子节点，随意)

 

**堆基本操作**

　　一、插入操作

　　　　1、创建一个节点，如21

　　![img](https://images0.cnblogs.com/blog2015/472792/201504/281946062863108.png)

　　2、把新建的节点插入到根链表中，如果是最小值，则更新它为堆的最小节点。插入位置没有规定，一般习惯插入到min的左边。把堆的“所有节点数”值加1

　　![img](https://images0.cnblogs.com/blog2015/472792/201504/281949411777591.png)

　　3、插入操作完成了(插入并不会对堆进行修改，修改是在其他操作中进行的，所以比较简单)

 

　　二、删除最小节点

　　　　1、删除最小节点，并把它的所有孩子合并到堆的根链表中，并更新min

 ![img](https://images0.cnblogs.com/blog2015/472792/201505/022041462249792.png)

![img](https://images0.cnblogs.com/blog2015/472792/201505/022048225364583.png)

![img](https://images0.cnblogs.com/blog2015/472792/201505/022043111612943.png)

　　2、合并根节点的树，**使任何树的度(rank)不相等**

　　　　观察到7有1个孩子节点，即度为1，先保存起来，由于是初始的，肯定没有和7度相同的

![img](https://images0.cnblogs.com/blog2015/472792/201505/022050222556708.png)

　　　　接着下一个根节点24，度为2，继续。

![img](https://images0.cnblogs.com/blog2015/472792/201505/022050405059066.png)

　　　　23, 度为0，继续

![img](https://images0.cnblogs.com/blog2015/472792/201505/022051072869717.png)

　　　　17, 度为0。 由于已经有度为0的根节点了，所以需要合并这两个节点

![img](https://images0.cnblogs.com/blog2015/472792/201505/022051404901507.png)

　　　　根据最小堆得性质，把23合并到17上，作为17的孩子节点

![img](https://images0.cnblogs.com/blog2015/472792/201505/022052141156467.png)

　　　　此时17的度为1，仍然重复，继续合并，直到没有度一样的根节点

![img](https://images0.cnblogs.com/blog2015/472792/201505/022052450683144.png)

![img](https://images0.cnblogs.com/blog2015/472792/201505/022053268338685.png)

![img](https://images0.cnblogs.com/blog2015/472792/201505/022053471617441.png)

![img](https://images0.cnblogs.com/blog2015/472792/201505/022054267556612.png)

 ![img](https://images0.cnblogs.com/blog2015/472792/201505/022055241462213.png)

![img](https://images0.cnblogs.com/blog2015/472792/201505/022055483331323.png)

　　　　最终结果如下图

![img](https://images0.cnblogs.com/blog2015/472792/201505/022056232408324.png)

 

　　　　

 

　　三、减小key值（节点里面的数字）

　　　　**如果没有违背最小堆的性质，直接减小key的值**

　　　　**否则，把以key为根节点的树合并到堆的根链表中**

　　　　**如果有一个节点有两个孩子移除了，把这个节点也合并到根链表中，并且unmark它**

　　　　![img](https://images0.cnblogs.com/blog2015/472792/201505/022156030993692.png)

　　　　现在举一个例子来说明各种可能情况

　　　　1、不违反最小堆性质

　　　　　　把46减小为29，不违反最小堆性质，不改变堆结构

　　![img](https://images0.cnblogs.com/blog2015/472792/201505/022155470529460.png)

　　　　2、违反最小堆性质，合并到根链表中，并且unmark 它

　　　　　　把29减小为15，违反了堆性质

　　![img](https://images0.cnblogs.com/blog2015/472792/201505/022158170839391.png)

　　　　把15合并到根链表中

![img](https://images0.cnblogs.com/blog2015/472792/201505/022201036615468.png)

　　如果父节点没有mark(没有失去孩子), 设置它为mark

　　![img](https://images0.cnblogs.com/blog2015/472792/201505/022203091773090.png)

　　如果父节点已经是mark，则把父节点合并到根链表中，并设置为unmark。

　　把节点35减小到5　

　　![img](https://images0.cnblogs.com/blog2015/472792/201505/022207422085843.png)

　　由于违反了，把5合并到根

![img](https://images0.cnblogs.com/blog2015/472792/201505/022208348332533.png)

　　由于26已经mark，把26这个子树合并到根

![img](https://images0.cnblogs.com/blog2015/472792/201505/022209376933728.png)

　　同理24合并到根

![img](https://images0.cnblogs.com/blog2015/472792/201505/022211076614476.png)

　　由于7已经是根节点了，停止，全部结束

![img](https://images0.cnblogs.com/blog2015/472792/201505/022211489589859.png)

　　四、删除节点

　　　　删除节点比较简单，主要分为两步

　　　　1、把节点值decrease比堆最小值还小

　　　　2、删除最小值











