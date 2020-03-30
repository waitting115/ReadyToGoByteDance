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
		int time = 0;
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