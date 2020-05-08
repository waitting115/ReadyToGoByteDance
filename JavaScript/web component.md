# web组件初学者教程（2019）

本教程教您如何构建第一个Web组件以及如何在应用程序中使用它们。在开始之前，让我们花一点时间来全面了解Web组件：近年来，Web组件（也称为“ **定制元素”**）已成为[几种浏览器](https://caniuse.com/#feat=custom-elementsv1)的[标准API](https://caniuse.com/#feat=custom-elementsv1)，允许开发人员仅使用HTML，CSS和JavaScript来实现可重用的组件。这里不需要React，Angular或Vue。相反，“自定义元素”可将所有结构（HTML），样式（CSS）和行为（JavaScript）封装在一个自定义HTML元素中。例如，假设您可能有一个HTML下拉组件，如以下代码片段所示：

```jsx
<my-dropdown
  label="Dropdown"
  option="option2"
  options='{ "option1": { "label": "Option 1" }, "option2": { "label": "Option 2" } }'
></my-dropdown>
```

在本教程中，我们将使用Web Components从头开始逐步实现此下拉组件。之后，您可以继续在整个应用程序中使用它，使其成为开源Web组件以将其安装在其他地方，或者使用类似React的框架[为您的React应用程序](https://github.com/the-road-to-learn-react/use-custom-element)构建基于[Web Components](https://github.com/the-road-to-learn-react/use-custom-element)的[坚实基础](https://github.com/the-road-to-learn-react/use-custom-element)。

## 为什么要使用WEB组件？

一个个人故事来说明如何从Web组件中受益：当我的一个拥有多个跨职能团队的客户希望基于样式指南创建UI库时，我选择了Web组件。两个团队开始根据样式指南实施组件，但是每个团队使用不同的框架：React和Angular。即使两个实现都共享*一点*与样式指南中的结构（HTML）和样式（CSS）相同，行为的实现（例如，打开/关闭下拉菜单，选择下拉菜单中的项目）由每个团队根据其期望的框架来实施。此外，如果样式指南在组件的样式或结构上犯了错误，则每个团队都会单独纠正这些错误，而无需随后修改样式指南。这两个UI库的外观和行为各不相同。

*注意：如果没有在代码中积极主动地使用样式指南（例如，生活样式指南），而又仅作为最终最终过时的文档，则独立于Web组件，这是样式指南中的常见缺陷。*

最终，两个团队聚在一起讨论了解决问题的方法。他们要求我调查Web组件，以了解是否可以用它们解决问题。Web组件确实提供了一种引人注目的解决方案：两个团队都可以使用基于样式指南的通用Web组件。Dropdown，Button和Table之类的组件只能使用HTML，CSS和JavaScript来实现。而且，以后不会强迫他们为自己的单个应用程序显式使用Web组件，而可以在React或Angular应用程序中使用这些组件。如果样式指南的要求发生变化，或者需要修复组件，则两个团队可以在其共享的Web组件UI库上进行协作。

## WEB组件入门

如果您需要以下教程的入门项目，则可以[从GitHub克隆该](https://github.com/rwieruch/web-components-starter-kit)项目。您应该查看*dist /*和*src /*文件夹，以按照本教程进行调整。教程中完成的项目可以[在GitHub上找到](https://github.com/rwieruch/web-components-dropdown)。

让我们开始第一个Web组件。我们不会从一开始就实现下拉组件，而是一个简单的按钮组件，稍后将在下拉组件中使用它。使用Web组件实现简单的按钮组件没有多大意义，因为您可以将``元素与某些CSS一起使用，但是，为了学习Web组件，我们将从此按钮组件开始。因此，以下代码块足以为具有自定义结构和样式的单个按钮创建Web组件：

```jsx
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
  <div class="container">
    <button>Label</button>
  </div>
`;
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
  }
}
window.customElements.define('my-button', Button);
```

让我们逐步进行所有操作。自定义元素（Web组件）的定义与从[HTMLElement](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement)扩展的[JavaScript类](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes)一起发生，[该类](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes)可帮助您实现*任何*自定义HTML元素。通过扩展它，您将可以访问各种类方法，例如，组件的**生命周期回调**（生命周期方法），这些方法可以帮助您实现Web组件。稍后您将看到我们如何利用这些类方法。

此外，Web组件正在使用[Shadow DOM](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_shadow_DOM)，不应将其误认为Virtual DOM（性能优化）。Shadow DOM用于封装CSS，HTML和JavaScript，对于使用Web组件的外部组件/ HTML，应将其隐藏。您可以为您的Shadow DOM设置一个模式，在我们的示例[中将](https://developer.mozilla.org/en-US/docs/Web/API/Element/attachShadow)其[设置为true](https://developer.mozilla.org/en-US/docs/Web/API/Element/attachShadow)，以使Shadow DOM可以被外界访问。无论如何，您可以将Shadow DOM视为封装结构和样式的自定义元素内的子树。

构造函数中还有另一条语句，该语句通过从上方克隆声明的模板将子级追加到我们的Shadow DOM。[模板](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/template)通常用于使HTML可重用。但是，模板在Web组件中对于定义其结构和样式也起着至关重要的作用。在自定义元素的顶部，我们借助在自定义元素的构造函数中使用的模板定义了结构和样式。

我们的代码片段的最后一行通过在窗口上定义custom元素，将其定义为HTML的有效元素。第一个参数是我们可重用的自定义元素的名称，如HTML（必须带有连字符），第二个参数是我们的自定义元素（包括呈现的模板）的定义。之后，我们可以在HTML中的某处使用新的自定义元素``。请注意，自定义元素不能/不应用作自闭标签。

## 如何将属性传递给WEB组件？

到目前为止，除了具有自己的结构和样式之外，我们的自定义元素没有做太多事情。通过在某些CSS中使用button元素，我们可以实现相同的目的。但是，为了学习有关Web组件的知识，让我们继续使用自定义按钮元素。就目前而言，我们无法更改其显示的内容。例如，如何将标签作为HTML属性传递给它：

```html
<my-button label="Click Me"></my-button>
```

呈现的输出仍将显示使用`Label`字符串的内部自定义元素的模板。为了使自定义元素对此新属性做出反应，您可以观察它，并通过使用来自扩展HTMLElement类的类方法对其进行某些操作：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
  }
  static get observedAttributes() {
    return ['label'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this[name] = newVal;
  }
}
```

每次标签属性更改时，`attributeChangedCallback()`都会调用该函数，因为我们将标签定义为`observedAttributes()`函数中的可观察属性。在我们的例子中，回调函数除了在Web Component的类实例上设置标签（此处为`this.label = 'Click Me'`）之外没有做其他事情。但是，自定义元素仍未呈现此标签。为了调整渲染的输出，您必须抓住实际的HTML按钮并设置其HTML：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
  }
  static get observedAttributes() {
    return ['label'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this[name] = newVal;
    this.render();
  }
  render() {
    this.$button.innerHTML = this.label;
  }
}
```

现在，在标签内设置了初始标签属性。此外，自定义元素还将对属性的更改做出反应。您可以以相同的方式实现其他属性。但是，您会注意到，非JavaScript原语（例如对象和数组）需要以JSON格式的字符串形式传递。我们稍后将在实现下拉组件时看到这一点。

## 将属性反映到属性

到目前为止，我们已经使用**属性将信息传递给我们的Custom Element**。每次属性更改时，我们都会在回调函数中将此属性设置为Web组件实例上的属性。之后，我们必须对渲染进行所有必要的更改。但是，我们也可以使用[GET方法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Class_body_and_method_definitions)，以**反映属性的属性**。这样，我们确保始终获得最新值，而无需自己在回调函数中分配它。然后，`this.label`始终从我们的getter函数返回最近的属性：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
  }
  get label() {
    return this.getAttribute('label');
  }
  static get observedAttributes() {
    return ['label'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this.render();
  }
  render() {
    this.$button.innerHTML = this.label;
  }
}
```

