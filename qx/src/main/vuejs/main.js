import Vue from 'vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
import { configRouter } from './routes';

require('es6-promise').polyfill();

// Bootstrap 4
require('bootstrap');

// debug mode
Vue.config.debug = false;

// install vue-resource
Vue.use(VueResource);

// install router
Vue.use(VueRouter);

// create router
const router = new VueRouter({
  history: true,
  saveScrollPosition: true
});

// configure router
configRouter(router);

// boostrap the app
router.start(require('./App'), 'app');

