public class Hash {
	private int[] hashTable;
	private int maxSize = 10;
	private int[] arr;
	private int p = 7;//默认的素数

	public Hash () {
		this.hashTable = new int[maxSize];
	}

	public Hash (int[] arr) {
		this.maxSize = arr.length;
		this.hashTable = new int[maxSize];
		this.arr = arr;
		//寻找不大于数组长度的最大的素数，素数一定是奇数，所以这就可以排除一半
		int n = arr.length;
		if(arr.length % 2 == 0) {
			n = arr.length - 1;
		}
		for(int i = n; i > 1; i -= 2) {
			for(int j = n - 2; j > 1; j -=2) { //不是素数奇数的两个公约数也一定是奇数
				if(i % j == 0) {
					break;
				}
			}
			if(j == 1) {//说明找到了要找的素数
				this.p = i;
				break;
			}
		}


	}

	//给定数组创建哈希表
	// public int[] crateHashTable (int[] arr) {

	// }
	//向哈希表中添加数据
	public void addHashTable (int n) {
		//使用除余法添加，用线性探测法解决哈希冲突
		int num = n % p;
		while(hashTable[num] == null) {
			if(num >= maxSize - 1) {//如果找到头也没找到空位置，则从0开始继续找
				num = 0;
			} else {
				num ++;
			}
		}
		hashTable[num] = n;
	}

	//查找哈希表中的数据
	public boolean findHashTable (int n) {
		int num = n % p;
		int time = 0//查找次数，防止无限循环
		while(hashTable[num] != n && time < maxSize) {
			if(num >= maxSize - 1){//如果找到头也没吵到，则从0开始继续找
				num = 0;
			} else {
				num ++;
			}
			time ++
		}
		if(time == maxSize) {//说明没找到
			return false;
		} else {
			return true;
		}
	}
}