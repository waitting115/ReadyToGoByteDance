const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const port = 2400;

let json = {
    'wei': '123'
}

app.use(bodyParser.urlencoded());//post need

app.use('/postMsg', (req, res) => {
    // res.send(req.body);
    if(json[req.body.user] === req.body.password) {
        res.send({ok:1, 'msg': 'You are success login it!'})
    } else {
        res.send({ok: 1, 'msg': 'User or password is error!'})
    }
})

app.use('/getMsg',(req, res) => {
    if(json[req.query.user] === req.query.password) {
        res.send({ok: 1, 'msg': 'You are success login it!'})
    } else {
        res.send({ok: 0, 'msg': 'User or password is error!'})
    }
    res.send(req.query)
})

app.listen(port, () => console.log(`Example app listening on port ${port}`));

app.use(express.static('./'));//静态，就不用跨域了
