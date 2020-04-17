<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-attr="http://www.w3.org/1999/xhtml">
  <div class="container">

    <div class="home row">

      <div class="col-xs-3 col-sm-3 col-md-3">
        <button type="button" class="btn btn-primary btn-lg btn-block margin" v-link="{ path: '/cart' }">
          <span class="glyphicon glyphicon-shopping-cart"></span> 购物车
          <span class="badge">{{cartSize}}</span>
        </button>
        <div class="list-group margin">
          <a class="list-group-item" v-bind:class="{'bg-info':$index===types.selected}"
             v-for="type in types.list" @click="selectType($index)">
            {{type.title}}
          </a>
        </div>
      </div>

      <div class="col-xs-9 col-md-9 col-md-9">
        <form class="form-inline text-right margin" role="form">
          <page-controller :refresh.sync="refresh" :page.sync="accessories.page"
                           :finalpage.sync="finalpage"></page-controller>
        </form>

        <div class="row margin">
          <div class="col-sm-6 col-md-3" v-for="accessory in accessories.list">
            <div class="thumbnail" style="width: 180px; height: 320px">
                <img :src="getRootPath()+'/file/accessoryimage?type=160&filename='+accessory.image"
                     class="img-responsive" style="width: 180px; height: 180px"
                     @click="onAccessoryClick(accessory)" alt="No image">
              <!--data-toggle="modal" data-target="#addtocart" data-accessory="accessory"-->
              <div class="caption">
                <h3>{{accessory.name}}</h3>
                <p>¥{{accessory.price}}</p>
                <p>{{accessory.type.title}}</p>
              </div>
            </div>
          </div>
        </div>

        <addtocart id="addtocart" :accessory="accessories.selected"></addtocart>

      </div>
    </div>

  </div>
</template>
<style>
.margin{
  margin-bottom:10px;
}
</style>
<script>
  import store from '../../store';
  import addtocart from './AddToCart.vue';
  import {getRootPath, postRequest} from '../../utils/common.js';
  import PageController from '../layout/PageController.vue';

  export default {

    name: 'mall',

    data () {
      return {
        finalpage: true,
        types: {
          selected: 0,
          list: []
        },
        accessories: {
          selected: {},
          page: 0,
          list: []
        }
      };
    },

    computed: {
      cartSize: {
        cache: false,
        get: function () {
          return store.state.cart.size;
        }
      }
    },
    components: {
      addtocart,
      'page-controller': PageController
    },

    methods: {
      getRootPath,
      postRequest,
      queryTypes: function () {
        this.$http.get(this.getRootPath() + '/accessory/type')
          .then(function (res) {
            this.types.list = res.data.data;
          }, function (res) {
            // Error handler
          });
      },

      queryItems: function () {
        var obj = {
          type: this.types.selected,
          page: this.accessories.page
        };
        this.postRequest('/accessory/list', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var list = resp.data.data;
        this.finalpage = list.length < 32;
        this.accessories.list = list;
      },
      onFailure: function (resp) {
      },
      selectType: function (selected) {
        this.types.selected = selected;
        this.queryItems();
      },

      onAccessoryClick: function (selected) {
        if (store.state.user === undefined) {
          this.$router.go('/login');
        } else {
          this.accessories.selected = selected;
          $('#addtocart').modal();
        }
      },
      showCart: function () {
      },

      refresh: function () {
        this.queryItems();
      }

    },

    ready: function () {
      this.queryTypes();
      this.refresh();
    }
  };
</script>
