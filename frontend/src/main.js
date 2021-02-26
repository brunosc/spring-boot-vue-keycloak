import Vue from 'vue'
import App from './App.vue'
import axios from 'axios';
import router from './router'
import store from './store'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';

Vue.config.productionTip = false

axios.interceptors.request.use(config => {
  if (localStorage.getItem('token')) {
    config.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`
  }
  return config;
});


new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
