import Vue from 'vue'
import Router from 'vue-router'

import HelloWorld from '@/components/HelloWorld';
import Home from '@/components/Home';
import Login from '@/components/Login';

Vue.use(Router);

const requireAuth = (to, _from, next) => {
  next({path: 'login'});
};

export default new Router({
  routes: [
    { path: '/', name: 'Home', component: Home },
    { path: '/hello', name: 'HelloWorld', component: HelloWorld, beforeEnter: requireAuth },
    { path: '/login', name: 'Login', component: Login },
  ]
})
