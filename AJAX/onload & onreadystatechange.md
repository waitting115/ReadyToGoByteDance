## onload

默认this.readystate=== 4，也就是说this.readystate !== 4的情况下是不会触发onload事件的。

可以另外用xhr.onprogress事件来监听this.readystate === 3 的情况。

## onreadystatechange

可以同时监听this.status与this.readystate。