# ListView

ListView是android开发中比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示，列表的显示需要三个元素：

- ListView：用来展示列表的视图View；
- 适配器Adapter：用来把数据映射到ListView上的中介；
- 数据：包括具体的将被映射的字符串，图片，或者基本组件

Adapter接口能把数据适配成ListView能访问的数据形式；

Adapter本身不维护数据，数据保存在数据存储区中如Array，但是Adapter适配了数据，如getCount返回数据的个数，getItem返回指定的数据，同时Adapter还维护数据的显示，也就是Item子视图的显示，getView需要返回一个View给ListView。

~~~java
Public interface Adapter
{
    public abstract int getCount();//得到ListView所要显示的总数目
    public abstract object getItem(int i);//得到第i条条目对象
    public abstract View getBiew(int i, View view, BiewGroup parent);//得到当前条目的view
}
~~~

## ArrayAdapter

ArrayAdapter通常用于适配TextView控件，常采用下面的构造方法：

public ArrayAdapter(Context context, int resoutce, T[] objects);

- context：表示上下文对象
- resoutce：Item布局的资源id
- objects：需要适配的数据

## SimpleAdapter

SimpleAdapter通常采用下面的构造方法：

public SimpleAdapter(Context context, List<? Extends Map<String, ?>>data, int resource, String[] from, int[] to);

- context：表示上下文
- data：数据集合
- resource：Item布局的资源id
- from：Map集合中的key值
- to：item布局中对应的控件

## ListView的开发步骤

- 设计主界面layout，比如设计activity_main.xml
- 设计ListView条目的layout
- 设计Adapter，比如设计ArrayAdapter或SimpleAdapter
- 设置Adapter，listView.setAdapter(simpleAdapter)
- 设置条目被点击或被选中的监听事件：listView.setOnItemClickListener（被点击）或listView.setOnItemSelectedListener（被选中）







*