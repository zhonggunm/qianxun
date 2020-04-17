<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div>
    <form class="form-inline text-right margin" role="form">
      <page-controller :refresh.sync="refresh" :page.sync="page"
                       :finalpage.sync="finalpage"></page-controller>
    </form>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>预览</th>
        <th>名称</th>
        <th>详细描述</th>
        <th>类型</th>
        <th>发布者</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>操作</th>

      </tr>
      </thead>
      <tbody v-for="item in items.list">
      <tr>
        <td><img :src="getRootPath()+'/file/image/item?type=80&filename='+item.image"
                 class="img-responsive" style="width: 60px; height: 48px"
                 alt="../assets/images/logo.png"></td>
        <td>{{item.name}}</td>
        <td>{{item.description}}</td>
        <td>{{item.category.title}}</td>
        <td>{{item.owner.mobile}}</td>
        <td>{{moment(item.creation).format('YYYY-MM-DD HH:mm:ss')}}</td>
        <td>{{getItemStatusMap(item.status)}}</td>
        <td>
          <div v-if="item.status===1">
            <a @click="lostReport(item)">挂失</a>
            <a v-link="{ name: 'additem',params:{itemid:item.id} }">重新关联</a>
          </div>
          <a @click="acceptConfirm(item)" v-if="item.status===5">收取</a>
        </td>
      </tr>
      </tbody>
    </table>

    <item-detail-model id="itemdetailmodel" :item="items.selected"></item-detail-model>

  </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import ItemDetailModel from './ItemDetailModel.vue';
  import {getItemStatusMap} from '../../utils/statemap.js';
  import moment from 'moment';
  import {getRootPath, postRequest} from '../../utils/common.js';
  import PageController from '../layout/PageController.vue';

  export default{
    data () {
      return {
        user: store.state.user,
        items: {
          selected: {},
          list: []
        },
        page: 0,
        finalpage: true,
        msg: '',
        status: 0
      };
    },
    components: {
      'item-detail-model': ItemDetailModel,
      'page-controller': PageController
    },
    computed: {},

    methods: {
      moment,
      getItemStatusMap,
      getRootPath,
      postRequest,
      queryItems: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          var obj = {
            ownerid: this.user.id,
            status: this.status,
            page: this.page
          };
          this.postRequest('/item/list', obj, this.onSuccess, this.onFailure);
        }
      },
      onSuccess: function (resp) {
        var list = resp.data.data;
        this.finalpage = list.length < 32;
        this.items.list = list;
      },
      onFailure: function (resp) {
      },
      lostReport: function (selected) {
        var self = this;
        this.items.selected = selected;
        $('#itemdetailmodel').on('hidden.bs.modal', function () {
          self.queryItems();
        });
        $('#itemdetailmodel').modal();
      },
      acceptConfirm: function (selected) {
        var self = this;
        this.items.selected = selected;
        $('#itemdetailmodel').on('hidden.bs.modal', function () {
          self.queryItems();
        });
        $('#itemdetailmodel').modal();
      },
      refresh: function () {
        this.queryItems();
      }
    },
    ready: function () {
      this.queryItems();
    }
  };
</script>