就是为了将属性反映到属性。但是，相反，您也可以**将信息传递给具有properties的自定义元素**。例如，``除了使用属性来渲染按钮外，我们还可以将信息设置为元素的属性。通常在将诸如对象和数组之类的信息分配给元素时使用这种方式：

```jsx
<my-button></my-button>
<script>
  const element = document.querySelector('my-button');
  element.label = 'Click Me';
</script>
```

不幸的是，当使用属性而不是属性时，不再调用针对更改属性的回调函数，因为它仅对属性更改不处理属性做出反应。那是我们班上的set方法发挥作用的地方：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
  }
  get label() {
    return this.getAttribute('label');
  }
  set label(value) {
    this.setAttribute('label', value);
  }
  static get observedAttributes() {
    return ['label'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this.render();
  }
  render() {
    this.$button.innerHTML = this.label;
  }
}
```

现在，由于我们*设定的属性*从我们的元素在外面，我们的自定义元素的setter方法会确保**物业反映的属性**，通过元素的属性设置为反射属性值。之后，我们的属性回调再次运行，因为属性已更改，因此我们重新获得了渲染机制。

您可以为此类的每个方法添加控制台日志，以了解每种方法发生的时间顺序。通过打开浏览器的开发人员工具，也可以在DOM中看到整个反射：即使将元素设置为属性，该属性也应出现在元素上。

最后，在为我们的信息准备好getter和setter方法之后，我们可以将信息作为属性和属性传递给自定义元素。整个过程称为**将属性反映到属性**，反之亦然。

## 如何将功能传递给WEB组件？

最后但并非最不重要的一点是，我们需要在单击自定义元素时使其起作用。首先，自定义元素可以注册事件侦听器以对用户的交互做出反应。例如，我们可以使用按钮并为其添加事件监听器：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
    this.$button.addEventListener('click', () => {
      // do something
    });
  }
  get label() {
    return this.getAttribute('label');
  }
  set label(value) {
    this.setAttribute('label', value);
  }
  static get observedAttributes() {
    return ['label'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this.render();
  }
  render() {
    this.$button.innerHTML = this.label;
  }
}
```

