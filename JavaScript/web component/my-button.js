//首先创建一个template
const template = document.createElement('template');

template.innerHTML = `
	<style>
		.container {
			padding: 8px;
		}
		button {
			display: block;
			overflow: hidden;
			position: relative;
			padding: 0 16px;
			font-size: 16px;
	      	font-weight: bold;
	      	text-overflow: ellipsis;
	      	white-space: nowrap;
	      	cursor: pointer;
	      	outline: none;
	      	width: 100%;
	      	height: 40px;
	      	box-sizing: border-box;
	      	border: 1px solid #a1a1a1;
	      	background: #ffffff;
	      	box-shadow: 0 2px 4px 0 rgba(0,0,0, 0.05), 0 2px 8px 0 rgba(161,161,161, 0.4);
	      	color: #363636;
	      	cursor: pointer;
		}
	</style>
	<div class='container'>
		<button>Label</button>	
	</div>
`;

class MyButton extends HTMLElement {
	constructor () {
		super();//一定要有
		this._shadowRoot = this.attachShadow( { mode : 'open' });//open：外界可访问组件；closed：不可访问
		this._shadowRoot.appendChild(template.content.cloneNode(true));//克隆一份template放到shadow DOM

		this.$container = this._shadowRoot.querySelector('div.container')

		this.$button = this._shadowRoot.querySelector('button');
		// this.$button.addEventListener('click', () => {
			//do something
			// alert('you are success!')

			// this.onClick('Hello from within the Custom Elements')//调用外部提供的函数，在组件内

			// 将自定义事件作为API公开给外部使用
			// this.dispatchEvent(//自定义事件
			// 	new CustomEvent('onClick', {//该接口表示由应用程序出于任何目的的初始化事件
			// 		detail: 'Hello from within the custom elements',
			// 	})
			// );
		// });
	}	

	get label () {
		return this.getAttribute('label');
	}
	set label (val) {
		this.setAttribute('label', val);
	}

	static get observedAttributes() {
		return ['label'];//要监听的属性列表，它里面的属性变化才会触发attributeChangedCallback()生命周期函数
	}

	attributeChangedCallback (name, oldVal, newVal) {//生命周期函数，属性值变化时调用
		// this.[name] = newVal;//让get set所取代
		this.render();
	}

	render () {
		this.$button.innerHTML = this.label;
	}

	connectedCallback() {//组件首次被调用时触发
		if (this.hasAttribute('as-atom')) {
			this.$container.style.padding = '0px';
		}
	}
}

window.customElements.define('my-button', MyButton);//注册新元素，获取注册实例,它有3个参数，（带有-的组件名，它的类对象，继承自什么元素，不如下面的例子）
// wineow.customElements.define('my-p', MyP, { extends : 'p'});这样可以扩展原生元素
// 使用： <p is='my-p'></p>

