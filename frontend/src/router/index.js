import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

import Home from '@/components/Home';
import Login from '@/components/Login';

Vue.use(Router);

const requireAuth = (to, _from, next) => {
  if (store.getters['auth/isAuthenticated']) {
    next();
  } else {
    next({path: 'login'});
  }
};

export default new Router({
  routes: [
    { path: '/', name: 'Home', component: Home, beforeEnter: requireAuth },
    { path: '/login', name: 'Login', component: Login },
  ]
})
