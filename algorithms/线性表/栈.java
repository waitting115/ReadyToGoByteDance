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