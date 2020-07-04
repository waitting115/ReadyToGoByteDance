# Vue1.x与Vue2.x的区别

## 1.作用域限制：

- 1.x可以随意定义作用域
- 2.x不允许html或者body作为作用域

## 2.json无需过滤器

- 1.x如果想显示要是用json过滤器：{{obj | json}}
- 2.x中json数据无需过滤器可以直接显示：{{obj}}

## 3.生命周期的变化

- vue1.x的生命周期：
  - created（）——实例创建完成
  - beforeCompile（）——实例编译前
  - compiled（）——实例编译后
  - ready（）——数据渲染完成（类似window.onload）
  - beforeDestroy（）——实例销毁前
  - destroyed（）——实例销毁后
- vue2.x的生命周期：
  - beforeCreated（）——初始化（注入&校验）之前
  - created（）——初始化（注入&校验）之后
  - beforeMount（）——模板以及el外部的HTML 编译完成，创建vm.$el并用其替换“el”之前
  - mounted（）——创建vm.$el并用其替换“el”之后
  - beforeUpdate（）——当data被修改时触发虚拟DOM重新渲染并应用更新之前
  - updated（）——当data被修改时触发虚拟DOM重新渲染并应用更新之后
  - beforeDestory（）——销毁实例前
  - destroyed（）——销毁实例后

销毁vue实例：app.$destroy();

## 4.循环没有 了$key和$value
取而代之的是：
vue2.x:
<div v-for="(value, key) in json">
  {{value}} = {{key}}
</div>


## 5.2.x取消了所有的过滤器
如uppercase等过滤器，因为过于简单，若用自己写；
<div id="div1">
  {{msg | uppercase}}
</div>

<script>
  Vue.filter('uppercase', (val) => {
    return val.toUpperCase();
  });
</script>

若需传参：
<div id="div1">
  {{msg | uppercase('1','2')}}
</div>

<script>
  Vue.filter('uppercase', (val, arg1, arg2) => {
    console.log(arg1, arg2)
    return val.toUpperCase();
  });
</script>
~~~html
<div v-for="(value, key) in json">{{key}}={{value}}</div>
~~~

