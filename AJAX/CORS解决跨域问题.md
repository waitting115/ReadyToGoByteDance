## **跨域资源共享**（**CORS**）

CORS是允许限制的机制，[资源](https://en.wikipedia.org/wiki/Web_resource)一个在[网页](https://en.wikipedia.org/wiki/Web_page)可以从另一个请求[域](https://en.wikipedia.org/wiki/Domain_name)从第一资源投放外域。

网页可以自由地嵌入跨域图像，[样式表](https://en.wikipedia.org/wiki/Style_sheet_(web_development))，脚本，[iframe](https://en.wikipedia.org/wiki/HTML_element)和视频。默认情况下，[同源安全策略](https://en.wikipedia.org/wiki/Same-origin_policy)禁止某些“跨域”请求，尤其是[Ajax](https://en.wikipedia.org/wiki/Ajax_(programming))请求。CORS定义了浏览器和服务器进行交互以确定允许跨域请求是否安全的方式。与纯同源请求相比，它提供了更多的自由和功能，但比仅允许所有跨域请求更安全。

CORS规范包含在[WHATWG](https://en.wikipedia.org/wiki/WHATWG)的Fetch Living标准中。本规范描述了当前在浏览器中如何实现CORS。较早的规范已发布为[W3C](https://en.wikipedia.org/wiki/World_Wide_Web_Consortium)建议。

## CORS的工作方式



![img](https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Flowchart_showing_Simple_and_Preflight_XHR.svg/770px-Flowchart_showing_Simple_and_Preflight_XHR.svg.png)

CORS标准描述了新的HTTP标头，这些标头为浏览器提供了一种仅在具有权限的情况下请求远程URL的方法。尽管服务器可以执行某些验证和授权，但是浏览器通常有责任支持这些标头并遵守其施加的限制。

对于可以修改数据的Ajax和[HTTP请求方法](https://en.wikipedia.org/wiki/Request_method)（通常是GET以外的HTTP方法，或者用于某些[MIME](https://en.wikipedia.org/wiki/MIME)类型的[POST](https://en.wikipedia.org/wiki/POST_(HTTP))使用），该规范要求浏览器“预检”请求，并通过HTTP OPTIONS请求从服务器请求支持的方法方法，然后在服务器“批准”后，使用实际的HTTP请求方法发送实际的请求。服务器还可以通知客户端是否应与请求一起发送“凭据”（包括Cookie和HTTP身份验证数据）。

## CORS的优缺点：

优点：可以发送多种类型的HTTP请求

缺点：发送真实请求之前要先做预检，发两次请求会有性能上的损失。

## CORS的流程

，浏览器将请求分为两类，一类是简单请求，一类是非简单请求，满足下列条件的就是简单请求，否则即使非简单请求：

```
条件：
    1、请求方式：HEAD、GET、POST
    2、请求头信息：
        Accept
        Accept-Language
        Content-Language
        Last-Event-ID
        Content-Type 对应的值是以下三个中的任意一个
                                application/x-www-form-urlencoded
                                multipart/form-data
                                text/plain
 
注意：同时满足以上两个条件时，则是简单请求，否则为复杂请求
```



　　2，简单请求和复杂请求的区别：

　　　　简单请求：一次请求

　　　　非简单请求：两次请求，在发送数据之前会先发第一次请求做预检，只有预检通过后在发一次请求作为数据传输。

　　3，关于预检：

```
- 请求方式：OPTIONS
- “预检”其实做检查，检查如果通过则允许传输数据，检查不通过则不再发送真正想要发送的消息
- 如何“预检”
     => 如果复杂请求是PUT等请求，则服务端需要设置允许某请求，否则“预检”不通过
        Access-Control-Request-Method
     => 如果复杂请求设置了请求头，则服务端需要设置允许某请求头，否则“预检”不通过
        Access-Control-Request-Headers
```

*