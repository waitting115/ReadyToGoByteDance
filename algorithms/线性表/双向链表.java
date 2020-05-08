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
		if(node.data == null) {
			throw new Exception("未找到指定节点！");
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