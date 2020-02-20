public class Link<T> {
	public T data;
	public Link<T> prev;
	public Link<T> next;

	public Link(T data) {
		this.data = data;
	}
}

public class DoubleCycleLinkList<T> {
	Link<T> head = new Link<>(null);//头指针
	// Link<T> ene = new Link<>(null);//尾节点
	int size = 0;//链表长度
	head.next = null;
	head.prev = null;
	// end.prev = head;
	// end.next = head.next;

	// 返回链表长度
	public int len() {
		return size;
	}

	//判断链表是否为空
	public boolean isEmpty() {
		return size == 0
	}

	//查询指定节点并返回
	public Link<T> findNode(T data) {
		Link<T> node = head.next;//第一个节点
		int time = 0;
		while(node.data != data && time < size) {
			node = node.next;
			time ++;
		}
		if(time == size) {
			throw new Exception("未找到指定节点");
		}
		return node;
	}

	//头部插入节点
	public void insertHeadNode(T data) {
		Link<T> node = new Link<>(data);
		node.next = head.next;
		node.prev = head.next.prev;
		head.next.prev = node;
		head.next = node;
		size ++;
	}
	// 指定节点后面插入节点
	public void insertMidNode(T data, T dataInsert) {//位置，节点
		Link<L> node = new Link<>(dataInsert);//要插入的节点
		Link<L> locationNode = findNode(data);//指定节点
		node.next = locationNode.next;
		node.prev = locationNode;
		locationNode.next.prev = node;
		locationNode.next = node;
		size ++;
	}

	// 尾部插入节点(画图很容易推出来)
	public void insertLowNode(T data) {
		Link<T> node = new Link<>(data);
		node.next = head.next;
		node.prev = head.next.prev;
		head.next.prev.next = node;
		head.next.prev = node;
		size ++;
	}

	// 头部删除节点
	public void delHeadNode() {
		head.next.next.prev = head.next.prev;
		head.next.prev.next = head.next.next;
		size --;
	}

	// 指定节点后面删除节点
	public void delNode(Node node) {
		Node nodeNextDel = find(Node node);
		nodeNextDel.next.next.prev = nodeNextDel;
		nodeNextDel.next = nodeNextDel.next.next;
		size --;
	}

	// 尾部删除节点
	public void delLowNode() {
		head.next.prev.next = head.next;
		head.next.prev = head.next.prev.prev;
		size --;
	}
	// 返回链表长度
	public int Size() {
		return size;
	}
}