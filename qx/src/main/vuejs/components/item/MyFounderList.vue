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
      </tr>
      </tbody>
    </table>

  </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import {getItemStatusMap} from '../../utils/statemap.js';
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
        status: 3
      };
    },
    components: {
      'page-controller': PageController
    },
    computed: {},

    methods: {
      getItemStatusMap,
      getRootPath,
      postRequest,
      queryItems: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          var obj = {
            founderid: this.user.id,
            status: this.status,
            page: this.page
          };
          this.postRequest('/item/list/byfounder', obj, this.onSuccess, this.onFailure);
        }
      },
      onSuccess: function (resp) {
        var list = resp.data.data;
        this.finalpage = list.length < 32;
        this.items.list = list;
      },
      onFailure: function (resp) {
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
