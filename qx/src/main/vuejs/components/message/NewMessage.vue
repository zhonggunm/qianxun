<template xmlns:v-bind="http://www.w3.org/1999/xhtml" >
    <div class="modal fade" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">消息回复 {{item.name}}</h4>
            <p class="help-block">{{msg}}</p>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">回复给</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="inputEmail3"
                         v-model="message.fromuser.mobile" disabled=true>
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">关联物品</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="inputPassword3"
                         v-model="message.item.name" disabled=true>
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">内容</label>
                <div class="col-sm-10">
                  <textarea class="form-control" rows="5" v-model="content"></textarea>
                </div>
              </div>
            </form>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" @click="onReplyClick">
              <span class="glyphicon glyphicon-plus"></span> 回复
            </button>

          </div>
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
  import store from '../../store';
  import {postRequest} from '../../utils/common.js';

  export default {
    props: ['message'],
    data () {
      return {
        user: store.state.user,
        msg: '',
        content: ''
      };
    },
    components: {},
    methods: {
      postRequest,
      onReplyClick: function () {
        if (this.user === undefined) {
          this.$router.go('/login');
        } else {
          var obj = {
            messageid: this.message.id,
            fromid: this.user.id,
            toid: this.message.fromuser.id,
            itemid: this.message.item.id,
            content: this.content
          };
          this.postRequest('/message/create', obj, this.onSuccess, this.onFailure);
        }
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          $('#newmessage').modal('hide');
        } else {
          // Error handler
          this.msg = 'Error: code=' + data.code;
        }
      },
      onFailure: function () {
      }
    }
  };
</script>
