- 在src中新建文件夹components

- 在components中新建文件夹loading

- 在loading中新建index.js  和  loading.vue （若干个）

- 在index.js中引入新建的vue模块（loading.vue）并安装，以及返回：

  - ~~~js
    import LoadingComponent from './loading.vue'
    import LoadingComponent2 from './loading2.vue'
    
    const Loading = {
        install : function (vue) {
            vue.component('Loading', LoadingComponent);
            vue.component('Loading2', LoadingComponent2);
        }
    }
    
    export default Loading;
    ~~~

- 在外面的main.js中告诉一声加上了index.js以及里面的东西：

  - ~~~js
    import Vue from 'vue'
    import App from './App.vue'
    
    import Loading from './components/loading/index.js'
    Vue.use(Loading);
    
    new Vue({
      el: '#app',
      render: h => h(App)
    })
    
    ~~~

- 然后书写loading.vue：

  - ~~~vue
    <template>
        <div class="loading">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
    </template>
    
    <script>
    export default {
        // data() {
        //     return {
        //         msg : 'I am Loading'
        //     }
        // }
    }
    </script>
    
    <style lang="stylus" scoped>
        .loading{
                width: 80px;
                height: 40px;
                margin: 0 auto;
                margin-top:100px;
            }
            .loading span{
                display: inline-block;
                width: 8px;
                height: 100%;
                border-radius: 4px;
                background: lightgreen;
                -webkit-animation: load 1s ease infinite;
            }
            @-webkit-keyframes load{
                0%,100%{
                    height: 40px;
                    background: lightgreen;
                }
                50%{
                    height: 70px;
                    margin: -15px 0;
                    background: lightblue;
                }
            }
            .loading span:nth-child(2){
                -webkit-animation-delay:0.2s;
            }
            .loading span:nth-child(3){
                -webkit-animation-delay:0.4s;
            }
            .loading span:nth-child(4){
                -webkit-animation-delay:0.6s;
            }
            .loading span:nth-child(5){
                -webkit-animation-delay:0.8s;
            }
    </style>
    ~~~

- 最后在外面的App.vue中引用该组件：

  - ~~~vue
    <template>
      <div id="app">
        <Loading></Loading>
        <Loading2></Loading2>
        <!-- <img src="./assets/logo.png">
        <h1>{{ msg }}</h1>
        <h2>Essential Links</h2>
        <ul>
          <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
          <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
          <li><a href="https://chat.vuejs.org" target="_blank">Community Chat</a></li>
          <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
        </ul>
        <h2>Ecosystem</h2>
        <ul>
          <li><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
          <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
          <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
          <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
        </ul> -->
      </div>
    </template>
    
    <script>
    export default {
      name: 'app',
      data () {
        return {
          msg: 'Welcome to wei Vue.js App'
        }
      }
    }
    </script>
    
    <style>
    #app {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
    }
    
    h1, h2 {
      font-weight: normal;
    }
    
    ul {
      list-style-type: none;
      padding: 0;
    }
    
    li {
      display: inline-block;
      margin: 0 10px;
    }
    
    a {
      color: #42b983;
    }
    </style>
    
    ~~~

- 