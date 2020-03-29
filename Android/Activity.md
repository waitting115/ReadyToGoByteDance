# Activity（活动）

Activity（活动）是一种展示型组件，它主要用于实现应用功能逻辑，并通过界面显示数据或接收用户输入。

一个应用程序可以包含0个或多个活动。没有活动的应用程序，用户将无法看到程序界面，这种应用程序通常在后台运行，不涉及用户交互。

为活动绑定自定义视图：

> setContentView(R.layout.activity_main);

启动另一个活动：

> startActivity(intent);

结束活动：

> finish();

# Activity生命周期

Activity的生命周期是指Activity从创建到被销毁的整个过程。在一个生命周期内，Activity可能存在多种状态。深入了解Activity的生命周期，有助于更合理管理应用程序资源，设计出效率更高的应用。

- 启动状态：当Activity启动之后便会进入下一状态。
- 运行状态：Activity处于屏幕最前端，可与用户进行交互。
- 暂停状态：Activity仍然可见，但无法获取焦点，用户对它操作没有影响。
- 停止状态：Activity完全不可见，系统内存不足时会销毁该Activity。
- 销毁状态：Activity将被清理出内存。

Activity生命周期包括创建、可见、获取焦点、失去焦点、不可见、重新可见、销毁等环节，每个环节Activity都定义了相关的回调方法，具体如下：

- onCreate(): Activity创建时调用，通常做一些初始化设置。
- onStart():Activity即将可见时调用。
- onResume():Activity获取焦点时调用。
- onPause():当前Activity被其他Activity覆盖或屏幕锁屏时调用。
- onStop():Activity对用户不可见时调用。
- onDestory():Acrivity销毁时调用。
- onRestart():Activity从停止状态到再次启动时调用。

# Activity传递数据

## putExtra()方法

putExtra(name.value)方法可将制定的数据封装到Intent对象中。其中，name为表示数据名称的字符串，value为要传递的各种数据类型的值。

要获取Intent对象中封装的数据，可调用各种getXXXExtra()方法。

- getCharExtra(String name, char defaultValue):获取指定name的char类型数据。
- getFloatExtra(String name, float defaultValue):获取指定name 的float类型数据。
- getFloatArrayExtra(String name):获取指定name 的float类型的数组。
- getIntArrayExtra(String name):获取指定name的Int类型的数组。
- getIntExtra(String name, int defaultValue):获取指定name的int类型数据。
- getStringArrayExtra(String name): 获取指定name 的String类型数组。
- getStringExtra(String name):获取指定name 的String类型数据。
- getSerializableExtra(String name): 获取指定name的对象数据。

从第一个Activity传递数据到第二个Activity：

~~~java
//第一个Activity
Intent intent = new Intent(MainActivity.this, Main2Activity.class);//从哪发到哪
intent.putExtra("name", "zhangsan");//添加数据
intent.putExtra("score", 95);
startActivity(intent);//发出去

//第二个Activity
Intent intent = getIntent();//拿到intent
String name = intent.getStringExtra('name');//获取名为name 的String类型数据
int score = intent.getIntExtra('score', 0);//获取名为score的int类型数据，默认为0
~~~

## Bundle

将各种数据封装到一个Bundle对象中，再将Bundle对象封装到Intent对象中传递给启动的活动。

Bundle对象的各种putXXX（String key， XXX value)方法，可将XXX类型的数据封装到其中，对应的用getXXX(String key)方法从其中获取数据。

Bundle对象准备好之后，调用putExtras(bundle)或putExtra(name, bundle)方法将其封装到Intent对象中。

要从Intent对象中获取Bundle对象时，调用对应的getExtras()或getBundleExtra()方法即可。

将第一个Activity中的数据传递到第二个Activity：

~~~java
//第一个Activity
Bundle bundle = new Bundle();
bundle.putString("name", "zhangsan");
bundle.putInt("score", 95);
intent.putExtras(bundle);
startActivity(intent);

//第二个Activity
Bundle bundle = getIntent().getExtras();//获取intent，然后获取intent中的bundle
String name =bundle.getString("name");
int score = bundle.getInt("score", 0);
~~~

## 获取活动返回的数据

第一个界面（MainActivity）；

第二个界面（Main2Activity）；

1向2请求返回结果用startActivityforResult()方法；

2向1设置结果用setResult()方法；

1处理返回结果：onActivityResult()方法。

**在第一个Activity中，用startActivityForResult(intent, requestCode)方法来启动活动，请求返回结果**：

- 参数intent是一个Intent对象，用于封装需要传递给互动的数据。
- 参数requestCode为请求码，是一个整数，用来标识当前请求。一个活动可能会接收到其他不同活动的请求，从活动返回时，它会原样返回接收到的请求码。
- 在处理返回结果时，可通过请求码判断是不是从所请求的活动返回。

**在第二个Activity中，用setTesult(resultCode, intent)方法设置返回结果，resultCode为结果代码，intent为封装了返回数据的Intent对象。**

**在第一个Activity中需要重写onActivityResult(int requestCode, int resultCode, Intent data)方法来处理返回结果：**

- requestCode为从所请求的活动返回的它所接收到的请求码；
- resultCode为结果代码，常量RESULT_CANCELED表示用户取消了操作，RESULT_OK表示用户正确完成了操作。
- data为请求活动返回的Intent对象，从中可获取返回的数据。

~~~java
//第一个Activity
Intent intent = new Intent(MainActivity.this, Main2Activity.class);
startActivityForResult(intent, 1);

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode,data);
    if(requestCode == 1) {
        if(resultCode == 1) {
            String string = data.getStringExtra("ExtraData");
        }
    }
}
//第二个Activity
Intent intent = new Intent();
intent.putExtra("ExtraData", "MainActivity");
setResult(1, intent);
finish();
~~~

java中的箭头函数(x) -> {x};



*