import Vue from 'vue'
import App from './App.vue'

import Loading from './components/loading/index.js'
Vue.use(Loading);

import Router1 from './components/router/index.js'
Vue.use(Router1);

import VueRouter from 'vue-router'
Vue.use(VueRouter);

import Home from './components/router/home.vue'

const router = new VueRouter({
  routes: [
    {
      path: '/home',
      component: Home
    },
    {
      path: '/news',
      component: {
        template: '<h1>我是news</h1>'
      }
    },
    {
      path: '/',
      redirect: '/news'
    }
  ]
})

new Vue({
  router,
  el: '#app',
  render: h => h(App)
})
