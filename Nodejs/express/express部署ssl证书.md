我用的宝塔面板，nginx服务器

首先在阿里云或其他云上申请ssl，审核通过后下载nginx的ssl证书，应该是两个文件，分别是.key   .pem。

先为域名添加ssl，这个在宝塔就可以做到，很容易

然后为express部署ssl，打开新建的express实例，新建一个文件夹用来存放ssl证书的两个文件，比如cert文件夹，然后把两个文件放里面，然后编辑bin下面的www文件：

~~~
#!/usr/bin/env node
 

/**
 * Module dependencies.
 */
 

var app = require('../app');
var debug = require('debug')('xchengxu:server');//需要改
var http = require('http');
var fs = require('fs')
var https = require('https')
var path = require('path')
var express = require('express')
 

const options = {
  key: fs.readFileSync(path.join(__dirname, '../cert/a.key')),//需要改
  cert: fs.readFileSync(path.join(__dirname, '../cert/a.pem'))//需要改
}
app.set('porthttps',11443)  //随意
 

/**
 * Get port from environment and store in Express.
 */
 

var port = normalizePort(process.env.PORT || '3000');
app.set('port', port);
 

/**
 * Create HTTP server.
 */
 

var server = http.createServer(app);
 

/**
 * Listen on provided port, on all network interfaces.
 */
 

server.listen(port);
server.on('error', onError);
server.on('listening', onListening);
 

https.createServer(options,app).listen(app.get('porthttps'),function(){
  console.log('11443端口运行');
})
 

/**
 * Normalize a port into a number, string, or false.
 */
 

function normalizePort(val) {
  var port = parseInt(val, 10);
 

  if (isNaN(port)) {
    // named pipe
    return val;
  }
 

  if (port >= 0) {
    // port number
    return port;
  }
 

  return false;
}
 

/**
 * Event listener for HTTP server "error" event.
 */
 

function onError(error) {
  if (error.syscall !== 'listen') {
    throw error;
  }
 

  var bind = typeof port === 'string'
    ? 'Pipe ' + port
    : 'Port ' + port;
 

  // handle specific listen errors with friendly messages
  switch (error.code) {
    case 'EACCES':
      console.error(bind + ' requires elevated privileges');
      process.exit(1);
      break;
    case 'EADDRINUSE':
      console.error(bind + ' is already in use');
      process.exit(1);
      break;
    default:
      throw error;
  }
}
 

/**
 * Event listener for HTTP server "listening" event.
 */
 

function onListening() {
  var addr = server.address();
  var bind = typeof addr === 'string'
    ? 'pipe ' + addr
    : 'port ' + addr.port;
  debug('Listening on ' + bind);
}
~~~

还有，要在阿里云控制台实例安全组开放11443端口号（如果你设置的其他的就开放对应的端口号），然后在宝塔也开放一下。

然后启动express实例：npm start。