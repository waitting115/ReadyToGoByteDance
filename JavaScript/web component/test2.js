const template2 = document.createElement('template');
template2.innerHTML = `  
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
  <div class="container">
    <button>Label</button>
  </div>
`;
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template2.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
    this.$button.addEventListener('click', () => {
      // this.onClick('Hello from within the Custom Element');
      alert('true')
    })
  }
  get label() {
    return this.getAttribute('label');
  }
  set label(val) {
    this.setAttribute('label', val);
  }
  static get observedAttributes() {
    return ['label'];//return[要监听的属性列表]（这里只要label属性变化，就会触发attributeChangedCallback函数）
  }
  attributeChangedCallback(name, oldVal, newVal) {//需配合observedAttributes属性来监听指定的属性
    // this[name] = newVal;//用上面的get  set代替了其功能
    this.render();
  }
  render() {
    this.$button.innerHTML = this.label;
  }
}
window.customElements.define('my-button', Button);
