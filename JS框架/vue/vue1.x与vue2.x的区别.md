# Vue1.x与Vue2.x的区别

## 1.作用域限制：

- 1.x可以随意定义作用域
- 2.x不允许html或者body作为作用域

## 2.json无需过滤器

- 1.x如果想显示要是用json过滤器：{{obj | json}}
- 2.x中json数据无需过滤器可以直接显示：{{obj}}