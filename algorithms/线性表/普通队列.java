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