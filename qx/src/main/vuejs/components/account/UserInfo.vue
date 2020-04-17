<template xmlns:v-bind="http://www.w3.org/1999/xhtml">

  <form class="form-horizontal" role="form">

    <div class="form-group">
      <label for="userid" class="col-sm-3 control-label">用户名</label>
      <div class="col-sm-9">
        <label class="control-label" id="userid">{{user.mobile}} (ID:{{user.id}})</label>
      </div>
    </div>

    <div class="form-group">
      <label for="dropdownSex" class="col-sm-3 control-label">性别</label>
      <div class="input-group-btn col-sm-9 dropdown" id="dropdownSex">
        <select class="form-control" v-model="user.sex" v-bind:disabled="!editmode">
          <option value="true">男</option>
          <option value="false">女</option>
        </select>
      </div>
    </div>

    <div class="form-group">
      <label for="inputNickname" class="col-sm-3 control-label">昵称</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputNickname" v-bind:disabled="!editmode"
               v-model="user.nickname">
      </div>
    </div>

    <div class="form-group">
      <label for="inputEmail" class="col-sm-3 control-label">Email</label>
      <div class="col-sm-9">
        <input type="email" class="form-control" id="inputEmail" v-bind:disabled="!editmode"
               v-model="user.mail">
      </div>
    </div>

    <div class="form-group">
      <label for="inputMobile" class="col-sm-3 control-label">手机</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputMobile" v-bind:disabled="!editmode"
               v-model="user.mobile">
      </div>
    </div>

    <div class="form-group">
      <label for="inputPhone" class="col-sm-3 control-label">固定电话</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputPhone" v-bind:disabled="!editmode"
               v-model="user.phone">
      </div>
    </div>

    <div class="form-group">
      <label for="inputQQ" class="col-sm-3 control-label">QQ</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputQQ" v-bind:disabled="!editmode"
               v-model="user.qq">
      </div>
    </div>

    <div class="form-group">
      <label for="inputWechat" class="col-sm-3 control-label">微信</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputWechat" v-bind:disabled="!editmode"
               v-model="user.wechat">
      </div>
    </div>

    <div class="form-group">
      <label for="inputAddress" class="col-sm-3 control-label">地址</label>
      <div class="col-sm-9">
        <input type="email" class="form-control" id="inputAddress" v-bind:disabled="!editmode"
               v-model="user.address">
      </div>
    </div>

    <div class="form-group">
      <label for="inputArea" class="col-sm-3 control-label">地区</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputArea" v-bind:disabled="!editmode"
               v-model="user.area.name">
      </div>
    </div>

    <div class="form-group">
      <label for="inputStatus" class="col-sm-3 control-label">状态</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputStatus" v-bind:disabled="!editmode"
               v-model="user.staus">
      </div>
    </div>

    <div class="form-group">
      <label for="inputVocation" class="col-sm-3 control-label">职业</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputVocation" v-bind:disabled="!editmode"
               v-model="user.vocation">
      </div>
    </div>

    <div class="form-group">
      <label for="dropdownInterest" class="col-sm-3 control-label">兴趣</label>
      <div class="input-group-btn col-sm-9 dropdown" id="dropdownInterest">
        <select class="form-control" v-model="user.interested" v-bind:disabled="!editmode">
          <option value="0">男</option>
          <option value="1">女</option>
        </select>
      </div>
    </div>

    <div class="form-group">
      <label for="inputClaimed" class="col-sm-3 control-label">已支取</label>
      <div class="col-sm-9">
        <label class="control-label" id="inputClaimed">{{user.claimed}}</label>
      </div>
    </div>

    <div class="form-group">
      <label for="inputCredit" class="col-sm-3 control-label">积分</label>
      <div class="col-sm-9">
        <label class="control-label" id="inputCredit">{{user.credit}}</label>
      </div>
    </div>

    <div class="form-group">
      <label for="inputCreation" class="col-sm-3 control-label">创建时间</label>
      <div class="col-sm-9">
        <label class="control-label" id="inputCreation">{{user.creation}}</label>
      </div>
    </div>

    <div class="form-group">
      <label for="inputLastaccess" class="col-sm-3 control-label">最后访问时间</label>
      <div class="col-sm-9">
        <label class="control-label" id="inputLastaccess">{{user.lastaccess}}</label>
      </div>
    </div>

    <div class="form-group">
      <label for="inputBalance" class="col-sm-3 control-label">余额</label>
      <div class="col-sm-9">
        <label class="control-label" id="inputBalance">{{user.balance}}</label>
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-3 col-sm-9">
        <button type="button" class="btn btn-primary" @click="onButtonClick">{{buttonText}}</button>
      </div>
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
  import {getRootPath} from '../../utils/common.js';

  export default {
    data () {
      return {
        editmode: false,
        user: store.state.user,
        msg: ''
      };
    },
    computed: {
      buttonText () {
        return this.editmode ? '提交' : '编辑';
      }
    },
    components: {
    },
    methods: {
      getRootPath,
      onButtonClick: function () {
        if (this.editmode) {
          this.postUser();
        }
        this.editmode = !this.editmode;
      },
      postUser: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          $.ajax({
            type: 'POST',
            url: this.getRootPath() + '/user/update',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(this.user),
            success: function (res) {
              this.msg = res.data.code;
            }
          });
        }
      }
    }
  };
</script>
