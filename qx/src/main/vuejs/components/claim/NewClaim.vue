<template>
  <div class="modal fade" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">
            <span class="glyphicon glyphicon-log-in"></span> 请求现金提取</h4>
          <p class="help-block">{{msg}}</p>
        </div>
        <div class="modal-body">

          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label for="amountList" class="col-sm-2 control-label">金额(¥)</label>
              <div class="col-sm-10">
                <select class="form-control" id="amountList" v-model="selected">
                  <option>50</option>
                  <option>100</option>
                  <option>200</option>
                  <option>300</option>
                  <option>500</option>
                </select>
              </div>
            </div>
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" @click="createClaim">
            <span class="glyphicon glyphicon-plus"></span> 提交
          </button>

        </div>
      </div>
    </div>

  </div></template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import {postRequest} from '../../utils/common.js';

  export default{
    data () {
      return {
        msg: '',
        selected: '',
        user: store.state.user
      };
    },
    computed: {},
    methods: {
      postRequest,
      createClaim: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          var obj = {
            uid: this.user.id,
            amount: self.selected
          };
          this.postRequest('/claim/create', obj, this.onSuccess, this.onFailure);
        }
      },
      onSuccess: function (res) {
        switch (res.data.code) {
          case 'NoError':
            this.msg = '提交成功，请等待处理！';
            break;
          default:
            this.msg = '错误：' + res.data.data.code;
            break;
        }
      },
      onFailure: function (res) {
      }
    }
  };
</script>
