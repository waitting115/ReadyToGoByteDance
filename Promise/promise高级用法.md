### promise是什么？

1、主要用于异步计算
 2、可以将异步操作队列化，按照期望的顺序执行，返回符合预期的结果
 3、可以在对象之间传递和操作promise，帮助我们处理队列

### 为什么会有promise？

**为了避免界面冻结（任务）**

- 同步：假设你去了一家饭店，找个位置，叫来服务员，这个时候服务员对你说，对不起我是“同步”服务员，我要服务完这张桌子才能招呼你。那桌客人明明已经吃上了，你只是想要个菜单，这么小的动作，服务员却要你等到别人的一个大动作完成之后，才能再来招呼你，这个便是同步的问题：也就是“顺序交付的工作1234，必须按照1234的顺序完成”。
- 异步：则是将耗时很长的A交付的工作交给系统之后，就去继续做B交付的工作，。等到系统完成了前面的工作之后，再通过回调或者事件，继续做A剩下的工作。
   AB工作的完成顺序，和交付他们的时间顺序无关，所以叫“异步”。

### 异步操作的常见语法

1. 事件监听



```jsx
document.getElementById('#start').addEventListener('click', start, false);
function start() {
  // 响应事件，进行相应的操作
}
// jquery on 监听
$('#start').on('click', start)
```

1. 回调



```jsx
// 比较常见的有ajax
$.ajax('http://www.wyunfei.com/', {
 success (res) {
   // 这里可以监听res返回的数据做回调逻辑的处理
 }
})

// 或者在页面加载完毕后回调
$(function() {
 // 页面结构加载完成，做回调逻辑处理
})
```

### 有了nodeJS之后...对异步的依赖进一步加剧了

大家都知道在nodeJS出来之前PHP、Java、python等后台语言已经很成熟了，nodejs要想能够有自己的一片天，那就得拿出点自己的绝活：
 **无阻塞高并发，是nodeJS的招牌，要达到无阻塞高并发异步是其基本保障**
 举例：查询数据从数据库，PHP第一个任务查询数据，后面有了新任务，那么后面任务会被挂起排队；而nodeJS是第一个任务挂起交给数据库去跑，然后去接待第二个任务交给对应的系统组件去处理挂起，接着去接待第三个任务...**那这样子的处理必然要依赖于异步操作**

### 异步回调的问题：

- 之前处理异步是通过纯粹的回调函数的形式进行处理
- 很容易进入到回调地狱中，剥夺了函数return的能力
- 问题可以解决，但是难以读懂，维护困难
- 稍有不慎就会踏入回调地狱 - 嵌套层次深，不好维护

