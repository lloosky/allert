import Vue from 'vue';
import Spinner from 'vue-spinkit';
import VueResource from 'vue-resource';

import App from './App.vue';
import axios from 'axios';
import router from './routes/router';
import store from './store';
import './registerServiceWorker';

Vue.component('Spinner', Spinner);
Vue.use(VueResource);

Vue.config.productionTip = false;

Vue.http.interceptors.push(function(request) {
  // modify headers
  request.headers.set('Authorization', `Bearer ${store.state.userToken}`);
});

const reqInterceptor = axios.interceptors.request.use(config => {
  console.log('Request Interceptor', config);
  return config;
});
const resInterceptor = axios.interceptors.response.use(res => {
  console.log('Response Interceptor', res);
  return res;
});

axios.interceptors.request.eject(reqInterceptor);
axios.interceptors.response.eject(resInterceptor);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
