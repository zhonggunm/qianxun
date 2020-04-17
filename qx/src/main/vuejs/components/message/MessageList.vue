<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div>
    <form class="form-inline text-right margin" role="form">
      <page-controller :refresh.sync="refresh" :page.sync="page"
                       :finalpage.sync="finalpage"></page-controller>
    </form>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>关联宝贝</th>
        <th>内容</th>
        <th>发送者</th>
        <th>接收者</th>
        <th>创建时间</th>
        <th>操作</th>

      </tr>
      </thead>
      <tbody v-for="message in messages.list">
      <tr>
        <td>{{message.item.name}}</td>
        <td>{{message.content}}</td>
        <td>{{message.fromuser.mobile}}</td>
        <td>{{message.touser.mobile}}</td>
        <td>{{moment(message.creation).format('YYYY-MM-DD HH:mm:ss')}}</td>
        <td><a @click="onReplyClick(message)">回复</a>
          <a @click="onDeleteClick(message)">删除</a></td>
      </tr>
      </tbody>
    </table>

    <new-message id="newmessage" :message="messages.selected"></new-message>

  </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import moment from 'moment';
  import {getRootPath, postRequest} from '../../utils/common.js';
  import NewMessage from './NewMessage.vue';
  import PageController from '../layout/PageController.vue';

  export default{
    data () {
      return {
        user: store.state.user,
        messages: {
          selected: {},
          list: []
        },
        page: 0,
        finalpage: true,
        msg: '',
        status: 0
      };
    },
    components: {
      'new-message': NewMessage,
      'page-controller': PageController
    },
    computed: {},

    methods: {
      moment,
      getRootPath,
      postRequest,
      queryMessages: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          var obj = {
            uid: this.user.id,
            page: this.page
          };
          this.postRequest('/message/list/byuser', obj, this.onListSuccess, this.onFailure);
        }
      },
      onListSuccess: function (resp) {
        var list = resp.data.data;
        this.finalpage = list.length < 32;
        this.messages.list = list;
      },
      onFailure: function (resp) {
      },
      onReplyClick: function (selected) {
        this.messages.selected = selected;
        var self = this;
        $('#newmessage').on('hidden.bs.modal', function () {
          self.queryMessages();
        });
        $('#newmessage').modal();
      },
      onDeleteClick: function (message) {
      }
    },
    ready: function () {
      this.queryMessages();
    }
  };
</script>
