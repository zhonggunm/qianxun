<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">详细信息 {{item.name}}</h4>
          <p class="help-block">{{msg}}</p>
        </div>
        <div class="modal-body">

          <common-item-view :item="item"></common-item-view>

          <form class="form-inline">
            <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox" v-model="confirmed"> 我已核对信息。
                </label>
              </div>
            </div>
          </form>

          <form class="form-inline">
            <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox" v-model="hasMessage"> 附加消息
                </label>
              </div>
              <textarea class="form-control" rows="3" v-model="message"
                        v-bind:disabled="!hasMessage"></textarea>
            </div>
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" @click="confirmLost">
            <span class="glyphicon glyphicon-plus"></span> 确定通知失主
          </button>

        </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import store from '../../store';
  import CommonItemView from './CommonItemView.vue';
  import {postRequest} from '../../utils/common.js';

  export default{
    name: 'foundreport',
    props: ['item'],
    data () {
      return {
        msg: '',
        confirmed: false,
        hasMessage: false,
        message: ''
      };
    },
    components: {
      'common-item-view': CommonItemView
    },
    methods: {
      postRequest,
      confirmLost: function () {
        this.postItem();
        if (this.hasMessage) {
          this.newMessage();
        }
      },
      postItem: function () {
        var obj = {
          id: this.item.id,
          founderid: store.state.user.id
        };
        this.postRequest('/item/found', obj, this.onPostItemSuccess, this.onPostItemFailure);
      },
      onPostItemSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          $('#itemdetailmodel').modal('hide');
        } else {
          // Error handler
          this.msg = 'Error: code=' + data.code;
        }
        this.working = false;
      },
      onPostItemFailure: function (resp) {
      },
      newMessage: function () {
        var obj = {
          messageid: 0,
          fromid: store.state.user.id,
          toid: this.item.owner.id,
          itemid: this.item.id,
          content: this.message
        };
        this.postRequest('/message/create', obj, this.onNewMessageSuccess, this.onNewMessageFailure);
      },
      onNewMessageSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          $('#itemdetailmodel').modal('hide');
        } else {
          // Error handler
          this.msg = 'Error: code=' + data.code;
        }
        this.working = false;
      },
      onNewMessageFailure: function (resp) {
      }
    },

    ready: function () {
    }
  };
</script>
