<template>
  <form role="form">
    <h3>请登录</h3>
    <p>{{msg}}</p>
    <div class="form-group">
      <label for="exampleInputEmail1"><span class="glyphicon glyphicon-user"></span> 用户名</label>
      <input type="text" class="form-control" id="exampleInputEmail1" v-model="mobile"
             placeholder="手机号">
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1"><span class="glyphicon glyphicon-lock"></span> 密码</label>
      <input type="password" class="form-control" id="exampleInputPassword1" v-model="password"
             placeholder="6-16位，英文（区分大小写）、数字或常用符号">
    </div>
    <div class="checkbox">
      <label>
        <input type="checkbox"> 下次自动登录
      </label>
    </div>
    <div>
      <button type="button" class="btn btn-default" v-link="{ path: '/reg' }" data-dismiss="modal">
        <span class="glyphicon glyphicon-plus"></span> 注册新账号
      </button>
      <button type="button" class="btn btn-primary" @click="login">
        <span class="glyphicon glyphicon-ok"></span> 登录
      </button>
    </div>
  </form>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import {b64_md5} from '../../utils/md5.js';
  import {postRequest} from '../../utils/common.js';

  export default {
    data () {
      return {
        mobile: '',
        password: '',
        msg: ''
      };
    },
    components: {},
    methods: {
      b64_md5,
      postRequest,
      login: function () {
        var obj = {
          mobile: this.mobile,
          password: b64_md5(this.password)
        };
        this.postRequest('/user/login', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.msg = '登录成功！';
          store.dispatch('SET_USER', data.data);
          window.history.go(-1);
        } else {
          this.msg = '登录失败：' + data.code;
        }
      },
      onFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      }
    }
  };
</script>
