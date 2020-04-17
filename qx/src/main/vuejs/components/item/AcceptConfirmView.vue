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
                  <input type="checkbox" v-model="confirmed"> 已核对信息。
                </label>
              </div>
            </div>
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" @click="acceptConfirm">
            <span class="glyphicon glyphicon-plus"></span> 确定收取
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
    name: 'acceptConfirm',
    props: ['item'],
    data () {
      return {
        msg: '',
        confirmed: false
      };
    },
    components: {
      'common-item-view': CommonItemView
    },
    methods: {
      postRequest,
      acceptConfirm: function () {
        if (store.state.user === undefined) {
          this.$router.go('/login');
        } else {
          this.postItem();
        }
      },
      postItem: function () {
        var obj = {
          id: this.item.id
        };
        this.postRequest('/item/accept', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          $('#itemdetailmodel').modal('hide');
        } else {
          // Error handler
          this.msg = 'Error: code=' + data.code;
        }
      },
      onFailure: function (resp) {
      }
    },

    ready: function () {
    }
  };
</script>
