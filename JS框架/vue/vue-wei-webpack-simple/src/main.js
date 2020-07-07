import Vue from 'vue'
import App from './App.vue'

import Loading from './components/loading/index.js'
Vue.use(Loading);

new Vue({
  el: '#app',
  render: h => h(App)
})
