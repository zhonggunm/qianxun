<template>
  <div>
    <form class="form-horizontal" role="form">

      <div class="form-group">
        <label for="inputPassword0" class="col-sm-3 control-label">旧密码</label>
        <div class="col-sm-6">
          <input type="password" class="form-control" id="inputPassword0" v-model="password0">
        </div>
      </div>
      <div class="form-group">
        <label for="inputPassword1" class="col-sm-3 control-label">新密码</label>
        <div class="col-sm-6">
          <input type="password" class="form-control" id="inputPassword1" v-model="password1">
        </div>
      </div>
      <div class="form-group">
        <label for="inputPassword2" class="col-sm-3 control-label">重复新密码</label>
        <div class="col-sm-6">
          <input type="password" class="form-control" id="inputPassword2" v-model="password2">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-6">
          <h3><span class="label label-info">{{msg}}</span></h3>
          <button type="button" class="btn btn-default" @click="submit">提交</button>
        </div>
      </div>
    </form>
  </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import {hex_md5} from '../../utils/md5.js';
  import {postRequest} from '../../utils/common.js';

  export default{
    data () {
      return {
        user: store.state.user,
        password0: '',
        password1: '',
        password2: '',
        msg: ''
      };
    },
    components: {
    },
    methods: {
      hex_md5,
      postRequest,
      validation: function () {
        var ret = '';
        if (this.password1 !== this.password2) {
          ret = '密码不一致';
        }
        return ret;
      },
      postPassword: function () {
        var obj = {
          id: this.user.id,
          oldpassword: hex_md5(this.password0),
          newpassword: hex_md5(this.password1)
        };
        this.postRequest('/user/pwd', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.msg = '修改成功！';
          store.dispatch('SET_PASSWORD', this.password1);
        } else {
          this.msg = '修改失败：' + data.code;
        }
      },
      onFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      },
      submit: function () {
        var ret = this.validation();
        if (ret === '') {
          this.postPassword();
        } else {
          this.msg = ret;
        }
      }
    }
  };
</script>
