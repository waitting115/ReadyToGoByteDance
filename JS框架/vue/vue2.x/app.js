const express = require('express');
const server = express();

server.use('/book', (req, res) => {
    console.log(req.query);
    res.setHeader("Access-Control-Allow-Origin","*");//允许哪一个域进行访问，若有多个用逗号隔开
    // res.setHeader("Access-Control-Allow-Gredentials","true");//是否开启跨域
    if(req.query.id == '123456') {
        res.send({ok: 1, data: "别人穿越都是无敌王者，秦川却成了一根烂木头。不过即使是一根烂木又怎么样？我能吞噬，我能进化！开局吞噬开始爽，一直吞噬一直爽！"})
    } else if (req.query.id == '654321') {
        res.send({ok: 1, data: '你见过午夜十二点，在窄小的空间里，一位病人给自己画死人妆吗？你见过，在角落里，两人互相啃食双方的手指，流露出享受的神色吗？还有，你看见过将自己幻想成猫，兴高采烈的吃老鼠的人吗？这些，我都见过，甚至还要恐怖的事情，我都经过了........'})
    } else {
        res.send({ok: 0, data: '传入数据出错！'})
    }
});

server.listen(9298);
server.use(express.static('/'));