![img](https:////upload-images.jianshu.io/upload_images/15311104-f36baae9a21490c7.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

回调地狱



一般情况我们一次性调用API就可以完成请求。
 有些情况需要多次调用服务器API，就会形成一个链式调用，比如为了完成一个功能，我们需要调用API1、API2、API3，依次按照顺序进行调用，这个时候就会出现回调地狱的问题

### promise

- promise是一个对象，对象和函数的区别就是对象可以保存状态，函数不可以（闭包除外）
- 并未剥夺函数return的能力，因此无需层层传递callback，进行回调获取数据
- 代码风格，容易理解，便于维护
- 多个异步等待合并便于解决

### promise详解



```jsx
new Promise(
  function (resolve, reject) {
    // 一段耗时的异步操作
    resolve('成功') // 数据处理完成
    // reject('失败') // 数据处理出错
  }
).then(
  (res) => {console.log(res)},  // 成功
  (err) => {console.log(err)} // 失败
)
```

- resolve作用是，将Promise对象的状态从“未完成”变为“成功”（即从 pending 变为 resolved），在异步操作成功时调用，并将异步操作的结果，作为参数传递出去；
   reject作用是，将Promise对象的状态从“未完成”变为“失败”（即从 pending 变为 rejected），在异步操作失败时调用，并将异步操作报出的错误，作为参数传递出去。
- promise有三个状态：
   1、pending[待定]初始状态
   2、fulfilled[实现]操作成功
   3、rejected[被否决]操作失败
   当promise状态发生改变，就会触发then()里的响应函数处理后续步骤；
   promise状态一经改变，不会再变。
- Promise对象的状态改变，只有两种可能：
   从pending变为fulfilled
   从pending变为rejected。
   这两种情况只要发生，状态就凝固了，不会再变了。

##### 最简单示例：



```jsx
new Promise(resolve => {
  setTimeout(() => {
    resolve('hello')
  }, 2000)
}).then(res => {
  console.log(res)
})
```

##### 分两次，顺序执行



```jsx
new Promise(resolve => {
    setTimeout(() => {
      resolve('hello')
    }, 2000)
  }).then(val => {
    console.log(val) //  参数val = 'hello'
    return new Promise(resolve => {
      setTimeout(() => {
        resolve('world')
      }, 2000)
    })
  }).then(val => {
    console.log(val) // 参数val = 'world'
  })
```

##### promise完成后then()



```jsx
let pro = new Promise(resolve => {
   setTimeout(() => {
     resolve('hello world')
   }, 2000)
 })
 setTimeout(() => {
   pro.then(value => {
   console.log(value) // hello world
 })
 }, 2000)
```

结论：promise作为队列最为重要的特性，我们在任何一个地方生成了一个promise队列之后，我们可以把他作为一个变量传递到其他地方。

##### 假如在.then()的函数里面不返回新的promise，会怎样？

### .then()

1、接收两个函数作为参数，分别代表fulfilled（成功）和rejected（失败）
 2、.then()返回一个新的Promise实例，所以它可以链式调用
 3、当前面的Promise状态改变时，.then()根据其最终状态，选择特定的状态响应函数执行
 4、状态响应函数可以返回新的promise，或其他值，不返回值也可以我们可以认为它返回了一个null；
 5、如果返回新的promise，那么下一级.then()会在新的promise状态改变之后执行
 6、如果返回其他任何值，则会立即执行下一级.then()

##### .then()里面有.then()的情况

1、因为.then()返回的还是Promise实例
 2、会等里面的then()执行完，再执行外面的

![img](https:////upload-images.jianshu.io/upload_images/15311104-53b61cb990d856b5.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

then嵌套

- 对于我们来说，此时最好将其展开，也是一样的结果，而且会更好读：

  ![img](https:////upload-images.jianshu.io/upload_images/15311104-7aa75490f79e0725.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

  展开增加可读性

##### 错误处理

Promise会自动捕获内部异常，并交给rejected响应函数处理。

1. 第一种错误处理

   ![img](https:////upload-images.jianshu.io/upload_images/15311104-d93b7cf287478e43.png?imageMogr2/auto-orient/strip|imageView2/2/w/860/format/webp)

   第一种错误处理

2. 第二种错误处理

   ![img](https:////upload-images.jianshu.io/upload_images/15311104-5e3ab96cbdcde863.png?imageMogr2/auto-orient/strip|imageView2/2/w/861/format/webp)

   第二种错误处理

- 错误处理两种做法：
   第一种：reject('错误信息').then(() => {}, () => {错误处理逻辑})
   第二种：throw new Error('错误信息').catch( () => {错误处理逻辑})
   推荐使用第二种方式，更加清晰好读，并且可以捕获前面所有的错误（可以捕获N个then回调错误）

### catch() + then()

- 第一种情况：

  ![img](https:////upload-images.jianshu.io/upload_images/15311104-f63f673f5fbb74d1.png?imageMogr2/auto-orient/strip|imageView2/2/w/917/format/webp)

  第一种情况

  ![img](https:////upload-images.jianshu.io/upload_images/15311104-947df4320e99263b.png?imageMogr2/auto-orient/strip|imageView2/2/w/417/format/webp)

  第一种情况 - 结果

  结论：catch也会返回一个promise实例，并且是resolved状态

- 第二种情况：

  ![img](https:////upload-images.jianshu.io/upload_images/15311104-1aa27776f33dc101.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

  第二种情况

![img](https:////upload-images.jianshu.io/upload_images/15311104-a1d408c57330e14b.png?imageMogr2/auto-orient/strip|imageView2/2/w/460/format/webp)

第二种情况结果



结论：抛出错误变为rejected状态，所以绕过两个then直接跑到最下面的catch

##### Promise.all() 批量执行

Promise.all([p1, p2, p3])用于将多个promise实例，包装成一个新的Promise实例，返回的实例就是普通的promise
 它接收一个数组作为参数
 数组里可以是Promise对象，也可以是别的值，只有Promise会等待状态改变
 当所有的子Promise都完成，该Promise完成，返回值是全部值得数组
 有任何一个失败，该Promise失败，返回值是第一个失败的子Promise结果



```jsx
//切菜
    function cutUp(){
        console.log('开始切菜。');
        var p = new Promise(function(resolve, reject){        //做一些异步操作
            setTimeout(function(){
                console.log('切菜完毕！');
                resolve('切好的菜');
            }, 1000);
        });
        return p;
    }

    //烧水
    function boil(){
        console.log('开始烧水。');
        var p = new Promise(function(resolve, reject){        //做一些异步操作
            setTimeout(function(){
                console.log('烧水完毕！');
                resolve('烧好的水');
            }, 1000);
        });
        return p;
    }

    Promise.all([cutUp(), boil()])
        .then((result) => {
            console.log('准备工作完毕');
            console.log(result);
        })
```

##### Promise.race() 类似于Promise.all() ，区别在于它有任意一个完成就算完成



```jsx
let p1 = new Promise(resolve => {
        setTimeout(() => {
            resolve('I\`m p1 ')
        }, 1000)
    });
    let p2 = new Promise(resolve => {
        setTimeout(() => {
            resolve('I\`m p2 ')
        }, 2000)
    });
    Promise.race([p1, p2])
        .then(value => {
            console.log(value)
        })
```

- 常见用法：
   异步操作和定时器放在一起，，如果定时器先触发，就认为超时，告知用户；
   例如我们要从远程的服务家在资源如果5000ms还没有加载过来我们就告知用户加载失败
- 现实中的用法
   回调包装成Promise，他有两个显而易见的好处：
   1、可读性好
   2、返回 的结果可以加入任何Promise队列

> 实战示例，回调地狱和promise对比：



```jsx
/***
   第一步：找到北京的id
   第二步：根据北京的id -> 找到北京公司的id
   第三步：根据北京公司的id -> 找到北京公司的详情
   目的：模拟链式调用、回调地狱
 ***/
 
 // 回调地狱
 // 请求第一个API: 地址在北京的公司的id
 $.ajax({
   url: 'https://www.easy-mock.com/mock/5a52256ad408383e0e3868d7/lagou/city',
   success (resCity) {
     let findCityId = resCity.filter(item => {
       if (item.id == 'c1') {
         return item
       }
     })[0].id
     
     $.ajax({
       //  请求第二个API: 根据上一个返回的在北京公司的id “findCityId”，找到北京公司的第一家公司的id
       url: 'https://www.easy-mock.com/mock/5a52256ad408383e0e3868d7/lagou/position-list',
       success (resPosition) {
         let findPostionId = resPosition.filter(item => {
           if(item.cityId == findCityId) {
             return item
           }
         })[0].id
         // 请求第三个API: 根据上一个API的id(findPostionId)找到具体公司，然后返回公司详情
         $.ajax({
           url: 'https://www.easy-mock.com/mock/5a52256ad408383e0e3868d7/lagou/company',
           success (resCom) {
             let comInfo = resCom.filter(item => {
               if (findPostionId == item.id) {
                 return item
               }
             })[0]
             console.log(comInfo)
           }
         })
       }
     })
   }
 })
```



```jsx
// Promise 写法
  // 第一步：获取城市列表
  const cityList = new Promise((resolve, reject) => {
    $.ajax({
      url: 'https://www.easy-mock.com/mock/5a52256ad408383e0e3868d7/lagou/city',
      success (res) {
        resolve(res)
      }
    })
  })

  // 第二步：找到城市是北京的id
    cityList.then(res => {
      let findCityId = res.filter(item => {
        if (item.id == 'c1') {
          return item
        }
      })[0].id
      
      findCompanyId().then(res => {
        // 第三步（2）：根据北京的id -> 找到北京公司的id
        let findPostionId = res.filter(item => {
            if(item.cityId == findCityId) {
              return item
            }
        })[0].id

        // 第四步（2）：传入公司的id
        companyInfo(findPostionId)

      })

    })

  // 第三步（1）：根据北京的id -> 找到北京公司的id
  function findCompanyId () {
    let aaa = new Promise((resolve, reject) => {
      $.ajax({
        url: 'https://www.easy-mock.com/mock/5a52256ad408383e0e3868d7/lagou/position-list',
        success (res) {
          resolve(res)
        }
      })
    })
    return aaa
  }

// 第四步：根据上一个API的id(findPostionId)找到具体公司，然后返回公司详情
function companyInfo (id) {
  let companyList = new Promise((resolve, reject) => {
    $.ajax({
      url: 'https://www.easy-mock.com/mock/5a52256ad408383e0e3868d7/lagou/company',
      success (res) {
        let comInfo = res.filter(item => {
            if (id == item.id) {
               return item
            }
        })[0]
        console.log(comInfo)
      }
    })
  })
}
```