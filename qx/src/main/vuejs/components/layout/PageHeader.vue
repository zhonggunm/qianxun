<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="page-header">
    <!-- Fixed navbar -->
    <div class="pos-f-t">
      <div class="collapse" id="navbar-header">
        <div class="container bg-inverse p-a-1">
          <h3>Collapsed content</h3>
          <p>Toggleable via the navbar brand.</p>
        </div>
      </div>
      <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
          <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse"
                  data-target="#exCollapsingNavbar2">
            &#9776;
          </button>
          <div class="collapse navbar-toggleable-xs">
            <a class="navbar-brand nav-item" v-link="{ path: '/home' }">
              <img class="pull-xs-left header-logo" src="../../assets/images/logo.png">
              <span class="pull-xs-left m-l-1">千寻</span>
              <span class="pull-xs-none"></span>
            </a>
            <ul class="nav navbar-nav navbar-left">
              <li class="nav-item v-link-active">
                <a class="nav-link" v-link="{ path: '/home' }">首页</a>
              </li>
              <li class="nav-item v-link-active">
                <a class="nav-link" v-link="{ path: '/mall' }">商城</a>
              </li>
              <li class="nav-item v-link-active">
                <a class="nav-link" v-link="{ path: '/about' }">关于</a>
              </li>
              <li class="nav-item v-link-active">
                <a class="nav-link" v-bind:href="downloadLink">下载</a>
              </li>
            </ul>

            <ul class="nav navbar-nav navbar-right" v-if="loggedIn">
              <li class="nav-item dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <span class="glyphicon glyphicon-user"></span>{{user.mobile}}</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a v-link="{ path: '/userhome' }">
                      <span class="glyphicon glyphicon-cog"></span> 我的</a></li>
                    <li class="divider"></li>
                    <li><a href="#" @click="logout">
                      <span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
                  </ul>
              </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" v-else>
              <li class="nav-item">
                <a data-toggle="modal" data-target="#myModal">
                  <span class="glyphicon glyphicon-log-in"></span> 登录
                </a>
              </li>
              <li class="nav-item">
                <a v-link="{ path: '/reg' }">
                  <span class="glyphicon glyphicon-plus"></span> 注册
                </a>
              </li>
            </ul>

          </div>
        </div>
      </nav>
    </div>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">
            <span class="glyphicon glyphicon-log-in"></span> 登录</h4>
          <p class="help-block">{{msg}}</p>
        </div>
        <div class="modal-body">

          <form role="form" id="loginpanel">

            <div class="form-group">
              <label for="exampleInputEmail1"><span class="glyphicon glyphicon-user"></span> 用户名</label>
              <input type="text" class="form-control" id="exampleInputEmail1" v-model="mobile"
                     placeholder="6-16位数字字母组合">
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
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" v-link="{ path: '/reg' }" data-dismiss="modal">
            <span class="glyphicon glyphicon-plus"></span> 注册新账号</button>
          <button type="button" class="btn btn-primary" @click="login">
            <span class="glyphicon glyphicon-ok"></span> 登录</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import store from '../../store';
  import {b64_md5} from '../../utils/md5.js';
  import {postRequest, getAPIPath} from '../../utils/common.js';

  export default {
    name: 'page-header',
    data () {
      return {
        mobile: '',
        password: '',
        msg: ''
      };
    },
    computed: {
      user () {
        return store.state.user;
      },
      loggedIn () {
        return store.state.user !== undefined;
      },
      downloadLink () {
        return this.getAPIPath() + '/qx';
      }
    },
    methods: {
      b64_md5,
      getAPIPath,
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
          $('#myModal').modal('hide');
        } else {
          this.msg = '登录失败：' + data.code;
        }
      },
      onFailure: function (resp) {
        this.msg = resp.status + '服务器连接失败，请稍候重试！';
      },

      logout: function () {
        store.dispatch('SET_USER', undefined);
        this.$router.go('/home');
      }

    },
    ready: function () {
    }
  };

</script>
