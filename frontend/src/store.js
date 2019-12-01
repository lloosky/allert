import Vue from 'vue';
import Vuex from 'vuex';
import router from './routes/router';

Vue.use(Vuex);

const API_HOST = process.env.VUE_APP_API_HOST;

export default new Vuex.Store({
  state: {
    listHeight: 0,
    componentLeft: -150,
    allerts: []
  },
  mutations: {},
  actions: {},
  getters: {}
});
