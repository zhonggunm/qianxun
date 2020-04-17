<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div>
    <form class="form-inline text-right margin" role="form">
      <page-controller :refresh.sync="refresh" :page.sync="page"
                       :finalpage.sync="finalpage"></page-controller>
      <button type="button" class="btn btn-default btn-lg" @click="openCreateClaimDialog">
        <span class="glyphicon glyphicon-refresh"></span> 申请现金提取
      </button>
    </form>
    <h3>当前账户余额(¥): {{user.balance}}</h3>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>申请时间</th>
        <th>数量</th>
        <th>状态</th>
        <th>备注</th>
      </tr>
      </thead>
      <tbody v-for="claim in claims.list">
      <tr>
        <td>{{moment(claim.claimdate).format('YYYY-MM-DD HH:mm:ss')}}</td>
        <td>{{claim.amount}}</td>
        <td>{{getClaimStatusMap(claim.status)}}</td>
        <td>{{claim.remark}}</td>
      </tr>
      </tbody>
    </table>
    <new-claim id="newclaim"></new-claim>
  </div>

</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import NewClaim from './NewClaim.vue';
  import moment from 'moment';
  import {getClaimStatusMap} from '../../utils/statemap.js';
  import {postRequest} from '../../utils/common.js';
  import PageController from '../layout/PageController.vue';

  export default{
    name: 'claim',
    data () {
      return {
        user: store.state.user,
        msg: '',
        finalpage: true,
        claims: {
          selected: -1,
          list: []
        },
        page: 0
      };
    },
    components: {
      'new-claim': NewClaim,
      'page-controller': PageController
    },
    methods: {
      moment,
      getClaimStatusMap,
      postRequest,
      getClaimList: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          var obj = {
            uid: this.user.id,
            page: this.page
          };
          this.postRequest('/claim/query', obj, this.onSuccess, this.onFailure);
        }
      },
      onSuccess: function (res) {
        var list = res.data.data;
        this.finalpage = list.length < 32;
        this.claims.list = list;
      },
      onFailure: function (resp) {
      },

      openCreateClaimDialog: function () {
        $('#newclaim').modal();
        $('#newclaim').on('shown.bs.modal', function () {
        });
      },

      refresh: function () {
        this.getClaimList();
      }
    },
    ready: function () {
      this.getClaimList();
    }
  };
</script>
