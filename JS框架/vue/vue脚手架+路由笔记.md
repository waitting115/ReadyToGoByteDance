- 在src的components中新建router文件夹

- 在router文件夹中新建index.js  和 router.vue（若干）

- index.js：

  - ~~~js
    import RouterComponent from './router.vue'
    
    const Router1 = {
        install : function (vue) {
            vue.component('Router1', RouterComponent)
        }
    }
    
    export default Router1;
    ~~~

- router.vue：

  - ~~~vue
    <template>
        <div>
            <p>routercomponent</p>
            <router-link to='/home'>home</router-link>
            <router-link to='/news'>news</router-link>
    
            <router-view></router-view>
        </div>
    </template>
    
    <script>
    export default {
        
    }
    </script>
    
    <style lang="stylus" scoped>
    
    </style>
    ~~~

- 在外面main.js中配置一个路由：

  - ~~~js
    import Vue from 'vue'
    import App from './App.vue'
    
    import Loading from './components/loading/index.js'
    Vue.use(Loading);
    
    import Router1 from './components/router/index.js'
    Vue.use(Router1);
    
    import VueRouter from 'vue-router'
    Vue.use(VueRouter);
    
    const router = new VueRouter({
      routes: [
        {
          path: '/home',
          component: {
            template: '<h1>我是home</h1>'
          }
        },
        {
          path: '/news',
          component: {
            template: '<h1>我是news</h1>'
          }
        }
      ]
    })
    
    new Vue({
      router,
      el: '#app',
      render: h => h(App)
    })
    
    ~~~

- 最后再App.vue中添加路由模板：

  - ~~~vue
    <template>
      <div id="app">
        <Router1></Router1>
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