*注意：可以从元素外部简单地添加此侦听器-无需在自定义元素中烦恼-但是，在自定义元素内部定义它可以让您更好地控制应传递给侦听器的内容在外面注册的*

缺少的是可以在此侦听器中调用的，从外部提供的回调函数。有多种方法可以解决此任务。首先，我们可以**将函数作为attribute传递**。但是，由于我们已经知道将非基元传递给HTML元素很麻烦，因此我们希望避免这种情况。其次，我们可以**将函数作为property传递**。让我们看看使用我们的自定义元素时的外观：

```jsx
<my-button label="Click Me"></my-button>
<script>
  document.querySelector('my-button').onClick = value =>
    console.log(value);
</script>
```

我们只是将`onClick`处理程序定义为元素的函数。接下来，我们可以在自定义元素的侦听器中调用此函数属性：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
    this.$button.addEventListener('click', () => {
      this.onClick('Hello from within the Custom Element');
    });
  }
  ...
}
```

查看您如何负责将什么传递给回调函数。如果您在custom元素中没有侦听器，则只需接收该事件。自己尝试。现在，即使这可以按预期工作，但我还是希望使用DOM API提供的内置事件系统。因此，让我们从外部注册一个事件侦听器，而不将函数作为属性分配给元素：

```jsx
<my-button label="Click Me"></my-button>
<script>
  document
    .querySelector('my-button')
    .addEventListener('click', value => console.log(value));
</script>
```

单击按钮时的输出与上一个相同，但是这次具有单击交互的事件侦听器。这样，自定义元素仍然可以通过使用click事件将信息发送到外界，因为自定义元素内部工作产生的消息仍然可以发送，并且可以在浏览器的日志记录中看到。这样，如果不需要特殊行为，也可以在自定义元素中省略事件侦听器的定义，如前所述。

需要注意的是，将所有内容保留为此类：我们只能将内置事件用于自定义元素。但是，如果以后要在其他环境（例如React）中使用Web组件，则可能还希望提供自定义事件（例如onClick）作为组件的API。当然，我们也可以手动将`click`事件从custom元素映射到`onClick`框架中的函数，但是如果我们可以简单地在此处使用相同的命名约定，那么麻烦就少了。让我们看一下如何使我们以前的实现又进一步支持自定义事件：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$button = this._shadowRoot.querySelector('button');
    this.$button.addEventListener('click', () => {
      this.dispatchEvent(//自定义事件
        new CustomEvent('onClick', {//该接口表示由应用程序出于任何目的初始化的事件
          detail: 'Hello from within the Custom Element',
        })
      );
    });
  }
  ...
}
```

