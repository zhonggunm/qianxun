<template xmlns:v-bind="http://www.w3.org/1999/xhtml">

  <div>
    <form class="form-inline text-right margin" role="form">
      <page-controller :refresh.sync="refresh" :page.sync="page"
                       :finalpage.sync="finalpage"></page-controller>
    </form>
    <table class="table table-striped table-hover">
      <tr>
        <th width="15%">预览</th>
        <th width="40%">商品</th>
        <th width="15%">类型</th>
        <th width="15%">单价</th>
        <th width="15%">状态</th>
      </tr>
    </table>

    <order-panel v-for="order in orders" :order='order'></order-panel>

  </div>
</template>
<style>
  body{
    background-color:#ff0000;
  }
</style>
<script>
  import store from '../../store';
  import moment from 'moment';
  import {getOrderStatusMap} from '../../utils/statemap.js';
  import {getRootPath, postRequest} from '../../utils/common.js';
  import PageController from '../layout/PageController.vue';
  import OrderPanel from './OrderPanel.vue';

  export default{
    data () {
      return {
        msg: '',
        orders: [],
        page: 0,
        finalpage: false
      };
    },
    components: {
      'page-controller': PageController,
      'order-panel': OrderPanel
    },
    methods: {
      moment,
      getOrderStatusMap,
      getRootPath,
      postRequest,
      queryOrder: function () {
        var obj = {
          ownerid: store.state.user.id,
          status: 1,
          page: this.page
        };
        this.postRequest('/order/list', obj,
          this.onQueryOrderSuccess, this.onQueryOrderFailure);
      },
      onQueryOrderSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.orders = data.data;
        } else {
          this.msg = '提交失败：' + data.code;
        }
      },
      onQueryOrderFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      },

      refresh: function () {
        if (store.state.user === undefined) {
          this.$router.go('/login');
        } else {
          this.queryOrder();
        }
      }
    },
    ready: function () {
      this.queryOrder();
    }
  };
</script>
