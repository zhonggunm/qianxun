<template>
  <div>

    <form class="form-horizontal" role="form">

      <div class="form-group">
        <label for="inputEmail" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputEmail" v-model="mobile">
        </div>
      </div>
      <div class="form-group">
        <label for="inputPassword1" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword1" v-model="password1">
        </div>
      </div>
      <div class="form-group">
        <label for="inputPassword2" class="col-sm-2 control-label">重复密码</label>
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword2" v-model="password2">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="button" class="btn btn-default" @click="reg">注册</button>
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
  import {b64_md5} from '../../utils/md5.js';
  import {postRequest} from '../../utils/common.js';

  export default {
    name: 'register',
    data () {
      return {
        selected: 0,
        mobile: '',
        password1: '',
        password2: '',
        msg: ''
      };
    },
    computed: {
      account () {
        return store.state.account;
      }
    },
    methods: {
      b64_md5,
      postRequest,
      reg: function () {
        var obj = {
          mobile: this.mobile,
          password: b64_md5(this.password1)
        };
        this.postRequest('/user/reg', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.msg = '注册成功！';
          store.dispatch('SET_USER', data.data);
          this.$router.go('/home');
        } else {
          this.msg = '注册失败：' + data.code;
        }
      },
      onFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      }
    }
  };
</script>
