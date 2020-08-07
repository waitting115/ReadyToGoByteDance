const express = require('express');
const server = express();

server.listen(2233);

server.use('/get', (req, res) => {
    // res.setHeader("Access-Control-Allow-Origin", "*");
    res.send(['锡纸金针菇','烤面筋','热干面'])
})

// server.use(express.static('./'))