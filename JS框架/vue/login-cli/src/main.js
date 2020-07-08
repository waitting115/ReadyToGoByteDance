import Vue from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'

import Login from './login/login'
Vue.use(Login);

import Register from './register/register'
Vue.use(Register);

new Vue({
  el: '#app',
  render: h => h(App)
})
