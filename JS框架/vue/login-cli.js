const express = require('express');
const server = express();
const bp = require('body-parser');

server.use(bp.urlencoded({}));

let userSQL = {};

server.use('/login', (req, res) => {
    // let {user, password} = req.query;
    console.log(userSQL)
    console.log(req.query.user,req.query.password)
    if(userSQL[req.query.user] == undefined) {
        res.send({ok: 0, data: '没有此用户，请重新输入！'})
    } else if(userSQL[req.query.user] != req.query.password) {
        res.send({ok: 0, data: '用户名或密码错误，请重新输入！'})
    } else {
        res.send({ok: 1, data: '恭喜您登陆成功！'})
    }
    // console.log(req.query);
})

server.use('/register', (req, res) => {
    // console.log(req.body)
    // console.log(req.body.user, req.body.password, req.body.pswagain);
    if(userSQL[req.body.user] == undefined && req.body.password == req.body.pswagain) {
        userSQL[req.body.user] = req.body.password;
        res.send({ok: 1, data: '恭喜您注册成功！'})
    } else {
        res.send({ok: 0, data: '已有此用户或两次密码输入不一致！'})
    }
    console.log('userSQL:', userSQL);
    // console.log(req.body);
})

server.listen(5678);