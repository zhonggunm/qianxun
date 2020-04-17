<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <form class="form-inline">

        <div class="row">
          <div class="col-xs-6 col-md-4">
            {{moment(order.creation).format('YYYY-MM-DD HH:mm:ss')}}   订单号：{{order.id}}</div>
          <div class="col-xs-6 col-md-4"></div>
          <div class="col-xs-3 col-md-2">{{order.amount}}件,总价:¥{{order.total}}</div>
          <div class="col-xs-3 col-md-2">{{getOrderStatusMap(order.status)}}</div>
        </div>
      </form>
    </div>

    <!-- Table -->
    <table class="table table-striped table-hover">
      <tr v-for="item in items">
        <td width="15%">
          <img :src="getRootPath()+'/file/accessoryimage?type=80&filename='+item.accessory.image"
             class="img-responsive" style="width: 60px; height: 48px"
             alt="../../assets/images/logo.png"></td>
        <td width="40%">{{item.accessory.name}}</td>
        <td width="15%">{{item.accessory.type.title}}</td>
        <td width="15%">¥{{item.accessory.price}}</td>
        <td width="15%">{{item.amount}}</td>
      </tr>
    </table>
  </div>
</template>

<style>
  body{
    background-color:#ff0000;
  }
</style>

<script>
  import moment from 'moment';
  import {getOrderStatusMap} from '../../utils/statemap.js';
  import {getRootPath, postRequest} from '../../utils/common.js';

  export default{
    name: 'orderpanel',
    props: ['order'],
    data () {
      return {
        items: [],
        msg: ''
      };
    },
    methods: {
      moment,
      getRootPath,
      postRequest,
      getOrderStatusMap,
      queryOrderDetail: function () {
        var obj = {
          orderid: this.order.id
        };
        this.postRequest('/order/detail', obj,
          this.onQueryOrderDetailSuccess, this.onQueryOrderDetailFailure);
      },
      onQueryOrderDetailSuccess: function (resp) {
        var data = resp.data;

        if (data.code === 'NoError') {
          this.items = data.data;
        } else {
          this.msg = '提交失败：' + data.code;
        }
      },

      onQueryOrderDetailFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      }
    },
    ready: function () {
      this.queryOrderDetail();
    }
  };
</script>
