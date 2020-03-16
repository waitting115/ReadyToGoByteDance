
const template = document.createElement('template');//创建一个template实例，用来存放组件的html和css代码

template.innerHTML = `
	<style>
	   :host {
	     display: flex;
	     align-items: center;
	     width: 450px;
	     height: 180px;
	     background-color: #d4d4d4;
	     border: 1px solid #d5d5d5;
	     box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.1);
	     border-radius: 3px;
	     overflow: hidden;
	     padding: 10px;
	     box-sizing: border-box;
	     font-family: 'Poppins', sans-serif;
	   }
	   .image {
	     flex: 0 0 auto;
	     width: 160px;
	     height: 160px;
	     vertical-align: middle;
	     border-radius: 5px;
	   }
	   .container {
	     box-sizing: border-box;
	     padding: 20px;
	     height: 160px;
	   }
	   .container > .name {
	     font-size: 20px;
	     font-weight: 600;
	     line-height: 1;
	     margin: 0;
	     margin-bottom: 5px;
	   }
	   .container > .email {
	     font-size: 12px;
	     opacity: 0.75;
	     line-height: 1;
	     margin: 0;
	     margin-bottom: 15px;
	   }
	   .container > .button {
	     padding: 10px 25px;
	     font-size: 12px;
	     border-radius: 5px;
	     text-transform: uppercase;
	   }
  </style>
  
  <img class="image">
  <div class="container">
    <p class="name"></p>
    <p class="email"></p>
    <button class="button">Follow John</button>
  </div>
`


class UserCard extends HTMLElement {//继承了HTML元素特性，
	constructor () {
		super();

		let shadow = this.attachShadow( {mode: 'closed'} );

		let content = template.content.cloneNode(true);
		content.querySelector('.container>.name').innerText = this.getAttribute('name');

		shadow.appendChild(content);

		// this._shadowRoot = this.attachShadow(({ mode : 'open'}));//创建一个shadow DOM，它用于封装html，css和js，将它们隐藏起来，内外代码互不影响
		// const content = template.content.cloneNode(true)
		// // 属性
		// content.querySelector('p.name').innerText = this.getAttribute('name');
		// content.querySelector('.container>.email').innerText = this.getAttribute('email')
		// content.querySelector('img.image').setAttribute('src', this.getAttribute('img'))
		// this._shadowRoot.appendChild(content);//克隆一份template中的代码放进shadow DOM Tree中

		// // 行为
		// this.$button = this._shadowRoot.querySelector('button');//抓到元素
		// this.$button.addEventListener('click', () => {
		// 	//do something
		// 	// alert()
		// })


	}
	

	// get label () {
	// 	return this.getAttribute('label');
	// }

	// set label (val) {
	// 	this.setAttribute('label',val);
	// }

	// //生命周期函数
	// connectedCallback  () {
	// 	//当custom element首次被插入到文档DOM时，被调用
	// }
	// disconnectedCallback () {
	// 	//当custom element从文档DOM中删除时调用
	// }
	// adoptedCallback () {
	// 	//当custom element被移动到新的文档时被调用
	// }
	// attributeChangedCallback (name, oldVal, newVal) {//在这可以与外界相联系，每一个自定义属性变化都会引用此函数
	// 	//当custom element增加、删除、修改自身属性时被调用。
	// 	this[name] = newVal;
	// 	this.render();//渲染
	// }

	// render () {
	// 	this.$button.innerHTML = this.label;
	// }
}

window.customElements.define('user-card', UserCard);//注册新元素，获取注册实例,它有3个参数，（带有-的组件名，它的类对象，继承自什么元素，不如下面的例子）
// wineow.customElements.define('my-p', MyP, { extends : 'p'});这样可以扩展原生元素
// 使用： <p is='my-p'></p>