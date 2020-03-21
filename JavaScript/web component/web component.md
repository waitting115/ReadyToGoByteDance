# WEB components

web components技术用来用原生js写组件。

它右3个技术组成：

- Custom elements（自定义元素）：

  - 一组JavaScript API，允许您定义custom elements及其行为，然后按需使用

- Shadow DOM（影子DOM）：

  - 一组JavaScript API，用于将封装的shadow DOM树附加到元素（与主文档分开呈现）并控制其关联的功能。
  - 通过这种方式可以保持元素的功能私有，这样它们就可以被脚本化和样式化，而不用担心与文档的其他部分发生冲突。

- HTML templates（HTML 模板）：

  - <template>和<slot>元素使您可以编写不在呈现页面中显示的标记模板。然后它们可以作为自定义元素结构的基础被多次重用。 

实现web components的基本方法通常如下所示：

1. 创建一个类或函数定义组件的功能。

2. 使用customElementRegistry接口的define()方法注册组件，其三个参数（组件名，指定组件功能的类，可选的所继承自的元素）。使用方法如下：

   1. > window.customElements.define('my-bottun', MyButton);

3. 如果需要，使用Element.attachShadow()方法将一个shadow DOM附加到组件上。适用通常的DOM方法向其添加元素、事件监听等

4. 如果需要，使用<template\>    <slot\>定义一个HTML模板，再次使用常规DOM方法克隆一份放到shadow DOM中。
5. 在页面任何位置使用组件，就像使用常规HTML元素一样。



生命周期回调函数：

- `connectedCallback`：当 custom element首次被插入文档DOM时，被调用。
- `disconnectedCallback`：当 custom element从文档DOM中删除时，被调用。
- `adoptedCallback`：当 custom element被移动到新的文档时，被调用。
- `attributeChangedCallback`: 当 custom element增加、删除、修改自身属性时，被调用。

### \<slot>

 在定义组件内部模版时可使用 <slot> 标记声明此处未来可能会被外部元素代替，以增强组件的扩展性。 

使用：

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>slot</title>
</head>
<body>
	<better-button>
		<!-- 具名插槽 -->
		<img src="img/a.jpg" slot='icon' alt="">
		<span>Setting</span>
	</better-button>

	<template id="myTemplate">
		<style>
			
		</style>
		<!-- 具名插槽	 -->
		<slot name='icon'></slot>
		<span id="wrapper">
			<!-- 默认插槽 -->
			<slot>Button</slot>
		</span>
	</template>

	<script>
		class BetterButton extends HTMLElement {
			constructor () {
				super ();
				let shadowRoot = this.attachShadow( { mode: 'closed' } );
				let template = document.querySelector('#myTemplate')

				shadowRoot.appendChild(template.content.cloneNode(true));
			}
		}
		window.customElements.define('better-button', BetterButton);
	</script>
</body>
</html>
~~~













*











*