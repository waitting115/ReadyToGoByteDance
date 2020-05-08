const template2 = document.createElement('template');

template2.innerHTML = `
	<style>
	    :host {
	      font-family: sans-serif;
	    }
	    .dropdown {
	      padding: 3px 8px 8px;
	    }
	    .label {
	      display: block;
	      margin-bottom: 5px;
	      color: #000000;
	      font-size: 16px;
	      font-weight: normal;
	      line-height: 16px;
	    }
	    .dropdown-list-container {
	      position: relative;
	    }
	    .dropdown-list {
	      position: absolute;
	      width: 100%;
	      display: none;
	      max-height: 192px;
	      overflow-y: auto;
	      margin: 4px 0 0;
	      padding: 0;
	      background-color: #ffffff;
	      border: 1px solid #a1a1a1;
	      box-shadow: 0 2px 4px 0 rgba(0,0,0, 0.05), 0 2px 8px 0 rgba(161,161,161, 0.4);
	      list-style: none;
	    }
	    .dropdown-list li {
	      display: flex;
	      align-items: center;
	      margin: 4px 0;
	      padding: 0 7px;
	      font-size: 16px;
	      height: 40px;
	      cursor: pointer;
	    }

	    .dropdown.open .dropdown-list {
	    	display: flex;
	    	flex-direction: column;
	    }

	    .dropdown-list li.selected {
      		font-weight: 600;
    	}
	  </style>
	  <div class="dropdown">
	    <span class="label">Label</span>
	    <my-button as-atom>Content</my-button>
	    <div class="dropdown-list-container">
	      <ul class="dropdown-list"></ul>
	    </div>
	  </div>
`;

class MyDropdown extends HTMLElement {
	constructor () {
		super();
		this._shadowRoot = this.attachShadow({ mode : 'open' });
		this._shadowRoot.appendChild(template2.content.cloneNode(true));//true 克隆所有后代

		this.$label = this._shadowRoot.querySelector('.label');
		this.$button = this._shadowRoot.querySelector('my-button');
		this.$dropdown = this._shadowRoot.querySelector('.dropdown');
		this.$dropdownList = this._shadowRoot.querySelector('.dropdown-list');

		this.open = false;//控制dropdown的开关

		this.$button.addEventListener('click', this.toggleOpen.bind(this));//点击按钮实现切换
	}

	static get observedAttributes () {
		return ['label', 'option', 'options'];//监听元素
	}

	get label () {
		return this.getAttribute('label');
	}
	set label (val) {
		this.setAttribute('label', val);
	}

	get option () {
		return this.getAttribute('option');
	}
	set option (val) {
		this.setAttribute('option', val);
	}

	get options () {
		return JSON.parse(this.getAttribute('options'));
	}
	set options (val) {
		this.setAttribute('options', JSON.stringify(val));
	}

	attributeChangedCallback (name, oldVal, newVal) {
		this.render();
	}

	render () {
		this.$label.innerTHML = this.label;
		// this.$button.setAttribute('label', 'Select Option');
		if(this.options) {//按钮中显示当前选项
			this.$button.setAttribute('label', this.options[this.option].label);
		}
		this.$dropdownList.innerHTML = '';
		Object.keys(this.options || {}).forEach( key => {
			let option = this.options[key]
			let $option = document.createElement('li');
			$option.innerHTML = option.label;
			if(this.option && this.option === key) {//为选中的li添加加粗字体
				$option.classList.add('selected');
			}
			$option.addEventListener('click', () => {//为每个li添加click
				this.option = key;//更新option
				this.toggleOpen();//点击后就关闭列表
				this.dispatchEvent(//自定义事件
					new CustomEvent('onChange', { detail : key })//该接口表示由应用程序出于任何目的的初始化事件
				);
				this.render();//渲染
			})
			this.$dropdownList.appendChild($option);
		})
	}

	toggleOpen(event) {
		this.open = !this.open;
		this.open
			? this.$dropdown.classList.add('open')
			: this.$dropdown.classList.remove('open');
	}
}

window.customElements.define('my-dropdown', MyDropdown);