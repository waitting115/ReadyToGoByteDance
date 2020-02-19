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