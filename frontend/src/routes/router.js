import Vue from 'vue';
import Router from 'vue-router';

import Home from './../views/Home.vue';

import contact from './contact';
import manual from './manual';

Vue.use(Router);

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  ...contact,
  ...manual
];

export default new Router({
  base: process.env.BASE_URL,
  mode: 'history',
  routes
});
