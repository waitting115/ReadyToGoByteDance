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