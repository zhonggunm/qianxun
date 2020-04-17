<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-attr="http://www.w3.org/1999/xhtml">
  <div class="container">

    <form class="form-inline text-right margin" role="form">

      <label>所有待寻宝贝</label>
        <div class="input-group">
          <input type="number" class="form-control input-lg" placeholder="输入宝贝标识"
            v-model="searchid">
          <span class="input-group-btn">
            <button class="btn btn-default btn-lg" type="button" @click="fetchItem">
              <span class="glyphicon glyphicon-search"></span></button>
          </span>
        </div>

      <page-controller :refresh.sync="refresh" :page.sync="page"
                       :finalpage.sync="finalpage"></page-controller>

    </form>

    <div class="row margin">
      <div class="col-sm-6 col-md-3" v-for="item in items.list">
        <div class="thumbnail" style="width: 180px; height: 320px">
          <a @click="selectItem(item.id)">
            <img :src="getRootPath()+'/file/image/item?type=160&filename='+item.image"
                 class="img-responsive" style="width: 180px; height: 180px"
                 @click="onItemClick(item)"
                 alt="../assets/images/logo.png"></a>
          <div class="caption">
            <h3>{{item.name}}</h3>
            <p>酬金 ¥{{item.reward}}</p>
            <p>失主 {{item.owner.mobile}}</p>
          </div>
        </div>
      </div>

      <item-detail-model id="itemdetailmodel" :item="items.selected"></item-detail-model>

    </div>

  </div>
</template>
<style>
.margin{
  margin-bottom:10px;
}
</style>
<script>
  import store from '../store';
  import ItemDetailModel from './item/ItemDetailModel.vue';
  import {getRootPath, postRequest} from '../utils/common.js';
  import PageController from './layout/PageController.vue';

  export default {

    name: 'home',

    data () {
      return {
        finalpage: true,
        status: 0,
        page: 0,
        categories: {
          selected: 0,
          list: []
        },
        items: {
          selected: {},
          list: []
        },
        area: 0,
        searchid: 0
      };
    },

    computed: {},
    components: {
      'item-detail-model': ItemDetailModel,
      'page-controller': PageController
    },

    methods: {
      getRootPath,
      postRequest,
      queryItems: function () {
        var obj = {
          ownerid: 0,
          category: this.categories.selected,
          status: 2,
          page: this.page
        };
        this.postRequest('/item/list', obj, this.onQueryItemSuccess, this.onFailure);
      },

      onQueryItemSuccess: function (res) {
        var list = res.data.data;
        this.finalpage = list.length < 32;
        this.items.list = list;
      },
      onFailure: function (res) {
      },

      fetchItem: function () {
        var obj = {
          id: this.searchid
        };
        this.postRequest('/item/list/id', obj, this.onFetchItemSuccess, this.onFailure);
      },

      onFetchItemSuccess: function (res) {
        var list = res.data.data;
        this.finalpage = list.length < 32;
        this.items.list = list;
      },

      onItemClick: function (selected) {
        if (store.state.user === undefined) {
          this.$router.go('/login');
        } else {
          var self = this;
          this.items.selected = selected;
          $('#itemdetailmodel').on('hidden.bs.modal', function () {
            self.queryItems();
          });
          $('#itemdetailmodel').modal();
        }
      },

      refresh: function () {
        this.queryItems();
      },

      selectItem: function (id) {}

    },

    ready: function () {
      this.refresh();
    }
  };
</script>
