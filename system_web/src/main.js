import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import axios from 'axios';
Vue.prototype.$axios = axios;
Vue.config.productionTip = false;
axios.defaults.baseURL = 'http://localhost:8080';
Vue.use(ElementUI);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
