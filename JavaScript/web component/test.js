
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

		this.
	}
}

window.customElements.define('user-card', UserCard);//注册新元素，获取注册实例,它有3个参数，（带有-的组件名，它的类对象，继承自什么元素，不如下面的例子）
// wineow.customElements.define('my-p', MyP, { extends : 'p'});
// 使用： <p is='my-p'></p>