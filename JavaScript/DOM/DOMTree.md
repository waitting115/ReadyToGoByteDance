# DOMTree

如下一段HTML代码：

~~~html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Simple DOM example</title>
  </head>
  <body>
      <section>
        <img src="dinosaur.png" alt="A red Tyrannosaurus Rex: A two legged dinosaur standing upright like a human, with small arms, and a large head with lots of sharp teeth.">
        <p>Here we will add a link to the <a href="https://www.mozilla.org/">Mozilla homepage</a></p>
      </section>
  </body>
</html>
~~~

它的DOM结构为：

![img](https://mdn.mozillademos.org/files/14559/dom-screenshot.png)

要注意text节点，画DOM树时要一层一层的画，以免落下某text节点