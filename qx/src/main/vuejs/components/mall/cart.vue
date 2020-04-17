<template>
  <div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">我的购物车 <p>{{msg}}</p>
    </div>
    <!-- Table -->
    <table class="table table-striped table-hover">
      <tr>
        <th>预览</th>
        <th>名字</th>
        <th>描述</th>
        <th>类型</th>
        <th>单价</th>
        <th>数量</th>
        <th>操作</th>
      </tr>
      <tr v-for="item in cart">
        <td><img :src="getRootPath()+'/file/accessoryimage?type=80&filename='+item.key.image"
                 class="img-responsive" style="width: 60px; height: 48px"
                 alt="../assets/images/logo.png"></td>
        <td>{{item.key.name}}</td>
        <td>{{item.key.description}}</td>
        <td>{{item.key.type.title}}</td>
        <td>{{item.key.price}}</td>
        <td>{{item.value}}</td>
        <td><a @click="deleteItem(item.key)">删除</a></td>
      </tr>
    </table>

    <div class="panel-footer">
      <p>总计</p>
      <button type="button" class="btn btn-default" @click="submitOrder">
        <span class="glyphicon glyphicon-plus"></span> 提交
      </button>
    </div>
  </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import {getRootPath, postRequest} from '../../utils/common.js';

  export default{
    data () {
      return {
        msg: ''
      };
    },
    computed: {
      cart () {
        var list = [];
        var cart = store.state.cart;
        for (var [key, value] of cart) {
          list.push({key, value});
        }
        return list;
      }
    },
    methods: {
      getRootPath,
      postRequest,
      deleteItem: function (item) {
        store.dispatch('REMOVE_FROM_CART', item);
      },

      postOrder: function (list) {
        var obj = {
          ownerid: store.state.user.id,
          items: list
        };
        this.postRequest('/order/new', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.msg = '提交成功！';
        } else {
          this.msg = '提交失败：' + data.code;
        }
      },
      onFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      },

      buildOrder: function () {
        var list = [];
        var cart = store.state.cart;
        for (var [key, value] of cart) {
          var aid = key.id;
          var aprice = key.price;
          var amount = value;
          list.push({aid, aprice, amount});
        }
        return list;
      },

      submitOrder: function () {
        if (store.state.user === undefined) {
          this.$router.go('/login');
        } else {
          var list = this.buildOrder();
          this.postOrder(list);
        }
      }
    }
  };
</script>
