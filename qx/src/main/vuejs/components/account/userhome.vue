<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="container">

    <div class="home row">

      <div class="col-xs-3 col-sm-3 col-md-3">
        <button type="button" class="btn btn-primary btn-lg btn-block margin"
                v-link="{ name: 'additem', params: {itemid:0} }">
          <span class="glyphicon glyphicon-file"></span> 新加宝贝
        </button>

        <div class="list-group margin">
          <a class="list-group-item" v-bind:class="{'bg-info':$index===selected}"
             v-for="item in menu.menuitems" @click="selectMenu($index)">
            {{item}}
          </a>
        </div>
      </div>

      <div class="col-xs-9 col-sm-9 col-md-9">
        <my-item-list v-if="selected==0"></my-item-list>
        <my-founder-list v-if="selected==1"></my-founder-list>
        <my-order v-if="selected==2"></my-order>
        <message-list v-if="selected==3"></message-list>
        <user-info v-if="selected==5"></user-info>
        <reset-password v-if="selected==6"></reset-password>
        <claim v-if="selected==7"></claim>
      </div>

    </div>
  </div>

</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import Claim from './../claim/Claim.vue';
  import UserInfo from './UserInfo.vue';
  import ResetPassword from './ResetPassword.vue';
  import MyOrder from '../mall/MyOrder.vue';
  import MyItemList from '../item/MyItemlist.vue';
  import MessageList from '../message/MessageList.vue';
  import MyFounderList from '../item/MyFounderList.vue';

  export default{
    data () {
      return {
        menu: {
          selected: 0,
          menuitems: ['我的宝贝', '找到的宝贝', '我的订单', '我的留言', '统计信息', '账号信息', '密码重置', '现金提取', '支付方式']
        }
      };
    },
    computed: {
      selected: {
        // getter
        get: function () {
          return this.menu.selected;
        },
        // setter
        set: function (newValue) {
          this.menu.selected = newValue;
        }
      }
    },
    components: {
      'claim': Claim,
      'user-info': UserInfo,
      'reset-password': ResetPassword,
      'my-order': MyOrder,
      'my-item-list': MyItemList,
      'message-list': MessageList,
      'my-founder-list': MyFounderList
    },
    methods: {
      selectMenu: function (select) {
        this.menu.selected = select;
      }
    }
  };
</script>
