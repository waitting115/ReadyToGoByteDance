//add()  get()  length()  insert()  delete()  unite()
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