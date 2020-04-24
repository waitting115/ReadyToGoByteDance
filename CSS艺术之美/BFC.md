# BFC

**BFC（Block formatting context）直译为：块级格式化上下文，它是一个独立的区域，决定了其内部如何布局，并与外界毫不相干。**

 **BFC就是页面上的一个隔离的独立容器，容器里面的子元素不会影响到外面的元素。反之也如此。** 

## Box：

css布局的基本单位。

一个页面是由很多个box组成的，元素的类型和display属性决定了这个box 的类型，不同类型的box会有不同的渲染方式。

**box种类：**

- block-level box: display 属性为block，list-item，table的元素，会生成block-level box，并且参与Block Fomatting Context（BFC）。
- inline-level box：display 属性为inline，inline-block，inline-table的元素，会生成inline-level box，并且参与Inline Formatting Context（IFC）。
- run-in box：目前很少浏览器支持，这里不做多解释。

## Formatting Context

Formatting context 是 W3C CSS2.1 规范中的一个概念。它是页面中的一块渲染区域，并且有一套渲染规则，它决定了其子元素将如何定位，以及和其他元素的关系和相互作用。最常见的 Formatting context 有 Block fomatting context (简称BFC)和 Inline formatting context (简称IFC)。

## BFC 的布局规则

- 内部的Box会垂直一个个放置
- **Box垂直方向的距离由margin决定，属于同一个BFC的相邻的两个Box的margin会发生重叠**
-  每个盒子（块盒与行盒）的margin box的左边，与包含块border box的左边相接触(对于从左往右的格式化，否则相反)。即使存在浮动也是如此。 
-  BFC的区域不会与float box重叠。 
-  BFC就是页面上的一个隔离的独立容器，容器里面的子元素不会影响到外面的元素。反之也如此。 
-  **计算BFC的高度时，浮动元素也参与计算。** 

## 如何创建BFC

- float的值不是none
- position的值不是static或者relative
- display的值是inline-block、table-cell、flex、table-cation或者inline-flex
- overflow的值不是visible

## BFC的作用

### 利用BFC避免margin重叠

做法就是将两个margin重叠的元素其中之一用一个div包围起来，使之成为一个独立的BFC（或者两个都包围起来）

### 利用BFC清除浮动

为浮动的最后一个元素设置overflow：hidden或auto，使之成为一个BFC。

因为**计算BFC高度时，浮动元素也参与计算**，所以浮动元素就放到了该容器中。















*