# BFC

**格式化上下文（Block Fromatting Content，BFC）**

**它是web页面可视化CSS渲染的一部分，是块盒子布局过程发生的区域，也是浮动元素与其他元素交互的区域。**

下面方式会创建BFC：

- 根元素（<html>）
- 浮动元素（元素的float不是none）
- 绝对定位元素（元素的position为absolute或fixed）
- 行内块元素（元素的display为inline-block）
- 表格单元格（元素的display为table-cell，默认值）
- 表格标题（元素的display为table-caption，默认值）
- 匿名表格单元格元素 （元素的 [`display`](https://developer.mozilla.org/zh-CN/docs/Web/CSS/display)为 `table、``table-row`、 `table-row-group、``table-header-group、``table-footer-group`（分别是HTML table、row、tbody、thead、tfoot的默认属性）或 `inline-table`） 
- overflow值不为visible的元素
- display为flow-root的元素
- contain值为layout、content或paint的元素
- 弹性元素（display为flex或inline-flex元素的直接子元素）
- 网格元素（display为grid或inline-grid元素的直接子元素）
- 多列容器（元素的column-count或column-width不为auto，包括column-count为1）
- column-span为all的元素会始终创建一个FBC，即使该元素没有包裹在一个多列容器中。



**块格式化上下文包含创建它的元素内的所有子元素。**

块格式化上下文对浮动定位和清除浮动都很重要。

浮动定位与清除浮动时只会应用于同一个BFC内的元素。

浮动不会影响其他BFC中元素的布局。















*