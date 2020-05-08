## CSRF

CSRF（Cross-site request forgery跨站请求伪造，也被称为“One Click Attack”或者Session Riding，通常缩写为CSRF或者XSRF，是一种对网站的恶意利用。
其实就是**网站中的一些提交行为，被黑客利用**，**你在访问黑客的网站的时候，进行的操作，会被操作到其他网站上(如：你所使用的网络银行的网站)。**

原理：1、你在一个网站上登录过有cookie信息。2、你再访问别的网站进行操作的时候 ，这个网站向你之前的网站发送请求。

例如：你的代码

```php
<?php
$username = $_COOKIE['username'];
// 换为post了，可以规避黑客直接的提交
$productId = $_POST['pid'];
// 这里进行购买操作
//store_into_database($username, $productId);
?>
<meta charset="utf-8" />
<?php
echo $username . '买入商品：' . $productId;
?>
```

黑客代码如下：

```
<!DOCYTPE HTML>
<html>
    <head>
        <meta charset="utf-8" />
        <script src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/lib/jquery-1.10.2_d88366fd.js"></script>
    </head>
    <body>
        <button id="clickme">点我看相册</button>
        <script>
            $('#clickme').on('click', function () {
                // 用户再不知情的情况下，提交了表单，服务器这边也会以为是用户提交过来的。
                $('#myform').submit();
            });
        </script>
        <form id="myform" style="display:none;" target="myformer" method="post" action="http://myhost:8082/lab/xsrflab/submit.php">
            <input type="hidden" name="pid" value="1">
        </form>
        <iframe name="myformer" style="display:none;"></iframe>
    </body>
</html>
```

预防这种的办法就是：

1.**增加token验证**.因为cookie发送请求的时候会自动增加上，但是token却不会，这样就避免了攻击

2.**Referer验证**。页面来源的判断