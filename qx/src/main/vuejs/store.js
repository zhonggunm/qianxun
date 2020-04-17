import Vuex from 'vuex';
import Vue from 'vue';

Vue.use(Vuex);

var store = new Vuex.Store({
  state: {
    user: undefined,
    cart: new Map()
  },

  mutations: {
    SET_USER (state, user) {
      state.user = user;
    },
    ADD_TO_CART (state, key, value) {
      state.cart.set(key, value);
    },
    REMOVE_FROM_CART (state, key) {
      state.cart.delete(key);
    }
  }
});

export default store;
