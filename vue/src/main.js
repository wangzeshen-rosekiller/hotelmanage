import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'

Vue.config.productionTip = false
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8889'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.use(ElementUI)


axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