现在，我们将自定义事件作为API公开给外部调用，`onClick`而该信息则通过optional `detail`属性传递。接下来，我们可以改为监听这个新的自定义事件：

```jsx
<my-button label="Click Me"></my-button>
<script>
  document
    .querySelector('my-button')
    .addEventListener('onClick', value => console.log(value));
</script>
```

从内置事件到自定义事件的最后一次重构是可选的。它只是在向您显示自定义事件的可能性，并且如果您要查找的话，也许可以为您提供更轻松的时间，以便稍后在您喜欢的框架中使用Web组件。

## WEB组件生命周期回调

我们几乎完成了自定义按钮。在继续使用自定义下拉菜单元素（将使用自定义按钮元素）之前，让我们添加最后一点。此刻，按钮定义了一个带有填充的内部容器元素。这对于将这些自定义按钮并排使用时非常有用。但是，在其他上下文（例如下拉组件）中使用按钮时，您可能希望从容器中删除此填充。因此，您可以使用Web组件的生命周期回调之一`connectedCallback`：

```jsx
class Button extends HTMLElement {
  constructor() {
    super();
    this._shadowRoot = this.attachShadow({ mode: 'open' });
    this._shadowRoot.appendChild(template.content.cloneNode(true));
    this.$container = this._shadowRoot.querySelector('.container');
    this.$button = this._shadowRoot.querySelector('button');
    ...
  }
  connectedCallback() {//组件首次被调用时调用
    if (this.hasAttribute('as-atom')) {
      this.$container.style.padding = '0px';
    }
  }
  ...
}
```

在我们的例子中，如果`as-atom`元素上存在一个名为set 的现有属性，它将把我们按钮容器的填充重置为零。顺便说一句，这就是如何创建一个伟大的UI库，同时牢记原子设计原则，而自定义按钮元素是一个原子，而自定义下拉元素是一个分子。也许两者后来都在一个更大的有机体中以另一种元素结束。现在，可以按以下方式使用我们的按钮而无需在下拉元素中填充：``。稍后将通过使用属性来设置按钮的标签。

