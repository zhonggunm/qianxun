<template xmlns:padding="http://www.w3.org/1999/xhtml">
    <div>
      <img :src="getRootPath()+'/file/image/item?type=480&filename='+item.image"
           class="img-responsive" alt="No image">
      <div class="margin">
        <qrcode :val="qrlink"></qrcode>
      <div>
    </div>
</template>
<style>
  .div{
    padding:5px;
  }
  .margin {
    margin-bottom: 10px;
  }
</style>
<script>
  import qrcode from 'vue-qrcode';
  import {getRootPath} from '../../utils/common.js';

  export default{
    props: ['item'],
    methods: {
      getRootPath
    },
    computed: {
      qrlink: function () {
        return this.getRootPath() + '/qx?itemid=' + this.item.id;
      }
    },
    components: {
      qrcode
    },
    ready: function () {}
  };
</script>
