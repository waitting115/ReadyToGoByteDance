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
	Link<T> ene = new Link<>(null);//尾节点
	int size = 0;//链表长度
	head.next = end;
	end.prev = head;
	end.next = head.next;

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
		while(node.data != data && node.next != head) {
			node = node.next;
		}
		return node;
	}

	//头部插入节点
	public void insertHeadNode(T data) {
		Link<T> node = new Link<>(data);
		node.next = head.next.next;
		node.prev = head;
		head.next.next.prev = node;
		head.next = node;
		size ++;
	}
	// 指定节点后面插入节点
	public void insertMidNode(T data, T dataInsert) {//位置，节点
		Link<L> node = new Link<>(dataInsert);//要插入的节点
		Link<L> locationNode = findNode(data);//指定节点
		node.next = locationNode.next.next;
		node.prev = locationNode
		locationNode.next.next.prev = node;
		locationNode.next = node;
	}

	// 尾部插入节点
	public void insertLowNode(T data) {
		Link<T> node = new Link<>(data);
		head.
	}

	// 头部删除节点

	// 指定节点后面删除节点

	// 尾部删除节点

	// 返回节点长度
}