但是生命周期回调呢？将`connectedCallback`Web组件附加到DOM后运行。这就是为什么您可以完成渲染组件后需要做的所有事情。当组件被删除时，存在一个等效的生命周期回调`disconnectedCallback`。另外，在`attributeChangedCallback`对属性更改做出反应之前，您已经在自定义元素中使用了生命周期方法。有[可用的Web组件不同的生命周期回调](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_custom_elements#Using_the_lifecycle_callbacks)，所以一定要检查他们的细节。

## WEB组件中的WEB组件

最后但并非最不重要的一点是，我们想在另一个Web组件中使用完成的Button Web组件。因此，我们将实现自定义下拉菜单元素，该元素应通过以下方式使用：

```jsx
<my-dropdown
  label="Dropdown"
  option="option2"
  options='{ "option1": { "label": "Option 1" }, "option2": { "label": "Option 2" } }'
></my-dropdown>
```

请注意，这些选项（它们是一个对象）将作为JSON格式的属性传递给自定义元素。如我们所知，将对象和数组作为属性传递会更方便：

```jsx
<my-dropdown
  label="Dropdown"
  option="option2"
></my-dropdown>
<script>
  document.querySelector('my-dropdown').options = {
    option1: { label: 'Option 1' },
    option2: { label: 'Option 2' },
  };
</script>
```

让我们深入研究自定义下拉元素的实现。我们将从一个简单的基础开始，该基础为定义Web组件的类定义结构，样式和样板代码。后者用于设置Shadow DOM的模式，将模板附加到我们的自定义元素，为属性/属性定义getter和setter方法，观察我们的属性更改并对它们做出反应：

```jsx
const template = document.createElement('template');
template.innerHTML = `
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
  </style>
  <div class="dropdown">
    <span class="label">Label</span>
    <my-button as-atom>Content</my-button>
    <div class="dropdown-list-container">
      <ul class="dropdown-list"></ul>
    </div>
  </div>
`;
class Dropdown extends HTMLElement {
  constructor() {
    super();
    this._sR = this.attachShadow({ mode: 'open' });
    this._sR.appendChild(template.content.cloneNode(true));
  }
  static get observedAttributes() {
    return ['label', 'option', 'options'];
  }
  get label() {
    return this.getAttribute('label');
  }
  set label(value) {
    this.setAttribute('label', value);
  }
  get option() {
    return this.getAttribute('option');
  }
  set option(value) {
    this.setAttribute('option', value);
  }
  get options() {
    return JSON.parse(this.getAttribute('options'));
  }
  set options(value) {
    this.setAttribute('options', JSON.stringify(value));
  }
  static get observedAttributes() {
    return ['label', 'option', 'options'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this.render();
  }
  render() {
  }
}
window.customElements.define('my-dropdown', Dropdown);
```

这里有几件事要注意：

- 首先，在我们的样式中，我们可以使用选择器为我们的自定义元素设置*全局样式*`:host`。
- 其次，模板使用我们的自定义按钮元素，但尚未为其提供label属性。
- 第三，每个属性/属性都有getter和setter，但是，`options`属性/属性反射的getter和setter 会将对象从JSON解析到JSON。

*注意：除上述所有内容外，您还可能注意到所有关于属性/属性反射的getter和setter方法的样板。同样，属性的生命周期回调看起来是重复的，并且构造函数与自定义按钮元素中的构造函数相同。稍后您可能会了解到，在Web组件之上可以使用各种轻量级库（例如，带有LitHTML的LitElement）来为我们消除这种重复性。*

到目前为止，尚未使用所有传递的属性和属性。我们只是用空的render方法对它们做出反应。让我们通过将它们分配给下拉菜单和按钮元素来使用它们：

```jsx
class Dropdown extends HTMLElement {
  constructor() {
    super();
    this._sR = this.attachShadow({ mode: 'open' });
    this._sR.appendChild(template.content.cloneNode(true));
    this.$label = this._sR.querySelector('.label');
    this.$button = this._sR.querySelector('my-button');
  }
  ...
  static get observedAttributes() {
    return ['label', 'option', 'options'];
  }
  attributeChangedCallback(name, oldVal, newVal) {
    this.render();
  }
  render() {
    this.$label.innerHTML = this.label;
    this.$button.setAttribute('label', 'Select Option');
  }
}
window.customElements.define('my-dropdown', Dropdown);
```

下拉菜单从外部将其标签作为属性设置为内部HTML，而按钮现在将任意标签设置为属性。我们稍后将根据下拉菜单中的选定选项设置此标签。同样，我们可以利用这些选项为下拉菜单呈现实际的可选项目：

```jsx
class Dropdown extends HTMLElement {
  constructor() {
    super();
    this._sR = this.attachShadow({ mode: 'open' });
    this._sR.appendChild(template.content.cloneNode(true));
    this.$label = this._sR.querySelector('.label');
    this.$button = this._sR.querySelector('my-button');
    this.$dropdownList = this._sR.querySelector('.dropdown-list');
  }
  ...
  render() {
    this.$label.innerHTML = this.label;
    this.$button.setAttribute('label', 'Select Option');
    this.$dropdownList.innerHTML = '';
    Object.keys(this.options || {}).forEach(key => {//Object.keys(obj)返回由obj自身可枚举属性组成的数组。
      let option = this.options[key];
      let $option = document.createElement('li');
      $option.innerHTML = option.label;
      this.$dropdownList.appendChild($option);
    });
  }
}
window.customElements.define('my-dropdown', Dropdown);
```

在这种情况下，由于选项可能已更改，因此在每个渲染上我们都会擦除下拉列表的内部HTML。然后，我们动态地为每个列表元素`option`在我们的`options`对象，并将其添加到我们的列表元素`option`属性的`label`。如果`properties`未定义，我们将使用默认的空对象以避免在此处遇到异常，因为传入的属性和属性之间存在竞争条件。但是，即使列表已呈现，我们的样式也将CSS `display`属性定义为`none`。这就是为什么我们还看不到列表的原因，但是在为自定义元素的行为添加了更多JavaScript之后，我们将在下一步中看到它。

## 使用JAVASCRIPT的WEB组件的行为

到目前为止，我们主要对自定义元素进行了结构化和样式化。我们还对更改的属性做出了反应，但是在渲染步骤中并没有做很多事情。现在，我们将使用更多JavaScript将行为添加到Web组件。只有这样，它才与用CSS设置样式的简单HTML元素真正不同。您将看到如何将所有行为封装在自定义下拉列表元素中，而无需外部进行任何操作。

让我们首先使用按钮元素打开和关闭下拉列表，这将使我们的下拉列表可见。首先，定义一个新样式以使用`open`类渲染下拉列表。请记住，我们之前将`display: none;`下拉列表用作默认样式。

```jsx
const template = document.createElement('template');
template.innerHTML = `
  <style>
    :host {
      font-family: sans-serif;
    }
    ...
    .dropdown.open .dropdown-list {
      display: flex;
      flex-direction: column;
    }
    ...
  </style>
  ...
`;
```

在下一步中，我们定义一个类方法，用于切换自定义元素的内部状态。同样，当调用此类方法时，会根据新`open`状态将新类添加或删除到我们的dropdown元素中。

```jsx
class Dropdown extends HTMLElement {
  constructor() {
    super();
    this._sR = this.attachShadow({ mode: 'open' });
    this._sR.appendChild(template.content.cloneNode(true));
    this.open = false;
    this.$label = this._sR.querySelector('.label');
    this.$button = this._sR.querySelector('my-button');
    this.$dropdown = this._sR.querySelector('.dropdown');
    this.$dropdownList = this._sR.querySelector('.dropdown-list');
  }
  toggleOpen(event) {
    this.open = !this.open;
    this.open
      ? this.$dropdown.classList.add('open')
      : this.$dropdown.classList.remove('open');
  }
  ...
}
```

最后但并非最不重要的一点是，我们需要为自定义按钮元素的事件添加事件侦听器，以将下拉菜单的内部状态从打开切换为关闭，反之亦然。`this`使用它时，请不要忘记绑定到我们的新类方法，因为否则它将无权`this`设置新的内部状态或访问分配的`$dropdown`元素。

```jsx
class Dropdown extends HTMLElement {
  constructor() {
    super();
    this._sR = this.attachShadow({ mode: 'open' });
    this._sR.appendChild(template.content.cloneNode(true));
    this.open = false;
    this.$label = this._sR.querySelector('.label');
    this.$button = this._sR.querySelector('my-button');
    this.$dropdown = this._sR.querySelector('.dropdown');
    this.$dropdownList = this._sR.querySelector('.dropdown-list');
    this.$button.addEventListener(
      'onClick',
      this.toggleOpen.bind(this)
    );
  }
  toggleOpen(event) {
    this.open = !this.open;
    this.open
      ? this.$dropdown.classList.add('open')
      : this.$dropdown.classList.remove('open');
  }
  ...
}
```

立即尝试使用Web组件。通过单击我们的自定义按钮，应该可以打开和关闭自定义下拉元素。这是自定义元素的第一个真正的内部行为，否则将在类似React或Angular的框架中实现。现在，您的框架可以简单地使用此Web组件并从中期望这种行为。让我们继续单击鼠标，从打开的列表中选择一项：

```jsx
class Dropdown extends HTMLElement {
  ...
  render() {
    ...
    Object.keys(this.options || {}).forEach(key => {
      let option = this.options[key];
      let $option = document.createElement('li');
      $option.innerHTML = option.label;
      $option.addEventListener('click', () => {
        this.option = key;
        this.toggleOpen();
        this.render();
      });
      this.$dropdownList.appendChild($option);
    });
  }
}
```

列表中的每个呈现选项都为click事件获取一个事件侦听器。单击该选项时，该选项设置为属性，下拉菜单切换为`close`，并且该组件再次呈现。但是，为了查看发生了什么，让我们在下拉列表中可视化所选的选项项：

```jsx
const template = document.createElement('template');
template.innerHTML = `
  <style>
    ...
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
```

接下来，只要option属性与列表中的选项匹配，就可以在render方法中设置这个新类。有了这种新样式，并在下拉列表中的一个选项上动态设置了样式，我们可以看到该功能确实有效：

```jsx
class Dropdown extends HTMLElement {
  ...
  render() {
    ...
    Object.keys(this.options || {}).forEach(key => {
      let option = this.options[key];
      let $option = document.createElement('li');
      $option.innerHTML = option.label;
      if (this.option && this.option === key) {
        $option.classList.add('selected');
      }
      $option.addEventListener('click', () => {
        this.option = key;
        this.toggleOpen();
        this.render();
      });
      this.$dropdownList.appendChild($option);
    });
  }
}
```

让我们在自定义按钮元素中显示当前选定的选项，而不是设置任意值：

```jsx
class Dropdown extends HTMLElement {
  ...
  render() {
    this.$label.innerHTML = this.label;
    if (this.options) {
      this.$button.setAttribute(
        'label',
        this.options[this.option].label
      );
    }
    this.$dropdownList.innerHTML = '';
    Object.keys(this.options || {}).forEach(key => {
      ...
    });
  }
}
```

我们对自定义下拉菜单元素的内部行为有效。我们能够打开和关闭它，并且我们能够通过从下拉列表中选择一个来设置一个新选项。缺少一件重要的事情：我们需要再次向外界提供API（例如自定义事件），以通知他们有关更改的选项。因此，为每个列表项单击调度一个自定义事件，但为每个自定义事件分配一个键以标识单击了哪个项目：

```jsx
class Dropdown extends HTMLElement {
  ...
  render() {
    ...
    Object.keys(this.options || {}).forEach(key => {
      let option = this.options[key];
      let $option = document.createElement('li');
      $option.innerHTML = option.label;
      if (this.option && this.option === key) {
        $option.classList.add('selected');
      }
      $option.addEventListener('click', () => {
        this.option = key;
        this.toggleOpen();
        this.dispatchEvent(
          new CustomEvent('onChange', { detail: key })
        );
        this.render();
      });
      this.$dropdownList.appendChild($option);
    });
  }
}
```

最后，当使用下拉列表作为Web组件时，可以为自定义事件添加事件侦听器，以获取有关更改的通知：

```jsx
<my-dropdown label="Dropdown" option="option2"></my-dropdown>
<script>
  document.querySelector('my-dropdown').options = {
    option1: { label: 'Option 1' },
    option2: { label: 'Option 2' },
  };
  document
    .querySelector('my-dropdown')
    .addEventListener('onChange', event => console.log(event.detail));
</script>
```

而已。您已经创建了一个完全封装的下拉组件作为Web组件，并具有自己的结构，样式和行为。后者是Web组件的关键部分，因为否则您可以简单地使用带有CSS样式的HTML元素。现在，您的行为也封装在新的自定义HTML元素中。恭喜你！

------

可以在[此GitHub](https://github.com/rwieruch/web-components-dropdown)项目中找到作为Web组件的dropdown和button元素的实现，并带有一些有用的扩展。就像我之前说的那样，自定义按钮元素对于下拉组件来说并不是必需的，因为它没有实现任何特殊行为。您可以在CSS样式中使用普通的HTML按钮元素。但是，自定义按钮元素通过一个简单的示例帮助我们掌握了Web组件的概念。这就是为什么我认为从在以后的下拉菜单组件中使用的按钮组件开始是一个好主意。如果您想继续在React中使用Web组件，请查看此简洁的[React钩子](https://github.com/the-road-to-learn-react/use-custom-element)或本[Web组件以进行React教程](https://www.robinwieruch.de/react-web-components)。最后，希望您从本Web组件教程中学到了很多东西。如果您有反馈或只是喜欢它，请发表评论:-)