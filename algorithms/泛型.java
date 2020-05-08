//普遍的泛型方法
public class test {
	//泛型方法
	public static < E > void printElement(E[] array) {//这里的E表示可以输入任何类型的数组
		//输出数组元素
		for(E element : array) {//E类型的element用来遍历array
			System.out.println('%s', element);
		}
		System.out.println();
	} 

	public static void main (String[] args) {
		//创建不同类型的数组
		Integer[] intArray = {1,2,4,66,7,9};
		Double[] doubleArray = {1.1,2.2,4.4,6.6,7.7,9.9};
		Character[] charArray = {'H', 'E', 'L', 'L', 'O'};

		//正常调用泛型方法
		System.out.println('整型数组元素为：');
		printElement(intArray);//传递一个整型数组

		System.out.println('\n双精度型数组元素为：');
		printElement(doubleArray);

		System.out.println('\n字符型数组元素为：');
		printElement(charArray);
	}
}

//泛型有界类型参数
pubic class MaxiumTest {
	//比较三个值并返回最大值
	public static <T extends Comparable<T>> maxium(T x, T y, T z) {
		//它的意思是只接收那种可以比较大小的参数类型
		//下面只是普通的比较算法
		T max = x;
		if(y.compareTo( max ) > 0) {//number.compareTo(number)判断参数与指定的数的大小，如果参数与指定的数相等返回0；参数比指定的数大则返回-1；参数比指定的数小则返回1；
			max = y;
		}
		if(z.compareTo( max ) > 0) {
			max = z;
		}
	}
	public static void main (String[] args) {
		System.out.pringln('%d,%d,%d中最大的数为：%d\n', 3,6,8,maxium(3,6,8));
		System.out.pringln('%.1f,%.1f,%.1f中最大的数为：%.1f\n', 3.3,6.3,8.3,maxium(3,6,8));
		System.out.pringln('%s,%s,%s中最大的数为：%s\n', 'apple','banana','orange',maxium('apple','banana','orange'));
	}
}

//基本的泛型类
public class GenericClass<T> {
	private T t;

	public void setValue(T t) {
		this.t = t;
	}
	public T getValue() {
		return t;
	}

	public static void main(String[] args) {
		GenericClass<Integer> intValue = new GenericClass<Integer>();
		GenericClass<String> strValue = new GenericClass<String>();

		intValue.add(new Integer(3));
		strValue.add(new String('hello'));

		System.out.printf("整数类型实例化：%d\n",intValue.get());
		System.out.printf("字符串类型实例化：%d\n", strValue.get());

	}
}

//类型通配符
import java.util.*;

public class GenericTest {
	public static void main(String[] args) {
		//用同一个方法实例化了三种不同类型的对象。因为getData()方法的参数是List类型，而name，age，number都是List的一种，所以三者都可以作为这个方法的实参，这就是类型通配符的好处。
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();

		name.add('jignwei');
		age.add(18);
		number.add(15532779298);

		getData(name);
		getData(age);
		getData(number);
	}
	
	public static void getData(List<?> data) {//类型通配符的用法
		System.out.println('data:' + data.get(0));
	}	
}

//类型通配符的上下限
public class GenericTest {
	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();

		name.add('jingwei');
		age.add(18);
		number.add(15532779298);

		//getUperNumber(name);//该行代码会导致错误，因为String不是Number的子类型
		getUperNumber(age);
		getUperNumber(number);
	}
	public static void getData(List<?> data) {
		System.out.pringln('data:' + data.get(0));
	}

	publilc static void getUperNumber(List<? extends Number> data) {
		//List<? extends Number> 定义上限为Number类型
		System.out.pringln("data:" + data.get(0));
	}
}