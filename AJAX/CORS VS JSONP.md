CORS可以用作JSONP模式的现代替代方案。

CORS的好处有：

- JSONP仅支持GET方法，而CORS还支持其他类型的HTTP请求
- CORS可以使用常规的XMLHttpRequest对象，它比JSONP有更好的错误处理机制
- 当外部站点受到攻击时JSONP可能会导致跨站点脚本（XSS）问题。但CORS允许网站手动解析响应 以提高安全性。

JSONP的主要优点在于可以在老版本浏览器（如IE9-，Opera Mini）中使用，但现代浏览器大多数都支持CORS。