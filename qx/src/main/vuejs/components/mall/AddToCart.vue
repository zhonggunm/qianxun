<template>
  <div class="modal fade" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">{{accessory.name}}</h4>
          <p class="help-block">{{msg}}</p>
        </div>
        <div class="modal-body">

          <img :src="getRootPath()+'/file/accessoryimage?type=480&filename='+accessory.image"
               class="img-responsive" alt="No image">

          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label for="amount" class="col-sm-2 control-label">数量</label>
              <div class="col-sm-4">
                <input type="number" class="form-control" id="amount" v-model="amount">
              </div>
            </div>
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" @click="addToCart">
            <span class="glyphicon glyphicon-plus"></span> 添加到购物车
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
  import {getRootPath} from '../../utils/common.js';

  export default{
    name: 'addtocart',
    props: ['accessory'],
    data () {
      return {
        amount: 5,
        msg: ''
      };
    },
    methods: {
      getRootPath,
      addToCart: function () {
        store.dispatch('ADD_TO_CART', this.accessory, this.amount);
        $('#addtocart').modal('hide');
      }
    },

    ready: function () {
    }
  };
</script>
