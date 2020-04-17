<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">挂失 {{item.name}}</h4>
          <p class="help-block">{{msg}}</p>
        </div>
        <div class="modal-body">

          <common-item-view :item="item"></common-item-view>

          <form class="form-inline">
            <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox" v-model="hasReword"> 愿意支付酬谢金
                </label>
              </div>
              <div class="input-group">
                <div class="input-group-addon">¥</div>
                <input type="number" class="form-control" id="exampleInputAmount" v-model="reward"
                       v-bind:disabled="!hasReword">
                <div class="input-group-addon">.00</div>
              </div>
            </div>
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" @click="confirmLost">
            <span class="glyphicon glyphicon-plus"></span> 确定挂失
          </button>

        </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import CommonItemView from './CommonItemView.vue';
  import {postRequest} from '../../utils/common.js';

  export default{
    name: 'lostreport',
    props: ['item'],
    data () {
      return {
        reward: 0,
        msg: '',
        hasReword: false
      };
    },
    components: {
      'common-item-view': CommonItemView
    },
    methods: {
      postRequest,
      postItem: function () {
        var obj = {
          id: this.item.id,
          reward: this.reward
        };
        this.postRequest('/item/lost', obj, this.onSuccess, this.onFailure);
      },
      onSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          $('#itemdetailmodel').modal('hide');
        } else {
          // Error handler
          this.msg = 'Error: code=' + data.code;
        }
        this.working = false;
      },
      onFailure: function (resp) {
      },

      confirmLost: function () {
        this.postItem();
      }

    },

    ready: function () {
    }
  };
</script>
