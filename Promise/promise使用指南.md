# promise



~~~js
//基本用法1
let pro = new Promise( (resolve, reject) => {
    //do something ...
    resolve(arg1);// || reject(arg2)
}).then((n) => {
    console.log(n);//arg1
}).catch((m) => {
    console.log(m);//arg2
})

//基本用法2
let pro2 = new Promise( (resolve, reject) => {
    //do something...
    resolve(arg1);//  || reject(arg2)
}).then((n) => {
    console.log(n);//arg1
}, (m) => {
    console.log(m);//arg2
})

//串行执行异步任务：
//若有如下场景：需要请求到a1数据，然后利用a1数据请求a2数据，然后用a2数据请求a3数据，该如何做？
//请求a1
let a1Pro = new Promise((resolve, reject) => {
    //ajax  a1 ...
    resolve(a1Data);
});
//请求a2
let a2Pro =  a1Pro.then((a1Data) => {
    return new Promise((resolve, reject) => {
        //a1Data  ajax  a2 ...
        resolve(a2Data);
    })
}).catch(() => {
    console.log('a1数据请求出错！');
})
//请求a3
let a3Pro = a2Pro.then((a2Data) => {
    return new Promise((resolve, reject) => {
        //a2Data ajax a3 ...
        resolve(a3Data);
    })
}).catch(() => {
    console.log('a2数据请求出错！');
});

let finallyData = a3Pro.then((a3Data) => {
	return a3Data;
})

//Promise.all()  并行异步执行任务
//Promise.all([p1,p2,p3,...])方法会并行执行每个promise，然后将所有的结果放到一个数组中传入then，如果其中有一个promise执行了reject（也就是失败了）那么它就会报错
//比如一个页面聊天系统，我们需要在两个不同的地址分别获取两者的用户信息和个人列表，这两个任务就是可以并行的
let pro3 = new Promise((resolve, reject) => {
    //do something...
})
let pro4 = new Promise((resolve, reject) => {
    //do something...
})
let pro5 = Promise.all([pro3, pro4]).then( (result) => {
    console.log(result);//[resPro3, resPro4]
})

//Promise.race()
//Promise.race([p1,p2,p3,...])方法会并行每个promise，但只会将运行resolve最早的一个promise的结果放入then，其他的promise就不会管了，这好比一个赛跑比赛
//比如一个用户请求后台数据，需要判定5000毫秒还没请求到就是超时，那么就可以一个promise包含ajax请求，另一个promise包含一个定时器，就可以实现此操作
let pro6 = new Promise((resolve, reject) => {
    //ajax  data
    resolve(data);
});
let pro7 = new Promise((resolve,reject) => {
    setInterval(resolve(false), 5000);
})
let pro8 = Promise.race([pro6, pro7]).then((res) => {
    if(res) {
        // do something ...
    } else {
        console.log('超时啦！');
    }
})
~~~

