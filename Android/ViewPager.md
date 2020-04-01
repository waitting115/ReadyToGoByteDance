# ViewPager

ViewPager组件可以很方便你的开啊水平View切换的功能，用户通过手指左右滑动，就可以轻易切换不同的视图，现在大量的用在APP的引导页上

ViewPager类直接继承自ViewGroup类，是一个容器类，可以在其中添加其他的View类，也可以添加Activity

用户通常采用适配器填充ViewPager中的界面内容，填充ViewPager的适配器称为PageAdapter

创建自定义的PageAdapter的基本步骤如下：

- 定义一个类继承自PageAdapter
- 创建用于填充的View集合
- 重写getCount()、destoryItem()、instantiateItem()方法；其中getCount()方法返回用于填充的页面个数，destoryItem()方法用于当前页面滑出屏幕焦点后销毁视图，instantiateItem()方法用于当前页面划入屏幕焦点时创建视图。





*