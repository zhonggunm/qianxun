import store from './store';

export function configRouter (router) {

  // normal routes
  router.map({
    '/home': {
      component: require('./components/Homepage.vue')
    },
    '/login': {
      component: require('./components/account/Login.vue')
    },
    '/reg': {
      component: require('./components/account/Registration.vue')
    },
    '/userhome': {
      auth: true,
      component: require('./components/account/Userhome.vue')
    },
    '/mall': {
      component: require('./components/mall/Mall.vue')
    },
    '/cart': {
      component: require('./components/mall/Cart.vue')
    },
    '/additem/:itemid': {
      name: 'additem',
      auth: true,
      component: require('./components/item/AddItem.vue')
    },
    '/about': {
      component: require('./components/About.vue')
    },
    '*': {
      component: require('./components/NotFound.vue')
    }
  });

  // redirect
  router.redirect({
    '/': '/home'
  });

  // authonticate redirect
  router.beforeEach((transition) => {
    if (transition.to.auth === true && store.state.user === undefined) {
      transition.redirect('/login');
    } else {
      transition.next();
    }
  });
}
